package mono.android.media;

import android.media.AudioRecord;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class AudioRecord_OnRecordPositionUpdateListenerImplementor implements IGCUserPeer, AudioRecord.OnRecordPositionUpdateListener {
  public static final String __md_methods = "n_onMarkerReached:(Landroid/media/AudioRecord;)V:GetOnMarkerReached_Landroid_media_AudioRecord_Handler:Android.Media.AudioRecord/IOnRecordPositionUpdateListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onPeriodicNotification:(Landroid/media/AudioRecord;)V:GetOnPeriodicNotification_Landroid_media_AudioRecord_Handler:Android.Media.AudioRecord/IOnRecordPositionUpdateListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Media.AudioRecord+IOnRecordPositionUpdateListenerImplementor, Mono.Android", AudioRecord_OnRecordPositionUpdateListenerImplementor.class, "n_onMarkerReached:(Landroid/media/AudioRecord;)V:GetOnMarkerReached_Landroid_media_AudioRecord_Handler:Android.Media.AudioRecord/IOnRecordPositionUpdateListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onPeriodicNotification:(Landroid/media/AudioRecord;)V:GetOnPeriodicNotification_Landroid_media_AudioRecord_Handler:Android.Media.AudioRecord/IOnRecordPositionUpdateListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public AudioRecord_OnRecordPositionUpdateListenerImplementor() {
    if (getClass() == AudioRecord_OnRecordPositionUpdateListenerImplementor.class)
      TypeManager.Activate("Android.Media.AudioRecord+IOnRecordPositionUpdateListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onMarkerReached(AudioRecord paramAudioRecord);
  
  private native void n_onPeriodicNotification(AudioRecord paramAudioRecord);
  
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
  
  public void onMarkerReached(AudioRecord paramAudioRecord) {
    n_onMarkerReached(paramAudioRecord);
  }
  
  public void onPeriodicNotification(AudioRecord paramAudioRecord) {
    n_onPeriodicNotification(paramAudioRecord);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/media/AudioRecord_OnRecordPositionUpdateListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */