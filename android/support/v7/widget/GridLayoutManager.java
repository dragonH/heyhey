package android.support.v7.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import java.util.Arrays;

public class GridLayoutManager extends LinearLayoutManager {
  private static final boolean DEBUG = false;
  
  public static final int DEFAULT_SPAN_COUNT = -1;
  
  private static final String TAG = "GridLayoutManager";
  
  int[] mCachedBorders;
  
  final Rect mDecorInsets = new Rect();
  
  boolean mPendingSpanCountChange = false;
  
  final SparseIntArray mPreLayoutSpanIndexCache = new SparseIntArray();
  
  final SparseIntArray mPreLayoutSpanSizeCache = new SparseIntArray();
  
  View[] mSet;
  
  int mSpanCount = -1;
  
  SpanSizeLookup mSpanSizeLookup = new DefaultSpanSizeLookup();
  
  public GridLayoutManager(Context paramContext, int paramInt) {
    super(paramContext);
    setSpanCount(paramInt);
  }
  
  public GridLayoutManager(Context paramContext, int paramInt1, int paramInt2, boolean paramBoolean) {
    super(paramContext, paramInt2, paramBoolean);
    setSpanCount(paramInt1);
  }
  
  public GridLayoutManager(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2) {
    super(paramContext, paramAttributeSet, paramInt1, paramInt2);
    setSpanCount((RecyclerView.LayoutManager.getProperties(paramContext, paramAttributeSet, paramInt1, paramInt2)).spanCount);
  }
  
  private void assignSpans(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState, int paramInt1, int paramInt2, boolean paramBoolean) {
    int i = 0;
    int j = -1;
    if (paramBoolean) {
      boolean bool = false;
      paramInt2 = 1;
      j = paramInt1;
      paramInt1 = bool;
    } else {
      paramInt1--;
      paramInt2 = -1;
    } 
    while (paramInt1 != j) {
      View view = this.mSet[paramInt1];
      LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
      int k = getSpanSize(paramRecycler, paramState, getPosition(view));
      layoutParams.mSpanSize = k;
      layoutParams.mSpanIndex = i;
      i += k;
      paramInt1 += paramInt2;
    } 
  }
  
  private void cachePreLayoutSpanMapping() {
    int i = getChildCount();
    for (byte b = 0; b < i; b++) {
      LayoutParams layoutParams = (LayoutParams)getChildAt(b).getLayoutParams();
      int j = layoutParams.getViewLayoutPosition();
      this.mPreLayoutSpanSizeCache.put(j, layoutParams.getSpanSize());
      this.mPreLayoutSpanIndexCache.put(j, layoutParams.getSpanIndex());
    } 
  }
  
  private void calculateItemBorders(int paramInt) {
    this.mCachedBorders = calculateItemBorders(this.mCachedBorders, this.mSpanCount, paramInt);
  }
  
  static int[] calculateItemBorders(int[] paramArrayOfint, int paramInt1, int paramInt2) {
    // Byte code:
    //   0: iconst_1
    //   1: istore_3
    //   2: aload_0
    //   3: ifnull -> 27
    //   6: aload_0
    //   7: arraylength
    //   8: iload_1
    //   9: iconst_1
    //   10: iadd
    //   11: if_icmpne -> 27
    //   14: aload_0
    //   15: astore #4
    //   17: aload_0
    //   18: aload_0
    //   19: arraylength
    //   20: iconst_1
    //   21: isub
    //   22: iaload
    //   23: iload_2
    //   24: if_icmpeq -> 34
    //   27: iload_1
    //   28: iconst_1
    //   29: iadd
    //   30: newarray int
    //   32: astore #4
    //   34: iconst_0
    //   35: istore #5
    //   37: aload #4
    //   39: iconst_0
    //   40: iconst_0
    //   41: iastore
    //   42: iload_2
    //   43: iload_1
    //   44: idiv
    //   45: istore #6
    //   47: iload_2
    //   48: iload_1
    //   49: irem
    //   50: istore #7
    //   52: iconst_0
    //   53: istore #8
    //   55: iload #5
    //   57: istore_2
    //   58: iload_3
    //   59: iload_1
    //   60: if_icmpgt -> 116
    //   63: iload_2
    //   64: iload #7
    //   66: iadd
    //   67: istore_2
    //   68: iload_2
    //   69: ifle -> 93
    //   72: iload_1
    //   73: iload_2
    //   74: isub
    //   75: iload #7
    //   77: if_icmpge -> 93
    //   80: iload #6
    //   82: iconst_1
    //   83: iadd
    //   84: istore #5
    //   86: iload_2
    //   87: iload_1
    //   88: isub
    //   89: istore_2
    //   90: goto -> 97
    //   93: iload #6
    //   95: istore #5
    //   97: iload #8
    //   99: iload #5
    //   101: iadd
    //   102: istore #8
    //   104: aload #4
    //   106: iload_3
    //   107: iload #8
    //   109: iastore
    //   110: iinc #3, 1
    //   113: goto -> 58
    //   116: aload #4
    //   118: areturn
  }
  
  private void clearPreLayoutSpanMappingCache() {
    this.mPreLayoutSpanSizeCache.clear();
    this.mPreLayoutSpanIndexCache.clear();
  }
  
  private void ensureAnchorIsInCorrectSpan(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState, LinearLayoutManager.AnchorInfo paramAnchorInfo, int paramInt) {
    if (paramInt == 1) {
      paramInt = 1;
    } else {
      paramInt = 0;
    } 
    int i = getSpanIndex(paramRecycler, paramState, paramAnchorInfo.mPosition);
    if (paramInt != 0) {
      while (i > 0) {
        paramInt = paramAnchorInfo.mPosition;
        if (paramInt > 0) {
          paramAnchorInfo.mPosition = --paramInt;
          i = getSpanIndex(paramRecycler, paramState, paramInt);
        } 
      } 
    } else {
      int j = paramState.getItemCount();
      paramInt = paramAnchorInfo.mPosition;
      while (paramInt < j - 1) {
        int k = paramInt + 1;
        int m = getSpanIndex(paramRecycler, paramState, k);
        if (m > i) {
          paramInt = k;
          i = m;
        } 
      } 
      paramAnchorInfo.mPosition = paramInt;
    } 
  }
  
  private void ensureViewSet() {
    View[] arrayOfView = this.mSet;
    if (arrayOfView == null || arrayOfView.length != this.mSpanCount)
      this.mSet = new View[this.mSpanCount]; 
  }
  
  private int getSpanGroupIndex(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState, int paramInt) {
    if (!paramState.isPreLayout())
      return this.mSpanSizeLookup.getSpanGroupIndex(paramInt, this.mSpanCount); 
    int i = paramRecycler.convertPreLayoutPositionToPostLayout(paramInt);
    if (i == -1) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Cannot find span size for pre layout position. ");
      stringBuilder.append(paramInt);
      Log.w("GridLayoutManager", stringBuilder.toString());
      return 0;
    } 
    return this.mSpanSizeLookup.getSpanGroupIndex(i, this.mSpanCount);
  }
  
  private int getSpanIndex(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState, int paramInt) {
    if (!paramState.isPreLayout())
      return this.mSpanSizeLookup.getCachedSpanIndex(paramInt, this.mSpanCount); 
    int i = this.mPreLayoutSpanIndexCache.get(paramInt, -1);
    if (i != -1)
      return i; 
    i = paramRecycler.convertPreLayoutPositionToPostLayout(paramInt);
    if (i == -1) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:");
      stringBuilder.append(paramInt);
      Log.w("GridLayoutManager", stringBuilder.toString());
      return 0;
    } 
    return this.mSpanSizeLookup.getCachedSpanIndex(i, this.mSpanCount);
  }
  
  private int getSpanSize(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState, int paramInt) {
    if (!paramState.isPreLayout())
      return this.mSpanSizeLookup.getSpanSize(paramInt); 
    int i = this.mPreLayoutSpanSizeCache.get(paramInt, -1);
    if (i != -1)
      return i; 
    i = paramRecycler.convertPreLayoutPositionToPostLayout(paramInt);
    if (i == -1) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:");
      stringBuilder.append(paramInt);
      Log.w("GridLayoutManager", stringBuilder.toString());
      return 1;
    } 
    return this.mSpanSizeLookup.getSpanSize(i);
  }
  
  private void guessMeasurement(float paramFloat, int paramInt) {
    calculateItemBorders(Math.max(Math.round(paramFloat * this.mSpanCount), paramInt));
  }
  
  private void measureChild(View paramView, int paramInt, boolean paramBoolean) {
    LayoutParams layoutParams = (LayoutParams)paramView.getLayoutParams();
    Rect rect = layoutParams.mDecorInsets;
    int i = rect.top + rect.bottom + layoutParams.topMargin + layoutParams.bottomMargin;
    int j = rect.left + rect.right + layoutParams.leftMargin + layoutParams.rightMargin;
    int k = getSpaceForSpanRange(layoutParams.mSpanIndex, layoutParams.mSpanSize);
    if (this.mOrientation == 1) {
      j = RecyclerView.LayoutManager.getChildMeasureSpec(k, paramInt, j, layoutParams.width, false);
      paramInt = RecyclerView.LayoutManager.getChildMeasureSpec(this.mOrientationHelper.getTotalSpace(), getHeightMode(), i, layoutParams.height, true);
    } else {
      paramInt = RecyclerView.LayoutManager.getChildMeasureSpec(k, paramInt, i, layoutParams.height, false);
      j = RecyclerView.LayoutManager.getChildMeasureSpec(this.mOrientationHelper.getTotalSpace(), getWidthMode(), j, layoutParams.width, true);
    } 
    measureChildWithDecorationsAndMargin(paramView, j, paramInt, paramBoolean);
  }
  
  private void measureChildWithDecorationsAndMargin(View paramView, int paramInt1, int paramInt2, boolean paramBoolean) {
    RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)paramView.getLayoutParams();
    if (paramBoolean) {
      paramBoolean = shouldReMeasureChild(paramView, paramInt1, paramInt2, layoutParams);
    } else {
      paramBoolean = shouldMeasureChild(paramView, paramInt1, paramInt2, layoutParams);
    } 
    if (paramBoolean)
      paramView.measure(paramInt1, paramInt2); 
  }
  
  private void updateMeasurements() {
    int i;
    int j;
    if (getOrientation() == 1) {
      i = getWidth() - getPaddingRight();
      j = getPaddingLeft();
    } else {
      i = getHeight() - getPaddingBottom();
      j = getPaddingTop();
    } 
    calculateItemBorders(i - j);
  }
  
  public boolean checkLayoutParams(RecyclerView.LayoutParams paramLayoutParams) {
    return paramLayoutParams instanceof LayoutParams;
  }
  
  void collectPrefetchPositionsForLayoutState(RecyclerView.State paramState, LinearLayoutManager.LayoutState paramLayoutState, RecyclerView.LayoutManager.LayoutPrefetchRegistry paramLayoutPrefetchRegistry) {
    int i = this.mSpanCount;
    for (byte b = 0; b < this.mSpanCount && paramLayoutState.hasMore(paramState) && i > 0; b++) {
      int j = paramLayoutState.mCurrentPosition;
      paramLayoutPrefetchRegistry.addPosition(j, Math.max(0, paramLayoutState.mScrollingOffset));
      i -= this.mSpanSizeLookup.getSpanSize(j);
      paramLayoutState.mCurrentPosition += paramLayoutState.mItemDirection;
    } 
  }
  
  View findReferenceChild(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState, int paramInt1, int paramInt2, int paramInt3) {
    byte b;
    ensureLayoutState();
    int i = this.mOrientationHelper.getStartAfterPadding();
    int j = this.mOrientationHelper.getEndAfterPadding();
    if (paramInt2 > paramInt1) {
      b = 1;
    } else {
      b = -1;
    } 
    View view1 = null;
    View view2;
    for (view2 = null; paramInt1 != paramInt2; view2 = view5) {
      View view3 = getChildAt(paramInt1);
      int k = getPosition(view3);
      View view4 = view1;
      View view5 = view2;
      if (k >= 0) {
        view4 = view1;
        view5 = view2;
        if (k < paramInt3)
          if (getSpanIndex(paramRecycler, paramState, k) != 0) {
            view4 = view1;
            view5 = view2;
          } else if (((RecyclerView.LayoutParams)view3.getLayoutParams()).isItemRemoved()) {
            view4 = view1;
            view5 = view2;
            if (view2 == null) {
              view5 = view3;
              view4 = view1;
            } 
          } else if (this.mOrientationHelper.getDecoratedStart(view3) >= j || this.mOrientationHelper.getDecoratedEnd(view3) < i) {
            view4 = view1;
            view5 = view2;
            if (view1 == null) {
              view4 = view3;
              view5 = view2;
            } 
          } else {
            return view3;
          }  
      } 
      paramInt1 += b;
      view1 = view4;
    } 
    if (view1 == null)
      view1 = view2; 
    return view1;
  }
  
  public RecyclerView.LayoutParams generateDefaultLayoutParams() {
    return (this.mOrientation == 0) ? new LayoutParams(-2, -1) : new LayoutParams(-1, -2);
  }
  
  public RecyclerView.LayoutParams generateLayoutParams(Context paramContext, AttributeSet paramAttributeSet) {
    return new LayoutParams(paramContext, paramAttributeSet);
  }
  
  public RecyclerView.LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams) {
    return (paramLayoutParams instanceof ViewGroup.MarginLayoutParams) ? new LayoutParams((ViewGroup.MarginLayoutParams)paramLayoutParams) : new LayoutParams(paramLayoutParams);
  }
  
  public int getColumnCountForAccessibility(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState) {
    return (this.mOrientation == 1) ? this.mSpanCount : ((paramState.getItemCount() < 1) ? 0 : (getSpanGroupIndex(paramRecycler, paramState, paramState.getItemCount() - 1) + 1));
  }
  
  public int getRowCountForAccessibility(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState) {
    return (this.mOrientation == 0) ? this.mSpanCount : ((paramState.getItemCount() < 1) ? 0 : (getSpanGroupIndex(paramRecycler, paramState, paramState.getItemCount() - 1) + 1));
  }
  
  int getSpaceForSpanRange(int paramInt1, int paramInt2) {
    if (this.mOrientation == 1 && isLayoutRTL()) {
      int[] arrayOfInt1 = this.mCachedBorders;
      int i = this.mSpanCount;
      return arrayOfInt1[i - paramInt1] - arrayOfInt1[i - paramInt1 - paramInt2];
    } 
    int[] arrayOfInt = this.mCachedBorders;
    return arrayOfInt[paramInt2 + paramInt1] - arrayOfInt[paramInt1];
  }
  
  public int getSpanCount() {
    return this.mSpanCount;
  }
  
  public SpanSizeLookup getSpanSizeLookup() {
    return this.mSpanSizeLookup;
  }
  
  void layoutChunk(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState, LinearLayoutManager.LayoutState paramLayoutState, LinearLayoutManager.LayoutChunkResult paramLayoutChunkResult) {
    StringBuilder stringBuilder;
    int j;
    int k;
    boolean bool;
    int i = this.mOrientationHelper.getModeInOther();
    if (i != 1073741824) {
      j = 1;
    } else {
      j = 0;
    } 
    if (getChildCount() > 0) {
      k = this.mCachedBorders[this.mSpanCount];
    } else {
      k = 0;
    } 
    if (j)
      updateMeasurements(); 
    if (paramLayoutState.mItemDirection == 1) {
      bool = true;
    } else {
      bool = false;
    } 
    int m = this.mSpanCount;
    if (!bool)
      m = getSpanIndex(paramRecycler, paramState, paramLayoutState.mCurrentPosition) + getSpanSize(paramRecycler, paramState, paramLayoutState.mCurrentPosition); 
    int n = 0;
    byte b1 = 0;
    while (b1 < this.mSpanCount && paramLayoutState.hasMore(paramState) && m > 0) {
      int i2 = paramLayoutState.mCurrentPosition;
      int i3 = getSpanSize(paramRecycler, paramState, i2);
      if (i3 <= this.mSpanCount) {
        m -= i3;
        if (m < 0)
          break; 
        View view = paramLayoutState.next(paramRecycler);
        if (view == null)
          break; 
        n += i3;
        this.mSet[b1] = view;
        b1++;
        continue;
      } 
      stringBuilder = new StringBuilder();
      stringBuilder.append("Item at position ");
      stringBuilder.append(i2);
      stringBuilder.append(" requires ");
      stringBuilder.append(i3);
      stringBuilder.append(" spans but GridLayoutManager has only ");
      stringBuilder.append(this.mSpanCount);
      stringBuilder.append(" spans.");
      throw new IllegalArgumentException(stringBuilder.toString());
    } 
    if (b1 == 0) {
      paramLayoutChunkResult.mFinished = true;
      return;
    } 
    float f = 0.0F;
    assignSpans((RecyclerView.Recycler)stringBuilder, paramState, b1, n, bool);
    int i1 = 0;
    m = 0;
    while (i1 < b1) {
      View view = this.mSet[i1];
      if (paramLayoutState.mScrapList == null) {
        if (bool) {
          addView(view);
        } else {
          addView(view, 0);
        } 
      } else if (bool) {
        addDisappearingView(view);
      } else {
        addDisappearingView(view, 0);
      } 
      calculateItemDecorationsForChild(view, this.mDecorInsets);
      measureChild(view, i, false);
      int i2 = this.mOrientationHelper.getDecoratedMeasurement(view);
      n = m;
      if (i2 > m)
        n = i2; 
      LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
      float f1 = this.mOrientationHelper.getDecoratedMeasurementInOther(view) * 1.0F / layoutParams.mSpanSize;
      float f2 = f;
      if (f1 > f)
        f2 = f1; 
      i1++;
      m = n;
      f = f2;
    } 
    n = m;
    if (j) {
      guessMeasurement(f, k);
      m = 0;
      j = 0;
      while (true) {
        n = m;
        if (j < b1) {
          View view = this.mSet[j];
          measureChild(view, 1073741824, true);
          k = this.mOrientationHelper.getDecoratedMeasurement(view);
          n = m;
          if (k > m)
            n = k; 
          j++;
          m = n;
          continue;
        } 
        break;
      } 
    } 
    for (m = 0; m < b1; m++) {
      View view = this.mSet[m];
      if (this.mOrientationHelper.getDecoratedMeasurement(view) != n) {
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        Rect rect = layoutParams.mDecorInsets;
        k = rect.top + rect.bottom + layoutParams.topMargin + layoutParams.bottomMargin;
        j = rect.left + rect.right + layoutParams.leftMargin + layoutParams.rightMargin;
        i1 = getSpaceForSpanRange(layoutParams.mSpanIndex, layoutParams.mSpanSize);
        if (this.mOrientation == 1) {
          j = RecyclerView.LayoutManager.getChildMeasureSpec(i1, 1073741824, j, layoutParams.width, false);
          k = View.MeasureSpec.makeMeasureSpec(n - k, 1073741824);
        } else {
          j = View.MeasureSpec.makeMeasureSpec(n - j, 1073741824);
          k = RecyclerView.LayoutManager.getChildMeasureSpec(i1, 1073741824, k, layoutParams.height, false);
        } 
        measureChildWithDecorationsAndMargin(view, j, k, true);
      } 
    } 
    byte b2 = 0;
    paramLayoutChunkResult.mConsumed = n;
    if (this.mOrientation == 1) {
      if (paramLayoutState.mLayoutDirection == -1) {
        m = paramLayoutState.mOffset;
        i1 = 0;
        k = 0;
        j = m - n;
        n = i1;
      } else {
        j = paramLayoutState.mOffset;
        m = j + n;
        n = 0;
        k = 0;
      } 
    } else {
      if (paramLayoutState.mLayoutDirection == -1) {
        k = paramLayoutState.mOffset;
        n = k - n;
      } else {
        m = paramLayoutState.mOffset;
        k = m + n;
        n = m;
      } 
      m = 0;
      j = 0;
    } 
    while (b2 < b1) {
      View view = this.mSet[b2];
      LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
      if (this.mOrientation == 1) {
        if (isLayoutRTL()) {
          k = getPaddingLeft() + this.mCachedBorders[this.mSpanCount - layoutParams.mSpanIndex];
          i1 = this.mOrientationHelper.getDecoratedMeasurementInOther(view);
          n = k;
          k -= i1;
        } else {
          n = getPaddingLeft() + this.mCachedBorders[layoutParams.mSpanIndex];
          i1 = this.mOrientationHelper.getDecoratedMeasurementInOther(view);
          k = n;
          n = i1 + n;
        } 
        i1 = m;
        m = j;
        j = n;
      } else {
        i1 = getPaddingTop() + this.mCachedBorders[layoutParams.mSpanIndex];
        i = this.mOrientationHelper.getDecoratedMeasurementInOther(view);
        m = i1;
        j = k;
        i1 = i + i1;
        k = n;
      } 
      layoutDecoratedWithMargins(view, k, m, j, i1);
      if (layoutParams.isItemRemoved() || layoutParams.isItemChanged())
        paramLayoutChunkResult.mIgnoreConsumed = true; 
      paramLayoutChunkResult.mFocusable |= view.hasFocusable();
      b2++;
      n = k;
      k = j;
      j = m;
      m = i1;
    } 
    Arrays.fill((Object[])this.mSet, (Object)null);
  }
  
  void onAnchorReady(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState, LinearLayoutManager.AnchorInfo paramAnchorInfo, int paramInt) {
    super.onAnchorReady(paramRecycler, paramState, paramAnchorInfo, paramInt);
    updateMeasurements();
    if (paramState.getItemCount() > 0 && !paramState.isPreLayout())
      ensureAnchorIsInCorrectSpan(paramRecycler, paramState, paramAnchorInfo, paramInt); 
    ensureViewSet();
  }
  
  public View onFocusSearchFailed(View paramView, int paramInt, RecyclerView.Recycler paramRecycler, RecyclerView.State paramState) {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokevirtual findContainingItemView : (Landroid/view/View;)Landroid/view/View;
    //   5: astore #5
    //   7: aconst_null
    //   8: astore #6
    //   10: aload #5
    //   12: ifnonnull -> 17
    //   15: aconst_null
    //   16: areturn
    //   17: aload #5
    //   19: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   22: checkcast android/support/v7/widget/GridLayoutManager$LayoutParams
    //   25: astore #7
    //   27: aload #7
    //   29: getfield mSpanIndex : I
    //   32: istore #8
    //   34: aload #7
    //   36: getfield mSpanSize : I
    //   39: iload #8
    //   41: iadd
    //   42: istore #9
    //   44: aload_0
    //   45: aload_1
    //   46: iload_2
    //   47: aload_3
    //   48: aload #4
    //   50: invokespecial onFocusSearchFailed : (Landroid/view/View;ILandroid/support/v7/widget/RecyclerView$Recycler;Landroid/support/v7/widget/RecyclerView$State;)Landroid/view/View;
    //   53: ifnonnull -> 58
    //   56: aconst_null
    //   57: areturn
    //   58: aload_0
    //   59: iload_2
    //   60: invokevirtual convertFocusDirectionToLayoutDirection : (I)I
    //   63: iconst_1
    //   64: if_icmpne -> 73
    //   67: iconst_1
    //   68: istore #10
    //   70: goto -> 76
    //   73: iconst_0
    //   74: istore #10
    //   76: iload #10
    //   78: aload_0
    //   79: getfield mShouldReverseLayout : Z
    //   82: if_icmpeq -> 90
    //   85: iconst_1
    //   86: istore_2
    //   87: goto -> 92
    //   90: iconst_0
    //   91: istore_2
    //   92: iload_2
    //   93: ifeq -> 112
    //   96: aload_0
    //   97: invokevirtual getChildCount : ()I
    //   100: iconst_1
    //   101: isub
    //   102: istore_2
    //   103: iconst_m1
    //   104: istore #11
    //   106: iconst_m1
    //   107: istore #12
    //   109: goto -> 123
    //   112: aload_0
    //   113: invokevirtual getChildCount : ()I
    //   116: istore #11
    //   118: iconst_0
    //   119: istore_2
    //   120: iconst_1
    //   121: istore #12
    //   123: aload_0
    //   124: getfield mOrientation : I
    //   127: iconst_1
    //   128: if_icmpne -> 144
    //   131: aload_0
    //   132: invokevirtual isLayoutRTL : ()Z
    //   135: ifeq -> 144
    //   138: iconst_1
    //   139: istore #13
    //   141: goto -> 147
    //   144: iconst_0
    //   145: istore #13
    //   147: aload_0
    //   148: aload_3
    //   149: aload #4
    //   151: iload_2
    //   152: invokespecial getSpanGroupIndex : (Landroid/support/v7/widget/RecyclerView$Recycler;Landroid/support/v7/widget/RecyclerView$State;I)I
    //   155: istore #14
    //   157: iload_2
    //   158: istore #15
    //   160: iconst_0
    //   161: istore #16
    //   163: iconst_m1
    //   164: istore #17
    //   166: iconst_m1
    //   167: istore #18
    //   169: iconst_0
    //   170: istore_2
    //   171: aconst_null
    //   172: astore_1
    //   173: iload #11
    //   175: istore #19
    //   177: iload #16
    //   179: istore #11
    //   181: iload #15
    //   183: iload #19
    //   185: if_icmpeq -> 564
    //   188: aload_0
    //   189: aload_3
    //   190: aload #4
    //   192: iload #15
    //   194: invokespecial getSpanGroupIndex : (Landroid/support/v7/widget/RecyclerView$Recycler;Landroid/support/v7/widget/RecyclerView$State;I)I
    //   197: istore #16
    //   199: aload_0
    //   200: iload #15
    //   202: invokevirtual getChildAt : (I)Landroid/view/View;
    //   205: astore #7
    //   207: aload #7
    //   209: aload #5
    //   211: if_acmpne -> 217
    //   214: goto -> 564
    //   217: aload #7
    //   219: invokevirtual hasFocusable : ()Z
    //   222: ifeq -> 243
    //   225: iload #16
    //   227: iload #14
    //   229: if_icmpeq -> 243
    //   232: aload #6
    //   234: ifnull -> 240
    //   237: goto -> 564
    //   240: goto -> 554
    //   243: aload #7
    //   245: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   248: checkcast android/support/v7/widget/GridLayoutManager$LayoutParams
    //   251: astore #20
    //   253: aload #20
    //   255: getfield mSpanIndex : I
    //   258: istore #21
    //   260: aload #20
    //   262: getfield mSpanSize : I
    //   265: iload #21
    //   267: iadd
    //   268: istore #22
    //   270: aload #7
    //   272: invokevirtual hasFocusable : ()Z
    //   275: ifeq -> 295
    //   278: iload #21
    //   280: iload #8
    //   282: if_icmpne -> 295
    //   285: iload #22
    //   287: iload #9
    //   289: if_icmpne -> 295
    //   292: aload #7
    //   294: areturn
    //   295: aload #7
    //   297: invokevirtual hasFocusable : ()Z
    //   300: ifeq -> 308
    //   303: aload #6
    //   305: ifnull -> 320
    //   308: aload #7
    //   310: invokevirtual hasFocusable : ()Z
    //   313: ifne -> 326
    //   316: aload_1
    //   317: ifnonnull -> 326
    //   320: iconst_1
    //   321: istore #16
    //   323: goto -> 473
    //   326: iload #21
    //   328: iload #8
    //   330: invokestatic max : (II)I
    //   333: istore #16
    //   335: iload #22
    //   337: iload #9
    //   339: invokestatic min : (II)I
    //   342: iload #16
    //   344: isub
    //   345: istore #23
    //   347: aload #7
    //   349: invokevirtual hasFocusable : ()Z
    //   352: ifeq -> 398
    //   355: iload #23
    //   357: iload #11
    //   359: if_icmple -> 365
    //   362: goto -> 320
    //   365: iload #23
    //   367: iload #11
    //   369: if_icmpne -> 470
    //   372: iload #21
    //   374: iload #17
    //   376: if_icmple -> 385
    //   379: iconst_1
    //   380: istore #16
    //   382: goto -> 388
    //   385: iconst_0
    //   386: istore #16
    //   388: iload #13
    //   390: iload #16
    //   392: if_icmpne -> 470
    //   395: goto -> 362
    //   398: aload #6
    //   400: ifnonnull -> 470
    //   403: iconst_1
    //   404: istore #24
    //   406: iconst_1
    //   407: istore #16
    //   409: aload_0
    //   410: aload #7
    //   412: iconst_0
    //   413: iconst_1
    //   414: invokevirtual isViewPartiallyVisible : (Landroid/view/View;ZZ)Z
    //   417: ifeq -> 470
    //   420: iload_2
    //   421: istore #25
    //   423: iload #23
    //   425: iload #25
    //   427: if_icmple -> 437
    //   430: iload #24
    //   432: istore #16
    //   434: goto -> 473
    //   437: iload #23
    //   439: iload #25
    //   441: if_icmpne -> 467
    //   444: iload #21
    //   446: iload #18
    //   448: if_icmple -> 454
    //   451: goto -> 457
    //   454: iconst_0
    //   455: istore #16
    //   457: iload #13
    //   459: iload #16
    //   461: if_icmpne -> 470
    //   464: goto -> 320
    //   467: goto -> 470
    //   470: iconst_0
    //   471: istore #16
    //   473: iload #16
    //   475: ifeq -> 554
    //   478: aload #7
    //   480: invokevirtual hasFocusable : ()Z
    //   483: ifeq -> 525
    //   486: aload #20
    //   488: getfield mSpanIndex : I
    //   491: istore #17
    //   493: iload #22
    //   495: iload #9
    //   497: invokestatic min : (II)I
    //   500: istore #11
    //   502: iload #21
    //   504: iload #8
    //   506: invokestatic max : (II)I
    //   509: istore #16
    //   511: aload #7
    //   513: astore #6
    //   515: iload #11
    //   517: iload #16
    //   519: isub
    //   520: istore #11
    //   522: goto -> 554
    //   525: aload #20
    //   527: getfield mSpanIndex : I
    //   530: istore #18
    //   532: iload #22
    //   534: iload #9
    //   536: invokestatic min : (II)I
    //   539: iload #21
    //   541: iload #8
    //   543: invokestatic max : (II)I
    //   546: isub
    //   547: istore_2
    //   548: aload #7
    //   550: astore_1
    //   551: goto -> 554
    //   554: iload #15
    //   556: iload #12
    //   558: iadd
    //   559: istore #15
    //   561: goto -> 181
    //   564: aload #6
    //   566: ifnull -> 575
    //   569: aload #6
    //   571: astore_1
    //   572: goto -> 575
    //   575: aload_1
    //   576: areturn
  }
  
  public void onInitializeAccessibilityNodeInfoForItem(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState, View paramView, AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat) {
    ViewGroup.LayoutParams layoutParams1 = paramView.getLayoutParams();
    if (!(layoutParams1 instanceof LayoutParams)) {
      onInitializeAccessibilityNodeInfoForItem(paramView, paramAccessibilityNodeInfoCompat);
      return;
    } 
    LayoutParams layoutParams = (LayoutParams)layoutParams1;
    int i = getSpanGroupIndex(paramRecycler, paramState, layoutParams.getViewLayoutPosition());
    if (this.mOrientation == 0) {
      boolean bool;
      int j = layoutParams.getSpanIndex();
      int k = layoutParams.getSpanSize();
      if (this.mSpanCount > 1 && layoutParams.getSpanSize() == this.mSpanCount) {
        bool = true;
      } else {
        bool = false;
      } 
      paramAccessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(j, k, i, 1, bool, false));
    } else {
      boolean bool;
      int k = layoutParams.getSpanIndex();
      int j = layoutParams.getSpanSize();
      if (this.mSpanCount > 1 && layoutParams.getSpanSize() == this.mSpanCount) {
        bool = true;
      } else {
        bool = false;
      } 
      paramAccessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(i, 1, k, j, bool, false));
    } 
  }
  
  public void onItemsAdded(RecyclerView paramRecyclerView, int paramInt1, int paramInt2) {
    this.mSpanSizeLookup.invalidateSpanIndexCache();
  }
  
  public void onItemsChanged(RecyclerView paramRecyclerView) {
    this.mSpanSizeLookup.invalidateSpanIndexCache();
  }
  
  public void onItemsMoved(RecyclerView paramRecyclerView, int paramInt1, int paramInt2, int paramInt3) {
    this.mSpanSizeLookup.invalidateSpanIndexCache();
  }
  
  public void onItemsRemoved(RecyclerView paramRecyclerView, int paramInt1, int paramInt2) {
    this.mSpanSizeLookup.invalidateSpanIndexCache();
  }
  
  public void onItemsUpdated(RecyclerView paramRecyclerView, int paramInt1, int paramInt2, Object paramObject) {
    this.mSpanSizeLookup.invalidateSpanIndexCache();
  }
  
  public void onLayoutChildren(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState) {
    if (paramState.isPreLayout())
      cachePreLayoutSpanMapping(); 
    super.onLayoutChildren(paramRecycler, paramState);
    clearPreLayoutSpanMappingCache();
  }
  
  public void onLayoutCompleted(RecyclerView.State paramState) {
    super.onLayoutCompleted(paramState);
    this.mPendingSpanCountChange = false;
  }
  
  public int scrollHorizontallyBy(int paramInt, RecyclerView.Recycler paramRecycler, RecyclerView.State paramState) {
    updateMeasurements();
    ensureViewSet();
    return super.scrollHorizontallyBy(paramInt, paramRecycler, paramState);
  }
  
  public int scrollVerticallyBy(int paramInt, RecyclerView.Recycler paramRecycler, RecyclerView.State paramState) {
    updateMeasurements();
    ensureViewSet();
    return super.scrollVerticallyBy(paramInt, paramRecycler, paramState);
  }
  
  public void setMeasuredDimension(Rect paramRect, int paramInt1, int paramInt2) {
    int[] arrayOfInt;
    if (this.mCachedBorders == null)
      super.setMeasuredDimension(paramRect, paramInt1, paramInt2); 
    int i = getPaddingLeft() + getPaddingRight();
    int j = getPaddingTop() + getPaddingBottom();
    if (this.mOrientation == 1) {
      paramInt2 = RecyclerView.LayoutManager.chooseSize(paramInt2, paramRect.height() + j, getMinimumHeight());
      arrayOfInt = this.mCachedBorders;
      j = RecyclerView.LayoutManager.chooseSize(paramInt1, arrayOfInt[arrayOfInt.length - 1] + i, getMinimumWidth());
      paramInt1 = paramInt2;
      paramInt2 = j;
    } else {
      paramInt1 = RecyclerView.LayoutManager.chooseSize(paramInt1, arrayOfInt.width() + i, getMinimumWidth());
      arrayOfInt = this.mCachedBorders;
      j = RecyclerView.LayoutManager.chooseSize(paramInt2, arrayOfInt[arrayOfInt.length - 1] + j, getMinimumHeight());
      paramInt2 = paramInt1;
      paramInt1 = j;
    } 
    setMeasuredDimension(paramInt2, paramInt1);
  }
  
  public void setSpanCount(int paramInt) {
    if (paramInt == this.mSpanCount)
      return; 
    this.mPendingSpanCountChange = true;
    if (paramInt >= 1) {
      this.mSpanCount = paramInt;
      this.mSpanSizeLookup.invalidateSpanIndexCache();
      requestLayout();
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Span count should be at least 1. Provided ");
    stringBuilder.append(paramInt);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public void setSpanSizeLookup(SpanSizeLookup paramSpanSizeLookup) {
    this.mSpanSizeLookup = paramSpanSizeLookup;
  }
  
  public void setStackFromEnd(boolean paramBoolean) {
    if (!paramBoolean) {
      super.setStackFromEnd(false);
      return;
    } 
    throw new UnsupportedOperationException("GridLayoutManager does not support stack from end. Consider using reverse layout");
  }
  
  public boolean supportsPredictiveItemAnimations() {
    boolean bool;
    if (this.mPendingSavedState == null && !this.mPendingSpanCountChange) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public static final class DefaultSpanSizeLookup extends SpanSizeLookup {
    public int getSpanIndex(int param1Int1, int param1Int2) {
      return param1Int1 % param1Int2;
    }
    
    public int getSpanSize(int param1Int) {
      return 1;
    }
  }
  
  public static class LayoutParams extends RecyclerView.LayoutParams {
    public static final int INVALID_SPAN_ID = -1;
    
    int mSpanIndex = -1;
    
    int mSpanSize = 0;
    
    public LayoutParams(int param1Int1, int param1Int2) {
      super(param1Int1, param1Int2);
    }
    
    public LayoutParams(Context param1Context, AttributeSet param1AttributeSet) {
      super(param1Context, param1AttributeSet);
    }
    
    public LayoutParams(RecyclerView.LayoutParams param1LayoutParams) {
      super(param1LayoutParams);
    }
    
    public LayoutParams(ViewGroup.LayoutParams param1LayoutParams) {
      super(param1LayoutParams);
    }
    
    public LayoutParams(ViewGroup.MarginLayoutParams param1MarginLayoutParams) {
      super(param1MarginLayoutParams);
    }
    
    public int getSpanIndex() {
      return this.mSpanIndex;
    }
    
    public int getSpanSize() {
      return this.mSpanSize;
    }
  }
  
  public static abstract class SpanSizeLookup {
    private boolean mCacheSpanIndices = false;
    
    final SparseIntArray mSpanIndexCache = new SparseIntArray();
    
    int findReferenceIndexFromCache(int param1Int) {
      int i = this.mSpanIndexCache.size() - 1;
      int j = 0;
      while (j <= i) {
        int k = j + i >>> 1;
        if (this.mSpanIndexCache.keyAt(k) < param1Int) {
          j = k + 1;
          continue;
        } 
        i = k - 1;
      } 
      param1Int = j - 1;
      return (param1Int >= 0 && param1Int < this.mSpanIndexCache.size()) ? this.mSpanIndexCache.keyAt(param1Int) : -1;
    }
    
    int getCachedSpanIndex(int param1Int1, int param1Int2) {
      if (!this.mCacheSpanIndices)
        return getSpanIndex(param1Int1, param1Int2); 
      int i = this.mSpanIndexCache.get(param1Int1, -1);
      if (i != -1)
        return i; 
      param1Int2 = getSpanIndex(param1Int1, param1Int2);
      this.mSpanIndexCache.put(param1Int1, param1Int2);
      return param1Int2;
    }
    
    public int getSpanGroupIndex(int param1Int1, int param1Int2) {
      int i = getSpanSize(param1Int1);
      byte b = 0;
      int j = 0;
      int k;
      for (k = 0; b < param1Int1; k = i1) {
        int i1;
        int m = getSpanSize(b);
        int n = j + m;
        if (n == param1Int2) {
          i1 = k + 1;
          j = 0;
        } else {
          j = n;
          i1 = k;
          if (n > param1Int2) {
            i1 = k + 1;
            j = m;
          } 
        } 
        b++;
      } 
      param1Int1 = k;
      if (j + i > param1Int2)
        param1Int1 = k + 1; 
      return param1Int1;
    }
    
    public int getSpanIndex(int param1Int1, int param1Int2) {
      // Byte code:
      //   0: aload_0
      //   1: iload_1
      //   2: invokevirtual getSpanSize : (I)I
      //   5: istore_3
      //   6: iload_3
      //   7: iload_2
      //   8: if_icmpne -> 13
      //   11: iconst_0
      //   12: ireturn
      //   13: aload_0
      //   14: getfield mCacheSpanIndices : Z
      //   17: ifeq -> 63
      //   20: aload_0
      //   21: getfield mSpanIndexCache : Landroid/util/SparseIntArray;
      //   24: invokevirtual size : ()I
      //   27: ifle -> 63
      //   30: aload_0
      //   31: iload_1
      //   32: invokevirtual findReferenceIndexFromCache : (I)I
      //   35: istore #4
      //   37: iload #4
      //   39: iflt -> 63
      //   42: aload_0
      //   43: getfield mSpanIndexCache : Landroid/util/SparseIntArray;
      //   46: iload #4
      //   48: invokevirtual get : (I)I
      //   51: aload_0
      //   52: iload #4
      //   54: invokevirtual getSpanSize : (I)I
      //   57: iadd
      //   58: istore #5
      //   60: goto -> 128
      //   63: iconst_0
      //   64: istore #6
      //   66: iconst_0
      //   67: istore #5
      //   69: iload #6
      //   71: iload_1
      //   72: if_icmpge -> 137
      //   75: aload_0
      //   76: iload #6
      //   78: invokevirtual getSpanSize : (I)I
      //   81: istore #7
      //   83: iload #5
      //   85: iload #7
      //   87: iadd
      //   88: istore #8
      //   90: iload #8
      //   92: iload_2
      //   93: if_icmpne -> 106
      //   96: iconst_0
      //   97: istore #5
      //   99: iload #6
      //   101: istore #4
      //   103: goto -> 128
      //   106: iload #6
      //   108: istore #4
      //   110: iload #8
      //   112: istore #5
      //   114: iload #8
      //   116: iload_2
      //   117: if_icmple -> 128
      //   120: iload #7
      //   122: istore #5
      //   124: iload #6
      //   126: istore #4
      //   128: iload #4
      //   130: iconst_1
      //   131: iadd
      //   132: istore #6
      //   134: goto -> 69
      //   137: iload_3
      //   138: iload #5
      //   140: iadd
      //   141: iload_2
      //   142: if_icmpgt -> 148
      //   145: iload #5
      //   147: ireturn
      //   148: iconst_0
      //   149: ireturn
    }
    
    public abstract int getSpanSize(int param1Int);
    
    public void invalidateSpanIndexCache() {
      this.mSpanIndexCache.clear();
    }
    
    public boolean isSpanIndexCacheEnabled() {
      return this.mCacheSpanIndices;
    }
    
    public void setSpanIndexCacheEnabled(boolean param1Boolean) {
      this.mCacheSpanIndices = param1Boolean;
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/widget/GridLayoutManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */