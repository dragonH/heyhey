package com.google.android.gms.location;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.zzm;
import com.google.android.gms.internal.zzbye;
import com.google.android.gms.internal.zzbzu;

public class ActivityRecognition {
  public static final Api<Api.ApiOptions.NoOptions> API;
  
  @Deprecated
  public static final ActivityRecognitionApi ActivityRecognitionApi;
  
  public static final String CLIENT_NAME = "activity_recognition";
  
  private static final Api.zzf<zzbzu> zzdwp;
  
  private static final Api.zza<zzbzu, Api.ApiOptions.NoOptions> zzdwq;
  
  static {
    Api.zzf<zzbzu> zzf1 = new Api.zzf();
    zzdwp = zzf1;
    zza zza1 = new zza();
    zzdwq = zza1;
    API = new Api("ActivityRecognition.API", zza1, zzf1);
    ActivityRecognitionApi = (ActivityRecognitionApi)new zzbye();
  }
  
  public static ActivityRecognitionClient getClient(Activity paramActivity) {
    return new ActivityRecognitionClient(paramActivity);
  }
  
  public static ActivityRecognitionClient getClient(Context paramContext) {
    return new ActivityRecognitionClient(paramContext);
  }
  
  public static abstract class zza<R extends Result> extends zzm<R, zzbzu> {
    public zza(GoogleApiClient param1GoogleApiClient) {
      super(ActivityRecognition.API, param1GoogleApiClient);
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/ActivityRecognition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */