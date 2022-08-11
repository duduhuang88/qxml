package com.qxml.transform.pool

import javassist.ClassPool
import javassist.CtClass
import javassist.NotFoundException

object PoolManager {

    lateinit var pool: ClassPool

    @JvmStatic
    fun initPool(): ClassPool {
        return FixImportPackageClassPool(null).apply {
            appendSystemPath()
        }
    }
}

class FixImportPackageClassPool(parent: ClassPool?) : ClassPool(parent) {

    override fun get(classname: String?): CtClass {
        if (classname != null) {
            try {
                return super.get(classname)
            } catch (e: NotFoundException) {
                if (classname.indexOf('.') < 0) {
                    val it: Iterator<String> = importedPackages
                    while (it.hasNext()) {
                        val pac = it.next()
                        val fqName = pac.replace("\\.$".toRegex(), "") + "." + classname
                        try {
                            return super.get(fqName)
                        } catch (e: NotFoundException) {
                            try {
                                if (pac.endsWith(".$classname")) return super.get(pac)
                            } catch (e2: NotFoundException) {
                            }
                        }
                    }
                }
            }
        }
        throw NotFoundException(classname)
    }

}