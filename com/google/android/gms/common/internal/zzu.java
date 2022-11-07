package com.google.android.gms.common.internal;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import com.google.android.gms.common.api.internal.zzcg;

public abstract class zzu implements DialogInterface.OnClickListener {
  public static zzu zza(Activity paramActivity, Intent paramIntent, int paramInt) {
    return new zzv(paramIntent, paramActivity, paramInt);
  }
  
  public static zzu zza(@NonNull Fragment paramFragment, Intent paramIntent, int paramInt) {
    return new zzw(paramIntent, paramFragment, paramInt);
  }
  
  public static zzu zza(@NonNull zzcg paramzzcg, Intent paramIntent, int paramInt) {
    return new zzx(paramIntent, paramzzcg, 2);
  }
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt) {
    Exception exception;
    try {
      zzaka();
      paramDialogInterface.dismiss();
      return;
    } catch (ActivityNotFoundException activityNotFoundException) {
      Log.e("DialogRedirect", "Failed to start resolution intent", (Throwable)activityNotFoundException);
      paramDialogInterface.dismiss();
      return;
    } finally {}
    paramDialogInterface.dismiss();
    throw exception;
  }
  
  protected abstract void zzaka();
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/internal/zzu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */