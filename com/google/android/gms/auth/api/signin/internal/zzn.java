package com.google.android.gms.auth.api.signin.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.GoogleSignInOptionsExtension;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;

public final class zzn extends zzbck {
  public static final Parcelable.Creator<zzn> CREATOR = new zzm();
  
  private Bundle mBundle;
  
  private int versionCode;
  
  private int zzecz;
  
  zzn(int paramInt1, int paramInt2, Bundle paramBundle) {
    this.versionCode = paramInt1;
    this.zzecz = paramInt2;
    this.mBundle = paramBundle;
  }
  
  public zzn(GoogleSignInOptionsExtension paramGoogleSignInOptionsExtension) {
    this(1, 1, paramGoogleSignInOptionsExtension.toBundle());
  }
  
  public final int getType() {
    return this.zzecz;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = zzbcn.zze(paramParcel);
    zzbcn.zzc(paramParcel, 1, this.versionCode);
    zzbcn.zzc(paramParcel, 2, this.zzecz);
    zzbcn.zza(paramParcel, 3, this.mBundle, false);
    zzbcn.zzai(paramParcel, paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/auth/api/signin/internal/zzn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */