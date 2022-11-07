package mono.android.content;

import android.content.DialogInterface;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class DialogInterface_OnMultiChoiceClickListenerImplementor implements IGCUserPeer, DialogInterface.OnMultiChoiceClickListener {
  public static final String __md_methods = "n_onClick:(Landroid/content/DialogInterface;IZ)V:GetOnClick_Landroid_content_DialogInterface_IZHandler:Android.Content.IDialogInterfaceOnMultiChoiceClickListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Content.IDialogInterfaceOnMultiChoiceClickListenerImplementor, Mono.Android", DialogInterface_OnMultiChoiceClickListenerImplementor.class, "n_onClick:(Landroid/content/DialogInterface;IZ)V:GetOnClick_Landroid_content_DialogInterface_IZHandler:Android.Content.IDialogInterfaceOnMultiChoiceClickListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public DialogInterface_OnMultiChoiceClickListenerImplementor() {
    if (getClass() == DialogInterface_OnMultiChoiceClickListenerImplementor.class)
      TypeManager.Activate("Android.Content.IDialogInterfaceOnMultiChoiceClickListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onClick(DialogInterface paramDialogInterface, int paramInt, boolean paramBoolean);
  
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
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt, boolean paramBoolean) {
    n_onClick(paramDialogInterface, paramInt, paramBoolean);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/content/DialogInterface_OnMultiChoiceClickListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */