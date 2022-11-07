package crc64b76f6e8b2d8c8db1;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public abstract class AbstractAppCompatDialogFragment_1 extends AppCompatDialogFragment implements IGCUserPeer {
  public static final String __md_methods = "n_onSaveInstanceState:(Landroid/os/Bundle;)V:GetOnSaveInstanceState_Landroid_os_Bundle_Handler\nn_onCreateDialog:(Landroid/os/Bundle;)Landroid/app/Dialog;:GetOnCreateDialog_Landroid_os_Bundle_Handler\nn_onDetach:()V:GetOnDetachHandler\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Acr.UserDialogs.Fragments.AbstractAppCompatDialogFragment`1, Acr.UserDialogs", AbstractAppCompatDialogFragment_1.class, "n_onSaveInstanceState:(Landroid/os/Bundle;)V:GetOnSaveInstanceState_Landroid_os_Bundle_Handler\nn_onCreateDialog:(Landroid/os/Bundle;)Landroid/app/Dialog;:GetOnCreateDialog_Landroid_os_Bundle_Handler\nn_onDetach:()V:GetOnDetachHandler\n");
  }
  
  public AbstractAppCompatDialogFragment_1() {
    if (getClass() == AbstractAppCompatDialogFragment_1.class)
      TypeManager.Activate("Acr.UserDialogs.Fragments.AbstractAppCompatDialogFragment`1, Acr.UserDialogs", "", this, new Object[0]); 
  }
  
  private native Dialog n_onCreateDialog(Bundle paramBundle);
  
  private native void n_onDetach();
  
  private native void n_onSaveInstanceState(Bundle paramBundle);
  
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
  
  public Dialog onCreateDialog(Bundle paramBundle) {
    return n_onCreateDialog(paramBundle);
  }
  
  public void onDetach() {
    n_onDetach();
  }
  
  public void onSaveInstanceState(Bundle paramBundle) {
    n_onSaveInstanceState(paramBundle);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/crc64b76f6e8b2d8c8db1/AbstractAppCompatDialogFragment_1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */