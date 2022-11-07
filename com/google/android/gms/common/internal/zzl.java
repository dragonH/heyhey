package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;

public final class zzl implements ServiceConnection {
  private final int zzfto;
  
  public zzl(zzd paramzzd, int paramInt) {
    this.zzfto = paramInt;
  }
  
  public final void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder) {
    null = this.zzftl;
    if (paramIBinder == null) {
      zzd.zza(null, 16);
      return;
    } 
    synchronized (zzd.zza(null)) {
      zzd zzd1 = this.zzftl;
      IInterface iInterface = paramIBinder.queryLocalInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
      if (iInterface != null && iInterface instanceof zzax) {
        iInterface = iInterface;
      } else {
        iInterface = new zzay(paramIBinder);
      } 
      zzd.zza(zzd1, (zzax)iInterface);
      this.zzftl.zza(0, (Bundle)null, this.zzfto);
      return;
    } 
  }
  
  public final void onServiceDisconnected(ComponentName paramComponentName) {
    synchronized (zzd.zza(this.zzftl)) {
      zzd.zza(this.zzftl, (zzax)null);
      Handler handler = this.zzftl.mHandler;
      handler.sendMessage(handler.obtainMessage(6, this.zzfto, 1));
      return;
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/internal/zzl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */