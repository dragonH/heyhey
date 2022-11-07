package mono.android.preference;

import android.preference.Preference;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class Preference_OnPreferenceChangeListenerImplementor implements IGCUserPeer, Preference.OnPreferenceChangeListener {
  public static final String __md_methods = "n_onPreferenceChange:(Landroid/preference/Preference;Ljava/lang/Object;)Z:GetOnPreferenceChange_Landroid_preference_Preference_Ljava_lang_Object_Handler:Android.Preferences.Preference/IOnPreferenceChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Preferences.Preference+IOnPreferenceChangeListenerImplementor, Mono.Android", Preference_OnPreferenceChangeListenerImplementor.class, "n_onPreferenceChange:(Landroid/preference/Preference;Ljava/lang/Object;)Z:GetOnPreferenceChange_Landroid_preference_Preference_Ljava_lang_Object_Handler:Android.Preferences.Preference/IOnPreferenceChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public Preference_OnPreferenceChangeListenerImplementor() {
    if (getClass() == Preference_OnPreferenceChangeListenerImplementor.class)
      TypeManager.Activate("Android.Preferences.Preference+IOnPreferenceChangeListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native boolean n_onPreferenceChange(Preference paramPreference, Object paramObject);
  
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
  
  public boolean onPreferenceChange(Preference paramPreference, Object paramObject) {
    return n_onPreferenceChange(paramPreference, paramObject);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/preference/Preference_OnPreferenceChangeListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */