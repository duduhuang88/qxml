package com.qxml.qxml_androidx.gen.fresco;

import com.facebook.drawee.drawable.ScalingUtils;

public class FrescoHelper {

    private FrescoHelper() {
    }

    public static ScalingUtils.ScaleType getScaleTypeFromXml(int type) {
        switch (type) {
            case -1: // none
                return null;
            case 0: // fitXY
                return ScalingUtils.ScaleType.FIT_XY;
            case 1: // fitStart
                return ScalingUtils.ScaleType.FIT_START;
            case 2: // fitCenter
                return ScalingUtils.ScaleType.FIT_CENTER;
            case 3: // fitEnd
                return ScalingUtils.ScaleType.FIT_END;
            case 4: // center
                return ScalingUtils.ScaleType.CENTER;
            case 5: // centerInside
                return ScalingUtils.ScaleType.CENTER_INSIDE;
            case 6: // centerCrop
                return ScalingUtils.ScaleType.CENTER_CROP;
            case 7: // focusCrop
                return ScalingUtils.ScaleType.FOCUS_CROP;
            case 8: // fitBottomStart
                return ScalingUtils.ScaleType.FIT_BOTTOM_START;
            default:
                // this method is supposed to be called only when XML attribute is specified.
                throw new RuntimeException("XML attribute not specified!");
        }
    }
}
