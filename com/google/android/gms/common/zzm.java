package com.google.android.gms.common;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.zzat;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;

public final class zzm extends zzbck {
  public static final Parcelable.Creator<zzm> CREATOR = new zzn();
  
  private final String zzfft;
  
  private final zzg zzffu;
  
  private final boolean zzffv;
  
  zzm(String paramString, IBinder paramIBinder, boolean paramBoolean) {
    this.zzfft = paramString;
    this.zzffu = zzai(paramIBinder);
    this.zzffv = paramBoolean;
  }
  
  zzm(String paramString, zzg paramzzg, boolean paramBoolean) {
    this.zzfft = paramString;
    this.zzffu = paramzzg;
    this.zzffv = paramBoolean;
  }
  
  private static zzg zzai(IBinder paramIBinder) {
    byte[] arrayOfByte = null;
    if (paramIBinder == null)
      return null; 
    try {
      byte[] arrayOfByte1;
      IObjectWrapper iObjectWrapper = zzat.zzak(paramIBinder).zzaez();
      if (iObjectWrapper == null) {
        iObjectWrapper = null;
      } else {
        arrayOfByte1 = (byte[])zzn.zzx(iObjectWrapper);
      } 
      if (arrayOfByte1 != null) {
        zzh zzh = new zzh(arrayOfByte1);
      } else {
        Log.e("GoogleCertificatesQuery", "Could not unwrap certificate");
        arrayOfByte1 = arrayOfByte;
      } 
      return (zzg)arrayOfByte1;
    } catch (RemoteException remoteException) {
      Log.e("GoogleCertificatesQuery", "Could not unwrap certificate", (Throwable)remoteException);
      return null;
    } 
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    IBinder iBinder;
    paramInt = zzbcn.zze(paramParcel);
    zzbcn.zza(paramParcel, 1, this.zzfft, false);
    zzg zzg1 = this.zzffu;
    if (zzg1 == null) {
      Log.w("GoogleCertificatesQuery", "certificate binder is null");
      zzg1 = null;
    } else {
      iBinder = zzg1.asBinder();
    } 
    zzbcn.zza(paramParcel, 2, iBinder, false);
    zzbcn.zza(paramParcel, 3, this.zzffv);
    zzbcn.zzai(paramParcel, paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/zzm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */