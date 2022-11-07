package mono.android.os;

import android.os.Handler;
import android.os.Message;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class ActionHandlerCallback implements IGCUserPeer, Handler.Callback {
  public static final String __md_methods = "n_handleMessage:(Landroid/os/Message;)Z:GetHandleMessage_Landroid_os_Message_Handler:Android.OS.Handler/ICallbackInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.OS.ActionHandlerCallback, Mono.Android", ActionHandlerCallback.class, "n_handleMessage:(Landroid/os/Message;)Z:GetHandleMessage_Landroid_os_Message_Handler:Android.OS.Handler/ICallbackInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public ActionHandlerCallback() {
    if (getClass() == ActionHandlerCallback.class)
      TypeManager.Activate("Android.OS.ActionHandlerCallback, Mono.Android", "", this, new Object[0]); 
  }
  
  private native boolean n_handleMessage(Message paramMessage);
  
  public boolean handleMessage(Message paramMessage) {
    return n_handleMessage(paramMessage);
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


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/os/ActionHandlerCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */