package mono.java.util;

import java.util.ArrayList;
import java.util.EventListener;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class EventListenerImplementor implements IGCUserPeer, EventListener {
  public static final String __md_methods = "";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Java.Util.IEventListenerImplementor, Mono.Android", EventListenerImplementor.class, "");
  }
  
  public EventListenerImplementor() {
    if (getClass() == EventListenerImplementor.class)
      TypeManager.Activate("Java.Util.IEventListenerImplementor, Mono.Android", "", this, new Object[0]); 
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


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/java/util/EventListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */