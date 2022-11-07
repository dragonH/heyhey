package mono.android.preference;

import android.preference.Preference;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class Preference_OnPreferenceClickListenerImplementor implements IGCUserPeer, Preference.OnPreferenceClickListener {
  public static final String __md_methods = "n_onPreferenceClick:(Landroid/preference/Preference;)Z:GetOnPreferenceClick_Landroid_preference_Preference_Handler:Android.Preferences.Preference/IOnPreferenceClickListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Preferences.Preference+IOnPreferenceClickListenerImplementor, Mono.Android", Preference_OnPreferenceClickListenerImplementor.class, "n_onPreferenceClick:(Landroid/preference/Preference;)Z:GetOnPreferenceClick_Landroid_preference_Preference_Handler:Android.Preferences.Preference/IOnPreferenceClickListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public Preference_OnPreferenceClickListenerImplementor() {
    if (getClass() == Preference_OnPreferenceClickListenerImplementor.class)
      TypeManager.Activate("Android.Preferences.Preference+IOnPreferenceClickListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native boolean n_onPreferenceClick(Preference paramPreference);
  
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
  
  public boolean onPreferenceClick(Preference paramPreference) {
    return n_onPreferenceClick(paramPreference);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/preference/Preference_OnPreferenceClickListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */