package com.google.android.gms.common.internal;

import com.google.android.gms.internal.zzbck;

public abstract class DowngradeableSafeParcel extends zzbck implements ReflectedParcelable {
  private static final Object zzfty = new Object();
  
  private static ClassLoader zzftz;
  
  private static Integer zzfua;
  
  private boolean zzfub = false;
  
  private static ClassLoader zzakb() {
    synchronized (zzfty) {
      return null;
    } 
  }
  
  protected static Integer zzakc() {
    synchronized (zzfty) {
      return null;
    } 
  }
  
  protected static boolean zzgb(String paramString) {
    zzakb();
    return true;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/internal/DowngradeableSafeParcel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */