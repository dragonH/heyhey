package com.google.android.gms.ads.identifier;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.annotation.KeepForSdkWithMembers;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.zze;
import com.google.android.gms.internal.zzev;
import com.google.android.gms.internal.zzew;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@KeepForSdkWithMembers
public class AdvertisingIdClient {
  private final Context mContext;
  
  @Nullable
  private com.google.android.gms.common.zza zzall;
  
  @Nullable
  private zzev zzalm;
  
  private boolean zzaln;
  
  private Object zzalo = new Object();
  
  @Nullable
  private zza zzalp;
  
  private long zzalq;
  
  public AdvertisingIdClient(Context paramContext) {
    this(paramContext, 30000L, false);
  }
  
  public AdvertisingIdClient(Context paramContext, long paramLong, boolean paramBoolean) {
    zzbp.zzu(paramContext);
    Context context = paramContext;
    if (paramBoolean) {
      context = paramContext.getApplicationContext();
      if (context == null)
        context = paramContext; 
    } 
    this.mContext = context;
    this.zzaln = false;
    this.zzalq = paramLong;
  }
  
  public static Info getAdvertisingIdInfo(Context paramContext) throws IOException, IllegalStateException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
    zzd zzd = new zzd(paramContext);
    boolean bool1 = zzd.getBoolean("gads:ad_id_app_context:enabled", false);
    float f = zzd.getFloat("gads:ad_id_app_context:ping_ratio", 0.0F);
    boolean bool2 = zzd.getBoolean("gads:ad_id_use_shared_preference:enabled", false);
    String str = zzd.getString("gads:ad_id_use_shared_preference:experiment_id", "");
    if (bool2) {
      Info info = zzb.zzd(paramContext).getInfo();
      if (info != null)
        return info; 
    } 
    AdvertisingIdClient advertisingIdClient = new AdvertisingIdClient(paramContext, -1L, bool1);
    try {
      long l = SystemClock.elapsedRealtime();
      advertisingIdClient.start(false);
      Info info = advertisingIdClient.getInfo();
      advertisingIdClient.zza(info, bool1, f, SystemClock.elapsedRealtime() - l, str, null);
      return info;
    } finally {
      Exception exception = null;
    } 
  }
  
  public static void setShouldSkipGmsCoreVersionCheck(boolean paramBoolean) {}
  
  private final void start(boolean paramBoolean) throws IOException, IllegalStateException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
    // Byte code:
    //   0: ldc 'Calling this from your main thread can lead to deadlock'
    //   2: invokestatic zzgh : (Ljava/lang/String;)V
    //   5: aload_0
    //   6: monitorenter
    //   7: aload_0
    //   8: getfield zzaln : Z
    //   11: ifeq -> 18
    //   14: aload_0
    //   15: invokevirtual finish : ()V
    //   18: aload_0
    //   19: getfield mContext : Landroid/content/Context;
    //   22: invokestatic zzc : (Landroid/content/Context;)Lcom/google/android/gms/common/zza;
    //   25: astore_2
    //   26: aload_0
    //   27: aload_2
    //   28: putfield zzall : Lcom/google/android/gms/common/zza;
    //   31: aload_0
    //   32: aload_0
    //   33: getfield mContext : Landroid/content/Context;
    //   36: aload_2
    //   37: invokestatic zza : (Landroid/content/Context;Lcom/google/android/gms/common/zza;)Lcom/google/android/gms/internal/zzev;
    //   40: putfield zzalm : Lcom/google/android/gms/internal/zzev;
    //   43: aload_0
    //   44: iconst_1
    //   45: putfield zzaln : Z
    //   48: iload_1
    //   49: ifeq -> 56
    //   52: aload_0
    //   53: invokespecial zzbh : ()V
    //   56: aload_0
    //   57: monitorexit
    //   58: return
    //   59: astore_2
    //   60: aload_0
    //   61: monitorexit
    //   62: aload_2
    //   63: athrow
    // Exception table:
    //   from	to	target	type
    //   7	18	59	finally
    //   18	48	59	finally
    //   52	56	59	finally
    //   56	58	59	finally
    //   60	62	59	finally
  }
  
  private static zzev zza(Context paramContext, com.google.android.gms.common.zza paramzza) throws IOException {
    try {
      return zzew.zzc(paramzza.zza(10000L, TimeUnit.MILLISECONDS));
    } catch (InterruptedException interruptedException) {
      throw new IOException("Interrupted exception");
    } finally {
      paramContext = null;
    } 
  }
  
  private final boolean zza(Info paramInfo, boolean paramBoolean, float paramFloat, long paramLong, String paramString, Throwable paramThrowable) {
    String str2;
    if (Math.random() > paramFloat)
      return false; 
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    String str1 = "1";
    if (paramBoolean) {
      str2 = "1";
    } else {
      str2 = "0";
    } 
    hashMap.put("app_context", str2);
    if (paramInfo != null) {
      if (paramInfo.isLimitAdTrackingEnabled()) {
        str2 = str1;
      } else {
        str2 = "0";
      } 
      hashMap.put("limit_ad_tracking", str2);
    } 
    if (paramInfo != null && paramInfo.getId() != null)
      hashMap.put("ad_id_size", Integer.toString(paramInfo.getId().length())); 
    if (paramThrowable != null)
      hashMap.put("error", paramThrowable.getClass().getName()); 
    if (paramString != null && !paramString.isEmpty())
      hashMap.put("experiment_id", paramString); 
    hashMap.put("tag", "AdvertisingIdClient");
    hashMap.put("time_spent", Long.toString(paramLong));
    (new zza(this, hashMap)).start();
    return true;
  }
  
  private final void zzbh() {
    synchronized (this.zzalo) {
      zza zza1 = this.zzalp;
      if (zza1 != null) {
        zza1.zzalu.countDown();
        try {
          this.zzalp.join();
        } catch (InterruptedException interruptedException) {}
      } 
      if (this.zzalq > 0L) {
        zza1 = new zza();
        this(this, this.zzalq);
        this.zzalp = zza1;
      } 
      return;
    } 
  }
  
  private static com.google.android.gms.common.zza zzc(Context paramContext) throws IOException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
    try {
      paramContext.getPackageManager().getPackageInfo("com.android.vending", 0);
      int i = zze.zzaex().isGooglePlayServicesAvailable(paramContext);
      if (i == 0 || i == 2) {
        com.google.android.gms.common.zza zza1 = new com.google.android.gms.common.zza();
        Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
        intent.setPackage("com.google.android.gms");
        try {
          boolean bool = com.google.android.gms.common.stats.zza.zzaky().zza(paramContext, intent, (ServiceConnection)zza1, 1);
          if (bool)
            return zza1; 
          throw new IOException("Connection failure");
        } finally {
          paramContext = null;
        } 
      } 
      throw new IOException("Google Play services not available");
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      throw new GooglePlayServicesNotAvailableException(9);
    } 
  }
  
  protected void finalize() throws Throwable {
    finish();
    super.finalize();
  }
  
  public void finish() {
    // Byte code:
    //   0: ldc 'Calling this from your main thread can lead to deadlock'
    //   2: invokestatic zzgh : (Ljava/lang/String;)V
    //   5: aload_0
    //   6: monitorenter
    //   7: aload_0
    //   8: getfield mContext : Landroid/content/Context;
    //   11: ifnull -> 80
    //   14: aload_0
    //   15: getfield zzall : Lcom/google/android/gms/common/zza;
    //   18: astore_1
    //   19: aload_1
    //   20: ifnonnull -> 26
    //   23: goto -> 80
    //   26: aload_0
    //   27: getfield zzaln : Z
    //   30: ifeq -> 62
    //   33: invokestatic zzaky : ()Lcom/google/android/gms/common/stats/zza;
    //   36: pop
    //   37: aload_0
    //   38: getfield mContext : Landroid/content/Context;
    //   41: aload_0
    //   42: getfield zzall : Lcom/google/android/gms/common/zza;
    //   45: invokevirtual unbindService : (Landroid/content/ServiceConnection;)V
    //   48: goto -> 62
    //   51: astore_1
    //   52: ldc 'AdvertisingIdClient'
    //   54: ldc_w 'AdvertisingIdClient unbindService failed.'
    //   57: aload_1
    //   58: invokestatic i : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   61: pop
    //   62: aload_0
    //   63: iconst_0
    //   64: putfield zzaln : Z
    //   67: aload_0
    //   68: aconst_null
    //   69: putfield zzalm : Lcom/google/android/gms/internal/zzev;
    //   72: aload_0
    //   73: aconst_null
    //   74: putfield zzall : Lcom/google/android/gms/common/zza;
    //   77: aload_0
    //   78: monitorexit
    //   79: return
    //   80: aload_0
    //   81: monitorexit
    //   82: return
    //   83: astore_1
    //   84: aload_0
    //   85: monitorexit
    //   86: aload_1
    //   87: athrow
    // Exception table:
    //   from	to	target	type
    //   7	19	83	finally
    //   26	48	51	finally
    //   52	62	83	finally
    //   62	79	83	finally
    //   80	82	83	finally
    //   84	86	83	finally
  }
  
  public Info getInfo() throws IOException {
    // Byte code:
    //   0: ldc 'Calling this from your main thread can lead to deadlock'
    //   2: invokestatic zzgh : (Ljava/lang/String;)V
    //   5: aload_0
    //   6: monitorenter
    //   7: aload_0
    //   8: getfield zzaln : Z
    //   11: ifne -> 100
    //   14: aload_0
    //   15: getfield zzalo : Ljava/lang/Object;
    //   18: astore_1
    //   19: aload_1
    //   20: monitorenter
    //   21: aload_0
    //   22: getfield zzalp : Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$zza;
    //   25: astore_2
    //   26: aload_2
    //   27: ifnull -> 82
    //   30: aload_2
    //   31: getfield zzalv : Z
    //   34: ifeq -> 82
    //   37: aload_1
    //   38: monitorexit
    //   39: aload_0
    //   40: iconst_0
    //   41: invokespecial start : (Z)V
    //   44: aload_0
    //   45: getfield zzaln : Z
    //   48: ifeq -> 54
    //   51: goto -> 100
    //   54: new java/io/IOException
    //   57: astore_1
    //   58: aload_1
    //   59: ldc_w 'AdvertisingIdClient cannot reconnect.'
    //   62: invokespecial <init> : (Ljava/lang/String;)V
    //   65: aload_1
    //   66: athrow
    //   67: astore_1
    //   68: new java/io/IOException
    //   71: astore_2
    //   72: aload_2
    //   73: ldc_w 'AdvertisingIdClient cannot reconnect.'
    //   76: aload_1
    //   77: invokespecial <init> : (Ljava/lang/String;Ljava/lang/Throwable;)V
    //   80: aload_2
    //   81: athrow
    //   82: new java/io/IOException
    //   85: astore_2
    //   86: aload_2
    //   87: ldc_w 'AdvertisingIdClient is not connected.'
    //   90: invokespecial <init> : (Ljava/lang/String;)V
    //   93: aload_2
    //   94: athrow
    //   95: astore_2
    //   96: aload_1
    //   97: monitorexit
    //   98: aload_2
    //   99: athrow
    //   100: aload_0
    //   101: getfield zzall : Lcom/google/android/gms/common/zza;
    //   104: invokestatic zzu : (Ljava/lang/Object;)Ljava/lang/Object;
    //   107: pop
    //   108: aload_0
    //   109: getfield zzalm : Lcom/google/android/gms/internal/zzev;
    //   112: invokestatic zzu : (Ljava/lang/Object;)Ljava/lang/Object;
    //   115: pop
    //   116: new com/google/android/gms/ads/identifier/AdvertisingIdClient$Info
    //   119: astore_1
    //   120: aload_1
    //   121: aload_0
    //   122: getfield zzalm : Lcom/google/android/gms/internal/zzev;
    //   125: invokeinterface getId : ()Ljava/lang/String;
    //   130: aload_0
    //   131: getfield zzalm : Lcom/google/android/gms/internal/zzev;
    //   134: iconst_1
    //   135: invokeinterface zzb : (Z)Z
    //   140: invokespecial <init> : (Ljava/lang/String;Z)V
    //   143: aload_0
    //   144: monitorexit
    //   145: aload_0
    //   146: invokespecial zzbh : ()V
    //   149: aload_1
    //   150: areturn
    //   151: astore_1
    //   152: ldc 'AdvertisingIdClient'
    //   154: ldc_w 'GMS remote exception '
    //   157: aload_1
    //   158: invokestatic i : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   161: pop
    //   162: new java/io/IOException
    //   165: astore_1
    //   166: aload_1
    //   167: ldc_w 'Remote exception'
    //   170: invokespecial <init> : (Ljava/lang/String;)V
    //   173: aload_1
    //   174: athrow
    //   175: astore_1
    //   176: aload_0
    //   177: monitorexit
    //   178: aload_1
    //   179: athrow
    // Exception table:
    //   from	to	target	type
    //   7	21	175	finally
    //   21	26	95	finally
    //   30	39	95	finally
    //   39	44	67	java/lang/Exception
    //   39	44	175	finally
    //   44	51	175	finally
    //   54	67	175	finally
    //   68	82	175	finally
    //   82	95	95	finally
    //   96	98	95	finally
    //   98	100	175	finally
    //   100	116	175	finally
    //   116	143	151	android/os/RemoteException
    //   116	143	175	finally
    //   143	145	175	finally
    //   152	175	175	finally
    //   176	178	175	finally
  }
  
  public void start() throws IOException, IllegalStateException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
    start(true);
  }
  
  public static final class Info {
    private final String zzalw;
    
    private final boolean zzalx;
    
    public Info(String param1String, boolean param1Boolean) {
      this.zzalw = param1String;
      this.zzalx = param1Boolean;
    }
    
    public final String getId() {
      return this.zzalw;
    }
    
    public final boolean isLimitAdTrackingEnabled() {
      return this.zzalx;
    }
    
    public final String toString() {
      String str = this.zzalw;
      boolean bool = this.zzalx;
      StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 7);
      stringBuilder.append("{");
      stringBuilder.append(str);
      stringBuilder.append("}");
      stringBuilder.append(bool);
      return stringBuilder.toString();
    }
  }
  
  static final class zza extends Thread {
    private WeakReference<AdvertisingIdClient> zzals;
    
    private long zzalt;
    
    CountDownLatch zzalu;
    
    boolean zzalv;
    
    public zza(AdvertisingIdClient param1AdvertisingIdClient, long param1Long) {
      this.zzals = new WeakReference<AdvertisingIdClient>(param1AdvertisingIdClient);
      this.zzalt = param1Long;
      this.zzalu = new CountDownLatch(1);
      this.zzalv = false;
      start();
    }
    
    private final void disconnect() {
      AdvertisingIdClient advertisingIdClient = this.zzals.get();
      if (advertisingIdClient != null) {
        advertisingIdClient.finish();
        this.zzalv = true;
      } 
    }
    
    public final void run() {
      try {
        if (!this.zzalu.await(this.zzalt, TimeUnit.MILLISECONDS))
          disconnect(); 
        return;
      } catch (InterruptedException interruptedException) {
        disconnect();
        return;
      } 
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/ads/identifier/AdvertisingIdClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */