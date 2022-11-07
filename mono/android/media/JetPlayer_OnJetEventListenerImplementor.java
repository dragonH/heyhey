package mono.android.media;

import android.media.JetPlayer;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class JetPlayer_OnJetEventListenerImplementor implements IGCUserPeer, JetPlayer.OnJetEventListener {
  public static final String __md_methods = "n_onJetEvent:(Landroid/media/JetPlayer;SBBBB)V:GetOnJetEvent_Landroid_media_JetPlayer_SBBBBHandler:Android.Media.JetPlayer/IOnJetEventListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onJetNumQueuedSegmentUpdate:(Landroid/media/JetPlayer;I)V:GetOnJetNumQueuedSegmentUpdate_Landroid_media_JetPlayer_IHandler:Android.Media.JetPlayer/IOnJetEventListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onJetPauseUpdate:(Landroid/media/JetPlayer;I)V:GetOnJetPauseUpdate_Landroid_media_JetPlayer_IHandler:Android.Media.JetPlayer/IOnJetEventListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onJetUserIdUpdate:(Landroid/media/JetPlayer;II)V:GetOnJetUserIdUpdate_Landroid_media_JetPlayer_IIHandler:Android.Media.JetPlayer/IOnJetEventListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Media.JetPlayer+IOnJetEventListenerImplementor, Mono.Android", JetPlayer_OnJetEventListenerImplementor.class, "n_onJetEvent:(Landroid/media/JetPlayer;SBBBB)V:GetOnJetEvent_Landroid_media_JetPlayer_SBBBBHandler:Android.Media.JetPlayer/IOnJetEventListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onJetNumQueuedSegmentUpdate:(Landroid/media/JetPlayer;I)V:GetOnJetNumQueuedSegmentUpdate_Landroid_media_JetPlayer_IHandler:Android.Media.JetPlayer/IOnJetEventListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onJetPauseUpdate:(Landroid/media/JetPlayer;I)V:GetOnJetPauseUpdate_Landroid_media_JetPlayer_IHandler:Android.Media.JetPlayer/IOnJetEventListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onJetUserIdUpdate:(Landroid/media/JetPlayer;II)V:GetOnJetUserIdUpdate_Landroid_media_JetPlayer_IIHandler:Android.Media.JetPlayer/IOnJetEventListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public JetPlayer_OnJetEventListenerImplementor() {
    if (getClass() == JetPlayer_OnJetEventListenerImplementor.class)
      TypeManager.Activate("Android.Media.JetPlayer+IOnJetEventListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onJetEvent(JetPlayer paramJetPlayer, short paramShort, byte paramByte1, byte paramByte2, byte paramByte3, byte paramByte4);
  
  private native void n_onJetNumQueuedSegmentUpdate(JetPlayer paramJetPlayer, int paramInt);
  
  private native void n_onJetPauseUpdate(JetPlayer paramJetPlayer, int paramInt);
  
  private native void n_onJetUserIdUpdate(JetPlayer paramJetPlayer, int paramInt1, int paramInt2);
  
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
  
  public void onJetEvent(JetPlayer paramJetPlayer, short paramShort, byte paramByte1, byte paramByte2, byte paramByte3, byte paramByte4) {
    n_onJetEvent(paramJetPlayer, paramShort, paramByte1, paramByte2, paramByte3, paramByte4);
  }
  
  public void onJetNumQueuedSegmentUpdate(JetPlayer paramJetPlayer, int paramInt) {
    n_onJetNumQueuedSegmentUpdate(paramJetPlayer, paramInt);
  }
  
  public void onJetPauseUpdate(JetPlayer paramJetPlayer, int paramInt) {
    n_onJetPauseUpdate(paramJetPlayer, paramInt);
  }
  
  public void onJetUserIdUpdate(JetPlayer paramJetPlayer, int paramInt1, int paramInt2) {
    n_onJetUserIdUpdate(paramJetPlayer, paramInt1, paramInt2);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/media/JetPlayer_OnJetEventListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */