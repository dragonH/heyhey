package com.google.android.gms.common;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ProgressBar;
import com.google.android.gms.R;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiActivity;
import com.google.android.gms.common.api.internal.zzbp;
import com.google.android.gms.common.api.internal.zzby;
import com.google.android.gms.common.api.internal.zzbz;
import com.google.android.gms.common.api.internal.zzcg;
import com.google.android.gms.common.api.internal.zzco;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.internal.zzt;
import com.google.android.gms.common.internal.zzu;
import com.google.android.gms.common.util.zzi;
import com.google.android.gms.common.util.zzq;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.ArrayList;
import java.util.Arrays;

public class GoogleApiAvailability extends zze {
  public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
  
  public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE;
  
  private static final GoogleApiAvailability zzffi = new GoogleApiAvailability();
  
  static {
    GOOGLE_PLAY_SERVICES_VERSION_CODE = zze.GOOGLE_PLAY_SERVICES_VERSION_CODE;
  }
  
  public static GoogleApiAvailability getInstance() {
    return zzffi;
  }
  
  public static Dialog zza(Activity paramActivity, DialogInterface.OnCancelListener paramOnCancelListener) {
    ProgressBar progressBar = new ProgressBar((Context)paramActivity, null, 16842874);
    progressBar.setIndeterminate(true);
    progressBar.setVisibility(0);
    AlertDialog.Builder builder = new AlertDialog.Builder((Context)paramActivity);
    builder.setView((View)progressBar);
    builder.setMessage(zzt.zzi((Context)paramActivity, 18));
    builder.setPositiveButton("", null);
    AlertDialog alertDialog = builder.create();
    zza(paramActivity, (Dialog)alertDialog, "GooglePlayServicesUpdatingDialog", paramOnCancelListener);
    return (Dialog)alertDialog;
  }
  
  static Dialog zza(Context paramContext, int paramInt, zzu paramzzu, DialogInterface.OnCancelListener paramOnCancelListener) {
    AlertDialog.Builder builder1 = null;
    if (paramInt == 0)
      return null; 
    TypedValue typedValue = new TypedValue();
    paramContext.getTheme().resolveAttribute(16843529, typedValue, true);
    if ("Theme.Dialog.Alert".equals(paramContext.getResources().getResourceEntryName(typedValue.resourceId)))
      builder1 = new AlertDialog.Builder(paramContext, 5); 
    AlertDialog.Builder builder2 = builder1;
    if (builder1 == null)
      builder2 = new AlertDialog.Builder(paramContext); 
    builder2.setMessage(zzt.zzi(paramContext, paramInt));
    if (paramOnCancelListener != null)
      builder2.setOnCancelListener(paramOnCancelListener); 
    String str2 = zzt.zzk(paramContext, paramInt);
    if (str2 != null)
      builder2.setPositiveButton(str2, (DialogInterface.OnClickListener)paramzzu); 
    String str1 = zzt.zzg(paramContext, paramInt);
    if (str1 != null)
      builder2.setTitle(str1); 
    return (Dialog)builder2.create();
  }
  
  @Nullable
  public static zzby zza(Context paramContext, zzbz paramzzbz) {
    IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
    intentFilter.addDataScheme("package");
    zzby zzby = new zzby(paramzzbz);
    paramContext.registerReceiver((BroadcastReceiver)zzby, intentFilter);
    zzby.setContext(paramContext);
    if (!zzo.zzx(paramContext, "com.google.android.gms")) {
      paramzzbz.zzage();
      zzby.unregister();
      return null;
    } 
    return zzby;
  }
  
  static void zza(Activity paramActivity, Dialog paramDialog, String paramString, DialogInterface.OnCancelListener paramOnCancelListener) {
    FragmentManager fragmentManager1;
    if (paramActivity instanceof FragmentActivity) {
      fragmentManager1 = ((FragmentActivity)paramActivity).getSupportFragmentManager();
      SupportErrorDialogFragment.newInstance(paramDialog, paramOnCancelListener).show(fragmentManager1, paramString);
      return;
    } 
    FragmentManager fragmentManager = fragmentManager1.getFragmentManager();
    ErrorDialogFragment.newInstance(paramDialog, paramOnCancelListener).show(fragmentManager, paramString);
  }
  
  @TargetApi(20)
  private final void zza(Context paramContext, int paramInt, String paramString, PendingIntent paramPendingIntent) {
    Notification notification;
    if (paramInt == 18) {
      zzbt(paramContext);
      return;
    } 
    if (paramPendingIntent == null) {
      if (paramInt == 6)
        Log.w("GoogleApiAvailability", "Missing resolution for ConnectionResult.RESOLUTION_REQUIRED. Call GoogleApiAvailability#showErrorNotification(Context, ConnectionResult) instead."); 
      return;
    } 
    paramString = zzt.zzh(paramContext, paramInt);
    String str = zzt.zzj(paramContext, paramInt);
    Resources resources = paramContext.getResources();
    if (zzi.zzcj(paramContext)) {
      zzbp.zzbg(zzq.zzali());
      notification = (new Notification.Builder(paramContext)).setSmallIcon((paramContext.getApplicationInfo()).icon).setPriority(2).setAutoCancel(true).setContentTitle(paramString).setStyle((Notification.Style)(new Notification.BigTextStyle()).bigText(str)).addAction(R.drawable.common_full_open_on_phone, resources.getString(R.string.common_open_on_phone), paramPendingIntent).build();
    } else {
      notification = (new NotificationCompat.Builder(paramContext)).setSmallIcon(17301642).setTicker(resources.getString(R.string.common_google_play_services_notification_ticker)).setWhen(System.currentTimeMillis()).setAutoCancel(true).setContentIntent(paramPendingIntent).setContentTitle((CharSequence)notification).setContentText(str).setLocalOnly(true).setStyle((NotificationCompat.Style)(new NotificationCompat.BigTextStyle()).bigText(str)).build();
    } 
    if (paramInt != 1 && paramInt != 2 && paramInt != 3) {
      paramInt = 39789;
    } else {
      paramInt = 10436;
      zzo.zzfga.set(false);
    } 
    ((NotificationManager)paramContext.getSystemService("notification")).notify(paramInt, notification);
  }
  
  public Task<Void> checkApiAvailability(GoogleApi<?> paramGoogleApi, GoogleApi<?>... paramVarArgs) {
    zzbp.zzb(paramGoogleApi, "Requested API must not be null.");
    int i = paramVarArgs.length;
    for (byte b = 0; b < i; b++)
      zzbp.zzb(paramVarArgs[b], "Requested API must not be null."); 
    ArrayList<GoogleApi<?>> arrayList = new ArrayList(paramVarArgs.length + 1);
    arrayList.add(paramGoogleApi);
    arrayList.addAll(Arrays.asList(paramVarArgs));
    return zzbp.zzaho().zza(arrayList);
  }
  
  public Dialog getErrorDialog(Activity paramActivity, int paramInt1, int paramInt2) {
    return getErrorDialog(paramActivity, paramInt1, paramInt2, null);
  }
  
  public Dialog getErrorDialog(Activity paramActivity, int paramInt1, int paramInt2, DialogInterface.OnCancelListener paramOnCancelListener) {
    return zza((Context)paramActivity, paramInt1, zzu.zza(paramActivity, zze.zza((Context)paramActivity, paramInt1, "d"), paramInt2), paramOnCancelListener);
  }
  
  @Nullable
  public PendingIntent getErrorResolutionPendingIntent(Context paramContext, int paramInt1, int paramInt2) {
    return super.getErrorResolutionPendingIntent(paramContext, paramInt1, paramInt2);
  }
  
  @Nullable
  public PendingIntent getErrorResolutionPendingIntent(Context paramContext, ConnectionResult paramConnectionResult) {
    return paramConnectionResult.hasResolution() ? paramConnectionResult.getResolution() : super.getErrorResolutionPendingIntent(paramContext, paramConnectionResult.getErrorCode(), 0);
  }
  
  public final String getErrorString(int paramInt) {
    return super.getErrorString(paramInt);
  }
  
  public int isGooglePlayServicesAvailable(Context paramContext) {
    return super.isGooglePlayServicesAvailable(paramContext);
  }
  
  public final boolean isUserResolvableError(int paramInt) {
    return super.isUserResolvableError(paramInt);
  }
  
  @MainThread
  public Task<Void> makeGooglePlayServicesAvailable(Activity paramActivity) {
    zzbp.zzfy("makeGooglePlayServicesAvailable must be called from the main thread");
    int i = super.isGooglePlayServicesAvailable((Context)paramActivity);
    if (i == 0)
      return Tasks.forResult(null); 
    zzco zzco = zzco.zzp(paramActivity);
    zzco.zzb(new ConnectionResult(i, null), 0);
    return zzco.getTask();
  }
  
  public boolean showErrorDialogFragment(Activity paramActivity, int paramInt1, int paramInt2) {
    return showErrorDialogFragment(paramActivity, paramInt1, paramInt2, null);
  }
  
  public boolean showErrorDialogFragment(Activity paramActivity, int paramInt1, int paramInt2, DialogInterface.OnCancelListener paramOnCancelListener) {
    Dialog dialog = getErrorDialog(paramActivity, paramInt1, paramInt2, paramOnCancelListener);
    if (dialog == null)
      return false; 
    zza(paramActivity, dialog, "GooglePlayServicesErrorDialog", paramOnCancelListener);
    return true;
  }
  
  public void showErrorNotification(Context paramContext, int paramInt) {
    zza(paramContext, paramInt, (String)null, zza(paramContext, paramInt, 0, "n"));
  }
  
  public void showErrorNotification(Context paramContext, ConnectionResult paramConnectionResult) {
    PendingIntent pendingIntent = getErrorResolutionPendingIntent(paramContext, paramConnectionResult);
    zza(paramContext, paramConnectionResult.getErrorCode(), (String)null, pendingIntent);
  }
  
  public final boolean zza(Activity paramActivity, @NonNull zzcg paramzzcg, int paramInt1, int paramInt2, DialogInterface.OnCancelListener paramOnCancelListener) {
    Dialog dialog = zza((Context)paramActivity, paramInt1, zzu.zza(paramzzcg, zze.zza((Context)paramActivity, paramInt1, "d"), 2), paramOnCancelListener);
    if (dialog == null)
      return false; 
    zza(paramActivity, dialog, "GooglePlayServicesErrorDialog", paramOnCancelListener);
    return true;
  }
  
  public final boolean zza(Context paramContext, ConnectionResult paramConnectionResult, int paramInt) {
    PendingIntent pendingIntent = getErrorResolutionPendingIntent(paramContext, paramConnectionResult);
    if (pendingIntent != null) {
      zza(paramContext, paramConnectionResult.getErrorCode(), (String)null, GoogleApiActivity.zza(paramContext, pendingIntent, paramInt));
      return true;
    } 
    return false;
  }
  
  final void zzbt(Context paramContext) {
    (new zza(this, paramContext)).sendEmptyMessageDelayed(1, 120000L);
  }
  
  @SuppressLint({"HandlerLeak"})
  final class zza extends Handler {
    private final Context mApplicationContext;
    
    public zza(GoogleApiAvailability this$0, Context param1Context) {
      super(looper);
      Looper looper;
      this.mApplicationContext = param1Context.getApplicationContext();
    }
    
    public final void handleMessage(Message param1Message) {
      int i = param1Message.what;
      if (i != 1) {
        StringBuilder stringBuilder = new StringBuilder(50);
        stringBuilder.append("Don't know how to handle this message: ");
        stringBuilder.append(i);
        Log.w("GoogleApiAvailability", stringBuilder.toString());
      } else {
        i = this.zzffj.isGooglePlayServicesAvailable(this.mApplicationContext);
        if (this.zzffj.isUserResolvableError(i))
          this.zzffj.showErrorNotification(this.mApplicationContext, i); 
      } 
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/GoogleApiAvailability.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */