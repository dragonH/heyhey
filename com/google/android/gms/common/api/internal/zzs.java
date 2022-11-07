package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.internal.zzap;
import com.google.android.gms.common.internal.zzbp;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public abstract class zzs<R extends Result> extends PendingResult<R> {
  static final ThreadLocal<Boolean> zzfje = new zzt();
  
  private Status mStatus;
  
  private boolean zzaj;
  
  private final CountDownLatch zzaof;
  
  private R zzfhr;
  
  private final Object zzfjf;
  
  private zzu<R> zzfjg;
  
  private WeakReference<GoogleApiClient> zzfjh;
  
  private final ArrayList<PendingResult.zza> zzfji;
  
  private ResultCallback<? super R> zzfjj;
  
  private final AtomicReference<zzdm> zzfjk;
  
  private zzv zzfjl;
  
  private volatile boolean zzfjm;
  
  private boolean zzfjn;
  
  private zzap zzfjo;
  
  private volatile zzdg<R> zzfjp;
  
  private boolean zzfjq;
  
  @Deprecated
  zzs() {
    this.zzfjf = new Object();
    this.zzaof = new CountDownLatch(1);
    this.zzfji = new ArrayList<PendingResult.zza>();
    this.zzfjk = new AtomicReference<zzdm>();
    this.zzfjq = false;
    this.zzfjg = new zzu<R>(Looper.getMainLooper());
    this.zzfjh = new WeakReference<GoogleApiClient>(null);
  }
  
  @Deprecated
  protected zzs(Looper paramLooper) {
    this.zzfjf = new Object();
    this.zzaof = new CountDownLatch(1);
    this.zzfji = new ArrayList<PendingResult.zza>();
    this.zzfjk = new AtomicReference<zzdm>();
    this.zzfjq = false;
    this.zzfjg = new zzu<R>(paramLooper);
    this.zzfjh = new WeakReference<GoogleApiClient>(null);
  }
  
  protected zzs(GoogleApiClient paramGoogleApiClient) {
    Looper looper;
    this.zzfjf = new Object();
    this.zzaof = new CountDownLatch(1);
    this.zzfji = new ArrayList<PendingResult.zza>();
    this.zzfjk = new AtomicReference<zzdm>();
    this.zzfjq = false;
    if (paramGoogleApiClient != null) {
      looper = paramGoogleApiClient.getLooper();
    } else {
      looper = Looper.getMainLooper();
    } 
    this.zzfjg = new zzu<R>(looper);
    this.zzfjh = new WeakReference<GoogleApiClient>(paramGoogleApiClient);
  }
  
  private final R get() {
    synchronized (this.zzfjf) {
      boolean bool;
      if (!this.zzfjm) {
        bool = true;
      } else {
        bool = false;
      } 
      zzbp.zza(bool, "Result has already been consumed.");
      zzbp.zza(isReady(), "Result is not ready.");
      R r = this.zzfhr;
      this.zzfhr = null;
      this.zzfjj = null;
      this.zzfjm = true;
      null = this.zzfjk.getAndSet(null);
      if (null != null)
        null.zzc(this); 
      return r;
    } 
  }
  
  private final void zzc(R paramR) {
    this.zzfhr = paramR;
    this.zzfjo = null;
    this.zzaof.countDown();
    this.mStatus = this.zzfhr.getStatus();
    if (this.zzaj) {
      this.zzfjj = null;
    } else if (this.zzfjj == null) {
      if (this.zzfhr instanceof Releasable)
        this.zzfjl = new zzv(this, null); 
    } else {
      this.zzfjg.removeMessages(2);
      this.zzfjg.zza(this.zzfjj, get());
    } 
    ArrayList<PendingResult.zza> arrayList = this.zzfji;
    int i = arrayList.size();
    byte b = 0;
    while (b < i) {
      PendingResult.zza zza = (PendingResult.zza)arrayList.get(b);
      b++;
      ((PendingResult.zza)zza).zzq(this.mStatus);
    } 
    this.zzfji.clear();
  }
  
  public static void zzd(Result paramResult) {
    if (paramResult instanceof Releasable)
      try {
        ((Releasable)paramResult).release();
        return;
      } catch (RuntimeException runtimeException) {
        String str = String.valueOf(paramResult);
        StringBuilder stringBuilder = new StringBuilder(str.length() + 18);
        stringBuilder.append("Unable to release ");
        stringBuilder.append(str);
        Log.w("BasePendingResult", stringBuilder.toString(), runtimeException);
      }  
  }
  
  public final R await() {
    Looper looper1 = Looper.myLooper();
    Looper looper2 = Looper.getMainLooper();
    boolean bool1 = false;
    if (looper1 != looper2) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    zzbp.zza(bool2, "await must not be called on the UI thread");
    zzbp.zza(this.zzfjm ^ true, "Result has already been consumed");
    boolean bool2 = bool1;
    if (this.zzfjp == null)
      bool2 = true; 
    zzbp.zza(bool2, "Cannot await if then() has been called.");
    try {
      this.zzaof.await();
    } catch (InterruptedException interruptedException) {
      zzu(Status.zzfhw);
    } 
    zzbp.zza(isReady(), "Result is not ready.");
    return get();
  }
  
  public final R await(long paramLong, TimeUnit paramTimeUnit) {
    boolean bool1 = false;
    if (paramLong <= 0L || Looper.myLooper() != Looper.getMainLooper()) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    zzbp.zza(bool2, "await must not be called on the UI thread when time is greater than zero.");
    zzbp.zza(this.zzfjm ^ true, "Result has already been consumed.");
    boolean bool2 = bool1;
    if (this.zzfjp == null)
      bool2 = true; 
    zzbp.zza(bool2, "Cannot await if then() has been called.");
    try {
      if (!this.zzaof.await(paramLong, paramTimeUnit))
        zzu(Status.zzfhy); 
    } catch (InterruptedException interruptedException) {
      zzu(Status.zzfhw);
    } 
    zzbp.zza(isReady(), "Result is not ready.");
    return get();
  }
  
  public void cancel() {
    synchronized (this.zzfjf) {
      if (this.zzaj || this.zzfjm)
        return; 
      zzap zzap1 = this.zzfjo;
      if (zzap1 != null)
        try {
          zzap1.cancel();
        } catch (RemoteException remoteException) {} 
      zzd((Result)this.zzfhr);
      this.zzaj = true;
      zzc(zzb(Status.zzfhz));
      return;
    } 
  }
  
  public boolean isCanceled() {
    synchronized (this.zzfjf) {
      return this.zzaj;
    } 
  }
  
  public final boolean isReady() {
    return (this.zzaof.getCount() == 0L);
  }
  
  public final void setResult(R paramR) {
    synchronized (this.zzfjf) {
      if (!this.zzfjn && !this.zzaj) {
        isReady();
        boolean bool = isReady();
        boolean bool1 = true;
        if (!bool) {
          bool = true;
        } else {
          bool = false;
        } 
        zzbp.zza(bool, "Results have already been set");
        if (!this.zzfjm) {
          bool = bool1;
        } else {
          bool = false;
        } 
        zzbp.zza(bool, "Result has already been consumed");
        zzc(paramR);
        return;
      } 
      zzd((Result)paramR);
      return;
    } 
  }
  
  public final void setResultCallback(ResultCallback<? super R> paramResultCallback) {
    // Byte code:
    //   0: aload_0
    //   1: getfield zzfjf : Ljava/lang/Object;
    //   4: astore_2
    //   5: aload_2
    //   6: monitorenter
    //   7: aload_1
    //   8: ifnonnull -> 19
    //   11: aload_0
    //   12: aconst_null
    //   13: putfield zzfjj : Lcom/google/android/gms/common/api/ResultCallback;
    //   16: aload_2
    //   17: monitorexit
    //   18: return
    //   19: aload_0
    //   20: getfield zzfjm : Z
    //   23: istore_3
    //   24: iconst_1
    //   25: istore #4
    //   27: iload_3
    //   28: ifne -> 36
    //   31: iconst_1
    //   32: istore_3
    //   33: goto -> 38
    //   36: iconst_0
    //   37: istore_3
    //   38: iload_3
    //   39: ldc 'Result has already been consumed.'
    //   41: invokestatic zza : (ZLjava/lang/Object;)V
    //   44: aload_0
    //   45: getfield zzfjp : Lcom/google/android/gms/common/api/internal/zzdg;
    //   48: ifnonnull -> 57
    //   51: iload #4
    //   53: istore_3
    //   54: goto -> 59
    //   57: iconst_0
    //   58: istore_3
    //   59: iload_3
    //   60: ldc_w 'Cannot set callbacks if then() has been called.'
    //   63: invokestatic zza : (ZLjava/lang/Object;)V
    //   66: aload_0
    //   67: invokevirtual isCanceled : ()Z
    //   70: ifeq -> 76
    //   73: aload_2
    //   74: monitorexit
    //   75: return
    //   76: aload_0
    //   77: invokevirtual isReady : ()Z
    //   80: ifeq -> 98
    //   83: aload_0
    //   84: getfield zzfjg : Lcom/google/android/gms/common/api/internal/zzu;
    //   87: aload_1
    //   88: aload_0
    //   89: invokespecial get : ()Lcom/google/android/gms/common/api/Result;
    //   92: invokevirtual zza : (Lcom/google/android/gms/common/api/ResultCallback;Lcom/google/android/gms/common/api/Result;)V
    //   95: goto -> 103
    //   98: aload_0
    //   99: aload_1
    //   100: putfield zzfjj : Lcom/google/android/gms/common/api/ResultCallback;
    //   103: aload_2
    //   104: monitorexit
    //   105: return
    //   106: astore_1
    //   107: aload_2
    //   108: monitorexit
    //   109: aload_1
    //   110: athrow
    // Exception table:
    //   from	to	target	type
    //   11	18	106	finally
    //   19	24	106	finally
    //   38	51	106	finally
    //   59	75	106	finally
    //   76	95	106	finally
    //   98	103	106	finally
    //   103	105	106	finally
    //   107	109	106	finally
  }
  
  public final void setResultCallback(ResultCallback<? super R> paramResultCallback, long paramLong, TimeUnit paramTimeUnit) {
    // Byte code:
    //   0: aload_0
    //   1: getfield zzfjf : Ljava/lang/Object;
    //   4: astore #5
    //   6: aload #5
    //   8: monitorenter
    //   9: aload_1
    //   10: ifnonnull -> 22
    //   13: aload_0
    //   14: aconst_null
    //   15: putfield zzfjj : Lcom/google/android/gms/common/api/ResultCallback;
    //   18: aload #5
    //   20: monitorexit
    //   21: return
    //   22: aload_0
    //   23: getfield zzfjm : Z
    //   26: istore #6
    //   28: iconst_1
    //   29: istore #7
    //   31: iload #6
    //   33: ifne -> 42
    //   36: iconst_1
    //   37: istore #6
    //   39: goto -> 45
    //   42: iconst_0
    //   43: istore #6
    //   45: iload #6
    //   47: ldc 'Result has already been consumed.'
    //   49: invokestatic zza : (ZLjava/lang/Object;)V
    //   52: aload_0
    //   53: getfield zzfjp : Lcom/google/android/gms/common/api/internal/zzdg;
    //   56: ifnonnull -> 66
    //   59: iload #7
    //   61: istore #6
    //   63: goto -> 69
    //   66: iconst_0
    //   67: istore #6
    //   69: iload #6
    //   71: ldc_w 'Cannot set callbacks if then() has been called.'
    //   74: invokestatic zza : (ZLjava/lang/Object;)V
    //   77: aload_0
    //   78: invokevirtual isCanceled : ()Z
    //   81: ifeq -> 88
    //   84: aload #5
    //   86: monitorexit
    //   87: return
    //   88: aload_0
    //   89: invokevirtual isReady : ()Z
    //   92: ifeq -> 110
    //   95: aload_0
    //   96: getfield zzfjg : Lcom/google/android/gms/common/api/internal/zzu;
    //   99: aload_1
    //   100: aload_0
    //   101: invokespecial get : ()Lcom/google/android/gms/common/api/Result;
    //   104: invokevirtual zza : (Lcom/google/android/gms/common/api/ResultCallback;Lcom/google/android/gms/common/api/Result;)V
    //   107: goto -> 139
    //   110: aload_0
    //   111: aload_1
    //   112: putfield zzfjj : Lcom/google/android/gms/common/api/ResultCallback;
    //   115: aload_0
    //   116: getfield zzfjg : Lcom/google/android/gms/common/api/internal/zzu;
    //   119: astore_1
    //   120: aload #4
    //   122: lload_2
    //   123: invokevirtual toMillis : (J)J
    //   126: lstore_2
    //   127: aload_1
    //   128: aload_1
    //   129: iconst_2
    //   130: aload_0
    //   131: invokevirtual obtainMessage : (ILjava/lang/Object;)Landroid/os/Message;
    //   134: lload_2
    //   135: invokevirtual sendMessageDelayed : (Landroid/os/Message;J)Z
    //   138: pop
    //   139: aload #5
    //   141: monitorexit
    //   142: return
    //   143: astore_1
    //   144: aload #5
    //   146: monitorexit
    //   147: aload_1
    //   148: athrow
    // Exception table:
    //   from	to	target	type
    //   13	21	143	finally
    //   22	28	143	finally
    //   45	59	143	finally
    //   69	87	143	finally
    //   88	107	143	finally
    //   110	139	143	finally
    //   139	142	143	finally
    //   144	147	143	finally
  }
  
  public <S extends Result> TransformedResult<S> then(ResultTransform<? super R, ? extends S> paramResultTransform) {
    zzbp.zza(this.zzfjm ^ true, "Result has already been consumed.");
    synchronized (this.zzfjf) {
      zzdg<R> zzdg1 = this.zzfjp;
      boolean bool1 = false;
      if (zzdg1 == null) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      zzbp.zza(bool2, "Cannot call then() twice.");
      if (this.zzfjj == null) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      zzbp.zza(bool2, "Cannot call then() if callbacks are set.");
      boolean bool2 = bool1;
      if (!this.zzaj)
        bool2 = true; 
      zzbp.zza(bool2, "Cannot call then() if result was canceled.");
      this.zzfjq = true;
      zzdg1 = new zzdg<R>();
      this(this.zzfjh);
      this.zzfjp = zzdg1;
      TransformedResult<S> transformedResult = this.zzfjp.then(paramResultTransform);
      if (isReady()) {
        this.zzfjg.zza(this.zzfjp, get());
      } else {
        this.zzfjj = this.zzfjp;
      } 
      return transformedResult;
    } 
  }
  
  public final void zza(PendingResult.zza paramzza) {
    boolean bool;
    if (paramzza != null) {
      bool = true;
    } else {
      bool = false;
    } 
    zzbp.zzb(bool, "Callback cannot be null.");
    synchronized (this.zzfjf) {
      if (isReady()) {
        paramzza.zzq(this.mStatus);
      } else {
        this.zzfji.add(paramzza);
      } 
      return;
    } 
  }
  
  public final void zza(zzdm paramzzdm) {
    this.zzfjk.set(paramzzdm);
  }
  
  protected final void zza(zzap paramzzap) {
    synchronized (this.zzfjf) {
      this.zzfjo = paramzzap;
      return;
    } 
  }
  
  public final Integer zzafs() {
    return null;
  }
  
  public final boolean zzagf() {
    synchronized (this.zzfjf) {
      if ((GoogleApiClient)this.zzfjh.get() == null || !this.zzfjq)
        super.cancel(); 
      return super.isCanceled();
    } 
  }
  
  public final void zzagg() {
    boolean bool;
    if (this.zzfjq || ((Boolean)zzfje.get()).booleanValue()) {
      bool = true;
    } else {
      bool = false;
    } 
    this.zzfjq = bool;
  }
  
  @NonNull
  protected abstract R zzb(Status paramStatus);
  
  public final void zzu(Status paramStatus) {
    synchronized (this.zzfjf) {
      if (!isReady()) {
        setResult(zzb(paramStatus));
        this.zzfjn = true;
      } 
      return;
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */