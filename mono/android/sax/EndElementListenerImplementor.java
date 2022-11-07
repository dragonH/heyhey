package mono.android.sax;

import android.sax.EndElementListener;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class EndElementListenerImplementor implements IGCUserPeer, EndElementListener {
  public static final String __md_methods = "n_end:()V:GetEndHandler:Android.Sax.IEndElementListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Sax.IEndElementListenerImplementor, Mono.Android", EndElementListenerImplementor.class, "n_end:()V:GetEndHandler:Android.Sax.IEndElementListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public EndElementListenerImplementor() {
    if (getClass() == EndElementListenerImplementor.class)
      TypeManager.Activate("Android.Sax.IEndElementListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_end();
  
  public void end() {
    n_end();
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


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/sax/EndElementListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */