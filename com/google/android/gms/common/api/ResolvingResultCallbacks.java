package com.google.android.gms.common.api;

import android.app.Activity;
import android.content.IntentSender;
import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.zzbp;

public abstract class ResolvingResultCallbacks<R extends Result> extends ResultCallbacks<R> {
  private final Activity mActivity;
  
  private final int zzfhs;
  
  protected ResolvingResultCallbacks(@NonNull Activity paramActivity, int paramInt) {
    this.mActivity = (Activity)zzbp.zzb(paramActivity, "Activity must not be null");
    this.zzfhs = paramInt;
  }
  
  @KeepForSdk
  public final void onFailure(@NonNull Status paramStatus) {
    if (paramStatus.hasResolution())
      try {
        paramStatus.startResolutionForResult(this.mActivity, this.zzfhs);
        return;
      } catch (android.content.IntentSender.SendIntentException sendIntentException) {
        Log.e("ResolvingResultCallback", "Failed to start resolution", (Throwable)sendIntentException);
        onUnresolvableFailure(new Status(8));
        return;
      }  
    onUnresolvableFailure((Status)sendIntentException);
  }
  
  public abstract void onSuccess(@NonNull R paramR);
  
  public abstract void onUnresolvableFailure(@NonNull Status paramStatus);
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/ResolvingResultCallbacks.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */