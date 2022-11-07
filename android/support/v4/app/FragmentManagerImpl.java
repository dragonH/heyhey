package android.support.v4.app;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.v4.util.ArraySet;
import android.support.v4.util.DebugUtils;
import android.support.v4.util.LogWriter;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

final class FragmentManagerImpl extends FragmentManager implements LayoutInflater.Factory2 {
  static final Interpolator ACCELERATE_CUBIC;
  
  static final Interpolator ACCELERATE_QUINT;
  
  static final int ANIM_DUR = 220;
  
  public static final int ANIM_STYLE_CLOSE_ENTER = 3;
  
  public static final int ANIM_STYLE_CLOSE_EXIT = 4;
  
  public static final int ANIM_STYLE_FADE_ENTER = 5;
  
  public static final int ANIM_STYLE_FADE_EXIT = 6;
  
  public static final int ANIM_STYLE_OPEN_ENTER = 1;
  
  public static final int ANIM_STYLE_OPEN_EXIT = 2;
  
  static boolean DEBUG = false;
  
  static final Interpolator DECELERATE_CUBIC;
  
  static final Interpolator DECELERATE_QUINT = (Interpolator)new DecelerateInterpolator(2.5F);
  
  static final String TAG = "FragmentManager";
  
  static final String TARGET_REQUEST_CODE_STATE_TAG = "android:target_req_state";
  
  static final String TARGET_STATE_TAG = "android:target_state";
  
  static final String USER_VISIBLE_HINT_TAG = "android:user_visible_hint";
  
  static final String VIEW_STATE_TAG = "android:view_state";
  
  static Field sAnimationListenerField;
  
  SparseArray<Fragment> mActive;
  
  final ArrayList<Fragment> mAdded = new ArrayList<Fragment>();
  
  ArrayList<Integer> mAvailBackStackIndices;
  
  ArrayList<BackStackRecord> mBackStack;
  
  ArrayList<FragmentManager.OnBackStackChangedListener> mBackStackChangeListeners;
  
  ArrayList<BackStackRecord> mBackStackIndices;
  
  FragmentContainer mContainer;
  
  ArrayList<Fragment> mCreatedMenus;
  
  int mCurState = 0;
  
  boolean mDestroyed;
  
  Runnable mExecCommit = new Runnable() {
      public void run() {
        FragmentManagerImpl.this.execPendingActions();
      }
    };
  
  boolean mExecutingActions;
  
  boolean mHavePendingDeferredStart;
  
  FragmentHostCallback mHost;
  
  private final CopyOnWriteArrayList<Pair<FragmentManager.FragmentLifecycleCallbacks, Boolean>> mLifecycleCallbacks = new CopyOnWriteArrayList<Pair<FragmentManager.FragmentLifecycleCallbacks, Boolean>>();
  
  boolean mNeedMenuInvalidate;
  
  int mNextFragmentIndex = 0;
  
  String mNoTransactionsBecause;
  
  Fragment mParent;
  
  ArrayList<OpGenerator> mPendingActions;
  
  ArrayList<StartEnterTransitionListener> mPostponedTransactions;
  
  Fragment mPrimaryNav;
  
  FragmentManagerNonConfig mSavedNonConfig;
  
  SparseArray<Parcelable> mStateArray = null;
  
  Bundle mStateBundle = null;
  
  boolean mStateSaved;
  
  ArrayList<Fragment> mTmpAddedFragments;
  
  ArrayList<Boolean> mTmpIsPop;
  
  ArrayList<BackStackRecord> mTmpRecords;
  
  static {
    DECELERATE_CUBIC = (Interpolator)new DecelerateInterpolator(1.5F);
    ACCELERATE_QUINT = (Interpolator)new AccelerateInterpolator(2.5F);
    ACCELERATE_CUBIC = (Interpolator)new AccelerateInterpolator(1.5F);
  }
  
  private void addAddedFragments(ArraySet<Fragment> paramArraySet) {
    int i = this.mCurState;
    if (i < 1)
      return; 
    int j = Math.min(i, 4);
    int k = this.mAdded.size();
    for (i = 0; i < k; i++) {
      Fragment fragment = this.mAdded.get(i);
      if (fragment.mState < j) {
        moveToState(fragment, j, fragment.getNextAnim(), fragment.getNextTransition(), false);
        if (fragment.mView != null && !fragment.mHidden && fragment.mIsNewlyAdded)
          paramArraySet.add(fragment); 
      } 
    } 
  }
  
  private void animateRemoveFragment(@NonNull final Fragment fragment, @NonNull AnimationOrAnimator paramAnimationOrAnimator, int paramInt) {
    final View viewToAnimate = fragment.mView;
    fragment.setStateAfterAnimating(paramInt);
    Animation animation = paramAnimationOrAnimator.animation;
    if (animation != null) {
      fragment.setAnimatingAway(fragment.mView);
      animation.setAnimationListener(new AnimationListenerWrapper(getAnimationListener(animation)) {
            public void onAnimationEnd(Animation param1Animation) {
              super.onAnimationEnd(param1Animation);
              if (fragment.getAnimatingAway() != null) {
                fragment.setAnimatingAway(null);
                FragmentManagerImpl fragmentManagerImpl = FragmentManagerImpl.this;
                Fragment fragment = fragment;
                fragmentManagerImpl.moveToState(fragment, fragment.getStateAfterAnimating(), 0, 0, false);
              } 
            }
          });
      setHWLayerAnimListenerIfAlpha(view, paramAnimationOrAnimator);
      fragment.mView.startAnimation(animation);
    } else {
      Animator animator = paramAnimationOrAnimator.animator;
      fragment.setAnimator(animator);
      final ViewGroup container = fragment.mContainer;
      if (viewGroup != null)
        viewGroup.startViewTransition(view); 
      animator.addListener((Animator.AnimatorListener)new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator param1Animator) {
              ViewGroup viewGroup = container;
              if (viewGroup != null)
                viewGroup.endViewTransition(viewToAnimate); 
              if (fragment.getAnimator() != null) {
                fragment.setAnimator(null);
                FragmentManagerImpl fragmentManagerImpl = FragmentManagerImpl.this;
                Fragment fragment = fragment;
                fragmentManagerImpl.moveToState(fragment, fragment.getStateAfterAnimating(), 0, 0, false);
              } 
            }
          });
      animator.setTarget(fragment.mView);
      setHWLayerAnimListenerIfAlpha(fragment.mView, paramAnimationOrAnimator);
      animator.start();
    } 
  }
  
  private void burpActive() {
    SparseArray<Fragment> sparseArray = this.mActive;
    if (sparseArray != null)
      for (int i = sparseArray.size() - 1; i >= 0; i--) {
        if (this.mActive.valueAt(i) == null) {
          sparseArray = this.mActive;
          sparseArray.delete(sparseArray.keyAt(i));
        } 
      }  
  }
  
  private void checkStateLoss() {
    if (!this.mStateSaved) {
      if (this.mNoTransactionsBecause == null)
        return; 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Can not perform this action inside of ");
      stringBuilder.append(this.mNoTransactionsBecause);
      throw new IllegalStateException(stringBuilder.toString());
    } 
    throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
  }
  
  private void cleanupExec() {
    this.mExecutingActions = false;
    this.mTmpIsPop.clear();
    this.mTmpRecords.clear();
  }
  
  private void completeExecute(BackStackRecord paramBackStackRecord, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3) {
    if (paramBoolean1) {
      paramBackStackRecord.executePopOps(paramBoolean3);
    } else {
      paramBackStackRecord.executeOps();
    } 
    ArrayList<BackStackRecord> arrayList = new ArrayList(1);
    ArrayList<Boolean> arrayList1 = new ArrayList(1);
    arrayList.add(paramBackStackRecord);
    arrayList1.add(Boolean.valueOf(paramBoolean1));
    if (paramBoolean2)
      FragmentTransition.startTransitions(this, arrayList, arrayList1, 0, 1, true); 
    if (paramBoolean3)
      moveToState(this.mCurState, true); 
    SparseArray<Fragment> sparseArray = this.mActive;
    if (sparseArray != null) {
      int i = sparseArray.size();
      for (byte b = 0; b < i; b++) {
        Fragment fragment = (Fragment)this.mActive.valueAt(b);
        if (fragment != null && fragment.mView != null && fragment.mIsNewlyAdded && paramBackStackRecord.interactsWith(fragment.mContainerId)) {
          float f = fragment.mPostponedAlpha;
          if (f > 0.0F)
            fragment.mView.setAlpha(f); 
          if (paramBoolean3) {
            fragment.mPostponedAlpha = 0.0F;
          } else {
            fragment.mPostponedAlpha = -1.0F;
            fragment.mIsNewlyAdded = false;
          } 
        } 
      } 
    } 
  }
  
  private void dispatchStateChange(int paramInt) {
    try {
      this.mExecutingActions = true;
      moveToState(paramInt, false);
      this.mExecutingActions = false;
      return;
    } finally {
      this.mExecutingActions = false;
    } 
  }
  
  private void endAnimatingAwayFragments() {
    int i;
    SparseArray<Fragment> sparseArray = this.mActive;
    byte b = 0;
    if (sparseArray == null) {
      i = 0;
    } else {
      i = sparseArray.size();
    } 
    while (b < i) {
      Fragment fragment = (Fragment)this.mActive.valueAt(b);
      if (fragment != null)
        if (fragment.getAnimatingAway() != null) {
          int j = fragment.getStateAfterAnimating();
          View view = fragment.getAnimatingAway();
          fragment.setAnimatingAway(null);
          Animation animation = view.getAnimation();
          if (animation != null) {
            animation.cancel();
            view.clearAnimation();
          } 
          moveToState(fragment, j, 0, 0, false);
        } else if (fragment.getAnimator() != null) {
          fragment.getAnimator().end();
        }  
      b++;
    } 
  }
  
  private void ensureExecReady(boolean paramBoolean) {
    if (!this.mExecutingActions) {
      if (Looper.myLooper() == this.mHost.getHandler().getLooper()) {
        if (!paramBoolean)
          checkStateLoss(); 
        if (this.mTmpRecords == null) {
          this.mTmpRecords = new ArrayList<BackStackRecord>();
          this.mTmpIsPop = new ArrayList<Boolean>();
        } 
        this.mExecutingActions = true;
        try {
          executePostponedTransaction(null, null);
          return;
        } finally {
          this.mExecutingActions = false;
        } 
      } 
      throw new IllegalStateException("Must be called from main thread of fragment host");
    } 
    throw new IllegalStateException("FragmentManager is already executing transactions");
  }
  
  private static void executeOps(ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1, int paramInt1, int paramInt2) {
    while (paramInt1 < paramInt2) {
      BackStackRecord backStackRecord = paramArrayList.get(paramInt1);
      boolean bool = ((Boolean)paramArrayList1.get(paramInt1)).booleanValue();
      boolean bool1 = true;
      if (bool) {
        backStackRecord.bumpBackStackNesting(-1);
        if (paramInt1 != paramInt2 - 1)
          bool1 = false; 
        backStackRecord.executePopOps(bool1);
      } else {
        backStackRecord.bumpBackStackNesting(1);
        backStackRecord.executeOps();
      } 
      paramInt1++;
    } 
  }
  
  private void executeOpsTogether(ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1, int paramInt1, int paramInt2) {
    int k;
    int i = paramInt1;
    boolean bool = ((BackStackRecord)paramArrayList.get(i)).mReorderingAllowed;
    ArrayList<Fragment> arrayList = this.mTmpAddedFragments;
    if (arrayList == null) {
      this.mTmpAddedFragments = new ArrayList<Fragment>();
    } else {
      arrayList.clear();
    } 
    this.mTmpAddedFragments.addAll(this.mAdded);
    Fragment fragment = getPrimaryNavigationFragment();
    int j = i;
    boolean bool1 = false;
    while (j < paramInt2) {
      BackStackRecord backStackRecord = paramArrayList.get(j);
      if (!((Boolean)paramArrayList1.get(j)).booleanValue()) {
        fragment = backStackRecord.expandOps(this.mTmpAddedFragments, fragment);
      } else {
        fragment = backStackRecord.trackAddedFragmentsInPop(this.mTmpAddedFragments, fragment);
      } 
      if (bool1 || backStackRecord.mAddToBackStack) {
        bool1 = true;
      } else {
        bool1 = false;
      } 
      j++;
    } 
    this.mTmpAddedFragments.clear();
    if (!bool)
      FragmentTransition.startTransitions(this, paramArrayList, paramArrayList1, paramInt1, paramInt2, false); 
    executeOps(paramArrayList, paramArrayList1, paramInt1, paramInt2);
    if (bool) {
      ArraySet<Fragment> arraySet = new ArraySet();
      addAddedFragments(arraySet);
      k = postponePostponableTransactions(paramArrayList, paramArrayList1, paramInt1, paramInt2, arraySet);
      makeRemovedFragmentsInvisible(arraySet);
    } else {
      k = paramInt2;
    } 
    j = i;
    if (k != i) {
      j = i;
      if (bool) {
        FragmentTransition.startTransitions(this, paramArrayList, paramArrayList1, paramInt1, k, true);
        moveToState(this.mCurState, true);
        j = i;
      } 
    } 
    while (j < paramInt2) {
      BackStackRecord backStackRecord = paramArrayList.get(j);
      if (((Boolean)paramArrayList1.get(j)).booleanValue()) {
        paramInt1 = backStackRecord.mIndex;
        if (paramInt1 >= 0) {
          freeBackStackIndex(paramInt1);
          backStackRecord.mIndex = -1;
        } 
      } 
      backStackRecord.runOnCommitRunnables();
      j++;
    } 
    if (bool1)
      reportBackStackChanged(); 
  }
  
  private void executePostponedTransaction(ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mPostponedTransactions : Ljava/util/ArrayList;
    //   4: astore_3
    //   5: aload_3
    //   6: ifnonnull -> 15
    //   9: iconst_0
    //   10: istore #4
    //   12: goto -> 21
    //   15: aload_3
    //   16: invokevirtual size : ()I
    //   19: istore #4
    //   21: iconst_0
    //   22: istore #5
    //   24: iload #5
    //   26: iload #4
    //   28: if_icmpge -> 232
    //   31: aload_0
    //   32: getfield mPostponedTransactions : Ljava/util/ArrayList;
    //   35: iload #5
    //   37: invokevirtual get : (I)Ljava/lang/Object;
    //   40: checkcast android/support/v4/app/FragmentManagerImpl$StartEnterTransitionListener
    //   43: astore_3
    //   44: aload_1
    //   45: ifnull -> 101
    //   48: aload_3
    //   49: invokestatic access$300 : (Landroid/support/v4/app/FragmentManagerImpl$StartEnterTransitionListener;)Z
    //   52: ifne -> 101
    //   55: aload_1
    //   56: aload_3
    //   57: invokestatic access$400 : (Landroid/support/v4/app/FragmentManagerImpl$StartEnterTransitionListener;)Landroid/support/v4/app/BackStackRecord;
    //   60: invokevirtual indexOf : (Ljava/lang/Object;)I
    //   63: istore #6
    //   65: iload #6
    //   67: iconst_m1
    //   68: if_icmpeq -> 101
    //   71: aload_2
    //   72: iload #6
    //   74: invokevirtual get : (I)Ljava/lang/Object;
    //   77: checkcast java/lang/Boolean
    //   80: invokevirtual booleanValue : ()Z
    //   83: ifeq -> 101
    //   86: aload_3
    //   87: invokevirtual cancelTransaction : ()V
    //   90: iload #4
    //   92: istore #6
    //   94: iload #5
    //   96: istore #7
    //   98: goto -> 219
    //   101: aload_3
    //   102: invokevirtual isReady : ()Z
    //   105: ifne -> 144
    //   108: iload #4
    //   110: istore #6
    //   112: iload #5
    //   114: istore #7
    //   116: aload_1
    //   117: ifnull -> 219
    //   120: iload #4
    //   122: istore #6
    //   124: iload #5
    //   126: istore #7
    //   128: aload_3
    //   129: invokestatic access$400 : (Landroid/support/v4/app/FragmentManagerImpl$StartEnterTransitionListener;)Landroid/support/v4/app/BackStackRecord;
    //   132: aload_1
    //   133: iconst_0
    //   134: aload_1
    //   135: invokevirtual size : ()I
    //   138: invokevirtual interactsWith : (Ljava/util/ArrayList;II)Z
    //   141: ifeq -> 219
    //   144: aload_0
    //   145: getfield mPostponedTransactions : Ljava/util/ArrayList;
    //   148: iload #5
    //   150: invokevirtual remove : (I)Ljava/lang/Object;
    //   153: pop
    //   154: iload #5
    //   156: iconst_1
    //   157: isub
    //   158: istore #7
    //   160: iload #4
    //   162: iconst_1
    //   163: isub
    //   164: istore #6
    //   166: aload_1
    //   167: ifnull -> 215
    //   170: aload_3
    //   171: invokestatic access$300 : (Landroid/support/v4/app/FragmentManagerImpl$StartEnterTransitionListener;)Z
    //   174: ifne -> 215
    //   177: aload_1
    //   178: aload_3
    //   179: invokestatic access$400 : (Landroid/support/v4/app/FragmentManagerImpl$StartEnterTransitionListener;)Landroid/support/v4/app/BackStackRecord;
    //   182: invokevirtual indexOf : (Ljava/lang/Object;)I
    //   185: istore #5
    //   187: iload #5
    //   189: iconst_m1
    //   190: if_icmpeq -> 215
    //   193: aload_2
    //   194: iload #5
    //   196: invokevirtual get : (I)Ljava/lang/Object;
    //   199: checkcast java/lang/Boolean
    //   202: invokevirtual booleanValue : ()Z
    //   205: ifeq -> 215
    //   208: aload_3
    //   209: invokevirtual cancelTransaction : ()V
    //   212: goto -> 219
    //   215: aload_3
    //   216: invokevirtual completeTransaction : ()V
    //   219: iload #7
    //   221: iconst_1
    //   222: iadd
    //   223: istore #5
    //   225: iload #6
    //   227: istore #4
    //   229: goto -> 24
    //   232: return
  }
  
  private Fragment findFragmentUnder(Fragment paramFragment) {
    ViewGroup viewGroup = paramFragment.mContainer;
    View view = paramFragment.mView;
    if (viewGroup != null && view != null)
      for (int i = this.mAdded.indexOf(paramFragment) - 1; i >= 0; i--) {
        paramFragment = this.mAdded.get(i);
        if (paramFragment.mContainer == viewGroup && paramFragment.mView != null)
          return paramFragment; 
      }  
    return null;
  }
  
  private void forcePostponedTransactions() {
    if (this.mPostponedTransactions != null)
      while (!this.mPostponedTransactions.isEmpty())
        ((StartEnterTransitionListener)this.mPostponedTransactions.remove(0)).completeTransaction();  
  }
  
  private boolean generateOpsForPendingActions(ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mPendingActions : Ljava/util/ArrayList;
    //   6: astore_3
    //   7: iconst_0
    //   8: istore #4
    //   10: aload_3
    //   11: ifnull -> 99
    //   14: aload_3
    //   15: invokevirtual size : ()I
    //   18: ifne -> 24
    //   21: goto -> 99
    //   24: aload_0
    //   25: getfield mPendingActions : Ljava/util/ArrayList;
    //   28: invokevirtual size : ()I
    //   31: istore #5
    //   33: iconst_0
    //   34: istore #6
    //   36: iload #4
    //   38: iload #5
    //   40: if_icmpge -> 73
    //   43: iload #6
    //   45: aload_0
    //   46: getfield mPendingActions : Ljava/util/ArrayList;
    //   49: iload #4
    //   51: invokevirtual get : (I)Ljava/lang/Object;
    //   54: checkcast android/support/v4/app/FragmentManagerImpl$OpGenerator
    //   57: aload_1
    //   58: aload_2
    //   59: invokeinterface generateOps : (Ljava/util/ArrayList;Ljava/util/ArrayList;)Z
    //   64: ior
    //   65: istore #6
    //   67: iinc #4, 1
    //   70: goto -> 36
    //   73: aload_0
    //   74: getfield mPendingActions : Ljava/util/ArrayList;
    //   77: invokevirtual clear : ()V
    //   80: aload_0
    //   81: getfield mHost : Landroid/support/v4/app/FragmentHostCallback;
    //   84: invokevirtual getHandler : ()Landroid/os/Handler;
    //   87: aload_0
    //   88: getfield mExecCommit : Ljava/lang/Runnable;
    //   91: invokevirtual removeCallbacks : (Ljava/lang/Runnable;)V
    //   94: aload_0
    //   95: monitorexit
    //   96: iload #6
    //   98: ireturn
    //   99: aload_0
    //   100: monitorexit
    //   101: iconst_0
    //   102: ireturn
    //   103: astore_1
    //   104: aload_0
    //   105: monitorexit
    //   106: goto -> 111
    //   109: aload_1
    //   110: athrow
    //   111: goto -> 109
    // Exception table:
    //   from	to	target	type
    //   2	7	103	finally
    //   14	21	103	finally
    //   24	33	103	finally
    //   43	67	103	finally
    //   73	96	103	finally
    //   99	101	103	finally
    //   104	106	103	finally
  }
  
  private static Animation.AnimationListener getAnimationListener(Animation paramAnimation) {
    try {
      if (sAnimationListenerField == null) {
        Field field = Animation.class.getDeclaredField("mListener");
        sAnimationListenerField = field;
        field.setAccessible(true);
      } 
      Animation.AnimationListener animationListener = (Animation.AnimationListener)sAnimationListenerField.get(paramAnimation);
    } catch (NoSuchFieldException noSuchFieldException) {
      Log.e("FragmentManager", "No field with the name mListener is found in Animation class", noSuchFieldException);
      noSuchFieldException = null;
    } catch (IllegalAccessException illegalAccessException) {
      Log.e("FragmentManager", "Cannot access Animation's mListener field", illegalAccessException);
    } 
    return (Animation.AnimationListener)illegalAccessException;
  }
  
  static AnimationOrAnimator makeFadeAnimation(Context paramContext, float paramFloat1, float paramFloat2) {
    AlphaAnimation alphaAnimation = new AlphaAnimation(paramFloat1, paramFloat2);
    alphaAnimation.setInterpolator(DECELERATE_CUBIC);
    alphaAnimation.setDuration(220L);
    return new AnimationOrAnimator((Animation)alphaAnimation);
  }
  
  static AnimationOrAnimator makeOpenCloseAnimation(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    AnimationSet animationSet = new AnimationSet(false);
    ScaleAnimation scaleAnimation = new ScaleAnimation(paramFloat1, paramFloat2, paramFloat1, paramFloat2, 1, 0.5F, 1, 0.5F);
    scaleAnimation.setInterpolator(DECELERATE_QUINT);
    scaleAnimation.setDuration(220L);
    animationSet.addAnimation((Animation)scaleAnimation);
    AlphaAnimation alphaAnimation = new AlphaAnimation(paramFloat3, paramFloat4);
    alphaAnimation.setInterpolator(DECELERATE_CUBIC);
    alphaAnimation.setDuration(220L);
    animationSet.addAnimation((Animation)alphaAnimation);
    return new AnimationOrAnimator((Animation)animationSet);
  }
  
  private void makeRemovedFragmentsInvisible(ArraySet<Fragment> paramArraySet) {
    int i = paramArraySet.size();
    for (byte b = 0; b < i; b++) {
      Fragment fragment = (Fragment)paramArraySet.valueAt(b);
      if (!fragment.mAdded) {
        View view = fragment.getView();
        fragment.mPostponedAlpha = view.getAlpha();
        view.setAlpha(0.0F);
      } 
    } 
  }
  
  static boolean modifiesAlpha(Animator paramAnimator) {
    PropertyValuesHolder[] arrayOfPropertyValuesHolder;
    if (paramAnimator == null)
      return false; 
    if (paramAnimator instanceof ValueAnimator) {
      arrayOfPropertyValuesHolder = ((ValueAnimator)paramAnimator).getValues();
      for (byte b = 0; b < arrayOfPropertyValuesHolder.length; b++) {
        if ("alpha".equals(arrayOfPropertyValuesHolder[b].getPropertyName()))
          return true; 
      } 
    } else if (arrayOfPropertyValuesHolder instanceof AnimatorSet) {
      ArrayList<Animator> arrayList = ((AnimatorSet)arrayOfPropertyValuesHolder).getChildAnimations();
      for (byte b = 0; b < arrayList.size(); b++) {
        if (modifiesAlpha(arrayList.get(b)))
          return true; 
      } 
    } 
    return false;
  }
  
  static boolean modifiesAlpha(AnimationOrAnimator paramAnimationOrAnimator) {
    List list;
    Animation animation = paramAnimationOrAnimator.animation;
    if (animation instanceof AlphaAnimation)
      return true; 
    if (animation instanceof AnimationSet) {
      list = ((AnimationSet)animation).getAnimations();
      for (byte b = 0; b < list.size(); b++) {
        if (list.get(b) instanceof AlphaAnimation)
          return true; 
      } 
      return false;
    } 
    return modifiesAlpha(((AnimationOrAnimator)list).animator);
  }
  
  private boolean popBackStackImmediate(String paramString, int paramInt1, int paramInt2) {
    execPendingActions();
    ensureExecReady(true);
    Fragment fragment = this.mPrimaryNav;
    if (fragment != null && paramInt1 < 0 && paramString == null) {
      FragmentManager fragmentManager = fragment.peekChildFragmentManager();
      if (fragmentManager != null && fragmentManager.popBackStackImmediate())
        return true; 
    } 
    boolean bool = popBackStackState(this.mTmpRecords, this.mTmpIsPop, paramString, paramInt1, paramInt2);
    if (bool) {
      this.mExecutingActions = true;
      try {
        removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);
      } finally {
        cleanupExec();
      } 
    } 
    doPendingDeferredStart();
    burpActive();
    return bool;
  }
  
  private int postponePostponableTransactions(ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1, int paramInt1, int paramInt2, ArraySet<Fragment> paramArraySet) {
    int i = paramInt2 - 1;
    int j;
    for (j = paramInt2; i >= paramInt1; j = k) {
      boolean bool1;
      BackStackRecord backStackRecord = paramArrayList.get(i);
      boolean bool = ((Boolean)paramArrayList1.get(i)).booleanValue();
      if (backStackRecord.isPostponed() && !backStackRecord.interactsWith(paramArrayList, i + 1, paramInt2)) {
        bool1 = true;
      } else {
        bool1 = false;
      } 
      int k = j;
      if (bool1) {
        if (this.mPostponedTransactions == null)
          this.mPostponedTransactions = new ArrayList<StartEnterTransitionListener>(); 
        StartEnterTransitionListener startEnterTransitionListener = new StartEnterTransitionListener(backStackRecord, bool);
        this.mPostponedTransactions.add(startEnterTransitionListener);
        backStackRecord.setOnStartPostponedListener(startEnterTransitionListener);
        if (bool) {
          backStackRecord.executeOps();
        } else {
          backStackRecord.executePopOps(false);
        } 
        k = j - 1;
        if (i != k) {
          paramArrayList.remove(i);
          paramArrayList.add(k, backStackRecord);
        } 
        addAddedFragments(paramArraySet);
      } 
      i--;
    } 
    return j;
  }
  
  private void removeRedundantOperationsAndExecute(ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1) {
    if (paramArrayList == null || paramArrayList.isEmpty())
      return; 
    if (paramArrayList1 != null && paramArrayList.size() == paramArrayList1.size()) {
      executePostponedTransaction(paramArrayList, paramArrayList1);
      int i = paramArrayList.size();
      int j = 0;
      int k;
      for (k = 0; j < i; k = n) {
        int m = j;
        int n = k;
        if (!((BackStackRecord)paramArrayList.get(j)).mReorderingAllowed) {
          if (k != j)
            executeOpsTogether(paramArrayList, paramArrayList1, k, j); 
          k = j + 1;
          n = k;
          if (((Boolean)paramArrayList1.get(j)).booleanValue())
            while (true) {
              n = k;
              if (k < i) {
                n = k;
                if (((Boolean)paramArrayList1.get(k)).booleanValue()) {
                  n = k;
                  if (!((BackStackRecord)paramArrayList.get(k)).mReorderingAllowed) {
                    k++;
                    continue;
                  } 
                } 
              } 
              break;
            }  
          executeOpsTogether(paramArrayList, paramArrayList1, j, n);
          m = n - 1;
        } 
        j = m + 1;
      } 
      if (k != i)
        executeOpsTogether(paramArrayList, paramArrayList1, k, i); 
      return;
    } 
    throw new IllegalStateException("Internal error with the back stack records");
  }
  
  public static int reverseTransit(int paramInt) {
    char c = ' ';
    if (paramInt != 4097)
      if (paramInt != 4099) {
        if (paramInt != 8194) {
          c = Character.MIN_VALUE;
        } else {
          c = 'ခ';
        } 
      } else {
        c = 'ဃ';
      }  
    return c;
  }
  
  private void scheduleCommit() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mPostponedTransactions : Ljava/util/ArrayList;
    //   6: astore_1
    //   7: iconst_0
    //   8: istore_2
    //   9: aload_1
    //   10: ifnull -> 25
    //   13: aload_1
    //   14: invokevirtual isEmpty : ()Z
    //   17: ifne -> 25
    //   20: iconst_1
    //   21: istore_3
    //   22: goto -> 27
    //   25: iconst_0
    //   26: istore_3
    //   27: aload_0
    //   28: getfield mPendingActions : Ljava/util/ArrayList;
    //   31: astore_1
    //   32: iload_2
    //   33: istore #4
    //   35: aload_1
    //   36: ifnull -> 53
    //   39: iload_2
    //   40: istore #4
    //   42: aload_1
    //   43: invokevirtual size : ()I
    //   46: iconst_1
    //   47: if_icmpne -> 53
    //   50: iconst_1
    //   51: istore #4
    //   53: iload_3
    //   54: ifne -> 62
    //   57: iload #4
    //   59: ifeq -> 91
    //   62: aload_0
    //   63: getfield mHost : Landroid/support/v4/app/FragmentHostCallback;
    //   66: invokevirtual getHandler : ()Landroid/os/Handler;
    //   69: aload_0
    //   70: getfield mExecCommit : Ljava/lang/Runnable;
    //   73: invokevirtual removeCallbacks : (Ljava/lang/Runnable;)V
    //   76: aload_0
    //   77: getfield mHost : Landroid/support/v4/app/FragmentHostCallback;
    //   80: invokevirtual getHandler : ()Landroid/os/Handler;
    //   83: aload_0
    //   84: getfield mExecCommit : Ljava/lang/Runnable;
    //   87: invokevirtual post : (Ljava/lang/Runnable;)Z
    //   90: pop
    //   91: aload_0
    //   92: monitorexit
    //   93: return
    //   94: astore_1
    //   95: aload_0
    //   96: monitorexit
    //   97: aload_1
    //   98: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	94	finally
    //   13	20	94	finally
    //   27	32	94	finally
    //   42	50	94	finally
    //   62	91	94	finally
    //   91	93	94	finally
    //   95	97	94	finally
  }
  
  private static void setHWLayerAnimListenerIfAlpha(View paramView, AnimationOrAnimator paramAnimationOrAnimator) {
    if (paramView != null && paramAnimationOrAnimator != null && shouldRunOnHWLayer(paramView, paramAnimationOrAnimator)) {
      Animator animator = paramAnimationOrAnimator.animator;
      if (animator != null) {
        animator.addListener((Animator.AnimatorListener)new AnimatorOnHWLayerIfNeededListener(paramView));
      } else {
        Animation.AnimationListener animationListener = getAnimationListener(paramAnimationOrAnimator.animation);
        paramView.setLayerType(2, null);
        paramAnimationOrAnimator.animation.setAnimationListener(new AnimateOnHWLayerIfNeededListener(paramView, animationListener));
      } 
    } 
  }
  
  private static void setRetaining(FragmentManagerNonConfig paramFragmentManagerNonConfig) {
    if (paramFragmentManagerNonConfig == null)
      return; 
    List<Fragment> list1 = paramFragmentManagerNonConfig.getFragments();
    if (list1 != null) {
      Iterator<Fragment> iterator = list1.iterator();
      while (iterator.hasNext())
        ((Fragment)iterator.next()).mRetaining = true; 
    } 
    List<FragmentManagerNonConfig> list = paramFragmentManagerNonConfig.getChildNonConfigs();
    if (list != null) {
      Iterator<FragmentManagerNonConfig> iterator = list.iterator();
      while (iterator.hasNext())
        setRetaining(iterator.next()); 
    } 
  }
  
  static boolean shouldRunOnHWLayer(View paramView, AnimationOrAnimator paramAnimationOrAnimator) {
    boolean bool1 = false;
    boolean bool2 = bool1;
    if (paramView != null)
      if (paramAnimationOrAnimator == null) {
        bool2 = bool1;
      } else {
        bool2 = bool1;
        if (Build.VERSION.SDK_INT >= 19) {
          bool2 = bool1;
          if (paramView.getLayerType() == 0) {
            bool2 = bool1;
            if (ViewCompat.hasOverlappingRendering(paramView)) {
              bool2 = bool1;
              if (modifiesAlpha(paramAnimationOrAnimator))
                bool2 = true; 
            } 
          } 
        } 
      }  
    return bool2;
  }
  
  private void throwException(RuntimeException paramRuntimeException) {
    Log.e("FragmentManager", paramRuntimeException.getMessage());
    Log.e("FragmentManager", "Activity state:");
    PrintWriter printWriter = new PrintWriter((Writer)new LogWriter("FragmentManager"));
    FragmentHostCallback fragmentHostCallback = this.mHost;
    if (fragmentHostCallback != null) {
      try {
        fragmentHostCallback.onDump("  ", null, printWriter, new String[0]);
      } catch (Exception exception) {
        Log.e("FragmentManager", "Failed dumping state", exception);
      } 
    } else {
      try {
        dump("  ", null, (PrintWriter)exception, new String[0]);
      } catch (Exception exception1) {
        Log.e("FragmentManager", "Failed dumping state", exception1);
      } 
    } 
    throw paramRuntimeException;
  }
  
  public static int transitToStyleIndex(int paramInt, boolean paramBoolean) {
    if (paramInt != 4097) {
      if (paramInt != 4099) {
        if (paramInt != 8194) {
          paramInt = -1;
        } else if (paramBoolean) {
          paramInt = 3;
        } else {
          paramInt = 4;
        } 
      } else if (paramBoolean) {
        paramInt = 5;
      } else {
        paramInt = 6;
      } 
    } else if (paramBoolean) {
      paramInt = 1;
    } else {
      paramInt = 2;
    } 
    return paramInt;
  }
  
  void addBackStackState(BackStackRecord paramBackStackRecord) {
    if (this.mBackStack == null)
      this.mBackStack = new ArrayList<BackStackRecord>(); 
    this.mBackStack.add(paramBackStackRecord);
  }
  
  public void addFragment(Fragment paramFragment, boolean paramBoolean) {
    if (DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("add: ");
      stringBuilder.append(paramFragment);
      Log.v("FragmentManager", stringBuilder.toString());
    } 
    makeActive(paramFragment);
    if (!paramFragment.mDetached)
      if (!this.mAdded.contains(paramFragment)) {
        synchronized (this.mAdded) {
          this.mAdded.add(paramFragment);
          paramFragment.mAdded = true;
          paramFragment.mRemoving = false;
          if (paramFragment.mView == null)
            paramFragment.mHiddenChanged = false; 
          if (paramFragment.mHasMenu && paramFragment.mMenuVisible)
            this.mNeedMenuInvalidate = true; 
          if (paramBoolean)
            moveToState(paramFragment); 
        } 
      } else {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Fragment already added: ");
        stringBuilder.append(paramFragment);
        throw new IllegalStateException(stringBuilder.toString());
      }  
  }
  
  public void addOnBackStackChangedListener(FragmentManager.OnBackStackChangedListener paramOnBackStackChangedListener) {
    if (this.mBackStackChangeListeners == null)
      this.mBackStackChangeListeners = new ArrayList<FragmentManager.OnBackStackChangedListener>(); 
    this.mBackStackChangeListeners.add(paramOnBackStackChangedListener);
  }
  
  public int allocBackStackIndex(BackStackRecord paramBackStackRecord) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   6: astore_2
    //   7: aload_2
    //   8: ifnull -> 109
    //   11: aload_2
    //   12: invokevirtual size : ()I
    //   15: ifgt -> 21
    //   18: goto -> 109
    //   21: aload_0
    //   22: getfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   25: astore_2
    //   26: aload_2
    //   27: aload_2
    //   28: invokevirtual size : ()I
    //   31: iconst_1
    //   32: isub
    //   33: invokevirtual remove : (I)Ljava/lang/Object;
    //   36: checkcast java/lang/Integer
    //   39: invokevirtual intValue : ()I
    //   42: istore_3
    //   43: getstatic android/support/v4/app/FragmentManagerImpl.DEBUG : Z
    //   46: ifeq -> 95
    //   49: new java/lang/StringBuilder
    //   52: astore_2
    //   53: aload_2
    //   54: invokespecial <init> : ()V
    //   57: aload_2
    //   58: ldc_w 'Adding back stack index '
    //   61: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   64: pop
    //   65: aload_2
    //   66: iload_3
    //   67: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   70: pop
    //   71: aload_2
    //   72: ldc_w ' with '
    //   75: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   78: pop
    //   79: aload_2
    //   80: aload_1
    //   81: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   84: pop
    //   85: ldc 'FragmentManager'
    //   87: aload_2
    //   88: invokevirtual toString : ()Ljava/lang/String;
    //   91: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   94: pop
    //   95: aload_0
    //   96: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   99: iload_3
    //   100: aload_1
    //   101: invokevirtual set : (ILjava/lang/Object;)Ljava/lang/Object;
    //   104: pop
    //   105: aload_0
    //   106: monitorexit
    //   107: iload_3
    //   108: ireturn
    //   109: aload_0
    //   110: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   113: ifnonnull -> 129
    //   116: new java/util/ArrayList
    //   119: astore_2
    //   120: aload_2
    //   121: invokespecial <init> : ()V
    //   124: aload_0
    //   125: aload_2
    //   126: putfield mBackStackIndices : Ljava/util/ArrayList;
    //   129: aload_0
    //   130: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   133: invokevirtual size : ()I
    //   136: istore_3
    //   137: getstatic android/support/v4/app/FragmentManagerImpl.DEBUG : Z
    //   140: ifeq -> 189
    //   143: new java/lang/StringBuilder
    //   146: astore_2
    //   147: aload_2
    //   148: invokespecial <init> : ()V
    //   151: aload_2
    //   152: ldc_w 'Setting back stack index '
    //   155: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   158: pop
    //   159: aload_2
    //   160: iload_3
    //   161: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   164: pop
    //   165: aload_2
    //   166: ldc_w ' to '
    //   169: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   172: pop
    //   173: aload_2
    //   174: aload_1
    //   175: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   178: pop
    //   179: ldc 'FragmentManager'
    //   181: aload_2
    //   182: invokevirtual toString : ()Ljava/lang/String;
    //   185: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   188: pop
    //   189: aload_0
    //   190: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   193: aload_1
    //   194: invokevirtual add : (Ljava/lang/Object;)Z
    //   197: pop
    //   198: aload_0
    //   199: monitorexit
    //   200: iload_3
    //   201: ireturn
    //   202: astore_1
    //   203: aload_0
    //   204: monitorexit
    //   205: aload_1
    //   206: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	202	finally
    //   11	18	202	finally
    //   21	95	202	finally
    //   95	107	202	finally
    //   109	129	202	finally
    //   129	189	202	finally
    //   189	200	202	finally
    //   203	205	202	finally
  }
  
  public void attachController(FragmentHostCallback paramFragmentHostCallback, FragmentContainer paramFragmentContainer, Fragment paramFragment) {
    if (this.mHost == null) {
      this.mHost = paramFragmentHostCallback;
      this.mContainer = paramFragmentContainer;
      this.mParent = paramFragment;
      return;
    } 
    throw new IllegalStateException("Already attached");
  }
  
  public void attachFragment(Fragment paramFragment) {
    if (DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("attach: ");
      stringBuilder.append(paramFragment);
      Log.v("FragmentManager", stringBuilder.toString());
    } 
    if (paramFragment.mDetached) {
      paramFragment.mDetached = false;
      if (!paramFragment.mAdded)
        if (!this.mAdded.contains(paramFragment)) {
          if (DEBUG) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("add from attach: ");
            stringBuilder.append(paramFragment);
            Log.v("FragmentManager", stringBuilder.toString());
          } 
          synchronized (this.mAdded) {
            this.mAdded.add(paramFragment);
            paramFragment.mAdded = true;
            if (paramFragment.mHasMenu && paramFragment.mMenuVisible)
              this.mNeedMenuInvalidate = true; 
          } 
        } else {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Fragment already added: ");
          stringBuilder.append(paramFragment);
          throw new IllegalStateException(stringBuilder.toString());
        }  
    } 
  }
  
  public FragmentTransaction beginTransaction() {
    return new BackStackRecord(this);
  }
  
  void completeShowHideFragment(Fragment paramFragment) {
    // Byte code:
    //   0: aload_1
    //   1: getfield mView : Landroid/view/View;
    //   4: ifnull -> 210
    //   7: aload_0
    //   8: aload_1
    //   9: aload_1
    //   10: invokevirtual getNextTransition : ()I
    //   13: aload_1
    //   14: getfield mHidden : Z
    //   17: iconst_1
    //   18: ixor
    //   19: aload_1
    //   20: invokevirtual getNextTransitionStyle : ()I
    //   23: invokevirtual loadAnimation : (Landroid/support/v4/app/Fragment;IZI)Landroid/support/v4/app/FragmentManagerImpl$AnimationOrAnimator;
    //   26: astore_2
    //   27: aload_2
    //   28: ifnull -> 135
    //   31: aload_2
    //   32: getfield animator : Landroid/animation/Animator;
    //   35: astore_3
    //   36: aload_3
    //   37: ifnull -> 135
    //   40: aload_3
    //   41: aload_1
    //   42: getfield mView : Landroid/view/View;
    //   45: invokevirtual setTarget : (Ljava/lang/Object;)V
    //   48: aload_1
    //   49: getfield mHidden : Z
    //   52: ifeq -> 109
    //   55: aload_1
    //   56: invokevirtual isHideReplaced : ()Z
    //   59: ifeq -> 70
    //   62: aload_1
    //   63: iconst_0
    //   64: invokevirtual setHideReplaced : (Z)V
    //   67: goto -> 117
    //   70: aload_1
    //   71: getfield mContainer : Landroid/view/ViewGroup;
    //   74: astore #4
    //   76: aload_1
    //   77: getfield mView : Landroid/view/View;
    //   80: astore_3
    //   81: aload #4
    //   83: aload_3
    //   84: invokevirtual startViewTransition : (Landroid/view/View;)V
    //   87: aload_2
    //   88: getfield animator : Landroid/animation/Animator;
    //   91: new android/support/v4/app/FragmentManagerImpl$4
    //   94: dup
    //   95: aload_0
    //   96: aload #4
    //   98: aload_3
    //   99: aload_1
    //   100: invokespecial <init> : (Landroid/support/v4/app/FragmentManagerImpl;Landroid/view/ViewGroup;Landroid/view/View;Landroid/support/v4/app/Fragment;)V
    //   103: invokevirtual addListener : (Landroid/animation/Animator$AnimatorListener;)V
    //   106: goto -> 117
    //   109: aload_1
    //   110: getfield mView : Landroid/view/View;
    //   113: iconst_0
    //   114: invokevirtual setVisibility : (I)V
    //   117: aload_1
    //   118: getfield mView : Landroid/view/View;
    //   121: aload_2
    //   122: invokestatic setHWLayerAnimListenerIfAlpha : (Landroid/view/View;Landroid/support/v4/app/FragmentManagerImpl$AnimationOrAnimator;)V
    //   125: aload_2
    //   126: getfield animator : Landroid/animation/Animator;
    //   129: invokevirtual start : ()V
    //   132: goto -> 210
    //   135: aload_2
    //   136: ifnull -> 165
    //   139: aload_1
    //   140: getfield mView : Landroid/view/View;
    //   143: aload_2
    //   144: invokestatic setHWLayerAnimListenerIfAlpha : (Landroid/view/View;Landroid/support/v4/app/FragmentManagerImpl$AnimationOrAnimator;)V
    //   147: aload_1
    //   148: getfield mView : Landroid/view/View;
    //   151: aload_2
    //   152: getfield animation : Landroid/view/animation/Animation;
    //   155: invokevirtual startAnimation : (Landroid/view/animation/Animation;)V
    //   158: aload_2
    //   159: getfield animation : Landroid/view/animation/Animation;
    //   162: invokevirtual start : ()V
    //   165: aload_1
    //   166: getfield mHidden : Z
    //   169: ifeq -> 186
    //   172: aload_1
    //   173: invokevirtual isHideReplaced : ()Z
    //   176: ifne -> 186
    //   179: bipush #8
    //   181: istore #5
    //   183: goto -> 189
    //   186: iconst_0
    //   187: istore #5
    //   189: aload_1
    //   190: getfield mView : Landroid/view/View;
    //   193: iload #5
    //   195: invokevirtual setVisibility : (I)V
    //   198: aload_1
    //   199: invokevirtual isHideReplaced : ()Z
    //   202: ifeq -> 210
    //   205: aload_1
    //   206: iconst_0
    //   207: invokevirtual setHideReplaced : (Z)V
    //   210: aload_1
    //   211: getfield mAdded : Z
    //   214: ifeq -> 236
    //   217: aload_1
    //   218: getfield mHasMenu : Z
    //   221: ifeq -> 236
    //   224: aload_1
    //   225: getfield mMenuVisible : Z
    //   228: ifeq -> 236
    //   231: aload_0
    //   232: iconst_1
    //   233: putfield mNeedMenuInvalidate : Z
    //   236: aload_1
    //   237: iconst_0
    //   238: putfield mHiddenChanged : Z
    //   241: aload_1
    //   242: aload_1
    //   243: getfield mHidden : Z
    //   246: invokevirtual onHiddenChanged : (Z)V
    //   249: return
  }
  
  public void detachFragment(Fragment paramFragment) {
    if (DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("detach: ");
      stringBuilder.append(paramFragment);
      Log.v("FragmentManager", stringBuilder.toString());
    } 
    if (!paramFragment.mDetached) {
      paramFragment.mDetached = true;
      if (paramFragment.mAdded) {
        if (DEBUG) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("remove from detach: ");
          stringBuilder.append(paramFragment);
          Log.v("FragmentManager", stringBuilder.toString());
        } 
        synchronized (this.mAdded) {
          this.mAdded.remove(paramFragment);
          if (paramFragment.mHasMenu && paramFragment.mMenuVisible)
            this.mNeedMenuInvalidate = true; 
          paramFragment.mAdded = false;
        } 
      } 
    } 
  }
  
  public void dispatchActivityCreated() {
    this.mStateSaved = false;
    dispatchStateChange(2);
  }
  
  public void dispatchConfigurationChanged(Configuration paramConfiguration) {
    for (byte b = 0; b < this.mAdded.size(); b++) {
      Fragment fragment = this.mAdded.get(b);
      if (fragment != null)
        fragment.performConfigurationChanged(paramConfiguration); 
    } 
  }
  
  public boolean dispatchContextItemSelected(MenuItem paramMenuItem) {
    for (byte b = 0; b < this.mAdded.size(); b++) {
      Fragment fragment = this.mAdded.get(b);
      if (fragment != null && fragment.performContextItemSelected(paramMenuItem))
        return true; 
    } 
    return false;
  }
  
  public void dispatchCreate() {
    this.mStateSaved = false;
    dispatchStateChange(1);
  }
  
  public boolean dispatchCreateOptionsMenu(Menu paramMenu, MenuInflater paramMenuInflater) {
    boolean bool1 = false;
    ArrayList<Fragment> arrayList = null;
    byte b = 0;
    boolean bool2;
    for (bool2 = false; b < this.mAdded.size(); bool2 = bool) {
      Fragment fragment = this.mAdded.get(b);
      ArrayList<Fragment> arrayList1 = arrayList;
      boolean bool = bool2;
      if (fragment != null) {
        arrayList1 = arrayList;
        bool = bool2;
        if (fragment.performCreateOptionsMenu(paramMenu, paramMenuInflater)) {
          arrayList1 = arrayList;
          if (arrayList == null)
            arrayList1 = new ArrayList(); 
          arrayList1.add(fragment);
          bool = true;
        } 
      } 
      b++;
      arrayList = arrayList1;
    } 
    if (this.mCreatedMenus != null)
      for (b = bool1; b < this.mCreatedMenus.size(); b++) {
        Fragment fragment = this.mCreatedMenus.get(b);
        if (arrayList == null || !arrayList.contains(fragment))
          fragment.onDestroyOptionsMenu(); 
      }  
    this.mCreatedMenus = arrayList;
    return bool2;
  }
  
  public void dispatchDestroy() {
    this.mDestroyed = true;
    execPendingActions();
    dispatchStateChange(0);
    this.mHost = null;
    this.mContainer = null;
    this.mParent = null;
  }
  
  public void dispatchDestroyView() {
    dispatchStateChange(1);
  }
  
  public void dispatchLowMemory() {
    for (byte b = 0; b < this.mAdded.size(); b++) {
      Fragment fragment = this.mAdded.get(b);
      if (fragment != null)
        fragment.performLowMemory(); 
    } 
  }
  
  public void dispatchMultiWindowModeChanged(boolean paramBoolean) {
    for (int i = this.mAdded.size() - 1; i >= 0; i--) {
      Fragment fragment = this.mAdded.get(i);
      if (fragment != null)
        fragment.performMultiWindowModeChanged(paramBoolean); 
    } 
  }
  
  void dispatchOnFragmentActivityCreated(Fragment paramFragment, Bundle paramBundle, boolean paramBoolean) {
    Fragment fragment = this.mParent;
    if (fragment != null) {
      FragmentManager fragmentManager = fragment.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentActivityCreated(paramFragment, paramBundle, true); 
    } 
    for (Pair<FragmentManager.FragmentLifecycleCallbacks, Boolean> pair : this.mLifecycleCallbacks) {
      if (!paramBoolean || ((Boolean)pair.second).booleanValue())
        ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentActivityCreated(this, paramFragment, paramBundle); 
    } 
  }
  
  void dispatchOnFragmentAttached(Fragment paramFragment, Context paramContext, boolean paramBoolean) {
    Fragment fragment = this.mParent;
    if (fragment != null) {
      FragmentManager fragmentManager = fragment.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentAttached(paramFragment, paramContext, true); 
    } 
    for (Pair<FragmentManager.FragmentLifecycleCallbacks, Boolean> pair : this.mLifecycleCallbacks) {
      if (!paramBoolean || ((Boolean)pair.second).booleanValue())
        ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentAttached(this, paramFragment, paramContext); 
    } 
  }
  
  void dispatchOnFragmentCreated(Fragment paramFragment, Bundle paramBundle, boolean paramBoolean) {
    Fragment fragment = this.mParent;
    if (fragment != null) {
      FragmentManager fragmentManager = fragment.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentCreated(paramFragment, paramBundle, true); 
    } 
    for (Pair<FragmentManager.FragmentLifecycleCallbacks, Boolean> pair : this.mLifecycleCallbacks) {
      if (!paramBoolean || ((Boolean)pair.second).booleanValue())
        ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentCreated(this, paramFragment, paramBundle); 
    } 
  }
  
  void dispatchOnFragmentDestroyed(Fragment paramFragment, boolean paramBoolean) {
    Fragment fragment = this.mParent;
    if (fragment != null) {
      FragmentManager fragmentManager = fragment.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentDestroyed(paramFragment, true); 
    } 
    for (Pair<FragmentManager.FragmentLifecycleCallbacks, Boolean> pair : this.mLifecycleCallbacks) {
      if (!paramBoolean || ((Boolean)pair.second).booleanValue())
        ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentDestroyed(this, paramFragment); 
    } 
  }
  
  void dispatchOnFragmentDetached(Fragment paramFragment, boolean paramBoolean) {
    Fragment fragment = this.mParent;
    if (fragment != null) {
      FragmentManager fragmentManager = fragment.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentDetached(paramFragment, true); 
    } 
    for (Pair<FragmentManager.FragmentLifecycleCallbacks, Boolean> pair : this.mLifecycleCallbacks) {
      if (!paramBoolean || ((Boolean)pair.second).booleanValue())
        ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentDetached(this, paramFragment); 
    } 
  }
  
  void dispatchOnFragmentPaused(Fragment paramFragment, boolean paramBoolean) {
    Fragment fragment = this.mParent;
    if (fragment != null) {
      FragmentManager fragmentManager = fragment.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentPaused(paramFragment, true); 
    } 
    for (Pair<FragmentManager.FragmentLifecycleCallbacks, Boolean> pair : this.mLifecycleCallbacks) {
      if (!paramBoolean || ((Boolean)pair.second).booleanValue())
        ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentPaused(this, paramFragment); 
    } 
  }
  
  void dispatchOnFragmentPreAttached(Fragment paramFragment, Context paramContext, boolean paramBoolean) {
    Fragment fragment = this.mParent;
    if (fragment != null) {
      FragmentManager fragmentManager = fragment.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentPreAttached(paramFragment, paramContext, true); 
    } 
    for (Pair<FragmentManager.FragmentLifecycleCallbacks, Boolean> pair : this.mLifecycleCallbacks) {
      if (!paramBoolean || ((Boolean)pair.second).booleanValue())
        ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentPreAttached(this, paramFragment, paramContext); 
    } 
  }
  
  void dispatchOnFragmentPreCreated(Fragment paramFragment, Bundle paramBundle, boolean paramBoolean) {
    Fragment fragment = this.mParent;
    if (fragment != null) {
      FragmentManager fragmentManager = fragment.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentPreCreated(paramFragment, paramBundle, true); 
    } 
    for (Pair<FragmentManager.FragmentLifecycleCallbacks, Boolean> pair : this.mLifecycleCallbacks) {
      if (!paramBoolean || ((Boolean)pair.second).booleanValue())
        ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentPreCreated(this, paramFragment, paramBundle); 
    } 
  }
  
  void dispatchOnFragmentResumed(Fragment paramFragment, boolean paramBoolean) {
    Fragment fragment = this.mParent;
    if (fragment != null) {
      FragmentManager fragmentManager = fragment.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentResumed(paramFragment, true); 
    } 
    for (Pair<FragmentManager.FragmentLifecycleCallbacks, Boolean> pair : this.mLifecycleCallbacks) {
      if (!paramBoolean || ((Boolean)pair.second).booleanValue())
        ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentResumed(this, paramFragment); 
    } 
  }
  
  void dispatchOnFragmentSaveInstanceState(Fragment paramFragment, Bundle paramBundle, boolean paramBoolean) {
    Fragment fragment = this.mParent;
    if (fragment != null) {
      FragmentManager fragmentManager = fragment.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentSaveInstanceState(paramFragment, paramBundle, true); 
    } 
    for (Pair<FragmentManager.FragmentLifecycleCallbacks, Boolean> pair : this.mLifecycleCallbacks) {
      if (!paramBoolean || ((Boolean)pair.second).booleanValue())
        ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentSaveInstanceState(this, paramFragment, paramBundle); 
    } 
  }
  
  void dispatchOnFragmentStarted(Fragment paramFragment, boolean paramBoolean) {
    Fragment fragment = this.mParent;
    if (fragment != null) {
      FragmentManager fragmentManager = fragment.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentStarted(paramFragment, true); 
    } 
    for (Pair<FragmentManager.FragmentLifecycleCallbacks, Boolean> pair : this.mLifecycleCallbacks) {
      if (!paramBoolean || ((Boolean)pair.second).booleanValue())
        ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentStarted(this, paramFragment); 
    } 
  }
  
  void dispatchOnFragmentStopped(Fragment paramFragment, boolean paramBoolean) {
    Fragment fragment = this.mParent;
    if (fragment != null) {
      FragmentManager fragmentManager = fragment.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentStopped(paramFragment, true); 
    } 
    for (Pair<FragmentManager.FragmentLifecycleCallbacks, Boolean> pair : this.mLifecycleCallbacks) {
      if (!paramBoolean || ((Boolean)pair.second).booleanValue())
        ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentStopped(this, paramFragment); 
    } 
  }
  
  void dispatchOnFragmentViewCreated(Fragment paramFragment, View paramView, Bundle paramBundle, boolean paramBoolean) {
    Fragment fragment = this.mParent;
    if (fragment != null) {
      FragmentManager fragmentManager = fragment.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentViewCreated(paramFragment, paramView, paramBundle, true); 
    } 
    for (Pair<FragmentManager.FragmentLifecycleCallbacks, Boolean> pair : this.mLifecycleCallbacks) {
      if (!paramBoolean || ((Boolean)pair.second).booleanValue())
        ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentViewCreated(this, paramFragment, paramView, paramBundle); 
    } 
  }
  
  void dispatchOnFragmentViewDestroyed(Fragment paramFragment, boolean paramBoolean) {
    Fragment fragment = this.mParent;
    if (fragment != null) {
      FragmentManager fragmentManager = fragment.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentViewDestroyed(paramFragment, true); 
    } 
    for (Pair<FragmentManager.FragmentLifecycleCallbacks, Boolean> pair : this.mLifecycleCallbacks) {
      if (!paramBoolean || ((Boolean)pair.second).booleanValue())
        ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentViewDestroyed(this, paramFragment); 
    } 
  }
  
  public boolean dispatchOptionsItemSelected(MenuItem paramMenuItem) {
    for (byte b = 0; b < this.mAdded.size(); b++) {
      Fragment fragment = this.mAdded.get(b);
      if (fragment != null && fragment.performOptionsItemSelected(paramMenuItem))
        return true; 
    } 
    return false;
  }
  
  public void dispatchOptionsMenuClosed(Menu paramMenu) {
    for (byte b = 0; b < this.mAdded.size(); b++) {
      Fragment fragment = this.mAdded.get(b);
      if (fragment != null)
        fragment.performOptionsMenuClosed(paramMenu); 
    } 
  }
  
  public void dispatchPause() {
    dispatchStateChange(4);
  }
  
  public void dispatchPictureInPictureModeChanged(boolean paramBoolean) {
    for (int i = this.mAdded.size() - 1; i >= 0; i--) {
      Fragment fragment = this.mAdded.get(i);
      if (fragment != null)
        fragment.performPictureInPictureModeChanged(paramBoolean); 
    } 
  }
  
  public boolean dispatchPrepareOptionsMenu(Menu paramMenu) {
    byte b = 0;
    boolean bool;
    for (bool = false; b < this.mAdded.size(); bool = bool1) {
      Fragment fragment = this.mAdded.get(b);
      boolean bool1 = bool;
      if (fragment != null) {
        bool1 = bool;
        if (fragment.performPrepareOptionsMenu(paramMenu))
          bool1 = true; 
      } 
      b++;
    } 
    return bool;
  }
  
  public void dispatchReallyStop() {
    dispatchStateChange(2);
  }
  
  public void dispatchResume() {
    this.mStateSaved = false;
    dispatchStateChange(5);
  }
  
  public void dispatchStart() {
    this.mStateSaved = false;
    dispatchStateChange(4);
  }
  
  public void dispatchStop() {
    this.mStateSaved = true;
    dispatchStateChange(3);
  }
  
  void doPendingDeferredStart() {
    if (this.mHavePendingDeferredStart) {
      byte b = 0;
      boolean bool;
      for (bool = false; b < this.mActive.size(); bool = bool1) {
        Fragment fragment = (Fragment)this.mActive.valueAt(b);
        boolean bool1 = bool;
        if (fragment != null) {
          LoaderManagerImpl loaderManagerImpl = fragment.mLoaderManager;
          bool1 = bool;
          if (loaderManagerImpl != null)
            bool1 = bool | loaderManagerImpl.hasRunningLoaders(); 
        } 
        b++;
      } 
      if (!bool) {
        this.mHavePendingDeferredStart = false;
        startPendingDeferredFragments();
      } 
    } 
  }
  
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {
    // Byte code:
    //   0: new java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: astore #5
    //   9: aload #5
    //   11: aload_1
    //   12: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   15: pop
    //   16: aload #5
    //   18: ldc_w '    '
    //   21: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   24: pop
    //   25: aload #5
    //   27: invokevirtual toString : ()Ljava/lang/String;
    //   30: astore #5
    //   32: aload_0
    //   33: getfield mActive : Landroid/util/SparseArray;
    //   36: astore #6
    //   38: iconst_0
    //   39: istore #7
    //   41: aload #6
    //   43: ifnull -> 165
    //   46: aload #6
    //   48: invokevirtual size : ()I
    //   51: istore #8
    //   53: iload #8
    //   55: ifle -> 165
    //   58: aload_3
    //   59: aload_1
    //   60: invokevirtual print : (Ljava/lang/String;)V
    //   63: aload_3
    //   64: ldc_w 'Active Fragments in '
    //   67: invokevirtual print : (Ljava/lang/String;)V
    //   70: aload_3
    //   71: aload_0
    //   72: invokestatic identityHashCode : (Ljava/lang/Object;)I
    //   75: invokestatic toHexString : (I)Ljava/lang/String;
    //   78: invokevirtual print : (Ljava/lang/String;)V
    //   81: aload_3
    //   82: ldc_w ':'
    //   85: invokevirtual println : (Ljava/lang/String;)V
    //   88: iconst_0
    //   89: istore #9
    //   91: iload #9
    //   93: iload #8
    //   95: if_icmpge -> 165
    //   98: aload_0
    //   99: getfield mActive : Landroid/util/SparseArray;
    //   102: iload #9
    //   104: invokevirtual valueAt : (I)Ljava/lang/Object;
    //   107: checkcast android/support/v4/app/Fragment
    //   110: astore #6
    //   112: aload_3
    //   113: aload_1
    //   114: invokevirtual print : (Ljava/lang/String;)V
    //   117: aload_3
    //   118: ldc_w '  #'
    //   121: invokevirtual print : (Ljava/lang/String;)V
    //   124: aload_3
    //   125: iload #9
    //   127: invokevirtual print : (I)V
    //   130: aload_3
    //   131: ldc_w ': '
    //   134: invokevirtual print : (Ljava/lang/String;)V
    //   137: aload_3
    //   138: aload #6
    //   140: invokevirtual println : (Ljava/lang/Object;)V
    //   143: aload #6
    //   145: ifnull -> 159
    //   148: aload #6
    //   150: aload #5
    //   152: aload_2
    //   153: aload_3
    //   154: aload #4
    //   156: invokevirtual dump : (Ljava/lang/String;Ljava/io/FileDescriptor;Ljava/io/PrintWriter;[Ljava/lang/String;)V
    //   159: iinc #9, 1
    //   162: goto -> 91
    //   165: aload_0
    //   166: getfield mAdded : Ljava/util/ArrayList;
    //   169: invokevirtual size : ()I
    //   172: istore #8
    //   174: iload #8
    //   176: ifle -> 255
    //   179: aload_3
    //   180: aload_1
    //   181: invokevirtual print : (Ljava/lang/String;)V
    //   184: aload_3
    //   185: ldc_w 'Added Fragments:'
    //   188: invokevirtual println : (Ljava/lang/String;)V
    //   191: iconst_0
    //   192: istore #9
    //   194: iload #9
    //   196: iload #8
    //   198: if_icmpge -> 255
    //   201: aload_0
    //   202: getfield mAdded : Ljava/util/ArrayList;
    //   205: iload #9
    //   207: invokevirtual get : (I)Ljava/lang/Object;
    //   210: checkcast android/support/v4/app/Fragment
    //   213: astore #6
    //   215: aload_3
    //   216: aload_1
    //   217: invokevirtual print : (Ljava/lang/String;)V
    //   220: aload_3
    //   221: ldc_w '  #'
    //   224: invokevirtual print : (Ljava/lang/String;)V
    //   227: aload_3
    //   228: iload #9
    //   230: invokevirtual print : (I)V
    //   233: aload_3
    //   234: ldc_w ': '
    //   237: invokevirtual print : (Ljava/lang/String;)V
    //   240: aload_3
    //   241: aload #6
    //   243: invokevirtual toString : ()Ljava/lang/String;
    //   246: invokevirtual println : (Ljava/lang/String;)V
    //   249: iinc #9, 1
    //   252: goto -> 194
    //   255: aload_0
    //   256: getfield mCreatedMenus : Ljava/util/ArrayList;
    //   259: astore #6
    //   261: aload #6
    //   263: ifnull -> 354
    //   266: aload #6
    //   268: invokevirtual size : ()I
    //   271: istore #8
    //   273: iload #8
    //   275: ifle -> 354
    //   278: aload_3
    //   279: aload_1
    //   280: invokevirtual print : (Ljava/lang/String;)V
    //   283: aload_3
    //   284: ldc_w 'Fragments Created Menus:'
    //   287: invokevirtual println : (Ljava/lang/String;)V
    //   290: iconst_0
    //   291: istore #9
    //   293: iload #9
    //   295: iload #8
    //   297: if_icmpge -> 354
    //   300: aload_0
    //   301: getfield mCreatedMenus : Ljava/util/ArrayList;
    //   304: iload #9
    //   306: invokevirtual get : (I)Ljava/lang/Object;
    //   309: checkcast android/support/v4/app/Fragment
    //   312: astore #6
    //   314: aload_3
    //   315: aload_1
    //   316: invokevirtual print : (Ljava/lang/String;)V
    //   319: aload_3
    //   320: ldc_w '  #'
    //   323: invokevirtual print : (Ljava/lang/String;)V
    //   326: aload_3
    //   327: iload #9
    //   329: invokevirtual print : (I)V
    //   332: aload_3
    //   333: ldc_w ': '
    //   336: invokevirtual print : (Ljava/lang/String;)V
    //   339: aload_3
    //   340: aload #6
    //   342: invokevirtual toString : ()Ljava/lang/String;
    //   345: invokevirtual println : (Ljava/lang/String;)V
    //   348: iinc #9, 1
    //   351: goto -> 293
    //   354: aload_0
    //   355: getfield mBackStack : Ljava/util/ArrayList;
    //   358: astore #6
    //   360: aload #6
    //   362: ifnull -> 464
    //   365: aload #6
    //   367: invokevirtual size : ()I
    //   370: istore #8
    //   372: iload #8
    //   374: ifle -> 464
    //   377: aload_3
    //   378: aload_1
    //   379: invokevirtual print : (Ljava/lang/String;)V
    //   382: aload_3
    //   383: ldc_w 'Back Stack:'
    //   386: invokevirtual println : (Ljava/lang/String;)V
    //   389: iconst_0
    //   390: istore #9
    //   392: iload #9
    //   394: iload #8
    //   396: if_icmpge -> 464
    //   399: aload_0
    //   400: getfield mBackStack : Ljava/util/ArrayList;
    //   403: iload #9
    //   405: invokevirtual get : (I)Ljava/lang/Object;
    //   408: checkcast android/support/v4/app/BackStackRecord
    //   411: astore #6
    //   413: aload_3
    //   414: aload_1
    //   415: invokevirtual print : (Ljava/lang/String;)V
    //   418: aload_3
    //   419: ldc_w '  #'
    //   422: invokevirtual print : (Ljava/lang/String;)V
    //   425: aload_3
    //   426: iload #9
    //   428: invokevirtual print : (I)V
    //   431: aload_3
    //   432: ldc_w ': '
    //   435: invokevirtual print : (Ljava/lang/String;)V
    //   438: aload_3
    //   439: aload #6
    //   441: invokevirtual toString : ()Ljava/lang/String;
    //   444: invokevirtual println : (Ljava/lang/String;)V
    //   447: aload #6
    //   449: aload #5
    //   451: aload_2
    //   452: aload_3
    //   453: aload #4
    //   455: invokevirtual dump : (Ljava/lang/String;Ljava/io/FileDescriptor;Ljava/io/PrintWriter;[Ljava/lang/String;)V
    //   458: iinc #9, 1
    //   461: goto -> 392
    //   464: aload_0
    //   465: monitorenter
    //   466: aload_0
    //   467: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   470: astore_2
    //   471: aload_2
    //   472: ifnull -> 557
    //   475: aload_2
    //   476: invokevirtual size : ()I
    //   479: istore #8
    //   481: iload #8
    //   483: ifle -> 557
    //   486: aload_3
    //   487: aload_1
    //   488: invokevirtual print : (Ljava/lang/String;)V
    //   491: aload_3
    //   492: ldc_w 'Back Stack Indices:'
    //   495: invokevirtual println : (Ljava/lang/String;)V
    //   498: iconst_0
    //   499: istore #9
    //   501: iload #9
    //   503: iload #8
    //   505: if_icmpge -> 557
    //   508: aload_0
    //   509: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   512: iload #9
    //   514: invokevirtual get : (I)Ljava/lang/Object;
    //   517: checkcast android/support/v4/app/BackStackRecord
    //   520: astore_2
    //   521: aload_3
    //   522: aload_1
    //   523: invokevirtual print : (Ljava/lang/String;)V
    //   526: aload_3
    //   527: ldc_w '  #'
    //   530: invokevirtual print : (Ljava/lang/String;)V
    //   533: aload_3
    //   534: iload #9
    //   536: invokevirtual print : (I)V
    //   539: aload_3
    //   540: ldc_w ': '
    //   543: invokevirtual print : (Ljava/lang/String;)V
    //   546: aload_3
    //   547: aload_2
    //   548: invokevirtual println : (Ljava/lang/Object;)V
    //   551: iinc #9, 1
    //   554: goto -> 501
    //   557: aload_0
    //   558: getfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   561: astore_2
    //   562: aload_2
    //   563: ifnull -> 599
    //   566: aload_2
    //   567: invokevirtual size : ()I
    //   570: ifle -> 599
    //   573: aload_3
    //   574: aload_1
    //   575: invokevirtual print : (Ljava/lang/String;)V
    //   578: aload_3
    //   579: ldc_w 'mAvailBackStackIndices: '
    //   582: invokevirtual print : (Ljava/lang/String;)V
    //   585: aload_3
    //   586: aload_0
    //   587: getfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   590: invokevirtual toArray : ()[Ljava/lang/Object;
    //   593: invokestatic toString : ([Ljava/lang/Object;)Ljava/lang/String;
    //   596: invokevirtual println : (Ljava/lang/String;)V
    //   599: aload_0
    //   600: monitorexit
    //   601: aload_0
    //   602: getfield mPendingActions : Ljava/util/ArrayList;
    //   605: astore_2
    //   606: aload_2
    //   607: ifnull -> 693
    //   610: aload_2
    //   611: invokevirtual size : ()I
    //   614: istore #8
    //   616: iload #8
    //   618: ifle -> 693
    //   621: aload_3
    //   622: aload_1
    //   623: invokevirtual print : (Ljava/lang/String;)V
    //   626: aload_3
    //   627: ldc_w 'Pending Actions:'
    //   630: invokevirtual println : (Ljava/lang/String;)V
    //   633: iload #7
    //   635: istore #9
    //   637: iload #9
    //   639: iload #8
    //   641: if_icmpge -> 693
    //   644: aload_0
    //   645: getfield mPendingActions : Ljava/util/ArrayList;
    //   648: iload #9
    //   650: invokevirtual get : (I)Ljava/lang/Object;
    //   653: checkcast android/support/v4/app/FragmentManagerImpl$OpGenerator
    //   656: astore_2
    //   657: aload_3
    //   658: aload_1
    //   659: invokevirtual print : (Ljava/lang/String;)V
    //   662: aload_3
    //   663: ldc_w '  #'
    //   666: invokevirtual print : (Ljava/lang/String;)V
    //   669: aload_3
    //   670: iload #9
    //   672: invokevirtual print : (I)V
    //   675: aload_3
    //   676: ldc_w ': '
    //   679: invokevirtual print : (Ljava/lang/String;)V
    //   682: aload_3
    //   683: aload_2
    //   684: invokevirtual println : (Ljava/lang/Object;)V
    //   687: iinc #9, 1
    //   690: goto -> 637
    //   693: aload_3
    //   694: aload_1
    //   695: invokevirtual print : (Ljava/lang/String;)V
    //   698: aload_3
    //   699: ldc_w 'FragmentManager misc state:'
    //   702: invokevirtual println : (Ljava/lang/String;)V
    //   705: aload_3
    //   706: aload_1
    //   707: invokevirtual print : (Ljava/lang/String;)V
    //   710: aload_3
    //   711: ldc_w '  mHost='
    //   714: invokevirtual print : (Ljava/lang/String;)V
    //   717: aload_3
    //   718: aload_0
    //   719: getfield mHost : Landroid/support/v4/app/FragmentHostCallback;
    //   722: invokevirtual println : (Ljava/lang/Object;)V
    //   725: aload_3
    //   726: aload_1
    //   727: invokevirtual print : (Ljava/lang/String;)V
    //   730: aload_3
    //   731: ldc_w '  mContainer='
    //   734: invokevirtual print : (Ljava/lang/String;)V
    //   737: aload_3
    //   738: aload_0
    //   739: getfield mContainer : Landroid/support/v4/app/FragmentContainer;
    //   742: invokevirtual println : (Ljava/lang/Object;)V
    //   745: aload_0
    //   746: getfield mParent : Landroid/support/v4/app/Fragment;
    //   749: ifnull -> 772
    //   752: aload_3
    //   753: aload_1
    //   754: invokevirtual print : (Ljava/lang/String;)V
    //   757: aload_3
    //   758: ldc_w '  mParent='
    //   761: invokevirtual print : (Ljava/lang/String;)V
    //   764: aload_3
    //   765: aload_0
    //   766: getfield mParent : Landroid/support/v4/app/Fragment;
    //   769: invokevirtual println : (Ljava/lang/Object;)V
    //   772: aload_3
    //   773: aload_1
    //   774: invokevirtual print : (Ljava/lang/String;)V
    //   777: aload_3
    //   778: ldc_w '  mCurState='
    //   781: invokevirtual print : (Ljava/lang/String;)V
    //   784: aload_3
    //   785: aload_0
    //   786: getfield mCurState : I
    //   789: invokevirtual print : (I)V
    //   792: aload_3
    //   793: ldc_w ' mStateSaved='
    //   796: invokevirtual print : (Ljava/lang/String;)V
    //   799: aload_3
    //   800: aload_0
    //   801: getfield mStateSaved : Z
    //   804: invokevirtual print : (Z)V
    //   807: aload_3
    //   808: ldc_w ' mDestroyed='
    //   811: invokevirtual print : (Ljava/lang/String;)V
    //   814: aload_3
    //   815: aload_0
    //   816: getfield mDestroyed : Z
    //   819: invokevirtual println : (Z)V
    //   822: aload_0
    //   823: getfield mNeedMenuInvalidate : Z
    //   826: ifeq -> 849
    //   829: aload_3
    //   830: aload_1
    //   831: invokevirtual print : (Ljava/lang/String;)V
    //   834: aload_3
    //   835: ldc_w '  mNeedMenuInvalidate='
    //   838: invokevirtual print : (Ljava/lang/String;)V
    //   841: aload_3
    //   842: aload_0
    //   843: getfield mNeedMenuInvalidate : Z
    //   846: invokevirtual println : (Z)V
    //   849: aload_0
    //   850: getfield mNoTransactionsBecause : Ljava/lang/String;
    //   853: ifnull -> 876
    //   856: aload_3
    //   857: aload_1
    //   858: invokevirtual print : (Ljava/lang/String;)V
    //   861: aload_3
    //   862: ldc_w '  mNoTransactionsBecause='
    //   865: invokevirtual print : (Ljava/lang/String;)V
    //   868: aload_3
    //   869: aload_0
    //   870: getfield mNoTransactionsBecause : Ljava/lang/String;
    //   873: invokevirtual println : (Ljava/lang/String;)V
    //   876: return
    //   877: astore_1
    //   878: aload_0
    //   879: monitorexit
    //   880: goto -> 885
    //   883: aload_1
    //   884: athrow
    //   885: goto -> 883
    // Exception table:
    //   from	to	target	type
    //   466	471	877	finally
    //   475	481	877	finally
    //   486	498	877	finally
    //   508	551	877	finally
    //   557	562	877	finally
    //   566	599	877	finally
    //   599	601	877	finally
    //   878	880	877	finally
  }
  
  public void enqueueAction(OpGenerator paramOpGenerator, boolean paramBoolean) {
    // Byte code:
    //   0: iload_2
    //   1: ifne -> 8
    //   4: aload_0
    //   5: invokespecial checkStateLoss : ()V
    //   8: aload_0
    //   9: monitorenter
    //   10: aload_0
    //   11: getfield mDestroyed : Z
    //   14: ifne -> 63
    //   17: aload_0
    //   18: getfield mHost : Landroid/support/v4/app/FragmentHostCallback;
    //   21: ifnonnull -> 27
    //   24: goto -> 63
    //   27: aload_0
    //   28: getfield mPendingActions : Ljava/util/ArrayList;
    //   31: ifnonnull -> 47
    //   34: new java/util/ArrayList
    //   37: astore_3
    //   38: aload_3
    //   39: invokespecial <init> : ()V
    //   42: aload_0
    //   43: aload_3
    //   44: putfield mPendingActions : Ljava/util/ArrayList;
    //   47: aload_0
    //   48: getfield mPendingActions : Ljava/util/ArrayList;
    //   51: aload_1
    //   52: invokevirtual add : (Ljava/lang/Object;)Z
    //   55: pop
    //   56: aload_0
    //   57: invokespecial scheduleCommit : ()V
    //   60: aload_0
    //   61: monitorexit
    //   62: return
    //   63: iload_2
    //   64: ifeq -> 70
    //   67: aload_0
    //   68: monitorexit
    //   69: return
    //   70: new java/lang/IllegalStateException
    //   73: astore_1
    //   74: aload_1
    //   75: ldc_w 'Activity has been destroyed'
    //   78: invokespecial <init> : (Ljava/lang/String;)V
    //   81: aload_1
    //   82: athrow
    //   83: astore_1
    //   84: aload_0
    //   85: monitorexit
    //   86: aload_1
    //   87: athrow
    // Exception table:
    //   from	to	target	type
    //   10	24	83	finally
    //   27	47	83	finally
    //   47	62	83	finally
    //   67	69	83	finally
    //   70	83	83	finally
    //   84	86	83	finally
  }
  
  void ensureInflatedFragmentView(Fragment paramFragment) {
    if (paramFragment.mFromLayout && !paramFragment.mPerformedCreateView) {
      View view = paramFragment.performCreateView(paramFragment.performGetLayoutInflater(paramFragment.mSavedFragmentState), null, paramFragment.mSavedFragmentState);
      paramFragment.mView = view;
      if (view != null) {
        paramFragment.mInnerView = view;
        view.setSaveFromParentEnabled(false);
        if (paramFragment.mHidden)
          paramFragment.mView.setVisibility(8); 
        paramFragment.onViewCreated(paramFragment.mView, paramFragment.mSavedFragmentState);
        dispatchOnFragmentViewCreated(paramFragment, paramFragment.mView, paramFragment.mSavedFragmentState, false);
      } else {
        paramFragment.mInnerView = null;
      } 
    } 
  }
  
  public boolean execPendingActions() {
    ensureExecReady(true);
    boolean bool = false;
    while (generateOpsForPendingActions(this.mTmpRecords, this.mTmpIsPop)) {
      this.mExecutingActions = true;
      try {
        removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);
        cleanupExec();
      } finally {
        cleanupExec();
      } 
    } 
    doPendingDeferredStart();
    burpActive();
    return bool;
  }
  
  public void execSingleAction(OpGenerator paramOpGenerator, boolean paramBoolean) {
    if (paramBoolean && (this.mHost == null || this.mDestroyed))
      return; 
    ensureExecReady(paramBoolean);
    if (paramOpGenerator.generateOps(this.mTmpRecords, this.mTmpIsPop)) {
      this.mExecutingActions = true;
      try {
        removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);
      } finally {
        cleanupExec();
      } 
    } 
    doPendingDeferredStart();
    burpActive();
  }
  
  public boolean executePendingTransactions() {
    boolean bool = execPendingActions();
    forcePostponedTransactions();
    return bool;
  }
  
  public Fragment findFragmentById(int paramInt) {
    int i;
    for (i = this.mAdded.size() - 1; i >= 0; i--) {
      Fragment fragment = this.mAdded.get(i);
      if (fragment != null && fragment.mFragmentId == paramInt)
        return fragment; 
    } 
    SparseArray<Fragment> sparseArray = this.mActive;
    if (sparseArray != null)
      for (i = sparseArray.size() - 1; i >= 0; i--) {
        Fragment fragment = (Fragment)this.mActive.valueAt(i);
        if (fragment != null && fragment.mFragmentId == paramInt)
          return fragment; 
      }  
    return null;
  }
  
  public Fragment findFragmentByTag(String paramString) {
    if (paramString != null)
      for (int i = this.mAdded.size() - 1; i >= 0; i--) {
        Fragment fragment = this.mAdded.get(i);
        if (fragment != null && paramString.equals(fragment.mTag))
          return fragment; 
      }  
    SparseArray<Fragment> sparseArray = this.mActive;
    if (sparseArray != null && paramString != null)
      for (int i = sparseArray.size() - 1; i >= 0; i--) {
        Fragment fragment = (Fragment)this.mActive.valueAt(i);
        if (fragment != null && paramString.equals(fragment.mTag))
          return fragment; 
      }  
    return null;
  }
  
  public Fragment findFragmentByWho(String paramString) {
    SparseArray<Fragment> sparseArray = this.mActive;
    if (sparseArray != null && paramString != null)
      for (int i = sparseArray.size() - 1; i >= 0; i--) {
        Fragment fragment = (Fragment)this.mActive.valueAt(i);
        if (fragment != null) {
          fragment = fragment.findFragmentByWho(paramString);
          if (fragment != null)
            return fragment; 
        } 
      }  
    return null;
  }
  
  public void freeBackStackIndex(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   6: iload_1
    //   7: aconst_null
    //   8: invokevirtual set : (ILjava/lang/Object;)Ljava/lang/Object;
    //   11: pop
    //   12: aload_0
    //   13: getfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   16: ifnonnull -> 32
    //   19: new java/util/ArrayList
    //   22: astore_2
    //   23: aload_2
    //   24: invokespecial <init> : ()V
    //   27: aload_0
    //   28: aload_2
    //   29: putfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   32: getstatic android/support/v4/app/FragmentManagerImpl.DEBUG : Z
    //   35: ifeq -> 70
    //   38: new java/lang/StringBuilder
    //   41: astore_2
    //   42: aload_2
    //   43: invokespecial <init> : ()V
    //   46: aload_2
    //   47: ldc_w 'Freeing back stack index '
    //   50: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   53: pop
    //   54: aload_2
    //   55: iload_1
    //   56: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   59: pop
    //   60: ldc 'FragmentManager'
    //   62: aload_2
    //   63: invokevirtual toString : ()Ljava/lang/String;
    //   66: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   69: pop
    //   70: aload_0
    //   71: getfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   74: iload_1
    //   75: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   78: invokevirtual add : (Ljava/lang/Object;)Z
    //   81: pop
    //   82: aload_0
    //   83: monitorexit
    //   84: return
    //   85: astore_2
    //   86: aload_0
    //   87: monitorexit
    //   88: aload_2
    //   89: athrow
    // Exception table:
    //   from	to	target	type
    //   2	32	85	finally
    //   32	70	85	finally
    //   70	84	85	finally
    //   86	88	85	finally
  }
  
  int getActiveFragmentCount() {
    SparseArray<Fragment> sparseArray = this.mActive;
    return (sparseArray == null) ? 0 : sparseArray.size();
  }
  
  List<Fragment> getActiveFragments() {
    SparseArray<Fragment> sparseArray = this.mActive;
    if (sparseArray == null)
      return null; 
    int i = sparseArray.size();
    ArrayList<Object> arrayList = new ArrayList(i);
    for (byte b = 0; b < i; b++)
      arrayList.add(this.mActive.valueAt(b)); 
    return arrayList;
  }
  
  public FragmentManager.BackStackEntry getBackStackEntryAt(int paramInt) {
    return this.mBackStack.get(paramInt);
  }
  
  public int getBackStackEntryCount() {
    boolean bool;
    ArrayList<BackStackRecord> arrayList = this.mBackStack;
    if (arrayList != null) {
      bool = arrayList.size();
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public Fragment getFragment(Bundle paramBundle, String paramString) {
    int i = paramBundle.getInt(paramString, -1);
    if (i == -1)
      return null; 
    Fragment fragment = (Fragment)this.mActive.get(i);
    if (fragment == null) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Fragment no longer exists for key ");
      stringBuilder.append(paramString);
      stringBuilder.append(": index ");
      stringBuilder.append(i);
      throwException(new IllegalStateException(stringBuilder.toString()));
    } 
    return fragment;
  }
  
  public List<Fragment> getFragments() {
    if (this.mAdded.isEmpty())
      return Collections.EMPTY_LIST; 
    synchronized (this.mAdded) {
      return (List)this.mAdded.clone();
    } 
  }
  
  LayoutInflater.Factory2 getLayoutInflaterFactory() {
    return this;
  }
  
  public Fragment getPrimaryNavigationFragment() {
    return this.mPrimaryNav;
  }
  
  public void hideFragment(Fragment paramFragment) {
    if (DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("hide: ");
      stringBuilder.append(paramFragment);
      Log.v("FragmentManager", stringBuilder.toString());
    } 
    if (!paramFragment.mHidden) {
      paramFragment.mHidden = true;
      paramFragment.mHiddenChanged = true ^ paramFragment.mHiddenChanged;
    } 
  }
  
  public boolean isDestroyed() {
    return this.mDestroyed;
  }
  
  boolean isStateAtLeast(int paramInt) {
    boolean bool;
    if (this.mCurState >= paramInt) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isStateSaved() {
    return this.mStateSaved;
  }
  
  AnimationOrAnimator loadAnimation(Fragment paramFragment, int paramInt1, boolean paramBoolean, int paramInt2) {
    int i = paramFragment.getNextAnim();
    Animation animation = paramFragment.onCreateAnimation(paramInt1, paramBoolean, i);
    if (animation != null)
      return new AnimationOrAnimator(animation); 
    Animator animator = paramFragment.onCreateAnimator(paramInt1, paramBoolean, i);
    if (animator != null)
      return new AnimationOrAnimator(animator); 
    if (i != 0) {
      boolean bool = "anim".equals(this.mHost.getContext().getResources().getResourceTypeName(i));
      boolean bool1 = false;
      boolean bool2 = bool1;
      if (bool)
        try {
          Animation animation1 = AnimationUtils.loadAnimation(this.mHost.getContext(), i);
          if (animation1 != null)
            return new AnimationOrAnimator(animation1); 
          bool2 = true;
        } catch (android.content.res.Resources.NotFoundException notFoundException) {
          throw notFoundException;
        } catch (RuntimeException runtimeException) {
          bool2 = bool1;
        }  
      if (!bool2)
        try {
          animator = AnimatorInflater.loadAnimator(this.mHost.getContext(), i);
          if (animator != null)
            return new AnimationOrAnimator(animator); 
        } catch (RuntimeException runtimeException) {
          Animation animation1;
          if (!bool) {
            animation1 = AnimationUtils.loadAnimation(this.mHost.getContext(), i);
            if (animation1 != null)
              return new AnimationOrAnimator(animation1); 
          } else {
            throw animation1;
          } 
        }  
    } 
    if (paramInt1 == 0)
      return null; 
    paramInt1 = transitToStyleIndex(paramInt1, paramBoolean);
    if (paramInt1 < 0)
      return null; 
    switch (paramInt1) {
      default:
        if (paramInt2 == 0 && this.mHost.onHasWindowAnimations())
          this.mHost.onGetWindowAnimations(); 
        return null;
      case 6:
        return makeFadeAnimation(this.mHost.getContext(), 1.0F, 0.0F);
      case 5:
        return makeFadeAnimation(this.mHost.getContext(), 0.0F, 1.0F);
      case 4:
        return makeOpenCloseAnimation(this.mHost.getContext(), 1.0F, 1.075F, 1.0F, 0.0F);
      case 3:
        return makeOpenCloseAnimation(this.mHost.getContext(), 0.975F, 1.0F, 0.0F, 1.0F);
      case 2:
        return makeOpenCloseAnimation(this.mHost.getContext(), 1.0F, 0.975F, 1.0F, 0.0F);
      case 1:
        break;
    } 
    return makeOpenCloseAnimation(this.mHost.getContext(), 1.125F, 1.0F, 0.0F, 1.0F);
  }
  
  void makeActive(Fragment paramFragment) {
    if (paramFragment.mIndex >= 0)
      return; 
    int i = this.mNextFragmentIndex;
    this.mNextFragmentIndex = i + 1;
    paramFragment.setIndex(i, this.mParent);
    if (this.mActive == null)
      this.mActive = new SparseArray(); 
    this.mActive.put(paramFragment.mIndex, paramFragment);
    if (DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Allocated fragment index ");
      stringBuilder.append(paramFragment);
      Log.v("FragmentManager", stringBuilder.toString());
    } 
  }
  
  void makeInactive(Fragment paramFragment) {
    if (paramFragment.mIndex < 0)
      return; 
    if (DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Freeing fragment index ");
      stringBuilder.append(paramFragment);
      Log.v("FragmentManager", stringBuilder.toString());
    } 
    this.mActive.put(paramFragment.mIndex, null);
    this.mHost.inactivateFragment(paramFragment.mWho);
    paramFragment.initState();
  }
  
  void moveFragmentToExpectedState(Fragment paramFragment) {
    if (paramFragment == null)
      return; 
    int i = this.mCurState;
    int j = i;
    if (paramFragment.mRemoving)
      if (paramFragment.isInBackStack()) {
        j = Math.min(i, 1);
      } else {
        j = Math.min(i, 0);
      }  
    moveToState(paramFragment, j, paramFragment.getNextTransition(), paramFragment.getNextTransitionStyle(), false);
    if (paramFragment.mView != null) {
      Fragment fragment = findFragmentUnder(paramFragment);
      if (fragment != null) {
        View view = fragment.mView;
        ViewGroup viewGroup = paramFragment.mContainer;
        j = viewGroup.indexOfChild(view);
        i = viewGroup.indexOfChild(paramFragment.mView);
        if (i < j) {
          viewGroup.removeViewAt(i);
          viewGroup.addView(paramFragment.mView, j);
        } 
      } 
      if (paramFragment.mIsNewlyAdded && paramFragment.mContainer != null) {
        float f = paramFragment.mPostponedAlpha;
        if (f > 0.0F)
          paramFragment.mView.setAlpha(f); 
        paramFragment.mPostponedAlpha = 0.0F;
        paramFragment.mIsNewlyAdded = false;
        AnimationOrAnimator animationOrAnimator = loadAnimation(paramFragment, paramFragment.getNextTransition(), true, paramFragment.getNextTransitionStyle());
        if (animationOrAnimator != null) {
          setHWLayerAnimListenerIfAlpha(paramFragment.mView, animationOrAnimator);
          Animation animation = animationOrAnimator.animation;
          if (animation != null) {
            paramFragment.mView.startAnimation(animation);
          } else {
            animationOrAnimator.animator.setTarget(paramFragment.mView);
            animationOrAnimator.animator.start();
          } 
        } 
      } 
    } 
    if (paramFragment.mHiddenChanged)
      completeShowHideFragment(paramFragment); 
  }
  
  void moveToState(int paramInt, boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mHost : Landroid/support/v4/app/FragmentHostCallback;
    //   4: ifnonnull -> 25
    //   7: iload_1
    //   8: ifne -> 14
    //   11: goto -> 25
    //   14: new java/lang/IllegalStateException
    //   17: dup
    //   18: ldc_w 'No activity'
    //   21: invokespecial <init> : (Ljava/lang/String;)V
    //   24: athrow
    //   25: iload_2
    //   26: ifne -> 38
    //   29: iload_1
    //   30: aload_0
    //   31: getfield mCurState : I
    //   34: if_icmpne -> 38
    //   37: return
    //   38: aload_0
    //   39: iload_1
    //   40: putfield mCurState : I
    //   43: aload_0
    //   44: getfield mActive : Landroid/util/SparseArray;
    //   47: ifnull -> 285
    //   50: aload_0
    //   51: getfield mAdded : Ljava/util/ArrayList;
    //   54: invokevirtual size : ()I
    //   57: istore_3
    //   58: iconst_0
    //   59: istore #4
    //   61: iconst_0
    //   62: istore_1
    //   63: iload #4
    //   65: iload_3
    //   66: if_icmpge -> 122
    //   69: aload_0
    //   70: getfield mAdded : Ljava/util/ArrayList;
    //   73: iload #4
    //   75: invokevirtual get : (I)Ljava/lang/Object;
    //   78: checkcast android/support/v4/app/Fragment
    //   81: astore #5
    //   83: aload_0
    //   84: aload #5
    //   86: invokevirtual moveFragmentToExpectedState : (Landroid/support/v4/app/Fragment;)V
    //   89: aload #5
    //   91: getfield mLoaderManager : Landroid/support/v4/app/LoaderManagerImpl;
    //   94: astore #5
    //   96: iload_1
    //   97: istore #6
    //   99: aload #5
    //   101: ifnull -> 113
    //   104: iload_1
    //   105: aload #5
    //   107: invokevirtual hasRunningLoaders : ()Z
    //   110: ior
    //   111: istore #6
    //   113: iinc #4, 1
    //   116: iload #6
    //   118: istore_1
    //   119: goto -> 63
    //   122: aload_0
    //   123: getfield mActive : Landroid/util/SparseArray;
    //   126: invokevirtual size : ()I
    //   129: istore_3
    //   130: iconst_0
    //   131: istore #4
    //   133: iload_1
    //   134: istore #6
    //   136: iload #4
    //   138: istore_1
    //   139: iload_1
    //   140: iload_3
    //   141: if_icmpge -> 240
    //   144: aload_0
    //   145: getfield mActive : Landroid/util/SparseArray;
    //   148: iload_1
    //   149: invokevirtual valueAt : (I)Ljava/lang/Object;
    //   152: checkcast android/support/v4/app/Fragment
    //   155: astore #5
    //   157: iload #6
    //   159: istore #4
    //   161: aload #5
    //   163: ifnull -> 230
    //   166: aload #5
    //   168: getfield mRemoving : Z
    //   171: ifne -> 186
    //   174: iload #6
    //   176: istore #4
    //   178: aload #5
    //   180: getfield mDetached : Z
    //   183: ifeq -> 230
    //   186: iload #6
    //   188: istore #4
    //   190: aload #5
    //   192: getfield mIsNewlyAdded : Z
    //   195: ifne -> 230
    //   198: aload_0
    //   199: aload #5
    //   201: invokevirtual moveFragmentToExpectedState : (Landroid/support/v4/app/Fragment;)V
    //   204: aload #5
    //   206: getfield mLoaderManager : Landroid/support/v4/app/LoaderManagerImpl;
    //   209: astore #5
    //   211: iload #6
    //   213: istore #4
    //   215: aload #5
    //   217: ifnull -> 230
    //   220: iload #6
    //   222: aload #5
    //   224: invokevirtual hasRunningLoaders : ()Z
    //   227: ior
    //   228: istore #4
    //   230: iinc #1, 1
    //   233: iload #4
    //   235: istore #6
    //   237: goto -> 139
    //   240: iload #6
    //   242: ifne -> 249
    //   245: aload_0
    //   246: invokevirtual startPendingDeferredFragments : ()V
    //   249: aload_0
    //   250: getfield mNeedMenuInvalidate : Z
    //   253: ifeq -> 285
    //   256: aload_0
    //   257: getfield mHost : Landroid/support/v4/app/FragmentHostCallback;
    //   260: astore #5
    //   262: aload #5
    //   264: ifnull -> 285
    //   267: aload_0
    //   268: getfield mCurState : I
    //   271: iconst_5
    //   272: if_icmpne -> 285
    //   275: aload #5
    //   277: invokevirtual onSupportInvalidateOptionsMenu : ()V
    //   280: aload_0
    //   281: iconst_0
    //   282: putfield mNeedMenuInvalidate : Z
    //   285: return
  }
  
  void moveToState(Fragment paramFragment) {
    moveToState(paramFragment, this.mCurState, 0, 0, false);
  }
  
  void moveToState(Fragment paramFragment, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean) {
    // Byte code:
    //   0: aload_1
    //   1: getfield mAdded : Z
    //   4: istore #6
    //   6: iconst_1
    //   7: istore #7
    //   9: iconst_1
    //   10: istore #8
    //   12: iload #6
    //   14: ifeq -> 30
    //   17: aload_1
    //   18: getfield mDetached : Z
    //   21: ifeq -> 27
    //   24: goto -> 30
    //   27: goto -> 44
    //   30: iload_2
    //   31: istore #9
    //   33: iload #9
    //   35: istore_2
    //   36: iload #9
    //   38: iconst_1
    //   39: if_icmple -> 44
    //   42: iconst_1
    //   43: istore_2
    //   44: iload_2
    //   45: istore #9
    //   47: aload_1
    //   48: getfield mRemoving : Z
    //   51: ifeq -> 93
    //   54: aload_1
    //   55: getfield mState : I
    //   58: istore #10
    //   60: iload_2
    //   61: istore #9
    //   63: iload_2
    //   64: iload #10
    //   66: if_icmple -> 93
    //   69: iload #10
    //   71: ifne -> 87
    //   74: aload_1
    //   75: invokevirtual isInBackStack : ()Z
    //   78: ifeq -> 87
    //   81: iconst_1
    //   82: istore #9
    //   84: goto -> 93
    //   87: aload_1
    //   88: getfield mState : I
    //   91: istore #9
    //   93: aload_1
    //   94: getfield mDeferStart : Z
    //   97: ifeq -> 119
    //   100: aload_1
    //   101: getfield mState : I
    //   104: iconst_4
    //   105: if_icmpge -> 119
    //   108: iload #9
    //   110: iconst_3
    //   111: if_icmple -> 119
    //   114: iconst_3
    //   115: istore_2
    //   116: goto -> 122
    //   119: iload #9
    //   121: istore_2
    //   122: aload_1
    //   123: getfield mState : I
    //   126: istore #10
    //   128: iload #10
    //   130: iload_2
    //   131: if_icmpgt -> 1392
    //   134: aload_1
    //   135: getfield mFromLayout : Z
    //   138: ifeq -> 149
    //   141: aload_1
    //   142: getfield mInLayout : Z
    //   145: ifne -> 149
    //   148: return
    //   149: aload_1
    //   150: invokevirtual getAnimatingAway : ()Landroid/view/View;
    //   153: ifnonnull -> 163
    //   156: aload_1
    //   157: invokevirtual getAnimator : ()Landroid/animation/Animator;
    //   160: ifnull -> 185
    //   163: aload_1
    //   164: aconst_null
    //   165: invokevirtual setAnimatingAway : (Landroid/view/View;)V
    //   168: aload_1
    //   169: aconst_null
    //   170: invokevirtual setAnimator : (Landroid/animation/Animator;)V
    //   173: aload_0
    //   174: aload_1
    //   175: aload_1
    //   176: invokevirtual getStateAfterAnimating : ()I
    //   179: iconst_0
    //   180: iconst_0
    //   181: iconst_1
    //   182: invokevirtual moveToState : (Landroid/support/v4/app/Fragment;IIIZ)V
    //   185: aload_1
    //   186: getfield mState : I
    //   189: istore #9
    //   191: iload #9
    //   193: ifeq -> 239
    //   196: iload_2
    //   197: istore #4
    //   199: iload #9
    //   201: iconst_1
    //   202: if_icmpeq -> 770
    //   205: iload_2
    //   206: istore_3
    //   207: iload #9
    //   209: iconst_2
    //   210: if_icmpeq -> 1233
    //   213: iload_2
    //   214: istore #4
    //   216: iload #9
    //   218: iconst_3
    //   219: if_icmpeq -> 1249
    //   222: iload_2
    //   223: istore_3
    //   224: iload #9
    //   226: iconst_4
    //   227: if_icmpeq -> 236
    //   230: iload_2
    //   231: istore #9
    //   233: goto -> 2038
    //   236: goto -> 1316
    //   239: iload_2
    //   240: istore #4
    //   242: iload_2
    //   243: ifle -> 770
    //   246: getstatic android/support/v4/app/FragmentManagerImpl.DEBUG : Z
    //   249: ifeq -> 288
    //   252: new java/lang/StringBuilder
    //   255: dup
    //   256: invokespecial <init> : ()V
    //   259: astore #11
    //   261: aload #11
    //   263: ldc_w 'moveto CREATED: '
    //   266: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   269: pop
    //   270: aload #11
    //   272: aload_1
    //   273: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   276: pop
    //   277: ldc 'FragmentManager'
    //   279: aload #11
    //   281: invokevirtual toString : ()Ljava/lang/String;
    //   284: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   287: pop
    //   288: aload_1
    //   289: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   292: astore #11
    //   294: iload_2
    //   295: istore #4
    //   297: aload #11
    //   299: ifnull -> 409
    //   302: aload #11
    //   304: aload_0
    //   305: getfield mHost : Landroid/support/v4/app/FragmentHostCallback;
    //   308: invokevirtual getContext : ()Landroid/content/Context;
    //   311: invokevirtual getClassLoader : ()Ljava/lang/ClassLoader;
    //   314: invokevirtual setClassLoader : (Ljava/lang/ClassLoader;)V
    //   317: aload_1
    //   318: aload_1
    //   319: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   322: ldc 'android:view_state'
    //   324: invokevirtual getSparseParcelableArray : (Ljava/lang/String;)Landroid/util/SparseArray;
    //   327: putfield mSavedViewState : Landroid/util/SparseArray;
    //   330: aload_0
    //   331: aload_1
    //   332: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   335: ldc 'android:target_state'
    //   337: invokevirtual getFragment : (Landroid/os/Bundle;Ljava/lang/String;)Landroid/support/v4/app/Fragment;
    //   340: astore #11
    //   342: aload_1
    //   343: aload #11
    //   345: putfield mTarget : Landroid/support/v4/app/Fragment;
    //   348: aload #11
    //   350: ifnull -> 367
    //   353: aload_1
    //   354: aload_1
    //   355: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   358: ldc 'android:target_req_state'
    //   360: iconst_0
    //   361: invokevirtual getInt : (Ljava/lang/String;I)I
    //   364: putfield mTargetRequestCode : I
    //   367: aload_1
    //   368: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   371: ldc 'android:user_visible_hint'
    //   373: iconst_1
    //   374: invokevirtual getBoolean : (Ljava/lang/String;Z)Z
    //   377: istore #5
    //   379: aload_1
    //   380: iload #5
    //   382: putfield mUserVisibleHint : Z
    //   385: iload_2
    //   386: istore #4
    //   388: iload #5
    //   390: ifne -> 409
    //   393: aload_1
    //   394: iconst_1
    //   395: putfield mDeferStart : Z
    //   398: iload_2
    //   399: istore #4
    //   401: iload_2
    //   402: iconst_3
    //   403: if_icmple -> 409
    //   406: iconst_3
    //   407: istore #4
    //   409: aload_0
    //   410: getfield mHost : Landroid/support/v4/app/FragmentHostCallback;
    //   413: astore #11
    //   415: aload_1
    //   416: aload #11
    //   418: putfield mHost : Landroid/support/v4/app/FragmentHostCallback;
    //   421: aload_0
    //   422: getfield mParent : Landroid/support/v4/app/Fragment;
    //   425: astore #12
    //   427: aload_1
    //   428: aload #12
    //   430: putfield mParentFragment : Landroid/support/v4/app/Fragment;
    //   433: aload #12
    //   435: ifnull -> 448
    //   438: aload #12
    //   440: getfield mChildFragmentManager : Landroid/support/v4/app/FragmentManagerImpl;
    //   443: astore #11
    //   445: goto -> 455
    //   448: aload #11
    //   450: invokevirtual getFragmentManagerImpl : ()Landroid/support/v4/app/FragmentManagerImpl;
    //   453: astore #11
    //   455: aload_1
    //   456: aload #11
    //   458: putfield mFragmentManager : Landroid/support/v4/app/FragmentManagerImpl;
    //   461: aload_1
    //   462: getfield mTarget : Landroid/support/v4/app/Fragment;
    //   465: astore #11
    //   467: aload #11
    //   469: ifnull -> 587
    //   472: aload_0
    //   473: getfield mActive : Landroid/util/SparseArray;
    //   476: aload #11
    //   478: getfield mIndex : I
    //   481: invokevirtual get : (I)Ljava/lang/Object;
    //   484: astore #11
    //   486: aload_1
    //   487: getfield mTarget : Landroid/support/v4/app/Fragment;
    //   490: astore #12
    //   492: aload #11
    //   494: aload #12
    //   496: if_acmpne -> 521
    //   499: aload #12
    //   501: getfield mState : I
    //   504: iconst_1
    //   505: if_icmpge -> 587
    //   508: aload_0
    //   509: aload #12
    //   511: iconst_1
    //   512: iconst_0
    //   513: iconst_0
    //   514: iconst_1
    //   515: invokevirtual moveToState : (Landroid/support/v4/app/Fragment;IIIZ)V
    //   518: goto -> 587
    //   521: new java/lang/StringBuilder
    //   524: dup
    //   525: invokespecial <init> : ()V
    //   528: astore #11
    //   530: aload #11
    //   532: ldc_w 'Fragment '
    //   535: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   538: pop
    //   539: aload #11
    //   541: aload_1
    //   542: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   545: pop
    //   546: aload #11
    //   548: ldc_w ' declared target fragment '
    //   551: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   554: pop
    //   555: aload #11
    //   557: aload_1
    //   558: getfield mTarget : Landroid/support/v4/app/Fragment;
    //   561: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   564: pop
    //   565: aload #11
    //   567: ldc_w ' that does not belong to this FragmentManager!'
    //   570: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   573: pop
    //   574: new java/lang/IllegalStateException
    //   577: dup
    //   578: aload #11
    //   580: invokevirtual toString : ()Ljava/lang/String;
    //   583: invokespecial <init> : (Ljava/lang/String;)V
    //   586: athrow
    //   587: aload_0
    //   588: aload_1
    //   589: aload_0
    //   590: getfield mHost : Landroid/support/v4/app/FragmentHostCallback;
    //   593: invokevirtual getContext : ()Landroid/content/Context;
    //   596: iconst_0
    //   597: invokevirtual dispatchOnFragmentPreAttached : (Landroid/support/v4/app/Fragment;Landroid/content/Context;Z)V
    //   600: aload_1
    //   601: iconst_0
    //   602: putfield mCalled : Z
    //   605: aload_1
    //   606: aload_0
    //   607: getfield mHost : Landroid/support/v4/app/FragmentHostCallback;
    //   610: invokevirtual getContext : ()Landroid/content/Context;
    //   613: invokevirtual onAttach : (Landroid/content/Context;)V
    //   616: aload_1
    //   617: getfield mCalled : Z
    //   620: ifeq -> 723
    //   623: aload_1
    //   624: getfield mParentFragment : Landroid/support/v4/app/Fragment;
    //   627: astore #11
    //   629: aload #11
    //   631: ifnonnull -> 645
    //   634: aload_0
    //   635: getfield mHost : Landroid/support/v4/app/FragmentHostCallback;
    //   638: aload_1
    //   639: invokevirtual onAttachFragment : (Landroid/support/v4/app/Fragment;)V
    //   642: goto -> 651
    //   645: aload #11
    //   647: aload_1
    //   648: invokevirtual onAttachFragment : (Landroid/support/v4/app/Fragment;)V
    //   651: aload_0
    //   652: aload_1
    //   653: aload_0
    //   654: getfield mHost : Landroid/support/v4/app/FragmentHostCallback;
    //   657: invokevirtual getContext : ()Landroid/content/Context;
    //   660: iconst_0
    //   661: invokevirtual dispatchOnFragmentAttached : (Landroid/support/v4/app/Fragment;Landroid/content/Context;Z)V
    //   664: aload_1
    //   665: getfield mIsCreated : Z
    //   668: ifne -> 702
    //   671: aload_0
    //   672: aload_1
    //   673: aload_1
    //   674: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   677: iconst_0
    //   678: invokevirtual dispatchOnFragmentPreCreated : (Landroid/support/v4/app/Fragment;Landroid/os/Bundle;Z)V
    //   681: aload_1
    //   682: aload_1
    //   683: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   686: invokevirtual performCreate : (Landroid/os/Bundle;)V
    //   689: aload_0
    //   690: aload_1
    //   691: aload_1
    //   692: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   695: iconst_0
    //   696: invokevirtual dispatchOnFragmentCreated : (Landroid/support/v4/app/Fragment;Landroid/os/Bundle;Z)V
    //   699: goto -> 715
    //   702: aload_1
    //   703: aload_1
    //   704: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   707: invokevirtual restoreChildFragmentState : (Landroid/os/Bundle;)V
    //   710: aload_1
    //   711: iconst_1
    //   712: putfield mState : I
    //   715: aload_1
    //   716: iconst_0
    //   717: putfield mRetaining : Z
    //   720: goto -> 770
    //   723: new java/lang/StringBuilder
    //   726: dup
    //   727: invokespecial <init> : ()V
    //   730: astore #11
    //   732: aload #11
    //   734: ldc_w 'Fragment '
    //   737: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   740: pop
    //   741: aload #11
    //   743: aload_1
    //   744: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   747: pop
    //   748: aload #11
    //   750: ldc_w ' did not call through to super.onAttach()'
    //   753: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   756: pop
    //   757: new android/support/v4/app/SuperNotCalledException
    //   760: dup
    //   761: aload #11
    //   763: invokevirtual toString : ()Ljava/lang/String;
    //   766: invokespecial <init> : (Ljava/lang/String;)V
    //   769: athrow
    //   770: aload_0
    //   771: aload_1
    //   772: invokevirtual ensureInflatedFragmentView : (Landroid/support/v4/app/Fragment;)V
    //   775: iload #4
    //   777: istore_3
    //   778: iload #4
    //   780: iconst_1
    //   781: if_icmple -> 1233
    //   784: getstatic android/support/v4/app/FragmentManagerImpl.DEBUG : Z
    //   787: ifeq -> 826
    //   790: new java/lang/StringBuilder
    //   793: dup
    //   794: invokespecial <init> : ()V
    //   797: astore #11
    //   799: aload #11
    //   801: ldc_w 'moveto ACTIVITY_CREATED: '
    //   804: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   807: pop
    //   808: aload #11
    //   810: aload_1
    //   811: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   814: pop
    //   815: ldc 'FragmentManager'
    //   817: aload #11
    //   819: invokevirtual toString : ()Ljava/lang/String;
    //   822: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   825: pop
    //   826: aload_1
    //   827: getfield mFromLayout : Z
    //   830: ifne -> 1192
    //   833: aload_1
    //   834: getfield mContainerId : I
    //   837: istore_2
    //   838: iload_2
    //   839: ifeq -> 1043
    //   842: iload_2
    //   843: iconst_m1
    //   844: if_icmpne -> 897
    //   847: new java/lang/StringBuilder
    //   850: dup
    //   851: invokespecial <init> : ()V
    //   854: astore #11
    //   856: aload #11
    //   858: ldc_w 'Cannot create fragment '
    //   861: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   864: pop
    //   865: aload #11
    //   867: aload_1
    //   868: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   871: pop
    //   872: aload #11
    //   874: ldc_w ' for a container view with no id'
    //   877: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   880: pop
    //   881: aload_0
    //   882: new java/lang/IllegalArgumentException
    //   885: dup
    //   886: aload #11
    //   888: invokevirtual toString : ()Ljava/lang/String;
    //   891: invokespecial <init> : (Ljava/lang/String;)V
    //   894: invokespecial throwException : (Ljava/lang/RuntimeException;)V
    //   897: aload_0
    //   898: getfield mContainer : Landroid/support/v4/app/FragmentContainer;
    //   901: aload_1
    //   902: getfield mContainerId : I
    //   905: invokevirtual onFindViewById : (I)Landroid/view/View;
    //   908: checkcast android/view/ViewGroup
    //   911: astore #12
    //   913: aload #12
    //   915: astore #11
    //   917: aload #12
    //   919: ifnonnull -> 1046
    //   922: aload #12
    //   924: astore #11
    //   926: aload_1
    //   927: getfield mRestored : Z
    //   930: ifne -> 1046
    //   933: aload_1
    //   934: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   937: aload_1
    //   938: getfield mContainerId : I
    //   941: invokevirtual getResourceName : (I)Ljava/lang/String;
    //   944: astore #11
    //   946: goto -> 956
    //   949: astore #11
    //   951: ldc_w 'unknown'
    //   954: astore #11
    //   956: new java/lang/StringBuilder
    //   959: dup
    //   960: invokespecial <init> : ()V
    //   963: astore #13
    //   965: aload #13
    //   967: ldc_w 'No view found for id 0x'
    //   970: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   973: pop
    //   974: aload #13
    //   976: aload_1
    //   977: getfield mContainerId : I
    //   980: invokestatic toHexString : (I)Ljava/lang/String;
    //   983: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   986: pop
    //   987: aload #13
    //   989: ldc_w ' ('
    //   992: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   995: pop
    //   996: aload #13
    //   998: aload #11
    //   1000: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1003: pop
    //   1004: aload #13
    //   1006: ldc_w ') for fragment '
    //   1009: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1012: pop
    //   1013: aload #13
    //   1015: aload_1
    //   1016: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1019: pop
    //   1020: aload_0
    //   1021: new java/lang/IllegalArgumentException
    //   1024: dup
    //   1025: aload #13
    //   1027: invokevirtual toString : ()Ljava/lang/String;
    //   1030: invokespecial <init> : (Ljava/lang/String;)V
    //   1033: invokespecial throwException : (Ljava/lang/RuntimeException;)V
    //   1036: aload #12
    //   1038: astore #11
    //   1040: goto -> 1046
    //   1043: aconst_null
    //   1044: astore #11
    //   1046: aload_1
    //   1047: aload #11
    //   1049: putfield mContainer : Landroid/view/ViewGroup;
    //   1052: aload_1
    //   1053: aload_1
    //   1054: aload_1
    //   1055: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   1058: invokevirtual performGetLayoutInflater : (Landroid/os/Bundle;)Landroid/view/LayoutInflater;
    //   1061: aload #11
    //   1063: aload_1
    //   1064: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   1067: invokevirtual performCreateView : (Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;
    //   1070: astore #12
    //   1072: aload_1
    //   1073: aload #12
    //   1075: putfield mView : Landroid/view/View;
    //   1078: aload #12
    //   1080: ifnull -> 1187
    //   1083: aload_1
    //   1084: aload #12
    //   1086: putfield mInnerView : Landroid/view/View;
    //   1089: aload #12
    //   1091: iconst_0
    //   1092: invokevirtual setSaveFromParentEnabled : (Z)V
    //   1095: aload #11
    //   1097: ifnull -> 1109
    //   1100: aload #11
    //   1102: aload_1
    //   1103: getfield mView : Landroid/view/View;
    //   1106: invokevirtual addView : (Landroid/view/View;)V
    //   1109: aload_1
    //   1110: getfield mHidden : Z
    //   1113: ifeq -> 1125
    //   1116: aload_1
    //   1117: getfield mView : Landroid/view/View;
    //   1120: bipush #8
    //   1122: invokevirtual setVisibility : (I)V
    //   1125: aload_1
    //   1126: aload_1
    //   1127: getfield mView : Landroid/view/View;
    //   1130: aload_1
    //   1131: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   1134: invokevirtual onViewCreated : (Landroid/view/View;Landroid/os/Bundle;)V
    //   1137: aload_0
    //   1138: aload_1
    //   1139: aload_1
    //   1140: getfield mView : Landroid/view/View;
    //   1143: aload_1
    //   1144: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   1147: iconst_0
    //   1148: invokevirtual dispatchOnFragmentViewCreated : (Landroid/support/v4/app/Fragment;Landroid/view/View;Landroid/os/Bundle;Z)V
    //   1151: aload_1
    //   1152: getfield mView : Landroid/view/View;
    //   1155: invokevirtual getVisibility : ()I
    //   1158: ifne -> 1175
    //   1161: aload_1
    //   1162: getfield mContainer : Landroid/view/ViewGroup;
    //   1165: ifnull -> 1175
    //   1168: iload #8
    //   1170: istore #5
    //   1172: goto -> 1178
    //   1175: iconst_0
    //   1176: istore #5
    //   1178: aload_1
    //   1179: iload #5
    //   1181: putfield mIsNewlyAdded : Z
    //   1184: goto -> 1192
    //   1187: aload_1
    //   1188: aconst_null
    //   1189: putfield mInnerView : Landroid/view/View;
    //   1192: aload_1
    //   1193: aload_1
    //   1194: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   1197: invokevirtual performActivityCreated : (Landroid/os/Bundle;)V
    //   1200: aload_0
    //   1201: aload_1
    //   1202: aload_1
    //   1203: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   1206: iconst_0
    //   1207: invokevirtual dispatchOnFragmentActivityCreated : (Landroid/support/v4/app/Fragment;Landroid/os/Bundle;Z)V
    //   1210: aload_1
    //   1211: getfield mView : Landroid/view/View;
    //   1214: ifnull -> 1225
    //   1217: aload_1
    //   1218: aload_1
    //   1219: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   1222: invokevirtual restoreViewState : (Landroid/os/Bundle;)V
    //   1225: aload_1
    //   1226: aconst_null
    //   1227: putfield mSavedFragmentState : Landroid/os/Bundle;
    //   1230: iload #4
    //   1232: istore_3
    //   1233: iload_3
    //   1234: istore #4
    //   1236: iload_3
    //   1237: iconst_2
    //   1238: if_icmple -> 1249
    //   1241: aload_1
    //   1242: iconst_3
    //   1243: putfield mState : I
    //   1246: iload_3
    //   1247: istore #4
    //   1249: iload #4
    //   1251: istore_3
    //   1252: iload #4
    //   1254: iconst_3
    //   1255: if_icmple -> 236
    //   1258: getstatic android/support/v4/app/FragmentManagerImpl.DEBUG : Z
    //   1261: ifeq -> 1300
    //   1264: new java/lang/StringBuilder
    //   1267: dup
    //   1268: invokespecial <init> : ()V
    //   1271: astore #11
    //   1273: aload #11
    //   1275: ldc_w 'moveto STARTED: '
    //   1278: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1281: pop
    //   1282: aload #11
    //   1284: aload_1
    //   1285: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1288: pop
    //   1289: ldc 'FragmentManager'
    //   1291: aload #11
    //   1293: invokevirtual toString : ()Ljava/lang/String;
    //   1296: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   1299: pop
    //   1300: aload_1
    //   1301: invokevirtual performStart : ()V
    //   1304: aload_0
    //   1305: aload_1
    //   1306: iconst_0
    //   1307: invokevirtual dispatchOnFragmentStarted : (Landroid/support/v4/app/Fragment;Z)V
    //   1310: iload #4
    //   1312: istore_3
    //   1313: goto -> 236
    //   1316: iload_3
    //   1317: istore #9
    //   1319: iload_3
    //   1320: iconst_4
    //   1321: if_icmple -> 2038
    //   1324: getstatic android/support/v4/app/FragmentManagerImpl.DEBUG : Z
    //   1327: ifeq -> 1366
    //   1330: new java/lang/StringBuilder
    //   1333: dup
    //   1334: invokespecial <init> : ()V
    //   1337: astore #11
    //   1339: aload #11
    //   1341: ldc_w 'moveto RESUMED: '
    //   1344: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1347: pop
    //   1348: aload #11
    //   1350: aload_1
    //   1351: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1354: pop
    //   1355: ldc 'FragmentManager'
    //   1357: aload #11
    //   1359: invokevirtual toString : ()Ljava/lang/String;
    //   1362: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   1365: pop
    //   1366: aload_1
    //   1367: invokevirtual performResume : ()V
    //   1370: aload_0
    //   1371: aload_1
    //   1372: iconst_0
    //   1373: invokevirtual dispatchOnFragmentResumed : (Landroid/support/v4/app/Fragment;Z)V
    //   1376: aload_1
    //   1377: aconst_null
    //   1378: putfield mSavedFragmentState : Landroid/os/Bundle;
    //   1381: aload_1
    //   1382: aconst_null
    //   1383: putfield mSavedViewState : Landroid/util/SparseArray;
    //   1386: iload_3
    //   1387: istore #9
    //   1389: goto -> 2038
    //   1392: iload_2
    //   1393: istore #9
    //   1395: iload #10
    //   1397: iload_2
    //   1398: if_icmple -> 2038
    //   1401: iload #10
    //   1403: iconst_1
    //   1404: if_icmpeq -> 1822
    //   1407: iload #10
    //   1409: iconst_2
    //   1410: if_icmpeq -> 1602
    //   1413: iload #10
    //   1415: iconst_3
    //   1416: if_icmpeq -> 1551
    //   1419: iload #10
    //   1421: iconst_4
    //   1422: if_icmpeq -> 1494
    //   1425: iload #10
    //   1427: iconst_5
    //   1428: if_icmpeq -> 1437
    //   1431: iload_2
    //   1432: istore #9
    //   1434: goto -> 2038
    //   1437: iload_2
    //   1438: iconst_5
    //   1439: if_icmpge -> 1494
    //   1442: getstatic android/support/v4/app/FragmentManagerImpl.DEBUG : Z
    //   1445: ifeq -> 1484
    //   1448: new java/lang/StringBuilder
    //   1451: dup
    //   1452: invokespecial <init> : ()V
    //   1455: astore #11
    //   1457: aload #11
    //   1459: ldc_w 'movefrom RESUMED: '
    //   1462: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1465: pop
    //   1466: aload #11
    //   1468: aload_1
    //   1469: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1472: pop
    //   1473: ldc 'FragmentManager'
    //   1475: aload #11
    //   1477: invokevirtual toString : ()Ljava/lang/String;
    //   1480: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   1483: pop
    //   1484: aload_1
    //   1485: invokevirtual performPause : ()V
    //   1488: aload_0
    //   1489: aload_1
    //   1490: iconst_0
    //   1491: invokevirtual dispatchOnFragmentPaused : (Landroid/support/v4/app/Fragment;Z)V
    //   1494: iload_2
    //   1495: iconst_4
    //   1496: if_icmpge -> 1551
    //   1499: getstatic android/support/v4/app/FragmentManagerImpl.DEBUG : Z
    //   1502: ifeq -> 1541
    //   1505: new java/lang/StringBuilder
    //   1508: dup
    //   1509: invokespecial <init> : ()V
    //   1512: astore #11
    //   1514: aload #11
    //   1516: ldc_w 'movefrom STARTED: '
    //   1519: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1522: pop
    //   1523: aload #11
    //   1525: aload_1
    //   1526: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1529: pop
    //   1530: ldc 'FragmentManager'
    //   1532: aload #11
    //   1534: invokevirtual toString : ()Ljava/lang/String;
    //   1537: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   1540: pop
    //   1541: aload_1
    //   1542: invokevirtual performStop : ()V
    //   1545: aload_0
    //   1546: aload_1
    //   1547: iconst_0
    //   1548: invokevirtual dispatchOnFragmentStopped : (Landroid/support/v4/app/Fragment;Z)V
    //   1551: iload_2
    //   1552: iconst_3
    //   1553: if_icmpge -> 1602
    //   1556: getstatic android/support/v4/app/FragmentManagerImpl.DEBUG : Z
    //   1559: ifeq -> 1598
    //   1562: new java/lang/StringBuilder
    //   1565: dup
    //   1566: invokespecial <init> : ()V
    //   1569: astore #11
    //   1571: aload #11
    //   1573: ldc_w 'movefrom STOPPED: '
    //   1576: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1579: pop
    //   1580: aload #11
    //   1582: aload_1
    //   1583: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1586: pop
    //   1587: ldc 'FragmentManager'
    //   1589: aload #11
    //   1591: invokevirtual toString : ()Ljava/lang/String;
    //   1594: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   1597: pop
    //   1598: aload_1
    //   1599: invokevirtual performReallyStop : ()V
    //   1602: iload_2
    //   1603: iconst_2
    //   1604: if_icmpge -> 1822
    //   1607: getstatic android/support/v4/app/FragmentManagerImpl.DEBUG : Z
    //   1610: ifeq -> 1649
    //   1613: new java/lang/StringBuilder
    //   1616: dup
    //   1617: invokespecial <init> : ()V
    //   1620: astore #11
    //   1622: aload #11
    //   1624: ldc_w 'movefrom ACTIVITY_CREATED: '
    //   1627: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1630: pop
    //   1631: aload #11
    //   1633: aload_1
    //   1634: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1637: pop
    //   1638: ldc 'FragmentManager'
    //   1640: aload #11
    //   1642: invokevirtual toString : ()Ljava/lang/String;
    //   1645: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   1648: pop
    //   1649: aload_1
    //   1650: getfield mView : Landroid/view/View;
    //   1653: ifnull -> 1679
    //   1656: aload_0
    //   1657: getfield mHost : Landroid/support/v4/app/FragmentHostCallback;
    //   1660: aload_1
    //   1661: invokevirtual onShouldSaveFragmentState : (Landroid/support/v4/app/Fragment;)Z
    //   1664: ifeq -> 1679
    //   1667: aload_1
    //   1668: getfield mSavedViewState : Landroid/util/SparseArray;
    //   1671: ifnonnull -> 1679
    //   1674: aload_0
    //   1675: aload_1
    //   1676: invokevirtual saveFragmentViewState : (Landroid/support/v4/app/Fragment;)V
    //   1679: aload_1
    //   1680: invokevirtual performDestroyView : ()V
    //   1683: aload_0
    //   1684: aload_1
    //   1685: iconst_0
    //   1686: invokevirtual dispatchOnFragmentViewDestroyed : (Landroid/support/v4/app/Fragment;Z)V
    //   1689: aload_1
    //   1690: getfield mView : Landroid/view/View;
    //   1693: astore #11
    //   1695: aload #11
    //   1697: ifnull -> 1802
    //   1700: aload_1
    //   1701: getfield mContainer : Landroid/view/ViewGroup;
    //   1704: ifnull -> 1802
    //   1707: aload #11
    //   1709: invokevirtual clearAnimation : ()V
    //   1712: aload_1
    //   1713: getfield mContainer : Landroid/view/ViewGroup;
    //   1716: aload_1
    //   1717: getfield mView : Landroid/view/View;
    //   1720: invokevirtual endViewTransition : (Landroid/view/View;)V
    //   1723: aload_0
    //   1724: getfield mCurState : I
    //   1727: ifle -> 1770
    //   1730: aload_0
    //   1731: getfield mDestroyed : Z
    //   1734: ifne -> 1770
    //   1737: aload_1
    //   1738: getfield mView : Landroid/view/View;
    //   1741: invokevirtual getVisibility : ()I
    //   1744: ifne -> 1770
    //   1747: aload_1
    //   1748: getfield mPostponedAlpha : F
    //   1751: fconst_0
    //   1752: fcmpl
    //   1753: iflt -> 1770
    //   1756: aload_0
    //   1757: aload_1
    //   1758: iload_3
    //   1759: iconst_0
    //   1760: iload #4
    //   1762: invokevirtual loadAnimation : (Landroid/support/v4/app/Fragment;IZI)Landroid/support/v4/app/FragmentManagerImpl$AnimationOrAnimator;
    //   1765: astore #11
    //   1767: goto -> 1773
    //   1770: aconst_null
    //   1771: astore #11
    //   1773: aload_1
    //   1774: fconst_0
    //   1775: putfield mPostponedAlpha : F
    //   1778: aload #11
    //   1780: ifnull -> 1791
    //   1783: aload_0
    //   1784: aload_1
    //   1785: aload #11
    //   1787: iload_2
    //   1788: invokespecial animateRemoveFragment : (Landroid/support/v4/app/Fragment;Landroid/support/v4/app/FragmentManagerImpl$AnimationOrAnimator;I)V
    //   1791: aload_1
    //   1792: getfield mContainer : Landroid/view/ViewGroup;
    //   1795: aload_1
    //   1796: getfield mView : Landroid/view/View;
    //   1799: invokevirtual removeView : (Landroid/view/View;)V
    //   1802: aload_1
    //   1803: aconst_null
    //   1804: putfield mContainer : Landroid/view/ViewGroup;
    //   1807: aload_1
    //   1808: aconst_null
    //   1809: putfield mView : Landroid/view/View;
    //   1812: aload_1
    //   1813: aconst_null
    //   1814: putfield mInnerView : Landroid/view/View;
    //   1817: aload_1
    //   1818: iconst_0
    //   1819: putfield mInLayout : Z
    //   1822: iload_2
    //   1823: istore #9
    //   1825: iload_2
    //   1826: iconst_1
    //   1827: if_icmpge -> 2038
    //   1830: aload_0
    //   1831: getfield mDestroyed : Z
    //   1834: ifeq -> 1886
    //   1837: aload_1
    //   1838: invokevirtual getAnimatingAway : ()Landroid/view/View;
    //   1841: ifnull -> 1863
    //   1844: aload_1
    //   1845: invokevirtual getAnimatingAway : ()Landroid/view/View;
    //   1848: astore #11
    //   1850: aload_1
    //   1851: aconst_null
    //   1852: invokevirtual setAnimatingAway : (Landroid/view/View;)V
    //   1855: aload #11
    //   1857: invokevirtual clearAnimation : ()V
    //   1860: goto -> 1886
    //   1863: aload_1
    //   1864: invokevirtual getAnimator : ()Landroid/animation/Animator;
    //   1867: ifnull -> 1886
    //   1870: aload_1
    //   1871: invokevirtual getAnimator : ()Landroid/animation/Animator;
    //   1874: astore #11
    //   1876: aload_1
    //   1877: aconst_null
    //   1878: invokevirtual setAnimator : (Landroid/animation/Animator;)V
    //   1881: aload #11
    //   1883: invokevirtual cancel : ()V
    //   1886: aload_1
    //   1887: invokevirtual getAnimatingAway : ()Landroid/view/View;
    //   1890: ifnonnull -> 2027
    //   1893: aload_1
    //   1894: invokevirtual getAnimator : ()Landroid/animation/Animator;
    //   1897: ifnull -> 1903
    //   1900: goto -> 2027
    //   1903: getstatic android/support/v4/app/FragmentManagerImpl.DEBUG : Z
    //   1906: ifeq -> 1945
    //   1909: new java/lang/StringBuilder
    //   1912: dup
    //   1913: invokespecial <init> : ()V
    //   1916: astore #11
    //   1918: aload #11
    //   1920: ldc_w 'movefrom CREATED: '
    //   1923: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1926: pop
    //   1927: aload #11
    //   1929: aload_1
    //   1930: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1933: pop
    //   1934: ldc 'FragmentManager'
    //   1936: aload #11
    //   1938: invokevirtual toString : ()Ljava/lang/String;
    //   1941: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   1944: pop
    //   1945: aload_1
    //   1946: getfield mRetaining : Z
    //   1949: ifne -> 1965
    //   1952: aload_1
    //   1953: invokevirtual performDestroy : ()V
    //   1956: aload_0
    //   1957: aload_1
    //   1958: iconst_0
    //   1959: invokevirtual dispatchOnFragmentDestroyed : (Landroid/support/v4/app/Fragment;Z)V
    //   1962: goto -> 1970
    //   1965: aload_1
    //   1966: iconst_0
    //   1967: putfield mState : I
    //   1970: aload_1
    //   1971: invokevirtual performDetach : ()V
    //   1974: aload_0
    //   1975: aload_1
    //   1976: iconst_0
    //   1977: invokevirtual dispatchOnFragmentDetached : (Landroid/support/v4/app/Fragment;Z)V
    //   1980: iload_2
    //   1981: istore #9
    //   1983: iload #5
    //   1985: ifne -> 2038
    //   1988: aload_1
    //   1989: getfield mRetaining : Z
    //   1992: ifne -> 2006
    //   1995: aload_0
    //   1996: aload_1
    //   1997: invokevirtual makeInactive : (Landroid/support/v4/app/Fragment;)V
    //   2000: iload_2
    //   2001: istore #9
    //   2003: goto -> 2038
    //   2006: aload_1
    //   2007: aconst_null
    //   2008: putfield mHost : Landroid/support/v4/app/FragmentHostCallback;
    //   2011: aload_1
    //   2012: aconst_null
    //   2013: putfield mParentFragment : Landroid/support/v4/app/Fragment;
    //   2016: aload_1
    //   2017: aconst_null
    //   2018: putfield mFragmentManager : Landroid/support/v4/app/FragmentManagerImpl;
    //   2021: iload_2
    //   2022: istore #9
    //   2024: goto -> 2038
    //   2027: aload_1
    //   2028: iload_2
    //   2029: invokevirtual setStateAfterAnimating : (I)V
    //   2032: iload #7
    //   2034: istore_2
    //   2035: goto -> 2041
    //   2038: iload #9
    //   2040: istore_2
    //   2041: aload_1
    //   2042: getfield mState : I
    //   2045: iload_2
    //   2046: if_icmpeq -> 2134
    //   2049: new java/lang/StringBuilder
    //   2052: dup
    //   2053: invokespecial <init> : ()V
    //   2056: astore #11
    //   2058: aload #11
    //   2060: ldc_w 'moveToState: Fragment state for '
    //   2063: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2066: pop
    //   2067: aload #11
    //   2069: aload_1
    //   2070: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   2073: pop
    //   2074: aload #11
    //   2076: ldc_w ' not updated inline; '
    //   2079: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2082: pop
    //   2083: aload #11
    //   2085: ldc_w 'expected state '
    //   2088: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2091: pop
    //   2092: aload #11
    //   2094: iload_2
    //   2095: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   2098: pop
    //   2099: aload #11
    //   2101: ldc_w ' found '
    //   2104: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2107: pop
    //   2108: aload #11
    //   2110: aload_1
    //   2111: getfield mState : I
    //   2114: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   2117: pop
    //   2118: ldc 'FragmentManager'
    //   2120: aload #11
    //   2122: invokevirtual toString : ()Ljava/lang/String;
    //   2125: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   2128: pop
    //   2129: aload_1
    //   2130: iload_2
    //   2131: putfield mState : I
    //   2134: return
    // Exception table:
    //   from	to	target	type
    //   933	946	949	android/content/res/Resources$NotFoundException
  }
  
  public void noteStateNotSaved() {
    this.mSavedNonConfig = null;
    byte b = 0;
    this.mStateSaved = false;
    int i = this.mAdded.size();
    while (b < i) {
      Fragment fragment = this.mAdded.get(b);
      if (fragment != null)
        fragment.noteStateNotSaved(); 
      b++;
    } 
  }
  
  public View onCreateView(View paramView, String paramString, Context paramContext, AttributeSet paramAttributeSet) {
    if (!"fragment".equals(paramString))
      return null; 
    paramString = paramAttributeSet.getAttributeValue(null, "class");
    TypedArray typedArray = paramContext.obtainStyledAttributes(paramAttributeSet, FragmentTag.Fragment);
    int i = 0;
    String str1 = paramString;
    if (paramString == null)
      str1 = typedArray.getString(0); 
    int j = typedArray.getResourceId(1, -1);
    String str2 = typedArray.getString(2);
    typedArray.recycle();
    if (!Fragment.isSupportFragmentClass(this.mHost.getContext(), str1))
      return null; 
    if (paramView != null)
      i = paramView.getId(); 
    if (i != -1 || j != -1 || str2 != null) {
      FragmentHostCallback fragmentHostCallback1;
      Fragment fragment2;
      FragmentHostCallback fragmentHostCallback2;
      if (j != -1) {
        Fragment fragment = findFragmentById(j);
      } else {
        paramView = null;
      } 
      View view2 = paramView;
      if (paramView == null) {
        view2 = paramView;
        if (str2 != null)
          fragment2 = findFragmentByTag(str2); 
      } 
      Fragment fragment1 = fragment2;
      if (fragment2 == null) {
        fragment1 = fragment2;
        if (i != -1)
          fragment1 = findFragmentById(i); 
      } 
      if (DEBUG) {
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("onCreateView: id=0x");
        stringBuilder2.append(Integer.toHexString(j));
        stringBuilder2.append(" fname=");
        stringBuilder2.append(str1);
        stringBuilder2.append(" existing=");
        stringBuilder2.append(fragment1);
        Log.v("FragmentManager", stringBuilder2.toString());
      } 
      if (fragment1 == null) {
        int k;
        fragment2 = this.mContainer.instantiate(paramContext, str1, null);
        fragment2.mFromLayout = true;
        if (j != 0) {
          k = j;
        } else {
          k = i;
        } 
        fragment2.mFragmentId = k;
        fragment2.mContainerId = i;
        fragment2.mTag = str2;
        fragment2.mInLayout = true;
        fragment2.mFragmentManager = this;
        fragmentHostCallback1 = this.mHost;
        fragment2.mHost = fragmentHostCallback1;
        fragment2.onInflate(fragmentHostCallback1.getContext(), paramAttributeSet, fragment2.mSavedFragmentState);
        addFragment(fragment2, true);
      } else if (!((Fragment)fragmentHostCallback1).mInLayout) {
        ((Fragment)fragmentHostCallback1).mInLayout = true;
        FragmentHostCallback fragmentHostCallback = this.mHost;
        ((Fragment)fragmentHostCallback1).mHost = fragmentHostCallback;
        fragmentHostCallback2 = fragmentHostCallback1;
        if (!((Fragment)fragmentHostCallback1).mRetaining) {
          fragmentHostCallback1.onInflate(fragmentHostCallback.getContext(), paramAttributeSet, ((Fragment)fragmentHostCallback1).mSavedFragmentState);
          fragmentHostCallback2 = fragmentHostCallback1;
        } 
      } else {
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(paramAttributeSet.getPositionDescription());
        stringBuilder2.append(": Duplicate id 0x");
        stringBuilder2.append(Integer.toHexString(j));
        stringBuilder2.append(", tag ");
        stringBuilder2.append(str2);
        stringBuilder2.append(", or parent id 0x");
        stringBuilder2.append(Integer.toHexString(i));
        stringBuilder2.append(" with another fragment for ");
        stringBuilder2.append(str1);
        throw new IllegalArgumentException(stringBuilder2.toString());
      } 
      if (this.mCurState < 1 && ((Fragment)fragmentHostCallback2).mFromLayout) {
        moveToState((Fragment)fragmentHostCallback2, 1, 0, 0, false);
      } else {
        moveToState((Fragment)fragmentHostCallback2);
      } 
      View view1 = ((Fragment)fragmentHostCallback2).mView;
      if (view1 != null) {
        if (j != 0)
          view1.setId(j); 
        if (((Fragment)fragmentHostCallback2).mView.getTag() == null)
          ((Fragment)fragmentHostCallback2).mView.setTag(str2); 
        return ((Fragment)fragmentHostCallback2).mView;
      } 
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Fragment ");
      stringBuilder1.append(str1);
      stringBuilder1.append(" did not create a view.");
      throw new IllegalStateException(stringBuilder1.toString());
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramAttributeSet.getPositionDescription());
    stringBuilder.append(": Must specify unique android:id, android:tag, or have a parent with an id for ");
    stringBuilder.append(str1);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public View onCreateView(String paramString, Context paramContext, AttributeSet paramAttributeSet) {
    return onCreateView(null, paramString, paramContext, paramAttributeSet);
  }
  
  public void performPendingDeferredStart(Fragment paramFragment) {
    if (paramFragment.mDeferStart) {
      if (this.mExecutingActions) {
        this.mHavePendingDeferredStart = true;
        return;
      } 
      paramFragment.mDeferStart = false;
      moveToState(paramFragment, this.mCurState, 0, 0, false);
    } 
  }
  
  public void popBackStack() {
    enqueueAction(new PopBackStackState(null, -1, 0), false);
  }
  
  public void popBackStack(int paramInt1, int paramInt2) {
    if (paramInt1 >= 0) {
      enqueueAction(new PopBackStackState(null, paramInt1, paramInt2), false);
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Bad id: ");
    stringBuilder.append(paramInt1);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public void popBackStack(String paramString, int paramInt) {
    enqueueAction(new PopBackStackState(paramString, -1, paramInt), false);
  }
  
  public boolean popBackStackImmediate() {
    checkStateLoss();
    return popBackStackImmediate(null, -1, 0);
  }
  
  public boolean popBackStackImmediate(int paramInt1, int paramInt2) {
    checkStateLoss();
    execPendingActions();
    if (paramInt1 >= 0)
      return popBackStackImmediate(null, paramInt1, paramInt2); 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Bad id: ");
    stringBuilder.append(paramInt1);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public boolean popBackStackImmediate(String paramString, int paramInt) {
    checkStateLoss();
    return popBackStackImmediate(paramString, -1, paramInt);
  }
  
  boolean popBackStackState(ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1, String paramString, int paramInt1, int paramInt2) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mBackStack : Ljava/util/ArrayList;
    //   4: astore #6
    //   6: aload #6
    //   8: ifnonnull -> 13
    //   11: iconst_0
    //   12: ireturn
    //   13: aload_3
    //   14: ifnonnull -> 70
    //   17: iload #4
    //   19: ifge -> 70
    //   22: iload #5
    //   24: iconst_1
    //   25: iand
    //   26: ifne -> 70
    //   29: aload #6
    //   31: invokevirtual size : ()I
    //   34: iconst_1
    //   35: isub
    //   36: istore #4
    //   38: iload #4
    //   40: ifge -> 45
    //   43: iconst_0
    //   44: ireturn
    //   45: aload_1
    //   46: aload_0
    //   47: getfield mBackStack : Ljava/util/ArrayList;
    //   50: iload #4
    //   52: invokevirtual remove : (I)Ljava/lang/Object;
    //   55: invokevirtual add : (Ljava/lang/Object;)Z
    //   58: pop
    //   59: aload_2
    //   60: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
    //   63: invokevirtual add : (Ljava/lang/Object;)Z
    //   66: pop
    //   67: goto -> 322
    //   70: aload_3
    //   71: ifnonnull -> 88
    //   74: iload #4
    //   76: iflt -> 82
    //   79: goto -> 88
    //   82: iconst_m1
    //   83: istore #4
    //   85: goto -> 260
    //   88: aload #6
    //   90: invokevirtual size : ()I
    //   93: iconst_1
    //   94: isub
    //   95: istore #7
    //   97: iload #7
    //   99: iflt -> 159
    //   102: aload_0
    //   103: getfield mBackStack : Ljava/util/ArrayList;
    //   106: iload #7
    //   108: invokevirtual get : (I)Ljava/lang/Object;
    //   111: checkcast android/support/v4/app/BackStackRecord
    //   114: astore #6
    //   116: aload_3
    //   117: ifnull -> 135
    //   120: aload_3
    //   121: aload #6
    //   123: invokevirtual getName : ()Ljava/lang/String;
    //   126: invokevirtual equals : (Ljava/lang/Object;)Z
    //   129: ifeq -> 135
    //   132: goto -> 159
    //   135: iload #4
    //   137: iflt -> 153
    //   140: iload #4
    //   142: aload #6
    //   144: getfield mIndex : I
    //   147: if_icmpne -> 153
    //   150: goto -> 159
    //   153: iinc #7, -1
    //   156: goto -> 97
    //   159: iload #7
    //   161: ifge -> 166
    //   164: iconst_0
    //   165: ireturn
    //   166: iload #7
    //   168: istore #8
    //   170: iload #5
    //   172: iconst_1
    //   173: iand
    //   174: ifeq -> 256
    //   177: iload #7
    //   179: iconst_1
    //   180: isub
    //   181: istore #5
    //   183: iload #5
    //   185: istore #8
    //   187: iload #5
    //   189: iflt -> 256
    //   192: aload_0
    //   193: getfield mBackStack : Ljava/util/ArrayList;
    //   196: iload #5
    //   198: invokevirtual get : (I)Ljava/lang/Object;
    //   201: checkcast android/support/v4/app/BackStackRecord
    //   204: astore #6
    //   206: aload_3
    //   207: ifnull -> 226
    //   210: iload #5
    //   212: istore #7
    //   214: aload_3
    //   215: aload #6
    //   217: invokevirtual getName : ()Ljava/lang/String;
    //   220: invokevirtual equals : (Ljava/lang/Object;)Z
    //   223: ifne -> 177
    //   226: iload #5
    //   228: istore #8
    //   230: iload #4
    //   232: iflt -> 256
    //   235: iload #5
    //   237: istore #8
    //   239: iload #4
    //   241: aload #6
    //   243: getfield mIndex : I
    //   246: if_icmpne -> 256
    //   249: iload #5
    //   251: istore #7
    //   253: goto -> 177
    //   256: iload #8
    //   258: istore #4
    //   260: iload #4
    //   262: aload_0
    //   263: getfield mBackStack : Ljava/util/ArrayList;
    //   266: invokevirtual size : ()I
    //   269: iconst_1
    //   270: isub
    //   271: if_icmpne -> 276
    //   274: iconst_0
    //   275: ireturn
    //   276: aload_0
    //   277: getfield mBackStack : Ljava/util/ArrayList;
    //   280: invokevirtual size : ()I
    //   283: iconst_1
    //   284: isub
    //   285: istore #5
    //   287: iload #5
    //   289: iload #4
    //   291: if_icmple -> 322
    //   294: aload_1
    //   295: aload_0
    //   296: getfield mBackStack : Ljava/util/ArrayList;
    //   299: iload #5
    //   301: invokevirtual remove : (I)Ljava/lang/Object;
    //   304: invokevirtual add : (Ljava/lang/Object;)Z
    //   307: pop
    //   308: aload_2
    //   309: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
    //   312: invokevirtual add : (Ljava/lang/Object;)Z
    //   315: pop
    //   316: iinc #5, -1
    //   319: goto -> 287
    //   322: iconst_1
    //   323: ireturn
  }
  
  public void putFragment(Bundle paramBundle, String paramString, Fragment paramFragment) {
    if (paramFragment.mIndex < 0) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Fragment ");
      stringBuilder.append(paramFragment);
      stringBuilder.append(" is not currently in the FragmentManager");
      throwException(new IllegalStateException(stringBuilder.toString()));
    } 
    paramBundle.putInt(paramString, paramFragment.mIndex);
  }
  
  public void registerFragmentLifecycleCallbacks(FragmentManager.FragmentLifecycleCallbacks paramFragmentLifecycleCallbacks, boolean paramBoolean) {
    this.mLifecycleCallbacks.add(new Pair(paramFragmentLifecycleCallbacks, Boolean.valueOf(paramBoolean)));
  }
  
  public void removeFragment(Fragment paramFragment) {
    if (DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("remove: ");
      stringBuilder.append(paramFragment);
      stringBuilder.append(" nesting=");
      stringBuilder.append(paramFragment.mBackStackNesting);
      Log.v("FragmentManager", stringBuilder.toString());
    } 
    boolean bool = paramFragment.isInBackStack();
    if (!paramFragment.mDetached || (bool ^ true) != 0)
      synchronized (this.mAdded) {
        this.mAdded.remove(paramFragment);
        if (paramFragment.mHasMenu && paramFragment.mMenuVisible)
          this.mNeedMenuInvalidate = true; 
        paramFragment.mAdded = false;
        paramFragment.mRemoving = true;
        return;
      }  
  }
  
  public void removeOnBackStackChangedListener(FragmentManager.OnBackStackChangedListener paramOnBackStackChangedListener) {
    ArrayList<FragmentManager.OnBackStackChangedListener> arrayList = this.mBackStackChangeListeners;
    if (arrayList != null)
      arrayList.remove(paramOnBackStackChangedListener); 
  }
  
  void reportBackStackChanged() {
    if (this.mBackStackChangeListeners != null)
      for (byte b = 0; b < this.mBackStackChangeListeners.size(); b++)
        ((FragmentManager.OnBackStackChangedListener)this.mBackStackChangeListeners.get(b)).onBackStackChanged();  
  }
  
  void restoreAllState(Parcelable<FragmentManagerNonConfig> paramParcelable, FragmentManagerNonConfig paramFragmentManagerNonConfig) {
    // Byte code:
    //   0: aload_1
    //   1: ifnonnull -> 5
    //   4: return
    //   5: aload_1
    //   6: checkcast android/support/v4/app/FragmentManagerState
    //   9: astore_3
    //   10: aload_3
    //   11: getfield mActive : [Landroid/support/v4/app/FragmentState;
    //   14: ifnonnull -> 18
    //   17: return
    //   18: aload_2
    //   19: ifnull -> 308
    //   22: aload_2
    //   23: invokevirtual getFragments : ()Ljava/util/List;
    //   26: astore #4
    //   28: aload_2
    //   29: invokevirtual getChildNonConfigs : ()Ljava/util/List;
    //   32: astore #5
    //   34: aload #4
    //   36: ifnull -> 51
    //   39: aload #4
    //   41: invokeinterface size : ()I
    //   46: istore #6
    //   48: goto -> 54
    //   51: iconst_0
    //   52: istore #6
    //   54: iconst_0
    //   55: istore #7
    //   57: aload #5
    //   59: astore_1
    //   60: iload #7
    //   62: iload #6
    //   64: if_icmpge -> 310
    //   67: aload #4
    //   69: iload #7
    //   71: invokeinterface get : (I)Ljava/lang/Object;
    //   76: checkcast android/support/v4/app/Fragment
    //   79: astore_1
    //   80: getstatic android/support/v4/app/FragmentManagerImpl.DEBUG : Z
    //   83: ifeq -> 122
    //   86: new java/lang/StringBuilder
    //   89: dup
    //   90: invokespecial <init> : ()V
    //   93: astore #8
    //   95: aload #8
    //   97: ldc_w 'restoreAllState: re-attaching retained '
    //   100: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   103: pop
    //   104: aload #8
    //   106: aload_1
    //   107: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   110: pop
    //   111: ldc 'FragmentManager'
    //   113: aload #8
    //   115: invokevirtual toString : ()Ljava/lang/String;
    //   118: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   121: pop
    //   122: iconst_0
    //   123: istore #9
    //   125: aload_3
    //   126: getfield mActive : [Landroid/support/v4/app/FragmentState;
    //   129: astore #8
    //   131: iload #9
    //   133: aload #8
    //   135: arraylength
    //   136: if_icmpge -> 160
    //   139: aload #8
    //   141: iload #9
    //   143: aaload
    //   144: getfield mIndex : I
    //   147: aload_1
    //   148: getfield mIndex : I
    //   151: if_icmpeq -> 160
    //   154: iinc #9, 1
    //   157: goto -> 125
    //   160: iload #9
    //   162: aload #8
    //   164: arraylength
    //   165: if_icmpne -> 212
    //   168: new java/lang/StringBuilder
    //   171: dup
    //   172: invokespecial <init> : ()V
    //   175: astore #8
    //   177: aload #8
    //   179: ldc_w 'Could not find active fragment with index '
    //   182: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   185: pop
    //   186: aload #8
    //   188: aload_1
    //   189: getfield mIndex : I
    //   192: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   195: pop
    //   196: aload_0
    //   197: new java/lang/IllegalStateException
    //   200: dup
    //   201: aload #8
    //   203: invokevirtual toString : ()Ljava/lang/String;
    //   206: invokespecial <init> : (Ljava/lang/String;)V
    //   209: invokespecial throwException : (Ljava/lang/RuntimeException;)V
    //   212: aload_3
    //   213: getfield mActive : [Landroid/support/v4/app/FragmentState;
    //   216: iload #9
    //   218: aaload
    //   219: astore #8
    //   221: aload #8
    //   223: aload_1
    //   224: putfield mInstance : Landroid/support/v4/app/Fragment;
    //   227: aload_1
    //   228: aconst_null
    //   229: putfield mSavedViewState : Landroid/util/SparseArray;
    //   232: aload_1
    //   233: iconst_0
    //   234: putfield mBackStackNesting : I
    //   237: aload_1
    //   238: iconst_0
    //   239: putfield mInLayout : Z
    //   242: aload_1
    //   243: iconst_0
    //   244: putfield mAdded : Z
    //   247: aload_1
    //   248: aconst_null
    //   249: putfield mTarget : Landroid/support/v4/app/Fragment;
    //   252: aload #8
    //   254: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   257: astore #10
    //   259: aload #10
    //   261: ifnull -> 302
    //   264: aload #10
    //   266: aload_0
    //   267: getfield mHost : Landroid/support/v4/app/FragmentHostCallback;
    //   270: invokevirtual getContext : ()Landroid/content/Context;
    //   273: invokevirtual getClassLoader : ()Ljava/lang/ClassLoader;
    //   276: invokevirtual setClassLoader : (Ljava/lang/ClassLoader;)V
    //   279: aload_1
    //   280: aload #8
    //   282: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   285: ldc 'android:view_state'
    //   287: invokevirtual getSparseParcelableArray : (Ljava/lang/String;)Landroid/util/SparseArray;
    //   290: putfield mSavedViewState : Landroid/util/SparseArray;
    //   293: aload_1
    //   294: aload #8
    //   296: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   299: putfield mSavedFragmentState : Landroid/os/Bundle;
    //   302: iinc #7, 1
    //   305: goto -> 57
    //   308: aconst_null
    //   309: astore_1
    //   310: aload_0
    //   311: new android/util/SparseArray
    //   314: dup
    //   315: aload_3
    //   316: getfield mActive : [Landroid/support/v4/app/FragmentState;
    //   319: arraylength
    //   320: invokespecial <init> : (I)V
    //   323: putfield mActive : Landroid/util/SparseArray;
    //   326: iconst_0
    //   327: istore #6
    //   329: aload_3
    //   330: getfield mActive : [Landroid/support/v4/app/FragmentState;
    //   333: astore #5
    //   335: iload #6
    //   337: aload #5
    //   339: arraylength
    //   340: if_icmpge -> 496
    //   343: aload #5
    //   345: iload #6
    //   347: aaload
    //   348: astore #4
    //   350: aload #4
    //   352: ifnull -> 490
    //   355: aload_1
    //   356: ifnull -> 386
    //   359: iload #6
    //   361: aload_1
    //   362: invokeinterface size : ()I
    //   367: if_icmpge -> 386
    //   370: aload_1
    //   371: iload #6
    //   373: invokeinterface get : (I)Ljava/lang/Object;
    //   378: checkcast android/support/v4/app/FragmentManagerNonConfig
    //   381: astore #5
    //   383: goto -> 389
    //   386: aconst_null
    //   387: astore #5
    //   389: aload #4
    //   391: aload_0
    //   392: getfield mHost : Landroid/support/v4/app/FragmentHostCallback;
    //   395: aload_0
    //   396: getfield mContainer : Landroid/support/v4/app/FragmentContainer;
    //   399: aload_0
    //   400: getfield mParent : Landroid/support/v4/app/Fragment;
    //   403: aload #5
    //   405: invokevirtual instantiate : (Landroid/support/v4/app/FragmentHostCallback;Landroid/support/v4/app/FragmentContainer;Landroid/support/v4/app/Fragment;Landroid/support/v4/app/FragmentManagerNonConfig;)Landroid/support/v4/app/Fragment;
    //   408: astore #5
    //   410: getstatic android/support/v4/app/FragmentManagerImpl.DEBUG : Z
    //   413: ifeq -> 470
    //   416: new java/lang/StringBuilder
    //   419: dup
    //   420: invokespecial <init> : ()V
    //   423: astore #8
    //   425: aload #8
    //   427: ldc_w 'restoreAllState: active #'
    //   430: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   433: pop
    //   434: aload #8
    //   436: iload #6
    //   438: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   441: pop
    //   442: aload #8
    //   444: ldc_w ': '
    //   447: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   450: pop
    //   451: aload #8
    //   453: aload #5
    //   455: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   458: pop
    //   459: ldc 'FragmentManager'
    //   461: aload #8
    //   463: invokevirtual toString : ()Ljava/lang/String;
    //   466: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   469: pop
    //   470: aload_0
    //   471: getfield mActive : Landroid/util/SparseArray;
    //   474: aload #5
    //   476: getfield mIndex : I
    //   479: aload #5
    //   481: invokevirtual put : (ILjava/lang/Object;)V
    //   484: aload #4
    //   486: aconst_null
    //   487: putfield mInstance : Landroid/support/v4/app/Fragment;
    //   490: iinc #6, 1
    //   493: goto -> 329
    //   496: aload_2
    //   497: ifnull -> 642
    //   500: aload_2
    //   501: invokevirtual getFragments : ()Ljava/util/List;
    //   504: astore_1
    //   505: aload_1
    //   506: ifnull -> 520
    //   509: aload_1
    //   510: invokeinterface size : ()I
    //   515: istore #6
    //   517: goto -> 523
    //   520: iconst_0
    //   521: istore #6
    //   523: iconst_0
    //   524: istore #7
    //   526: iload #7
    //   528: iload #6
    //   530: if_icmpge -> 642
    //   533: aload_1
    //   534: iload #7
    //   536: invokeinterface get : (I)Ljava/lang/Object;
    //   541: checkcast android/support/v4/app/Fragment
    //   544: astore_2
    //   545: aload_2
    //   546: getfield mTargetIndex : I
    //   549: istore #9
    //   551: iload #9
    //   553: iflt -> 636
    //   556: aload_0
    //   557: getfield mActive : Landroid/util/SparseArray;
    //   560: iload #9
    //   562: invokevirtual get : (I)Ljava/lang/Object;
    //   565: checkcast android/support/v4/app/Fragment
    //   568: astore #5
    //   570: aload_2
    //   571: aload #5
    //   573: putfield mTarget : Landroid/support/v4/app/Fragment;
    //   576: aload #5
    //   578: ifnonnull -> 636
    //   581: new java/lang/StringBuilder
    //   584: dup
    //   585: invokespecial <init> : ()V
    //   588: astore #5
    //   590: aload #5
    //   592: ldc_w 'Re-attaching retained fragment '
    //   595: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   598: pop
    //   599: aload #5
    //   601: aload_2
    //   602: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   605: pop
    //   606: aload #5
    //   608: ldc_w ' target no longer exists: '
    //   611: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   614: pop
    //   615: aload #5
    //   617: aload_2
    //   618: getfield mTargetIndex : I
    //   621: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   624: pop
    //   625: ldc 'FragmentManager'
    //   627: aload #5
    //   629: invokevirtual toString : ()Ljava/lang/String;
    //   632: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   635: pop
    //   636: iinc #7, 1
    //   639: goto -> 526
    //   642: aload_0
    //   643: getfield mAdded : Ljava/util/ArrayList;
    //   646: invokevirtual clear : ()V
    //   649: aload_3
    //   650: getfield mAdded : [I
    //   653: ifnull -> 842
    //   656: iconst_0
    //   657: istore #6
    //   659: aload_3
    //   660: getfield mAdded : [I
    //   663: astore_1
    //   664: iload #6
    //   666: aload_1
    //   667: arraylength
    //   668: if_icmpge -> 842
    //   671: aload_0
    //   672: getfield mActive : Landroid/util/SparseArray;
    //   675: aload_1
    //   676: iload #6
    //   678: iaload
    //   679: invokevirtual get : (I)Ljava/lang/Object;
    //   682: checkcast android/support/v4/app/Fragment
    //   685: astore_1
    //   686: aload_1
    //   687: ifnonnull -> 733
    //   690: new java/lang/StringBuilder
    //   693: dup
    //   694: invokespecial <init> : ()V
    //   697: astore_2
    //   698: aload_2
    //   699: ldc_w 'No instantiated fragment for index #'
    //   702: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   705: pop
    //   706: aload_2
    //   707: aload_3
    //   708: getfield mAdded : [I
    //   711: iload #6
    //   713: iaload
    //   714: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   717: pop
    //   718: aload_0
    //   719: new java/lang/IllegalStateException
    //   722: dup
    //   723: aload_2
    //   724: invokevirtual toString : ()Ljava/lang/String;
    //   727: invokespecial <init> : (Ljava/lang/String;)V
    //   730: invokespecial throwException : (Ljava/lang/RuntimeException;)V
    //   733: aload_1
    //   734: iconst_1
    //   735: putfield mAdded : Z
    //   738: getstatic android/support/v4/app/FragmentManagerImpl.DEBUG : Z
    //   741: ifeq -> 791
    //   744: new java/lang/StringBuilder
    //   747: dup
    //   748: invokespecial <init> : ()V
    //   751: astore_2
    //   752: aload_2
    //   753: ldc_w 'restoreAllState: added #'
    //   756: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   759: pop
    //   760: aload_2
    //   761: iload #6
    //   763: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   766: pop
    //   767: aload_2
    //   768: ldc_w ': '
    //   771: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   774: pop
    //   775: aload_2
    //   776: aload_1
    //   777: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   780: pop
    //   781: ldc 'FragmentManager'
    //   783: aload_2
    //   784: invokevirtual toString : ()Ljava/lang/String;
    //   787: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   790: pop
    //   791: aload_0
    //   792: getfield mAdded : Ljava/util/ArrayList;
    //   795: aload_1
    //   796: invokevirtual contains : (Ljava/lang/Object;)Z
    //   799: ifne -> 831
    //   802: aload_0
    //   803: getfield mAdded : Ljava/util/ArrayList;
    //   806: astore_2
    //   807: aload_2
    //   808: monitorenter
    //   809: aload_0
    //   810: getfield mAdded : Ljava/util/ArrayList;
    //   813: aload_1
    //   814: invokevirtual add : (Ljava/lang/Object;)Z
    //   817: pop
    //   818: aload_2
    //   819: monitorexit
    //   820: iinc #6, 1
    //   823: goto -> 659
    //   826: astore_1
    //   827: aload_2
    //   828: monitorexit
    //   829: aload_1
    //   830: athrow
    //   831: new java/lang/IllegalStateException
    //   834: dup
    //   835: ldc_w 'Already added!'
    //   838: invokespecial <init> : (Ljava/lang/String;)V
    //   841: athrow
    //   842: aload_3
    //   843: getfield mBackStack : [Landroid/support/v4/app/BackStackState;
    //   846: ifnull -> 1022
    //   849: aload_0
    //   850: new java/util/ArrayList
    //   853: dup
    //   854: aload_3
    //   855: getfield mBackStack : [Landroid/support/v4/app/BackStackState;
    //   858: arraylength
    //   859: invokespecial <init> : (I)V
    //   862: putfield mBackStack : Ljava/util/ArrayList;
    //   865: iconst_0
    //   866: istore #6
    //   868: aload_3
    //   869: getfield mBackStack : [Landroid/support/v4/app/BackStackState;
    //   872: astore_1
    //   873: iload #6
    //   875: aload_1
    //   876: arraylength
    //   877: if_icmpge -> 1027
    //   880: aload_1
    //   881: iload #6
    //   883: aaload
    //   884: aload_0
    //   885: invokevirtual instantiate : (Landroid/support/v4/app/FragmentManagerImpl;)Landroid/support/v4/app/BackStackRecord;
    //   888: astore_1
    //   889: getstatic android/support/v4/app/FragmentManagerImpl.DEBUG : Z
    //   892: ifeq -> 989
    //   895: new java/lang/StringBuilder
    //   898: dup
    //   899: invokespecial <init> : ()V
    //   902: astore_2
    //   903: aload_2
    //   904: ldc_w 'restoreAllState: back stack #'
    //   907: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   910: pop
    //   911: aload_2
    //   912: iload #6
    //   914: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   917: pop
    //   918: aload_2
    //   919: ldc_w ' (index '
    //   922: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   925: pop
    //   926: aload_2
    //   927: aload_1
    //   928: getfield mIndex : I
    //   931: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   934: pop
    //   935: aload_2
    //   936: ldc_w '): '
    //   939: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   942: pop
    //   943: aload_2
    //   944: aload_1
    //   945: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   948: pop
    //   949: ldc 'FragmentManager'
    //   951: aload_2
    //   952: invokevirtual toString : ()Ljava/lang/String;
    //   955: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   958: pop
    //   959: new java/io/PrintWriter
    //   962: dup
    //   963: new android/support/v4/util/LogWriter
    //   966: dup
    //   967: ldc 'FragmentManager'
    //   969: invokespecial <init> : (Ljava/lang/String;)V
    //   972: invokespecial <init> : (Ljava/io/Writer;)V
    //   975: astore_2
    //   976: aload_1
    //   977: ldc_w '  '
    //   980: aload_2
    //   981: iconst_0
    //   982: invokevirtual dump : (Ljava/lang/String;Ljava/io/PrintWriter;Z)V
    //   985: aload_2
    //   986: invokevirtual close : ()V
    //   989: aload_0
    //   990: getfield mBackStack : Ljava/util/ArrayList;
    //   993: aload_1
    //   994: invokevirtual add : (Ljava/lang/Object;)Z
    //   997: pop
    //   998: aload_1
    //   999: getfield mIndex : I
    //   1002: istore #7
    //   1004: iload #7
    //   1006: iflt -> 1016
    //   1009: aload_0
    //   1010: iload #7
    //   1012: aload_1
    //   1013: invokevirtual setBackStackIndex : (ILandroid/support/v4/app/BackStackRecord;)V
    //   1016: iinc #6, 1
    //   1019: goto -> 868
    //   1022: aload_0
    //   1023: aconst_null
    //   1024: putfield mBackStack : Ljava/util/ArrayList;
    //   1027: aload_3
    //   1028: getfield mPrimaryNavActiveIndex : I
    //   1031: istore #6
    //   1033: iload #6
    //   1035: iflt -> 1054
    //   1038: aload_0
    //   1039: aload_0
    //   1040: getfield mActive : Landroid/util/SparseArray;
    //   1043: iload #6
    //   1045: invokevirtual get : (I)Ljava/lang/Object;
    //   1048: checkcast android/support/v4/app/Fragment
    //   1051: putfield mPrimaryNav : Landroid/support/v4/app/Fragment;
    //   1054: aload_0
    //   1055: aload_3
    //   1056: getfield mNextFragmentIndex : I
    //   1059: putfield mNextFragmentIndex : I
    //   1062: return
    // Exception table:
    //   from	to	target	type
    //   809	820	826	finally
    //   827	829	826	finally
  }
  
  FragmentManagerNonConfig retainNonConfig() {
    setRetaining(this.mSavedNonConfig);
    return this.mSavedNonConfig;
  }
  
  Parcelable saveAllState() {
    StringBuilder stringBuilder;
    forcePostponedTransactions();
    endAnimatingAwayFragments();
    execPendingActions();
    this.mStateSaved = true;
    BackStackState[] arrayOfBackStackState1 = null;
    this.mSavedNonConfig = null;
    SparseArray<Fragment> sparseArray = this.mActive;
    if (sparseArray == null || sparseArray.size() <= 0)
      return null; 
    int i = this.mActive.size();
    FragmentState[] arrayOfFragmentState = new FragmentState[i];
    boolean bool = false;
    byte b = 0;
    int j = 0;
    while (b < i) {
      Fragment fragment1 = (Fragment)this.mActive.valueAt(b);
      if (fragment1 != null) {
        if (fragment1.mIndex < 0) {
          stringBuilder = new StringBuilder();
          stringBuilder.append("Failure saving state: active ");
          stringBuilder.append(fragment1);
          stringBuilder.append(" has cleared index: ");
          stringBuilder.append(fragment1.mIndex);
          throwException(new IllegalStateException(stringBuilder.toString()));
        } 
        FragmentState fragmentState = new FragmentState(fragment1);
        arrayOfFragmentState[b] = fragmentState;
        if (fragment1.mState > 0 && fragmentState.mSavedFragmentState == null) {
          fragmentState.mSavedFragmentState = saveFragmentBasicState(fragment1);
          Fragment fragment2 = fragment1.mTarget;
          if (fragment2 != null) {
            if (fragment2.mIndex < 0) {
              StringBuilder stringBuilder1 = new StringBuilder();
              stringBuilder1.append("Failure saving state: ");
              stringBuilder1.append(fragment1);
              stringBuilder1.append(" has target not in fragment manager: ");
              stringBuilder1.append(fragment1.mTarget);
              throwException(new IllegalStateException(stringBuilder1.toString()));
            } 
            if (fragmentState.mSavedFragmentState == null)
              fragmentState.mSavedFragmentState = new Bundle(); 
            putFragment(fragmentState.mSavedFragmentState, "android:target_state", fragment1.mTarget);
            j = fragment1.mTargetRequestCode;
            if (j != 0)
              fragmentState.mSavedFragmentState.putInt("android:target_req_state", j); 
          } 
        } else {
          fragmentState.mSavedFragmentState = fragment1.mSavedFragmentState;
        } 
        if (DEBUG) {
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append("Saved state of ");
          stringBuilder1.append(fragment1);
          stringBuilder1.append(": ");
          stringBuilder1.append(fragmentState.mSavedFragmentState);
          Log.v("FragmentManager", stringBuilder1.toString());
        } 
        j = 1;
      } 
      b++;
    } 
    if (j == 0) {
      if (DEBUG)
        Log.v("FragmentManager", "saveAllState: no fragments!"); 
      return null;
    } 
    j = this.mAdded.size();
    if (j > 0) {
      int[] arrayOfInt = new int[j];
      b = 0;
      while (true) {
        int[] arrayOfInt1 = arrayOfInt;
        if (b < j) {
          i = ((Fragment)this.mAdded.get(b)).mIndex;
          arrayOfInt[b] = i;
          if (i < 0) {
            StringBuilder stringBuilder1 = new StringBuilder();
            stringBuilder1.append("Failure saving state: active ");
            stringBuilder1.append(this.mAdded.get(b));
            stringBuilder1.append(" has cleared index: ");
            stringBuilder1.append(arrayOfInt[b]);
            throwException(new IllegalStateException(stringBuilder1.toString()));
          } 
          if (DEBUG) {
            StringBuilder stringBuilder1 = new StringBuilder();
            stringBuilder1.append("saveAllState: adding fragment #");
            stringBuilder1.append(b);
            stringBuilder1.append(": ");
            stringBuilder1.append(this.mAdded.get(b));
            Log.v("FragmentManager", stringBuilder1.toString());
          } 
          b++;
          continue;
        } 
        break;
      } 
    } else {
      sparseArray = null;
    } 
    ArrayList<BackStackRecord> arrayList = this.mBackStack;
    BackStackState[] arrayOfBackStackState2 = arrayOfBackStackState1;
    if (arrayList != null) {
      j = arrayList.size();
      arrayOfBackStackState2 = arrayOfBackStackState1;
      if (j > 0) {
        arrayOfBackStackState1 = new BackStackState[j];
        b = bool;
        while (true) {
          arrayOfBackStackState2 = arrayOfBackStackState1;
          if (b < j) {
            arrayOfBackStackState1[b] = new BackStackState(this.mBackStack.get(b));
            if (DEBUG) {
              stringBuilder = new StringBuilder();
              stringBuilder.append("saveAllState: adding back stack #");
              stringBuilder.append(b);
              stringBuilder.append(": ");
              stringBuilder.append(this.mBackStack.get(b));
              Log.v("FragmentManager", stringBuilder.toString());
            } 
            b++;
            continue;
          } 
          break;
        } 
      } 
    } 
    FragmentManagerState fragmentManagerState = new FragmentManagerState();
    fragmentManagerState.mActive = arrayOfFragmentState;
    fragmentManagerState.mAdded = (int[])sparseArray;
    fragmentManagerState.mBackStack = (BackStackState[])stringBuilder;
    Fragment fragment = this.mPrimaryNav;
    if (fragment != null)
      fragmentManagerState.mPrimaryNavActiveIndex = fragment.mIndex; 
    fragmentManagerState.mNextFragmentIndex = this.mNextFragmentIndex;
    saveNonConfig();
    return fragmentManagerState;
  }
  
  Bundle saveFragmentBasicState(Fragment paramFragment) {
    if (this.mStateBundle == null)
      this.mStateBundle = new Bundle(); 
    paramFragment.performSaveInstanceState(this.mStateBundle);
    dispatchOnFragmentSaveInstanceState(paramFragment, this.mStateBundle, false);
    boolean bool = this.mStateBundle.isEmpty();
    Bundle bundle1 = null;
    if (!bool) {
      bundle1 = this.mStateBundle;
      this.mStateBundle = null;
    } 
    if (paramFragment.mView != null)
      saveFragmentViewState(paramFragment); 
    Bundle bundle2 = bundle1;
    if (paramFragment.mSavedViewState != null) {
      bundle2 = bundle1;
      if (bundle1 == null)
        bundle2 = new Bundle(); 
      bundle2.putSparseParcelableArray("android:view_state", paramFragment.mSavedViewState);
    } 
    bundle1 = bundle2;
    if (!paramFragment.mUserVisibleHint) {
      bundle1 = bundle2;
      if (bundle2 == null)
        bundle1 = new Bundle(); 
      bundle1.putBoolean("android:user_visible_hint", paramFragment.mUserVisibleHint);
    } 
    return bundle1;
  }
  
  public Fragment.SavedState saveFragmentInstanceState(Fragment paramFragment) {
    if (paramFragment.mIndex < 0) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Fragment ");
      stringBuilder.append(paramFragment);
      stringBuilder.append(" is not currently in the FragmentManager");
      throwException(new IllegalStateException(stringBuilder.toString()));
    } 
    int i = paramFragment.mState;
    Fragment.SavedState savedState2 = null;
    Fragment.SavedState savedState1 = savedState2;
    if (i > 0) {
      Bundle bundle = saveFragmentBasicState(paramFragment);
      savedState1 = savedState2;
      if (bundle != null)
        savedState1 = new Fragment.SavedState(bundle); 
    } 
    return savedState1;
  }
  
  void saveFragmentViewState(Fragment paramFragment) {
    if (paramFragment.mInnerView == null)
      return; 
    SparseArray<Parcelable> sparseArray = this.mStateArray;
    if (sparseArray == null) {
      this.mStateArray = new SparseArray();
    } else {
      sparseArray.clear();
    } 
    paramFragment.mInnerView.saveHierarchyState(this.mStateArray);
    if (this.mStateArray.size() > 0) {
      paramFragment.mSavedViewState = this.mStateArray;
      this.mStateArray = null;
    } 
  }
  
  void saveNonConfig() {
    List<FragmentManagerNonConfig> list1;
    List<FragmentManagerNonConfig> list2;
    if (this.mActive != null) {
      ArrayList<Fragment> arrayList1 = null;
      ArrayList<Fragment> arrayList2 = arrayList1;
      byte b = 0;
      while (true) {
        list1 = (List)arrayList1;
        list2 = (List)arrayList2;
        if (b < this.mActive.size()) {
          Fragment fragment = (Fragment)this.mActive.valueAt(b);
          list1 = (List)arrayList1;
          ArrayList<Fragment> arrayList = arrayList2;
          if (fragment != null) {
            FragmentManagerNonConfig fragmentManagerNonConfig;
            list2 = (List)arrayList1;
            if (fragment.mRetainInstance) {
              byte b1;
              list1 = (List)arrayList1;
              if (arrayList1 == null)
                list1 = new ArrayList(); 
              list1.add(fragment);
              Fragment fragment1 = fragment.mTarget;
              if (fragment1 != null) {
                b1 = fragment1.mIndex;
              } else {
                b1 = -1;
              } 
              fragment.mTargetIndex = b1;
              list2 = list1;
              if (DEBUG) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("retainNonConfig: keeping retained ");
                stringBuilder.append(fragment);
                Log.v("FragmentManager", stringBuilder.toString());
                list2 = list1;
              } 
            } 
            FragmentManagerImpl fragmentManagerImpl = fragment.mChildFragmentManager;
            if (fragmentManagerImpl != null) {
              fragmentManagerImpl.saveNonConfig();
              fragmentManagerNonConfig = fragment.mChildFragmentManager.mSavedNonConfig;
            } else {
              fragmentManagerNonConfig = ((Fragment)fragmentManagerNonConfig).mChildNonConfig;
            } 
            ArrayList<Fragment> arrayList3 = arrayList2;
            if (arrayList2 == null) {
              arrayList3 = arrayList2;
              if (fragmentManagerNonConfig != null) {
                arrayList2 = new ArrayList<Fragment>(this.mActive.size());
                byte b1 = 0;
                while (true) {
                  arrayList3 = arrayList2;
                  if (b1 < b) {
                    arrayList2.add(null);
                    b1++;
                    continue;
                  } 
                  break;
                } 
              } 
            } 
            list1 = list2;
            arrayList = arrayList3;
            if (arrayList3 != null) {
              arrayList3.add(fragmentManagerNonConfig);
              arrayList = arrayList3;
              list1 = list2;
            } 
          } 
          b++;
          arrayList1 = (ArrayList)list1;
          arrayList2 = arrayList;
          continue;
        } 
        break;
      } 
    } else {
      list1 = null;
      list2 = list1;
    } 
    if (list1 == null && list2 == null) {
      this.mSavedNonConfig = null;
    } else {
      this.mSavedNonConfig = new FragmentManagerNonConfig((List)list1, list2);
    } 
  }
  
  public void setBackStackIndex(int paramInt, BackStackRecord paramBackStackRecord) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   6: ifnonnull -> 22
    //   9: new java/util/ArrayList
    //   12: astore_3
    //   13: aload_3
    //   14: invokespecial <init> : ()V
    //   17: aload_0
    //   18: aload_3
    //   19: putfield mBackStackIndices : Ljava/util/ArrayList;
    //   22: aload_0
    //   23: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   26: invokevirtual size : ()I
    //   29: istore #4
    //   31: iload #4
    //   33: istore #5
    //   35: iload_1
    //   36: iload #4
    //   38: if_icmpge -> 106
    //   41: getstatic android/support/v4/app/FragmentManagerImpl.DEBUG : Z
    //   44: ifeq -> 93
    //   47: new java/lang/StringBuilder
    //   50: astore_3
    //   51: aload_3
    //   52: invokespecial <init> : ()V
    //   55: aload_3
    //   56: ldc_w 'Setting back stack index '
    //   59: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   62: pop
    //   63: aload_3
    //   64: iload_1
    //   65: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   68: pop
    //   69: aload_3
    //   70: ldc_w ' to '
    //   73: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   76: pop
    //   77: aload_3
    //   78: aload_2
    //   79: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   82: pop
    //   83: ldc 'FragmentManager'
    //   85: aload_3
    //   86: invokevirtual toString : ()Ljava/lang/String;
    //   89: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   92: pop
    //   93: aload_0
    //   94: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   97: iload_1
    //   98: aload_2
    //   99: invokevirtual set : (ILjava/lang/Object;)Ljava/lang/Object;
    //   102: pop
    //   103: goto -> 260
    //   106: iload #5
    //   108: iload_1
    //   109: if_icmpge -> 199
    //   112: aload_0
    //   113: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   116: aconst_null
    //   117: invokevirtual add : (Ljava/lang/Object;)Z
    //   120: pop
    //   121: aload_0
    //   122: getfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   125: ifnonnull -> 141
    //   128: new java/util/ArrayList
    //   131: astore_3
    //   132: aload_3
    //   133: invokespecial <init> : ()V
    //   136: aload_0
    //   137: aload_3
    //   138: putfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   141: getstatic android/support/v4/app/FragmentManagerImpl.DEBUG : Z
    //   144: ifeq -> 180
    //   147: new java/lang/StringBuilder
    //   150: astore_3
    //   151: aload_3
    //   152: invokespecial <init> : ()V
    //   155: aload_3
    //   156: ldc_w 'Adding available back stack index '
    //   159: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   162: pop
    //   163: aload_3
    //   164: iload #5
    //   166: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   169: pop
    //   170: ldc 'FragmentManager'
    //   172: aload_3
    //   173: invokevirtual toString : ()Ljava/lang/String;
    //   176: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   179: pop
    //   180: aload_0
    //   181: getfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   184: iload #5
    //   186: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   189: invokevirtual add : (Ljava/lang/Object;)Z
    //   192: pop
    //   193: iinc #5, 1
    //   196: goto -> 106
    //   199: getstatic android/support/v4/app/FragmentManagerImpl.DEBUG : Z
    //   202: ifeq -> 251
    //   205: new java/lang/StringBuilder
    //   208: astore_3
    //   209: aload_3
    //   210: invokespecial <init> : ()V
    //   213: aload_3
    //   214: ldc_w 'Adding back stack index '
    //   217: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   220: pop
    //   221: aload_3
    //   222: iload_1
    //   223: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   226: pop
    //   227: aload_3
    //   228: ldc_w ' with '
    //   231: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   234: pop
    //   235: aload_3
    //   236: aload_2
    //   237: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   240: pop
    //   241: ldc 'FragmentManager'
    //   243: aload_3
    //   244: invokevirtual toString : ()Ljava/lang/String;
    //   247: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   250: pop
    //   251: aload_0
    //   252: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   255: aload_2
    //   256: invokevirtual add : (Ljava/lang/Object;)Z
    //   259: pop
    //   260: aload_0
    //   261: monitorexit
    //   262: return
    //   263: astore_2
    //   264: aload_0
    //   265: monitorexit
    //   266: goto -> 271
    //   269: aload_2
    //   270: athrow
    //   271: goto -> 269
    // Exception table:
    //   from	to	target	type
    //   2	22	263	finally
    //   22	31	263	finally
    //   41	93	263	finally
    //   93	103	263	finally
    //   112	141	263	finally
    //   141	180	263	finally
    //   180	193	263	finally
    //   199	251	263	finally
    //   251	260	263	finally
    //   260	262	263	finally
    //   264	266	263	finally
  }
  
  public void setPrimaryNavigationFragment(Fragment paramFragment) {
    if (paramFragment == null || (this.mActive.get(paramFragment.mIndex) == paramFragment && (paramFragment.mHost == null || paramFragment.getFragmentManager() == this))) {
      this.mPrimaryNav = paramFragment;
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Fragment ");
    stringBuilder.append(paramFragment);
    stringBuilder.append(" is not an active fragment of FragmentManager ");
    stringBuilder.append(this);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public void showFragment(Fragment paramFragment) {
    if (DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("show: ");
      stringBuilder.append(paramFragment);
      Log.v("FragmentManager", stringBuilder.toString());
    } 
    if (paramFragment.mHidden) {
      paramFragment.mHidden = false;
      paramFragment.mHiddenChanged ^= 0x1;
    } 
  }
  
  void startPendingDeferredFragments() {
    if (this.mActive == null)
      return; 
    for (byte b = 0; b < this.mActive.size(); b++) {
      Fragment fragment = (Fragment)this.mActive.valueAt(b);
      if (fragment != null)
        performPendingDeferredStart(fragment); 
    } 
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder(128);
    stringBuilder.append("FragmentManager{");
    stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    stringBuilder.append(" in ");
    Fragment fragment = this.mParent;
    if (fragment != null) {
      DebugUtils.buildShortClassTag(fragment, stringBuilder);
    } else {
      DebugUtils.buildShortClassTag(this.mHost, stringBuilder);
    } 
    stringBuilder.append("}}");
    return stringBuilder.toString();
  }
  
  public void unregisterFragmentLifecycleCallbacks(FragmentManager.FragmentLifecycleCallbacks paramFragmentLifecycleCallbacks) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mLifecycleCallbacks : Ljava/util/concurrent/CopyOnWriteArrayList;
    //   4: astore_2
    //   5: aload_2
    //   6: monitorenter
    //   7: iconst_0
    //   8: istore_3
    //   9: aload_0
    //   10: getfield mLifecycleCallbacks : Ljava/util/concurrent/CopyOnWriteArrayList;
    //   13: invokevirtual size : ()I
    //   16: istore #4
    //   18: iload_3
    //   19: iload #4
    //   21: if_icmpge -> 60
    //   24: aload_0
    //   25: getfield mLifecycleCallbacks : Ljava/util/concurrent/CopyOnWriteArrayList;
    //   28: iload_3
    //   29: invokevirtual get : (I)Ljava/lang/Object;
    //   32: checkcast android/support/v4/util/Pair
    //   35: getfield first : Ljava/lang/Object;
    //   38: aload_1
    //   39: if_acmpne -> 54
    //   42: aload_0
    //   43: getfield mLifecycleCallbacks : Ljava/util/concurrent/CopyOnWriteArrayList;
    //   46: iload_3
    //   47: invokevirtual remove : (I)Ljava/lang/Object;
    //   50: pop
    //   51: goto -> 60
    //   54: iinc #3, 1
    //   57: goto -> 18
    //   60: aload_2
    //   61: monitorexit
    //   62: return
    //   63: astore_1
    //   64: aload_2
    //   65: monitorexit
    //   66: goto -> 71
    //   69: aload_1
    //   70: athrow
    //   71: goto -> 69
    // Exception table:
    //   from	to	target	type
    //   9	18	63	finally
    //   24	51	63	finally
    //   60	62	63	finally
    //   64	66	63	finally
  }
  
  private static class AnimateOnHWLayerIfNeededListener extends AnimationListenerWrapper {
    View mView;
    
    AnimateOnHWLayerIfNeededListener(View param1View, Animation.AnimationListener param1AnimationListener) {
      super(param1AnimationListener);
      this.mView = param1View;
    }
    
    @CallSuper
    public void onAnimationEnd(Animation param1Animation) {
      if (ViewCompat.isAttachedToWindow(this.mView) || Build.VERSION.SDK_INT >= 24) {
        this.mView.post(new Runnable() {
              public void run() {
                FragmentManagerImpl.AnimateOnHWLayerIfNeededListener.this.mView.setLayerType(0, null);
              }
            });
      } else {
        this.mView.setLayerType(0, null);
      } 
      super.onAnimationEnd(param1Animation);
    }
  }
  
  class null implements Runnable {
    public void run() {
      this.this$0.mView.setLayerType(0, null);
    }
  }
  
  private static class AnimationListenerWrapper implements Animation.AnimationListener {
    private final Animation.AnimationListener mWrapped;
    
    private AnimationListenerWrapper(Animation.AnimationListener param1AnimationListener) {
      this.mWrapped = param1AnimationListener;
    }
    
    @CallSuper
    public void onAnimationEnd(Animation param1Animation) {
      Animation.AnimationListener animationListener = this.mWrapped;
      if (animationListener != null)
        animationListener.onAnimationEnd(param1Animation); 
    }
    
    @CallSuper
    public void onAnimationRepeat(Animation param1Animation) {
      Animation.AnimationListener animationListener = this.mWrapped;
      if (animationListener != null)
        animationListener.onAnimationRepeat(param1Animation); 
    }
    
    @CallSuper
    public void onAnimationStart(Animation param1Animation) {
      Animation.AnimationListener animationListener = this.mWrapped;
      if (animationListener != null)
        animationListener.onAnimationStart(param1Animation); 
    }
  }
  
  private static class AnimationOrAnimator {
    public final Animation animation = null;
    
    public final Animator animator;
    
    private AnimationOrAnimator(Animator param1Animator) {
      this.animator = param1Animator;
      if (param1Animator != null)
        return; 
      throw new IllegalStateException("Animator cannot be null");
    }
    
    private AnimationOrAnimator(Animation param1Animation) {
      this.animator = null;
      if (param1Animation != null)
        return; 
      throw new IllegalStateException("Animation cannot be null");
    }
  }
  
  private static class AnimatorOnHWLayerIfNeededListener extends AnimatorListenerAdapter {
    View mView;
    
    AnimatorOnHWLayerIfNeededListener(View param1View) {
      this.mView = param1View;
    }
    
    public void onAnimationEnd(Animator param1Animator) {
      this.mView.setLayerType(0, null);
      param1Animator.removeListener((Animator.AnimatorListener)this);
    }
    
    public void onAnimationStart(Animator param1Animator) {
      this.mView.setLayerType(2, null);
    }
  }
  
  static class FragmentTag {
    public static final int[] Fragment = new int[] { 16842755, 16842960, 16842961 };
    
    public static final int Fragment_id = 1;
    
    public static final int Fragment_name = 0;
    
    public static final int Fragment_tag = 2;
  }
  
  static interface OpGenerator {
    boolean generateOps(ArrayList<BackStackRecord> param1ArrayList, ArrayList<Boolean> param1ArrayList1);
  }
  
  private class PopBackStackState implements OpGenerator {
    final int mFlags;
    
    final int mId;
    
    final String mName;
    
    PopBackStackState(String param1String, int param1Int1, int param1Int2) {
      this.mName = param1String;
      this.mId = param1Int1;
      this.mFlags = param1Int2;
    }
    
    public boolean generateOps(ArrayList<BackStackRecord> param1ArrayList, ArrayList<Boolean> param1ArrayList1) {
      Fragment fragment = FragmentManagerImpl.this.mPrimaryNav;
      if (fragment != null && this.mId < 0 && this.mName == null) {
        FragmentManager fragmentManager = fragment.peekChildFragmentManager();
        if (fragmentManager != null && fragmentManager.popBackStackImmediate())
          return false; 
      } 
      return FragmentManagerImpl.this.popBackStackState(param1ArrayList, param1ArrayList1, this.mName, this.mId, this.mFlags);
    }
  }
  
  static class StartEnterTransitionListener implements Fragment.OnStartEnterTransitionListener {
    private final boolean mIsBack;
    
    private int mNumPostponed;
    
    private final BackStackRecord mRecord;
    
    StartEnterTransitionListener(BackStackRecord param1BackStackRecord, boolean param1Boolean) {
      this.mIsBack = param1Boolean;
      this.mRecord = param1BackStackRecord;
    }
    
    public void cancelTransaction() {
      BackStackRecord backStackRecord = this.mRecord;
      backStackRecord.mManager.completeExecute(backStackRecord, this.mIsBack, false, false);
    }
    
    public void completeTransaction() {
      int i = this.mNumPostponed;
      byte b = 0;
      if (i > 0) {
        i = 1;
      } else {
        i = 0;
      } 
      FragmentManagerImpl fragmentManagerImpl = this.mRecord.mManager;
      int j = fragmentManagerImpl.mAdded.size();
      while (b < j) {
        Fragment fragment = fragmentManagerImpl.mAdded.get(b);
        fragment.setOnStartEnterTransitionListener(null);
        if (i != 0 && fragment.isPostponed())
          fragment.startPostponedEnterTransition(); 
        b++;
      } 
      BackStackRecord backStackRecord = this.mRecord;
      backStackRecord.mManager.completeExecute(backStackRecord, this.mIsBack, i ^ 0x1, true);
    }
    
    public boolean isReady() {
      boolean bool;
      if (this.mNumPostponed == 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public void onStartEnterTransition() {
      int i = this.mNumPostponed - 1;
      this.mNumPostponed = i;
      if (i != 0)
        return; 
      this.mRecord.mManager.scheduleCommit();
    }
    
    public void startListening() {
      this.mNumPostponed++;
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/app/FragmentManagerImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */