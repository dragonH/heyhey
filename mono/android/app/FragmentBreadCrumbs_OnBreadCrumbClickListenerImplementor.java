package mono.android.app;

import android.app.FragmentBreadCrumbs;
import android.app.FragmentManager;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class FragmentBreadCrumbs_OnBreadCrumbClickListenerImplementor implements IGCUserPeer, FragmentBreadCrumbs.OnBreadCrumbClickListener {
  public static final String __md_methods = "n_onBreadCrumbClick:(Landroid/app/FragmentManager$BackStackEntry;I)Z:GetOnBreadCrumbClick_Landroid_app_FragmentManager_BackStackEntry_IHandler:Android.App.FragmentBreadCrumbs/IOnBreadCrumbClickListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.App.FragmentBreadCrumbs+IOnBreadCrumbClickListenerImplementor, Mono.Android", FragmentBreadCrumbs_OnBreadCrumbClickListenerImplementor.class, "n_onBreadCrumbClick:(Landroid/app/FragmentManager$BackStackEntry;I)Z:GetOnBreadCrumbClick_Landroid_app_FragmentManager_BackStackEntry_IHandler:Android.App.FragmentBreadCrumbs/IOnBreadCrumbClickListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public FragmentBreadCrumbs_OnBreadCrumbClickListenerImplementor() {
    if (getClass() == FragmentBreadCrumbs_OnBreadCrumbClickListenerImplementor.class)
      TypeManager.Activate("Android.App.FragmentBreadCrumbs+IOnBreadCrumbClickListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native boolean n_onBreadCrumbClick(FragmentManager.BackStackEntry paramBackStackEntry, int paramInt);
  
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
  
  public boolean onBreadCrumbClick(FragmentManager.BackStackEntry paramBackStackEntry, int paramInt) {
    return n_onBreadCrumbClick(paramBackStackEntry, paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/app/FragmentBreadCrumbs_OnBreadCrumbClickListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */