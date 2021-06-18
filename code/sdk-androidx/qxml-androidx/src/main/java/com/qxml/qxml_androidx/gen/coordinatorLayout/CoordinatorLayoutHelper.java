package com.qxml.qxml_androidx.gen.coordinatorLayout;

import android.content.Context;
import android.content.res.Resources;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.qxml.tools.ReflectUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class CoordinatorLayoutHelper {

    static final String WIDGET_PACKAGE_NAME;
    static final Class<?>[] CONSTRUCTOR_PARAMS;
    static final ThreadLocal<Map<String, Constructor<androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior>>> sConstructors;

    static {
        Package pkg = CoordinatorLayout.class.getPackage();
        WIDGET_PACKAGE_NAME = pkg != null ? pkg.getName() : null;
        CONSTRUCTOR_PARAMS = new Class[]{Context.class, AttributeSet.class};
        sConstructors = new ThreadLocal<>();
    }

    private static final Field mKeylinesField = ReflectUtils.getDeclaredFieldOrNull(CoordinatorLayout.class, "mKeylines");

    public static void setMKeylines(CoordinatorLayout coordinatorLayout, Resources resources, int res) {
        try {
            if (mKeylinesField != null) {
                int[] keyLines = resources.getIntArray(res);
                float density = resources.getDisplayMetrics().density;
                int count = keyLines.length;
                for(int i = 0; i < count; ++i) {
                    keyLines[i] = (int)((float)keyLines[i] * density);
                }
                mKeylinesField.set(coordinatorLayout, keyLines);
            }
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static CoordinatorLayout.Behavior parseBehavior(Context context, AttributeSet attrs, String name) {
        if (TextUtils.isEmpty(name)) {
            return null;
        } else {
            String fullName;
            if (name.startsWith(".")) {
                fullName = context.getPackageName() + name;
            } else if (name.indexOf(46) >= 0) {
                fullName = name;
            } else {
                fullName = !TextUtils.isEmpty(WIDGET_PACKAGE_NAME) ? WIDGET_PACKAGE_NAME + '.' + name : name;
            }

            try {
                Map<String, Constructor<CoordinatorLayout.Behavior>> constructors = (Map)sConstructors.get();
                if (constructors == null) {
                    constructors = new HashMap();
                    sConstructors.set(constructors);
                }

                Constructor<CoordinatorLayout.Behavior> c = (Constructor)((Map)constructors).get(fullName);
                if (c == null) {
                    Class<CoordinatorLayout.Behavior> clazz = (Class<CoordinatorLayout.Behavior>) context.getClassLoader().loadClass(fullName);
                    c = clazz.getConstructor(CONSTRUCTOR_PARAMS);
                    c.setAccessible(true);
                    ((Map)constructors).put(fullName, c);
                }

                return (CoordinatorLayout.Behavior)c.newInstance(context, attrs);
            } catch (Exception var7) {
                throw new RuntimeException("Could not inflate Behavior subclass " + fullName, var7);
            }
        }
    }

}
