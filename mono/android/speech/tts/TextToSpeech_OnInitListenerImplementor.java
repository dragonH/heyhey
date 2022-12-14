package mono.android.speech.tts;

import android.speech.tts.TextToSpeech;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class TextToSpeech_OnInitListenerImplementor implements IGCUserPeer, TextToSpeech.OnInitListener {
  public static final String __md_methods = "n_onInit:(I)V:GetOnInit_IHandler:Android.Speech.Tts.TextToSpeech/IOnInitListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Speech.Tts.TextToSpeech+IOnInitListenerImplementor, Mono.Android", TextToSpeech_OnInitListenerImplementor.class, "n_onInit:(I)V:GetOnInit_IHandler:Android.Speech.Tts.TextToSpeech/IOnInitListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public TextToSpeech_OnInitListenerImplementor() {
    if (getClass() == TextToSpeech_OnInitListenerImplementor.class)
      TypeManager.Activate("Android.Speech.Tts.TextToSpeech+IOnInitListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onInit(int paramInt);
  
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
  
  public void onInit(int paramInt) {
    n_onInit(paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/speech/tts/TextToSpeech_OnInitListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */