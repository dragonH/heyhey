package mono.android.sax;

import android.sax.EndTextElementListener;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class EndTextElementListenerImplementor implements IGCUserPeer, EndTextElementListener {
  public static final String __md_methods = "n_end:(Ljava/lang/String;)V:GetEnd_Ljava_lang_String_Handler:Android.Sax.IEndTextElementListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Sax.IEndTextElementListenerImplementor, Mono.Android", EndTextElementListenerImplementor.class, "n_end:(Ljava/lang/String;)V:GetEnd_Ljava_lang_String_Handler:Android.Sax.IEndTextElementListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public EndTextElementListenerImplementor() {
    if (getClass() == EndTextElementListenerImplementor.class)
      TypeManager.Activate("Android.Sax.IEndTextElementListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_end(String paramString);
  
  public void end(String paramString) {
    n_end(paramString);
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
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/sax/EndTextElementListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */