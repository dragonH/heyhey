package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzq;

public final class zzbcz extends zzaa<zzbdc> {
  public zzbcz(Context paramContext, Looper paramLooper, zzq paramzzq, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener) {
    super(paramContext, paramLooper, 39, paramzzq, paramConnectionCallbacks, paramOnConnectionFailedListener);
  }
  
  public final String zzhc() {
    return "com.google.android.gms.common.service.START";
  }
  
  protected final String zzhd() {
    return "com.google.android.gms.common.internal.service.ICommonService";
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzbcz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */