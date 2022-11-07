package com.google.android.gms.common.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.IntentSender;
import android.support.annotation.NonNull;

public class ResolvableApiException extends ApiException {
  public ResolvableApiException(@NonNull Status paramStatus) {
    super(paramStatus);
  }
  
  public PendingIntent getResolution() {
    return this.mStatus.getResolution();
  }
  
  public void startResolutionForResult(Activity paramActivity, int paramInt) throws IntentSender.SendIntentException {
    this.mStatus.startResolutionForResult(paramActivity, paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/ResolvableApiException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */