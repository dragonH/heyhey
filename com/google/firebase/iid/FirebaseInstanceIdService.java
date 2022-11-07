package com.google.firebase.iid;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.annotation.WorkerThread;
import android.util.Log;
import java.io.IOException;

public class FirebaseInstanceIdService extends zzb {
  @VisibleForTesting
  private static Object zznfp = new Object();
  
  @VisibleForTesting
  private static boolean zznfq = false;
  
  private boolean zznfr = false;
  
  static void zza(Context paramContext, FirebaseInstanceId paramFirebaseInstanceId) {
    synchronized (zznfp) {
      if (zznfq)
        return; 
      zzs zzs = paramFirebaseInstanceId.zzcfx();
      if (zzs == null || zzs.zzrg(zzj.zzhtt) || FirebaseInstanceId.zzcfz().zzcgc() != null)
        zzem(paramContext); 
      return;
    } 
  }
  
  private final void zza(Intent paramIntent, String paramString) {
    int i;
    boolean bool = zzen((Context)this);
    byte b = 10;
    if (paramIntent == null) {
      i = 10;
    } else {
      i = paramIntent.getIntExtra("next_retry_delay_in_seconds", 0);
    } 
    if (i < 10 && !bool) {
      i = 30;
    } else if (i < 10) {
      i = b;
    } else if (i > 28800) {
      i = 28800;
    } 
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(paramString).length() + 47);
    stringBuilder.append("background sync failed: ");
    stringBuilder.append(paramString);
    stringBuilder.append(", retry in ");
    stringBuilder.append(i);
    stringBuilder.append("s");
    Log.d("FirebaseInstanceId", stringBuilder.toString());
    synchronized (zznfp) {
      AlarmManager alarmManager = (AlarmManager)getSystemService("alarm");
      PendingIntent pendingIntent = zzq.zza((Context)this, 0, zzhk(i << 1), 134217728);
      alarmManager.set(3, SystemClock.elapsedRealtime() + (i * 1000), pendingIntent);
      zznfq = true;
      if (!bool) {
        if (this.zznfr)
          Log.d("FirebaseInstanceId", "device not connected. Connectivity change received registered"); 
        zza.zzl((Context)this, i);
      } 
      return;
    } 
  }
  
  private final void zza(Intent paramIntent, boolean paramBoolean1, boolean paramBoolean2) {
    synchronized (zznfp) {
      String str;
      zznfq = false;
      if (zzl.zzdf((Context)this) == null)
        return; 
      null = FirebaseInstanceId.getInstance();
      zzs zzs = null.zzcfx();
      if (zzs == null || zzs.zzrg(zzj.zzhtt))
        try {
          str = null.zzcfy();
          if (str != null) {
            if (this.zznfr)
              Log.d("FirebaseInstanceId", "get master token succeeded"); 
            zza((Context)this, (FirebaseInstanceId)null);
            if (paramBoolean2 || zzs == null || !str.equals(zzs.zzkoo))
              onTokenRefresh(); 
            return;
          } 
          zza(paramIntent, "returned token is null");
          return;
        } catch (IOException iOException) {
          zza(paramIntent, iOException.getMessage());
          return;
        } catch (SecurityException securityException) {
          Log.e("FirebaseInstanceId", "Unable to get master token", securityException);
          return;
        }  
      zzk zzk = FirebaseInstanceId.zzcfz();
      while (true) {
        str = zzk.zzcgc();
        if (str != null) {
          String[] arrayOfString = str.split("!");
          if (arrayOfString.length == 2) {
            null = arrayOfString[0];
            String str1 = arrayOfString[1];
            byte b = -1;
            try {
              int i = null.hashCode();
              if (i != 83) {
                if (i == 85 && null.equals("U"))
                  b = 1; 
              } else if (null.equals("S")) {
                b = 0;
              } 
              if (b != 0) {
                if (b != 1)
                  continue; 
                FirebaseInstanceId.getInstance().zzqy(str1);
                if (this.zznfr) {
                  null = "unsubscribe operation succeeded";
                } else {
                  continue;
                } 
              } else {
                FirebaseInstanceId.getInstance().zzqx(str1);
                if (this.zznfr) {
                  null = "subscribe operation succeeded";
                } else {
                  continue;
                } 
              } 
              Log.d("FirebaseInstanceId", (String)null);
            } catch (IOException null) {
              zza((Intent)securityException, null.getMessage());
              return;
            } 
          } 
          continue;
        } 
        Log.d("FirebaseInstanceId", "topic sync succeeded");
        return;
        zzk.zzra(str);
      } 
    } 
  }
  
  static void zzem(Context paramContext) {
    if (zzl.zzdf(paramContext) == null)
      return; 
    synchronized (zznfp) {
      if (!zznfq) {
        zzq.zzcge().zze(paramContext, zzhk(0));
        zznfq = true;
      } 
      return;
    } 
  }
  
  private static boolean zzen(Context paramContext) {
    NetworkInfo networkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    return (networkInfo != null && networkInfo.isConnected());
  }
  
  private static Intent zzhk(int paramInt) {
    Intent intent = new Intent("ACTION_TOKEN_REFRESH_RETRY");
    intent.putExtra("next_retry_delay_in_seconds", paramInt);
    return intent;
  }
  
  private static String zzp(Intent paramIntent) {
    String str2 = paramIntent.getStringExtra("subtype");
    String str1 = str2;
    if (str2 == null)
      str1 = ""; 
    return str1;
  }
  
  private final zzj zzqz(String paramString) {
    if (paramString == null)
      return zzj.zza((Context)this, null); 
    Bundle bundle = new Bundle();
    bundle.putString("subtype", paramString);
    return zzj.zza((Context)this, bundle);
  }
  
  public void handleIntent(Intent paramIntent) {
    String str1 = paramIntent.getAction();
    String str2 = "";
    String str3 = str1;
    if (str1 == null)
      str3 = ""; 
    if (!str3.equals("ACTION_TOKEN_REFRESH_RETRY")) {
      zzr zzr;
      str3 = zzp(paramIntent);
      zzj zzj = zzqz(str3);
      String str = paramIntent.getStringExtra("CMD");
      if (this.zznfr) {
        String str4 = String.valueOf(paramIntent.getExtras());
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(str3).length() + 18 + String.valueOf(str).length() + str4.length());
        stringBuilder.append("Service command ");
        stringBuilder.append(str3);
        stringBuilder.append(" ");
        stringBuilder.append(str);
        stringBuilder.append(" ");
        stringBuilder.append(str4);
        Log.d("FirebaseInstanceId", stringBuilder.toString());
      } 
      if (paramIntent.getStringExtra("unregistered") != null) {
        zzr = zzj.zzcga();
        if (str3 == null)
          str3 = str2; 
        zzr.zzhu(str3);
        zzj.zzcgb().zzi(paramIntent);
        return;
      } 
      if ("gcm.googleapis.com/refresh".equals(paramIntent.getStringExtra("from"))) {
        zzj.zzcga().zzhu(str3);
        zza(paramIntent, false, true);
        return;
      } 
      if ("RST".equals(str)) {
        zzr.zzasr();
        zza(paramIntent, true, true);
        return;
      } 
      if ("RST_FULL".equals(str)) {
        if (!zzj.zzcga().isEmpty()) {
          zzr.zzasr();
          zzj.zzcga().zzasv();
          zza(paramIntent, true, true);
          return;
        } 
      } else {
        if ("SYNC".equals(str)) {
          zzj.zzcga().zzhu(str3);
          zza(paramIntent, false, true);
          return;
        } 
        if ("PING".equals(str)) {
          Bundle bundle = paramIntent.getExtras();
          str2 = zzl.zzdf((Context)this);
          if (str2 == null) {
            Log.w("FirebaseInstanceId", "Unable to respond to ping due to missing target package");
            return;
          } 
          paramIntent = new Intent("com.google.android.gcm.intent.SEND");
          paramIntent.setPackage(str2);
          paramIntent.putExtras(bundle);
          zzl.zzd((Context)this, paramIntent);
          paramIntent.putExtra("google.to", "google.com/iid");
          paramIntent.putExtra("google.message_id", zzl.zzasu());
          sendOrderedBroadcast(paramIntent, "com.google.android.gtalkservice.permission.GTALK_SERVICE");
        } 
      } 
      return;
    } 
    zza(paramIntent, false, false);
  }
  
  @WorkerThread
  public void onTokenRefresh() {}
  
  protected final Intent zzn(Intent paramIntent) {
    return (zzq.zzcge()).zzngd.poll();
  }
  
  public final boolean zzo(Intent paramIntent) {
    this.zznfr = Log.isLoggable("FirebaseInstanceId", 3);
    if (paramIntent.getStringExtra("error") != null || paramIntent.getStringExtra("registration_id") != null) {
      String str = zzp(paramIntent);
      if (this.zznfr) {
        String str1 = String.valueOf(str);
        if (str1.length() != 0) {
          str1 = "Register result in service ".concat(str1);
        } else {
          str1 = new String("Register result in service ");
        } 
        Log.d("FirebaseInstanceId", str1);
      } 
      zzqz(str);
      zzj.zzcgb().zzi(paramIntent);
      return true;
    } 
    return false;
  }
  
  static class zza extends BroadcastReceiver {
    @Nullable
    private static BroadcastReceiver receiver;
    
    private int zznfs;
    
    private zza(int param1Int) {
      this.zznfs = param1Int;
    }
    
    static void zzl(Context param1Context, int param1Int) {
      // Byte code:
      //   0: ldc com/google/firebase/iid/FirebaseInstanceIdService$zza
      //   2: monitorenter
      //   3: getstatic com/google/firebase/iid/FirebaseInstanceIdService$zza.receiver : Landroid/content/BroadcastReceiver;
      //   6: astore_2
      //   7: aload_2
      //   8: ifnull -> 15
      //   11: ldc com/google/firebase/iid/FirebaseInstanceIdService$zza
      //   13: monitorexit
      //   14: return
      //   15: new com/google/firebase/iid/FirebaseInstanceIdService$zza
      //   18: astore_2
      //   19: aload_2
      //   20: iload_1
      //   21: invokespecial <init> : (I)V
      //   24: aload_2
      //   25: putstatic com/google/firebase/iid/FirebaseInstanceIdService$zza.receiver : Landroid/content/BroadcastReceiver;
      //   28: aload_0
      //   29: invokevirtual getApplicationContext : ()Landroid/content/Context;
      //   32: astore_2
      //   33: getstatic com/google/firebase/iid/FirebaseInstanceIdService$zza.receiver : Landroid/content/BroadcastReceiver;
      //   36: astore_0
      //   37: new android/content/IntentFilter
      //   40: astore_3
      //   41: aload_3
      //   42: ldc 'android.net.conn.CONNECTIVITY_CHANGE'
      //   44: invokespecial <init> : (Ljava/lang/String;)V
      //   47: aload_2
      //   48: aload_0
      //   49: aload_3
      //   50: invokevirtual registerReceiver : (Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;
      //   53: pop
      //   54: ldc com/google/firebase/iid/FirebaseInstanceIdService$zza
      //   56: monitorexit
      //   57: return
      //   58: astore_0
      //   59: ldc com/google/firebase/iid/FirebaseInstanceIdService$zza
      //   61: monitorexit
      //   62: aload_0
      //   63: athrow
      // Exception table:
      //   from	to	target	type
      //   3	7	58	finally
      //   15	54	58	finally
    }
    
    public void onReceive(Context param1Context, Intent param1Intent) {
      // Byte code:
      //   0: ldc com/google/firebase/iid/FirebaseInstanceIdService$zza
      //   2: monitorenter
      //   3: getstatic com/google/firebase/iid/FirebaseInstanceIdService$zza.receiver : Landroid/content/BroadcastReceiver;
      //   6: aload_0
      //   7: if_acmpeq -> 14
      //   10: ldc com/google/firebase/iid/FirebaseInstanceIdService$zza
      //   12: monitorexit
      //   13: return
      //   14: aload_1
      //   15: invokestatic zzeo : (Landroid/content/Context;)Z
      //   18: ifne -> 25
      //   21: ldc com/google/firebase/iid/FirebaseInstanceIdService$zza
      //   23: monitorexit
      //   24: return
      //   25: ldc 'FirebaseInstanceId'
      //   27: iconst_3
      //   28: invokestatic isLoggable : (Ljava/lang/String;I)Z
      //   31: ifeq -> 42
      //   34: ldc 'FirebaseInstanceId'
      //   36: ldc 'connectivity changed. starting background sync.'
      //   38: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
      //   41: pop
      //   42: aload_1
      //   43: invokevirtual getApplicationContext : ()Landroid/content/Context;
      //   46: aload_0
      //   47: invokevirtual unregisterReceiver : (Landroid/content/BroadcastReceiver;)V
      //   50: aconst_null
      //   51: putstatic com/google/firebase/iid/FirebaseInstanceIdService$zza.receiver : Landroid/content/BroadcastReceiver;
      //   54: ldc com/google/firebase/iid/FirebaseInstanceIdService$zza
      //   56: monitorexit
      //   57: invokestatic zzcge : ()Lcom/google/firebase/iid/zzq;
      //   60: aload_1
      //   61: aload_0
      //   62: getfield zznfs : I
      //   65: invokestatic zzhl : (I)Landroid/content/Intent;
      //   68: invokevirtual zze : (Landroid/content/Context;Landroid/content/Intent;)V
      //   71: return
      //   72: astore_1
      //   73: ldc com/google/firebase/iid/FirebaseInstanceIdService$zza
      //   75: monitorexit
      //   76: aload_1
      //   77: athrow
      // Exception table:
      //   from	to	target	type
      //   3	13	72	finally
      //   14	24	72	finally
      //   25	42	72	finally
      //   42	57	72	finally
      //   73	76	72	finally
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/firebase/iid/FirebaseInstanceIdService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */