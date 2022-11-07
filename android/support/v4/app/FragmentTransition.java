package android.support.v4.app;

import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.ViewCompat;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

class FragmentTransition {
  private static final int[] INVERSE_OPS = new int[] { 0, 3, 0, 1, 5, 4, 7, 6, 9, 8 };
  
  private static void addSharedElementsWithMatchingNames(ArrayList<View> paramArrayList, ArrayMap<String, View> paramArrayMap, Collection<String> paramCollection) {
    for (int i = paramArrayMap.size() - 1; i >= 0; i--) {
      View view = (View)paramArrayMap.valueAt(i);
      if (paramCollection.contains(ViewCompat.getTransitionName(view)))
        paramArrayList.add(view); 
    } 
  }
  
  private static void addToFirstInLastOut(BackStackRecord paramBackStackRecord, BackStackRecord.Op paramOp, SparseArray<FragmentContainerTransition> paramSparseArray, boolean paramBoolean1, boolean paramBoolean2) {
    // Byte code:
    //   0: aload_1
    //   1: getfield fragment : Landroid/support/v4/app/Fragment;
    //   4: astore #5
    //   6: aload #5
    //   8: ifnonnull -> 12
    //   11: return
    //   12: aload #5
    //   14: getfield mContainerId : I
    //   17: istore #6
    //   19: iload #6
    //   21: ifne -> 25
    //   24: return
    //   25: iload_3
    //   26: ifeq -> 42
    //   29: getstatic android/support/v4/app/FragmentTransition.INVERSE_OPS : [I
    //   32: aload_1
    //   33: getfield cmd : I
    //   36: iaload
    //   37: istore #7
    //   39: goto -> 48
    //   42: aload_1
    //   43: getfield cmd : I
    //   46: istore #7
    //   48: iconst_0
    //   49: istore #8
    //   51: iconst_0
    //   52: istore #9
    //   54: iload #7
    //   56: iconst_1
    //   57: if_icmpeq -> 285
    //   60: iload #7
    //   62: iconst_3
    //   63: if_icmpeq -> 197
    //   66: iload #7
    //   68: iconst_4
    //   69: if_icmpeq -> 146
    //   72: iload #7
    //   74: iconst_5
    //   75: if_icmpeq -> 104
    //   78: iload #7
    //   80: bipush #6
    //   82: if_icmpeq -> 197
    //   85: iload #7
    //   87: bipush #7
    //   89: if_icmpeq -> 285
    //   92: iconst_0
    //   93: istore #7
    //   95: iconst_0
    //   96: istore #10
    //   98: iconst_0
    //   99: istore #11
    //   101: goto -> 331
    //   104: iload #4
    //   106: ifeq -> 136
    //   109: aload #5
    //   111: getfield mHiddenChanged : Z
    //   114: ifeq -> 322
    //   117: aload #5
    //   119: getfield mHidden : Z
    //   122: ifne -> 322
    //   125: aload #5
    //   127: getfield mAdded : Z
    //   130: ifeq -> 322
    //   133: goto -> 316
    //   136: aload #5
    //   138: getfield mHidden : Z
    //   141: istore #9
    //   143: goto -> 325
    //   146: iload #4
    //   148: ifeq -> 178
    //   151: aload #5
    //   153: getfield mHiddenChanged : Z
    //   156: ifeq -> 243
    //   159: aload #5
    //   161: getfield mAdded : Z
    //   164: ifeq -> 243
    //   167: aload #5
    //   169: getfield mHidden : Z
    //   172: ifeq -> 243
    //   175: goto -> 237
    //   178: aload #5
    //   180: getfield mAdded : Z
    //   183: ifeq -> 243
    //   186: aload #5
    //   188: getfield mHidden : Z
    //   191: ifne -> 243
    //   194: goto -> 175
    //   197: iload #4
    //   199: ifeq -> 249
    //   202: aload #5
    //   204: getfield mAdded : Z
    //   207: ifne -> 243
    //   210: aload #5
    //   212: getfield mView : Landroid/view/View;
    //   215: astore_1
    //   216: aload_1
    //   217: ifnull -> 243
    //   220: aload_1
    //   221: invokevirtual getVisibility : ()I
    //   224: ifne -> 243
    //   227: aload #5
    //   229: getfield mPostponedAlpha : F
    //   232: fconst_0
    //   233: fcmpl
    //   234: iflt -> 243
    //   237: iconst_1
    //   238: istore #7
    //   240: goto -> 268
    //   243: iconst_0
    //   244: istore #7
    //   246: goto -> 268
    //   249: aload #5
    //   251: getfield mAdded : Z
    //   254: ifeq -> 243
    //   257: aload #5
    //   259: getfield mHidden : Z
    //   262: ifne -> 243
    //   265: goto -> 237
    //   268: iload #7
    //   270: istore #11
    //   272: iconst_0
    //   273: istore #7
    //   275: iconst_1
    //   276: istore #10
    //   278: iload #8
    //   280: istore #9
    //   282: goto -> 331
    //   285: iload #4
    //   287: ifeq -> 300
    //   290: aload #5
    //   292: getfield mIsNewlyAdded : Z
    //   295: istore #9
    //   297: goto -> 325
    //   300: aload #5
    //   302: getfield mAdded : Z
    //   305: ifne -> 322
    //   308: aload #5
    //   310: getfield mHidden : Z
    //   313: ifne -> 322
    //   316: iconst_1
    //   317: istore #9
    //   319: goto -> 325
    //   322: iconst_0
    //   323: istore #9
    //   325: iconst_1
    //   326: istore #7
    //   328: goto -> 95
    //   331: aload_2
    //   332: iload #6
    //   334: invokevirtual get : (I)Ljava/lang/Object;
    //   337: checkcast android/support/v4/app/FragmentTransition$FragmentContainerTransition
    //   340: astore #12
    //   342: aload #12
    //   344: astore_1
    //   345: iload #9
    //   347: ifeq -> 375
    //   350: aload #12
    //   352: aload_2
    //   353: iload #6
    //   355: invokestatic ensureContainer : (Landroid/support/v4/app/FragmentTransition$FragmentContainerTransition;Landroid/util/SparseArray;I)Landroid/support/v4/app/FragmentTransition$FragmentContainerTransition;
    //   358: astore_1
    //   359: aload_1
    //   360: aload #5
    //   362: putfield lastIn : Landroid/support/v4/app/Fragment;
    //   365: aload_1
    //   366: iload_3
    //   367: putfield lastInIsPop : Z
    //   370: aload_1
    //   371: aload_0
    //   372: putfield lastInTransaction : Landroid/support/v4/app/BackStackRecord;
    //   375: iload #4
    //   377: ifne -> 452
    //   380: iload #7
    //   382: ifeq -> 452
    //   385: aload_1
    //   386: ifnull -> 403
    //   389: aload_1
    //   390: getfield firstOut : Landroid/support/v4/app/Fragment;
    //   393: aload #5
    //   395: if_acmpne -> 403
    //   398: aload_1
    //   399: aconst_null
    //   400: putfield firstOut : Landroid/support/v4/app/Fragment;
    //   403: aload_0
    //   404: getfield mManager : Landroid/support/v4/app/FragmentManagerImpl;
    //   407: astore #12
    //   409: aload #5
    //   411: getfield mState : I
    //   414: iconst_1
    //   415: if_icmpge -> 452
    //   418: aload #12
    //   420: getfield mCurState : I
    //   423: iconst_1
    //   424: if_icmplt -> 452
    //   427: aload_0
    //   428: getfield mReorderingAllowed : Z
    //   431: ifne -> 452
    //   434: aload #12
    //   436: aload #5
    //   438: invokevirtual makeActive : (Landroid/support/v4/app/Fragment;)V
    //   441: aload #12
    //   443: aload #5
    //   445: iconst_1
    //   446: iconst_0
    //   447: iconst_0
    //   448: iconst_0
    //   449: invokevirtual moveToState : (Landroid/support/v4/app/Fragment;IIIZ)V
    //   452: aload_1
    //   453: astore #12
    //   455: iload #11
    //   457: ifeq -> 502
    //   460: aload_1
    //   461: ifnull -> 474
    //   464: aload_1
    //   465: astore #12
    //   467: aload_1
    //   468: getfield firstOut : Landroid/support/v4/app/Fragment;
    //   471: ifnonnull -> 502
    //   474: aload_1
    //   475: aload_2
    //   476: iload #6
    //   478: invokestatic ensureContainer : (Landroid/support/v4/app/FragmentTransition$FragmentContainerTransition;Landroid/util/SparseArray;I)Landroid/support/v4/app/FragmentTransition$FragmentContainerTransition;
    //   481: astore #12
    //   483: aload #12
    //   485: aload #5
    //   487: putfield firstOut : Landroid/support/v4/app/Fragment;
    //   490: aload #12
    //   492: iload_3
    //   493: putfield firstOutIsPop : Z
    //   496: aload #12
    //   498: aload_0
    //   499: putfield firstOutTransaction : Landroid/support/v4/app/BackStackRecord;
    //   502: iload #4
    //   504: ifne -> 533
    //   507: iload #10
    //   509: ifeq -> 533
    //   512: aload #12
    //   514: ifnull -> 533
    //   517: aload #12
    //   519: getfield lastIn : Landroid/support/v4/app/Fragment;
    //   522: aload #5
    //   524: if_acmpne -> 533
    //   527: aload #12
    //   529: aconst_null
    //   530: putfield lastIn : Landroid/support/v4/app/Fragment;
    //   533: return
  }
  
  public static void calculateFragments(BackStackRecord paramBackStackRecord, SparseArray<FragmentContainerTransition> paramSparseArray, boolean paramBoolean) {
    int i = paramBackStackRecord.mOps.size();
    for (byte b = 0; b < i; b++)
      addToFirstInLastOut(paramBackStackRecord, paramBackStackRecord.mOps.get(b), paramSparseArray, false, paramBoolean); 
  }
  
  private static ArrayMap<String, String> calculateNameOverrides(int paramInt1, ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1, int paramInt2, int paramInt3) {
    ArrayMap<String, String> arrayMap = new ArrayMap();
    while (--paramInt3 >= paramInt2) {
      BackStackRecord backStackRecord = paramArrayList.get(paramInt3);
      if (backStackRecord.interactsWith(paramInt1)) {
        boolean bool = ((Boolean)paramArrayList1.get(paramInt3)).booleanValue();
        ArrayList<String> arrayList = backStackRecord.mSharedElementSourceNames;
        if (arrayList != null) {
          ArrayList<String> arrayList1;
          int i = arrayList.size();
          if (bool) {
            arrayList = backStackRecord.mSharedElementSourceNames;
            arrayList1 = backStackRecord.mSharedElementTargetNames;
          } else {
            arrayList1 = backStackRecord.mSharedElementSourceNames;
            arrayList = backStackRecord.mSharedElementTargetNames;
          } 
          for (byte b = 0; b < i; b++) {
            String str2 = arrayList1.get(b);
            String str1 = arrayList.get(b);
            String str3 = (String)arrayMap.remove(str1);
            if (str3 != null) {
              arrayMap.put(str2, str3);
            } else {
              arrayMap.put(str2, str1);
            } 
          } 
        } 
      } 
      paramInt3--;
    } 
    return arrayMap;
  }
  
  public static void calculatePopFragments(BackStackRecord paramBackStackRecord, SparseArray<FragmentContainerTransition> paramSparseArray, boolean paramBoolean) {
    if (!paramBackStackRecord.mManager.mContainer.onHasView())
      return; 
    for (int i = paramBackStackRecord.mOps.size() - 1; i >= 0; i--)
      addToFirstInLastOut(paramBackStackRecord, paramBackStackRecord.mOps.get(i), paramSparseArray, true, paramBoolean); 
  }
  
  private static void callSharedElementStartEnd(Fragment paramFragment1, Fragment paramFragment2, boolean paramBoolean1, ArrayMap<String, View> paramArrayMap, boolean paramBoolean2) {
    SharedElementCallback sharedElementCallback;
    if (paramBoolean1) {
      sharedElementCallback = paramFragment2.getEnterTransitionCallback();
    } else {
      sharedElementCallback = sharedElementCallback.getEnterTransitionCallback();
    } 
    if (sharedElementCallback != null) {
      int i;
      ArrayList<Object> arrayList2 = new ArrayList();
      ArrayList<Object> arrayList1 = new ArrayList();
      byte b = 0;
      if (paramArrayMap == null) {
        i = 0;
      } else {
        i = paramArrayMap.size();
      } 
      while (b < i) {
        arrayList1.add(paramArrayMap.keyAt(b));
        arrayList2.add(paramArrayMap.valueAt(b));
        b++;
      } 
      if (paramBoolean2) {
        sharedElementCallback.onSharedElementStart(arrayList1, arrayList2, null);
      } else {
        sharedElementCallback.onSharedElementEnd(arrayList1, arrayList2, null);
      } 
    } 
  }
  
  @RequiresApi(21)
  private static ArrayMap<String, View> captureInSharedElements(ArrayMap<String, String> paramArrayMap, Object<String> paramObject, FragmentContainerTransition paramFragmentContainerTransition) {
    ArrayList<String> arrayList;
    SharedElementCallback sharedElementCallback;
    Fragment fragment = paramFragmentContainerTransition.lastIn;
    View view = fragment.getView();
    if (paramArrayMap.isEmpty() || paramObject == null || view == null) {
      paramArrayMap.clear();
      return null;
    } 
    ArrayMap<String, View> arrayMap = new ArrayMap();
    FragmentTransitionCompat21.findNamedViews((Map<String, View>)arrayMap, view);
    paramObject = (Object<String>)paramFragmentContainerTransition.lastInTransaction;
    if (paramFragmentContainerTransition.lastInIsPop) {
      sharedElementCallback = fragment.getExitTransitionCallback();
      paramObject = (Object<String>)((BackStackRecord)paramObject).mSharedElementSourceNames;
    } else {
      sharedElementCallback = fragment.getEnterTransitionCallback();
      arrayList = ((BackStackRecord)paramObject).mSharedElementTargetNames;
    } 
    if (arrayList != null)
      arrayMap.retainAll(arrayList); 
    if (sharedElementCallback != null) {
      sharedElementCallback.onMapSharedElements(arrayList, (Map<String, View>)arrayMap);
      for (int i = arrayList.size() - 1; i >= 0; i--) {
        String str1;
        String str2 = arrayList.get(i);
        View view1 = (View)arrayMap.get(str2);
        if (view1 == null) {
          str1 = findKeyForValue(paramArrayMap, str2);
          if (str1 != null)
            paramArrayMap.remove(str1); 
        } else if (!str2.equals(ViewCompat.getTransitionName((View)str1))) {
          str2 = findKeyForValue(paramArrayMap, str2);
          if (str2 != null)
            paramArrayMap.put(str2, ViewCompat.getTransitionName((View)str1)); 
        } 
      } 
    } else {
      retainValues(paramArrayMap, arrayMap);
    } 
    return arrayMap;
  }
  
  @RequiresApi(21)
  private static ArrayMap<String, View> captureOutSharedElements(ArrayMap<String, String> paramArrayMap, Object<String> paramObject, FragmentContainerTransition paramFragmentContainerTransition) {
    ArrayList<String> arrayList;
    SharedElementCallback sharedElementCallback;
    if (paramArrayMap.isEmpty() || paramObject == null) {
      paramArrayMap.clear();
      return null;
    } 
    Fragment fragment = paramFragmentContainerTransition.firstOut;
    ArrayMap<String, View> arrayMap = new ArrayMap();
    FragmentTransitionCompat21.findNamedViews((Map<String, View>)arrayMap, fragment.getView());
    paramObject = (Object<String>)paramFragmentContainerTransition.firstOutTransaction;
    if (paramFragmentContainerTransition.firstOutIsPop) {
      sharedElementCallback = fragment.getEnterTransitionCallback();
      paramObject = (Object<String>)((BackStackRecord)paramObject).mSharedElementTargetNames;
    } else {
      sharedElementCallback = fragment.getExitTransitionCallback();
      arrayList = ((BackStackRecord)paramObject).mSharedElementSourceNames;
    } 
    arrayMap.retainAll(arrayList);
    if (sharedElementCallback != null) {
      sharedElementCallback.onMapSharedElements(arrayList, (Map<String, View>)arrayMap);
      for (int i = arrayList.size() - 1; i >= 0; i--) {
        String str = arrayList.get(i);
        View view = (View)arrayMap.get(str);
        if (view == null) {
          paramArrayMap.remove(str);
        } else if (!str.equals(ViewCompat.getTransitionName(view))) {
          str = (String)paramArrayMap.remove(str);
          paramArrayMap.put(ViewCompat.getTransitionName(view), str);
        } 
      } 
    } else {
      paramArrayMap.retainAll(arrayMap.keySet());
    } 
    return arrayMap;
  }
  
  @RequiresApi(21)
  private static ArrayList<View> configureEnteringExitingViews(Object paramObject, Fragment paramFragment, ArrayList<View> paramArrayList, View paramView) {
    if (paramObject != null) {
      ArrayList<View> arrayList2 = new ArrayList();
      View view = paramFragment.getView();
      if (view != null)
        FragmentTransitionCompat21.captureTransitioningViews(arrayList2, view); 
      if (paramArrayList != null)
        arrayList2.removeAll(paramArrayList); 
      ArrayList<View> arrayList1 = arrayList2;
      if (!arrayList2.isEmpty()) {
        arrayList2.add(paramView);
        FragmentTransitionCompat21.addTargets(paramObject, arrayList2);
        arrayList1 = arrayList2;
      } 
    } else {
      paramFragment = null;
    } 
    return (ArrayList<View>)paramFragment;
  }
  
  @RequiresApi(21)
  private static Object configureSharedElementsOrdered(ViewGroup paramViewGroup, final View nonExistentView, final ArrayMap<String, String> nameOverrides, final FragmentContainerTransition fragments, final ArrayList<View> sharedElementsOut, final ArrayList<View> sharedElementsIn, final Object enterTransition, final Object inEpicenter) {
    final Object finalSharedElementTransition;
    final Fragment inFragment = fragments.lastIn;
    final Fragment outFragment = fragments.firstOut;
    Rect rect = null;
    if (fragment1 == null || fragment2 == null)
      return null; 
    final boolean inIsPop = fragments.lastInIsPop;
    if (nameOverrides.isEmpty()) {
      object = null;
    } else {
      object = getSharedElementTransition(fragment1, fragment2, bool);
    } 
    ArrayMap<String, View> arrayMap = captureOutSharedElements(nameOverrides, object, fragments);
    if (nameOverrides.isEmpty()) {
      object = null;
    } else {
      sharedElementsOut.addAll(arrayMap.values());
    } 
    if (enterTransition == null && inEpicenter == null && object == null)
      return null; 
    callSharedElementStartEnd(fragment1, fragment2, bool, arrayMap, true);
    if (object != null) {
      rect = new Rect();
      FragmentTransitionCompat21.setSharedElementTargets(object, nonExistentView, sharedElementsOut);
      setOutEpicenter(object, inEpicenter, arrayMap, fragments.firstOutIsPop, fragments.firstOutTransaction);
      inEpicenter = rect;
      if (enterTransition != null) {
        FragmentTransitionCompat21.setEpicenter(enterTransition, rect);
        inEpicenter = rect;
      } 
    } else {
      inEpicenter = rect;
    } 
    OneShotPreDrawListener.add((View)paramViewGroup, new Runnable() {
          public void run() {
            ArrayMap arrayMap = FragmentTransition.captureInSharedElements(nameOverrides, finalSharedElementTransition, fragments);
            if (arrayMap != null) {
              sharedElementsIn.addAll(arrayMap.values());
              sharedElementsIn.add(nonExistentView);
            } 
            FragmentTransition.callSharedElementStartEnd(inFragment, outFragment, inIsPop, arrayMap, false);
            Object object = finalSharedElementTransition;
            if (object != null) {
              FragmentTransitionCompat21.swapSharedElementTargets(object, sharedElementsOut, sharedElementsIn);
              View view = FragmentTransition.getInEpicenterView(arrayMap, fragments, enterTransition, inIsPop);
              if (view != null)
                FragmentTransitionCompat21.getBoundsOnScreen(view, inEpicenter); 
            } 
          }
        });
    return object;
  }
  
  @RequiresApi(21)
  private static Object configureSharedElementsReordered(ViewGroup paramViewGroup, View paramView, final ArrayMap<String, String> epicenterView, FragmentContainerTransition paramFragmentContainerTransition, ArrayList<View> paramArrayList1, ArrayList<View> paramArrayList2, Object paramObject1, Object paramObject2) {
    final ArrayMap<String, String> epicenter;
    Object object;
    final Fragment inFragment = paramFragmentContainerTransition.lastIn;
    final Fragment outFragment = paramFragmentContainerTransition.firstOut;
    if (fragment1 != null)
      fragment1.getView().setVisibility(0); 
    if (fragment1 == null || fragment2 == null)
      return null; 
    final boolean inIsPop = paramFragmentContainerTransition.lastInIsPop;
    if (epicenterView.isEmpty()) {
      object = null;
    } else {
      object = getSharedElementTransition(fragment1, fragment2, bool);
    } 
    ArrayMap<String, View> arrayMap1 = captureOutSharedElements(epicenterView, object, paramFragmentContainerTransition);
    final ArrayMap<String, View> inSharedElements = captureInSharedElements(epicenterView, object, paramFragmentContainerTransition);
    if (epicenterView.isEmpty()) {
      if (arrayMap1 != null)
        arrayMap1.clear(); 
      if (arrayMap2 != null)
        arrayMap2.clear(); 
      object = null;
    } else {
      addSharedElementsWithMatchingNames(paramArrayList1, arrayMap1, epicenterView.keySet());
      addSharedElementsWithMatchingNames(paramArrayList2, arrayMap2, epicenterView.values());
    } 
    if (paramObject1 == null && paramObject2 == null && object == null)
      return null; 
    callSharedElementStartEnd(fragment1, fragment2, bool, arrayMap1, true);
    if (object != null) {
      paramArrayList2.add(paramView);
      FragmentTransitionCompat21.setSharedElementTargets(object, paramView, paramArrayList1);
      setOutEpicenter(object, paramObject2, arrayMap1, paramFragmentContainerTransition.firstOutIsPop, paramFragmentContainerTransition.firstOutTransaction);
      Rect rect = new Rect();
      View view = getInEpicenterView(arrayMap2, paramFragmentContainerTransition, paramObject1, bool);
      if (view != null)
        FragmentTransitionCompat21.setEpicenter(paramObject1, rect); 
    } else {
      epicenterView = null;
      arrayMap = epicenterView;
    } 
    OneShotPreDrawListener.add((View)paramViewGroup, new Runnable() {
          public void run() {
            FragmentTransition.callSharedElementStartEnd(inFragment, outFragment, inIsPop, inSharedElements, false);
            View view = epicenterView;
            if (view != null)
              FragmentTransitionCompat21.getBoundsOnScreen(view, epicenter); 
          }
        });
    return object;
  }
  
  @RequiresApi(21)
  private static void configureTransitionsOrdered(FragmentManagerImpl paramFragmentManagerImpl, int paramInt, FragmentContainerTransition paramFragmentContainerTransition, View paramView, ArrayMap<String, String> paramArrayMap) {
    if (paramFragmentManagerImpl.mContainer.onHasView()) {
      ViewGroup viewGroup = (ViewGroup)paramFragmentManagerImpl.mContainer.onFindViewById(paramInt);
    } else {
      paramFragmentManagerImpl = null;
    } 
    if (paramFragmentManagerImpl == null)
      return; 
    Fragment fragment1 = paramFragmentContainerTransition.lastIn;
    Fragment fragment2 = paramFragmentContainerTransition.firstOut;
    boolean bool1 = paramFragmentContainerTransition.lastInIsPop;
    boolean bool2 = paramFragmentContainerTransition.firstOutIsPop;
    Object object2 = getEnterTransition(fragment1, bool1);
    Object object3 = getExitTransition(fragment2, bool2);
    ArrayList<View> arrayList1 = new ArrayList();
    ArrayList<View> arrayList2 = new ArrayList();
    Object object4 = configureSharedElementsOrdered((ViewGroup)paramFragmentManagerImpl, paramView, paramArrayMap, paramFragmentContainerTransition, arrayList1, arrayList2, object2, object3);
    if (object2 == null && object4 == null && object3 == null)
      return; 
    arrayList1 = configureEnteringExitingViews(object3, fragment2, arrayList1, paramView);
    if (arrayList1 == null || arrayList1.isEmpty())
      object3 = null; 
    FragmentTransitionCompat21.addTarget(object2, paramView);
    Object object1 = mergeTransitions(object2, object3, object4, fragment1, paramFragmentContainerTransition.lastInIsPop);
    if (object1 != null) {
      ArrayList<View> arrayList = new ArrayList();
      FragmentTransitionCompat21.scheduleRemoveTargets(object1, object2, arrayList, object3, arrayList1, object4, arrayList2);
      scheduleTargetChange((ViewGroup)paramFragmentManagerImpl, fragment1, paramView, arrayList2, object2, arrayList, object3, arrayList1);
      FragmentTransitionCompat21.setNameOverridesOrdered((View)paramFragmentManagerImpl, arrayList2, (Map<String, String>)paramArrayMap);
      FragmentTransitionCompat21.beginDelayedTransition((ViewGroup)paramFragmentManagerImpl, object1);
      FragmentTransitionCompat21.scheduleNameReset((ViewGroup)paramFragmentManagerImpl, arrayList2, (Map<String, String>)paramArrayMap);
    } 
  }
  
  @RequiresApi(21)
  private static void configureTransitionsReordered(FragmentManagerImpl paramFragmentManagerImpl, int paramInt, FragmentContainerTransition paramFragmentContainerTransition, View paramView, ArrayMap<String, String> paramArrayMap) {
    if (paramFragmentManagerImpl.mContainer.onHasView()) {
      ViewGroup viewGroup = (ViewGroup)paramFragmentManagerImpl.mContainer.onFindViewById(paramInt);
    } else {
      paramFragmentManagerImpl = null;
    } 
    if (paramFragmentManagerImpl == null)
      return; 
    Fragment fragment1 = paramFragmentContainerTransition.lastIn;
    Fragment fragment2 = paramFragmentContainerTransition.firstOut;
    boolean bool1 = paramFragmentContainerTransition.lastInIsPop;
    boolean bool2 = paramFragmentContainerTransition.firstOutIsPop;
    ArrayList<View> arrayList2 = new ArrayList();
    ArrayList<View> arrayList3 = new ArrayList();
    Object object3 = getEnterTransition(fragment1, bool1);
    Object<View> object4 = (Object<View>)getExitTransition(fragment2, bool2);
    Object object5 = configureSharedElementsReordered((ViewGroup)paramFragmentManagerImpl, paramView, paramArrayMap, paramFragmentContainerTransition, arrayList3, arrayList2, object3, object4);
    if (object3 == null && object5 == null && object4 == null)
      return; 
    Object<View> object1 = object4;
    object4 = (Object<View>)configureEnteringExitingViews(object1, fragment2, arrayList3, paramView);
    ArrayList<View> arrayList1 = configureEnteringExitingViews(object3, fragment1, arrayList2, paramView);
    setViewVisibility(arrayList1, 4);
    Object object2 = mergeTransitions(object3, object1, object5, fragment1, bool1);
    if (object2 != null) {
      replaceHide(object1, fragment2, (ArrayList<View>)object4);
      ArrayList<String> arrayList = FragmentTransitionCompat21.prepareSetNameOverridesReordered(arrayList2);
      FragmentTransitionCompat21.scheduleRemoveTargets(object2, object3, arrayList1, object1, (ArrayList<View>)object4, object5, arrayList2);
      FragmentTransitionCompat21.beginDelayedTransition((ViewGroup)paramFragmentManagerImpl, object2);
      FragmentTransitionCompat21.setNameOverridesReordered((View)paramFragmentManagerImpl, arrayList3, arrayList2, arrayList, (Map<String, String>)paramArrayMap);
      setViewVisibility(arrayList1, 0);
      FragmentTransitionCompat21.swapSharedElementTargets(object5, arrayList3, arrayList2);
    } 
  }
  
  private static FragmentContainerTransition ensureContainer(FragmentContainerTransition paramFragmentContainerTransition, SparseArray<FragmentContainerTransition> paramSparseArray, int paramInt) {
    FragmentContainerTransition fragmentContainerTransition = paramFragmentContainerTransition;
    if (paramFragmentContainerTransition == null) {
      fragmentContainerTransition = new FragmentContainerTransition();
      paramSparseArray.put(paramInt, fragmentContainerTransition);
    } 
    return fragmentContainerTransition;
  }
  
  private static String findKeyForValue(ArrayMap<String, String> paramArrayMap, String paramString) {
    int i = paramArrayMap.size();
    for (byte b = 0; b < i; b++) {
      if (paramString.equals(paramArrayMap.valueAt(b)))
        return (String)paramArrayMap.keyAt(b); 
    } 
    return null;
  }
  
  @RequiresApi(21)
  private static Object getEnterTransition(Fragment paramFragment, boolean paramBoolean) {
    Object object;
    if (paramFragment == null)
      return null; 
    if (paramBoolean) {
      object = paramFragment.getReenterTransition();
    } else {
      object = object.getEnterTransition();
    } 
    return FragmentTransitionCompat21.cloneTransition(object);
  }
  
  @RequiresApi(21)
  private static Object getExitTransition(Fragment paramFragment, boolean paramBoolean) {
    Object object;
    if (paramFragment == null)
      return null; 
    if (paramBoolean) {
      object = paramFragment.getReturnTransition();
    } else {
      object = object.getExitTransition();
    } 
    return FragmentTransitionCompat21.cloneTransition(object);
  }
  
  private static View getInEpicenterView(ArrayMap<String, View> paramArrayMap, FragmentContainerTransition paramFragmentContainerTransition, Object<String> paramObject, boolean paramBoolean) {
    BackStackRecord backStackRecord = paramFragmentContainerTransition.lastInTransaction;
    if (paramObject != null && paramArrayMap != null) {
      paramObject = (Object<String>)backStackRecord.mSharedElementSourceNames;
      if (paramObject != null && !paramObject.isEmpty()) {
        String str;
        if (paramBoolean) {
          str = backStackRecord.mSharedElementSourceNames.get(0);
        } else {
          str = ((BackStackRecord)str).mSharedElementTargetNames.get(0);
        } 
        return (View)paramArrayMap.get(str);
      } 
    } 
    return null;
  }
  
  @RequiresApi(21)
  private static Object getSharedElementTransition(Fragment paramFragment1, Fragment paramFragment2, boolean paramBoolean) {
    Object object;
    if (paramFragment1 == null || paramFragment2 == null)
      return null; 
    if (paramBoolean) {
      object = paramFragment2.getSharedElementReturnTransition();
    } else {
      object = object.getSharedElementEnterTransition();
    } 
    return FragmentTransitionCompat21.wrapTransitionInSet(FragmentTransitionCompat21.cloneTransition(object));
  }
  
  @RequiresApi(21)
  private static Object mergeTransitions(Object paramObject1, Object paramObject2, Object paramObject3, Fragment paramFragment, boolean paramBoolean) {
    if (paramObject1 != null && paramObject2 != null && paramFragment != null) {
      if (paramBoolean) {
        paramBoolean = paramFragment.getAllowReturnTransitionOverlap();
      } else {
        paramBoolean = paramFragment.getAllowEnterTransitionOverlap();
      } 
    } else {
      paramBoolean = true;
    } 
    if (paramBoolean) {
      paramObject1 = FragmentTransitionCompat21.mergeTransitionsTogether(paramObject2, paramObject1, paramObject3);
    } else {
      paramObject1 = FragmentTransitionCompat21.mergeTransitionsInSequence(paramObject2, paramObject1, paramObject3);
    } 
    return paramObject1;
  }
  
  @RequiresApi(21)
  private static void replaceHide(Object paramObject, Fragment paramFragment, final ArrayList<View> exitingViews) {
    if (paramFragment != null && paramObject != null && paramFragment.mAdded && paramFragment.mHidden && paramFragment.mHiddenChanged) {
      paramFragment.setHideReplaced(true);
      FragmentTransitionCompat21.scheduleHideFragmentView(paramObject, paramFragment.getView(), exitingViews);
      OneShotPreDrawListener.add((View)paramFragment.mContainer, new Runnable() {
            public void run() {
              FragmentTransition.setViewVisibility(exitingViews, 4);
            }
          });
    } 
  }
  
  private static void retainValues(ArrayMap<String, String> paramArrayMap, ArrayMap<String, View> paramArrayMap1) {
    for (int i = paramArrayMap.size() - 1; i >= 0; i--) {
      if (!paramArrayMap1.containsKey(paramArrayMap.valueAt(i)))
        paramArrayMap.removeAt(i); 
    } 
  }
  
  @RequiresApi(21)
  private static void scheduleTargetChange(ViewGroup paramViewGroup, final Fragment inFragment, final View nonExistentView, final ArrayList<View> sharedElementsIn, final Object enterTransition, final ArrayList<View> enteringViews, final Object exitTransition, final ArrayList<View> exitingViews) {
    OneShotPreDrawListener.add((View)paramViewGroup, new Runnable() {
          public void run() {
            Object object = enterTransition;
            if (object != null) {
              FragmentTransitionCompat21.removeTarget(object, nonExistentView);
              object = FragmentTransition.configureEnteringExitingViews(enterTransition, inFragment, sharedElementsIn, nonExistentView);
              enteringViews.addAll((Collection)object);
            } 
            if (exitingViews != null) {
              if (exitTransition != null) {
                object = new ArrayList();
                object.add(nonExistentView);
                FragmentTransitionCompat21.replaceTargets(exitTransition, exitingViews, (ArrayList<View>)object);
              } 
              exitingViews.clear();
              exitingViews.add(nonExistentView);
            } 
          }
        });
  }
  
  @RequiresApi(21)
  private static void setOutEpicenter(Object paramObject1, Object paramObject2, ArrayMap<String, View> paramArrayMap, boolean paramBoolean, BackStackRecord paramBackStackRecord) {
    ArrayList<String> arrayList = paramBackStackRecord.mSharedElementSourceNames;
    if (arrayList != null && !arrayList.isEmpty()) {
      String str;
      if (paramBoolean) {
        str = paramBackStackRecord.mSharedElementTargetNames.get(0);
      } else {
        str = ((BackStackRecord)str).mSharedElementSourceNames.get(0);
      } 
      View view = (View)paramArrayMap.get(str);
      FragmentTransitionCompat21.setEpicenter(paramObject1, view);
      if (paramObject2 != null)
        FragmentTransitionCompat21.setEpicenter(paramObject2, view); 
    } 
  }
  
  private static void setViewVisibility(ArrayList<View> paramArrayList, int paramInt) {
    if (paramArrayList == null)
      return; 
    for (int i = paramArrayList.size() - 1; i >= 0; i--)
      ((View)paramArrayList.get(i)).setVisibility(paramInt); 
  }
  
  static void startTransitions(FragmentManagerImpl paramFragmentManagerImpl, ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1, int paramInt1, int paramInt2, boolean paramBoolean) {
    if (paramFragmentManagerImpl.mCurState < 1)
      return; 
    if (Build.VERSION.SDK_INT >= 21) {
      SparseArray<FragmentContainerTransition> sparseArray = new SparseArray();
      int i;
      for (i = paramInt1; i < paramInt2; i++) {
        BackStackRecord backStackRecord = paramArrayList.get(i);
        if (((Boolean)paramArrayList1.get(i)).booleanValue()) {
          calculatePopFragments(backStackRecord, sparseArray, paramBoolean);
        } else {
          calculateFragments(backStackRecord, sparseArray, paramBoolean);
        } 
      } 
      if (sparseArray.size() != 0) {
        View view = new View(paramFragmentManagerImpl.mHost.getContext());
        int j = sparseArray.size();
        for (i = 0; i < j; i++) {
          int k = sparseArray.keyAt(i);
          ArrayMap<String, String> arrayMap = calculateNameOverrides(k, paramArrayList, paramArrayList1, paramInt1, paramInt2);
          FragmentContainerTransition fragmentContainerTransition = (FragmentContainerTransition)sparseArray.valueAt(i);
          if (paramBoolean) {
            configureTransitionsReordered(paramFragmentManagerImpl, k, fragmentContainerTransition, view, arrayMap);
          } else {
            configureTransitionsOrdered(paramFragmentManagerImpl, k, fragmentContainerTransition, view, arrayMap);
          } 
        } 
      } 
    } 
  }
  
  static class FragmentContainerTransition {
    public Fragment firstOut;
    
    public boolean firstOutIsPop;
    
    public BackStackRecord firstOutTransaction;
    
    public Fragment lastIn;
    
    public boolean lastInIsPop;
    
    public BackStackRecord lastInTransaction;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/app/FragmentTransition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */