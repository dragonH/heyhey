package com.google.android.gms.common.internal;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.google.android.gms.common.util.zzv;

public final class zzbp {
  public static int zza(int paramInt, Object paramObject) {
    if (paramInt != 0)
      return paramInt; 
    throw new IllegalArgumentException(String.valueOf(paramObject));
  }
  
  public static long zza(long paramLong, Object paramObject) {
    if (paramLong != 0L)
      return paramLong; 
    throw new IllegalArgumentException(String.valueOf(paramObject));
  }
  
  public static void zza(Handler paramHandler) {
    if (Looper.myLooper() == paramHandler.getLooper())
      return; 
    throw new IllegalStateException("Must be called on the handler thread");
  }
  
  public static void zza(boolean paramBoolean, Object paramObject) {
    if (paramBoolean)
      return; 
    throw new IllegalStateException(String.valueOf(paramObject));
  }
  
  public static void zza(boolean paramBoolean, String paramString, Object... paramVarArgs) {
    if (paramBoolean)
      return; 
    throw new IllegalStateException(String.format(paramString, paramVarArgs));
  }
  
  public static <T> T zzb(T paramT, Object paramObject) {
    if (paramT != null)
      return paramT; 
    throw new NullPointerException(String.valueOf(paramObject));
  }
  
  public static void zzb(boolean paramBoolean, Object paramObject) {
    if (paramBoolean)
      return; 
    throw new IllegalArgumentException(String.valueOf(paramObject));
  }
  
  public static void zzb(boolean paramBoolean, String paramString, Object... paramVarArgs) {
    if (paramBoolean)
      return; 
    throw new IllegalArgumentException(String.format(paramString, paramVarArgs));
  }
  
  public static void zzbg(boolean paramBoolean) {
    if (paramBoolean)
      return; 
    throw new IllegalStateException();
  }
  
  public static void zzbh(boolean paramBoolean) {
    if (paramBoolean)
      return; 
    throw new IllegalArgumentException();
  }
  
  public static void zzfy(String paramString) {
    if (zzv.zzaq())
      return; 
    throw new IllegalStateException(paramString);
  }
  
  public static String zzgg(String paramString) {
    if (!TextUtils.isEmpty(paramString))
      return paramString; 
    throw new IllegalArgumentException("Given String is empty or null");
  }
  
  public static void zzgh(String paramString) {
    if (!zzv.zzaq())
      return; 
    throw new IllegalStateException(paramString);
  }
  
  public static String zzh(String paramString, Object paramObject) {
    if (!TextUtils.isEmpty(paramString))
      return paramString; 
    throw new IllegalArgumentException(String.valueOf(paramObject));
  }
  
  public static <T> T zzu(T paramT) {
    if (paramT != null)
      return paramT; 
    throw new NullPointerException("null reference");
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/internal/zzbp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */