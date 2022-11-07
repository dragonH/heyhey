package com.google.android.gms.internal;

import android.app.PendingIntent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzm;
import com.google.android.gms.location.ActivityRecognitionApi;

public final class zzbye implements ActivityRecognitionApi {
  public final PendingResult<Status> removeActivityUpdates(GoogleApiClient paramGoogleApiClient, PendingIntent paramPendingIntent) {
    return (PendingResult<Status>)paramGoogleApiClient.zze((zzm)new zzbyg(this, paramGoogleApiClient, paramPendingIntent));
  }
  
  public final PendingResult<Status> requestActivityUpdates(GoogleApiClient paramGoogleApiClient, long paramLong, PendingIntent paramPendingIntent) {
    return (PendingResult<Status>)paramGoogleApiClient.zze((zzm)new zzbyf(this, paramGoogleApiClient, paramLong, paramPendingIntent));
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzbye.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */