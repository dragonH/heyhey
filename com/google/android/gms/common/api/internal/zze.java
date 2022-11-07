package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zze<TResult> extends zza {
  private final TaskCompletionSource<TResult> zzdzc;
  
  private final zzdd<Api.zzb, TResult> zzfif;
  
  private final zzcz zzfig;
  
  public zze(int paramInt, zzdd<Api.zzb, TResult> paramzzdd, TaskCompletionSource<TResult> paramTaskCompletionSource, zzcz paramzzcz) {
    super(paramInt);
    this.zzdzc = paramTaskCompletionSource;
    this.zzfif = paramzzdd;
    this.zzfig = paramzzcz;
  }
  
  public final void zza(@NonNull zzah paramzzah, boolean paramBoolean) {
    paramzzah.zza(this.zzdzc, paramBoolean);
  }
  
  public final void zza(zzbr<?> paramzzbr) throws DeadObjectException {
    try {
      this.zzfif.zza(paramzzbr.zzagn(), this.zzdzc);
      return;
    } catch (DeadObjectException deadObjectException) {
      throw deadObjectException;
    } catch (RemoteException remoteException) {
      super.zzr(zza.zzb(remoteException));
      return;
    } 
  }
  
  public final void zzr(@NonNull Status paramStatus) {
    this.zzdzc.trySetException(this.zzfig.zzs(paramStatus));
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */