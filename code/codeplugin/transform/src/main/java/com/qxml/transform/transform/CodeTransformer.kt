package com.qxml.transform.transform

import com.qxml.constant.Constants
import com.qxml.transform.converter.method.InflateMethodConverter
import com.qxml.transform.converter.method.MethodCallManager
import com.qxml.transform.converter.method.ActivitySetContentViewMethodConverter
import com.qxml.transform.converter.method.DialogSetContentViewMethodConverter
import com.qxml.transform.converter.qxml.QxmlInitConverter
import com.qxml.transform.pool.PoolManager
import java.io.DataOutputStream
import java.io.InputStream
import java.io.OutputStream

object CodeTransformer {

    init {
        MethodCallManager.register(
            InflateMethodConverter(),
            ActivitySetContentViewMethodConverter(),
            DialogSetContentViewMethodConverter()
        )
    }

    fun transform(input: InputStream, output: OutputStream, genClassInfoList: List<String>, packageName: String, layoutIdMap: Map<String, Int>, retryCount: Int = 0) {
        /*val ctClass = synchronized(this@CodeTransformer) {
            PoolManager.pool.makeClass(input, false)
        }*/
        try {
            val ctClass = PoolManager.pool.makeClass(input, false)
            val className = ctClass.name
            if (className != Constants.QXML_INFLATER_CLASS_NAME) {
                MethodCallManager.convert(ctClass)
            } else {
                QxmlInitConverter.convert(ctClass, genClassInfoList, packageName, layoutIdMap)
            }
            ctClass.toBytecode(DataOutputStream(output))
            ctClass.defrost()
            ctClass.detach()
        } catch (e: Exception) {
            e.printStackTrace()
            if (retryCount < 2) {
                Thread.sleep(200)
                transform(input, output, genClassInfoList, packageName, layoutIdMap, retryCount+1)
            } else {
                throw e
            }
        }
    }

}