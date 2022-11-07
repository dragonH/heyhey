package mono.java.lang;

import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class RunnableImplementor implements IGCUserPeer, java.lang.Runnable {
  public static final String __md_methods = "n_run:()V:GetRunHandler:Java.Lang.IRunnableInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Java.Lang.Thread+RunnableImplementor, Mono.Android", RunnableImplementor.class, "n_run:()V:GetRunHandler:Java.Lang.IRunnableInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public RunnableImplementor() {
    if (getClass() == RunnableImplementor.class)
      TypeManager.Activate("Java.Lang.Thread+RunnableImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_run();
  
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
  
  public void run() {
    n_run();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/java/lang/RunnableImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */