package mono.android.media.effect;

import android.media.effect.Effect;
import android.media.effect.EffectUpdateListener;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class EffectUpdateListenerImplementor implements IGCUserPeer, EffectUpdateListener {
  public static final String __md_methods = "n_onEffectUpdated:(Landroid/media/effect/Effect;Ljava/lang/Object;)V:GetOnEffectUpdated_Landroid_media_effect_Effect_Ljava_lang_Object_Handler:Android.Media.Effect.IEffectUpdateListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Media.Effect.IEffectUpdateListenerImplementor, Mono.Android", EffectUpdateListenerImplementor.class, "n_onEffectUpdated:(Landroid/media/effect/Effect;Ljava/lang/Object;)V:GetOnEffectUpdated_Landroid_media_effect_Effect_Ljava_lang_Object_Handler:Android.Media.Effect.IEffectUpdateListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public EffectUpdateListenerImplementor() {
    if (getClass() == EffectUpdateListenerImplementor.class)
      TypeManager.Activate("Android.Media.Effect.IEffectUpdateListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onEffectUpdated(Effect paramEffect, Object paramObject);
  
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
  
  public void onEffectUpdated(Effect paramEffect, Object paramObject) {
    n_onEffectUpdated(paramEffect, paramObject);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/media/effect/EffectUpdateListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */