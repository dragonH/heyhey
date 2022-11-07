package mono.android.media.midi;

import android.media.midi.MidiDevice;
import android.media.midi.MidiManager;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class MidiManager_OnDeviceOpenedListenerImplementor implements IGCUserPeer, MidiManager.OnDeviceOpenedListener {
  public static final String __md_methods = "n_onDeviceOpened:(Landroid/media/midi/MidiDevice;)V:GetOnDeviceOpened_Landroid_media_midi_MidiDevice_Handler:Android.Media.Midi.MidiManager/IOnDeviceOpenedListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Media.Midi.MidiManager+IOnDeviceOpenedListenerImplementor, Mono.Android", MidiManager_OnDeviceOpenedListenerImplementor.class, "n_onDeviceOpened:(Landroid/media/midi/MidiDevice;)V:GetOnDeviceOpened_Landroid_media_midi_MidiDevice_Handler:Android.Media.Midi.MidiManager/IOnDeviceOpenedListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public MidiManager_OnDeviceOpenedListenerImplementor() {
    if (getClass() == MidiManager_OnDeviceOpenedListenerImplementor.class)
      TypeManager.Activate("Android.Media.Midi.MidiManager+IOnDeviceOpenedListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onDeviceOpened(MidiDevice paramMidiDevice);
  
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
  
  public void onDeviceOpened(MidiDevice paramMidiDevice) {
    n_onDeviceOpened(paramMidiDevice);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/media/midi/MidiManager_OnDeviceOpenedListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */