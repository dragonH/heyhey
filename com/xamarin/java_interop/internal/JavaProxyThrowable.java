package com.xamarin.java_interop.internal;

import com.xamarin.java_interop.GCUserPeerable;
import com.xamarin.java_interop.ManagedPeer;
import java.util.ArrayList;

final class JavaProxyThrowable extends Error implements GCUserPeerable {
  static final String assemblyQualifiedName = "Java.Interop.JavaProxyThrowable, Java.Interop";
  
  ArrayList<Object> managedReferences = new ArrayList();
  
  static {
    ManagedPeer.registerNativeMembers(JavaProxyThrowable.class, "Java.Interop.JavaProxyThrowable, Java.Interop", "");
  }
  
  public JavaProxyThrowable() {}
  
  public JavaProxyThrowable(String paramString) {
    super(paramString);
  }
  
  public void jiAddManagedReference(Object paramObject) {
    this.managedReferences.add(paramObject);
  }
  
  public void jiClearManagedReferences() {
    this.managedReferences.clear();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/xamarin/java_interop/internal/JavaProxyThrowable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */