package com.google.firebase.iid;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public final class zzh implements ServiceConnection {
  private final Context zzahz;
  
  private final Intent zznfd;
  
  private final ScheduledExecutorService zznfe;
  
  private final Queue<zzd> zznff = new LinkedList<zzd>();
  
  private zzf zznfg;
  
  private boolean zznfh = false;
  
  public zzh(Context paramContext, String paramString) {
    this(paramContext, paramString, new ScheduledThreadPoolExecutor(0));
  }
  
  @VisibleForTesting
  private zzh(Context paramContext, String paramString, ScheduledExecutorService paramScheduledExecutorService) {
    paramContext = paramContext.getApplicationContext();
    this.zzahz = paramContext;
    this.zznfd = (new Intent(paramString)).setPackage(paramContext.getPackageName());
    this.zznfe = paramScheduledExecutorService;
  }
  
  private final void zzcfv() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: ldc 'EnhancedIntentService'
    //   4: iconst_3
    //   5: invokestatic isLoggable : (Ljava/lang/String;I)Z
    //   8: ifeq -> 19
    //   11: ldc 'EnhancedIntentService'
    //   13: ldc 'flush queue called'
    //   15: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   18: pop
    //   19: aload_0
    //   20: getfield zznff : Ljava/util/Queue;
    //   23: invokeinterface isEmpty : ()Z
    //   28: ifne -> 252
    //   31: ldc 'EnhancedIntentService'
    //   33: iconst_3
    //   34: invokestatic isLoggable : (Ljava/lang/String;I)Z
    //   37: ifeq -> 48
    //   40: ldc 'EnhancedIntentService'
    //   42: ldc 'found intent to be delivered'
    //   44: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   47: pop
    //   48: aload_0
    //   49: getfield zznfg : Lcom/google/firebase/iid/zzf;
    //   52: astore_1
    //   53: aload_1
    //   54: ifnull -> 105
    //   57: aload_1
    //   58: invokevirtual isBinderAlive : ()Z
    //   61: ifeq -> 105
    //   64: ldc 'EnhancedIntentService'
    //   66: iconst_3
    //   67: invokestatic isLoggable : (Ljava/lang/String;I)Z
    //   70: ifeq -> 81
    //   73: ldc 'EnhancedIntentService'
    //   75: ldc 'binder is alive, sending the intent.'
    //   77: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   80: pop
    //   81: aload_0
    //   82: getfield zznff : Ljava/util/Queue;
    //   85: invokeinterface poll : ()Ljava/lang/Object;
    //   90: checkcast com/google/firebase/iid/zzd
    //   93: astore_1
    //   94: aload_0
    //   95: getfield zznfg : Lcom/google/firebase/iid/zzf;
    //   98: aload_1
    //   99: invokevirtual zza : (Lcom/google/firebase/iid/zzd;)V
    //   102: goto -> 19
    //   105: ldc 'EnhancedIntentService'
    //   107: iconst_3
    //   108: invokestatic isLoggable : (Ljava/lang/String;I)Z
    //   111: ifeq -> 161
    //   114: aload_0
    //   115: getfield zznfh : Z
    //   118: ifne -> 126
    //   121: iconst_1
    //   122: istore_2
    //   123: goto -> 128
    //   126: iconst_0
    //   127: istore_2
    //   128: new java/lang/StringBuilder
    //   131: astore_1
    //   132: aload_1
    //   133: bipush #39
    //   135: invokespecial <init> : (I)V
    //   138: aload_1
    //   139: ldc 'binder is dead. start connection? '
    //   141: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   144: pop
    //   145: aload_1
    //   146: iload_2
    //   147: invokevirtual append : (Z)Ljava/lang/StringBuilder;
    //   150: pop
    //   151: ldc 'EnhancedIntentService'
    //   153: aload_1
    //   154: invokevirtual toString : ()Ljava/lang/String;
    //   157: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   160: pop
    //   161: aload_0
    //   162: getfield zznfh : Z
    //   165: ifne -> 249
    //   168: aload_0
    //   169: iconst_1
    //   170: putfield zznfh : Z
    //   173: invokestatic zzaky : ()Lcom/google/android/gms/common/stats/zza;
    //   176: aload_0
    //   177: getfield zzahz : Landroid/content/Context;
    //   180: aload_0
    //   181: getfield zznfd : Landroid/content/Intent;
    //   184: aload_0
    //   185: bipush #65
    //   187: invokevirtual zza : (Landroid/content/Context;Landroid/content/Intent;Landroid/content/ServiceConnection;I)Z
    //   190: istore_2
    //   191: iload_2
    //   192: ifeq -> 198
    //   195: aload_0
    //   196: monitorexit
    //   197: return
    //   198: ldc 'EnhancedIntentService'
    //   200: ldc 'binding to the service failed'
    //   202: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   205: pop
    //   206: goto -> 219
    //   209: astore_1
    //   210: ldc 'EnhancedIntentService'
    //   212: ldc 'Exception while binding the service'
    //   214: aload_1
    //   215: invokestatic e : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   218: pop
    //   219: aload_0
    //   220: getfield zznff : Ljava/util/Queue;
    //   223: invokeinterface isEmpty : ()Z
    //   228: ifne -> 249
    //   231: aload_0
    //   232: getfield zznff : Ljava/util/Queue;
    //   235: invokeinterface poll : ()Ljava/lang/Object;
    //   240: checkcast com/google/firebase/iid/zzd
    //   243: invokevirtual finish : ()V
    //   246: goto -> 219
    //   249: aload_0
    //   250: monitorexit
    //   251: return
    //   252: aload_0
    //   253: monitorexit
    //   254: return
    //   255: astore_1
    //   256: aload_0
    //   257: monitorexit
    //   258: goto -> 263
    //   261: aload_1
    //   262: athrow
    //   263: goto -> 261
    // Exception table:
    //   from	to	target	type
    //   2	19	255	finally
    //   19	48	255	finally
    //   48	53	255	finally
    //   57	81	255	finally
    //   81	102	255	finally
    //   105	121	255	finally
    //   128	161	255	finally
    //   161	173	255	finally
    //   173	191	209	java/lang/SecurityException
    //   173	191	255	finally
    //   198	206	209	java/lang/SecurityException
    //   198	206	255	finally
    //   210	219	255	finally
    //   219	246	255	finally
  }
  
  public final void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: iconst_0
    //   4: putfield zznfh : Z
    //   7: aload_0
    //   8: aload_2
    //   9: checkcast com/google/firebase/iid/zzf
    //   12: putfield zznfg : Lcom/google/firebase/iid/zzf;
    //   15: ldc 'EnhancedIntentService'
    //   17: iconst_3
    //   18: invokestatic isLoggable : (Ljava/lang/String;I)Z
    //   21: ifeq -> 69
    //   24: aload_1
    //   25: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   28: astore_2
    //   29: aload_2
    //   30: invokevirtual length : ()I
    //   33: istore_3
    //   34: new java/lang/StringBuilder
    //   37: astore_1
    //   38: aload_1
    //   39: iload_3
    //   40: bipush #20
    //   42: iadd
    //   43: invokespecial <init> : (I)V
    //   46: aload_1
    //   47: ldc 'onServiceConnected: '
    //   49: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   52: pop
    //   53: aload_1
    //   54: aload_2
    //   55: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   58: pop
    //   59: ldc 'EnhancedIntentService'
    //   61: aload_1
    //   62: invokevirtual toString : ()Ljava/lang/String;
    //   65: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   68: pop
    //   69: aload_0
    //   70: invokespecial zzcfv : ()V
    //   73: aload_0
    //   74: monitorexit
    //   75: return
    //   76: astore_1
    //   77: aload_0
    //   78: monitorexit
    //   79: aload_1
    //   80: athrow
    // Exception table:
    //   from	to	target	type
    //   2	69	76	finally
    //   69	75	76	finally
    //   77	79	76	finally
  }
  
  public final void onServiceDisconnected(ComponentName paramComponentName) {
    if (Log.isLoggable("EnhancedIntentService", 3)) {
      String str = String.valueOf(paramComponentName);
      StringBuilder stringBuilder = new StringBuilder(str.length() + 23);
      stringBuilder.append("onServiceDisconnected: ");
      stringBuilder.append(str);
      Log.d("EnhancedIntentService", stringBuilder.toString());
    } 
    zzcfv();
  }
  
  public final void zza(Intent paramIntent, BroadcastReceiver.PendingResult paramPendingResult) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: ldc 'EnhancedIntentService'
    //   4: iconst_3
    //   5: invokestatic isLoggable : (Ljava/lang/String;I)Z
    //   8: ifeq -> 19
    //   11: ldc 'EnhancedIntentService'
    //   13: ldc 'new intent queued in the bind-strategy delivery'
    //   15: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   18: pop
    //   19: aload_0
    //   20: getfield zznff : Ljava/util/Queue;
    //   23: astore_3
    //   24: new com/google/firebase/iid/zzd
    //   27: astore #4
    //   29: aload #4
    //   31: aload_1
    //   32: aload_2
    //   33: aload_0
    //   34: getfield zznfe : Ljava/util/concurrent/ScheduledExecutorService;
    //   37: invokespecial <init> : (Landroid/content/Intent;Landroid/content/BroadcastReceiver$PendingResult;Ljava/util/concurrent/ScheduledExecutorService;)V
    //   40: aload_3
    //   41: aload #4
    //   43: invokeinterface add : (Ljava/lang/Object;)Z
    //   48: pop
    //   49: aload_0
    //   50: invokespecial zzcfv : ()V
    //   53: aload_0
    //   54: monitorexit
    //   55: return
    //   56: astore_1
    //   57: aload_0
    //   58: monitorexit
    //   59: aload_1
    //   60: athrow
    // Exception table:
    //   from	to	target	type
    //   2	19	56	finally
    //   19	53	56	finally
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/firebase/iid/zzh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */