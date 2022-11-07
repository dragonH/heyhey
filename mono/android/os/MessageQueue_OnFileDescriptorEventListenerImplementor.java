package mono.android.os;

import android.os.MessageQueue;
import java.io.FileDescriptor;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class MessageQueue_OnFileDescriptorEventListenerImplementor implements IGCUserPeer, MessageQueue.OnFileDescriptorEventListener {
  public static final String __md_methods = "n_onFileDescriptorEvents:(Ljava/io/FileDescriptor;I)I:GetOnFileDescriptorEvents_Ljava_io_FileDescriptor_IHandler:Android.OS.MessageQueue/IOnFileDescriptorEventListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.OS.MessageQueue+IOnFileDescriptorEventListenerImplementor, Mono.Android", MessageQueue_OnFileDescriptorEventListenerImplementor.class, "n_onFileDescriptorEvents:(Ljava/io/FileDescriptor;I)I:GetOnFileDescriptorEvents_Ljava_io_FileDescriptor_IHandler:Android.OS.MessageQueue/IOnFileDescriptorEventListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public MessageQueue_OnFileDescriptorEventListenerImplementor() {
    if (getClass() == MessageQueue_OnFileDescriptorEventListenerImplementor.class)
      TypeManager.Activate("Android.OS.MessageQueue+IOnFileDescriptorEventListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native int n_onFileDescriptorEvents(FileDescriptor paramFileDescriptor, int paramInt);
  
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
  
  public int onFileDescriptorEvents(FileDescriptor paramFileDescriptor, int paramInt) {
    return n_onFileDescriptorEvents(paramFileDescriptor, paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/os/MessageQueue_OnFileDescriptorEventListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */