package com.google.firebase.iid;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import java.util.LinkedList;
import java.util.Queue;

public final class zzq {
  private static zzq zznga;
  
  private final SimpleArrayMap<String, String> zzngb = new SimpleArrayMap();
  
  private Boolean zzngc = null;
  
  @VisibleForTesting
  final Queue<Intent> zzngd = new LinkedList<Intent>();
  
  @VisibleForTesting
  private Queue<Intent> zznge = new LinkedList<Intent>();
  
  public static PendingIntent zza(Context paramContext, int paramInt1, Intent paramIntent, int paramInt2) {
    return zza(paramContext, 0, "com.google.firebase.INSTANCE_ID_EVENT", paramIntent, 134217728);
  }
  
  private static PendingIntent zza(Context paramContext, int paramInt1, String paramString, Intent paramIntent, int paramInt2) {
    Intent intent = new Intent(paramContext, FirebaseInstanceIdInternalReceiver.class);
    intent.setAction(paramString);
    intent.putExtra("wrapped_intent", (Parcelable)paramIntent);
    return PendingIntent.getBroadcast(paramContext, paramInt1, intent, paramInt2);
  }
  
  public static PendingIntent zzb(Context paramContext, int paramInt1, Intent paramIntent, int paramInt2) {
    return zza(paramContext, paramInt1, "com.google.firebase.MESSAGING_EVENT", paramIntent, 1073741824);
  }
  
  public static zzq zzcge() {
    // Byte code:
    //   0: ldc com/google/firebase/iid/zzq
    //   2: monitorenter
    //   3: getstatic com/google/firebase/iid/zzq.zznga : Lcom/google/firebase/iid/zzq;
    //   6: ifnonnull -> 21
    //   9: new com/google/firebase/iid/zzq
    //   12: astore_0
    //   13: aload_0
    //   14: invokespecial <init> : ()V
    //   17: aload_0
    //   18: putstatic com/google/firebase/iid/zzq.zznga : Lcom/google/firebase/iid/zzq;
    //   21: getstatic com/google/firebase/iid/zzq.zznga : Lcom/google/firebase/iid/zzq;
    //   24: astore_0
    //   25: ldc com/google/firebase/iid/zzq
    //   27: monitorexit
    //   28: aload_0
    //   29: areturn
    //   30: astore_0
    //   31: ldc com/google/firebase/iid/zzq
    //   33: monitorexit
    //   34: aload_0
    //   35: athrow
    // Exception table:
    //   from	to	target	type
    //   3	21	30	finally
    //   21	25	30	finally
  }
  
  private final int zzf(Context paramContext, Intent paramIntent) {
    // Byte code:
    //   0: aload_0
    //   1: getfield zzngb : Landroid/support/v4/util/SimpleArrayMap;
    //   4: astore_3
    //   5: aload_3
    //   6: monitorenter
    //   7: aload_0
    //   8: getfield zzngb : Landroid/support/v4/util/SimpleArrayMap;
    //   11: aload_2
    //   12: invokevirtual getAction : ()Ljava/lang/String;
    //   15: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   18: checkcast java/lang/String
    //   21: astore #4
    //   23: aload_3
    //   24: monitorexit
    //   25: iconst_0
    //   26: istore #5
    //   28: aload #4
    //   30: astore_3
    //   31: aload #4
    //   33: ifnonnull -> 273
    //   36: aload_1
    //   37: invokevirtual getPackageManager : ()Landroid/content/pm/PackageManager;
    //   40: aload_2
    //   41: iconst_0
    //   42: invokevirtual resolveService : (Landroid/content/Intent;I)Landroid/content/pm/ResolveInfo;
    //   45: astore_3
    //   46: aload_3
    //   47: ifnull -> 262
    //   50: aload_3
    //   51: getfield serviceInfo : Landroid/content/pm/ServiceInfo;
    //   54: astore #6
    //   56: aload #6
    //   58: ifnonnull -> 64
    //   61: goto -> 262
    //   64: aload_1
    //   65: invokevirtual getPackageName : ()Ljava/lang/String;
    //   68: aload #6
    //   70: getfield packageName : Ljava/lang/String;
    //   73: invokevirtual equals : (Ljava/lang/Object;)Z
    //   76: ifeq -> 176
    //   79: aload #6
    //   81: getfield name : Ljava/lang/String;
    //   84: astore #4
    //   86: aload #4
    //   88: ifnonnull -> 94
    //   91: goto -> 176
    //   94: aload #4
    //   96: astore_3
    //   97: aload #4
    //   99: ldc '.'
    //   101: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   104: ifeq -> 142
    //   107: aload_1
    //   108: invokevirtual getPackageName : ()Ljava/lang/String;
    //   111: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   114: astore_3
    //   115: aload #4
    //   117: invokevirtual length : ()I
    //   120: ifeq -> 133
    //   123: aload_3
    //   124: aload #4
    //   126: invokevirtual concat : (Ljava/lang/String;)Ljava/lang/String;
    //   129: astore_3
    //   130: goto -> 142
    //   133: new java/lang/String
    //   136: dup
    //   137: aload_3
    //   138: invokespecial <init> : (Ljava/lang/String;)V
    //   141: astore_3
    //   142: aload_0
    //   143: getfield zzngb : Landroid/support/v4/util/SimpleArrayMap;
    //   146: astore #4
    //   148: aload #4
    //   150: monitorenter
    //   151: aload_0
    //   152: getfield zzngb : Landroid/support/v4/util/SimpleArrayMap;
    //   155: aload_2
    //   156: invokevirtual getAction : ()Ljava/lang/String;
    //   159: aload_3
    //   160: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   163: pop
    //   164: aload #4
    //   166: monitorexit
    //   167: goto -> 273
    //   170: astore_1
    //   171: aload #4
    //   173: monitorexit
    //   174: aload_1
    //   175: athrow
    //   176: aload #6
    //   178: getfield packageName : Ljava/lang/String;
    //   181: astore_3
    //   182: aload #6
    //   184: getfield name : Ljava/lang/String;
    //   187: astore #4
    //   189: new java/lang/StringBuilder
    //   192: dup
    //   193: aload_3
    //   194: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   197: invokevirtual length : ()I
    //   200: bipush #94
    //   202: iadd
    //   203: aload #4
    //   205: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   208: invokevirtual length : ()I
    //   211: iadd
    //   212: invokespecial <init> : (I)V
    //   215: astore #6
    //   217: aload #6
    //   219: ldc 'Error resolving target intent service, skipping classname enforcement. Resolved service was: '
    //   221: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   224: pop
    //   225: aload #6
    //   227: aload_3
    //   228: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   231: pop
    //   232: aload #6
    //   234: ldc '/'
    //   236: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   239: pop
    //   240: aload #6
    //   242: aload #4
    //   244: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   247: pop
    //   248: ldc 'FirebaseInstanceId'
    //   250: aload #6
    //   252: invokevirtual toString : ()Ljava/lang/String;
    //   255: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   258: pop
    //   259: goto -> 337
    //   262: ldc 'FirebaseInstanceId'
    //   264: ldc 'Failed to resolve target intent service, skipping classname enforcement'
    //   266: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   269: pop
    //   270: goto -> 337
    //   273: ldc 'FirebaseInstanceId'
    //   275: iconst_3
    //   276: invokestatic isLoggable : (Ljava/lang/String;I)Z
    //   279: ifeq -> 327
    //   282: aload_3
    //   283: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   286: astore #4
    //   288: aload #4
    //   290: invokevirtual length : ()I
    //   293: ifeq -> 308
    //   296: ldc 'Restricting intent to a specific service: '
    //   298: aload #4
    //   300: invokevirtual concat : (Ljava/lang/String;)Ljava/lang/String;
    //   303: astore #4
    //   305: goto -> 319
    //   308: new java/lang/String
    //   311: dup
    //   312: ldc 'Restricting intent to a specific service: '
    //   314: invokespecial <init> : (Ljava/lang/String;)V
    //   317: astore #4
    //   319: ldc 'FirebaseInstanceId'
    //   321: aload #4
    //   323: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   326: pop
    //   327: aload_2
    //   328: aload_1
    //   329: invokevirtual getPackageName : ()Ljava/lang/String;
    //   332: aload_3
    //   333: invokevirtual setClassName : (Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   336: pop
    //   337: aload_0
    //   338: getfield zzngc : Ljava/lang/Boolean;
    //   341: ifnonnull -> 365
    //   344: aload_1
    //   345: ldc 'android.permission.WAKE_LOCK'
    //   347: invokevirtual checkCallingOrSelfPermission : (Ljava/lang/String;)I
    //   350: ifne -> 356
    //   353: iconst_1
    //   354: istore #5
    //   356: aload_0
    //   357: iload #5
    //   359: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   362: putfield zzngc : Ljava/lang/Boolean;
    //   365: aload_0
    //   366: getfield zzngc : Ljava/lang/Boolean;
    //   369: invokevirtual booleanValue : ()Z
    //   372: ifeq -> 384
    //   375: aload_1
    //   376: aload_2
    //   377: invokestatic startWakefulService : (Landroid/content/Context;Landroid/content/Intent;)Landroid/content/ComponentName;
    //   380: astore_1
    //   381: goto -> 398
    //   384: aload_1
    //   385: aload_2
    //   386: invokevirtual startService : (Landroid/content/Intent;)Landroid/content/ComponentName;
    //   389: astore_1
    //   390: ldc 'FirebaseInstanceId'
    //   392: ldc 'Missing wake lock permission, service start may be delayed'
    //   394: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   397: pop
    //   398: aload_1
    //   399: ifnonnull -> 414
    //   402: ldc 'FirebaseInstanceId'
    //   404: ldc 'Error while delivering the message: ServiceIntent not found.'
    //   406: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   409: pop
    //   410: sipush #404
    //   413: ireturn
    //   414: iconst_m1
    //   415: ireturn
    //   416: astore_1
    //   417: aload_1
    //   418: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   421: astore_1
    //   422: new java/lang/StringBuilder
    //   425: dup
    //   426: aload_1
    //   427: invokevirtual length : ()I
    //   430: bipush #45
    //   432: iadd
    //   433: invokespecial <init> : (I)V
    //   436: astore_2
    //   437: aload_2
    //   438: ldc 'Failed to start service while in background: '
    //   440: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   443: pop
    //   444: aload_2
    //   445: aload_1
    //   446: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   449: pop
    //   450: ldc 'FirebaseInstanceId'
    //   452: aload_2
    //   453: invokevirtual toString : ()Ljava/lang/String;
    //   456: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   459: pop
    //   460: sipush #402
    //   463: ireturn
    //   464: astore_1
    //   465: ldc 'FirebaseInstanceId'
    //   467: ldc 'Error while delivering the message to the serviceIntent'
    //   469: aload_1
    //   470: invokestatic e : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   473: pop
    //   474: sipush #401
    //   477: ireturn
    //   478: astore_1
    //   479: aload_3
    //   480: monitorexit
    //   481: aload_1
    //   482: athrow
    // Exception table:
    //   from	to	target	type
    //   7	25	478	finally
    //   151	167	170	finally
    //   171	174	170	finally
    //   337	344	464	java/lang/SecurityException
    //   337	344	416	java/lang/IllegalStateException
    //   344	353	464	java/lang/SecurityException
    //   344	353	416	java/lang/IllegalStateException
    //   356	365	464	java/lang/SecurityException
    //   356	365	416	java/lang/IllegalStateException
    //   365	381	464	java/lang/SecurityException
    //   365	381	416	java/lang/IllegalStateException
    //   384	398	464	java/lang/SecurityException
    //   384	398	416	java/lang/IllegalStateException
    //   402	410	464	java/lang/SecurityException
    //   402	410	416	java/lang/IllegalStateException
    //   479	481	478	finally
  }
  
  public final int zza(Context paramContext, String paramString, Intent paramIntent) {
    String str;
    Queue<Intent> queue;
    paramString.hashCode();
    if (!paramString.equals("com.google.firebase.INSTANCE_ID_EVENT")) {
      if (!paramString.equals("com.google.firebase.MESSAGING_EVENT")) {
        if (paramString.length() != 0) {
          str = "Unknown service action: ".concat(paramString);
        } else {
          str = new String("Unknown service action: ");
        } 
        Log.w("FirebaseInstanceId", str);
        return 500;
      } 
      queue = this.zznge;
    } else {
      queue = this.zzngd;
    } 
    queue.offer(paramIntent);
    Intent intent = new Intent(paramString);
    intent.setPackage(str.getPackageName());
    return zzf((Context)str, intent);
  }
  
  public final Intent zzcgf() {
    return this.zznge.poll();
  }
  
  public final void zze(Context paramContext, Intent paramIntent) {
    zza(paramContext, "com.google.firebase.INSTANCE_ID_EVENT", paramIntent);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/firebase/iid/zzq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */