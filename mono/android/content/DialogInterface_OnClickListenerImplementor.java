package mono.android.content;

import android.content.DialogInterface;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class DialogInterface_OnClickListenerImplementor implements IGCUserPeer, DialogInterface.OnClickListener {
  public static final String __md_methods = "n_onClick:(Landroid/content/DialogInterface;I)V:GetOnClick_Landroid_content_DialogInterface_IHandler:Android.Content.IDialogInterfaceOnClickListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Content.IDialogInterfaceOnClickListenerImplementor, Mono.Android", DialogInterface_OnClickListenerImplementor.class, "n_onClick:(Landroid/content/DialogInterface;I)V:GetOnClick_Landroid_content_DialogInterface_IHandler:Android.Content.IDialogInterfaceOnClickListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public DialogInterface_OnClickListenerImplementor() {
    if (getClass() == DialogInterface_OnClickListenerImplementor.class)
      TypeManager.Activate("Android.Content.IDialogInterfaceOnClickListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onClick(DialogInterface paramDialogInterface, int paramInt);
  
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
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt) {
    n_onClick(paramDialogInterface, paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/content/DialogInterface_OnClickListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */