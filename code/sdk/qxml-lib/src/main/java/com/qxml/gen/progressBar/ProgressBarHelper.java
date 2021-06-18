package com.qxml.gen.progressBar;

import android.os.Build;
import android.widget.ProgressBar;

import com.qxml.tools.ReflectUtils;

import java.lang.reflect.Field;

public class ProgressBarHelper {

    private static final Field mDurationField = ReflectUtils.getDeclaredFieldOrNull(ProgressBar.class, "mDuration");
    private static final Field mBehaviorField = ReflectUtils.getDeclaredFieldOrNull(ProgressBar.class, "mBehavior");
    private static final Field mMirrorForRtlField = ReflectUtils.getDeclaredFieldOrNull(ProgressBar.class, "mMirrorForRtl");

    private static final Field mMinWidthField = android.os.Build.VERSION.SDK_INT >= 29 ? null : ReflectUtils.getDeclaredFieldOrNull(ProgressBar.class, "mMinWidth");
    private static final Field mMaxWidthField = android.os.Build.VERSION.SDK_INT >= 29 ? null : ReflectUtils.getDeclaredFieldOrNull(ProgressBar.class, "mMaxWidth");
    private static final Field mMinHeightField = android.os.Build.VERSION.SDK_INT >= 29 ? null : ReflectUtils.getDeclaredFieldOrNull(ProgressBar.class, "mMinHeight");
    private static final Field mMaxHeightField = android.os.Build.VERSION.SDK_INT >= 29 ? null : ReflectUtils.getDeclaredFieldOrNull(ProgressBar.class, "mMaxHeight");

    public static void setMinMaxField(ProgressBar progressBar, int minWidth, int maxWidth, int minHeight, int maxHeight) {
        try {
            if (android.os.Build.VERSION.SDK_INT >= 29) {
                progressBar.setMinWidth(minWidth);
                progressBar.setMaxWidth(maxWidth);
                progressBar.setMinHeight(minHeight);
                progressBar.setMaxHeight(maxHeight);
            } else {
                if (mMinWidthField != null) {
                    mMinWidthField.set(progressBar, minWidth);
                }
                if (mMaxWidthField != null) {
                    mMaxWidthField.set(progressBar, maxWidth);
                }
                if (mMinHeightField != null) {
                    mMinHeightField.set(progressBar, minHeight);
                }
                if (mMaxHeightField != null) {
                    mMaxHeightField.set(progressBar, maxHeight);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setMDurationField(ProgressBar progressBar, int mDuration) {
        try {
            if (mDurationField != null) {
                mDurationField.set(progressBar, mDuration);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setMBehaviorField(ProgressBar progressBar, int mBehavior) {
        try {
            if (mBehaviorField != null) {
                mBehaviorField.set(progressBar, mBehavior);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setMMirrorForRtlField(ProgressBar progressBar, boolean mirrorForRtl) {
        try {
            if (Build.VERSION.SDK_INT >= 18) {
                if (mMirrorForRtlField != null) {
                    mMirrorForRtlField.set(progressBar, mirrorForRtl);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
