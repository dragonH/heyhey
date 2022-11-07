package mono.android.content;

import android.content.DialogInterface;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class DialogInterface_OnDismissListenerImplementor implements IGCUserPeer, DialogInterface.OnDismissListener {
  public static final String __md_methods = "n_onDismiss:(Landroid/content/DialogInterface;)V:GetOnDismiss_Landroid_content_DialogInterface_Handler:Android.Content.IDialogInterfaceOnDismissListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Content.IDialogInterfaceOnDismissListenerImplementor, Mono.Android", DialogInterface_OnDismissListenerImplementor.class, "n_onDismiss:(Landroid/content/DialogInterface;)V:GetOnDismiss_Landroid_content_DialogInterface_Handler:Android.Content.IDialogInterfaceOnDismissListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public DialogInterface_OnDismissListenerImplementor() {
    if (getClass() == DialogInterface_OnDismissListenerImplementor.class)
      TypeManager.Activate("Android.Content.IDialogInterfaceOnDismissListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onDismiss(DialogInterface paramDialogInterface);
  
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
  
  public void onDismiss(DialogInterface paramDialogInterface) {
    n_onDismiss(paramDialogInterface);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/content/DialogInterface_OnDismissListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */