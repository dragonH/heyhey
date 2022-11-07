package mono.android.sax;

import android.sax.StartElementListener;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;
import org.xml.sax.Attributes;

public class StartElementListenerImplementor implements IGCUserPeer, StartElementListener {
  public static final String __md_methods = "n_start:(Lorg/xml/sax/Attributes;)V:GetStart_Lorg_xml_sax_Attributes_Handler:Android.Sax.IStartElementListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Sax.IStartElementListenerImplementor, Mono.Android", StartElementListenerImplementor.class, "n_start:(Lorg/xml/sax/Attributes;)V:GetStart_Lorg_xml_sax_Attributes_Handler:Android.Sax.IStartElementListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public StartElementListenerImplementor() {
    if (getClass() == StartElementListenerImplementor.class)
      TypeManager.Activate("Android.Sax.IStartElementListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_start(Attributes paramAttributes);
  
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
  
  public void start(Attributes paramAttributes) {
    n_start(paramAttributes);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/sax/StartElementListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */