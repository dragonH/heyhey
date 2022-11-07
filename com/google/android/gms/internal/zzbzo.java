package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.ContentProviderClient;
import android.content.Context;
import android.location.Location;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzcj;
import com.google.android.gms.common.api.internal.zzcl;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.zzj;
import com.google.android.gms.location.zzm;
import java.util.HashMap;
import java.util.Map;

public final class zzbzo {
  private final Context mContext;
  
  private final Map<zzcl<LocationListener>, zzbzs> zzgzm = new HashMap<zzcl<LocationListener>, zzbzs>();
  
  private final zzcae<zzbzk> zzhzb;
  
  private ContentProviderClient zzhzm = null;
  
  private boolean zzhzn = false;
  
  private final Map<zzcl<LocationCallback>, zzbzp> zzhzo = new HashMap<zzcl<LocationCallback>, zzbzp>();
  
  public zzbzo(Context paramContext, zzcae<zzbzk> paramzzcae) {
    this.mContext = paramContext;
    this.zzhzb = paramzzcae;
  }
  
  private final zzbzs zzf(zzcj<LocationListener> paramzzcj) {
    synchronized (this.zzgzm) {
      zzbzs zzbzs1 = this.zzgzm.get(paramzzcj.zzaik());
      zzbzs zzbzs2 = zzbzs1;
      if (zzbzs1 == null) {
        zzbzs2 = new zzbzs();
        this(paramzzcj);
      } 
      this.zzgzm.put(paramzzcj.zzaik(), zzbzs2);
      return zzbzs2;
    } 
  }
  
  private final zzbzp zzg(zzcj<LocationCallback> paramzzcj) {
    synchronized (this.zzhzo) {
      zzbzp zzbzp1 = this.zzhzo.get(paramzzcj.zzaik());
      zzbzp zzbzp2 = zzbzp1;
      if (zzbzp1 == null) {
        zzbzp2 = new zzbzp();
        this(paramzzcj);
      } 
      this.zzhzo.put(paramzzcj.zzaik(), zzbzp2);
      return zzbzp2;
    } 
  }
  
  public final Location getLastLocation() {
    this.zzhzb.zzaji();
    try {
      return ((zzbzk)this.zzhzb.zzajj()).zzia(this.mContext.getPackageName());
    } catch (RemoteException remoteException) {
      throw new IllegalStateException(remoteException);
    } 
  }
  
  public final void removeAllListeners() {
    try {
      synchronized (this.zzgzm) {
        for (zzbzs zzbzs : this.zzgzm.values()) {
          if (zzbzs != null)
            ((zzbzk)this.zzhzb.zzajj()).zza(zzcaa.zza((zzm)zzbzs, (zzbzf)null)); 
        } 
        this.zzgzm.clear();
        synchronized (this.zzhzo) {
          for (zzbzp zzbzp : this.zzhzo.values()) {
            if (zzbzp != null)
              ((zzbzk)this.zzhzb.zzajj()).zza(zzcaa.zza((zzj)zzbzp, (zzbzf)null)); 
          } 
          this.zzhzo.clear();
          return;
        } 
      } 
    } catch (RemoteException remoteException) {
      IllegalStateException illegalStateException = new IllegalStateException((Throwable)remoteException);
      throw illegalStateException;
    } 
  }
  
  public final void zza(PendingIntent paramPendingIntent, zzbzf paramzzbzf) throws RemoteException {
    this.zzhzb.zzaji();
    zzbzk zzbzk = this.zzhzb.zzajj();
    if (paramzzbzf != null) {
      IBinder iBinder = paramzzbzf.asBinder();
    } else {
      paramzzbzf = null;
    } 
    zzbzk.zza(new zzcaa(2, null, null, paramPendingIntent, null, (IBinder)paramzzbzf));
  }
  
  public final void zza(zzcl<LocationListener> paramzzcl, zzbzf paramzzbzf) throws RemoteException {
    this.zzhzb.zzaji();
    zzbp.zzb(paramzzcl, "Invalid null listener key");
    synchronized (this.zzgzm) {
      zzbzs zzbzs = this.zzgzm.remove(paramzzcl);
      if (zzbzs != null) {
        zzbzs.release();
        ((zzbzk)this.zzhzb.zzajj()).zza(zzcaa.zza((zzm)zzbzs, paramzzbzf));
      } 
      return;
    } 
  }
  
  public final void zza(zzbzf paramzzbzf) throws RemoteException {
    this.zzhzb.zzaji();
    ((zzbzk)this.zzhzb.zzajj()).zza(paramzzbzf);
  }
  
  public final void zza(zzbzy paramzzbzy, zzcj<LocationCallback> paramzzcj, zzbzf paramzzbzf) throws RemoteException {
    this.zzhzb.zzaji();
    zzbzp zzbzp = zzg(paramzzcj);
    zzbzk zzbzk = this.zzhzb.zzajj();
    IBinder iBinder = zzbzp.asBinder();
    if (paramzzbzf != null) {
      IBinder iBinder1 = paramzzbzf.asBinder();
    } else {
      zzbzp = null;
    } 
    zzbzk.zza(new zzcaa(1, paramzzbzy, null, null, iBinder, (IBinder)zzbzp));
  }
  
  public final void zza(LocationRequest paramLocationRequest, PendingIntent paramPendingIntent, zzbzf paramzzbzf) throws RemoteException {
    this.zzhzb.zzaji();
    zzbzk zzbzk = this.zzhzb.zzajj();
    zzbzy zzbzy = zzbzy.zza(paramLocationRequest);
    if (paramzzbzf != null) {
      IBinder iBinder = paramzzbzf.asBinder();
    } else {
      paramLocationRequest = null;
    } 
    zzbzk.zza(new zzcaa(1, zzbzy, null, paramPendingIntent, null, (IBinder)paramLocationRequest));
  }
  
  public final void zza(LocationRequest paramLocationRequest, zzcj<LocationListener> paramzzcj, zzbzf paramzzbzf) throws RemoteException {
    this.zzhzb.zzaji();
    zzbzs zzbzs = zzf(paramzzcj);
    zzbzk zzbzk = this.zzhzb.zzajj();
    zzbzy zzbzy = zzbzy.zza(paramLocationRequest);
    IBinder iBinder = zzbzs.asBinder();
    if (paramzzbzf != null) {
      IBinder iBinder1 = paramzzbzf.asBinder();
    } else {
      paramLocationRequest = null;
    } 
    zzbzk.zza(new zzcaa(1, zzbzy, iBinder, null, null, (IBinder)paramLocationRequest));
  }
  
  public final LocationAvailability zzasw() {
    this.zzhzb.zzaji();
    try {
      return ((zzbzk)this.zzhzb.zzajj()).zzib(this.mContext.getPackageName());
    } catch (RemoteException remoteException) {
      throw new IllegalStateException(remoteException);
    } 
  }
  
  public final void zzasx() {
    if (this.zzhzn)
      try {
        zzbk(false);
        return;
      } catch (RemoteException remoteException) {
        throw new IllegalStateException(remoteException);
      }  
  }
  
  public final void zzb(zzcl<LocationCallback> paramzzcl, zzbzf paramzzbzf) throws RemoteException {
    this.zzhzb.zzaji();
    zzbp.zzb(paramzzcl, "Invalid null listener key");
    synchronized (this.zzhzo) {
      zzbzp zzbzp = this.zzhzo.remove(paramzzcl);
      if (zzbzp != null) {
        zzbzp.release();
        ((zzbzk)this.zzhzb.zzajj()).zza(zzcaa.zza((zzj)zzbzp, paramzzbzf));
      } 
      return;
    } 
  }
  
  public final void zzbk(boolean paramBoolean) throws RemoteException {
    this.zzhzb.zzaji();
    ((zzbzk)this.zzhzb.zzajj()).zzbk(paramBoolean);
    this.zzhzn = paramBoolean;
  }
  
  public final void zzc(Location paramLocation) throws RemoteException {
    this.zzhzb.zzaji();
    ((zzbzk)this.zzhzb.zzajj()).zzc(paramLocation);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzbzo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */