package com.google.android.gms.internal;

public abstract class zzbvd<T> {
  private final int zzbfe;
  
  private final String zzbff;
  
  private final T zzbfg;
  
  private zzbvd(int paramInt, String paramString, T paramT) {
    this.zzbfe = paramInt;
    this.zzbff = paramString;
    this.zzbfg = paramT;
    zzbvo.zzapf().zza(this);
  }
  
  public static zzbvf zzb(int paramInt, String paramString, Boolean paramBoolean) {
    return new zzbvf(0, paramString, paramBoolean);
  }
  
  public static zzbvg zzb(int paramInt1, String paramString, int paramInt2) {
    return new zzbvg(0, paramString, Integer.valueOf(paramInt2));
  }
  
  public static zzbvh zzb(int paramInt, String paramString, long paramLong) {
    return new zzbvh(0, paramString, Long.valueOf(paramLong));
  }
  
  public final String getKey() {
    return this.zzbff;
  }
  
  public final int getSource() {
    return this.zzbfe;
  }
  
  protected abstract T zza(zzbvl paramzzbvl);
  
  public final T zzil() {
    return this.zzbfg;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzbvd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */