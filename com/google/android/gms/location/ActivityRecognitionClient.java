package com.google.android.gms.location;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.zzcz;
import com.google.android.gms.common.api.internal.zzg;
import com.google.android.gms.common.internal.zzbi;
import com.google.android.gms.tasks.Task;

public class ActivityRecognitionClient extends GoogleApi<Api.ApiOptions.NoOptions> {
  public ActivityRecognitionClient(@NonNull Activity paramActivity) {
    super(paramActivity, LocationServices.API, null, (zzcz)new zzg());
  }
  
  public ActivityRecognitionClient(@NonNull Context paramContext) {
    super(paramContext, LocationServices.API, null, (zzcz)new zzg());
  }
  
  @RequiresPermission("com.google.android.gms.permission.ACTIVITY_RECOGNITION")
  public Task<Void> removeActivityUpdates(PendingIntent paramPendingIntent) {
    return zzbi.zzb(ActivityRecognition.ActivityRecognitionApi.removeActivityUpdates(zzafl(), paramPendingIntent));
  }
  
  @RequiresPermission("com.google.android.gms.permission.ACTIVITY_RECOGNITION")
  public Task<Void> requestActivityUpdates(long paramLong, PendingIntent paramPendingIntent) {
    return zzbi.zzb(ActivityRecognition.ActivityRecognitionApi.requestActivityUpdates(zzafl(), paramLong, paramPendingIntent));
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/ActivityRecognitionClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */