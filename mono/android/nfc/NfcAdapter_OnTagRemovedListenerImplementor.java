package mono.android.nfc;

import android.nfc.NfcAdapter;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class NfcAdapter_OnTagRemovedListenerImplementor implements IGCUserPeer, NfcAdapter.OnTagRemovedListener {
  public static final String __md_methods = "n_onTagRemoved:()V:GetOnTagRemovedHandler:Android.Nfc.NfcAdapter/IOnTagRemovedListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Nfc.NfcAdapter+IOnTagRemovedListenerImplementor, Mono.Android", NfcAdapter_OnTagRemovedListenerImplementor.class, "n_onTagRemoved:()V:GetOnTagRemovedHandler:Android.Nfc.NfcAdapter/IOnTagRemovedListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public NfcAdapter_OnTagRemovedListenerImplementor() {
    if (getClass() == NfcAdapter_OnTagRemovedListenerImplementor.class)
      TypeManager.Activate("Android.Nfc.NfcAdapter+IOnTagRemovedListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onTagRemoved();
  
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
  
  public void onTagRemoved() {
    n_onTagRemoved();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/nfc/NfcAdapter_OnTagRemovedListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */