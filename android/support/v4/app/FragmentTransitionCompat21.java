package android.support.v4.app;

import android.graphics.Rect;
import android.support.annotation.RequiresApi;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiresApi(21)
class FragmentTransitionCompat21 {
  public static void addTarget(Object paramObject, View paramView) {
    if (paramObject != null)
      ((Transition)paramObject).addTarget(paramView); 
  }
  
  public static void addTargets(Object paramObject, ArrayList<View> paramArrayList) {
    paramObject = paramObject;
    if (paramObject == null)
      return; 
    boolean bool = paramObject instanceof TransitionSet;
    int i = 0;
    int j = 0;
    if (bool) {
      paramObject = paramObject;
      i = paramObject.getTransitionCount();
      while (j < i) {
        addTargets(paramObject.getTransitionAt(j), paramArrayList);
        j++;
      } 
    } else if (!hasSimpleTarget((Transition)paramObject) && isNullOrEmpty(paramObject.getTargets())) {
      int k = paramArrayList.size();
      for (j = i; j < k; j++)
        paramObject.addTarget(paramArrayList.get(j)); 
    } 
  }
  
  public static void beginDelayedTransition(ViewGroup paramViewGroup, Object paramObject) {
    TransitionManager.beginDelayedTransition(paramViewGroup, (Transition)paramObject);
  }
  
  private static void bfsAddViewChildren(List<View> paramList, View paramView) {
    int i = paramList.size();
    if (containedBeforeIndex(paramList, paramView, i))
      return; 
    paramList.add(paramView);
    for (int j = i; j < paramList.size(); j++) {
      paramView = paramList.get(j);
      if (paramView instanceof ViewGroup) {
        ViewGroup viewGroup = (ViewGroup)paramView;
        int k = viewGroup.getChildCount();
        for (byte b = 0; b < k; b++) {
          View view = viewGroup.getChildAt(b);
          if (!containedBeforeIndex(paramList, view, i))
            paramList.add(view); 
        } 
      } 
    } 
  }
  
  public static void captureTransitioningViews(ArrayList<View> paramArrayList, View paramView) {
    if (paramView.getVisibility() == 0) {
      ViewGroup viewGroup;
      if (paramView instanceof ViewGroup) {
        viewGroup = (ViewGroup)paramView;
        if (viewGroup.isTransitionGroup()) {
          paramArrayList.add(viewGroup);
        } else {
          int i = viewGroup.getChildCount();
          for (byte b = 0; b < i; b++)
            captureTransitioningViews(paramArrayList, viewGroup.getChildAt(b)); 
        } 
      } else {
        paramArrayList.add(viewGroup);
      } 
    } 
  }
  
  public static Object cloneTransition(Object paramObject) {
    if (paramObject != null) {
      paramObject = ((Transition)paramObject).clone();
    } else {
      paramObject = null;
    } 
    return paramObject;
  }
  
  private static boolean containedBeforeIndex(List<View> paramList, View paramView, int paramInt) {
    for (byte b = 0; b < paramInt; b++) {
      if (paramList.get(b) == paramView)
        return true; 
    } 
    return false;
  }
  
  private static String findKeyForValue(Map<String, String> paramMap, String paramString) {
    for (Map.Entry<String, String> entry : paramMap.entrySet()) {
      if (paramString.equals(entry.getValue()))
        return (String)entry.getKey(); 
    } 
    return null;
  }
  
  public static void findNamedViews(Map<String, View> paramMap, View paramView) {
    if (paramView.getVisibility() == 0) {
      String str = paramView.getTransitionName();
      if (str != null)
        paramMap.put(str, paramView); 
      if (paramView instanceof ViewGroup) {
        ViewGroup viewGroup = (ViewGroup)paramView;
        int i = viewGroup.getChildCount();
        for (byte b = 0; b < i; b++)
          findNamedViews(paramMap, viewGroup.getChildAt(b)); 
      } 
    } 
  }
  
  public static void getBoundsOnScreen(View paramView, Rect paramRect) {
    int[] arrayOfInt = new int[2];
    paramView.getLocationOnScreen(arrayOfInt);
    int i = arrayOfInt[0];
    paramRect.set(i, arrayOfInt[1], paramView.getWidth() + i, arrayOfInt[1] + paramView.getHeight());
  }
  
  private static boolean hasSimpleTarget(Transition paramTransition) {
    return (!isNullOrEmpty(paramTransition.getTargetIds()) || !isNullOrEmpty(paramTransition.getTargetNames()) || !isNullOrEmpty(paramTransition.getTargetTypes()));
  }
  
  private static boolean isNullOrEmpty(List paramList) {
    return (paramList == null || paramList.isEmpty());
  }
  
  public static Object mergeTransitionsInSequence(Object paramObject1, Object paramObject2, Object paramObject3) {
    paramObject1 = paramObject1;
    paramObject2 = paramObject2;
    paramObject3 = paramObject3;
    if (paramObject1 != null && paramObject2 != null) {
      paramObject1 = (new TransitionSet()).addTransition((Transition)paramObject1).addTransition((Transition)paramObject2).setOrdering(1);
    } else if (paramObject1 == null) {
      if (paramObject2 != null) {
        paramObject1 = paramObject2;
      } else {
        paramObject1 = null;
      } 
    } 
    if (paramObject3 != null) {
      paramObject2 = new TransitionSet();
      if (paramObject1 != null)
        paramObject2.addTransition((Transition)paramObject1); 
      paramObject2.addTransition((Transition)paramObject3);
      return paramObject2;
    } 
    return paramObject1;
  }
  
  public static Object mergeTransitionsTogether(Object paramObject1, Object paramObject2, Object paramObject3) {
    TransitionSet transitionSet = new TransitionSet();
    if (paramObject1 != null)
      transitionSet.addTransition((Transition)paramObject1); 
    if (paramObject2 != null)
      transitionSet.addTransition((Transition)paramObject2); 
    if (paramObject3 != null)
      transitionSet.addTransition((Transition)paramObject3); 
    return transitionSet;
  }
  
  public static ArrayList<String> prepareSetNameOverridesReordered(ArrayList<View> paramArrayList) {
    ArrayList<String> arrayList = new ArrayList();
    int i = paramArrayList.size();
    for (byte b = 0; b < i; b++) {
      View view = paramArrayList.get(b);
      arrayList.add(view.getTransitionName());
      view.setTransitionName(null);
    } 
    return arrayList;
  }
  
  public static void removeTarget(Object paramObject, View paramView) {
    if (paramObject != null)
      ((Transition)paramObject).removeTarget(paramView); 
  }
  
  public static void replaceTargets(Object paramObject, ArrayList<View> paramArrayList1, ArrayList<View> paramArrayList2) {
    paramObject = paramObject;
    boolean bool = paramObject instanceof TransitionSet;
    int i = 0;
    int j = 0;
    if (bool) {
      paramObject = paramObject;
      i = paramObject.getTransitionCount();
      while (j < i) {
        replaceTargets(paramObject.getTransitionAt(j), paramArrayList1, paramArrayList2);
        j++;
      } 
    } else if (!hasSimpleTarget((Transition)paramObject)) {
      List list = paramObject.getTargets();
      if (list != null && list.size() == paramArrayList1.size() && list.containsAll(paramArrayList1)) {
        if (paramArrayList2 == null) {
          j = 0;
        } else {
          j = paramArrayList2.size();
        } 
        while (i < j) {
          paramObject.addTarget(paramArrayList2.get(i));
          i++;
        } 
        for (j = paramArrayList1.size() - 1; j >= 0; j--)
          paramObject.removeTarget(paramArrayList1.get(j)); 
      } 
    } 
  }
  
  public static void scheduleHideFragmentView(Object paramObject, final View fragmentView, final ArrayList<View> exitingViews) {
    ((Transition)paramObject).addListener(new Transition.TransitionListener() {
          public void onTransitionCancel(Transition param1Transition) {}
          
          public void onTransitionEnd(Transition param1Transition) {
            param1Transition.removeListener(this);
            fragmentView.setVisibility(8);
            int i = exitingViews.size();
            for (byte b = 0; b < i; b++)
              ((View)exitingViews.get(b)).setVisibility(0); 
          }
          
          public void onTransitionPause(Transition param1Transition) {}
          
          public void onTransitionResume(Transition param1Transition) {}
          
          public void onTransitionStart(Transition param1Transition) {}
        });
  }
  
  public static void scheduleNameReset(ViewGroup paramViewGroup, final ArrayList<View> sharedElementsIn, final Map<String, String> nameOverrides) {
    OneShotPreDrawListener.add((View)paramViewGroup, new Runnable() {
          public void run() {
            int i = sharedElementsIn.size();
            for (byte b = 0; b < i; b++) {
              View view = sharedElementsIn.get(b);
              String str = view.getTransitionName();
              view.setTransitionName((String)nameOverrides.get(str));
            } 
          }
        });
  }
  
  public static void scheduleRemoveTargets(Object paramObject1, final Object enterTransition, final ArrayList<View> enteringViews, final Object exitTransition, final ArrayList<View> exitingViews, final Object sharedElementTransition, final ArrayList<View> sharedElementsIn) {
    ((Transition)paramObject1).addListener(new Transition.TransitionListener() {
          public void onTransitionCancel(Transition param1Transition) {}
          
          public void onTransitionEnd(Transition param1Transition) {}
          
          public void onTransitionPause(Transition param1Transition) {}
          
          public void onTransitionResume(Transition param1Transition) {}
          
          public void onTransitionStart(Transition param1Transition) {
            Object object = enterTransition;
            if (object != null)
              FragmentTransitionCompat21.replaceTargets(object, enteringViews, null); 
            object = exitTransition;
            if (object != null)
              FragmentTransitionCompat21.replaceTargets(object, exitingViews, null); 
            object = sharedElementTransition;
            if (object != null)
              FragmentTransitionCompat21.replaceTargets(object, sharedElementsIn, null); 
          }
        });
  }
  
  public static void setEpicenter(Object paramObject, final Rect epicenter) {
    if (paramObject != null)
      ((Transition)paramObject).setEpicenterCallback(new Transition.EpicenterCallback() {
            public Rect onGetEpicenter(Transition param1Transition) {
              Rect rect = epicenter;
              return (rect == null || rect.isEmpty()) ? null : epicenter;
            }
          }); 
  }
  
  public static void setEpicenter(final Object epicenter, View paramView) {
    if (paramView != null) {
      Transition transition = (Transition)epicenter;
      epicenter = new Rect();
      getBoundsOnScreen(paramView, (Rect)epicenter);
      transition.setEpicenterCallback(new Transition.EpicenterCallback() {
            public Rect onGetEpicenter(Transition param1Transition) {
              return epicenter;
            }
          });
    } 
  }
  
  public static void setNameOverridesOrdered(View paramView, final ArrayList<View> sharedElementsIn, final Map<String, String> nameOverrides) {
    OneShotPreDrawListener.add(paramView, new Runnable() {
          public void run() {
            int i = sharedElementsIn.size();
            for (byte b = 0; b < i; b++) {
              View view = sharedElementsIn.get(b);
              String str = view.getTransitionName();
              if (str != null)
                view.setTransitionName(FragmentTransitionCompat21.findKeyForValue(nameOverrides, str)); 
            } 
          }
        });
  }
  
  public static void setNameOverridesReordered(View paramView, final ArrayList<View> sharedElementsOut, final ArrayList<View> sharedElementsIn, final ArrayList<String> inNames, Map<String, String> paramMap) {
    final int numSharedElements = sharedElementsIn.size();
    final ArrayList<String> outNames = new ArrayList();
    for (byte b = 0; b < i; b++) {
      View view = sharedElementsOut.get(b);
      String str = view.getTransitionName();
      arrayList.add(str);
      if (str != null) {
        view.setTransitionName(null);
        String str1 = paramMap.get(str);
        for (byte b1 = 0; b1 < i; b1++) {
          if (str1.equals(inNames.get(b1))) {
            ((View)sharedElementsIn.get(b1)).setTransitionName(str);
            break;
          } 
        } 
      } 
    } 
    OneShotPreDrawListener.add(paramView, new Runnable() {
          public void run() {
            for (byte b = 0; b < numSharedElements; b++) {
              ((View)sharedElementsIn.get(b)).setTransitionName(inNames.get(b));
              ((View)sharedElementsOut.get(b)).setTransitionName(outNames.get(b));
            } 
          }
        });
  }
  
  public static void setSharedElementTargets(Object paramObject, View paramView, ArrayList<View> paramArrayList) {
    TransitionSet transitionSet = (TransitionSet)paramObject;
    paramObject = transitionSet.getTargets();
    paramObject.clear();
    int i = paramArrayList.size();
    for (byte b = 0; b < i; b++)
      bfsAddViewChildren((List<View>)paramObject, paramArrayList.get(b)); 
    paramObject.add(paramView);
    paramArrayList.add(paramView);
    addTargets(transitionSet, paramArrayList);
  }
  
  public static void swapSharedElementTargets(Object paramObject, ArrayList<View> paramArrayList1, ArrayList<View> paramArrayList2) {
    paramObject = paramObject;
    if (paramObject != null) {
      paramObject.getTargets().clear();
      paramObject.getTargets().addAll(paramArrayList2);
      replaceTargets(paramObject, paramArrayList1, paramArrayList2);
    } 
  }
  
  public static Object wrapTransitionInSet(Object paramObject) {
    if (paramObject == null)
      return null; 
    TransitionSet transitionSet = new TransitionSet();
    transitionSet.addTransition((Transition)paramObject);
    return transitionSet;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/app/FragmentTransitionCompat21.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */