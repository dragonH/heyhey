package com.google.android.gms.common.api.internal;

import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArraySet;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzcps;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public final class zzbp implements Handler.Callback {
  private static final Object zzaqd;
  
  public static final Status zzfnk = new Status(4, "Sign-out occurred while this API call was in progress.");
  
  private static final Status zzfnl = new Status(4, "The user must be signed in to make this API call.");
  
  private static zzbp zzfnn;
  
  private final Context mContext;
  
  private final Handler mHandler;
  
  private final GoogleApiAvailability zzfhl;
  
  private final Map<zzh<?>, zzbr<?>> zzfkk = new ConcurrentHashMap<zzh<?>, zzbr<?>>(5, 0.75F, 1);
  
  private long zzfmj = 120000L;
  
  private long zzfmk = 5000L;
  
  private long zzfnm = 10000L;
  
  private int zzfno = -1;
  
  private final AtomicInteger zzfnp = new AtomicInteger(1);
  
  private final AtomicInteger zzfnq = new AtomicInteger(0);
  
  private zzak zzfnr = null;
  
  private final Set<zzh<?>> zzfns = (Set<zzh<?>>)new ArraySet();
  
  private final Set<zzh<?>> zzfnt = (Set<zzh<?>>)new ArraySet();
  
  static {
    zzaqd = new Object();
  }
  
  private zzbp(Context paramContext, Looper paramLooper, GoogleApiAvailability paramGoogleApiAvailability) {
    this.mContext = paramContext;
    Handler handler = new Handler(paramLooper, this);
    this.mHandler = handler;
    this.zzfhl = paramGoogleApiAvailability;
    handler.sendMessage(handler.obtainMessage(6));
  }
  
  public static zzbp zzaho() {
    synchronized (zzaqd) {
      com.google.android.gms.common.internal.zzbp.zzb(zzfnn, "Must guarantee manager is non-null before using getInstance");
      return zzfnn;
    } 
  }
  
  public static void zzahp() {
    synchronized (zzaqd) {
      zzbp zzbp1 = zzfnn;
      if (zzbp1 != null) {
        zzbp1.zzfnq.incrementAndGet();
        Handler handler = zzbp1.mHandler;
        handler.sendMessageAtFrontOfQueue(handler.obtainMessage(10));
      } 
      return;
    } 
  }
  
  @WorkerThread
  private final void zzahr() {
    for (zzh<?> zzh : this.zzfnt)
      ((zzbr)this.zzfkk.remove(zzh)).signOut(); 
    this.zzfnt.clear();
  }
  
  @WorkerThread
  private final void zzb(GoogleApi<?> paramGoogleApi) {
    zzh<?> zzh = paramGoogleApi.zzafk();
    zzbr<?> zzbr1 = this.zzfkk.get(zzh);
    zzbr<?> zzbr2 = zzbr1;
    if (zzbr1 == null) {
      zzbr2 = new zzbr(this, paramGoogleApi);
      this.zzfkk.put(zzh, zzbr2);
    } 
    if (zzbr2.zzaac())
      this.zzfnt.add(zzh); 
    zzbr2.connect();
  }
  
  public static zzbp zzca(Context paramContext) {
    synchronized (zzaqd) {
      if (zzfnn == null) {
        HandlerThread handlerThread = new HandlerThread();
        this("GoogleApiHandler", 9);
        handlerThread.start();
        Looper looper = handlerThread.getLooper();
        zzbp zzbp1 = new zzbp();
        this(paramContext.getApplicationContext(), looper, GoogleApiAvailability.getInstance());
        zzfnn = zzbp1;
      } 
      return zzfnn;
    } 
  }
  
  @WorkerThread
  public final boolean handleMessage(Message paramMessage) {
    StringBuilder stringBuilder2;
    zzbr zzbr;
    StringBuilder stringBuilder1;
    zzbr<?> zzbr1;
    ConnectionResult connectionResult1;
    ConnectionResult connectionResult2;
    zzcq zzcq;
    StringBuilder stringBuilder3;
    Iterator<zzbr> iterator;
    zzj zzj;
    int i = paramMessage.what;
    long l = 300000L;
    switch (i) {
      default:
        stringBuilder2 = new StringBuilder(31);
        stringBuilder2.append("Unknown message id: ");
        stringBuilder2.append(i);
        Log.w("GoogleApiManager", stringBuilder2.toString());
        return false;
      case 12:
        if (this.zzfkk.containsKey(((Message)stringBuilder2).obj))
          ((zzbr)this.zzfkk.get(((Message)stringBuilder2).obj)).zzaib(); 
        return true;
      case 11:
        if (this.zzfkk.containsKey(((Message)stringBuilder2).obj))
          ((zzbr)this.zzfkk.get(((Message)stringBuilder2).obj)).zzahh(); 
        return true;
      case 10:
        zzahr();
        return true;
      case 9:
        if (this.zzfkk.containsKey(((Message)stringBuilder2).obj))
          ((zzbr)this.zzfkk.get(((Message)stringBuilder2).obj)).resume(); 
        return true;
      case 7:
        zzb((GoogleApi)((Message)stringBuilder2).obj);
        return true;
      case 6:
        if (this.mContext.getApplicationContext() instanceof Application) {
          zzk.zza((Application)this.mContext.getApplicationContext());
          zzk.zzafz().zza(new zzbq(this));
          if (!zzk.zzafz().zzbd(true))
            this.zzfnm = 300000L; 
        } 
        return true;
      case 5:
        i = ((Message)stringBuilder2).arg1;
        connectionResult2 = (ConnectionResult)((Message)stringBuilder2).obj;
        stringBuilder3 = null;
        iterator = this.zzfkk.values().iterator();
        while (true) {
          stringBuilder2 = stringBuilder3;
          if (iterator.hasNext()) {
            zzbr = iterator.next();
            if (zzbr.getInstanceId() == i)
              break; 
            continue;
          } 
          break;
        } 
        if (zzbr != null) {
          String str2 = this.zzfhl.getErrorString(connectionResult2.getErrorCode());
          String str1 = connectionResult2.getErrorMessage();
          StringBuilder stringBuilder = new StringBuilder(String.valueOf(str2).length() + 69 + String.valueOf(str1).length());
          stringBuilder.append("Error resolution was canceled by the user, original error message: ");
          stringBuilder.append(str2);
          stringBuilder.append(": ");
          stringBuilder.append(str1);
          zzbr.zzv(new Status(17, stringBuilder.toString()));
        } else {
          stringBuilder1 = new StringBuilder(76);
          stringBuilder1.append("Could not find API instance ");
          stringBuilder1.append(i);
          stringBuilder1.append(" while trying to fail enqueued calls.");
          Log.wtf("GoogleApiManager", stringBuilder1.toString(), new Exception());
        } 
        return true;
      case 4:
      case 8:
      case 13:
        zzcq = (zzcq)((Message)stringBuilder1).obj;
        zzbr2 = this.zzfkk.get(zzcq.zzfpb.zzafk());
        zzbr1 = zzbr2;
        if (zzbr2 == null) {
          zzb(zzcq.zzfpb);
          zzbr1 = this.zzfkk.get(zzcq.zzfpb.zzafk());
        } 
        if (zzbr1.zzaac() && this.zzfnq.get() != zzcq.zzfpa) {
          zzcq.zzfoz.zzr(zzfnk);
          zzbr1.signOut();
        } else {
          zzbr1.zza(zzcq.zzfoz);
        } 
        return true;
      case 3:
        for (zzbr<?> zzbr2 : this.zzfkk.values()) {
          zzbr2.zzahx();
          zzbr2.connect();
        } 
        return true;
      case 2:
        zzj = (zzj)((Message)zzbr1).obj;
        for (zzh<?> zzh : zzj.zzafx()) {
          zzbr1 = this.zzfkk.get(zzh);
          if (zzbr1 == null) {
            zzj.zza(zzh, new ConnectionResult(13));
            break;
          } 
          if (zzbr1.isConnected()) {
            connectionResult1 = ConnectionResult.zzfff;
          } else if (connectionResult1.zzahy() != null) {
            connectionResult1 = connectionResult1.zzahy();
          } else {
            connectionResult1.zza(zzj);
            continue;
          } 
          zzj.zza(zzh, connectionResult1);
        } 
        return true;
      case 1:
        break;
    } 
    if (((Boolean)((Message)connectionResult1).obj).booleanValue())
      l = 10000L; 
    this.zzfnm = l;
    this.mHandler.removeMessages(12);
    for (zzh<?> zzh : this.zzfkk.keySet()) {
      Handler handler = this.mHandler;
      handler.sendMessageDelayed(handler.obtainMessage(12, zzh), this.zzfnm);
    } 
    return true;
  }
  
  final PendingIntent zza(zzh<?> paramzzh, int paramInt) {
    zzbr zzbr = this.zzfkk.get(paramzzh);
    if (zzbr == null)
      return null; 
    zzcps zzcps = zzbr.zzaic();
    return (zzcps == null) ? null : PendingIntent.getActivity(this.mContext, paramInt, zzcps.zzaam(), 134217728);
  }
  
  public final <O extends Api.ApiOptions> Task<Boolean> zza(@NonNull GoogleApi<O> paramGoogleApi, @NonNull zzcl<?> paramzzcl) {
    TaskCompletionSource<Boolean> taskCompletionSource = new TaskCompletionSource();
    zzf zzf = new zzf(paramzzcl, taskCompletionSource);
    Handler handler = this.mHandler;
    handler.sendMessage(handler.obtainMessage(13, new zzcq(zzf, this.zzfnq.get(), paramGoogleApi)));
    return taskCompletionSource.getTask();
  }
  
  public final <O extends Api.ApiOptions> Task<Void> zza(@NonNull GoogleApi<O> paramGoogleApi, @NonNull zzcr<Api.zzb, ?> paramzzcr, @NonNull zzdn<Api.zzb, ?> paramzzdn) {
    TaskCompletionSource<Void> taskCompletionSource = new TaskCompletionSource();
    zzd zzd = new zzd(new zzcs(paramzzcr, paramzzdn), taskCompletionSource);
    Handler handler = this.mHandler;
    handler.sendMessage(handler.obtainMessage(8, new zzcq(zzd, this.zzfnq.get(), paramGoogleApi)));
    return taskCompletionSource.getTask();
  }
  
  public final Task<Void> zza(Iterable<? extends GoogleApi<?>> paramIterable) {
    // Byte code:
    //   0: new com/google/android/gms/common/api/internal/zzj
    //   3: dup
    //   4: aload_1
    //   5: invokespecial <init> : (Ljava/lang/Iterable;)V
    //   8: astore_2
    //   9: aload_1
    //   10: invokeinterface iterator : ()Ljava/util/Iterator;
    //   15: astore_1
    //   16: aload_1
    //   17: invokeinterface hasNext : ()Z
    //   22: ifeq -> 84
    //   25: aload_1
    //   26: invokeinterface next : ()Ljava/lang/Object;
    //   31: checkcast com/google/android/gms/common/api/GoogleApi
    //   34: astore_3
    //   35: aload_0
    //   36: getfield zzfkk : Ljava/util/Map;
    //   39: aload_3
    //   40: invokevirtual zzafk : ()Lcom/google/android/gms/common/api/internal/zzh;
    //   43: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   48: checkcast com/google/android/gms/common/api/internal/zzbr
    //   51: astore_3
    //   52: aload_3
    //   53: ifnull -> 63
    //   56: aload_3
    //   57: invokevirtual isConnected : ()Z
    //   60: ifne -> 16
    //   63: aload_0
    //   64: getfield mHandler : Landroid/os/Handler;
    //   67: astore_1
    //   68: aload_1
    //   69: aload_1
    //   70: iconst_2
    //   71: aload_2
    //   72: invokevirtual obtainMessage : (ILjava/lang/Object;)Landroid/os/Message;
    //   75: invokevirtual sendMessage : (Landroid/os/Message;)Z
    //   78: pop
    //   79: aload_2
    //   80: invokevirtual getTask : ()Lcom/google/android/gms/tasks/Task;
    //   83: areturn
    //   84: aload_2
    //   85: invokevirtual zzafy : ()V
    //   88: goto -> 79
  }
  
  public final void zza(ConnectionResult paramConnectionResult, int paramInt) {
    if (!zzc(paramConnectionResult, paramInt)) {
      Handler handler = this.mHandler;
      handler.sendMessage(handler.obtainMessage(5, paramInt, 0, paramConnectionResult));
    } 
  }
  
  public final void zza(GoogleApi<?> paramGoogleApi) {
    Handler handler = this.mHandler;
    handler.sendMessage(handler.obtainMessage(7, paramGoogleApi));
  }
  
  public final <O extends Api.ApiOptions, TResult> void zza(GoogleApi<O> paramGoogleApi, int paramInt, zzdd<Api.zzb, TResult> paramzzdd, TaskCompletionSource<TResult> paramTaskCompletionSource, zzcz paramzzcz) {
    zze<TResult> zze = new zze<TResult>(paramInt, paramzzdd, paramTaskCompletionSource, paramzzcz);
    Handler handler = this.mHandler;
    handler.sendMessage(handler.obtainMessage(4, new zzcq(zze, this.zzfnq.get(), paramGoogleApi)));
  }
  
  public final <O extends Api.ApiOptions> void zza(GoogleApi<O> paramGoogleApi, int paramInt, zzm<? extends Result, Api.zzb> paramzzm) {
    zzc<zzm<? extends Result, Api.zzb>> zzc = new zzc<zzm<? extends Result, Api.zzb>>(paramInt, paramzzm);
    Handler handler = this.mHandler;
    handler.sendMessage(handler.obtainMessage(4, new zzcq(zzc, this.zzfnq.get(), paramGoogleApi)));
  }
  
  public final void zza(@NonNull zzak paramzzak) {
    synchronized (zzaqd) {
      if (this.zzfnr != paramzzak) {
        this.zzfnr = paramzzak;
        this.zzfns.clear();
        this.zzfns.addAll((Collection<? extends zzh<?>>)paramzzak.zzagv());
      } 
      return;
    } 
  }
  
  final void zzafp() {
    this.zzfnq.incrementAndGet();
    Handler handler = this.mHandler;
    handler.sendMessage(handler.obtainMessage(10));
  }
  
  public final void zzafw() {
    Handler handler = this.mHandler;
    handler.sendMessage(handler.obtainMessage(3));
  }
  
  public final int zzahq() {
    return this.zzfnp.getAndIncrement();
  }
  
  final void zzb(@NonNull zzak paramzzak) {
    synchronized (zzaqd) {
      if (this.zzfnr == paramzzak) {
        this.zzfnr = null;
        this.zzfns.clear();
      } 
      return;
    } 
  }
  
  final boolean zzc(ConnectionResult paramConnectionResult, int paramInt) {
    return this.zzfhl.zza(this.mContext, paramConnectionResult, paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzbp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */