package mono.android.content;

import android.content.DialogInterface;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class DialogInterface_OnShowListenerImplementor implements IGCUserPeer, DialogInterface.OnShowListener {
  public static final String __md_methods = "n_onShow:(Landroid/content/DialogInterface;)V:GetOnShow_Landroid_content_DialogInterface_Handler:Android.Content.IDialogInterfaceOnShowListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Content.IDialogInterfaceOnShowListenerImplementor, Mono.Android", DialogInterface_OnShowListenerImplementor.class, "n_onShow:(Landroid/content/DialogInterface;)V:GetOnShow_Landroid_content_DialogInterface_Handler:Android.Content.IDialogInterfaceOnShowListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public DialogInterface_OnShowListenerImplementor() {
    if (getClass() == DialogInterface_OnShowListenerImplementor.class)
      TypeManager.Activate("Android.Content.IDialogInterfaceOnShowListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onShow(DialogInterface paramDialogInterface);
  
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
  
  public void onShow(DialogInterface paramDialogInterface) {
    n_onShow(paramDialogInterface);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/content/DialogInterface_OnShowListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */