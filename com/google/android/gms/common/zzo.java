package com.google.android.gms.common;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.util.Log;
import com.google.android.gms.R;
import com.google.android.gms.common.util.zzi;
import com.google.android.gms.common.util.zzw;
import com.google.android.gms.internal.zzbed;
import java.util.concurrent.atomic.AtomicBoolean;

public class zzo {
  @Deprecated
  public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
  
  @Deprecated
  public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = 11400000;
  
  public static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";
  
  private static boolean zzffw = false;
  
  private static boolean zzffx = false;
  
  private static boolean zzffy = false;
  
  private static boolean zzffz = false;
  
  static final AtomicBoolean zzfga = new AtomicBoolean();
  
  private static final AtomicBoolean zzfgb = new AtomicBoolean();
  
  @Deprecated
  public static PendingIntent getErrorPendingIntent(int paramInt1, Context paramContext, int paramInt2) {
    return zze.zzaex().getErrorResolutionPendingIntent(paramContext, paramInt1, paramInt2);
  }
  
  @Deprecated
  public static String getErrorString(int paramInt) {
    return ConnectionResult.getStatusString(paramInt);
  }
  
  public static Context getRemoteContext(Context paramContext) {
    try {
      return paramContext.createPackageContext("com.google.android.gms", 3);
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      return null;
    } 
  }
  
  public static Resources getRemoteResource(Context paramContext) {
    try {
      return paramContext.getPackageManager().getResourcesForApplication("com.google.android.gms");
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      return null;
    } 
  }
  
  @Deprecated
  public static int isGooglePlayServicesAvailable(Context paramContext) {
    StringBuilder stringBuilder;
    String str;
    int i;
    PackageManager packageManager = paramContext.getPackageManager();
    try {
      paramContext.getResources().getString(R.string.common_google_play_services_unknown_issue);
    } finally {
      Exception exception = null;
    } 
    if (!zzi.zzcj((Context)stringBuilder) && !zzi.zzcl((Context)stringBuilder)) {
      i = 1;
    } else {
      i = 0;
    } 
    PackageInfo packageInfo = null;
    if (i)
      try {
        packageInfo = packageManager.getPackageInfo("com.android.vending", 8256);
      } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
        str = "Google Play Store is missing.";
        Log.w("GooglePlayServicesUtil", str);
        return 9;
      }  
    try {
      PackageInfo packageInfo1 = packageManager.getPackageInfo("com.google.android.gms", 64);
      zzp.zzbz((Context)str);
      if (i) {
        String str1;
        zzg zzg = zzp.zza(packageInfo, zzj.zzffs);
        if (zzg == null) {
          str1 = "Google Play Store signature invalid.";
          Log.w("GooglePlayServicesUtil", str1);
          return 9;
        } 
        if (zzp.zza(packageInfo1, new zzg[] { (zzg)str1 }) == null) {
          Log.w("GooglePlayServicesUtil", "Google Play services signature invalid.");
          return 9;
        } 
      } else if (zzp.zza(packageInfo1, zzj.zzffs) == null) {
        Log.w("GooglePlayServicesUtil", "Google Play services signature invalid.");
        return 9;
      } 
      int k = GOOGLE_PLAY_SERVICES_VERSION_CODE;
      int j = k / 1000;
      i = packageInfo1.versionCode;
      if (i / 1000 < j) {
        StringBuilder stringBuilder1 = new StringBuilder(77);
        stringBuilder1.append("Google Play services out of date.  Requires ");
        stringBuilder1.append(k);
        stringBuilder1.append(" but found ");
        stringBuilder1.append(i);
        Log.w("GooglePlayServicesUtil", stringBuilder1.toString());
        return 2;
      } 
      ApplicationInfo applicationInfo2 = packageInfo1.applicationInfo;
      ApplicationInfo applicationInfo1 = applicationInfo2;
      if (applicationInfo2 == null)
        try {
          applicationInfo1 = packageManager.getApplicationInfo("com.google.android.gms", 0);
        } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
          Log.wtf("GooglePlayServicesUtil", "Google Play services missing when getting application info.", (Throwable)nameNotFoundException);
          return 1;
        }  
      return !((ApplicationInfo)nameNotFoundException).enabled ? 3 : 0;
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
      return 1;
    } 
  }
  
  @Deprecated
  public static boolean isUserRecoverableError(int paramInt) {
    return !(paramInt != 1 && paramInt != 2 && paramInt != 3 && paramInt != 9);
  }
  
  @Deprecated
  @TargetApi(19)
  public static boolean zzb(Context paramContext, int paramInt, String paramString) {
    return zzw.zzb(paramContext, paramInt, paramString);
  }
  
  @Deprecated
  public static void zzbj(Context paramContext) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
    int i = zze.zzaex().isGooglePlayServicesAvailable(paramContext);
    if (i != 0) {
      zze.zzaex();
      Intent intent = zze.zza(paramContext, i, "e");
      StringBuilder stringBuilder = new StringBuilder(57);
      stringBuilder.append("GooglePlayServices not available due to error ");
      stringBuilder.append(i);
      Log.e("GooglePlayServicesUtil", stringBuilder.toString());
      if (intent == null)
        throw new GooglePlayServicesNotAvailableException(i); 
      throw new GooglePlayServicesRepairableException(i, "Google Play Services not available", intent);
    } 
  }
  
  @Deprecated
  public static void zzbv(Context paramContext) {
    if (zzfga.getAndSet(true))
      return; 
    try {
      NotificationManager notificationManager = (NotificationManager)paramContext.getSystemService("notification");
      if (notificationManager != null)
        notificationManager.cancel(10436); 
    } catch (SecurityException securityException) {}
  }
  
  @Deprecated
  public static int zzbw(Context paramContext) {
    try {
      PackageInfo packageInfo = paramContext.getPackageManager().getPackageInfo("com.google.android.gms", 0);
      return packageInfo.versionCode;
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
      return 0;
    } 
  }
  
  public static boolean zzby(Context paramContext) {
    if (!zzffz) {
      try {
        PackageInfo packageInfo = zzbed.zzcr(paramContext).getPackageInfo("com.google.android.gms", 64);
        zzp.zzbz(paramContext);
        if (packageInfo != null && zzp.zza(packageInfo, new zzg[] { zzj.zzffs[1] }) != null) {
          zzffy = true;
        } else {
          zzffy = false;
        } 
      } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
        Log.w("GooglePlayServicesUtil", "Cannot find Google Play services package name.", (Throwable)nameNotFoundException);
      } finally {}
      zzffz = true;
    } 
    return (zzffy || !"user".equals(Build.TYPE));
  }
  
  @Deprecated
  public static boolean zze(Context paramContext, int paramInt) {
    return (paramInt == 18) ? true : ((paramInt == 1) ? zzx(paramContext, "com.google.android.gms") : false);
  }
  
  @Deprecated
  public static boolean zzf(Context paramContext, int paramInt) {
    return zzw.zzf(paramContext, paramInt);
  }
  
  @TargetApi(21)
  static boolean zzx(Context paramContext, String paramString) {
    // Byte code:
    //   0: aload_1
    //   1: ldc 'com.google.android.gms'
    //   3: invokevirtual equals : (Ljava/lang/Object;)Z
    //   6: istore_2
    //   7: invokestatic zzalj : ()Z
    //   10: ifeq -> 64
    //   13: aload_0
    //   14: invokevirtual getPackageManager : ()Landroid/content/pm/PackageManager;
    //   17: invokevirtual getPackageInstaller : ()Landroid/content/pm/PackageInstaller;
    //   20: invokevirtual getAllSessions : ()Ljava/util/List;
    //   23: astore_3
    //   24: aload_3
    //   25: invokeinterface iterator : ()Ljava/util/Iterator;
    //   30: astore_3
    //   31: aload_3
    //   32: invokeinterface hasNext : ()Z
    //   37: ifeq -> 64
    //   40: aload_1
    //   41: aload_3
    //   42: invokeinterface next : ()Ljava/lang/Object;
    //   47: checkcast android/content/pm/PackageInstaller$SessionInfo
    //   50: invokevirtual getAppPackageName : ()Ljava/lang/String;
    //   53: invokevirtual equals : (Ljava/lang/Object;)Z
    //   56: ifeq -> 31
    //   59: iconst_1
    //   60: ireturn
    //   61: astore_0
    //   62: iconst_0
    //   63: ireturn
    //   64: aload_0
    //   65: invokevirtual getPackageManager : ()Landroid/content/pm/PackageManager;
    //   68: astore_3
    //   69: aload_3
    //   70: aload_1
    //   71: sipush #8192
    //   74: invokevirtual getApplicationInfo : (Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   77: astore_1
    //   78: iload_2
    //   79: ifeq -> 87
    //   82: aload_1
    //   83: getfield enabled : Z
    //   86: ireturn
    //   87: aload_1
    //   88: getfield enabled : Z
    //   91: ifeq -> 156
    //   94: invokestatic zzalg : ()Z
    //   97: ifeq -> 146
    //   100: aload_0
    //   101: ldc_w 'user'
    //   104: invokevirtual getSystemService : (Ljava/lang/String;)Ljava/lang/Object;
    //   107: checkcast android/os/UserManager
    //   110: aload_0
    //   111: invokevirtual getPackageName : ()Ljava/lang/String;
    //   114: invokevirtual getApplicationRestrictions : (Ljava/lang/String;)Landroid/os/Bundle;
    //   117: astore_0
    //   118: aload_0
    //   119: ifnull -> 146
    //   122: ldc_w 'true'
    //   125: aload_0
    //   126: ldc_w 'restricted_profile'
    //   129: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   132: invokevirtual equals : (Ljava/lang/Object;)Z
    //   135: istore_2
    //   136: iload_2
    //   137: ifeq -> 146
    //   140: iconst_1
    //   141: istore #4
    //   143: goto -> 149
    //   146: iconst_0
    //   147: istore #4
    //   149: iload #4
    //   151: ifne -> 156
    //   154: iconst_1
    //   155: ireturn
    //   156: iconst_0
    //   157: ireturn
    //   158: astore_0
    //   159: goto -> 156
    // Exception table:
    //   from	to	target	type
    //   13	24	61	java/lang/Exception
    //   69	78	158	android/content/pm/PackageManager$NameNotFoundException
    //   82	87	158	android/content/pm/PackageManager$NameNotFoundException
    //   87	118	158	android/content/pm/PackageManager$NameNotFoundException
    //   122	136	158	android/content/pm/PackageManager$NameNotFoundException
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/zzo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */