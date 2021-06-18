package com.qxml.qxml_support.gen.constraintLayout.tools;

import android.content.Context;
import android.support.constraint.ConstraintHelper;
import android.support.constraint.ConstraintLayout;
import android.util.Log;

import java.lang.reflect.Field;

public class ConstraintHelperTool {

    public static void setIds(ConstraintHelper constraintHelper, Context myContext, String idList) {
        if (idList != null) {
            int begin = 0;

            while(true) {
                int end = idList.indexOf(44, begin);
                if (end == -1) {
                    addID(constraintHelper, myContext, idList.substring(begin));
                    return;
                }

                addID(constraintHelper, myContext, idList.substring(begin, end));
                begin = end + 1;
            }
        }
    }

    private static void addID(ConstraintHelper constraintHelper, Context myContext, String idString) {
        if (idString != null) {
            if (myContext != null) {
                idString = idString.trim();
                int tag = 0;

                try {
                    Class res = android.support.constraint.R.id.class;
                    Field field = res.getField(idString);
                    tag = field.getInt(null);
                } catch (Exception var5) {
                }

                if (tag == 0) {
                    tag = myContext.getResources().getIdentifier(idString, "id", myContext.getPackageName());
                }

                if (tag == 0 && constraintHelper.isInEditMode() && constraintHelper.getParent() instanceof ConstraintLayout) {
                    ConstraintLayout constraintLayout = (ConstraintLayout)constraintHelper.getParent();
                    Object value = constraintLayout.getDesignInformation(0, idString);
                    if (value != null && value instanceof Integer) {
                        tag = (Integer)value;
                    }
                }

                if (tag != 0) {
                    constraintHelper.setTag(tag, (Object)null);
                } else {
                    Log.w("ConstraintHelper", "Could not find id of \"" + idString + "\"");
                }

            }
        }
    }

}
