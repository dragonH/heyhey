package mono.android.animation;

import android.animation.TimeAnimator;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class TimeAnimator_TimeListenerImplementor implements IGCUserPeer, TimeAnimator.TimeListener {
  public static final String __md_methods = "n_onTimeUpdate:(Landroid/animation/TimeAnimator;JJ)V:GetOnTimeUpdate_Landroid_animation_TimeAnimator_JJHandler:Android.Animation.TimeAnimator/ITimeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Animation.TimeAnimator+ITimeListenerImplementor, Mono.Android", TimeAnimator_TimeListenerImplementor.class, "n_onTimeUpdate:(Landroid/animation/TimeAnimator;JJ)V:GetOnTimeUpdate_Landroid_animation_TimeAnimator_JJHandler:Android.Animation.TimeAnimator/ITimeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public TimeAnimator_TimeListenerImplementor() {
    if (getClass() == TimeAnimator_TimeListenerImplementor.class)
      TypeManager.Activate("Android.Animation.TimeAnimator+ITimeListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onTimeUpdate(TimeAnimator paramTimeAnimator, long paramLong1, long paramLong2);
  
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
  
  public void onTimeUpdate(TimeAnimator paramTimeAnimator, long paramLong1, long paramLong2) {
    n_onTimeUpdate(paramTimeAnimator, paramLong1, paramLong2);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/animation/TimeAnimator_TimeListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */