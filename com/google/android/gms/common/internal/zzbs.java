package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;

public final class zzbs extends zzbck {
  public static final Parcelable.Creator<zzbs> CREATOR = new zzbt();
  
  private int zzdxs;
  
  private ConnectionResult zzfiz;
  
  private boolean zzflu;
  
  private IBinder zzfvz;
  
  private boolean zzfwa;
  
  zzbs(int paramInt, IBinder paramIBinder, ConnectionResult paramConnectionResult, boolean paramBoolean1, boolean paramBoolean2) {
    this.zzdxs = paramInt;
    this.zzfvz = paramIBinder;
    this.zzfiz = paramConnectionResult;
    this.zzflu = paramBoolean1;
    this.zzfwa = paramBoolean2;
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof zzbs))
      return false; 
    paramObject = paramObject;
    return (this.zzfiz.equals(((zzbs)paramObject).zzfiz) && zzakl().equals(paramObject.zzakl()));
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = zzbcn.zze(paramParcel);
    zzbcn.zzc(paramParcel, 1, this.zzdxs);
    zzbcn.zza(paramParcel, 2, this.zzfvz, false);
    zzbcn.zza(paramParcel, 3, (Parcelable)this.zzfiz, paramInt, false);
    zzbcn.zza(paramParcel, 4, this.zzflu);
    zzbcn.zza(paramParcel, 5, this.zzfwa);
    zzbcn.zzai(paramParcel, i);
  }
  
  public final ConnectionResult zzagd() {
    return this.zzfiz;
  }
  
  public final zzam zzakl() {
    IBinder iBinder = this.zzfvz;
    if (iBinder == null)
      return null; 
    IInterface iInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IAccountAccessor");
    return (iInterface instanceof zzam) ? (zzam)iInterface : new zzao(iBinder);
  }
  
  public final boolean zzakm() {
    return this.zzflu;
  }
  
  public final boolean zzakn() {
    return this.zzfwa;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/internal/zzbs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */