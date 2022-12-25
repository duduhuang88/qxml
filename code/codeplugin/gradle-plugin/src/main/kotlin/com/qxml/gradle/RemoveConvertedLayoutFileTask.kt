package com.qxml.gradle

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.qxml.tools.log.LogUtil
import com.qxml.transform.generate.GenResult
import com.qxml.transform.generate.ViewGenResultInfo
import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.*
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.LinkedHashMap
import java.util.zip.Deflater
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream
import java.util.zip.ZipOutputStream

@CacheableTask
abstract class RemoveConvertedLayoutFileTask : DefaultTask() {

    @get:Input
    var isOptimized: Boolean = false

    @get:InputFile
    @get:PathSensitive(PathSensitivity.NONE)
    lateinit var resProcessOutputFile: File

    @get:Input
    var enable: Boolean = true

    @get:InputFile
    @get:PathSensitive(PathSensitivity.NONE)
    lateinit var layoutTypeInfoFile: File

    @get:InputFile
    @get:PathSensitive(PathSensitivity.NONE)
    lateinit var relativeIncludeLayoutInfoFile: File

    @get:InputFile
    @get:PathSensitive(PathSensitivity.NONE)
    lateinit var apFile: File

    @InputFile
    @PathSensitive(PathSensitivity.NONE)
    @Optional
    abstract fun getWhiteListFile(): RegularFileProperty

    @TaskAction
    fun removeConvertedLayoutFile() {
        if (enable) {
            val whiteListFile = getWhiteListFile().orNull
            val removeWhiteListMap = hashMapOf<String, Boolean>()
            whiteListFile?.asFile?.readLines()?.forEach {
                removeWhiteListMap[it.trim()] = true
            }
            val gson = Gson()
            val layoutInfoMap: HashMap<String, HashMap<String, ViewGenResultInfo>> = gson.fromJson(layoutTypeInfoFile.readText(), object : TypeToken<HashMap<String, HashMap<String, ViewGenResultInfo>>>() {}.type)
            val resProcessInfoMap: Map<String, Map<String, String>> = gson.fromJson(resProcessOutputFile.readText(), object : TypeToken<Map<String, Map<String, String>>>() {}.type)

            val relativeLayoutInfoMap: LinkedHashMap<String, Int> = gson.fromJson(relativeIncludeLayoutInfoFile.readText(), object : TypeToken<LinkedHashMap<String, Int>>() {}.type)


            relativeLayoutInfoMap.forEach { (usingLayoutFile, _) ->
                removeWhiteListMap[usingLayoutFile] = true
            }

            val convertedLayoutMap = hashMapOf<String, Boolean>()
            for ((layoutName, map) in layoutInfoMap) {
                if (removeWhiteListMap[layoutName] != true) {
                    var allTypeConverted = true
                    for ((_, info) in map) {
                        if (info.result != GenResult.SUCCESS) {
                            allTypeConverted = false
                            break
                        }
                    }
                    if (allTypeConverted) {
                        resProcessInfoMap[layoutName]?.forEach { (_, arscPath) ->
                            convertedLayoutMap[arscPath] = true
                        }
                    } else {
                        map.forEach { (layoutType, info) ->
                            if (info.result == GenResult.SUCCESS) {
                                resProcessInfoMap[layoutName]?.get(if (layoutType.isEmpty()) "layout" else layoutType)?.let {
                                    convertedLayoutMap[it] = true
                                }
                            }
                        }
                    }
                }
            }

            val removedLayoutApFile = File(apFile.absolutePath)
            val sourceFile = apFile.parentFile.resolve(apFile.name + "src")
            if (sourceFile.exists()) {
                sourceFile.delete()
            }
            apFile.renameTo(sourceFile)
            sourceFile.createNewFile()
            try {
                FileInputStream(sourceFile).use { fis ->
                    ZipInputStream(fis).use { zis ->
                        FileOutputStream(removedLayoutApFile).use { fos ->
                            ZipOutputStream(fos).use { zos ->
                                zos.setLevel(Deflater.NO_COMPRESSION)
                                zos.setMethod(ZipEntry.STORED)
                                var entry = zis.nextEntry
                                while (entry != null) {
                                    val nextEntry = ZipEntry(entry.name)
                                    nextEntry.method = ZipEntry.STORED
                                    if (convertedLayoutMap[entry.name] == null) {
                                        nextEntry.time = -1L
                                        nextEntry.size = entry.size
                                        nextEntry.crc = entry.crc
                                        zos.putNextEntry(nextEntry)
                                        zos.write(zis.readBytes())
                                    } else {
                                        LogUtil.pl("remove converted layout file ${entry.name}")
                                    }
                                    entry = zis.nextEntry
                                }
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}