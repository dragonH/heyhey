package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

public final class zzdj {
  public static final Status zzfpq = new Status(8, "The connection to Google Play services was lost");
  
  private static final zzs<?>[] zzfpr = (zzs<?>[])new zzs[0];
  
  private final Map<Api.zzc<?>, Api.zze> zzfmn;
  
  final Set<zzs<?>> zzfps = Collections.synchronizedSet(Collections.newSetFromMap(new WeakHashMap<zzs<?>, Boolean>()));
  
  private final zzdm zzfpt = new zzdk(this);
  
  public zzdj(Map<Api.zzc<?>, Api.zze> paramMap) {
    this.zzfmn = paramMap;
  }
  
  public final void release() {
    // Byte code:
    //   0: aload_0
    //   1: getfield zzfps : Ljava/util/Set;
    //   4: getstatic com/google/android/gms/common/api/internal/zzdj.zzfpr : [Lcom/google/android/gms/common/api/internal/zzs;
    //   7: invokeinterface toArray : ([Ljava/lang/Object;)[Ljava/lang/Object;
    //   12: checkcast [Lcom/google/android/gms/common/api/internal/zzs;
    //   15: astore_1
    //   16: aload_1
    //   17: arraylength
    //   18: istore_2
    //   19: iconst_0
    //   20: istore_3
    //   21: iload_3
    //   22: iload_2
    //   23: if_icmpge -> 226
    //   26: aload_1
    //   27: iload_3
    //   28: aaload
    //   29: astore #4
    //   31: aload #4
    //   33: aconst_null
    //   34: invokevirtual zza : (Lcom/google/android/gms/common/api/internal/zzdm;)V
    //   37: aload #4
    //   39: invokevirtual zzafs : ()Ljava/lang/Integer;
    //   42: ifnonnull -> 68
    //   45: aload #4
    //   47: invokevirtual zzagf : ()Z
    //   50: ifeq -> 180
    //   53: aload_0
    //   54: getfield zzfps : Ljava/util/Set;
    //   57: aload #4
    //   59: invokeinterface remove : (Ljava/lang/Object;)Z
    //   64: pop
    //   65: goto -> 180
    //   68: aload #4
    //   70: aconst_null
    //   71: invokevirtual setResultCallback : (Lcom/google/android/gms/common/api/ResultCallback;)V
    //   74: aload_0
    //   75: getfield zzfmn : Ljava/util/Map;
    //   78: aload #4
    //   80: checkcast com/google/android/gms/common/api/internal/zzm
    //   83: invokevirtual zzafe : ()Lcom/google/android/gms/common/api/Api$zzc;
    //   86: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   91: checkcast com/google/android/gms/common/api/Api$zze
    //   94: invokeinterface zzafg : ()Landroid/os/IBinder;
    //   99: astore #5
    //   101: aload #4
    //   103: invokevirtual isReady : ()Z
    //   106: ifeq -> 130
    //   109: aload #4
    //   111: new com/google/android/gms/common/api/internal/zzdl
    //   114: dup
    //   115: aload #4
    //   117: aconst_null
    //   118: aload #5
    //   120: aconst_null
    //   121: invokespecial <init> : (Lcom/google/android/gms/common/api/internal/zzs;Lcom/google/android/gms/common/api/zze;Landroid/os/IBinder;Lcom/google/android/gms/common/api/internal/zzdk;)V
    //   124: invokevirtual zza : (Lcom/google/android/gms/common/api/internal/zzdm;)V
    //   127: goto -> 53
    //   130: aload #5
    //   132: ifnull -> 204
    //   135: aload #5
    //   137: invokeinterface isBinderAlive : ()Z
    //   142: ifeq -> 204
    //   145: new com/google/android/gms/common/api/internal/zzdl
    //   148: dup
    //   149: aload #4
    //   151: aconst_null
    //   152: aload #5
    //   154: aconst_null
    //   155: invokespecial <init> : (Lcom/google/android/gms/common/api/internal/zzs;Lcom/google/android/gms/common/api/zze;Landroid/os/IBinder;Lcom/google/android/gms/common/api/internal/zzdk;)V
    //   158: astore #6
    //   160: aload #4
    //   162: aload #6
    //   164: invokevirtual zza : (Lcom/google/android/gms/common/api/internal/zzdm;)V
    //   167: aload #5
    //   169: aload #6
    //   171: iconst_0
    //   172: invokeinterface linkToDeath : (Landroid/os/IBinder$DeathRecipient;I)V
    //   177: goto -> 53
    //   180: iinc #3, 1
    //   183: goto -> 21
    //   186: astore #6
    //   188: aload #4
    //   190: invokevirtual cancel : ()V
    //   193: aload #4
    //   195: invokevirtual zzafs : ()Ljava/lang/Integer;
    //   198: invokevirtual intValue : ()I
    //   201: pop
    //   202: aconst_null
    //   203: athrow
    //   204: aload #4
    //   206: aconst_null
    //   207: invokevirtual zza : (Lcom/google/android/gms/common/api/internal/zzdm;)V
    //   210: aload #4
    //   212: invokevirtual cancel : ()V
    //   215: aload #4
    //   217: invokevirtual zzafs : ()Ljava/lang/Integer;
    //   220: invokevirtual intValue : ()I
    //   223: pop
    //   224: aconst_null
    //   225: athrow
    //   226: return
    // Exception table:
    //   from	to	target	type
    //   167	177	186	android/os/RemoteException
  }
  
  public final void zzaiq() {
    zzs[] arrayOfZzs = this.zzfps.<zzs>toArray((zzs[])zzfpr);
    int i = arrayOfZzs.length;
    for (byte b = 0; b < i; b++)
      arrayOfZzs[b].zzu(zzfpq); 
  }
  
  final void zzb(zzs<? extends Result> paramzzs) {
    this.zzfps.add(paramzzs);
    paramzzs.zza(this.zzfpt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzdj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */