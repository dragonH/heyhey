package crc643f46942d9dd1fff9;

import android.view.MenuItem;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class GenericMenuClickListener implements IGCUserPeer, MenuItem.OnMenuItemClickListener {
  public static final String __md_methods = "n_onMenuItemClick:(Landroid/view/MenuItem;)Z:GetOnMenuItemClick_Landroid_view_MenuItem_Handler:Android.Views.IMenuItemOnMenuItemClickListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Xamarin.Forms.Platform.Android.GenericMenuClickListener, Xamarin.Forms.Platform.Android", GenericMenuClickListener.class, "n_onMenuItemClick:(Landroid/view/MenuItem;)Z:GetOnMenuItemClick_Landroid_view_MenuItem_Handler:Android.Views.IMenuItemOnMenuItemClickListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public GenericMenuClickListener() {
    if (getClass() == GenericMenuClickListener.class)
      TypeManager.Activate("Xamarin.Forms.Platform.Android.GenericMenuClickListener, Xamarin.Forms.Platform.Android", "", this, new Object[0]); 
  }
  
  private native boolean n_onMenuItemClick(MenuItem paramMenuItem);
  
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
  
  public boolean onMenuItemClick(MenuItem paramMenuItem) {
    return n_onMenuItemClick(paramMenuItem);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/crc643f46942d9dd1fff9/GenericMenuClickListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */