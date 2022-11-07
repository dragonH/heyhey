package mono.android.media;

import android.media.RemoteControlClient;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class RemoteControlClient_OnMetadataUpdateListenerImplementor implements IGCUserPeer, RemoteControlClient.OnMetadataUpdateListener {
  public static final String __md_methods = "n_onMetadataUpdate:(ILjava/lang/Object;)V:GetOnMetadataUpdate_ILjava_lang_Object_Handler:Android.Media.RemoteControlClient/IOnMetadataUpdateListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Media.RemoteControlClient+IOnMetadataUpdateListenerImplementor, Mono.Android", RemoteControlClient_OnMetadataUpdateListenerImplementor.class, "n_onMetadataUpdate:(ILjava/lang/Object;)V:GetOnMetadataUpdate_ILjava_lang_Object_Handler:Android.Media.RemoteControlClient/IOnMetadataUpdateListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public RemoteControlClient_OnMetadataUpdateListenerImplementor() {
    if (getClass() == RemoteControlClient_OnMetadataUpdateListenerImplementor.class)
      TypeManager.Activate("Android.Media.RemoteControlClient+IOnMetadataUpdateListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onMetadataUpdate(int paramInt, Object paramObject);
  
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
  
  public void onMetadataUpdate(int paramInt, Object paramObject) {
    n_onMetadataUpdate(paramInt, paramObject);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/media/RemoteControlClient_OnMetadataUpdateListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */