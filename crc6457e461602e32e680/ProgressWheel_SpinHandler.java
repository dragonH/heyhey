package crc6457e461602e32e680;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class ProgressWheel_SpinHandler extends Handler implements IGCUserPeer {
  public static final String __md_methods = "n_handleMessage:(Landroid/os/Message;)V:GetHandleMessage_Landroid_os_Message_Handler\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("AndroidHUD.ProgressWheel+SpinHandler, AndHUD", ProgressWheel_SpinHandler.class, "n_handleMessage:(Landroid/os/Message;)V:GetHandleMessage_Landroid_os_Message_Handler\n");
  }
  
  public ProgressWheel_SpinHandler() {
    if (getClass() == ProgressWheel_SpinHandler.class)
      TypeManager.Activate("AndroidHUD.ProgressWheel+SpinHandler, AndHUD", "", this, new Object[0]); 
  }
  
  public ProgressWheel_SpinHandler(Looper paramLooper) {
    super(paramLooper);
    if (getClass() == ProgressWheel_SpinHandler.class)
      TypeManager.Activate("AndroidHUD.ProgressWheel+SpinHandler, AndHUD", "Android.OS.Looper, Mono.Android", this, new Object[] { paramLooper }); 
  }
  
  private native void n_handleMessage(Message paramMessage);
  
  public void handleMessage(Message paramMessage) {
    n_handleMessage(paramMessage);
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


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/crc6457e461602e32e680/ProgressWheel_SpinHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */