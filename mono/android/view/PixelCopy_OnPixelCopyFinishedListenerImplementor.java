package mono.android.view;

import android.view.PixelCopy;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class PixelCopy_OnPixelCopyFinishedListenerImplementor implements IGCUserPeer, PixelCopy.OnPixelCopyFinishedListener {
  public static final String __md_methods = "n_onPixelCopyFinished:(I)V:GetOnPixelCopyFinished_IHandler:Android.Views.PixelCopy/IOnPixelCopyFinishedListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Views.PixelCopy+IOnPixelCopyFinishedListenerImplementor, Mono.Android", PixelCopy_OnPixelCopyFinishedListenerImplementor.class, "n_onPixelCopyFinished:(I)V:GetOnPixelCopyFinished_IHandler:Android.Views.PixelCopy/IOnPixelCopyFinishedListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public PixelCopy_OnPixelCopyFinishedListenerImplementor() {
    if (getClass() == PixelCopy_OnPixelCopyFinishedListenerImplementor.class)
      TypeManager.Activate("Android.Views.PixelCopy+IOnPixelCopyFinishedListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onPixelCopyFinished(int paramInt);
  
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
  
  public void onPixelCopyFinished(int paramInt) {
    n_onPixelCopyFinished(paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/view/PixelCopy_OnPixelCopyFinishedListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */