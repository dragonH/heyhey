package mono.android.widget;

import android.content.Intent;
import android.widget.ShareActionProvider;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class ShareActionProvider_OnShareTargetSelectedListenerImplementor implements IGCUserPeer, ShareActionProvider.OnShareTargetSelectedListener {
  public static final String __md_methods = "n_onShareTargetSelected:(Landroid/widget/ShareActionProvider;Landroid/content/Intent;)Z:GetOnShareTargetSelected_Landroid_widget_ShareActionProvider_Landroid_content_Intent_Handler:Android.Widget.ShareActionProvider/IOnShareTargetSelectedListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Widget.ShareActionProvider+IOnShareTargetSelectedListenerImplementor, Mono.Android", ShareActionProvider_OnShareTargetSelectedListenerImplementor.class, "n_onShareTargetSelected:(Landroid/widget/ShareActionProvider;Landroid/content/Intent;)Z:GetOnShareTargetSelected_Landroid_widget_ShareActionProvider_Landroid_content_Intent_Handler:Android.Widget.ShareActionProvider/IOnShareTargetSelectedListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public ShareActionProvider_OnShareTargetSelectedListenerImplementor() {
    if (getClass() == ShareActionProvider_OnShareTargetSelectedListenerImplementor.class)
      TypeManager.Activate("Android.Widget.ShareActionProvider+IOnShareTargetSelectedListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native boolean n_onShareTargetSelected(ShareActionProvider paramShareActionProvider, Intent paramIntent);
  
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
  
  public boolean onShareTargetSelected(ShareActionProvider paramShareActionProvider, Intent paramIntent) {
    return n_onShareTargetSelected(paramShareActionProvider, paramIntent);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/widget/ShareActionProvider_OnShareTargetSelectedListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */