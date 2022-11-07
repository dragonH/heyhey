package com.google.android.gms.maps.model;

import android.graphics.Bitmap;
import android.os.RemoteException;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.maps.model.internal.zza;

public final class BitmapDescriptorFactory {
  public static final float HUE_AZURE = 210.0F;
  
  public static final float HUE_BLUE = 240.0F;
  
  public static final float HUE_CYAN = 180.0F;
  
  public static final float HUE_GREEN = 120.0F;
  
  public static final float HUE_MAGENTA = 300.0F;
  
  public static final float HUE_ORANGE = 30.0F;
  
  public static final float HUE_RED = 0.0F;
  
  public static final float HUE_ROSE = 330.0F;
  
  public static final float HUE_VIOLET = 270.0F;
  
  public static final float HUE_YELLOW = 60.0F;
  
  private static zza zziif;
  
  public static BitmapDescriptor defaultMarker() {
    try {
      return new BitmapDescriptor(zzatq().zzats());
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public static BitmapDescriptor defaultMarker(float paramFloat) {
    try {
      return new BitmapDescriptor(zzatq().zze(paramFloat));
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public static BitmapDescriptor fromAsset(String paramString) {
    try {
      return new BitmapDescriptor(zzatq().zzii(paramString));
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public static BitmapDescriptor fromBitmap(Bitmap paramBitmap) {
    try {
      return new BitmapDescriptor(zzatq().zzd(paramBitmap));
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public static BitmapDescriptor fromFile(String paramString) {
    try {
      return new BitmapDescriptor(zzatq().zzij(paramString));
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public static BitmapDescriptor fromPath(String paramString) {
    try {
      return new BitmapDescriptor(zzatq().zzik(paramString));
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public static BitmapDescriptor fromResource(int paramInt) {
    try {
      return new BitmapDescriptor(zzatq().zzdv(paramInt));
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public static void zza(zza paramzza) {
    if (zziif != null)
      return; 
    zziif = (zza)zzbp.zzu(paramzza);
  }
  
  private static zza zzatq() {
    return (zza)zzbp.zzb(zziif, "IBitmapDescriptorFactory is not initialized");
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/model/BitmapDescriptorFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */