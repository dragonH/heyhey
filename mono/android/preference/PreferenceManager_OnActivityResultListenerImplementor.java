package mono.android.preference;

import android.content.Intent;
import android.preference.PreferenceManager;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class PreferenceManager_OnActivityResultListenerImplementor implements IGCUserPeer, PreferenceManager.OnActivityResultListener {
  public static final String __md_methods = "n_onActivityResult:(IILandroid/content/Intent;)Z:GetOnActivityResult_IILandroid_content_Intent_Handler:Android.Preferences.PreferenceManager/IOnActivityResultListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Preferences.PreferenceManager+IOnActivityResultListenerImplementor, Mono.Android", PreferenceManager_OnActivityResultListenerImplementor.class, "n_onActivityResult:(IILandroid/content/Intent;)Z:GetOnActivityResult_IILandroid_content_Intent_Handler:Android.Preferences.PreferenceManager/IOnActivityResultListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public PreferenceManager_OnActivityResultListenerImplementor() {
    if (getClass() == PreferenceManager_OnActivityResultListenerImplementor.class)
      TypeManager.Activate("Android.Preferences.PreferenceManager+IOnActivityResultListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native boolean n_onActivityResult(int paramInt1, int paramInt2, Intent paramIntent);
  
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
  
  public boolean onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
    return n_onActivityResult(paramInt1, paramInt2, paramIntent);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/preference/PreferenceManager_OnActivityResultListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */