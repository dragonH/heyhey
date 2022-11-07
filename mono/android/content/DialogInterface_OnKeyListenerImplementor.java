package mono.android.content;

import android.content.DialogInterface;
import android.view.KeyEvent;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class DialogInterface_OnKeyListenerImplementor implements IGCUserPeer, DialogInterface.OnKeyListener {
  public static final String __md_methods = "n_onKey:(Landroid/content/DialogInterface;ILandroid/view/KeyEvent;)Z:GetOnKey_Landroid_content_DialogInterface_ILandroid_view_KeyEvent_Handler:Android.Content.IDialogInterfaceOnKeyListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Content.IDialogInterfaceOnKeyListenerImplementor, Mono.Android", DialogInterface_OnKeyListenerImplementor.class, "n_onKey:(Landroid/content/DialogInterface;ILandroid/view/KeyEvent;)Z:GetOnKey_Landroid_content_DialogInterface_ILandroid_view_KeyEvent_Handler:Android.Content.IDialogInterfaceOnKeyListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public DialogInterface_OnKeyListenerImplementor() {
    if (getClass() == DialogInterface_OnKeyListenerImplementor.class)
      TypeManager.Activate("Android.Content.IDialogInterfaceOnKeyListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native boolean n_onKey(DialogInterface paramDialogInterface, int paramInt, KeyEvent paramKeyEvent);
  
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
  
  public boolean onKey(DialogInterface paramDialogInterface, int paramInt, KeyEvent paramKeyEvent) {
    return n_onKey(paramDialogInterface, paramInt, paramKeyEvent);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/content/DialogInterface_OnKeyListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */