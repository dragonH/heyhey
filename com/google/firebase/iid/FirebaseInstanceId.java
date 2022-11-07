package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.util.Base64;
import android.util.Log;
import com.google.firebase.FirebaseApp;
import java.io.IOException;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class FirebaseInstanceId {
  private static Map<String, FirebaseInstanceId> zzhtn = (Map<String, FirebaseInstanceId>)new ArrayMap();
  
  private static zzk zznfj;
  
  private final FirebaseApp zznfk;
  
  private final zzj zznfl;
  
  private final String zznfm;
  
  private FirebaseInstanceId(FirebaseApp paramFirebaseApp, zzj paramzzj) {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial <init> : ()V
    //   4: aload_0
    //   5: aload_1
    //   6: putfield zznfk : Lcom/google/firebase/FirebaseApp;
    //   9: aload_0
    //   10: aload_2
    //   11: putfield zznfl : Lcom/google/firebase/iid/zzj;
    //   14: aload_1
    //   15: invokevirtual getOptions : ()Lcom/google/firebase/FirebaseOptions;
    //   18: invokevirtual getGcmSenderId : ()Ljava/lang/String;
    //   21: astore_2
    //   22: aload_2
    //   23: ifnull -> 29
    //   26: goto -> 82
    //   29: aload_1
    //   30: invokevirtual getOptions : ()Lcom/google/firebase/FirebaseOptions;
    //   33: invokevirtual getApplicationId : ()Ljava/lang/String;
    //   36: astore_3
    //   37: aload_3
    //   38: astore_2
    //   39: aload_3
    //   40: ldc '1:'
    //   42: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   45: ifeq -> 82
    //   48: aload_3
    //   49: ldc ':'
    //   51: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
    //   54: astore_2
    //   55: aload_2
    //   56: arraylength
    //   57: iconst_2
    //   58: if_icmpge -> 66
    //   61: aconst_null
    //   62: astore_2
    //   63: goto -> 82
    //   66: aload_2
    //   67: iconst_1
    //   68: aaload
    //   69: astore_3
    //   70: aload_3
    //   71: astore_2
    //   72: aload_3
    //   73: invokevirtual isEmpty : ()Z
    //   76: ifeq -> 82
    //   79: goto -> 61
    //   82: aload_0
    //   83: aload_2
    //   84: putfield zznfm : Ljava/lang/String;
    //   87: aload_2
    //   88: ifnull -> 100
    //   91: aload_1
    //   92: invokevirtual getApplicationContext : ()Landroid/content/Context;
    //   95: aload_0
    //   96: invokestatic zza : (Landroid/content/Context;Lcom/google/firebase/iid/FirebaseInstanceId;)V
    //   99: return
    //   100: new java/lang/IllegalStateException
    //   103: dup
    //   104: ldc 'IID failing to initialize, FirebaseApp is missing project ID'
    //   106: invokespecial <init> : (Ljava/lang/String;)V
    //   109: astore_1
    //   110: goto -> 115
    //   113: aload_1
    //   114: athrow
    //   115: goto -> 113
  }
  
  public static FirebaseInstanceId getInstance() {
    return getInstance(FirebaseApp.getInstance());
  }
  
  @Keep
  public static FirebaseInstanceId getInstance(@NonNull FirebaseApp paramFirebaseApp) {
    // Byte code:
    //   0: ldc com/google/firebase/iid/FirebaseInstanceId
    //   2: monitorenter
    //   3: getstatic com/google/firebase/iid/FirebaseInstanceId.zzhtn : Ljava/util/Map;
    //   6: aload_0
    //   7: invokevirtual getOptions : ()Lcom/google/firebase/FirebaseOptions;
    //   10: invokevirtual getApplicationId : ()Ljava/lang/String;
    //   13: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   18: checkcast com/google/firebase/iid/FirebaseInstanceId
    //   21: astore_1
    //   22: aload_1
    //   23: astore_2
    //   24: aload_1
    //   25: ifnonnull -> 85
    //   28: aload_0
    //   29: invokevirtual getApplicationContext : ()Landroid/content/Context;
    //   32: aconst_null
    //   33: invokestatic zza : (Landroid/content/Context;Landroid/os/Bundle;)Lcom/google/firebase/iid/zzj;
    //   36: astore_1
    //   37: getstatic com/google/firebase/iid/FirebaseInstanceId.zznfj : Lcom/google/firebase/iid/zzk;
    //   40: ifnonnull -> 58
    //   43: new com/google/firebase/iid/zzk
    //   46: astore_2
    //   47: aload_2
    //   48: invokestatic zzcga : ()Lcom/google/firebase/iid/zzr;
    //   51: invokespecial <init> : (Lcom/google/firebase/iid/zzr;)V
    //   54: aload_2
    //   55: putstatic com/google/firebase/iid/FirebaseInstanceId.zznfj : Lcom/google/firebase/iid/zzk;
    //   58: new com/google/firebase/iid/FirebaseInstanceId
    //   61: astore_2
    //   62: aload_2
    //   63: aload_0
    //   64: aload_1
    //   65: invokespecial <init> : (Lcom/google/firebase/FirebaseApp;Lcom/google/firebase/iid/zzj;)V
    //   68: getstatic com/google/firebase/iid/FirebaseInstanceId.zzhtn : Ljava/util/Map;
    //   71: aload_0
    //   72: invokevirtual getOptions : ()Lcom/google/firebase/FirebaseOptions;
    //   75: invokevirtual getApplicationId : ()Ljava/lang/String;
    //   78: aload_2
    //   79: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   84: pop
    //   85: ldc com/google/firebase/iid/FirebaseInstanceId
    //   87: monitorexit
    //   88: aload_2
    //   89: areturn
    //   90: astore_0
    //   91: ldc com/google/firebase/iid/FirebaseInstanceId
    //   93: monitorexit
    //   94: aload_0
    //   95: athrow
    // Exception table:
    //   from	to	target	type
    //   3	22	90	finally
    //   28	58	90	finally
    //   58	85	90	finally
  }
  
  static String zza(KeyPair paramKeyPair) {
    byte[] arrayOfByte = paramKeyPair.getPublic().getEncoded();
    try {
      arrayOfByte = MessageDigest.getInstance("SHA1").digest(arrayOfByte);
      arrayOfByte[0] = (byte)(byte)((arrayOfByte[0] & 0xF) + 112);
      return Base64.encodeToString(arrayOfByte, 0, 8, 11);
    } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
      Log.w("FirebaseInstanceId", "Unexpected error, device missing required alghorithms");
      return null;
    } 
  }
  
  static void zza(Context paramContext, zzr paramzzr) {
    paramzzr.zzasv();
    Intent intent = new Intent();
    intent.putExtra("CMD", "RST");
    zzq.zzcge().zze(paramContext, intent);
  }
  
  private final void zzac(Bundle paramBundle) {
    paramBundle.putString("gmp_app_id", this.zznfk.getOptions().getApplicationId());
  }
  
  static int zzao(Context paramContext, String paramString) {
    try {
      return (paramContext.getPackageManager().getPackageInfo(paramString, 0)).versionCode;
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      paramString = String.valueOf(nameNotFoundException);
      StringBuilder stringBuilder = new StringBuilder(paramString.length() + 23);
      stringBuilder.append("Failed to find package ");
      stringBuilder.append(paramString);
      Log.w("FirebaseInstanceId", stringBuilder.toString());
      return 0;
    } 
  }
  
  static zzk zzcfz() {
    return zznfj;
  }
  
  static String zzdd(Context paramContext) {
    try {
      return (paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0)).versionName;
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      String str = String.valueOf(nameNotFoundException);
      StringBuilder stringBuilder = new StringBuilder(str.length() + 38);
      stringBuilder.append("Never happens: can't find own package ");
      stringBuilder.append(str);
      Log.w("FirebaseInstanceId", stringBuilder.toString());
      return null;
    } 
  }
  
  static int zzej(Context paramContext) {
    return zzao(paramContext, paramContext.getPackageName());
  }
  
  static void zzek(Context paramContext) {
    Intent intent = new Intent();
    intent.putExtra("CMD", "SYNC");
    zzq.zzcge().zze(paramContext, intent);
  }
  
  static String zzn(byte[] paramArrayOfbyte) {
    return Base64.encodeToString(paramArrayOfbyte, 11);
  }
  
  public void deleteInstanceId() throws IOException {
    this.zznfl.zza("*", "*", null);
    this.zznfl.zzasr();
  }
  
  @WorkerThread
  public void deleteToken(String paramString1, String paramString2) throws IOException {
    Bundle bundle = new Bundle();
    zzac(bundle);
    this.zznfl.zza(paramString1, paramString2, bundle);
  }
  
  public long getCreationTime() {
    return this.zznfl.getCreationTime();
  }
  
  public String getId() {
    return zza(this.zznfl.zzasq());
  }
  
  @Nullable
  public String getToken() {
    zzs zzs = zzcfx();
    if (zzs == null || zzs.zzrg(zzj.zzhtt))
      FirebaseInstanceIdService.zzem(this.zznfk.getApplicationContext()); 
    return (zzs != null) ? zzs.zzkoo : null;
  }
  
  @WorkerThread
  public String getToken(String paramString1, String paramString2) throws IOException {
    Bundle bundle = new Bundle();
    zzac(bundle);
    return this.zznfl.getToken(paramString1, paramString2, bundle);
  }
  
  @Nullable
  final zzs zzcfx() {
    return zzj.zzcga().zzo("", this.zznfm, "*");
  }
  
  final String zzcfy() throws IOException {
    return getToken(this.zznfm, "*");
  }
  
  public final void zzqw(String paramString) {
    zznfj.zzqw(paramString);
    FirebaseInstanceIdService.zzem(this.zznfk.getApplicationContext());
  }
  
  final void zzqx(String paramString) throws IOException {
    zzs zzs = zzcfx();
    if (zzs != null && !zzs.zzrg(zzj.zzhtt)) {
      Bundle bundle = new Bundle();
      String str = String.valueOf(paramString);
      if (str.length() != 0) {
        str = "/topics/".concat(str);
      } else {
        str = new String("/topics/");
      } 
      bundle.putString("gcm.topic", str);
      str = zzs.zzkoo;
      paramString = String.valueOf(paramString);
      if (paramString.length() != 0) {
        paramString = "/topics/".concat(paramString);
      } else {
        paramString = new String("/topics/");
      } 
      zzac(bundle);
      this.zznfl.zzb(str, paramString, bundle);
      return;
    } 
    throw new IOException("token not available");
  }
  
  final void zzqy(String paramString) throws IOException {
    zzs zzs = zzcfx();
    if (zzs != null && !zzs.zzrg(zzj.zzhtt)) {
      Bundle bundle = new Bundle();
      String str2 = String.valueOf(paramString);
      if (str2.length() != 0) {
        str2 = "/topics/".concat(str2);
      } else {
        str2 = new String("/topics/");
      } 
      bundle.putString("gcm.topic", str2);
      zzj zzj1 = this.zznfl;
      String str1 = zzs.zzkoo;
      paramString = String.valueOf(paramString);
      if (paramString.length() != 0) {
        paramString = "/topics/".concat(paramString);
      } else {
        paramString = new String("/topics/");
      } 
      zzj1.zza(str1, paramString, bundle);
      return;
    } 
    throw new IOException("token not available");
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/firebase/iid/FirebaseInstanceId.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */