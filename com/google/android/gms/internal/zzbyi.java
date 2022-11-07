package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzq;

public class zzbyi extends zzaa<zzbzk> {
  private final String zzhza;
  
  protected final zzcae<zzbzk> zzhzb = new zzbyj(this);
  
  public zzbyi(Context paramContext, Looper paramLooper, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener, String paramString, zzq paramzzq) {
    super(paramContext, paramLooper, 23, paramzzq, paramConnectionCallbacks, paramOnConnectionFailedListener);
    this.zzhza = paramString;
  }
  
  protected final String zzhc() {
    return "com.google.android.location.internal.GoogleLocationManagerService.START";
  }
  
  protected final String zzhd() {
    return "com.google.android.gms.location.internal.IGoogleLocationManagerService";
  }
  
  protected final Bundle zzzu() {
    Bundle bundle = new Bundle();
    bundle.putString("client_name", this.zzhza);
    return bundle;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzbyi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */