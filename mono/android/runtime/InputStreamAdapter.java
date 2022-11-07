package mono.android.runtime;

import java.io.InputStream;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class InputStreamAdapter extends InputStream implements IGCUserPeer {
  public static final String __md_methods = "n_close:()V:GetCloseHandler\nn_read:()I:GetReadHandler\nn_read:([B)I:GetRead_arrayBHandler\nn_read:([BII)I:GetRead_arrayBIIHandler\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Runtime.InputStreamAdapter, Mono.Android", InputStreamAdapter.class, "n_close:()V:GetCloseHandler\nn_read:()I:GetReadHandler\nn_read:([B)I:GetRead_arrayBHandler\nn_read:([BII)I:GetRead_arrayBIIHandler\n");
  }
  
  public InputStreamAdapter() {
    if (getClass() == InputStreamAdapter.class)
      TypeManager.Activate("Android.Runtime.InputStreamAdapter, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_close();
  
  private native int n_read();
  
  private native int n_read(byte[] paramArrayOfbyte);
  
  private native int n_read(byte[] paramArrayOfbyte, int paramInt1, int paramInt2);
  
  public void close() {
    n_close();
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
  
  public int read() {
    return n_read();
  }
  
  public int read(byte[] paramArrayOfbyte) {
    return n_read(paramArrayOfbyte);
  }
  
  public int read(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    return n_read(paramArrayOfbyte, paramInt1, paramInt2);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/runtime/InputStreamAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */