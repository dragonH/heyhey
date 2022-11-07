package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public final class zzs {
  private AtomicInteger zzaw = new AtomicInteger();
  
  private final Map<String, Queue<zzp<?>>> zzax = new HashMap<String, Queue<zzp<?>>>();
  
  private final Set<zzp<?>> zzay = new HashSet<zzp<?>>();
  
  private final PriorityBlockingQueue<zzp<?>> zzaz = new PriorityBlockingQueue<zzp<?>>();
  
  private final PriorityBlockingQueue<zzp<?>> zzba = new PriorityBlockingQueue<zzp<?>>();
  
  private zzl[] zzbb;
  
  private zzd zzbc;
  
  private List<Object> zzbd = new ArrayList();
  
  private final zzb zzi;
  
  private final zzw zzj;
  
  private final zzk zzx;
  
  public zzs(zzb paramzzb, zzk paramzzk) {
    this(paramzzb, paramzzk, 4);
  }
  
  private zzs(zzb paramzzb, zzk paramzzk, int paramInt) {
    this(paramzzb, paramzzk, 4, new zzh(new Handler(Looper.getMainLooper())));
  }
  
  private zzs(zzb paramzzb, zzk paramzzk, int paramInt, zzw paramzzw) {
    this.zzi = paramzzb;
    this.zzx = paramzzk;
    this.zzbb = new zzl[4];
    this.zzj = paramzzw;
  }
  
  public final void start() {
    zzd zzd1 = this.zzbc;
    if (zzd1 != null)
      zzd1.quit(); 
    boolean bool = false;
    byte b = 0;
    while (true) {
      zzl[] arrayOfZzl = this.zzbb;
      if (b < arrayOfZzl.length) {
        zzl zzl1 = arrayOfZzl[b];
        if (zzl1 != null)
          zzl1.quit(); 
        b++;
        continue;
      } 
      zzd zzd2 = new zzd(this.zzaz, this.zzba, this.zzi, this.zzj);
      this.zzbc = zzd2;
      zzd2.start();
      for (b = bool; b < this.zzbb.length; b++) {
        zzl zzl1 = new zzl(this.zzba, this.zzx, this.zzi, this.zzj);
        this.zzbb[b] = zzl1;
        zzl1.start();
      } 
      return;
    } 
  }
  
  public final <T> zzp<T> zzc(zzp<T> paramzzp) {
    Set<zzp<?>> set;
    Queue<zzp<T>> queue;
    paramzzp.zza(this);
    synchronized (this.zzay) {
      this.zzay.add(paramzzp);
      paramzzp.zza(this.zzaw.incrementAndGet());
      paramzzp.zzb("add-to-queue");
      if (!paramzzp.zzh()) {
        this.zzba.add(paramzzp);
        return paramzzp;
      } 
      synchronized (this.zzax) {
        String str = paramzzp.zzd();
        if (this.zzax.containsKey(str)) {
          Queue<zzp<T>> queue1 = (Queue)this.zzax.get(str);
          queue = queue1;
          if (queue1 == null) {
            queue = new LinkedList();
            super();
          } 
          queue.add(paramzzp);
          this.zzax.put(str, queue);
          if (zzab.DEBUG)
            zzab.zza("Request for cacheKey=%s is in flight, putting on hold.", new Object[] { str }); 
        } else {
          this.zzax.put(str, null);
          this.zzaz.add(paramzzp);
        } 
        return paramzzp;
      } 
    } 
  }
  
  final <T> void zzd(zzp<T> paramzzp) {
    synchronized (this.zzay) {
      this.zzay.remove(paramzzp);
      synchronized (this.zzbd) {
        Iterator iterator = this.zzbd.iterator();
        while (iterator.hasNext())
          iterator.next(); 
        if (paramzzp.zzh())
          synchronized (this.zzax) {
            String str = paramzzp.zzd();
            Queue<? extends zzp<?>> queue = this.zzax.remove(str);
            if (queue != null) {
              if (zzab.DEBUG)
                zzab.zza("Releasing %d waiting requests for cacheKey=%s.", new Object[] { Integer.valueOf(queue.size()), str }); 
              this.zzaz.addAll(queue);
            } 
            return;
          }  
        return;
      } 
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */