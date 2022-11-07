package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import com.google.android.gms.location.zzj;
import com.google.android.gms.location.zzk;
import com.google.android.gms.location.zzm;
import com.google.android.gms.location.zzn;

public final class zzcaa extends zzbck {
  public static final Parcelable.Creator<zzcaa> CREATOR = new zzcab();
  
  private PendingIntent mPendingIntent;
  
  private int zziaa;
  
  private zzbzy zziab;
  
  private zzm zziac;
  
  private zzj zziad;
  
  private zzbzf zziae;
  
  zzcaa(int paramInt, zzbzy paramzzbzy, IBinder paramIBinder1, PendingIntent paramPendingIntent, IBinder paramIBinder2, IBinder paramIBinder3) {
    zzm zzm1;
    zzj zzj1;
    IInterface iInterface;
    this.zziaa = paramInt;
    this.zziab = paramzzbzy;
    zzj zzj2 = null;
    if (paramIBinder1 == null) {
      paramzzbzy = null;
    } else {
      zzm1 = zzn.zzbb(paramIBinder1);
    } 
    this.zziac = zzm1;
    this.mPendingIntent = paramPendingIntent;
    if (paramIBinder2 == null) {
      zzm1 = null;
    } else {
      zzj1 = zzk.zzba(paramIBinder2);
    } 
    this.zziad = zzj1;
    if (paramIBinder3 == null) {
      zzj1 = zzj2;
    } else {
      iInterface = paramIBinder3.queryLocalInterface("com.google.android.gms.location.internal.IFusedLocationProviderCallback");
      if (iInterface instanceof zzbzf) {
        iInterface = iInterface;
      } else {
        iInterface = new zzbzh(paramIBinder3);
      } 
    } 
    this.zziae = (zzbzf)iInterface;
  }
  
  public static zzcaa zza(zzj paramzzj, @Nullable zzbzf paramzzbzf) {
    IBinder iBinder = paramzzj.asBinder();
    if (paramzzbzf != null) {
      IBinder iBinder1 = paramzzbzf.asBinder();
    } else {
      paramzzj = null;
    } 
    return new zzcaa(2, null, null, null, iBinder, (IBinder)paramzzj);
  }
  
  public static zzcaa zza(zzm paramzzm, @Nullable zzbzf paramzzbzf) {
    IBinder iBinder = paramzzm.asBinder();
    if (paramzzbzf != null) {
      IBinder iBinder1 = paramzzbzf.asBinder();
    } else {
      paramzzm = null;
    } 
    return new zzcaa(2, null, iBinder, null, null, (IBinder)paramzzm);
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    IBinder iBinder3;
    IBinder iBinder2;
    IBinder iBinder1;
    int i = zzbcn.zze(paramParcel);
    zzbcn.zzc(paramParcel, 1, this.zziaa);
    zzbcn.zza(paramParcel, 2, this.zziab, paramInt, false);
    zzm zzm1 = this.zziac;
    zzbzf zzbzf2 = null;
    if (zzm1 == null) {
      zzm1 = null;
    } else {
      iBinder3 = zzm1.asBinder();
    } 
    zzbcn.zza(paramParcel, 3, iBinder3, false);
    zzbcn.zza(paramParcel, 4, (Parcelable)this.mPendingIntent, paramInt, false);
    zzj zzj1 = this.zziad;
    if (zzj1 == null) {
      zzj1 = null;
    } else {
      iBinder2 = zzj1.asBinder();
    } 
    zzbcn.zza(paramParcel, 5, iBinder2, false);
    zzbzf zzbzf1 = this.zziae;
    if (zzbzf1 == null) {
      zzbzf1 = zzbzf2;
    } else {
      iBinder1 = zzbzf1.asBinder();
    } 
    zzbcn.zza(paramParcel, 6, iBinder1, false);
    zzbcn.zzai(paramParcel, i);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzcaa.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */