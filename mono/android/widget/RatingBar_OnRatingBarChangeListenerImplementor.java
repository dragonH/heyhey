package mono.android.widget;

import android.widget.RatingBar;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class RatingBar_OnRatingBarChangeListenerImplementor implements IGCUserPeer, RatingBar.OnRatingBarChangeListener {
  public static final String __md_methods = "n_onRatingChanged:(Landroid/widget/RatingBar;FZ)V:GetOnRatingChanged_Landroid_widget_RatingBar_FZHandler:Android.Widget.RatingBar/IOnRatingBarChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Widget.RatingBar+IOnRatingBarChangeListenerImplementor, Mono.Android", RatingBar_OnRatingBarChangeListenerImplementor.class, "n_onRatingChanged:(Landroid/widget/RatingBar;FZ)V:GetOnRatingChanged_Landroid_widget_RatingBar_FZHandler:Android.Widget.RatingBar/IOnRatingBarChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public RatingBar_OnRatingBarChangeListenerImplementor() {
    if (getClass() == RatingBar_OnRatingBarChangeListenerImplementor.class)
      TypeManager.Activate("Android.Widget.RatingBar+IOnRatingBarChangeListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onRatingChanged(RatingBar paramRatingBar, float paramFloat, boolean paramBoolean);
  
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
  
  public void onRatingChanged(RatingBar paramRatingBar, float paramFloat, boolean paramBoolean) {
    n_onRatingChanged(paramRatingBar, paramFloat, paramBoolean);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/widget/RatingBar_OnRatingBarChangeListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */