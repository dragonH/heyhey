package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

final class zzai implements ServiceConnection {
  private int mState;
  
  private IBinder zzftq;
  
  private ComponentName zzfuw;
  
  private final Set<ServiceConnection> zzfvc;
  
  private boolean zzfvd;
  
  private final zzag zzfve;
  
  public zzai(zzah paramzzah, zzag paramzzag) {
    this.zzfve = paramzzag;
    this.zzfvc = new HashSet<ServiceConnection>();
    this.mState = 2;
  }
  
  public final IBinder getBinder() {
    return this.zzftq;
  }
  
  public final ComponentName getComponentName() {
    return this.zzfuw;
  }
  
  public final int getState() {
    return this.mState;
  }
  
  public final boolean isBound() {
    return this.zzfvd;
  }
  
  public final void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder) {
    synchronized (zzah.zza(this.zzfvf)) {
      zzah.zzb(this.zzfvf).removeMessages(1, this.zzfve);
      this.zzftq = paramIBinder;
      this.zzfuw = paramComponentName;
      Iterator<ServiceConnection> iterator = this.zzfvc.iterator();
      while (iterator.hasNext())
        ((ServiceConnection)iterator.next()).onServiceConnected(paramComponentName, paramIBinder); 
      this.mState = 1;
      return;
    } 
  }
  
  public final void onServiceDisconnected(ComponentName paramComponentName) {
    synchronized (zzah.zza(this.zzfvf)) {
      zzah.zzb(this.zzfvf).removeMessages(1, this.zzfve);
      this.zzftq = null;
      this.zzfuw = paramComponentName;
      Iterator<ServiceConnection> iterator = this.zzfvc.iterator();
      while (iterator.hasNext())
        ((ServiceConnection)iterator.next()).onServiceDisconnected(paramComponentName); 
      this.mState = 2;
      return;
    } 
  }
  
  public final void zza(ServiceConnection paramServiceConnection, String paramString) {
    zzah.zzd(this.zzfvf);
    zzah.zzc(this.zzfvf);
    this.zzfve.zzakh();
    this.zzfvc.add(paramServiceConnection);
  }
  
  public final boolean zza(ServiceConnection paramServiceConnection) {
    return this.zzfvc.contains(paramServiceConnection);
  }
  
  public final boolean zzaki() {
    return this.zzfvc.isEmpty();
  }
  
  public final void zzb(ServiceConnection paramServiceConnection, String paramString) {
    zzah.zzd(this.zzfvf);
    zzah.zzc(this.zzfvf);
    this.zzfvc.remove(paramServiceConnection);
  }
  
  public final void zzgc(String paramString) {
    this.mState = 3;
    boolean bool = zzah.zzd(this.zzfvf).zza(zzah.zzc(this.zzfvf), paramString, this.zzfve.zzakh(), this, this.zzfve.zzakg());
    this.zzfvd = bool;
    if (bool) {
      Message message = zzah.zzb(this.zzfvf).obtainMessage(1, this.zzfve);
      zzah.zzb(this.zzfvf).sendMessageDelayed(message, zzah.zze(this.zzfvf));
      return;
    } 
    this.mState = 2;
    try {
      zzah.zzd(this.zzfvf);
      zzah.zzc(this.zzfvf).unbindService(this);
    } catch (IllegalArgumentException illegalArgumentException) {}
  }
  
  public final void zzgd(String paramString) {
    zzah.zzb(this.zzfvf).removeMessages(1, this.zzfve);
    zzah.zzd(this.zzfvf);
    zzah.zzc(this.zzfvf).unbindService(this);
    this.zzfvd = false;
    this.mState = 2;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/internal/zzai.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */