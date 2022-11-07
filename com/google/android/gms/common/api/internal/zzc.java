package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

public final class zzc<A extends zzm<? extends Result, Api.zzb>> extends zza {
  private A zzfic;
  
  public zzc(int paramInt, A paramA) {
    super(paramInt);
    this.zzfic = paramA;
  }
  
  public final void zza(@NonNull zzah paramzzah, boolean paramBoolean) {
    paramzzah.zza((zzs<? extends Result>)this.zzfic, paramBoolean);
  }
  
  public final void zza(zzbr<?> paramzzbr) throws DeadObjectException {
    this.zzfic.zzb(paramzzbr.zzagn());
  }
  
  public final void zzr(@NonNull Status paramStatus) {
    this.zzfic.zzt(paramStatus);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */