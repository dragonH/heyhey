package mono.android.media;

import android.media.RemoteController;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class RemoteController_OnClientUpdateListenerImplementor implements IGCUserPeer, RemoteController.OnClientUpdateListener {
  public static final String __md_methods = "n_onClientChange:(Z)V:GetOnClientChange_ZHandler:Android.Media.RemoteController/IOnClientUpdateListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onClientMetadataUpdate:(Landroid/media/RemoteController$MetadataEditor;)V:GetOnClientMetadataUpdate_Landroid_media_RemoteController_MetadataEditor_Handler:Android.Media.RemoteController/IOnClientUpdateListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onClientPlaybackStateUpdate:(I)V:GetOnClientPlaybackStateUpdateSimple_IHandler:Android.Media.RemoteController/IOnClientUpdateListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onClientPlaybackStateUpdate:(IJJF)V:GetOnClientPlaybackStateUpdate_IJJFHandler:Android.Media.RemoteController/IOnClientUpdateListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onClientTransportControlUpdate:(I)V:GetOnClientTransportControlUpdate_IHandler:Android.Media.RemoteController/IOnClientUpdateListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Media.RemoteController+IOnClientUpdateListenerImplementor, Mono.Android", RemoteController_OnClientUpdateListenerImplementor.class, "n_onClientChange:(Z)V:GetOnClientChange_ZHandler:Android.Media.RemoteController/IOnClientUpdateListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onClientMetadataUpdate:(Landroid/media/RemoteController$MetadataEditor;)V:GetOnClientMetadataUpdate_Landroid_media_RemoteController_MetadataEditor_Handler:Android.Media.RemoteController/IOnClientUpdateListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onClientPlaybackStateUpdate:(I)V:GetOnClientPlaybackStateUpdateSimple_IHandler:Android.Media.RemoteController/IOnClientUpdateListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onClientPlaybackStateUpdate:(IJJF)V:GetOnClientPlaybackStateUpdate_IJJFHandler:Android.Media.RemoteController/IOnClientUpdateListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onClientTransportControlUpdate:(I)V:GetOnClientTransportControlUpdate_IHandler:Android.Media.RemoteController/IOnClientUpdateListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public RemoteController_OnClientUpdateListenerImplementor() {
    if (getClass() == RemoteController_OnClientUpdateListenerImplementor.class)
      TypeManager.Activate("Android.Media.RemoteController+IOnClientUpdateListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onClientChange(boolean paramBoolean);
  
  private native void n_onClientMetadataUpdate(RemoteController.MetadataEditor paramMetadataEditor);
  
  private native void n_onClientPlaybackStateUpdate(int paramInt);
  
  private native void n_onClientPlaybackStateUpdate(int paramInt, long paramLong1, long paramLong2, float paramFloat);
  
  private native void n_onClientTransportControlUpdate(int paramInt);
  
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
  
  public void onClientChange(boolean paramBoolean) {
    n_onClientChange(paramBoolean);
  }
  
  public void onClientMetadataUpdate(RemoteController.MetadataEditor paramMetadataEditor) {
    n_onClientMetadataUpdate(paramMetadataEditor);
  }
  
  public void onClientPlaybackStateUpdate(int paramInt) {
    n_onClientPlaybackStateUpdate(paramInt);
  }
  
  public void onClientPlaybackStateUpdate(int paramInt, long paramLong1, long paramLong2, float paramFloat) {
    n_onClientPlaybackStateUpdate(paramInt, paramLong1, paramLong2, paramFloat);
  }
  
  public void onClientTransportControlUpdate(int paramInt) {
    n_onClientTransportControlUpdate(paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/media/RemoteController_OnClientUpdateListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */