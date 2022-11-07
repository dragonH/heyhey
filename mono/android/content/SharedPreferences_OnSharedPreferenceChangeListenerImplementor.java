package mono.android.content;

import android.content.SharedPreferences;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class SharedPreferences_OnSharedPreferenceChangeListenerImplementor implements IGCUserPeer, SharedPreferences.OnSharedPreferenceChangeListener {
  public static final String __md_methods = "n_onSharedPreferenceChanged:(Landroid/content/SharedPreferences;Ljava/lang/String;)V:GetOnSharedPreferenceChanged_Landroid_content_SharedPreferences_Ljava_lang_String_Handler:Android.Content.ISharedPreferencesOnSharedPreferenceChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Content.ISharedPreferencesOnSharedPreferenceChangeListenerImplementor, Mono.Android", SharedPreferences_OnSharedPreferenceChangeListenerImplementor.class, "n_onSharedPreferenceChanged:(Landroid/content/SharedPreferences;Ljava/lang/String;)V:GetOnSharedPreferenceChanged_Landroid_content_SharedPreferences_Ljava_lang_String_Handler:Android.Content.ISharedPreferencesOnSharedPreferenceChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public SharedPreferences_OnSharedPreferenceChangeListenerImplementor() {
    if (getClass() == SharedPreferences_OnSharedPreferenceChangeListenerImplementor.class)
      TypeManager.Activate("Android.Content.ISharedPreferencesOnSharedPreferenceChangeListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onSharedPreferenceChanged(SharedPreferences paramSharedPreferences, String paramString);
  
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
  
  public void onSharedPreferenceChanged(SharedPreferences paramSharedPreferences, String paramString) {
    n_onSharedPreferenceChanged(paramSharedPreferences, paramString);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/content/SharedPreferences_OnSharedPreferenceChangeListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */