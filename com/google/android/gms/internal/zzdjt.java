package com.google.android.gms.internal;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

final class zzdjt extends WeakReference<Throwable> {
  private final int zzlib;
  
  public zzdjt(Throwable paramThrowable, ReferenceQueue<Throwable> paramReferenceQueue) {
    super(paramThrowable, (ReferenceQueue<? super Throwable>)null);
    if (paramThrowable != null) {
      this.zzlib = System.identityHashCode(paramThrowable);
      return;
    } 
    throw new NullPointerException("The referent cannot be null");
  }
  
  public final boolean equals(Object paramObject) {
    if (paramObject != null && paramObject.getClass() == zzdjt.class) {
      if (this == paramObject)
        return true; 
      paramObject = paramObject;
      if (this.zzlib == ((zzdjt)paramObject).zzlib && get() == paramObject.get())
        return true; 
    } 
    return false;
  }
  
  public final int hashCode() {
    return this.zzlib;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzdjt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */