package mono.android.gesture;

import android.gesture.GestureOverlayView;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class GestureOverlayView_OnGesturingListenerImplementor implements IGCUserPeer, GestureOverlayView.OnGesturingListener {
  public static final String __md_methods = "n_onGesturingEnded:(Landroid/gesture/GestureOverlayView;)V:GetOnGesturingEnded_Landroid_gesture_GestureOverlayView_Handler:Android.Gestures.GestureOverlayView/IOnGesturingListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onGesturingStarted:(Landroid/gesture/GestureOverlayView;)V:GetOnGesturingStarted_Landroid_gesture_GestureOverlayView_Handler:Android.Gestures.GestureOverlayView/IOnGesturingListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Gestures.GestureOverlayView+IOnGesturingListenerImplementor, Mono.Android", GestureOverlayView_OnGesturingListenerImplementor.class, "n_onGesturingEnded:(Landroid/gesture/GestureOverlayView;)V:GetOnGesturingEnded_Landroid_gesture_GestureOverlayView_Handler:Android.Gestures.GestureOverlayView/IOnGesturingListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onGesturingStarted:(Landroid/gesture/GestureOverlayView;)V:GetOnGesturingStarted_Landroid_gesture_GestureOverlayView_Handler:Android.Gestures.GestureOverlayView/IOnGesturingListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public GestureOverlayView_OnGesturingListenerImplementor() {
    if (getClass() == GestureOverlayView_OnGesturingListenerImplementor.class)
      TypeManager.Activate("Android.Gestures.GestureOverlayView+IOnGesturingListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onGesturingEnded(GestureOverlayView paramGestureOverlayView);
  
  private native void n_onGesturingStarted(GestureOverlayView paramGestureOverlayView);
  
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
  
  public void onGesturingEnded(GestureOverlayView paramGestureOverlayView) {
    n_onGesturingEnded(paramGestureOverlayView);
  }
  
  public void onGesturingStarted(GestureOverlayView paramGestureOverlayView) {
    n_onGesturingStarted(paramGestureOverlayView);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/gesture/GestureOverlayView_OnGesturingListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */