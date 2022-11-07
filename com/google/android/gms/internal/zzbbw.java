package com.google.android.gms.internal;

public class zzbbw<T> {
  private static String READ_PERMISSION = "com.google.android.providers.gsf.permission.READ_GSERVICES";
  
  private static final Object zzaqd = new Object();
  
  private static zzbcc zzfpy;
  
  private static int zzfpz;
  
  private String zzbff;
  
  private T zzbfg;
  
  private T zzfqa = null;
  
  protected zzbbw(String paramString, T paramT) {
    this.zzbff = paramString;
    this.zzbfg = paramT;
  }
  
  public static zzbbw<Float> zza(String paramString, Float paramFloat) {
    return new zzbca(paramString, paramFloat);
  }
  
  public static zzbbw<Integer> zza(String paramString, Integer paramInteger) {
    return new zzbbz(paramString, paramInteger);
  }
  
  public static zzbbw<Long> zza(String paramString, Long paramLong) {
    return new zzbby(paramString, paramLong);
  }
  
  public static zzbbw<Boolean> zze(String paramString, boolean paramBoolean) {
    return new zzbbx(paramString, Boolean.valueOf(paramBoolean));
  }
  
  public static zzbbw<String> zzt(String paramString1, String paramString2) {
    return new zzbcb(paramString1, paramString2);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzbbw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */