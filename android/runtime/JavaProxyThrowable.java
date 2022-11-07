package android.runtime;

import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class JavaProxyThrowable extends Error implements IGCUserPeer {
  public static final String __md_methods = "";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Runtime.JavaProxyThrowable, Mono.Android", JavaProxyThrowable.class, "");
  }
  
  public JavaProxyThrowable() {
    if (getClass() == JavaProxyThrowable.class)
      TypeManager.Activate("Android.Runtime.JavaProxyThrowable, Mono.Android", "", this, new Object[0]); 
  }
  
  public JavaProxyThrowable(String paramString) {
    super(paramString);
    if (getClass() == JavaProxyThrowable.class)
      TypeManager.Activate("Android.Runtime.JavaProxyThrowable, Mono.Android", "System.String, mscorlib", this, new Object[] { paramString }); 
  }
  
  public JavaProxyThrowable(String paramString, Throwable paramThrowable) {
    super(paramString, paramThrowable);
    if (getClass() == JavaProxyThrowable.class)
      TypeManager.Activate("Android.Runtime.JavaProxyThrowable, Mono.Android", "System.String, mscorlib:Java.Lang.Throwable, Mono.Android", this, new Object[] { paramString, paramThrowable }); 
  }
  
  public JavaProxyThrowable(String paramString, Throwable paramThrowable, boolean paramBoolean1, boolean paramBoolean2) {
    super(paramString, paramThrowable, paramBoolean1, paramBoolean2);
    if (getClass() == JavaProxyThrowable.class)
      TypeManager.Activate("Android.Runtime.JavaProxyThrowable, Mono.Android", "System.String, mscorlib:Java.Lang.Throwable, Mono.Android:System.Boolean, mscorlib:System.Boolean, mscorlib", this, new Object[] { paramString, paramThrowable, Boolean.valueOf(paramBoolean1), Boolean.valueOf(paramBoolean2) }); 
  }
  
  public JavaProxyThrowable(Throwable paramThrowable) {
    super(paramThrowable);
    if (getClass() == JavaProxyThrowable.class)
      TypeManager.Activate("Android.Runtime.JavaProxyThrowable, Mono.Android", "Java.Lang.Throwable, Mono.Android", this, new Object[] { paramThrowable }); 
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


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/runtime/JavaProxyThrowable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */