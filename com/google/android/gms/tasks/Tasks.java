package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzbp;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public final class Tasks {
  public static <TResult> TResult await(@NonNull Task<TResult> paramTask) throws ExecutionException, InterruptedException {
    zzbp.zzgh("Must not be called on the main application thread");
    zzbp.zzb(paramTask, "Task must not be null");
    if (paramTask.isComplete())
      return zzc(paramTask); 
    zza zza = new zza(null);
    zza(paramTask, zza);
    zza.await();
    return zzc(paramTask);
  }
  
  public static <TResult> TResult await(@NonNull Task<TResult> paramTask, long paramLong, @NonNull TimeUnit paramTimeUnit) throws ExecutionException, InterruptedException, TimeoutException {
    zzbp.zzgh("Must not be called on the main application thread");
    zzbp.zzb(paramTask, "Task must not be null");
    zzbp.zzb(paramTimeUnit, "TimeUnit must not be null");
    if (paramTask.isComplete())
      return zzc(paramTask); 
    zza zza = new zza(null);
    zza(paramTask, zza);
    if (zza.await(paramLong, paramTimeUnit))
      return zzc(paramTask); 
    throw new TimeoutException("Timed out waiting for Task");
  }
  
  public static <TResult> Task<TResult> call(@NonNull Callable<TResult> paramCallable) {
    return call(TaskExecutors.MAIN_THREAD, paramCallable);
  }
  
  public static <TResult> Task<TResult> call(@NonNull Executor paramExecutor, @NonNull Callable<TResult> paramCallable) {
    zzbp.zzb(paramExecutor, "Executor must not be null");
    zzbp.zzb(paramCallable, "Callback must not be null");
    zzn<TResult> zzn = new zzn();
    paramExecutor.execute(new zzo(zzn, paramCallable));
    return zzn;
  }
  
  public static <TResult> Task<TResult> forException(@NonNull Exception paramException) {
    zzn<TResult> zzn = new zzn();
    zzn.setException(paramException);
    return zzn;
  }
  
  public static <TResult> Task<TResult> forResult(TResult paramTResult) {
    zzn<TResult> zzn = new zzn();
    zzn.setResult(paramTResult);
    return zzn;
  }
  
  public static Task<Void> whenAll(Collection<? extends Task<?>> paramCollection) {
    if (paramCollection.isEmpty())
      return forResult(null); 
    Iterator<? extends Task<?>> iterator2 = paramCollection.iterator();
    while (iterator2.hasNext()) {
      if ((Task)iterator2.next() != null)
        continue; 
      throw new NullPointerException("null tasks are not accepted");
    } 
    zzn<Void> zzn = new zzn();
    zzc zzc = new zzc(paramCollection.size(), zzn);
    Iterator<? extends Task<?>> iterator1 = paramCollection.iterator();
    while (iterator1.hasNext())
      zza(iterator1.next(), zzc); 
    return zzn;
  }
  
  public static Task<Void> whenAll(Task<?>... paramVarArgs) {
    return (paramVarArgs.length == 0) ? forResult(null) : whenAll(Arrays.asList(paramVarArgs));
  }
  
  private static void zza(Task<?> paramTask, zzb paramzzb) {
    Executor executor = TaskExecutors.zzkgi;
    paramTask.addOnSuccessListener(executor, paramzzb);
    paramTask.addOnFailureListener(executor, paramzzb);
  }
  
  private static <TResult> TResult zzc(Task<TResult> paramTask) throws ExecutionException {
    if (paramTask.isSuccessful())
      return paramTask.getResult(); 
    throw new ExecutionException(paramTask.getException());
  }
  
  static final class zza implements zzb {
    private final CountDownLatch zzaof = new CountDownLatch(1);
    
    private zza() {}
    
    public final void await() throws InterruptedException {
      this.zzaof.await();
    }
    
    public final boolean await(long param1Long, TimeUnit param1TimeUnit) throws InterruptedException {
      return this.zzaof.await(param1Long, param1TimeUnit);
    }
    
    public final void onFailure(@NonNull Exception param1Exception) {
      this.zzaof.countDown();
    }
    
    public final void onSuccess(Object param1Object) {
      this.zzaof.countDown();
    }
  }
  
  static interface zzb extends OnFailureListener, OnSuccessListener<Object> {}
  
  static final class zzc implements zzb {
    private final Object mLock = new Object();
    
    private final zzn<Void> zzkgh;
    
    private Exception zzkgm;
    
    private final int zzkgo;
    
    private int zzkgp;
    
    private int zzkgq;
    
    public zzc(int param1Int, zzn<Void> param1zzn) {
      this.zzkgo = param1Int;
      this.zzkgh = param1zzn;
    }
    
    private final void zzbih() {
      if (this.zzkgp + this.zzkgq == this.zzkgo) {
        if (this.zzkgm == null) {
          this.zzkgh.setResult(null);
          return;
        } 
        zzn<Void> zzn1 = this.zzkgh;
        int i = this.zzkgq;
        int j = this.zzkgo;
        StringBuilder stringBuilder = new StringBuilder(54);
        stringBuilder.append(i);
        stringBuilder.append(" out of ");
        stringBuilder.append(j);
        stringBuilder.append(" underlying tasks failed");
        zzn1.setException(new ExecutionException(stringBuilder.toString(), this.zzkgm));
      } 
    }
    
    public final void onFailure(@NonNull Exception param1Exception) {
      synchronized (this.mLock) {
        this.zzkgq++;
        this.zzkgm = param1Exception;
        zzbih();
        return;
      } 
    }
    
    public final void onSuccess(Object param1Object) {
      synchronized (this.mLock) {
        this.zzkgp++;
        zzbih();
        return;
      } 
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/tasks/Tasks.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */