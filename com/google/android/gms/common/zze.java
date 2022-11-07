package com.google.android.gms.common;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzaj;
import com.google.android.gms.common.util.zzi;
import com.google.android.gms.internal.zzbed;

public class zze {
  public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
  
  public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = zzo.GOOGLE_PLAY_SERVICES_VERSION_CODE;
  
  private static final zze zzffk = new zze();
  
  @Nullable
  public static Intent zza(Context paramContext, int paramInt, @Nullable String paramString) {
    return (paramInt != 1 && paramInt != 2) ? ((paramInt != 3) ? null : zzaj.zzge("com.google.android.gms")) : ((paramContext != null && zzi.zzcj(paramContext)) ? zzaj.zzakj() : zzaj.zzu("com.google.android.gms", zzw(paramContext, paramString)));
  }
  
  public static zze zzaex() {
    return zzffk;
  }
  
  public static void zzbu(Context paramContext) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
    zzo.zzbj(paramContext);
  }
  
  public static void zzbv(Context paramContext) {
    zzo.zzbv(paramContext);
  }
  
  public static int zzbw(Context paramContext) {
    return zzo.zzbw(paramContext);
  }
  
  public static boolean zze(Context paramContext, int paramInt) {
    return zzo.zze(paramContext, paramInt);
  }
  
  private static String zzw(@Nullable Context paramContext, @Nullable String paramString) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("gcore_");
    stringBuilder.append(GOOGLE_PLAY_SERVICES_VERSION_CODE);
    stringBuilder.append("-");
    if (!TextUtils.isEmpty(paramString))
      stringBuilder.append(paramString); 
    stringBuilder.append("-");
    if (paramContext != null)
      stringBuilder.append(paramContext.getPackageName()); 
    stringBuilder.append("-");
    if (paramContext != null)
      try {
        stringBuilder.append((zzbed.zzcr(paramContext).getPackageInfo(paramContext.getPackageName(), 0)).versionCode);
      } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {} 
    return stringBuilder.toString();
  }
  
  @Nullable
  public PendingIntent getErrorResolutionPendingIntent(Context paramContext, int paramInt1, int paramInt2) {
    return zza(paramContext, paramInt1, paramInt2, null);
  }
  
  public String getErrorString(int paramInt) {
    return zzo.getErrorString(paramInt);
  }
  
  public int isGooglePlayServicesAvailable(Context paramContext) {
    int i = zzo.isGooglePlayServicesAvailable(paramContext);
    int j = i;
    if (zzo.zze(paramContext, i))
      j = 18; 
    return j;
  }
  
  public boolean isUserResolvableError(int paramInt) {
    return zzo.isUserRecoverableError(paramInt);
  }
  
  @Nullable
  public final PendingIntent zza(Context paramContext, int paramInt1, int paramInt2, @Nullable String paramString) {
    Intent intent = zza(paramContext, paramInt1, paramString);
    return (intent == null) ? null : PendingIntent.getActivity(paramContext, paramInt2, intent, 268435456);
  }
  
  @Deprecated
  @Nullable
  public final Intent zzbn(int paramInt) {
    return zza(null, paramInt, null);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/zze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */