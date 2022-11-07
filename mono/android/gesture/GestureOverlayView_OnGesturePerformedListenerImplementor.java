package mono.android.gesture;

import android.gesture.Gesture;
import android.gesture.GestureOverlayView;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class GestureOverlayView_OnGesturePerformedListenerImplementor implements IGCUserPeer, GestureOverlayView.OnGesturePerformedListener {
  public static final String __md_methods = "n_onGesturePerformed:(Landroid/gesture/GestureOverlayView;Landroid/gesture/Gesture;)V:GetOnGesturePerformed_Landroid_gesture_GestureOverlayView_Landroid_gesture_Gesture_Handler:Android.Gestures.GestureOverlayView/IOnGesturePerformedListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Gestures.GestureOverlayView+IOnGesturePerformedListenerImplementor, Mono.Android", GestureOverlayView_OnGesturePerformedListenerImplementor.class, "n_onGesturePerformed:(Landroid/gesture/GestureOverlayView;Landroid/gesture/Gesture;)V:GetOnGesturePerformed_Landroid_gesture_GestureOverlayView_Landroid_gesture_Gesture_Handler:Android.Gestures.GestureOverlayView/IOnGesturePerformedListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public GestureOverlayView_OnGesturePerformedListenerImplementor() {
    if (getClass() == GestureOverlayView_OnGesturePerformedListenerImplementor.class)
      TypeManager.Activate("Android.Gestures.GestureOverlayView+IOnGesturePerformedListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onGesturePerformed(GestureOverlayView paramGestureOverlayView, Gesture paramGesture);
  
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
  
  public void onGesturePerformed(GestureOverlayView paramGestureOverlayView, Gesture paramGesture) {
    n_onGesturePerformed(paramGestureOverlayView, paramGesture);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/gesture/GestureOverlayView_OnGesturePerformedListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */