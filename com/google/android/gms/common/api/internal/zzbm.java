package com.google.android.gms.common.api.internal;

abstract class zzbm {
  private final zzbk zzfnh;
  
  protected zzbm(zzbk paramzzbk) {
    this.zzfnh = paramzzbk;
  }
  
  protected abstract void zzagz();
  
  public final void zzc(zzbl paramzzbl) {
    zzbl.zza(paramzzbl).lock();
    try {
      zzbk zzbk1 = zzbl.zzb(paramzzbl);
      zzbk zzbk2 = this.zzfnh;
      if (zzbk1 == zzbk2)
        zzagz(); 
      return;
    } finally {
      zzbl.zza(paramzzbl).unlock();
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzbm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */