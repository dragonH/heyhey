package com.google.android.gms.common;

import android.app.Activity;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import com.google.android.gms.common.internal.zzu;

public final class GooglePlayServicesUtil extends zzo {
  public static final String GMS_ERROR_DIALOG = "GooglePlayServicesErrorDialog";
  
  @Deprecated
  public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
  
  @Deprecated
  public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = zzo.GOOGLE_PLAY_SERVICES_VERSION_CODE;
  
  public static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";
  
  @Deprecated
  public static Dialog getErrorDialog(int paramInt1, Activity paramActivity, int paramInt2) {
    return getErrorDialog(paramInt1, paramActivity, paramInt2, null);
  }
  
  @Deprecated
  public static Dialog getErrorDialog(int paramInt1, Activity paramActivity, int paramInt2, DialogInterface.OnCancelListener paramOnCancelListener) {
    int i = paramInt1;
    if (zzo.zze((Context)paramActivity, paramInt1))
      i = 18; 
    return GoogleApiAvailability.getInstance().getErrorDialog(paramActivity, i, paramInt2, paramOnCancelListener);
  }
  
  @Deprecated
  public static PendingIntent getErrorPendingIntent(int paramInt1, Context paramContext, int paramInt2) {
    return zzo.getErrorPendingIntent(paramInt1, paramContext, paramInt2);
  }
  
  @Deprecated
  public static String getErrorString(int paramInt) {
    return zzo.getErrorString(paramInt);
  }
  
  public static Context getRemoteContext(Context paramContext) {
    return zzo.getRemoteContext(paramContext);
  }
  
  public static Resources getRemoteResource(Context paramContext) {
    return zzo.getRemoteResource(paramContext);
  }
  
  @Deprecated
  public static int isGooglePlayServicesAvailable(Context paramContext) {
    return zzo.isGooglePlayServicesAvailable(paramContext);
  }
  
  @Deprecated
  public static boolean isUserRecoverableError(int paramInt) {
    return zzo.isUserRecoverableError(paramInt);
  }
  
  @Deprecated
  public static boolean showErrorDialogFragment(int paramInt1, Activity paramActivity, int paramInt2) {
    return showErrorDialogFragment(paramInt1, paramActivity, paramInt2, null);
  }
  
  @Deprecated
  public static boolean showErrorDialogFragment(int paramInt1, Activity paramActivity, int paramInt2, DialogInterface.OnCancelListener paramOnCancelListener) {
    return showErrorDialogFragment(paramInt1, paramActivity, null, paramInt2, paramOnCancelListener);
  }
  
  public static boolean showErrorDialogFragment(int paramInt1, Activity paramActivity, Fragment paramFragment, int paramInt2, DialogInterface.OnCancelListener paramOnCancelListener) {
    int i = paramInt1;
    if (zzo.zze((Context)paramActivity, paramInt1))
      i = 18; 
    GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
    if (paramFragment == null)
      return googleApiAvailability.showErrorDialogFragment(paramActivity, i, paramInt2, paramOnCancelListener); 
    GoogleApiAvailability.getInstance();
    Dialog dialog = GoogleApiAvailability.zza((Context)paramActivity, i, zzu.zza(paramFragment, zze.zza((Context)paramActivity, i, "d"), paramInt2), paramOnCancelListener);
    if (dialog == null)
      return false; 
    GoogleApiAvailability.zza(paramActivity, dialog, "GooglePlayServicesErrorDialog", paramOnCancelListener);
    return true;
  }
  
  @Deprecated
  public static void showErrorNotification(int paramInt, Context paramContext) {
    GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
    if (!zzo.zze(paramContext, paramInt)) {
      boolean bool;
      if (paramInt == 9) {
        bool = zzo.zzx(paramContext, "com.android.vending");
      } else {
        bool = false;
      } 
      if (!bool) {
        googleApiAvailability.showErrorNotification(paramContext, paramInt);
        return;
      } 
    } 
    googleApiAvailability.zzbt(paramContext);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/GooglePlayServicesUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */