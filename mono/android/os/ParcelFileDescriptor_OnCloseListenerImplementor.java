package mono.android.os;

import android.os.ParcelFileDescriptor;
import java.io.IOException;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class ParcelFileDescriptor_OnCloseListenerImplementor implements IGCUserPeer, ParcelFileDescriptor.OnCloseListener {
  public static final String __md_methods = "n_onClose:(Ljava/io/IOException;)V:GetOnClose_Ljava_io_IOException_Handler:Android.OS.ParcelFileDescriptor/IOnCloseListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.OS.ParcelFileDescriptor+IOnCloseListenerImplementor, Mono.Android", ParcelFileDescriptor_OnCloseListenerImplementor.class, "n_onClose:(Ljava/io/IOException;)V:GetOnClose_Ljava_io_IOException_Handler:Android.OS.ParcelFileDescriptor/IOnCloseListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public ParcelFileDescriptor_OnCloseListenerImplementor() {
    if (getClass() == ParcelFileDescriptor_OnCloseListenerImplementor.class)
      TypeManager.Activate("Android.OS.ParcelFileDescriptor+IOnCloseListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onClose(IOException paramIOException);
  
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
  
  public void onClose(IOException paramIOException) {
    n_onClose(paramIOException);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/os/ParcelFileDescriptor_OnCloseListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */