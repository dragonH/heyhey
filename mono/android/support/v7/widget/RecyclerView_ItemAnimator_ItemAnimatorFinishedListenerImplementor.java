package mono.android.support.v7.widget;

import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class RecyclerView_ItemAnimator_ItemAnimatorFinishedListenerImplementor implements IGCUserPeer, RecyclerView.ItemAnimator.ItemAnimatorFinishedListener {
  public static final String __md_methods = "n_onAnimationsFinished:()V:GetOnAnimationsFinishedHandler:Android.Support.V7.Widget.RecyclerView/ItemAnimator/IItemAnimatorFinishedListenerInvoker, Xamarin.Android.Support.v7.RecyclerView\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Support.V7.Widget.RecyclerView+ItemAnimator+IItemAnimatorFinishedListenerImplementor, Xamarin.Android.Support.v7.RecyclerView", RecyclerView_ItemAnimator_ItemAnimatorFinishedListenerImplementor.class, "n_onAnimationsFinished:()V:GetOnAnimationsFinishedHandler:Android.Support.V7.Widget.RecyclerView/ItemAnimator/IItemAnimatorFinishedListenerInvoker, Xamarin.Android.Support.v7.RecyclerView\n");
  }
  
  public RecyclerView_ItemAnimator_ItemAnimatorFinishedListenerImplementor() {
    if (getClass() == RecyclerView_ItemAnimator_ItemAnimatorFinishedListenerImplementor.class)
      TypeManager.Activate("Android.Support.V7.Widget.RecyclerView+ItemAnimator+IItemAnimatorFinishedListenerImplementor, Xamarin.Android.Support.v7.RecyclerView", "", this, new Object[0]); 
  }
  
  private native void n_onAnimationsFinished();
  
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
  
  public void onAnimationsFinished() {
    n_onAnimationsFinished();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/support/v7/widget/RecyclerView_ItemAnimator_ItemAnimatorFinishedListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */