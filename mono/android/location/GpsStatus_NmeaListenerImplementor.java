package mono.android.location;

import android.location.GpsStatus;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class GpsStatus_NmeaListenerImplementor implements IGCUserPeer, GpsStatus.NmeaListener {
  public static final String __md_methods = "n_onNmeaReceived:(JLjava/lang/String;)V:GetOnNmeaReceived_JLjava_lang_String_Handler:Android.Locations.GpsStatus/INmeaListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Locations.GpsStatus+INmeaListenerImplementor, Mono.Android", GpsStatus_NmeaListenerImplementor.class, "n_onNmeaReceived:(JLjava/lang/String;)V:GetOnNmeaReceived_JLjava_lang_String_Handler:Android.Locations.GpsStatus/INmeaListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public GpsStatus_NmeaListenerImplementor() {
    if (getClass() == GpsStatus_NmeaListenerImplementor.class)
      TypeManager.Activate("Android.Locations.GpsStatus+INmeaListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onNmeaReceived(long paramLong, String paramString);
  
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
  
  public void onNmeaReceived(long paramLong, String paramString) {
    n_onNmeaReceived(paramLong, paramString);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/location/GpsStatus_NmeaListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */