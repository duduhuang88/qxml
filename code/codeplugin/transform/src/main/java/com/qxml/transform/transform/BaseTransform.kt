package com.qxml.transform.transform

import com.android.SdkConstants
import com.android.build.api.transform.*
import com.android.build.gradle.internal.pipeline.TransformManager
import com.android.builder.utils.isValidZipEntryName
import com.android.ide.common.internal.WaitableExecutor
import com.google.common.io.Files
import com.qxml.constant.Constants
import com.qxml.tools.log.LogUtil
import org.apache.commons.codec.digest.DigestUtils
import org.apache.commons.io.FileUtils
import java.io.*
import java.util.function.BiConsumer
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream
import java.util.zip.ZipOutputStream

//CustomClassTransform
abstract class BaseTransform: Transform() {
    override fun getName(): String = this::class.java.simpleName

    //todo use newest api
    internal val waitableExecutor = WaitableExecutor.useGlobalSharedThreadPool()

    override fun getInputTypes(): MutableSet<QualifiedContent.ContentType> = TransformManager.CONTENT_JARS

    override fun getScopes(): MutableSet<in QualifiedContent.Scope> = TransformManager.SCOPE_FULL_PROJECT

    override fun isIncremental(): Boolean = true

    abstract fun provideFunction(): BiConsumer<InputStream, OutputStream>?

    private val async = true

    override fun transform(transformInvocation: TransformInvocation) {
        super.transform(transformInvocation)
        val time = System.currentTimeMillis()
        val inputs = transformInvocation.inputs
        val outputProvider = transformInvocation.outputProvider
        val isIncremental = transformInvocation.isIncremental
        if(!isIncremental) {
            outputProvider.deleteAll()
        }
        initTransform(transformInvocation)

        val transTime = System.currentTimeMillis()

        val function = provideFunction()
        for (ti in inputs) {
            for (jarInput in ti.jarInputs) {
                val inputJar = jarInput.file
                var destName = jarInput.file.name
                val hexName = DigestUtils.md5Hex(jarInput.file.absolutePath).substring(0, 8)
                if (destName.endsWith(SdkConstants.DOT_JAR)) {
                    destName = destName.substring(0, destName.length - 4)
                }
                val outputJar: File = outputProvider.getContentLocation(
                    destName + hexName, jarInput.contentTypes, jarInput.scopes, Format.JAR
                )
                if (isIncremental) {
                    when (jarInput.status ?: Status.NOTCHANGED) {
                        Status.NOTCHANGED -> {
                            if (shouldProcessNotChangeJar(jarInput)) {
                                //LogUtil.d("processNotChangeJar "+jarInput.name)
                                FileUtils.forceDelete(outputJar)
                                execTransformJar(function, inputJar, outputJar)
                            }
                        }
                        Status.ADDED, Status.CHANGED -> {
                            execTransformJar(function, inputJar, outputJar)
                        }
                        Status.REMOVED -> FileUtils.forceDelete(outputJar)
                    }
                } else {
                    execTransformJar(function, inputJar, outputJar)
                }
            }

            for (di in ti.directoryInputs) {
                val outputDir: File = outputProvider.getContentLocation(
                    di.name,
                    di.contentTypes,
                    di.scopes,
                    Format.DIRECTORY
                )
                val inputDir = di.file
                if (isIncremental) {
                    for ((inputFile, value) in di.changedFiles) {
                        when (value) {
                            null, Status.NOTCHANGED -> { }
                            Status.ADDED, Status.CHANGED -> {
                                if (!inputFile.isDirectory && inputFile.name.endsWith(SdkConstants.DOT_CLASS)) {
                                    val outFile = toOutputFile(outputDir, inputDir, inputFile)
                                    execTransformFile(function, inputFile, outFile)
                                }
                            }
                            Status.REMOVED -> {
                                val outputFile = toOutputFile(outputDir, inputDir, inputFile)
                                com.android.utils.FileUtils.deleteIfExists(outputFile)
                            }
                        }
                    }
                } else {
                    for (input in com.android.utils.FileUtils.getAllFiles(di.file)) {
                        if (input.name.endsWith(SdkConstants.DOT_CLASS)) {
                            val outFile = toOutputFile(outputDir, inputDir, input)
                            execTransformFile(function, input, outFile)
                        }
                    }
                }
            }
        }
        if (async) {
            waitableExecutor.waitForTasksWithQuickFail<Any>(true)
        }
        finishTransform()
        val spendTime = System.currentTimeMillis() - time
        val transSpend = System.currentTimeMillis() - transTime
        LogUtil.pl("code transform time cost: "+transSpend + "ms")
        LogUtil.pl("all transform time cost: "+spendTime + "ms")
    }

    abstract fun initTransform(transformInvocation: TransformInvocation)
    abstract fun finishTransform()
    abstract fun shouldProcessNotChangeJar(inputJar: JarInput): Boolean
    abstract fun shouldJarEntryPackage(entry: ZipEntry): Boolean

    private fun execTransformJar(function: BiConsumer<InputStream, OutputStream>?, inputJar: File, outputJar: File) {
        if (async) {
            waitableExecutor.execute {
                try {
                    transformJar(function, inputJar, outputJar)
                } catch (e: Exception) {
                    LogUtil.pl("transform jar err "+inputJar.absolutePath+" "+outputJar.absolutePath)
                    e.printStackTrace()
                    throw e
                }
            }
        } else {
            transformJar(function, inputJar, outputJar)
        }
    }

    private fun execTransformFile(function: BiConsumer<InputStream, OutputStream>?, inputFile: File, outputFile: File) {
        if (async) {
            waitableExecutor.execute {
                transformFile(function, inputFile, outputFile)
            }
        } else {
            transformFile(function, inputFile, outputFile)
        }
    }

    @Throws(IOException::class)
    private fun transformJar(function: BiConsumer<InputStream, OutputStream>?, inputJar: File, outputJar: File) {
        //LogUtil.d("transformJar "+inputJar.absolutePath)
        Files.createParentDirs(outputJar)
        FileInputStream(inputJar).use { fis ->
            ZipInputStream(fis).use { zis ->
                FileOutputStream(outputJar).use { fos ->
                    ZipOutputStream(fos).use { zos ->
                        var entry = zis.nextEntry
                        while (entry != null && isValidZipEntryName(entry)) {
                            if (shouldJarEntryPackage(entry)) {
                                val nextEntry = ZipEntry(entry.name)
                                nextEntry.time = -1L
                                if (!entry.isDirectory && entry.name.endsWith(SdkConstants.DOT_CLASS)) {
                                    // Any negative time value sets ZipEntry's xdostime to DOSTIME_BEFORE_1980
                                    // constant.
                                    zos.putNextEntry(nextEntry)
                                    apply(function, entry.name.toClassName(), zis, zos)
                                } else {
                                    zos.putNextEntry(nextEntry)
                                    zos.write(zis.readBytes())
                                }
                            }
                            entry = zis.nextEntry
                        }
                    }
                }
            }
        }
    }

    private fun toOutputFile(outputDir: File, inputDir: File, inputFile: File): File {
        return File(
            outputDir, inputFile.toRelativeString(inputDir)
        )
    }

    @Throws(IOException::class)
    open fun transformFile(function: BiConsumer<InputStream, OutputStream>?, inputFile: File, outputFile: File) {
        Files.createParentDirs(outputFile)

        FileInputStream(inputFile).use { fis ->
            FileOutputStream(outputFile).use { fos -> apply(
                function,
                inputFile.name.toClassName(),
                fis,
                fos
            ) }
        }
    }

    @Throws(IOException::class)
    open fun apply(function: BiConsumer<InputStream, OutputStream>?, name: String, input: InputStream, output: OutputStream) {
        try {
            //LogUtil.d("apply transform "+name)
            function?.accept(input, output)
        } catch (e: UncheckedIOException) {
            e.printStackTrace()
            throw e.cause!!
        }
    }

    private fun String.toClassName(): String {
        val end = length - 6 // .class length = 6
        return substring(0, end).replace('\\', '.').replace('/', '.')
    }

}