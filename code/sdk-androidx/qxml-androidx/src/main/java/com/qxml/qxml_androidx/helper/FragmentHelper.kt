package com.qxml.qxml_androidx.helper

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentController
import com.qxml.tools.ReflectUtils

object FragmentHelper {

    private val mFragmentsField = ReflectUtils.getDeclaredFieldOrNull(FragmentActivity::class.java, "mFragments")
    private val fragmentControllerMHostField = ReflectUtils.getDeclaredFieldOrNull(FragmentController::class.java, "mHost")
    private val mFromLayoutField = ReflectUtils.getDeclaredFieldOrNull(Fragment::class.java, "mFromLayout")
    private val mFragmentIdField = ReflectUtils.getDeclaredFieldOrNull(Fragment::class.java, "mFragmentId")
    private val mContainerIdField = ReflectUtils.getDeclaredFieldOrNull(Fragment::class.java, "mContainerId")
    private val mTagField = ReflectUtils.getDeclaredFieldOrNull(Fragment::class.java, "mTag")
    private val mInLayoutField = ReflectUtils.getDeclaredFieldOrNull(Fragment::class.java, "mInLayout")
    private val mFragmentManagerField = ReflectUtils.getDeclaredFieldOrNull(Fragment::class.java, "mFragmentManager")
    private val mHostField = ReflectUtils.getDeclaredFieldOrNull(Fragment::class.java, "mHost")
    //private val addFragmentMethod = /*supportFragmentManager::class.java*/Class.forName("androidx.fragment.app.FragmentManagerImpl").getMethod("addFragment", Fragment::class.java, Boolean::class.java)

    @JvmStatic
    fun add(context: Context, f: Fragment, id: Int, tag: String? = null, parent: ViewGroup? = null) {
        if (fragmentControllerMHostField != null && mFragmentsField != null) {
            (context as? FragmentActivity)?.also { fragmentActivity ->
                val fm = fragmentActivity.supportFragmentManager
                val mHost = fragmentControllerMHostField.get(mFragmentsField.get(fragmentActivity))
                if (tag != null) {
                    mTagField?.set(f, tag)
                }
                mFromLayoutField?.setBoolean(f, true)
                mFragmentIdField?.setInt(f, id)
                mContainerIdField?.setInt(f, parent?.id ?: View.NO_ID)
                mInLayoutField?.setBoolean(f, true)
                mFragmentManagerField?.set(f, fm)
                mHostField?.set(f, mHost)
                `$$FragmentHelper`.fragmentOnInflate(fragmentActivity, f)
                //f.onInflate(fragmentActivity, null, null)
                //change to androidx.fragment.app.FragmentLayoutInflaterFactory logic
                //addFragmentMethod.invoke(fm, f, true)
            }
        }
    }
}