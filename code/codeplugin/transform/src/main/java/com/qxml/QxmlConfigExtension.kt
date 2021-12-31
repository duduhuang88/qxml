package com.qxml

import org.gradle.api.Action
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project

open class QxmlExtension(project: Project) {

    var enable = true
    //是否忽略未实现的属性
    var ignoreUnImplementAttr = true
    var compatMode = CompatMode.AUTO
    var acceptReferenceStyle = true
    var useFactory = false
    var viewDebug = false
    var logEnable = false
    var debugEnable = false
    var useCreateViewListener = false

    val buildType = project.container(QxmlConfigExtension::class.java)

    lateinit var defaultConfig: QxmlConfigExtension

    fun buildType(action: Action<NamedDomainObjectContainer<QxmlConfigExtension>>) {
        action.execute(buildType)
    }

    fun fixBuildTypeUnSetParam() {
        buildType.forEach {
            if (!it.enableSet) {
                it.enable = enable
            }
            if (!it.ignoreUnImplementAttrSet) {
                it.ignoreUnImplementAttr = ignoreUnImplementAttr
            }
            if (!it.compatModeSet) {
                it.compatMode = compatMode
            }
            if (!it.acceptReferenceStyleSet) {
                it.acceptReferenceStyle = acceptReferenceStyle
            }
            if (!it.useFactorySet) {
                it.useFactory = useFactory
            }
            if (!it.viewDebugSet) {
                it.viewDebug = viewDebug
            }
            if (!it.logEnableSet) {
                it.logEnable = logEnable
            }
            if (!it.debugEnableSet) {
                it.debugEnable = debugEnable
            }
            if (!it.useCreateViewListenerSet) {
                it.useCreateViewListener = useCreateViewListener
            }
        }
        defaultConfig = QxmlConfigExtension("default").also {
            it.enable = enable
            it.ignoreUnImplementAttr = ignoreUnImplementAttr
            it.compatMode = compatMode
            it.acceptReferenceStyle = acceptReferenceStyle
            it.useFactory = useFactory
            it.viewDebug = viewDebug
            it.logEnable = logEnable
            it.useCreateViewListener = useCreateViewListener
        }
    }

    fun getConfigByBuildType(buildType: String): QxmlConfigExtension {
        this.buildType.forEach {
            if (it.name == buildType) {
                return it
            }
        }
        return defaultConfig
    }

}

open class QxmlConfigExtension(val name: String) {
    var enable = true
    //是否忽略未实现的属性
    var ignoreUnImplementAttr = true
    var compatMode = CompatMode.AUTO
    var acceptReferenceStyle = true
    var useFactory = false
    var viewDebug = false
    var logEnable = false
    var debugEnable = false
    var useCreateViewListener = false

    var enableSet = false
    var ignoreUnImplementAttrSet = false
    var compatModeSet = false
    var acceptReferenceStyleSet = false
    var useFactorySet = false
    var viewDebugSet = false
    var logEnableSet = false
    var debugEnableSet = false
    var useCreateViewListenerSet = false

    fun enable(enable: Boolean) {
        this.enable = enable
        enableSet = true
    }

    fun ignoreUnImplementAttr(ignoreUnImplementAttr: Boolean) {
        this.ignoreUnImplementAttr = ignoreUnImplementAttr
        ignoreUnImplementAttrSet = true
    }

    fun acceptReferenceStyle(acceptReferenceStyle: Boolean) {
        this.acceptReferenceStyle = acceptReferenceStyle
        acceptReferenceStyleSet = true
    }

    fun useFactory(useFactory: Boolean) {
        this.useFactory = useFactory
        useFactorySet = true
    }

    fun viewDebug(viewDebug: Boolean) {
        this.viewDebug = viewDebug
        viewDebugSet = true
    }

    fun logEnable(logEnable: Boolean) {
        this.logEnable = logEnable
        logEnableSet = true
    }

    fun debugEnable(debugEnable: Boolean) {
        this.debugEnable = debugEnable
        debugEnableSet = true
    }

    fun useCreateViewListener(useCreateViewListener: Boolean) {
        this.useCreateViewListener = useCreateViewListener
        useCreateViewListenerSet = true
    }

    fun compatMode(compatMode: CompatMode) {
        this.compatMode = compatMode
        compatModeSet = true
    }

}