package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzbp;

final class zzp {
  private final int zzfiy;
  
  private final ConnectionResult zzfiz;
  
  zzp(ConnectionResult paramConnectionResult, int paramInt) {
    zzbp.zzu(paramConnectionResult);
    this.zzfiz = paramConnectionResult;
    this.zzfiy = paramInt;
  }
  
  final int zzagc() {
    return this.zzfiy;
  }
  
  final ConnectionResult zzagd() {
    return this.zzfiz;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */