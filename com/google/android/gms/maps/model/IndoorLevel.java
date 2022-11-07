package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.maps.model.internal.zzm;

public final class IndoorLevel {
  private final zzm zzijb;
  
  public IndoorLevel(zzm paramzzm) {
    this.zzijb = (zzm)zzbp.zzu(paramzzm);
  }
  
  public final void activate() {
    try {
      this.zzijb.activate();
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final boolean equals(Object paramObject) {
    if (!(paramObject instanceof IndoorLevel))
      return false; 
    try {
      return this.zzijb.zza(((IndoorLevel)paramObject).zzijb);
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final String getName() {
    try {
      return this.zzijb.getName();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final String getShortName() {
    try {
      return this.zzijb.getShortName();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final int hashCode() {
    try {
      return this.zzijb.hashCodeRemote();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/model/IndoorLevel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */