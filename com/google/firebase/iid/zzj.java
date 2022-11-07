package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import java.io.IOException;
import java.security.KeyPair;
import java.util.Map;

public final class zzj {
  private static Map<String, zzj> zzhtn = (Map<String, zzj>)new ArrayMap();
  
  static String zzhtt;
  
  private static zzr zznft;
  
  private static zzl zznfu;
  
  private Context mContext;
  
  private KeyPair zzhtq;
  
  private String zzhtr = "";
  
  private zzj(Context paramContext, String paramString, Bundle paramBundle) {
    this.mContext = paramContext.getApplicationContext();
    this.zzhtr = paramString;
  }
  
  public static zzj zza(Context paramContext, Bundle paramBundle) {
    // Byte code:
    //   0: ldc com/google/firebase/iid/zzj
    //   2: monitorenter
    //   3: aload_1
    //   4: ifnonnull -> 13
    //   7: ldc ''
    //   9: astore_2
    //   10: goto -> 20
    //   13: aload_1
    //   14: ldc 'subtype'
    //   16: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   19: astore_2
    //   20: aload_2
    //   21: astore_3
    //   22: aload_2
    //   23: ifnonnull -> 29
    //   26: ldc ''
    //   28: astore_3
    //   29: aload_0
    //   30: invokevirtual getApplicationContext : ()Landroid/content/Context;
    //   33: astore #4
    //   35: getstatic com/google/firebase/iid/zzj.zznft : Lcom/google/firebase/iid/zzr;
    //   38: ifnonnull -> 69
    //   41: new com/google/firebase/iid/zzr
    //   44: astore_0
    //   45: aload_0
    //   46: aload #4
    //   48: invokespecial <init> : (Landroid/content/Context;)V
    //   51: aload_0
    //   52: putstatic com/google/firebase/iid/zzj.zznft : Lcom/google/firebase/iid/zzr;
    //   55: new com/google/firebase/iid/zzl
    //   58: astore_0
    //   59: aload_0
    //   60: aload #4
    //   62: invokespecial <init> : (Landroid/content/Context;)V
    //   65: aload_0
    //   66: putstatic com/google/firebase/iid/zzj.zznfu : Lcom/google/firebase/iid/zzl;
    //   69: aload #4
    //   71: invokestatic zzej : (Landroid/content/Context;)I
    //   74: invokestatic toString : (I)Ljava/lang/String;
    //   77: putstatic com/google/firebase/iid/zzj.zzhtt : Ljava/lang/String;
    //   80: getstatic com/google/firebase/iid/zzj.zzhtn : Ljava/util/Map;
    //   83: aload_3
    //   84: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   89: checkcast com/google/firebase/iid/zzj
    //   92: astore_2
    //   93: aload_2
    //   94: astore_0
    //   95: aload_2
    //   96: ifnonnull -> 122
    //   99: new com/google/firebase/iid/zzj
    //   102: astore_0
    //   103: aload_0
    //   104: aload #4
    //   106: aload_3
    //   107: aload_1
    //   108: invokespecial <init> : (Landroid/content/Context;Ljava/lang/String;Landroid/os/Bundle;)V
    //   111: getstatic com/google/firebase/iid/zzj.zzhtn : Ljava/util/Map;
    //   114: aload_3
    //   115: aload_0
    //   116: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   121: pop
    //   122: ldc com/google/firebase/iid/zzj
    //   124: monitorexit
    //   125: aload_0
    //   126: areturn
    //   127: astore_0
    //   128: ldc com/google/firebase/iid/zzj
    //   130: monitorexit
    //   131: aload_0
    //   132: athrow
    // Exception table:
    //   from	to	target	type
    //   13	20	127	finally
    //   29	69	127	finally
    //   69	93	127	finally
    //   99	122	127	finally
  }
  
  public static zzr zzcga() {
    return zznft;
  }
  
  public static zzl zzcgb() {
    return zznfu;
  }
  
  public final long getCreationTime() {
    return zznft.zzrb(this.zzhtr);
  }
  
  public final String getToken(String paramString1, String paramString2, Bundle paramBundle) throws IOException {
    if (Looper.getMainLooper() != Looper.myLooper()) {
      boolean bool2;
      boolean bool1 = true;
      if (paramBundle.getString("ttl") != null || "jwt".equals(paramBundle.getString("type"))) {
        bool2 = false;
      } else {
        zzs zzs = zznft.zzo(this.zzhtr, paramString1, paramString2);
        bool2 = bool1;
        if (zzs != null) {
          bool2 = bool1;
          if (!zzs.zzrg(zzhtt))
            return zzs.zzkoo; 
        } 
      } 
      String str = zzb(paramString1, paramString2, paramBundle);
      if (str != null && bool2)
        zznft.zza(this.zzhtr, paramString1, paramString2, str, zzhtt); 
      return str;
    } 
    throw new IOException("MAIN_THREAD");
  }
  
  public final void zza(String paramString1, String paramString2, Bundle paramBundle) throws IOException {
    if (Looper.getMainLooper() != Looper.myLooper()) {
      zznft.zzf(this.zzhtr, paramString1, paramString2);
      Bundle bundle = paramBundle;
      if (paramBundle == null)
        bundle = new Bundle(); 
      bundle.putString("delete", "1");
      zzb(paramString1, paramString2, bundle);
      return;
    } 
    throw new IOException("MAIN_THREAD");
  }
  
  final KeyPair zzasq() {
    if (this.zzhtq == null)
      this.zzhtq = zznft.zzre(this.zzhtr); 
    if (this.zzhtq == null)
      this.zzhtq = zznft.zzrc(this.zzhtr); 
    return this.zzhtq;
  }
  
  public final void zzasr() {
    zznft.zzrd(this.zzhtr);
    this.zzhtq = null;
  }
  
  public final String zzb(String paramString1, String paramString2, Bundle paramBundle) throws IOException {
    if (paramString2 != null)
      paramBundle.putString("scope", paramString2); 
    paramBundle.putString("sender", paramString1);
    if (!"".equals(this.zzhtr))
      paramString1 = this.zzhtr; 
    paramBundle.putString("subtype", paramString1);
    paramBundle.putString("X-subtype", paramString1);
    Intent intent = zznfu.zza(paramBundle, zzasq());
    if (intent != null) {
      paramString2 = intent.getStringExtra("registration_id");
      paramString1 = paramString2;
      if (paramString2 == null)
        paramString1 = intent.getStringExtra("unregistered"); 
      if (paramString1 == null) {
        paramString1 = intent.getStringExtra("error");
        if (paramString1 != null)
          throw new IOException(paramString1); 
        paramString1 = String.valueOf(intent.getExtras());
        StringBuilder stringBuilder = new StringBuilder(paramString1.length() + 29);
        stringBuilder.append("Unexpected response from GCM ");
        stringBuilder.append(paramString1);
        Log.w("InstanceID/Rpc", stringBuilder.toString(), new Throwable());
        throw new IOException("SERVICE_NOT_AVAILABLE");
      } 
      return paramString1;
    } 
    throw new IOException("SERVICE_NOT_AVAILABLE");
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/firebase/iid/zzj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */