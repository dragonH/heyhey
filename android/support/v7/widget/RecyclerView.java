package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Observable;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.v4.os.TraceCompat;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.NestedScrollingChild2;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.ScrollingView;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v7.recyclerview.R;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.Display;
import android.view.FocusFinder;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.OverScroller;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecyclerView extends ViewGroup implements ScrollingView, NestedScrollingChild2 {
  static {
    CLIP_TO_PADDING_ATTR = new int[] { 16842987 };
    int i = Build.VERSION.SDK_INT;
    if (i == 18 || i == 19 || i == 20) {
      bool = true;
    } else {
      bool = false;
    } 
    FORCE_INVALIDATE_DISPLAY_LIST = bool;
    if (i >= 23) {
      bool = true;
    } else {
      bool = false;
    } 
    ALLOW_SIZE_IN_UNSPECIFIED_SPEC = bool;
    POST_UPDATES_ON_ANIMATION = true;
    if (i >= 21) {
      bool = true;
    } else {
      bool = false;
    } 
    ALLOW_THREAD_GAP_WORK = bool;
    FORCE_ABS_FOCUS_SEARCH_DIRECTION = false;
    IGNORE_DETACHED_FOCUSED_CHILD = false;
    Class<int> clazz = int.class;
    LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE = new Class[] { Context.class, AttributeSet.class, clazz, clazz };
    sQuinticInterpolator = new Interpolator() {
        public float getInterpolation(float param1Float) {
          param1Float--;
          return param1Float * param1Float * param1Float * param1Float * param1Float + 1.0F;
        }
      };
  }
  
  public RecyclerView(Context paramContext) {
    this(paramContext, null);
  }
  
  public RecyclerView(Context paramContext, @Nullable AttributeSet paramAttributeSet) {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public RecyclerView(Context paramContext, @Nullable AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    GapWorker.LayoutPrefetchRegistryImpl layoutPrefetchRegistryImpl;
    boolean bool1;
    this.mObserver = new RecyclerViewDataObserver();
    this.mRecycler = new Recycler();
    this.mViewInfoStore = new ViewInfoStore();
    this.mUpdateChildViewsRunnable = new Runnable() {
        public void run() {
          RecyclerView recyclerView = RecyclerView.this;
          if (recyclerView.mFirstLayoutComplete && !recyclerView.isLayoutRequested()) {
            recyclerView = RecyclerView.this;
            if (!recyclerView.mIsAttached) {
              recyclerView.requestLayout();
              return;
            } 
            if (recyclerView.mLayoutFrozen) {
              recyclerView.mLayoutRequestEaten = true;
              return;
            } 
            recyclerView.consumePendingUpdateOperations();
          } 
        }
      };
    this.mTempRect = new Rect();
    this.mTempRect2 = new Rect();
    this.mTempRectF = new RectF();
    this.mItemDecorations = new ArrayList<ItemDecoration>();
    this.mOnItemTouchListeners = new ArrayList<OnItemTouchListener>();
    this.mEatRequestLayout = 0;
    this.mDataSetHasChangedAfterLayout = false;
    this.mLayoutOrScrollCounter = 0;
    this.mDispatchScrollCounter = 0;
    this.mItemAnimator = new DefaultItemAnimator();
    this.mScrollState = 0;
    this.mScrollPointerId = -1;
    this.mScaledHorizontalScrollFactor = Float.MIN_VALUE;
    this.mScaledVerticalScrollFactor = Float.MIN_VALUE;
    boolean bool = true;
    this.mPreserveFocusAfterLayout = true;
    this.mViewFlinger = new ViewFlinger();
    if (ALLOW_THREAD_GAP_WORK) {
      layoutPrefetchRegistryImpl = new GapWorker.LayoutPrefetchRegistryImpl();
    } else {
      layoutPrefetchRegistryImpl = null;
    } 
    this.mPrefetchRegistry = layoutPrefetchRegistryImpl;
    this.mState = new State();
    this.mItemsAddedOrRemoved = false;
    this.mItemsChanged = false;
    this.mItemAnimatorListener = new ItemAnimatorRestoreListener();
    this.mPostedAnimatorRunner = false;
    this.mMinMaxLayoutPositions = new int[2];
    this.mScrollOffset = new int[2];
    this.mScrollConsumed = new int[2];
    this.mNestedOffsets = new int[2];
    this.mPendingAccessibilityImportanceChange = new ArrayList<ViewHolder>();
    this.mItemAnimatorRunner = new Runnable() {
        public void run() {
          RecyclerView.ItemAnimator itemAnimator = RecyclerView.this.mItemAnimator;
          if (itemAnimator != null)
            itemAnimator.runPendingAnimations(); 
          RecyclerView.this.mPostedAnimatorRunner = false;
        }
      };
    this.mViewInfoProcessCallback = new ViewInfoStore.ProcessCallback() {
        public void processAppeared(RecyclerView.ViewHolder param1ViewHolder, RecyclerView.ItemAnimator.ItemHolderInfo param1ItemHolderInfo1, RecyclerView.ItemAnimator.ItemHolderInfo param1ItemHolderInfo2) {
          RecyclerView.this.animateAppearance(param1ViewHolder, param1ItemHolderInfo1, param1ItemHolderInfo2);
        }
        
        public void processDisappeared(RecyclerView.ViewHolder param1ViewHolder, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo param1ItemHolderInfo1, @Nullable RecyclerView.ItemAnimator.ItemHolderInfo param1ItemHolderInfo2) {
          RecyclerView.this.mRecycler.unscrapView(param1ViewHolder);
          RecyclerView.this.animateDisappearance(param1ViewHolder, param1ItemHolderInfo1, param1ItemHolderInfo2);
        }
        
        public void processPersistent(RecyclerView.ViewHolder param1ViewHolder, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo param1ItemHolderInfo1, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo param1ItemHolderInfo2) {
          param1ViewHolder.setIsRecyclable(false);
          RecyclerView recyclerView = RecyclerView.this;
          if (recyclerView.mDataSetHasChangedAfterLayout) {
            if (recyclerView.mItemAnimator.animateChange(param1ViewHolder, param1ViewHolder, param1ItemHolderInfo1, param1ItemHolderInfo2))
              RecyclerView.this.postAnimationRunner(); 
          } else if (recyclerView.mItemAnimator.animatePersistence(param1ViewHolder, param1ItemHolderInfo1, param1ItemHolderInfo2)) {
            RecyclerView.this.postAnimationRunner();
          } 
        }
        
        public void unused(RecyclerView.ViewHolder param1ViewHolder) {
          RecyclerView recyclerView = RecyclerView.this;
          recyclerView.mLayout.removeAndRecycleView(param1ViewHolder.itemView, recyclerView.mRecycler);
        }
      };
    if (paramAttributeSet != null) {
      TypedArray typedArray = paramContext.obtainStyledAttributes(paramAttributeSet, CLIP_TO_PADDING_ATTR, paramInt, 0);
      this.mClipToPadding = typedArray.getBoolean(0, true);
      typedArray.recycle();
    } else {
      this.mClipToPadding = true;
    } 
    setScrollContainer(true);
    setFocusableInTouchMode(true);
    ViewConfiguration viewConfiguration = ViewConfiguration.get(paramContext);
    this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
    this.mScaledHorizontalScrollFactor = ViewConfigurationCompat.getScaledHorizontalScrollFactor(viewConfiguration, paramContext);
    this.mScaledVerticalScrollFactor = ViewConfigurationCompat.getScaledVerticalScrollFactor(viewConfiguration, paramContext);
    this.mMinFlingVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
    this.mMaxFlingVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
    if (getOverScrollMode() == 2) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    setWillNotDraw(bool1);
    this.mItemAnimator.setListener(this.mItemAnimatorListener);
    initAdapterManager();
    initChildrenHelper();
    if (ViewCompat.getImportantForAccessibility((View)this) == 0)
      ViewCompat.setImportantForAccessibility((View)this, 1); 
    this.mAccessibilityManager = (AccessibilityManager)getContext().getSystemService("accessibility");
    setAccessibilityDelegateCompat(new RecyclerViewAccessibilityDelegate(this));
    if (paramAttributeSet != null) {
      TypedArray typedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.RecyclerView, paramInt, 0);
      String str = typedArray.getString(R.styleable.RecyclerView_layoutManager);
      if (typedArray.getInt(R.styleable.RecyclerView_android_descendantFocusability, -1) == -1)
        setDescendantFocusability(262144); 
      bool1 = typedArray.getBoolean(R.styleable.RecyclerView_fastScrollEnabled, false);
      this.mEnableFastScroller = bool1;
      if (bool1)
        initFastScroller((StateListDrawable)typedArray.getDrawable(R.styleable.RecyclerView_fastScrollVerticalThumbDrawable), typedArray.getDrawable(R.styleable.RecyclerView_fastScrollVerticalTrackDrawable), (StateListDrawable)typedArray.getDrawable(R.styleable.RecyclerView_fastScrollHorizontalThumbDrawable), typedArray.getDrawable(R.styleable.RecyclerView_fastScrollHorizontalTrackDrawable)); 
      typedArray.recycle();
      createLayoutManager(paramContext, str, paramAttributeSet, paramInt, 0);
      bool1 = bool;
      if (Build.VERSION.SDK_INT >= 21) {
        TypedArray typedArray1 = paramContext.obtainStyledAttributes(paramAttributeSet, NESTED_SCROLLING_ATTRS, paramInt, 0);
        bool1 = typedArray1.getBoolean(0, true);
        typedArray1.recycle();
      } 
    } else {
      setDescendantFocusability(262144);
      bool1 = bool;
    } 
    setNestedScrollingEnabled(bool1);
  }
  
  private void addAnimatingView(ViewHolder paramViewHolder) {
    boolean bool;
    View view = paramViewHolder.itemView;
    if (view.getParent() == this) {
      bool = true;
    } else {
      bool = false;
    } 
    this.mRecycler.unscrapView(getChildViewHolder(view));
    if (paramViewHolder.isTmpDetached()) {
      this.mChildHelper.attachViewToParent(view, -1, view.getLayoutParams(), true);
    } else if (!bool) {
      this.mChildHelper.addView(view, true);
    } else {
      this.mChildHelper.hide(view);
    } 
  }
  
  private void animateChange(@NonNull ViewHolder paramViewHolder1, @NonNull ViewHolder paramViewHolder2, @NonNull ItemAnimator.ItemHolderInfo paramItemHolderInfo1, @NonNull ItemAnimator.ItemHolderInfo paramItemHolderInfo2, boolean paramBoolean1, boolean paramBoolean2) {
    paramViewHolder1.setIsRecyclable(false);
    if (paramBoolean1)
      addAnimatingView(paramViewHolder1); 
    if (paramViewHolder1 != paramViewHolder2) {
      if (paramBoolean2)
        addAnimatingView(paramViewHolder2); 
      paramViewHolder1.mShadowedHolder = paramViewHolder2;
      addAnimatingView(paramViewHolder1);
      this.mRecycler.unscrapView(paramViewHolder1);
      paramViewHolder2.setIsRecyclable(false);
      paramViewHolder2.mShadowingHolder = paramViewHolder1;
    } 
    if (this.mItemAnimator.animateChange(paramViewHolder1, paramViewHolder2, paramItemHolderInfo1, paramItemHolderInfo2))
      postAnimationRunner(); 
  }
  
  private void cancelTouch() {
    resetTouch();
    setScrollState(0);
  }
  
  static void clearNestedRecyclerViewIfNotNested(@NonNull ViewHolder paramViewHolder) {
    WeakReference<RecyclerView> weakReference = paramViewHolder.mNestedRecyclerView;
    if (weakReference != null) {
      View view = (View)weakReference.get();
      while (view != null) {
        if (view == paramViewHolder.itemView)
          return; 
        ViewParent viewParent = view.getParent();
        if (viewParent instanceof View) {
          View view1 = (View)viewParent;
          continue;
        } 
        viewParent = null;
      } 
      paramViewHolder.mNestedRecyclerView = null;
    } 
  }
  
  private void createLayoutManager(Context paramContext, String paramString, AttributeSet paramAttributeSet, int paramInt1, int paramInt2) {
    if (paramString != null) {
      paramString = paramString.trim();
      if (!paramString.isEmpty()) {
        String str = getFullClassName(paramContext, paramString);
        try {
          Object[] arrayOfObject;
          IllegalStateException illegalStateException;
          if (isInEditMode()) {
            classLoader = getClass().getClassLoader();
          } else {
            classLoader = paramContext.getClassLoader();
          } 
          Class<? extends LayoutManager> clazz = classLoader.loadClass(str).asSubclass(LayoutManager.class);
          ClassLoader classLoader = null;
          try {
            Constructor<? extends LayoutManager> constructor2 = clazz.getConstructor(LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE);
            arrayOfObject = new Object[] { paramContext, paramAttributeSet, Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) };
            Constructor<? extends LayoutManager> constructor1 = constructor2;
          } catch (NoSuchMethodException noSuchMethodException1) {
            try {
              Constructor<? extends LayoutManager> constructor = clazz.getConstructor(new Class[0]);
              constructor.setAccessible(true);
              setLayoutManager(constructor.newInstance(arrayOfObject));
            } catch (NoSuchMethodException noSuchMethodException) {
              noSuchMethodException.initCause(noSuchMethodException1);
              illegalStateException = new IllegalStateException();
              StringBuilder stringBuilder = new StringBuilder();
              this();
              stringBuilder.append(paramAttributeSet.getPositionDescription());
              stringBuilder.append(": Error creating LayoutManager ");
              stringBuilder.append(str);
              this(stringBuilder.toString(), noSuchMethodException);
              throw illegalStateException;
            } 
          } 
          noSuchMethodException.setAccessible(true);
          setLayoutManager(noSuchMethodException.newInstance((Object[])illegalStateException));
        } catch (ClassNotFoundException classNotFoundException) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append(paramAttributeSet.getPositionDescription());
          stringBuilder.append(": Unable to find LayoutManager ");
          stringBuilder.append(str);
          throw new IllegalStateException(stringBuilder.toString(), classNotFoundException);
        } catch (InvocationTargetException invocationTargetException) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append(paramAttributeSet.getPositionDescription());
          stringBuilder.append(": Could not instantiate the LayoutManager: ");
          stringBuilder.append(str);
          throw new IllegalStateException(stringBuilder.toString(), invocationTargetException);
        } catch (InstantiationException instantiationException) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append(paramAttributeSet.getPositionDescription());
          stringBuilder.append(": Could not instantiate the LayoutManager: ");
          stringBuilder.append(str);
          throw new IllegalStateException(stringBuilder.toString(), instantiationException);
        } catch (IllegalAccessException illegalAccessException) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append(paramAttributeSet.getPositionDescription());
          stringBuilder.append(": Cannot access non-public constructor ");
          stringBuilder.append(str);
          throw new IllegalStateException(stringBuilder.toString(), illegalAccessException);
        } catch (ClassCastException classCastException) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append(paramAttributeSet.getPositionDescription());
          stringBuilder.append(": Class is not a LayoutManager ");
          stringBuilder.append(str);
          throw new IllegalStateException(stringBuilder.toString(), classCastException);
        } 
      } 
    } 
  }
  
  private boolean didChildRangeChange(int paramInt1, int paramInt2) {
    findMinMaxChildLayoutPositions(this.mMinMaxLayoutPositions);
    int[] arrayOfInt = this.mMinMaxLayoutPositions;
    boolean bool = false;
    if (arrayOfInt[0] != paramInt1 || arrayOfInt[1] != paramInt2)
      bool = true; 
    return bool;
  }
  
  private void dispatchContentChangedIfNecessary() {
    int i = this.mEatenAccessibilityChangeFlags;
    this.mEatenAccessibilityChangeFlags = 0;
    if (i != 0 && isAccessibilityEnabled()) {
      AccessibilityEvent accessibilityEvent = AccessibilityEvent.obtain();
      accessibilityEvent.setEventType(2048);
      AccessibilityEventCompat.setContentChangeTypes(accessibilityEvent, i);
      sendAccessibilityEventUnchecked(accessibilityEvent);
    } 
  }
  
  private void dispatchLayoutStep1() {
    State state = this.mState;
    boolean bool = true;
    state.assertLayoutStep(1);
    fillRemainingScrollValues(this.mState);
    this.mState.mIsMeasuring = false;
    eatRequestLayout();
    this.mViewInfoStore.clear();
    onEnterLayoutOrScroll();
    processAdapterUpdatesAndSetAnimationFlags();
    saveFocusInfo();
    state = this.mState;
    if (!state.mRunSimpleAnimations || !this.mItemsChanged)
      bool = false; 
    state.mTrackOldChangeHolders = bool;
    this.mItemsChanged = false;
    this.mItemsAddedOrRemoved = false;
    state.mInPreLayout = state.mRunPredictiveAnimations;
    state.mItemCount = this.mAdapter.getItemCount();
    findMinMaxChildLayoutPositions(this.mMinMaxLayoutPositions);
    if (this.mState.mRunSimpleAnimations) {
      int i = this.mChildHelper.getChildCount();
      for (byte b = 0; b < i; b++) {
        ViewHolder viewHolder = getChildViewHolderInt(this.mChildHelper.getChildAt(b));
        if (!viewHolder.shouldIgnore() && (!viewHolder.isInvalid() || this.mAdapter.hasStableIds())) {
          ItemAnimator.ItemHolderInfo itemHolderInfo = this.mItemAnimator.recordPreLayoutInformation(this.mState, viewHolder, ItemAnimator.buildAdapterChangeFlagsForAnimations(viewHolder), viewHolder.getUnmodifiedPayloads());
          this.mViewInfoStore.addToPreLayout(viewHolder, itemHolderInfo);
          if (this.mState.mTrackOldChangeHolders && viewHolder.isUpdated() && !viewHolder.isRemoved() && !viewHolder.shouldIgnore() && !viewHolder.isInvalid()) {
            long l = getChangedHolderKey(viewHolder);
            this.mViewInfoStore.addToOldChangeHolders(l, viewHolder);
          } 
        } 
      } 
    } 
    if (this.mState.mRunPredictiveAnimations) {
      saveOldPositions();
      state = this.mState;
      bool = state.mStructureChanged;
      state.mStructureChanged = false;
      this.mLayout.onLayoutChildren(this.mRecycler, state);
      this.mState.mStructureChanged = bool;
      for (byte b = 0; b < this.mChildHelper.getChildCount(); b++) {
        ViewHolder viewHolder = getChildViewHolderInt(this.mChildHelper.getChildAt(b));
        if (!viewHolder.shouldIgnore() && !this.mViewInfoStore.isInPreLayout(viewHolder)) {
          int j = ItemAnimator.buildAdapterChangeFlagsForAnimations(viewHolder);
          bool = viewHolder.hasAnyOfTheFlags(8192);
          int i = j;
          if (!bool)
            i = j | 0x1000; 
          ItemAnimator.ItemHolderInfo itemHolderInfo = this.mItemAnimator.recordPreLayoutInformation(this.mState, viewHolder, i, viewHolder.getUnmodifiedPayloads());
          if (bool) {
            recordAnimationInfoIfBouncedHiddenView(viewHolder, itemHolderInfo);
          } else {
            this.mViewInfoStore.addToAppearedInPreLayoutHolders(viewHolder, itemHolderInfo);
          } 
        } 
      } 
      clearOldPositions();
    } else {
      clearOldPositions();
    } 
    onExitLayoutOrScroll();
    resumeRequestLayout(false);
    this.mState.mLayoutStep = 2;
  }
  
  private void dispatchLayoutStep2() {
    boolean bool;
    eatRequestLayout();
    onEnterLayoutOrScroll();
    this.mState.assertLayoutStep(6);
    this.mAdapterHelper.consumeUpdatesInOnePass();
    this.mState.mItemCount = this.mAdapter.getItemCount();
    State state = this.mState;
    state.mDeletedInvisibleItemCountSincePreviousLayout = 0;
    state.mInPreLayout = false;
    this.mLayout.onLayoutChildren(this.mRecycler, state);
    state = this.mState;
    state.mStructureChanged = false;
    this.mPendingSavedState = null;
    if (state.mRunSimpleAnimations && this.mItemAnimator != null) {
      bool = true;
    } else {
      bool = false;
    } 
    state.mRunSimpleAnimations = bool;
    state.mLayoutStep = 4;
    onExitLayoutOrScroll();
    resumeRequestLayout(false);
  }
  
  private void dispatchLayoutStep3() {
    this.mState.assertLayoutStep(4);
    eatRequestLayout();
    onEnterLayoutOrScroll();
    State state = this.mState;
    state.mLayoutStep = 1;
    if (state.mRunSimpleAnimations) {
      for (int i = this.mChildHelper.getChildCount() - 1; i >= 0; i--) {
        ViewHolder viewHolder = getChildViewHolderInt(this.mChildHelper.getChildAt(i));
        if (!viewHolder.shouldIgnore()) {
          long l = getChangedHolderKey(viewHolder);
          ItemAnimator.ItemHolderInfo itemHolderInfo = this.mItemAnimator.recordPostLayoutInformation(this.mState, viewHolder);
          ViewHolder viewHolder1 = this.mViewInfoStore.getFromOldChangeHolders(l);
          if (viewHolder1 != null && !viewHolder1.shouldIgnore()) {
            boolean bool1 = this.mViewInfoStore.isDisappearing(viewHolder1);
            boolean bool2 = this.mViewInfoStore.isDisappearing(viewHolder);
            if (bool1 && viewHolder1 == viewHolder) {
              this.mViewInfoStore.addToPostLayout(viewHolder, itemHolderInfo);
            } else {
              ItemAnimator.ItemHolderInfo itemHolderInfo1 = this.mViewInfoStore.popFromPreLayout(viewHolder1);
              this.mViewInfoStore.addToPostLayout(viewHolder, itemHolderInfo);
              itemHolderInfo = this.mViewInfoStore.popFromPostLayout(viewHolder);
              if (itemHolderInfo1 == null) {
                handleMissingPreInfoForChangeError(l, viewHolder, viewHolder1);
              } else {
                animateChange(viewHolder1, viewHolder, itemHolderInfo1, itemHolderInfo, bool1, bool2);
              } 
            } 
          } else {
            this.mViewInfoStore.addToPostLayout(viewHolder, itemHolderInfo);
          } 
        } 
      } 
      this.mViewInfoStore.process(this.mViewInfoProcessCallback);
    } 
    this.mLayout.removeAndRecycleScrapInt(this.mRecycler);
    state = this.mState;
    state.mPreviousLayoutItemCount = state.mItemCount;
    this.mDataSetHasChangedAfterLayout = false;
    state.mRunSimpleAnimations = false;
    state.mRunPredictiveAnimations = false;
    this.mLayout.mRequestedSimpleAnimations = false;
    ArrayList<ViewHolder> arrayList = this.mRecycler.mChangedScrap;
    if (arrayList != null)
      arrayList.clear(); 
    LayoutManager layoutManager = this.mLayout;
    if (layoutManager.mPrefetchMaxObservedInInitialPrefetch) {
      layoutManager.mPrefetchMaxCountObserved = 0;
      layoutManager.mPrefetchMaxObservedInInitialPrefetch = false;
      this.mRecycler.updateViewCacheSize();
    } 
    this.mLayout.onLayoutCompleted(this.mState);
    onExitLayoutOrScroll();
    resumeRequestLayout(false);
    this.mViewInfoStore.clear();
    int[] arrayOfInt = this.mMinMaxLayoutPositions;
    if (didChildRangeChange(arrayOfInt[0], arrayOfInt[1]))
      dispatchOnScrolled(0, 0); 
    recoverFocusFromState();
    resetFocusInfo();
  }
  
  private boolean dispatchOnItemTouch(MotionEvent paramMotionEvent) {
    int i = paramMotionEvent.getAction();
    OnItemTouchListener onItemTouchListener = this.mActiveOnItemTouchListener;
    if (onItemTouchListener != null)
      if (i == 0) {
        this.mActiveOnItemTouchListener = null;
      } else {
        onItemTouchListener.onTouchEvent(this, paramMotionEvent);
        if (i == 3 || i == 1)
          this.mActiveOnItemTouchListener = null; 
        return true;
      }  
    if (i != 0) {
      int j = this.mOnItemTouchListeners.size();
      for (i = 0; i < j; i++) {
        onItemTouchListener = this.mOnItemTouchListeners.get(i);
        if (onItemTouchListener.onInterceptTouchEvent(this, paramMotionEvent)) {
          this.mActiveOnItemTouchListener = onItemTouchListener;
          return true;
        } 
      } 
    } 
    return false;
  }
  
  private boolean dispatchOnItemTouchIntercept(MotionEvent paramMotionEvent) {
    int i = paramMotionEvent.getAction();
    if (i == 3 || i == 0)
      this.mActiveOnItemTouchListener = null; 
    int j = this.mOnItemTouchListeners.size();
    for (byte b = 0; b < j; b++) {
      OnItemTouchListener onItemTouchListener = this.mOnItemTouchListeners.get(b);
      if (onItemTouchListener.onInterceptTouchEvent(this, paramMotionEvent) && i != 3) {
        this.mActiveOnItemTouchListener = onItemTouchListener;
        return true;
      } 
    } 
    return false;
  }
  
  private void findMinMaxChildLayoutPositions(int[] paramArrayOfint) {
    int i = this.mChildHelper.getChildCount();
    if (i == 0) {
      paramArrayOfint[0] = -1;
      paramArrayOfint[1] = -1;
      return;
    } 
    int j = Integer.MAX_VALUE;
    int k = Integer.MIN_VALUE;
    byte b = 0;
    while (b < i) {
      int m;
      ViewHolder viewHolder = getChildViewHolderInt(this.mChildHelper.getChildAt(b));
      if (viewHolder.shouldIgnore()) {
        m = k;
      } else {
        int n = viewHolder.getLayoutPosition();
        int i1 = j;
        if (n < j)
          i1 = n; 
        j = i1;
        m = k;
        if (n > k) {
          m = n;
          j = i1;
        } 
      } 
      b++;
      k = m;
    } 
    paramArrayOfint[0] = j;
    paramArrayOfint[1] = k;
  }
  
  @Nullable
  static RecyclerView findNestedRecyclerView(@NonNull View paramView) {
    if (!(paramView instanceof ViewGroup))
      return null; 
    if (paramView instanceof RecyclerView)
      return (RecyclerView)paramView; 
    ViewGroup viewGroup = (ViewGroup)paramView;
    int i = viewGroup.getChildCount();
    for (byte b = 0; b < i; b++) {
      RecyclerView recyclerView = findNestedRecyclerView(viewGroup.getChildAt(b));
      if (recyclerView != null)
        return recyclerView; 
    } 
    return null;
  }
  
  @Nullable
  private View findNextViewToFocus() {
    State state = this.mState;
    int i = state.mFocusedItemPosition;
    if (i == -1)
      i = 0; 
    int j = state.getItemCount();
    for (int k = i; k < j; k++) {
      ViewHolder viewHolder = findViewHolderForAdapterPosition(k);
      if (viewHolder == null)
        break; 
      if (viewHolder.itemView.hasFocusable())
        return viewHolder.itemView; 
    } 
    for (i = Math.min(j, i) - 1; i >= 0; i--) {
      ViewHolder viewHolder = findViewHolderForAdapterPosition(i);
      if (viewHolder == null)
        return null; 
      if (viewHolder.itemView.hasFocusable())
        return viewHolder.itemView; 
    } 
    return null;
  }
  
  static ViewHolder getChildViewHolderInt(View paramView) {
    return (paramView == null) ? null : ((LayoutParams)paramView.getLayoutParams()).mViewHolder;
  }
  
  static void getDecoratedBoundsWithMarginsInt(View paramView, Rect paramRect) {
    LayoutParams layoutParams = (LayoutParams)paramView.getLayoutParams();
    Rect rect = layoutParams.mDecorInsets;
    paramRect.set(paramView.getLeft() - rect.left - layoutParams.leftMargin, paramView.getTop() - rect.top - layoutParams.topMargin, paramView.getRight() + rect.right + layoutParams.rightMargin, paramView.getBottom() + rect.bottom + layoutParams.bottomMargin);
  }
  
  private int getDeepestFocusedViewWithId(View paramView) {
    int i = paramView.getId();
    while (!paramView.isFocused() && paramView instanceof ViewGroup && paramView.hasFocus()) {
      View view = ((ViewGroup)paramView).getFocusedChild();
      paramView = view;
      if (view.getId() != -1) {
        i = view.getId();
        paramView = view;
      } 
    } 
    return i;
  }
  
  private String getFullClassName(Context paramContext, String paramString) {
    if (paramString.charAt(0) == '.') {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(paramContext.getPackageName());
      stringBuilder1.append(paramString);
      return stringBuilder1.toString();
    } 
    if (paramString.contains("."))
      return paramString; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(RecyclerView.class.getPackage().getName());
    stringBuilder.append('.');
    stringBuilder.append(paramString);
    return stringBuilder.toString();
  }
  
  private NestedScrollingChildHelper getScrollingChildHelper() {
    if (this.mScrollingChildHelper == null)
      this.mScrollingChildHelper = new NestedScrollingChildHelper((View)this); 
    return this.mScrollingChildHelper;
  }
  
  private void handleMissingPreInfoForChangeError(long paramLong, ViewHolder paramViewHolder1, ViewHolder paramViewHolder2) {
    StringBuilder stringBuilder1;
    int i = this.mChildHelper.getChildCount();
    for (byte b = 0; b < i; b++) {
      ViewHolder viewHolder = getChildViewHolderInt(this.mChildHelper.getChildAt(b));
      if (viewHolder != paramViewHolder1 && getChangedHolderKey(viewHolder) == paramLong) {
        Adapter adapter = this.mAdapter;
        if (adapter != null && adapter.hasStableIds()) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Two different ViewHolders have the same stable ID. Stable IDs in your adapter MUST BE unique and SHOULD NOT change.\n ViewHolder 1:");
          stringBuilder.append(viewHolder);
          stringBuilder.append(" \n View Holder 2:");
          stringBuilder.append(paramViewHolder1);
          stringBuilder.append(exceptionLabel());
          throw new IllegalStateException(stringBuilder.toString());
        } 
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Two different ViewHolders have the same change ID. This might happen due to inconsistent Adapter update events or if the LayoutManager lays out the same View multiple times.\n ViewHolder 1:");
        stringBuilder1.append(viewHolder);
        stringBuilder1.append(" \n View Holder 2:");
        stringBuilder1.append(paramViewHolder1);
        stringBuilder1.append(exceptionLabel());
        throw new IllegalStateException(stringBuilder1.toString());
      } 
    } 
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append("Problem while matching changed view holders with the newones. The pre-layout information for the change holder ");
    stringBuilder2.append(stringBuilder1);
    stringBuilder2.append(" cannot be found but it is necessary for ");
    stringBuilder2.append(paramViewHolder1);
    stringBuilder2.append(exceptionLabel());
    Log.e("RecyclerView", stringBuilder2.toString());
  }
  
  private boolean hasUpdatedView() {
    int i = this.mChildHelper.getChildCount();
    for (byte b = 0; b < i; b++) {
      ViewHolder viewHolder = getChildViewHolderInt(this.mChildHelper.getChildAt(b));
      if (viewHolder != null && !viewHolder.shouldIgnore() && viewHolder.isUpdated())
        return true; 
    } 
    return false;
  }
  
  private void initChildrenHelper() {
    this.mChildHelper = new ChildHelper(new ChildHelper.Callback() {
          public void addView(View param1View, int param1Int) {
            RecyclerView.this.addView(param1View, param1Int);
            RecyclerView.this.dispatchChildAttached(param1View);
          }
          
          public void attachViewToParent(View param1View, int param1Int, ViewGroup.LayoutParams param1LayoutParams) {
            StringBuilder stringBuilder;
            RecyclerView.ViewHolder viewHolder = RecyclerView.getChildViewHolderInt(param1View);
            if (viewHolder != null)
              if (viewHolder.isTmpDetached() || viewHolder.shouldIgnore()) {
                viewHolder.clearTmpDetachFlag();
              } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append("Called attach on a child which is not detached: ");
                stringBuilder.append(viewHolder);
                stringBuilder.append(RecyclerView.this.exceptionLabel());
                throw new IllegalArgumentException(stringBuilder.toString());
              }  
            RecyclerView.this.attachViewToParent((View)stringBuilder, param1Int, param1LayoutParams);
          }
          
          public void detachViewFromParent(int param1Int) {
            View view = getChildAt(param1Int);
            if (view != null) {
              RecyclerView.ViewHolder viewHolder = RecyclerView.getChildViewHolderInt(view);
              if (viewHolder != null)
                if (!viewHolder.isTmpDetached() || viewHolder.shouldIgnore()) {
                  viewHolder.addFlags(256);
                } else {
                  StringBuilder stringBuilder = new StringBuilder();
                  stringBuilder.append("called detach on an already detached child ");
                  stringBuilder.append(viewHolder);
                  stringBuilder.append(RecyclerView.this.exceptionLabel());
                  throw new IllegalArgumentException(stringBuilder.toString());
                }  
            } 
            RecyclerView.this.detachViewFromParent(param1Int);
          }
          
          public View getChildAt(int param1Int) {
            return RecyclerView.this.getChildAt(param1Int);
          }
          
          public int getChildCount() {
            return RecyclerView.this.getChildCount();
          }
          
          public RecyclerView.ViewHolder getChildViewHolder(View param1View) {
            return RecyclerView.getChildViewHolderInt(param1View);
          }
          
          public int indexOfChild(View param1View) {
            return RecyclerView.this.indexOfChild(param1View);
          }
          
          public void onEnteredHiddenState(View param1View) {
            RecyclerView.ViewHolder viewHolder = RecyclerView.getChildViewHolderInt(param1View);
            if (viewHolder != null)
              viewHolder.onEnteredHiddenState(RecyclerView.this); 
          }
          
          public void onLeftHiddenState(View param1View) {
            RecyclerView.ViewHolder viewHolder = RecyclerView.getChildViewHolderInt(param1View);
            if (viewHolder != null)
              viewHolder.onLeftHiddenState(RecyclerView.this); 
          }
          
          public void removeAllViews() {
            int i = getChildCount();
            for (byte b = 0; b < i; b++) {
              View view = getChildAt(b);
              RecyclerView.this.dispatchChildDetached(view);
              view.clearAnimation();
            } 
            RecyclerView.this.removeAllViews();
          }
          
          public void removeViewAt(int param1Int) {
            View view = RecyclerView.this.getChildAt(param1Int);
            if (view != null) {
              RecyclerView.this.dispatchChildDetached(view);
              view.clearAnimation();
            } 
            RecyclerView.this.removeViewAt(param1Int);
          }
        });
  }
  
  private boolean isPreferredNextFocus(View paramView1, View paramView2, int paramInt) {
    byte b = 0;
    if (paramView2 == null || paramView2 == this)
      return false; 
    if (paramView1 == null)
      return true; 
    if (paramInt == 2 || paramInt == 1) {
      byte b1;
      if (this.mLayout.getLayoutDirection() == 1) {
        b1 = 1;
      } else {
        b1 = 0;
      } 
      if (paramInt == 2)
        b = 1; 
      if ((b ^ b1) != 0) {
        b1 = 66;
      } else {
        b1 = 17;
      } 
      return isPreferredNextFocusAbsolute(paramView1, paramView2, b1) ? true : ((paramInt == 2) ? isPreferredNextFocusAbsolute(paramView1, paramView2, 130) : isPreferredNextFocusAbsolute(paramView1, paramView2, 33));
    } 
    return isPreferredNextFocusAbsolute(paramView1, paramView2, paramInt);
  }
  
  private boolean isPreferredNextFocusAbsolute(View paramView1, View paramView2, int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mTempRect : Landroid/graphics/Rect;
    //   4: astore #4
    //   6: aload_1
    //   7: invokevirtual getWidth : ()I
    //   10: istore #5
    //   12: aload_1
    //   13: invokevirtual getHeight : ()I
    //   16: istore #6
    //   18: iconst_0
    //   19: istore #7
    //   21: iconst_0
    //   22: istore #8
    //   24: iconst_0
    //   25: istore #9
    //   27: iconst_0
    //   28: istore #10
    //   30: aload #4
    //   32: iconst_0
    //   33: iconst_0
    //   34: iload #5
    //   36: iload #6
    //   38: invokevirtual set : (IIII)V
    //   41: aload_0
    //   42: getfield mTempRect2 : Landroid/graphics/Rect;
    //   45: iconst_0
    //   46: iconst_0
    //   47: aload_2
    //   48: invokevirtual getWidth : ()I
    //   51: aload_2
    //   52: invokevirtual getHeight : ()I
    //   55: invokevirtual set : (IIII)V
    //   58: aload_0
    //   59: aload_1
    //   60: aload_0
    //   61: getfield mTempRect : Landroid/graphics/Rect;
    //   64: invokevirtual offsetDescendantRectToMyCoords : (Landroid/view/View;Landroid/graphics/Rect;)V
    //   67: aload_0
    //   68: aload_2
    //   69: aload_0
    //   70: getfield mTempRect2 : Landroid/graphics/Rect;
    //   73: invokevirtual offsetDescendantRectToMyCoords : (Landroid/view/View;Landroid/graphics/Rect;)V
    //   76: iload_3
    //   77: bipush #17
    //   79: if_icmpeq -> 325
    //   82: iload_3
    //   83: bipush #33
    //   85: if_icmpeq -> 264
    //   88: iload_3
    //   89: bipush #66
    //   91: if_icmpeq -> 204
    //   94: iload_3
    //   95: sipush #130
    //   98: if_icmpne -> 161
    //   101: aload_0
    //   102: getfield mTempRect : Landroid/graphics/Rect;
    //   105: astore_1
    //   106: aload_1
    //   107: getfield top : I
    //   110: istore #5
    //   112: aload_0
    //   113: getfield mTempRect2 : Landroid/graphics/Rect;
    //   116: astore_2
    //   117: aload_2
    //   118: getfield top : I
    //   121: istore_3
    //   122: iload #5
    //   124: iload_3
    //   125: if_icmplt -> 140
    //   128: iload #10
    //   130: istore #11
    //   132: aload_1
    //   133: getfield bottom : I
    //   136: iload_3
    //   137: if_icmpgt -> 158
    //   140: iload #10
    //   142: istore #11
    //   144: aload_1
    //   145: getfield bottom : I
    //   148: aload_2
    //   149: getfield bottom : I
    //   152: if_icmpge -> 158
    //   155: iconst_1
    //   156: istore #11
    //   158: iload #11
    //   160: ireturn
    //   161: new java/lang/StringBuilder
    //   164: dup
    //   165: invokespecial <init> : ()V
    //   168: astore_1
    //   169: aload_1
    //   170: ldc_w 'direction must be absolute. received:'
    //   173: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   176: pop
    //   177: aload_1
    //   178: iload_3
    //   179: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   182: pop
    //   183: aload_1
    //   184: aload_0
    //   185: invokevirtual exceptionLabel : ()Ljava/lang/String;
    //   188: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   191: pop
    //   192: new java/lang/IllegalArgumentException
    //   195: dup
    //   196: aload_1
    //   197: invokevirtual toString : ()Ljava/lang/String;
    //   200: invokespecial <init> : (Ljava/lang/String;)V
    //   203: athrow
    //   204: aload_0
    //   205: getfield mTempRect : Landroid/graphics/Rect;
    //   208: astore_1
    //   209: aload_1
    //   210: getfield left : I
    //   213: istore #5
    //   215: aload_0
    //   216: getfield mTempRect2 : Landroid/graphics/Rect;
    //   219: astore_2
    //   220: aload_2
    //   221: getfield left : I
    //   224: istore_3
    //   225: iload #5
    //   227: iload_3
    //   228: if_icmplt -> 243
    //   231: iload #7
    //   233: istore #11
    //   235: aload_1
    //   236: getfield right : I
    //   239: iload_3
    //   240: if_icmpgt -> 261
    //   243: iload #7
    //   245: istore #11
    //   247: aload_1
    //   248: getfield right : I
    //   251: aload_2
    //   252: getfield right : I
    //   255: if_icmpge -> 261
    //   258: iconst_1
    //   259: istore #11
    //   261: iload #11
    //   263: ireturn
    //   264: aload_0
    //   265: getfield mTempRect : Landroid/graphics/Rect;
    //   268: astore_1
    //   269: aload_1
    //   270: getfield bottom : I
    //   273: istore_3
    //   274: aload_0
    //   275: getfield mTempRect2 : Landroid/graphics/Rect;
    //   278: astore_2
    //   279: aload_2
    //   280: getfield bottom : I
    //   283: istore #5
    //   285: iload_3
    //   286: iload #5
    //   288: if_icmpgt -> 304
    //   291: iload #8
    //   293: istore #11
    //   295: aload_1
    //   296: getfield top : I
    //   299: iload #5
    //   301: if_icmplt -> 322
    //   304: iload #8
    //   306: istore #11
    //   308: aload_1
    //   309: getfield top : I
    //   312: aload_2
    //   313: getfield top : I
    //   316: if_icmple -> 322
    //   319: iconst_1
    //   320: istore #11
    //   322: iload #11
    //   324: ireturn
    //   325: aload_0
    //   326: getfield mTempRect : Landroid/graphics/Rect;
    //   329: astore_1
    //   330: aload_1
    //   331: getfield right : I
    //   334: istore #5
    //   336: aload_0
    //   337: getfield mTempRect2 : Landroid/graphics/Rect;
    //   340: astore_2
    //   341: aload_2
    //   342: getfield right : I
    //   345: istore_3
    //   346: iload #5
    //   348: iload_3
    //   349: if_icmpgt -> 364
    //   352: iload #9
    //   354: istore #11
    //   356: aload_1
    //   357: getfield left : I
    //   360: iload_3
    //   361: if_icmplt -> 382
    //   364: iload #9
    //   366: istore #11
    //   368: aload_1
    //   369: getfield left : I
    //   372: aload_2
    //   373: getfield left : I
    //   376: if_icmple -> 382
    //   379: iconst_1
    //   380: istore #11
    //   382: iload #11
    //   384: ireturn
  }
  
  private void onPointerUp(MotionEvent paramMotionEvent) {
    int i = paramMotionEvent.getActionIndex();
    if (paramMotionEvent.getPointerId(i) == this.mScrollPointerId) {
      if (i == 0) {
        i = 1;
      } else {
        i = 0;
      } 
      this.mScrollPointerId = paramMotionEvent.getPointerId(i);
      int j = (int)(paramMotionEvent.getX(i) + 0.5F);
      this.mLastTouchX = j;
      this.mInitialTouchX = j;
      i = (int)(paramMotionEvent.getY(i) + 0.5F);
      this.mLastTouchY = i;
      this.mInitialTouchY = i;
    } 
  }
  
  private boolean predictiveItemAnimationsEnabled() {
    boolean bool;
    if (this.mItemAnimator != null && this.mLayout.supportsPredictiveItemAnimations()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private void processAdapterUpdatesAndSetAnimationFlags() {
    // Byte code:
    //   0: aload_0
    //   1: getfield mDataSetHasChangedAfterLayout : Z
    //   4: ifeq -> 22
    //   7: aload_0
    //   8: getfield mAdapterHelper : Landroid/support/v7/widget/AdapterHelper;
    //   11: invokevirtual reset : ()V
    //   14: aload_0
    //   15: getfield mLayout : Landroid/support/v7/widget/RecyclerView$LayoutManager;
    //   18: aload_0
    //   19: invokevirtual onItemsChanged : (Landroid/support/v7/widget/RecyclerView;)V
    //   22: aload_0
    //   23: invokespecial predictiveItemAnimationsEnabled : ()Z
    //   26: ifeq -> 39
    //   29: aload_0
    //   30: getfield mAdapterHelper : Landroid/support/v7/widget/AdapterHelper;
    //   33: invokevirtual preProcess : ()V
    //   36: goto -> 46
    //   39: aload_0
    //   40: getfield mAdapterHelper : Landroid/support/v7/widget/AdapterHelper;
    //   43: invokevirtual consumeUpdatesInOnePass : ()V
    //   46: aload_0
    //   47: getfield mItemsAddedOrRemoved : Z
    //   50: istore_1
    //   51: iconst_0
    //   52: istore_2
    //   53: iload_1
    //   54: ifne -> 72
    //   57: aload_0
    //   58: getfield mItemsChanged : Z
    //   61: ifeq -> 67
    //   64: goto -> 72
    //   67: iconst_0
    //   68: istore_3
    //   69: goto -> 74
    //   72: iconst_1
    //   73: istore_3
    //   74: aload_0
    //   75: getfield mState : Landroid/support/v7/widget/RecyclerView$State;
    //   78: astore #4
    //   80: aload_0
    //   81: getfield mFirstLayoutComplete : Z
    //   84: ifeq -> 136
    //   87: aload_0
    //   88: getfield mItemAnimator : Landroid/support/v7/widget/RecyclerView$ItemAnimator;
    //   91: ifnull -> 136
    //   94: aload_0
    //   95: getfield mDataSetHasChangedAfterLayout : Z
    //   98: istore_1
    //   99: iload_1
    //   100: ifne -> 117
    //   103: iload_3
    //   104: ifne -> 117
    //   107: aload_0
    //   108: getfield mLayout : Landroid/support/v7/widget/RecyclerView$LayoutManager;
    //   111: getfield mRequestedSimpleAnimations : Z
    //   114: ifeq -> 136
    //   117: iload_1
    //   118: ifeq -> 131
    //   121: aload_0
    //   122: getfield mAdapter : Landroid/support/v7/widget/RecyclerView$Adapter;
    //   125: invokevirtual hasStableIds : ()Z
    //   128: ifeq -> 136
    //   131: iconst_1
    //   132: istore_1
    //   133: goto -> 138
    //   136: iconst_0
    //   137: istore_1
    //   138: aload #4
    //   140: iload_1
    //   141: putfield mRunSimpleAnimations : Z
    //   144: aload_0
    //   145: getfield mState : Landroid/support/v7/widget/RecyclerView$State;
    //   148: astore #4
    //   150: iload_2
    //   151: istore_1
    //   152: aload #4
    //   154: getfield mRunSimpleAnimations : Z
    //   157: ifeq -> 186
    //   160: iload_2
    //   161: istore_1
    //   162: iload_3
    //   163: ifeq -> 186
    //   166: iload_2
    //   167: istore_1
    //   168: aload_0
    //   169: getfield mDataSetHasChangedAfterLayout : Z
    //   172: ifne -> 186
    //   175: iload_2
    //   176: istore_1
    //   177: aload_0
    //   178: invokespecial predictiveItemAnimationsEnabled : ()Z
    //   181: ifeq -> 186
    //   184: iconst_1
    //   185: istore_1
    //   186: aload #4
    //   188: iload_1
    //   189: putfield mRunPredictiveAnimations : Z
    //   192: return
  }
  
  private void pullGlows(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    // Byte code:
    //   0: iconst_1
    //   1: istore #5
    //   3: fload_2
    //   4: fconst_0
    //   5: fcmpg
    //   6: ifge -> 43
    //   9: aload_0
    //   10: invokevirtual ensureLeftGlow : ()V
    //   13: aload_0
    //   14: getfield mLeftGlow : Landroid/widget/EdgeEffect;
    //   17: fload_2
    //   18: fneg
    //   19: aload_0
    //   20: invokevirtual getWidth : ()I
    //   23: i2f
    //   24: fdiv
    //   25: fconst_1
    //   26: fload_3
    //   27: aload_0
    //   28: invokevirtual getHeight : ()I
    //   31: i2f
    //   32: fdiv
    //   33: fsub
    //   34: invokestatic onPull : (Landroid/widget/EdgeEffect;FF)V
    //   37: iconst_1
    //   38: istore #6
    //   40: goto -> 80
    //   43: fload_2
    //   44: fconst_0
    //   45: fcmpl
    //   46: ifle -> 77
    //   49: aload_0
    //   50: invokevirtual ensureRightGlow : ()V
    //   53: aload_0
    //   54: getfield mRightGlow : Landroid/widget/EdgeEffect;
    //   57: fload_2
    //   58: aload_0
    //   59: invokevirtual getWidth : ()I
    //   62: i2f
    //   63: fdiv
    //   64: fload_3
    //   65: aload_0
    //   66: invokevirtual getHeight : ()I
    //   69: i2f
    //   70: fdiv
    //   71: invokestatic onPull : (Landroid/widget/EdgeEffect;FF)V
    //   74: goto -> 37
    //   77: iconst_0
    //   78: istore #6
    //   80: fload #4
    //   82: fconst_0
    //   83: fcmpg
    //   84: ifge -> 121
    //   87: aload_0
    //   88: invokevirtual ensureTopGlow : ()V
    //   91: aload_0
    //   92: getfield mTopGlow : Landroid/widget/EdgeEffect;
    //   95: fload #4
    //   97: fneg
    //   98: aload_0
    //   99: invokevirtual getHeight : ()I
    //   102: i2f
    //   103: fdiv
    //   104: fload_1
    //   105: aload_0
    //   106: invokevirtual getWidth : ()I
    //   109: i2f
    //   110: fdiv
    //   111: invokestatic onPull : (Landroid/widget/EdgeEffect;FF)V
    //   114: iload #5
    //   116: istore #6
    //   118: goto -> 163
    //   121: fload #4
    //   123: fconst_0
    //   124: fcmpl
    //   125: ifle -> 163
    //   128: aload_0
    //   129: invokevirtual ensureBottomGlow : ()V
    //   132: aload_0
    //   133: getfield mBottomGlow : Landroid/widget/EdgeEffect;
    //   136: fload #4
    //   138: aload_0
    //   139: invokevirtual getHeight : ()I
    //   142: i2f
    //   143: fdiv
    //   144: fconst_1
    //   145: fload_1
    //   146: aload_0
    //   147: invokevirtual getWidth : ()I
    //   150: i2f
    //   151: fdiv
    //   152: fsub
    //   153: invokestatic onPull : (Landroid/widget/EdgeEffect;FF)V
    //   156: iload #5
    //   158: istore #6
    //   160: goto -> 163
    //   163: iload #6
    //   165: ifne -> 181
    //   168: fload_2
    //   169: fconst_0
    //   170: fcmpl
    //   171: ifne -> 181
    //   174: fload #4
    //   176: fconst_0
    //   177: fcmpl
    //   178: ifeq -> 185
    //   181: aload_0
    //   182: invokestatic postInvalidateOnAnimation : (Landroid/view/View;)V
    //   185: return
  }
  
  private void recoverFocusFromState() {
    if (this.mPreserveFocusAfterLayout && this.mAdapter != null && hasFocus() && getDescendantFocusability() != 393216 && (getDescendantFocusability() != 131072 || !isFocused())) {
      View view1;
      if (!isFocused()) {
        view1 = getFocusedChild();
        if (IGNORE_DETACHED_FOCUSED_CHILD && (view1.getParent() == null || !view1.hasFocus())) {
          if (this.mChildHelper.getChildCount() == 0) {
            requestFocus();
            return;
          } 
        } else if (!this.mChildHelper.isHidden(view1)) {
          return;
        } 
      } 
      long l = this.mState.mFocusedItemId;
      View view2 = null;
      if (l != -1L && this.mAdapter.hasStableIds()) {
        view1 = (View)findViewHolderForItemId(this.mState.mFocusedItemId);
      } else {
        view1 = null;
      } 
      if (view1 == null || this.mChildHelper.isHidden(((ViewHolder)view1).itemView) || !((ViewHolder)view1).itemView.hasFocusable()) {
        view1 = view2;
        if (this.mChildHelper.getChildCount() > 0)
          view1 = findNextViewToFocus(); 
      } else {
        view1 = ((ViewHolder)view1).itemView;
      } 
      if (view1 != null) {
        int i = this.mState.mFocusedSubChildId;
        view2 = view1;
        if (i != -1L) {
          View view = view1.findViewById(i);
          view2 = view1;
          if (view != null) {
            view2 = view1;
            if (view.isFocusable())
              view2 = view; 
          } 
        } 
        view2.requestFocus();
      } 
    } 
  }
  
  private void releaseGlows() {
    EdgeEffect edgeEffect = this.mLeftGlow;
    if (edgeEffect != null) {
      edgeEffect.onRelease();
      bool1 = this.mLeftGlow.isFinished();
    } else {
      bool1 = false;
    } 
    edgeEffect = this.mTopGlow;
    boolean bool2 = bool1;
    if (edgeEffect != null) {
      edgeEffect.onRelease();
      bool2 = bool1 | this.mTopGlow.isFinished();
    } 
    edgeEffect = this.mRightGlow;
    boolean bool1 = bool2;
    if (edgeEffect != null) {
      edgeEffect.onRelease();
      bool1 = bool2 | this.mRightGlow.isFinished();
    } 
    edgeEffect = this.mBottomGlow;
    bool2 = bool1;
    if (edgeEffect != null) {
      edgeEffect.onRelease();
      bool2 = bool1 | this.mBottomGlow.isFinished();
    } 
    if (bool2)
      ViewCompat.postInvalidateOnAnimation((View)this); 
  }
  
  private void requestChildOnScreen(@NonNull View paramView1, @Nullable View paramView2) {
    View view;
    boolean bool1;
    if (paramView2 != null) {
      view = paramView2;
    } else {
      view = paramView1;
    } 
    this.mTempRect.set(0, 0, view.getWidth(), view.getHeight());
    ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
    if (layoutParams instanceof LayoutParams) {
      LayoutParams layoutParams1 = (LayoutParams)layoutParams;
      if (!layoutParams1.mInsetsDirty) {
        Rect rect1 = layoutParams1.mDecorInsets;
        Rect rect2 = this.mTempRect;
        rect2.left -= rect1.left;
        rect2.right += rect1.right;
        rect2.top -= rect1.top;
        rect2.bottom += rect1.bottom;
      } 
    } 
    if (paramView2 != null) {
      offsetDescendantRectToMyCoords(paramView2, this.mTempRect);
      offsetRectIntoDescendantCoords(paramView1, this.mTempRect);
    } 
    LayoutManager layoutManager = this.mLayout;
    Rect rect = this.mTempRect;
    boolean bool = this.mFirstLayoutComplete;
    if (paramView2 == null) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    layoutManager.requestChildRectangleOnScreen(this, paramView1, rect, bool ^ true, bool1);
  }
  
  private void resetFocusInfo() {
    State state = this.mState;
    state.mFocusedItemId = -1L;
    state.mFocusedItemPosition = -1;
    state.mFocusedSubChildId = -1;
  }
  
  private void resetTouch() {
    VelocityTracker velocityTracker = this.mVelocityTracker;
    if (velocityTracker != null)
      velocityTracker.clear(); 
    stopNestedScroll(0);
    releaseGlows();
  }
  
  private void saveFocusInfo() {
    ViewHolder viewHolder2;
    boolean bool = this.mPreserveFocusAfterLayout;
    ViewHolder viewHolder1 = null;
    if (bool && hasFocus() && this.mAdapter != null) {
      viewHolder2 = (ViewHolder)getFocusedChild();
    } else {
      viewHolder2 = null;
    } 
    if (viewHolder2 == null) {
      viewHolder2 = viewHolder1;
    } else {
      viewHolder2 = findContainingViewHolder((View)viewHolder2);
    } 
    if (viewHolder2 == null) {
      resetFocusInfo();
    } else {
      long l;
      int i;
      State state = this.mState;
      if (this.mAdapter.hasStableIds()) {
        l = viewHolder2.getItemId();
      } else {
        l = -1L;
      } 
      state.mFocusedItemId = l;
      state = this.mState;
      if (this.mDataSetHasChangedAfterLayout) {
        i = -1;
      } else if (viewHolder2.isRemoved()) {
        i = viewHolder2.mOldPosition;
      } else {
        i = viewHolder2.getAdapterPosition();
      } 
      state.mFocusedItemPosition = i;
      this.mState.mFocusedSubChildId = getDeepestFocusedViewWithId(viewHolder2.itemView);
    } 
  }
  
  private void setAdapterInternal(Adapter paramAdapter, boolean paramBoolean1, boolean paramBoolean2) {
    Adapter adapter = this.mAdapter;
    if (adapter != null) {
      adapter.unregisterAdapterDataObserver(this.mObserver);
      this.mAdapter.onDetachedFromRecyclerView(this);
    } 
    if (!paramBoolean1 || paramBoolean2)
      removeAndRecycleViews(); 
    this.mAdapterHelper.reset();
    adapter = this.mAdapter;
    this.mAdapter = paramAdapter;
    if (paramAdapter != null) {
      paramAdapter.registerAdapterDataObserver(this.mObserver);
      paramAdapter.onAttachedToRecyclerView(this);
    } 
    LayoutManager layoutManager = this.mLayout;
    if (layoutManager != null)
      layoutManager.onAdapterChanged(adapter, this.mAdapter); 
    this.mRecycler.onAdapterChanged(adapter, this.mAdapter, paramBoolean1);
    this.mState.mStructureChanged = true;
    setDataSetChangedAfterLayout();
  }
  
  private void stopScrollersInternal() {
    this.mViewFlinger.stop();
    LayoutManager layoutManager = this.mLayout;
    if (layoutManager != null)
      layoutManager.stopSmoothScroller(); 
  }
  
  void absorbGlows(int paramInt1, int paramInt2) {
    if (paramInt1 < 0) {
      ensureLeftGlow();
      this.mLeftGlow.onAbsorb(-paramInt1);
    } else if (paramInt1 > 0) {
      ensureRightGlow();
      this.mRightGlow.onAbsorb(paramInt1);
    } 
    if (paramInt2 < 0) {
      ensureTopGlow();
      this.mTopGlow.onAbsorb(-paramInt2);
    } else if (paramInt2 > 0) {
      ensureBottomGlow();
      this.mBottomGlow.onAbsorb(paramInt2);
    } 
    if (paramInt1 != 0 || paramInt2 != 0)
      ViewCompat.postInvalidateOnAnimation((View)this); 
  }
  
  public void addFocusables(ArrayList<View> paramArrayList, int paramInt1, int paramInt2) {
    LayoutManager layoutManager = this.mLayout;
    if (layoutManager == null || !layoutManager.onAddFocusables(this, paramArrayList, paramInt1, paramInt2))
      super.addFocusables(paramArrayList, paramInt1, paramInt2); 
  }
  
  public void addItemDecoration(ItemDecoration paramItemDecoration) {
    addItemDecoration(paramItemDecoration, -1);
  }
  
  public void addItemDecoration(ItemDecoration paramItemDecoration, int paramInt) {
    LayoutManager layoutManager = this.mLayout;
    if (layoutManager != null)
      layoutManager.assertNotInLayoutOrScroll("Cannot add item decoration during a scroll  or layout"); 
    if (this.mItemDecorations.isEmpty())
      setWillNotDraw(false); 
    if (paramInt < 0) {
      this.mItemDecorations.add(paramItemDecoration);
    } else {
      this.mItemDecorations.add(paramInt, paramItemDecoration);
    } 
    markItemDecorInsetsDirty();
    requestLayout();
  }
  
  public void addOnChildAttachStateChangeListener(OnChildAttachStateChangeListener paramOnChildAttachStateChangeListener) {
    if (this.mOnChildAttachStateListeners == null)
      this.mOnChildAttachStateListeners = new ArrayList<OnChildAttachStateChangeListener>(); 
    this.mOnChildAttachStateListeners.add(paramOnChildAttachStateChangeListener);
  }
  
  public void addOnItemTouchListener(OnItemTouchListener paramOnItemTouchListener) {
    this.mOnItemTouchListeners.add(paramOnItemTouchListener);
  }
  
  public void addOnScrollListener(OnScrollListener paramOnScrollListener) {
    if (this.mScrollListeners == null)
      this.mScrollListeners = new ArrayList<OnScrollListener>(); 
    this.mScrollListeners.add(paramOnScrollListener);
  }
  
  void animateAppearance(@NonNull ViewHolder paramViewHolder, @Nullable ItemAnimator.ItemHolderInfo paramItemHolderInfo1, @NonNull ItemAnimator.ItemHolderInfo paramItemHolderInfo2) {
    paramViewHolder.setIsRecyclable(false);
    if (this.mItemAnimator.animateAppearance(paramViewHolder, paramItemHolderInfo1, paramItemHolderInfo2))
      postAnimationRunner(); 
  }
  
  void animateDisappearance(@NonNull ViewHolder paramViewHolder, @NonNull ItemAnimator.ItemHolderInfo paramItemHolderInfo1, @Nullable ItemAnimator.ItemHolderInfo paramItemHolderInfo2) {
    addAnimatingView(paramViewHolder);
    paramViewHolder.setIsRecyclable(false);
    if (this.mItemAnimator.animateDisappearance(paramViewHolder, paramItemHolderInfo1, paramItemHolderInfo2))
      postAnimationRunner(); 
  }
  
  void assertInLayoutOrScroll(String paramString) {
    if (!isComputingLayout()) {
      StringBuilder stringBuilder1;
      if (paramString == null) {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Cannot call this method unless RecyclerView is computing a layout or scrolling");
        stringBuilder1.append(exceptionLabel());
        throw new IllegalStateException(stringBuilder1.toString());
      } 
      StringBuilder stringBuilder2 = new StringBuilder();
      stringBuilder2.append((String)stringBuilder1);
      stringBuilder2.append(exceptionLabel());
      throw new IllegalStateException(stringBuilder2.toString());
    } 
  }
  
  void assertNotInLayoutOrScroll(String paramString) {
    if (isComputingLayout()) {
      StringBuilder stringBuilder;
      if (paramString == null) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Cannot call this method while RecyclerView is computing a layout or scrolling");
        stringBuilder.append(exceptionLabel());
        throw new IllegalStateException(stringBuilder.toString());
      } 
      throw new IllegalStateException(stringBuilder);
    } 
    if (this.mDispatchScrollCounter > 0) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("");
      stringBuilder.append(exceptionLabel());
      Log.w("RecyclerView", "Cannot call this method in a scroll callback. Scroll callbacks mightbe run during a measure & layout pass where you cannot change theRecyclerView data. Any method call that might change the structureof the RecyclerView or the adapter contents should be postponed tothe next frame.", new IllegalStateException(stringBuilder.toString()));
    } 
  }
  
  boolean canReuseUpdatedViewHolder(ViewHolder paramViewHolder) {
    ItemAnimator itemAnimator = this.mItemAnimator;
    return (itemAnimator == null || itemAnimator.canReuseUpdatedViewHolder(paramViewHolder, paramViewHolder.getUnmodifiedPayloads()));
  }
  
  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams) {
    boolean bool;
    if (paramLayoutParams instanceof LayoutParams && this.mLayout.checkLayoutParams((LayoutParams)paramLayoutParams)) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  void clearOldPositions() {
    int i = this.mChildHelper.getUnfilteredChildCount();
    for (byte b = 0; b < i; b++) {
      ViewHolder viewHolder = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(b));
      if (!viewHolder.shouldIgnore())
        viewHolder.clearOldPosition(); 
    } 
    this.mRecycler.clearOldPositions();
  }
  
  public void clearOnChildAttachStateChangeListeners() {
    List<OnChildAttachStateChangeListener> list = this.mOnChildAttachStateListeners;
    if (list != null)
      list.clear(); 
  }
  
  public void clearOnScrollListeners() {
    List<OnScrollListener> list = this.mScrollListeners;
    if (list != null)
      list.clear(); 
  }
  
  public int computeHorizontalScrollExtent() {
    LayoutManager layoutManager = this.mLayout;
    int i = 0;
    if (layoutManager == null)
      return 0; 
    if (layoutManager.canScrollHorizontally())
      i = this.mLayout.computeHorizontalScrollExtent(this.mState); 
    return i;
  }
  
  public int computeHorizontalScrollOffset() {
    LayoutManager layoutManager = this.mLayout;
    int i = 0;
    if (layoutManager == null)
      return 0; 
    if (layoutManager.canScrollHorizontally())
      i = this.mLayout.computeHorizontalScrollOffset(this.mState); 
    return i;
  }
  
  public int computeHorizontalScrollRange() {
    LayoutManager layoutManager = this.mLayout;
    int i = 0;
    if (layoutManager == null)
      return 0; 
    if (layoutManager.canScrollHorizontally())
      i = this.mLayout.computeHorizontalScrollRange(this.mState); 
    return i;
  }
  
  public int computeVerticalScrollExtent() {
    LayoutManager layoutManager = this.mLayout;
    int i = 0;
    if (layoutManager == null)
      return 0; 
    if (layoutManager.canScrollVertically())
      i = this.mLayout.computeVerticalScrollExtent(this.mState); 
    return i;
  }
  
  public int computeVerticalScrollOffset() {
    LayoutManager layoutManager = this.mLayout;
    int i = 0;
    if (layoutManager == null)
      return 0; 
    if (layoutManager.canScrollVertically())
      i = this.mLayout.computeVerticalScrollOffset(this.mState); 
    return i;
  }
  
  public int computeVerticalScrollRange() {
    LayoutManager layoutManager = this.mLayout;
    int i = 0;
    if (layoutManager == null)
      return 0; 
    if (layoutManager.canScrollVertically())
      i = this.mLayout.computeVerticalScrollRange(this.mState); 
    return i;
  }
  
  void considerReleasingGlowsOnScroll(int paramInt1, int paramInt2) {
    EdgeEffect edgeEffect = this.mLeftGlow;
    if (edgeEffect != null && !edgeEffect.isFinished() && paramInt1 > 0) {
      this.mLeftGlow.onRelease();
      bool1 = this.mLeftGlow.isFinished();
    } else {
      bool1 = false;
    } 
    edgeEffect = this.mRightGlow;
    boolean bool2 = bool1;
    if (edgeEffect != null) {
      bool2 = bool1;
      if (!edgeEffect.isFinished()) {
        bool2 = bool1;
        if (paramInt1 < 0) {
          this.mRightGlow.onRelease();
          bool2 = bool1 | this.mRightGlow.isFinished();
        } 
      } 
    } 
    edgeEffect = this.mTopGlow;
    boolean bool1 = bool2;
    if (edgeEffect != null) {
      bool1 = bool2;
      if (!edgeEffect.isFinished()) {
        bool1 = bool2;
        if (paramInt2 > 0) {
          this.mTopGlow.onRelease();
          bool1 = bool2 | this.mTopGlow.isFinished();
        } 
      } 
    } 
    edgeEffect = this.mBottomGlow;
    bool2 = bool1;
    if (edgeEffect != null) {
      bool2 = bool1;
      if (!edgeEffect.isFinished()) {
        bool2 = bool1;
        if (paramInt2 < 0) {
          this.mBottomGlow.onRelease();
          bool2 = bool1 | this.mBottomGlow.isFinished();
        } 
      } 
    } 
    if (bool2)
      ViewCompat.postInvalidateOnAnimation((View)this); 
  }
  
  void consumePendingUpdateOperations() {
    if (!this.mFirstLayoutComplete || this.mDataSetHasChangedAfterLayout) {
      TraceCompat.beginSection("RV FullInvalidate");
      dispatchLayout();
      TraceCompat.endSection();
      return;
    } 
    if (!this.mAdapterHelper.hasPendingUpdates())
      return; 
    if (this.mAdapterHelper.hasAnyUpdateTypes(4) && !this.mAdapterHelper.hasAnyUpdateTypes(11)) {
      TraceCompat.beginSection("RV PartialInvalidate");
      eatRequestLayout();
      onEnterLayoutOrScroll();
      this.mAdapterHelper.preProcess();
      if (!this.mLayoutRequestEaten)
        if (hasUpdatedView()) {
          dispatchLayout();
        } else {
          this.mAdapterHelper.consumePostponedUpdates();
        }  
      resumeRequestLayout(true);
      onExitLayoutOrScroll();
      TraceCompat.endSection();
    } else if (this.mAdapterHelper.hasPendingUpdates()) {
      TraceCompat.beginSection("RV FullInvalidate");
      dispatchLayout();
      TraceCompat.endSection();
    } 
  }
  
  void defaultOnMeasure(int paramInt1, int paramInt2) {
    setMeasuredDimension(LayoutManager.chooseSize(paramInt1, getPaddingLeft() + getPaddingRight(), ViewCompat.getMinimumWidth((View)this)), LayoutManager.chooseSize(paramInt2, getPaddingTop() + getPaddingBottom(), ViewCompat.getMinimumHeight((View)this)));
  }
  
  void dispatchChildAttached(View paramView) {
    ViewHolder viewHolder = getChildViewHolderInt(paramView);
    onChildAttachedToWindow(paramView);
    Adapter<ViewHolder> adapter = this.mAdapter;
    if (adapter != null && viewHolder != null)
      adapter.onViewAttachedToWindow(viewHolder); 
    List<OnChildAttachStateChangeListener> list = this.mOnChildAttachStateListeners;
    if (list != null)
      for (int i = list.size() - 1; i >= 0; i--)
        ((OnChildAttachStateChangeListener)this.mOnChildAttachStateListeners.get(i)).onChildViewAttachedToWindow(paramView);  
  }
  
  void dispatchChildDetached(View paramView) {
    ViewHolder viewHolder = getChildViewHolderInt(paramView);
    onChildDetachedFromWindow(paramView);
    Adapter<ViewHolder> adapter = this.mAdapter;
    if (adapter != null && viewHolder != null)
      adapter.onViewDetachedFromWindow(viewHolder); 
    List<OnChildAttachStateChangeListener> list = this.mOnChildAttachStateListeners;
    if (list != null)
      for (int i = list.size() - 1; i >= 0; i--)
        ((OnChildAttachStateChangeListener)this.mOnChildAttachStateListeners.get(i)).onChildViewDetachedFromWindow(paramView);  
  }
  
  void dispatchLayout() {
    if (this.mAdapter == null) {
      Log.e("RecyclerView", "No adapter attached; skipping layout");
      return;
    } 
    if (this.mLayout == null) {
      Log.e("RecyclerView", "No layout manager attached; skipping layout");
      return;
    } 
    State state = this.mState;
    state.mIsMeasuring = false;
    if (state.mLayoutStep == 1) {
      dispatchLayoutStep1();
      this.mLayout.setExactMeasureSpecsFrom(this);
      dispatchLayoutStep2();
    } else if (this.mAdapterHelper.hasUpdates() || this.mLayout.getWidth() != getWidth() || this.mLayout.getHeight() != getHeight()) {
      this.mLayout.setExactMeasureSpecsFrom(this);
      dispatchLayoutStep2();
    } else {
      this.mLayout.setExactMeasureSpecsFrom(this);
    } 
    dispatchLayoutStep3();
  }
  
  public boolean dispatchNestedFling(float paramFloat1, float paramFloat2, boolean paramBoolean) {
    return getScrollingChildHelper().dispatchNestedFling(paramFloat1, paramFloat2, paramBoolean);
  }
  
  public boolean dispatchNestedPreFling(float paramFloat1, float paramFloat2) {
    return getScrollingChildHelper().dispatchNestedPreFling(paramFloat1, paramFloat2);
  }
  
  public boolean dispatchNestedPreScroll(int paramInt1, int paramInt2, int[] paramArrayOfint1, int[] paramArrayOfint2) {
    return getScrollingChildHelper().dispatchNestedPreScroll(paramInt1, paramInt2, paramArrayOfint1, paramArrayOfint2);
  }
  
  public boolean dispatchNestedPreScroll(int paramInt1, int paramInt2, int[] paramArrayOfint1, int[] paramArrayOfint2, int paramInt3) {
    return getScrollingChildHelper().dispatchNestedPreScroll(paramInt1, paramInt2, paramArrayOfint1, paramArrayOfint2, paramInt3);
  }
  
  public boolean dispatchNestedScroll(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfint) {
    return getScrollingChildHelper().dispatchNestedScroll(paramInt1, paramInt2, paramInt3, paramInt4, paramArrayOfint);
  }
  
  public boolean dispatchNestedScroll(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfint, int paramInt5) {
    return getScrollingChildHelper().dispatchNestedScroll(paramInt1, paramInt2, paramInt3, paramInt4, paramArrayOfint, paramInt5);
  }
  
  void dispatchOnScrollStateChanged(int paramInt) {
    LayoutManager layoutManager = this.mLayout;
    if (layoutManager != null)
      layoutManager.onScrollStateChanged(paramInt); 
    onScrollStateChanged(paramInt);
    OnScrollListener onScrollListener = this.mScrollListener;
    if (onScrollListener != null)
      onScrollListener.onScrollStateChanged(this, paramInt); 
    List<OnScrollListener> list = this.mScrollListeners;
    if (list != null)
      for (int i = list.size() - 1; i >= 0; i--)
        ((OnScrollListener)this.mScrollListeners.get(i)).onScrollStateChanged(this, paramInt);  
  }
  
  void dispatchOnScrolled(int paramInt1, int paramInt2) {
    this.mDispatchScrollCounter++;
    int i = getScrollX();
    int j = getScrollY();
    onScrollChanged(i, j, i, j);
    onScrolled(paramInt1, paramInt2);
    OnScrollListener onScrollListener = this.mScrollListener;
    if (onScrollListener != null)
      onScrollListener.onScrolled(this, paramInt1, paramInt2); 
    List<OnScrollListener> list = this.mScrollListeners;
    if (list != null)
      for (j = list.size() - 1; j >= 0; j--)
        ((OnScrollListener)this.mScrollListeners.get(j)).onScrolled(this, paramInt1, paramInt2);  
    this.mDispatchScrollCounter--;
  }
  
  void dispatchPendingImportantForAccessibilityChanges() {
    for (int i = this.mPendingAccessibilityImportanceChange.size() - 1; i >= 0; i--) {
      ViewHolder viewHolder = this.mPendingAccessibilityImportanceChange.get(i);
      if (viewHolder.itemView.getParent() == this && !viewHolder.shouldIgnore()) {
        int j = viewHolder.mPendingAccessibilityState;
        if (j != -1) {
          ViewCompat.setImportantForAccessibility(viewHolder.itemView, j);
          viewHolder.mPendingAccessibilityState = -1;
        } 
      } 
    } 
    this.mPendingAccessibilityImportanceChange.clear();
  }
  
  protected void dispatchRestoreInstanceState(SparseArray<Parcelable> paramSparseArray) {
    dispatchThawSelfOnly(paramSparseArray);
  }
  
  protected void dispatchSaveInstanceState(SparseArray<Parcelable> paramSparseArray) {
    dispatchFreezeSelfOnly(paramSparseArray);
  }
  
  public void draw(Canvas paramCanvas) {
    super.draw(paramCanvas);
    int i = this.mItemDecorations.size();
    boolean bool1 = false;
    int j;
    for (j = 0; j < i; j++)
      ((ItemDecoration)this.mItemDecorations.get(j)).onDrawOver(paramCanvas, this, this.mState); 
    EdgeEffect edgeEffect = this.mLeftGlow;
    boolean bool2 = true;
    if (edgeEffect != null && !edgeEffect.isFinished()) {
      int k = paramCanvas.save();
      if (this.mClipToPadding) {
        j = getPaddingBottom();
      } else {
        j = 0;
      } 
      paramCanvas.rotate(270.0F);
      paramCanvas.translate((-getHeight() + j), 0.0F);
      edgeEffect = this.mLeftGlow;
      if (edgeEffect != null && edgeEffect.draw(paramCanvas)) {
        i = 1;
      } else {
        i = 0;
      } 
      paramCanvas.restoreToCount(k);
    } else {
      i = 0;
    } 
    edgeEffect = this.mTopGlow;
    j = i;
    if (edgeEffect != null) {
      j = i;
      if (!edgeEffect.isFinished()) {
        int k = paramCanvas.save();
        if (this.mClipToPadding)
          paramCanvas.translate(getPaddingLeft(), getPaddingTop()); 
        edgeEffect = this.mTopGlow;
        if (edgeEffect != null && edgeEffect.draw(paramCanvas)) {
          j = 1;
        } else {
          j = 0;
        } 
        j = i | j;
        paramCanvas.restoreToCount(k);
      } 
    } 
    edgeEffect = this.mRightGlow;
    i = j;
    if (edgeEffect != null) {
      i = j;
      if (!edgeEffect.isFinished()) {
        int k = paramCanvas.save();
        int m = getWidth();
        if (this.mClipToPadding) {
          i = getPaddingTop();
        } else {
          i = 0;
        } 
        paramCanvas.rotate(90.0F);
        paramCanvas.translate(-i, -m);
        edgeEffect = this.mRightGlow;
        if (edgeEffect != null && edgeEffect.draw(paramCanvas)) {
          i = 1;
        } else {
          i = 0;
        } 
        i = j | i;
        paramCanvas.restoreToCount(k);
      } 
    } 
    edgeEffect = this.mBottomGlow;
    j = i;
    if (edgeEffect != null) {
      j = i;
      if (!edgeEffect.isFinished()) {
        int k = paramCanvas.save();
        paramCanvas.rotate(180.0F);
        if (this.mClipToPadding) {
          paramCanvas.translate((-getWidth() + getPaddingRight()), (-getHeight() + getPaddingBottom()));
        } else {
          paramCanvas.translate(-getWidth(), -getHeight());
        } 
        edgeEffect = this.mBottomGlow;
        j = bool1;
        if (edgeEffect != null) {
          j = bool1;
          if (edgeEffect.draw(paramCanvas))
            j = 1; 
        } 
        j = i | j;
        paramCanvas.restoreToCount(k);
      } 
    } 
    if (j == 0 && this.mItemAnimator != null && this.mItemDecorations.size() > 0 && this.mItemAnimator.isRunning())
      j = bool2; 
    if (j != 0)
      ViewCompat.postInvalidateOnAnimation((View)this); 
  }
  
  public boolean drawChild(Canvas paramCanvas, View paramView, long paramLong) {
    return super.drawChild(paramCanvas, paramView, paramLong);
  }
  
  void eatRequestLayout() {
    int i = this.mEatRequestLayout + 1;
    this.mEatRequestLayout = i;
    if (i == 1 && !this.mLayoutFrozen)
      this.mLayoutRequestEaten = false; 
  }
  
  void ensureBottomGlow() {
    if (this.mBottomGlow != null)
      return; 
    EdgeEffect edgeEffect = new EdgeEffect(getContext());
    this.mBottomGlow = edgeEffect;
    if (this.mClipToPadding) {
      edgeEffect.setSize(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(), getMeasuredHeight() - getPaddingTop() - getPaddingBottom());
    } else {
      edgeEffect.setSize(getMeasuredWidth(), getMeasuredHeight());
    } 
  }
  
  void ensureLeftGlow() {
    if (this.mLeftGlow != null)
      return; 
    EdgeEffect edgeEffect = new EdgeEffect(getContext());
    this.mLeftGlow = edgeEffect;
    if (this.mClipToPadding) {
      edgeEffect.setSize(getMeasuredHeight() - getPaddingTop() - getPaddingBottom(), getMeasuredWidth() - getPaddingLeft() - getPaddingRight());
    } else {
      edgeEffect.setSize(getMeasuredHeight(), getMeasuredWidth());
    } 
  }
  
  void ensureRightGlow() {
    if (this.mRightGlow != null)
      return; 
    EdgeEffect edgeEffect = new EdgeEffect(getContext());
    this.mRightGlow = edgeEffect;
    if (this.mClipToPadding) {
      edgeEffect.setSize(getMeasuredHeight() - getPaddingTop() - getPaddingBottom(), getMeasuredWidth() - getPaddingLeft() - getPaddingRight());
    } else {
      edgeEffect.setSize(getMeasuredHeight(), getMeasuredWidth());
    } 
  }
  
  void ensureTopGlow() {
    if (this.mTopGlow != null)
      return; 
    EdgeEffect edgeEffect = new EdgeEffect(getContext());
    this.mTopGlow = edgeEffect;
    if (this.mClipToPadding) {
      edgeEffect.setSize(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(), getMeasuredHeight() - getPaddingTop() - getPaddingBottom());
    } else {
      edgeEffect.setSize(getMeasuredWidth(), getMeasuredHeight());
    } 
  }
  
  String exceptionLabel() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(" ");
    stringBuilder.append(toString());
    stringBuilder.append(", adapter:");
    stringBuilder.append(this.mAdapter);
    stringBuilder.append(", layout:");
    stringBuilder.append(this.mLayout);
    stringBuilder.append(", context:");
    stringBuilder.append(getContext());
    return stringBuilder.toString();
  }
  
  final void fillRemainingScrollValues(State paramState) {
    if (getScrollState() == 2) {
      OverScroller overScroller = this.mViewFlinger.mScroller;
      paramState.mRemainingScrollHorizontal = overScroller.getFinalX() - overScroller.getCurrX();
      paramState.mRemainingScrollVertical = overScroller.getFinalY() - overScroller.getCurrY();
    } else {
      paramState.mRemainingScrollHorizontal = 0;
      paramState.mRemainingScrollVertical = 0;
    } 
  }
  
  public View findChildViewUnder(float paramFloat1, float paramFloat2) {
    for (int i = this.mChildHelper.getChildCount() - 1; i >= 0; i--) {
      View view = this.mChildHelper.getChildAt(i);
      float f1 = view.getTranslationX();
      float f2 = view.getTranslationY();
      if (paramFloat1 >= view.getLeft() + f1 && paramFloat1 <= view.getRight() + f1 && paramFloat2 >= view.getTop() + f2 && paramFloat2 <= view.getBottom() + f2)
        return view; 
    } 
    return null;
  }
  
  @Nullable
  public View findContainingItemView(View paramView) {
    ViewParent viewParent;
    for (viewParent = paramView.getParent(); viewParent != null && viewParent != this && viewParent instanceof View; viewParent = paramView.getParent())
      paramView = (View)viewParent; 
    if (viewParent != this)
      paramView = null; 
    return paramView;
  }
  
  @Nullable
  public ViewHolder findContainingViewHolder(View paramView) {
    ViewHolder viewHolder;
    paramView = findContainingItemView(paramView);
    if (paramView == null) {
      paramView = null;
    } else {
      viewHolder = getChildViewHolder(paramView);
    } 
    return viewHolder;
  }
  
  public ViewHolder findViewHolderForAdapterPosition(int paramInt) {
    boolean bool = this.mDataSetHasChangedAfterLayout;
    ViewHolder viewHolder = null;
    if (bool)
      return null; 
    int i = this.mChildHelper.getUnfilteredChildCount();
    byte b = 0;
    while (b < i) {
      ViewHolder viewHolder1 = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(b));
      ViewHolder viewHolder2 = viewHolder;
      if (viewHolder1 != null) {
        viewHolder2 = viewHolder;
        if (!viewHolder1.isRemoved()) {
          viewHolder2 = viewHolder;
          if (getAdapterPositionFor(viewHolder1) == paramInt)
            if (this.mChildHelper.isHidden(viewHolder1.itemView)) {
              viewHolder2 = viewHolder1;
            } else {
              return viewHolder1;
            }  
        } 
      } 
      b++;
      viewHolder = viewHolder2;
    } 
    return viewHolder;
  }
  
  public ViewHolder findViewHolderForItemId(long paramLong) {
    Adapter adapter = this.mAdapter;
    ViewHolder viewHolder1 = null;
    ViewHolder viewHolder2 = null;
    ViewHolder viewHolder3 = viewHolder1;
    if (adapter != null)
      if (!adapter.hasStableIds()) {
        viewHolder3 = viewHolder1;
      } else {
        int i = this.mChildHelper.getUnfilteredChildCount();
        byte b = 0;
        while (true) {
          viewHolder3 = viewHolder2;
          if (b < i) {
            viewHolder1 = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(b));
            viewHolder3 = viewHolder2;
            if (viewHolder1 != null) {
              viewHolder3 = viewHolder2;
              if (!viewHolder1.isRemoved()) {
                viewHolder3 = viewHolder2;
                if (viewHolder1.getItemId() == paramLong)
                  if (this.mChildHelper.isHidden(viewHolder1.itemView)) {
                    viewHolder3 = viewHolder1;
                  } else {
                    return viewHolder1;
                  }  
              } 
            } 
            b++;
            viewHolder2 = viewHolder3;
            continue;
          } 
          break;
        } 
      }  
    return viewHolder3;
  }
  
  public ViewHolder findViewHolderForLayoutPosition(int paramInt) {
    return findViewHolderForPosition(paramInt, false);
  }
  
  @Deprecated
  public ViewHolder findViewHolderForPosition(int paramInt) {
    return findViewHolderForPosition(paramInt, false);
  }
  
  ViewHolder findViewHolderForPosition(int paramInt, boolean paramBoolean) {
    int i = this.mChildHelper.getUnfilteredChildCount();
    Object object = null;
    byte b = 0;
    while (b < i) {
      ViewHolder viewHolder = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(b));
      Object object1 = object;
      if (viewHolder != null) {
        object1 = object;
        if (!viewHolder.isRemoved()) {
          if (paramBoolean) {
            if (viewHolder.mPosition != paramInt) {
              object1 = object;
              continue;
            } 
          } else if (viewHolder.getLayoutPosition() != paramInt) {
            object1 = object;
            continue;
          } 
          if (this.mChildHelper.isHidden(viewHolder.itemView)) {
            object1 = viewHolder;
          } else {
            return viewHolder;
          } 
        } 
      } 
      continue;
      b++;
      object = SYNTHETIC_LOCAL_VARIABLE_7;
    } 
    return (ViewHolder)object;
  }
  
  public boolean fling(int paramInt1, int paramInt2) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mLayout : Landroid/support/v7/widget/RecyclerView$LayoutManager;
    //   4: astore_3
    //   5: aload_3
    //   6: ifnonnull -> 20
    //   9: ldc 'RecyclerView'
    //   11: ldc_w 'Cannot fling without a LayoutManager set. Call setLayoutManager with a non-null argument.'
    //   14: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   17: pop
    //   18: iconst_0
    //   19: ireturn
    //   20: aload_0
    //   21: getfield mLayoutFrozen : Z
    //   24: ifeq -> 29
    //   27: iconst_0
    //   28: ireturn
    //   29: aload_3
    //   30: invokevirtual canScrollHorizontally : ()Z
    //   33: istore #4
    //   35: aload_0
    //   36: getfield mLayout : Landroid/support/v7/widget/RecyclerView$LayoutManager;
    //   39: invokevirtual canScrollVertically : ()Z
    //   42: istore #5
    //   44: iload #4
    //   46: ifeq -> 63
    //   49: iload_1
    //   50: istore #6
    //   52: iload_1
    //   53: invokestatic abs : (I)I
    //   56: aload_0
    //   57: getfield mMinFlingVelocity : I
    //   60: if_icmpge -> 66
    //   63: iconst_0
    //   64: istore #6
    //   66: iload #5
    //   68: ifeq -> 84
    //   71: iload_2
    //   72: istore_1
    //   73: iload_2
    //   74: invokestatic abs : (I)I
    //   77: aload_0
    //   78: getfield mMinFlingVelocity : I
    //   81: if_icmpge -> 86
    //   84: iconst_0
    //   85: istore_1
    //   86: iload #6
    //   88: ifne -> 97
    //   91: iload_1
    //   92: ifne -> 97
    //   95: iconst_0
    //   96: ireturn
    //   97: iload #6
    //   99: i2f
    //   100: fstore #7
    //   102: iload_1
    //   103: i2f
    //   104: fstore #8
    //   106: aload_0
    //   107: fload #7
    //   109: fload #8
    //   111: invokevirtual dispatchNestedPreFling : (FF)Z
    //   114: ifne -> 243
    //   117: iload #4
    //   119: ifne -> 136
    //   122: iload #5
    //   124: ifeq -> 130
    //   127: goto -> 136
    //   130: iconst_0
    //   131: istore #9
    //   133: goto -> 139
    //   136: iconst_1
    //   137: istore #9
    //   139: aload_0
    //   140: fload #7
    //   142: fload #8
    //   144: iload #9
    //   146: invokevirtual dispatchNestedFling : (FFZ)Z
    //   149: pop
    //   150: aload_0
    //   151: getfield mOnFlingListener : Landroid/support/v7/widget/RecyclerView$OnFlingListener;
    //   154: astore_3
    //   155: aload_3
    //   156: ifnull -> 171
    //   159: aload_3
    //   160: iload #6
    //   162: iload_1
    //   163: invokevirtual onFling : (II)Z
    //   166: ifeq -> 171
    //   169: iconst_1
    //   170: ireturn
    //   171: iload #9
    //   173: ifeq -> 243
    //   176: iload #4
    //   178: istore_2
    //   179: iload #5
    //   181: ifeq -> 189
    //   184: iload #4
    //   186: iconst_2
    //   187: ior
    //   188: istore_2
    //   189: aload_0
    //   190: iload_2
    //   191: iconst_1
    //   192: invokevirtual startNestedScroll : (II)Z
    //   195: pop
    //   196: aload_0
    //   197: getfield mMaxFlingVelocity : I
    //   200: istore_2
    //   201: iload_2
    //   202: ineg
    //   203: iload #6
    //   205: iload_2
    //   206: invokestatic min : (II)I
    //   209: invokestatic max : (II)I
    //   212: istore_2
    //   213: aload_0
    //   214: getfield mMaxFlingVelocity : I
    //   217: istore #6
    //   219: iload #6
    //   221: ineg
    //   222: iload_1
    //   223: iload #6
    //   225: invokestatic min : (II)I
    //   228: invokestatic max : (II)I
    //   231: istore_1
    //   232: aload_0
    //   233: getfield mViewFlinger : Landroid/support/v7/widget/RecyclerView$ViewFlinger;
    //   236: iload_2
    //   237: iload_1
    //   238: invokevirtual fling : (II)V
    //   241: iconst_1
    //   242: ireturn
    //   243: iconst_0
    //   244: ireturn
  }
  
  public View focusSearch(View paramView, int paramInt) {
    View view1;
    int i;
    View view2 = this.mLayout.onInterceptFocusSearch(paramView, paramInt);
    if (view2 != null)
      return view2; 
    Adapter adapter = this.mAdapter;
    boolean bool = true;
    if (adapter != null && this.mLayout != null && !isComputingLayout() && !this.mLayoutFrozen) {
      i = 1;
    } else {
      i = 0;
    } 
    FocusFinder focusFinder = FocusFinder.getInstance();
    if (i && (paramInt == 2 || paramInt == 1)) {
      if (this.mLayout.canScrollVertically()) {
        byte b1;
        byte b2;
        if (paramInt == 2) {
          b1 = 130;
        } else {
          b1 = 33;
        } 
        if (focusFinder.findNextFocus(this, paramView, b1) == null) {
          b2 = 1;
        } else {
          b2 = 0;
        } 
        i = b2;
        if (FORCE_ABS_FOCUS_SEARCH_DIRECTION) {
          paramInt = b1;
          i = b2;
        } 
      } else {
        i = 0;
      } 
      int k = i;
      int j = paramInt;
      if (!i) {
        k = i;
        j = paramInt;
        if (this.mLayout.canScrollHorizontally()) {
          if (this.mLayout.getLayoutDirection() == 1) {
            i = 1;
          } else {
            i = 0;
          } 
          if (paramInt == 2) {
            j = 1;
          } else {
            j = 0;
          } 
          if ((i ^ j) != 0) {
            i = 66;
          } else {
            i = 17;
          } 
          if (focusFinder.findNextFocus(this, paramView, i) == null) {
            j = bool;
          } else {
            j = 0;
          } 
          if (FORCE_ABS_FOCUS_SEARCH_DIRECTION)
            paramInt = i; 
          k = j;
          j = paramInt;
        } 
      } 
      if (k != 0) {
        consumePendingUpdateOperations();
        if (findContainingItemView(paramView) == null)
          return null; 
        eatRequestLayout();
        this.mLayout.onFocusSearchFailed(paramView, j, this.mRecycler, this.mState);
        resumeRequestLayout(false);
      } 
      view1 = focusFinder.findNextFocus(this, paramView, j);
      paramInt = j;
    } else {
      view1 = view1.findNextFocus(this, paramView, paramInt);
      if (view1 == null && i != 0) {
        consumePendingUpdateOperations();
        if (findContainingItemView(paramView) == null)
          return null; 
        eatRequestLayout();
        view1 = this.mLayout.onFocusSearchFailed(paramView, paramInt, this.mRecycler, this.mState);
        resumeRequestLayout(false);
      } 
    } 
    if (view1 != null && !view1.hasFocusable()) {
      if (getFocusedChild() == null)
        return super.focusSearch(paramView, paramInt); 
      requestChildOnScreen(view1, null);
      return paramView;
    } 
    if (!isPreferredNextFocus(paramView, view1, paramInt))
      view1 = super.focusSearch(paramView, paramInt); 
    return view1;
  }
  
  protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
    LayoutManager layoutManager = this.mLayout;
    if (layoutManager != null)
      return (ViewGroup.LayoutParams)layoutManager.generateDefaultLayoutParams(); 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("RecyclerView has no LayoutManager");
    stringBuilder.append(exceptionLabel());
    throw new IllegalStateException(stringBuilder.toString());
  }
  
  public ViewGroup.LayoutParams generateLayoutParams(AttributeSet paramAttributeSet) {
    LayoutManager layoutManager = this.mLayout;
    if (layoutManager != null)
      return (ViewGroup.LayoutParams)layoutManager.generateLayoutParams(getContext(), paramAttributeSet); 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("RecyclerView has no LayoutManager");
    stringBuilder.append(exceptionLabel());
    throw new IllegalStateException(stringBuilder.toString());
  }
  
  protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams) {
    LayoutManager layoutManager = this.mLayout;
    if (layoutManager != null)
      return (ViewGroup.LayoutParams)layoutManager.generateLayoutParams(paramLayoutParams); 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("RecyclerView has no LayoutManager");
    stringBuilder.append(exceptionLabel());
    throw new IllegalStateException(stringBuilder.toString());
  }
  
  public Adapter getAdapter() {
    return this.mAdapter;
  }
  
  int getAdapterPositionFor(ViewHolder paramViewHolder) {
    return (paramViewHolder.hasAnyOfTheFlags(524) || !paramViewHolder.isBound()) ? -1 : this.mAdapterHelper.applyPendingUpdatesToPosition(paramViewHolder.mPosition);
  }
  
  public int getBaseline() {
    LayoutManager layoutManager = this.mLayout;
    return (layoutManager != null) ? layoutManager.getBaseline() : super.getBaseline();
  }
  
  long getChangedHolderKey(ViewHolder paramViewHolder) {
    long l;
    if (this.mAdapter.hasStableIds()) {
      l = paramViewHolder.getItemId();
    } else {
      l = paramViewHolder.mPosition;
    } 
    return l;
  }
  
  public int getChildAdapterPosition(View paramView) {
    byte b;
    ViewHolder viewHolder = getChildViewHolderInt(paramView);
    if (viewHolder != null) {
      b = viewHolder.getAdapterPosition();
    } else {
      b = -1;
    } 
    return b;
  }
  
  protected int getChildDrawingOrder(int paramInt1, int paramInt2) {
    ChildDrawingOrderCallback childDrawingOrderCallback = this.mChildDrawingOrderCallback;
    return (childDrawingOrderCallback == null) ? super.getChildDrawingOrder(paramInt1, paramInt2) : childDrawingOrderCallback.onGetChildDrawingOrder(paramInt1, paramInt2);
  }
  
  public long getChildItemId(View paramView) {
    Adapter adapter = this.mAdapter;
    long l1 = -1L;
    long l2 = l1;
    if (adapter != null)
      if (!adapter.hasStableIds()) {
        l2 = l1;
      } else {
        ViewHolder viewHolder = getChildViewHolderInt(paramView);
        l2 = l1;
        if (viewHolder != null)
          l2 = viewHolder.getItemId(); 
      }  
    return l2;
  }
  
  public int getChildLayoutPosition(View paramView) {
    byte b;
    ViewHolder viewHolder = getChildViewHolderInt(paramView);
    if (viewHolder != null) {
      b = viewHolder.getLayoutPosition();
    } else {
      b = -1;
    } 
    return b;
  }
  
  @Deprecated
  public int getChildPosition(View paramView) {
    return getChildAdapterPosition(paramView);
  }
  
  public ViewHolder getChildViewHolder(View paramView) {
    ViewParent viewParent = paramView.getParent();
    if (viewParent == null || viewParent == this)
      return getChildViewHolderInt(paramView); 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("View ");
    stringBuilder.append(paramView);
    stringBuilder.append(" is not a direct child of ");
    stringBuilder.append(this);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public boolean getClipToPadding() {
    return this.mClipToPadding;
  }
  
  public RecyclerViewAccessibilityDelegate getCompatAccessibilityDelegate() {
    return this.mAccessibilityDelegate;
  }
  
  public void getDecoratedBoundsWithMargins(View paramView, Rect paramRect) {
    getDecoratedBoundsWithMarginsInt(paramView, paramRect);
  }
  
  public ItemAnimator getItemAnimator() {
    return this.mItemAnimator;
  }
  
  Rect getItemDecorInsetsForChild(View paramView) {
    LayoutParams layoutParams = (LayoutParams)paramView.getLayoutParams();
    if (!layoutParams.mInsetsDirty)
      return layoutParams.mDecorInsets; 
    if (this.mState.isPreLayout() && (layoutParams.isItemChanged() || layoutParams.isViewInvalid()))
      return layoutParams.mDecorInsets; 
    Rect rect = layoutParams.mDecorInsets;
    rect.set(0, 0, 0, 0);
    int i = this.mItemDecorations.size();
    for (byte b = 0; b < i; b++) {
      this.mTempRect.set(0, 0, 0, 0);
      ((ItemDecoration)this.mItemDecorations.get(b)).getItemOffsets(this.mTempRect, paramView, this, this.mState);
      int j = rect.left;
      Rect rect1 = this.mTempRect;
      rect.left = j + rect1.left;
      rect.top += rect1.top;
      rect.right += rect1.right;
      rect.bottom += rect1.bottom;
    } 
    layoutParams.mInsetsDirty = false;
    return rect;
  }
  
  public ItemDecoration getItemDecorationAt(int paramInt) {
    return (paramInt < 0 || paramInt >= this.mItemDecorations.size()) ? null : this.mItemDecorations.get(paramInt);
  }
  
  public LayoutManager getLayoutManager() {
    return this.mLayout;
  }
  
  public int getMaxFlingVelocity() {
    return this.mMaxFlingVelocity;
  }
  
  public int getMinFlingVelocity() {
    return this.mMinFlingVelocity;
  }
  
  long getNanoTime() {
    return ALLOW_THREAD_GAP_WORK ? System.nanoTime() : 0L;
  }
  
  @Nullable
  public OnFlingListener getOnFlingListener() {
    return this.mOnFlingListener;
  }
  
  public boolean getPreserveFocusAfterLayout() {
    return this.mPreserveFocusAfterLayout;
  }
  
  public RecycledViewPool getRecycledViewPool() {
    return this.mRecycler.getRecycledViewPool();
  }
  
  public int getScrollState() {
    return this.mScrollState;
  }
  
  public boolean hasFixedSize() {
    return this.mHasFixedSize;
  }
  
  public boolean hasNestedScrollingParent() {
    return getScrollingChildHelper().hasNestedScrollingParent();
  }
  
  public boolean hasNestedScrollingParent(int paramInt) {
    return getScrollingChildHelper().hasNestedScrollingParent(paramInt);
  }
  
  public boolean hasPendingAdapterUpdates() {
    return (!this.mFirstLayoutComplete || this.mDataSetHasChangedAfterLayout || this.mAdapterHelper.hasPendingUpdates());
  }
  
  void initAdapterManager() {
    this.mAdapterHelper = new AdapterHelper(new AdapterHelper.Callback() {
          void dispatchUpdate(AdapterHelper.UpdateOp param1UpdateOp) {
            int i = param1UpdateOp.cmd;
            if (i != 1) {
              if (i != 2) {
                if (i != 4) {
                  if (i == 8) {
                    RecyclerView recyclerView = RecyclerView.this;
                    recyclerView.mLayout.onItemsMoved(recyclerView, param1UpdateOp.positionStart, param1UpdateOp.itemCount, 1);
                  } 
                } else {
                  RecyclerView recyclerView = RecyclerView.this;
                  recyclerView.mLayout.onItemsUpdated(recyclerView, param1UpdateOp.positionStart, param1UpdateOp.itemCount, param1UpdateOp.payload);
                } 
              } else {
                RecyclerView recyclerView = RecyclerView.this;
                recyclerView.mLayout.onItemsRemoved(recyclerView, param1UpdateOp.positionStart, param1UpdateOp.itemCount);
              } 
            } else {
              RecyclerView recyclerView = RecyclerView.this;
              recyclerView.mLayout.onItemsAdded(recyclerView, param1UpdateOp.positionStart, param1UpdateOp.itemCount);
            } 
          }
          
          public RecyclerView.ViewHolder findViewHolder(int param1Int) {
            RecyclerView.ViewHolder viewHolder = RecyclerView.this.findViewHolderForPosition(param1Int, true);
            return (viewHolder == null) ? null : (RecyclerView.this.mChildHelper.isHidden(viewHolder.itemView) ? null : viewHolder);
          }
          
          public void markViewHoldersUpdated(int param1Int1, int param1Int2, Object param1Object) {
            RecyclerView.this.viewRangeUpdate(param1Int1, param1Int2, param1Object);
            RecyclerView.this.mItemsChanged = true;
          }
          
          public void offsetPositionsForAdd(int param1Int1, int param1Int2) {
            RecyclerView.this.offsetPositionRecordsForInsert(param1Int1, param1Int2);
            RecyclerView.this.mItemsAddedOrRemoved = true;
          }
          
          public void offsetPositionsForMove(int param1Int1, int param1Int2) {
            RecyclerView.this.offsetPositionRecordsForMove(param1Int1, param1Int2);
            RecyclerView.this.mItemsAddedOrRemoved = true;
          }
          
          public void offsetPositionsForRemovingInvisible(int param1Int1, int param1Int2) {
            RecyclerView.this.offsetPositionRecordsForRemove(param1Int1, param1Int2, true);
            RecyclerView recyclerView = RecyclerView.this;
            recyclerView.mItemsAddedOrRemoved = true;
            RecyclerView.State state = recyclerView.mState;
            state.mDeletedInvisibleItemCountSincePreviousLayout += param1Int2;
          }
          
          public void offsetPositionsForRemovingLaidOutOrNewView(int param1Int1, int param1Int2) {
            RecyclerView.this.offsetPositionRecordsForRemove(param1Int1, param1Int2, false);
            RecyclerView.this.mItemsAddedOrRemoved = true;
          }
          
          public void onDispatchFirstPass(AdapterHelper.UpdateOp param1UpdateOp) {
            dispatchUpdate(param1UpdateOp);
          }
          
          public void onDispatchSecondPass(AdapterHelper.UpdateOp param1UpdateOp) {
            dispatchUpdate(param1UpdateOp);
          }
        });
  }
  
  @VisibleForTesting
  void initFastScroller(StateListDrawable paramStateListDrawable1, Drawable paramDrawable1, StateListDrawable paramStateListDrawable2, Drawable paramDrawable2) {
    if (paramStateListDrawable1 != null && paramDrawable1 != null && paramStateListDrawable2 != null && paramDrawable2 != null) {
      Resources resources = getContext().getResources();
      new FastScroller(this, paramStateListDrawable1, paramDrawable1, paramStateListDrawable2, paramDrawable2, resources.getDimensionPixelSize(R.dimen.fastscroll_default_thickness), resources.getDimensionPixelSize(R.dimen.fastscroll_minimum_range), resources.getDimensionPixelOffset(R.dimen.fastscroll_margin));
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Trying to set fast scroller without both required drawables.");
    stringBuilder.append(exceptionLabel());
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  void invalidateGlows() {
    this.mBottomGlow = null;
    this.mTopGlow = null;
    this.mRightGlow = null;
    this.mLeftGlow = null;
  }
  
  public void invalidateItemDecorations() {
    if (this.mItemDecorations.size() == 0)
      return; 
    LayoutManager layoutManager = this.mLayout;
    if (layoutManager != null)
      layoutManager.assertNotInLayoutOrScroll("Cannot invalidate item decorations during a scroll or layout"); 
    markItemDecorInsetsDirty();
    requestLayout();
  }
  
  boolean isAccessibilityEnabled() {
    boolean bool;
    AccessibilityManager accessibilityManager = this.mAccessibilityManager;
    if (accessibilityManager != null && accessibilityManager.isEnabled()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isAnimating() {
    boolean bool;
    ItemAnimator itemAnimator = this.mItemAnimator;
    if (itemAnimator != null && itemAnimator.isRunning()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isAttachedToWindow() {
    return this.mIsAttached;
  }
  
  public boolean isComputingLayout() {
    boolean bool;
    if (this.mLayoutOrScrollCounter > 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isLayoutFrozen() {
    return this.mLayoutFrozen;
  }
  
  public boolean isNestedScrollingEnabled() {
    return getScrollingChildHelper().isNestedScrollingEnabled();
  }
  
  void jumpToPositionForSmoothScroller(int paramInt) {
    LayoutManager layoutManager = this.mLayout;
    if (layoutManager == null)
      return; 
    layoutManager.scrollToPosition(paramInt);
    awakenScrollBars();
  }
  
  void markItemDecorInsetsDirty() {
    int i = this.mChildHelper.getUnfilteredChildCount();
    for (byte b = 0; b < i; b++)
      ((LayoutParams)this.mChildHelper.getUnfilteredChildAt(b).getLayoutParams()).mInsetsDirty = true; 
    this.mRecycler.markItemDecorInsetsDirty();
  }
  
  void markKnownViewsInvalid() {
    int i = this.mChildHelper.getUnfilteredChildCount();
    for (byte b = 0; b < i; b++) {
      ViewHolder viewHolder = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(b));
      if (viewHolder != null && !viewHolder.shouldIgnore())
        viewHolder.addFlags(6); 
    } 
    markItemDecorInsetsDirty();
    this.mRecycler.markKnownViewsInvalid();
  }
  
  public void offsetChildrenHorizontal(int paramInt) {
    int i = this.mChildHelper.getChildCount();
    for (byte b = 0; b < i; b++)
      this.mChildHelper.getChildAt(b).offsetLeftAndRight(paramInt); 
  }
  
  public void offsetChildrenVertical(int paramInt) {
    int i = this.mChildHelper.getChildCount();
    for (byte b = 0; b < i; b++)
      this.mChildHelper.getChildAt(b).offsetTopAndBottom(paramInt); 
  }
  
  void offsetPositionRecordsForInsert(int paramInt1, int paramInt2) {
    int i = this.mChildHelper.getUnfilteredChildCount();
    for (byte b = 0; b < i; b++) {
      ViewHolder viewHolder = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(b));
      if (viewHolder != null && !viewHolder.shouldIgnore() && viewHolder.mPosition >= paramInt1) {
        viewHolder.offsetPosition(paramInt2, false);
        this.mState.mStructureChanged = true;
      } 
    } 
    this.mRecycler.offsetPositionRecordsForInsert(paramInt1, paramInt2);
    requestLayout();
  }
  
  void offsetPositionRecordsForMove(int paramInt1, int paramInt2) {
    boolean bool;
    int j;
    int k;
    int i = this.mChildHelper.getUnfilteredChildCount();
    if (paramInt1 < paramInt2) {
      bool = true;
      j = paramInt1;
      k = paramInt2;
    } else {
      k = paramInt1;
      j = paramInt2;
      bool = true;
    } 
    for (byte b = 0; b < i; b++) {
      ViewHolder viewHolder = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(b));
      if (viewHolder != null) {
        int m = viewHolder.mPosition;
        if (m >= j && m <= k) {
          if (m == paramInt1) {
            viewHolder.offsetPosition(paramInt2 - paramInt1, false);
          } else {
            viewHolder.offsetPosition(bool, false);
          } 
          this.mState.mStructureChanged = true;
        } 
      } 
    } 
    this.mRecycler.offsetPositionRecordsForMove(paramInt1, paramInt2);
    requestLayout();
  }
  
  void offsetPositionRecordsForRemove(int paramInt1, int paramInt2, boolean paramBoolean) {
    int i = this.mChildHelper.getUnfilteredChildCount();
    for (byte b = 0; b < i; b++) {
      ViewHolder viewHolder = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(b));
      if (viewHolder != null && !viewHolder.shouldIgnore()) {
        int j = viewHolder.mPosition;
        if (j >= paramInt1 + paramInt2) {
          viewHolder.offsetPosition(-paramInt2, paramBoolean);
          this.mState.mStructureChanged = true;
        } else if (j >= paramInt1) {
          viewHolder.flagRemovedAndOffsetPosition(paramInt1 - 1, -paramInt2, paramBoolean);
          this.mState.mStructureChanged = true;
        } 
      } 
    } 
    this.mRecycler.offsetPositionRecordsForRemove(paramInt1, paramInt2, paramBoolean);
    requestLayout();
  }
  
  protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    this.mLayoutOrScrollCounter = 0;
    boolean bool = true;
    this.mIsAttached = true;
    if (!this.mFirstLayoutComplete || isLayoutRequested())
      bool = false; 
    this.mFirstLayoutComplete = bool;
    LayoutManager layoutManager = this.mLayout;
    if (layoutManager != null)
      layoutManager.dispatchAttachedToWindow(this); 
    this.mPostedAnimatorRunner = false;
    if (ALLOW_THREAD_GAP_WORK) {
      ThreadLocal<GapWorker> threadLocal = GapWorker.sGapWorker;
      GapWorker gapWorker = threadLocal.get();
      this.mGapWorker = gapWorker;
      if (gapWorker == null) {
        this.mGapWorker = new GapWorker();
        Display display = ViewCompat.getDisplay((View)this);
        float f1 = 60.0F;
        float f2 = f1;
        if (!isInEditMode()) {
          f2 = f1;
          if (display != null) {
            float f = display.getRefreshRate();
            f2 = f1;
            if (f >= 30.0F)
              f2 = f; 
          } 
        } 
        GapWorker gapWorker1 = this.mGapWorker;
        gapWorker1.mFrameIntervalNs = (long)(1.0E9F / f2);
        threadLocal.set(gapWorker1);
      } 
      this.mGapWorker.add(this);
    } 
  }
  
  public void onChildAttachedToWindow(View paramView) {}
  
  public void onChildDetachedFromWindow(View paramView) {}
  
  protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    ItemAnimator itemAnimator = this.mItemAnimator;
    if (itemAnimator != null)
      itemAnimator.endAnimations(); 
    stopScroll();
    this.mIsAttached = false;
    LayoutManager layoutManager = this.mLayout;
    if (layoutManager != null)
      layoutManager.dispatchDetachedFromWindow(this, this.mRecycler); 
    this.mPendingAccessibilityImportanceChange.clear();
    removeCallbacks(this.mItemAnimatorRunner);
    this.mViewInfoStore.onDetach();
    if (ALLOW_THREAD_GAP_WORK) {
      this.mGapWorker.remove(this);
      this.mGapWorker = null;
    } 
  }
  
  public void onDraw(Canvas paramCanvas) {
    super.onDraw(paramCanvas);
    int i = this.mItemDecorations.size();
    for (byte b = 0; b < i; b++)
      ((ItemDecoration)this.mItemDecorations.get(b)).onDraw(paramCanvas, this, this.mState); 
  }
  
  void onEnterLayoutOrScroll() {
    this.mLayoutOrScrollCounter++;
  }
  
  void onExitLayoutOrScroll() {
    onExitLayoutOrScroll(true);
  }
  
  void onExitLayoutOrScroll(boolean paramBoolean) {
    int i = this.mLayoutOrScrollCounter - 1;
    this.mLayoutOrScrollCounter = i;
    if (i < 1) {
      this.mLayoutOrScrollCounter = 0;
      if (paramBoolean) {
        dispatchContentChangedIfNecessary();
        dispatchPendingImportantForAccessibilityChanges();
      } 
    } 
  }
  
  public boolean onGenericMotionEvent(MotionEvent paramMotionEvent) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mLayout : Landroid/support/v7/widget/RecyclerView$LayoutManager;
    //   4: ifnonnull -> 9
    //   7: iconst_0
    //   8: ireturn
    //   9: aload_0
    //   10: getfield mLayoutFrozen : Z
    //   13: ifeq -> 18
    //   16: iconst_0
    //   17: ireturn
    //   18: aload_1
    //   19: invokevirtual getAction : ()I
    //   22: bipush #8
    //   24: if_icmpne -> 172
    //   27: aload_1
    //   28: invokevirtual getSource : ()I
    //   31: iconst_2
    //   32: iand
    //   33: ifeq -> 87
    //   36: aload_0
    //   37: getfield mLayout : Landroid/support/v7/widget/RecyclerView$LayoutManager;
    //   40: invokevirtual canScrollVertically : ()Z
    //   43: ifeq -> 57
    //   46: aload_1
    //   47: bipush #9
    //   49: invokevirtual getAxisValue : (I)F
    //   52: fneg
    //   53: fstore_2
    //   54: goto -> 59
    //   57: fconst_0
    //   58: fstore_2
    //   59: fload_2
    //   60: fstore_3
    //   61: aload_0
    //   62: getfield mLayout : Landroid/support/v7/widget/RecyclerView$LayoutManager;
    //   65: invokevirtual canScrollHorizontally : ()Z
    //   68: ifeq -> 138
    //   71: aload_1
    //   72: bipush #10
    //   74: invokevirtual getAxisValue : (I)F
    //   77: fstore #4
    //   79: fload_2
    //   80: fstore_3
    //   81: fload #4
    //   83: fstore_2
    //   84: goto -> 140
    //   87: aload_1
    //   88: invokevirtual getSource : ()I
    //   91: ldc_w 4194304
    //   94: iand
    //   95: ifeq -> 136
    //   98: aload_1
    //   99: bipush #26
    //   101: invokevirtual getAxisValue : (I)F
    //   104: fstore_2
    //   105: aload_0
    //   106: getfield mLayout : Landroid/support/v7/widget/RecyclerView$LayoutManager;
    //   109: invokevirtual canScrollVertically : ()Z
    //   112: ifeq -> 121
    //   115: fload_2
    //   116: fneg
    //   117: fstore_3
    //   118: goto -> 138
    //   121: aload_0
    //   122: getfield mLayout : Landroid/support/v7/widget/RecyclerView$LayoutManager;
    //   125: invokevirtual canScrollHorizontally : ()Z
    //   128: ifeq -> 136
    //   131: fconst_0
    //   132: fstore_3
    //   133: goto -> 140
    //   136: fconst_0
    //   137: fstore_3
    //   138: fconst_0
    //   139: fstore_2
    //   140: fload_3
    //   141: fconst_0
    //   142: fcmpl
    //   143: ifne -> 152
    //   146: fload_2
    //   147: fconst_0
    //   148: fcmpl
    //   149: ifeq -> 172
    //   152: aload_0
    //   153: fload_2
    //   154: aload_0
    //   155: getfield mScaledHorizontalScrollFactor : F
    //   158: fmul
    //   159: f2i
    //   160: fload_3
    //   161: aload_0
    //   162: getfield mScaledVerticalScrollFactor : F
    //   165: fmul
    //   166: f2i
    //   167: aload_1
    //   168: invokevirtual scrollByInternal : (IILandroid/view/MotionEvent;)Z
    //   171: pop
    //   172: iconst_0
    //   173: ireturn
  }
  
  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent) {
    StringBuilder stringBuilder;
    int i;
    boolean bool1 = this.mLayoutFrozen;
    boolean bool = false;
    if (bool1)
      return false; 
    if (dispatchOnItemTouchIntercept(paramMotionEvent)) {
      cancelTouch();
      return true;
    } 
    LayoutManager layoutManager = this.mLayout;
    if (layoutManager == null)
      return false; 
    boolean bool2 = layoutManager.canScrollHorizontally();
    bool1 = this.mLayout.canScrollVertically();
    if (this.mVelocityTracker == null)
      this.mVelocityTracker = VelocityTracker.obtain(); 
    this.mVelocityTracker.addMovement(paramMotionEvent);
    int j = paramMotionEvent.getActionMasked();
    int k = paramMotionEvent.getActionIndex();
    if (j != 0) {
      if (j != 1) {
        if (j != 2) {
          if (j != 3) {
            if (j != 5) {
              if (j == 6)
                onPointerUp(paramMotionEvent); 
            } else {
              this.mScrollPointerId = paramMotionEvent.getPointerId(k);
              i = (int)(paramMotionEvent.getX(k) + 0.5F);
              this.mLastTouchX = i;
              this.mInitialTouchX = i;
              k = (int)(paramMotionEvent.getY(k) + 0.5F);
              this.mLastTouchY = k;
              this.mInitialTouchY = k;
            } 
          } else {
            cancelTouch();
          } 
        } else {
          j = paramMotionEvent.findPointerIndex(this.mScrollPointerId);
          if (j < 0) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Error processing scroll; pointer index for id ");
            stringBuilder.append(this.mScrollPointerId);
            stringBuilder.append(" not found. Did any MotionEvents get skipped?");
            Log.e("RecyclerView", stringBuilder.toString());
            return false;
          } 
          k = (int)(stringBuilder.getX(j) + 0.5F);
          int m = (int)(stringBuilder.getY(j) + 0.5F);
          if (this.mScrollState != 1) {
            int n = this.mInitialTouchX;
            j = this.mInitialTouchY;
            if (i != 0 && Math.abs(k - n) > this.mTouchSlop) {
              this.mLastTouchX = k;
              k = 1;
            } else {
              k = 0;
            } 
            i = k;
            if (bool1) {
              i = k;
              if (Math.abs(m - j) > this.mTouchSlop) {
                this.mLastTouchY = m;
                i = 1;
              } 
            } 
            if (i != 0)
              setScrollState(1); 
          } 
        } 
      } else {
        this.mVelocityTracker.clear();
        stopNestedScroll(0);
      } 
    } else {
      if (this.mIgnoreMotionEventTillDown)
        this.mIgnoreMotionEventTillDown = false; 
      this.mScrollPointerId = stringBuilder.getPointerId(0);
      k = (int)(stringBuilder.getX() + 0.5F);
      this.mLastTouchX = k;
      this.mInitialTouchX = k;
      k = (int)(stringBuilder.getY() + 0.5F);
      this.mLastTouchY = k;
      this.mInitialTouchY = k;
      if (this.mScrollState == 2) {
        getParent().requestDisallowInterceptTouchEvent(true);
        setScrollState(1);
      } 
      int[] arrayOfInt = this.mNestedOffsets;
      arrayOfInt[1] = 0;
      arrayOfInt[0] = 0;
      k = i;
      if (bool1)
        k = i | 0x2; 
      startNestedScroll(k, 0);
    } 
    if (this.mScrollState == 1)
      bool = true; 
    return bool;
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    TraceCompat.beginSection("RV OnLayout");
    dispatchLayout();
    TraceCompat.endSection();
    this.mFirstLayoutComplete = true;
  }
  
  protected void onMeasure(int paramInt1, int paramInt2) {
    LayoutManager layoutManager = this.mLayout;
    if (layoutManager == null) {
      defaultOnMeasure(paramInt1, paramInt2);
      return;
    } 
    boolean bool = layoutManager.mAutoMeasure;
    boolean bool1 = false;
    if (bool) {
      int i = View.MeasureSpec.getMode(paramInt1);
      int j = View.MeasureSpec.getMode(paramInt2);
      boolean bool2 = bool1;
      if (i == 1073741824) {
        bool2 = bool1;
        if (j == 1073741824)
          bool2 = true; 
      } 
      this.mLayout.onMeasure(this.mRecycler, this.mState, paramInt1, paramInt2);
      if (bool2 || this.mAdapter == null)
        return; 
      if (this.mState.mLayoutStep == 1)
        dispatchLayoutStep1(); 
      this.mLayout.setMeasureSpecs(paramInt1, paramInt2);
      this.mState.mIsMeasuring = true;
      dispatchLayoutStep2();
      this.mLayout.setMeasuredDimensionFromChildren(paramInt1, paramInt2);
      if (this.mLayout.shouldMeasureTwice()) {
        this.mLayout.setMeasureSpecs(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824));
        this.mState.mIsMeasuring = true;
        dispatchLayoutStep2();
        this.mLayout.setMeasuredDimensionFromChildren(paramInt1, paramInt2);
      } 
    } else {
      if (this.mHasFixedSize) {
        layoutManager.onMeasure(this.mRecycler, this.mState, paramInt1, paramInt2);
        return;
      } 
      if (this.mAdapterUpdateDuringMeasure) {
        eatRequestLayout();
        onEnterLayoutOrScroll();
        processAdapterUpdatesAndSetAnimationFlags();
        onExitLayoutOrScroll();
        State state = this.mState;
        if (state.mRunPredictiveAnimations) {
          state.mInPreLayout = true;
        } else {
          this.mAdapterHelper.consumeUpdatesInOnePass();
          this.mState.mInPreLayout = false;
        } 
        this.mAdapterUpdateDuringMeasure = false;
        resumeRequestLayout(false);
      } else if (this.mState.mRunPredictiveAnimations) {
        setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
        return;
      } 
      Adapter adapter = this.mAdapter;
      if (adapter != null) {
        this.mState.mItemCount = adapter.getItemCount();
      } else {
        this.mState.mItemCount = 0;
      } 
      eatRequestLayout();
      this.mLayout.onMeasure(this.mRecycler, this.mState, paramInt1, paramInt2);
      resumeRequestLayout(false);
      this.mState.mInPreLayout = false;
    } 
  }
  
  protected boolean onRequestFocusInDescendants(int paramInt, Rect paramRect) {
    return isComputingLayout() ? false : super.onRequestFocusInDescendants(paramInt, paramRect);
  }
  
  protected void onRestoreInstanceState(Parcelable paramParcelable) {
    if (!(paramParcelable instanceof SavedState)) {
      super.onRestoreInstanceState(paramParcelable);
      return;
    } 
    SavedState savedState = (SavedState)paramParcelable;
    this.mPendingSavedState = savedState;
    super.onRestoreInstanceState(savedState.getSuperState());
    LayoutManager layoutManager = this.mLayout;
    if (layoutManager != null) {
      Parcelable parcelable = this.mPendingSavedState.mLayoutState;
      if (parcelable != null)
        layoutManager.onRestoreInstanceState(parcelable); 
    } 
  }
  
  protected Parcelable onSaveInstanceState() {
    SavedState savedState1 = new SavedState(super.onSaveInstanceState());
    SavedState savedState2 = this.mPendingSavedState;
    if (savedState2 != null) {
      savedState1.copyFrom(savedState2);
    } else {
      LayoutManager layoutManager = this.mLayout;
      if (layoutManager != null) {
        savedState1.mLayoutState = layoutManager.onSaveInstanceState();
      } else {
        savedState1.mLayoutState = null;
      } 
    } 
    return (Parcelable)savedState1;
  }
  
  public void onScrollStateChanged(int paramInt) {}
  
  public void onScrolled(int paramInt1, int paramInt2) {}
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if (paramInt1 != paramInt3 || paramInt2 != paramInt4)
      invalidateGlows(); 
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mLayoutFrozen : Z
    //   4: istore_2
    //   5: iconst_0
    //   6: istore_3
    //   7: iload_2
    //   8: ifne -> 983
    //   11: aload_0
    //   12: getfield mIgnoreMotionEventTillDown : Z
    //   15: ifeq -> 21
    //   18: goto -> 983
    //   21: aload_0
    //   22: aload_1
    //   23: invokespecial dispatchOnItemTouch : (Landroid/view/MotionEvent;)Z
    //   26: ifeq -> 35
    //   29: aload_0
    //   30: invokespecial cancelTouch : ()V
    //   33: iconst_1
    //   34: ireturn
    //   35: aload_0
    //   36: getfield mLayout : Landroid/support/v7/widget/RecyclerView$LayoutManager;
    //   39: astore #4
    //   41: aload #4
    //   43: ifnonnull -> 48
    //   46: iconst_0
    //   47: ireturn
    //   48: aload #4
    //   50: invokevirtual canScrollHorizontally : ()Z
    //   53: istore #5
    //   55: aload_0
    //   56: getfield mLayout : Landroid/support/v7/widget/RecyclerView$LayoutManager;
    //   59: invokevirtual canScrollVertically : ()Z
    //   62: istore_2
    //   63: aload_0
    //   64: getfield mVelocityTracker : Landroid/view/VelocityTracker;
    //   67: ifnonnull -> 77
    //   70: aload_0
    //   71: invokestatic obtain : ()Landroid/view/VelocityTracker;
    //   74: putfield mVelocityTracker : Landroid/view/VelocityTracker;
    //   77: aload_1
    //   78: invokestatic obtain : (Landroid/view/MotionEvent;)Landroid/view/MotionEvent;
    //   81: astore #4
    //   83: aload_1
    //   84: invokevirtual getActionMasked : ()I
    //   87: istore #6
    //   89: aload_1
    //   90: invokevirtual getActionIndex : ()I
    //   93: istore #7
    //   95: iload #6
    //   97: ifne -> 116
    //   100: aload_0
    //   101: getfield mNestedOffsets : [I
    //   104: astore #8
    //   106: aload #8
    //   108: iconst_1
    //   109: iconst_0
    //   110: iastore
    //   111: aload #8
    //   113: iconst_0
    //   114: iconst_0
    //   115: iastore
    //   116: aload_0
    //   117: getfield mNestedOffsets : [I
    //   120: astore #8
    //   122: aload #4
    //   124: aload #8
    //   126: iconst_0
    //   127: iaload
    //   128: i2f
    //   129: aload #8
    //   131: iconst_1
    //   132: iaload
    //   133: i2f
    //   134: invokevirtual offsetLocation : (FF)V
    //   137: iload #6
    //   139: ifeq -> 882
    //   142: iload #6
    //   144: iconst_1
    //   145: if_icmpeq -> 767
    //   148: iload #6
    //   150: iconst_2
    //   151: if_icmpeq -> 266
    //   154: iload #6
    //   156: iconst_3
    //   157: if_icmpeq -> 256
    //   160: iload #6
    //   162: iconst_5
    //   163: if_icmpeq -> 190
    //   166: iload #6
    //   168: bipush #6
    //   170: if_icmpeq -> 179
    //   173: iload_3
    //   174: istore #7
    //   176: goto -> 962
    //   179: aload_0
    //   180: aload_1
    //   181: invokespecial onPointerUp : (Landroid/view/MotionEvent;)V
    //   184: iload_3
    //   185: istore #7
    //   187: goto -> 962
    //   190: aload_0
    //   191: aload_1
    //   192: iload #7
    //   194: invokevirtual getPointerId : (I)I
    //   197: putfield mScrollPointerId : I
    //   200: aload_1
    //   201: iload #7
    //   203: invokevirtual getX : (I)F
    //   206: ldc_w 0.5
    //   209: fadd
    //   210: f2i
    //   211: istore #6
    //   213: aload_0
    //   214: iload #6
    //   216: putfield mLastTouchX : I
    //   219: aload_0
    //   220: iload #6
    //   222: putfield mInitialTouchX : I
    //   225: aload_1
    //   226: iload #7
    //   228: invokevirtual getY : (I)F
    //   231: ldc_w 0.5
    //   234: fadd
    //   235: f2i
    //   236: istore #7
    //   238: aload_0
    //   239: iload #7
    //   241: putfield mLastTouchY : I
    //   244: aload_0
    //   245: iload #7
    //   247: putfield mInitialTouchY : I
    //   250: iload_3
    //   251: istore #7
    //   253: goto -> 962
    //   256: aload_0
    //   257: invokespecial cancelTouch : ()V
    //   260: iload_3
    //   261: istore #7
    //   263: goto -> 962
    //   266: aload_1
    //   267: aload_0
    //   268: getfield mScrollPointerId : I
    //   271: invokevirtual findPointerIndex : (I)I
    //   274: istore #7
    //   276: iload #7
    //   278: ifge -> 326
    //   281: new java/lang/StringBuilder
    //   284: dup
    //   285: invokespecial <init> : ()V
    //   288: astore_1
    //   289: aload_1
    //   290: ldc_w 'Error processing scroll; pointer index for id '
    //   293: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   296: pop
    //   297: aload_1
    //   298: aload_0
    //   299: getfield mScrollPointerId : I
    //   302: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   305: pop
    //   306: aload_1
    //   307: ldc_w ' not found. Did any MotionEvents get skipped?'
    //   310: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   313: pop
    //   314: ldc 'RecyclerView'
    //   316: aload_1
    //   317: invokevirtual toString : ()Ljava/lang/String;
    //   320: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   323: pop
    //   324: iconst_0
    //   325: ireturn
    //   326: aload_1
    //   327: iload #7
    //   329: invokevirtual getX : (I)F
    //   332: ldc_w 0.5
    //   335: fadd
    //   336: f2i
    //   337: istore #9
    //   339: aload_1
    //   340: iload #7
    //   342: invokevirtual getY : (I)F
    //   345: ldc_w 0.5
    //   348: fadd
    //   349: f2i
    //   350: istore #10
    //   352: aload_0
    //   353: getfield mLastTouchX : I
    //   356: iload #9
    //   358: isub
    //   359: istore #11
    //   361: aload_0
    //   362: getfield mLastTouchY : I
    //   365: iload #10
    //   367: isub
    //   368: istore #12
    //   370: iload #11
    //   372: istore #6
    //   374: iload #12
    //   376: istore #7
    //   378: aload_0
    //   379: iload #11
    //   381: iload #12
    //   383: aload_0
    //   384: getfield mScrollConsumed : [I
    //   387: aload_0
    //   388: getfield mScrollOffset : [I
    //   391: iconst_0
    //   392: invokevirtual dispatchNestedPreScroll : (II[I[II)Z
    //   395: ifeq -> 476
    //   398: aload_0
    //   399: getfield mScrollConsumed : [I
    //   402: astore_1
    //   403: iload #11
    //   405: aload_1
    //   406: iconst_0
    //   407: iaload
    //   408: isub
    //   409: istore #6
    //   411: iload #12
    //   413: aload_1
    //   414: iconst_1
    //   415: iaload
    //   416: isub
    //   417: istore #7
    //   419: aload_0
    //   420: getfield mScrollOffset : [I
    //   423: astore_1
    //   424: aload #4
    //   426: aload_1
    //   427: iconst_0
    //   428: iaload
    //   429: i2f
    //   430: aload_1
    //   431: iconst_1
    //   432: iaload
    //   433: i2f
    //   434: invokevirtual offsetLocation : (FF)V
    //   437: aload_0
    //   438: getfield mNestedOffsets : [I
    //   441: astore #8
    //   443: aload #8
    //   445: iconst_0
    //   446: iaload
    //   447: istore #12
    //   449: aload_0
    //   450: getfield mScrollOffset : [I
    //   453: astore_1
    //   454: aload #8
    //   456: iconst_0
    //   457: iload #12
    //   459: aload_1
    //   460: iconst_0
    //   461: iaload
    //   462: iadd
    //   463: iastore
    //   464: aload #8
    //   466: iconst_1
    //   467: aload #8
    //   469: iconst_1
    //   470: iaload
    //   471: aload_1
    //   472: iconst_1
    //   473: iaload
    //   474: iadd
    //   475: iastore
    //   476: iload #6
    //   478: istore #12
    //   480: iload #7
    //   482: istore #11
    //   484: aload_0
    //   485: getfield mScrollState : I
    //   488: iconst_1
    //   489: if_icmpeq -> 639
    //   492: iload #5
    //   494: ifeq -> 545
    //   497: iload #6
    //   499: invokestatic abs : (I)I
    //   502: istore #12
    //   504: aload_0
    //   505: getfield mTouchSlop : I
    //   508: istore #11
    //   510: iload #12
    //   512: iload #11
    //   514: if_icmple -> 545
    //   517: iload #6
    //   519: ifle -> 532
    //   522: iload #6
    //   524: iload #11
    //   526: isub
    //   527: istore #6
    //   529: goto -> 539
    //   532: iload #6
    //   534: iload #11
    //   536: iadd
    //   537: istore #6
    //   539: iconst_1
    //   540: istore #12
    //   542: goto -> 548
    //   545: iconst_0
    //   546: istore #12
    //   548: iload #12
    //   550: istore #13
    //   552: iload #7
    //   554: istore #14
    //   556: iload_2
    //   557: ifeq -> 613
    //   560: iload #7
    //   562: invokestatic abs : (I)I
    //   565: istore #15
    //   567: aload_0
    //   568: getfield mTouchSlop : I
    //   571: istore #11
    //   573: iload #12
    //   575: istore #13
    //   577: iload #7
    //   579: istore #14
    //   581: iload #15
    //   583: iload #11
    //   585: if_icmple -> 613
    //   588: iload #7
    //   590: ifle -> 603
    //   593: iload #7
    //   595: iload #11
    //   597: isub
    //   598: istore #14
    //   600: goto -> 610
    //   603: iload #7
    //   605: iload #11
    //   607: iadd
    //   608: istore #14
    //   610: iconst_1
    //   611: istore #13
    //   613: iload #6
    //   615: istore #12
    //   617: iload #14
    //   619: istore #11
    //   621: iload #13
    //   623: ifeq -> 639
    //   626: aload_0
    //   627: iconst_1
    //   628: invokevirtual setScrollState : (I)V
    //   631: iload #14
    //   633: istore #11
    //   635: iload #6
    //   637: istore #12
    //   639: iload_3
    //   640: istore #7
    //   642: aload_0
    //   643: getfield mScrollState : I
    //   646: iconst_1
    //   647: if_icmpne -> 962
    //   650: aload_0
    //   651: getfield mScrollOffset : [I
    //   654: astore_1
    //   655: aload_0
    //   656: iload #9
    //   658: aload_1
    //   659: iconst_0
    //   660: iaload
    //   661: isub
    //   662: putfield mLastTouchX : I
    //   665: aload_0
    //   666: iload #10
    //   668: aload_1
    //   669: iconst_1
    //   670: iaload
    //   671: isub
    //   672: putfield mLastTouchY : I
    //   675: iload #5
    //   677: ifeq -> 687
    //   680: iload #12
    //   682: istore #7
    //   684: goto -> 690
    //   687: iconst_0
    //   688: istore #7
    //   690: iload_2
    //   691: ifeq -> 701
    //   694: iload #11
    //   696: istore #6
    //   698: goto -> 704
    //   701: iconst_0
    //   702: istore #6
    //   704: aload_0
    //   705: iload #7
    //   707: iload #6
    //   709: aload #4
    //   711: invokevirtual scrollByInternal : (IILandroid/view/MotionEvent;)Z
    //   714: ifeq -> 727
    //   717: aload_0
    //   718: invokevirtual getParent : ()Landroid/view/ViewParent;
    //   721: iconst_1
    //   722: invokeinterface requestDisallowInterceptTouchEvent : (Z)V
    //   727: aload_0
    //   728: getfield mGapWorker : Landroid/support/v7/widget/GapWorker;
    //   731: astore_1
    //   732: iload_3
    //   733: istore #7
    //   735: aload_1
    //   736: ifnull -> 962
    //   739: iload #12
    //   741: ifne -> 752
    //   744: iload_3
    //   745: istore #7
    //   747: iload #11
    //   749: ifeq -> 962
    //   752: aload_1
    //   753: aload_0
    //   754: iload #12
    //   756: iload #11
    //   758: invokevirtual postFromTraversal : (Landroid/support/v7/widget/RecyclerView;II)V
    //   761: iload_3
    //   762: istore #7
    //   764: goto -> 962
    //   767: aload_0
    //   768: getfield mVelocityTracker : Landroid/view/VelocityTracker;
    //   771: aload #4
    //   773: invokevirtual addMovement : (Landroid/view/MotionEvent;)V
    //   776: aload_0
    //   777: getfield mVelocityTracker : Landroid/view/VelocityTracker;
    //   780: sipush #1000
    //   783: aload_0
    //   784: getfield mMaxFlingVelocity : I
    //   787: i2f
    //   788: invokevirtual computeCurrentVelocity : (IF)V
    //   791: iload #5
    //   793: ifeq -> 813
    //   796: aload_0
    //   797: getfield mVelocityTracker : Landroid/view/VelocityTracker;
    //   800: aload_0
    //   801: getfield mScrollPointerId : I
    //   804: invokevirtual getXVelocity : (I)F
    //   807: fneg
    //   808: fstore #16
    //   810: goto -> 816
    //   813: fconst_0
    //   814: fstore #16
    //   816: iload_2
    //   817: ifeq -> 837
    //   820: aload_0
    //   821: getfield mVelocityTracker : Landroid/view/VelocityTracker;
    //   824: aload_0
    //   825: getfield mScrollPointerId : I
    //   828: invokevirtual getYVelocity : (I)F
    //   831: fneg
    //   832: fstore #17
    //   834: goto -> 840
    //   837: fconst_0
    //   838: fstore #17
    //   840: fload #16
    //   842: fconst_0
    //   843: fcmpl
    //   844: ifne -> 854
    //   847: fload #17
    //   849: fconst_0
    //   850: fcmpl
    //   851: ifeq -> 867
    //   854: aload_0
    //   855: fload #16
    //   857: f2i
    //   858: fload #17
    //   860: f2i
    //   861: invokevirtual fling : (II)Z
    //   864: ifne -> 872
    //   867: aload_0
    //   868: iconst_0
    //   869: invokevirtual setScrollState : (I)V
    //   872: aload_0
    //   873: invokespecial resetTouch : ()V
    //   876: iconst_1
    //   877: istore #7
    //   879: goto -> 962
    //   882: aload_0
    //   883: aload_1
    //   884: iconst_0
    //   885: invokevirtual getPointerId : (I)I
    //   888: putfield mScrollPointerId : I
    //   891: aload_1
    //   892: invokevirtual getX : ()F
    //   895: ldc_w 0.5
    //   898: fadd
    //   899: f2i
    //   900: istore #7
    //   902: aload_0
    //   903: iload #7
    //   905: putfield mLastTouchX : I
    //   908: aload_0
    //   909: iload #7
    //   911: putfield mInitialTouchX : I
    //   914: aload_1
    //   915: invokevirtual getY : ()F
    //   918: ldc_w 0.5
    //   921: fadd
    //   922: f2i
    //   923: istore #7
    //   925: aload_0
    //   926: iload #7
    //   928: putfield mLastTouchY : I
    //   931: aload_0
    //   932: iload #7
    //   934: putfield mInitialTouchY : I
    //   937: iload #5
    //   939: istore #7
    //   941: iload_2
    //   942: ifeq -> 951
    //   945: iload #5
    //   947: iconst_2
    //   948: ior
    //   949: istore #7
    //   951: aload_0
    //   952: iload #7
    //   954: iconst_0
    //   955: invokevirtual startNestedScroll : (II)Z
    //   958: pop
    //   959: iload_3
    //   960: istore #7
    //   962: iload #7
    //   964: ifne -> 976
    //   967: aload_0
    //   968: getfield mVelocityTracker : Landroid/view/VelocityTracker;
    //   971: aload #4
    //   973: invokevirtual addMovement : (Landroid/view/MotionEvent;)V
    //   976: aload #4
    //   978: invokevirtual recycle : ()V
    //   981: iconst_1
    //   982: ireturn
    //   983: iconst_0
    //   984: ireturn
  }
  
  void postAnimationRunner() {
    if (!this.mPostedAnimatorRunner && this.mIsAttached) {
      ViewCompat.postOnAnimation((View)this, this.mItemAnimatorRunner);
      this.mPostedAnimatorRunner = true;
    } 
  }
  
  void recordAnimationInfoIfBouncedHiddenView(ViewHolder paramViewHolder, ItemAnimator.ItemHolderInfo paramItemHolderInfo) {
    paramViewHolder.setFlags(0, 8192);
    if (this.mState.mTrackOldChangeHolders && paramViewHolder.isUpdated() && !paramViewHolder.isRemoved() && !paramViewHolder.shouldIgnore()) {
      long l = getChangedHolderKey(paramViewHolder);
      this.mViewInfoStore.addToOldChangeHolders(l, paramViewHolder);
    } 
    this.mViewInfoStore.addToPreLayout(paramViewHolder, paramItemHolderInfo);
  }
  
  void removeAndRecycleViews() {
    ItemAnimator itemAnimator = this.mItemAnimator;
    if (itemAnimator != null)
      itemAnimator.endAnimations(); 
    LayoutManager layoutManager = this.mLayout;
    if (layoutManager != null) {
      layoutManager.removeAndRecycleAllViews(this.mRecycler);
      this.mLayout.removeAndRecycleScrapInt(this.mRecycler);
    } 
    this.mRecycler.clear();
  }
  
  boolean removeAnimatingView(View paramView) {
    eatRequestLayout();
    boolean bool = this.mChildHelper.removeViewIfHidden(paramView);
    if (bool) {
      ViewHolder viewHolder = getChildViewHolderInt(paramView);
      this.mRecycler.unscrapView(viewHolder);
      this.mRecycler.recycleViewHolderInternal(viewHolder);
    } 
    resumeRequestLayout(bool ^ true);
    return bool;
  }
  
  protected void removeDetachedView(View paramView, boolean paramBoolean) {
    StringBuilder stringBuilder;
    ViewHolder viewHolder = getChildViewHolderInt(paramView);
    if (viewHolder != null)
      if (viewHolder.isTmpDetached()) {
        viewHolder.clearTmpDetachFlag();
      } else if (!viewHolder.shouldIgnore()) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Called removeDetachedView with a view which is not flagged as tmp detached.");
        stringBuilder.append(viewHolder);
        stringBuilder.append(exceptionLabel());
        throw new IllegalArgumentException(stringBuilder.toString());
      }  
    stringBuilder.clearAnimation();
    dispatchChildDetached((View)stringBuilder);
    super.removeDetachedView((View)stringBuilder, paramBoolean);
  }
  
  public void removeItemDecoration(ItemDecoration paramItemDecoration) {
    LayoutManager layoutManager = this.mLayout;
    if (layoutManager != null)
      layoutManager.assertNotInLayoutOrScroll("Cannot remove item decoration during a scroll  or layout"); 
    this.mItemDecorations.remove(paramItemDecoration);
    if (this.mItemDecorations.isEmpty()) {
      boolean bool;
      if (getOverScrollMode() == 2) {
        bool = true;
      } else {
        bool = false;
      } 
      setWillNotDraw(bool);
    } 
    markItemDecorInsetsDirty();
    requestLayout();
  }
  
  public void removeOnChildAttachStateChangeListener(OnChildAttachStateChangeListener paramOnChildAttachStateChangeListener) {
    List<OnChildAttachStateChangeListener> list = this.mOnChildAttachStateListeners;
    if (list == null)
      return; 
    list.remove(paramOnChildAttachStateChangeListener);
  }
  
  public void removeOnItemTouchListener(OnItemTouchListener paramOnItemTouchListener) {
    this.mOnItemTouchListeners.remove(paramOnItemTouchListener);
    if (this.mActiveOnItemTouchListener == paramOnItemTouchListener)
      this.mActiveOnItemTouchListener = null; 
  }
  
  public void removeOnScrollListener(OnScrollListener paramOnScrollListener) {
    List<OnScrollListener> list = this.mScrollListeners;
    if (list != null)
      list.remove(paramOnScrollListener); 
  }
  
  void repositionShadowingViews() {
    int i = this.mChildHelper.getChildCount();
    for (byte b = 0; b < i; b++) {
      View view = this.mChildHelper.getChildAt(b);
      ViewHolder viewHolder = getChildViewHolder(view);
      if (viewHolder != null) {
        viewHolder = viewHolder.mShadowingHolder;
        if (viewHolder != null) {
          View view1 = viewHolder.itemView;
          int j = view.getLeft();
          int k = view.getTop();
          if (j != view1.getLeft() || k != view1.getTop())
            view1.layout(j, k, view1.getWidth() + j, view1.getHeight() + k); 
        } 
      } 
    } 
  }
  
  public void requestChildFocus(View paramView1, View paramView2) {
    if (!this.mLayout.onRequestChildFocus(this, this.mState, paramView1, paramView2) && paramView2 != null)
      requestChildOnScreen(paramView1, paramView2); 
    super.requestChildFocus(paramView1, paramView2);
  }
  
  public boolean requestChildRectangleOnScreen(View paramView, Rect paramRect, boolean paramBoolean) {
    return this.mLayout.requestChildRectangleOnScreen(this, paramView, paramRect, paramBoolean);
  }
  
  public void requestDisallowInterceptTouchEvent(boolean paramBoolean) {
    int i = this.mOnItemTouchListeners.size();
    for (byte b = 0; b < i; b++)
      ((OnItemTouchListener)this.mOnItemTouchListeners.get(b)).onRequestDisallowInterceptTouchEvent(paramBoolean); 
    super.requestDisallowInterceptTouchEvent(paramBoolean);
  }
  
  public void requestLayout() {
    if (this.mEatRequestLayout == 0 && !this.mLayoutFrozen) {
      super.requestLayout();
    } else {
      this.mLayoutRequestEaten = true;
    } 
  }
  
  void resumeRequestLayout(boolean paramBoolean) {
    if (this.mEatRequestLayout < 1)
      this.mEatRequestLayout = 1; 
    if (!paramBoolean)
      this.mLayoutRequestEaten = false; 
    if (this.mEatRequestLayout == 1) {
      if (paramBoolean && this.mLayoutRequestEaten && !this.mLayoutFrozen && this.mLayout != null && this.mAdapter != null)
        dispatchLayout(); 
      if (!this.mLayoutFrozen)
        this.mLayoutRequestEaten = false; 
    } 
    this.mEatRequestLayout--;
  }
  
  void saveOldPositions() {
    int i = this.mChildHelper.getUnfilteredChildCount();
    for (byte b = 0; b < i; b++) {
      ViewHolder viewHolder = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(b));
      if (!viewHolder.shouldIgnore())
        viewHolder.saveOldPosition(); 
    } 
  }
  
  public void scrollBy(int paramInt1, int paramInt2) {
    LayoutManager layoutManager = this.mLayout;
    if (layoutManager == null) {
      Log.e("RecyclerView", "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
      return;
    } 
    if (this.mLayoutFrozen)
      return; 
    boolean bool1 = layoutManager.canScrollHorizontally();
    boolean bool2 = this.mLayout.canScrollVertically();
    if (bool1 || bool2) {
      if (!bool1)
        paramInt1 = 0; 
      if (!bool2)
        paramInt2 = 0; 
      scrollByInternal(paramInt1, paramInt2, null);
    } 
  }
  
  boolean scrollByInternal(int paramInt1, int paramInt2, MotionEvent paramMotionEvent) {
    int[] arrayOfInt;
    boolean bool2;
    int i;
    boolean bool3;
    boolean bool4;
    consumePendingUpdateOperations();
    Adapter adapter = this.mAdapter;
    boolean bool1 = false;
    if (adapter != null) {
      eatRequestLayout();
      onEnterLayoutOrScroll();
      TraceCompat.beginSection("RV Scroll");
      fillRemainingScrollValues(this.mState);
      if (paramInt1 != 0) {
        bool2 = this.mLayout.scrollHorizontallyBy(paramInt1, this.mRecycler, this.mState);
        i = paramInt1 - bool2;
      } else {
        bool2 = false;
        i = 0;
      } 
      if (paramInt2 != 0) {
        bool3 = this.mLayout.scrollVerticallyBy(paramInt2, this.mRecycler, this.mState);
        bool4 = paramInt2 - bool3;
      } else {
        bool3 = false;
        bool4 = false;
      } 
      TraceCompat.endSection();
      repositionShadowingViews();
      onExitLayoutOrScroll();
      resumeRequestLayout(false);
    } else {
      bool2 = false;
      i = 0;
      bool3 = false;
      bool4 = false;
    } 
    if (!this.mItemDecorations.isEmpty())
      invalidate(); 
    if (dispatchNestedScroll(bool2, bool3, i, bool4, this.mScrollOffset, 0)) {
      paramInt2 = this.mLastTouchX;
      int[] arrayOfInt1 = this.mScrollOffset;
      paramInt1 = arrayOfInt1[0];
      this.mLastTouchX = paramInt2 - paramInt1;
      paramInt2 = this.mLastTouchY;
      i = arrayOfInt1[1];
      this.mLastTouchY = paramInt2 - i;
      if (paramMotionEvent != null)
        paramMotionEvent.offsetLocation(paramInt1, i); 
      arrayOfInt1 = this.mNestedOffsets;
      paramInt1 = arrayOfInt1[0];
      arrayOfInt = this.mScrollOffset;
      arrayOfInt1[0] = paramInt1 + arrayOfInt[0];
      arrayOfInt1[1] = arrayOfInt1[1] + arrayOfInt[1];
    } else if (getOverScrollMode() != 2) {
      if (arrayOfInt != null && !MotionEventCompat.isFromSource((MotionEvent)arrayOfInt, 8194))
        pullGlows(arrayOfInt.getX(), i, arrayOfInt.getY(), bool4); 
      considerReleasingGlowsOnScroll(paramInt1, paramInt2);
    } 
    if (bool2 || bool3)
      dispatchOnScrolled(bool2, bool3); 
    if (!awakenScrollBars())
      invalidate(); 
    if (bool2 || bool3)
      bool1 = true; 
    return bool1;
  }
  
  public void scrollTo(int paramInt1, int paramInt2) {
    Log.w("RecyclerView", "RecyclerView does not support scrolling to an absolute position. Use scrollToPosition instead");
  }
  
  public void scrollToPosition(int paramInt) {
    if (this.mLayoutFrozen)
      return; 
    stopScroll();
    LayoutManager layoutManager = this.mLayout;
    if (layoutManager == null) {
      Log.e("RecyclerView", "Cannot scroll to position a LayoutManager set. Call setLayoutManager with a non-null argument.");
      return;
    } 
    layoutManager.scrollToPosition(paramInt);
    awakenScrollBars();
  }
  
  public void sendAccessibilityEventUnchecked(AccessibilityEvent paramAccessibilityEvent) {
    if (shouldDeferAccessibilityEvent(paramAccessibilityEvent))
      return; 
    super.sendAccessibilityEventUnchecked(paramAccessibilityEvent);
  }
  
  public void setAccessibilityDelegateCompat(RecyclerViewAccessibilityDelegate paramRecyclerViewAccessibilityDelegate) {
    this.mAccessibilityDelegate = paramRecyclerViewAccessibilityDelegate;
    ViewCompat.setAccessibilityDelegate((View)this, paramRecyclerViewAccessibilityDelegate);
  }
  
  public void setAdapter(Adapter paramAdapter) {
    setLayoutFrozen(false);
    setAdapterInternal(paramAdapter, false, true);
    requestLayout();
  }
  
  public void setChildDrawingOrderCallback(ChildDrawingOrderCallback paramChildDrawingOrderCallback) {
    boolean bool;
    if (paramChildDrawingOrderCallback == this.mChildDrawingOrderCallback)
      return; 
    this.mChildDrawingOrderCallback = paramChildDrawingOrderCallback;
    if (paramChildDrawingOrderCallback != null) {
      bool = true;
    } else {
      bool = false;
    } 
    setChildrenDrawingOrderEnabled(bool);
  }
  
  @VisibleForTesting
  boolean setChildImportantForAccessibilityInternal(ViewHolder paramViewHolder, int paramInt) {
    if (isComputingLayout()) {
      paramViewHolder.mPendingAccessibilityState = paramInt;
      this.mPendingAccessibilityImportanceChange.add(paramViewHolder);
      return false;
    } 
    ViewCompat.setImportantForAccessibility(paramViewHolder.itemView, paramInt);
    return true;
  }
  
  public void setClipToPadding(boolean paramBoolean) {
    if (paramBoolean != this.mClipToPadding)
      invalidateGlows(); 
    this.mClipToPadding = paramBoolean;
    super.setClipToPadding(paramBoolean);
    if (this.mFirstLayoutComplete)
      requestLayout(); 
  }
  
  void setDataSetChangedAfterLayout() {
    this.mDataSetHasChangedAfterLayout = true;
    markKnownViewsInvalid();
  }
  
  public void setHasFixedSize(boolean paramBoolean) {
    this.mHasFixedSize = paramBoolean;
  }
  
  public void setItemAnimator(ItemAnimator paramItemAnimator) {
    ItemAnimator itemAnimator = this.mItemAnimator;
    if (itemAnimator != null) {
      itemAnimator.endAnimations();
      this.mItemAnimator.setListener(null);
    } 
    this.mItemAnimator = paramItemAnimator;
    if (paramItemAnimator != null)
      paramItemAnimator.setListener(this.mItemAnimatorListener); 
  }
  
  public void setItemViewCacheSize(int paramInt) {
    this.mRecycler.setViewCacheSize(paramInt);
  }
  
  public void setLayoutFrozen(boolean paramBoolean) {
    if (paramBoolean != this.mLayoutFrozen) {
      assertNotInLayoutOrScroll("Do not setLayoutFrozen in layout or scroll");
      if (!paramBoolean) {
        this.mLayoutFrozen = false;
        if (this.mLayoutRequestEaten && this.mLayout != null && this.mAdapter != null)
          requestLayout(); 
        this.mLayoutRequestEaten = false;
      } else {
        long l = SystemClock.uptimeMillis();
        onTouchEvent(MotionEvent.obtain(l, l, 3, 0.0F, 0.0F, 0));
        this.mLayoutFrozen = true;
        this.mIgnoreMotionEventTillDown = true;
        stopScroll();
      } 
    } 
  }
  
  public void setLayoutManager(LayoutManager paramLayoutManager) {
    if (paramLayoutManager == this.mLayout)
      return; 
    stopScroll();
    if (this.mLayout != null) {
      ItemAnimator itemAnimator = this.mItemAnimator;
      if (itemAnimator != null)
        itemAnimator.endAnimations(); 
      this.mLayout.removeAndRecycleAllViews(this.mRecycler);
      this.mLayout.removeAndRecycleScrapInt(this.mRecycler);
      this.mRecycler.clear();
      if (this.mIsAttached)
        this.mLayout.dispatchDetachedFromWindow(this, this.mRecycler); 
      this.mLayout.setRecyclerView(null);
      this.mLayout = null;
    } else {
      this.mRecycler.clear();
    } 
    this.mChildHelper.removeAllViewsUnfiltered();
    this.mLayout = paramLayoutManager;
    if (paramLayoutManager != null)
      if (paramLayoutManager.mRecyclerView == null) {
        paramLayoutManager.setRecyclerView(this);
        if (this.mIsAttached)
          this.mLayout.dispatchAttachedToWindow(this); 
      } else {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("LayoutManager ");
        stringBuilder.append(paramLayoutManager);
        stringBuilder.append(" is already attached to a RecyclerView:");
        stringBuilder.append(paramLayoutManager.mRecyclerView.exceptionLabel());
        throw new IllegalArgumentException(stringBuilder.toString());
      }  
    this.mRecycler.updateViewCacheSize();
    requestLayout();
  }
  
  public void setNestedScrollingEnabled(boolean paramBoolean) {
    getScrollingChildHelper().setNestedScrollingEnabled(paramBoolean);
  }
  
  public void setOnFlingListener(@Nullable OnFlingListener paramOnFlingListener) {
    this.mOnFlingListener = paramOnFlingListener;
  }
  
  @Deprecated
  public void setOnScrollListener(OnScrollListener paramOnScrollListener) {
    this.mScrollListener = paramOnScrollListener;
  }
  
  public void setPreserveFocusAfterLayout(boolean paramBoolean) {
    this.mPreserveFocusAfterLayout = paramBoolean;
  }
  
  public void setRecycledViewPool(RecycledViewPool paramRecycledViewPool) {
    this.mRecycler.setRecycledViewPool(paramRecycledViewPool);
  }
  
  public void setRecyclerListener(RecyclerListener paramRecyclerListener) {
    this.mRecyclerListener = paramRecyclerListener;
  }
  
  void setScrollState(int paramInt) {
    if (paramInt == this.mScrollState)
      return; 
    this.mScrollState = paramInt;
    if (paramInt != 2)
      stopScrollersInternal(); 
    dispatchOnScrollStateChanged(paramInt);
  }
  
  public void setScrollingTouchSlop(int paramInt) {
    ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
    if (paramInt != 0)
      if (paramInt != 1) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setScrollingTouchSlop(): bad argument constant ");
        stringBuilder.append(paramInt);
        stringBuilder.append("; using default value");
        Log.w("RecyclerView", stringBuilder.toString());
      } else {
        this.mTouchSlop = viewConfiguration.getScaledPagingTouchSlop();
        return;
      }  
    this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
  }
  
  public void setViewCacheExtension(ViewCacheExtension paramViewCacheExtension) {
    this.mRecycler.setViewCacheExtension(paramViewCacheExtension);
  }
  
  boolean shouldDeferAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent) {
    boolean bool = isComputingLayout();
    boolean bool1 = false;
    if (bool) {
      boolean bool2;
      if (paramAccessibilityEvent != null) {
        bool2 = AccessibilityEventCompat.getContentChangeTypes(paramAccessibilityEvent);
      } else {
        bool2 = false;
      } 
      if (!bool2)
        bool2 = bool1; 
      this.mEatenAccessibilityChangeFlags |= bool2;
      return true;
    } 
    return false;
  }
  
  public void smoothScrollBy(int paramInt1, int paramInt2) {
    smoothScrollBy(paramInt1, paramInt2, null);
  }
  
  public void smoothScrollBy(int paramInt1, int paramInt2, Interpolator paramInterpolator) {
    LayoutManager layoutManager = this.mLayout;
    if (layoutManager == null) {
      Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
      return;
    } 
    if (this.mLayoutFrozen)
      return; 
    if (!layoutManager.canScrollHorizontally())
      paramInt1 = 0; 
    if (!this.mLayout.canScrollVertically())
      paramInt2 = 0; 
    if (paramInt1 != 0 || paramInt2 != 0)
      this.mViewFlinger.smoothScrollBy(paramInt1, paramInt2, paramInterpolator); 
  }
  
  public void smoothScrollToPosition(int paramInt) {
    if (this.mLayoutFrozen)
      return; 
    LayoutManager layoutManager = this.mLayout;
    if (layoutManager == null) {
      Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
      return;
    } 
    layoutManager.smoothScrollToPosition(this, this.mState, paramInt);
  }
  
  public boolean startNestedScroll(int paramInt) {
    return getScrollingChildHelper().startNestedScroll(paramInt);
  }
  
  public boolean startNestedScroll(int paramInt1, int paramInt2) {
    return getScrollingChildHelper().startNestedScroll(paramInt1, paramInt2);
  }
  
  public void stopNestedScroll() {
    getScrollingChildHelper().stopNestedScroll();
  }
  
  public void stopNestedScroll(int paramInt) {
    getScrollingChildHelper().stopNestedScroll(paramInt);
  }
  
  public void stopScroll() {
    setScrollState(0);
    stopScrollersInternal();
  }
  
  public void swapAdapter(Adapter paramAdapter, boolean paramBoolean) {
    setLayoutFrozen(false);
    setAdapterInternal(paramAdapter, true, paramBoolean);
    requestLayout();
  }
  
  void viewRangeUpdate(int paramInt1, int paramInt2, Object paramObject) {
    int i = this.mChildHelper.getUnfilteredChildCount();
    for (byte b = 0; b < i; b++) {
      View view = this.mChildHelper.getUnfilteredChildAt(b);
      ViewHolder viewHolder = getChildViewHolderInt(view);
      if (viewHolder != null && !viewHolder.shouldIgnore()) {
        int j = viewHolder.mPosition;
        if (j >= paramInt1 && j < paramInt1 + paramInt2) {
          viewHolder.addFlags(2);
          viewHolder.addChangePayload(paramObject);
          ((LayoutParams)view.getLayoutParams()).mInsetsDirty = true;
        } 
      } 
    } 
    this.mRecycler.viewRangeUpdate(paramInt1, paramInt2);
  }
  
  static {
    boolean bool;
  }
  
  static final boolean ALLOW_SIZE_IN_UNSPECIFIED_SPEC;
  
  private static final boolean ALLOW_THREAD_GAP_WORK;
  
  private static final int[] CLIP_TO_PADDING_ATTR;
  
  static final boolean DEBUG = false;
  
  static final boolean DISPATCH_TEMP_DETACH = false;
  
  private static final boolean FORCE_ABS_FOCUS_SEARCH_DIRECTION;
  
  static final boolean FORCE_INVALIDATE_DISPLAY_LIST;
  
  static final long FOREVER_NS = 9223372036854775807L;
  
  public static final int HORIZONTAL = 0;
  
  private static final boolean IGNORE_DETACHED_FOCUSED_CHILD;
  
  private static final int INVALID_POINTER = -1;
  
  public static final int INVALID_TYPE = -1;
  
  private static final Class<?>[] LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE;
  
  static final int MAX_SCROLL_DURATION = 2000;
  
  private static final int[] NESTED_SCROLLING_ATTRS = new int[] { 16843830 };
  
  public static final long NO_ID = -1L;
  
  public static final int NO_POSITION = -1;
  
  static final boolean POST_UPDATES_ON_ANIMATION;
  
  public static final int SCROLL_STATE_DRAGGING = 1;
  
  public static final int SCROLL_STATE_IDLE = 0;
  
  public static final int SCROLL_STATE_SETTLING = 2;
  
  static final String TAG = "RecyclerView";
  
  public static final int TOUCH_SLOP_DEFAULT = 0;
  
  public static final int TOUCH_SLOP_PAGING = 1;
  
  static final String TRACE_BIND_VIEW_TAG = "RV OnBindView";
  
  static final String TRACE_CREATE_VIEW_TAG = "RV CreateView";
  
  private static final String TRACE_HANDLE_ADAPTER_UPDATES_TAG = "RV PartialInvalidate";
  
  static final String TRACE_NESTED_PREFETCH_TAG = "RV Nested Prefetch";
  
  private static final String TRACE_ON_DATA_SET_CHANGE_LAYOUT_TAG = "RV FullInvalidate";
  
  private static final String TRACE_ON_LAYOUT_TAG = "RV OnLayout";
  
  static final String TRACE_PREFETCH_TAG = "RV Prefetch";
  
  static final String TRACE_SCROLL_TAG = "RV Scroll";
  
  static final boolean VERBOSE_TRACING = false;
  
  public static final int VERTICAL = 1;
  
  static final Interpolator sQuinticInterpolator;
  
  RecyclerViewAccessibilityDelegate mAccessibilityDelegate;
  
  private final AccessibilityManager mAccessibilityManager;
  
  private OnItemTouchListener mActiveOnItemTouchListener;
  
  Adapter mAdapter;
  
  AdapterHelper mAdapterHelper;
  
  boolean mAdapterUpdateDuringMeasure;
  
  private EdgeEffect mBottomGlow;
  
  private ChildDrawingOrderCallback mChildDrawingOrderCallback;
  
  ChildHelper mChildHelper;
  
  boolean mClipToPadding;
  
  boolean mDataSetHasChangedAfterLayout;
  
  private int mDispatchScrollCounter;
  
  private int mEatRequestLayout;
  
  private int mEatenAccessibilityChangeFlags;
  
  boolean mEnableFastScroller;
  
  @VisibleForTesting
  boolean mFirstLayoutComplete;
  
  GapWorker mGapWorker;
  
  boolean mHasFixedSize;
  
  private boolean mIgnoreMotionEventTillDown;
  
  private int mInitialTouchX;
  
  private int mInitialTouchY;
  
  boolean mIsAttached;
  
  ItemAnimator mItemAnimator;
  
  private ItemAnimator.ItemAnimatorListener mItemAnimatorListener;
  
  private Runnable mItemAnimatorRunner;
  
  final ArrayList<ItemDecoration> mItemDecorations;
  
  boolean mItemsAddedOrRemoved;
  
  boolean mItemsChanged;
  
  private int mLastTouchX;
  
  private int mLastTouchY;
  
  @VisibleForTesting
  LayoutManager mLayout;
  
  boolean mLayoutFrozen;
  
  private int mLayoutOrScrollCounter;
  
  boolean mLayoutRequestEaten;
  
  private EdgeEffect mLeftGlow;
  
  private final int mMaxFlingVelocity;
  
  private final int mMinFlingVelocity;
  
  private final int[] mMinMaxLayoutPositions;
  
  private final int[] mNestedOffsets;
  
  private final RecyclerViewDataObserver mObserver;
  
  private List<OnChildAttachStateChangeListener> mOnChildAttachStateListeners;
  
  private OnFlingListener mOnFlingListener;
  
  private final ArrayList<OnItemTouchListener> mOnItemTouchListeners;
  
  @VisibleForTesting
  final List<ViewHolder> mPendingAccessibilityImportanceChange;
  
  private SavedState mPendingSavedState;
  
  boolean mPostedAnimatorRunner;
  
  GapWorker.LayoutPrefetchRegistryImpl mPrefetchRegistry;
  
  private boolean mPreserveFocusAfterLayout;
  
  final Recycler mRecycler;
  
  RecyclerListener mRecyclerListener;
  
  private EdgeEffect mRightGlow;
  
  private float mScaledHorizontalScrollFactor;
  
  private float mScaledVerticalScrollFactor;
  
  private final int[] mScrollConsumed;
  
  private OnScrollListener mScrollListener;
  
  private List<OnScrollListener> mScrollListeners;
  
  private final int[] mScrollOffset;
  
  private int mScrollPointerId;
  
  private int mScrollState;
  
  private NestedScrollingChildHelper mScrollingChildHelper;
  
  final State mState;
  
  final Rect mTempRect;
  
  private final Rect mTempRect2;
  
  final RectF mTempRectF;
  
  private EdgeEffect mTopGlow;
  
  private int mTouchSlop;
  
  final Runnable mUpdateChildViewsRunnable;
  
  private VelocityTracker mVelocityTracker;
  
  final ViewFlinger mViewFlinger;
  
  private final ViewInfoStore.ProcessCallback mViewInfoProcessCallback;
  
  final ViewInfoStore mViewInfoStore;
  
  public static abstract class Adapter<VH extends ViewHolder> {
    private boolean mHasStableIds = false;
    
    private final RecyclerView.AdapterDataObservable mObservable = new RecyclerView.AdapterDataObservable();
    
    public final void bindViewHolder(VH param1VH, int param1Int) {
      ((RecyclerView.ViewHolder)param1VH).mPosition = param1Int;
      if (hasStableIds())
        ((RecyclerView.ViewHolder)param1VH).mItemId = getItemId(param1Int); 
      param1VH.setFlags(1, 519);
      TraceCompat.beginSection("RV OnBindView");
      onBindViewHolder(param1VH, param1Int, param1VH.getUnmodifiedPayloads());
      param1VH.clearPayload();
      ViewGroup.LayoutParams layoutParams = ((RecyclerView.ViewHolder)param1VH).itemView.getLayoutParams();
      if (layoutParams instanceof RecyclerView.LayoutParams)
        ((RecyclerView.LayoutParams)layoutParams).mInsetsDirty = true; 
      TraceCompat.endSection();
    }
    
    public final VH createViewHolder(ViewGroup param1ViewGroup, int param1Int) {
      TraceCompat.beginSection("RV CreateView");
      param1ViewGroup = (ViewGroup)onCreateViewHolder(param1ViewGroup, param1Int);
      ((RecyclerView.ViewHolder)param1ViewGroup).mItemViewType = param1Int;
      TraceCompat.endSection();
      return (VH)param1ViewGroup;
    }
    
    public abstract int getItemCount();
    
    public long getItemId(int param1Int) {
      return -1L;
    }
    
    public int getItemViewType(int param1Int) {
      return 0;
    }
    
    public final boolean hasObservers() {
      return this.mObservable.hasObservers();
    }
    
    public final boolean hasStableIds() {
      return this.mHasStableIds;
    }
    
    public final void notifyDataSetChanged() {
      this.mObservable.notifyChanged();
    }
    
    public final void notifyItemChanged(int param1Int) {
      this.mObservable.notifyItemRangeChanged(param1Int, 1);
    }
    
    public final void notifyItemChanged(int param1Int, Object param1Object) {
      this.mObservable.notifyItemRangeChanged(param1Int, 1, param1Object);
    }
    
    public final void notifyItemInserted(int param1Int) {
      this.mObservable.notifyItemRangeInserted(param1Int, 1);
    }
    
    public final void notifyItemMoved(int param1Int1, int param1Int2) {
      this.mObservable.notifyItemMoved(param1Int1, param1Int2);
    }
    
    public final void notifyItemRangeChanged(int param1Int1, int param1Int2) {
      this.mObservable.notifyItemRangeChanged(param1Int1, param1Int2);
    }
    
    public final void notifyItemRangeChanged(int param1Int1, int param1Int2, Object param1Object) {
      this.mObservable.notifyItemRangeChanged(param1Int1, param1Int2, param1Object);
    }
    
    public final void notifyItemRangeInserted(int param1Int1, int param1Int2) {
      this.mObservable.notifyItemRangeInserted(param1Int1, param1Int2);
    }
    
    public final void notifyItemRangeRemoved(int param1Int1, int param1Int2) {
      this.mObservable.notifyItemRangeRemoved(param1Int1, param1Int2);
    }
    
    public final void notifyItemRemoved(int param1Int) {
      this.mObservable.notifyItemRangeRemoved(param1Int, 1);
    }
    
    public void onAttachedToRecyclerView(RecyclerView param1RecyclerView) {}
    
    public abstract void onBindViewHolder(VH param1VH, int param1Int);
    
    public void onBindViewHolder(VH param1VH, int param1Int, List<Object> param1List) {
      onBindViewHolder(param1VH, param1Int);
    }
    
    public abstract VH onCreateViewHolder(ViewGroup param1ViewGroup, int param1Int);
    
    public void onDetachedFromRecyclerView(RecyclerView param1RecyclerView) {}
    
    public boolean onFailedToRecycleView(VH param1VH) {
      return false;
    }
    
    public void onViewAttachedToWindow(VH param1VH) {}
    
    public void onViewDetachedFromWindow(VH param1VH) {}
    
    public void onViewRecycled(VH param1VH) {}
    
    public void registerAdapterDataObserver(RecyclerView.AdapterDataObserver param1AdapterDataObserver) {
      this.mObservable.registerObserver(param1AdapterDataObserver);
    }
    
    public void setHasStableIds(boolean param1Boolean) {
      if (!hasObservers()) {
        this.mHasStableIds = param1Boolean;
        return;
      } 
      throw new IllegalStateException("Cannot change whether this adapter has stable IDs while the adapter has registered observers.");
    }
    
    public void unregisterAdapterDataObserver(RecyclerView.AdapterDataObserver param1AdapterDataObserver) {
      this.mObservable.unregisterObserver(param1AdapterDataObserver);
    }
  }
  
  static class AdapterDataObservable extends Observable<AdapterDataObserver> {
    public boolean hasObservers() {
      return this.mObservers.isEmpty() ^ true;
    }
    
    public void notifyChanged() {
      for (int i = this.mObservers.size() - 1; i >= 0; i--)
        ((RecyclerView.AdapterDataObserver)this.mObservers.get(i)).onChanged(); 
    }
    
    public void notifyItemMoved(int param1Int1, int param1Int2) {
      for (int i = this.mObservers.size() - 1; i >= 0; i--)
        ((RecyclerView.AdapterDataObserver)this.mObservers.get(i)).onItemRangeMoved(param1Int1, param1Int2, 1); 
    }
    
    public void notifyItemRangeChanged(int param1Int1, int param1Int2) {
      notifyItemRangeChanged(param1Int1, param1Int2, null);
    }
    
    public void notifyItemRangeChanged(int param1Int1, int param1Int2, Object param1Object) {
      for (int i = this.mObservers.size() - 1; i >= 0; i--)
        ((RecyclerView.AdapterDataObserver)this.mObservers.get(i)).onItemRangeChanged(param1Int1, param1Int2, param1Object); 
    }
    
    public void notifyItemRangeInserted(int param1Int1, int param1Int2) {
      for (int i = this.mObservers.size() - 1; i >= 0; i--)
        ((RecyclerView.AdapterDataObserver)this.mObservers.get(i)).onItemRangeInserted(param1Int1, param1Int2); 
    }
    
    public void notifyItemRangeRemoved(int param1Int1, int param1Int2) {
      for (int i = this.mObservers.size() - 1; i >= 0; i--)
        ((RecyclerView.AdapterDataObserver)this.mObservers.get(i)).onItemRangeRemoved(param1Int1, param1Int2); 
    }
  }
  
  public static abstract class AdapterDataObserver {
    public void onChanged() {}
    
    public void onItemRangeChanged(int param1Int1, int param1Int2) {}
    
    public void onItemRangeChanged(int param1Int1, int param1Int2, Object param1Object) {
      onItemRangeChanged(param1Int1, param1Int2);
    }
    
    public void onItemRangeInserted(int param1Int1, int param1Int2) {}
    
    public void onItemRangeMoved(int param1Int1, int param1Int2, int param1Int3) {}
    
    public void onItemRangeRemoved(int param1Int1, int param1Int2) {}
  }
  
  public static interface ChildDrawingOrderCallback {
    int onGetChildDrawingOrder(int param1Int1, int param1Int2);
  }
  
  public static abstract class ItemAnimator {
    public static final int FLAG_APPEARED_IN_PRE_LAYOUT = 4096;
    
    public static final int FLAG_CHANGED = 2;
    
    public static final int FLAG_INVALIDATED = 4;
    
    public static final int FLAG_MOVED = 2048;
    
    public static final int FLAG_REMOVED = 8;
    
    private long mAddDuration = 120L;
    
    private long mChangeDuration = 250L;
    
    private ArrayList<ItemAnimatorFinishedListener> mFinishedListeners = new ArrayList<ItemAnimatorFinishedListener>();
    
    private ItemAnimatorListener mListener = null;
    
    private long mMoveDuration = 250L;
    
    private long mRemoveDuration = 120L;
    
    static int buildAdapterChangeFlagsForAnimations(RecyclerView.ViewHolder param1ViewHolder) {
      int i = param1ViewHolder.mFlags & 0xE;
      if (param1ViewHolder.isInvalid())
        return 4; 
      int j = i;
      if ((i & 0x4) == 0) {
        int k = param1ViewHolder.getOldPosition();
        int m = param1ViewHolder.getAdapterPosition();
        j = i;
        if (k != -1) {
          j = i;
          if (m != -1) {
            j = i;
            if (k != m)
              j = i | 0x800; 
          } 
        } 
      } 
      return j;
    }
    
    public abstract boolean animateAppearance(@NonNull RecyclerView.ViewHolder param1ViewHolder, @Nullable ItemHolderInfo param1ItemHolderInfo1, @NonNull ItemHolderInfo param1ItemHolderInfo2);
    
    public abstract boolean animateChange(@NonNull RecyclerView.ViewHolder param1ViewHolder1, @NonNull RecyclerView.ViewHolder param1ViewHolder2, @NonNull ItemHolderInfo param1ItemHolderInfo1, @NonNull ItemHolderInfo param1ItemHolderInfo2);
    
    public abstract boolean animateDisappearance(@NonNull RecyclerView.ViewHolder param1ViewHolder, @NonNull ItemHolderInfo param1ItemHolderInfo1, @Nullable ItemHolderInfo param1ItemHolderInfo2);
    
    public abstract boolean animatePersistence(@NonNull RecyclerView.ViewHolder param1ViewHolder, @NonNull ItemHolderInfo param1ItemHolderInfo1, @NonNull ItemHolderInfo param1ItemHolderInfo2);
    
    public boolean canReuseUpdatedViewHolder(@NonNull RecyclerView.ViewHolder param1ViewHolder) {
      return true;
    }
    
    public boolean canReuseUpdatedViewHolder(@NonNull RecyclerView.ViewHolder param1ViewHolder, @NonNull List<Object> param1List) {
      return canReuseUpdatedViewHolder(param1ViewHolder);
    }
    
    public final void dispatchAnimationFinished(RecyclerView.ViewHolder param1ViewHolder) {
      onAnimationFinished(param1ViewHolder);
      ItemAnimatorListener itemAnimatorListener = this.mListener;
      if (itemAnimatorListener != null)
        itemAnimatorListener.onAnimationFinished(param1ViewHolder); 
    }
    
    public final void dispatchAnimationStarted(RecyclerView.ViewHolder param1ViewHolder) {
      onAnimationStarted(param1ViewHolder);
    }
    
    public final void dispatchAnimationsFinished() {
      int i = this.mFinishedListeners.size();
      for (byte b = 0; b < i; b++)
        ((ItemAnimatorFinishedListener)this.mFinishedListeners.get(b)).onAnimationsFinished(); 
      this.mFinishedListeners.clear();
    }
    
    public abstract void endAnimation(RecyclerView.ViewHolder param1ViewHolder);
    
    public abstract void endAnimations();
    
    public long getAddDuration() {
      return this.mAddDuration;
    }
    
    public long getChangeDuration() {
      return this.mChangeDuration;
    }
    
    public long getMoveDuration() {
      return this.mMoveDuration;
    }
    
    public long getRemoveDuration() {
      return this.mRemoveDuration;
    }
    
    public abstract boolean isRunning();
    
    public final boolean isRunning(ItemAnimatorFinishedListener param1ItemAnimatorFinishedListener) {
      boolean bool = isRunning();
      if (param1ItemAnimatorFinishedListener != null)
        if (!bool) {
          param1ItemAnimatorFinishedListener.onAnimationsFinished();
        } else {
          this.mFinishedListeners.add(param1ItemAnimatorFinishedListener);
        }  
      return bool;
    }
    
    public ItemHolderInfo obtainHolderInfo() {
      return new ItemHolderInfo();
    }
    
    public void onAnimationFinished(RecyclerView.ViewHolder param1ViewHolder) {}
    
    public void onAnimationStarted(RecyclerView.ViewHolder param1ViewHolder) {}
    
    @NonNull
    public ItemHolderInfo recordPostLayoutInformation(@NonNull RecyclerView.State param1State, @NonNull RecyclerView.ViewHolder param1ViewHolder) {
      return obtainHolderInfo().setFrom(param1ViewHolder);
    }
    
    @NonNull
    public ItemHolderInfo recordPreLayoutInformation(@NonNull RecyclerView.State param1State, @NonNull RecyclerView.ViewHolder param1ViewHolder, int param1Int, @NonNull List<Object> param1List) {
      return obtainHolderInfo().setFrom(param1ViewHolder);
    }
    
    public abstract void runPendingAnimations();
    
    public void setAddDuration(long param1Long) {
      this.mAddDuration = param1Long;
    }
    
    public void setChangeDuration(long param1Long) {
      this.mChangeDuration = param1Long;
    }
    
    void setListener(ItemAnimatorListener param1ItemAnimatorListener) {
      this.mListener = param1ItemAnimatorListener;
    }
    
    public void setMoveDuration(long param1Long) {
      this.mMoveDuration = param1Long;
    }
    
    public void setRemoveDuration(long param1Long) {
      this.mRemoveDuration = param1Long;
    }
    
    @Retention(RetentionPolicy.SOURCE)
    public static @interface AdapterChanges {}
    
    public static interface ItemAnimatorFinishedListener {
      void onAnimationsFinished();
    }
    
    static interface ItemAnimatorListener {
      void onAnimationFinished(RecyclerView.ViewHolder param2ViewHolder);
    }
    
    public static class ItemHolderInfo {
      public int bottom;
      
      public int changeFlags;
      
      public int left;
      
      public int right;
      
      public int top;
      
      public ItemHolderInfo setFrom(RecyclerView.ViewHolder param2ViewHolder) {
        return setFrom(param2ViewHolder, 0);
      }
      
      public ItemHolderInfo setFrom(RecyclerView.ViewHolder param2ViewHolder, int param2Int) {
        View view = param2ViewHolder.itemView;
        this.left = view.getLeft();
        this.top = view.getTop();
        this.right = view.getRight();
        this.bottom = view.getBottom();
        return this;
      }
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface AdapterChanges {}
  
  public static interface ItemAnimatorFinishedListener {
    void onAnimationsFinished();
  }
  
  static interface ItemAnimatorListener {
    void onAnimationFinished(RecyclerView.ViewHolder param1ViewHolder);
  }
  
  public static class ItemHolderInfo {
    public int bottom;
    
    public int changeFlags;
    
    public int left;
    
    public int right;
    
    public int top;
    
    public ItemHolderInfo setFrom(RecyclerView.ViewHolder param1ViewHolder) {
      return setFrom(param1ViewHolder, 0);
    }
    
    public ItemHolderInfo setFrom(RecyclerView.ViewHolder param1ViewHolder, int param1Int) {
      View view = param1ViewHolder.itemView;
      this.left = view.getLeft();
      this.top = view.getTop();
      this.right = view.getRight();
      this.bottom = view.getBottom();
      return this;
    }
  }
  
  private class ItemAnimatorRestoreListener implements ItemAnimator.ItemAnimatorListener {
    public void onAnimationFinished(RecyclerView.ViewHolder param1ViewHolder) {
      param1ViewHolder.setIsRecyclable(true);
      if (param1ViewHolder.mShadowedHolder != null && param1ViewHolder.mShadowingHolder == null)
        param1ViewHolder.mShadowedHolder = null; 
      param1ViewHolder.mShadowingHolder = null;
      if (!param1ViewHolder.shouldBeKeptAsChild() && !RecyclerView.this.removeAnimatingView(param1ViewHolder.itemView) && param1ViewHolder.isTmpDetached())
        RecyclerView.this.removeDetachedView(param1ViewHolder.itemView, false); 
    }
  }
  
  public static abstract class ItemDecoration {
    @Deprecated
    public void getItemOffsets(Rect param1Rect, int param1Int, RecyclerView param1RecyclerView) {
      param1Rect.set(0, 0, 0, 0);
    }
    
    public void getItemOffsets(Rect param1Rect, View param1View, RecyclerView param1RecyclerView, RecyclerView.State param1State) {
      getItemOffsets(param1Rect, ((RecyclerView.LayoutParams)param1View.getLayoutParams()).getViewLayoutPosition(), param1RecyclerView);
    }
    
    @Deprecated
    public void onDraw(Canvas param1Canvas, RecyclerView param1RecyclerView) {}
    
    public void onDraw(Canvas param1Canvas, RecyclerView param1RecyclerView, RecyclerView.State param1State) {
      onDraw(param1Canvas, param1RecyclerView);
    }
    
    @Deprecated
    public void onDrawOver(Canvas param1Canvas, RecyclerView param1RecyclerView) {}
    
    public void onDrawOver(Canvas param1Canvas, RecyclerView param1RecyclerView, RecyclerView.State param1State) {
      onDrawOver(param1Canvas, param1RecyclerView);
    }
  }
  
  public static abstract class LayoutManager {
    boolean mAutoMeasure;
    
    ChildHelper mChildHelper;
    
    private int mHeight;
    
    private int mHeightMode;
    
    ViewBoundsCheck mHorizontalBoundCheck;
    
    private final ViewBoundsCheck.Callback mHorizontalBoundCheckCallback;
    
    boolean mIsAttachedToWindow;
    
    private boolean mItemPrefetchEnabled;
    
    private boolean mMeasurementCacheEnabled;
    
    int mPrefetchMaxCountObserved;
    
    boolean mPrefetchMaxObservedInInitialPrefetch;
    
    RecyclerView mRecyclerView;
    
    boolean mRequestedSimpleAnimations;
    
    @Nullable
    RecyclerView.SmoothScroller mSmoothScroller;
    
    ViewBoundsCheck mVerticalBoundCheck;
    
    private final ViewBoundsCheck.Callback mVerticalBoundCheckCallback;
    
    private int mWidth;
    
    private int mWidthMode;
    
    public LayoutManager() {
      ViewBoundsCheck.Callback callback1 = new ViewBoundsCheck.Callback() {
          public View getChildAt(int param2Int) {
            return RecyclerView.LayoutManager.this.getChildAt(param2Int);
          }
          
          public int getChildCount() {
            return RecyclerView.LayoutManager.this.getChildCount();
          }
          
          public int getChildEnd(View param2View) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)param2View.getLayoutParams();
            return RecyclerView.LayoutManager.this.getDecoratedRight(param2View) + layoutParams.rightMargin;
          }
          
          public int getChildStart(View param2View) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)param2View.getLayoutParams();
            return RecyclerView.LayoutManager.this.getDecoratedLeft(param2View) - layoutParams.leftMargin;
          }
          
          public View getParent() {
            return (View)RecyclerView.LayoutManager.this.mRecyclerView;
          }
          
          public int getParentEnd() {
            return RecyclerView.LayoutManager.this.getWidth() - RecyclerView.LayoutManager.this.getPaddingRight();
          }
          
          public int getParentStart() {
            return RecyclerView.LayoutManager.this.getPaddingLeft();
          }
        };
      this.mHorizontalBoundCheckCallback = callback1;
      ViewBoundsCheck.Callback callback2 = new ViewBoundsCheck.Callback() {
          public View getChildAt(int param2Int) {
            return RecyclerView.LayoutManager.this.getChildAt(param2Int);
          }
          
          public int getChildCount() {
            return RecyclerView.LayoutManager.this.getChildCount();
          }
          
          public int getChildEnd(View param2View) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)param2View.getLayoutParams();
            return RecyclerView.LayoutManager.this.getDecoratedBottom(param2View) + layoutParams.bottomMargin;
          }
          
          public int getChildStart(View param2View) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)param2View.getLayoutParams();
            return RecyclerView.LayoutManager.this.getDecoratedTop(param2View) - layoutParams.topMargin;
          }
          
          public View getParent() {
            return (View)RecyclerView.LayoutManager.this.mRecyclerView;
          }
          
          public int getParentEnd() {
            return RecyclerView.LayoutManager.this.getHeight() - RecyclerView.LayoutManager.this.getPaddingBottom();
          }
          
          public int getParentStart() {
            return RecyclerView.LayoutManager.this.getPaddingTop();
          }
        };
      this.mVerticalBoundCheckCallback = callback2;
      this.mHorizontalBoundCheck = new ViewBoundsCheck(callback1);
      this.mVerticalBoundCheck = new ViewBoundsCheck(callback2);
      this.mRequestedSimpleAnimations = false;
      this.mIsAttachedToWindow = false;
      this.mAutoMeasure = false;
      this.mMeasurementCacheEnabled = true;
      this.mItemPrefetchEnabled = true;
    }
    
    private void addViewInt(View param1View, int param1Int, boolean param1Boolean) {
      StringBuilder stringBuilder;
      RecyclerView.ViewHolder viewHolder = RecyclerView.getChildViewHolderInt(param1View);
      if (param1Boolean || viewHolder.isRemoved()) {
        this.mRecyclerView.mViewInfoStore.addToDisappearedInLayout(viewHolder);
      } else {
        this.mRecyclerView.mViewInfoStore.removeFromDisappearedInLayout(viewHolder);
      } 
      RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)param1View.getLayoutParams();
      if (viewHolder.wasReturnedFromScrap() || viewHolder.isScrap()) {
        if (viewHolder.isScrap()) {
          viewHolder.unScrap();
        } else {
          viewHolder.clearReturnedFromScrapFlag();
        } 
        this.mChildHelper.attachViewToParent(param1View, param1Int, param1View.getLayoutParams(), false);
      } else if (param1View.getParent() == this.mRecyclerView) {
        int i = this.mChildHelper.indexOfChild(param1View);
        int j = param1Int;
        if (param1Int == -1)
          j = this.mChildHelper.getChildCount(); 
        if (i != -1) {
          if (i != j)
            this.mRecyclerView.mLayout.moveView(i, j); 
        } else {
          stringBuilder = new StringBuilder();
          stringBuilder.append("Added View has RecyclerView as parent but view is not a real child. Unfiltered index:");
          stringBuilder.append(this.mRecyclerView.indexOfChild(param1View));
          stringBuilder.append(this.mRecyclerView.exceptionLabel());
          throw new IllegalStateException(stringBuilder.toString());
        } 
      } else {
        this.mChildHelper.addView(param1View, param1Int, false);
        ((RecyclerView.LayoutParams)stringBuilder).mInsetsDirty = true;
        RecyclerView.SmoothScroller smoothScroller = this.mSmoothScroller;
        if (smoothScroller != null && smoothScroller.isRunning())
          this.mSmoothScroller.onChildAttachedToWindow(param1View); 
      } 
      if (((RecyclerView.LayoutParams)stringBuilder).mPendingInvalidate) {
        viewHolder.itemView.invalidate();
        ((RecyclerView.LayoutParams)stringBuilder).mPendingInvalidate = false;
      } 
    }
    
    public static int chooseSize(int param1Int1, int param1Int2, int param1Int3) {
      int i = View.MeasureSpec.getMode(param1Int1);
      param1Int1 = View.MeasureSpec.getSize(param1Int1);
      if (i != Integer.MIN_VALUE) {
        if (i != 1073741824)
          param1Int1 = Math.max(param1Int2, param1Int3); 
        return param1Int1;
      } 
      return Math.min(param1Int1, Math.max(param1Int2, param1Int3));
    }
    
    private void detachViewInternal(int param1Int, View param1View) {
      this.mChildHelper.detachViewFromParent(param1Int);
    }
    
    public static int getChildMeasureSpec(int param1Int1, int param1Int2, int param1Int3, int param1Int4, boolean param1Boolean) {
      // Byte code:
      //   0: iconst_0
      //   1: iload_0
      //   2: iload_2
      //   3: isub
      //   4: invokestatic max : (II)I
      //   7: istore_2
      //   8: iload #4
      //   10: ifeq -> 48
      //   13: iload_3
      //   14: iflt -> 20
      //   17: goto -> 52
      //   20: iload_3
      //   21: iconst_m1
      //   22: if_icmpne -> 102
      //   25: iload_1
      //   26: istore_0
      //   27: iload_1
      //   28: ldc -2147483648
      //   30: if_icmpeq -> 65
      //   33: iload_1
      //   34: ifeq -> 102
      //   37: iload_1
      //   38: istore_0
      //   39: iload_1
      //   40: ldc 1073741824
      //   42: if_icmpeq -> 65
      //   45: goto -> 102
      //   48: iload_3
      //   49: iflt -> 58
      //   52: ldc 1073741824
      //   54: istore_0
      //   55: goto -> 106
      //   58: iload_3
      //   59: iconst_m1
      //   60: if_icmpne -> 70
      //   63: iload_1
      //   64: istore_0
      //   65: iload_2
      //   66: istore_3
      //   67: goto -> 106
      //   70: iload_3
      //   71: bipush #-2
      //   73: if_icmpne -> 102
      //   76: iload_1
      //   77: ldc -2147483648
      //   79: if_icmpeq -> 96
      //   82: iload_1
      //   83: ldc 1073741824
      //   85: if_icmpne -> 91
      //   88: goto -> 96
      //   91: iconst_0
      //   92: istore_0
      //   93: goto -> 65
      //   96: ldc -2147483648
      //   98: istore_0
      //   99: goto -> 65
      //   102: iconst_0
      //   103: istore_0
      //   104: iconst_0
      //   105: istore_3
      //   106: iload_3
      //   107: iload_0
      //   108: invokestatic makeMeasureSpec : (II)I
      //   111: ireturn
    }
    
    @Deprecated
    public static int getChildMeasureSpec(int param1Int1, int param1Int2, int param1Int3, boolean param1Boolean) {
      // Byte code:
      //   0: iconst_0
      //   1: istore #4
      //   3: iconst_0
      //   4: iload_0
      //   5: iload_1
      //   6: isub
      //   7: invokestatic max : (II)I
      //   10: istore_1
      //   11: iload_3
      //   12: ifeq -> 30
      //   15: iload_2
      //   16: iflt -> 22
      //   19: goto -> 34
      //   22: iconst_0
      //   23: istore_2
      //   24: iload #4
      //   26: istore_0
      //   27: goto -> 65
      //   30: iload_2
      //   31: iflt -> 40
      //   34: ldc 1073741824
      //   36: istore_0
      //   37: goto -> 65
      //   40: iload_2
      //   41: iconst_m1
      //   42: if_icmpne -> 53
      //   45: ldc 1073741824
      //   47: istore_0
      //   48: iload_1
      //   49: istore_2
      //   50: goto -> 65
      //   53: iload_2
      //   54: bipush #-2
      //   56: if_icmpne -> 22
      //   59: ldc -2147483648
      //   61: istore_0
      //   62: goto -> 48
      //   65: iload_2
      //   66: iload_0
      //   67: invokestatic makeMeasureSpec : (II)I
      //   70: ireturn
    }
    
    private int[] getChildRectangleOnScreenScrollAmount(RecyclerView param1RecyclerView, View param1View, Rect param1Rect, boolean param1Boolean) {
      int i = getPaddingLeft();
      int j = getPaddingTop();
      int k = getWidth();
      int m = getPaddingRight();
      int n = getHeight();
      int i1 = getPaddingBottom();
      int i2 = param1View.getLeft() + param1Rect.left - param1View.getScrollX();
      int i3 = param1View.getTop() + param1Rect.top - param1View.getScrollY();
      int i4 = param1Rect.width();
      int i5 = param1Rect.height();
      int i6 = i2 - i;
      i = Math.min(0, i6);
      int i7 = i3 - j;
      j = Math.min(0, i7);
      i4 = i4 + i2 - k - m;
      i2 = Math.max(0, i4);
      i5 = Math.max(0, i5 + i3 - n - i1);
      if (getLayoutDirection() == 1) {
        if (i2 != 0) {
          i = i2;
        } else {
          i = Math.max(i, i4);
        } 
      } else if (i == 0) {
        i = Math.min(i6, i2);
      } 
      if (j == 0)
        j = Math.min(i7, i5); 
      return new int[] { i, j };
    }
    
    public static Properties getProperties(Context param1Context, AttributeSet param1AttributeSet, int param1Int1, int param1Int2) {
      Properties properties = new Properties();
      TypedArray typedArray = param1Context.obtainStyledAttributes(param1AttributeSet, R.styleable.RecyclerView, param1Int1, param1Int2);
      properties.orientation = typedArray.getInt(R.styleable.RecyclerView_android_orientation, 1);
      properties.spanCount = typedArray.getInt(R.styleable.RecyclerView_spanCount, 1);
      properties.reverseLayout = typedArray.getBoolean(R.styleable.RecyclerView_reverseLayout, false);
      properties.stackFromEnd = typedArray.getBoolean(R.styleable.RecyclerView_stackFromEnd, false);
      typedArray.recycle();
      return properties;
    }
    
    private boolean isFocusedChildVisibleAfterScrolling(RecyclerView param1RecyclerView, int param1Int1, int param1Int2) {
      View view = param1RecyclerView.getFocusedChild();
      if (view == null)
        return false; 
      int i = getPaddingLeft();
      int j = getPaddingTop();
      int k = getWidth();
      int m = getPaddingRight();
      int n = getHeight();
      int i1 = getPaddingBottom();
      Rect rect = this.mRecyclerView.mTempRect;
      getDecoratedBoundsWithMargins(view, rect);
      return !(rect.left - param1Int1 >= k - m || rect.right - param1Int1 <= i || rect.top - param1Int2 >= n - i1 || rect.bottom - param1Int2 <= j);
    }
    
    private static boolean isMeasurementUpToDate(int param1Int1, int param1Int2, int param1Int3) {
      int i = View.MeasureSpec.getMode(param1Int2);
      param1Int2 = View.MeasureSpec.getSize(param1Int2);
      boolean bool1 = false;
      boolean bool2 = false;
      if (param1Int3 > 0 && param1Int1 != param1Int3)
        return false; 
      if (i != Integer.MIN_VALUE) {
        if (i != 0) {
          if (i != 1073741824)
            return false; 
          if (param1Int2 == param1Int1)
            bool2 = true; 
          return bool2;
        } 
        return true;
      } 
      bool2 = bool1;
      if (param1Int2 >= param1Int1)
        bool2 = true; 
      return bool2;
    }
    
    private void onSmoothScrollerStopped(RecyclerView.SmoothScroller param1SmoothScroller) {
      if (this.mSmoothScroller == param1SmoothScroller)
        this.mSmoothScroller = null; 
    }
    
    private void scrapOrRecycleView(RecyclerView.Recycler param1Recycler, int param1Int, View param1View) {
      RecyclerView.ViewHolder viewHolder = RecyclerView.getChildViewHolderInt(param1View);
      if (viewHolder.shouldIgnore())
        return; 
      if (viewHolder.isInvalid() && !viewHolder.isRemoved() && !this.mRecyclerView.mAdapter.hasStableIds()) {
        removeViewAt(param1Int);
        param1Recycler.recycleViewHolderInternal(viewHolder);
      } else {
        detachViewAt(param1Int);
        param1Recycler.scrapView(param1View);
        this.mRecyclerView.mViewInfoStore.onViewDetached(viewHolder);
      } 
    }
    
    public void addDisappearingView(View param1View) {
      addDisappearingView(param1View, -1);
    }
    
    public void addDisappearingView(View param1View, int param1Int) {
      addViewInt(param1View, param1Int, true);
    }
    
    public void addView(View param1View) {
      addView(param1View, -1);
    }
    
    public void addView(View param1View, int param1Int) {
      addViewInt(param1View, param1Int, false);
    }
    
    public void assertInLayoutOrScroll(String param1String) {
      RecyclerView recyclerView = this.mRecyclerView;
      if (recyclerView != null)
        recyclerView.assertInLayoutOrScroll(param1String); 
    }
    
    public void assertNotInLayoutOrScroll(String param1String) {
      RecyclerView recyclerView = this.mRecyclerView;
      if (recyclerView != null)
        recyclerView.assertNotInLayoutOrScroll(param1String); 
    }
    
    public void attachView(View param1View) {
      attachView(param1View, -1);
    }
    
    public void attachView(View param1View, int param1Int) {
      attachView(param1View, param1Int, (RecyclerView.LayoutParams)param1View.getLayoutParams());
    }
    
    public void attachView(View param1View, int param1Int, RecyclerView.LayoutParams param1LayoutParams) {
      RecyclerView.ViewHolder viewHolder = RecyclerView.getChildViewHolderInt(param1View);
      if (viewHolder.isRemoved()) {
        this.mRecyclerView.mViewInfoStore.addToDisappearedInLayout(viewHolder);
      } else {
        this.mRecyclerView.mViewInfoStore.removeFromDisappearedInLayout(viewHolder);
      } 
      this.mChildHelper.attachViewToParent(param1View, param1Int, (ViewGroup.LayoutParams)param1LayoutParams, viewHolder.isRemoved());
    }
    
    public void calculateItemDecorationsForChild(View param1View, Rect param1Rect) {
      RecyclerView recyclerView = this.mRecyclerView;
      if (recyclerView == null) {
        param1Rect.set(0, 0, 0, 0);
        return;
      } 
      param1Rect.set(recyclerView.getItemDecorInsetsForChild(param1View));
    }
    
    public boolean canScrollHorizontally() {
      return false;
    }
    
    public boolean canScrollVertically() {
      return false;
    }
    
    public boolean checkLayoutParams(RecyclerView.LayoutParams param1LayoutParams) {
      boolean bool;
      if (param1LayoutParams != null) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public void collectAdjacentPrefetchPositions(int param1Int1, int param1Int2, RecyclerView.State param1State, LayoutPrefetchRegistry param1LayoutPrefetchRegistry) {}
    
    public void collectInitialPrefetchPositions(int param1Int, LayoutPrefetchRegistry param1LayoutPrefetchRegistry) {}
    
    public int computeHorizontalScrollExtent(RecyclerView.State param1State) {
      return 0;
    }
    
    public int computeHorizontalScrollOffset(RecyclerView.State param1State) {
      return 0;
    }
    
    public int computeHorizontalScrollRange(RecyclerView.State param1State) {
      return 0;
    }
    
    public int computeVerticalScrollExtent(RecyclerView.State param1State) {
      return 0;
    }
    
    public int computeVerticalScrollOffset(RecyclerView.State param1State) {
      return 0;
    }
    
    public int computeVerticalScrollRange(RecyclerView.State param1State) {
      return 0;
    }
    
    public void detachAndScrapAttachedViews(RecyclerView.Recycler param1Recycler) {
      for (int i = getChildCount() - 1; i >= 0; i--)
        scrapOrRecycleView(param1Recycler, i, getChildAt(i)); 
    }
    
    public void detachAndScrapView(View param1View, RecyclerView.Recycler param1Recycler) {
      scrapOrRecycleView(param1Recycler, this.mChildHelper.indexOfChild(param1View), param1View);
    }
    
    public void detachAndScrapViewAt(int param1Int, RecyclerView.Recycler param1Recycler) {
      scrapOrRecycleView(param1Recycler, param1Int, getChildAt(param1Int));
    }
    
    public void detachView(View param1View) {
      int i = this.mChildHelper.indexOfChild(param1View);
      if (i >= 0)
        detachViewInternal(i, param1View); 
    }
    
    public void detachViewAt(int param1Int) {
      detachViewInternal(param1Int, getChildAt(param1Int));
    }
    
    void dispatchAttachedToWindow(RecyclerView param1RecyclerView) {
      this.mIsAttachedToWindow = true;
      onAttachedToWindow(param1RecyclerView);
    }
    
    void dispatchDetachedFromWindow(RecyclerView param1RecyclerView, RecyclerView.Recycler param1Recycler) {
      this.mIsAttachedToWindow = false;
      onDetachedFromWindow(param1RecyclerView, param1Recycler);
    }
    
    public void endAnimation(View param1View) {
      RecyclerView.ItemAnimator itemAnimator = this.mRecyclerView.mItemAnimator;
      if (itemAnimator != null)
        itemAnimator.endAnimation(RecyclerView.getChildViewHolderInt(param1View)); 
    }
    
    @Nullable
    public View findContainingItemView(View param1View) {
      RecyclerView recyclerView = this.mRecyclerView;
      if (recyclerView == null)
        return null; 
      param1View = recyclerView.findContainingItemView(param1View);
      return (param1View == null) ? null : (this.mChildHelper.isHidden(param1View) ? null : param1View);
    }
    
    public View findViewByPosition(int param1Int) {
      int i = getChildCount();
      for (byte b = 0; b < i; b++) {
        View view = getChildAt(b);
        RecyclerView.ViewHolder viewHolder = RecyclerView.getChildViewHolderInt(view);
        if (viewHolder != null && viewHolder.getLayoutPosition() == param1Int && !viewHolder.shouldIgnore() && (this.mRecyclerView.mState.isPreLayout() || !viewHolder.isRemoved()))
          return view; 
      } 
      return null;
    }
    
    public abstract RecyclerView.LayoutParams generateDefaultLayoutParams();
    
    public RecyclerView.LayoutParams generateLayoutParams(Context param1Context, AttributeSet param1AttributeSet) {
      return new RecyclerView.LayoutParams(param1Context, param1AttributeSet);
    }
    
    public RecyclerView.LayoutParams generateLayoutParams(ViewGroup.LayoutParams param1LayoutParams) {
      return (param1LayoutParams instanceof RecyclerView.LayoutParams) ? new RecyclerView.LayoutParams((RecyclerView.LayoutParams)param1LayoutParams) : ((param1LayoutParams instanceof ViewGroup.MarginLayoutParams) ? new RecyclerView.LayoutParams((ViewGroup.MarginLayoutParams)param1LayoutParams) : new RecyclerView.LayoutParams(param1LayoutParams));
    }
    
    public int getBaseline() {
      return -1;
    }
    
    public int getBottomDecorationHeight(View param1View) {
      return ((RecyclerView.LayoutParams)param1View.getLayoutParams()).mDecorInsets.bottom;
    }
    
    public View getChildAt(int param1Int) {
      ChildHelper childHelper = this.mChildHelper;
      if (childHelper != null) {
        View view = childHelper.getChildAt(param1Int);
      } else {
        childHelper = null;
      } 
      return (View)childHelper;
    }
    
    public int getChildCount() {
      boolean bool;
      ChildHelper childHelper = this.mChildHelper;
      if (childHelper != null) {
        bool = childHelper.getChildCount();
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean getClipToPadding() {
      boolean bool;
      RecyclerView recyclerView = this.mRecyclerView;
      if (recyclerView != null && recyclerView.mClipToPadding) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public int getColumnCountForAccessibility(RecyclerView.Recycler param1Recycler, RecyclerView.State param1State) {
      RecyclerView recyclerView = this.mRecyclerView;
      byte b = 1;
      int i = b;
      if (recyclerView != null)
        if (recyclerView.mAdapter == null) {
          i = b;
        } else {
          i = b;
          if (canScrollHorizontally())
            i = this.mRecyclerView.mAdapter.getItemCount(); 
        }  
      return i;
    }
    
    public int getDecoratedBottom(View param1View) {
      return param1View.getBottom() + getBottomDecorationHeight(param1View);
    }
    
    public void getDecoratedBoundsWithMargins(View param1View, Rect param1Rect) {
      RecyclerView.getDecoratedBoundsWithMarginsInt(param1View, param1Rect);
    }
    
    public int getDecoratedLeft(View param1View) {
      return param1View.getLeft() - getLeftDecorationWidth(param1View);
    }
    
    public int getDecoratedMeasuredHeight(View param1View) {
      Rect rect = ((RecyclerView.LayoutParams)param1View.getLayoutParams()).mDecorInsets;
      return param1View.getMeasuredHeight() + rect.top + rect.bottom;
    }
    
    public int getDecoratedMeasuredWidth(View param1View) {
      Rect rect = ((RecyclerView.LayoutParams)param1View.getLayoutParams()).mDecorInsets;
      return param1View.getMeasuredWidth() + rect.left + rect.right;
    }
    
    public int getDecoratedRight(View param1View) {
      return param1View.getRight() + getRightDecorationWidth(param1View);
    }
    
    public int getDecoratedTop(View param1View) {
      return param1View.getTop() - getTopDecorationHeight(param1View);
    }
    
    public View getFocusedChild() {
      RecyclerView recyclerView = this.mRecyclerView;
      if (recyclerView == null)
        return null; 
      View view = recyclerView.getFocusedChild();
      return (view == null || this.mChildHelper.isHidden(view)) ? null : view;
    }
    
    public int getHeight() {
      return this.mHeight;
    }
    
    public int getHeightMode() {
      return this.mHeightMode;
    }
    
    public int getItemCount() {
      boolean bool;
      RecyclerView recyclerView = this.mRecyclerView;
      if (recyclerView != null) {
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
      } else {
        recyclerView = null;
      } 
      if (recyclerView != null) {
        bool = recyclerView.getItemCount();
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public int getItemViewType(View param1View) {
      return RecyclerView.getChildViewHolderInt(param1View).getItemViewType();
    }
    
    public int getLayoutDirection() {
      return ViewCompat.getLayoutDirection((View)this.mRecyclerView);
    }
    
    public int getLeftDecorationWidth(View param1View) {
      return ((RecyclerView.LayoutParams)param1View.getLayoutParams()).mDecorInsets.left;
    }
    
    public int getMinimumHeight() {
      return ViewCompat.getMinimumHeight((View)this.mRecyclerView);
    }
    
    public int getMinimumWidth() {
      return ViewCompat.getMinimumWidth((View)this.mRecyclerView);
    }
    
    public int getPaddingBottom() {
      boolean bool;
      RecyclerView recyclerView = this.mRecyclerView;
      if (recyclerView != null) {
        bool = recyclerView.getPaddingBottom();
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public int getPaddingEnd() {
      boolean bool;
      RecyclerView recyclerView = this.mRecyclerView;
      if (recyclerView != null) {
        bool = ViewCompat.getPaddingEnd((View)recyclerView);
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public int getPaddingLeft() {
      boolean bool;
      RecyclerView recyclerView = this.mRecyclerView;
      if (recyclerView != null) {
        bool = recyclerView.getPaddingLeft();
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public int getPaddingRight() {
      boolean bool;
      RecyclerView recyclerView = this.mRecyclerView;
      if (recyclerView != null) {
        bool = recyclerView.getPaddingRight();
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public int getPaddingStart() {
      boolean bool;
      RecyclerView recyclerView = this.mRecyclerView;
      if (recyclerView != null) {
        bool = ViewCompat.getPaddingStart((View)recyclerView);
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public int getPaddingTop() {
      boolean bool;
      RecyclerView recyclerView = this.mRecyclerView;
      if (recyclerView != null) {
        bool = recyclerView.getPaddingTop();
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public int getPosition(View param1View) {
      return ((RecyclerView.LayoutParams)param1View.getLayoutParams()).getViewLayoutPosition();
    }
    
    public int getRightDecorationWidth(View param1View) {
      return ((RecyclerView.LayoutParams)param1View.getLayoutParams()).mDecorInsets.right;
    }
    
    public int getRowCountForAccessibility(RecyclerView.Recycler param1Recycler, RecyclerView.State param1State) {
      RecyclerView recyclerView = this.mRecyclerView;
      byte b = 1;
      int i = b;
      if (recyclerView != null)
        if (recyclerView.mAdapter == null) {
          i = b;
        } else {
          i = b;
          if (canScrollVertically())
            i = this.mRecyclerView.mAdapter.getItemCount(); 
        }  
      return i;
    }
    
    public int getSelectionModeForAccessibility(RecyclerView.Recycler param1Recycler, RecyclerView.State param1State) {
      return 0;
    }
    
    public int getTopDecorationHeight(View param1View) {
      return ((RecyclerView.LayoutParams)param1View.getLayoutParams()).mDecorInsets.top;
    }
    
    public void getTransformedBoundingBox(View param1View, boolean param1Boolean, Rect param1Rect) {
      if (param1Boolean) {
        Rect rect = ((RecyclerView.LayoutParams)param1View.getLayoutParams()).mDecorInsets;
        param1Rect.set(-rect.left, -rect.top, param1View.getWidth() + rect.right, param1View.getHeight() + rect.bottom);
      } else {
        param1Rect.set(0, 0, param1View.getWidth(), param1View.getHeight());
      } 
      if (this.mRecyclerView != null) {
        Matrix matrix = param1View.getMatrix();
        if (matrix != null && !matrix.isIdentity()) {
          RectF rectF = this.mRecyclerView.mTempRectF;
          rectF.set(param1Rect);
          matrix.mapRect(rectF);
          param1Rect.set((int)Math.floor(rectF.left), (int)Math.floor(rectF.top), (int)Math.ceil(rectF.right), (int)Math.ceil(rectF.bottom));
        } 
      } 
      param1Rect.offset(param1View.getLeft(), param1View.getTop());
    }
    
    public int getWidth() {
      return this.mWidth;
    }
    
    public int getWidthMode() {
      return this.mWidthMode;
    }
    
    boolean hasFlexibleChildInBothOrientations() {
      int i = getChildCount();
      for (byte b = 0; b < i; b++) {
        ViewGroup.LayoutParams layoutParams = getChildAt(b).getLayoutParams();
        if (layoutParams.width < 0 && layoutParams.height < 0)
          return true; 
      } 
      return false;
    }
    
    public boolean hasFocus() {
      boolean bool;
      RecyclerView recyclerView = this.mRecyclerView;
      if (recyclerView != null && recyclerView.hasFocus()) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public void ignoreView(View param1View) {
      ViewParent viewParent = param1View.getParent();
      RecyclerView recyclerView = this.mRecyclerView;
      if (viewParent == recyclerView && recyclerView.indexOfChild(param1View) != -1) {
        RecyclerView.ViewHolder viewHolder = RecyclerView.getChildViewHolderInt(param1View);
        viewHolder.addFlags(128);
        this.mRecyclerView.mViewInfoStore.removeViewHolder(viewHolder);
        return;
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("View should be fully attached to be ignored");
      stringBuilder.append(this.mRecyclerView.exceptionLabel());
      throw new IllegalArgumentException(stringBuilder.toString());
    }
    
    public boolean isAttachedToWindow() {
      return this.mIsAttachedToWindow;
    }
    
    public boolean isAutoMeasureEnabled() {
      return this.mAutoMeasure;
    }
    
    public boolean isFocused() {
      boolean bool;
      RecyclerView recyclerView = this.mRecyclerView;
      if (recyclerView != null && recyclerView.isFocused()) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public final boolean isItemPrefetchEnabled() {
      return this.mItemPrefetchEnabled;
    }
    
    public boolean isLayoutHierarchical(RecyclerView.Recycler param1Recycler, RecyclerView.State param1State) {
      return false;
    }
    
    public boolean isMeasurementCacheEnabled() {
      return this.mMeasurementCacheEnabled;
    }
    
    public boolean isSmoothScrolling() {
      boolean bool;
      RecyclerView.SmoothScroller smoothScroller = this.mSmoothScroller;
      if (smoothScroller != null && smoothScroller.isRunning()) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean isViewPartiallyVisible(@NonNull View param1View, boolean param1Boolean1, boolean param1Boolean2) {
      if (this.mHorizontalBoundCheck.isViewWithinBoundFlags(param1View, 24579) && this.mVerticalBoundCheck.isViewWithinBoundFlags(param1View, 24579)) {
        param1Boolean2 = true;
      } else {
        param1Boolean2 = false;
      } 
      return param1Boolean1 ? param1Boolean2 : (param1Boolean2 ^ true);
    }
    
    public void layoutDecorated(View param1View, int param1Int1, int param1Int2, int param1Int3, int param1Int4) {
      Rect rect = ((RecyclerView.LayoutParams)param1View.getLayoutParams()).mDecorInsets;
      param1View.layout(param1Int1 + rect.left, param1Int2 + rect.top, param1Int3 - rect.right, param1Int4 - rect.bottom);
    }
    
    public void layoutDecoratedWithMargins(View param1View, int param1Int1, int param1Int2, int param1Int3, int param1Int4) {
      RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)param1View.getLayoutParams();
      Rect rect = layoutParams.mDecorInsets;
      param1View.layout(param1Int1 + rect.left + layoutParams.leftMargin, param1Int2 + rect.top + layoutParams.topMargin, param1Int3 - rect.right - layoutParams.rightMargin, param1Int4 - rect.bottom - layoutParams.bottomMargin);
    }
    
    public void measureChild(View param1View, int param1Int1, int param1Int2) {
      RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)param1View.getLayoutParams();
      Rect rect = this.mRecyclerView.getItemDecorInsetsForChild(param1View);
      int i = rect.left;
      int j = rect.right;
      int k = rect.top;
      int m = rect.bottom;
      param1Int1 = getChildMeasureSpec(getWidth(), getWidthMode(), getPaddingLeft() + getPaddingRight() + param1Int1 + i + j, layoutParams.width, canScrollHorizontally());
      param1Int2 = getChildMeasureSpec(getHeight(), getHeightMode(), getPaddingTop() + getPaddingBottom() + param1Int2 + k + m, layoutParams.height, canScrollVertically());
      if (shouldMeasureChild(param1View, param1Int1, param1Int2, layoutParams))
        param1View.measure(param1Int1, param1Int2); 
    }
    
    public void measureChildWithMargins(View param1View, int param1Int1, int param1Int2) {
      RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)param1View.getLayoutParams();
      Rect rect = this.mRecyclerView.getItemDecorInsetsForChild(param1View);
      int i = rect.left;
      int j = rect.right;
      int k = rect.top;
      int m = rect.bottom;
      param1Int1 = getChildMeasureSpec(getWidth(), getWidthMode(), getPaddingLeft() + getPaddingRight() + layoutParams.leftMargin + layoutParams.rightMargin + param1Int1 + i + j, layoutParams.width, canScrollHorizontally());
      param1Int2 = getChildMeasureSpec(getHeight(), getHeightMode(), getPaddingTop() + getPaddingBottom() + layoutParams.topMargin + layoutParams.bottomMargin + param1Int2 + k + m, layoutParams.height, canScrollVertically());
      if (shouldMeasureChild(param1View, param1Int1, param1Int2, layoutParams))
        param1View.measure(param1Int1, param1Int2); 
    }
    
    public void moveView(int param1Int1, int param1Int2) {
      View view = getChildAt(param1Int1);
      if (view != null) {
        detachViewAt(param1Int1);
        attachView(view, param1Int2);
        return;
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Cannot move a child from non-existing index:");
      stringBuilder.append(param1Int1);
      stringBuilder.append(this.mRecyclerView.toString());
      throw new IllegalArgumentException(stringBuilder.toString());
    }
    
    public void offsetChildrenHorizontal(int param1Int) {
      RecyclerView recyclerView = this.mRecyclerView;
      if (recyclerView != null)
        recyclerView.offsetChildrenHorizontal(param1Int); 
    }
    
    public void offsetChildrenVertical(int param1Int) {
      RecyclerView recyclerView = this.mRecyclerView;
      if (recyclerView != null)
        recyclerView.offsetChildrenVertical(param1Int); 
    }
    
    public void onAdapterChanged(RecyclerView.Adapter param1Adapter1, RecyclerView.Adapter param1Adapter2) {}
    
    public boolean onAddFocusables(RecyclerView param1RecyclerView, ArrayList<View> param1ArrayList, int param1Int1, int param1Int2) {
      return false;
    }
    
    @CallSuper
    public void onAttachedToWindow(RecyclerView param1RecyclerView) {}
    
    @Deprecated
    public void onDetachedFromWindow(RecyclerView param1RecyclerView) {}
    
    @CallSuper
    public void onDetachedFromWindow(RecyclerView param1RecyclerView, RecyclerView.Recycler param1Recycler) {
      onDetachedFromWindow(param1RecyclerView);
    }
    
    @Nullable
    public View onFocusSearchFailed(View param1View, int param1Int, RecyclerView.Recycler param1Recycler, RecyclerView.State param1State) {
      return null;
    }
    
    public void onInitializeAccessibilityEvent(RecyclerView.Recycler param1Recycler, RecyclerView.State param1State, AccessibilityEvent param1AccessibilityEvent) {
      RecyclerView recyclerView = this.mRecyclerView;
      if (recyclerView != null && param1AccessibilityEvent != null) {
        boolean bool1 = true;
        boolean bool2 = bool1;
        if (!recyclerView.canScrollVertically(1)) {
          bool2 = bool1;
          if (!this.mRecyclerView.canScrollVertically(-1)) {
            bool2 = bool1;
            if (!this.mRecyclerView.canScrollHorizontally(-1))
              if (this.mRecyclerView.canScrollHorizontally(1)) {
                bool2 = bool1;
              } else {
                bool2 = false;
              }  
          } 
        } 
        param1AccessibilityEvent.setScrollable(bool2);
        RecyclerView.Adapter adapter = this.mRecyclerView.mAdapter;
        if (adapter != null)
          param1AccessibilityEvent.setItemCount(adapter.getItemCount()); 
      } 
    }
    
    public void onInitializeAccessibilityEvent(AccessibilityEvent param1AccessibilityEvent) {
      RecyclerView recyclerView = this.mRecyclerView;
      onInitializeAccessibilityEvent(recyclerView.mRecycler, recyclerView.mState, param1AccessibilityEvent);
    }
    
    void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfoCompat param1AccessibilityNodeInfoCompat) {
      RecyclerView recyclerView = this.mRecyclerView;
      onInitializeAccessibilityNodeInfo(recyclerView.mRecycler, recyclerView.mState, param1AccessibilityNodeInfoCompat);
    }
    
    public void onInitializeAccessibilityNodeInfo(RecyclerView.Recycler param1Recycler, RecyclerView.State param1State, AccessibilityNodeInfoCompat param1AccessibilityNodeInfoCompat) {
      if (this.mRecyclerView.canScrollVertically(-1) || this.mRecyclerView.canScrollHorizontally(-1)) {
        param1AccessibilityNodeInfoCompat.addAction(8192);
        param1AccessibilityNodeInfoCompat.setScrollable(true);
      } 
      if (this.mRecyclerView.canScrollVertically(1) || this.mRecyclerView.canScrollHorizontally(1)) {
        param1AccessibilityNodeInfoCompat.addAction(4096);
        param1AccessibilityNodeInfoCompat.setScrollable(true);
      } 
      param1AccessibilityNodeInfoCompat.setCollectionInfo(AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(getRowCountForAccessibility(param1Recycler, param1State), getColumnCountForAccessibility(param1Recycler, param1State), isLayoutHierarchical(param1Recycler, param1State), getSelectionModeForAccessibility(param1Recycler, param1State)));
    }
    
    public void onInitializeAccessibilityNodeInfoForItem(RecyclerView.Recycler param1Recycler, RecyclerView.State param1State, View param1View, AccessibilityNodeInfoCompat param1AccessibilityNodeInfoCompat) {
      boolean bool1;
      boolean bool2;
      if (canScrollVertically()) {
        bool1 = getPosition(param1View);
      } else {
        bool1 = false;
      } 
      if (canScrollHorizontally()) {
        bool2 = getPosition(param1View);
      } else {
        bool2 = false;
      } 
      param1AccessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(bool1, 1, bool2, 1, false, false));
    }
    
    void onInitializeAccessibilityNodeInfoForItem(View param1View, AccessibilityNodeInfoCompat param1AccessibilityNodeInfoCompat) {
      RecyclerView.ViewHolder viewHolder = RecyclerView.getChildViewHolderInt(param1View);
      if (viewHolder != null && !viewHolder.isRemoved() && !this.mChildHelper.isHidden(viewHolder.itemView)) {
        RecyclerView recyclerView = this.mRecyclerView;
        onInitializeAccessibilityNodeInfoForItem(recyclerView.mRecycler, recyclerView.mState, param1View, param1AccessibilityNodeInfoCompat);
      } 
    }
    
    public View onInterceptFocusSearch(View param1View, int param1Int) {
      return null;
    }
    
    public void onItemsAdded(RecyclerView param1RecyclerView, int param1Int1, int param1Int2) {}
    
    public void onItemsChanged(RecyclerView param1RecyclerView) {}
    
    public void onItemsMoved(RecyclerView param1RecyclerView, int param1Int1, int param1Int2, int param1Int3) {}
    
    public void onItemsRemoved(RecyclerView param1RecyclerView, int param1Int1, int param1Int2) {}
    
    public void onItemsUpdated(RecyclerView param1RecyclerView, int param1Int1, int param1Int2) {}
    
    public void onItemsUpdated(RecyclerView param1RecyclerView, int param1Int1, int param1Int2, Object param1Object) {
      onItemsUpdated(param1RecyclerView, param1Int1, param1Int2);
    }
    
    public void onLayoutChildren(RecyclerView.Recycler param1Recycler, RecyclerView.State param1State) {
      Log.e("RecyclerView", "You must override onLayoutChildren(Recycler recycler, State state) ");
    }
    
    public void onLayoutCompleted(RecyclerView.State param1State) {}
    
    public void onMeasure(RecyclerView.Recycler param1Recycler, RecyclerView.State param1State, int param1Int1, int param1Int2) {
      this.mRecyclerView.defaultOnMeasure(param1Int1, param1Int2);
    }
    
    public boolean onRequestChildFocus(RecyclerView param1RecyclerView, RecyclerView.State param1State, View param1View1, View param1View2) {
      return onRequestChildFocus(param1RecyclerView, param1View1, param1View2);
    }
    
    @Deprecated
    public boolean onRequestChildFocus(RecyclerView param1RecyclerView, View param1View1, View param1View2) {
      return (isSmoothScrolling() || param1RecyclerView.isComputingLayout());
    }
    
    public void onRestoreInstanceState(Parcelable param1Parcelable) {}
    
    public Parcelable onSaveInstanceState() {
      return null;
    }
    
    public void onScrollStateChanged(int param1Int) {}
    
    boolean performAccessibilityAction(int param1Int, Bundle param1Bundle) {
      RecyclerView recyclerView = this.mRecyclerView;
      return performAccessibilityAction(recyclerView.mRecycler, recyclerView.mState, param1Int, param1Bundle);
    }
    
    public boolean performAccessibilityAction(RecyclerView.Recycler param1Recycler, RecyclerView.State param1State, int param1Int, Bundle param1Bundle) {
      // Byte code:
      //   0: aload_0
      //   1: getfield mRecyclerView : Landroid/support/v7/widget/RecyclerView;
      //   4: astore_1
      //   5: aload_1
      //   6: ifnonnull -> 11
      //   9: iconst_0
      //   10: ireturn
      //   11: iload_3
      //   12: sipush #4096
      //   15: if_icmpeq -> 104
      //   18: iload_3
      //   19: sipush #8192
      //   22: if_icmpeq -> 41
      //   25: iconst_0
      //   26: istore #5
      //   28: iconst_0
      //   29: istore #6
      //   31: iload #5
      //   33: istore_3
      //   34: iload #6
      //   36: istore #5
      //   38: goto -> 162
      //   41: aload_1
      //   42: iconst_m1
      //   43: invokevirtual canScrollVertically : (I)Z
      //   46: ifeq -> 68
      //   49: aload_0
      //   50: invokevirtual getHeight : ()I
      //   53: aload_0
      //   54: invokevirtual getPaddingTop : ()I
      //   57: isub
      //   58: aload_0
      //   59: invokevirtual getPaddingBottom : ()I
      //   62: isub
      //   63: ineg
      //   64: istore_3
      //   65: goto -> 70
      //   68: iconst_0
      //   69: istore_3
      //   70: iload_3
      //   71: istore #5
      //   73: aload_0
      //   74: getfield mRecyclerView : Landroid/support/v7/widget/RecyclerView;
      //   77: iconst_m1
      //   78: invokevirtual canScrollHorizontally : (I)Z
      //   81: ifeq -> 28
      //   84: aload_0
      //   85: invokevirtual getWidth : ()I
      //   88: aload_0
      //   89: invokevirtual getPaddingLeft : ()I
      //   92: isub
      //   93: aload_0
      //   94: invokevirtual getPaddingRight : ()I
      //   97: isub
      //   98: ineg
      //   99: istore #5
      //   101: goto -> 162
      //   104: aload_1
      //   105: iconst_1
      //   106: invokevirtual canScrollVertically : (I)Z
      //   109: ifeq -> 130
      //   112: aload_0
      //   113: invokevirtual getHeight : ()I
      //   116: aload_0
      //   117: invokevirtual getPaddingTop : ()I
      //   120: isub
      //   121: aload_0
      //   122: invokevirtual getPaddingBottom : ()I
      //   125: isub
      //   126: istore_3
      //   127: goto -> 132
      //   130: iconst_0
      //   131: istore_3
      //   132: iload_3
      //   133: istore #5
      //   135: aload_0
      //   136: getfield mRecyclerView : Landroid/support/v7/widget/RecyclerView;
      //   139: iconst_1
      //   140: invokevirtual canScrollHorizontally : (I)Z
      //   143: ifeq -> 28
      //   146: aload_0
      //   147: invokevirtual getWidth : ()I
      //   150: aload_0
      //   151: invokevirtual getPaddingLeft : ()I
      //   154: isub
      //   155: aload_0
      //   156: invokevirtual getPaddingRight : ()I
      //   159: isub
      //   160: istore #5
      //   162: iload_3
      //   163: ifne -> 173
      //   166: iload #5
      //   168: ifne -> 173
      //   171: iconst_0
      //   172: ireturn
      //   173: aload_0
      //   174: getfield mRecyclerView : Landroid/support/v7/widget/RecyclerView;
      //   177: iload #5
      //   179: iload_3
      //   180: invokevirtual scrollBy : (II)V
      //   183: iconst_1
      //   184: ireturn
    }
    
    public boolean performAccessibilityActionForItem(RecyclerView.Recycler param1Recycler, RecyclerView.State param1State, View param1View, int param1Int, Bundle param1Bundle) {
      return false;
    }
    
    boolean performAccessibilityActionForItem(View param1View, int param1Int, Bundle param1Bundle) {
      RecyclerView recyclerView = this.mRecyclerView;
      return performAccessibilityActionForItem(recyclerView.mRecycler, recyclerView.mState, param1View, param1Int, param1Bundle);
    }
    
    public void postOnAnimation(Runnable param1Runnable) {
      RecyclerView recyclerView = this.mRecyclerView;
      if (recyclerView != null)
        ViewCompat.postOnAnimation((View)recyclerView, param1Runnable); 
    }
    
    public void removeAllViews() {
      for (int i = getChildCount() - 1; i >= 0; i--)
        this.mChildHelper.removeViewAt(i); 
    }
    
    public void removeAndRecycleAllViews(RecyclerView.Recycler param1Recycler) {
      for (int i = getChildCount() - 1; i >= 0; i--) {
        if (!RecyclerView.getChildViewHolderInt(getChildAt(i)).shouldIgnore())
          removeAndRecycleViewAt(i, param1Recycler); 
      } 
    }
    
    void removeAndRecycleScrapInt(RecyclerView.Recycler param1Recycler) {
      int i = param1Recycler.getScrapCount();
      for (int j = i - 1; j >= 0; j--) {
        View view = param1Recycler.getScrapViewAt(j);
        RecyclerView.ViewHolder viewHolder = RecyclerView.getChildViewHolderInt(view);
        if (!viewHolder.shouldIgnore()) {
          viewHolder.setIsRecyclable(false);
          if (viewHolder.isTmpDetached())
            this.mRecyclerView.removeDetachedView(view, false); 
          RecyclerView.ItemAnimator itemAnimator = this.mRecyclerView.mItemAnimator;
          if (itemAnimator != null)
            itemAnimator.endAnimation(viewHolder); 
          viewHolder.setIsRecyclable(true);
          param1Recycler.quickRecycleScrapView(view);
        } 
      } 
      param1Recycler.clearScrap();
      if (i > 0)
        this.mRecyclerView.invalidate(); 
    }
    
    public void removeAndRecycleView(View param1View, RecyclerView.Recycler param1Recycler) {
      removeView(param1View);
      param1Recycler.recycleView(param1View);
    }
    
    public void removeAndRecycleViewAt(int param1Int, RecyclerView.Recycler param1Recycler) {
      View view = getChildAt(param1Int);
      removeViewAt(param1Int);
      param1Recycler.recycleView(view);
    }
    
    public boolean removeCallbacks(Runnable param1Runnable) {
      RecyclerView recyclerView = this.mRecyclerView;
      return (recyclerView != null) ? recyclerView.removeCallbacks(param1Runnable) : false;
    }
    
    public void removeDetachedView(View param1View) {
      this.mRecyclerView.removeDetachedView(param1View, false);
    }
    
    public void removeView(View param1View) {
      this.mChildHelper.removeView(param1View);
    }
    
    public void removeViewAt(int param1Int) {
      if (getChildAt(param1Int) != null)
        this.mChildHelper.removeViewAt(param1Int); 
    }
    
    public boolean requestChildRectangleOnScreen(RecyclerView param1RecyclerView, View param1View, Rect param1Rect, boolean param1Boolean) {
      return requestChildRectangleOnScreen(param1RecyclerView, param1View, param1Rect, param1Boolean, false);
    }
    
    public boolean requestChildRectangleOnScreen(RecyclerView param1RecyclerView, View param1View, Rect param1Rect, boolean param1Boolean1, boolean param1Boolean2) {
      int[] arrayOfInt = getChildRectangleOnScreenScrollAmount(param1RecyclerView, param1View, param1Rect, param1Boolean1);
      int i = arrayOfInt[0];
      int j = arrayOfInt[1];
      if ((!param1Boolean2 || isFocusedChildVisibleAfterScrolling(param1RecyclerView, i, j)) && (i != 0 || j != 0)) {
        if (param1Boolean1) {
          param1RecyclerView.scrollBy(i, j);
        } else {
          param1RecyclerView.smoothScrollBy(i, j);
        } 
        return true;
      } 
      return false;
    }
    
    public void requestLayout() {
      RecyclerView recyclerView = this.mRecyclerView;
      if (recyclerView != null)
        recyclerView.requestLayout(); 
    }
    
    public void requestSimpleAnimationsInNextLayout() {
      this.mRequestedSimpleAnimations = true;
    }
    
    public int scrollHorizontallyBy(int param1Int, RecyclerView.Recycler param1Recycler, RecyclerView.State param1State) {
      return 0;
    }
    
    public void scrollToPosition(int param1Int) {}
    
    public int scrollVerticallyBy(int param1Int, RecyclerView.Recycler param1Recycler, RecyclerView.State param1State) {
      return 0;
    }
    
    public void setAutoMeasureEnabled(boolean param1Boolean) {
      this.mAutoMeasure = param1Boolean;
    }
    
    void setExactMeasureSpecsFrom(RecyclerView param1RecyclerView) {
      setMeasureSpecs(View.MeasureSpec.makeMeasureSpec(param1RecyclerView.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(param1RecyclerView.getHeight(), 1073741824));
    }
    
    public final void setItemPrefetchEnabled(boolean param1Boolean) {
      if (param1Boolean != this.mItemPrefetchEnabled) {
        this.mItemPrefetchEnabled = param1Boolean;
        this.mPrefetchMaxCountObserved = 0;
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null)
          recyclerView.mRecycler.updateViewCacheSize(); 
      } 
    }
    
    void setMeasureSpecs(int param1Int1, int param1Int2) {
      this.mWidth = View.MeasureSpec.getSize(param1Int1);
      param1Int1 = View.MeasureSpec.getMode(param1Int1);
      this.mWidthMode = param1Int1;
      if (param1Int1 == 0 && !RecyclerView.ALLOW_SIZE_IN_UNSPECIFIED_SPEC)
        this.mWidth = 0; 
      this.mHeight = View.MeasureSpec.getSize(param1Int2);
      param1Int1 = View.MeasureSpec.getMode(param1Int2);
      this.mHeightMode = param1Int1;
      if (param1Int1 == 0 && !RecyclerView.ALLOW_SIZE_IN_UNSPECIFIED_SPEC)
        this.mHeight = 0; 
    }
    
    public void setMeasuredDimension(int param1Int1, int param1Int2) {
      this.mRecyclerView.setMeasuredDimension(param1Int1, param1Int2);
    }
    
    public void setMeasuredDimension(Rect param1Rect, int param1Int1, int param1Int2) {
      int i = param1Rect.width();
      int j = getPaddingLeft();
      int k = getPaddingRight();
      int m = param1Rect.height();
      int n = getPaddingTop();
      int i1 = getPaddingBottom();
      setMeasuredDimension(chooseSize(param1Int1, i + j + k, getMinimumWidth()), chooseSize(param1Int2, m + n + i1, getMinimumHeight()));
    }
    
    void setMeasuredDimensionFromChildren(int param1Int1, int param1Int2) {
      int i = getChildCount();
      if (i == 0) {
        this.mRecyclerView.defaultOnMeasure(param1Int1, param1Int2);
        return;
      } 
      byte b = 0;
      int j = Integer.MIN_VALUE;
      int k = Integer.MIN_VALUE;
      int m = Integer.MAX_VALUE;
      int n;
      for (n = Integer.MAX_VALUE; b < i; n = i1) {
        View view = getChildAt(b);
        Rect rect = this.mRecyclerView.mTempRect;
        getDecoratedBoundsWithMargins(view, rect);
        int i1 = rect.left;
        int i2 = m;
        if (i1 < m)
          i2 = i1; 
        i1 = rect.right;
        m = j;
        if (i1 > j)
          m = i1; 
        j = rect.top;
        i1 = n;
        if (j < n)
          i1 = j; 
        j = rect.bottom;
        n = k;
        if (j > k)
          n = j; 
        b++;
        j = m;
        k = n;
        m = i2;
      } 
      this.mRecyclerView.mTempRect.set(m, n, j, k);
      setMeasuredDimension(this.mRecyclerView.mTempRect, param1Int1, param1Int2);
    }
    
    public void setMeasurementCacheEnabled(boolean param1Boolean) {
      this.mMeasurementCacheEnabled = param1Boolean;
    }
    
    void setRecyclerView(RecyclerView param1RecyclerView) {
      if (param1RecyclerView == null) {
        this.mRecyclerView = null;
        this.mChildHelper = null;
        this.mWidth = 0;
        this.mHeight = 0;
      } else {
        this.mRecyclerView = param1RecyclerView;
        this.mChildHelper = param1RecyclerView.mChildHelper;
        this.mWidth = param1RecyclerView.getWidth();
        this.mHeight = param1RecyclerView.getHeight();
      } 
      this.mWidthMode = 1073741824;
      this.mHeightMode = 1073741824;
    }
    
    boolean shouldMeasureChild(View param1View, int param1Int1, int param1Int2, RecyclerView.LayoutParams param1LayoutParams) {
      return (param1View.isLayoutRequested() || !this.mMeasurementCacheEnabled || !isMeasurementUpToDate(param1View.getWidth(), param1Int1, param1LayoutParams.width) || !isMeasurementUpToDate(param1View.getHeight(), param1Int2, param1LayoutParams.height));
    }
    
    boolean shouldMeasureTwice() {
      return false;
    }
    
    boolean shouldReMeasureChild(View param1View, int param1Int1, int param1Int2, RecyclerView.LayoutParams param1LayoutParams) {
      return (!this.mMeasurementCacheEnabled || !isMeasurementUpToDate(param1View.getMeasuredWidth(), param1Int1, param1LayoutParams.width) || !isMeasurementUpToDate(param1View.getMeasuredHeight(), param1Int2, param1LayoutParams.height));
    }
    
    public void smoothScrollToPosition(RecyclerView param1RecyclerView, RecyclerView.State param1State, int param1Int) {
      Log.e("RecyclerView", "You must override smoothScrollToPosition to support smooth scrolling");
    }
    
    public void startSmoothScroll(RecyclerView.SmoothScroller param1SmoothScroller) {
      RecyclerView.SmoothScroller smoothScroller = this.mSmoothScroller;
      if (smoothScroller != null && param1SmoothScroller != smoothScroller && smoothScroller.isRunning())
        this.mSmoothScroller.stop(); 
      this.mSmoothScroller = param1SmoothScroller;
      param1SmoothScroller.start(this.mRecyclerView, this);
    }
    
    public void stopIgnoringView(View param1View) {
      RecyclerView.ViewHolder viewHolder = RecyclerView.getChildViewHolderInt(param1View);
      viewHolder.stopIgnoring();
      viewHolder.resetInternal();
      viewHolder.addFlags(4);
    }
    
    void stopSmoothScroller() {
      RecyclerView.SmoothScroller smoothScroller = this.mSmoothScroller;
      if (smoothScroller != null)
        smoothScroller.stop(); 
    }
    
    public boolean supportsPredictiveItemAnimations() {
      return false;
    }
    
    public static interface LayoutPrefetchRegistry {
      void addPosition(int param2Int1, int param2Int2);
    }
    
    public static class Properties {
      public int orientation;
      
      public boolean reverseLayout;
      
      public int spanCount;
      
      public boolean stackFromEnd;
    }
  }
  
  class null implements ViewBoundsCheck.Callback {
    public View getChildAt(int param1Int) {
      return this.this$0.getChildAt(param1Int);
    }
    
    public int getChildCount() {
      return this.this$0.getChildCount();
    }
    
    public int getChildEnd(View param1View) {
      RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)param1View.getLayoutParams();
      return this.this$0.getDecoratedRight(param1View) + layoutParams.rightMargin;
    }
    
    public int getChildStart(View param1View) {
      RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)param1View.getLayoutParams();
      return this.this$0.getDecoratedLeft(param1View) - layoutParams.leftMargin;
    }
    
    public View getParent() {
      return (View)this.this$0.mRecyclerView;
    }
    
    public int getParentEnd() {
      return this.this$0.getWidth() - this.this$0.getPaddingRight();
    }
    
    public int getParentStart() {
      return this.this$0.getPaddingLeft();
    }
  }
  
  class null implements ViewBoundsCheck.Callback {
    public View getChildAt(int param1Int) {
      return this.this$0.getChildAt(param1Int);
    }
    
    public int getChildCount() {
      return this.this$0.getChildCount();
    }
    
    public int getChildEnd(View param1View) {
      RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)param1View.getLayoutParams();
      return this.this$0.getDecoratedBottom(param1View) + layoutParams.bottomMargin;
    }
    
    public int getChildStart(View param1View) {
      RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)param1View.getLayoutParams();
      return this.this$0.getDecoratedTop(param1View) - layoutParams.topMargin;
    }
    
    public View getParent() {
      return (View)this.this$0.mRecyclerView;
    }
    
    public int getParentEnd() {
      return this.this$0.getHeight() - this.this$0.getPaddingBottom();
    }
    
    public int getParentStart() {
      return this.this$0.getPaddingTop();
    }
  }
  
  public static interface LayoutPrefetchRegistry {
    void addPosition(int param1Int1, int param1Int2);
  }
  
  public static class Properties {
    public int orientation;
    
    public boolean reverseLayout;
    
    public int spanCount;
    
    public boolean stackFromEnd;
  }
  
  public static class LayoutParams extends ViewGroup.MarginLayoutParams {
    final Rect mDecorInsets = new Rect();
    
    boolean mInsetsDirty = true;
    
    boolean mPendingInvalidate = false;
    
    RecyclerView.ViewHolder mViewHolder;
    
    public LayoutParams(int param1Int1, int param1Int2) {
      super(param1Int1, param1Int2);
    }
    
    public LayoutParams(Context param1Context, AttributeSet param1AttributeSet) {
      super(param1Context, param1AttributeSet);
    }
    
    public LayoutParams(LayoutParams param1LayoutParams) {
      super((ViewGroup.LayoutParams)param1LayoutParams);
    }
    
    public LayoutParams(ViewGroup.LayoutParams param1LayoutParams) {
      super(param1LayoutParams);
    }
    
    public LayoutParams(ViewGroup.MarginLayoutParams param1MarginLayoutParams) {
      super(param1MarginLayoutParams);
    }
    
    public int getViewAdapterPosition() {
      return this.mViewHolder.getAdapterPosition();
    }
    
    public int getViewLayoutPosition() {
      return this.mViewHolder.getLayoutPosition();
    }
    
    @Deprecated
    public int getViewPosition() {
      return this.mViewHolder.getPosition();
    }
    
    public boolean isItemChanged() {
      return this.mViewHolder.isUpdated();
    }
    
    public boolean isItemRemoved() {
      return this.mViewHolder.isRemoved();
    }
    
    public boolean isViewInvalid() {
      return this.mViewHolder.isInvalid();
    }
    
    public boolean viewNeedsUpdate() {
      return this.mViewHolder.needsUpdate();
    }
  }
  
  public static interface OnChildAttachStateChangeListener {
    void onChildViewAttachedToWindow(View param1View);
    
    void onChildViewDetachedFromWindow(View param1View);
  }
  
  public static abstract class OnFlingListener {
    public abstract boolean onFling(int param1Int1, int param1Int2);
  }
  
  public static interface OnItemTouchListener {
    boolean onInterceptTouchEvent(RecyclerView param1RecyclerView, MotionEvent param1MotionEvent);
    
    void onRequestDisallowInterceptTouchEvent(boolean param1Boolean);
    
    void onTouchEvent(RecyclerView param1RecyclerView, MotionEvent param1MotionEvent);
  }
  
  public static abstract class OnScrollListener {
    public void onScrollStateChanged(RecyclerView param1RecyclerView, int param1Int) {}
    
    public void onScrolled(RecyclerView param1RecyclerView, int param1Int1, int param1Int2) {}
  }
  
  public static class RecycledViewPool {
    private static final int DEFAULT_MAX_SCRAP = 5;
    
    private int mAttachCount = 0;
    
    SparseArray<ScrapData> mScrap = new SparseArray();
    
    private ScrapData getScrapDataForType(int param1Int) {
      ScrapData scrapData1 = (ScrapData)this.mScrap.get(param1Int);
      ScrapData scrapData2 = scrapData1;
      if (scrapData1 == null) {
        scrapData2 = new ScrapData();
        this.mScrap.put(param1Int, scrapData2);
      } 
      return scrapData2;
    }
    
    void attach(RecyclerView.Adapter param1Adapter) {
      this.mAttachCount++;
    }
    
    public void clear() {
      for (byte b = 0; b < this.mScrap.size(); b++)
        ((ScrapData)this.mScrap.valueAt(b)).mScrapHeap.clear(); 
    }
    
    void detach() {
      this.mAttachCount--;
    }
    
    void factorInBindTime(int param1Int, long param1Long) {
      ScrapData scrapData = getScrapDataForType(param1Int);
      scrapData.mBindRunningAverageNs = runningAverage(scrapData.mBindRunningAverageNs, param1Long);
    }
    
    void factorInCreateTime(int param1Int, long param1Long) {
      ScrapData scrapData = getScrapDataForType(param1Int);
      scrapData.mCreateRunningAverageNs = runningAverage(scrapData.mCreateRunningAverageNs, param1Long);
    }
    
    public RecyclerView.ViewHolder getRecycledView(int param1Int) {
      ScrapData scrapData = (ScrapData)this.mScrap.get(param1Int);
      if (scrapData != null && !scrapData.mScrapHeap.isEmpty()) {
        ArrayList<RecyclerView.ViewHolder> arrayList = scrapData.mScrapHeap;
        return arrayList.remove(arrayList.size() - 1);
      } 
      return null;
    }
    
    public int getRecycledViewCount(int param1Int) {
      return (getScrapDataForType(param1Int)).mScrapHeap.size();
    }
    
    void onAdapterChanged(RecyclerView.Adapter param1Adapter1, RecyclerView.Adapter param1Adapter2, boolean param1Boolean) {
      if (param1Adapter1 != null)
        detach(); 
      if (!param1Boolean && this.mAttachCount == 0)
        clear(); 
      if (param1Adapter2 != null)
        attach(param1Adapter2); 
    }
    
    public void putRecycledView(RecyclerView.ViewHolder param1ViewHolder) {
      int i = param1ViewHolder.getItemViewType();
      ArrayList<RecyclerView.ViewHolder> arrayList = (getScrapDataForType(i)).mScrapHeap;
      if (((ScrapData)this.mScrap.get(i)).mMaxScrap <= arrayList.size())
        return; 
      param1ViewHolder.resetInternal();
      arrayList.add(param1ViewHolder);
    }
    
    long runningAverage(long param1Long1, long param1Long2) {
      return (param1Long1 == 0L) ? param1Long2 : (param1Long1 / 4L * 3L + param1Long2 / 4L);
    }
    
    public void setMaxRecycledViews(int param1Int1, int param1Int2) {
      ScrapData scrapData = getScrapDataForType(param1Int1);
      scrapData.mMaxScrap = param1Int2;
      ArrayList<RecyclerView.ViewHolder> arrayList = scrapData.mScrapHeap;
      if (arrayList != null)
        while (arrayList.size() > param1Int2)
          arrayList.remove(arrayList.size() - 1);  
    }
    
    int size() {
      byte b = 0;
      int i;
      for (i = 0; b < this.mScrap.size(); i = j) {
        ArrayList<RecyclerView.ViewHolder> arrayList = ((ScrapData)this.mScrap.valueAt(b)).mScrapHeap;
        int j = i;
        if (arrayList != null)
          j = i + arrayList.size(); 
        b++;
      } 
      return i;
    }
    
    boolean willBindInTime(int param1Int, long param1Long1, long param1Long2) {
      long l = (getScrapDataForType(param1Int)).mBindRunningAverageNs;
      return (l == 0L || param1Long1 + l < param1Long2);
    }
    
    boolean willCreateInTime(int param1Int, long param1Long1, long param1Long2) {
      long l = (getScrapDataForType(param1Int)).mCreateRunningAverageNs;
      return (l == 0L || param1Long1 + l < param1Long2);
    }
    
    static class ScrapData {
      long mBindRunningAverageNs = 0L;
      
      long mCreateRunningAverageNs = 0L;
      
      int mMaxScrap = 5;
      
      ArrayList<RecyclerView.ViewHolder> mScrapHeap = new ArrayList<RecyclerView.ViewHolder>();
    }
  }
  
  static class ScrapData {
    long mBindRunningAverageNs = 0L;
    
    long mCreateRunningAverageNs = 0L;
    
    int mMaxScrap = 5;
    
    ArrayList<RecyclerView.ViewHolder> mScrapHeap = new ArrayList<RecyclerView.ViewHolder>();
  }
  
  public final class Recycler {
    static final int DEFAULT_CACHE_SIZE = 2;
    
    final ArrayList<RecyclerView.ViewHolder> mAttachedScrap;
    
    final ArrayList<RecyclerView.ViewHolder> mCachedViews;
    
    ArrayList<RecyclerView.ViewHolder> mChangedScrap;
    
    RecyclerView.RecycledViewPool mRecyclerPool;
    
    private int mRequestedCacheMax;
    
    private final List<RecyclerView.ViewHolder> mUnmodifiableAttachedScrap;
    
    private RecyclerView.ViewCacheExtension mViewCacheExtension;
    
    int mViewCacheMax;
    
    public Recycler() {
      ArrayList<RecyclerView.ViewHolder> arrayList = new ArrayList();
      this.mAttachedScrap = arrayList;
      this.mChangedScrap = null;
      this.mCachedViews = new ArrayList<RecyclerView.ViewHolder>();
      this.mUnmodifiableAttachedScrap = Collections.unmodifiableList(arrayList);
      this.mRequestedCacheMax = 2;
      this.mViewCacheMax = 2;
    }
    
    private void attachAccessibilityDelegateOnBind(RecyclerView.ViewHolder param1ViewHolder) {
      if (RecyclerView.this.isAccessibilityEnabled()) {
        View view = param1ViewHolder.itemView;
        if (ViewCompat.getImportantForAccessibility(view) == 0)
          ViewCompat.setImportantForAccessibility(view, 1); 
        if (!ViewCompat.hasAccessibilityDelegate(view)) {
          param1ViewHolder.addFlags(16384);
          ViewCompat.setAccessibilityDelegate(view, RecyclerView.this.mAccessibilityDelegate.getItemDelegate());
        } 
      } 
    }
    
    private void invalidateDisplayListInt(RecyclerView.ViewHolder param1ViewHolder) {
      View view = param1ViewHolder.itemView;
      if (view instanceof ViewGroup)
        invalidateDisplayListInt((ViewGroup)view, false); 
    }
    
    private void invalidateDisplayListInt(ViewGroup param1ViewGroup, boolean param1Boolean) {
      int i;
      for (i = param1ViewGroup.getChildCount() - 1; i >= 0; i--) {
        View view = param1ViewGroup.getChildAt(i);
        if (view instanceof ViewGroup)
          invalidateDisplayListInt((ViewGroup)view, true); 
      } 
      if (!param1Boolean)
        return; 
      if (param1ViewGroup.getVisibility() == 4) {
        param1ViewGroup.setVisibility(0);
        param1ViewGroup.setVisibility(4);
      } else {
        i = param1ViewGroup.getVisibility();
        param1ViewGroup.setVisibility(4);
        param1ViewGroup.setVisibility(i);
      } 
    }
    
    private boolean tryBindViewHolderByDeadline(RecyclerView.ViewHolder param1ViewHolder, int param1Int1, int param1Int2, long param1Long) {
      param1ViewHolder.mOwnerRecyclerView = RecyclerView.this;
      int i = param1ViewHolder.getItemViewType();
      long l = RecyclerView.this.getNanoTime();
      if (param1Long != Long.MAX_VALUE && !this.mRecyclerPool.willBindInTime(i, l, param1Long))
        return false; 
      RecyclerView.this.mAdapter.bindViewHolder(param1ViewHolder, param1Int1);
      param1Long = RecyclerView.this.getNanoTime();
      this.mRecyclerPool.factorInBindTime(param1ViewHolder.getItemViewType(), param1Long - l);
      attachAccessibilityDelegateOnBind(param1ViewHolder);
      if (RecyclerView.this.mState.isPreLayout())
        param1ViewHolder.mPreLayoutPosition = param1Int2; 
      return true;
    }
    
    void addViewHolderToRecycledViewPool(RecyclerView.ViewHolder param1ViewHolder, boolean param1Boolean) {
      RecyclerView.clearNestedRecyclerViewIfNotNested(param1ViewHolder);
      if (param1ViewHolder.hasAnyOfTheFlags(16384)) {
        param1ViewHolder.setFlags(0, 16384);
        ViewCompat.setAccessibilityDelegate(param1ViewHolder.itemView, null);
      } 
      if (param1Boolean)
        dispatchViewRecycled(param1ViewHolder); 
      param1ViewHolder.mOwnerRecyclerView = null;
      getRecycledViewPool().putRecycledView(param1ViewHolder);
    }
    
    public void bindViewToPosition(View param1View, int param1Int) {
      RecyclerView.ViewHolder viewHolder = RecyclerView.getChildViewHolderInt(param1View);
      if (viewHolder != null) {
        int i = RecyclerView.this.mAdapterHelper.findPositionOffset(param1Int);
        if (i >= 0 && i < RecyclerView.this.mAdapter.getItemCount()) {
          RecyclerView.LayoutParams layoutParams;
          tryBindViewHolderByDeadline(viewHolder, i, param1Int, Long.MAX_VALUE);
          ViewGroup.LayoutParams layoutParams1 = viewHolder.itemView.getLayoutParams();
          if (layoutParams1 == null) {
            layoutParams = (RecyclerView.LayoutParams)RecyclerView.this.generateDefaultLayoutParams();
            viewHolder.itemView.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
          } else if (!RecyclerView.this.checkLayoutParams((ViewGroup.LayoutParams)layoutParams)) {
            layoutParams = (RecyclerView.LayoutParams)RecyclerView.this.generateLayoutParams((ViewGroup.LayoutParams)layoutParams);
            viewHolder.itemView.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
          } else {
            layoutParams = layoutParams;
          } 
          boolean bool = true;
          layoutParams.mInsetsDirty = true;
          layoutParams.mViewHolder = viewHolder;
          if (viewHolder.itemView.getParent() != null)
            bool = false; 
          layoutParams.mPendingInvalidate = bool;
          return;
        } 
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Inconsistency detected. Invalid item position ");
        stringBuilder1.append(param1Int);
        stringBuilder1.append("(offset:");
        stringBuilder1.append(i);
        stringBuilder1.append(").");
        stringBuilder1.append("state:");
        stringBuilder1.append(RecyclerView.this.mState.getItemCount());
        stringBuilder1.append(RecyclerView.this.exceptionLabel());
        throw new IndexOutOfBoundsException(stringBuilder1.toString());
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("The view does not have a ViewHolder. You cannot pass arbitrary views to this method, they should be created by the Adapter");
      stringBuilder.append(RecyclerView.this.exceptionLabel());
      throw new IllegalArgumentException(stringBuilder.toString());
    }
    
    public void clear() {
      this.mAttachedScrap.clear();
      recycleAndClearCachedViews();
    }
    
    void clearOldPositions() {
      int i = this.mCachedViews.size();
      boolean bool = false;
      byte b;
      for (b = 0; b < i; b++)
        ((RecyclerView.ViewHolder)this.mCachedViews.get(b)).clearOldPosition(); 
      i = this.mAttachedScrap.size();
      for (b = 0; b < i; b++)
        ((RecyclerView.ViewHolder)this.mAttachedScrap.get(b)).clearOldPosition(); 
      ArrayList<RecyclerView.ViewHolder> arrayList = this.mChangedScrap;
      if (arrayList != null) {
        i = arrayList.size();
        for (b = bool; b < i; b++)
          ((RecyclerView.ViewHolder)this.mChangedScrap.get(b)).clearOldPosition(); 
      } 
    }
    
    void clearScrap() {
      this.mAttachedScrap.clear();
      ArrayList<RecyclerView.ViewHolder> arrayList = this.mChangedScrap;
      if (arrayList != null)
        arrayList.clear(); 
    }
    
    public int convertPreLayoutPositionToPostLayout(int param1Int) {
      if (param1Int >= 0 && param1Int < RecyclerView.this.mState.getItemCount())
        return !RecyclerView.this.mState.isPreLayout() ? param1Int : RecyclerView.this.mAdapterHelper.findPositionOffset(param1Int); 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("invalid position ");
      stringBuilder.append(param1Int);
      stringBuilder.append(". State ");
      stringBuilder.append("item count is ");
      stringBuilder.append(RecyclerView.this.mState.getItemCount());
      stringBuilder.append(RecyclerView.this.exceptionLabel());
      throw new IndexOutOfBoundsException(stringBuilder.toString());
    }
    
    void dispatchViewRecycled(RecyclerView.ViewHolder param1ViewHolder) {
      RecyclerView.RecyclerListener recyclerListener = RecyclerView.this.mRecyclerListener;
      if (recyclerListener != null)
        recyclerListener.onViewRecycled(param1ViewHolder); 
      RecyclerView.Adapter<RecyclerView.ViewHolder> adapter = RecyclerView.this.mAdapter;
      if (adapter != null)
        adapter.onViewRecycled(param1ViewHolder); 
      RecyclerView recyclerView = RecyclerView.this;
      if (recyclerView.mState != null)
        recyclerView.mViewInfoStore.removeViewHolder(param1ViewHolder); 
    }
    
    RecyclerView.ViewHolder getChangedScrapViewForPosition(int param1Int) {
      ArrayList<RecyclerView.ViewHolder> arrayList = this.mChangedScrap;
      if (arrayList != null) {
        int i = arrayList.size();
        if (i != 0) {
          boolean bool = false;
          for (byte b = 0; b < i; b++) {
            RecyclerView.ViewHolder viewHolder = this.mChangedScrap.get(b);
            if (!viewHolder.wasReturnedFromScrap() && viewHolder.getLayoutPosition() == param1Int) {
              viewHolder.addFlags(32);
              return viewHolder;
            } 
          } 
          if (RecyclerView.this.mAdapter.hasStableIds()) {
            param1Int = RecyclerView.this.mAdapterHelper.findPositionOffset(param1Int);
            if (param1Int > 0 && param1Int < RecyclerView.this.mAdapter.getItemCount()) {
              long l = RecyclerView.this.mAdapter.getItemId(param1Int);
              for (param1Int = bool; param1Int < i; param1Int++) {
                RecyclerView.ViewHolder viewHolder = this.mChangedScrap.get(param1Int);
                if (!viewHolder.wasReturnedFromScrap() && viewHolder.getItemId() == l) {
                  viewHolder.addFlags(32);
                  return viewHolder;
                } 
              } 
            } 
          } 
        } 
      } 
      return null;
    }
    
    RecyclerView.RecycledViewPool getRecycledViewPool() {
      if (this.mRecyclerPool == null)
        this.mRecyclerPool = new RecyclerView.RecycledViewPool(); 
      return this.mRecyclerPool;
    }
    
    int getScrapCount() {
      return this.mAttachedScrap.size();
    }
    
    public List<RecyclerView.ViewHolder> getScrapList() {
      return this.mUnmodifiableAttachedScrap;
    }
    
    RecyclerView.ViewHolder getScrapOrCachedViewForId(long param1Long, int param1Int, boolean param1Boolean) {
      int i;
      for (i = this.mAttachedScrap.size() - 1; i >= 0; i--) {
        RecyclerView.ViewHolder viewHolder = this.mAttachedScrap.get(i);
        if (viewHolder.getItemId() == param1Long && !viewHolder.wasReturnedFromScrap()) {
          if (param1Int == viewHolder.getItemViewType()) {
            viewHolder.addFlags(32);
            if (viewHolder.isRemoved() && !RecyclerView.this.mState.isPreLayout())
              viewHolder.setFlags(2, 14); 
            return viewHolder;
          } 
          if (!param1Boolean) {
            this.mAttachedScrap.remove(i);
            RecyclerView.this.removeDetachedView(viewHolder.itemView, false);
            quickRecycleScrapView(viewHolder.itemView);
          } 
        } 
      } 
      for (i = this.mCachedViews.size() - 1; i >= 0; i--) {
        RecyclerView.ViewHolder viewHolder = this.mCachedViews.get(i);
        if (viewHolder.getItemId() == param1Long) {
          if (param1Int == viewHolder.getItemViewType()) {
            if (!param1Boolean)
              this.mCachedViews.remove(i); 
            return viewHolder;
          } 
          if (!param1Boolean) {
            recycleCachedViewAt(i);
            return null;
          } 
        } 
      } 
      return null;
    }
    
    RecyclerView.ViewHolder getScrapOrHiddenOrCachedHolderForPosition(int param1Int, boolean param1Boolean) {
      int i = this.mAttachedScrap.size();
      boolean bool = false;
      byte b;
      for (b = 0; b < i; b++) {
        RecyclerView.ViewHolder viewHolder = this.mAttachedScrap.get(b);
        if (!viewHolder.wasReturnedFromScrap() && viewHolder.getLayoutPosition() == param1Int && !viewHolder.isInvalid() && (RecyclerView.this.mState.mInPreLayout || !viewHolder.isRemoved())) {
          viewHolder.addFlags(32);
          return viewHolder;
        } 
      } 
      if (!param1Boolean) {
        View view = RecyclerView.this.mChildHelper.findHiddenNonRemovedView(param1Int);
        if (view != null) {
          RecyclerView.ViewHolder viewHolder = RecyclerView.getChildViewHolderInt(view);
          RecyclerView.this.mChildHelper.unhide(view);
          param1Int = RecyclerView.this.mChildHelper.indexOfChild(view);
          if (param1Int != -1) {
            RecyclerView.this.mChildHelper.detachViewFromParent(param1Int);
            scrapView(view);
            viewHolder.addFlags(8224);
            return viewHolder;
          } 
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("layout index should not be -1 after unhiding a view:");
          stringBuilder.append(viewHolder);
          stringBuilder.append(RecyclerView.this.exceptionLabel());
          throw new IllegalStateException(stringBuilder.toString());
        } 
      } 
      i = this.mCachedViews.size();
      for (b = bool; b < i; b++) {
        RecyclerView.ViewHolder viewHolder = this.mCachedViews.get(b);
        if (!viewHolder.isInvalid() && viewHolder.getLayoutPosition() == param1Int) {
          if (!param1Boolean)
            this.mCachedViews.remove(b); 
          return viewHolder;
        } 
      } 
      return null;
    }
    
    View getScrapViewAt(int param1Int) {
      return ((RecyclerView.ViewHolder)this.mAttachedScrap.get(param1Int)).itemView;
    }
    
    public View getViewForPosition(int param1Int) {
      return getViewForPosition(param1Int, false);
    }
    
    View getViewForPosition(int param1Int, boolean param1Boolean) {
      return (tryGetViewHolderForPositionByDeadline(param1Int, param1Boolean, Long.MAX_VALUE)).itemView;
    }
    
    void markItemDecorInsetsDirty() {
      int i = this.mCachedViews.size();
      for (byte b = 0; b < i; b++) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)((RecyclerView.ViewHolder)this.mCachedViews.get(b)).itemView.getLayoutParams();
        if (layoutParams != null)
          layoutParams.mInsetsDirty = true; 
      } 
    }
    
    void markKnownViewsInvalid() {
      RecyclerView.Adapter adapter = RecyclerView.this.mAdapter;
      if (adapter != null && adapter.hasStableIds()) {
        int i = this.mCachedViews.size();
        for (byte b = 0; b < i; b++) {
          RecyclerView.ViewHolder viewHolder = this.mCachedViews.get(b);
          if (viewHolder != null) {
            viewHolder.addFlags(6);
            viewHolder.addChangePayload(null);
          } 
        } 
      } else {
        recycleAndClearCachedViews();
      } 
    }
    
    void offsetPositionRecordsForInsert(int param1Int1, int param1Int2) {
      int i = this.mCachedViews.size();
      for (byte b = 0; b < i; b++) {
        RecyclerView.ViewHolder viewHolder = this.mCachedViews.get(b);
        if (viewHolder != null && viewHolder.mPosition >= param1Int1)
          viewHolder.offsetPosition(param1Int2, true); 
      } 
    }
    
    void offsetPositionRecordsForMove(int param1Int1, int param1Int2) {
      boolean bool;
      int i;
      int j;
      if (param1Int1 < param1Int2) {
        bool = true;
        i = param1Int1;
        j = param1Int2;
      } else {
        bool = true;
        j = param1Int1;
        i = param1Int2;
      } 
      int k = this.mCachedViews.size();
      for (byte b = 0; b < k; b++) {
        RecyclerView.ViewHolder viewHolder = this.mCachedViews.get(b);
        if (viewHolder != null) {
          int m = viewHolder.mPosition;
          if (m >= i && m <= j)
            if (m == param1Int1) {
              viewHolder.offsetPosition(param1Int2 - param1Int1, false);
            } else {
              viewHolder.offsetPosition(bool, false);
            }  
        } 
      } 
    }
    
    void offsetPositionRecordsForRemove(int param1Int1, int param1Int2, boolean param1Boolean) {
      for (int i = this.mCachedViews.size() - 1; i >= 0; i--) {
        RecyclerView.ViewHolder viewHolder = this.mCachedViews.get(i);
        if (viewHolder != null) {
          int j = viewHolder.mPosition;
          if (j >= param1Int1 + param1Int2) {
            viewHolder.offsetPosition(-param1Int2, param1Boolean);
          } else if (j >= param1Int1) {
            viewHolder.addFlags(8);
            recycleCachedViewAt(i);
          } 
        } 
      } 
    }
    
    void onAdapterChanged(RecyclerView.Adapter param1Adapter1, RecyclerView.Adapter param1Adapter2, boolean param1Boolean) {
      clear();
      getRecycledViewPool().onAdapterChanged(param1Adapter1, param1Adapter2, param1Boolean);
    }
    
    void quickRecycleScrapView(View param1View) {
      RecyclerView.ViewHolder viewHolder = RecyclerView.getChildViewHolderInt(param1View);
      RecyclerView.ViewHolder.access$1002(viewHolder, null);
      RecyclerView.ViewHolder.access$1102(viewHolder, false);
      viewHolder.clearReturnedFromScrapFlag();
      recycleViewHolderInternal(viewHolder);
    }
    
    void recycleAndClearCachedViews() {
      for (int i = this.mCachedViews.size() - 1; i >= 0; i--)
        recycleCachedViewAt(i); 
      this.mCachedViews.clear();
      if (RecyclerView.ALLOW_THREAD_GAP_WORK)
        RecyclerView.this.mPrefetchRegistry.clearPrefetchPositions(); 
    }
    
    void recycleCachedViewAt(int param1Int) {
      addViewHolderToRecycledViewPool(this.mCachedViews.get(param1Int), true);
      this.mCachedViews.remove(param1Int);
    }
    
    public void recycleView(View param1View) {
      RecyclerView.ViewHolder viewHolder = RecyclerView.getChildViewHolderInt(param1View);
      if (viewHolder.isTmpDetached())
        RecyclerView.this.removeDetachedView(param1View, false); 
      if (viewHolder.isScrap()) {
        viewHolder.unScrap();
      } else if (viewHolder.wasReturnedFromScrap()) {
        viewHolder.clearReturnedFromScrapFlag();
      } 
      recycleViewHolderInternal(viewHolder);
    }
    
    void recycleViewHolderInternal(RecyclerView.ViewHolder param1ViewHolder) {
      // Byte code:
      //   0: aload_1
      //   1: invokevirtual isScrap : ()Z
      //   4: istore_2
      //   5: iconst_0
      //   6: istore_3
      //   7: iconst_0
      //   8: istore #4
      //   10: iconst_1
      //   11: istore #5
      //   13: iload_2
      //   14: ifne -> 426
      //   17: aload_1
      //   18: getfield itemView : Landroid/view/View;
      //   21: invokevirtual getParent : ()Landroid/view/ViewParent;
      //   24: ifnull -> 30
      //   27: goto -> 426
      //   30: aload_1
      //   31: invokevirtual isTmpDetached : ()Z
      //   34: ifne -> 375
      //   37: aload_1
      //   38: invokevirtual shouldIgnore : ()Z
      //   41: ifne -> 335
      //   44: aload_1
      //   45: invokestatic access$900 : (Landroid/support/v7/widget/RecyclerView$ViewHolder;)Z
      //   48: istore_3
      //   49: aload_0
      //   50: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   53: getfield mAdapter : Landroid/support/v7/widget/RecyclerView$Adapter;
      //   56: astore #6
      //   58: aload #6
      //   60: ifnull -> 82
      //   63: iload_3
      //   64: ifeq -> 82
      //   67: aload #6
      //   69: aload_1
      //   70: invokevirtual onFailedToRecycleView : (Landroid/support/v7/widget/RecyclerView$ViewHolder;)Z
      //   73: ifeq -> 82
      //   76: iconst_1
      //   77: istore #7
      //   79: goto -> 85
      //   82: iconst_0
      //   83: istore #7
      //   85: iload #7
      //   87: ifne -> 110
      //   90: iload #4
      //   92: istore #7
      //   94: aload_1
      //   95: invokevirtual isRecyclable : ()Z
      //   98: ifeq -> 104
      //   101: goto -> 110
      //   104: iconst_0
      //   105: istore #4
      //   107: goto -> 304
      //   110: aload_0
      //   111: getfield mViewCacheMax : I
      //   114: ifle -> 280
      //   117: aload_1
      //   118: sipush #526
      //   121: invokevirtual hasAnyOfTheFlags : (I)Z
      //   124: ifne -> 280
      //   127: aload_0
      //   128: getfield mCachedViews : Ljava/util/ArrayList;
      //   131: invokevirtual size : ()I
      //   134: istore #4
      //   136: iload #4
      //   138: istore #7
      //   140: iload #4
      //   142: aload_0
      //   143: getfield mViewCacheMax : I
      //   146: if_icmplt -> 169
      //   149: iload #4
      //   151: istore #7
      //   153: iload #4
      //   155: ifle -> 169
      //   158: aload_0
      //   159: iconst_0
      //   160: invokevirtual recycleCachedViewAt : (I)V
      //   163: iload #4
      //   165: iconst_1
      //   166: isub
      //   167: istore #7
      //   169: iload #7
      //   171: istore #4
      //   173: invokestatic access$800 : ()Z
      //   176: ifeq -> 264
      //   179: iload #7
      //   181: istore #4
      //   183: iload #7
      //   185: ifle -> 264
      //   188: iload #7
      //   190: istore #4
      //   192: aload_0
      //   193: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   196: getfield mPrefetchRegistry : Landroid/support/v7/widget/GapWorker$LayoutPrefetchRegistryImpl;
      //   199: aload_1
      //   200: getfield mPosition : I
      //   203: invokevirtual lastPrefetchIncludedPosition : (I)Z
      //   206: ifne -> 264
      //   209: iinc #7, -1
      //   212: iload #7
      //   214: iflt -> 258
      //   217: aload_0
      //   218: getfield mCachedViews : Ljava/util/ArrayList;
      //   221: iload #7
      //   223: invokevirtual get : (I)Ljava/lang/Object;
      //   226: checkcast android/support/v7/widget/RecyclerView$ViewHolder
      //   229: getfield mPosition : I
      //   232: istore #4
      //   234: aload_0
      //   235: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   238: getfield mPrefetchRegistry : Landroid/support/v7/widget/GapWorker$LayoutPrefetchRegistryImpl;
      //   241: iload #4
      //   243: invokevirtual lastPrefetchIncludedPosition : (I)Z
      //   246: ifne -> 252
      //   249: goto -> 258
      //   252: iinc #7, -1
      //   255: goto -> 212
      //   258: iload #7
      //   260: iconst_1
      //   261: iadd
      //   262: istore #4
      //   264: aload_0
      //   265: getfield mCachedViews : Ljava/util/ArrayList;
      //   268: iload #4
      //   270: aload_1
      //   271: invokevirtual add : (ILjava/lang/Object;)V
      //   274: iconst_1
      //   275: istore #7
      //   277: goto -> 283
      //   280: iconst_0
      //   281: istore #7
      //   283: iload #7
      //   285: ifne -> 301
      //   288: aload_0
      //   289: aload_1
      //   290: iconst_1
      //   291: invokevirtual addViewHolderToRecycledViewPool : (Landroid/support/v7/widget/RecyclerView$ViewHolder;Z)V
      //   294: iload #5
      //   296: istore #4
      //   298: goto -> 304
      //   301: goto -> 104
      //   304: aload_0
      //   305: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   308: getfield mViewInfoStore : Landroid/support/v7/widget/ViewInfoStore;
      //   311: aload_1
      //   312: invokevirtual removeViewHolder : (Landroid/support/v7/widget/RecyclerView$ViewHolder;)V
      //   315: iload #7
      //   317: ifne -> 334
      //   320: iload #4
      //   322: ifne -> 334
      //   325: iload_3
      //   326: ifeq -> 334
      //   329: aload_1
      //   330: aconst_null
      //   331: putfield mOwnerRecyclerView : Landroid/support/v7/widget/RecyclerView;
      //   334: return
      //   335: new java/lang/StringBuilder
      //   338: dup
      //   339: invokespecial <init> : ()V
      //   342: astore_1
      //   343: aload_1
      //   344: ldc_w 'Trying to recycle an ignored view holder. You should first call stopIgnoringView(view) before calling recycle.'
      //   347: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   350: pop
      //   351: aload_1
      //   352: aload_0
      //   353: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   356: invokevirtual exceptionLabel : ()Ljava/lang/String;
      //   359: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   362: pop
      //   363: new java/lang/IllegalArgumentException
      //   366: dup
      //   367: aload_1
      //   368: invokevirtual toString : ()Ljava/lang/String;
      //   371: invokespecial <init> : (Ljava/lang/String;)V
      //   374: athrow
      //   375: new java/lang/StringBuilder
      //   378: dup
      //   379: invokespecial <init> : ()V
      //   382: astore #6
      //   384: aload #6
      //   386: ldc_w 'Tmp detached view should be removed from RecyclerView before it can be recycled: '
      //   389: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   392: pop
      //   393: aload #6
      //   395: aload_1
      //   396: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   399: pop
      //   400: aload #6
      //   402: aload_0
      //   403: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   406: invokevirtual exceptionLabel : ()Ljava/lang/String;
      //   409: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   412: pop
      //   413: new java/lang/IllegalArgumentException
      //   416: dup
      //   417: aload #6
      //   419: invokevirtual toString : ()Ljava/lang/String;
      //   422: invokespecial <init> : (Ljava/lang/String;)V
      //   425: athrow
      //   426: new java/lang/StringBuilder
      //   429: dup
      //   430: invokespecial <init> : ()V
      //   433: astore #6
      //   435: aload #6
      //   437: ldc_w 'Scrapped or attached views may not be recycled. isScrap:'
      //   440: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   443: pop
      //   444: aload #6
      //   446: aload_1
      //   447: invokevirtual isScrap : ()Z
      //   450: invokevirtual append : (Z)Ljava/lang/StringBuilder;
      //   453: pop
      //   454: aload #6
      //   456: ldc_w ' isAttached:'
      //   459: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   462: pop
      //   463: aload_1
      //   464: getfield itemView : Landroid/view/View;
      //   467: invokevirtual getParent : ()Landroid/view/ViewParent;
      //   470: ifnull -> 475
      //   473: iconst_1
      //   474: istore_3
      //   475: aload #6
      //   477: iload_3
      //   478: invokevirtual append : (Z)Ljava/lang/StringBuilder;
      //   481: pop
      //   482: aload #6
      //   484: aload_0
      //   485: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   488: invokevirtual exceptionLabel : ()Ljava/lang/String;
      //   491: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   494: pop
      //   495: new java/lang/IllegalArgumentException
      //   498: dup
      //   499: aload #6
      //   501: invokevirtual toString : ()Ljava/lang/String;
      //   504: invokespecial <init> : (Ljava/lang/String;)V
      //   507: astore_1
      //   508: goto -> 513
      //   511: aload_1
      //   512: athrow
      //   513: goto -> 511
    }
    
    void recycleViewInternal(View param1View) {
      recycleViewHolderInternal(RecyclerView.getChildViewHolderInt(param1View));
    }
    
    void scrapView(View param1View) {
      StringBuilder stringBuilder;
      RecyclerView.ViewHolder viewHolder = RecyclerView.getChildViewHolderInt(param1View);
      if (viewHolder.hasAnyOfTheFlags(12) || !viewHolder.isUpdated() || RecyclerView.this.canReuseUpdatedViewHolder(viewHolder)) {
        if (!viewHolder.isInvalid() || viewHolder.isRemoved() || RecyclerView.this.mAdapter.hasStableIds()) {
          viewHolder.setScrapContainer(this, false);
          this.mAttachedScrap.add(viewHolder);
          return;
        } 
        stringBuilder = new StringBuilder();
        stringBuilder.append("Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool.");
        stringBuilder.append(RecyclerView.this.exceptionLabel());
        throw new IllegalArgumentException(stringBuilder.toString());
      } 
      if (this.mChangedScrap == null)
        this.mChangedScrap = new ArrayList<RecyclerView.ViewHolder>(); 
      stringBuilder.setScrapContainer(this, true);
      this.mChangedScrap.add(stringBuilder);
    }
    
    void setRecycledViewPool(RecyclerView.RecycledViewPool param1RecycledViewPool) {
      RecyclerView.RecycledViewPool recycledViewPool = this.mRecyclerPool;
      if (recycledViewPool != null)
        recycledViewPool.detach(); 
      this.mRecyclerPool = param1RecycledViewPool;
      if (param1RecycledViewPool != null)
        param1RecycledViewPool.attach(RecyclerView.this.getAdapter()); 
    }
    
    void setViewCacheExtension(RecyclerView.ViewCacheExtension param1ViewCacheExtension) {
      this.mViewCacheExtension = param1ViewCacheExtension;
    }
    
    public void setViewCacheSize(int param1Int) {
      this.mRequestedCacheMax = param1Int;
      updateViewCacheSize();
    }
    
    @Nullable
    RecyclerView.ViewHolder tryGetViewHolderForPositionByDeadline(int param1Int, boolean param1Boolean, long param1Long) {
      // Byte code:
      //   0: iload_1
      //   1: iflt -> 1059
      //   4: iload_1
      //   5: aload_0
      //   6: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   9: getfield mState : Landroid/support/v7/widget/RecyclerView$State;
      //   12: invokevirtual getItemCount : ()I
      //   15: if_icmpge -> 1059
      //   18: aload_0
      //   19: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   22: getfield mState : Landroid/support/v7/widget/RecyclerView$State;
      //   25: invokevirtual isPreLayout : ()Z
      //   28: istore #5
      //   30: iconst_1
      //   31: istore #6
      //   33: iload #5
      //   35: ifeq -> 64
      //   38: aload_0
      //   39: iload_1
      //   40: invokevirtual getChangedScrapViewForPosition : (I)Landroid/support/v7/widget/RecyclerView$ViewHolder;
      //   43: astore #7
      //   45: aload #7
      //   47: astore #8
      //   49: aload #7
      //   51: ifnull -> 67
      //   54: iconst_1
      //   55: istore #9
      //   57: aload #7
      //   59: astore #8
      //   61: goto -> 70
      //   64: aconst_null
      //   65: astore #8
      //   67: iconst_0
      //   68: istore #9
      //   70: aload #8
      //   72: astore #7
      //   74: iload #9
      //   76: istore #10
      //   78: aload #8
      //   80: ifnonnull -> 188
      //   83: aload_0
      //   84: iload_1
      //   85: iload_2
      //   86: invokevirtual getScrapOrHiddenOrCachedHolderForPosition : (IZ)Landroid/support/v7/widget/RecyclerView$ViewHolder;
      //   89: astore #8
      //   91: aload #8
      //   93: astore #7
      //   95: iload #9
      //   97: istore #10
      //   99: aload #8
      //   101: ifnull -> 188
      //   104: aload_0
      //   105: aload #8
      //   107: invokevirtual validateViewHolderForOffsetPosition : (Landroid/support/v7/widget/RecyclerView$ViewHolder;)Z
      //   110: ifne -> 181
      //   113: iload_2
      //   114: ifne -> 171
      //   117: aload #8
      //   119: iconst_4
      //   120: invokevirtual addFlags : (I)V
      //   123: aload #8
      //   125: invokevirtual isScrap : ()Z
      //   128: ifeq -> 152
      //   131: aload_0
      //   132: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   135: aload #8
      //   137: getfield itemView : Landroid/view/View;
      //   140: iconst_0
      //   141: invokevirtual removeDetachedView : (Landroid/view/View;Z)V
      //   144: aload #8
      //   146: invokevirtual unScrap : ()V
      //   149: goto -> 165
      //   152: aload #8
      //   154: invokevirtual wasReturnedFromScrap : ()Z
      //   157: ifeq -> 165
      //   160: aload #8
      //   162: invokevirtual clearReturnedFromScrapFlag : ()V
      //   165: aload_0
      //   166: aload #8
      //   168: invokevirtual recycleViewHolderInternal : (Landroid/support/v7/widget/RecyclerView$ViewHolder;)V
      //   171: aconst_null
      //   172: astore #7
      //   174: iload #9
      //   176: istore #10
      //   178: goto -> 188
      //   181: iconst_1
      //   182: istore #10
      //   184: aload #8
      //   186: astore #7
      //   188: aload #7
      //   190: astore #11
      //   192: iload #10
      //   194: istore #12
      //   196: aload #7
      //   198: ifnonnull -> 747
      //   201: aload_0
      //   202: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   205: getfield mAdapterHelper : Landroid/support/v7/widget/AdapterHelper;
      //   208: iload_1
      //   209: invokevirtual findPositionOffset : (I)I
      //   212: istore #12
      //   214: iload #12
      //   216: iflt -> 646
      //   219: iload #12
      //   221: aload_0
      //   222: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   225: getfield mAdapter : Landroid/support/v7/widget/RecyclerView$Adapter;
      //   228: invokevirtual getItemCount : ()I
      //   231: if_icmpge -> 646
      //   234: aload_0
      //   235: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   238: getfield mAdapter : Landroid/support/v7/widget/RecyclerView$Adapter;
      //   241: iload #12
      //   243: invokevirtual getItemViewType : (I)I
      //   246: istore #13
      //   248: aload #7
      //   250: astore #8
      //   252: iload #10
      //   254: istore #9
      //   256: aload_0
      //   257: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   260: getfield mAdapter : Landroid/support/v7/widget/RecyclerView$Adapter;
      //   263: invokevirtual hasStableIds : ()Z
      //   266: ifeq -> 317
      //   269: aload_0
      //   270: aload_0
      //   271: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   274: getfield mAdapter : Landroid/support/v7/widget/RecyclerView$Adapter;
      //   277: iload #12
      //   279: invokevirtual getItemId : (I)J
      //   282: iload #13
      //   284: iload_2
      //   285: invokevirtual getScrapOrCachedViewForId : (JIZ)Landroid/support/v7/widget/RecyclerView$ViewHolder;
      //   288: astore #7
      //   290: aload #7
      //   292: astore #8
      //   294: iload #10
      //   296: istore #9
      //   298: aload #7
      //   300: ifnull -> 317
      //   303: aload #7
      //   305: iload #12
      //   307: putfield mPosition : I
      //   310: iconst_1
      //   311: istore #9
      //   313: aload #7
      //   315: astore #8
      //   317: aload #8
      //   319: astore #7
      //   321: aload #8
      //   323: ifnonnull -> 476
      //   326: aload_0
      //   327: getfield mViewCacheExtension : Landroid/support/v7/widget/RecyclerView$ViewCacheExtension;
      //   330: astore #11
      //   332: aload #8
      //   334: astore #7
      //   336: aload #11
      //   338: ifnull -> 476
      //   341: aload #11
      //   343: aload_0
      //   344: iload_1
      //   345: iload #13
      //   347: invokevirtual getViewForPositionAndType : (Landroid/support/v7/widget/RecyclerView$Recycler;II)Landroid/view/View;
      //   350: astore #11
      //   352: aload #8
      //   354: astore #7
      //   356: aload #11
      //   358: ifnull -> 476
      //   361: aload_0
      //   362: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   365: aload #11
      //   367: invokevirtual getChildViewHolder : (Landroid/view/View;)Landroid/support/v7/widget/RecyclerView$ViewHolder;
      //   370: astore #7
      //   372: aload #7
      //   374: ifnull -> 432
      //   377: aload #7
      //   379: invokevirtual shouldIgnore : ()Z
      //   382: ifne -> 388
      //   385: goto -> 476
      //   388: new java/lang/StringBuilder
      //   391: dup
      //   392: invokespecial <init> : ()V
      //   395: astore #7
      //   397: aload #7
      //   399: ldc_w 'getViewForPositionAndType returned a view that is ignored. You must call stopIgnoring before returning this view.'
      //   402: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   405: pop
      //   406: aload #7
      //   408: aload_0
      //   409: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   412: invokevirtual exceptionLabel : ()Ljava/lang/String;
      //   415: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   418: pop
      //   419: new java/lang/IllegalArgumentException
      //   422: dup
      //   423: aload #7
      //   425: invokevirtual toString : ()Ljava/lang/String;
      //   428: invokespecial <init> : (Ljava/lang/String;)V
      //   431: athrow
      //   432: new java/lang/StringBuilder
      //   435: dup
      //   436: invokespecial <init> : ()V
      //   439: astore #7
      //   441: aload #7
      //   443: ldc_w 'getViewForPositionAndType returned a view which does not have a ViewHolder'
      //   446: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   449: pop
      //   450: aload #7
      //   452: aload_0
      //   453: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   456: invokevirtual exceptionLabel : ()Ljava/lang/String;
      //   459: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   462: pop
      //   463: new java/lang/IllegalArgumentException
      //   466: dup
      //   467: aload #7
      //   469: invokevirtual toString : ()Ljava/lang/String;
      //   472: invokespecial <init> : (Ljava/lang/String;)V
      //   475: athrow
      //   476: aload #7
      //   478: astore #8
      //   480: aload #7
      //   482: ifnonnull -> 518
      //   485: aload_0
      //   486: invokevirtual getRecycledViewPool : ()Landroid/support/v7/widget/RecyclerView$RecycledViewPool;
      //   489: iload #13
      //   491: invokevirtual getRecycledView : (I)Landroid/support/v7/widget/RecyclerView$ViewHolder;
      //   494: astore #8
      //   496: aload #8
      //   498: ifnull -> 518
      //   501: aload #8
      //   503: invokevirtual resetInternal : ()V
      //   506: getstatic android/support/v7/widget/RecyclerView.FORCE_INVALIDATE_DISPLAY_LIST : Z
      //   509: ifeq -> 518
      //   512: aload_0
      //   513: aload #8
      //   515: invokespecial invalidateDisplayListInt : (Landroid/support/v7/widget/RecyclerView$ViewHolder;)V
      //   518: aload #8
      //   520: astore #11
      //   522: iload #9
      //   524: istore #12
      //   526: aload #8
      //   528: ifnonnull -> 747
      //   531: aload_0
      //   532: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   535: invokevirtual getNanoTime : ()J
      //   538: lstore #14
      //   540: lload_3
      //   541: ldc2_w 9223372036854775807
      //   544: lcmp
      //   545: ifeq -> 565
      //   548: aload_0
      //   549: getfield mRecyclerPool : Landroid/support/v7/widget/RecyclerView$RecycledViewPool;
      //   552: iload #13
      //   554: lload #14
      //   556: lload_3
      //   557: invokevirtual willCreateInTime : (IJJ)Z
      //   560: ifne -> 565
      //   563: aconst_null
      //   564: areturn
      //   565: aload_0
      //   566: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   569: astore #7
      //   571: aload #7
      //   573: getfield mAdapter : Landroid/support/v7/widget/RecyclerView$Adapter;
      //   576: aload #7
      //   578: iload #13
      //   580: invokevirtual createViewHolder : (Landroid/view/ViewGroup;I)Landroid/support/v7/widget/RecyclerView$ViewHolder;
      //   583: astore #8
      //   585: invokestatic access$800 : ()Z
      //   588: ifeq -> 620
      //   591: aload #8
      //   593: getfield itemView : Landroid/view/View;
      //   596: invokestatic findNestedRecyclerView : (Landroid/view/View;)Landroid/support/v7/widget/RecyclerView;
      //   599: astore #7
      //   601: aload #7
      //   603: ifnull -> 620
      //   606: aload #8
      //   608: new java/lang/ref/WeakReference
      //   611: dup
      //   612: aload #7
      //   614: invokespecial <init> : (Ljava/lang/Object;)V
      //   617: putfield mNestedRecyclerView : Ljava/lang/ref/WeakReference;
      //   620: aload_0
      //   621: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   624: invokevirtual getNanoTime : ()J
      //   627: lstore #16
      //   629: aload_0
      //   630: getfield mRecyclerPool : Landroid/support/v7/widget/RecyclerView$RecycledViewPool;
      //   633: iload #13
      //   635: lload #16
      //   637: lload #14
      //   639: lsub
      //   640: invokevirtual factorInCreateTime : (IJ)V
      //   643: goto -> 755
      //   646: new java/lang/StringBuilder
      //   649: dup
      //   650: invokespecial <init> : ()V
      //   653: astore #7
      //   655: aload #7
      //   657: ldc 'Inconsistency detected. Invalid item position '
      //   659: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   662: pop
      //   663: aload #7
      //   665: iload_1
      //   666: invokevirtual append : (I)Ljava/lang/StringBuilder;
      //   669: pop
      //   670: aload #7
      //   672: ldc_w '(offset:'
      //   675: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   678: pop
      //   679: aload #7
      //   681: iload #12
      //   683: invokevirtual append : (I)Ljava/lang/StringBuilder;
      //   686: pop
      //   687: aload #7
      //   689: ldc_w ').'
      //   692: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   695: pop
      //   696: aload #7
      //   698: ldc_w 'state:'
      //   701: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   704: pop
      //   705: aload #7
      //   707: aload_0
      //   708: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   711: getfield mState : Landroid/support/v7/widget/RecyclerView$State;
      //   714: invokevirtual getItemCount : ()I
      //   717: invokevirtual append : (I)Ljava/lang/StringBuilder;
      //   720: pop
      //   721: aload #7
      //   723: aload_0
      //   724: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   727: invokevirtual exceptionLabel : ()Ljava/lang/String;
      //   730: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   733: pop
      //   734: new java/lang/IndexOutOfBoundsException
      //   737: dup
      //   738: aload #7
      //   740: invokevirtual toString : ()Ljava/lang/String;
      //   743: invokespecial <init> : (Ljava/lang/String;)V
      //   746: athrow
      //   747: aload #11
      //   749: astore #8
      //   751: iload #12
      //   753: istore #9
      //   755: iload #9
      //   757: ifeq -> 858
      //   760: aload_0
      //   761: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   764: getfield mState : Landroid/support/v7/widget/RecyclerView$State;
      //   767: invokevirtual isPreLayout : ()Z
      //   770: ifne -> 858
      //   773: aload #8
      //   775: sipush #8192
      //   778: invokevirtual hasAnyOfTheFlags : (I)Z
      //   781: ifeq -> 858
      //   784: aload #8
      //   786: iconst_0
      //   787: sipush #8192
      //   790: invokevirtual setFlags : (II)V
      //   793: aload_0
      //   794: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   797: getfield mState : Landroid/support/v7/widget/RecyclerView$State;
      //   800: getfield mRunSimpleAnimations : Z
      //   803: ifeq -> 858
      //   806: aload #8
      //   808: invokestatic buildAdapterChangeFlagsForAnimations : (Landroid/support/v7/widget/RecyclerView$ViewHolder;)I
      //   811: istore #10
      //   813: aload_0
      //   814: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   817: astore #7
      //   819: aload #7
      //   821: getfield mItemAnimator : Landroid/support/v7/widget/RecyclerView$ItemAnimator;
      //   824: aload #7
      //   826: getfield mState : Landroid/support/v7/widget/RecyclerView$State;
      //   829: aload #8
      //   831: iload #10
      //   833: sipush #4096
      //   836: ior
      //   837: aload #8
      //   839: invokevirtual getUnmodifiedPayloads : ()Ljava/util/List;
      //   842: invokevirtual recordPreLayoutInformation : (Landroid/support/v7/widget/RecyclerView$State;Landroid/support/v7/widget/RecyclerView$ViewHolder;ILjava/util/List;)Landroid/support/v7/widget/RecyclerView$ItemAnimator$ItemHolderInfo;
      //   845: astore #7
      //   847: aload_0
      //   848: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   851: aload #8
      //   853: aload #7
      //   855: invokevirtual recordAnimationInfoIfBouncedHiddenView : (Landroid/support/v7/widget/RecyclerView$ViewHolder;Landroid/support/v7/widget/RecyclerView$ItemAnimator$ItemHolderInfo;)V
      //   858: aload_0
      //   859: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   862: getfield mState : Landroid/support/v7/widget/RecyclerView$State;
      //   865: invokevirtual isPreLayout : ()Z
      //   868: ifeq -> 888
      //   871: aload #8
      //   873: invokevirtual isBound : ()Z
      //   876: ifeq -> 888
      //   879: aload #8
      //   881: iload_1
      //   882: putfield mPreLayoutPosition : I
      //   885: goto -> 915
      //   888: aload #8
      //   890: invokevirtual isBound : ()Z
      //   893: ifeq -> 920
      //   896: aload #8
      //   898: invokevirtual needsUpdate : ()Z
      //   901: ifne -> 920
      //   904: aload #8
      //   906: invokevirtual isInvalid : ()Z
      //   909: ifeq -> 915
      //   912: goto -> 920
      //   915: iconst_0
      //   916: istore_2
      //   917: goto -> 940
      //   920: aload_0
      //   921: aload #8
      //   923: aload_0
      //   924: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   927: getfield mAdapterHelper : Landroid/support/v7/widget/AdapterHelper;
      //   930: iload_1
      //   931: invokevirtual findPositionOffset : (I)I
      //   934: iload_1
      //   935: lload_3
      //   936: invokespecial tryBindViewHolderByDeadline : (Landroid/support/v7/widget/RecyclerView$ViewHolder;IIJ)Z
      //   939: istore_2
      //   940: aload #8
      //   942: getfield itemView : Landroid/view/View;
      //   945: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
      //   948: astore #7
      //   950: aload #7
      //   952: ifnonnull -> 980
      //   955: aload_0
      //   956: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   959: invokevirtual generateDefaultLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
      //   962: checkcast android/support/v7/widget/RecyclerView$LayoutParams
      //   965: astore #7
      //   967: aload #8
      //   969: getfield itemView : Landroid/view/View;
      //   972: aload #7
      //   974: invokevirtual setLayoutParams : (Landroid/view/ViewGroup$LayoutParams;)V
      //   977: goto -> 1026
      //   980: aload_0
      //   981: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   984: aload #7
      //   986: invokevirtual checkLayoutParams : (Landroid/view/ViewGroup$LayoutParams;)Z
      //   989: ifne -> 1019
      //   992: aload_0
      //   993: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   996: aload #7
      //   998: invokevirtual generateLayoutParams : (Landroid/view/ViewGroup$LayoutParams;)Landroid/view/ViewGroup$LayoutParams;
      //   1001: checkcast android/support/v7/widget/RecyclerView$LayoutParams
      //   1004: astore #7
      //   1006: aload #8
      //   1008: getfield itemView : Landroid/view/View;
      //   1011: aload #7
      //   1013: invokevirtual setLayoutParams : (Landroid/view/ViewGroup$LayoutParams;)V
      //   1016: goto -> 1026
      //   1019: aload #7
      //   1021: checkcast android/support/v7/widget/RecyclerView$LayoutParams
      //   1024: astore #7
      //   1026: aload #7
      //   1028: aload #8
      //   1030: putfield mViewHolder : Landroid/support/v7/widget/RecyclerView$ViewHolder;
      //   1033: iload #9
      //   1035: ifeq -> 1048
      //   1038: iload_2
      //   1039: ifeq -> 1048
      //   1042: iload #6
      //   1044: istore_2
      //   1045: goto -> 1050
      //   1048: iconst_0
      //   1049: istore_2
      //   1050: aload #7
      //   1052: iload_2
      //   1053: putfield mPendingInvalidate : Z
      //   1056: aload #8
      //   1058: areturn
      //   1059: new java/lang/StringBuilder
      //   1062: dup
      //   1063: invokespecial <init> : ()V
      //   1066: astore #7
      //   1068: aload #7
      //   1070: ldc_w 'Invalid item position '
      //   1073: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1076: pop
      //   1077: aload #7
      //   1079: iload_1
      //   1080: invokevirtual append : (I)Ljava/lang/StringBuilder;
      //   1083: pop
      //   1084: aload #7
      //   1086: ldc_w '('
      //   1089: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1092: pop
      //   1093: aload #7
      //   1095: iload_1
      //   1096: invokevirtual append : (I)Ljava/lang/StringBuilder;
      //   1099: pop
      //   1100: aload #7
      //   1102: ldc_w '). Item count:'
      //   1105: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1108: pop
      //   1109: aload #7
      //   1111: aload_0
      //   1112: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   1115: getfield mState : Landroid/support/v7/widget/RecyclerView$State;
      //   1118: invokevirtual getItemCount : ()I
      //   1121: invokevirtual append : (I)Ljava/lang/StringBuilder;
      //   1124: pop
      //   1125: aload #7
      //   1127: aload_0
      //   1128: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   1131: invokevirtual exceptionLabel : ()Ljava/lang/String;
      //   1134: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1137: pop
      //   1138: new java/lang/IndexOutOfBoundsException
      //   1141: dup
      //   1142: aload #7
      //   1144: invokevirtual toString : ()Ljava/lang/String;
      //   1147: invokespecial <init> : (Ljava/lang/String;)V
      //   1150: athrow
    }
    
    void unscrapView(RecyclerView.ViewHolder param1ViewHolder) {
      if (param1ViewHolder.mInChangeScrap) {
        this.mChangedScrap.remove(param1ViewHolder);
      } else {
        this.mAttachedScrap.remove(param1ViewHolder);
      } 
      RecyclerView.ViewHolder.access$1002(param1ViewHolder, null);
      RecyclerView.ViewHolder.access$1102(param1ViewHolder, false);
      param1ViewHolder.clearReturnedFromScrapFlag();
    }
    
    void updateViewCacheSize() {
      RecyclerView.LayoutManager layoutManager = RecyclerView.this.mLayout;
      if (layoutManager != null) {
        i = layoutManager.mPrefetchMaxCountObserved;
      } else {
        i = 0;
      } 
      this.mViewCacheMax = this.mRequestedCacheMax + i;
      for (int i = this.mCachedViews.size() - 1; i >= 0 && this.mCachedViews.size() > this.mViewCacheMax; i--)
        recycleCachedViewAt(i); 
    }
    
    boolean validateViewHolderForOffsetPosition(RecyclerView.ViewHolder param1ViewHolder) {
      if (param1ViewHolder.isRemoved())
        return RecyclerView.this.mState.isPreLayout(); 
      int i = param1ViewHolder.mPosition;
      if (i >= 0 && i < RecyclerView.this.mAdapter.getItemCount()) {
        boolean bool = RecyclerView.this.mState.isPreLayout();
        boolean bool1 = false;
        if (!bool && RecyclerView.this.mAdapter.getItemViewType(param1ViewHolder.mPosition) != param1ViewHolder.getItemViewType())
          return false; 
        if (RecyclerView.this.mAdapter.hasStableIds()) {
          if (param1ViewHolder.getItemId() == RecyclerView.this.mAdapter.getItemId(param1ViewHolder.mPosition))
            bool1 = true; 
          return bool1;
        } 
        return true;
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Inconsistency detected. Invalid view holder adapter position");
      stringBuilder.append(param1ViewHolder);
      stringBuilder.append(RecyclerView.this.exceptionLabel());
      throw new IndexOutOfBoundsException(stringBuilder.toString());
    }
    
    void viewRangeUpdate(int param1Int1, int param1Int2) {
      for (int i = this.mCachedViews.size() - 1; i >= 0; i--) {
        RecyclerView.ViewHolder viewHolder = this.mCachedViews.get(i);
        if (viewHolder != null) {
          int j = viewHolder.mPosition;
          if (j >= param1Int1 && j < param1Int2 + param1Int1) {
            viewHolder.addFlags(2);
            recycleCachedViewAt(i);
          } 
        } 
      } 
    }
  }
  
  public static interface RecyclerListener {
    void onViewRecycled(RecyclerView.ViewHolder param1ViewHolder);
  }
  
  private class RecyclerViewDataObserver extends AdapterDataObserver {
    public void onChanged() {
      RecyclerView.this.assertNotInLayoutOrScroll(null);
      RecyclerView recyclerView = RecyclerView.this;
      recyclerView.mState.mStructureChanged = true;
      recyclerView.setDataSetChangedAfterLayout();
      if (!RecyclerView.this.mAdapterHelper.hasPendingUpdates())
        RecyclerView.this.requestLayout(); 
    }
    
    public void onItemRangeChanged(int param1Int1, int param1Int2, Object param1Object) {
      RecyclerView.this.assertNotInLayoutOrScroll(null);
      if (RecyclerView.this.mAdapterHelper.onItemRangeChanged(param1Int1, param1Int2, param1Object))
        triggerUpdateProcessor(); 
    }
    
    public void onItemRangeInserted(int param1Int1, int param1Int2) {
      RecyclerView.this.assertNotInLayoutOrScroll(null);
      if (RecyclerView.this.mAdapterHelper.onItemRangeInserted(param1Int1, param1Int2))
        triggerUpdateProcessor(); 
    }
    
    public void onItemRangeMoved(int param1Int1, int param1Int2, int param1Int3) {
      RecyclerView.this.assertNotInLayoutOrScroll(null);
      if (RecyclerView.this.mAdapterHelper.onItemRangeMoved(param1Int1, param1Int2, param1Int3))
        triggerUpdateProcessor(); 
    }
    
    public void onItemRangeRemoved(int param1Int1, int param1Int2) {
      RecyclerView.this.assertNotInLayoutOrScroll(null);
      if (RecyclerView.this.mAdapterHelper.onItemRangeRemoved(param1Int1, param1Int2))
        triggerUpdateProcessor(); 
    }
    
    void triggerUpdateProcessor() {
      if (RecyclerView.POST_UPDATES_ON_ANIMATION) {
        RecyclerView recyclerView1 = RecyclerView.this;
        if (recyclerView1.mHasFixedSize && recyclerView1.mIsAttached) {
          ViewCompat.postOnAnimation((View)recyclerView1, recyclerView1.mUpdateChildViewsRunnable);
          return;
        } 
      } 
      RecyclerView recyclerView = RecyclerView.this;
      recyclerView.mAdapterUpdateDuringMeasure = true;
      recyclerView.requestLayout();
    }
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static class SavedState extends AbsSavedState {
    public static final Parcelable.Creator<SavedState> CREATOR = (Parcelable.Creator<SavedState>)new Parcelable.ClassLoaderCreator<SavedState>() {
        public RecyclerView.SavedState createFromParcel(Parcel param2Parcel) {
          return new RecyclerView.SavedState(param2Parcel, null);
        }
        
        public RecyclerView.SavedState createFromParcel(Parcel param2Parcel, ClassLoader param2ClassLoader) {
          return new RecyclerView.SavedState(param2Parcel, param2ClassLoader);
        }
        
        public RecyclerView.SavedState[] newArray(int param2Int) {
          return new RecyclerView.SavedState[param2Int];
        }
      };
    
    Parcelable mLayoutState;
    
    SavedState(Parcel param1Parcel, ClassLoader param1ClassLoader) {
      super(param1Parcel, param1ClassLoader);
      if (param1ClassLoader == null)
        param1ClassLoader = RecyclerView.LayoutManager.class.getClassLoader(); 
      this.mLayoutState = param1Parcel.readParcelable(param1ClassLoader);
    }
    
    SavedState(Parcelable param1Parcelable) {
      super(param1Parcelable);
    }
    
    void copyFrom(SavedState param1SavedState) {
      this.mLayoutState = param1SavedState.mLayoutState;
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      super.writeToParcel(param1Parcel, param1Int);
      param1Parcel.writeParcelable(this.mLayoutState, 0);
    }
  }
  
  static final class null implements Parcelable.ClassLoaderCreator<SavedState> {
    public RecyclerView.SavedState createFromParcel(Parcel param1Parcel) {
      return new RecyclerView.SavedState(param1Parcel, null);
    }
    
    public RecyclerView.SavedState createFromParcel(Parcel param1Parcel, ClassLoader param1ClassLoader) {
      return new RecyclerView.SavedState(param1Parcel, param1ClassLoader);
    }
    
    public RecyclerView.SavedState[] newArray(int param1Int) {
      return new RecyclerView.SavedState[param1Int];
    }
  }
  
  public static class SimpleOnItemTouchListener implements OnItemTouchListener {
    public boolean onInterceptTouchEvent(RecyclerView param1RecyclerView, MotionEvent param1MotionEvent) {
      return false;
    }
    
    public void onRequestDisallowInterceptTouchEvent(boolean param1Boolean) {}
    
    public void onTouchEvent(RecyclerView param1RecyclerView, MotionEvent param1MotionEvent) {}
  }
  
  public static abstract class SmoothScroller {
    private RecyclerView.LayoutManager mLayoutManager;
    
    private boolean mPendingInitialRun;
    
    private RecyclerView mRecyclerView;
    
    private final Action mRecyclingAction = new Action(0, 0);
    
    private boolean mRunning;
    
    private int mTargetPosition = -1;
    
    private View mTargetView;
    
    private void onAnimation(int param1Int1, int param1Int2) {
      RecyclerView recyclerView = this.mRecyclerView;
      if (!this.mRunning || this.mTargetPosition == -1 || recyclerView == null)
        stop(); 
      this.mPendingInitialRun = false;
      View view = this.mTargetView;
      if (view != null)
        if (getChildPosition(view) == this.mTargetPosition) {
          onTargetFound(this.mTargetView, recyclerView.mState, this.mRecyclingAction);
          this.mRecyclingAction.runIfNecessary(recyclerView);
          stop();
        } else {
          Log.e("RecyclerView", "Passed over target position while smooth scrolling.");
          this.mTargetView = null;
        }  
      if (this.mRunning) {
        onSeekTargetStep(param1Int1, param1Int2, recyclerView.mState, this.mRecyclingAction);
        boolean bool = this.mRecyclingAction.hasJumpTarget();
        this.mRecyclingAction.runIfNecessary(recyclerView);
        if (bool)
          if (this.mRunning) {
            this.mPendingInitialRun = true;
            recyclerView.mViewFlinger.postOnAnimation();
          } else {
            stop();
          }  
      } 
    }
    
    public View findViewByPosition(int param1Int) {
      return this.mRecyclerView.mLayout.findViewByPosition(param1Int);
    }
    
    public int getChildCount() {
      return this.mRecyclerView.mLayout.getChildCount();
    }
    
    public int getChildPosition(View param1View) {
      return this.mRecyclerView.getChildLayoutPosition(param1View);
    }
    
    @Nullable
    public RecyclerView.LayoutManager getLayoutManager() {
      return this.mLayoutManager;
    }
    
    public int getTargetPosition() {
      return this.mTargetPosition;
    }
    
    @Deprecated
    public void instantScrollToPosition(int param1Int) {
      this.mRecyclerView.scrollToPosition(param1Int);
    }
    
    public boolean isPendingInitialRun() {
      return this.mPendingInitialRun;
    }
    
    public boolean isRunning() {
      return this.mRunning;
    }
    
    protected void normalize(PointF param1PointF) {
      float f1 = param1PointF.x;
      float f2 = param1PointF.y;
      f2 = (float)Math.sqrt((f1 * f1 + f2 * f2));
      param1PointF.x /= f2;
      param1PointF.y /= f2;
    }
    
    protected void onChildAttachedToWindow(View param1View) {
      if (getChildPosition(param1View) == getTargetPosition())
        this.mTargetView = param1View; 
    }
    
    protected abstract void onSeekTargetStep(int param1Int1, int param1Int2, RecyclerView.State param1State, Action param1Action);
    
    protected abstract void onStart();
    
    protected abstract void onStop();
    
    protected abstract void onTargetFound(View param1View, RecyclerView.State param1State, Action param1Action);
    
    public void setTargetPosition(int param1Int) {
      this.mTargetPosition = param1Int;
    }
    
    void start(RecyclerView param1RecyclerView, RecyclerView.LayoutManager param1LayoutManager) {
      this.mRecyclerView = param1RecyclerView;
      this.mLayoutManager = param1LayoutManager;
      int i = this.mTargetPosition;
      if (i != -1) {
        RecyclerView.State.access$1302(param1RecyclerView.mState, i);
        this.mRunning = true;
        this.mPendingInitialRun = true;
        this.mTargetView = findViewByPosition(getTargetPosition());
        onStart();
        this.mRecyclerView.mViewFlinger.postOnAnimation();
        return;
      } 
      throw new IllegalArgumentException("Invalid target position");
    }
    
    protected final void stop() {
      if (!this.mRunning)
        return; 
      onStop();
      RecyclerView.State.access$1302(this.mRecyclerView.mState, -1);
      this.mTargetView = null;
      this.mTargetPosition = -1;
      this.mPendingInitialRun = false;
      this.mRunning = false;
      this.mLayoutManager.onSmoothScrollerStopped(this);
      this.mLayoutManager = null;
      this.mRecyclerView = null;
    }
    
    public static class Action {
      public static final int UNDEFINED_DURATION = -2147483648;
      
      private boolean mChanged = false;
      
      private int mConsecutiveUpdates = 0;
      
      private int mDuration;
      
      private int mDx;
      
      private int mDy;
      
      private Interpolator mInterpolator;
      
      private int mJumpToPosition = -1;
      
      public Action(int param2Int1, int param2Int2) {
        this(param2Int1, param2Int2, -2147483648, null);
      }
      
      public Action(int param2Int1, int param2Int2, int param2Int3) {
        this(param2Int1, param2Int2, param2Int3, null);
      }
      
      public Action(int param2Int1, int param2Int2, int param2Int3, Interpolator param2Interpolator) {
        this.mDx = param2Int1;
        this.mDy = param2Int2;
        this.mDuration = param2Int3;
        this.mInterpolator = param2Interpolator;
      }
      
      private void validate() {
        if (this.mInterpolator == null || this.mDuration >= 1) {
          if (this.mDuration >= 1)
            return; 
          throw new IllegalStateException("Scroll duration must be a positive number");
        } 
        throw new IllegalStateException("If you provide an interpolator, you must set a positive duration");
      }
      
      public int getDuration() {
        return this.mDuration;
      }
      
      public int getDx() {
        return this.mDx;
      }
      
      public int getDy() {
        return this.mDy;
      }
      
      public Interpolator getInterpolator() {
        return this.mInterpolator;
      }
      
      boolean hasJumpTarget() {
        boolean bool;
        if (this.mJumpToPosition >= 0) {
          bool = true;
        } else {
          bool = false;
        } 
        return bool;
      }
      
      public void jumpTo(int param2Int) {
        this.mJumpToPosition = param2Int;
      }
      
      void runIfNecessary(RecyclerView param2RecyclerView) {
        int i = this.mJumpToPosition;
        if (i >= 0) {
          this.mJumpToPosition = -1;
          param2RecyclerView.jumpToPositionForSmoothScroller(i);
          this.mChanged = false;
          return;
        } 
        if (this.mChanged) {
          validate();
          Interpolator interpolator = this.mInterpolator;
          if (interpolator == null) {
            i = this.mDuration;
            if (i == Integer.MIN_VALUE) {
              param2RecyclerView.mViewFlinger.smoothScrollBy(this.mDx, this.mDy);
            } else {
              param2RecyclerView.mViewFlinger.smoothScrollBy(this.mDx, this.mDy, i);
            } 
          } else {
            param2RecyclerView.mViewFlinger.smoothScrollBy(this.mDx, this.mDy, this.mDuration, interpolator);
          } 
          i = this.mConsecutiveUpdates + 1;
          this.mConsecutiveUpdates = i;
          if (i > 10)
            Log.e("RecyclerView", "Smooth Scroll action is being updated too frequently. Make sure you are not changing it unless necessary"); 
          this.mChanged = false;
        } else {
          this.mConsecutiveUpdates = 0;
        } 
      }
      
      public void setDuration(int param2Int) {
        this.mChanged = true;
        this.mDuration = param2Int;
      }
      
      public void setDx(int param2Int) {
        this.mChanged = true;
        this.mDx = param2Int;
      }
      
      public void setDy(int param2Int) {
        this.mChanged = true;
        this.mDy = param2Int;
      }
      
      public void setInterpolator(Interpolator param2Interpolator) {
        this.mChanged = true;
        this.mInterpolator = param2Interpolator;
      }
      
      public void update(int param2Int1, int param2Int2, int param2Int3, Interpolator param2Interpolator) {
        this.mDx = param2Int1;
        this.mDy = param2Int2;
        this.mDuration = param2Int3;
        this.mInterpolator = param2Interpolator;
        this.mChanged = true;
      }
    }
    
    public static interface ScrollVectorProvider {
      PointF computeScrollVectorForPosition(int param2Int);
    }
  }
  
  public static class Action {
    public static final int UNDEFINED_DURATION = -2147483648;
    
    private boolean mChanged = false;
    
    private int mConsecutiveUpdates = 0;
    
    private int mDuration;
    
    private int mDx;
    
    private int mDy;
    
    private Interpolator mInterpolator;
    
    private int mJumpToPosition = -1;
    
    public Action(int param1Int1, int param1Int2) {
      this(param1Int1, param1Int2, -2147483648, null);
    }
    
    public Action(int param1Int1, int param1Int2, int param1Int3) {
      this(param1Int1, param1Int2, param1Int3, null);
    }
    
    public Action(int param1Int1, int param1Int2, int param1Int3, Interpolator param1Interpolator) {
      this.mDx = param1Int1;
      this.mDy = param1Int2;
      this.mDuration = param1Int3;
      this.mInterpolator = param1Interpolator;
    }
    
    private void validate() {
      if (this.mInterpolator == null || this.mDuration >= 1) {
        if (this.mDuration >= 1)
          return; 
        throw new IllegalStateException("Scroll duration must be a positive number");
      } 
      throw new IllegalStateException("If you provide an interpolator, you must set a positive duration");
    }
    
    public int getDuration() {
      return this.mDuration;
    }
    
    public int getDx() {
      return this.mDx;
    }
    
    public int getDy() {
      return this.mDy;
    }
    
    public Interpolator getInterpolator() {
      return this.mInterpolator;
    }
    
    boolean hasJumpTarget() {
      boolean bool;
      if (this.mJumpToPosition >= 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public void jumpTo(int param1Int) {
      this.mJumpToPosition = param1Int;
    }
    
    void runIfNecessary(RecyclerView param1RecyclerView) {
      int i = this.mJumpToPosition;
      if (i >= 0) {
        this.mJumpToPosition = -1;
        param1RecyclerView.jumpToPositionForSmoothScroller(i);
        this.mChanged = false;
        return;
      } 
      if (this.mChanged) {
        validate();
        Interpolator interpolator = this.mInterpolator;
        if (interpolator == null) {
          i = this.mDuration;
          if (i == Integer.MIN_VALUE) {
            param1RecyclerView.mViewFlinger.smoothScrollBy(this.mDx, this.mDy);
          } else {
            param1RecyclerView.mViewFlinger.smoothScrollBy(this.mDx, this.mDy, i);
          } 
        } else {
          param1RecyclerView.mViewFlinger.smoothScrollBy(this.mDx, this.mDy, this.mDuration, interpolator);
        } 
        i = this.mConsecutiveUpdates + 1;
        this.mConsecutiveUpdates = i;
        if (i > 10)
          Log.e("RecyclerView", "Smooth Scroll action is being updated too frequently. Make sure you are not changing it unless necessary"); 
        this.mChanged = false;
      } else {
        this.mConsecutiveUpdates = 0;
      } 
    }
    
    public void setDuration(int param1Int) {
      this.mChanged = true;
      this.mDuration = param1Int;
    }
    
    public void setDx(int param1Int) {
      this.mChanged = true;
      this.mDx = param1Int;
    }
    
    public void setDy(int param1Int) {
      this.mChanged = true;
      this.mDy = param1Int;
    }
    
    public void setInterpolator(Interpolator param1Interpolator) {
      this.mChanged = true;
      this.mInterpolator = param1Interpolator;
    }
    
    public void update(int param1Int1, int param1Int2, int param1Int3, Interpolator param1Interpolator) {
      this.mDx = param1Int1;
      this.mDy = param1Int2;
      this.mDuration = param1Int3;
      this.mInterpolator = param1Interpolator;
      this.mChanged = true;
    }
  }
  
  public static interface ScrollVectorProvider {
    PointF computeScrollVectorForPosition(int param1Int);
  }
  
  public static class State {
    static final int STEP_ANIMATIONS = 4;
    
    static final int STEP_LAYOUT = 2;
    
    static final int STEP_START = 1;
    
    private SparseArray<Object> mData;
    
    int mDeletedInvisibleItemCountSincePreviousLayout = 0;
    
    long mFocusedItemId;
    
    int mFocusedItemPosition;
    
    int mFocusedSubChildId;
    
    boolean mInPreLayout = false;
    
    boolean mIsMeasuring = false;
    
    int mItemCount = 0;
    
    int mLayoutStep = 1;
    
    int mPreviousLayoutItemCount = 0;
    
    int mRemainingScrollHorizontal;
    
    int mRemainingScrollVertical;
    
    boolean mRunPredictiveAnimations = false;
    
    boolean mRunSimpleAnimations = false;
    
    boolean mStructureChanged = false;
    
    private int mTargetPosition = -1;
    
    boolean mTrackOldChangeHolders = false;
    
    void assertLayoutStep(int param1Int) {
      if ((this.mLayoutStep & param1Int) != 0)
        return; 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Layout state should be one of ");
      stringBuilder.append(Integer.toBinaryString(param1Int));
      stringBuilder.append(" but it is ");
      stringBuilder.append(Integer.toBinaryString(this.mLayoutStep));
      throw new IllegalStateException(stringBuilder.toString());
    }
    
    public boolean didStructureChange() {
      return this.mStructureChanged;
    }
    
    public <T> T get(int param1Int) {
      SparseArray<Object> sparseArray = this.mData;
      return (T)((sparseArray == null) ? null : sparseArray.get(param1Int));
    }
    
    public int getItemCount() {
      int i;
      if (this.mInPreLayout) {
        i = this.mPreviousLayoutItemCount - this.mDeletedInvisibleItemCountSincePreviousLayout;
      } else {
        i = this.mItemCount;
      } 
      return i;
    }
    
    public int getRemainingScrollHorizontal() {
      return this.mRemainingScrollHorizontal;
    }
    
    public int getRemainingScrollVertical() {
      return this.mRemainingScrollVertical;
    }
    
    public int getTargetScrollPosition() {
      return this.mTargetPosition;
    }
    
    public boolean hasTargetScrollPosition() {
      boolean bool;
      if (this.mTargetPosition != -1) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean isMeasuring() {
      return this.mIsMeasuring;
    }
    
    public boolean isPreLayout() {
      return this.mInPreLayout;
    }
    
    void prepareForNestedPrefetch(RecyclerView.Adapter param1Adapter) {
      this.mLayoutStep = 1;
      this.mItemCount = param1Adapter.getItemCount();
      this.mInPreLayout = false;
      this.mTrackOldChangeHolders = false;
      this.mIsMeasuring = false;
    }
    
    public void put(int param1Int, Object param1Object) {
      if (this.mData == null)
        this.mData = new SparseArray(); 
      this.mData.put(param1Int, param1Object);
    }
    
    public void remove(int param1Int) {
      SparseArray<Object> sparseArray = this.mData;
      if (sparseArray == null)
        return; 
      sparseArray.remove(param1Int);
    }
    
    State reset() {
      this.mTargetPosition = -1;
      SparseArray<Object> sparseArray = this.mData;
      if (sparseArray != null)
        sparseArray.clear(); 
      this.mItemCount = 0;
      this.mStructureChanged = false;
      this.mIsMeasuring = false;
      return this;
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("State{mTargetPosition=");
      stringBuilder.append(this.mTargetPosition);
      stringBuilder.append(", mData=");
      stringBuilder.append(this.mData);
      stringBuilder.append(", mItemCount=");
      stringBuilder.append(this.mItemCount);
      stringBuilder.append(", mPreviousLayoutItemCount=");
      stringBuilder.append(this.mPreviousLayoutItemCount);
      stringBuilder.append(", mDeletedInvisibleItemCountSincePreviousLayout=");
      stringBuilder.append(this.mDeletedInvisibleItemCountSincePreviousLayout);
      stringBuilder.append(", mStructureChanged=");
      stringBuilder.append(this.mStructureChanged);
      stringBuilder.append(", mInPreLayout=");
      stringBuilder.append(this.mInPreLayout);
      stringBuilder.append(", mRunSimpleAnimations=");
      stringBuilder.append(this.mRunSimpleAnimations);
      stringBuilder.append(", mRunPredictiveAnimations=");
      stringBuilder.append(this.mRunPredictiveAnimations);
      stringBuilder.append('}');
      return stringBuilder.toString();
    }
    
    public boolean willRunPredictiveAnimations() {
      return this.mRunPredictiveAnimations;
    }
    
    public boolean willRunSimpleAnimations() {
      return this.mRunSimpleAnimations;
    }
    
    @Retention(RetentionPolicy.SOURCE)
    static @interface LayoutState {}
  }
  
  @Retention(RetentionPolicy.SOURCE)
  static @interface LayoutState {}
  
  public static abstract class ViewCacheExtension {
    public abstract View getViewForPositionAndType(RecyclerView.Recycler param1Recycler, int param1Int1, int param1Int2);
  }
  
  class ViewFlinger implements Runnable {
    private boolean mEatRunOnAnimationRequest;
    
    Interpolator mInterpolator;
    
    private int mLastFlingX;
    
    private int mLastFlingY;
    
    private boolean mReSchedulePostAnimationCallback;
    
    private OverScroller mScroller;
    
    ViewFlinger() {
      Interpolator interpolator = RecyclerView.sQuinticInterpolator;
      this.mInterpolator = interpolator;
      this.mEatRunOnAnimationRequest = false;
      this.mReSchedulePostAnimationCallback = false;
      this.mScroller = new OverScroller(RecyclerView.this.getContext(), interpolator);
    }
    
    private int computeScrollDuration(int param1Int1, int param1Int2, int param1Int3, int param1Int4) {
      boolean bool;
      int i = Math.abs(param1Int1);
      int j = Math.abs(param1Int2);
      if (i > j) {
        bool = true;
      } else {
        bool = false;
      } 
      param1Int3 = (int)Math.sqrt((param1Int3 * param1Int3 + param1Int4 * param1Int4));
      param1Int2 = (int)Math.sqrt((param1Int1 * param1Int1 + param1Int2 * param1Int2));
      RecyclerView recyclerView = RecyclerView.this;
      if (bool) {
        param1Int1 = recyclerView.getWidth();
      } else {
        param1Int1 = recyclerView.getHeight();
      } 
      param1Int4 = param1Int1 / 2;
      float f1 = param1Int2;
      float f2 = param1Int1;
      float f3 = Math.min(1.0F, f1 * 1.0F / f2);
      f1 = param1Int4;
      f3 = distanceInfluenceForSnapDuration(f3);
      if (param1Int3 > 0) {
        param1Int1 = Math.round(Math.abs((f1 + f3 * f1) / param1Int3) * 1000.0F) * 4;
      } else {
        if (bool) {
          param1Int1 = i;
        } else {
          param1Int1 = j;
        } 
        param1Int1 = (int)((param1Int1 / f2 + 1.0F) * 300.0F);
      } 
      return Math.min(param1Int1, 2000);
    }
    
    private void disableRunOnAnimationRequests() {
      this.mReSchedulePostAnimationCallback = false;
      this.mEatRunOnAnimationRequest = true;
    }
    
    private float distanceInfluenceForSnapDuration(float param1Float) {
      return (float)Math.sin(((param1Float - 0.5F) * 0.47123894F));
    }
    
    private void enableRunOnAnimationRequests() {
      this.mEatRunOnAnimationRequest = false;
      if (this.mReSchedulePostAnimationCallback)
        postOnAnimation(); 
    }
    
    public void fling(int param1Int1, int param1Int2) {
      RecyclerView.this.setScrollState(2);
      this.mLastFlingY = 0;
      this.mLastFlingX = 0;
      this.mScroller.fling(0, 0, param1Int1, param1Int2, -2147483648, 2147483647, -2147483648, 2147483647);
      postOnAnimation();
    }
    
    void postOnAnimation() {
      if (this.mEatRunOnAnimationRequest) {
        this.mReSchedulePostAnimationCallback = true;
      } else {
        RecyclerView.this.removeCallbacks(this);
        ViewCompat.postOnAnimation((View)RecyclerView.this, this);
      } 
    }
    
    public void run() {
      // Byte code:
      //   0: aload_0
      //   1: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   4: getfield mLayout : Landroid/support/v7/widget/RecyclerView$LayoutManager;
      //   7: ifnonnull -> 15
      //   10: aload_0
      //   11: invokevirtual stop : ()V
      //   14: return
      //   15: aload_0
      //   16: invokespecial disableRunOnAnimationRequests : ()V
      //   19: aload_0
      //   20: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   23: invokevirtual consumePendingUpdateOperations : ()V
      //   26: aload_0
      //   27: getfield mScroller : Landroid/widget/OverScroller;
      //   30: astore_1
      //   31: aload_0
      //   32: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   35: getfield mLayout : Landroid/support/v7/widget/RecyclerView$LayoutManager;
      //   38: getfield mSmoothScroller : Landroid/support/v7/widget/RecyclerView$SmoothScroller;
      //   41: astore_2
      //   42: aload_1
      //   43: invokevirtual computeScrollOffset : ()Z
      //   46: ifeq -> 932
      //   49: aload_0
      //   50: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   53: invokestatic access$500 : (Landroid/support/v7/widget/RecyclerView;)[I
      //   56: astore_3
      //   57: aload_1
      //   58: invokevirtual getCurrX : ()I
      //   61: istore #4
      //   63: aload_1
      //   64: invokevirtual getCurrY : ()I
      //   67: istore #5
      //   69: iload #4
      //   71: aload_0
      //   72: getfield mLastFlingX : I
      //   75: isub
      //   76: istore #6
      //   78: iload #5
      //   80: aload_0
      //   81: getfield mLastFlingY : I
      //   84: isub
      //   85: istore #7
      //   87: aload_0
      //   88: iload #4
      //   90: putfield mLastFlingX : I
      //   93: aload_0
      //   94: iload #5
      //   96: putfield mLastFlingY : I
      //   99: iload #6
      //   101: istore #8
      //   103: iload #7
      //   105: istore #9
      //   107: aload_0
      //   108: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   111: iload #6
      //   113: iload #7
      //   115: aload_3
      //   116: aconst_null
      //   117: iconst_1
      //   118: invokevirtual dispatchNestedPreScroll : (II[I[II)Z
      //   121: ifeq -> 140
      //   124: iload #6
      //   126: aload_3
      //   127: iconst_0
      //   128: iaload
      //   129: isub
      //   130: istore #8
      //   132: iload #7
      //   134: aload_3
      //   135: iconst_1
      //   136: iaload
      //   137: isub
      //   138: istore #9
      //   140: aload_0
      //   141: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   144: astore_3
      //   145: aload_3
      //   146: getfield mAdapter : Landroid/support/v7/widget/RecyclerView$Adapter;
      //   149: ifnull -> 485
      //   152: aload_3
      //   153: invokevirtual eatRequestLayout : ()V
      //   156: aload_0
      //   157: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   160: invokevirtual onEnterLayoutOrScroll : ()V
      //   163: ldc 'RV Scroll'
      //   165: invokestatic beginSection : (Ljava/lang/String;)V
      //   168: aload_0
      //   169: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   172: astore_3
      //   173: aload_3
      //   174: aload_3
      //   175: getfield mState : Landroid/support/v7/widget/RecyclerView$State;
      //   178: invokevirtual fillRemainingScrollValues : (Landroid/support/v7/widget/RecyclerView$State;)V
      //   181: iload #8
      //   183: ifeq -> 220
      //   186: aload_0
      //   187: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   190: astore_3
      //   191: aload_3
      //   192: getfield mLayout : Landroid/support/v7/widget/RecyclerView$LayoutManager;
      //   195: iload #8
      //   197: aload_3
      //   198: getfield mRecycler : Landroid/support/v7/widget/RecyclerView$Recycler;
      //   201: aload_3
      //   202: getfield mState : Landroid/support/v7/widget/RecyclerView$State;
      //   205: invokevirtual scrollHorizontallyBy : (ILandroid/support/v7/widget/RecyclerView$Recycler;Landroid/support/v7/widget/RecyclerView$State;)I
      //   208: istore #6
      //   210: iload #8
      //   212: iload #6
      //   214: isub
      //   215: istore #7
      //   217: goto -> 226
      //   220: iconst_0
      //   221: istore #6
      //   223: iconst_0
      //   224: istore #7
      //   226: iload #9
      //   228: ifeq -> 265
      //   231: aload_0
      //   232: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   235: astore_3
      //   236: aload_3
      //   237: getfield mLayout : Landroid/support/v7/widget/RecyclerView$LayoutManager;
      //   240: iload #9
      //   242: aload_3
      //   243: getfield mRecycler : Landroid/support/v7/widget/RecyclerView$Recycler;
      //   246: aload_3
      //   247: getfield mState : Landroid/support/v7/widget/RecyclerView$State;
      //   250: invokevirtual scrollVerticallyBy : (ILandroid/support/v7/widget/RecyclerView$Recycler;Landroid/support/v7/widget/RecyclerView$State;)I
      //   253: istore #10
      //   255: iload #9
      //   257: iload #10
      //   259: isub
      //   260: istore #11
      //   262: goto -> 271
      //   265: iconst_0
      //   266: istore #10
      //   268: iconst_0
      //   269: istore #11
      //   271: invokestatic endSection : ()V
      //   274: aload_0
      //   275: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   278: invokevirtual repositionShadowingViews : ()V
      //   281: aload_0
      //   282: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   285: invokevirtual onExitLayoutOrScroll : ()V
      //   288: aload_0
      //   289: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   292: iconst_0
      //   293: invokevirtual resumeRequestLayout : (Z)V
      //   296: iload #6
      //   298: istore #12
      //   300: iload #7
      //   302: istore #13
      //   304: iload #10
      //   306: istore #14
      //   308: iload #11
      //   310: istore #15
      //   312: aload_2
      //   313: ifnull -> 497
      //   316: iload #6
      //   318: istore #12
      //   320: iload #7
      //   322: istore #13
      //   324: iload #10
      //   326: istore #14
      //   328: iload #11
      //   330: istore #15
      //   332: aload_2
      //   333: invokevirtual isPendingInitialRun : ()Z
      //   336: ifne -> 497
      //   339: iload #6
      //   341: istore #12
      //   343: iload #7
      //   345: istore #13
      //   347: iload #10
      //   349: istore #14
      //   351: iload #11
      //   353: istore #15
      //   355: aload_2
      //   356: invokevirtual isRunning : ()Z
      //   359: ifeq -> 497
      //   362: aload_0
      //   363: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   366: getfield mState : Landroid/support/v7/widget/RecyclerView$State;
      //   369: invokevirtual getItemCount : ()I
      //   372: istore #12
      //   374: iload #12
      //   376: ifne -> 402
      //   379: aload_2
      //   380: invokevirtual stop : ()V
      //   383: iload #6
      //   385: istore #12
      //   387: iload #7
      //   389: istore #13
      //   391: iload #10
      //   393: istore #14
      //   395: iload #11
      //   397: istore #15
      //   399: goto -> 497
      //   402: aload_2
      //   403: invokevirtual getTargetPosition : ()I
      //   406: iload #12
      //   408: if_icmplt -> 452
      //   411: aload_2
      //   412: iload #12
      //   414: iconst_1
      //   415: isub
      //   416: invokevirtual setTargetPosition : (I)V
      //   419: aload_2
      //   420: iload #8
      //   422: iload #7
      //   424: isub
      //   425: iload #9
      //   427: iload #11
      //   429: isub
      //   430: invokestatic access$600 : (Landroid/support/v7/widget/RecyclerView$SmoothScroller;II)V
      //   433: iload #6
      //   435: istore #12
      //   437: iload #7
      //   439: istore #13
      //   441: iload #10
      //   443: istore #14
      //   445: iload #11
      //   447: istore #15
      //   449: goto -> 497
      //   452: aload_2
      //   453: iload #8
      //   455: iload #7
      //   457: isub
      //   458: iload #9
      //   460: iload #11
      //   462: isub
      //   463: invokestatic access$600 : (Landroid/support/v7/widget/RecyclerView$SmoothScroller;II)V
      //   466: iload #6
      //   468: istore #12
      //   470: iload #7
      //   472: istore #13
      //   474: iload #10
      //   476: istore #14
      //   478: iload #11
      //   480: istore #15
      //   482: goto -> 497
      //   485: iconst_0
      //   486: istore #12
      //   488: iconst_0
      //   489: istore #13
      //   491: iconst_0
      //   492: istore #14
      //   494: iconst_0
      //   495: istore #15
      //   497: aload_0
      //   498: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   501: getfield mItemDecorations : Ljava/util/ArrayList;
      //   504: invokevirtual isEmpty : ()Z
      //   507: ifne -> 517
      //   510: aload_0
      //   511: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   514: invokevirtual invalidate : ()V
      //   517: aload_0
      //   518: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   521: invokevirtual getOverScrollMode : ()I
      //   524: iconst_2
      //   525: if_icmpeq -> 539
      //   528: aload_0
      //   529: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   532: iload #8
      //   534: iload #9
      //   536: invokevirtual considerReleasingGlowsOnScroll : (II)V
      //   539: aload_0
      //   540: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   543: iload #12
      //   545: iload #14
      //   547: iload #13
      //   549: iload #15
      //   551: aconst_null
      //   552: iconst_1
      //   553: invokevirtual dispatchNestedScroll : (IIII[II)Z
      //   556: ifne -> 706
      //   559: iload #13
      //   561: ifne -> 569
      //   564: iload #15
      //   566: ifeq -> 706
      //   569: aload_1
      //   570: invokevirtual getCurrVelocity : ()F
      //   573: f2i
      //   574: istore #6
      //   576: iload #13
      //   578: iload #4
      //   580: if_icmpeq -> 608
      //   583: iload #13
      //   585: ifge -> 596
      //   588: iload #6
      //   590: ineg
      //   591: istore #7
      //   593: goto -> 611
      //   596: iload #13
      //   598: ifle -> 608
      //   601: iload #6
      //   603: istore #7
      //   605: goto -> 611
      //   608: iconst_0
      //   609: istore #7
      //   611: iload #15
      //   613: iload #5
      //   615: if_icmpeq -> 639
      //   618: iload #15
      //   620: ifge -> 631
      //   623: iload #6
      //   625: ineg
      //   626: istore #6
      //   628: goto -> 642
      //   631: iload #15
      //   633: ifle -> 639
      //   636: goto -> 642
      //   639: iconst_0
      //   640: istore #6
      //   642: aload_0
      //   643: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   646: invokevirtual getOverScrollMode : ()I
      //   649: iconst_2
      //   650: if_icmpeq -> 664
      //   653: aload_0
      //   654: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   657: iload #7
      //   659: iload #6
      //   661: invokevirtual absorbGlows : (II)V
      //   664: iload #7
      //   666: ifne -> 683
      //   669: iload #13
      //   671: iload #4
      //   673: if_icmpeq -> 683
      //   676: aload_1
      //   677: invokevirtual getFinalX : ()I
      //   680: ifne -> 706
      //   683: iload #6
      //   685: ifne -> 702
      //   688: iload #15
      //   690: iload #5
      //   692: if_icmpeq -> 702
      //   695: aload_1
      //   696: invokevirtual getFinalY : ()I
      //   699: ifne -> 706
      //   702: aload_1
      //   703: invokevirtual abortAnimation : ()V
      //   706: iload #12
      //   708: ifne -> 716
      //   711: iload #14
      //   713: ifeq -> 727
      //   716: aload_0
      //   717: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   720: iload #12
      //   722: iload #14
      //   724: invokevirtual dispatchOnScrolled : (II)V
      //   727: aload_0
      //   728: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   731: invokestatic access$700 : (Landroid/support/v7/widget/RecyclerView;)Z
      //   734: ifne -> 744
      //   737: aload_0
      //   738: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   741: invokevirtual invalidate : ()V
      //   744: iload #9
      //   746: ifeq -> 775
      //   749: aload_0
      //   750: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   753: getfield mLayout : Landroid/support/v7/widget/RecyclerView$LayoutManager;
      //   756: invokevirtual canScrollVertically : ()Z
      //   759: ifeq -> 775
      //   762: iload #14
      //   764: iload #9
      //   766: if_icmpne -> 775
      //   769: iconst_1
      //   770: istore #7
      //   772: goto -> 778
      //   775: iconst_0
      //   776: istore #7
      //   778: iload #8
      //   780: ifeq -> 809
      //   783: aload_0
      //   784: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   787: getfield mLayout : Landroid/support/v7/widget/RecyclerView$LayoutManager;
      //   790: invokevirtual canScrollHorizontally : ()Z
      //   793: ifeq -> 809
      //   796: iload #12
      //   798: iload #8
      //   800: if_icmpne -> 809
      //   803: iconst_1
      //   804: istore #6
      //   806: goto -> 812
      //   809: iconst_0
      //   810: istore #6
      //   812: iload #8
      //   814: ifne -> 822
      //   817: iload #9
      //   819: ifeq -> 841
      //   822: iload #6
      //   824: ifne -> 841
      //   827: iload #7
      //   829: ifeq -> 835
      //   832: goto -> 841
      //   835: iconst_0
      //   836: istore #7
      //   838: goto -> 844
      //   841: iconst_1
      //   842: istore #7
      //   844: aload_1
      //   845: invokevirtual isFinished : ()Z
      //   848: ifne -> 900
      //   851: iload #7
      //   853: ifne -> 870
      //   856: aload_0
      //   857: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   860: iconst_1
      //   861: invokevirtual hasNestedScrollingParent : (I)Z
      //   864: ifne -> 870
      //   867: goto -> 900
      //   870: aload_0
      //   871: invokevirtual postOnAnimation : ()V
      //   874: aload_0
      //   875: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   878: astore_1
      //   879: aload_1
      //   880: getfield mGapWorker : Landroid/support/v7/widget/GapWorker;
      //   883: astore_3
      //   884: aload_3
      //   885: ifnull -> 932
      //   888: aload_3
      //   889: aload_1
      //   890: iload #8
      //   892: iload #9
      //   894: invokevirtual postFromTraversal : (Landroid/support/v7/widget/RecyclerView;II)V
      //   897: goto -> 932
      //   900: aload_0
      //   901: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   904: iconst_0
      //   905: invokevirtual setScrollState : (I)V
      //   908: invokestatic access$800 : ()Z
      //   911: ifeq -> 924
      //   914: aload_0
      //   915: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   918: getfield mPrefetchRegistry : Landroid/support/v7/widget/GapWorker$LayoutPrefetchRegistryImpl;
      //   921: invokevirtual clearPrefetchPositions : ()V
      //   924: aload_0
      //   925: getfield this$0 : Landroid/support/v7/widget/RecyclerView;
      //   928: iconst_1
      //   929: invokevirtual stopNestedScroll : (I)V
      //   932: aload_2
      //   933: ifnull -> 960
      //   936: aload_2
      //   937: invokevirtual isPendingInitialRun : ()Z
      //   940: ifeq -> 949
      //   943: aload_2
      //   944: iconst_0
      //   945: iconst_0
      //   946: invokestatic access$600 : (Landroid/support/v7/widget/RecyclerView$SmoothScroller;II)V
      //   949: aload_0
      //   950: getfield mReSchedulePostAnimationCallback : Z
      //   953: ifne -> 960
      //   956: aload_2
      //   957: invokevirtual stop : ()V
      //   960: aload_0
      //   961: invokespecial enableRunOnAnimationRequests : ()V
      //   964: return
    }
    
    public void smoothScrollBy(int param1Int1, int param1Int2) {
      smoothScrollBy(param1Int1, param1Int2, 0, 0);
    }
    
    public void smoothScrollBy(int param1Int1, int param1Int2, int param1Int3) {
      smoothScrollBy(param1Int1, param1Int2, param1Int3, RecyclerView.sQuinticInterpolator);
    }
    
    public void smoothScrollBy(int param1Int1, int param1Int2, int param1Int3, int param1Int4) {
      smoothScrollBy(param1Int1, param1Int2, computeScrollDuration(param1Int1, param1Int2, param1Int3, param1Int4));
    }
    
    public void smoothScrollBy(int param1Int1, int param1Int2, int param1Int3, Interpolator param1Interpolator) {
      if (this.mInterpolator != param1Interpolator) {
        this.mInterpolator = param1Interpolator;
        this.mScroller = new OverScroller(RecyclerView.this.getContext(), param1Interpolator);
      } 
      RecyclerView.this.setScrollState(2);
      this.mLastFlingY = 0;
      this.mLastFlingX = 0;
      this.mScroller.startScroll(0, 0, param1Int1, param1Int2, param1Int3);
      if (Build.VERSION.SDK_INT < 23)
        this.mScroller.computeScrollOffset(); 
      postOnAnimation();
    }
    
    public void smoothScrollBy(int param1Int1, int param1Int2, Interpolator param1Interpolator) {
      int i = computeScrollDuration(param1Int1, param1Int2, 0, 0);
      Interpolator interpolator = param1Interpolator;
      if (param1Interpolator == null)
        interpolator = RecyclerView.sQuinticInterpolator; 
      smoothScrollBy(param1Int1, param1Int2, i, interpolator);
    }
    
    public void stop() {
      RecyclerView.this.removeCallbacks(this);
      this.mScroller.abortAnimation();
    }
  }
  
  public static abstract class ViewHolder {
    static final int FLAG_ADAPTER_FULLUPDATE = 1024;
    
    static final int FLAG_ADAPTER_POSITION_UNKNOWN = 512;
    
    static final int FLAG_APPEARED_IN_PRE_LAYOUT = 4096;
    
    static final int FLAG_BOUNCED_FROM_HIDDEN_LIST = 8192;
    
    static final int FLAG_BOUND = 1;
    
    static final int FLAG_IGNORE = 128;
    
    static final int FLAG_INVALID = 4;
    
    static final int FLAG_MOVED = 2048;
    
    static final int FLAG_NOT_RECYCLABLE = 16;
    
    static final int FLAG_REMOVED = 8;
    
    static final int FLAG_RETURNED_FROM_SCRAP = 32;
    
    static final int FLAG_SET_A11Y_ITEM_DELEGATE = 16384;
    
    static final int FLAG_TMP_DETACHED = 256;
    
    static final int FLAG_UPDATE = 2;
    
    private static final List<Object> FULLUPDATE_PAYLOADS = Collections.EMPTY_LIST;
    
    static final int PENDING_ACCESSIBILITY_STATE_NOT_SET = -1;
    
    public final View itemView;
    
    private int mFlags;
    
    private boolean mInChangeScrap = false;
    
    private int mIsRecyclableCount = 0;
    
    long mItemId = -1L;
    
    int mItemViewType = -1;
    
    WeakReference<RecyclerView> mNestedRecyclerView;
    
    int mOldPosition = -1;
    
    RecyclerView mOwnerRecyclerView;
    
    List<Object> mPayloads = null;
    
    @VisibleForTesting
    int mPendingAccessibilityState = -1;
    
    int mPosition = -1;
    
    int mPreLayoutPosition = -1;
    
    private RecyclerView.Recycler mScrapContainer = null;
    
    ViewHolder mShadowedHolder = null;
    
    ViewHolder mShadowingHolder = null;
    
    List<Object> mUnmodifiedPayloads = null;
    
    private int mWasImportantForAccessibilityBeforeHidden = 0;
    
    public ViewHolder(View param1View) {
      if (param1View != null) {
        this.itemView = param1View;
        return;
      } 
      throw new IllegalArgumentException("itemView may not be null");
    }
    
    private void createPayloadsIfNeeded() {
      if (this.mPayloads == null) {
        ArrayList<Object> arrayList = new ArrayList();
        this.mPayloads = arrayList;
        this.mUnmodifiedPayloads = Collections.unmodifiableList(arrayList);
      } 
    }
    
    private boolean doesTransientStatePreventRecycling() {
      boolean bool;
      if ((this.mFlags & 0x10) == 0 && ViewCompat.hasTransientState(this.itemView)) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    private void onEnteredHiddenState(RecyclerView param1RecyclerView) {
      this.mWasImportantForAccessibilityBeforeHidden = ViewCompat.getImportantForAccessibility(this.itemView);
      param1RecyclerView.setChildImportantForAccessibilityInternal(this, 4);
    }
    
    private void onLeftHiddenState(RecyclerView param1RecyclerView) {
      param1RecyclerView.setChildImportantForAccessibilityInternal(this, this.mWasImportantForAccessibilityBeforeHidden);
      this.mWasImportantForAccessibilityBeforeHidden = 0;
    }
    
    private boolean shouldBeKeptAsChild() {
      boolean bool;
      if ((this.mFlags & 0x10) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    void addChangePayload(Object param1Object) {
      if (param1Object == null) {
        addFlags(1024);
      } else if ((0x400 & this.mFlags) == 0) {
        createPayloadsIfNeeded();
        this.mPayloads.add(param1Object);
      } 
    }
    
    void addFlags(int param1Int) {
      this.mFlags = param1Int | this.mFlags;
    }
    
    void clearOldPosition() {
      this.mOldPosition = -1;
      this.mPreLayoutPosition = -1;
    }
    
    void clearPayload() {
      List<Object> list = this.mPayloads;
      if (list != null)
        list.clear(); 
      this.mFlags &= 0xFFFFFBFF;
    }
    
    void clearReturnedFromScrapFlag() {
      this.mFlags &= 0xFFFFFFDF;
    }
    
    void clearTmpDetachFlag() {
      this.mFlags &= 0xFFFFFEFF;
    }
    
    void flagRemovedAndOffsetPosition(int param1Int1, int param1Int2, boolean param1Boolean) {
      addFlags(8);
      offsetPosition(param1Int2, param1Boolean);
      this.mPosition = param1Int1;
    }
    
    public final int getAdapterPosition() {
      RecyclerView recyclerView = this.mOwnerRecyclerView;
      return (recyclerView == null) ? -1 : recyclerView.getAdapterPositionFor(this);
    }
    
    public final long getItemId() {
      return this.mItemId;
    }
    
    public final int getItemViewType() {
      return this.mItemViewType;
    }
    
    public final int getLayoutPosition() {
      int i = this.mPreLayoutPosition;
      int j = i;
      if (i == -1)
        j = this.mPosition; 
      return j;
    }
    
    public final int getOldPosition() {
      return this.mOldPosition;
    }
    
    @Deprecated
    public final int getPosition() {
      int i = this.mPreLayoutPosition;
      int j = i;
      if (i == -1)
        j = this.mPosition; 
      return j;
    }
    
    List<Object> getUnmodifiedPayloads() {
      if ((this.mFlags & 0x400) == 0) {
        List<Object> list = this.mPayloads;
        return (list == null || list.size() == 0) ? FULLUPDATE_PAYLOADS : this.mUnmodifiedPayloads;
      } 
      return FULLUPDATE_PAYLOADS;
    }
    
    boolean hasAnyOfTheFlags(int param1Int) {
      boolean bool;
      if ((param1Int & this.mFlags) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    boolean isAdapterPositionUnknown() {
      return ((this.mFlags & 0x200) != 0 || isInvalid());
    }
    
    boolean isBound() {
      int i = this.mFlags;
      boolean bool = true;
      if ((i & 0x1) == 0)
        bool = false; 
      return bool;
    }
    
    boolean isInvalid() {
      boolean bool;
      if ((this.mFlags & 0x4) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public final boolean isRecyclable() {
      boolean bool;
      if ((this.mFlags & 0x10) == 0 && !ViewCompat.hasTransientState(this.itemView)) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    boolean isRemoved() {
      boolean bool;
      if ((this.mFlags & 0x8) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    boolean isScrap() {
      boolean bool;
      if (this.mScrapContainer != null) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    boolean isTmpDetached() {
      boolean bool;
      if ((this.mFlags & 0x100) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    boolean isUpdated() {
      boolean bool;
      if ((this.mFlags & 0x2) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    boolean needsUpdate() {
      boolean bool;
      if ((this.mFlags & 0x2) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    void offsetPosition(int param1Int, boolean param1Boolean) {
      if (this.mOldPosition == -1)
        this.mOldPosition = this.mPosition; 
      if (this.mPreLayoutPosition == -1)
        this.mPreLayoutPosition = this.mPosition; 
      if (param1Boolean)
        this.mPreLayoutPosition += param1Int; 
      this.mPosition += param1Int;
      if (this.itemView.getLayoutParams() != null)
        ((RecyclerView.LayoutParams)this.itemView.getLayoutParams()).mInsetsDirty = true; 
    }
    
    void resetInternal() {
      this.mFlags = 0;
      this.mPosition = -1;
      this.mOldPosition = -1;
      this.mItemId = -1L;
      this.mPreLayoutPosition = -1;
      this.mIsRecyclableCount = 0;
      this.mShadowedHolder = null;
      this.mShadowingHolder = null;
      clearPayload();
      this.mWasImportantForAccessibilityBeforeHidden = 0;
      this.mPendingAccessibilityState = -1;
      RecyclerView.clearNestedRecyclerViewIfNotNested(this);
    }
    
    void saveOldPosition() {
      if (this.mOldPosition == -1)
        this.mOldPosition = this.mPosition; 
    }
    
    void setFlags(int param1Int1, int param1Int2) {
      this.mFlags = param1Int1 & param1Int2 | this.mFlags & (param1Int2 ^ 0xFFFFFFFF);
    }
    
    public final void setIsRecyclable(boolean param1Boolean) {
      int i = this.mIsRecyclableCount;
      if (param1Boolean) {
        i--;
      } else {
        i++;
      } 
      this.mIsRecyclableCount = i;
      if (i < 0) {
        this.mIsRecyclableCount = 0;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for ");
        stringBuilder.append(this);
        Log.e("View", stringBuilder.toString());
      } else if (!param1Boolean && i == 1) {
        this.mFlags |= 0x10;
      } else if (param1Boolean && i == 0) {
        this.mFlags &= 0xFFFFFFEF;
      } 
    }
    
    void setScrapContainer(RecyclerView.Recycler param1Recycler, boolean param1Boolean) {
      this.mScrapContainer = param1Recycler;
      this.mInChangeScrap = param1Boolean;
    }
    
    boolean shouldIgnore() {
      boolean bool;
      if ((this.mFlags & 0x80) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    void stopIgnoring() {
      this.mFlags &= 0xFFFFFF7F;
    }
    
    public String toString() {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("ViewHolder{");
      stringBuilder1.append(Integer.toHexString(hashCode()));
      stringBuilder1.append(" position=");
      stringBuilder1.append(this.mPosition);
      stringBuilder1.append(" id=");
      stringBuilder1.append(this.mItemId);
      stringBuilder1.append(", oldPos=");
      stringBuilder1.append(this.mOldPosition);
      stringBuilder1.append(", pLpos:");
      stringBuilder1.append(this.mPreLayoutPosition);
      StringBuilder stringBuilder2 = new StringBuilder(stringBuilder1.toString());
      if (isScrap()) {
        String str;
        stringBuilder2.append(" scrap ");
        if (this.mInChangeScrap) {
          str = "[changeScrap]";
        } else {
          str = "[attachedScrap]";
        } 
        stringBuilder2.append(str);
      } 
      if (isInvalid())
        stringBuilder2.append(" invalid"); 
      if (!isBound())
        stringBuilder2.append(" unbound"); 
      if (needsUpdate())
        stringBuilder2.append(" update"); 
      if (isRemoved())
        stringBuilder2.append(" removed"); 
      if (shouldIgnore())
        stringBuilder2.append(" ignored"); 
      if (isTmpDetached())
        stringBuilder2.append(" tmpDetached"); 
      if (!isRecyclable()) {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append(" not recyclable(");
        stringBuilder1.append(this.mIsRecyclableCount);
        stringBuilder1.append(")");
        stringBuilder2.append(stringBuilder1.toString());
      } 
      if (isAdapterPositionUnknown())
        stringBuilder2.append(" undefined adapter position"); 
      if (this.itemView.getParent() == null)
        stringBuilder2.append(" no parent"); 
      stringBuilder2.append("}");
      return stringBuilder2.toString();
    }
    
    void unScrap() {
      this.mScrapContainer.unscrapView(this);
    }
    
    boolean wasReturnedFromScrap() {
      boolean bool;
      if ((this.mFlags & 0x20) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/widget/RecyclerView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */