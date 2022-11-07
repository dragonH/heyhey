package com.google.android.gms.common.internal;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.IInterface;
import android.support.annotation.BinderThread;
import com.google.android.gms.common.ConnectionResult;

abstract class zze extends zzi<Boolean> {
  private int statusCode;
  
  private Bundle zzftk;
  
  @BinderThread
  protected zze(zzd paramzzd, int paramInt, Bundle paramBundle) {
    super(paramzzd, Boolean.TRUE);
    this.statusCode = paramInt;
    this.zzftk = paramBundle;
  }
  
  protected abstract boolean zzajn();
  
  protected abstract void zzj(ConnectionResult paramConnectionResult);
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/internal/zze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */