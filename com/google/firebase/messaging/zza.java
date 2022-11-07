package com.google.firebase.messaging;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.R;
import com.google.android.gms.common.util.zzq;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.MissingFormatArgumentException;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;

final class zza {
  private static zza zzngg;
  
  private final Context mContext;
  
  private Bundle zzfqo;
  
  private Method zzhqs;
  
  private Method zzhqt;
  
  private final AtomicInteger zzngh = new AtomicInteger((int)SystemClock.elapsedRealtime());
  
  private zza(Context paramContext) {
    this.mContext = paramContext.getApplicationContext();
  }
  
  @TargetApi(26)
  private final Notification zza(CharSequence paramCharSequence, String paramString1, int paramInt, Integer paramInteger, Uri paramUri, PendingIntent paramPendingIntent1, PendingIntent paramPendingIntent2, String paramString2) {
    Notification.Builder builder = (new Notification.Builder(this.mContext)).setAutoCancel(true).setSmallIcon(paramInt);
    if (!TextUtils.isEmpty(paramCharSequence))
      builder.setContentTitle(paramCharSequence); 
    if (!TextUtils.isEmpty(paramString1)) {
      builder.setContentText(paramString1);
      builder.setStyle((Notification.Style)(new Notification.BigTextStyle()).bigText(paramString1));
    } 
    if (paramInteger != null)
      builder.setColor(paramInteger.intValue()); 
    if (paramUri != null)
      builder.setSound(paramUri); 
    if (paramPendingIntent1 != null)
      builder.setContentIntent(paramPendingIntent1); 
    if (paramPendingIntent2 != null)
      builder.setDeleteIntent(paramPendingIntent2); 
    if (paramString2 != null) {
      if (this.zzhqs == null)
        this.zzhqs = zzhq("setChannelId"); 
      if (this.zzhqs == null)
        this.zzhqs = zzhq("setChannel"); 
      Method method = this.zzhqs;
      if (method == null) {
        Log.e("FirebaseMessaging", "Error while setting the notification channel");
      } else {
        try {
          method.invoke(builder, new Object[] { paramString2 });
        } catch (IllegalAccessException illegalAccessException) {
          Log.e("FirebaseMessaging", "Error while setting the notification channel", illegalAccessException);
        } catch (InvocationTargetException invocationTargetException) {
        
        } catch (SecurityException securityException) {
        
        } catch (IllegalArgumentException illegalArgumentException) {}
      } 
    } 
    return builder.build();
  }
  
  private static void zza(Intent paramIntent, Bundle paramBundle) {
    for (String str : paramBundle.keySet()) {
      if (str.startsWith("google.c.a.") || str.equals("from"))
        paramIntent.putExtra(str, paramBundle.getString(str)); 
    } 
  }
  
  static boolean zzad(Bundle paramBundle) {
    return ("1".equals(zze(paramBundle, "gcm.n.e")) || zze(paramBundle, "gcm.n.icon") != null);
  }
  
  @Nullable
  static Uri zzae(@NonNull Bundle paramBundle) {
    String str1 = zze(paramBundle, "gcm.n.link_android");
    String str2 = str1;
    if (TextUtils.isEmpty(str1))
      str2 = zze(paramBundle, "gcm.n.link"); 
    return !TextUtils.isEmpty(str2) ? Uri.parse(str2) : null;
  }
  
  static String zzaf(Bundle paramBundle) {
    String str1 = zze(paramBundle, "gcm.n.sound2");
    String str2 = str1;
    if (TextUtils.isEmpty(str1))
      str2 = zze(paramBundle, "gcm.n.sound"); 
    return str2;
  }
  
  private final Bundle zzash() {
    ApplicationInfo applicationInfo;
    Bundle bundle = this.zzfqo;
    if (bundle != null)
      return bundle; 
    bundle = null;
    try {
      ApplicationInfo applicationInfo1 = this.mContext.getPackageManager().getApplicationInfo(this.mContext.getPackageName(), 128);
      applicationInfo = applicationInfo1;
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {}
    if (applicationInfo != null) {
      Bundle bundle1 = applicationInfo.metaData;
      if (bundle1 != null) {
        this.zzfqo = bundle1;
        return bundle1;
      } 
    } 
    return Bundle.EMPTY;
  }
  
  static String zze(Bundle paramBundle, String paramString) {
    String str1 = paramBundle.getString(paramString);
    String str2 = str1;
    if (str1 == null)
      str2 = paramBundle.getString(paramString.replace("gcm.n.", "gcm.notification.")); 
    return str2;
  }
  
  static zza zzeq(Context paramContext) {
    // Byte code:
    //   0: ldc com/google/firebase/messaging/zza
    //   2: monitorenter
    //   3: getstatic com/google/firebase/messaging/zza.zzngg : Lcom/google/firebase/messaging/zza;
    //   6: ifnonnull -> 22
    //   9: new com/google/firebase/messaging/zza
    //   12: astore_1
    //   13: aload_1
    //   14: aload_0
    //   15: invokespecial <init> : (Landroid/content/Context;)V
    //   18: aload_1
    //   19: putstatic com/google/firebase/messaging/zza.zzngg : Lcom/google/firebase/messaging/zza;
    //   22: getstatic com/google/firebase/messaging/zza.zzngg : Lcom/google/firebase/messaging/zza;
    //   25: astore_0
    //   26: ldc com/google/firebase/messaging/zza
    //   28: monitorexit
    //   29: aload_0
    //   30: areturn
    //   31: astore_0
    //   32: ldc com/google/firebase/messaging/zza
    //   34: monitorexit
    //   35: aload_0
    //   36: athrow
    // Exception table:
    //   from	to	target	type
    //   3	22	31	finally
    //   22	26	31	finally
  }
  
  static String zzh(Bundle paramBundle, String paramString) {
    paramString = String.valueOf(paramString);
    if ("_loc_key".length() != 0) {
      paramString = paramString.concat("_loc_key");
    } else {
      paramString = new String(paramString);
    } 
    return zze(paramBundle, paramString);
  }
  
  @TargetApi(26)
  private static Method zzhq(String paramString) {
    try {
      return Notification.Builder.class.getMethod(paramString, new Class[] { String.class });
    } catch (NoSuchMethodException|SecurityException noSuchMethodException) {
      return null;
    } 
  }
  
  static Object[] zzi(Bundle paramBundle, String paramString) {
    String str = String.valueOf(paramString);
    if ("_loc_args".length() != 0) {
      str = str.concat("_loc_args");
    } else {
      str = new String(str);
    } 
    str = zze(paramBundle, str);
    if (TextUtils.isEmpty(str))
      return null; 
    try {
      JSONArray jSONArray = new JSONArray();
      this(str);
      int i = jSONArray.length();
      String[] arrayOfString = new String[i];
      for (byte b = 0; b < i; b++)
        arrayOfString[b] = (String)jSONArray.opt(b); 
      return (Object[])arrayOfString;
    } catch (JSONException jSONException) {
      String str1 = String.valueOf(paramString);
      if ("_loc_args".length() != 0) {
        str1 = str1.concat("_loc_args");
      } else {
        str1 = new String(str1);
      } 
      str1 = str1.substring(6);
      StringBuilder stringBuilder = new StringBuilder(String.valueOf(str1).length() + 41 + String.valueOf(str).length());
      stringBuilder.append("Malformed ");
      stringBuilder.append(str1);
      stringBuilder.append(": ");
      stringBuilder.append(str);
      stringBuilder.append("  Default value will be used.");
      Log.w("FirebaseMessaging", stringBuilder.toString());
      return null;
    } 
  }
  
  private final String zzj(Bundle paramBundle, String paramString) {
    String str1;
    StringBuilder stringBuilder;
    String str2 = zze(paramBundle, paramString);
    if (!TextUtils.isEmpty(str2))
      return str2; 
    str2 = zzh(paramBundle, paramString);
    if (TextUtils.isEmpty(str2))
      return null; 
    Resources resources = this.mContext.getResources();
    int i = resources.getIdentifier(str2, "string", this.mContext.getPackageName());
    if (i == 0) {
      str1 = String.valueOf(paramString);
      if ("_loc_key".length() != 0) {
        str1 = str1.concat("_loc_key");
      } else {
        str1 = new String(str1);
      } 
      str1 = str1.substring(6);
      stringBuilder = new StringBuilder(String.valueOf(str1).length() + 49 + String.valueOf(str2).length());
      stringBuilder.append(str1);
      stringBuilder.append(" resource not found: ");
      stringBuilder.append(str2);
      stringBuilder.append(" Default value will be used.");
      Log.w("FirebaseMessaging", stringBuilder.toString());
      return null;
    } 
    Object[] arrayOfObject = zzi((Bundle)str1, (String)stringBuilder);
    if (arrayOfObject == null)
      return resources.getString(i); 
    try {
      return resources.getString(i, arrayOfObject);
    } catch (MissingFormatArgumentException missingFormatArgumentException) {
      String str = Arrays.toString(arrayOfObject);
      StringBuilder stringBuilder1 = new StringBuilder(String.valueOf(str2).length() + 58 + String.valueOf(str).length());
      stringBuilder1.append("Missing format argument for ");
      stringBuilder1.append(str2);
      stringBuilder1.append(": ");
      stringBuilder1.append(str);
      stringBuilder1.append(" Default value will be used.");
      Log.w("FirebaseMessaging", stringBuilder1.toString(), missingFormatArgumentException);
      return null;
    } 
  }
  
  private final Integer zzri(String paramString) {
    if (Build.VERSION.SDK_INT < 21)
      return null; 
    if (!TextUtils.isEmpty(paramString))
      try {
        int j = Color.parseColor(paramString);
        return Integer.valueOf(j);
      } catch (IllegalArgumentException illegalArgumentException) {
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(paramString).length() + 54);
        stringBuilder.append("Color ");
        stringBuilder.append(paramString);
        stringBuilder.append(" not valid. Notification will use default color.");
        Log.w("FirebaseMessaging", stringBuilder.toString());
      }  
    int i = zzash().getInt("com.google.firebase.messaging.default_notification_color", 0);
    if (i != 0)
      try {
        i = ContextCompat.getColor(this.mContext, i);
        return Integer.valueOf(i);
      } catch (android.content.res.Resources.NotFoundException notFoundException) {
        Log.w("FirebaseMessaging", "Cannot find the color resource referenced in AndroidManifest.");
      }  
    return null;
  }
  
  @TargetApi(26)
  private final String zzrj(String paramString) {
    if (!zzq.isAtLeastO())
      return null; 
    NotificationManager notificationManager = (NotificationManager)this.mContext.getSystemService(NotificationManager.class);
    try {
      if (this.zzhqt == null)
        this.zzhqt = notificationManager.getClass().getMethod("getNotificationChannel", new Class[] { String.class }); 
      if (!TextUtils.isEmpty(paramString)) {
        if (this.zzhqt.invoke(notificationManager, new Object[] { paramString }) != null)
          return paramString; 
        int i = String.valueOf(paramString).length();
        StringBuilder stringBuilder = new StringBuilder();
        this(i + 122);
        stringBuilder.append("Notification Channel requested (");
        stringBuilder.append(paramString);
        stringBuilder.append(") has not been created by the app. Manifest configuration, or default, value will be used.");
        Log.w("FirebaseMessaging", stringBuilder.toString());
      } 
      paramString = zzash().getString("com.google.firebase.messaging.default_notification_channel_id");
      if (!TextUtils.isEmpty(paramString)) {
        if (this.zzhqt.invoke(notificationManager, new Object[] { paramString }) != null)
          return paramString; 
        paramString = "Notification Channel set in AndroidManifest.xml has not been created by the app. Default value will be used.";
      } else {
        paramString = "Missing Default Notification Channel metadata in AndroidManifest. Default value will be used.";
      } 
      Log.w("FirebaseMessaging", paramString);
      if (this.zzhqt.invoke(notificationManager, new Object[] { "fcm_fallback_notification_channel" }) == null) {
        Class<?> clazz = Class.forName("android.app.NotificationChannel");
        Object object = clazz.getConstructor(new Class[] { String.class, CharSequence.class, int.class }).newInstance(new Object[] { "fcm_fallback_notification_channel", this.mContext.getString(R.string.fcm_fallback_notification_channel_label), Integer.valueOf(3) });
        notificationManager.getClass().getMethod("createNotificationChannel", new Class[] { clazz }).invoke(notificationManager, new Object[] { object });
      } 
      return "fcm_fallback_notification_channel";
    } catch (InstantiationException instantiationException) {
    
    } catch (InvocationTargetException invocationTargetException) {
    
    } catch (NoSuchMethodException noSuchMethodException) {
    
    } catch (IllegalAccessException illegalAccessException) {
    
    } catch (ClassNotFoundException classNotFoundException) {
    
    } catch (SecurityException securityException) {
    
    } catch (IllegalArgumentException illegalArgumentException) {
    
    } catch (LinkageError linkageError) {}
    Log.e("FirebaseMessaging", "Error while setting the notification channel", linkageError);
    return null;
  }
  
  private final PendingIntent zzt(Bundle paramBundle) {
    Intent intent;
    String str = zze(paramBundle, "gcm.n.click_action");
    if (!TextUtils.isEmpty(str)) {
      intent = new Intent(str);
      intent.setPackage(this.mContext.getPackageName());
      intent.setFlags(268435456);
    } else {
      Uri uri = zzae(paramBundle);
      if (uri != null) {
        intent = new Intent("android.intent.action.VIEW");
        intent.setPackage(this.mContext.getPackageName());
        intent.setData(uri);
      } else {
        Intent intent1 = this.mContext.getPackageManager().getLaunchIntentForPackage(this.mContext.getPackageName());
        intent = intent1;
        if (intent1 == null) {
          Log.w("FirebaseMessaging", "No activity found to launch app");
          intent = intent1;
        } 
      } 
    } 
    if (intent == null)
      return null; 
    intent.addFlags(67108864);
    paramBundle = new Bundle(paramBundle);
    FirebaseMessagingService.zzq(paramBundle);
    intent.putExtras(paramBundle);
    for (String str1 : paramBundle.keySet()) {
      if (str1.startsWith("gcm.n.") || str1.startsWith("gcm.notification."))
        intent.removeExtra(str1); 
    } 
    return PendingIntent.getActivity(this.mContext, this.zzngh.incrementAndGet(), intent, 1073741824);
  }
  
  final boolean zzs(Bundle paramBundle) {
    // Byte code:
    //   0: ldc '1'
    //   2: aload_1
    //   3: ldc_w 'gcm.n.noui'
    //   6: invokestatic zze : (Landroid/os/Bundle;Ljava/lang/String;)Ljava/lang/String;
    //   9: invokevirtual equals : (Ljava/lang/Object;)Z
    //   12: ifeq -> 17
    //   15: iconst_1
    //   16: ireturn
    //   17: aload_0
    //   18: getfield mContext : Landroid/content/Context;
    //   21: ldc_w 'keyguard'
    //   24: invokevirtual getSystemService : (Ljava/lang/String;)Ljava/lang/Object;
    //   27: checkcast android/app/KeyguardManager
    //   30: invokevirtual inKeyguardRestrictedInputMode : ()Z
    //   33: ifne -> 124
    //   36: invokestatic zzalj : ()Z
    //   39: ifne -> 48
    //   42: ldc2_w 10
    //   45: invokestatic sleep : (J)V
    //   48: invokestatic myPid : ()I
    //   51: istore_2
    //   52: aload_0
    //   53: getfield mContext : Landroid/content/Context;
    //   56: ldc_w 'activity'
    //   59: invokevirtual getSystemService : (Ljava/lang/String;)Ljava/lang/Object;
    //   62: checkcast android/app/ActivityManager
    //   65: invokevirtual getRunningAppProcesses : ()Ljava/util/List;
    //   68: astore_3
    //   69: aload_3
    //   70: ifnull -> 124
    //   73: aload_3
    //   74: invokeinterface iterator : ()Ljava/util/Iterator;
    //   79: astore_3
    //   80: aload_3
    //   81: invokeinterface hasNext : ()Z
    //   86: ifeq -> 124
    //   89: aload_3
    //   90: invokeinterface next : ()Ljava/lang/Object;
    //   95: checkcast android/app/ActivityManager$RunningAppProcessInfo
    //   98: astore #4
    //   100: aload #4
    //   102: getfield pid : I
    //   105: iload_2
    //   106: if_icmpne -> 80
    //   109: aload #4
    //   111: getfield importance : I
    //   114: bipush #100
    //   116: if_icmpne -> 124
    //   119: iconst_1
    //   120: istore_2
    //   121: goto -> 126
    //   124: iconst_0
    //   125: istore_2
    //   126: iload_2
    //   127: ifeq -> 132
    //   130: iconst_0
    //   131: ireturn
    //   132: aload_0
    //   133: aload_1
    //   134: ldc_w 'gcm.n.title'
    //   137: invokespecial zzj : (Landroid/os/Bundle;Ljava/lang/String;)Ljava/lang/String;
    //   140: astore_3
    //   141: aload_3
    //   142: astore #4
    //   144: aload_3
    //   145: invokestatic isEmpty : (Ljava/lang/CharSequence;)Z
    //   148: ifeq -> 170
    //   151: aload_0
    //   152: getfield mContext : Landroid/content/Context;
    //   155: invokevirtual getApplicationInfo : ()Landroid/content/pm/ApplicationInfo;
    //   158: aload_0
    //   159: getfield mContext : Landroid/content/Context;
    //   162: invokevirtual getPackageManager : ()Landroid/content/pm/PackageManager;
    //   165: invokevirtual loadLabel : (Landroid/content/pm/PackageManager;)Ljava/lang/CharSequence;
    //   168: astore #4
    //   170: aload_0
    //   171: aload_1
    //   172: ldc_w 'gcm.n.body'
    //   175: invokespecial zzj : (Landroid/os/Bundle;Ljava/lang/String;)Ljava/lang/String;
    //   178: astore #5
    //   180: aload_1
    //   181: ldc 'gcm.n.icon'
    //   183: invokestatic zze : (Landroid/os/Bundle;Ljava/lang/String;)Ljava/lang/String;
    //   186: astore_3
    //   187: aload_3
    //   188: invokestatic isEmpty : (Ljava/lang/CharSequence;)Z
    //   191: ifne -> 306
    //   194: aload_0
    //   195: getfield mContext : Landroid/content/Context;
    //   198: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   201: astore #6
    //   203: aload #6
    //   205: aload_3
    //   206: ldc_w 'drawable'
    //   209: aload_0
    //   210: getfield mContext : Landroid/content/Context;
    //   213: invokevirtual getPackageName : ()Ljava/lang/String;
    //   216: invokevirtual getIdentifier : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
    //   219: istore_2
    //   220: iload_2
    //   221: ifeq -> 227
    //   224: goto -> 353
    //   227: aload #6
    //   229: aload_3
    //   230: ldc_w 'mipmap'
    //   233: aload_0
    //   234: getfield mContext : Landroid/content/Context;
    //   237: invokevirtual getPackageName : ()Ljava/lang/String;
    //   240: invokevirtual getIdentifier : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
    //   243: istore_2
    //   244: iload_2
    //   245: ifeq -> 251
    //   248: goto -> 353
    //   251: new java/lang/StringBuilder
    //   254: dup
    //   255: aload_3
    //   256: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   259: invokevirtual length : ()I
    //   262: bipush #61
    //   264: iadd
    //   265: invokespecial <init> : (I)V
    //   268: astore #6
    //   270: aload #6
    //   272: ldc_w 'Icon resource '
    //   275: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   278: pop
    //   279: aload #6
    //   281: aload_3
    //   282: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   285: pop
    //   286: aload #6
    //   288: ldc_w ' not found. Notification will use default icon.'
    //   291: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   294: pop
    //   295: ldc 'FirebaseMessaging'
    //   297: aload #6
    //   299: invokevirtual toString : ()Ljava/lang/String;
    //   302: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   305: pop
    //   306: aload_0
    //   307: invokespecial zzash : ()Landroid/os/Bundle;
    //   310: ldc_w 'com.google.firebase.messaging.default_notification_icon'
    //   313: iconst_0
    //   314: invokevirtual getInt : (Ljava/lang/String;I)I
    //   317: istore #7
    //   319: iload #7
    //   321: istore_2
    //   322: iload #7
    //   324: ifne -> 338
    //   327: aload_0
    //   328: getfield mContext : Landroid/content/Context;
    //   331: invokevirtual getApplicationInfo : ()Landroid/content/pm/ApplicationInfo;
    //   334: getfield icon : I
    //   337: istore_2
    //   338: iload_2
    //   339: istore #7
    //   341: iload_2
    //   342: ifne -> 350
    //   345: ldc_w 17301651
    //   348: istore #7
    //   350: iload #7
    //   352: istore_2
    //   353: aload_0
    //   354: aload_1
    //   355: ldc_w 'gcm.n.color'
    //   358: invokestatic zze : (Landroid/os/Bundle;Ljava/lang/String;)Ljava/lang/String;
    //   361: invokespecial zzri : (Ljava/lang/String;)Ljava/lang/Integer;
    //   364: astore #8
    //   366: aload_1
    //   367: invokestatic zzaf : (Landroid/os/Bundle;)Ljava/lang/String;
    //   370: astore #9
    //   372: aload #9
    //   374: invokestatic isEmpty : (Ljava/lang/CharSequence;)Z
    //   377: istore #10
    //   379: aconst_null
    //   380: astore #6
    //   382: iload #10
    //   384: ifeq -> 392
    //   387: aconst_null
    //   388: astore_3
    //   389: goto -> 520
    //   392: ldc_w 'default'
    //   395: aload #9
    //   397: invokevirtual equals : (Ljava/lang/Object;)Z
    //   400: ifne -> 515
    //   403: aload_0
    //   404: getfield mContext : Landroid/content/Context;
    //   407: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   410: aload #9
    //   412: ldc_w 'raw'
    //   415: aload_0
    //   416: getfield mContext : Landroid/content/Context;
    //   419: invokevirtual getPackageName : ()Ljava/lang/String;
    //   422: invokevirtual getIdentifier : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
    //   425: ifeq -> 515
    //   428: aload_0
    //   429: getfield mContext : Landroid/content/Context;
    //   432: invokevirtual getPackageName : ()Ljava/lang/String;
    //   435: astore_3
    //   436: new java/lang/StringBuilder
    //   439: dup
    //   440: ldc_w 'android.resource://'
    //   443: invokevirtual length : ()I
    //   446: iconst_5
    //   447: iadd
    //   448: aload_3
    //   449: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   452: invokevirtual length : ()I
    //   455: iadd
    //   456: aload #9
    //   458: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   461: invokevirtual length : ()I
    //   464: iadd
    //   465: invokespecial <init> : (I)V
    //   468: astore #11
    //   470: aload #11
    //   472: ldc_w 'android.resource://'
    //   475: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   478: pop
    //   479: aload #11
    //   481: aload_3
    //   482: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   485: pop
    //   486: aload #11
    //   488: ldc_w '/raw/'
    //   491: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   494: pop
    //   495: aload #11
    //   497: aload #9
    //   499: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   502: pop
    //   503: aload #11
    //   505: invokevirtual toString : ()Ljava/lang/String;
    //   508: invokestatic parse : (Ljava/lang/String;)Landroid/net/Uri;
    //   511: astore_3
    //   512: goto -> 520
    //   515: iconst_2
    //   516: invokestatic getDefaultUri : (I)Landroid/net/Uri;
    //   519: astore_3
    //   520: aload_0
    //   521: aload_1
    //   522: invokespecial zzt : (Landroid/os/Bundle;)Landroid/app/PendingIntent;
    //   525: astore #9
    //   527: aload #9
    //   529: astore #11
    //   531: aload_1
    //   532: invokestatic zzag : (Landroid/os/Bundle;)Z
    //   535: ifeq -> 627
    //   538: new android/content/Intent
    //   541: dup
    //   542: ldc_w 'com.google.firebase.messaging.NOTIFICATION_OPEN'
    //   545: invokespecial <init> : (Ljava/lang/String;)V
    //   548: astore #6
    //   550: aload #6
    //   552: aload_1
    //   553: invokestatic zza : (Landroid/content/Intent;Landroid/os/Bundle;)V
    //   556: aload #6
    //   558: ldc_w 'pending_intent'
    //   561: aload #9
    //   563: invokevirtual putExtra : (Ljava/lang/String;Landroid/os/Parcelable;)Landroid/content/Intent;
    //   566: pop
    //   567: aload_0
    //   568: getfield mContext : Landroid/content/Context;
    //   571: aload_0
    //   572: getfield zzngh : Ljava/util/concurrent/atomic/AtomicInteger;
    //   575: invokevirtual incrementAndGet : ()I
    //   578: aload #6
    //   580: ldc_w 1073741824
    //   583: invokestatic zzb : (Landroid/content/Context;ILandroid/content/Intent;I)Landroid/app/PendingIntent;
    //   586: astore #11
    //   588: new android/content/Intent
    //   591: dup
    //   592: ldc_w 'com.google.firebase.messaging.NOTIFICATION_DISMISS'
    //   595: invokespecial <init> : (Ljava/lang/String;)V
    //   598: astore #6
    //   600: aload #6
    //   602: aload_1
    //   603: invokestatic zza : (Landroid/content/Intent;Landroid/os/Bundle;)V
    //   606: aload_0
    //   607: getfield mContext : Landroid/content/Context;
    //   610: aload_0
    //   611: getfield zzngh : Ljava/util/concurrent/atomic/AtomicInteger;
    //   614: invokevirtual incrementAndGet : ()I
    //   617: aload #6
    //   619: ldc_w 1073741824
    //   622: invokestatic zzb : (Landroid/content/Context;ILandroid/content/Intent;I)Landroid/app/PendingIntent;
    //   625: astore #6
    //   627: invokestatic isAtLeastO : ()Z
    //   630: ifeq -> 679
    //   633: aload_0
    //   634: getfield mContext : Landroid/content/Context;
    //   637: invokevirtual getApplicationInfo : ()Landroid/content/pm/ApplicationInfo;
    //   640: getfield targetSdkVersion : I
    //   643: bipush #25
    //   645: if_icmple -> 679
    //   648: aload_0
    //   649: aload #4
    //   651: aload #5
    //   653: iload_2
    //   654: aload #8
    //   656: aload_3
    //   657: aload #11
    //   659: aload #6
    //   661: aload_0
    //   662: aload_1
    //   663: ldc_w 'gcm.n.android_channel_id'
    //   666: invokestatic zze : (Landroid/os/Bundle;Ljava/lang/String;)Ljava/lang/String;
    //   669: invokespecial zzrj : (Ljava/lang/String;)Ljava/lang/String;
    //   672: invokespecial zza : (Ljava/lang/CharSequence;Ljava/lang/String;ILjava/lang/Integer;Landroid/net/Uri;Landroid/app/PendingIntent;Landroid/app/PendingIntent;Ljava/lang/String;)Landroid/app/Notification;
    //   675: astore_3
    //   676: goto -> 809
    //   679: new android/support/v4/app/NotificationCompat$Builder
    //   682: dup
    //   683: aload_0
    //   684: getfield mContext : Landroid/content/Context;
    //   687: invokespecial <init> : (Landroid/content/Context;)V
    //   690: iconst_1
    //   691: invokevirtual setAutoCancel : (Z)Landroid/support/v4/app/NotificationCompat$Builder;
    //   694: iload_2
    //   695: invokevirtual setSmallIcon : (I)Landroid/support/v4/app/NotificationCompat$Builder;
    //   698: astore #9
    //   700: aload #4
    //   702: invokestatic isEmpty : (Ljava/lang/CharSequence;)Z
    //   705: ifne -> 716
    //   708: aload #9
    //   710: aload #4
    //   712: invokevirtual setContentTitle : (Ljava/lang/CharSequence;)Landroid/support/v4/app/NotificationCompat$Builder;
    //   715: pop
    //   716: aload #5
    //   718: invokestatic isEmpty : (Ljava/lang/CharSequence;)Z
    //   721: ifne -> 750
    //   724: aload #9
    //   726: aload #5
    //   728: invokevirtual setContentText : (Ljava/lang/CharSequence;)Landroid/support/v4/app/NotificationCompat$Builder;
    //   731: pop
    //   732: aload #9
    //   734: new android/support/v4/app/NotificationCompat$BigTextStyle
    //   737: dup
    //   738: invokespecial <init> : ()V
    //   741: aload #5
    //   743: invokevirtual bigText : (Ljava/lang/CharSequence;)Landroid/support/v4/app/NotificationCompat$BigTextStyle;
    //   746: invokevirtual setStyle : (Landroid/support/v4/app/NotificationCompat$Style;)Landroid/support/v4/app/NotificationCompat$Builder;
    //   749: pop
    //   750: aload #8
    //   752: ifnull -> 766
    //   755: aload #9
    //   757: aload #8
    //   759: invokevirtual intValue : ()I
    //   762: invokevirtual setColor : (I)Landroid/support/v4/app/NotificationCompat$Builder;
    //   765: pop
    //   766: aload_3
    //   767: ifnull -> 777
    //   770: aload #9
    //   772: aload_3
    //   773: invokevirtual setSound : (Landroid/net/Uri;)Landroid/support/v4/app/NotificationCompat$Builder;
    //   776: pop
    //   777: aload #11
    //   779: ifnull -> 790
    //   782: aload #9
    //   784: aload #11
    //   786: invokevirtual setContentIntent : (Landroid/app/PendingIntent;)Landroid/support/v4/app/NotificationCompat$Builder;
    //   789: pop
    //   790: aload #6
    //   792: ifnull -> 803
    //   795: aload #9
    //   797: aload #6
    //   799: invokevirtual setDeleteIntent : (Landroid/app/PendingIntent;)Landroid/support/v4/app/NotificationCompat$Builder;
    //   802: pop
    //   803: aload #9
    //   805: invokevirtual build : ()Landroid/app/Notification;
    //   808: astore_3
    //   809: aload_1
    //   810: ldc_w 'gcm.n.tag'
    //   813: invokestatic zze : (Landroid/os/Bundle;Ljava/lang/String;)Ljava/lang/String;
    //   816: astore #4
    //   818: ldc 'FirebaseMessaging'
    //   820: iconst_3
    //   821: invokestatic isLoggable : (Ljava/lang/String;I)Z
    //   824: ifeq -> 836
    //   827: ldc 'FirebaseMessaging'
    //   829: ldc_w 'Showing notification'
    //   832: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   835: pop
    //   836: aload_0
    //   837: getfield mContext : Landroid/content/Context;
    //   840: ldc_w 'notification'
    //   843: invokevirtual getSystemService : (Ljava/lang/String;)Ljava/lang/Object;
    //   846: checkcast android/app/NotificationManager
    //   849: astore #6
    //   851: aload #4
    //   853: astore_1
    //   854: aload #4
    //   856: invokestatic isEmpty : (Ljava/lang/CharSequence;)Z
    //   859: ifeq -> 897
    //   862: invokestatic uptimeMillis : ()J
    //   865: lstore #12
    //   867: new java/lang/StringBuilder
    //   870: dup
    //   871: bipush #37
    //   873: invokespecial <init> : (I)V
    //   876: astore_1
    //   877: aload_1
    //   878: ldc_w 'FCM-Notification:'
    //   881: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   884: pop
    //   885: aload_1
    //   886: lload #12
    //   888: invokevirtual append : (J)Ljava/lang/StringBuilder;
    //   891: pop
    //   892: aload_1
    //   893: invokevirtual toString : ()Ljava/lang/String;
    //   896: astore_1
    //   897: aload #6
    //   899: aload_1
    //   900: iconst_0
    //   901: aload_3
    //   902: invokevirtual notify : (Ljava/lang/String;ILandroid/app/Notification;)V
    //   905: iconst_1
    //   906: ireturn
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/firebase/messaging/zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */