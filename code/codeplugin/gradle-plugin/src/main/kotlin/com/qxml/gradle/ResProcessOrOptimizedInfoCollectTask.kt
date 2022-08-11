package com.qxml.gradle

import com.google.gson.Gson
import org.gradle.api.DefaultTask
import org.gradle.api.file.FileCollection
import org.gradle.api.tasks.*
import zhao.arsceditor.AndrolibResources
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.zip.ZipFile

private const val ARSC_ANME = "resources.arsc"

//4.2+ OptimizeResourcesTask 资源路径压缩
@CacheableTask
open class ResProcessOrOptimizedInfoCollectTask : DefaultTask() {

    private val mAndRes by lazy(LazyThreadSafetyMode.NONE) { AndrolibResources() }

    @get:Input
    var isOptimized: Boolean? = null

    @get:Input
    var buildType: String? = null

    @get:OutputFile
    var outputFile: File? = null

    @get:InputFiles
    @get:PathSensitive(PathSensitivity.NONE)
    var apFiles: FileCollection? = null

    @Suppress("unused")
    @TaskAction
    fun collect() {
        val arscFileName = "${buildType}_resources.arsc"
        val arscFile = outputFile!!.parentFile.resolve(arscFileName)
        arscFile.delete()
        apFiles!!.files.forEach { typeDir ->
            if (typeDir.isDirectory && typeDir.exists()) {
                val optimizedAp = typeDir.resolve("resources-${buildType}-optimize.ap_")
                val normalAp = typeDir.resolve("resources-${buildType}.ap_")

                val apFile = if (isOptimized!!) {
                    if (optimizedAp.exists()) optimizedAp else null
                } else {
                    if (normalAp.exists()) normalAp else null
                }
                apFile?.apply {
                    val zipFile = ZipFile(this)
                    val entries = zipFile.entries()
                    while (entries.hasMoreElements()) {
                        val entry = entries.nextElement()
                        val name = entry.name
                        if (name == ARSC_ANME) {
                            arscFile.createNewFile()
                            FileOutputStream(arscFile).use { fos ->
                                zipFile.getInputStream(entry).use { ins ->
                                    fos.write(ins.readBytes())
                                }
                            }
                            break
                        }
                    }
                    zipFile.close()
                    if (arscFile.exists()) {
                        val layoutTypeInfoMap = mutableMapOf<String, MutableMap<String, String>>()
                        FileInputStream(arscFile).use {
                            try {
                                mAndRes.decodeARSC(mAndRes.getResTable(it)) { config, _, key, value ->
                                    var map = layoutTypeInfoMap[key]
                                    if (map == null) {
                                        map = mutableMapOf()
                                        layoutTypeInfoMap[key] = map
                                    }
                                    map["layout${config}"] = value
                                }
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                        if (!outputFile!!.exists()) {
                            outputFile!!.createNewFile()
                        }
                        FileOutputStream(outputFile!!).use {
                            it.bufferedWriter().use { bufferedWriter ->
                                bufferedWriter.write(Gson().toJson(layoutTypeInfoMap))
                            }
                        }
                    }
                    return@forEach
                }
            }
        }
    }
}