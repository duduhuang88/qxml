-keep class android.support.design.widget.TabLayout {
   int contentInsetStart;
   int tabBackgroundResId;
   final int requestedTabMinWidth;
   final int requestedTabMaxWidth;
   int tabPaddingStart;
   int tabPaddingTop;
   int tabPaddingEnd;
   int tabPaddingBottom;
   float tabTextSize;
   android.graphics.PorterDuff$Mode tabIconTintMode;
   int tabIndicatorAnimationDuration;
}

-keep class android.support.design.widget.TabItem {
   public final java.lang.CharSequence text;
   public final android.graphics.drawable.Drawable icon;
   public final int customLayout;
}

-keep class android.support.constraint.motion.MotionScene {
   int getStartId();
   int getEndId();
}

-keep class android.support.constraint.motion.MotionLayout {
   android.support.constraint.motion.MotionScene mScene;
   private int mBeginState;
   private int mEndState;
   int mCurrentState;
}



-keep class android.support.v4.app.FragmentActivity {
   android.support.v4.app.FragmentController mFragments;
}

-keep class android.support.v4.app.FragmentController {
   android.support.v4.app.FragmentHostCallback mHost;
}

-keep class android.support.v4.app.Fragment {
    boolean mFromLayout;
    int mFragmentId;
    int mContainerId;
    java.lang.String mTag;
    boolean mInLayout;
    android.support.v4.app.FragmentManagerImpl mFragmentManager;
    android.support.v4.app.FragmentHostCallback mHost;
}

-keep class android.support.v4.app.FragmentManagerImpl {
    public void addFragment(android.support.v4.app.Fragment, boolean);
}

-keep class android.support.v7.widget.DrawableUtils {
    static void fixDrawable(android.graphics.drawable.Drawable);
}

-keep class android.support.v7.widget.RecyclerView {
    void initFastScroller(android.graphics.drawable.StateListDrawable, android.graphics.drawable.Drawable, android.graphics.drawable.StateListDrawable, android.graphics.drawable.Drawable);
}

-keep class android.support.design.widget.CoordinatorLayout {
    int[] mKeylines;
}

-keep class android.support.design.widget.CollapsingToolbarLayout {
    int toolbarId;
}

-keep class android.support.v7.widget.Toolbar {
    int mGravity;
    int mMaxButtonHeight;
    int mButtonGravity;
    android.graphics.drawable.Drawable mCollapseIcon;
    java.lang.CharSequence mCollapseDescription;
}