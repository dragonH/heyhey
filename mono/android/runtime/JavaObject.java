package mono.android.runtime;

import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class JavaObject implements IGCUserPeer {
  public static final String __md_methods = "n_equals:(Ljava/lang/Object;)Z:GetEquals_Ljava_lang_Object_Handler\nn_hashCode:()I:GetGetHashCodeHandler\nn_toString:()Ljava/lang/String;:GetToStringHandler\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Runtime.JavaObject, Mono.Android", JavaObject.class, "n_equals:(Ljava/lang/Object;)Z:GetEquals_Ljava_lang_Object_Handler\nn_hashCode:()I:GetGetHashCodeHandler\nn_toString:()Ljava/lang/String;:GetToStringHandler\n");
  }
  
  public JavaObject() {
    if (getClass() == JavaObject.class)
      TypeManager.Activate("Android.Runtime.JavaObject, Mono.Android", "", this, new Object[0]); 
  }
  
  private native boolean n_equals(Object paramObject);
  
  private native int n_hashCode();
  
  private native String n_toString();
  
  public boolean equals(Object paramObject) {
    return n_equals(paramObject);
  }
  
  public int hashCode() {
    return n_hashCode();
  }
  
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
  
  public String toString() {
    return n_toString();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/runtime/JavaObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */