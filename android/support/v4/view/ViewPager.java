package android.support.v4.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.annotation.CallSuper;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.Scroller;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ViewPager extends ViewGroup {
  private static final int CLOSE_ENOUGH = 2;
  
  private static final Comparator<ItemInfo> COMPARATOR;
  
  private static final boolean DEBUG = false;
  
  private static final int DEFAULT_GUTTER_SIZE = 16;
  
  private static final int DEFAULT_OFFSCREEN_PAGES = 1;
  
  private static final int DRAW_ORDER_DEFAULT = 0;
  
  private static final int DRAW_ORDER_FORWARD = 1;
  
  private static final int DRAW_ORDER_REVERSE = 2;
  
  private static final int INVALID_POINTER = -1;
  
  static final int[] LAYOUT_ATTRS = new int[] { 16842931 };
  
  private static final int MAX_SETTLE_DURATION = 600;
  
  private static final int MIN_DISTANCE_FOR_FLING = 25;
  
  private static final int MIN_FLING_VELOCITY = 400;
  
  public static final int SCROLL_STATE_DRAGGING = 1;
  
  public static final int SCROLL_STATE_IDLE = 0;
  
  public static final int SCROLL_STATE_SETTLING = 2;
  
  private static final String TAG = "ViewPager";
  
  private static final boolean USE_CACHE = false;
  
  private static final Interpolator sInterpolator;
  
  private static final ViewPositionComparator sPositionComparator;
  
  private int mActivePointerId = -1;
  
  PagerAdapter mAdapter;
  
  private List<OnAdapterChangeListener> mAdapterChangeListeners;
  
  private int mBottomPageBounds;
  
  private boolean mCalledSuper;
  
  private int mChildHeightMeasureSpec;
  
  private int mChildWidthMeasureSpec;
  
  private int mCloseEnough;
  
  int mCurItem;
  
  private int mDecorChildCount;
  
  private int mDefaultGutterSize;
  
  private int mDrawingOrder;
  
  private ArrayList<View> mDrawingOrderedChildren;
  
  private final Runnable mEndScrollRunnable = new Runnable() {
      public void run() {
        ViewPager.this.setScrollState(0);
        ViewPager.this.populate();
      }
    };
  
  private int mExpectedAdapterCount;
  
  private long mFakeDragBeginTime;
  
  private boolean mFakeDragging;
  
  private boolean mFirstLayout = true;
  
  private float mFirstOffset = -3.4028235E38F;
  
  private int mFlingDistance;
  
  private int mGutterSize;
  
  private boolean mInLayout;
  
  private float mInitialMotionX;
  
  private float mInitialMotionY;
  
  private OnPageChangeListener mInternalPageChangeListener;
  
  private boolean mIsBeingDragged;
  
  private boolean mIsScrollStarted;
  
  private boolean mIsUnableToDrag;
  
  private final ArrayList<ItemInfo> mItems = new ArrayList<ItemInfo>();
  
  private float mLastMotionX;
  
  private float mLastMotionY;
  
  private float mLastOffset = Float.MAX_VALUE;
  
  private EdgeEffect mLeftEdge;
  
  private Drawable mMarginDrawable;
  
  private int mMaximumVelocity;
  
  private int mMinimumVelocity;
  
  private boolean mNeedCalculatePageOffsets = false;
  
  private PagerObserver mObserver;
  
  private int mOffscreenPageLimit = 1;
  
  private OnPageChangeListener mOnPageChangeListener;
  
  private List<OnPageChangeListener> mOnPageChangeListeners;
  
  private int mPageMargin;
  
  private PageTransformer mPageTransformer;
  
  private int mPageTransformerLayerType;
  
  private boolean mPopulatePending;
  
  private Parcelable mRestoredAdapterState = null;
  
  private ClassLoader mRestoredClassLoader = null;
  
  private int mRestoredCurItem = -1;
  
  private EdgeEffect mRightEdge;
  
  private int mScrollState = 0;
  
  private Scroller mScroller;
  
  private boolean mScrollingCacheEnabled;
  
  private final ItemInfo mTempItem = new ItemInfo();
  
  private final Rect mTempRect = new Rect();
  
  private int mTopPageBounds;
  
  private int mTouchSlop;
  
  private VelocityTracker mVelocityTracker;
  
  static {
    COMPARATOR = new Comparator<ItemInfo>() {
        public int compare(ViewPager.ItemInfo param1ItemInfo1, ViewPager.ItemInfo param1ItemInfo2) {
          return param1ItemInfo1.position - param1ItemInfo2.position;
        }
      };
    sInterpolator = new Interpolator() {
        public float getInterpolation(float param1Float) {
          param1Float--;
          return param1Float * param1Float * param1Float * param1Float * param1Float + 1.0F;
        }
      };
    sPositionComparator = new ViewPositionComparator();
  }
  
  public ViewPager(Context paramContext) {
    super(paramContext);
    initViewPager();
  }
  
  public ViewPager(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
    initViewPager();
  }
  
  private void calculatePageOffsets(ItemInfo paramItemInfo1, int paramInt, ItemInfo paramItemInfo2) {
    float f1;
    float f3;
    int i = this.mAdapter.getCount();
    int j = getClientWidth();
    if (j > 0) {
      f1 = this.mPageMargin / j;
    } else {
      f1 = 0.0F;
    } 
    if (paramItemInfo2 != null) {
      j = paramItemInfo2.position;
      int n = paramItemInfo1.position;
      if (j < n) {
        float f = paramItemInfo2.offset + paramItemInfo2.widthFactor + f1;
        j++;
        n = 0;
        while (j <= paramItemInfo1.position && n < this.mItems.size()) {
          int i1;
          paramItemInfo2 = this.mItems.get(n);
          while (true) {
            i1 = j;
            f3 = f;
            if (j > paramItemInfo2.position) {
              i1 = j;
              f3 = f;
              if (n < this.mItems.size() - 1) {
                paramItemInfo2 = this.mItems.get(++n);
                continue;
              } 
            } 
            break;
          } 
          while (i1 < paramItemInfo2.position) {
            f3 += this.mAdapter.getPageWidth(i1) + f1;
            i1++;
          } 
          paramItemInfo2.offset = f3;
          f = f3 + paramItemInfo2.widthFactor + f1;
          j = i1 + 1;
        } 
      } else if (j > n) {
        n = this.mItems.size() - 1;
        float f = paramItemInfo2.offset;
        while (--j >= paramItemInfo1.position && n >= 0) {
          int i1;
          paramItemInfo2 = this.mItems.get(n);
          while (true) {
            i1 = j;
            f3 = f;
            if (j < paramItemInfo2.position) {
              i1 = j;
              f3 = f;
              if (n > 0) {
                paramItemInfo2 = this.mItems.get(--n);
                continue;
              } 
            } 
            break;
          } 
          while (i1 > paramItemInfo2.position) {
            f3 -= this.mAdapter.getPageWidth(i1) + f1;
            i1--;
          } 
          f = f3 - paramItemInfo2.widthFactor + f1;
          paramItemInfo2.offset = f;
          j = i1 - 1;
        } 
      } 
    } 
    int m = this.mItems.size();
    float f2 = paramItemInfo1.offset;
    int k = paramItemInfo1.position;
    j = k - 1;
    if (k == 0) {
      f3 = f2;
    } else {
      f3 = -3.4028235E38F;
    } 
    this.mFirstOffset = f3;
    if (k == --i) {
      f3 = paramItemInfo1.widthFactor + f2 - 1.0F;
    } else {
      f3 = Float.MAX_VALUE;
    } 
    this.mLastOffset = f3;
    k = paramInt - 1;
    while (k >= 0) {
      paramItemInfo2 = this.mItems.get(k);
      while (true) {
        int n = paramItemInfo2.position;
        if (j > n) {
          f2 -= this.mAdapter.getPageWidth(j) + f1;
          j--;
          continue;
        } 
        f2 -= paramItemInfo2.widthFactor + f1;
        paramItemInfo2.offset = f2;
        if (n == 0)
          this.mFirstOffset = f2; 
        break;
      } 
      k--;
      j--;
    } 
    f2 = paramItemInfo1.offset + paramItemInfo1.widthFactor + f1;
    k = paramItemInfo1.position + 1;
    j = paramInt + 1;
    for (paramInt = k; j < m; paramInt++) {
      paramItemInfo1 = this.mItems.get(j);
      while (true) {
        k = paramItemInfo1.position;
        if (paramInt < k) {
          f2 += this.mAdapter.getPageWidth(paramInt) + f1;
          paramInt++;
          continue;
        } 
        if (k == i)
          this.mLastOffset = paramItemInfo1.widthFactor + f2 - 1.0F; 
        break;
      } 
      paramItemInfo1.offset = f2;
      f2 += paramItemInfo1.widthFactor + f1;
      j++;
    } 
    this.mNeedCalculatePageOffsets = false;
  }
  
  private void completeScroll(boolean paramBoolean) {
    boolean bool;
    if (this.mScrollState == 2) {
      bool = true;
    } else {
      bool = false;
    } 
    if (bool) {
      setScrollingCacheEnabled(false);
      if ((this.mScroller.isFinished() ^ true) != 0) {
        this.mScroller.abortAnimation();
        int i = getScrollX();
        int j = getScrollY();
        int k = this.mScroller.getCurrX();
        int m = this.mScroller.getCurrY();
        if (i != k || j != m) {
          scrollTo(k, m);
          if (k != i)
            pageScrolled(k); 
        } 
      } 
    } 
    this.mPopulatePending = false;
    for (byte b = 0; b < this.mItems.size(); b++) {
      ItemInfo itemInfo = this.mItems.get(b);
      if (itemInfo.scrolling) {
        itemInfo.scrolling = false;
        bool = true;
      } 
    } 
    if (bool)
      if (paramBoolean) {
        ViewCompat.postOnAnimation((View)this, this.mEndScrollRunnable);
      } else {
        this.mEndScrollRunnable.run();
      }  
  }
  
  private int determineTargetPage(int paramInt1, float paramFloat, int paramInt2, int paramInt3) {
    if (Math.abs(paramInt3) > this.mFlingDistance && Math.abs(paramInt2) > this.mMinimumVelocity) {
      if (paramInt2 <= 0)
        paramInt1++; 
    } else {
      float f;
      if (paramInt1 >= this.mCurItem) {
        f = 0.4F;
      } else {
        f = 0.6F;
      } 
      paramInt1 += (int)(paramFloat + f);
    } 
    paramInt2 = paramInt1;
    if (this.mItems.size() > 0) {
      ItemInfo itemInfo1 = this.mItems.get(0);
      ArrayList<ItemInfo> arrayList = this.mItems;
      ItemInfo itemInfo2 = arrayList.get(arrayList.size() - 1);
      paramInt2 = Math.max(itemInfo1.position, Math.min(paramInt1, itemInfo2.position));
    } 
    return paramInt2;
  }
  
  private void dispatchOnPageScrolled(int paramInt1, float paramFloat, int paramInt2) {
    OnPageChangeListener onPageChangeListener2 = this.mOnPageChangeListener;
    if (onPageChangeListener2 != null)
      onPageChangeListener2.onPageScrolled(paramInt1, paramFloat, paramInt2); 
    List<OnPageChangeListener> list = this.mOnPageChangeListeners;
    if (list != null) {
      byte b = 0;
      int i = list.size();
      while (b < i) {
        OnPageChangeListener onPageChangeListener = this.mOnPageChangeListeners.get(b);
        if (onPageChangeListener != null)
          onPageChangeListener.onPageScrolled(paramInt1, paramFloat, paramInt2); 
        b++;
      } 
    } 
    OnPageChangeListener onPageChangeListener1 = this.mInternalPageChangeListener;
    if (onPageChangeListener1 != null)
      onPageChangeListener1.onPageScrolled(paramInt1, paramFloat, paramInt2); 
  }
  
  private void dispatchOnPageSelected(int paramInt) {
    OnPageChangeListener onPageChangeListener2 = this.mOnPageChangeListener;
    if (onPageChangeListener2 != null)
      onPageChangeListener2.onPageSelected(paramInt); 
    List<OnPageChangeListener> list = this.mOnPageChangeListeners;
    if (list != null) {
      byte b = 0;
      int i = list.size();
      while (b < i) {
        OnPageChangeListener onPageChangeListener = this.mOnPageChangeListeners.get(b);
        if (onPageChangeListener != null)
          onPageChangeListener.onPageSelected(paramInt); 
        b++;
      } 
    } 
    OnPageChangeListener onPageChangeListener1 = this.mInternalPageChangeListener;
    if (onPageChangeListener1 != null)
      onPageChangeListener1.onPageSelected(paramInt); 
  }
  
  private void dispatchOnScrollStateChanged(int paramInt) {
    OnPageChangeListener onPageChangeListener2 = this.mOnPageChangeListener;
    if (onPageChangeListener2 != null)
      onPageChangeListener2.onPageScrollStateChanged(paramInt); 
    List<OnPageChangeListener> list = this.mOnPageChangeListeners;
    if (list != null) {
      byte b = 0;
      int i = list.size();
      while (b < i) {
        OnPageChangeListener onPageChangeListener = this.mOnPageChangeListeners.get(b);
        if (onPageChangeListener != null)
          onPageChangeListener.onPageScrollStateChanged(paramInt); 
        b++;
      } 
    } 
    OnPageChangeListener onPageChangeListener1 = this.mInternalPageChangeListener;
    if (onPageChangeListener1 != null)
      onPageChangeListener1.onPageScrollStateChanged(paramInt); 
  }
  
  private void enableLayers(boolean paramBoolean) {
    int i = getChildCount();
    for (byte b = 0; b < i; b++) {
      boolean bool;
      if (paramBoolean) {
        bool = this.mPageTransformerLayerType;
      } else {
        bool = false;
      } 
      getChildAt(b).setLayerType(bool, null);
    } 
  }
  
  private void endDrag() {
    this.mIsBeingDragged = false;
    this.mIsUnableToDrag = false;
    VelocityTracker velocityTracker = this.mVelocityTracker;
    if (velocityTracker != null) {
      velocityTracker.recycle();
      this.mVelocityTracker = null;
    } 
  }
  
  private Rect getChildRectInPagerCoordinates(Rect paramRect, View paramView) {
    Rect rect = paramRect;
    if (paramRect == null)
      rect = new Rect(); 
    if (paramView == null) {
      rect.set(0, 0, 0, 0);
      return rect;
    } 
    rect.left = paramView.getLeft();
    rect.right = paramView.getRight();
    rect.top = paramView.getTop();
    rect.bottom = paramView.getBottom();
    ViewParent viewParent = paramView.getParent();
    while (viewParent instanceof ViewGroup && viewParent != this) {
      ViewGroup viewGroup = (ViewGroup)viewParent;
      rect.left += viewGroup.getLeft();
      rect.right += viewGroup.getRight();
      rect.top += viewGroup.getTop();
      rect.bottom += viewGroup.getBottom();
      ViewParent viewParent1 = viewGroup.getParent();
    } 
    return rect;
  }
  
  private int getClientWidth() {
    return getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
  }
  
  private ItemInfo infoForCurrentScrollPosition() {
    float f2;
    float f3;
    int i = getClientWidth();
    float f1 = 0.0F;
    if (i > 0) {
      f2 = getScrollX() / i;
    } else {
      f2 = 0.0F;
    } 
    if (i > 0) {
      f3 = this.mPageMargin / i;
    } else {
      f3 = 0.0F;
    } 
    ItemInfo itemInfo = null;
    float f4 = 0.0F;
    int j = -1;
    i = 0;
    boolean bool = true;
    while (i < this.mItems.size()) {
      ItemInfo itemInfo1 = this.mItems.get(i);
      int k = i;
      ItemInfo itemInfo2 = itemInfo1;
      if (!bool) {
        int m = itemInfo1.position;
        j++;
        k = i;
        itemInfo2 = itemInfo1;
        if (m != j) {
          itemInfo2 = this.mTempItem;
          itemInfo2.offset = f1 + f4 + f3;
          itemInfo2.position = j;
          itemInfo2.widthFactor = this.mAdapter.getPageWidth(j);
          k = i - 1;
        } 
      } 
      f1 = itemInfo2.offset;
      f4 = itemInfo2.widthFactor;
      if (bool || f2 >= f1) {
        if (f2 < f4 + f1 + f3 || k == this.mItems.size() - 1)
          return itemInfo2; 
        j = itemInfo2.position;
        f4 = itemInfo2.widthFactor;
        i = k + 1;
        bool = false;
        itemInfo = itemInfo2;
        continue;
      } 
      return itemInfo;
    } 
    return itemInfo;
  }
  
  private static boolean isDecorView(@NonNull View paramView) {
    boolean bool;
    if (paramView.getClass().getAnnotation(DecorView.class) != null) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private boolean isGutterDrag(float paramFloat1, float paramFloat2) {
    boolean bool;
    if ((paramFloat1 < this.mGutterSize && paramFloat2 > 0.0F) || (paramFloat1 > (getWidth() - this.mGutterSize) && paramFloat2 < 0.0F)) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private void onSecondaryPointerUp(MotionEvent paramMotionEvent) {
    int i = paramMotionEvent.getActionIndex();
    if (paramMotionEvent.getPointerId(i) == this.mActivePointerId) {
      if (i == 0) {
        i = 1;
      } else {
        i = 0;
      } 
      this.mLastMotionX = paramMotionEvent.getX(i);
      this.mActivePointerId = paramMotionEvent.getPointerId(i);
      VelocityTracker velocityTracker = this.mVelocityTracker;
      if (velocityTracker != null)
        velocityTracker.clear(); 
    } 
  }
  
  private boolean pageScrolled(int paramInt) {
    if (this.mItems.size() == 0) {
      if (this.mFirstLayout)
        return false; 
      this.mCalledSuper = false;
      onPageScrolled(0, 0.0F, 0);
      if (this.mCalledSuper)
        return false; 
      throw new IllegalStateException("onPageScrolled did not call superclass implementation");
    } 
    ItemInfo itemInfo = infoForCurrentScrollPosition();
    int i = getClientWidth();
    int j = this.mPageMargin;
    float f1 = j;
    float f2 = i;
    f1 /= f2;
    int k = itemInfo.position;
    f2 = (paramInt / f2 - itemInfo.offset) / (itemInfo.widthFactor + f1);
    paramInt = (int)((i + j) * f2);
    this.mCalledSuper = false;
    onPageScrolled(k, f2, paramInt);
    if (this.mCalledSuper)
      return true; 
    throw new IllegalStateException("onPageScrolled did not call superclass implementation");
  }
  
  private boolean performDrag(float paramFloat) {
    boolean bool4;
    float f1 = this.mLastMotionX;
    this.mLastMotionX = paramFloat;
    float f2 = getScrollX() + f1 - paramFloat;
    float f3 = getClientWidth();
    paramFloat = this.mFirstOffset * f3;
    f1 = this.mLastOffset * f3;
    ArrayList<ItemInfo> arrayList1 = this.mItems;
    boolean bool1 = false;
    boolean bool2 = false;
    boolean bool3 = false;
    ItemInfo itemInfo1 = arrayList1.get(0);
    ArrayList<ItemInfo> arrayList2 = this.mItems;
    ItemInfo itemInfo2 = arrayList2.get(arrayList2.size() - 1);
    if (itemInfo1.position != 0) {
      paramFloat = itemInfo1.offset * f3;
      i = 0;
    } else {
      i = 1;
    } 
    if (itemInfo2.position != this.mAdapter.getCount() - 1) {
      f1 = itemInfo2.offset * f3;
      bool4 = false;
    } else {
      bool4 = true;
    } 
    if (f2 < paramFloat) {
      if (i) {
        this.mLeftEdge.onPull(Math.abs(paramFloat - f2) / f3);
        bool3 = true;
      } 
    } else {
      bool3 = bool2;
      paramFloat = f2;
      if (f2 > f1) {
        bool3 = bool1;
        if (bool4) {
          this.mRightEdge.onPull(Math.abs(f2 - f1) / f3);
          bool3 = true;
        } 
        paramFloat = f1;
      } 
    } 
    f1 = this.mLastMotionX;
    int i = (int)paramFloat;
    this.mLastMotionX = f1 + paramFloat - i;
    scrollTo(i, getScrollY());
    pageScrolled(i);
    return bool3;
  }
  
  private void recomputeScrollPosition(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    if (paramInt2 > 0 && !this.mItems.isEmpty()) {
      if (!this.mScroller.isFinished()) {
        this.mScroller.setFinalX(getCurrentItem() * getClientWidth());
      } else {
        int i = getPaddingLeft();
        int j = getPaddingRight();
        int k = getPaddingLeft();
        int m = getPaddingRight();
        scrollTo((int)(getScrollX() / (paramInt2 - k - m + paramInt4) * (paramInt1 - i - j + paramInt3)), getScrollY());
      } 
    } else {
      float f;
      ItemInfo itemInfo = infoForPosition(this.mCurItem);
      if (itemInfo != null) {
        f = Math.min(itemInfo.offset, this.mLastOffset);
      } else {
        f = 0.0F;
      } 
      paramInt1 = (int)(f * (paramInt1 - getPaddingLeft() - getPaddingRight()));
      if (paramInt1 != getScrollX()) {
        completeScroll(false);
        scrollTo(paramInt1, getScrollY());
      } 
    } 
  }
  
  private void removeNonDecorViews() {
    for (int i = 0; i < getChildCount(); i = j + 1) {
      int j = i;
      if (!((LayoutParams)getChildAt(i).getLayoutParams()).isDecor) {
        removeViewAt(i);
        j = i - 1;
      } 
    } 
  }
  
  private void requestParentDisallowInterceptTouchEvent(boolean paramBoolean) {
    ViewParent viewParent = getParent();
    if (viewParent != null)
      viewParent.requestDisallowInterceptTouchEvent(paramBoolean); 
  }
  
  private boolean resetTouch() {
    this.mActivePointerId = -1;
    endDrag();
    this.mLeftEdge.onRelease();
    this.mRightEdge.onRelease();
    return (this.mLeftEdge.isFinished() || this.mRightEdge.isFinished());
  }
  
  private void scrollToItem(int paramInt1, boolean paramBoolean1, int paramInt2, boolean paramBoolean2) {
    boolean bool;
    ItemInfo itemInfo = infoForPosition(paramInt1);
    if (itemInfo != null) {
      bool = (int)(getClientWidth() * Math.max(this.mFirstOffset, Math.min(itemInfo.offset, this.mLastOffset)));
    } else {
      bool = false;
    } 
    if (paramBoolean1) {
      smoothScrollTo(bool, 0, paramInt2);
      if (paramBoolean2)
        dispatchOnPageSelected(paramInt1); 
    } else {
      if (paramBoolean2)
        dispatchOnPageSelected(paramInt1); 
      completeScroll(false);
      scrollTo(bool, 0);
      pageScrolled(bool);
    } 
  }
  
  private void setScrollingCacheEnabled(boolean paramBoolean) {
    if (this.mScrollingCacheEnabled != paramBoolean)
      this.mScrollingCacheEnabled = paramBoolean; 
  }
  
  private void sortChildDrawingOrder() {
    if (this.mDrawingOrder != 0) {
      ArrayList<View> arrayList = this.mDrawingOrderedChildren;
      if (arrayList == null) {
        this.mDrawingOrderedChildren = new ArrayList<View>();
      } else {
        arrayList.clear();
      } 
      int i = getChildCount();
      for (byte b = 0; b < i; b++) {
        View view = getChildAt(b);
        this.mDrawingOrderedChildren.add(view);
      } 
      Collections.sort(this.mDrawingOrderedChildren, sPositionComparator);
    } 
  }
  
  public void addFocusables(ArrayList<View> paramArrayList, int paramInt1, int paramInt2) {
    int i = paramArrayList.size();
    int j = getDescendantFocusability();
    if (j != 393216)
      for (byte b = 0; b < getChildCount(); b++) {
        View view = getChildAt(b);
        if (view.getVisibility() == 0) {
          ItemInfo itemInfo = infoForChild(view);
          if (itemInfo != null && itemInfo.position == this.mCurItem)
            view.addFocusables(paramArrayList, paramInt1, paramInt2); 
        } 
      }  
    if (j != 262144 || i == paramArrayList.size()) {
      if (!isFocusable())
        return; 
      if ((paramInt2 & 0x1) == 1 && isInTouchMode() && !isFocusableInTouchMode())
        return; 
      paramArrayList.add(this);
    } 
  }
  
  ItemInfo addNewItem(int paramInt1, int paramInt2) {
    ItemInfo itemInfo = new ItemInfo();
    itemInfo.position = paramInt1;
    itemInfo.object = this.mAdapter.instantiateItem(this, paramInt1);
    itemInfo.widthFactor = this.mAdapter.getPageWidth(paramInt1);
    if (paramInt2 < 0 || paramInt2 >= this.mItems.size()) {
      this.mItems.add(itemInfo);
      return itemInfo;
    } 
    this.mItems.add(paramInt2, itemInfo);
    return itemInfo;
  }
  
  public void addOnAdapterChangeListener(@NonNull OnAdapterChangeListener paramOnAdapterChangeListener) {
    if (this.mAdapterChangeListeners == null)
      this.mAdapterChangeListeners = new ArrayList<OnAdapterChangeListener>(); 
    this.mAdapterChangeListeners.add(paramOnAdapterChangeListener);
  }
  
  public void addOnPageChangeListener(OnPageChangeListener paramOnPageChangeListener) {
    if (this.mOnPageChangeListeners == null)
      this.mOnPageChangeListeners = new ArrayList<OnPageChangeListener>(); 
    this.mOnPageChangeListeners.add(paramOnPageChangeListener);
  }
  
  public void addTouchables(ArrayList<View> paramArrayList) {
    for (byte b = 0; b < getChildCount(); b++) {
      View view = getChildAt(b);
      if (view.getVisibility() == 0) {
        ItemInfo itemInfo = infoForChild(view);
        if (itemInfo != null && itemInfo.position == this.mCurItem)
          view.addTouchables(paramArrayList); 
      } 
    } 
  }
  
  public void addView(View paramView, int paramInt, ViewGroup.LayoutParams paramLayoutParams) {
    ViewGroup.LayoutParams layoutParams = paramLayoutParams;
    if (!checkLayoutParams(paramLayoutParams))
      layoutParams = generateLayoutParams(paramLayoutParams); 
    paramLayoutParams = layoutParams;
    int i = ((LayoutParams)paramLayoutParams).isDecor | isDecorView(paramView);
    ((LayoutParams)paramLayoutParams).isDecor = i;
    if (this.mInLayout) {
      if (i == 0) {
        ((LayoutParams)paramLayoutParams).needsMeasure = true;
        addViewInLayout(paramView, paramInt, layoutParams);
      } else {
        throw new IllegalStateException("Cannot add pager decor view during layout");
      } 
    } else {
      super.addView(paramView, paramInt, layoutParams);
    } 
  }
  
  public boolean arrowScroll(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual findFocus : ()Landroid/view/View;
    //   4: astore_2
    //   5: iconst_0
    //   6: istore_3
    //   7: aload_2
    //   8: aload_0
    //   9: if_acmpne -> 18
    //   12: aconst_null
    //   13: astore #4
    //   15: goto -> 188
    //   18: aload_2
    //   19: astore #4
    //   21: aload_2
    //   22: ifnull -> 188
    //   25: aload_2
    //   26: invokevirtual getParent : ()Landroid/view/ViewParent;
    //   29: astore #4
    //   31: aload #4
    //   33: instanceof android/view/ViewGroup
    //   36: ifeq -> 63
    //   39: aload #4
    //   41: aload_0
    //   42: if_acmpne -> 51
    //   45: iconst_1
    //   46: istore #5
    //   48: goto -> 66
    //   51: aload #4
    //   53: invokeinterface getParent : ()Landroid/view/ViewParent;
    //   58: astore #4
    //   60: goto -> 31
    //   63: iconst_0
    //   64: istore #5
    //   66: aload_2
    //   67: astore #4
    //   69: iload #5
    //   71: ifne -> 188
    //   74: new java/lang/StringBuilder
    //   77: dup
    //   78: invokespecial <init> : ()V
    //   81: astore #6
    //   83: aload #6
    //   85: aload_2
    //   86: invokevirtual getClass : ()Ljava/lang/Class;
    //   89: invokevirtual getSimpleName : ()Ljava/lang/String;
    //   92: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   95: pop
    //   96: aload_2
    //   97: invokevirtual getParent : ()Landroid/view/ViewParent;
    //   100: astore #4
    //   102: aload #4
    //   104: instanceof android/view/ViewGroup
    //   107: ifeq -> 145
    //   110: aload #6
    //   112: ldc_w ' => '
    //   115: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   118: pop
    //   119: aload #6
    //   121: aload #4
    //   123: invokevirtual getClass : ()Ljava/lang/Class;
    //   126: invokevirtual getSimpleName : ()Ljava/lang/String;
    //   129: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   132: pop
    //   133: aload #4
    //   135: invokeinterface getParent : ()Landroid/view/ViewParent;
    //   140: astore #4
    //   142: goto -> 102
    //   145: new java/lang/StringBuilder
    //   148: dup
    //   149: invokespecial <init> : ()V
    //   152: astore #4
    //   154: aload #4
    //   156: ldc_w 'arrowScroll tried to find focus based on non-child current focused view '
    //   159: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   162: pop
    //   163: aload #4
    //   165: aload #6
    //   167: invokevirtual toString : ()Ljava/lang/String;
    //   170: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   173: pop
    //   174: ldc 'ViewPager'
    //   176: aload #4
    //   178: invokevirtual toString : ()Ljava/lang/String;
    //   181: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   184: pop
    //   185: goto -> 12
    //   188: invokestatic getInstance : ()Landroid/view/FocusFinder;
    //   191: aload_0
    //   192: aload #4
    //   194: iload_1
    //   195: invokevirtual findNextFocus : (Landroid/view/ViewGroup;Landroid/view/View;I)Landroid/view/View;
    //   198: astore_2
    //   199: aload_2
    //   200: ifnull -> 335
    //   203: aload_2
    //   204: aload #4
    //   206: if_acmpeq -> 335
    //   209: iload_1
    //   210: bipush #17
    //   212: if_icmpne -> 272
    //   215: aload_0
    //   216: aload_0
    //   217: getfield mTempRect : Landroid/graphics/Rect;
    //   220: aload_2
    //   221: invokespecial getChildRectInPagerCoordinates : (Landroid/graphics/Rect;Landroid/view/View;)Landroid/graphics/Rect;
    //   224: getfield left : I
    //   227: istore #5
    //   229: aload_0
    //   230: aload_0
    //   231: getfield mTempRect : Landroid/graphics/Rect;
    //   234: aload #4
    //   236: invokespecial getChildRectInPagerCoordinates : (Landroid/graphics/Rect;Landroid/view/View;)Landroid/graphics/Rect;
    //   239: getfield left : I
    //   242: istore #7
    //   244: aload #4
    //   246: ifnull -> 264
    //   249: iload #5
    //   251: iload #7
    //   253: if_icmplt -> 264
    //   256: aload_0
    //   257: invokevirtual pageLeft : ()Z
    //   260: istore_3
    //   261: goto -> 269
    //   264: aload_2
    //   265: invokevirtual requestFocus : ()Z
    //   268: istore_3
    //   269: goto -> 373
    //   272: iload_1
    //   273: bipush #66
    //   275: if_icmpne -> 373
    //   278: aload_0
    //   279: aload_0
    //   280: getfield mTempRect : Landroid/graphics/Rect;
    //   283: aload_2
    //   284: invokespecial getChildRectInPagerCoordinates : (Landroid/graphics/Rect;Landroid/view/View;)Landroid/graphics/Rect;
    //   287: getfield left : I
    //   290: istore #5
    //   292: aload_0
    //   293: aload_0
    //   294: getfield mTempRect : Landroid/graphics/Rect;
    //   297: aload #4
    //   299: invokespecial getChildRectInPagerCoordinates : (Landroid/graphics/Rect;Landroid/view/View;)Landroid/graphics/Rect;
    //   302: getfield left : I
    //   305: istore #7
    //   307: aload #4
    //   309: ifnull -> 327
    //   312: iload #5
    //   314: iload #7
    //   316: if_icmpgt -> 327
    //   319: aload_0
    //   320: invokevirtual pageRight : ()Z
    //   323: istore_3
    //   324: goto -> 269
    //   327: aload_2
    //   328: invokevirtual requestFocus : ()Z
    //   331: istore_3
    //   332: goto -> 269
    //   335: iload_1
    //   336: bipush #17
    //   338: if_icmpeq -> 368
    //   341: iload_1
    //   342: iconst_1
    //   343: if_icmpne -> 349
    //   346: goto -> 368
    //   349: iload_1
    //   350: bipush #66
    //   352: if_icmpeq -> 360
    //   355: iload_1
    //   356: iconst_2
    //   357: if_icmpne -> 373
    //   360: aload_0
    //   361: invokevirtual pageRight : ()Z
    //   364: istore_3
    //   365: goto -> 373
    //   368: aload_0
    //   369: invokevirtual pageLeft : ()Z
    //   372: istore_3
    //   373: iload_3
    //   374: ifeq -> 385
    //   377: aload_0
    //   378: iload_1
    //   379: invokestatic getContantForFocusDirection : (I)I
    //   382: invokevirtual playSoundEffect : (I)V
    //   385: iload_3
    //   386: ireturn
  }
  
  public boolean beginFakeDrag() {
    if (this.mIsBeingDragged)
      return false; 
    this.mFakeDragging = true;
    setScrollState(1);
    this.mLastMotionX = 0.0F;
    this.mInitialMotionX = 0.0F;
    VelocityTracker velocityTracker = this.mVelocityTracker;
    if (velocityTracker == null) {
      this.mVelocityTracker = VelocityTracker.obtain();
    } else {
      velocityTracker.clear();
    } 
    long l = SystemClock.uptimeMillis();
    MotionEvent motionEvent = MotionEvent.obtain(l, l, 0, 0.0F, 0.0F, 0);
    this.mVelocityTracker.addMovement(motionEvent);
    motionEvent.recycle();
    this.mFakeDragBeginTime = l;
    return true;
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
    if (paramBoolean && paramView.canScrollHorizontally(-paramInt1)) {
      paramBoolean = bool1;
    } else {
      paramBoolean = false;
    } 
    return paramBoolean;
  }
  
  public boolean canScrollHorizontally(int paramInt) {
    PagerAdapter pagerAdapter = this.mAdapter;
    boolean bool1 = false;
    boolean bool2 = false;
    if (pagerAdapter == null)
      return false; 
    int i = getClientWidth();
    int j = getScrollX();
    if (paramInt < 0) {
      if (j > (int)(i * this.mFirstOffset))
        bool2 = true; 
      return bool2;
    } 
    bool2 = bool1;
    if (paramInt > 0) {
      bool2 = bool1;
      if (j < (int)(i * this.mLastOffset))
        bool2 = true; 
    } 
    return bool2;
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
  
  public void clearOnPageChangeListeners() {
    List<OnPageChangeListener> list = this.mOnPageChangeListeners;
    if (list != null)
      list.clear(); 
  }
  
  public void computeScroll() {
    this.mIsScrollStarted = true;
    if (!this.mScroller.isFinished() && this.mScroller.computeScrollOffset()) {
      int i = getScrollX();
      int j = getScrollY();
      int k = this.mScroller.getCurrX();
      int m = this.mScroller.getCurrY();
      if (i != k || j != m) {
        scrollTo(k, m);
        if (!pageScrolled(k)) {
          this.mScroller.abortAnimation();
          scrollTo(0, m);
        } 
      } 
      ViewCompat.postInvalidateOnAnimation((View)this);
      return;
    } 
    completeScroll(true);
  }
  
  void dataSetChanged() {
    // Byte code:
    //   0: aload_0
    //   1: getfield mAdapter : Landroid/support/v4/view/PagerAdapter;
    //   4: invokevirtual getCount : ()I
    //   7: istore_1
    //   8: aload_0
    //   9: iload_1
    //   10: putfield mExpectedAdapterCount : I
    //   13: aload_0
    //   14: getfield mItems : Ljava/util/ArrayList;
    //   17: invokevirtual size : ()I
    //   20: aload_0
    //   21: getfield mOffscreenPageLimit : I
    //   24: iconst_2
    //   25: imul
    //   26: iconst_1
    //   27: iadd
    //   28: if_icmpge -> 47
    //   31: aload_0
    //   32: getfield mItems : Ljava/util/ArrayList;
    //   35: invokevirtual size : ()I
    //   38: iload_1
    //   39: if_icmpge -> 47
    //   42: iconst_1
    //   43: istore_2
    //   44: goto -> 49
    //   47: iconst_0
    //   48: istore_2
    //   49: aload_0
    //   50: getfield mCurItem : I
    //   53: istore_3
    //   54: iconst_0
    //   55: istore #4
    //   57: iconst_0
    //   58: istore #5
    //   60: iload #4
    //   62: aload_0
    //   63: getfield mItems : Ljava/util/ArrayList;
    //   66: invokevirtual size : ()I
    //   69: if_icmpge -> 301
    //   72: aload_0
    //   73: getfield mItems : Ljava/util/ArrayList;
    //   76: iload #4
    //   78: invokevirtual get : (I)Ljava/lang/Object;
    //   81: checkcast android/support/v4/view/ViewPager$ItemInfo
    //   84: astore #6
    //   86: aload_0
    //   87: getfield mAdapter : Landroid/support/v4/view/PagerAdapter;
    //   90: aload #6
    //   92: getfield object : Ljava/lang/Object;
    //   95: invokevirtual getItemPosition : (Ljava/lang/Object;)I
    //   98: istore #7
    //   100: iload #7
    //   102: iconst_m1
    //   103: if_icmpne -> 120
    //   106: iload_3
    //   107: istore #8
    //   109: iload #4
    //   111: istore #9
    //   113: iload #5
    //   115: istore #10
    //   117: goto -> 285
    //   120: iload #7
    //   122: bipush #-2
    //   124: if_icmpne -> 238
    //   127: aload_0
    //   128: getfield mItems : Ljava/util/ArrayList;
    //   131: iload #4
    //   133: invokevirtual remove : (I)Ljava/lang/Object;
    //   136: pop
    //   137: iload #4
    //   139: iconst_1
    //   140: isub
    //   141: istore #8
    //   143: iload #5
    //   145: istore_2
    //   146: iload #5
    //   148: ifne -> 161
    //   151: aload_0
    //   152: getfield mAdapter : Landroid/support/v4/view/PagerAdapter;
    //   155: aload_0
    //   156: invokevirtual startUpdate : (Landroid/view/ViewGroup;)V
    //   159: iconst_1
    //   160: istore_2
    //   161: aload_0
    //   162: getfield mAdapter : Landroid/support/v4/view/PagerAdapter;
    //   165: aload_0
    //   166: aload #6
    //   168: getfield position : I
    //   171: aload #6
    //   173: getfield object : Ljava/lang/Object;
    //   176: invokevirtual destroyItem : (Landroid/view/ViewGroup;ILjava/lang/Object;)V
    //   179: aload_0
    //   180: getfield mCurItem : I
    //   183: istore #10
    //   185: iload #8
    //   187: istore #4
    //   189: iload_2
    //   190: istore #5
    //   192: iload #10
    //   194: aload #6
    //   196: getfield position : I
    //   199: if_icmpne -> 222
    //   202: iconst_0
    //   203: iload #10
    //   205: iload_1
    //   206: iconst_1
    //   207: isub
    //   208: invokestatic min : (II)I
    //   211: invokestatic max : (II)I
    //   214: istore_3
    //   215: iload_2
    //   216: istore #5
    //   218: iload #8
    //   220: istore #4
    //   222: iconst_1
    //   223: istore_2
    //   224: iload_3
    //   225: istore #8
    //   227: iload #4
    //   229: istore #9
    //   231: iload #5
    //   233: istore #10
    //   235: goto -> 285
    //   238: aload #6
    //   240: getfield position : I
    //   243: istore #11
    //   245: iload_3
    //   246: istore #8
    //   248: iload #4
    //   250: istore #9
    //   252: iload #5
    //   254: istore #10
    //   256: iload #11
    //   258: iload #7
    //   260: if_icmpeq -> 285
    //   263: iload #11
    //   265: aload_0
    //   266: getfield mCurItem : I
    //   269: if_icmpne -> 275
    //   272: iload #7
    //   274: istore_3
    //   275: aload #6
    //   277: iload #7
    //   279: putfield position : I
    //   282: goto -> 222
    //   285: iload #9
    //   287: iconst_1
    //   288: iadd
    //   289: istore #4
    //   291: iload #8
    //   293: istore_3
    //   294: iload #10
    //   296: istore #5
    //   298: goto -> 60
    //   301: iload #5
    //   303: ifeq -> 314
    //   306: aload_0
    //   307: getfield mAdapter : Landroid/support/v4/view/PagerAdapter;
    //   310: aload_0
    //   311: invokevirtual finishUpdate : (Landroid/view/ViewGroup;)V
    //   314: aload_0
    //   315: getfield mItems : Ljava/util/ArrayList;
    //   318: getstatic android/support/v4/view/ViewPager.COMPARATOR : Ljava/util/Comparator;
    //   321: invokestatic sort : (Ljava/util/List;Ljava/util/Comparator;)V
    //   324: iload_2
    //   325: ifeq -> 389
    //   328: aload_0
    //   329: invokevirtual getChildCount : ()I
    //   332: istore #4
    //   334: iconst_0
    //   335: istore #5
    //   337: iload #5
    //   339: iload #4
    //   341: if_icmpge -> 378
    //   344: aload_0
    //   345: iload #5
    //   347: invokevirtual getChildAt : (I)Landroid/view/View;
    //   350: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   353: checkcast android/support/v4/view/ViewPager$LayoutParams
    //   356: astore #6
    //   358: aload #6
    //   360: getfield isDecor : Z
    //   363: ifne -> 372
    //   366: aload #6
    //   368: fconst_0
    //   369: putfield widthFactor : F
    //   372: iinc #5, 1
    //   375: goto -> 337
    //   378: aload_0
    //   379: iload_3
    //   380: iconst_0
    //   381: iconst_1
    //   382: invokevirtual setCurrentItemInternal : (IZZ)V
    //   385: aload_0
    //   386: invokevirtual requestLayout : ()V
    //   389: return
  }
  
  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent) {
    return (super.dispatchKeyEvent(paramKeyEvent) || executeKeyEvent(paramKeyEvent));
  }
  
  public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent) {
    if (paramAccessibilityEvent.getEventType() == 4096)
      return super.dispatchPopulateAccessibilityEvent(paramAccessibilityEvent); 
    int i = getChildCount();
    for (byte b = 0; b < i; b++) {
      View view = getChildAt(b);
      if (view.getVisibility() == 0) {
        ItemInfo itemInfo = infoForChild(view);
        if (itemInfo != null && itemInfo.position == this.mCurItem && view.dispatchPopulateAccessibilityEvent(paramAccessibilityEvent))
          return true; 
      } 
    } 
    return false;
  }
  
  float distanceInfluenceForSnapDuration(float paramFloat) {
    return (float)Math.sin(((paramFloat - 0.5F) * 0.47123894F));
  }
  
  public void draw(Canvas paramCanvas) {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokespecial draw : (Landroid/graphics/Canvas;)V
    //   5: aload_0
    //   6: invokevirtual getOverScrollMode : ()I
    //   9: istore_2
    //   10: iconst_0
    //   11: istore_3
    //   12: iconst_0
    //   13: istore #4
    //   15: iload_2
    //   16: ifeq -> 64
    //   19: iload_2
    //   20: iconst_1
    //   21: if_icmpne -> 47
    //   24: aload_0
    //   25: getfield mAdapter : Landroid/support/v4/view/PagerAdapter;
    //   28: astore #5
    //   30: aload #5
    //   32: ifnull -> 47
    //   35: aload #5
    //   37: invokevirtual getCount : ()I
    //   40: iconst_1
    //   41: if_icmple -> 47
    //   44: goto -> 64
    //   47: aload_0
    //   48: getfield mLeftEdge : Landroid/widget/EdgeEffect;
    //   51: invokevirtual finish : ()V
    //   54: aload_0
    //   55: getfield mRightEdge : Landroid/widget/EdgeEffect;
    //   58: invokevirtual finish : ()V
    //   61: goto -> 256
    //   64: aload_0
    //   65: getfield mLeftEdge : Landroid/widget/EdgeEffect;
    //   68: invokevirtual isFinished : ()Z
    //   71: ifne -> 154
    //   74: aload_1
    //   75: invokevirtual save : ()I
    //   78: istore_3
    //   79: aload_0
    //   80: invokevirtual getHeight : ()I
    //   83: aload_0
    //   84: invokevirtual getPaddingTop : ()I
    //   87: isub
    //   88: aload_0
    //   89: invokevirtual getPaddingBottom : ()I
    //   92: isub
    //   93: istore #4
    //   95: aload_0
    //   96: invokevirtual getWidth : ()I
    //   99: istore_2
    //   100: aload_1
    //   101: ldc_w 270.0
    //   104: invokevirtual rotate : (F)V
    //   107: aload_1
    //   108: iload #4
    //   110: ineg
    //   111: aload_0
    //   112: invokevirtual getPaddingTop : ()I
    //   115: iadd
    //   116: i2f
    //   117: aload_0
    //   118: getfield mFirstOffset : F
    //   121: iload_2
    //   122: i2f
    //   123: fmul
    //   124: invokevirtual translate : (FF)V
    //   127: aload_0
    //   128: getfield mLeftEdge : Landroid/widget/EdgeEffect;
    //   131: iload #4
    //   133: iload_2
    //   134: invokevirtual setSize : (II)V
    //   137: iconst_0
    //   138: aload_0
    //   139: getfield mLeftEdge : Landroid/widget/EdgeEffect;
    //   142: aload_1
    //   143: invokevirtual draw : (Landroid/graphics/Canvas;)Z
    //   146: ior
    //   147: istore #4
    //   149: aload_1
    //   150: iload_3
    //   151: invokevirtual restoreToCount : (I)V
    //   154: iload #4
    //   156: istore_3
    //   157: aload_0
    //   158: getfield mRightEdge : Landroid/widget/EdgeEffect;
    //   161: invokevirtual isFinished : ()Z
    //   164: ifne -> 256
    //   167: aload_1
    //   168: invokevirtual save : ()I
    //   171: istore_2
    //   172: aload_0
    //   173: invokevirtual getWidth : ()I
    //   176: istore #6
    //   178: aload_0
    //   179: invokevirtual getHeight : ()I
    //   182: istore #7
    //   184: aload_0
    //   185: invokevirtual getPaddingTop : ()I
    //   188: istore #8
    //   190: aload_0
    //   191: invokevirtual getPaddingBottom : ()I
    //   194: istore_3
    //   195: aload_1
    //   196: ldc_w 90.0
    //   199: invokevirtual rotate : (F)V
    //   202: aload_1
    //   203: aload_0
    //   204: invokevirtual getPaddingTop : ()I
    //   207: ineg
    //   208: i2f
    //   209: aload_0
    //   210: getfield mLastOffset : F
    //   213: fconst_1
    //   214: fadd
    //   215: fneg
    //   216: iload #6
    //   218: i2f
    //   219: fmul
    //   220: invokevirtual translate : (FF)V
    //   223: aload_0
    //   224: getfield mRightEdge : Landroid/widget/EdgeEffect;
    //   227: iload #7
    //   229: iload #8
    //   231: isub
    //   232: iload_3
    //   233: isub
    //   234: iload #6
    //   236: invokevirtual setSize : (II)V
    //   239: iload #4
    //   241: aload_0
    //   242: getfield mRightEdge : Landroid/widget/EdgeEffect;
    //   245: aload_1
    //   246: invokevirtual draw : (Landroid/graphics/Canvas;)Z
    //   249: ior
    //   250: istore_3
    //   251: aload_1
    //   252: iload_2
    //   253: invokevirtual restoreToCount : (I)V
    //   256: iload_3
    //   257: ifeq -> 264
    //   260: aload_0
    //   261: invokestatic postInvalidateOnAnimation : (Landroid/view/View;)V
    //   264: return
  }
  
  protected void drawableStateChanged() {
    super.drawableStateChanged();
    Drawable drawable = this.mMarginDrawable;
    if (drawable != null && drawable.isStateful())
      drawable.setState(getDrawableState()); 
  }
  
  public void endFakeDrag() {
    if (this.mFakeDragging) {
      if (this.mAdapter != null) {
        VelocityTracker velocityTracker = this.mVelocityTracker;
        velocityTracker.computeCurrentVelocity(1000, this.mMaximumVelocity);
        int i = (int)velocityTracker.getXVelocity(this.mActivePointerId);
        this.mPopulatePending = true;
        int j = getClientWidth();
        int k = getScrollX();
        ItemInfo itemInfo = infoForCurrentScrollPosition();
        setCurrentItemInternal(determineTargetPage(itemInfo.position, (k / j - itemInfo.offset) / itemInfo.widthFactor, i, (int)(this.mLastMotionX - this.mInitialMotionX)), true, true, i);
      } 
      endDrag();
      this.mFakeDragging = false;
      return;
    } 
    throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
  }
  
  public boolean executeKeyEvent(KeyEvent paramKeyEvent) {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual getAction : ()I
    //   4: ifne -> 118
    //   7: aload_1
    //   8: invokevirtual getKeyCode : ()I
    //   11: istore_2
    //   12: iload_2
    //   13: bipush #21
    //   15: if_icmpeq -> 92
    //   18: iload_2
    //   19: bipush #22
    //   21: if_icmpeq -> 66
    //   24: iload_2
    //   25: bipush #61
    //   27: if_icmpeq -> 33
    //   30: goto -> 118
    //   33: aload_1
    //   34: invokevirtual hasNoModifiers : ()Z
    //   37: ifeq -> 49
    //   40: aload_0
    //   41: iconst_2
    //   42: invokevirtual arrowScroll : (I)Z
    //   45: istore_3
    //   46: goto -> 120
    //   49: aload_1
    //   50: iconst_1
    //   51: invokevirtual hasModifiers : (I)Z
    //   54: ifeq -> 118
    //   57: aload_0
    //   58: iconst_1
    //   59: invokevirtual arrowScroll : (I)Z
    //   62: istore_3
    //   63: goto -> 120
    //   66: aload_1
    //   67: iconst_2
    //   68: invokevirtual hasModifiers : (I)Z
    //   71: ifeq -> 82
    //   74: aload_0
    //   75: invokevirtual pageRight : ()Z
    //   78: istore_3
    //   79: goto -> 120
    //   82: aload_0
    //   83: bipush #66
    //   85: invokevirtual arrowScroll : (I)Z
    //   88: istore_3
    //   89: goto -> 120
    //   92: aload_1
    //   93: iconst_2
    //   94: invokevirtual hasModifiers : (I)Z
    //   97: ifeq -> 108
    //   100: aload_0
    //   101: invokevirtual pageLeft : ()Z
    //   104: istore_3
    //   105: goto -> 120
    //   108: aload_0
    //   109: bipush #17
    //   111: invokevirtual arrowScroll : (I)Z
    //   114: istore_3
    //   115: goto -> 120
    //   118: iconst_0
    //   119: istore_3
    //   120: iload_3
    //   121: ireturn
  }
  
  public void fakeDragBy(float paramFloat) {
    if (this.mFakeDragging) {
      if (this.mAdapter == null)
        return; 
      this.mLastMotionX += paramFloat;
      float f1 = getScrollX() - paramFloat;
      float f2 = getClientWidth();
      paramFloat = this.mFirstOffset * f2;
      float f3 = this.mLastOffset * f2;
      ItemInfo itemInfo1 = this.mItems.get(0);
      ArrayList<ItemInfo> arrayList = this.mItems;
      ItemInfo itemInfo2 = arrayList.get(arrayList.size() - 1);
      if (itemInfo1.position != 0)
        paramFloat = itemInfo1.offset * f2; 
      if (itemInfo2.position != this.mAdapter.getCount() - 1)
        f3 = itemInfo2.offset * f2; 
      if (f1 >= paramFloat) {
        paramFloat = f1;
        if (f1 > f3)
          paramFloat = f3; 
      } 
      f3 = this.mLastMotionX;
      int i = (int)paramFloat;
      this.mLastMotionX = f3 + paramFloat - i;
      scrollTo(i, getScrollY());
      pageScrolled(i);
      long l = SystemClock.uptimeMillis();
      MotionEvent motionEvent = MotionEvent.obtain(this.mFakeDragBeginTime, l, 2, this.mLastMotionX, 0.0F, 0);
      this.mVelocityTracker.addMovement(motionEvent);
      motionEvent.recycle();
      return;
    } 
    throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
  }
  
  protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
    return new LayoutParams();
  }
  
  public ViewGroup.LayoutParams generateLayoutParams(AttributeSet paramAttributeSet) {
    return new LayoutParams(getContext(), paramAttributeSet);
  }
  
  protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams) {
    return generateDefaultLayoutParams();
  }
  
  public PagerAdapter getAdapter() {
    return this.mAdapter;
  }
  
  protected int getChildDrawingOrder(int paramInt1, int paramInt2) {
    int i = paramInt2;
    if (this.mDrawingOrder == 2)
      i = paramInt1 - 1 - paramInt2; 
    return ((LayoutParams)((View)this.mDrawingOrderedChildren.get(i)).getLayoutParams()).childIndex;
  }
  
  public int getCurrentItem() {
    return this.mCurItem;
  }
  
  public int getOffscreenPageLimit() {
    return this.mOffscreenPageLimit;
  }
  
  public int getPageMargin() {
    return this.mPageMargin;
  }
  
  ItemInfo infoForAnyChild(View paramView) {
    while (true) {
      ViewParent viewParent = paramView.getParent();
      if (viewParent != this) {
        if (viewParent != null) {
          if (!(viewParent instanceof View))
            return null; 
          paramView = (View)viewParent;
          continue;
        } 
        continue;
      } 
      return infoForChild(paramView);
    } 
  }
  
  ItemInfo infoForChild(View paramView) {
    for (byte b = 0; b < this.mItems.size(); b++) {
      ItemInfo itemInfo = this.mItems.get(b);
      if (this.mAdapter.isViewFromObject(paramView, itemInfo.object))
        return itemInfo; 
    } 
    return null;
  }
  
  ItemInfo infoForPosition(int paramInt) {
    for (byte b = 0; b < this.mItems.size(); b++) {
      ItemInfo itemInfo = this.mItems.get(b);
      if (itemInfo.position == paramInt)
        return itemInfo; 
    } 
    return null;
  }
  
  void initViewPager() {
    setWillNotDraw(false);
    setDescendantFocusability(262144);
    setFocusable(true);
    Context context = getContext();
    this.mScroller = new Scroller(context, sInterpolator);
    ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
    float f = (context.getResources().getDisplayMetrics()).density;
    this.mTouchSlop = viewConfiguration.getScaledPagingTouchSlop();
    this.mMinimumVelocity = (int)(400.0F * f);
    this.mMaximumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
    this.mLeftEdge = new EdgeEffect(context);
    this.mRightEdge = new EdgeEffect(context);
    this.mFlingDistance = (int)(25.0F * f);
    this.mCloseEnough = (int)(2.0F * f);
    this.mDefaultGutterSize = (int)(f * 16.0F);
    ViewCompat.setAccessibilityDelegate((View)this, new MyAccessibilityDelegate());
    if (ViewCompat.getImportantForAccessibility((View)this) == 0)
      ViewCompat.setImportantForAccessibility((View)this, 1); 
    ViewCompat.setOnApplyWindowInsetsListener((View)this, new OnApplyWindowInsetsListener() {
          private final Rect mTempRect = new Rect();
          
          public WindowInsetsCompat onApplyWindowInsets(View param1View, WindowInsetsCompat param1WindowInsetsCompat) {
            WindowInsetsCompat windowInsetsCompat = ViewCompat.onApplyWindowInsets(param1View, param1WindowInsetsCompat);
            if (windowInsetsCompat.isConsumed())
              return windowInsetsCompat; 
            Rect rect = this.mTempRect;
            rect.left = windowInsetsCompat.getSystemWindowInsetLeft();
            rect.top = windowInsetsCompat.getSystemWindowInsetTop();
            rect.right = windowInsetsCompat.getSystemWindowInsetRight();
            rect.bottom = windowInsetsCompat.getSystemWindowInsetBottom();
            byte b = 0;
            int i = ViewPager.this.getChildCount();
            while (b < i) {
              WindowInsetsCompat windowInsetsCompat1 = ViewCompat.dispatchApplyWindowInsets(ViewPager.this.getChildAt(b), windowInsetsCompat);
              rect.left = Math.min(windowInsetsCompat1.getSystemWindowInsetLeft(), rect.left);
              rect.top = Math.min(windowInsetsCompat1.getSystemWindowInsetTop(), rect.top);
              rect.right = Math.min(windowInsetsCompat1.getSystemWindowInsetRight(), rect.right);
              rect.bottom = Math.min(windowInsetsCompat1.getSystemWindowInsetBottom(), rect.bottom);
              b++;
            } 
            return windowInsetsCompat.replaceSystemWindowInsets(rect.left, rect.top, rect.right, rect.bottom);
          }
        });
  }
  
  public boolean isFakeDragging() {
    return this.mFakeDragging;
  }
  
  protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    this.mFirstLayout = true;
  }
  
  protected void onDetachedFromWindow() {
    removeCallbacks(this.mEndScrollRunnable);
    Scroller scroller = this.mScroller;
    if (scroller != null && !scroller.isFinished())
      this.mScroller.abortAnimation(); 
    super.onDetachedFromWindow();
  }
  
  protected void onDraw(Canvas paramCanvas) {
    super.onDraw(paramCanvas);
    if (this.mPageMargin > 0 && this.mMarginDrawable != null && this.mItems.size() > 0 && this.mAdapter != null) {
      int i = getScrollX();
      int j = getWidth();
      float f1 = this.mPageMargin;
      float f2 = j;
      float f3 = f1 / f2;
      ArrayList<ItemInfo> arrayList = this.mItems;
      byte b = 0;
      ItemInfo itemInfo = arrayList.get(0);
      f1 = itemInfo.offset;
      int k = this.mItems.size();
      int m = itemInfo.position;
      int n = ((ItemInfo)this.mItems.get(k - 1)).position;
      while (m < n) {
        ItemInfo itemInfo1;
        int i1;
        float f;
        while (true) {
          i1 = itemInfo.position;
          if (m > i1 && b < k) {
            ArrayList<ItemInfo> arrayList1 = this.mItems;
            itemInfo1 = arrayList1.get(++b);
            continue;
          } 
          break;
        } 
        if (m == i1) {
          f1 = itemInfo1.offset;
          float f4 = itemInfo1.widthFactor;
          f = (f1 + f4) * f2;
          f1 = f1 + f4 + f3;
        } else {
          float f4 = this.mAdapter.getPageWidth(m);
          f = (f1 + f4) * f2;
          f1 += f4 + f3;
        } 
        if (this.mPageMargin + f > i) {
          this.mMarginDrawable.setBounds(Math.round(f), this.mTopPageBounds, Math.round(this.mPageMargin + f), this.mBottomPageBounds);
          this.mMarginDrawable.draw(paramCanvas);
        } 
        if (f > (i + j))
          break; 
        m++;
      } 
    } 
  }
  
  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent) {
    int i = paramMotionEvent.getAction() & 0xFF;
    if (i == 3 || i == 1) {
      resetTouch();
      return false;
    } 
    if (i != 0) {
      if (this.mIsBeingDragged)
        return true; 
      if (this.mIsUnableToDrag)
        return false; 
    } 
    if (i != 0) {
      if (i != 2) {
        if (i == 6)
          onSecondaryPointerUp(paramMotionEvent); 
      } else {
        i = this.mActivePointerId;
        if (i != -1) {
          i = paramMotionEvent.findPointerIndex(i);
          float f1 = paramMotionEvent.getX(i);
          float f2 = f1 - this.mLastMotionX;
          float f3 = Math.abs(f2);
          float f4 = paramMotionEvent.getY(i);
          float f5 = Math.abs(f4 - this.mInitialMotionY);
          if (f2 != 0.0F && !isGutterDrag(this.mLastMotionX, f2) && canScroll((View)this, false, (int)f2, (int)f1, (int)f4)) {
            this.mLastMotionX = f1;
            this.mLastMotionY = f4;
            this.mIsUnableToDrag = true;
            return false;
          } 
          i = this.mTouchSlop;
          if (f3 > i && f3 * 0.5F > f5) {
            this.mIsBeingDragged = true;
            requestParentDisallowInterceptTouchEvent(true);
            setScrollState(1);
            if (f2 > 0.0F) {
              f3 = this.mInitialMotionX + this.mTouchSlop;
            } else {
              f3 = this.mInitialMotionX - this.mTouchSlop;
            } 
            this.mLastMotionX = f3;
            this.mLastMotionY = f4;
            setScrollingCacheEnabled(true);
          } else if (f5 > i) {
            this.mIsUnableToDrag = true;
          } 
          if (this.mIsBeingDragged && performDrag(f1))
            ViewCompat.postInvalidateOnAnimation((View)this); 
        } 
      } 
    } else {
      float f = paramMotionEvent.getX();
      this.mInitialMotionX = f;
      this.mLastMotionX = f;
      f = paramMotionEvent.getY();
      this.mInitialMotionY = f;
      this.mLastMotionY = f;
      this.mActivePointerId = paramMotionEvent.getPointerId(0);
      this.mIsUnableToDrag = false;
      this.mIsScrollStarted = true;
      this.mScroller.computeScrollOffset();
      if (this.mScrollState == 2 && Math.abs(this.mScroller.getFinalX() - this.mScroller.getCurrX()) > this.mCloseEnough) {
        this.mScroller.abortAnimation();
        this.mPopulatePending = false;
        populate();
        this.mIsBeingDragged = true;
        requestParentDisallowInterceptTouchEvent(true);
        setScrollState(1);
      } else {
        completeScroll(false);
        this.mIsBeingDragged = false;
      } 
    } 
    if (this.mVelocityTracker == null)
      this.mVelocityTracker = VelocityTracker.obtain(); 
    this.mVelocityTracker.addMovement(paramMotionEvent);
    return this.mIsBeingDragged;
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    int i = getChildCount();
    int j = paramInt3 - paramInt1;
    int k = paramInt4 - paramInt2;
    paramInt2 = getPaddingLeft();
    paramInt1 = getPaddingTop();
    paramInt4 = getPaddingRight();
    paramInt3 = getPaddingBottom();
    int m = getScrollX();
    int n = 0;
    int i1;
    for (i1 = 0; n < i; i1 = i6) {
      View view = getChildAt(n);
      int i2 = paramInt2;
      int i3 = paramInt1;
      int i4 = paramInt4;
      int i5 = paramInt3;
      int i6 = i1;
      if (view.getVisibility() != 8) {
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        i2 = paramInt2;
        i3 = paramInt1;
        i4 = paramInt4;
        i5 = paramInt3;
        i6 = i1;
        if (layoutParams.isDecor) {
          i5 = layoutParams.gravity;
          i6 = i5 & 0x7;
          i4 = i5 & 0x70;
          if (i6 != 1) {
            if (i6 != 3) {
              if (i6 != 5) {
                i6 = paramInt2;
                i5 = paramInt2;
                paramInt2 = i6;
              } else {
                i6 = j - paramInt4 - view.getMeasuredWidth();
                paramInt4 += view.getMeasuredWidth();
                i5 = i6;
              } 
            } else {
              i6 = view.getMeasuredWidth() + paramInt2;
              i5 = paramInt2;
              paramInt2 = i6;
            } 
          } else {
            i6 = Math.max((j - view.getMeasuredWidth()) / 2, paramInt2);
            i5 = i6;
          } 
          if (i4 != 16) {
            if (i4 != 48) {
              if (i4 != 80) {
                i4 = paramInt1;
                i6 = paramInt1;
                paramInt1 = i4;
              } else {
                i6 = k - paramInt3 - view.getMeasuredHeight();
                paramInt3 += view.getMeasuredHeight();
              } 
            } else {
              i4 = view.getMeasuredHeight() + paramInt1;
              i6 = paramInt1;
              paramInt1 = i4;
            } 
          } else {
            i6 = Math.max((k - view.getMeasuredHeight()) / 2, paramInt1);
          } 
          i5 += m;
          view.layout(i5, i6, view.getMeasuredWidth() + i5, i6 + view.getMeasuredHeight());
          i6 = i1 + 1;
          i5 = paramInt3;
          i4 = paramInt4;
          i3 = paramInt1;
          i2 = paramInt2;
        } 
      } 
      n++;
      paramInt2 = i2;
      paramInt1 = i3;
      paramInt4 = i4;
      paramInt3 = i5;
    } 
    for (byte b = 0; b < i; b++) {
      View view = getChildAt(b);
      if (view.getVisibility() != 8) {
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        if (!layoutParams.isDecor) {
          ItemInfo itemInfo = infoForChild(view);
          if (itemInfo != null) {
            float f = (j - paramInt2 - paramInt4);
            n = (int)(itemInfo.offset * f) + paramInt2;
            if (layoutParams.needsMeasure) {
              layoutParams.needsMeasure = false;
              view.measure(View.MeasureSpec.makeMeasureSpec((int)(f * layoutParams.widthFactor), 1073741824), View.MeasureSpec.makeMeasureSpec(k - paramInt1 - paramInt3, 1073741824));
            } 
            view.layout(n, paramInt1, view.getMeasuredWidth() + n, view.getMeasuredHeight() + paramInt1);
          } 
        } 
      } 
    } 
    this.mTopPageBounds = paramInt1;
    this.mBottomPageBounds = k - paramInt3;
    this.mDecorChildCount = i1;
    if (this.mFirstLayout)
      scrollToItem(this.mCurItem, false, 0, false); 
    this.mFirstLayout = false;
  }
  
  protected void onMeasure(int paramInt1, int paramInt2) {
    // Byte code:
    //   0: iconst_0
    //   1: istore_3
    //   2: aload_0
    //   3: iconst_0
    //   4: iload_1
    //   5: invokestatic getDefaultSize : (II)I
    //   8: iconst_0
    //   9: iload_2
    //   10: invokestatic getDefaultSize : (II)I
    //   13: invokevirtual setMeasuredDimension : (II)V
    //   16: aload_0
    //   17: invokevirtual getMeasuredWidth : ()I
    //   20: istore_1
    //   21: aload_0
    //   22: iload_1
    //   23: bipush #10
    //   25: idiv
    //   26: aload_0
    //   27: getfield mDefaultGutterSize : I
    //   30: invokestatic min : (II)I
    //   33: putfield mGutterSize : I
    //   36: iload_1
    //   37: aload_0
    //   38: invokevirtual getPaddingLeft : ()I
    //   41: isub
    //   42: aload_0
    //   43: invokevirtual getPaddingRight : ()I
    //   46: isub
    //   47: istore_1
    //   48: aload_0
    //   49: invokevirtual getMeasuredHeight : ()I
    //   52: aload_0
    //   53: invokevirtual getPaddingTop : ()I
    //   56: isub
    //   57: aload_0
    //   58: invokevirtual getPaddingBottom : ()I
    //   61: isub
    //   62: istore_2
    //   63: aload_0
    //   64: invokevirtual getChildCount : ()I
    //   67: istore #4
    //   69: iconst_0
    //   70: istore #5
    //   72: iconst_1
    //   73: istore #6
    //   75: ldc_w 1073741824
    //   78: istore #7
    //   80: iload #5
    //   82: iload #4
    //   84: if_icmpge -> 434
    //   87: aload_0
    //   88: iload #5
    //   90: invokevirtual getChildAt : (I)Landroid/view/View;
    //   93: astore #8
    //   95: iload_1
    //   96: istore #9
    //   98: iload_2
    //   99: istore #10
    //   101: aload #8
    //   103: invokevirtual getVisibility : ()I
    //   106: bipush #8
    //   108: if_icmpeq -> 422
    //   111: aload #8
    //   113: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   116: checkcast android/support/v4/view/ViewPager$LayoutParams
    //   119: astore #11
    //   121: iload_1
    //   122: istore #9
    //   124: iload_2
    //   125: istore #10
    //   127: aload #11
    //   129: ifnull -> 422
    //   132: iload_1
    //   133: istore #9
    //   135: iload_2
    //   136: istore #10
    //   138: aload #11
    //   140: getfield isDecor : Z
    //   143: ifeq -> 422
    //   146: aload #11
    //   148: getfield gravity : I
    //   151: istore #10
    //   153: iload #10
    //   155: bipush #7
    //   157: iand
    //   158: istore #9
    //   160: iload #10
    //   162: bipush #112
    //   164: iand
    //   165: istore #10
    //   167: iload #10
    //   169: bipush #48
    //   171: if_icmpeq -> 190
    //   174: iload #10
    //   176: bipush #80
    //   178: if_icmpne -> 184
    //   181: goto -> 190
    //   184: iconst_0
    //   185: istore #12
    //   187: goto -> 193
    //   190: iconst_1
    //   191: istore #12
    //   193: iload #6
    //   195: istore #13
    //   197: iload #9
    //   199: iconst_3
    //   200: if_icmpeq -> 219
    //   203: iload #9
    //   205: iconst_5
    //   206: if_icmpne -> 216
    //   209: iload #6
    //   211: istore #13
    //   213: goto -> 219
    //   216: iconst_0
    //   217: istore #13
    //   219: ldc_w -2147483648
    //   222: istore #9
    //   224: iload #12
    //   226: ifeq -> 237
    //   229: ldc_w 1073741824
    //   232: istore #10
    //   234: goto -> 262
    //   237: iload #9
    //   239: istore #10
    //   241: iload #13
    //   243: ifeq -> 262
    //   246: ldc_w 1073741824
    //   249: istore #6
    //   251: iload #9
    //   253: istore #10
    //   255: iload #6
    //   257: istore #9
    //   259: goto -> 267
    //   262: ldc_w -2147483648
    //   265: istore #9
    //   267: aload #11
    //   269: getfield width : I
    //   272: istore #6
    //   274: iload #6
    //   276: bipush #-2
    //   278: if_icmpeq -> 309
    //   281: iload #6
    //   283: iconst_m1
    //   284: if_icmpeq -> 294
    //   287: iload #6
    //   289: istore #10
    //   291: goto -> 297
    //   294: iload_1
    //   295: istore #10
    //   297: ldc_w 1073741824
    //   300: istore #14
    //   302: iload #10
    //   304: istore #6
    //   306: goto -> 316
    //   309: iload_1
    //   310: istore #6
    //   312: iload #10
    //   314: istore #14
    //   316: aload #11
    //   318: getfield height : I
    //   321: istore #10
    //   323: iload #10
    //   325: bipush #-2
    //   327: if_icmpeq -> 349
    //   330: iload #10
    //   332: iconst_m1
    //   333: if_icmpeq -> 343
    //   336: iload #10
    //   338: istore #9
    //   340: goto -> 360
    //   343: iload_2
    //   344: istore #9
    //   346: goto -> 360
    //   349: iload_2
    //   350: istore #10
    //   352: iload #9
    //   354: istore #7
    //   356: iload #10
    //   358: istore #9
    //   360: aload #8
    //   362: iload #6
    //   364: iload #14
    //   366: invokestatic makeMeasureSpec : (II)I
    //   369: iload #9
    //   371: iload #7
    //   373: invokestatic makeMeasureSpec : (II)I
    //   376: invokevirtual measure : (II)V
    //   379: iload #12
    //   381: ifeq -> 399
    //   384: iload_2
    //   385: aload #8
    //   387: invokevirtual getMeasuredHeight : ()I
    //   390: isub
    //   391: istore #10
    //   393: iload_1
    //   394: istore #9
    //   396: goto -> 422
    //   399: iload_1
    //   400: istore #9
    //   402: iload_2
    //   403: istore #10
    //   405: iload #13
    //   407: ifeq -> 422
    //   410: iload_1
    //   411: aload #8
    //   413: invokevirtual getMeasuredWidth : ()I
    //   416: isub
    //   417: istore #9
    //   419: iload_2
    //   420: istore #10
    //   422: iinc #5, 1
    //   425: iload #9
    //   427: istore_1
    //   428: iload #10
    //   430: istore_2
    //   431: goto -> 72
    //   434: aload_0
    //   435: iload_1
    //   436: ldc_w 1073741824
    //   439: invokestatic makeMeasureSpec : (II)I
    //   442: putfield mChildWidthMeasureSpec : I
    //   445: aload_0
    //   446: iload_2
    //   447: ldc_w 1073741824
    //   450: invokestatic makeMeasureSpec : (II)I
    //   453: putfield mChildHeightMeasureSpec : I
    //   456: aload_0
    //   457: iconst_1
    //   458: putfield mInLayout : Z
    //   461: aload_0
    //   462: invokevirtual populate : ()V
    //   465: aload_0
    //   466: iconst_0
    //   467: putfield mInLayout : Z
    //   470: aload_0
    //   471: invokevirtual getChildCount : ()I
    //   474: istore #9
    //   476: iload_3
    //   477: istore_2
    //   478: iload_2
    //   479: iload #9
    //   481: if_icmpge -> 554
    //   484: aload_0
    //   485: iload_2
    //   486: invokevirtual getChildAt : (I)Landroid/view/View;
    //   489: astore #11
    //   491: aload #11
    //   493: invokevirtual getVisibility : ()I
    //   496: bipush #8
    //   498: if_icmpeq -> 548
    //   501: aload #11
    //   503: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   506: checkcast android/support/v4/view/ViewPager$LayoutParams
    //   509: astore #8
    //   511: aload #8
    //   513: ifnull -> 524
    //   516: aload #8
    //   518: getfield isDecor : Z
    //   521: ifne -> 548
    //   524: aload #11
    //   526: iload_1
    //   527: i2f
    //   528: aload #8
    //   530: getfield widthFactor : F
    //   533: fmul
    //   534: f2i
    //   535: ldc_w 1073741824
    //   538: invokestatic makeMeasureSpec : (II)I
    //   541: aload_0
    //   542: getfield mChildHeightMeasureSpec : I
    //   545: invokevirtual measure : (II)V
    //   548: iinc #2, 1
    //   551: goto -> 478
    //   554: return
  }
  
  @CallSuper
  protected void onPageScrolled(int paramInt1, float paramFloat, int paramInt2) {
    int i = this.mDecorChildCount;
    boolean bool = false;
    if (i > 0) {
      int j = getScrollX();
      i = getPaddingLeft();
      int k = getPaddingRight();
      int m = getWidth();
      int n = getChildCount();
      for (byte b = 0; b < n; b++) {
        View view = getChildAt(b);
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        if (layoutParams.isDecor) {
          int i1 = layoutParams.gravity & 0x7;
          if (i1 != 1) {
            if (i1 != 3) {
              if (i1 != 5) {
                int i2 = i;
                i1 = i;
                i = i2;
              } else {
                i1 = m - k - view.getMeasuredWidth();
                k += view.getMeasuredWidth();
              } 
            } else {
              int i2 = view.getWidth() + i;
              i1 = i;
              i = i2;
            } 
          } else {
            i1 = Math.max((m - view.getMeasuredWidth()) / 2, i);
          } 
          i1 = i1 + j - view.getLeft();
          if (i1 != 0)
            view.offsetLeftAndRight(i1); 
        } 
      } 
    } 
    dispatchOnPageScrolled(paramInt1, paramFloat, paramInt2);
    if (this.mPageTransformer != null) {
      i = getScrollX();
      paramInt2 = getChildCount();
      for (paramInt1 = bool; paramInt1 < paramInt2; paramInt1++) {
        View view = getChildAt(paramInt1);
        if (!((LayoutParams)view.getLayoutParams()).isDecor) {
          paramFloat = (view.getLeft() - i) / getClientWidth();
          this.mPageTransformer.transformPage(view, paramFloat);
        } 
      } 
    } 
    this.mCalledSuper = true;
  }
  
  protected boolean onRequestFocusInDescendants(int paramInt, Rect paramRect) {
    byte b;
    int i = getChildCount();
    int j = -1;
    if ((paramInt & 0x2) != 0) {
      j = i;
      i = 0;
      b = 1;
    } else {
      i--;
      b = -1;
    } 
    while (i != j) {
      View view = getChildAt(i);
      if (view.getVisibility() == 0) {
        ItemInfo itemInfo = infoForChild(view);
        if (itemInfo != null && itemInfo.position == this.mCurItem && view.requestFocus(paramInt, paramRect))
          return true; 
      } 
      i += b;
    } 
    return false;
  }
  
  public void onRestoreInstanceState(Parcelable paramParcelable) {
    if (!(paramParcelable instanceof SavedState)) {
      super.onRestoreInstanceState(paramParcelable);
      return;
    } 
    paramParcelable = paramParcelable;
    super.onRestoreInstanceState(paramParcelable.getSuperState());
    PagerAdapter pagerAdapter = this.mAdapter;
    if (pagerAdapter != null) {
      pagerAdapter.restoreState(((SavedState)paramParcelable).adapterState, ((SavedState)paramParcelable).loader);
      setCurrentItemInternal(((SavedState)paramParcelable).position, false, true);
    } else {
      this.mRestoredCurItem = ((SavedState)paramParcelable).position;
      this.mRestoredAdapterState = ((SavedState)paramParcelable).adapterState;
      this.mRestoredClassLoader = ((SavedState)paramParcelable).loader;
    } 
  }
  
  public Parcelable onSaveInstanceState() {
    SavedState savedState = new SavedState(super.onSaveInstanceState());
    savedState.position = this.mCurItem;
    PagerAdapter pagerAdapter = this.mAdapter;
    if (pagerAdapter != null)
      savedState.adapterState = pagerAdapter.saveState(); 
    return savedState;
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if (paramInt1 != paramInt3) {
      paramInt2 = this.mPageMargin;
      recomputeScrollPosition(paramInt1, paramInt3, paramInt2, paramInt2);
    } 
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mFakeDragging : Z
    //   4: ifeq -> 9
    //   7: iconst_1
    //   8: ireturn
    //   9: aload_1
    //   10: invokevirtual getAction : ()I
    //   13: istore_2
    //   14: iconst_0
    //   15: istore_3
    //   16: iload_2
    //   17: ifne -> 29
    //   20: aload_1
    //   21: invokevirtual getEdgeFlags : ()I
    //   24: ifeq -> 29
    //   27: iconst_0
    //   28: ireturn
    //   29: aload_0
    //   30: getfield mAdapter : Landroid/support/v4/view/PagerAdapter;
    //   33: astore #4
    //   35: aload #4
    //   37: ifnull -> 610
    //   40: aload #4
    //   42: invokevirtual getCount : ()I
    //   45: ifne -> 51
    //   48: goto -> 610
    //   51: aload_0
    //   52: getfield mVelocityTracker : Landroid/view/VelocityTracker;
    //   55: ifnonnull -> 65
    //   58: aload_0
    //   59: invokestatic obtain : ()Landroid/view/VelocityTracker;
    //   62: putfield mVelocityTracker : Landroid/view/VelocityTracker;
    //   65: aload_0
    //   66: getfield mVelocityTracker : Landroid/view/VelocityTracker;
    //   69: aload_1
    //   70: invokevirtual addMovement : (Landroid/view/MotionEvent;)V
    //   73: aload_1
    //   74: invokevirtual getAction : ()I
    //   77: sipush #255
    //   80: iand
    //   81: istore_2
    //   82: iload_2
    //   83: ifeq -> 539
    //   86: iload_2
    //   87: iconst_1
    //   88: if_icmpeq -> 396
    //   91: iload_2
    //   92: iconst_2
    //   93: if_icmpeq -> 191
    //   96: iload_2
    //   97: iconst_3
    //   98: if_icmpeq -> 165
    //   101: iload_2
    //   102: iconst_5
    //   103: if_icmpeq -> 139
    //   106: iload_2
    //   107: bipush #6
    //   109: if_icmpeq -> 115
    //   112: goto -> 600
    //   115: aload_0
    //   116: aload_1
    //   117: invokespecial onSecondaryPointerUp : (Landroid/view/MotionEvent;)V
    //   120: aload_0
    //   121: aload_1
    //   122: aload_1
    //   123: aload_0
    //   124: getfield mActivePointerId : I
    //   127: invokevirtual findPointerIndex : (I)I
    //   130: invokevirtual getX : (I)F
    //   133: putfield mLastMotionX : F
    //   136: goto -> 600
    //   139: aload_1
    //   140: invokevirtual getActionIndex : ()I
    //   143: istore_2
    //   144: aload_0
    //   145: aload_1
    //   146: iload_2
    //   147: invokevirtual getX : (I)F
    //   150: putfield mLastMotionX : F
    //   153: aload_0
    //   154: aload_1
    //   155: iload_2
    //   156: invokevirtual getPointerId : (I)I
    //   159: putfield mActivePointerId : I
    //   162: goto -> 600
    //   165: aload_0
    //   166: getfield mIsBeingDragged : Z
    //   169: ifeq -> 600
    //   172: aload_0
    //   173: aload_0
    //   174: getfield mCurItem : I
    //   177: iconst_1
    //   178: iconst_0
    //   179: iconst_0
    //   180: invokespecial scrollToItem : (IZIZ)V
    //   183: aload_0
    //   184: invokespecial resetTouch : ()Z
    //   187: istore_3
    //   188: goto -> 600
    //   191: aload_0
    //   192: getfield mIsBeingDragged : Z
    //   195: ifne -> 367
    //   198: aload_1
    //   199: aload_0
    //   200: getfield mActivePointerId : I
    //   203: invokevirtual findPointerIndex : (I)I
    //   206: istore_2
    //   207: iload_2
    //   208: iconst_m1
    //   209: if_icmpne -> 220
    //   212: aload_0
    //   213: invokespecial resetTouch : ()Z
    //   216: istore_3
    //   217: goto -> 600
    //   220: aload_1
    //   221: iload_2
    //   222: invokevirtual getX : (I)F
    //   225: fstore #5
    //   227: fload #5
    //   229: aload_0
    //   230: getfield mLastMotionX : F
    //   233: fsub
    //   234: invokestatic abs : (F)F
    //   237: fstore #6
    //   239: aload_1
    //   240: iload_2
    //   241: invokevirtual getY : (I)F
    //   244: fstore #7
    //   246: fload #7
    //   248: aload_0
    //   249: getfield mLastMotionY : F
    //   252: fsub
    //   253: invokestatic abs : (F)F
    //   256: fstore #8
    //   258: fload #6
    //   260: aload_0
    //   261: getfield mTouchSlop : I
    //   264: i2f
    //   265: fcmpl
    //   266: ifle -> 367
    //   269: fload #6
    //   271: fload #8
    //   273: fcmpl
    //   274: ifle -> 367
    //   277: aload_0
    //   278: iconst_1
    //   279: putfield mIsBeingDragged : Z
    //   282: aload_0
    //   283: iconst_1
    //   284: invokespecial requestParentDisallowInterceptTouchEvent : (Z)V
    //   287: aload_0
    //   288: getfield mInitialMotionX : F
    //   291: fstore #6
    //   293: fload #5
    //   295: fload #6
    //   297: fsub
    //   298: fconst_0
    //   299: fcmpl
    //   300: ifle -> 316
    //   303: fload #6
    //   305: aload_0
    //   306: getfield mTouchSlop : I
    //   309: i2f
    //   310: fadd
    //   311: fstore #5
    //   313: goto -> 326
    //   316: fload #6
    //   318: aload_0
    //   319: getfield mTouchSlop : I
    //   322: i2f
    //   323: fsub
    //   324: fstore #5
    //   326: aload_0
    //   327: fload #5
    //   329: putfield mLastMotionX : F
    //   332: aload_0
    //   333: fload #7
    //   335: putfield mLastMotionY : F
    //   338: aload_0
    //   339: iconst_1
    //   340: invokevirtual setScrollState : (I)V
    //   343: aload_0
    //   344: iconst_1
    //   345: invokespecial setScrollingCacheEnabled : (Z)V
    //   348: aload_0
    //   349: invokevirtual getParent : ()Landroid/view/ViewParent;
    //   352: astore #4
    //   354: aload #4
    //   356: ifnull -> 367
    //   359: aload #4
    //   361: iconst_1
    //   362: invokeinterface requestDisallowInterceptTouchEvent : (Z)V
    //   367: aload_0
    //   368: getfield mIsBeingDragged : Z
    //   371: ifeq -> 600
    //   374: iconst_0
    //   375: aload_0
    //   376: aload_1
    //   377: aload_1
    //   378: aload_0
    //   379: getfield mActivePointerId : I
    //   382: invokevirtual findPointerIndex : (I)I
    //   385: invokevirtual getX : (I)F
    //   388: invokespecial performDrag : (F)Z
    //   391: ior
    //   392: istore_3
    //   393: goto -> 600
    //   396: aload_0
    //   397: getfield mIsBeingDragged : Z
    //   400: ifeq -> 600
    //   403: aload_0
    //   404: getfield mVelocityTracker : Landroid/view/VelocityTracker;
    //   407: astore #4
    //   409: aload #4
    //   411: sipush #1000
    //   414: aload_0
    //   415: getfield mMaximumVelocity : I
    //   418: i2f
    //   419: invokevirtual computeCurrentVelocity : (IF)V
    //   422: aload #4
    //   424: aload_0
    //   425: getfield mActivePointerId : I
    //   428: invokevirtual getXVelocity : (I)F
    //   431: f2i
    //   432: istore_2
    //   433: aload_0
    //   434: iconst_1
    //   435: putfield mPopulatePending : Z
    //   438: aload_0
    //   439: invokespecial getClientWidth : ()I
    //   442: istore #9
    //   444: aload_0
    //   445: invokevirtual getScrollX : ()I
    //   448: istore #10
    //   450: aload_0
    //   451: invokespecial infoForCurrentScrollPosition : ()Landroid/support/v4/view/ViewPager$ItemInfo;
    //   454: astore #4
    //   456: aload_0
    //   457: getfield mPageMargin : I
    //   460: i2f
    //   461: fstore #7
    //   463: iload #9
    //   465: i2f
    //   466: fstore #5
    //   468: fload #7
    //   470: fload #5
    //   472: fdiv
    //   473: fstore #7
    //   475: aload_0
    //   476: aload_0
    //   477: aload #4
    //   479: getfield position : I
    //   482: iload #10
    //   484: i2f
    //   485: fload #5
    //   487: fdiv
    //   488: aload #4
    //   490: getfield offset : F
    //   493: fsub
    //   494: aload #4
    //   496: getfield widthFactor : F
    //   499: fload #7
    //   501: fadd
    //   502: fdiv
    //   503: iload_2
    //   504: aload_1
    //   505: aload_1
    //   506: aload_0
    //   507: getfield mActivePointerId : I
    //   510: invokevirtual findPointerIndex : (I)I
    //   513: invokevirtual getX : (I)F
    //   516: aload_0
    //   517: getfield mInitialMotionX : F
    //   520: fsub
    //   521: f2i
    //   522: invokespecial determineTargetPage : (IFII)I
    //   525: iconst_1
    //   526: iconst_1
    //   527: iload_2
    //   528: invokevirtual setCurrentItemInternal : (IZZI)V
    //   531: aload_0
    //   532: invokespecial resetTouch : ()Z
    //   535: istore_3
    //   536: goto -> 600
    //   539: aload_0
    //   540: getfield mScroller : Landroid/widget/Scroller;
    //   543: invokevirtual abortAnimation : ()V
    //   546: aload_0
    //   547: iconst_0
    //   548: putfield mPopulatePending : Z
    //   551: aload_0
    //   552: invokevirtual populate : ()V
    //   555: aload_1
    //   556: invokevirtual getX : ()F
    //   559: fstore #5
    //   561: aload_0
    //   562: fload #5
    //   564: putfield mInitialMotionX : F
    //   567: aload_0
    //   568: fload #5
    //   570: putfield mLastMotionX : F
    //   573: aload_1
    //   574: invokevirtual getY : ()F
    //   577: fstore #5
    //   579: aload_0
    //   580: fload #5
    //   582: putfield mInitialMotionY : F
    //   585: aload_0
    //   586: fload #5
    //   588: putfield mLastMotionY : F
    //   591: aload_0
    //   592: aload_1
    //   593: iconst_0
    //   594: invokevirtual getPointerId : (I)I
    //   597: putfield mActivePointerId : I
    //   600: iload_3
    //   601: ifeq -> 608
    //   604: aload_0
    //   605: invokestatic postInvalidateOnAnimation : (Landroid/view/View;)V
    //   608: iconst_1
    //   609: ireturn
    //   610: iconst_0
    //   611: ireturn
  }
  
  boolean pageLeft() {
    int i = this.mCurItem;
    if (i > 0) {
      setCurrentItem(i - 1, true);
      return true;
    } 
    return false;
  }
  
  boolean pageRight() {
    PagerAdapter pagerAdapter = this.mAdapter;
    if (pagerAdapter != null && this.mCurItem < pagerAdapter.getCount() - 1) {
      setCurrentItem(this.mCurItem + 1, true);
      return true;
    } 
    return false;
  }
  
  void populate() {
    populate(this.mCurItem);
  }
  
  void populate(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mCurItem : I
    //   4: istore_2
    //   5: iload_2
    //   6: iload_1
    //   7: if_icmpeq -> 24
    //   10: aload_0
    //   11: iload_2
    //   12: invokevirtual infoForPosition : (I)Landroid/support/v4/view/ViewPager$ItemInfo;
    //   15: astore_3
    //   16: aload_0
    //   17: iload_1
    //   18: putfield mCurItem : I
    //   21: goto -> 26
    //   24: aconst_null
    //   25: astore_3
    //   26: aload_0
    //   27: getfield mAdapter : Landroid/support/v4/view/PagerAdapter;
    //   30: ifnonnull -> 38
    //   33: aload_0
    //   34: invokespecial sortChildDrawingOrder : ()V
    //   37: return
    //   38: aload_0
    //   39: getfield mPopulatePending : Z
    //   42: ifeq -> 50
    //   45: aload_0
    //   46: invokespecial sortChildDrawingOrder : ()V
    //   49: return
    //   50: aload_0
    //   51: invokevirtual getWindowToken : ()Landroid/os/IBinder;
    //   54: ifnonnull -> 58
    //   57: return
    //   58: aload_0
    //   59: getfield mAdapter : Landroid/support/v4/view/PagerAdapter;
    //   62: aload_0
    //   63: invokevirtual startUpdate : (Landroid/view/ViewGroup;)V
    //   66: aload_0
    //   67: getfield mOffscreenPageLimit : I
    //   70: istore_1
    //   71: iconst_0
    //   72: aload_0
    //   73: getfield mCurItem : I
    //   76: iload_1
    //   77: isub
    //   78: invokestatic max : (II)I
    //   81: istore #4
    //   83: aload_0
    //   84: getfield mAdapter : Landroid/support/v4/view/PagerAdapter;
    //   87: invokevirtual getCount : ()I
    //   90: istore #5
    //   92: iload #5
    //   94: iconst_1
    //   95: isub
    //   96: aload_0
    //   97: getfield mCurItem : I
    //   100: iload_1
    //   101: iadd
    //   102: invokestatic min : (II)I
    //   105: istore #6
    //   107: iload #5
    //   109: aload_0
    //   110: getfield mExpectedAdapterCount : I
    //   113: if_icmpne -> 1211
    //   116: iconst_0
    //   117: istore_1
    //   118: iload_1
    //   119: aload_0
    //   120: getfield mItems : Ljava/util/ArrayList;
    //   123: invokevirtual size : ()I
    //   126: if_icmpge -> 175
    //   129: aload_0
    //   130: getfield mItems : Ljava/util/ArrayList;
    //   133: iload_1
    //   134: invokevirtual get : (I)Ljava/lang/Object;
    //   137: checkcast android/support/v4/view/ViewPager$ItemInfo
    //   140: astore #7
    //   142: aload #7
    //   144: getfield position : I
    //   147: istore_2
    //   148: aload_0
    //   149: getfield mCurItem : I
    //   152: istore #8
    //   154: iload_2
    //   155: iload #8
    //   157: if_icmplt -> 169
    //   160: iload_2
    //   161: iload #8
    //   163: if_icmpne -> 175
    //   166: goto -> 178
    //   169: iinc #1, 1
    //   172: goto -> 118
    //   175: aconst_null
    //   176: astore #7
    //   178: aload #7
    //   180: astore #9
    //   182: aload #7
    //   184: ifnonnull -> 207
    //   187: aload #7
    //   189: astore #9
    //   191: iload #5
    //   193: ifle -> 207
    //   196: aload_0
    //   197: aload_0
    //   198: getfield mCurItem : I
    //   201: iload_1
    //   202: invokevirtual addNewItem : (II)Landroid/support/v4/view/ViewPager$ItemInfo;
    //   205: astore #9
    //   207: aload #9
    //   209: ifnull -> 971
    //   212: iload_1
    //   213: iconst_1
    //   214: isub
    //   215: istore_2
    //   216: iload_2
    //   217: iflt -> 236
    //   220: aload_0
    //   221: getfield mItems : Ljava/util/ArrayList;
    //   224: iload_2
    //   225: invokevirtual get : (I)Ljava/lang/Object;
    //   228: checkcast android/support/v4/view/ViewPager$ItemInfo
    //   231: astore #7
    //   233: goto -> 239
    //   236: aconst_null
    //   237: astore #7
    //   239: aload_0
    //   240: invokespecial getClientWidth : ()I
    //   243: istore #10
    //   245: iload #10
    //   247: ifgt -> 256
    //   250: fconst_0
    //   251: fstore #11
    //   253: goto -> 275
    //   256: fconst_2
    //   257: aload #9
    //   259: getfield widthFactor : F
    //   262: fsub
    //   263: aload_0
    //   264: invokevirtual getPaddingLeft : ()I
    //   267: i2f
    //   268: iload #10
    //   270: i2f
    //   271: fdiv
    //   272: fadd
    //   273: fstore #11
    //   275: aload_0
    //   276: getfield mCurItem : I
    //   279: iconst_1
    //   280: isub
    //   281: istore #12
    //   283: fconst_0
    //   284: fstore #13
    //   286: iload #12
    //   288: iflt -> 579
    //   291: fload #13
    //   293: fload #11
    //   295: fcmpl
    //   296: iflt -> 424
    //   299: iload #12
    //   301: iload #4
    //   303: if_icmpge -> 424
    //   306: aload #7
    //   308: ifnonnull -> 314
    //   311: goto -> 579
    //   314: iload_1
    //   315: istore #14
    //   317: iload_2
    //   318: istore #8
    //   320: aload #7
    //   322: astore #15
    //   324: fload #13
    //   326: fstore #16
    //   328: iload #12
    //   330: aload #7
    //   332: getfield position : I
    //   335: if_icmpne -> 559
    //   338: iload_1
    //   339: istore #14
    //   341: iload_2
    //   342: istore #8
    //   344: aload #7
    //   346: astore #15
    //   348: fload #13
    //   350: fstore #16
    //   352: aload #7
    //   354: getfield scrolling : Z
    //   357: ifne -> 559
    //   360: aload_0
    //   361: getfield mItems : Ljava/util/ArrayList;
    //   364: iload_2
    //   365: invokevirtual remove : (I)Ljava/lang/Object;
    //   368: pop
    //   369: aload_0
    //   370: getfield mAdapter : Landroid/support/v4/view/PagerAdapter;
    //   373: aload_0
    //   374: iload #12
    //   376: aload #7
    //   378: getfield object : Ljava/lang/Object;
    //   381: invokevirtual destroyItem : (Landroid/view/ViewGroup;ILjava/lang/Object;)V
    //   384: iinc #2, -1
    //   387: iinc #1, -1
    //   390: iload_1
    //   391: istore #8
    //   393: iload_2
    //   394: istore #14
    //   396: fload #13
    //   398: fstore #16
    //   400: iload_2
    //   401: iflt -> 540
    //   404: aload_0
    //   405: getfield mItems : Ljava/util/ArrayList;
    //   408: iload_2
    //   409: invokevirtual get : (I)Ljava/lang/Object;
    //   412: checkcast android/support/v4/view/ViewPager$ItemInfo
    //   415: astore #7
    //   417: fload #13
    //   419: fstore #16
    //   421: goto -> 549
    //   424: aload #7
    //   426: ifnull -> 486
    //   429: iload #12
    //   431: aload #7
    //   433: getfield position : I
    //   436: if_icmpne -> 486
    //   439: fload #13
    //   441: aload #7
    //   443: getfield widthFactor : F
    //   446: fadd
    //   447: fstore #13
    //   449: iinc #2, -1
    //   452: iload_1
    //   453: istore #8
    //   455: iload_2
    //   456: istore #14
    //   458: fload #13
    //   460: fstore #16
    //   462: iload_2
    //   463: iflt -> 540
    //   466: aload_0
    //   467: getfield mItems : Ljava/util/ArrayList;
    //   470: iload_2
    //   471: invokevirtual get : (I)Ljava/lang/Object;
    //   474: checkcast android/support/v4/view/ViewPager$ItemInfo
    //   477: astore #7
    //   479: fload #13
    //   481: fstore #16
    //   483: goto -> 549
    //   486: fload #13
    //   488: aload_0
    //   489: iload #12
    //   491: iload_2
    //   492: iconst_1
    //   493: iadd
    //   494: invokevirtual addNewItem : (II)Landroid/support/v4/view/ViewPager$ItemInfo;
    //   497: getfield widthFactor : F
    //   500: fadd
    //   501: fstore #13
    //   503: iinc #1, 1
    //   506: iload_1
    //   507: istore #8
    //   509: iload_2
    //   510: istore #14
    //   512: fload #13
    //   514: fstore #16
    //   516: iload_2
    //   517: iflt -> 540
    //   520: aload_0
    //   521: getfield mItems : Ljava/util/ArrayList;
    //   524: iload_2
    //   525: invokevirtual get : (I)Ljava/lang/Object;
    //   528: checkcast android/support/v4/view/ViewPager$ItemInfo
    //   531: astore #7
    //   533: fload #13
    //   535: fstore #16
    //   537: goto -> 549
    //   540: aconst_null
    //   541: astore #7
    //   543: iload #14
    //   545: istore_2
    //   546: iload #8
    //   548: istore_1
    //   549: aload #7
    //   551: astore #15
    //   553: iload_2
    //   554: istore #8
    //   556: iload_1
    //   557: istore #14
    //   559: iinc #12, -1
    //   562: iload #14
    //   564: istore_1
    //   565: iload #8
    //   567: istore_2
    //   568: aload #15
    //   570: astore #7
    //   572: fload #16
    //   574: fstore #13
    //   576: goto -> 286
    //   579: aload #9
    //   581: getfield widthFactor : F
    //   584: fstore #13
    //   586: iload_1
    //   587: iconst_1
    //   588: iadd
    //   589: istore #8
    //   591: fload #13
    //   593: fconst_2
    //   594: fcmpg
    //   595: ifge -> 963
    //   598: iload #8
    //   600: aload_0
    //   601: getfield mItems : Ljava/util/ArrayList;
    //   604: invokevirtual size : ()I
    //   607: if_icmpge -> 627
    //   610: aload_0
    //   611: getfield mItems : Ljava/util/ArrayList;
    //   614: iload #8
    //   616: invokevirtual get : (I)Ljava/lang/Object;
    //   619: checkcast android/support/v4/view/ViewPager$ItemInfo
    //   622: astore #7
    //   624: goto -> 630
    //   627: aconst_null
    //   628: astore #7
    //   630: iload #10
    //   632: ifgt -> 641
    //   635: fconst_0
    //   636: fstore #11
    //   638: goto -> 654
    //   641: aload_0
    //   642: invokevirtual getPaddingRight : ()I
    //   645: i2f
    //   646: iload #10
    //   648: i2f
    //   649: fdiv
    //   650: fconst_2
    //   651: fadd
    //   652: fstore #11
    //   654: aload_0
    //   655: getfield mCurItem : I
    //   658: istore_2
    //   659: aload #7
    //   661: astore #15
    //   663: iload_2
    //   664: iconst_1
    //   665: iadd
    //   666: istore #14
    //   668: iload #14
    //   670: iload #5
    //   672: if_icmpge -> 963
    //   675: fload #13
    //   677: fload #11
    //   679: fcmpl
    //   680: iflt -> 812
    //   683: iload #14
    //   685: iload #6
    //   687: if_icmple -> 812
    //   690: aload #15
    //   692: ifnonnull -> 698
    //   695: goto -> 963
    //   698: fload #13
    //   700: fstore #16
    //   702: iload #8
    //   704: istore_2
    //   705: aload #15
    //   707: astore #7
    //   709: iload #14
    //   711: aload #15
    //   713: getfield position : I
    //   716: if_icmpne -> 946
    //   719: fload #13
    //   721: fstore #16
    //   723: iload #8
    //   725: istore_2
    //   726: aload #15
    //   728: astore #7
    //   730: aload #15
    //   732: getfield scrolling : Z
    //   735: ifne -> 946
    //   738: aload_0
    //   739: getfield mItems : Ljava/util/ArrayList;
    //   742: iload #8
    //   744: invokevirtual remove : (I)Ljava/lang/Object;
    //   747: pop
    //   748: aload_0
    //   749: getfield mAdapter : Landroid/support/v4/view/PagerAdapter;
    //   752: aload_0
    //   753: iload #14
    //   755: aload #15
    //   757: getfield object : Ljava/lang/Object;
    //   760: invokevirtual destroyItem : (Landroid/view/ViewGroup;ILjava/lang/Object;)V
    //   763: fload #13
    //   765: fstore #16
    //   767: iload #8
    //   769: istore_2
    //   770: iload #8
    //   772: aload_0
    //   773: getfield mItems : Ljava/util/ArrayList;
    //   776: invokevirtual size : ()I
    //   779: if_icmpge -> 806
    //   782: aload_0
    //   783: getfield mItems : Ljava/util/ArrayList;
    //   786: iload #8
    //   788: invokevirtual get : (I)Ljava/lang/Object;
    //   791: checkcast android/support/v4/view/ViewPager$ItemInfo
    //   794: astore #7
    //   796: fload #13
    //   798: fstore #16
    //   800: iload #8
    //   802: istore_2
    //   803: goto -> 946
    //   806: aconst_null
    //   807: astore #7
    //   809: goto -> 946
    //   812: aload #15
    //   814: ifnull -> 883
    //   817: iload #14
    //   819: aload #15
    //   821: getfield position : I
    //   824: if_icmpne -> 883
    //   827: fload #13
    //   829: aload #15
    //   831: getfield widthFactor : F
    //   834: fadd
    //   835: fstore #13
    //   837: iinc #8, 1
    //   840: fload #13
    //   842: fstore #16
    //   844: iload #8
    //   846: istore_2
    //   847: iload #8
    //   849: aload_0
    //   850: getfield mItems : Ljava/util/ArrayList;
    //   853: invokevirtual size : ()I
    //   856: if_icmpge -> 806
    //   859: aload_0
    //   860: getfield mItems : Ljava/util/ArrayList;
    //   863: iload #8
    //   865: invokevirtual get : (I)Ljava/lang/Object;
    //   868: checkcast android/support/v4/view/ViewPager$ItemInfo
    //   871: astore #7
    //   873: fload #13
    //   875: fstore #16
    //   877: iload #8
    //   879: istore_2
    //   880: goto -> 946
    //   883: aload_0
    //   884: iload #14
    //   886: iload #8
    //   888: invokevirtual addNewItem : (II)Landroid/support/v4/view/ViewPager$ItemInfo;
    //   891: astore #7
    //   893: iinc #8, 1
    //   896: fload #13
    //   898: aload #7
    //   900: getfield widthFactor : F
    //   903: fadd
    //   904: fstore #13
    //   906: fload #13
    //   908: fstore #16
    //   910: iload #8
    //   912: istore_2
    //   913: iload #8
    //   915: aload_0
    //   916: getfield mItems : Ljava/util/ArrayList;
    //   919: invokevirtual size : ()I
    //   922: if_icmpge -> 806
    //   925: aload_0
    //   926: getfield mItems : Ljava/util/ArrayList;
    //   929: iload #8
    //   931: invokevirtual get : (I)Ljava/lang/Object;
    //   934: checkcast android/support/v4/view/ViewPager$ItemInfo
    //   937: astore #7
    //   939: iload #8
    //   941: istore_2
    //   942: fload #13
    //   944: fstore #16
    //   946: fload #16
    //   948: fstore #13
    //   950: iload_2
    //   951: istore #8
    //   953: aload #7
    //   955: astore #15
    //   957: iload #14
    //   959: istore_2
    //   960: goto -> 663
    //   963: aload_0
    //   964: aload #9
    //   966: iload_1
    //   967: aload_3
    //   968: invokespecial calculatePageOffsets : (Landroid/support/v4/view/ViewPager$ItemInfo;ILandroid/support/v4/view/ViewPager$ItemInfo;)V
    //   971: aload_0
    //   972: getfield mAdapter : Landroid/support/v4/view/PagerAdapter;
    //   975: astore_3
    //   976: aload_0
    //   977: getfield mCurItem : I
    //   980: istore_1
    //   981: aload #9
    //   983: ifnull -> 996
    //   986: aload #9
    //   988: getfield object : Ljava/lang/Object;
    //   991: astore #7
    //   993: goto -> 999
    //   996: aconst_null
    //   997: astore #7
    //   999: aload_3
    //   1000: aload_0
    //   1001: iload_1
    //   1002: aload #7
    //   1004: invokevirtual setPrimaryItem : (Landroid/view/ViewGroup;ILjava/lang/Object;)V
    //   1007: aload_0
    //   1008: getfield mAdapter : Landroid/support/v4/view/PagerAdapter;
    //   1011: aload_0
    //   1012: invokevirtual finishUpdate : (Landroid/view/ViewGroup;)V
    //   1015: aload_0
    //   1016: invokevirtual getChildCount : ()I
    //   1019: istore_2
    //   1020: iconst_0
    //   1021: istore_1
    //   1022: iload_1
    //   1023: iload_2
    //   1024: if_icmpge -> 1100
    //   1027: aload_0
    //   1028: iload_1
    //   1029: invokevirtual getChildAt : (I)Landroid/view/View;
    //   1032: astore_3
    //   1033: aload_3
    //   1034: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   1037: checkcast android/support/v4/view/ViewPager$LayoutParams
    //   1040: astore #7
    //   1042: aload #7
    //   1044: iload_1
    //   1045: putfield childIndex : I
    //   1048: aload #7
    //   1050: getfield isDecor : Z
    //   1053: ifne -> 1094
    //   1056: aload #7
    //   1058: getfield widthFactor : F
    //   1061: fconst_0
    //   1062: fcmpl
    //   1063: ifne -> 1094
    //   1066: aload_0
    //   1067: aload_3
    //   1068: invokevirtual infoForChild : (Landroid/view/View;)Landroid/support/v4/view/ViewPager$ItemInfo;
    //   1071: astore_3
    //   1072: aload_3
    //   1073: ifnull -> 1094
    //   1076: aload #7
    //   1078: aload_3
    //   1079: getfield widthFactor : F
    //   1082: putfield widthFactor : F
    //   1085: aload #7
    //   1087: aload_3
    //   1088: getfield position : I
    //   1091: putfield position : I
    //   1094: iinc #1, 1
    //   1097: goto -> 1022
    //   1100: aload_0
    //   1101: invokespecial sortChildDrawingOrder : ()V
    //   1104: aload_0
    //   1105: invokevirtual hasFocus : ()Z
    //   1108: ifeq -> 1210
    //   1111: aload_0
    //   1112: invokevirtual findFocus : ()Landroid/view/View;
    //   1115: astore #7
    //   1117: aload #7
    //   1119: ifnull -> 1133
    //   1122: aload_0
    //   1123: aload #7
    //   1125: invokevirtual infoForAnyChild : (Landroid/view/View;)Landroid/support/v4/view/ViewPager$ItemInfo;
    //   1128: astore #7
    //   1130: goto -> 1136
    //   1133: aconst_null
    //   1134: astore #7
    //   1136: aload #7
    //   1138: ifnull -> 1153
    //   1141: aload #7
    //   1143: getfield position : I
    //   1146: aload_0
    //   1147: getfield mCurItem : I
    //   1150: if_icmpeq -> 1210
    //   1153: iconst_0
    //   1154: istore_1
    //   1155: iload_1
    //   1156: aload_0
    //   1157: invokevirtual getChildCount : ()I
    //   1160: if_icmpge -> 1210
    //   1163: aload_0
    //   1164: iload_1
    //   1165: invokevirtual getChildAt : (I)Landroid/view/View;
    //   1168: astore_3
    //   1169: aload_0
    //   1170: aload_3
    //   1171: invokevirtual infoForChild : (Landroid/view/View;)Landroid/support/v4/view/ViewPager$ItemInfo;
    //   1174: astore #7
    //   1176: aload #7
    //   1178: ifnull -> 1204
    //   1181: aload #7
    //   1183: getfield position : I
    //   1186: aload_0
    //   1187: getfield mCurItem : I
    //   1190: if_icmpne -> 1204
    //   1193: aload_3
    //   1194: iconst_2
    //   1195: invokevirtual requestFocus : (I)Z
    //   1198: ifeq -> 1204
    //   1201: goto -> 1210
    //   1204: iinc #1, 1
    //   1207: goto -> 1155
    //   1210: return
    //   1211: aload_0
    //   1212: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   1215: aload_0
    //   1216: invokevirtual getId : ()I
    //   1219: invokevirtual getResourceName : (I)Ljava/lang/String;
    //   1222: astore #7
    //   1224: goto -> 1238
    //   1227: astore #7
    //   1229: aload_0
    //   1230: invokevirtual getId : ()I
    //   1233: invokestatic toHexString : (I)Ljava/lang/String;
    //   1236: astore #7
    //   1238: new java/lang/StringBuilder
    //   1241: dup
    //   1242: invokespecial <init> : ()V
    //   1245: astore_3
    //   1246: aload_3
    //   1247: ldc_w 'The application's PagerAdapter changed the adapter's contents without calling PagerAdapter#notifyDataSetChanged! Expected adapter item count: '
    //   1250: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1253: pop
    //   1254: aload_3
    //   1255: aload_0
    //   1256: getfield mExpectedAdapterCount : I
    //   1259: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   1262: pop
    //   1263: aload_3
    //   1264: ldc_w ', found: '
    //   1267: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1270: pop
    //   1271: aload_3
    //   1272: iload #5
    //   1274: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   1277: pop
    //   1278: aload_3
    //   1279: ldc_w ' Pager id: '
    //   1282: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1285: pop
    //   1286: aload_3
    //   1287: aload #7
    //   1289: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1292: pop
    //   1293: aload_3
    //   1294: ldc_w ' Pager class: '
    //   1297: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1300: pop
    //   1301: aload_3
    //   1302: aload_0
    //   1303: invokevirtual getClass : ()Ljava/lang/Class;
    //   1306: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1309: pop
    //   1310: aload_3
    //   1311: ldc_w ' Problematic adapter: '
    //   1314: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1317: pop
    //   1318: aload_3
    //   1319: aload_0
    //   1320: getfield mAdapter : Landroid/support/v4/view/PagerAdapter;
    //   1323: invokevirtual getClass : ()Ljava/lang/Class;
    //   1326: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1329: pop
    //   1330: new java/lang/IllegalStateException
    //   1333: dup
    //   1334: aload_3
    //   1335: invokevirtual toString : ()Ljava/lang/String;
    //   1338: invokespecial <init> : (Ljava/lang/String;)V
    //   1341: astore #7
    //   1343: goto -> 1349
    //   1346: aload #7
    //   1348: athrow
    //   1349: goto -> 1346
    // Exception table:
    //   from	to	target	type
    //   1211	1224	1227	android/content/res/Resources$NotFoundException
  }
  
  public void removeOnAdapterChangeListener(@NonNull OnAdapterChangeListener paramOnAdapterChangeListener) {
    List<OnAdapterChangeListener> list = this.mAdapterChangeListeners;
    if (list != null)
      list.remove(paramOnAdapterChangeListener); 
  }
  
  public void removeOnPageChangeListener(OnPageChangeListener paramOnPageChangeListener) {
    List<OnPageChangeListener> list = this.mOnPageChangeListeners;
    if (list != null)
      list.remove(paramOnPageChangeListener); 
  }
  
  public void removeView(View paramView) {
    if (this.mInLayout) {
      removeViewInLayout(paramView);
    } else {
      super.removeView(paramView);
    } 
  }
  
  public void setAdapter(PagerAdapter paramPagerAdapter) {
    PagerAdapter pagerAdapter = this.mAdapter;
    byte b = 0;
    if (pagerAdapter != null) {
      pagerAdapter.setViewPagerObserver(null);
      this.mAdapter.startUpdate(this);
      for (byte b1 = 0; b1 < this.mItems.size(); b1++) {
        ItemInfo itemInfo = this.mItems.get(b1);
        this.mAdapter.destroyItem(this, itemInfo.position, itemInfo.object);
      } 
      this.mAdapter.finishUpdate(this);
      this.mItems.clear();
      removeNonDecorViews();
      this.mCurItem = 0;
      scrollTo(0, 0);
    } 
    pagerAdapter = this.mAdapter;
    this.mAdapter = paramPagerAdapter;
    this.mExpectedAdapterCount = 0;
    if (paramPagerAdapter != null) {
      if (this.mObserver == null)
        this.mObserver = new PagerObserver(); 
      this.mAdapter.setViewPagerObserver(this.mObserver);
      this.mPopulatePending = false;
      boolean bool = this.mFirstLayout;
      this.mFirstLayout = true;
      this.mExpectedAdapterCount = this.mAdapter.getCount();
      if (this.mRestoredCurItem >= 0) {
        this.mAdapter.restoreState(this.mRestoredAdapterState, this.mRestoredClassLoader);
        setCurrentItemInternal(this.mRestoredCurItem, false, true);
        this.mRestoredCurItem = -1;
        this.mRestoredAdapterState = null;
        this.mRestoredClassLoader = null;
      } else if (!bool) {
        populate();
      } else {
        requestLayout();
      } 
    } 
    List<OnAdapterChangeListener> list = this.mAdapterChangeListeners;
    if (list != null && !list.isEmpty()) {
      int i = this.mAdapterChangeListeners.size();
      for (byte b1 = b; b1 < i; b1++)
        ((OnAdapterChangeListener)this.mAdapterChangeListeners.get(b1)).onAdapterChanged(this, pagerAdapter, paramPagerAdapter); 
    } 
  }
  
  public void setCurrentItem(int paramInt) {
    this.mPopulatePending = false;
    setCurrentItemInternal(paramInt, this.mFirstLayout ^ true, false);
  }
  
  public void setCurrentItem(int paramInt, boolean paramBoolean) {
    this.mPopulatePending = false;
    setCurrentItemInternal(paramInt, paramBoolean, false);
  }
  
  void setCurrentItemInternal(int paramInt, boolean paramBoolean1, boolean paramBoolean2) {
    setCurrentItemInternal(paramInt, paramBoolean1, paramBoolean2, 0);
  }
  
  void setCurrentItemInternal(int paramInt1, boolean paramBoolean1, boolean paramBoolean2, int paramInt2) {
    int i;
    PagerAdapter pagerAdapter = this.mAdapter;
    boolean bool = false;
    if (pagerAdapter == null || pagerAdapter.getCount() <= 0) {
      setScrollingCacheEnabled(false);
      return;
    } 
    if (!paramBoolean2 && this.mCurItem == paramInt1 && this.mItems.size() != 0) {
      setScrollingCacheEnabled(false);
      return;
    } 
    if (paramInt1 < 0) {
      i = 0;
    } else {
      i = paramInt1;
      if (paramInt1 >= this.mAdapter.getCount())
        i = this.mAdapter.getCount() - 1; 
    } 
    int j = this.mOffscreenPageLimit;
    paramInt1 = this.mCurItem;
    if (i > paramInt1 + j || i < paramInt1 - j)
      for (paramInt1 = 0; paramInt1 < this.mItems.size(); paramInt1++)
        ((ItemInfo)this.mItems.get(paramInt1)).scrolling = true;  
    paramBoolean2 = bool;
    if (this.mCurItem != i)
      paramBoolean2 = true; 
    if (this.mFirstLayout) {
      this.mCurItem = i;
      if (paramBoolean2)
        dispatchOnPageSelected(i); 
      requestLayout();
    } else {
      populate(i);
      scrollToItem(i, paramBoolean1, paramInt2, paramBoolean2);
    } 
  }
  
  OnPageChangeListener setInternalPageChangeListener(OnPageChangeListener paramOnPageChangeListener) {
    OnPageChangeListener onPageChangeListener = this.mInternalPageChangeListener;
    this.mInternalPageChangeListener = paramOnPageChangeListener;
    return onPageChangeListener;
  }
  
  public void setOffscreenPageLimit(int paramInt) {
    int i = paramInt;
    if (paramInt < 1) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Requested offscreen page limit ");
      stringBuilder.append(paramInt);
      stringBuilder.append(" too small; defaulting to ");
      stringBuilder.append(1);
      Log.w("ViewPager", stringBuilder.toString());
      i = 1;
    } 
    if (i != this.mOffscreenPageLimit) {
      this.mOffscreenPageLimit = i;
      populate();
    } 
  }
  
  @Deprecated
  public void setOnPageChangeListener(OnPageChangeListener paramOnPageChangeListener) {
    this.mOnPageChangeListener = paramOnPageChangeListener;
  }
  
  public void setPageMargin(int paramInt) {
    int i = this.mPageMargin;
    this.mPageMargin = paramInt;
    int j = getWidth();
    recomputeScrollPosition(j, j, paramInt, i);
    requestLayout();
  }
  
  public void setPageMarginDrawable(@DrawableRes int paramInt) {
    setPageMarginDrawable(ContextCompat.getDrawable(getContext(), paramInt));
  }
  
  public void setPageMarginDrawable(Drawable paramDrawable) {
    boolean bool;
    this.mMarginDrawable = paramDrawable;
    if (paramDrawable != null)
      refreshDrawableState(); 
    if (paramDrawable == null) {
      bool = true;
    } else {
      bool = false;
    } 
    setWillNotDraw(bool);
    invalidate();
  }
  
  public void setPageTransformer(boolean paramBoolean, PageTransformer paramPageTransformer) {
    setPageTransformer(paramBoolean, paramPageTransformer, 2);
  }
  
  public void setPageTransformer(boolean paramBoolean, PageTransformer paramPageTransformer, int paramInt) {
    boolean bool1;
    boolean bool2;
    boolean bool3;
    byte b = 1;
    if (paramPageTransformer != null) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    if (this.mPageTransformer != null) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    if (bool1 != bool2) {
      bool3 = true;
    } else {
      bool3 = false;
    } 
    this.mPageTransformer = paramPageTransformer;
    setChildrenDrawingOrderEnabled(bool1);
    if (bool1) {
      if (paramBoolean)
        b = 2; 
      this.mDrawingOrder = b;
      this.mPageTransformerLayerType = paramInt;
    } else {
      this.mDrawingOrder = 0;
    } 
    if (bool3)
      populate(); 
  }
  
  void setScrollState(int paramInt) {
    if (this.mScrollState == paramInt)
      return; 
    this.mScrollState = paramInt;
    if (this.mPageTransformer != null) {
      boolean bool;
      if (paramInt != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      enableLayers(bool);
    } 
    dispatchOnScrollStateChanged(paramInt);
  }
  
  void smoothScrollTo(int paramInt1, int paramInt2) {
    smoothScrollTo(paramInt1, paramInt2, 0);
  }
  
  void smoothScrollTo(int paramInt1, int paramInt2, int paramInt3) {
    int i;
    if (getChildCount() == 0) {
      setScrollingCacheEnabled(false);
      return;
    } 
    Scroller scroller = this.mScroller;
    if (scroller != null && !scroller.isFinished()) {
      i = 1;
    } else {
      i = 0;
    } 
    if (i) {
      if (this.mIsScrollStarted) {
        i = this.mScroller.getCurrX();
      } else {
        i = this.mScroller.getStartX();
      } 
      this.mScroller.abortAnimation();
      setScrollingCacheEnabled(false);
    } else {
      i = getScrollX();
    } 
    int j = getScrollY();
    int k = paramInt1 - i;
    paramInt2 -= j;
    if (k == 0 && paramInt2 == 0) {
      completeScroll(false);
      populate();
      setScrollState(0);
      return;
    } 
    setScrollingCacheEnabled(true);
    setScrollState(2);
    int m = getClientWidth();
    paramInt1 = m / 2;
    float f1 = Math.abs(k);
    float f2 = m;
    float f3 = Math.min(1.0F, f1 * 1.0F / f2);
    f1 = paramInt1;
    f3 = distanceInfluenceForSnapDuration(f3);
    paramInt1 = Math.abs(paramInt3);
    if (paramInt1 > 0) {
      paramInt1 = Math.round(Math.abs((f1 + f3 * f1) / paramInt1) * 1000.0F) * 4;
    } else {
      f1 = this.mAdapter.getPageWidth(this.mCurItem);
      paramInt1 = (int)((Math.abs(k) / (f2 * f1 + this.mPageMargin) + 1.0F) * 100.0F);
    } 
    paramInt1 = Math.min(paramInt1, 600);
    this.mIsScrollStarted = false;
    this.mScroller.startScroll(i, j, k, paramInt2, paramInt1);
    ViewCompat.postInvalidateOnAnimation((View)this);
  }
  
  protected boolean verifyDrawable(Drawable paramDrawable) {
    return (super.verifyDrawable(paramDrawable) || paramDrawable == this.mMarginDrawable);
  }
  
  @Inherited
  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.TYPE})
  public static @interface DecorView {}
  
  static class ItemInfo {
    Object object;
    
    float offset;
    
    int position;
    
    boolean scrolling;
    
    float widthFactor;
  }
  
  public static class LayoutParams extends ViewGroup.LayoutParams {
    int childIndex;
    
    public int gravity;
    
    public boolean isDecor;
    
    boolean needsMeasure;
    
    int position;
    
    float widthFactor = 0.0F;
    
    public LayoutParams() {
      super(-1, -1);
    }
    
    public LayoutParams(Context param1Context, AttributeSet param1AttributeSet) {
      super(param1Context, param1AttributeSet);
      TypedArray typedArray = param1Context.obtainStyledAttributes(param1AttributeSet, ViewPager.LAYOUT_ATTRS);
      this.gravity = typedArray.getInteger(0, 48);
      typedArray.recycle();
    }
  }
  
  class MyAccessibilityDelegate extends AccessibilityDelegateCompat {
    private boolean canScroll() {
      PagerAdapter pagerAdapter = ViewPager.this.mAdapter;
      boolean bool = true;
      if (pagerAdapter == null || pagerAdapter.getCount() <= 1)
        bool = false; 
      return bool;
    }
    
    public void onInitializeAccessibilityEvent(View param1View, AccessibilityEvent param1AccessibilityEvent) {
      super.onInitializeAccessibilityEvent(param1View, param1AccessibilityEvent);
      param1AccessibilityEvent.setClassName(ViewPager.class.getName());
      param1AccessibilityEvent.setScrollable(canScroll());
      if (param1AccessibilityEvent.getEventType() == 4096) {
        PagerAdapter pagerAdapter = ViewPager.this.mAdapter;
        if (pagerAdapter != null) {
          param1AccessibilityEvent.setItemCount(pagerAdapter.getCount());
          param1AccessibilityEvent.setFromIndex(ViewPager.this.mCurItem);
          param1AccessibilityEvent.setToIndex(ViewPager.this.mCurItem);
        } 
      } 
    }
    
    public void onInitializeAccessibilityNodeInfo(View param1View, AccessibilityNodeInfoCompat param1AccessibilityNodeInfoCompat) {
      super.onInitializeAccessibilityNodeInfo(param1View, param1AccessibilityNodeInfoCompat);
      param1AccessibilityNodeInfoCompat.setClassName(ViewPager.class.getName());
      param1AccessibilityNodeInfoCompat.setScrollable(canScroll());
      if (ViewPager.this.canScrollHorizontally(1))
        param1AccessibilityNodeInfoCompat.addAction(4096); 
      if (ViewPager.this.canScrollHorizontally(-1))
        param1AccessibilityNodeInfoCompat.addAction(8192); 
    }
    
    public boolean performAccessibilityAction(View param1View, int param1Int, Bundle param1Bundle) {
      if (super.performAccessibilityAction(param1View, param1Int, param1Bundle))
        return true; 
      if (param1Int != 4096) {
        if (param1Int != 8192)
          return false; 
        if (ViewPager.this.canScrollHorizontally(-1)) {
          ViewPager viewPager = ViewPager.this;
          viewPager.setCurrentItem(viewPager.mCurItem - 1);
          return true;
        } 
        return false;
      } 
      if (ViewPager.this.canScrollHorizontally(1)) {
        ViewPager viewPager = ViewPager.this;
        viewPager.setCurrentItem(viewPager.mCurItem + 1);
        return true;
      } 
      return false;
    }
  }
  
  public static interface OnAdapterChangeListener {
    void onAdapterChanged(@NonNull ViewPager param1ViewPager, @Nullable PagerAdapter param1PagerAdapter1, @Nullable PagerAdapter param1PagerAdapter2);
  }
  
  public static interface OnPageChangeListener {
    void onPageScrollStateChanged(int param1Int);
    
    void onPageScrolled(int param1Int1, float param1Float, int param1Int2);
    
    void onPageSelected(int param1Int);
  }
  
  public static interface PageTransformer {
    void transformPage(View param1View, float param1Float);
  }
  
  private class PagerObserver extends DataSetObserver {
    public void onChanged() {
      ViewPager.this.dataSetChanged();
    }
    
    public void onInvalidated() {
      ViewPager.this.dataSetChanged();
    }
  }
  
  public static class SavedState extends AbsSavedState {
    public static final Parcelable.Creator<SavedState> CREATOR = (Parcelable.Creator<SavedState>)new Parcelable.ClassLoaderCreator<SavedState>() {
        public ViewPager.SavedState createFromParcel(Parcel param2Parcel) {
          return new ViewPager.SavedState(param2Parcel, null);
        }
        
        public ViewPager.SavedState createFromParcel(Parcel param2Parcel, ClassLoader param2ClassLoader) {
          return new ViewPager.SavedState(param2Parcel, param2ClassLoader);
        }
        
        public ViewPager.SavedState[] newArray(int param2Int) {
          return new ViewPager.SavedState[param2Int];
        }
      };
    
    Parcelable adapterState;
    
    ClassLoader loader;
    
    int position;
    
    SavedState(Parcel param1Parcel, ClassLoader param1ClassLoader) {
      super(param1Parcel, param1ClassLoader);
      ClassLoader classLoader = param1ClassLoader;
      if (param1ClassLoader == null)
        classLoader = getClass().getClassLoader(); 
      this.position = param1Parcel.readInt();
      this.adapterState = param1Parcel.readParcelable(classLoader);
      this.loader = classLoader;
    }
    
    public SavedState(Parcelable param1Parcelable) {
      super(param1Parcelable);
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("FragmentPager.SavedState{");
      stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
      stringBuilder.append(" position=");
      stringBuilder.append(this.position);
      stringBuilder.append("}");
      return stringBuilder.toString();
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      super.writeToParcel(param1Parcel, param1Int);
      param1Parcel.writeInt(this.position);
      param1Parcel.writeParcelable(this.adapterState, param1Int);
    }
  }
  
  static final class null implements Parcelable.ClassLoaderCreator<SavedState> {
    public ViewPager.SavedState createFromParcel(Parcel param1Parcel) {
      return new ViewPager.SavedState(param1Parcel, null);
    }
    
    public ViewPager.SavedState createFromParcel(Parcel param1Parcel, ClassLoader param1ClassLoader) {
      return new ViewPager.SavedState(param1Parcel, param1ClassLoader);
    }
    
    public ViewPager.SavedState[] newArray(int param1Int) {
      return new ViewPager.SavedState[param1Int];
    }
  }
  
  public static class SimpleOnPageChangeListener implements OnPageChangeListener {
    public void onPageScrollStateChanged(int param1Int) {}
    
    public void onPageScrolled(int param1Int1, float param1Float, int param1Int2) {}
    
    public void onPageSelected(int param1Int) {}
  }
  
  static class ViewPositionComparator implements Comparator<View> {
    public int compare(View param1View1, View param1View2) {
      ViewPager.LayoutParams layoutParams1 = (ViewPager.LayoutParams)param1View1.getLayoutParams();
      ViewPager.LayoutParams layoutParams2 = (ViewPager.LayoutParams)param1View2.getLayoutParams();
      boolean bool = layoutParams1.isDecor;
      if (bool != layoutParams2.isDecor) {
        byte b;
        if (bool) {
          b = 1;
        } else {
          b = -1;
        } 
        return b;
      } 
      return layoutParams1.position - layoutParams2.position;
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/view/ViewPager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */