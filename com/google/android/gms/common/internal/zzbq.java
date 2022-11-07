package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;

public final class zzbq extends zzbck {
  public static final Parcelable.Creator<zzbq> CREATOR = new zzbr();
  
  private final Account zzduz;
  
  private int zzdxs;
  
  private final int zzfvx;
  
  private final GoogleSignInAccount zzfvy;
  
  zzbq(int paramInt1, Account paramAccount, int paramInt2, GoogleSignInAccount paramGoogleSignInAccount) {
    this.zzdxs = paramInt1;
    this.zzduz = paramAccount;
    this.zzfvx = paramInt2;
    this.zzfvy = paramGoogleSignInAccount;
  }
  
  public zzbq(Account paramAccount, int paramInt, GoogleSignInAccount paramGoogleSignInAccount) {
    this(2, paramAccount, paramInt, paramGoogleSignInAccount);
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = zzbcn.zze(paramParcel);
    zzbcn.zzc(paramParcel, 1, this.zzdxs);
    zzbcn.zza(paramParcel, 2, (Parcelable)this.zzduz, paramInt, false);
    zzbcn.zzc(paramParcel, 3, this.zzfvx);
    zzbcn.zza(paramParcel, 4, (Parcelable)this.zzfvy, paramInt, false);
    zzbcn.zzai(paramParcel, i);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/internal/zzbq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */