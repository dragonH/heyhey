package mono.android.gesture;

import android.gesture.GestureOverlayView;
import android.view.MotionEvent;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class GestureOverlayView_OnGestureListenerImplementor implements IGCUserPeer, GestureOverlayView.OnGestureListener {
  public static final String __md_methods = "n_onGesture:(Landroid/gesture/GestureOverlayView;Landroid/view/MotionEvent;)V:GetOnGesture_Landroid_gesture_GestureOverlayView_Landroid_view_MotionEvent_Handler:Android.Gestures.GestureOverlayView/IOnGestureListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onGestureCancelled:(Landroid/gesture/GestureOverlayView;Landroid/view/MotionEvent;)V:GetOnGestureCancelled_Landroid_gesture_GestureOverlayView_Landroid_view_MotionEvent_Handler:Android.Gestures.GestureOverlayView/IOnGestureListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onGestureEnded:(Landroid/gesture/GestureOverlayView;Landroid/view/MotionEvent;)V:GetOnGestureEnded_Landroid_gesture_GestureOverlayView_Landroid_view_MotionEvent_Handler:Android.Gestures.GestureOverlayView/IOnGestureListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onGestureStarted:(Landroid/gesture/GestureOverlayView;Landroid/view/MotionEvent;)V:GetOnGestureStarted_Landroid_gesture_GestureOverlayView_Landroid_view_MotionEvent_Handler:Android.Gestures.GestureOverlayView/IOnGestureListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Gestures.GestureOverlayView+IOnGestureListenerImplementor, Mono.Android", GestureOverlayView_OnGestureListenerImplementor.class, "n_onGesture:(Landroid/gesture/GestureOverlayView;Landroid/view/MotionEvent;)V:GetOnGesture_Landroid_gesture_GestureOverlayView_Landroid_view_MotionEvent_Handler:Android.Gestures.GestureOverlayView/IOnGestureListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onGestureCancelled:(Landroid/gesture/GestureOverlayView;Landroid/view/MotionEvent;)V:GetOnGestureCancelled_Landroid_gesture_GestureOverlayView_Landroid_view_MotionEvent_Handler:Android.Gestures.GestureOverlayView/IOnGestureListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onGestureEnded:(Landroid/gesture/GestureOverlayView;Landroid/view/MotionEvent;)V:GetOnGestureEnded_Landroid_gesture_GestureOverlayView_Landroid_view_MotionEvent_Handler:Android.Gestures.GestureOverlayView/IOnGestureListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onGestureStarted:(Landroid/gesture/GestureOverlayView;Landroid/view/MotionEvent;)V:GetOnGestureStarted_Landroid_gesture_GestureOverlayView_Landroid_view_MotionEvent_Handler:Android.Gestures.GestureOverlayView/IOnGestureListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public GestureOverlayView_OnGestureListenerImplementor() {
    if (getClass() == GestureOverlayView_OnGestureListenerImplementor.class)
      TypeManager.Activate("Android.Gestures.GestureOverlayView+IOnGestureListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onGesture(GestureOverlayView paramGestureOverlayView, MotionEvent paramMotionEvent);
  
  private native void n_onGestureCancelled(GestureOverlayView paramGestureOverlayView, MotionEvent paramMotionEvent);
  
  private native void n_onGestureEnded(GestureOverlayView paramGestureOverlayView, MotionEvent paramMotionEvent);
  
  private native void n_onGestureStarted(GestureOverlayView paramGestureOverlayView, MotionEvent paramMotionEvent);
  
  public void monodroidAddReference(Object paramObject) {
    if (this.refList == null)
      this.refList = new ArrayList(); 
    this.refList.add(paramObject);
  }
  
  public void monodroidClearReferences() {
    ArrayList arrayList = this.refList;
    if (arrayList != null)
      arrayList.clear(); 
  }
  
  public void onGesture(GestureOverlayView paramGestureOverlayView, MotionEvent paramMotionEvent) {
    n_onGesture(paramGestureOverlayView, paramMotionEvent);
  }
  
  public void onGestureCancelled(GestureOverlayView paramGestureOverlayView, MotionEvent paramMotionEvent) {
    n_onGestureCancelled(paramGestureOverlayView, paramMotionEvent);
  }
  
  public void onGestureEnded(GestureOverlayView paramGestureOverlayView, MotionEvent paramMotionEvent) {
    n_onGestureEnded(paramGestureOverlayView, paramMotionEvent);
  }
  
  public void onGestureStarted(GestureOverlayView paramGestureOverlayView, MotionEvent paramMotionEvent) {
    n_onGestureStarted(paramGestureOverlayView, paramMotionEvent);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/gesture/GestureOverlayView_OnGestureListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */