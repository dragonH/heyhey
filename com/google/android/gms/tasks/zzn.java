package com.google.android.gms.tasks;

import android.app.Activity;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.internal.LifecycleCallback;
import com.google.android.gms.common.api.internal.zzcg;
import com.google.android.gms.common.internal.zzbp;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

final class zzn<TResult> extends Task<TResult> {
  private final Object mLock = new Object();
  
  private final zzl<TResult> zzkgj = new zzl<TResult>();
  
  private boolean zzkgk;
  
  private TResult zzkgl;
  
  private Exception zzkgm;
  
  private final void zzbie() {
    zzbp.zza(this.zzkgk, "Task is not yet complete");
  }
  
  private final void zzbif() {
    zzbp.zza(this.zzkgk ^ true, "Task is already complete");
  }
  
  private final void zzbig() {
    synchronized (this.mLock) {
      if (!this.zzkgk)
        return; 
      this.zzkgj.zzb(this);
      return;
    } 
  }
  
  @NonNull
  public final Task<TResult> addOnCompleteListener(@NonNull Activity paramActivity, @NonNull OnCompleteListener<TResult> paramOnCompleteListener) {
    zze<TResult> zze = new zze<TResult>(TaskExecutors.MAIN_THREAD, paramOnCompleteListener);
    this.zzkgj.zza(zze);
    zza.zzr(paramActivity).zzb(zze);
    zzbig();
    return this;
  }
  
  @NonNull
  public final Task<TResult> addOnCompleteListener(@NonNull OnCompleteListener<TResult> paramOnCompleteListener) {
    return super.addOnCompleteListener(TaskExecutors.MAIN_THREAD, paramOnCompleteListener);
  }
  
  @NonNull
  public final Task<TResult> addOnCompleteListener(@NonNull Executor paramExecutor, @NonNull OnCompleteListener<TResult> paramOnCompleteListener) {
    this.zzkgj.zza(new zze<TResult>(paramExecutor, paramOnCompleteListener));
    zzbig();
    return this;
  }
  
  @NonNull
  public final Task<TResult> addOnFailureListener(@NonNull Activity paramActivity, @NonNull OnFailureListener paramOnFailureListener) {
    zzg<TResult> zzg = new zzg(TaskExecutors.MAIN_THREAD, paramOnFailureListener);
    this.zzkgj.zza(zzg);
    zza.zzr(paramActivity).zzb(zzg);
    zzbig();
    return this;
  }
  
  @NonNull
  public final Task<TResult> addOnFailureListener(@NonNull OnFailureListener paramOnFailureListener) {
    return super.addOnFailureListener(TaskExecutors.MAIN_THREAD, paramOnFailureListener);
  }
  
  @NonNull
  public final Task<TResult> addOnFailureListener(@NonNull Executor paramExecutor, @NonNull OnFailureListener paramOnFailureListener) {
    this.zzkgj.zza(new zzg<TResult>(paramExecutor, paramOnFailureListener));
    zzbig();
    return this;
  }
  
  @NonNull
  public final Task<TResult> addOnSuccessListener(@NonNull Activity paramActivity, @NonNull OnSuccessListener<? super TResult> paramOnSuccessListener) {
    zzi<TResult> zzi = new zzi<TResult>(TaskExecutors.MAIN_THREAD, paramOnSuccessListener);
    this.zzkgj.zza(zzi);
    zza.zzr(paramActivity).zzb(zzi);
    zzbig();
    return this;
  }
  
  @NonNull
  public final Task<TResult> addOnSuccessListener(@NonNull OnSuccessListener<? super TResult> paramOnSuccessListener) {
    return super.addOnSuccessListener(TaskExecutors.MAIN_THREAD, paramOnSuccessListener);
  }
  
  @NonNull
  public final Task<TResult> addOnSuccessListener(@NonNull Executor paramExecutor, @NonNull OnSuccessListener<? super TResult> paramOnSuccessListener) {
    this.zzkgj.zza(new zzi<TResult>(paramExecutor, paramOnSuccessListener));
    zzbig();
    return this;
  }
  
  @NonNull
  public final <TContinuationResult> Task<TContinuationResult> continueWith(@NonNull Continuation<TResult, TContinuationResult> paramContinuation) {
    return super.continueWith(TaskExecutors.MAIN_THREAD, paramContinuation);
  }
  
  @NonNull
  public final <TContinuationResult> Task<TContinuationResult> continueWith(@NonNull Executor paramExecutor, @NonNull Continuation<TResult, TContinuationResult> paramContinuation) {
    zzn<TContinuationResult> zzn1 = new zzn();
    this.zzkgj.zza(new zza<TResult, TContinuationResult>(paramExecutor, paramContinuation, zzn1));
    zzbig();
    return zzn1;
  }
  
  @NonNull
  public final <TContinuationResult> Task<TContinuationResult> continueWithTask(@NonNull Continuation<TResult, Task<TContinuationResult>> paramContinuation) {
    return super.continueWithTask(TaskExecutors.MAIN_THREAD, paramContinuation);
  }
  
  @NonNull
  public final <TContinuationResult> Task<TContinuationResult> continueWithTask(@NonNull Executor paramExecutor, @NonNull Continuation<TResult, Task<TContinuationResult>> paramContinuation) {
    zzn<TContinuationResult> zzn1 = new zzn();
    this.zzkgj.zza(new zzc<TResult, TContinuationResult>(paramExecutor, paramContinuation, zzn1));
    zzbig();
    return zzn1;
  }
  
  @Nullable
  public final Exception getException() {
    synchronized (this.mLock) {
      return this.zzkgm;
    } 
  }
  
  public final TResult getResult() {
    synchronized (this.mLock) {
      zzbie();
      if (this.zzkgm == null)
        return this.zzkgl; 
      RuntimeExecutionException runtimeExecutionException = new RuntimeExecutionException();
      this(this.zzkgm);
      throw runtimeExecutionException;
    } 
  }
  
  public final <X extends Throwable> TResult getResult(@NonNull Class<X> paramClass) throws X {
    synchronized (this.mLock) {
      RuntimeExecutionException runtimeExecutionException;
      zzbie();
      if (!paramClass.isInstance(this.zzkgm)) {
        if (this.zzkgm == null)
          return this.zzkgl; 
        runtimeExecutionException = new RuntimeExecutionException();
        this(this.zzkgm);
        throw (X)runtimeExecutionException;
      } 
      throw (X)runtimeExecutionException.cast(this.zzkgm);
    } 
  }
  
  public final boolean isComplete() {
    synchronized (this.mLock) {
      return this.zzkgk;
    } 
  }
  
  public final boolean isSuccessful() {
    synchronized (this.mLock) {
      boolean bool;
      if (this.zzkgk && this.zzkgm == null) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    } 
  }
  
  public final void setException(@NonNull Exception paramException) {
    zzbp.zzb(paramException, "Exception must not be null");
    synchronized (this.mLock) {
      zzbif();
      this.zzkgk = true;
      this.zzkgm = paramException;
      this.zzkgj.zzb(this);
      return;
    } 
  }
  
  public final void setResult(TResult paramTResult) {
    synchronized (this.mLock) {
      zzbif();
      this.zzkgk = true;
      this.zzkgl = paramTResult;
      this.zzkgj.zzb(this);
      return;
    } 
  }
  
  public final boolean trySetException(@NonNull Exception paramException) {
    zzbp.zzb(paramException, "Exception must not be null");
    synchronized (this.mLock) {
      if (this.zzkgk)
        return false; 
      this.zzkgk = true;
      this.zzkgm = paramException;
      this.zzkgj.zzb(this);
      return true;
    } 
  }
  
  public final boolean trySetResult(TResult paramTResult) {
    synchronized (this.mLock) {
      if (this.zzkgk)
        return false; 
      this.zzkgk = true;
      this.zzkgl = paramTResult;
      this.zzkgj.zzb(this);
      return true;
    } 
  }
  
  static class zza extends LifecycleCallback {
    private final List<WeakReference<zzk<?>>> mListeners = new ArrayList<WeakReference<zzk<?>>>();
    
    private zza(zzcg param1zzcg) {
      super(param1zzcg);
      this.zzfoo.zza("TaskOnStopCallback", this);
    }
    
    public static zza zzr(Activity param1Activity) {
      zzcg zzcg = LifecycleCallback.zzn(param1Activity);
      zza zza2 = (zza)zzcg.zza("TaskOnStopCallback", zza.class);
      zza zza1 = zza2;
      if (zza2 == null)
        zza1 = new zza(zzcg); 
      return zza1;
    }
    
    @MainThread
    public final void onStop() {
      synchronized (this.mListeners) {
        Iterator<WeakReference<zzk<?>>> iterator = this.mListeners.iterator();
        while (iterator.hasNext()) {
          zzk zzk = ((WeakReference<zzk>)iterator.next()).get();
          if (zzk != null)
            zzk.cancel(); 
        } 
        this.mListeners.clear();
        return;
      } 
    }
    
    public final <T> void zzb(zzk<T> param1zzk) {
      synchronized (this.mListeners) {
        List<WeakReference<zzk<?>>> list = this.mListeners;
        WeakReference<zzk<?>> weakReference = new WeakReference();
        this((T)param1zzk);
        list.add(weakReference);
        return;
      } 
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/tasks/zzn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */