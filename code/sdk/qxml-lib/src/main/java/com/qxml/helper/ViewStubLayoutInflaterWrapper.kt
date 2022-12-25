package com.qxml.helper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.qxml.QxmlInflater
import org.xmlpull.v1.XmlPullParser

class ViewStubLayoutInflaterWrapper(context: Context): LayoutInflater(context) {

    private val realInflater by lazy { from(context) }

    override fun cloneInContext(newContext: Context?): LayoutInflater {
        return realInflater.cloneInContext(newContext)
    }

    override fun inflate(resource: Int, root: ViewGroup?, attachToRoot: Boolean): View {
        QxmlInflater.inflate(realInflater, resource, root, attachToRoot)?.let {
            return it
        }
        return super.inflate(resource, root, attachToRoot)
    }

    override fun inflate(resource: Int, root: ViewGroup?): View {
        return realInflater.inflate(resource, root)
    }

    override fun inflate(parser: XmlPullParser?, root: ViewGroup?): View {
        return realInflater.inflate(parser, root)
    }

    override fun inflate(parser: XmlPullParser?, root: ViewGroup?, attachToRoot: Boolean): View {
        return realInflater.inflate(parser, root, attachToRoot)
    }

}