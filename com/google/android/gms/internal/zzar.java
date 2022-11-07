package com.google.android.gms.internal;

import java.io.UnsupportedEncodingException;

public class zzar extends zzp<String> {
  private final zzv<String> zzce;
  
  public zzar(int paramInt, String paramString, zzv<String> paramzzv, zzu paramzzu) {
    super(paramInt, paramString, paramzzu);
    this.zzce = paramzzv;
  }
  
  protected final zzt<String> zza(zzn paramzzn) {
    String str;
    try {
      str = new String();
      this(paramzzn.data, zzam.zza(paramzzn.zzy));
    } catch (UnsupportedEncodingException unsupportedEncodingException) {
      str = new String(paramzzn.data);
    } 
    return zzt.zza(str, zzam.zzb(paramzzn));
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */