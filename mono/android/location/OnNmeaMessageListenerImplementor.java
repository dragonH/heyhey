package mono.android.location;

import android.location.OnNmeaMessageListener;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class OnNmeaMessageListenerImplementor implements IGCUserPeer, OnNmeaMessageListener {
  public static final String __md_methods = "n_onNmeaMessage:(Ljava/lang/String;J)V:GetOnNmeaMessage_Ljava_lang_String_JHandler:Android.Locations.IOnNmeaMessageListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Locations.IOnNmeaMessageListenerImplementor, Mono.Android", OnNmeaMessageListenerImplementor.class, "n_onNmeaMessage:(Ljava/lang/String;J)V:GetOnNmeaMessage_Ljava_lang_String_JHandler:Android.Locations.IOnNmeaMessageListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public OnNmeaMessageListenerImplementor() {
    if (getClass() == OnNmeaMessageListenerImplementor.class)
      TypeManager.Activate("Android.Locations.IOnNmeaMessageListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onNmeaMessage(String paramString, long paramLong);
  
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
  
  public void onNmeaMessage(String paramString, long paramLong) {
    n_onNmeaMessage(paramString, paramLong);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/location/OnNmeaMessageListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */