-keep class com.google.android.material.tabs.TabLayout {
   int contentInsetStart;
   int tabBackgroundResId;
   final int requestedTabMinWidth;
   final int requestedTabMaxWidth;
   int tabPaddingStart;
   int tabPaddingTop;
   int tabPaddingEnd;
   int tabPaddingBottom;
   float tabTextSize;
   android.graphics.PorterDuff.Mode tabIconTintMode;
   android.graphics.PorterDuff$Mode tabIconTintMode;
   int tabIndicatorAnimationDuration;
}

-keep class com.google.android.material.tabs.TabItem {
   public final java.lang.CharSequence text;
   public final android.graphics.drawable.Drawable icon;
   public final int customLayout;
}

-keep class androidx.constraintlayout.motion.widget.MotionScene {
   int getStartId();
   int getEndId();
}

-keep class androidx.constraintlayout.motion.widget.MotionLayout {
   androidx.constraintlayout.motion.widget.MotionScene mScene;
   private int mBeginState;
   private int mEndState;
   int mCurrentState;
}



-keep class androidx.fragment.app.FragmentActivity {
   androidx.fragment.app.FragmentController mFragments;
}

-keep class androidx.fragment.app.FragmentController {
   androidx.fragment.app.FragmentHostCallback mHost;
}

-keep class androidx.fragment.app.Fragment {
    boolean mFromLayout;
    int mFragmentId;
    int mContainerId;
    java.lang.String mTag;
    boolean mInLayout;
    androidx.fragment.app.FragmentManagerImpl mFragmentManager;
    androidx.fragment.app.FragmentHostCallback mHost;
}

-keep class androidx.fragment.app.FragmentManagerImpl {
    public void addFragment(androidx.fragment.app.Fragment, boolean);
}

-keep class androidx.appcompat.widget.DrawableUtils {
    static void fixDrawable(android.graphics.drawable.Drawable);
}

-keep class androidx.recyclerview.widget.RecyclerView {
    void initFastScroller(android.graphics.drawable.StateListDrawable, android.graphics.drawable.Drawable, android.graphics.drawable.StateListDrawable, android.graphics.drawable.Drawable);
}

-keep class androidx.coordinatorlayout.widget.CoordinatorLayout {
    int[] mKeylines;
}

-keep class com.google.android.material.appbar.CollapsingToolbarLayout {
    int toolbarId;
}

-keep class androidx.appcompat.widget.Toolbar {
    int mGravity;
    int mMaxButtonHeight;
    android.graphics.drawable.Drawable mCollapseIcon;
    java.lang.CharSequence mCollapseDescription;
}