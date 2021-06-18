package com.qxml.qxml_androidx.gen.constraintLayout.tools

import android.content.Context
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.MotionScene
import com.qxml.tools.ReflectUtils

object MotionLayoutHelper {

    private val getStartIdMethod = ReflectUtils.getDeclaredMethodOrNull(MotionScene::class.java,"getStartId")
    private val getEndIdMethod = ReflectUtils.getDeclaredMethodOrNull(MotionScene::class.java,"getEndId")

    private val motionSceneConstructor = ReflectUtils.getDeclaredConstructorOrNull(MotionScene::class.java, Context::class.java, MotionLayout::class.java, Int::class.java)

    private val mSceneField = ReflectUtils.getDeclaredFieldOrNull(MotionLayout::class.java, "mScene")
    private val mBeginStateField = ReflectUtils.getDeclaredFieldOrNull(MotionLayout::class.java, "mBeginState")
    private val mEndStateField = ReflectUtils.getDeclaredFieldOrNull(MotionLayout::class.java, "mEndState")
    private val mCurrentStateField = ReflectUtils.getDeclaredFieldOrNull(MotionLayout::class.java, "mCurrentState")

    @JvmStatic
    fun setUI(motionLayout: MotionLayout, res: Int) {
        //motionLayout.loadLayoutDescription(res)
        try {
            if (motionSceneConstructor != null && getStartIdMethod != null && getEndIdMethod != null) {
                val scene = motionSceneConstructor.newInstance(motionLayout.context, motionLayout, res)
                mSceneField?.set(motionLayout, scene)
                val startId = getStartIdMethod.invoke(scene) as Int
                mCurrentStateField?.setInt(motionLayout, startId)
                mBeginStateField?.setInt(motionLayout, startId)
                mEndStateField?.setInt(motionLayout, getEndIdMethod.invoke(scene) as Int)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}