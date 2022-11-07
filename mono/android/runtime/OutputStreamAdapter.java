package mono.android.runtime;

import java.io.OutputStream;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class OutputStreamAdapter extends OutputStream implements IGCUserPeer {
  public static final String __md_methods = "n_close:()V:GetCloseHandler\nn_flush:()V:GetFlushHandler\nn_write:([B)V:GetWrite_arrayBHandler\nn_write:([BII)V:GetWrite_arrayBIIHandler\nn_write:(I)V:GetWrite_IHandler\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Runtime.OutputStreamAdapter, Mono.Android", OutputStreamAdapter.class, "n_close:()V:GetCloseHandler\nn_flush:()V:GetFlushHandler\nn_write:([B)V:GetWrite_arrayBHandler\nn_write:([BII)V:GetWrite_arrayBIIHandler\nn_write:(I)V:GetWrite_IHandler\n");
  }
  
  public OutputStreamAdapter() {
    if (getClass() == OutputStreamAdapter.class)
      TypeManager.Activate("Android.Runtime.OutputStreamAdapter, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_close();
  
  private native void n_flush();
  
  private native void n_write(int paramInt);
  
  private native void n_write(byte[] paramArrayOfbyte);
  
  private native void n_write(byte[] paramArrayOfbyte, int paramInt1, int paramInt2);
  
  public void close() {
    n_close();
  }
  
  public void flush() {
    n_flush();
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
  
  public void write(int paramInt) {
    n_write(paramInt);
  }
  
  public void write(byte[] paramArrayOfbyte) {
    n_write(paramArrayOfbyte);
  }
  
  public void write(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    n_write(paramArrayOfbyte, paramInt1, paramInt2);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/runtime/OutputStreamAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */