package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class LinearLayoutCompat extends ViewGroup {
  public static final int HORIZONTAL = 0;
  
  private static final int INDEX_BOTTOM = 2;
  
  private static final int INDEX_CENTER_VERTICAL = 0;
  
  private static final int INDEX_FILL = 3;
  
  private static final int INDEX_TOP = 1;
  
  public static final int SHOW_DIVIDER_BEGINNING = 1;
  
  public static final int SHOW_DIVIDER_END = 4;
  
  public static final int SHOW_DIVIDER_MIDDLE = 2;
  
  public static final int SHOW_DIVIDER_NONE = 0;
  
  public static final int VERTICAL = 1;
  
  private static final int VERTICAL_GRAVITY_COUNT = 4;
  
  private boolean mBaselineAligned = true;
  
  private int mBaselineAlignedChildIndex = -1;
  
  private int mBaselineChildTop = 0;
  
  private Drawable mDivider;
  
  private int mDividerHeight;
  
  private int mDividerPadding;
  
  private int mDividerWidth;
  
  private int mGravity = 8388659;
  
  private int[] mMaxAscent;
  
  private int[] mMaxDescent;
  
  private int mOrientation;
  
  private int mShowDividers;
  
  private int mTotalLength;
  
  private boolean mUseLargestChild;
  
  private float mWeightSum;
  
  public LinearLayoutCompat(Context paramContext) {
    this(paramContext, null);
  }
  
  public LinearLayoutCompat(Context paramContext, AttributeSet paramAttributeSet) {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public LinearLayoutCompat(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    TintTypedArray tintTypedArray = TintTypedArray.obtainStyledAttributes(paramContext, paramAttributeSet, R.styleable.LinearLayoutCompat, paramInt, 0);
    paramInt = tintTypedArray.getInt(R.styleable.LinearLayoutCompat_android_orientation, -1);
    if (paramInt >= 0)
      setOrientation(paramInt); 
    paramInt = tintTypedArray.getInt(R.styleable.LinearLayoutCompat_android_gravity, -1);
    if (paramInt >= 0)
      setGravity(paramInt); 
    boolean bool = tintTypedArray.getBoolean(R.styleable.LinearLayoutCompat_android_baselineAligned, true);
    if (!bool)
      setBaselineAligned(bool); 
    this.mWeightSum = tintTypedArray.getFloat(R.styleable.LinearLayoutCompat_android_weightSum, -1.0F);
    this.mBaselineAlignedChildIndex = tintTypedArray.getInt(R.styleable.LinearLayoutCompat_android_baselineAlignedChildIndex, -1);
    this.mUseLargestChild = tintTypedArray.getBoolean(R.styleable.LinearLayoutCompat_measureWithLargestChild, false);
    setDividerDrawable(tintTypedArray.getDrawable(R.styleable.LinearLayoutCompat_divider));
    this.mShowDividers = tintTypedArray.getInt(R.styleable.LinearLayoutCompat_showDividers, 0);
    this.mDividerPadding = tintTypedArray.getDimensionPixelSize(R.styleable.LinearLayoutCompat_dividerPadding, 0);
    tintTypedArray.recycle();
  }
  
  private void forceUniformHeight(int paramInt1, int paramInt2) {
    int i = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824);
    for (byte b = 0; b < paramInt1; b++) {
      View view = getVirtualChildAt(b);
      if (view.getVisibility() != 8) {
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        if (layoutParams.height == -1) {
          int j = layoutParams.width;
          layoutParams.width = view.getMeasuredWidth();
          measureChildWithMargins(view, paramInt2, 0, i, 0);
          layoutParams.width = j;
        } 
      } 
    } 
  }
  
  private void forceUniformWidth(int paramInt1, int paramInt2) {
    int i = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
    for (byte b = 0; b < paramInt1; b++) {
      View view = getVirtualChildAt(b);
      if (view.getVisibility() != 8) {
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        if (layoutParams.width == -1) {
          int j = layoutParams.height;
          layoutParams.height = view.getMeasuredHeight();
          measureChildWithMargins(view, i, 0, paramInt2, 0);
          layoutParams.height = j;
        } 
      } 
    } 
  }
  
  private void setChildFrame(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    paramView.layout(paramInt1, paramInt2, paramInt3 + paramInt1, paramInt4 + paramInt2);
  }
  
  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams) {
    return paramLayoutParams instanceof LayoutParams;
  }
  
  void drawDividersHorizontal(Canvas paramCanvas) {
    int i = getVirtualChildCount();
    boolean bool = ViewUtils.isLayoutRtl((View)this);
    int j;
    for (j = 0; j < i; j++) {
      View view = getVirtualChildAt(j);
      if (view != null && view.getVisibility() != 8 && hasDividerBeforeChildAt(j)) {
        int k;
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        if (bool) {
          k = view.getRight() + layoutParams.rightMargin;
        } else {
          k = view.getLeft() - layoutParams.leftMargin - this.mDividerWidth;
        } 
        drawVerticalDivider(paramCanvas, k);
      } 
    } 
    if (hasDividerBeforeChildAt(i)) {
      View view = getVirtualChildAt(i - 1);
      if (view == null) {
        if (bool) {
          j = getPaddingLeft();
        } else {
          int k = getWidth() - getPaddingRight();
          j = this.mDividerWidth;
          j = k - j;
        } 
      } else {
        int k;
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        if (bool) {
          k = view.getLeft() - layoutParams.leftMargin;
          j = this.mDividerWidth;
        } else {
          j = view.getRight() + layoutParams.rightMargin;
          drawVerticalDivider(paramCanvas, j);
        } 
        j = k - j;
      } 
    } else {
      return;
    } 
    drawVerticalDivider(paramCanvas, j);
  }
  
  void drawDividersVertical(Canvas paramCanvas) {
    int i = getVirtualChildCount();
    int j;
    for (j = 0; j < i; j++) {
      View view = getVirtualChildAt(j);
      if (view != null && view.getVisibility() != 8 && hasDividerBeforeChildAt(j)) {
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        drawHorizontalDivider(paramCanvas, view.getTop() - layoutParams.topMargin - this.mDividerHeight);
      } 
    } 
    if (hasDividerBeforeChildAt(i)) {
      View view = getVirtualChildAt(i - 1);
      if (view == null) {
        j = getHeight() - getPaddingBottom() - this.mDividerHeight;
      } else {
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        j = view.getBottom() + layoutParams.bottomMargin;
      } 
      drawHorizontalDivider(paramCanvas, j);
    } 
  }
  
  void drawHorizontalDivider(Canvas paramCanvas, int paramInt) {
    this.mDivider.setBounds(getPaddingLeft() + this.mDividerPadding, paramInt, getWidth() - getPaddingRight() - this.mDividerPadding, this.mDividerHeight + paramInt);
    this.mDivider.draw(paramCanvas);
  }
  
  void drawVerticalDivider(Canvas paramCanvas, int paramInt) {
    this.mDivider.setBounds(paramInt, getPaddingTop() + this.mDividerPadding, this.mDividerWidth + paramInt, getHeight() - getPaddingBottom() - this.mDividerPadding);
    this.mDivider.draw(paramCanvas);
  }
  
  protected LayoutParams generateDefaultLayoutParams() {
    int i = this.mOrientation;
    return (i == 0) ? new LayoutParams(-2, -2) : ((i == 1) ? new LayoutParams(-1, -2) : null);
  }
  
  public LayoutParams generateLayoutParams(AttributeSet paramAttributeSet) {
    return new LayoutParams(getContext(), paramAttributeSet);
  }
  
  protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams) {
    return new LayoutParams(paramLayoutParams);
  }
  
  public int getBaseline() {
    if (this.mBaselineAlignedChildIndex < 0)
      return super.getBaseline(); 
    int i = getChildCount();
    int j = this.mBaselineAlignedChildIndex;
    if (i > j) {
      View view = getChildAt(j);
      int k = view.getBaseline();
      if (k == -1) {
        if (this.mBaselineAlignedChildIndex == 0)
          return -1; 
        throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
      } 
      i = this.mBaselineChildTop;
      j = i;
      if (this.mOrientation == 1) {
        int m = this.mGravity & 0x70;
        j = i;
        if (m != 48)
          if (m != 16) {
            if (m != 80) {
              j = i;
            } else {
              j = getBottom() - getTop() - getPaddingBottom() - this.mTotalLength;
            } 
          } else {
            j = i + (getBottom() - getTop() - getPaddingTop() - getPaddingBottom() - this.mTotalLength) / 2;
          }  
      } 
      return j + ((LayoutParams)view.getLayoutParams()).topMargin + k;
    } 
    throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
  }
  
  public int getBaselineAlignedChildIndex() {
    return this.mBaselineAlignedChildIndex;
  }
  
  int getChildrenSkipCount(View paramView, int paramInt) {
    return 0;
  }
  
  public Drawable getDividerDrawable() {
    return this.mDivider;
  }
  
  public int getDividerPadding() {
    return this.mDividerPadding;
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public int getDividerWidth() {
    return this.mDividerWidth;
  }
  
  public int getGravity() {
    return this.mGravity;
  }
  
  int getLocationOffset(View paramView) {
    return 0;
  }
  
  int getNextLocationOffset(View paramView) {
    return 0;
  }
  
  public int getOrientation() {
    return this.mOrientation;
  }
  
  public int getShowDividers() {
    return this.mShowDividers;
  }
  
  View getVirtualChildAt(int paramInt) {
    return getChildAt(paramInt);
  }
  
  int getVirtualChildCount() {
    return getChildCount();
  }
  
  public float getWeightSum() {
    return this.mWeightSum;
  }
  
  protected boolean hasDividerBeforeChildAt(int paramInt) {
    boolean bool1 = false;
    boolean bool2 = false;
    boolean bool3 = false;
    if (paramInt == 0) {
      if ((this.mShowDividers & 0x1) != 0)
        bool3 = true; 
      return bool3;
    } 
    if (paramInt == getChildCount()) {
      bool3 = bool1;
      if ((this.mShowDividers & 0x4) != 0)
        bool3 = true; 
      return bool3;
    } 
    bool3 = bool2;
    if ((this.mShowDividers & 0x2) != 0) {
      paramInt--;
      while (true) {
        bool3 = bool2;
        if (paramInt >= 0) {
          if (getChildAt(paramInt).getVisibility() != 8) {
            bool3 = true;
            break;
          } 
          paramInt--;
          continue;
        } 
        break;
      } 
    } 
    return bool3;
  }
  
  public boolean isBaselineAligned() {
    return this.mBaselineAligned;
  }
  
  public boolean isMeasureWithLargestChildEnabled() {
    return this.mUseLargestChild;
  }
  
  void layoutHorizontal(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    byte b1;
    byte b2;
    boolean bool1 = ViewUtils.isLayoutRtl((View)this);
    int i = getPaddingTop();
    int j = paramInt4 - paramInt2;
    int k = getPaddingBottom();
    int m = getPaddingBottom();
    int n = getVirtualChildCount();
    paramInt2 = this.mGravity;
    paramInt4 = paramInt2 & 0x70;
    boolean bool2 = this.mBaselineAligned;
    int[] arrayOfInt1 = this.mMaxAscent;
    int[] arrayOfInt2 = this.mMaxDescent;
    paramInt2 = GravityCompat.getAbsoluteGravity(0x800007 & paramInt2, ViewCompat.getLayoutDirection((View)this));
    if (paramInt2 != 1) {
      if (paramInt2 != 5) {
        paramInt2 = getPaddingLeft();
      } else {
        paramInt2 = getPaddingLeft() + paramInt3 - paramInt1 - this.mTotalLength;
      } 
    } else {
      paramInt2 = getPaddingLeft() + (paramInt3 - paramInt1 - this.mTotalLength) / 2;
    } 
    if (bool1) {
      b1 = n - 1;
      b2 = -1;
    } else {
      b1 = 0;
      b2 = 1;
    } 
    int i1 = 0;
    paramInt3 = paramInt4;
    paramInt4 = i;
    while (i1 < n) {
      int i2 = b1 + b2 * i1;
      View view = getVirtualChildAt(i2);
      if (view == null) {
        paramInt2 += measureNullChild(i2);
      } else if (view.getVisibility() != 8) {
        int i3 = view.getMeasuredWidth();
        int i4 = view.getMeasuredHeight();
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        if (bool2 && layoutParams.height != -1) {
          i5 = view.getBaseline();
        } else {
          i5 = -1;
        } 
        int i6 = layoutParams.gravity;
        paramInt1 = i6;
        if (i6 < 0)
          paramInt1 = paramInt3; 
        paramInt1 &= 0x70;
        if (paramInt1 != 16) {
          if (paramInt1 != 48) {
            if (paramInt1 != 80) {
              paramInt1 = paramInt4;
            } else {
              i6 = j - k - i4 - layoutParams.bottomMargin;
              paramInt1 = i6;
              if (i5 != -1) {
                paramInt1 = view.getMeasuredHeight();
                paramInt1 = i6 - arrayOfInt2[2] - paramInt1 - i5;
              } 
            } 
          } else {
            i6 = layoutParams.topMargin + paramInt4;
            paramInt1 = i6;
            if (i5 != -1)
              paramInt1 = i6 + arrayOfInt1[1] - i5; 
          } 
        } else {
          paramInt1 = (j - i - m - i4) / 2 + paramInt4 + layoutParams.topMargin - layoutParams.bottomMargin;
        } 
        int i5 = paramInt2;
        if (hasDividerBeforeChildAt(i2))
          i5 = paramInt2 + this.mDividerWidth; 
        paramInt2 = layoutParams.leftMargin + i5;
        setChildFrame(view, paramInt2 + getLocationOffset(view), paramInt1, i3, i4);
        paramInt1 = layoutParams.rightMargin;
        i5 = getNextLocationOffset(view);
        i1 += getChildrenSkipCount(view, i2);
        paramInt2 += i3 + paramInt1 + i5;
      } 
      i1++;
    } 
  }
  
  void layoutVertical(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    int i = getPaddingLeft();
    int j = paramInt3 - paramInt1;
    int k = getPaddingRight();
    int m = getPaddingRight();
    int n = getVirtualChildCount();
    int i1 = this.mGravity;
    paramInt1 = i1 & 0x70;
    if (paramInt1 != 16) {
      if (paramInt1 != 80) {
        paramInt1 = getPaddingTop();
      } else {
        paramInt1 = getPaddingTop() + paramInt4 - paramInt2 - this.mTotalLength;
      } 
    } else {
      paramInt1 = getPaddingTop() + (paramInt4 - paramInt2 - this.mTotalLength) / 2;
    } 
    paramInt2 = 0;
    while (paramInt2 < n) {
      View view = getVirtualChildAt(paramInt2);
      if (view == null) {
        paramInt3 = paramInt1 + measureNullChild(paramInt2);
        paramInt4 = paramInt2;
      } else {
        paramInt3 = paramInt1;
        paramInt4 = paramInt2;
        if (view.getVisibility() != 8) {
          int i2 = view.getMeasuredWidth();
          int i3 = view.getMeasuredHeight();
          LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
          paramInt4 = layoutParams.gravity;
          paramInt3 = paramInt4;
          if (paramInt4 < 0)
            paramInt3 = i1 & 0x800007; 
          paramInt3 = GravityCompat.getAbsoluteGravity(paramInt3, ViewCompat.getLayoutDirection((View)this)) & 0x7;
          if (paramInt3 != 1) {
            if (paramInt3 != 5) {
              paramInt3 = layoutParams.leftMargin + i;
            } else {
              paramInt3 = j - k - i2;
              paramInt4 = layoutParams.rightMargin;
              paramInt3 -= paramInt4;
            } 
          } else {
            paramInt3 = (j - i - m - i2) / 2 + i + layoutParams.leftMargin;
            paramInt4 = layoutParams.rightMargin;
            paramInt3 -= paramInt4;
          } 
          paramInt4 = paramInt1;
          if (hasDividerBeforeChildAt(paramInt2))
            paramInt4 = paramInt1 + this.mDividerHeight; 
          paramInt1 = paramInt4 + layoutParams.topMargin;
          setChildFrame(view, paramInt3, paramInt1 + getLocationOffset(view), i2, i3);
          paramInt3 = layoutParams.bottomMargin;
          i2 = getNextLocationOffset(view);
          paramInt4 = paramInt2 + getChildrenSkipCount(view, paramInt2);
          paramInt3 = paramInt1 + i3 + paramInt3 + i2;
        } 
      } 
      paramInt2 = paramInt4 + 1;
      paramInt1 = paramInt3;
    } 
  }
  
  void measureChildBeforeLayout(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    measureChildWithMargins(paramView, paramInt2, paramInt3, paramInt4, paramInt5);
  }
  
  void measureHorizontal(int paramInt1, int paramInt2) {
    // Byte code:
    //   0: aload_0
    //   1: iconst_0
    //   2: putfield mTotalLength : I
    //   5: aload_0
    //   6: invokevirtual getVirtualChildCount : ()I
    //   9: istore_3
    //   10: iload_1
    //   11: invokestatic getMode : (I)I
    //   14: istore #4
    //   16: iload_2
    //   17: invokestatic getMode : (I)I
    //   20: istore #5
    //   22: aload_0
    //   23: getfield mMaxAscent : [I
    //   26: ifnull -> 36
    //   29: aload_0
    //   30: getfield mMaxDescent : [I
    //   33: ifnonnull -> 50
    //   36: aload_0
    //   37: iconst_4
    //   38: newarray int
    //   40: putfield mMaxAscent : [I
    //   43: aload_0
    //   44: iconst_4
    //   45: newarray int
    //   47: putfield mMaxDescent : [I
    //   50: aload_0
    //   51: getfield mMaxAscent : [I
    //   54: astore #6
    //   56: aload_0
    //   57: getfield mMaxDescent : [I
    //   60: astore #7
    //   62: aload #6
    //   64: iconst_3
    //   65: iconst_m1
    //   66: iastore
    //   67: aload #6
    //   69: iconst_2
    //   70: iconst_m1
    //   71: iastore
    //   72: aload #6
    //   74: iconst_1
    //   75: iconst_m1
    //   76: iastore
    //   77: aload #6
    //   79: iconst_0
    //   80: iconst_m1
    //   81: iastore
    //   82: aload #7
    //   84: iconst_3
    //   85: iconst_m1
    //   86: iastore
    //   87: aload #7
    //   89: iconst_2
    //   90: iconst_m1
    //   91: iastore
    //   92: aload #7
    //   94: iconst_1
    //   95: iconst_m1
    //   96: iastore
    //   97: aload #7
    //   99: iconst_0
    //   100: iconst_m1
    //   101: iastore
    //   102: aload_0
    //   103: getfield mBaselineAligned : Z
    //   106: istore #8
    //   108: aload_0
    //   109: getfield mUseLargestChild : Z
    //   112: istore #9
    //   114: iload #4
    //   116: ldc 1073741824
    //   118: if_icmpne -> 127
    //   121: iconst_1
    //   122: istore #10
    //   124: goto -> 130
    //   127: iconst_0
    //   128: istore #10
    //   130: iconst_0
    //   131: istore #11
    //   133: ldc_w -2147483648
    //   136: istore #12
    //   138: fconst_0
    //   139: fstore #13
    //   141: iconst_0
    //   142: istore #14
    //   144: iconst_0
    //   145: istore #15
    //   147: iconst_0
    //   148: istore #16
    //   150: iconst_0
    //   151: istore #17
    //   153: iconst_0
    //   154: istore #18
    //   156: iconst_1
    //   157: istore #19
    //   159: iconst_0
    //   160: istore #20
    //   162: iload #11
    //   164: iload_3
    //   165: if_icmpge -> 854
    //   168: aload_0
    //   169: iload #11
    //   171: invokevirtual getVirtualChildAt : (I)Landroid/view/View;
    //   174: astore #21
    //   176: aload #21
    //   178: ifnonnull -> 199
    //   181: aload_0
    //   182: aload_0
    //   183: getfield mTotalLength : I
    //   186: aload_0
    //   187: iload #11
    //   189: invokevirtual measureNullChild : (I)I
    //   192: iadd
    //   193: putfield mTotalLength : I
    //   196: goto -> 848
    //   199: aload #21
    //   201: invokevirtual getVisibility : ()I
    //   204: bipush #8
    //   206: if_icmpne -> 225
    //   209: iload #11
    //   211: aload_0
    //   212: aload #21
    //   214: iload #11
    //   216: invokevirtual getChildrenSkipCount : (Landroid/view/View;I)I
    //   219: iadd
    //   220: istore #11
    //   222: goto -> 196
    //   225: aload_0
    //   226: iload #11
    //   228: invokevirtual hasDividerBeforeChildAt : (I)Z
    //   231: ifeq -> 247
    //   234: aload_0
    //   235: aload_0
    //   236: getfield mTotalLength : I
    //   239: aload_0
    //   240: getfield mDividerWidth : I
    //   243: iadd
    //   244: putfield mTotalLength : I
    //   247: aload #21
    //   249: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   252: checkcast android/support/v7/widget/LinearLayoutCompat$LayoutParams
    //   255: astore #22
    //   257: aload #22
    //   259: getfield weight : F
    //   262: fstore #23
    //   264: fload #13
    //   266: fload #23
    //   268: fadd
    //   269: fstore #13
    //   271: iload #4
    //   273: ldc 1073741824
    //   275: if_icmpne -> 384
    //   278: aload #22
    //   280: getfield width : I
    //   283: ifne -> 384
    //   286: fload #23
    //   288: fconst_0
    //   289: fcmpl
    //   290: ifle -> 384
    //   293: iload #10
    //   295: ifeq -> 321
    //   298: aload_0
    //   299: aload_0
    //   300: getfield mTotalLength : I
    //   303: aload #22
    //   305: getfield leftMargin : I
    //   308: aload #22
    //   310: getfield rightMargin : I
    //   313: iadd
    //   314: iadd
    //   315: putfield mTotalLength : I
    //   318: goto -> 350
    //   321: aload_0
    //   322: getfield mTotalLength : I
    //   325: istore #24
    //   327: aload_0
    //   328: iload #24
    //   330: aload #22
    //   332: getfield leftMargin : I
    //   335: iload #24
    //   337: iadd
    //   338: aload #22
    //   340: getfield rightMargin : I
    //   343: iadd
    //   344: invokestatic max : (II)I
    //   347: putfield mTotalLength : I
    //   350: iload #8
    //   352: ifeq -> 378
    //   355: iconst_0
    //   356: iconst_0
    //   357: invokestatic makeMeasureSpec : (II)I
    //   360: istore #24
    //   362: aload #21
    //   364: iload #24
    //   366: iload #24
    //   368: invokevirtual measure : (II)V
    //   371: iload #12
    //   373: istore #24
    //   375: goto -> 570
    //   378: iconst_1
    //   379: istore #17
    //   381: goto -> 574
    //   384: aload #22
    //   386: getfield width : I
    //   389: ifne -> 412
    //   392: fload #23
    //   394: fconst_0
    //   395: fcmpl
    //   396: ifle -> 412
    //   399: aload #22
    //   401: bipush #-2
    //   403: putfield width : I
    //   406: iconst_0
    //   407: istore #24
    //   409: goto -> 417
    //   412: ldc_w -2147483648
    //   415: istore #24
    //   417: fload #13
    //   419: fconst_0
    //   420: fcmpl
    //   421: ifne -> 433
    //   424: aload_0
    //   425: getfield mTotalLength : I
    //   428: istore #25
    //   430: goto -> 436
    //   433: iconst_0
    //   434: istore #25
    //   436: aload_0
    //   437: aload #21
    //   439: iload #11
    //   441: iload_1
    //   442: iload #25
    //   444: iload_2
    //   445: iconst_0
    //   446: invokevirtual measureChildBeforeLayout : (Landroid/view/View;IIIII)V
    //   449: aload #22
    //   451: astore #26
    //   453: iload #24
    //   455: ldc_w -2147483648
    //   458: if_icmpeq -> 468
    //   461: aload #26
    //   463: iload #24
    //   465: putfield width : I
    //   468: aload #21
    //   470: invokevirtual getMeasuredWidth : ()I
    //   473: istore #25
    //   475: iload #10
    //   477: ifeq -> 513
    //   480: aload_0
    //   481: aload_0
    //   482: getfield mTotalLength : I
    //   485: aload #26
    //   487: getfield leftMargin : I
    //   490: iload #25
    //   492: iadd
    //   493: aload #26
    //   495: getfield rightMargin : I
    //   498: iadd
    //   499: aload_0
    //   500: aload #21
    //   502: invokevirtual getNextLocationOffset : (Landroid/view/View;)I
    //   505: iadd
    //   506: iadd
    //   507: putfield mTotalLength : I
    //   510: goto -> 552
    //   513: aload_0
    //   514: getfield mTotalLength : I
    //   517: istore #24
    //   519: aload_0
    //   520: iload #24
    //   522: iload #24
    //   524: iload #25
    //   526: iadd
    //   527: aload #26
    //   529: getfield leftMargin : I
    //   532: iadd
    //   533: aload #26
    //   535: getfield rightMargin : I
    //   538: iadd
    //   539: aload_0
    //   540: aload #21
    //   542: invokevirtual getNextLocationOffset : (Landroid/view/View;)I
    //   545: iadd
    //   546: invokestatic max : (II)I
    //   549: putfield mTotalLength : I
    //   552: iload #12
    //   554: istore #24
    //   556: iload #9
    //   558: ifeq -> 570
    //   561: iload #25
    //   563: iload #12
    //   565: invokestatic max : (II)I
    //   568: istore #24
    //   570: iload #24
    //   572: istore #12
    //   574: iload #11
    //   576: istore #27
    //   578: aload #21
    //   580: astore #26
    //   582: iload #5
    //   584: ldc 1073741824
    //   586: if_icmpeq -> 607
    //   589: aload #22
    //   591: getfield height : I
    //   594: iconst_m1
    //   595: if_icmpne -> 607
    //   598: iconst_1
    //   599: istore #11
    //   601: iconst_1
    //   602: istore #20
    //   604: goto -> 610
    //   607: iconst_0
    //   608: istore #11
    //   610: aload #22
    //   612: getfield topMargin : I
    //   615: aload #22
    //   617: getfield bottomMargin : I
    //   620: iadd
    //   621: istore #25
    //   623: aload #26
    //   625: invokevirtual getMeasuredHeight : ()I
    //   628: iload #25
    //   630: iadd
    //   631: istore #24
    //   633: iload #18
    //   635: aload #26
    //   637: invokevirtual getMeasuredState : ()I
    //   640: invokestatic combineMeasuredStates : (II)I
    //   643: istore #28
    //   645: iload #8
    //   647: ifeq -> 732
    //   650: aload #26
    //   652: invokevirtual getBaseline : ()I
    //   655: istore #29
    //   657: iload #29
    //   659: iconst_m1
    //   660: if_icmpeq -> 732
    //   663: aload #22
    //   665: getfield gravity : I
    //   668: istore #30
    //   670: iload #30
    //   672: istore #18
    //   674: iload #30
    //   676: ifge -> 685
    //   679: aload_0
    //   680: getfield mGravity : I
    //   683: istore #18
    //   685: iload #18
    //   687: bipush #112
    //   689: iand
    //   690: iconst_4
    //   691: ishr
    //   692: bipush #-2
    //   694: iand
    //   695: iconst_1
    //   696: ishr
    //   697: istore #18
    //   699: aload #6
    //   701: iload #18
    //   703: aload #6
    //   705: iload #18
    //   707: iaload
    //   708: iload #29
    //   710: invokestatic max : (II)I
    //   713: iastore
    //   714: aload #7
    //   716: iload #18
    //   718: aload #7
    //   720: iload #18
    //   722: iaload
    //   723: iload #24
    //   725: iload #29
    //   727: isub
    //   728: invokestatic max : (II)I
    //   731: iastore
    //   732: iload #14
    //   734: iload #24
    //   736: invokestatic max : (II)I
    //   739: istore #14
    //   741: iload #19
    //   743: ifeq -> 761
    //   746: aload #22
    //   748: getfield height : I
    //   751: iconst_m1
    //   752: if_icmpne -> 761
    //   755: iconst_1
    //   756: istore #19
    //   758: goto -> 764
    //   761: iconst_0
    //   762: istore #19
    //   764: aload #22
    //   766: getfield weight : F
    //   769: fconst_0
    //   770: fcmpl
    //   771: ifle -> 798
    //   774: iload #11
    //   776: ifeq -> 782
    //   779: goto -> 786
    //   782: iload #24
    //   784: istore #25
    //   786: iload #16
    //   788: iload #25
    //   790: invokestatic max : (II)I
    //   793: istore #11
    //   795: goto -> 823
    //   798: iload #11
    //   800: ifeq -> 810
    //   803: iload #25
    //   805: istore #24
    //   807: goto -> 810
    //   810: iload #15
    //   812: iload #24
    //   814: invokestatic max : (II)I
    //   817: istore #15
    //   819: iload #16
    //   821: istore #11
    //   823: aload_0
    //   824: aload #26
    //   826: iload #27
    //   828: invokevirtual getChildrenSkipCount : (Landroid/view/View;I)I
    //   831: iload #27
    //   833: iadd
    //   834: istore #24
    //   836: iload #28
    //   838: istore #18
    //   840: iload #11
    //   842: istore #16
    //   844: iload #24
    //   846: istore #11
    //   848: iinc #11, 1
    //   851: goto -> 162
    //   854: iload #14
    //   856: istore #11
    //   858: iload #15
    //   860: istore #24
    //   862: aload_0
    //   863: getfield mTotalLength : I
    //   866: ifle -> 890
    //   869: aload_0
    //   870: iload_3
    //   871: invokevirtual hasDividerBeforeChildAt : (I)Z
    //   874: ifeq -> 890
    //   877: aload_0
    //   878: aload_0
    //   879: getfield mTotalLength : I
    //   882: aload_0
    //   883: getfield mDividerWidth : I
    //   886: iadd
    //   887: putfield mTotalLength : I
    //   890: aload #6
    //   892: iconst_1
    //   893: iaload
    //   894: istore #15
    //   896: iload #15
    //   898: iconst_m1
    //   899: if_icmpne -> 932
    //   902: aload #6
    //   904: iconst_0
    //   905: iaload
    //   906: iconst_m1
    //   907: if_icmpne -> 932
    //   910: aload #6
    //   912: iconst_2
    //   913: iaload
    //   914: iconst_m1
    //   915: if_icmpne -> 932
    //   918: aload #6
    //   920: iconst_3
    //   921: iaload
    //   922: iconst_m1
    //   923: if_icmpeq -> 929
    //   926: goto -> 932
    //   929: goto -> 988
    //   932: iload #11
    //   934: aload #6
    //   936: iconst_3
    //   937: iaload
    //   938: aload #6
    //   940: iconst_0
    //   941: iaload
    //   942: iload #15
    //   944: aload #6
    //   946: iconst_2
    //   947: iaload
    //   948: invokestatic max : (II)I
    //   951: invokestatic max : (II)I
    //   954: invokestatic max : (II)I
    //   957: aload #7
    //   959: iconst_3
    //   960: iaload
    //   961: aload #7
    //   963: iconst_0
    //   964: iaload
    //   965: aload #7
    //   967: iconst_1
    //   968: iaload
    //   969: aload #7
    //   971: iconst_2
    //   972: iaload
    //   973: invokestatic max : (II)I
    //   976: invokestatic max : (II)I
    //   979: invokestatic max : (II)I
    //   982: iadd
    //   983: invokestatic max : (II)I
    //   986: istore #11
    //   988: iload #11
    //   990: istore #14
    //   992: iload #9
    //   994: ifeq -> 1182
    //   997: iload #4
    //   999: ldc_w -2147483648
    //   1002: if_icmpeq -> 1014
    //   1005: iload #11
    //   1007: istore #14
    //   1009: iload #4
    //   1011: ifne -> 1182
    //   1014: aload_0
    //   1015: iconst_0
    //   1016: putfield mTotalLength : I
    //   1019: iconst_0
    //   1020: istore #15
    //   1022: iload #11
    //   1024: istore #14
    //   1026: iload #15
    //   1028: iload_3
    //   1029: if_icmpge -> 1182
    //   1032: aload_0
    //   1033: iload #15
    //   1035: invokevirtual getVirtualChildAt : (I)Landroid/view/View;
    //   1038: astore #26
    //   1040: aload #26
    //   1042: ifnonnull -> 1063
    //   1045: aload_0
    //   1046: aload_0
    //   1047: getfield mTotalLength : I
    //   1050: aload_0
    //   1051: iload #15
    //   1053: invokevirtual measureNullChild : (I)I
    //   1056: iadd
    //   1057: putfield mTotalLength : I
    //   1060: goto -> 1086
    //   1063: aload #26
    //   1065: invokevirtual getVisibility : ()I
    //   1068: bipush #8
    //   1070: if_icmpne -> 1089
    //   1073: iload #15
    //   1075: aload_0
    //   1076: aload #26
    //   1078: iload #15
    //   1080: invokevirtual getChildrenSkipCount : (Landroid/view/View;I)I
    //   1083: iadd
    //   1084: istore #15
    //   1086: goto -> 1176
    //   1089: aload #26
    //   1091: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   1094: checkcast android/support/v7/widget/LinearLayoutCompat$LayoutParams
    //   1097: astore #22
    //   1099: iload #10
    //   1101: ifeq -> 1137
    //   1104: aload_0
    //   1105: aload_0
    //   1106: getfield mTotalLength : I
    //   1109: aload #22
    //   1111: getfield leftMargin : I
    //   1114: iload #12
    //   1116: iadd
    //   1117: aload #22
    //   1119: getfield rightMargin : I
    //   1122: iadd
    //   1123: aload_0
    //   1124: aload #26
    //   1126: invokevirtual getNextLocationOffset : (Landroid/view/View;)I
    //   1129: iadd
    //   1130: iadd
    //   1131: putfield mTotalLength : I
    //   1134: goto -> 1086
    //   1137: aload_0
    //   1138: getfield mTotalLength : I
    //   1141: istore #14
    //   1143: aload_0
    //   1144: iload #14
    //   1146: iload #14
    //   1148: iload #12
    //   1150: iadd
    //   1151: aload #22
    //   1153: getfield leftMargin : I
    //   1156: iadd
    //   1157: aload #22
    //   1159: getfield rightMargin : I
    //   1162: iadd
    //   1163: aload_0
    //   1164: aload #26
    //   1166: invokevirtual getNextLocationOffset : (Landroid/view/View;)I
    //   1169: iadd
    //   1170: invokestatic max : (II)I
    //   1173: putfield mTotalLength : I
    //   1176: iinc #15, 1
    //   1179: goto -> 1022
    //   1182: aload_0
    //   1183: getfield mTotalLength : I
    //   1186: aload_0
    //   1187: invokevirtual getPaddingLeft : ()I
    //   1190: aload_0
    //   1191: invokevirtual getPaddingRight : ()I
    //   1194: iadd
    //   1195: iadd
    //   1196: istore #15
    //   1198: aload_0
    //   1199: iload #15
    //   1201: putfield mTotalLength : I
    //   1204: iload #15
    //   1206: aload_0
    //   1207: invokevirtual getSuggestedMinimumWidth : ()I
    //   1210: invokestatic max : (II)I
    //   1213: iload_1
    //   1214: iconst_0
    //   1215: invokestatic resolveSizeAndState : (III)I
    //   1218: istore #28
    //   1220: ldc_w 16777215
    //   1223: iload #28
    //   1225: iand
    //   1226: aload_0
    //   1227: getfield mTotalLength : I
    //   1230: isub
    //   1231: istore #25
    //   1233: iload #17
    //   1235: ifne -> 1368
    //   1238: iload #25
    //   1240: ifeq -> 1253
    //   1243: fload #13
    //   1245: fconst_0
    //   1246: fcmpl
    //   1247: ifle -> 1253
    //   1250: goto -> 1368
    //   1253: iload #24
    //   1255: iload #16
    //   1257: invokestatic max : (II)I
    //   1260: istore #11
    //   1262: iload #9
    //   1264: ifeq -> 1353
    //   1267: iload #4
    //   1269: ldc 1073741824
    //   1271: if_icmpeq -> 1353
    //   1274: iconst_0
    //   1275: istore #15
    //   1277: iload #15
    //   1279: iload_3
    //   1280: if_icmpge -> 1353
    //   1283: aload_0
    //   1284: iload #15
    //   1286: invokevirtual getVirtualChildAt : (I)Landroid/view/View;
    //   1289: astore #7
    //   1291: aload #7
    //   1293: ifnull -> 1347
    //   1296: aload #7
    //   1298: invokevirtual getVisibility : ()I
    //   1301: bipush #8
    //   1303: if_icmpne -> 1309
    //   1306: goto -> 1347
    //   1309: aload #7
    //   1311: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   1314: checkcast android/support/v7/widget/LinearLayoutCompat$LayoutParams
    //   1317: getfield weight : F
    //   1320: fconst_0
    //   1321: fcmpl
    //   1322: ifle -> 1347
    //   1325: aload #7
    //   1327: iload #12
    //   1329: ldc 1073741824
    //   1331: invokestatic makeMeasureSpec : (II)I
    //   1334: aload #7
    //   1336: invokevirtual getMeasuredHeight : ()I
    //   1339: ldc 1073741824
    //   1341: invokestatic makeMeasureSpec : (II)I
    //   1344: invokevirtual measure : (II)V
    //   1347: iinc #15, 1
    //   1350: goto -> 1277
    //   1353: iload #11
    //   1355: istore #12
    //   1357: iload #18
    //   1359: istore #15
    //   1361: iload #14
    //   1363: istore #11
    //   1365: goto -> 2169
    //   1368: aload_0
    //   1369: getfield mWeightSum : F
    //   1372: fstore #23
    //   1374: fload #23
    //   1376: fconst_0
    //   1377: fcmpl
    //   1378: ifle -> 1385
    //   1381: fload #23
    //   1383: fstore #13
    //   1385: aload #6
    //   1387: iconst_3
    //   1388: iconst_m1
    //   1389: iastore
    //   1390: aload #6
    //   1392: iconst_2
    //   1393: iconst_m1
    //   1394: iastore
    //   1395: aload #6
    //   1397: iconst_1
    //   1398: iconst_m1
    //   1399: iastore
    //   1400: aload #6
    //   1402: iconst_0
    //   1403: iconst_m1
    //   1404: iastore
    //   1405: aload #7
    //   1407: iconst_3
    //   1408: iconst_m1
    //   1409: iastore
    //   1410: aload #7
    //   1412: iconst_2
    //   1413: iconst_m1
    //   1414: iastore
    //   1415: aload #7
    //   1417: iconst_1
    //   1418: iconst_m1
    //   1419: iastore
    //   1420: aload #7
    //   1422: iconst_0
    //   1423: iconst_m1
    //   1424: iastore
    //   1425: aload_0
    //   1426: iconst_0
    //   1427: putfield mTotalLength : I
    //   1430: iconst_m1
    //   1431: istore #11
    //   1433: iconst_0
    //   1434: istore #14
    //   1436: iload #19
    //   1438: istore #15
    //   1440: iload #18
    //   1442: istore #19
    //   1444: iload #24
    //   1446: istore #12
    //   1448: iload #25
    //   1450: istore #18
    //   1452: iload #14
    //   1454: iload_3
    //   1455: if_icmpge -> 2041
    //   1458: aload_0
    //   1459: iload #14
    //   1461: invokevirtual getVirtualChildAt : (I)Landroid/view/View;
    //   1464: astore #22
    //   1466: iload #18
    //   1468: istore #27
    //   1470: iload #12
    //   1472: istore #25
    //   1474: iload #19
    //   1476: istore #16
    //   1478: iload #11
    //   1480: istore #24
    //   1482: fload #13
    //   1484: fstore #23
    //   1486: iload #15
    //   1488: istore #17
    //   1490: aload #22
    //   1492: ifnull -> 2011
    //   1495: aload #22
    //   1497: invokevirtual getVisibility : ()I
    //   1500: bipush #8
    //   1502: if_icmpne -> 1532
    //   1505: iload #18
    //   1507: istore #27
    //   1509: iload #12
    //   1511: istore #25
    //   1513: iload #19
    //   1515: istore #16
    //   1517: iload #11
    //   1519: istore #24
    //   1521: fload #13
    //   1523: fstore #23
    //   1525: iload #15
    //   1527: istore #17
    //   1529: goto -> 2011
    //   1532: aload #22
    //   1534: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   1537: checkcast android/support/v7/widget/LinearLayoutCompat$LayoutParams
    //   1540: astore #26
    //   1542: aload #26
    //   1544: getfield weight : F
    //   1547: fstore #31
    //   1549: iload #18
    //   1551: istore #17
    //   1553: iload #19
    //   1555: istore #16
    //   1557: fload #13
    //   1559: fstore #23
    //   1561: fload #31
    //   1563: fconst_0
    //   1564: fcmpl
    //   1565: ifle -> 1728
    //   1568: iload #18
    //   1570: i2f
    //   1571: fload #31
    //   1573: fmul
    //   1574: fload #13
    //   1576: fdiv
    //   1577: f2i
    //   1578: istore #17
    //   1580: fload #13
    //   1582: fload #31
    //   1584: fsub
    //   1585: fstore #23
    //   1587: iload_2
    //   1588: aload_0
    //   1589: invokevirtual getPaddingTop : ()I
    //   1592: aload_0
    //   1593: invokevirtual getPaddingBottom : ()I
    //   1596: iadd
    //   1597: aload #26
    //   1599: getfield topMargin : I
    //   1602: iadd
    //   1603: aload #26
    //   1605: getfield bottomMargin : I
    //   1608: iadd
    //   1609: aload #26
    //   1611: getfield height : I
    //   1614: invokestatic getChildMeasureSpec : (III)I
    //   1617: istore #25
    //   1619: aload #26
    //   1621: getfield width : I
    //   1624: ifne -> 1669
    //   1627: iload #4
    //   1629: ldc 1073741824
    //   1631: if_icmpeq -> 1637
    //   1634: goto -> 1669
    //   1637: iload #17
    //   1639: ifle -> 1649
    //   1642: iload #17
    //   1644: istore #16
    //   1646: goto -> 1652
    //   1649: iconst_0
    //   1650: istore #16
    //   1652: aload #22
    //   1654: iload #16
    //   1656: ldc 1073741824
    //   1658: invokestatic makeMeasureSpec : (II)I
    //   1661: iload #25
    //   1663: invokevirtual measure : (II)V
    //   1666: goto -> 1705
    //   1669: aload #22
    //   1671: invokevirtual getMeasuredWidth : ()I
    //   1674: iload #17
    //   1676: iadd
    //   1677: istore #24
    //   1679: iload #24
    //   1681: istore #16
    //   1683: iload #24
    //   1685: ifge -> 1691
    //   1688: iconst_0
    //   1689: istore #16
    //   1691: aload #22
    //   1693: iload #16
    //   1695: ldc 1073741824
    //   1697: invokestatic makeMeasureSpec : (II)I
    //   1700: iload #25
    //   1702: invokevirtual measure : (II)V
    //   1705: iload #19
    //   1707: aload #22
    //   1709: invokevirtual getMeasuredState : ()I
    //   1712: ldc_w -16777216
    //   1715: iand
    //   1716: invokestatic combineMeasuredStates : (II)I
    //   1719: istore #16
    //   1721: iload #18
    //   1723: iload #17
    //   1725: isub
    //   1726: istore #17
    //   1728: iload #10
    //   1730: ifeq -> 1769
    //   1733: aload_0
    //   1734: aload_0
    //   1735: getfield mTotalLength : I
    //   1738: aload #22
    //   1740: invokevirtual getMeasuredWidth : ()I
    //   1743: aload #26
    //   1745: getfield leftMargin : I
    //   1748: iadd
    //   1749: aload #26
    //   1751: getfield rightMargin : I
    //   1754: iadd
    //   1755: aload_0
    //   1756: aload #22
    //   1758: invokevirtual getNextLocationOffset : (Landroid/view/View;)I
    //   1761: iadd
    //   1762: iadd
    //   1763: putfield mTotalLength : I
    //   1766: goto -> 1811
    //   1769: aload_0
    //   1770: getfield mTotalLength : I
    //   1773: istore #19
    //   1775: aload_0
    //   1776: iload #19
    //   1778: aload #22
    //   1780: invokevirtual getMeasuredWidth : ()I
    //   1783: iload #19
    //   1785: iadd
    //   1786: aload #26
    //   1788: getfield leftMargin : I
    //   1791: iadd
    //   1792: aload #26
    //   1794: getfield rightMargin : I
    //   1797: iadd
    //   1798: aload_0
    //   1799: aload #22
    //   1801: invokevirtual getNextLocationOffset : (Landroid/view/View;)I
    //   1804: iadd
    //   1805: invokestatic max : (II)I
    //   1808: putfield mTotalLength : I
    //   1811: iload #5
    //   1813: ldc 1073741824
    //   1815: if_icmpeq -> 1833
    //   1818: aload #26
    //   1820: getfield height : I
    //   1823: iconst_m1
    //   1824: if_icmpne -> 1833
    //   1827: iconst_1
    //   1828: istore #19
    //   1830: goto -> 1836
    //   1833: iconst_0
    //   1834: istore #19
    //   1836: aload #26
    //   1838: getfield topMargin : I
    //   1841: aload #26
    //   1843: getfield bottomMargin : I
    //   1846: iadd
    //   1847: istore #25
    //   1849: aload #22
    //   1851: invokevirtual getMeasuredHeight : ()I
    //   1854: iload #25
    //   1856: iadd
    //   1857: istore #18
    //   1859: iload #11
    //   1861: iload #18
    //   1863: invokestatic max : (II)I
    //   1866: istore #24
    //   1868: iload #19
    //   1870: ifeq -> 1880
    //   1873: iload #25
    //   1875: istore #19
    //   1877: goto -> 1884
    //   1880: iload #18
    //   1882: istore #19
    //   1884: iload #12
    //   1886: iload #19
    //   1888: invokestatic max : (II)I
    //   1891: istore #25
    //   1893: iload #15
    //   1895: ifeq -> 1913
    //   1898: aload #26
    //   1900: getfield height : I
    //   1903: iconst_m1
    //   1904: if_icmpne -> 1913
    //   1907: iconst_1
    //   1908: istore #19
    //   1910: goto -> 1916
    //   1913: iconst_0
    //   1914: istore #19
    //   1916: iload #8
    //   1918: ifeq -> 2003
    //   1921: aload #22
    //   1923: invokevirtual getBaseline : ()I
    //   1926: istore #11
    //   1928: iload #11
    //   1930: iconst_m1
    //   1931: if_icmpeq -> 2003
    //   1934: aload #26
    //   1936: getfield gravity : I
    //   1939: istore #12
    //   1941: iload #12
    //   1943: istore #15
    //   1945: iload #12
    //   1947: ifge -> 1956
    //   1950: aload_0
    //   1951: getfield mGravity : I
    //   1954: istore #15
    //   1956: iload #15
    //   1958: bipush #112
    //   1960: iand
    //   1961: iconst_4
    //   1962: ishr
    //   1963: bipush #-2
    //   1965: iand
    //   1966: iconst_1
    //   1967: ishr
    //   1968: istore #15
    //   1970: aload #6
    //   1972: iload #15
    //   1974: aload #6
    //   1976: iload #15
    //   1978: iaload
    //   1979: iload #11
    //   1981: invokestatic max : (II)I
    //   1984: iastore
    //   1985: aload #7
    //   1987: iload #15
    //   1989: aload #7
    //   1991: iload #15
    //   1993: iaload
    //   1994: iload #18
    //   1996: iload #11
    //   1998: isub
    //   1999: invokestatic max : (II)I
    //   2002: iastore
    //   2003: iload #17
    //   2005: istore #27
    //   2007: iload #19
    //   2009: istore #17
    //   2011: iinc #14, 1
    //   2014: iload #27
    //   2016: istore #18
    //   2018: iload #25
    //   2020: istore #12
    //   2022: iload #16
    //   2024: istore #19
    //   2026: iload #24
    //   2028: istore #11
    //   2030: fload #23
    //   2032: fstore #13
    //   2034: iload #17
    //   2036: istore #15
    //   2038: goto -> 1452
    //   2041: aload_0
    //   2042: aload_0
    //   2043: getfield mTotalLength : I
    //   2046: aload_0
    //   2047: invokevirtual getPaddingLeft : ()I
    //   2050: aload_0
    //   2051: invokevirtual getPaddingRight : ()I
    //   2054: iadd
    //   2055: iadd
    //   2056: putfield mTotalLength : I
    //   2059: aload #6
    //   2061: iconst_1
    //   2062: iaload
    //   2063: istore #16
    //   2065: iload #16
    //   2067: iconst_m1
    //   2068: if_icmpne -> 2101
    //   2071: aload #6
    //   2073: iconst_0
    //   2074: iaload
    //   2075: iconst_m1
    //   2076: if_icmpne -> 2101
    //   2079: aload #6
    //   2081: iconst_2
    //   2082: iaload
    //   2083: iconst_m1
    //   2084: if_icmpne -> 2101
    //   2087: aload #6
    //   2089: iconst_3
    //   2090: iaload
    //   2091: iconst_m1
    //   2092: if_icmpeq -> 2098
    //   2095: goto -> 2101
    //   2098: goto -> 2157
    //   2101: iload #11
    //   2103: aload #6
    //   2105: iconst_3
    //   2106: iaload
    //   2107: aload #6
    //   2109: iconst_0
    //   2110: iaload
    //   2111: iload #16
    //   2113: aload #6
    //   2115: iconst_2
    //   2116: iaload
    //   2117: invokestatic max : (II)I
    //   2120: invokestatic max : (II)I
    //   2123: invokestatic max : (II)I
    //   2126: aload #7
    //   2128: iconst_3
    //   2129: iaload
    //   2130: aload #7
    //   2132: iconst_0
    //   2133: iaload
    //   2134: aload #7
    //   2136: iconst_1
    //   2137: iaload
    //   2138: aload #7
    //   2140: iconst_2
    //   2141: iaload
    //   2142: invokestatic max : (II)I
    //   2145: invokestatic max : (II)I
    //   2148: invokestatic max : (II)I
    //   2151: iadd
    //   2152: invokestatic max : (II)I
    //   2155: istore #11
    //   2157: iload #19
    //   2159: istore #16
    //   2161: iload #15
    //   2163: istore #19
    //   2165: iload #16
    //   2167: istore #15
    //   2169: iload #11
    //   2171: istore #16
    //   2173: iload #19
    //   2175: ifne -> 2193
    //   2178: iload #11
    //   2180: istore #16
    //   2182: iload #5
    //   2184: ldc 1073741824
    //   2186: if_icmpeq -> 2193
    //   2189: iload #12
    //   2191: istore #16
    //   2193: aload_0
    //   2194: iload #28
    //   2196: iload #15
    //   2198: ldc_w -16777216
    //   2201: iand
    //   2202: ior
    //   2203: iload #16
    //   2205: aload_0
    //   2206: invokevirtual getPaddingTop : ()I
    //   2209: aload_0
    //   2210: invokevirtual getPaddingBottom : ()I
    //   2213: iadd
    //   2214: iadd
    //   2215: aload_0
    //   2216: invokevirtual getSuggestedMinimumHeight : ()I
    //   2219: invokestatic max : (II)I
    //   2222: iload_2
    //   2223: iload #15
    //   2225: bipush #16
    //   2227: ishl
    //   2228: invokestatic resolveSizeAndState : (III)I
    //   2231: invokevirtual setMeasuredDimension : (II)V
    //   2234: iload #20
    //   2236: ifeq -> 2245
    //   2239: aload_0
    //   2240: iload_3
    //   2241: iload_1
    //   2242: invokespecial forceUniformHeight : (II)V
    //   2245: return
  }
  
  int measureNullChild(int paramInt) {
    return 0;
  }
  
  void measureVertical(int paramInt1, int paramInt2) {
    int i = 0;
    this.mTotalLength = 0;
    int j = getVirtualChildCount();
    int k = View.MeasureSpec.getMode(paramInt1);
    int m = View.MeasureSpec.getMode(paramInt2);
    int n = this.mBaselineAlignedChildIndex;
    boolean bool = this.mUseLargestChild;
    float f = 0.0F;
    int i1 = 0;
    int i2 = Integer.MIN_VALUE;
    int i3 = 0;
    int i4 = 0;
    int i5 = 0;
    int i6 = 1;
    boolean bool1 = false;
    int i7 = 0;
    while (i4 < j) {
      View view = getVirtualChildAt(i4);
      if (view == null) {
        this.mTotalLength += measureNullChild(i4);
      } else if (view.getVisibility() == 8) {
        i4 += getChildrenSkipCount(view, i4);
      } else {
        if (hasDividerBeforeChildAt(i4))
          this.mTotalLength += this.mDividerHeight; 
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        float f1 = layoutParams.weight;
        f += f1;
        if (m == 1073741824 && layoutParams.height == 0 && f1 > 0.0F) {
          i5 = this.mTotalLength;
          this.mTotalLength = Math.max(i5, layoutParams.topMargin + i5 + layoutParams.bottomMargin);
          i5 = 1;
        } else {
          if (layoutParams.height == 0 && f1 > 0.0F) {
            layoutParams.height = -2;
            i11 = 0;
          } else {
            i11 = Integer.MIN_VALUE;
          } 
          if (f == 0.0F) {
            i12 = this.mTotalLength;
          } else {
            i12 = 0;
          } 
          LayoutParams layoutParams1 = layoutParams;
          measureChildBeforeLayout(view, i4, paramInt1, 0, paramInt2, i12);
          if (i11 != Integer.MIN_VALUE)
            layoutParams1.height = i11; 
          int i12 = view.getMeasuredHeight();
          int i11 = this.mTotalLength;
          this.mTotalLength = Math.max(i11, i11 + i12 + layoutParams1.topMargin + layoutParams1.bottomMargin + getNextLocationOffset(view));
          if (bool)
            i2 = Math.max(i12, i2); 
        } 
        int i10 = i4;
        if (n >= 0 && n == i10 + 1)
          this.mBaselineChildTop = this.mTotalLength; 
        if (i10 >= n || layoutParams.weight <= 0.0F) {
          if (k != 1073741824 && layoutParams.width == -1) {
            i4 = 1;
            bool1 = true;
          } else {
            i4 = 0;
          } 
          int i12 = layoutParams.leftMargin + layoutParams.rightMargin;
          int i11 = view.getMeasuredWidth() + i12;
          i1 = Math.max(i1, i11);
          int i13 = View.combineMeasuredStates(i, view.getMeasuredState());
          if (i6 && layoutParams.width == -1) {
            i6 = 1;
          } else {
            i6 = 0;
          } 
          if (layoutParams.weight > 0.0F) {
            if (i4 != 0)
              i11 = i12; 
            i = Math.max(i3, i11);
          } else {
            i = i3;
            if (i4 != 0)
              i11 = i12; 
            i7 = Math.max(i7, i11);
          } 
          i11 = getChildrenSkipCount(view, i10);
          i4 = i13;
          i3 = i;
          i11 += i10;
          i = i4;
          i4 = i11;
        } else {
          throw new RuntimeException("A child of LinearLayout with index less than mBaselineAlignedChildIndex has weight > 0, which won't work.  Either remove the weight, or don't set mBaselineAlignedChildIndex.");
        } 
      } 
      i4++;
    } 
    if (this.mTotalLength > 0 && hasDividerBeforeChildAt(j))
      this.mTotalLength += this.mDividerHeight; 
    int i8 = j;
    if (bool && (m == Integer.MIN_VALUE || m == 0)) {
      this.mTotalLength = 0;
      for (i4 = 0; i4 < i8; i4++) {
        View view = getVirtualChildAt(i4);
        if (view == null) {
          this.mTotalLength += measureNullChild(i4);
        } else if (view.getVisibility() == 8) {
          i4 += getChildrenSkipCount(view, i4);
        } else {
          LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
          j = this.mTotalLength;
          this.mTotalLength = Math.max(j, j + i2 + layoutParams.topMargin + layoutParams.bottomMargin + getNextLocationOffset(view));
        } 
      } 
    } 
    i4 = this.mTotalLength + getPaddingTop() + getPaddingBottom();
    this.mTotalLength = i4;
    int i9 = View.resolveSizeAndState(Math.max(i4, getSuggestedMinimumHeight()), paramInt2, 0);
    i4 = (0xFFFFFF & i9) - this.mTotalLength;
    if (i5 != 0 || (i4 != 0 && f > 0.0F)) {
      float f1 = this.mWeightSum;
      if (f1 > 0.0F)
        f = f1; 
      this.mTotalLength = 0;
      i2 = i7;
      j = 0;
      i7 = i6;
      i6 = i4;
      while (j < i8) {
        View view = getVirtualChildAt(j);
        if (view.getVisibility() != 8) {
          LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
          f1 = layoutParams.weight;
          if (f1 > 0.0F) {
            i3 = (int)(i6 * f1 / f);
            int i10 = ViewGroup.getChildMeasureSpec(paramInt1, getPaddingLeft() + getPaddingRight() + layoutParams.leftMargin + layoutParams.rightMargin, layoutParams.width);
            if (layoutParams.height != 0 || m != 1073741824) {
              i5 = view.getMeasuredHeight() + i3;
              i4 = i5;
              if (i5 < 0)
                i4 = 0; 
              view.measure(i10, View.MeasureSpec.makeMeasureSpec(i4, 1073741824));
            } else {
              if (i3 > 0) {
                i4 = i3;
              } else {
                i4 = 0;
              } 
              view.measure(i10, View.MeasureSpec.makeMeasureSpec(i4, 1073741824));
            } 
            i = View.combineMeasuredStates(i, view.getMeasuredState() & 0xFFFFFF00);
            f -= f1;
            i6 -= i3;
          } 
          i3 = layoutParams.leftMargin + layoutParams.rightMargin;
          i5 = view.getMeasuredWidth() + i3;
          i4 = Math.max(i1, i5);
          if (k != 1073741824 && layoutParams.width == -1) {
            i1 = 1;
          } else {
            i1 = 0;
          } 
          if (i1 != 0) {
            i1 = i3;
          } else {
            i1 = i5;
          } 
          i2 = Math.max(i2, i1);
          if (i7 != 0 && layoutParams.width == -1) {
            i7 = 1;
          } else {
            i7 = 0;
          } 
          i1 = this.mTotalLength;
          this.mTotalLength = Math.max(i1, i1 + view.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin + getNextLocationOffset(view));
          i1 = i4;
        } 
        j++;
      } 
      this.mTotalLength += getPaddingTop() + getPaddingBottom();
      i6 = i7;
      i7 = i2;
    } else {
      i4 = Math.max(i7, i3);
      if (bool && m != 1073741824)
        for (i7 = 0; i7 < i8; i7++) {
          View view = getVirtualChildAt(i7);
          if (view != null && view.getVisibility() != 8 && ((LayoutParams)view.getLayoutParams()).weight > 0.0F)
            view.measure(View.MeasureSpec.makeMeasureSpec(view.getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(i2, 1073741824)); 
        }  
      i7 = i4;
    } 
    if (i6 != 0 || k == 1073741824)
      i7 = i1; 
    setMeasuredDimension(View.resolveSizeAndState(Math.max(i7 + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), paramInt1, i), i9);
    if (bool1)
      forceUniformWidth(i8, paramInt2); 
  }
  
  protected void onDraw(Canvas paramCanvas) {
    if (this.mDivider == null)
      return; 
    if (this.mOrientation == 1) {
      drawDividersVertical(paramCanvas);
    } else {
      drawDividersHorizontal(paramCanvas);
    } 
  }
  
  public void onInitializeAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent) {
    super.onInitializeAccessibilityEvent(paramAccessibilityEvent);
    paramAccessibilityEvent.setClassName(LinearLayoutCompat.class.getName());
  }
  
  public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo paramAccessibilityNodeInfo) {
    super.onInitializeAccessibilityNodeInfo(paramAccessibilityNodeInfo);
    paramAccessibilityNodeInfo.setClassName(LinearLayoutCompat.class.getName());
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    if (this.mOrientation == 1) {
      layoutVertical(paramInt1, paramInt2, paramInt3, paramInt4);
    } else {
      layoutHorizontal(paramInt1, paramInt2, paramInt3, paramInt4);
    } 
  }
  
  protected void onMeasure(int paramInt1, int paramInt2) {
    if (this.mOrientation == 1) {
      measureVertical(paramInt1, paramInt2);
    } else {
      measureHorizontal(paramInt1, paramInt2);
    } 
  }
  
  public void setBaselineAligned(boolean paramBoolean) {
    this.mBaselineAligned = paramBoolean;
  }
  
  public void setBaselineAlignedChildIndex(int paramInt) {
    if (paramInt >= 0 && paramInt < getChildCount()) {
      this.mBaselineAlignedChildIndex = paramInt;
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("base aligned child index out of range (0, ");
    stringBuilder.append(getChildCount());
    stringBuilder.append(")");
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public void setDividerDrawable(Drawable paramDrawable) {
    if (paramDrawable == this.mDivider)
      return; 
    this.mDivider = paramDrawable;
    boolean bool = false;
    if (paramDrawable != null) {
      this.mDividerWidth = paramDrawable.getIntrinsicWidth();
      this.mDividerHeight = paramDrawable.getIntrinsicHeight();
    } else {
      this.mDividerWidth = 0;
      this.mDividerHeight = 0;
    } 
    if (paramDrawable == null)
      bool = true; 
    setWillNotDraw(bool);
    requestLayout();
  }
  
  public void setDividerPadding(int paramInt) {
    this.mDividerPadding = paramInt;
  }
  
  public void setGravity(int paramInt) {
    if (this.mGravity != paramInt) {
      int i = paramInt;
      if ((0x800007 & paramInt) == 0)
        i = paramInt | 0x800003; 
      paramInt = i;
      if ((i & 0x70) == 0)
        paramInt = i | 0x30; 
      this.mGravity = paramInt;
      requestLayout();
    } 
  }
  
  public void setHorizontalGravity(int paramInt) {
    paramInt &= 0x800007;
    int i = this.mGravity;
    if ((0x800007 & i) != paramInt) {
      this.mGravity = paramInt | 0xFF7FFFF8 & i;
      requestLayout();
    } 
  }
  
  public void setMeasureWithLargestChildEnabled(boolean paramBoolean) {
    this.mUseLargestChild = paramBoolean;
  }
  
  public void setOrientation(int paramInt) {
    if (this.mOrientation != paramInt) {
      this.mOrientation = paramInt;
      requestLayout();
    } 
  }
  
  public void setShowDividers(int paramInt) {
    if (paramInt != this.mShowDividers)
      requestLayout(); 
    this.mShowDividers = paramInt;
  }
  
  public void setVerticalGravity(int paramInt) {
    paramInt &= 0x70;
    int i = this.mGravity;
    if ((i & 0x70) != paramInt) {
      this.mGravity = paramInt | i & 0xFFFFFF8F;
      requestLayout();
    } 
  }
  
  public void setWeightSum(float paramFloat) {
    this.mWeightSum = Math.max(0.0F, paramFloat);
  }
  
  public boolean shouldDelayChildPressedState() {
    return false;
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface DividerMode {}
  
  public static class LayoutParams extends ViewGroup.MarginLayoutParams {
    public int gravity = -1;
    
    public float weight;
    
    public LayoutParams(int param1Int1, int param1Int2) {
      super(param1Int1, param1Int2);
      this.weight = 0.0F;
    }
    
    public LayoutParams(int param1Int1, int param1Int2, float param1Float) {
      super(param1Int1, param1Int2);
      this.weight = param1Float;
    }
    
    public LayoutParams(Context param1Context, AttributeSet param1AttributeSet) {
      super(param1Context, param1AttributeSet);
      TypedArray typedArray = param1Context.obtainStyledAttributes(param1AttributeSet, R.styleable.LinearLayoutCompat_Layout);
      this.weight = typedArray.getFloat(R.styleable.LinearLayoutCompat_Layout_android_layout_weight, 0.0F);
      this.gravity = typedArray.getInt(R.styleable.LinearLayoutCompat_Layout_android_layout_gravity, -1);
      typedArray.recycle();
    }
    
    public LayoutParams(LayoutParams param1LayoutParams) {
      super(param1LayoutParams);
      this.weight = param1LayoutParams.weight;
      this.gravity = param1LayoutParams.gravity;
    }
    
    public LayoutParams(ViewGroup.LayoutParams param1LayoutParams) {
      super(param1LayoutParams);
    }
    
    public LayoutParams(ViewGroup.MarginLayoutParams param1MarginLayoutParams) {
      super(param1MarginLayoutParams);
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface OrientationMode {}
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/widget/LinearLayoutCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */