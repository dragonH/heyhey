package com.google.android.gms.common;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.google.android.gms.common.internal.zzbp;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public final class zza implements ServiceConnection {
  private boolean zzffd = false;
  
  private final BlockingQueue<IBinder> zzffe = new LinkedBlockingQueue<IBinder>();
  
  public final void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder) {
    this.zzffe.add(paramIBinder);
  }
  
  public final void onServiceDisconnected(ComponentName paramComponentName) {}
  
  public final IBinder zza(long paramLong, TimeUnit paramTimeUnit) throws InterruptedException, TimeoutException {
    zzbp.zzgh("BlockingServiceConnection.getServiceWithTimeout() called on main thread");
    if (!this.zzffd) {
      this.zzffd = true;
      IBinder iBinder = this.zzffe.poll(10000L, paramTimeUnit);
      if (iBinder != null)
        return iBinder; 
      throw new TimeoutException("Timed out waiting for the service connection");
    } 
    throw new IllegalStateException("Cannot call get on this connection more than once");
  }
  
  public final IBinder zzaew() throws InterruptedException {
    zzbp.zzgh("BlockingServiceConnection.getService() called on main thread");
    if (!this.zzffd) {
      this.zzffd = true;
      return this.zzffe.take();
    } 
    throw new IllegalStateException("Cannot call get on this connection more than once");
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */