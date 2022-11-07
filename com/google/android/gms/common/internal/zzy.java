package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.zzc;
import com.google.android.gms.common.zze;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;

public final class zzy extends zzbck {
  public static final Parcelable.Creator<zzy> CREATOR = new zzz();
  
  private int version = 3;
  
  private int zzfuc;
  
  private int zzfud;
  
  String zzfue;
  
  IBinder zzfuf;
  
  Scope[] zzfug;
  
  Bundle zzfuh;
  
  Account zzfui;
  
  zzc[] zzfuj;
  
  public zzy(int paramInt) {
    this.zzfud = zze.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    this.zzfuc = paramInt;
  }
  
  zzy(int paramInt1, int paramInt2, int paramInt3, String paramString, IBinder paramIBinder, Scope[] paramArrayOfScope, Bundle paramBundle, Account paramAccount, zzc[] paramArrayOfzzc) {
    this.zzfuc = paramInt2;
    this.zzfud = paramInt3;
    if ("com.google.android.gms".equals(paramString)) {
      this.zzfue = "com.google.android.gms";
    } else {
      this.zzfue = paramString;
    } 
    if (paramInt1 < 2) {
      Account account;
      paramString = null;
      if (paramIBinder != null) {
        IInterface iInterface = paramIBinder.queryLocalInterface("com.google.android.gms.common.internal.IAccountAccessor");
        if (iInterface instanceof zzam) {
          iInterface = iInterface;
        } else {
          iInterface = new zzao(paramIBinder);
        } 
        account = zza.zza((zzam)iInterface);
      } 
      this.zzfui = account;
    } else {
      this.zzfuf = paramIBinder;
      this.zzfui = paramAccount;
    } 
    this.zzfug = paramArrayOfScope;
    this.zzfuh = paramBundle;
    this.zzfuj = paramArrayOfzzc;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = zzbcn.zze(paramParcel);
    zzbcn.zzc(paramParcel, 1, this.version);
    zzbcn.zzc(paramParcel, 2, this.zzfuc);
    zzbcn.zzc(paramParcel, 3, this.zzfud);
    zzbcn.zza(paramParcel, 4, this.zzfue, false);
    zzbcn.zza(paramParcel, 5, this.zzfuf, false);
    zzbcn.zza(paramParcel, 6, (Parcelable[])this.zzfug, paramInt, false);
    zzbcn.zza(paramParcel, 7, this.zzfuh, false);
    zzbcn.zza(paramParcel, 8, (Parcelable)this.zzfui, paramInt, false);
    zzbcn.zza(paramParcel, 10, (Parcelable[])this.zzfuj, paramInt, false);
    zzbcn.zzai(paramParcel, i);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/internal/zzy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */