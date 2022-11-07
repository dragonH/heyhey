package mono.android.preference;

import android.preference.PreferenceManager;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class PreferenceManager_OnActivityStopListenerImplementor implements IGCUserPeer, PreferenceManager.OnActivityStopListener {
  public static final String __md_methods = "n_onActivityStop:()V:GetOnActivityStopHandler:Android.Preferences.PreferenceManager/IOnActivityStopListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Preferences.PreferenceManager+IOnActivityStopListenerImplementor, Mono.Android", PreferenceManager_OnActivityStopListenerImplementor.class, "n_onActivityStop:()V:GetOnActivityStopHandler:Android.Preferences.PreferenceManager/IOnActivityStopListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public PreferenceManager_OnActivityStopListenerImplementor() {
    if (getClass() == PreferenceManager_OnActivityStopListenerImplementor.class)
      TypeManager.Activate("Android.Preferences.PreferenceManager+IOnActivityStopListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onActivityStop();
  
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
  
  public void onActivityStop() {
    n_onActivityStop();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/preference/PreferenceManager_OnActivityStopListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */