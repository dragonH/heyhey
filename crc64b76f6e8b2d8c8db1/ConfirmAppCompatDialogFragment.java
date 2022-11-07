package crc64b76f6e8b2d8c8db1;

import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class ConfirmAppCompatDialogFragment extends AbstractAppCompatDialogFragment_1 implements IGCUserPeer {
  public static final String __md_methods = "";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Acr.UserDialogs.Fragments.ConfirmAppCompatDialogFragment, Acr.UserDialogs", ConfirmAppCompatDialogFragment.class, "");
  }
  
  public ConfirmAppCompatDialogFragment() {
    if (getClass() == ConfirmAppCompatDialogFragment.class)
      TypeManager.Activate("Acr.UserDialogs.Fragments.ConfirmAppCompatDialogFragment, Acr.UserDialogs", "", this, new Object[0]); 
  }
  
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
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/crc64b76f6e8b2d8c8db1/ConfirmAppCompatDialogFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */