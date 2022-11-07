package com.google.android.gms.internal;

public final class zzt<T> {
  public final T result = null;
  
  public final zzc zzbe = null;
  
  public final zzaa zzbf;
  
  public boolean zzbg = false;
  
  private zzt(zzaa paramzzaa) {
    this.zzbf = paramzzaa;
  }
  
  private zzt(T paramT, zzc paramzzc) {
    this.zzbf = null;
  }
  
  public static <T> zzt<T> zza(T paramT, zzc paramzzc) {
    return new zzt<T>(paramT, paramzzc);
  }
  
  public static <T> zzt<T> zzc(zzaa paramzzaa) {
    return new zzt<T>(paramzzaa);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */