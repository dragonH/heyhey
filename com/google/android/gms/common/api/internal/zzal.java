package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.data.DataHolder;

public abstract class zzal<L> implements zzcm<L> {
  private final DataHolder zzflf;
  
  protected zzal(DataHolder paramDataHolder) {
    this.zzflf = paramDataHolder;
  }
  
  protected abstract void zza(L paramL, DataHolder paramDataHolder);
  
  public final void zzagx() {
    DataHolder dataHolder = this.zzflf;
    if (dataHolder != null)
      dataHolder.close(); 
  }
  
  public final void zzq(L paramL) {
    zza(paramL, this.zzflf);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */