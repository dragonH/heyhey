package com.google.android.gms.common.internal;

import android.util.Log;

public final class zzak {
  private static int zzfvi = 15;
  
  private static final String zzfvj;
  
  private final String zzfvk;
  
  private final String zzfvl;
  
  public zzak(String paramString) {
    this(paramString, null);
  }
  
  private zzak(String paramString1, String paramString2) {
    boolean bool;
    zzbp.zzb(paramString1, "log tag cannot be null");
    if (paramString1.length() <= 23) {
      bool = true;
    } else {
      bool = false;
    } 
    zzbp.zzb(bool, "tag \"%s\" is longer than the %d character maximum", new Object[] { paramString1, Integer.valueOf(23) });
    this.zzfvk = paramString1;
    this.zzfvl = null;
  }
  
  private final boolean zzcf(int paramInt) {
    return Log.isLoggable(this.zzfvk, paramInt);
  }
  
  private final String zzgf(String paramString) {
    String str = this.zzfvl;
    return (str == null) ? paramString : str.concat(paramString);
  }
  
  public final void zzb(String paramString1, String paramString2, Throwable paramThrowable) {
    if (zzcf(4))
      Log.i(paramString1, zzgf(paramString2), paramThrowable); 
  }
  
  public final void zzc(String paramString1, String paramString2, Throwable paramThrowable) {
    if (zzcf(5))
      Log.w(paramString1, zzgf(paramString2), paramThrowable); 
  }
  
  public final void zzd(String paramString1, String paramString2, Throwable paramThrowable) {
    if (zzcf(6))
      Log.e(paramString1, zzgf(paramString2), paramThrowable); 
  }
  
  public final void zze(String paramString1, String paramString2, Throwable paramThrowable) {
    if (zzcf(7)) {
      Log.e(paramString1, zzgf(paramString2), paramThrowable);
      Log.wtf(paramString1, zzgf(paramString2), paramThrowable);
    } 
  }
  
  public final void zzv(String paramString1, String paramString2) {
    if (zzcf(3))
      Log.d(paramString1, zzgf(paramString2)); 
  }
  
  public final void zzw(String paramString1, String paramString2) {
    if (zzcf(5))
      Log.w(paramString1, zzgf(paramString2)); 
  }
  
  public final void zzx(String paramString1, String paramString2) {
    if (zzcf(6))
      Log.e(paramString1, zzgf(paramString2)); 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/internal/zzak.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */