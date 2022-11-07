package android.support.v7.widget.helper;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.recyclerview.R;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.view.animation.Interpolator;
import java.util.ArrayList;
import java.util.List;

public class ItemTouchHelper extends RecyclerView.ItemDecoration implements RecyclerView.OnChildAttachStateChangeListener {
  static final int ACTION_MODE_DRAG_MASK = 16711680;
  
  private static final int ACTION_MODE_IDLE_MASK = 255;
  
  static final int ACTION_MODE_SWIPE_MASK = 65280;
  
  public static final int ACTION_STATE_DRAG = 2;
  
  public static final int ACTION_STATE_IDLE = 0;
  
  public static final int ACTION_STATE_SWIPE = 1;
  
  static final int ACTIVE_POINTER_ID_NONE = -1;
  
  public static final int ANIMATION_TYPE_DRAG = 8;
  
  public static final int ANIMATION_TYPE_SWIPE_CANCEL = 4;
  
  public static final int ANIMATION_TYPE_SWIPE_SUCCESS = 2;
  
  static final boolean DEBUG = false;
  
  static final int DIRECTION_FLAG_COUNT = 8;
  
  public static final int DOWN = 2;
  
  public static final int END = 32;
  
  public static final int LEFT = 4;
  
  private static final int PIXELS_PER_SECOND = 1000;
  
  public static final int RIGHT = 8;
  
  public static final int START = 16;
  
  static final String TAG = "ItemTouchHelper";
  
  public static final int UP = 1;
  
  int mActionState = 0;
  
  int mActivePointerId = -1;
  
  Callback mCallback;
  
  private RecyclerView.ChildDrawingOrderCallback mChildDrawingOrderCallback = null;
  
  private List<Integer> mDistances;
  
  private long mDragScrollStartTimeInMs;
  
  float mDx;
  
  float mDy;
  
  GestureDetectorCompat mGestureDetector;
  
  float mInitialTouchX;
  
  float mInitialTouchY;
  
  float mMaxSwipeVelocity;
  
  private final RecyclerView.OnItemTouchListener mOnItemTouchListener = new RecyclerView.OnItemTouchListener() {
      public boolean onInterceptTouchEvent(RecyclerView param1RecyclerView, MotionEvent param1MotionEvent) {
        ItemTouchHelper.this.mGestureDetector.onTouchEvent(param1MotionEvent);
        int i = param1MotionEvent.getActionMasked();
        boolean bool = true;
        if (i == 0) {
          ItemTouchHelper.this.mActivePointerId = param1MotionEvent.getPointerId(0);
          ItemTouchHelper.this.mInitialTouchX = param1MotionEvent.getX();
          ItemTouchHelper.this.mInitialTouchY = param1MotionEvent.getY();
          ItemTouchHelper.this.obtainVelocityTracker();
          ItemTouchHelper itemTouchHelper = ItemTouchHelper.this;
          if (itemTouchHelper.mSelected == null) {
            ItemTouchHelper.RecoverAnimation recoverAnimation = itemTouchHelper.findAnimation(param1MotionEvent);
            if (recoverAnimation != null) {
              ItemTouchHelper itemTouchHelper2 = ItemTouchHelper.this;
              itemTouchHelper2.mInitialTouchX -= recoverAnimation.mX;
              itemTouchHelper2.mInitialTouchY -= recoverAnimation.mY;
              itemTouchHelper2.endRecoverAnimation(recoverAnimation.mViewHolder, true);
              if (ItemTouchHelper.this.mPendingCleanup.remove(recoverAnimation.mViewHolder.itemView)) {
                itemTouchHelper2 = ItemTouchHelper.this;
                itemTouchHelper2.mCallback.clearView(itemTouchHelper2.mRecyclerView, recoverAnimation.mViewHolder);
              } 
              ItemTouchHelper.this.select(recoverAnimation.mViewHolder, recoverAnimation.mActionState);
              ItemTouchHelper itemTouchHelper1 = ItemTouchHelper.this;
              itemTouchHelper1.updateDxDy(param1MotionEvent, itemTouchHelper1.mSelectedFlags, 0);
            } 
          } 
        } else if (i == 3 || i == 1) {
          ItemTouchHelper itemTouchHelper = ItemTouchHelper.this;
          itemTouchHelper.mActivePointerId = -1;
          itemTouchHelper.select(null, 0);
        } else {
          int j = ItemTouchHelper.this.mActivePointerId;
          if (j != -1) {
            j = param1MotionEvent.findPointerIndex(j);
            if (j >= 0)
              ItemTouchHelper.this.checkSelectForSwipe(i, param1MotionEvent, j); 
          } 
        } 
        VelocityTracker velocityTracker = ItemTouchHelper.this.mVelocityTracker;
        if (velocityTracker != null)
          velocityTracker.addMovement(param1MotionEvent); 
        if (ItemTouchHelper.this.mSelected == null)
          bool = false; 
        return bool;
      }
      
      public void onRequestDisallowInterceptTouchEvent(boolean param1Boolean) {
        if (!param1Boolean)
          return; 
        ItemTouchHelper.this.select(null, 0);
      }
      
      public void onTouchEvent(RecyclerView param1RecyclerView, MotionEvent param1MotionEvent) {
        ItemTouchHelper.this.mGestureDetector.onTouchEvent(param1MotionEvent);
        VelocityTracker velocityTracker = ItemTouchHelper.this.mVelocityTracker;
        if (velocityTracker != null)
          velocityTracker.addMovement(param1MotionEvent); 
        if (ItemTouchHelper.this.mActivePointerId == -1)
          return; 
        int i = param1MotionEvent.getActionMasked();
        int j = param1MotionEvent.findPointerIndex(ItemTouchHelper.this.mActivePointerId);
        if (j >= 0)
          ItemTouchHelper.this.checkSelectForSwipe(i, param1MotionEvent, j); 
        ItemTouchHelper itemTouchHelper = ItemTouchHelper.this;
        RecyclerView.ViewHolder viewHolder = itemTouchHelper.mSelected;
        if (viewHolder == null)
          return; 
        boolean bool = false;
        if (i != 1) {
          VelocityTracker velocityTracker1;
          if (i != 2) {
            if (i != 3) {
              if (i == 6) {
                j = param1MotionEvent.getActionIndex();
                i = param1MotionEvent.getPointerId(j);
                ItemTouchHelper itemTouchHelper1 = ItemTouchHelper.this;
                if (i == itemTouchHelper1.mActivePointerId) {
                  if (j == 0)
                    bool = true; 
                  itemTouchHelper1.mActivePointerId = param1MotionEvent.getPointerId(bool);
                  itemTouchHelper1 = ItemTouchHelper.this;
                  itemTouchHelper1.updateDxDy(param1MotionEvent, itemTouchHelper1.mSelectedFlags, j);
                } 
              } 
            } else {
              velocityTracker1 = itemTouchHelper.mVelocityTracker;
              if (velocityTracker1 != null)
                velocityTracker1.clear(); 
              ItemTouchHelper.this.select(null, 0);
              ItemTouchHelper.this.mActivePointerId = -1;
            } 
          } else if (j >= 0) {
            itemTouchHelper.updateDxDy(param1MotionEvent, itemTouchHelper.mSelectedFlags, j);
            ItemTouchHelper.this.moveIfNecessary((RecyclerView.ViewHolder)velocityTracker1);
            ItemTouchHelper itemTouchHelper1 = ItemTouchHelper.this;
            itemTouchHelper1.mRecyclerView.removeCallbacks(itemTouchHelper1.mScrollRunnable);
            ItemTouchHelper.this.mScrollRunnable.run();
            ItemTouchHelper.this.mRecyclerView.invalidate();
          } 
          return;
        } 
        ItemTouchHelper.this.select(null, 0);
        ItemTouchHelper.this.mActivePointerId = -1;
      }
    };
  
  View mOverdrawChild = null;
  
  int mOverdrawChildPosition = -1;
  
  final List<View> mPendingCleanup = new ArrayList<View>();
  
  List<RecoverAnimation> mRecoverAnimations = new ArrayList<RecoverAnimation>();
  
  RecyclerView mRecyclerView;
  
  final Runnable mScrollRunnable = new Runnable() {
      public void run() {
        ItemTouchHelper itemTouchHelper = ItemTouchHelper.this;
        if (itemTouchHelper.mSelected != null && itemTouchHelper.scrollIfNecessary()) {
          ItemTouchHelper itemTouchHelper2 = ItemTouchHelper.this;
          RecyclerView.ViewHolder viewHolder = itemTouchHelper2.mSelected;
          if (viewHolder != null)
            itemTouchHelper2.moveIfNecessary(viewHolder); 
          ItemTouchHelper itemTouchHelper1 = ItemTouchHelper.this;
          itemTouchHelper1.mRecyclerView.removeCallbacks(itemTouchHelper1.mScrollRunnable);
          ViewCompat.postOnAnimation((View)ItemTouchHelper.this.mRecyclerView, this);
        } 
      }
    };
  
  RecyclerView.ViewHolder mSelected = null;
  
  int mSelectedFlags;
  
  float mSelectedStartX;
  
  float mSelectedStartY;
  
  private int mSlop;
  
  private List<RecyclerView.ViewHolder> mSwapTargets;
  
  float mSwipeEscapeVelocity;
  
  private final float[] mTmpPosition = new float[2];
  
  private Rect mTmpRect;
  
  VelocityTracker mVelocityTracker;
  
  public ItemTouchHelper(Callback paramCallback) {
    this.mCallback = paramCallback;
  }
  
  private void addChildDrawingOrderCallback() {
    if (Build.VERSION.SDK_INT >= 21)
      return; 
    if (this.mChildDrawingOrderCallback == null)
      this.mChildDrawingOrderCallback = new RecyclerView.ChildDrawingOrderCallback() {
          public int onGetChildDrawingOrder(int param1Int1, int param1Int2) {
            ItemTouchHelper itemTouchHelper = ItemTouchHelper.this;
            View view = itemTouchHelper.mOverdrawChild;
            if (view == null)
              return param1Int2; 
            int i = itemTouchHelper.mOverdrawChildPosition;
            int j = i;
            if (i == -1) {
              j = itemTouchHelper.mRecyclerView.indexOfChild(view);
              ItemTouchHelper.this.mOverdrawChildPosition = j;
            } 
            if (param1Int2 == param1Int1 - 1)
              return j; 
            if (param1Int2 >= j)
              param1Int2++; 
            return param1Int2;
          }
        }; 
    this.mRecyclerView.setChildDrawingOrderCallback(this.mChildDrawingOrderCallback);
  }
  
  private int checkHorizontalSwipe(RecyclerView.ViewHolder paramViewHolder, int paramInt) {
    if ((paramInt & 0xC) != 0) {
      byte b2;
      float f1 = this.mDx;
      byte b1 = 8;
      if (f1 > 0.0F) {
        b2 = 8;
      } else {
        b2 = 4;
      } 
      VelocityTracker velocityTracker = this.mVelocityTracker;
      if (velocityTracker != null && this.mActivePointerId > -1) {
        velocityTracker.computeCurrentVelocity(1000, this.mCallback.getSwipeVelocityThreshold(this.mMaxSwipeVelocity));
        float f = this.mVelocityTracker.getXVelocity(this.mActivePointerId);
        f1 = this.mVelocityTracker.getYVelocity(this.mActivePointerId);
        if (f <= 0.0F)
          b1 = 4; 
        f = Math.abs(f);
        if ((b1 & paramInt) != 0 && b2 == b1 && f >= this.mCallback.getSwipeEscapeVelocity(this.mSwipeEscapeVelocity) && f > Math.abs(f1))
          return b1; 
      } 
      f1 = this.mRecyclerView.getWidth();
      float f2 = this.mCallback.getSwipeThreshold(paramViewHolder);
      if ((paramInt & b2) != 0 && Math.abs(this.mDx) > f1 * f2)
        return b2; 
    } 
    return 0;
  }
  
  private int checkVerticalSwipe(RecyclerView.ViewHolder paramViewHolder, int paramInt) {
    if ((paramInt & 0x3) != 0) {
      byte b2;
      float f1 = this.mDy;
      byte b1 = 2;
      if (f1 > 0.0F) {
        b2 = 2;
      } else {
        b2 = 1;
      } 
      VelocityTracker velocityTracker = this.mVelocityTracker;
      if (velocityTracker != null && this.mActivePointerId > -1) {
        velocityTracker.computeCurrentVelocity(1000, this.mCallback.getSwipeVelocityThreshold(this.mMaxSwipeVelocity));
        f1 = this.mVelocityTracker.getXVelocity(this.mActivePointerId);
        float f = this.mVelocityTracker.getYVelocity(this.mActivePointerId);
        if (f <= 0.0F)
          b1 = 1; 
        f = Math.abs(f);
        if ((b1 & paramInt) != 0 && b1 == b2 && f >= this.mCallback.getSwipeEscapeVelocity(this.mSwipeEscapeVelocity) && f > Math.abs(f1))
          return b1; 
      } 
      float f2 = this.mRecyclerView.getHeight();
      f1 = this.mCallback.getSwipeThreshold(paramViewHolder);
      if ((paramInt & b2) != 0 && Math.abs(this.mDy) > f2 * f1)
        return b2; 
    } 
    return 0;
  }
  
  private void destroyCallbacks() {
    this.mRecyclerView.removeItemDecoration(this);
    this.mRecyclerView.removeOnItemTouchListener(this.mOnItemTouchListener);
    this.mRecyclerView.removeOnChildAttachStateChangeListener(this);
    for (int i = this.mRecoverAnimations.size() - 1; i >= 0; i--) {
      RecoverAnimation recoverAnimation = this.mRecoverAnimations.get(0);
      this.mCallback.clearView(this.mRecyclerView, recoverAnimation.mViewHolder);
    } 
    this.mRecoverAnimations.clear();
    this.mOverdrawChild = null;
    this.mOverdrawChildPosition = -1;
    releaseVelocityTracker();
  }
  
  private List<RecyclerView.ViewHolder> findSwapTargets(RecyclerView.ViewHolder paramViewHolder) {
    RecyclerView.ViewHolder viewHolder = paramViewHolder;
    List<RecyclerView.ViewHolder> list = this.mSwapTargets;
    if (list == null) {
      this.mSwapTargets = new ArrayList<RecyclerView.ViewHolder>();
      this.mDistances = new ArrayList<Integer>();
    } else {
      list.clear();
      this.mDistances.clear();
    } 
    int i = this.mCallback.getBoundingBoxMargin();
    int j = Math.round(this.mSelectedStartX + this.mDx) - i;
    int k = Math.round(this.mSelectedStartY + this.mDy) - i;
    int m = viewHolder.itemView.getWidth();
    i *= 2;
    int n = m + j + i;
    int i1 = viewHolder.itemView.getHeight() + k + i;
    int i2 = (j + n) / 2;
    int i3 = (k + i1) / 2;
    RecyclerView.LayoutManager layoutManager = this.mRecyclerView.getLayoutManager();
    int i4 = layoutManager.getChildCount();
    for (i = 0; i < i4; i++) {
      View view = layoutManager.getChildAt(i);
      if (view != paramViewHolder.itemView && view.getBottom() >= k && view.getTop() <= i1 && view.getRight() >= j && view.getLeft() <= n) {
        RecyclerView.ViewHolder viewHolder1 = this.mRecyclerView.getChildViewHolder(view);
        if (this.mCallback.canDropOver(this.mRecyclerView, this.mSelected, viewHolder1)) {
          m = Math.abs(i2 - (view.getLeft() + view.getRight()) / 2);
          int i5 = Math.abs(i3 - (view.getTop() + view.getBottom()) / 2);
          int i6 = m * m + i5 * i5;
          int i7 = this.mSwapTargets.size();
          m = 0;
          i5 = 0;
          while (m < i7 && i6 > ((Integer)this.mDistances.get(m)).intValue()) {
            i5++;
            m++;
          } 
          this.mSwapTargets.add(i5, viewHolder1);
          this.mDistances.add(i5, Integer.valueOf(i6));
        } 
      } 
    } 
    return this.mSwapTargets;
  }
  
  private RecyclerView.ViewHolder findSwipedView(MotionEvent paramMotionEvent) {
    RecyclerView.LayoutManager layoutManager = this.mRecyclerView.getLayoutManager();
    int i = this.mActivePointerId;
    if (i == -1)
      return null; 
    i = paramMotionEvent.findPointerIndex(i);
    float f1 = paramMotionEvent.getX(i);
    float f2 = this.mInitialTouchX;
    float f3 = paramMotionEvent.getY(i);
    float f4 = this.mInitialTouchY;
    f1 = Math.abs(f1 - f2);
    f4 = Math.abs(f3 - f4);
    i = this.mSlop;
    if (f1 < i && f4 < i)
      return null; 
    if (f1 > f4 && layoutManager.canScrollHorizontally())
      return null; 
    if (f4 > f1 && layoutManager.canScrollVertically())
      return null; 
    View view = findChildView(paramMotionEvent);
    return (view == null) ? null : this.mRecyclerView.getChildViewHolder(view);
  }
  
  private void getSelectedDxDy(float[] paramArrayOffloat) {
    if ((this.mSelectedFlags & 0xC) != 0) {
      paramArrayOffloat[0] = this.mSelectedStartX + this.mDx - this.mSelected.itemView.getLeft();
    } else {
      paramArrayOffloat[0] = this.mSelected.itemView.getTranslationX();
    } 
    if ((this.mSelectedFlags & 0x3) != 0) {
      paramArrayOffloat[1] = this.mSelectedStartY + this.mDy - this.mSelected.itemView.getTop();
    } else {
      paramArrayOffloat[1] = this.mSelected.itemView.getTranslationY();
    } 
  }
  
  private static boolean hitTest(View paramView, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    boolean bool;
    if (paramFloat1 >= paramFloat3 && paramFloat1 <= paramFloat3 + paramView.getWidth() && paramFloat2 >= paramFloat4 && paramFloat2 <= paramFloat4 + paramView.getHeight()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private void initGestureDetector() {
    if (this.mGestureDetector != null)
      return; 
    this.mGestureDetector = new GestureDetectorCompat(this.mRecyclerView.getContext(), (GestureDetector.OnGestureListener)new ItemTouchHelperGestureListener());
  }
  
  private void releaseVelocityTracker() {
    VelocityTracker velocityTracker = this.mVelocityTracker;
    if (velocityTracker != null) {
      velocityTracker.recycle();
      this.mVelocityTracker = null;
    } 
  }
  
  private void setupCallbacks() {
    this.mSlop = ViewConfiguration.get(this.mRecyclerView.getContext()).getScaledTouchSlop();
    this.mRecyclerView.addItemDecoration(this);
    this.mRecyclerView.addOnItemTouchListener(this.mOnItemTouchListener);
    this.mRecyclerView.addOnChildAttachStateChangeListener(this);
    initGestureDetector();
  }
  
  private int swipeIfNecessary(RecyclerView.ViewHolder paramViewHolder) {
    if (this.mActionState == 2)
      return 0; 
    int i = this.mCallback.getMovementFlags(this.mRecyclerView, paramViewHolder);
    int j = (this.mCallback.convertToAbsoluteDirection(i, ViewCompat.getLayoutDirection((View)this.mRecyclerView)) & 0xFF00) >> 8;
    if (j == 0)
      return 0; 
    int k = (i & 0xFF00) >> 8;
    if (Math.abs(this.mDx) > Math.abs(this.mDy)) {
      i = checkHorizontalSwipe(paramViewHolder, j);
      if (i > 0)
        return ((k & i) == 0) ? Callback.convertToRelativeDirection(i, ViewCompat.getLayoutDirection((View)this.mRecyclerView)) : i; 
      j = checkVerticalSwipe(paramViewHolder, j);
      if (j > 0)
        return j; 
    } else {
      i = checkVerticalSwipe(paramViewHolder, j);
      if (i > 0)
        return i; 
      i = checkHorizontalSwipe(paramViewHolder, j);
      if (i > 0) {
        j = i;
        if ((k & i) == 0)
          j = Callback.convertToRelativeDirection(i, ViewCompat.getLayoutDirection((View)this.mRecyclerView)); 
        return j;
      } 
    } 
    return 0;
  }
  
  public void attachToRecyclerView(@Nullable RecyclerView paramRecyclerView) {
    RecyclerView recyclerView = this.mRecyclerView;
    if (recyclerView == paramRecyclerView)
      return; 
    if (recyclerView != null)
      destroyCallbacks(); 
    this.mRecyclerView = paramRecyclerView;
    if (paramRecyclerView != null) {
      Resources resources = paramRecyclerView.getResources();
      this.mSwipeEscapeVelocity = resources.getDimension(R.dimen.item_touch_helper_swipe_escape_velocity);
      this.mMaxSwipeVelocity = resources.getDimension(R.dimen.item_touch_helper_swipe_escape_max_velocity);
      setupCallbacks();
    } 
  }
  
  boolean checkSelectForSwipe(int paramInt1, MotionEvent paramMotionEvent, int paramInt2) {
    if (this.mSelected != null || paramInt1 != 2 || this.mActionState == 2 || !this.mCallback.isItemViewSwipeEnabled())
      return false; 
    if (this.mRecyclerView.getScrollState() == 1)
      return false; 
    RecyclerView.ViewHolder viewHolder = findSwipedView(paramMotionEvent);
    if (viewHolder == null)
      return false; 
    paramInt1 = (this.mCallback.getAbsoluteMovementFlags(this.mRecyclerView, viewHolder) & 0xFF00) >> 8;
    if (paramInt1 == 0)
      return false; 
    float f1 = paramMotionEvent.getX(paramInt2);
    float f2 = paramMotionEvent.getY(paramInt2);
    f1 -= this.mInitialTouchX;
    f2 -= this.mInitialTouchY;
    float f3 = Math.abs(f1);
    float f4 = Math.abs(f2);
    paramInt2 = this.mSlop;
    if (f3 < paramInt2 && f4 < paramInt2)
      return false; 
    if (f3 > f4) {
      if (f1 < 0.0F && (paramInt1 & 0x4) == 0)
        return false; 
      if (f1 > 0.0F && (paramInt1 & 0x8) == 0)
        return false; 
    } else {
      if (f2 < 0.0F && (paramInt1 & 0x1) == 0)
        return false; 
      if (f2 > 0.0F && (paramInt1 & 0x2) == 0)
        return false; 
    } 
    this.mDy = 0.0F;
    this.mDx = 0.0F;
    this.mActivePointerId = paramMotionEvent.getPointerId(0);
    select(viewHolder, 1);
    return true;
  }
  
  int endRecoverAnimation(RecyclerView.ViewHolder paramViewHolder, boolean paramBoolean) {
    for (int i = this.mRecoverAnimations.size() - 1; i >= 0; i--) {
      RecoverAnimation recoverAnimation = this.mRecoverAnimations.get(i);
      if (recoverAnimation.mViewHolder == paramViewHolder) {
        recoverAnimation.mOverridden |= paramBoolean;
        if (!recoverAnimation.mEnded)
          recoverAnimation.cancel(); 
        this.mRecoverAnimations.remove(i);
        return recoverAnimation.mAnimationType;
      } 
    } 
    return 0;
  }
  
  RecoverAnimation findAnimation(MotionEvent paramMotionEvent) {
    if (this.mRecoverAnimations.isEmpty())
      return null; 
    View view = findChildView(paramMotionEvent);
    for (int i = this.mRecoverAnimations.size() - 1; i >= 0; i--) {
      RecoverAnimation recoverAnimation = this.mRecoverAnimations.get(i);
      if (recoverAnimation.mViewHolder.itemView == view)
        return recoverAnimation; 
    } 
    return null;
  }
  
  View findChildView(MotionEvent paramMotionEvent) {
    float f1 = paramMotionEvent.getX();
    float f2 = paramMotionEvent.getY();
    RecyclerView.ViewHolder viewHolder = this.mSelected;
    if (viewHolder != null) {
      View view = viewHolder.itemView;
      if (hitTest(view, f1, f2, this.mSelectedStartX + this.mDx, this.mSelectedStartY + this.mDy))
        return view; 
    } 
    for (int i = this.mRecoverAnimations.size() - 1; i >= 0; i--) {
      RecoverAnimation recoverAnimation = this.mRecoverAnimations.get(i);
      View view = recoverAnimation.mViewHolder.itemView;
      if (hitTest(view, f1, f2, recoverAnimation.mX, recoverAnimation.mY))
        return view; 
    } 
    return this.mRecyclerView.findChildViewUnder(f1, f2);
  }
  
  public void getItemOffsets(Rect paramRect, View paramView, RecyclerView paramRecyclerView, RecyclerView.State paramState) {
    paramRect.setEmpty();
  }
  
  boolean hasRunningRecoverAnim() {
    int i = this.mRecoverAnimations.size();
    for (byte b = 0; b < i; b++) {
      if (!((RecoverAnimation)this.mRecoverAnimations.get(b)).mEnded)
        return true; 
    } 
    return false;
  }
  
  void moveIfNecessary(RecyclerView.ViewHolder paramViewHolder) {
    if (this.mRecyclerView.isLayoutRequested())
      return; 
    if (this.mActionState != 2)
      return; 
    float f = this.mCallback.getMoveThreshold(paramViewHolder);
    int i = (int)(this.mSelectedStartX + this.mDx);
    int j = (int)(this.mSelectedStartY + this.mDy);
    if (Math.abs(j - paramViewHolder.itemView.getTop()) < paramViewHolder.itemView.getHeight() * f && Math.abs(i - paramViewHolder.itemView.getLeft()) < paramViewHolder.itemView.getWidth() * f)
      return; 
    List<RecyclerView.ViewHolder> list = findSwapTargets(paramViewHolder);
    if (list.size() == 0)
      return; 
    RecyclerView.ViewHolder viewHolder = this.mCallback.chooseDropTarget(paramViewHolder, list, i, j);
    if (viewHolder == null) {
      this.mSwapTargets.clear();
      this.mDistances.clear();
      return;
    } 
    int k = viewHolder.getAdapterPosition();
    int m = paramViewHolder.getAdapterPosition();
    if (this.mCallback.onMove(this.mRecyclerView, paramViewHolder, viewHolder))
      this.mCallback.onMoved(this.mRecyclerView, paramViewHolder, m, viewHolder, k, i, j); 
  }
  
  void obtainVelocityTracker() {
    VelocityTracker velocityTracker = this.mVelocityTracker;
    if (velocityTracker != null)
      velocityTracker.recycle(); 
    this.mVelocityTracker = VelocityTracker.obtain();
  }
  
  public void onChildViewAttachedToWindow(View paramView) {}
  
  public void onChildViewDetachedFromWindow(View paramView) {
    removeChildDrawingOrderCallbackIfNecessary(paramView);
    RecyclerView.ViewHolder viewHolder1 = this.mRecyclerView.getChildViewHolder(paramView);
    if (viewHolder1 == null)
      return; 
    RecyclerView.ViewHolder viewHolder2 = this.mSelected;
    if (viewHolder2 != null && viewHolder1 == viewHolder2) {
      select(null, 0);
    } else {
      endRecoverAnimation(viewHolder1, false);
      if (this.mPendingCleanup.remove(viewHolder1.itemView))
        this.mCallback.clearView(this.mRecyclerView, viewHolder1); 
    } 
  }
  
  public void onDraw(Canvas paramCanvas, RecyclerView paramRecyclerView, RecyclerView.State paramState) {
    float f1;
    float f2;
    this.mOverdrawChildPosition = -1;
    if (this.mSelected != null) {
      getSelectedDxDy(this.mTmpPosition);
      float[] arrayOfFloat = this.mTmpPosition;
      f1 = arrayOfFloat[0];
      f2 = arrayOfFloat[1];
    } else {
      f1 = 0.0F;
      f2 = 0.0F;
    } 
    this.mCallback.onDraw(paramCanvas, paramRecyclerView, this.mSelected, this.mRecoverAnimations, this.mActionState, f1, f2);
  }
  
  public void onDrawOver(Canvas paramCanvas, RecyclerView paramRecyclerView, RecyclerView.State paramState) {
    float f1;
    float f2;
    if (this.mSelected != null) {
      getSelectedDxDy(this.mTmpPosition);
      float[] arrayOfFloat = this.mTmpPosition;
      f1 = arrayOfFloat[0];
      f2 = arrayOfFloat[1];
    } else {
      f1 = 0.0F;
      f2 = 0.0F;
    } 
    this.mCallback.onDrawOver(paramCanvas, paramRecyclerView, this.mSelected, this.mRecoverAnimations, this.mActionState, f1, f2);
  }
  
  void postDispatchSwipe(final RecoverAnimation anim, final int swipeDir) {
    this.mRecyclerView.post(new Runnable() {
          public void run() {
            RecyclerView recyclerView = ItemTouchHelper.this.mRecyclerView;
            if (recyclerView != null && recyclerView.isAttachedToWindow()) {
              ItemTouchHelper.RecoverAnimation recoverAnimation = anim;
              if (!recoverAnimation.mOverridden && recoverAnimation.mViewHolder.getAdapterPosition() != -1) {
                RecyclerView.ItemAnimator itemAnimator = ItemTouchHelper.this.mRecyclerView.getItemAnimator();
                if ((itemAnimator == null || !itemAnimator.isRunning(null)) && !ItemTouchHelper.this.hasRunningRecoverAnim()) {
                  ItemTouchHelper.this.mCallback.onSwiped(anim.mViewHolder, swipeDir);
                } else {
                  ItemTouchHelper.this.mRecyclerView.post(this);
                } 
              } 
            } 
          }
        });
  }
  
  void removeChildDrawingOrderCallbackIfNecessary(View paramView) {
    if (paramView == this.mOverdrawChild) {
      this.mOverdrawChild = null;
      if (this.mChildDrawingOrderCallback != null)
        this.mRecyclerView.setChildDrawingOrderCallback(null); 
    } 
  }
  
  boolean scrollIfNecessary() {
    // Byte code:
    //   0: aload_0
    //   1: getfield mSelected : Landroid/support/v7/widget/RecyclerView$ViewHolder;
    //   4: ifnonnull -> 16
    //   7: aload_0
    //   8: ldc2_w -9223372036854775808
    //   11: putfield mDragScrollStartTimeInMs : J
    //   14: iconst_0
    //   15: ireturn
    //   16: invokestatic currentTimeMillis : ()J
    //   19: lstore_1
    //   20: aload_0
    //   21: getfield mDragScrollStartTimeInMs : J
    //   24: lstore_3
    //   25: lload_3
    //   26: ldc2_w -9223372036854775808
    //   29: lcmp
    //   30: ifne -> 38
    //   33: lconst_0
    //   34: lstore_3
    //   35: goto -> 42
    //   38: lload_1
    //   39: lload_3
    //   40: lsub
    //   41: lstore_3
    //   42: aload_0
    //   43: getfield mRecyclerView : Landroid/support/v7/widget/RecyclerView;
    //   46: invokevirtual getLayoutManager : ()Landroid/support/v7/widget/RecyclerView$LayoutManager;
    //   49: astore #5
    //   51: aload_0
    //   52: getfield mTmpRect : Landroid/graphics/Rect;
    //   55: ifnonnull -> 69
    //   58: aload_0
    //   59: new android/graphics/Rect
    //   62: dup
    //   63: invokespecial <init> : ()V
    //   66: putfield mTmpRect : Landroid/graphics/Rect;
    //   69: aload #5
    //   71: aload_0
    //   72: getfield mSelected : Landroid/support/v7/widget/RecyclerView$ViewHolder;
    //   75: getfield itemView : Landroid/view/View;
    //   78: aload_0
    //   79: getfield mTmpRect : Landroid/graphics/Rect;
    //   82: invokevirtual calculateItemDecorationsForChild : (Landroid/view/View;Landroid/graphics/Rect;)V
    //   85: aload #5
    //   87: invokevirtual canScrollHorizontally : ()Z
    //   90: ifeq -> 200
    //   93: aload_0
    //   94: getfield mSelectedStartX : F
    //   97: aload_0
    //   98: getfield mDx : F
    //   101: fadd
    //   102: f2i
    //   103: istore #6
    //   105: iload #6
    //   107: aload_0
    //   108: getfield mTmpRect : Landroid/graphics/Rect;
    //   111: getfield left : I
    //   114: isub
    //   115: aload_0
    //   116: getfield mRecyclerView : Landroid/support/v7/widget/RecyclerView;
    //   119: invokevirtual getPaddingLeft : ()I
    //   122: isub
    //   123: istore #7
    //   125: aload_0
    //   126: getfield mDx : F
    //   129: fstore #8
    //   131: fload #8
    //   133: fconst_0
    //   134: fcmpg
    //   135: ifge -> 146
    //   138: iload #7
    //   140: ifge -> 146
    //   143: goto -> 203
    //   146: fload #8
    //   148: fconst_0
    //   149: fcmpl
    //   150: ifle -> 200
    //   153: iload #6
    //   155: aload_0
    //   156: getfield mSelected : Landroid/support/v7/widget/RecyclerView$ViewHolder;
    //   159: getfield itemView : Landroid/view/View;
    //   162: invokevirtual getWidth : ()I
    //   165: iadd
    //   166: aload_0
    //   167: getfield mTmpRect : Landroid/graphics/Rect;
    //   170: getfield right : I
    //   173: iadd
    //   174: aload_0
    //   175: getfield mRecyclerView : Landroid/support/v7/widget/RecyclerView;
    //   178: invokevirtual getWidth : ()I
    //   181: aload_0
    //   182: getfield mRecyclerView : Landroid/support/v7/widget/RecyclerView;
    //   185: invokevirtual getPaddingRight : ()I
    //   188: isub
    //   189: isub
    //   190: istore #7
    //   192: iload #7
    //   194: ifle -> 200
    //   197: goto -> 203
    //   200: iconst_0
    //   201: istore #7
    //   203: aload #5
    //   205: invokevirtual canScrollVertically : ()Z
    //   208: ifeq -> 318
    //   211: aload_0
    //   212: getfield mSelectedStartY : F
    //   215: aload_0
    //   216: getfield mDy : F
    //   219: fadd
    //   220: f2i
    //   221: istore #9
    //   223: iload #9
    //   225: aload_0
    //   226: getfield mTmpRect : Landroid/graphics/Rect;
    //   229: getfield top : I
    //   232: isub
    //   233: aload_0
    //   234: getfield mRecyclerView : Landroid/support/v7/widget/RecyclerView;
    //   237: invokevirtual getPaddingTop : ()I
    //   240: isub
    //   241: istore #6
    //   243: aload_0
    //   244: getfield mDy : F
    //   247: fstore #8
    //   249: fload #8
    //   251: fconst_0
    //   252: fcmpg
    //   253: ifge -> 264
    //   256: iload #6
    //   258: ifge -> 264
    //   261: goto -> 321
    //   264: fload #8
    //   266: fconst_0
    //   267: fcmpl
    //   268: ifle -> 318
    //   271: iload #9
    //   273: aload_0
    //   274: getfield mSelected : Landroid/support/v7/widget/RecyclerView$ViewHolder;
    //   277: getfield itemView : Landroid/view/View;
    //   280: invokevirtual getHeight : ()I
    //   283: iadd
    //   284: aload_0
    //   285: getfield mTmpRect : Landroid/graphics/Rect;
    //   288: getfield bottom : I
    //   291: iadd
    //   292: aload_0
    //   293: getfield mRecyclerView : Landroid/support/v7/widget/RecyclerView;
    //   296: invokevirtual getHeight : ()I
    //   299: aload_0
    //   300: getfield mRecyclerView : Landroid/support/v7/widget/RecyclerView;
    //   303: invokevirtual getPaddingBottom : ()I
    //   306: isub
    //   307: isub
    //   308: istore #6
    //   310: iload #6
    //   312: ifle -> 318
    //   315: goto -> 321
    //   318: iconst_0
    //   319: istore #6
    //   321: iload #7
    //   323: istore #9
    //   325: iload #7
    //   327: ifeq -> 363
    //   330: aload_0
    //   331: getfield mCallback : Landroid/support/v7/widget/helper/ItemTouchHelper$Callback;
    //   334: aload_0
    //   335: getfield mRecyclerView : Landroid/support/v7/widget/RecyclerView;
    //   338: aload_0
    //   339: getfield mSelected : Landroid/support/v7/widget/RecyclerView$ViewHolder;
    //   342: getfield itemView : Landroid/view/View;
    //   345: invokevirtual getWidth : ()I
    //   348: iload #7
    //   350: aload_0
    //   351: getfield mRecyclerView : Landroid/support/v7/widget/RecyclerView;
    //   354: invokevirtual getWidth : ()I
    //   357: lload_3
    //   358: invokevirtual interpolateOutOfBoundsScroll : (Landroid/support/v7/widget/RecyclerView;IIIJ)I
    //   361: istore #9
    //   363: iload #6
    //   365: ifeq -> 404
    //   368: aload_0
    //   369: getfield mCallback : Landroid/support/v7/widget/helper/ItemTouchHelper$Callback;
    //   372: aload_0
    //   373: getfield mRecyclerView : Landroid/support/v7/widget/RecyclerView;
    //   376: aload_0
    //   377: getfield mSelected : Landroid/support/v7/widget/RecyclerView$ViewHolder;
    //   380: getfield itemView : Landroid/view/View;
    //   383: invokevirtual getHeight : ()I
    //   386: iload #6
    //   388: aload_0
    //   389: getfield mRecyclerView : Landroid/support/v7/widget/RecyclerView;
    //   392: invokevirtual getHeight : ()I
    //   395: lload_3
    //   396: invokevirtual interpolateOutOfBoundsScroll : (Landroid/support/v7/widget/RecyclerView;IIIJ)I
    //   399: istore #6
    //   401: goto -> 404
    //   404: iload #9
    //   406: ifne -> 426
    //   409: iload #6
    //   411: ifeq -> 417
    //   414: goto -> 426
    //   417: aload_0
    //   418: ldc2_w -9223372036854775808
    //   421: putfield mDragScrollStartTimeInMs : J
    //   424: iconst_0
    //   425: ireturn
    //   426: aload_0
    //   427: getfield mDragScrollStartTimeInMs : J
    //   430: ldc2_w -9223372036854775808
    //   433: lcmp
    //   434: ifne -> 442
    //   437: aload_0
    //   438: lload_1
    //   439: putfield mDragScrollStartTimeInMs : J
    //   442: aload_0
    //   443: getfield mRecyclerView : Landroid/support/v7/widget/RecyclerView;
    //   446: iload #9
    //   448: iload #6
    //   450: invokevirtual scrollBy : (II)V
    //   453: iconst_1
    //   454: ireturn
  }
  
  void select(RecyclerView.ViewHolder paramViewHolder, int paramInt) {
    boolean bool;
    if (paramViewHolder == this.mSelected && paramInt == this.mActionState)
      return; 
    this.mDragScrollStartTimeInMs = Long.MIN_VALUE;
    int i = this.mActionState;
    endRecoverAnimation(paramViewHolder, true);
    this.mActionState = paramInt;
    if (paramInt == 2) {
      this.mOverdrawChild = paramViewHolder.itemView;
      addChildDrawingOrderCallback();
    } 
    final RecyclerView.ViewHolder prevSelected = this.mSelected;
    if (viewHolder != null) {
      RecoverAnimation recoverAnimation;
      if (viewHolder.itemView.getParent() != null) {
        final int swipeDir;
        float f1;
        float f3;
        if (i == 2) {
          j = 0;
        } else {
          j = swipeIfNecessary(viewHolder);
        } 
        releaseVelocityTracker();
        if (j != 1 && j != 2) {
          if (j != 4 && j != 8 && j != 16 && j != 32) {
            f1 = 0.0F;
          } else {
            f1 = Math.signum(this.mDx) * this.mRecyclerView.getWidth();
          } 
          float f = 0.0F;
          f3 = f1;
          f1 = f;
        } else {
          f1 = Math.signum(this.mDy) * this.mRecyclerView.getHeight();
          f3 = 0.0F;
        } 
        if (i == 2) {
          bool = true;
        } else if (j > 0) {
          bool = true;
        } else {
          bool = true;
        } 
        getSelectedDxDy(this.mTmpPosition);
        float[] arrayOfFloat = this.mTmpPosition;
        float f4 = arrayOfFloat[0];
        float f2 = arrayOfFloat[1];
        recoverAnimation = new RecoverAnimation(viewHolder, bool, i, f4, f2, f3, f1) {
            public void onAnimationEnd(Animator param1Animator) {
              super.onAnimationEnd(param1Animator);
              if (this.mOverridden)
                return; 
              if (swipeDir <= 0) {
                ItemTouchHelper itemTouchHelper1 = ItemTouchHelper.this;
                itemTouchHelper1.mCallback.clearView(itemTouchHelper1.mRecyclerView, prevSelected);
              } else {
                ItemTouchHelper.this.mPendingCleanup.add(prevSelected.itemView);
                this.mIsPendingCleanup = true;
                int i = swipeDir;
                if (i > 0)
                  ItemTouchHelper.this.postDispatchSwipe(this, i); 
              } 
              ItemTouchHelper itemTouchHelper = ItemTouchHelper.this;
              View view1 = itemTouchHelper.mOverdrawChild;
              View view2 = prevSelected.itemView;
              if (view1 == view2)
                itemTouchHelper.removeChildDrawingOrderCallbackIfNecessary(view2); 
            }
          };
        recoverAnimation.setDuration(this.mCallback.getAnimationDuration(this.mRecyclerView, bool, f3 - f4, f1 - f2));
        this.mRecoverAnimations.add(recoverAnimation);
        recoverAnimation.start();
        bool = true;
      } else {
        removeChildDrawingOrderCallbackIfNecessary(((RecyclerView.ViewHolder)recoverAnimation).itemView);
        this.mCallback.clearView(this.mRecyclerView, (RecyclerView.ViewHolder)recoverAnimation);
        bool = false;
      } 
      this.mSelected = null;
    } else {
      bool = false;
    } 
    if (paramViewHolder != null) {
      this.mSelectedFlags = (this.mCallback.getAbsoluteMovementFlags(this.mRecyclerView, paramViewHolder) & (1 << paramInt * 8 + 8) - 1) >> this.mActionState * 8;
      this.mSelectedStartX = paramViewHolder.itemView.getLeft();
      this.mSelectedStartY = paramViewHolder.itemView.getTop();
      this.mSelected = paramViewHolder;
      if (paramInt == 2)
        paramViewHolder.itemView.performHapticFeedback(0); 
    } 
    ViewParent viewParent = this.mRecyclerView.getParent();
    if (viewParent != null) {
      boolean bool1;
      if (this.mSelected != null) {
        bool1 = true;
      } else {
        bool1 = false;
      } 
      viewParent.requestDisallowInterceptTouchEvent(bool1);
    } 
    if (!bool)
      this.mRecyclerView.getLayoutManager().requestSimpleAnimationsInNextLayout(); 
    this.mCallback.onSelectedChanged(this.mSelected, this.mActionState);
    this.mRecyclerView.invalidate();
  }
  
  public void startDrag(RecyclerView.ViewHolder paramViewHolder) {
    if (!this.mCallback.hasDragFlag(this.mRecyclerView, paramViewHolder)) {
      Log.e("ItemTouchHelper", "Start drag has been called but dragging is not enabled");
      return;
    } 
    if (paramViewHolder.itemView.getParent() != this.mRecyclerView) {
      Log.e("ItemTouchHelper", "Start drag has been called with a view holder which is not a child of the RecyclerView which is controlled by this ItemTouchHelper.");
      return;
    } 
    obtainVelocityTracker();
    this.mDy = 0.0F;
    this.mDx = 0.0F;
    select(paramViewHolder, 2);
  }
  
  public void startSwipe(RecyclerView.ViewHolder paramViewHolder) {
    if (!this.mCallback.hasSwipeFlag(this.mRecyclerView, paramViewHolder)) {
      Log.e("ItemTouchHelper", "Start swipe has been called but swiping is not enabled");
      return;
    } 
    if (paramViewHolder.itemView.getParent() != this.mRecyclerView) {
      Log.e("ItemTouchHelper", "Start swipe has been called with a view holder which is not a child of the RecyclerView controlled by this ItemTouchHelper.");
      return;
    } 
    obtainVelocityTracker();
    this.mDy = 0.0F;
    this.mDx = 0.0F;
    select(paramViewHolder, 1);
  }
  
  void updateDxDy(MotionEvent paramMotionEvent, int paramInt1, int paramInt2) {
    float f1 = paramMotionEvent.getX(paramInt2);
    float f2 = paramMotionEvent.getY(paramInt2);
    f1 -= this.mInitialTouchX;
    this.mDx = f1;
    this.mDy = f2 - this.mInitialTouchY;
    if ((paramInt1 & 0x4) == 0)
      this.mDx = Math.max(0.0F, f1); 
    if ((paramInt1 & 0x8) == 0)
      this.mDx = Math.min(0.0F, this.mDx); 
    if ((paramInt1 & 0x1) == 0)
      this.mDy = Math.max(0.0F, this.mDy); 
    if ((paramInt1 & 0x2) == 0)
      this.mDy = Math.min(0.0F, this.mDy); 
  }
  
  public static abstract class Callback {
    private static final int ABS_HORIZONTAL_DIR_FLAGS = 789516;
    
    public static final int DEFAULT_DRAG_ANIMATION_DURATION = 200;
    
    public static final int DEFAULT_SWIPE_ANIMATION_DURATION = 250;
    
    private static final long DRAG_SCROLL_ACCELERATION_LIMIT_TIME_MS = 2000L;
    
    static final int RELATIVE_DIR_FLAGS = 3158064;
    
    private static final Interpolator sDragScrollInterpolator = new Interpolator() {
        public float getInterpolation(float param2Float) {
          return param2Float * param2Float * param2Float * param2Float * param2Float;
        }
      };
    
    private static final Interpolator sDragViewScrollCapInterpolator = new Interpolator() {
        public float getInterpolation(float param2Float) {
          param2Float--;
          return param2Float * param2Float * param2Float * param2Float * param2Float + 1.0F;
        }
      };
    
    private static final ItemTouchUIUtil sUICallback;
    
    private int mCachedMaxScrollSpeed = -1;
    
    static {
      if (Build.VERSION.SDK_INT >= 21) {
        sUICallback = new ItemTouchUIUtilImpl.Api21Impl();
      } else {
        sUICallback = new ItemTouchUIUtilImpl.BaseImpl();
      } 
    }
    
    public static int convertToRelativeDirection(int param1Int1, int param1Int2) {
      int i = param1Int1 & 0xC0C0C;
      if (i == 0)
        return param1Int1; 
      int j = param1Int1 & (i ^ 0xFFFFFFFF);
      if (param1Int2 == 0) {
        param1Int1 = i << 2;
        param1Int2 = j;
        return param1Int2 | param1Int1;
      } 
      param1Int1 = i << 1;
      param1Int2 = j | 0xFFF3F3F3 & param1Int1;
      param1Int1 = (param1Int1 & 0xC0C0C) << 2;
      return param1Int2 | param1Int1;
    }
    
    public static ItemTouchUIUtil getDefaultUIUtil() {
      return sUICallback;
    }
    
    private int getMaxDragScroll(RecyclerView param1RecyclerView) {
      if (this.mCachedMaxScrollSpeed == -1)
        this.mCachedMaxScrollSpeed = param1RecyclerView.getResources().getDimensionPixelSize(R.dimen.item_touch_helper_max_drag_scroll_per_frame); 
      return this.mCachedMaxScrollSpeed;
    }
    
    public static int makeFlag(int param1Int1, int param1Int2) {
      return param1Int2 << param1Int1 * 8;
    }
    
    public static int makeMovementFlags(int param1Int1, int param1Int2) {
      int i = makeFlag(0, param1Int2 | param1Int1);
      param1Int2 = makeFlag(1, param1Int2);
      return makeFlag(2, param1Int1) | param1Int2 | i;
    }
    
    public boolean canDropOver(RecyclerView param1RecyclerView, RecyclerView.ViewHolder param1ViewHolder1, RecyclerView.ViewHolder param1ViewHolder2) {
      return true;
    }
    
    public RecyclerView.ViewHolder chooseDropTarget(RecyclerView.ViewHolder param1ViewHolder, List<RecyclerView.ViewHolder> param1List, int param1Int1, int param1Int2) {
      int i = param1ViewHolder.itemView.getWidth();
      int j = param1ViewHolder.itemView.getHeight();
      int k = param1Int1 - param1ViewHolder.itemView.getLeft();
      int m = param1Int2 - param1ViewHolder.itemView.getTop();
      int n = param1List.size();
      RecyclerView.ViewHolder viewHolder = null;
      int i1 = -1;
      for (byte b = 0; b < n; b++) {
        RecyclerView.ViewHolder viewHolder1 = param1List.get(b);
        RecyclerView.ViewHolder viewHolder2 = viewHolder;
        int i2 = i1;
        if (k > 0) {
          int i3 = viewHolder1.itemView.getRight() - param1Int1 + i;
          viewHolder2 = viewHolder;
          i2 = i1;
          if (i3 < 0) {
            viewHolder2 = viewHolder;
            i2 = i1;
            if (viewHolder1.itemView.getRight() > param1ViewHolder.itemView.getRight()) {
              i3 = Math.abs(i3);
              viewHolder2 = viewHolder;
              i2 = i1;
              if (i3 > i1) {
                viewHolder2 = viewHolder1;
                i2 = i3;
              } 
            } 
          } 
        } 
        viewHolder = viewHolder2;
        i1 = i2;
        if (k < 0) {
          int i3 = viewHolder1.itemView.getLeft() - param1Int1;
          viewHolder = viewHolder2;
          i1 = i2;
          if (i3 > 0) {
            viewHolder = viewHolder2;
            i1 = i2;
            if (viewHolder1.itemView.getLeft() < param1ViewHolder.itemView.getLeft()) {
              i3 = Math.abs(i3);
              viewHolder = viewHolder2;
              i1 = i2;
              if (i3 > i2) {
                viewHolder = viewHolder1;
                i1 = i3;
              } 
            } 
          } 
        } 
        viewHolder2 = viewHolder;
        i2 = i1;
        if (m < 0) {
          int i3 = viewHolder1.itemView.getTop() - param1Int2;
          viewHolder2 = viewHolder;
          i2 = i1;
          if (i3 > 0) {
            viewHolder2 = viewHolder;
            i2 = i1;
            if (viewHolder1.itemView.getTop() < param1ViewHolder.itemView.getTop()) {
              i3 = Math.abs(i3);
              viewHolder2 = viewHolder;
              i2 = i1;
              if (i3 > i1) {
                viewHolder2 = viewHolder1;
                i2 = i3;
              } 
            } 
          } 
        } 
        viewHolder = viewHolder2;
        i1 = i2;
        if (m > 0) {
          int i3 = viewHolder1.itemView.getBottom() - param1Int2 + j;
          viewHolder = viewHolder2;
          i1 = i2;
          if (i3 < 0) {
            viewHolder = viewHolder2;
            i1 = i2;
            if (viewHolder1.itemView.getBottom() > param1ViewHolder.itemView.getBottom()) {
              i3 = Math.abs(i3);
              viewHolder = viewHolder2;
              i1 = i2;
              if (i3 > i2) {
                i1 = i3;
                viewHolder = viewHolder1;
              } 
            } 
          } 
        } 
      } 
      return viewHolder;
    }
    
    public void clearView(RecyclerView param1RecyclerView, RecyclerView.ViewHolder param1ViewHolder) {
      sUICallback.clearView(param1ViewHolder.itemView);
    }
    
    public int convertToAbsoluteDirection(int param1Int1, int param1Int2) {
      int i = param1Int1 & 0x303030;
      if (i == 0)
        return param1Int1; 
      int j = param1Int1 & (i ^ 0xFFFFFFFF);
      if (param1Int2 == 0) {
        param1Int1 = i >> 2;
        param1Int2 = j;
        return param1Int2 | param1Int1;
      } 
      param1Int1 = i >> 1;
      param1Int2 = j | 0xFFCFCFCF & param1Int1;
      param1Int1 = (param1Int1 & 0x303030) >> 2;
      return param1Int2 | param1Int1;
    }
    
    final int getAbsoluteMovementFlags(RecyclerView param1RecyclerView, RecyclerView.ViewHolder param1ViewHolder) {
      return convertToAbsoluteDirection(getMovementFlags(param1RecyclerView, param1ViewHolder), ViewCompat.getLayoutDirection((View)param1RecyclerView));
    }
    
    public long getAnimationDuration(RecyclerView param1RecyclerView, int param1Int, float param1Float1, float param1Float2) {
      long l;
      RecyclerView.ItemAnimator itemAnimator = param1RecyclerView.getItemAnimator();
      if (itemAnimator == null) {
        if (param1Int == 8) {
          l = 200L;
        } else {
          l = 250L;
        } 
        return l;
      } 
      if (param1Int == 8) {
        l = itemAnimator.getMoveDuration();
      } else {
        l = itemAnimator.getRemoveDuration();
      } 
      return l;
    }
    
    public int getBoundingBoxMargin() {
      return 0;
    }
    
    public float getMoveThreshold(RecyclerView.ViewHolder param1ViewHolder) {
      return 0.5F;
    }
    
    public abstract int getMovementFlags(RecyclerView param1RecyclerView, RecyclerView.ViewHolder param1ViewHolder);
    
    public float getSwipeEscapeVelocity(float param1Float) {
      return param1Float;
    }
    
    public float getSwipeThreshold(RecyclerView.ViewHolder param1ViewHolder) {
      return 0.5F;
    }
    
    public float getSwipeVelocityThreshold(float param1Float) {
      return param1Float;
    }
    
    boolean hasDragFlag(RecyclerView param1RecyclerView, RecyclerView.ViewHolder param1ViewHolder) {
      boolean bool;
      if ((getAbsoluteMovementFlags(param1RecyclerView, param1ViewHolder) & 0xFF0000) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    boolean hasSwipeFlag(RecyclerView param1RecyclerView, RecyclerView.ViewHolder param1ViewHolder) {
      boolean bool;
      if ((getAbsoluteMovementFlags(param1RecyclerView, param1ViewHolder) & 0xFF00) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public int interpolateOutOfBoundsScroll(RecyclerView param1RecyclerView, int param1Int1, int param1Int2, int param1Int3, long param1Long) {
      int i = getMaxDragScroll(param1RecyclerView);
      param1Int3 = Math.abs(param1Int2);
      int j = (int)Math.signum(param1Int2);
      float f1 = param1Int3;
      float f2 = 1.0F;
      f1 = Math.min(1.0F, f1 * 1.0F / param1Int1);
      param1Int1 = (int)((j * i) * sDragViewScrollCapInterpolator.getInterpolation(f1));
      if (param1Long <= 2000L)
        f2 = (float)param1Long / 2000.0F; 
      param1Int3 = (int)(param1Int1 * sDragScrollInterpolator.getInterpolation(f2));
      param1Int1 = param1Int3;
      if (param1Int3 == 0)
        if (param1Int2 > 0) {
          param1Int1 = 1;
        } else {
          param1Int1 = -1;
        }  
      return param1Int1;
    }
    
    public boolean isItemViewSwipeEnabled() {
      return true;
    }
    
    public boolean isLongPressDragEnabled() {
      return true;
    }
    
    public void onChildDraw(Canvas param1Canvas, RecyclerView param1RecyclerView, RecyclerView.ViewHolder param1ViewHolder, float param1Float1, float param1Float2, int param1Int, boolean param1Boolean) {
      sUICallback.onDraw(param1Canvas, param1RecyclerView, param1ViewHolder.itemView, param1Float1, param1Float2, param1Int, param1Boolean);
    }
    
    public void onChildDrawOver(Canvas param1Canvas, RecyclerView param1RecyclerView, RecyclerView.ViewHolder param1ViewHolder, float param1Float1, float param1Float2, int param1Int, boolean param1Boolean) {
      sUICallback.onDrawOver(param1Canvas, param1RecyclerView, param1ViewHolder.itemView, param1Float1, param1Float2, param1Int, param1Boolean);
    }
    
    void onDraw(Canvas param1Canvas, RecyclerView param1RecyclerView, RecyclerView.ViewHolder param1ViewHolder, List<ItemTouchHelper.RecoverAnimation> param1List, int param1Int, float param1Float1, float param1Float2) {
      int i = param1List.size();
      int j;
      for (j = 0; j < i; j++) {
        ItemTouchHelper.RecoverAnimation recoverAnimation = param1List.get(j);
        recoverAnimation.update();
        int k = param1Canvas.save();
        onChildDraw(param1Canvas, param1RecyclerView, recoverAnimation.mViewHolder, recoverAnimation.mX, recoverAnimation.mY, recoverAnimation.mActionState, false);
        param1Canvas.restoreToCount(k);
      } 
      if (param1ViewHolder != null) {
        j = param1Canvas.save();
        onChildDraw(param1Canvas, param1RecyclerView, param1ViewHolder, param1Float1, param1Float2, param1Int, true);
        param1Canvas.restoreToCount(j);
      } 
    }
    
    void onDrawOver(Canvas param1Canvas, RecyclerView param1RecyclerView, RecyclerView.ViewHolder param1ViewHolder, List<ItemTouchHelper.RecoverAnimation> param1List, int param1Int, float param1Float1, float param1Float2) {
      int i = param1List.size();
      boolean bool = false;
      int j;
      for (j = 0; j < i; j++) {
        ItemTouchHelper.RecoverAnimation recoverAnimation = param1List.get(j);
        int k = param1Canvas.save();
        onChildDrawOver(param1Canvas, param1RecyclerView, recoverAnimation.mViewHolder, recoverAnimation.mX, recoverAnimation.mY, recoverAnimation.mActionState, false);
        param1Canvas.restoreToCount(k);
      } 
      if (param1ViewHolder != null) {
        j = param1Canvas.save();
        onChildDrawOver(param1Canvas, param1RecyclerView, param1ViewHolder, param1Float1, param1Float2, param1Int, true);
        param1Canvas.restoreToCount(j);
      } 
      param1Int = i - 1;
      j = bool;
      while (param1Int >= 0) {
        ItemTouchHelper.RecoverAnimation recoverAnimation = param1List.get(param1Int);
        boolean bool1 = recoverAnimation.mEnded;
        if (bool1 && !recoverAnimation.mIsPendingCleanup) {
          param1List.remove(param1Int);
        } else if (!bool1) {
          j = 1;
        } 
        param1Int--;
      } 
      if (j != 0)
        param1RecyclerView.invalidate(); 
    }
    
    public abstract boolean onMove(RecyclerView param1RecyclerView, RecyclerView.ViewHolder param1ViewHolder1, RecyclerView.ViewHolder param1ViewHolder2);
    
    public void onMoved(RecyclerView param1RecyclerView, RecyclerView.ViewHolder param1ViewHolder1, int param1Int1, RecyclerView.ViewHolder param1ViewHolder2, int param1Int2, int param1Int3, int param1Int4) {
      RecyclerView.LayoutManager layoutManager = param1RecyclerView.getLayoutManager();
      if (layoutManager instanceof ItemTouchHelper.ViewDropHandler) {
        ((ItemTouchHelper.ViewDropHandler)layoutManager).prepareForDrop(param1ViewHolder1.itemView, param1ViewHolder2.itemView, param1Int3, param1Int4);
        return;
      } 
      if (layoutManager.canScrollHorizontally()) {
        if (layoutManager.getDecoratedLeft(param1ViewHolder2.itemView) <= param1RecyclerView.getPaddingLeft())
          param1RecyclerView.scrollToPosition(param1Int2); 
        if (layoutManager.getDecoratedRight(param1ViewHolder2.itemView) >= param1RecyclerView.getWidth() - param1RecyclerView.getPaddingRight())
          param1RecyclerView.scrollToPosition(param1Int2); 
      } 
      if (layoutManager.canScrollVertically()) {
        if (layoutManager.getDecoratedTop(param1ViewHolder2.itemView) <= param1RecyclerView.getPaddingTop())
          param1RecyclerView.scrollToPosition(param1Int2); 
        if (layoutManager.getDecoratedBottom(param1ViewHolder2.itemView) >= param1RecyclerView.getHeight() - param1RecyclerView.getPaddingBottom())
          param1RecyclerView.scrollToPosition(param1Int2); 
      } 
    }
    
    public void onSelectedChanged(RecyclerView.ViewHolder param1ViewHolder, int param1Int) {
      if (param1ViewHolder != null)
        sUICallback.onSelected(param1ViewHolder.itemView); 
    }
    
    public abstract void onSwiped(RecyclerView.ViewHolder param1ViewHolder, int param1Int);
  }
  
  static final class null implements Interpolator {
    public float getInterpolation(float param1Float) {
      return param1Float * param1Float * param1Float * param1Float * param1Float;
    }
  }
  
  static final class null implements Interpolator {
    public float getInterpolation(float param1Float) {
      param1Float--;
      return param1Float * param1Float * param1Float * param1Float * param1Float + 1.0F;
    }
  }
  
  private class ItemTouchHelperGestureListener extends GestureDetector.SimpleOnGestureListener {
    public boolean onDown(MotionEvent param1MotionEvent) {
      return true;
    }
    
    public void onLongPress(MotionEvent param1MotionEvent) {
      View view = ItemTouchHelper.this.findChildView(param1MotionEvent);
      if (view != null) {
        RecyclerView.ViewHolder viewHolder = ItemTouchHelper.this.mRecyclerView.getChildViewHolder(view);
        if (viewHolder != null) {
          ItemTouchHelper itemTouchHelper = ItemTouchHelper.this;
          if (!itemTouchHelper.mCallback.hasDragFlag(itemTouchHelper.mRecyclerView, viewHolder))
            return; 
          int i = param1MotionEvent.getPointerId(0);
          int j = ItemTouchHelper.this.mActivePointerId;
          if (i == j) {
            i = param1MotionEvent.findPointerIndex(j);
            float f1 = param1MotionEvent.getX(i);
            float f2 = param1MotionEvent.getY(i);
            ItemTouchHelper itemTouchHelper1 = ItemTouchHelper.this;
            itemTouchHelper1.mInitialTouchX = f1;
            itemTouchHelper1.mInitialTouchY = f2;
            itemTouchHelper1.mDy = 0.0F;
            itemTouchHelper1.mDx = 0.0F;
            if (itemTouchHelper1.mCallback.isLongPressDragEnabled())
              ItemTouchHelper.this.select(viewHolder, 2); 
          } 
        } 
      } 
    }
  }
  
  private static class RecoverAnimation implements Animator.AnimatorListener {
    final int mActionState;
    
    final int mAnimationType;
    
    boolean mEnded = false;
    
    private float mFraction;
    
    public boolean mIsPendingCleanup;
    
    boolean mOverridden = false;
    
    final float mStartDx;
    
    final float mStartDy;
    
    final float mTargetX;
    
    final float mTargetY;
    
    private final ValueAnimator mValueAnimator;
    
    final RecyclerView.ViewHolder mViewHolder;
    
    float mX;
    
    float mY;
    
    RecoverAnimation(RecyclerView.ViewHolder param1ViewHolder, int param1Int1, int param1Int2, float param1Float1, float param1Float2, float param1Float3, float param1Float4) {
      this.mActionState = param1Int2;
      this.mAnimationType = param1Int1;
      this.mViewHolder = param1ViewHolder;
      this.mStartDx = param1Float1;
      this.mStartDy = param1Float2;
      this.mTargetX = param1Float3;
      this.mTargetY = param1Float4;
      ValueAnimator valueAnimator = ValueAnimator.ofFloat(new float[] { 0.0F, 1.0F });
      this.mValueAnimator = valueAnimator;
      valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator param2ValueAnimator) {
              ItemTouchHelper.RecoverAnimation.this.setFraction(param2ValueAnimator.getAnimatedFraction());
            }
          });
      valueAnimator.setTarget(param1ViewHolder.itemView);
      valueAnimator.addListener(this);
      setFraction(0.0F);
    }
    
    public void cancel() {
      this.mValueAnimator.cancel();
    }
    
    public void onAnimationCancel(Animator param1Animator) {
      setFraction(1.0F);
    }
    
    public void onAnimationEnd(Animator param1Animator) {
      if (!this.mEnded)
        this.mViewHolder.setIsRecyclable(true); 
      this.mEnded = true;
    }
    
    public void onAnimationRepeat(Animator param1Animator) {}
    
    public void onAnimationStart(Animator param1Animator) {}
    
    public void setDuration(long param1Long) {
      this.mValueAnimator.setDuration(param1Long);
    }
    
    public void setFraction(float param1Float) {
      this.mFraction = param1Float;
    }
    
    public void start() {
      this.mViewHolder.setIsRecyclable(false);
      this.mValueAnimator.start();
    }
    
    public void update() {
      float f1 = this.mStartDx;
      float f2 = this.mTargetX;
      if (f1 == f2) {
        this.mX = this.mViewHolder.itemView.getTranslationX();
      } else {
        this.mX = f1 + this.mFraction * (f2 - f1);
      } 
      f1 = this.mStartDy;
      f2 = this.mTargetY;
      if (f1 == f2) {
        this.mY = this.mViewHolder.itemView.getTranslationY();
      } else {
        this.mY = f1 + this.mFraction * (f2 - f1);
      } 
    }
  }
  
  class null implements ValueAnimator.AnimatorUpdateListener {
    public void onAnimationUpdate(ValueAnimator param1ValueAnimator) {
      this.this$0.setFraction(param1ValueAnimator.getAnimatedFraction());
    }
  }
  
  public static abstract class SimpleCallback extends Callback {
    private int mDefaultDragDirs;
    
    private int mDefaultSwipeDirs;
    
    public SimpleCallback(int param1Int1, int param1Int2) {
      this.mDefaultSwipeDirs = param1Int2;
      this.mDefaultDragDirs = param1Int1;
    }
    
    public int getDragDirs(RecyclerView param1RecyclerView, RecyclerView.ViewHolder param1ViewHolder) {
      return this.mDefaultDragDirs;
    }
    
    public int getMovementFlags(RecyclerView param1RecyclerView, RecyclerView.ViewHolder param1ViewHolder) {
      return ItemTouchHelper.Callback.makeMovementFlags(getDragDirs(param1RecyclerView, param1ViewHolder), getSwipeDirs(param1RecyclerView, param1ViewHolder));
    }
    
    public int getSwipeDirs(RecyclerView param1RecyclerView, RecyclerView.ViewHolder param1ViewHolder) {
      return this.mDefaultSwipeDirs;
    }
    
    public void setDefaultDragDirs(int param1Int) {
      this.mDefaultDragDirs = param1Int;
    }
    
    public void setDefaultSwipeDirs(int param1Int) {
      this.mDefaultSwipeDirs = param1Int;
    }
  }
  
  public static interface ViewDropHandler {
    void prepareForDrop(View param1View1, View param1View2, int param1Int1, int param1Int2);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/widget/helper/ItemTouchHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */