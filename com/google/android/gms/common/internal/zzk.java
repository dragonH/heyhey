package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.BinderThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

public final class zzk extends zzaw {
  private zzd zzftn;
  
  private final int zzfto;
  
  public zzk(@NonNull zzd paramzzd, int paramInt) {
    this.zzftn = paramzzd;
    this.zzfto = paramInt;
  }
  
  @BinderThread
  public final void zza(int paramInt, @Nullable Bundle paramBundle) {
    Log.wtf("GmsClient", "received deprecated onAccountValidationComplete callback, ignoring", new Exception());
  }
  
  @BinderThread
  public final void zza(int paramInt, @NonNull IBinder paramIBinder, @Nullable Bundle paramBundle) {
    zzbp.zzb(this.zzftn, "onPostInitComplete can be called only once per call to getRemoteService");
    this.zzftn.zza(paramInt, paramIBinder, paramBundle, this.zzfto);
    this.zzftn = null;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/internal/zzk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */