package android.support.v4.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class SlidingPaneLayout extends ViewGroup {
  private static final int DEFAULT_FADE_COLOR = -858993460;
  
  private static final int DEFAULT_OVERHANG_SIZE = 32;
  
  static final SlidingPanelLayoutImpl IMPL;
  
  private static final int MIN_FLING_VELOCITY = 400;
  
  private static final String TAG = "SlidingPaneLayout";
  
  private boolean mCanSlide;
  
  private int mCoveredFadeColor;
  
  final ViewDragHelper mDragHelper;
  
  private boolean mFirstLayout = true;
  
  private float mInitialMotionX;
  
  private float mInitialMotionY;
  
  boolean mIsUnableToDrag;
  
  private final int mOverhangSize;
  
  private PanelSlideListener mPanelSlideListener;
  
  private int mParallaxBy;
  
  private float mParallaxOffset;
  
  final ArrayList<DisableLayerRunnable> mPostedRunnables = new ArrayList<DisableLayerRunnable>();
  
  boolean mPreservedOpenState;
  
  private Drawable mShadowDrawableLeft;
  
  private Drawable mShadowDrawableRight;
  
  float mSlideOffset;
  
  int mSlideRange;
  
  View mSlideableView;
  
  private int mSliderFadeColor = -858993460;
  
  private final Rect mTmpRect = new Rect();
  
  static {
    if (Build.VERSION.SDK_INT >= 17) {
      IMPL = new SlidingPanelLayoutImplJBMR1();
    } else {
      IMPL = new SlidingPanelLayoutImplJB();
    } 
  }
  
  public SlidingPaneLayout(Context paramContext) {
    this(paramContext, null);
  }
  
  public SlidingPaneLayout(Context paramContext, AttributeSet paramAttributeSet) {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public SlidingPaneLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    float f = (paramContext.getResources().getDisplayMetrics()).density;
    this.mOverhangSize = (int)(32.0F * f + 0.5F);
    setWillNotDraw(false);
    ViewCompat.setAccessibilityDelegate((View)this, new AccessibilityDelegate());
    ViewCompat.setImportantForAccessibility((View)this, 1);
    ViewDragHelper viewDragHelper = ViewDragHelper.create(this, 0.5F, new DragHelperCallback());
    this.mDragHelper = viewDragHelper;
    viewDragHelper.setMinVelocity(f * 400.0F);
  }
  
  private boolean closePane(View paramView, int paramInt) {
    if (this.mFirstLayout || smoothSlideTo(0.0F, paramInt)) {
      this.mPreservedOpenState = false;
      return true;
    } 
    return false;
  }
  
  private void dimChildView(View paramView, float paramFloat, int paramInt) {
    LayoutParams layoutParams = (LayoutParams)paramView.getLayoutParams();
    if (paramFloat > 0.0F && paramInt != 0) {
      int i = (int)(((0xFF000000 & paramInt) >>> 24) * paramFloat);
      if (layoutParams.dimPaint == null)
        layoutParams.dimPaint = new Paint(); 
      layoutParams.dimPaint.setColorFilter((ColorFilter)new PorterDuffColorFilter(i << 24 | paramInt & 0xFFFFFF, PorterDuff.Mode.SRC_OVER));
      if (paramView.getLayerType() != 2)
        paramView.setLayerType(2, layoutParams.dimPaint); 
      invalidateChildRegion(paramView);
    } else if (paramView.getLayerType() != 0) {
      Paint paint = layoutParams.dimPaint;
      if (paint != null)
        paint.setColorFilter(null); 
      DisableLayerRunnable disableLayerRunnable = new DisableLayerRunnable(paramView);
      this.mPostedRunnables.add(disableLayerRunnable);
      ViewCompat.postOnAnimation((View)this, disableLayerRunnable);
    } 
  }
  
  private boolean openPane(View paramView, int paramInt) {
    if (this.mFirstLayout || smoothSlideTo(1.0F, paramInt)) {
      this.mPreservedOpenState = true;
      return true;
    } 
    return false;
  }
  
  private void parallaxOtherViews(float paramFloat) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual isLayoutRtlSupport : ()Z
    //   4: istore_2
    //   5: aload_0
    //   6: getfield mSlideableView : Landroid/view/View;
    //   9: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   12: checkcast android/support/v4/widget/SlidingPaneLayout$LayoutParams
    //   15: astore_3
    //   16: aload_3
    //   17: getfield dimWhenOffset : Z
    //   20: istore #4
    //   22: iconst_0
    //   23: istore #5
    //   25: iload #4
    //   27: ifeq -> 60
    //   30: iload_2
    //   31: ifeq -> 43
    //   34: aload_3
    //   35: getfield rightMargin : I
    //   38: istore #6
    //   40: goto -> 49
    //   43: aload_3
    //   44: getfield leftMargin : I
    //   47: istore #6
    //   49: iload #6
    //   51: ifgt -> 60
    //   54: iconst_1
    //   55: istore #6
    //   57: goto -> 63
    //   60: iconst_0
    //   61: istore #6
    //   63: aload_0
    //   64: invokevirtual getChildCount : ()I
    //   67: istore #7
    //   69: iload #5
    //   71: iload #7
    //   73: if_icmpge -> 201
    //   76: aload_0
    //   77: iload #5
    //   79: invokevirtual getChildAt : (I)Landroid/view/View;
    //   82: astore_3
    //   83: aload_3
    //   84: aload_0
    //   85: getfield mSlideableView : Landroid/view/View;
    //   88: if_acmpne -> 94
    //   91: goto -> 195
    //   94: aload_0
    //   95: getfield mParallaxOffset : F
    //   98: fstore #8
    //   100: aload_0
    //   101: getfield mParallaxBy : I
    //   104: istore #9
    //   106: fconst_1
    //   107: fload #8
    //   109: fsub
    //   110: iload #9
    //   112: i2f
    //   113: fmul
    //   114: f2i
    //   115: istore #10
    //   117: aload_0
    //   118: fload_1
    //   119: putfield mParallaxOffset : F
    //   122: iload #10
    //   124: fconst_1
    //   125: fload_1
    //   126: fsub
    //   127: iload #9
    //   129: i2f
    //   130: fmul
    //   131: f2i
    //   132: isub
    //   133: istore #10
    //   135: iload #10
    //   137: istore #9
    //   139: iload_2
    //   140: ifeq -> 148
    //   143: iload #10
    //   145: ineg
    //   146: istore #9
    //   148: aload_3
    //   149: iload #9
    //   151: invokevirtual offsetLeftAndRight : (I)V
    //   154: iload #6
    //   156: ifeq -> 195
    //   159: aload_0
    //   160: getfield mParallaxOffset : F
    //   163: fstore #8
    //   165: iload_2
    //   166: ifeq -> 178
    //   169: fload #8
    //   171: fconst_1
    //   172: fsub
    //   173: fstore #8
    //   175: goto -> 184
    //   178: fconst_1
    //   179: fload #8
    //   181: fsub
    //   182: fstore #8
    //   184: aload_0
    //   185: aload_3
    //   186: fload #8
    //   188: aload_0
    //   189: getfield mCoveredFadeColor : I
    //   192: invokespecial dimChildView : (Landroid/view/View;FI)V
    //   195: iinc #5, 1
    //   198: goto -> 69
    //   201: return
  }
  
  private static boolean viewIsOpaque(View paramView) {
    boolean bool = paramView.isOpaque();
    boolean bool1 = true;
    if (bool)
      return true; 
    if (Build.VERSION.SDK_INT >= 18)
      return false; 
    Drawable drawable = paramView.getBackground();
    if (drawable != null) {
      if (drawable.getOpacity() != -1)
        bool1 = false; 
      return bool1;
    } 
    return false;
  }
  
  protected boolean canScroll(View paramView, boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3) {
    boolean bool = paramView instanceof ViewGroup;
    boolean bool1 = true;
    if (bool) {
      ViewGroup viewGroup = (ViewGroup)paramView;
      int i = paramView.getScrollX();
      int j = paramView.getScrollY();
      for (int k = viewGroup.getChildCount() - 1; k >= 0; k--) {
        View view = viewGroup.getChildAt(k);
        int m = paramInt2 + i;
        if (m >= view.getLeft() && m < view.getRight()) {
          int n = paramInt3 + j;
          if (n >= view.getTop() && n < view.getBottom() && canScroll(view, true, paramInt1, m - view.getLeft(), n - view.getTop()))
            return true; 
        } 
      } 
    } 
    if (paramBoolean) {
      if (!isLayoutRtlSupport())
        paramInt1 = -paramInt1; 
      if (paramView.canScrollHorizontally(paramInt1))
        return bool1; 
    } 
    return false;
  }
  
  @Deprecated
  public boolean canSlide() {
    return this.mCanSlide;
  }
  
  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams) {
    boolean bool;
    if (paramLayoutParams instanceof LayoutParams && super.checkLayoutParams(paramLayoutParams)) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean closePane() {
    return closePane(this.mSlideableView, 0);
  }
  
  public void computeScroll() {
    if (this.mDragHelper.continueSettling(true)) {
      if (!this.mCanSlide) {
        this.mDragHelper.abort();
        return;
      } 
      ViewCompat.postInvalidateOnAnimation((View)this);
    } 
  }
  
  void dispatchOnPanelClosed(View paramView) {
    PanelSlideListener panelSlideListener = this.mPanelSlideListener;
    if (panelSlideListener != null)
      panelSlideListener.onPanelClosed(paramView); 
    sendAccessibilityEvent(32);
  }
  
  void dispatchOnPanelOpened(View paramView) {
    PanelSlideListener panelSlideListener = this.mPanelSlideListener;
    if (panelSlideListener != null)
      panelSlideListener.onPanelOpened(paramView); 
    sendAccessibilityEvent(32);
  }
  
  void dispatchOnPanelSlide(View paramView) {
    PanelSlideListener panelSlideListener = this.mPanelSlideListener;
    if (panelSlideListener != null)
      panelSlideListener.onPanelSlide(paramView, this.mSlideOffset); 
  }
  
  public void draw(Canvas paramCanvas) {
    Drawable drawable;
    View view;
    super.draw(paramCanvas);
    if (isLayoutRtlSupport()) {
      drawable = this.mShadowDrawableRight;
    } else {
      drawable = this.mShadowDrawableLeft;
    } 
    if (getChildCount() > 1) {
      view = getChildAt(1);
    } else {
      view = null;
    } 
    if (view != null && drawable != null) {
      int m;
      int n;
      int i = view.getTop();
      int j = view.getBottom();
      int k = drawable.getIntrinsicWidth();
      if (isLayoutRtlSupport()) {
        m = view.getRight();
        n = k + m;
      } else {
        n = view.getLeft();
        m = n;
        k = n - k;
        n = m;
        m = k;
      } 
      drawable.setBounds(m, i, n, j);
      drawable.draw(paramCanvas);
    } 
  }
  
  protected boolean drawChild(Canvas paramCanvas, View paramView, long paramLong) {
    LayoutParams layoutParams = (LayoutParams)paramView.getLayoutParams();
    int i = paramCanvas.save(2);
    if (this.mCanSlide && !layoutParams.slideable && this.mSlideableView != null) {
      paramCanvas.getClipBounds(this.mTmpRect);
      if (isLayoutRtlSupport()) {
        Rect rect = this.mTmpRect;
        rect.left = Math.max(rect.left, this.mSlideableView.getRight());
      } else {
        Rect rect = this.mTmpRect;
        rect.right = Math.min(rect.right, this.mSlideableView.getLeft());
      } 
      paramCanvas.clipRect(this.mTmpRect);
    } 
    boolean bool = super.drawChild(paramCanvas, paramView, paramLong);
    paramCanvas.restoreToCount(i);
    return bool;
  }
  
  protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
    return (ViewGroup.LayoutParams)new LayoutParams();
  }
  
  public ViewGroup.LayoutParams generateLayoutParams(AttributeSet paramAttributeSet) {
    return (ViewGroup.LayoutParams)new LayoutParams(getContext(), paramAttributeSet);
  }
  
  protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams) {
    LayoutParams layoutParams;
    if (paramLayoutParams instanceof ViewGroup.MarginLayoutParams) {
      layoutParams = new LayoutParams((ViewGroup.MarginLayoutParams)paramLayoutParams);
    } else {
      layoutParams = new LayoutParams((ViewGroup.LayoutParams)layoutParams);
    } 
    return (ViewGroup.LayoutParams)layoutParams;
  }
  
  @ColorInt
  public int getCoveredFadeColor() {
    return this.mCoveredFadeColor;
  }
  
  public int getParallaxDistance() {
    return this.mParallaxBy;
  }
  
  @ColorInt
  public int getSliderFadeColor() {
    return this.mSliderFadeColor;
  }
  
  void invalidateChildRegion(View paramView) {
    IMPL.invalidateChildRegion(this, paramView);
  }
  
  boolean isDimmed(View paramView) {
    boolean bool1 = false;
    if (paramView == null)
      return false; 
    LayoutParams layoutParams = (LayoutParams)paramView.getLayoutParams();
    boolean bool2 = bool1;
    if (this.mCanSlide) {
      bool2 = bool1;
      if (layoutParams.dimWhenOffset) {
        bool2 = bool1;
        if (this.mSlideOffset > 0.0F)
          bool2 = true; 
      } 
    } 
    return bool2;
  }
  
  boolean isLayoutRtlSupport() {
    int i = ViewCompat.getLayoutDirection((View)this);
    boolean bool = true;
    if (i != 1)
      bool = false; 
    return bool;
  }
  
  public boolean isOpen() {
    return (!this.mCanSlide || this.mSlideOffset == 1.0F);
  }
  
  public boolean isSlideable() {
    return this.mCanSlide;
  }
  
  protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    this.mFirstLayout = true;
  }
  
  protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    this.mFirstLayout = true;
    int i = this.mPostedRunnables.size();
    for (byte b = 0; b < i; b++)
      ((DisableLayerRunnable)this.mPostedRunnables.get(b)).run(); 
    this.mPostedRunnables.clear();
  }
  
  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent) {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual getActionMasked : ()I
    //   4: istore_2
    //   5: aload_0
    //   6: getfield mCanSlide : Z
    //   9: istore_3
    //   10: iconst_1
    //   11: istore #4
    //   13: iload_3
    //   14: ifne -> 66
    //   17: iload_2
    //   18: ifne -> 66
    //   21: aload_0
    //   22: invokevirtual getChildCount : ()I
    //   25: iconst_1
    //   26: if_icmple -> 66
    //   29: aload_0
    //   30: iconst_1
    //   31: invokevirtual getChildAt : (I)Landroid/view/View;
    //   34: astore #5
    //   36: aload #5
    //   38: ifnull -> 66
    //   41: aload_0
    //   42: aload_0
    //   43: getfield mDragHelper : Landroid/support/v4/widget/ViewDragHelper;
    //   46: aload #5
    //   48: aload_1
    //   49: invokevirtual getX : ()F
    //   52: f2i
    //   53: aload_1
    //   54: invokevirtual getY : ()F
    //   57: f2i
    //   58: invokevirtual isViewUnder : (Landroid/view/View;II)Z
    //   61: iconst_1
    //   62: ixor
    //   63: putfield mPreservedOpenState : Z
    //   66: aload_0
    //   67: getfield mCanSlide : Z
    //   70: ifeq -> 288
    //   73: aload_0
    //   74: getfield mIsUnableToDrag : Z
    //   77: ifeq -> 87
    //   80: iload_2
    //   81: ifeq -> 87
    //   84: goto -> 288
    //   87: iload_2
    //   88: iconst_3
    //   89: if_icmpeq -> 279
    //   92: iload_2
    //   93: iconst_1
    //   94: if_icmpne -> 100
    //   97: goto -> 279
    //   100: iload_2
    //   101: ifeq -> 184
    //   104: iload_2
    //   105: iconst_2
    //   106: if_icmpeq -> 112
    //   109: goto -> 249
    //   112: aload_1
    //   113: invokevirtual getX : ()F
    //   116: fstore #6
    //   118: aload_1
    //   119: invokevirtual getY : ()F
    //   122: fstore #7
    //   124: fload #6
    //   126: aload_0
    //   127: getfield mInitialMotionX : F
    //   130: fsub
    //   131: invokestatic abs : (F)F
    //   134: fstore #6
    //   136: fload #7
    //   138: aload_0
    //   139: getfield mInitialMotionY : F
    //   142: fsub
    //   143: invokestatic abs : (F)F
    //   146: fstore #7
    //   148: fload #6
    //   150: aload_0
    //   151: getfield mDragHelper : Landroid/support/v4/widget/ViewDragHelper;
    //   154: invokevirtual getTouchSlop : ()I
    //   157: i2f
    //   158: fcmpl
    //   159: ifle -> 249
    //   162: fload #7
    //   164: fload #6
    //   166: fcmpl
    //   167: ifle -> 249
    //   170: aload_0
    //   171: getfield mDragHelper : Landroid/support/v4/widget/ViewDragHelper;
    //   174: invokevirtual cancel : ()V
    //   177: aload_0
    //   178: iconst_1
    //   179: putfield mIsUnableToDrag : Z
    //   182: iconst_0
    //   183: ireturn
    //   184: aload_0
    //   185: iconst_0
    //   186: putfield mIsUnableToDrag : Z
    //   189: aload_1
    //   190: invokevirtual getX : ()F
    //   193: fstore #7
    //   195: aload_1
    //   196: invokevirtual getY : ()F
    //   199: fstore #6
    //   201: aload_0
    //   202: fload #7
    //   204: putfield mInitialMotionX : F
    //   207: aload_0
    //   208: fload #6
    //   210: putfield mInitialMotionY : F
    //   213: aload_0
    //   214: getfield mDragHelper : Landroid/support/v4/widget/ViewDragHelper;
    //   217: aload_0
    //   218: getfield mSlideableView : Landroid/view/View;
    //   221: fload #7
    //   223: f2i
    //   224: fload #6
    //   226: f2i
    //   227: invokevirtual isViewUnder : (Landroid/view/View;II)Z
    //   230: ifeq -> 249
    //   233: aload_0
    //   234: aload_0
    //   235: getfield mSlideableView : Landroid/view/View;
    //   238: invokevirtual isDimmed : (Landroid/view/View;)Z
    //   241: ifeq -> 249
    //   244: iconst_1
    //   245: istore_2
    //   246: goto -> 251
    //   249: iconst_0
    //   250: istore_2
    //   251: iload #4
    //   253: istore_3
    //   254: aload_0
    //   255: getfield mDragHelper : Landroid/support/v4/widget/ViewDragHelper;
    //   258: aload_1
    //   259: invokevirtual shouldInterceptTouchEvent : (Landroid/view/MotionEvent;)Z
    //   262: ifne -> 277
    //   265: iload_2
    //   266: ifeq -> 275
    //   269: iload #4
    //   271: istore_3
    //   272: goto -> 277
    //   275: iconst_0
    //   276: istore_3
    //   277: iload_3
    //   278: ireturn
    //   279: aload_0
    //   280: getfield mDragHelper : Landroid/support/v4/widget/ViewDragHelper;
    //   283: invokevirtual cancel : ()V
    //   286: iconst_0
    //   287: ireturn
    //   288: aload_0
    //   289: getfield mDragHelper : Landroid/support/v4/widget/ViewDragHelper;
    //   292: invokevirtual cancel : ()V
    //   295: aload_0
    //   296: aload_1
    //   297: invokespecial onInterceptTouchEvent : (Landroid/view/MotionEvent;)Z
    //   300: ireturn
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual isLayoutRtlSupport : ()Z
    //   4: istore #6
    //   6: iload #6
    //   8: ifeq -> 22
    //   11: aload_0
    //   12: getfield mDragHelper : Landroid/support/v4/widget/ViewDragHelper;
    //   15: iconst_2
    //   16: invokevirtual setEdgeTrackingEnabled : (I)V
    //   19: goto -> 30
    //   22: aload_0
    //   23: getfield mDragHelper : Landroid/support/v4/widget/ViewDragHelper;
    //   26: iconst_1
    //   27: invokevirtual setEdgeTrackingEnabled : (I)V
    //   30: iload #4
    //   32: iload_2
    //   33: isub
    //   34: istore #7
    //   36: iload #6
    //   38: ifeq -> 49
    //   41: aload_0
    //   42: invokevirtual getPaddingRight : ()I
    //   45: istore_2
    //   46: goto -> 54
    //   49: aload_0
    //   50: invokevirtual getPaddingLeft : ()I
    //   53: istore_2
    //   54: iload #6
    //   56: ifeq -> 68
    //   59: aload_0
    //   60: invokevirtual getPaddingLeft : ()I
    //   63: istore #5
    //   65: goto -> 74
    //   68: aload_0
    //   69: invokevirtual getPaddingRight : ()I
    //   72: istore #5
    //   74: aload_0
    //   75: invokevirtual getPaddingTop : ()I
    //   78: istore #8
    //   80: aload_0
    //   81: invokevirtual getChildCount : ()I
    //   84: istore #9
    //   86: aload_0
    //   87: getfield mFirstLayout : Z
    //   90: ifeq -> 122
    //   93: aload_0
    //   94: getfield mCanSlide : Z
    //   97: ifeq -> 113
    //   100: aload_0
    //   101: getfield mPreservedOpenState : Z
    //   104: ifeq -> 113
    //   107: fconst_1
    //   108: fstore #10
    //   110: goto -> 116
    //   113: fconst_0
    //   114: fstore #10
    //   116: aload_0
    //   117: fload #10
    //   119: putfield mSlideOffset : F
    //   122: iload_2
    //   123: istore_3
    //   124: iconst_0
    //   125: istore #11
    //   127: iload #11
    //   129: iload #9
    //   131: if_icmpge -> 424
    //   134: aload_0
    //   135: iload #11
    //   137: invokevirtual getChildAt : (I)Landroid/view/View;
    //   140: astore #12
    //   142: aload #12
    //   144: invokevirtual getVisibility : ()I
    //   147: bipush #8
    //   149: if_icmpne -> 155
    //   152: goto -> 418
    //   155: aload #12
    //   157: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   160: checkcast android/support/v4/widget/SlidingPaneLayout$LayoutParams
    //   163: astore #13
    //   165: aload #12
    //   167: invokevirtual getMeasuredWidth : ()I
    //   170: istore #14
    //   172: aload #13
    //   174: getfield slideable : Z
    //   177: ifeq -> 316
    //   180: aload #13
    //   182: getfield leftMargin : I
    //   185: istore #4
    //   187: aload #13
    //   189: getfield rightMargin : I
    //   192: istore #15
    //   194: iload #7
    //   196: iload #5
    //   198: isub
    //   199: istore #16
    //   201: iload_2
    //   202: iload #16
    //   204: aload_0
    //   205: getfield mOverhangSize : I
    //   208: isub
    //   209: invokestatic min : (II)I
    //   212: iload_3
    //   213: isub
    //   214: iload #4
    //   216: iload #15
    //   218: iadd
    //   219: isub
    //   220: istore #15
    //   222: aload_0
    //   223: iload #15
    //   225: putfield mSlideRange : I
    //   228: iload #6
    //   230: ifeq -> 243
    //   233: aload #13
    //   235: getfield rightMargin : I
    //   238: istore #4
    //   240: goto -> 250
    //   243: aload #13
    //   245: getfield leftMargin : I
    //   248: istore #4
    //   250: iload_3
    //   251: iload #4
    //   253: iadd
    //   254: iload #15
    //   256: iadd
    //   257: iload #14
    //   259: iconst_2
    //   260: idiv
    //   261: iadd
    //   262: iload #16
    //   264: if_icmple -> 272
    //   267: iconst_1
    //   268: istore_1
    //   269: goto -> 274
    //   272: iconst_0
    //   273: istore_1
    //   274: aload #13
    //   276: iload_1
    //   277: putfield dimWhenOffset : Z
    //   280: iload #15
    //   282: i2f
    //   283: aload_0
    //   284: getfield mSlideOffset : F
    //   287: fmul
    //   288: f2i
    //   289: istore #16
    //   291: iload_3
    //   292: iload #4
    //   294: iload #16
    //   296: iadd
    //   297: iadd
    //   298: istore_3
    //   299: aload_0
    //   300: iload #16
    //   302: i2f
    //   303: iload #15
    //   305: i2f
    //   306: fdiv
    //   307: putfield mSlideOffset : F
    //   310: iconst_0
    //   311: istore #4
    //   313: goto -> 354
    //   316: aload_0
    //   317: getfield mCanSlide : Z
    //   320: ifeq -> 349
    //   323: aload_0
    //   324: getfield mParallaxBy : I
    //   327: istore_3
    //   328: iload_3
    //   329: ifeq -> 349
    //   332: fconst_1
    //   333: aload_0
    //   334: getfield mSlideOffset : F
    //   337: fsub
    //   338: iload_3
    //   339: i2f
    //   340: fmul
    //   341: f2i
    //   342: istore #4
    //   344: iload_2
    //   345: istore_3
    //   346: goto -> 354
    //   349: iload_2
    //   350: istore_3
    //   351: iconst_0
    //   352: istore #4
    //   354: iload #6
    //   356: ifeq -> 378
    //   359: iload #7
    //   361: iload_3
    //   362: isub
    //   363: iload #4
    //   365: iadd
    //   366: istore #4
    //   368: iload #4
    //   370: iload #14
    //   372: isub
    //   373: istore #16
    //   375: goto -> 391
    //   378: iload_3
    //   379: iload #4
    //   381: isub
    //   382: istore #16
    //   384: iload #16
    //   386: iload #14
    //   388: iadd
    //   389: istore #4
    //   391: aload #12
    //   393: iload #16
    //   395: iload #8
    //   397: iload #4
    //   399: aload #12
    //   401: invokevirtual getMeasuredHeight : ()I
    //   404: iload #8
    //   406: iadd
    //   407: invokevirtual layout : (IIII)V
    //   410: iload_2
    //   411: aload #12
    //   413: invokevirtual getWidth : ()I
    //   416: iadd
    //   417: istore_2
    //   418: iinc #11, 1
    //   421: goto -> 127
    //   424: aload_0
    //   425: getfield mFirstLayout : Z
    //   428: ifeq -> 524
    //   431: aload_0
    //   432: getfield mCanSlide : Z
    //   435: ifeq -> 488
    //   438: aload_0
    //   439: getfield mParallaxBy : I
    //   442: ifeq -> 453
    //   445: aload_0
    //   446: aload_0
    //   447: getfield mSlideOffset : F
    //   450: invokespecial parallaxOtherViews : (F)V
    //   453: aload_0
    //   454: getfield mSlideableView : Landroid/view/View;
    //   457: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   460: checkcast android/support/v4/widget/SlidingPaneLayout$LayoutParams
    //   463: getfield dimWhenOffset : Z
    //   466: ifeq -> 516
    //   469: aload_0
    //   470: aload_0
    //   471: getfield mSlideableView : Landroid/view/View;
    //   474: aload_0
    //   475: getfield mSlideOffset : F
    //   478: aload_0
    //   479: getfield mSliderFadeColor : I
    //   482: invokespecial dimChildView : (Landroid/view/View;FI)V
    //   485: goto -> 516
    //   488: iconst_0
    //   489: istore_2
    //   490: iload_2
    //   491: iload #9
    //   493: if_icmpge -> 516
    //   496: aload_0
    //   497: aload_0
    //   498: iload_2
    //   499: invokevirtual getChildAt : (I)Landroid/view/View;
    //   502: fconst_0
    //   503: aload_0
    //   504: getfield mSliderFadeColor : I
    //   507: invokespecial dimChildView : (Landroid/view/View;FI)V
    //   510: iinc #2, 1
    //   513: goto -> 490
    //   516: aload_0
    //   517: aload_0
    //   518: getfield mSlideableView : Landroid/view/View;
    //   521: invokevirtual updateObscuredViewsVisibility : (Landroid/view/View;)V
    //   524: aload_0
    //   525: iconst_0
    //   526: putfield mFirstLayout : Z
    //   529: return
  }
  
  protected void onMeasure(int paramInt1, int paramInt2) {
    int m;
    int n;
    int i = View.MeasureSpec.getMode(paramInt1);
    int j = View.MeasureSpec.getSize(paramInt1);
    int k = View.MeasureSpec.getMode(paramInt2);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    if (i != 1073741824) {
      if (isInEditMode()) {
        if (i == Integer.MIN_VALUE) {
          m = j;
          n = k;
          paramInt1 = paramInt2;
        } else {
          m = j;
          n = k;
          paramInt1 = paramInt2;
          if (i == 0) {
            m = 300;
            n = k;
            paramInt1 = paramInt2;
          } 
        } 
      } else {
        throw new IllegalStateException("Width must have an exact value or MATCH_PARENT");
      } 
    } else {
      m = j;
      n = k;
      paramInt1 = paramInt2;
      if (k == 0)
        if (isInEditMode()) {
          m = j;
          n = k;
          paramInt1 = paramInt2;
          if (k == 0) {
            n = Integer.MIN_VALUE;
            paramInt1 = 300;
            m = j;
          } 
        } else {
          throw new IllegalStateException("Height must not be UNSPECIFIED");
        }  
    } 
    if (n != Integer.MIN_VALUE) {
      if (n != 1073741824) {
        paramInt1 = 0;
        paramInt2 = 0;
      } else {
        paramInt1 = paramInt1 - getPaddingTop() - getPaddingBottom();
        paramInt2 = paramInt1;
      } 
    } else {
      paramInt2 = paramInt1 - getPaddingTop() - getPaddingBottom();
      paramInt1 = 0;
    } 
    int i1 = m - getPaddingLeft() - getPaddingRight();
    int i2 = getChildCount();
    if (i2 > 2)
      Log.e("SlidingPaneLayout", "onMeasure: More than two child views are not supported."); 
    this.mSlideableView = null;
    i = i1;
    int i3 = 0;
    int i4 = 0;
    float f = 0.0F;
    for (j = paramInt1; i3 < i2; j = paramInt1) {
      byte b;
      View view = getChildAt(i3);
      LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
      if (view.getVisibility() == 8) {
        layoutParams.dimWhenOffset = false;
        paramInt1 = j;
        continue;
      } 
      float f1 = layoutParams.weight;
      float f2 = f;
      if (f1 > 0.0F) {
        f += f1;
        f2 = f;
        if (layoutParams.width == 0) {
          paramInt1 = j;
          continue;
        } 
      } 
      k = layoutParams.leftMargin + layoutParams.rightMargin;
      paramInt1 = layoutParams.width;
      if (paramInt1 == -2) {
        paramInt1 = View.MeasureSpec.makeMeasureSpec(i1 - k, -2147483648);
      } else if (paramInt1 == -1) {
        paramInt1 = View.MeasureSpec.makeMeasureSpec(i1 - k, 1073741824);
      } else {
        paramInt1 = View.MeasureSpec.makeMeasureSpec(paramInt1, 1073741824);
      } 
      k = layoutParams.height;
      if (k == -2) {
        k = View.MeasureSpec.makeMeasureSpec(paramInt2, -2147483648);
      } else if (k == -1) {
        k = View.MeasureSpec.makeMeasureSpec(paramInt2, 1073741824);
      } else {
        k = View.MeasureSpec.makeMeasureSpec(k, 1073741824);
      } 
      view.measure(paramInt1, k);
      k = view.getMeasuredWidth();
      int i5 = view.getMeasuredHeight();
      paramInt1 = j;
      if (n == Integer.MIN_VALUE) {
        paramInt1 = j;
        if (i5 > j)
          paramInt1 = Math.min(i5, paramInt2); 
      } 
      i -= k;
      if (i < 0) {
        b = 1;
      } else {
        b = 0;
      } 
      layoutParams.slideable = b;
      i4 |= b;
      if (b != 0)
        this.mSlideableView = view; 
      f = f2;
      continue;
      i3++;
    } 
    if (i4 != 0 || f > 0.0F) {
      k = i1 - this.mOverhangSize;
      for (n = 0; n < i2; n++) {
        View view = getChildAt(n);
        if (view.getVisibility() != 8) {
          LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
          if (view.getVisibility() != 8) {
            if (layoutParams.width == 0 && layoutParams.weight > 0.0F) {
              paramInt1 = 1;
            } else {
              paramInt1 = 0;
            } 
            if (paramInt1 != 0) {
              i3 = 0;
            } else {
              i3 = view.getMeasuredWidth();
            } 
            if (i4 != 0 && view != this.mSlideableView) {
              if (layoutParams.width < 0 && (i3 > k || layoutParams.weight > 0.0F)) {
                if (paramInt1 != 0) {
                  paramInt1 = layoutParams.height;
                  if (paramInt1 == -2) {
                    paramInt1 = View.MeasureSpec.makeMeasureSpec(paramInt2, -2147483648);
                  } else if (paramInt1 == -1) {
                    paramInt1 = View.MeasureSpec.makeMeasureSpec(paramInt2, 1073741824);
                  } else {
                    paramInt1 = View.MeasureSpec.makeMeasureSpec(paramInt1, 1073741824);
                  } 
                } else {
                  paramInt1 = View.MeasureSpec.makeMeasureSpec(view.getMeasuredHeight(), 1073741824);
                } 
                view.measure(View.MeasureSpec.makeMeasureSpec(k, 1073741824), paramInt1);
              } 
            } else if (layoutParams.weight > 0.0F) {
              if (layoutParams.width == 0) {
                paramInt1 = layoutParams.height;
                if (paramInt1 == -2) {
                  paramInt1 = View.MeasureSpec.makeMeasureSpec(paramInt2, -2147483648);
                } else if (paramInt1 == -1) {
                  paramInt1 = View.MeasureSpec.makeMeasureSpec(paramInt2, 1073741824);
                } else {
                  paramInt1 = View.MeasureSpec.makeMeasureSpec(paramInt1, 1073741824);
                } 
              } else {
                paramInt1 = View.MeasureSpec.makeMeasureSpec(view.getMeasuredHeight(), 1073741824);
              } 
              if (i4 != 0) {
                int i6 = i1 - layoutParams.leftMargin + layoutParams.rightMargin;
                int i5 = View.MeasureSpec.makeMeasureSpec(i6, 1073741824);
                if (i3 != i6)
                  view.measure(i5, paramInt1); 
              } else {
                int i5 = Math.max(0, i);
                view.measure(View.MeasureSpec.makeMeasureSpec(i3 + (int)(layoutParams.weight * i5 / f), 1073741824), paramInt1);
              } 
            } 
          } 
        } 
      } 
    } 
    setMeasuredDimension(m, j + getPaddingTop() + getPaddingBottom());
    this.mCanSlide = i4;
    if (this.mDragHelper.getViewDragState() != 0 && i4 == 0)
      this.mDragHelper.abort(); 
  }
  
  void onPanelDragged(int paramInt) {
    if (this.mSlideableView == null) {
      this.mSlideOffset = 0.0F;
      return;
    } 
    boolean bool = isLayoutRtlSupport();
    LayoutParams layoutParams = (LayoutParams)this.mSlideableView.getLayoutParams();
    int i = this.mSlideableView.getWidth();
    int j = paramInt;
    if (bool)
      j = getWidth() - paramInt - i; 
    if (bool) {
      paramInt = getPaddingRight();
    } else {
      paramInt = getPaddingLeft();
    } 
    if (bool) {
      i = layoutParams.rightMargin;
    } else {
      i = layoutParams.leftMargin;
    } 
    float f = (j - paramInt + i) / this.mSlideRange;
    this.mSlideOffset = f;
    if (this.mParallaxBy != 0)
      parallaxOtherViews(f); 
    if (layoutParams.dimWhenOffset)
      dimChildView(this.mSlideableView, this.mSlideOffset, this.mSliderFadeColor); 
    dispatchOnPanelSlide(this.mSlideableView);
  }
  
  protected void onRestoreInstanceState(Parcelable paramParcelable) {
    if (!(paramParcelable instanceof SavedState)) {
      super.onRestoreInstanceState(paramParcelable);
      return;
    } 
    SavedState savedState = (SavedState)paramParcelable;
    super.onRestoreInstanceState(savedState.getSuperState());
    if (savedState.isOpen) {
      openPane();
    } else {
      closePane();
    } 
    this.mPreservedOpenState = savedState.isOpen;
  }
  
  protected Parcelable onSaveInstanceState() {
    boolean bool;
    SavedState savedState = new SavedState(super.onSaveInstanceState());
    if (isSlideable()) {
      bool = isOpen();
    } else {
      bool = this.mPreservedOpenState;
    } 
    savedState.isOpen = bool;
    return (Parcelable)savedState;
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if (paramInt1 != paramInt3)
      this.mFirstLayout = true; 
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent) {
    if (!this.mCanSlide)
      return super.onTouchEvent(paramMotionEvent); 
    this.mDragHelper.processTouchEvent(paramMotionEvent);
    int i = paramMotionEvent.getActionMasked();
    if (i != 0) {
      if (i == 1 && isDimmed(this.mSlideableView)) {
        float f1 = paramMotionEvent.getX();
        float f2 = paramMotionEvent.getY();
        float f3 = f1 - this.mInitialMotionX;
        float f4 = f2 - this.mInitialMotionY;
        i = this.mDragHelper.getTouchSlop();
        if (f3 * f3 + f4 * f4 < (i * i) && this.mDragHelper.isViewUnder(this.mSlideableView, (int)f1, (int)f2))
          closePane(this.mSlideableView, 0); 
      } 
    } else {
      float f2 = paramMotionEvent.getX();
      float f1 = paramMotionEvent.getY();
      this.mInitialMotionX = f2;
      this.mInitialMotionY = f1;
    } 
    return true;
  }
  
  public boolean openPane() {
    return openPane(this.mSlideableView, 0);
  }
  
  public void requestChildFocus(View paramView1, View paramView2) {
    super.requestChildFocus(paramView1, paramView2);
    if (!isInTouchMode() && !this.mCanSlide) {
      boolean bool;
      if (paramView1 == this.mSlideableView) {
        bool = true;
      } else {
        bool = false;
      } 
      this.mPreservedOpenState = bool;
    } 
  }
  
  void setAllChildrenVisible() {
    int i = getChildCount();
    for (byte b = 0; b < i; b++) {
      View view = getChildAt(b);
      if (view.getVisibility() == 4)
        view.setVisibility(0); 
    } 
  }
  
  public void setCoveredFadeColor(@ColorInt int paramInt) {
    this.mCoveredFadeColor = paramInt;
  }
  
  public void setPanelSlideListener(PanelSlideListener paramPanelSlideListener) {
    this.mPanelSlideListener = paramPanelSlideListener;
  }
  
  public void setParallaxDistance(int paramInt) {
    this.mParallaxBy = paramInt;
    requestLayout();
  }
  
  @Deprecated
  public void setShadowDrawable(Drawable paramDrawable) {
    setShadowDrawableLeft(paramDrawable);
  }
  
  public void setShadowDrawableLeft(Drawable paramDrawable) {
    this.mShadowDrawableLeft = paramDrawable;
  }
  
  public void setShadowDrawableRight(Drawable paramDrawable) {
    this.mShadowDrawableRight = paramDrawable;
  }
  
  @Deprecated
  public void setShadowResource(@DrawableRes int paramInt) {
    setShadowDrawable(getResources().getDrawable(paramInt));
  }
  
  public void setShadowResourceLeft(int paramInt) {
    setShadowDrawableLeft(ContextCompat.getDrawable(getContext(), paramInt));
  }
  
  public void setShadowResourceRight(int paramInt) {
    setShadowDrawableRight(ContextCompat.getDrawable(getContext(), paramInt));
  }
  
  public void setSliderFadeColor(@ColorInt int paramInt) {
    this.mSliderFadeColor = paramInt;
  }
  
  @Deprecated
  public void smoothSlideClosed() {
    closePane();
  }
  
  @Deprecated
  public void smoothSlideOpen() {
    openPane();
  }
  
  boolean smoothSlideTo(float paramFloat, int paramInt) {
    if (!this.mCanSlide)
      return false; 
    boolean bool = isLayoutRtlSupport();
    LayoutParams layoutParams = (LayoutParams)this.mSlideableView.getLayoutParams();
    if (bool) {
      paramInt = getPaddingRight();
      int i = layoutParams.rightMargin;
      int j = this.mSlideableView.getWidth();
      paramInt = (int)(getWidth() - (paramInt + i) + paramFloat * this.mSlideRange + j);
    } else {
      paramInt = (int)((getPaddingLeft() + layoutParams.leftMargin) + paramFloat * this.mSlideRange);
    } 
    ViewDragHelper viewDragHelper = this.mDragHelper;
    View view = this.mSlideableView;
    if (viewDragHelper.smoothSlideViewTo(view, paramInt, view.getTop())) {
      setAllChildrenVisible();
      ViewCompat.postInvalidateOnAnimation((View)this);
      return true;
    } 
    return false;
  }
  
  void updateObscuredViewsVisibility(View paramView) {
    int i;
    int j;
    byte b1;
    byte b2;
    byte b3;
    byte b4;
    boolean bool = isLayoutRtlSupport();
    if (bool) {
      i = getWidth() - getPaddingRight();
    } else {
      i = getPaddingLeft();
    } 
    if (bool) {
      j = getPaddingLeft();
    } else {
      j = getWidth() - getPaddingRight();
    } 
    int k = getPaddingTop();
    int m = getHeight();
    int n = getPaddingBottom();
    if (paramView != null && viewIsOpaque(paramView)) {
      b1 = paramView.getLeft();
      b2 = paramView.getRight();
      b3 = paramView.getTop();
      b4 = paramView.getBottom();
    } else {
      b1 = 0;
      b2 = 0;
      b3 = 0;
      b4 = 0;
    } 
    int i1 = getChildCount();
    for (byte b5 = 0; b5 < i1; b5++) {
      View view = getChildAt(b5);
      if (view == paramView)
        break; 
      if (view.getVisibility() != 8) {
        if (bool) {
          i2 = j;
        } else {
          i2 = i;
        } 
        int i3 = Math.max(i2, view.getLeft());
        int i4 = Math.max(k, view.getTop());
        if (bool) {
          i2 = i;
        } else {
          i2 = j;
        } 
        int i5 = Math.min(i2, view.getRight());
        int i2 = Math.min(m - n, view.getBottom());
        if (i3 >= b1 && i4 >= b3 && i5 <= b2 && i2 <= b4) {
          i2 = 4;
        } else {
          i2 = 0;
        } 
        view.setVisibility(i2);
      } 
    } 
  }
  
  class AccessibilityDelegate extends AccessibilityDelegateCompat {
    private final Rect mTmpRect = new Rect();
    
    private void copyNodeInfoNoChildren(AccessibilityNodeInfoCompat param1AccessibilityNodeInfoCompat1, AccessibilityNodeInfoCompat param1AccessibilityNodeInfoCompat2) {
      Rect rect = this.mTmpRect;
      param1AccessibilityNodeInfoCompat2.getBoundsInParent(rect);
      param1AccessibilityNodeInfoCompat1.setBoundsInParent(rect);
      param1AccessibilityNodeInfoCompat2.getBoundsInScreen(rect);
      param1AccessibilityNodeInfoCompat1.setBoundsInScreen(rect);
      param1AccessibilityNodeInfoCompat1.setVisibleToUser(param1AccessibilityNodeInfoCompat2.isVisibleToUser());
      param1AccessibilityNodeInfoCompat1.setPackageName(param1AccessibilityNodeInfoCompat2.getPackageName());
      param1AccessibilityNodeInfoCompat1.setClassName(param1AccessibilityNodeInfoCompat2.getClassName());
      param1AccessibilityNodeInfoCompat1.setContentDescription(param1AccessibilityNodeInfoCompat2.getContentDescription());
      param1AccessibilityNodeInfoCompat1.setEnabled(param1AccessibilityNodeInfoCompat2.isEnabled());
      param1AccessibilityNodeInfoCompat1.setClickable(param1AccessibilityNodeInfoCompat2.isClickable());
      param1AccessibilityNodeInfoCompat1.setFocusable(param1AccessibilityNodeInfoCompat2.isFocusable());
      param1AccessibilityNodeInfoCompat1.setFocused(param1AccessibilityNodeInfoCompat2.isFocused());
      param1AccessibilityNodeInfoCompat1.setAccessibilityFocused(param1AccessibilityNodeInfoCompat2.isAccessibilityFocused());
      param1AccessibilityNodeInfoCompat1.setSelected(param1AccessibilityNodeInfoCompat2.isSelected());
      param1AccessibilityNodeInfoCompat1.setLongClickable(param1AccessibilityNodeInfoCompat2.isLongClickable());
      param1AccessibilityNodeInfoCompat1.addAction(param1AccessibilityNodeInfoCompat2.getActions());
      param1AccessibilityNodeInfoCompat1.setMovementGranularities(param1AccessibilityNodeInfoCompat2.getMovementGranularities());
    }
    
    public boolean filter(View param1View) {
      return SlidingPaneLayout.this.isDimmed(param1View);
    }
    
    public void onInitializeAccessibilityEvent(View param1View, AccessibilityEvent param1AccessibilityEvent) {
      super.onInitializeAccessibilityEvent(param1View, param1AccessibilityEvent);
      param1AccessibilityEvent.setClassName(SlidingPaneLayout.class.getName());
    }
    
    public void onInitializeAccessibilityNodeInfo(View param1View, AccessibilityNodeInfoCompat param1AccessibilityNodeInfoCompat) {
      AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = AccessibilityNodeInfoCompat.obtain(param1AccessibilityNodeInfoCompat);
      super.onInitializeAccessibilityNodeInfo(param1View, accessibilityNodeInfoCompat);
      copyNodeInfoNoChildren(param1AccessibilityNodeInfoCompat, accessibilityNodeInfoCompat);
      accessibilityNodeInfoCompat.recycle();
      param1AccessibilityNodeInfoCompat.setClassName(SlidingPaneLayout.class.getName());
      param1AccessibilityNodeInfoCompat.setSource(param1View);
      ViewParent viewParent = ViewCompat.getParentForAccessibility(param1View);
      if (viewParent instanceof View)
        param1AccessibilityNodeInfoCompat.setParent((View)viewParent); 
      int i = SlidingPaneLayout.this.getChildCount();
      for (byte b = 0; b < i; b++) {
        View view = SlidingPaneLayout.this.getChildAt(b);
        if (!filter(view) && view.getVisibility() == 0) {
          ViewCompat.setImportantForAccessibility(view, 1);
          param1AccessibilityNodeInfoCompat.addChild(view);
        } 
      } 
    }
    
    public boolean onRequestSendAccessibilityEvent(ViewGroup param1ViewGroup, View param1View, AccessibilityEvent param1AccessibilityEvent) {
      return !filter(param1View) ? super.onRequestSendAccessibilityEvent(param1ViewGroup, param1View, param1AccessibilityEvent) : false;
    }
  }
  
  private class DisableLayerRunnable implements Runnable {
    final View mChildView;
    
    DisableLayerRunnable(View param1View) {
      this.mChildView = param1View;
    }
    
    public void run() {
      if (this.mChildView.getParent() == SlidingPaneLayout.this) {
        this.mChildView.setLayerType(0, null);
        SlidingPaneLayout.this.invalidateChildRegion(this.mChildView);
      } 
      SlidingPaneLayout.this.mPostedRunnables.remove(this);
    }
  }
  
  private class DragHelperCallback extends ViewDragHelper.Callback {
    public int clampViewPositionHorizontal(View param1View, int param1Int1, int param1Int2) {
      SlidingPaneLayout.LayoutParams layoutParams = (SlidingPaneLayout.LayoutParams)SlidingPaneLayout.this.mSlideableView.getLayoutParams();
      if (SlidingPaneLayout.this.isLayoutRtlSupport()) {
        param1Int2 = SlidingPaneLayout.this.getWidth() - SlidingPaneLayout.this.getPaddingRight() + layoutParams.rightMargin + SlidingPaneLayout.this.mSlideableView.getWidth();
        int i = SlidingPaneLayout.this.mSlideRange;
        param1Int1 = Math.max(Math.min(param1Int1, param1Int2), param1Int2 - i);
      } else {
        int i = SlidingPaneLayout.this.getPaddingLeft() + layoutParams.leftMargin;
        param1Int2 = SlidingPaneLayout.this.mSlideRange;
        param1Int1 = Math.min(Math.max(param1Int1, i), param1Int2 + i);
      } 
      return param1Int1;
    }
    
    public int clampViewPositionVertical(View param1View, int param1Int1, int param1Int2) {
      return param1View.getTop();
    }
    
    public int getViewHorizontalDragRange(View param1View) {
      return SlidingPaneLayout.this.mSlideRange;
    }
    
    public void onEdgeDragStarted(int param1Int1, int param1Int2) {
      SlidingPaneLayout slidingPaneLayout = SlidingPaneLayout.this;
      slidingPaneLayout.mDragHelper.captureChildView(slidingPaneLayout.mSlideableView, param1Int2);
    }
    
    public void onViewCaptured(View param1View, int param1Int) {
      SlidingPaneLayout.this.setAllChildrenVisible();
    }
    
    public void onViewDragStateChanged(int param1Int) {
      if (SlidingPaneLayout.this.mDragHelper.getViewDragState() == 0) {
        SlidingPaneLayout slidingPaneLayout = SlidingPaneLayout.this;
        if (slidingPaneLayout.mSlideOffset == 0.0F) {
          slidingPaneLayout.updateObscuredViewsVisibility(slidingPaneLayout.mSlideableView);
          slidingPaneLayout = SlidingPaneLayout.this;
          slidingPaneLayout.dispatchOnPanelClosed(slidingPaneLayout.mSlideableView);
          SlidingPaneLayout.this.mPreservedOpenState = false;
        } else {
          slidingPaneLayout.dispatchOnPanelOpened(slidingPaneLayout.mSlideableView);
          SlidingPaneLayout.this.mPreservedOpenState = true;
        } 
      } 
    }
    
    public void onViewPositionChanged(View param1View, int param1Int1, int param1Int2, int param1Int3, int param1Int4) {
      SlidingPaneLayout.this.onPanelDragged(param1Int1);
      SlidingPaneLayout.this.invalidate();
    }
    
    public void onViewReleased(View param1View, float param1Float1, float param1Float2) {
      // Byte code:
      //   0: aload_1
      //   1: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
      //   4: checkcast android/support/v4/widget/SlidingPaneLayout$LayoutParams
      //   7: astore #4
      //   9: aload_0
      //   10: getfield this$0 : Landroid/support/v4/widget/SlidingPaneLayout;
      //   13: invokevirtual isLayoutRtlSupport : ()Z
      //   16: ifeq -> 109
      //   19: aload_0
      //   20: getfield this$0 : Landroid/support/v4/widget/SlidingPaneLayout;
      //   23: invokevirtual getPaddingRight : ()I
      //   26: aload #4
      //   28: getfield rightMargin : I
      //   31: iadd
      //   32: istore #5
      //   34: fload_2
      //   35: fconst_0
      //   36: fcmpg
      //   37: iflt -> 67
      //   40: iload #5
      //   42: istore #6
      //   44: fload_2
      //   45: fconst_0
      //   46: fcmpl
      //   47: ifne -> 79
      //   50: iload #5
      //   52: istore #6
      //   54: aload_0
      //   55: getfield this$0 : Landroid/support/v4/widget/SlidingPaneLayout;
      //   58: getfield mSlideOffset : F
      //   61: ldc 0.5
      //   63: fcmpl
      //   64: ifle -> 79
      //   67: iload #5
      //   69: aload_0
      //   70: getfield this$0 : Landroid/support/v4/widget/SlidingPaneLayout;
      //   73: getfield mSlideRange : I
      //   76: iadd
      //   77: istore #6
      //   79: aload_0
      //   80: getfield this$0 : Landroid/support/v4/widget/SlidingPaneLayout;
      //   83: getfield mSlideableView : Landroid/view/View;
      //   86: invokevirtual getWidth : ()I
      //   89: istore #5
      //   91: aload_0
      //   92: getfield this$0 : Landroid/support/v4/widget/SlidingPaneLayout;
      //   95: invokevirtual getWidth : ()I
      //   98: iload #6
      //   100: isub
      //   101: iload #5
      //   103: isub
      //   104: istore #6
      //   106: goto -> 173
      //   109: aload_0
      //   110: getfield this$0 : Landroid/support/v4/widget/SlidingPaneLayout;
      //   113: invokevirtual getPaddingLeft : ()I
      //   116: istore #6
      //   118: aload #4
      //   120: getfield leftMargin : I
      //   123: iload #6
      //   125: iadd
      //   126: istore #5
      //   128: fload_2
      //   129: fconst_0
      //   130: fcmpl
      //   131: ifgt -> 161
      //   134: iload #5
      //   136: istore #6
      //   138: fload_2
      //   139: fconst_0
      //   140: fcmpl
      //   141: ifne -> 173
      //   144: iload #5
      //   146: istore #6
      //   148: aload_0
      //   149: getfield this$0 : Landroid/support/v4/widget/SlidingPaneLayout;
      //   152: getfield mSlideOffset : F
      //   155: ldc 0.5
      //   157: fcmpl
      //   158: ifle -> 173
      //   161: iload #5
      //   163: aload_0
      //   164: getfield this$0 : Landroid/support/v4/widget/SlidingPaneLayout;
      //   167: getfield mSlideRange : I
      //   170: iadd
      //   171: istore #6
      //   173: aload_0
      //   174: getfield this$0 : Landroid/support/v4/widget/SlidingPaneLayout;
      //   177: getfield mDragHelper : Landroid/support/v4/widget/ViewDragHelper;
      //   180: iload #6
      //   182: aload_1
      //   183: invokevirtual getTop : ()I
      //   186: invokevirtual settleCapturedViewAt : (II)Z
      //   189: pop
      //   190: aload_0
      //   191: getfield this$0 : Landroid/support/v4/widget/SlidingPaneLayout;
      //   194: invokevirtual invalidate : ()V
      //   197: return
    }
    
    public boolean tryCaptureView(View param1View, int param1Int) {
      return SlidingPaneLayout.this.mIsUnableToDrag ? false : ((SlidingPaneLayout.LayoutParams)param1View.getLayoutParams()).slideable;
    }
  }
  
  public static class LayoutParams extends ViewGroup.MarginLayoutParams {
    private static final int[] ATTRS = new int[] { 16843137 };
    
    Paint dimPaint;
    
    boolean dimWhenOffset;
    
    boolean slideable;
    
    public float weight = 0.0F;
    
    public LayoutParams() {
      super(-1, -1);
    }
    
    public LayoutParams(int param1Int1, int param1Int2) {
      super(param1Int1, param1Int2);
    }
    
    public LayoutParams(Context param1Context, AttributeSet param1AttributeSet) {
      super(param1Context, param1AttributeSet);
      TypedArray typedArray = param1Context.obtainStyledAttributes(param1AttributeSet, ATTRS);
      this.weight = typedArray.getFloat(0, 0.0F);
      typedArray.recycle();
    }
    
    public LayoutParams(LayoutParams param1LayoutParams) {
      super(param1LayoutParams);
      this.weight = param1LayoutParams.weight;
    }
    
    public LayoutParams(ViewGroup.LayoutParams param1LayoutParams) {
      super(param1LayoutParams);
    }
    
    public LayoutParams(ViewGroup.MarginLayoutParams param1MarginLayoutParams) {
      super(param1MarginLayoutParams);
    }
  }
  
  public static interface PanelSlideListener {
    void onPanelClosed(View param1View);
    
    void onPanelOpened(View param1View);
    
    void onPanelSlide(View param1View, float param1Float);
  }
  
  static class SavedState extends AbsSavedState {
    public static final Parcelable.Creator<SavedState> CREATOR = (Parcelable.Creator<SavedState>)new Parcelable.ClassLoaderCreator<SavedState>() {
        public SlidingPaneLayout.SavedState createFromParcel(Parcel param2Parcel) {
          return new SlidingPaneLayout.SavedState(param2Parcel, null);
        }
        
        public SlidingPaneLayout.SavedState createFromParcel(Parcel param2Parcel, ClassLoader param2ClassLoader) {
          return new SlidingPaneLayout.SavedState(param2Parcel, null);
        }
        
        public SlidingPaneLayout.SavedState[] newArray(int param2Int) {
          return new SlidingPaneLayout.SavedState[param2Int];
        }
      };
    
    boolean isOpen;
    
    SavedState(Parcel param1Parcel, ClassLoader param1ClassLoader) {
      super(param1Parcel, param1ClassLoader);
      boolean bool;
      if (param1Parcel.readInt() != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      this.isOpen = bool;
    }
    
    SavedState(Parcelable param1Parcelable) {
      super(param1Parcelable);
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      super.writeToParcel(param1Parcel, param1Int);
      param1Parcel.writeInt(this.isOpen);
    }
  }
  
  static final class null implements Parcelable.ClassLoaderCreator<SavedState> {
    public SlidingPaneLayout.SavedState createFromParcel(Parcel param1Parcel) {
      return new SlidingPaneLayout.SavedState(param1Parcel, null);
    }
    
    public SlidingPaneLayout.SavedState createFromParcel(Parcel param1Parcel, ClassLoader param1ClassLoader) {
      return new SlidingPaneLayout.SavedState(param1Parcel, null);
    }
    
    public SlidingPaneLayout.SavedState[] newArray(int param1Int) {
      return new SlidingPaneLayout.SavedState[param1Int];
    }
  }
  
  public static class SimplePanelSlideListener implements PanelSlideListener {
    public void onPanelClosed(View param1View) {}
    
    public void onPanelOpened(View param1View) {}
    
    public void onPanelSlide(View param1View, float param1Float) {}
  }
  
  static interface SlidingPanelLayoutImpl {
    void invalidateChildRegion(SlidingPaneLayout param1SlidingPaneLayout, View param1View);
  }
  
  static class SlidingPanelLayoutImplBase implements SlidingPanelLayoutImpl {
    public void invalidateChildRegion(SlidingPaneLayout param1SlidingPaneLayout, View param1View) {
      ViewCompat.postInvalidateOnAnimation((View)param1SlidingPaneLayout, param1View.getLeft(), param1View.getTop(), param1View.getRight(), param1View.getBottom());
    }
  }
  
  @RequiresApi(16)
  static class SlidingPanelLayoutImplJB extends SlidingPanelLayoutImplBase {
    private Method mGetDisplayList;
    
    private Field mRecreateDisplayList;
    
    SlidingPanelLayoutImplJB() {
      try {
        this.mGetDisplayList = View.class.getDeclaredMethod("getDisplayList", null);
      } catch (NoSuchMethodException noSuchMethodException) {
        Log.e("SlidingPaneLayout", "Couldn't fetch getDisplayList method; dimming won't work right.", noSuchMethodException);
      } 
      try {
        Field field = View.class.getDeclaredField("mRecreateDisplayList");
        this.mRecreateDisplayList = field;
        field.setAccessible(true);
      } catch (NoSuchFieldException noSuchFieldException) {
        Log.e("SlidingPaneLayout", "Couldn't fetch mRecreateDisplayList field; dimming will be slow.", noSuchFieldException);
      } 
    }
    
    public void invalidateChildRegion(SlidingPaneLayout param1SlidingPaneLayout, View param1View) {
      if (this.mGetDisplayList != null) {
        Field field = this.mRecreateDisplayList;
        if (field != null) {
          try {
            field.setBoolean(param1View, true);
            this.mGetDisplayList.invoke(param1View, null);
          } catch (Exception exception) {
            Log.e("SlidingPaneLayout", "Error refreshing display list state", exception);
          } 
          super.invalidateChildRegion(param1SlidingPaneLayout, param1View);
          return;
        } 
      } 
      param1View.invalidate();
    }
  }
  
  @RequiresApi(17)
  static class SlidingPanelLayoutImplJBMR1 extends SlidingPanelLayoutImplBase {
    public void invalidateChildRegion(SlidingPaneLayout param1SlidingPaneLayout, View param1View) {
      ViewCompat.setLayerPaint(param1View, ((SlidingPaneLayout.LayoutParams)param1View.getLayoutParams()).dimPaint);
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/widget/SlidingPaneLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */