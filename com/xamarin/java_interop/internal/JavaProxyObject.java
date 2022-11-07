package com.xamarin.java_interop.internal;

import com.xamarin.java_interop.GCUserPeerable;
import com.xamarin.java_interop.ManagedPeer;
import java.util.ArrayList;

final class JavaProxyObject implements GCUserPeerable {
  static final String assemblyQualifiedName = "Java.Interop.JavaProxyObject, Java.Interop";
  
  ArrayList<Object> managedReferences = new ArrayList();
  
  static {
    ManagedPeer.registerNativeMembers(JavaProxyObject.class, "Java.Interop.JavaProxyObject, Java.Interop", "");
  }
  
  public native boolean equals(Object paramObject);
  
  public native int hashCode();
  
  public void jiAddManagedReference(Object paramObject) {
    this.managedReferences.add(paramObject);
  }
  
  public void jiClearManagedReferences() {
    this.managedReferences.clear();
  }
  
  public native String toString();
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/xamarin/java_interop/internal/JavaProxyObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */