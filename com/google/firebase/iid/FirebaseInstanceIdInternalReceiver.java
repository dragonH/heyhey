package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;
import com.google.android.gms.common.util.zzq;

public final class FirebaseInstanceIdInternalReceiver extends WakefulBroadcastReceiver {
  private static boolean zzhqy = false;
  
  private static zzh zznfn;
  
  private static zzh zznfo;
  
  static zzh zzag(Context paramContext, String paramString) {
    // Byte code:
    //   0: ldc com/google/firebase/iid/FirebaseInstanceIdInternalReceiver
    //   2: monitorenter
    //   3: ldc 'com.google.firebase.MESSAGING_EVENT'
    //   5: aload_1
    //   6: invokevirtual equals : (Ljava/lang/Object;)Z
    //   9: ifeq -> 41
    //   12: getstatic com/google/firebase/iid/FirebaseInstanceIdInternalReceiver.zznfo : Lcom/google/firebase/iid/zzh;
    //   15: ifnonnull -> 32
    //   18: new com/google/firebase/iid/zzh
    //   21: astore_2
    //   22: aload_2
    //   23: aload_0
    //   24: aload_1
    //   25: invokespecial <init> : (Landroid/content/Context;Ljava/lang/String;)V
    //   28: aload_2
    //   29: putstatic com/google/firebase/iid/FirebaseInstanceIdInternalReceiver.zznfo : Lcom/google/firebase/iid/zzh;
    //   32: getstatic com/google/firebase/iid/FirebaseInstanceIdInternalReceiver.zznfo : Lcom/google/firebase/iid/zzh;
    //   35: astore_0
    //   36: ldc com/google/firebase/iid/FirebaseInstanceIdInternalReceiver
    //   38: monitorexit
    //   39: aload_0
    //   40: areturn
    //   41: getstatic com/google/firebase/iid/FirebaseInstanceIdInternalReceiver.zznfn : Lcom/google/firebase/iid/zzh;
    //   44: ifnonnull -> 61
    //   47: new com/google/firebase/iid/zzh
    //   50: astore_2
    //   51: aload_2
    //   52: aload_0
    //   53: aload_1
    //   54: invokespecial <init> : (Landroid/content/Context;Ljava/lang/String;)V
    //   57: aload_2
    //   58: putstatic com/google/firebase/iid/FirebaseInstanceIdInternalReceiver.zznfn : Lcom/google/firebase/iid/zzh;
    //   61: getstatic com/google/firebase/iid/FirebaseInstanceIdInternalReceiver.zznfn : Lcom/google/firebase/iid/zzh;
    //   64: astore_0
    //   65: ldc com/google/firebase/iid/FirebaseInstanceIdInternalReceiver
    //   67: monitorexit
    //   68: aload_0
    //   69: areturn
    //   70: astore_0
    //   71: ldc com/google/firebase/iid/FirebaseInstanceIdInternalReceiver
    //   73: monitorexit
    //   74: aload_0
    //   75: athrow
    // Exception table:
    //   from	to	target	type
    //   3	32	70	finally
    //   32	36	70	finally
    //   41	61	70	finally
    //   61	65	70	finally
  }
  
  static boolean zzel(Context paramContext) {
    return !zzq.isAtLeastO() ? false : (((paramContext.getApplicationInfo()).targetSdkVersion > 25));
  }
  
  public final void onReceive(Context paramContext, Intent paramIntent) {
    if (paramIntent == null)
      return; 
    Parcelable parcelable = paramIntent.getParcelableExtra("wrapped_intent");
    if (!(parcelable instanceof Intent)) {
      Log.e("FirebaseInstanceId", "Missing or invalid wrapped intent");
      return;
    } 
    Intent intent = (Intent)parcelable;
    if (zzel(paramContext)) {
      zzag(paramContext, paramIntent.getAction()).zza(intent, goAsync());
      return;
    } 
    zzq.zzcge().zza(paramContext, paramIntent.getAction(), intent);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/firebase/iid/FirebaseInstanceIdInternalReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */