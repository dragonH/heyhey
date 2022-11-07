package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.common.internal.zzak;

public final class zzbde {
  private final String mTag;
  
  private final int zzdqr;
  
  private final String zzfvl;
  
  private final zzak zzfwj;
  
  private zzbde(String paramString1, String paramString2) {
    this.zzfvl = paramString2;
    this.mTag = paramString1;
    this.zzfwj = new zzak(paramString1);
    this.zzdqr = getLogLevel();
  }
  
  public zzbde(String paramString, String... paramVarArgs) {
    this(paramString, zzb(paramVarArgs));
  }
  
  private final String format(String paramString, @Nullable Object... paramVarArgs) {
    String str = paramString;
    if (paramVarArgs != null) {
      str = paramString;
      if (paramVarArgs.length > 0)
        str = String.format(paramString, paramVarArgs); 
    } 
    return this.zzfvl.concat(str);
  }
  
  private final int getLogLevel() {
    byte b;
    for (b = 2; 7 >= b && !Log.isLoggable(this.mTag, b); b++);
    return b;
  }
  
  private final boolean zzad(int paramInt) {
    return (this.zzdqr <= paramInt);
  }
  
  private static String zzb(String... paramVarArgs) {
    if (paramVarArgs.length == 0)
      return ""; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append('[');
    int i = paramVarArgs.length;
    for (byte b = 0; b < i; b++) {
      String str = paramVarArgs[b];
      if (stringBuilder.length() > 1)
        stringBuilder.append(","); 
      stringBuilder.append(str);
    } 
    stringBuilder.append(']');
    stringBuilder.append(' ');
    return stringBuilder.toString();
  }
  
  public final void zza(String paramString, Throwable paramThrowable, @Nullable Object... paramVarArgs) {
    Log.wtf(this.mTag, format(paramString, paramVarArgs), paramThrowable);
  }
  
  public final void zza(String paramString, @Nullable Object... paramVarArgs) {
    if (zzad(2))
      Log.v(this.mTag, format(paramString, paramVarArgs)); 
  }
  
  public final void zzb(String paramString, @Nullable Object... paramVarArgs) {
    if (zzad(3))
      Log.d(this.mTag, format(paramString, paramVarArgs)); 
  }
  
  public final void zzc(Throwable paramThrowable) {
    Log.wtf(this.mTag, paramThrowable);
  }
  
  public final void zze(String paramString, @Nullable Object... paramVarArgs) {
    Log.i(this.mTag, format(paramString, paramVarArgs));
  }
  
  public final void zzf(String paramString, @Nullable Object... paramVarArgs) {
    Log.w(this.mTag, format(paramString, paramVarArgs));
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzbde.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */