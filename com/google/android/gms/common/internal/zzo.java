package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.support.annotation.BinderThread;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;

public final class zzo extends zze {
  @BinderThread
  public zzo(zzd paramzzd, @Nullable int paramInt, Bundle paramBundle) {
    super(paramzzd, paramInt, null);
  }
  
  protected final boolean zzajn() {
    this.zzftl.zzfsx.zzf(ConnectionResult.zzfff);
    return true;
  }
  
  protected final void zzj(ConnectionResult paramConnectionResult) {
    this.zzftl.zzfsx.zzf(paramConnectionResult);
    this.zzftl.onConnectionFailed(paramConnectionResult);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/internal/zzo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */