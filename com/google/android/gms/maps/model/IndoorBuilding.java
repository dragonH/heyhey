package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.maps.model.internal.zzj;
import com.google.android.gms.maps.model.internal.zzn;
import java.util.ArrayList;
import java.util.List;

public final class IndoorBuilding {
  private final zzj zzija;
  
  public IndoorBuilding(zzj paramzzj) {
    this.zzija = (zzj)zzbp.zzu(paramzzj);
  }
  
  public final boolean equals(Object paramObject) {
    if (!(paramObject instanceof IndoorBuilding))
      return false; 
    try {
      return this.zzija.zzb(((IndoorBuilding)paramObject).zzija);
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final int getActiveLevelIndex() {
    try {
      return this.zzija.getActiveLevelIndex();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final int getDefaultLevelIndex() {
    try {
      return this.zzija.getActiveLevelIndex();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final List<IndoorLevel> getLevels() {
    try {
      List list = this.zzija.getLevels();
      ArrayList<IndoorLevel> arrayList = new ArrayList();
      this(list.size());
      for (IBinder iBinder : list) {
        IndoorLevel indoorLevel = new IndoorLevel();
        this(zzn.zzbg(iBinder));
        arrayList.add(indoorLevel);
      } 
      return arrayList;
    } catch (RemoteException remoteException) {
      RuntimeRemoteException runtimeRemoteException = new RuntimeRemoteException(remoteException);
      throw runtimeRemoteException;
    } 
  }
  
  public final int hashCode() {
    try {
      return this.zzija.hashCodeRemote();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final boolean isUnderground() {
    try {
      return this.zzija.isUnderground();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/model/IndoorBuilding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */