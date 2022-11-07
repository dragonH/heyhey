package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.common.internal.zzs;
import com.google.android.gms.common.zze;
import com.google.android.gms.internal.zzbdy;
import com.google.android.gms.internal.zzcps;
import com.google.android.gms.internal.zzcpt;
import com.google.android.gms.tasks.Task;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public final class zzad implements zzcd {
  private final Looper zzakg;
  
  private final zzbp zzfgv;
  
  private final Lock zzfke;
  
  private final zzq zzfkj;
  
  private final Map<Api.zzc<?>, zzac<?>> zzfkk = new HashMap<Api.zzc<?>, zzac<?>>();
  
  private final Map<Api.zzc<?>, zzac<?>> zzfkl = new HashMap<Api.zzc<?>, zzac<?>>();
  
  private final Map<Api<?>, Boolean> zzfkm;
  
  private final zzbd zzfkn;
  
  private final zze zzfko;
  
  private final Condition zzfkp;
  
  private final boolean zzfkq;
  
  private final boolean zzfkr;
  
  private final Queue<zzm<?, ?>> zzfks = new LinkedList<zzm<?, ?>>();
  
  private boolean zzfkt;
  
  private Map<zzh<?>, ConnectionResult> zzfku;
  
  private Map<zzh<?>, ConnectionResult> zzfkv;
  
  private zzag zzfkw;
  
  private ConnectionResult zzfkx;
  
  public zzad(Context paramContext, Lock paramLock, Looper paramLooper, zze paramzze, Map<Api.zzc<?>, Api.zze> paramMap, zzq paramzzq, Map<Api<?>, Boolean> paramMap1, Api.zza<? extends zzcps, zzcpt> paramzza, ArrayList<zzw> paramArrayList, zzbd paramzzbd, boolean paramBoolean) {
    this.zzfke = paramLock;
    this.zzakg = paramLooper;
    this.zzfkp = paramLock.newCondition();
    this.zzfko = paramzze;
    this.zzfkn = paramzzbd;
    this.zzfkm = paramMap1;
    this.zzfkj = paramzzq;
    this.zzfkq = paramBoolean;
    HashMap<Object, Object> hashMap1 = new HashMap<Object, Object>();
    for (Api<?> api : paramMap1.keySet())
      hashMap1.put(api.zzafe(), api); 
    HashMap<Object, Object> hashMap2 = new HashMap<Object, Object>();
    int i = paramArrayList.size();
    byte b = 0;
    while (b < i) {
      paramMap1 = (Map<Api<?>, Boolean>)paramArrayList.get(b);
      b++;
      zzw zzw = (zzw)paramMap1;
      hashMap2.put(zzw.zzfdg, zzw);
    } 
    Iterator<Map.Entry> iterator = paramMap.entrySet().iterator();
    int j = 0;
    i = 1;
    b = 0;
    while (iterator.hasNext()) {
      Map.Entry entry = iterator.next();
      Api<Api.ApiOptions> api = (Api)hashMap1.get(entry.getKey());
      Api.zze zze1 = (Api.zze)entry.getValue();
      if (zze1.zzaff()) {
        paramBoolean = ((Boolean)this.zzfkm.get(api)).booleanValue();
        j = i;
        if (!paramBoolean) {
          i = 1;
          b = 1;
        } else {
          i = 1;
        } 
      } else {
        i = j;
        j = 0;
      } 
      zzac<Api.ApiOptions> zzac = new zzac<Api.ApiOptions>(paramContext, api, paramLooper, zze1, (zzw)hashMap2.get(api), paramzzq, paramzza);
      this.zzfkk.put((Api.zzc)entry.getKey(), zzac);
      if (zze1.zzaac())
        this.zzfkl.put((Api.zzc)entry.getKey(), zzac); 
      int k = i;
      i = j;
      j = k;
    } 
    if (j != 0 && i == 0 && b == 0) {
      paramBoolean = true;
    } else {
      paramBoolean = false;
    } 
    this.zzfkr = paramBoolean;
    this.zzfgv = zzbp.zzaho();
  }
  
  private final boolean zza(zzac<?> paramzzac, ConnectionResult paramConnectionResult) {
    return (!paramConnectionResult.isSuccess() && !paramConnectionResult.hasResolution() && ((Boolean)this.zzfkm.get(paramzzac.zzafj())).booleanValue() && paramzzac.zzagn().zzaff() && this.zzfko.isUserResolvableError(paramConnectionResult.getErrorCode()));
  }
  
  private final boolean zzago() {
    this.zzfke.lock();
    try {
      if (!this.zzfkt || !this.zzfkq)
        return false; 
      Iterator<Api.zzc> iterator = this.zzfkl.keySet().iterator();
      while (iterator.hasNext()) {
        ConnectionResult connectionResult = zzb(iterator.next());
        if (connectionResult != null) {
          boolean bool = connectionResult.isSuccess();
          if (!bool)
            continue; 
          continue;
        } 
        continue;
      } 
      return true;
    } finally {
      this.zzfke.unlock();
    } 
  }
  
  private final void zzagp() {
    if (this.zzfkj == null) {
      this.zzfkn.zzfmo = Collections.emptySet();
      return;
    } 
    HashSet<Scope> hashSet = new HashSet(this.zzfkj.zzajr());
    Map map = this.zzfkj.zzajt();
    for (Api<?> api : (Iterable<Api<?>>)map.keySet()) {
      ConnectionResult connectionResult = getConnectionResult(api);
      if (connectionResult != null && connectionResult.isSuccess())
        hashSet.addAll(((zzs)map.get(api)).zzecm); 
    } 
    this.zzfkn.zzfmo = hashSet;
  }
  
  private final void zzagq() {
    while (!this.zzfks.isEmpty())
      zze(this.zzfks.remove()); 
    this.zzfkn.zzj(null);
  }
  
  @Nullable
  private final ConnectionResult zzagr() {
    Iterator<zzac> iterator = this.zzfkk.values().iterator();
    int i = 0;
    ConnectionResult connectionResult1 = null;
    ConnectionResult connectionResult2 = connectionResult1;
    int j = 0;
    while (iterator.hasNext()) {
      zzac zzac = iterator.next();
      Api api = zzac.zzafj();
      zzh zzh = zzac.zzafk();
      ConnectionResult connectionResult = this.zzfku.get(zzh);
      if (!connectionResult.isSuccess() && (!((Boolean)this.zzfkm.get(api)).booleanValue() || connectionResult.hasResolution() || this.zzfko.isUserResolvableError(connectionResult.getErrorCode()))) {
        if (connectionResult.getErrorCode() == 4 && this.zzfkq) {
          int m = api.zzafc().getPriority();
          if (connectionResult2 == null || j > m) {
            connectionResult2 = connectionResult;
            j = m;
          } 
          continue;
        } 
        int k = api.zzafc().getPriority();
        if (connectionResult1 == null || i > k) {
          connectionResult1 = connectionResult;
          i = k;
        } 
      } 
    } 
    return (connectionResult1 != null && connectionResult2 != null && i > j) ? connectionResult2 : connectionResult1;
  }
  
  @Nullable
  private final ConnectionResult zzb(@NonNull Api.zzc<?> paramzzc) {
    this.zzfke.lock();
    try {
      zzac zzac = this.zzfkk.get(paramzzc);
      Map<zzh<?>, ConnectionResult> map = this.zzfku;
      if (map != null && zzac != null)
        return map.get(zzac.zzafk()); 
      return null;
    } finally {
      this.zzfke.unlock();
    } 
  }
  
  private final <T extends zzm<? extends Result, ? extends Api.zzb>> boolean zzg(@NonNull T paramT) {
    Api.zzc<?> zzc = paramT.zzafe();
    ConnectionResult connectionResult = zzb(zzc);
    if (connectionResult != null && connectionResult.getErrorCode() == 4) {
      paramT.zzt(new Status(4, null, this.zzfgv.zza(((zzac)this.zzfkk.get(zzc)).zzafk(), System.identityHashCode(this.zzfkn))));
      return true;
    } 
    return false;
  }
  
  public final ConnectionResult blockingConnect() {
    connect();
    while (isConnecting()) {
      try {
        this.zzfkp.await();
      } catch (InterruptedException interruptedException) {
        Thread.currentThread().interrupt();
        return new ConnectionResult(15, null);
      } 
    } 
    if (isConnected())
      return ConnectionResult.zzfff; 
    ConnectionResult connectionResult = this.zzfkx;
    return (connectionResult != null) ? connectionResult : new ConnectionResult(13, null);
  }
  
  public final ConnectionResult blockingConnect(long paramLong, TimeUnit paramTimeUnit) {
    connect();
    for (paramLong = paramTimeUnit.toNanos(paramLong); isConnecting(); paramLong = this.zzfkp.awaitNanos(paramLong)) {
      if (paramLong <= 0L)
        try {
          disconnect();
          return new ConnectionResult(14, null);
        } catch (InterruptedException interruptedException) {
          Thread.currentThread().interrupt();
          return new ConnectionResult(15, null);
        }  
    } 
    if (isConnected())
      return ConnectionResult.zzfff; 
    ConnectionResult connectionResult = this.zzfkx;
    return (connectionResult != null) ? connectionResult : new ConnectionResult(13, null);
  }
  
  public final void connect() {
    this.zzfke.lock();
    try {
      boolean bool = this.zzfkt;
      if (!bool) {
        this.zzfkt = true;
        this.zzfku = null;
        this.zzfkv = null;
        this.zzfkw = null;
        this.zzfkx = null;
        this.zzfgv.zzafw();
        Task<Void> task = this.zzfgv.zza(this.zzfkk.values());
        zzbdy zzbdy = new zzbdy();
        this(this.zzakg);
        zzaf zzaf = new zzaf();
        this(this, null);
        task.addOnCompleteListener((Executor)zzbdy, zzaf);
      } 
      return;
    } finally {
      this.zzfke.unlock();
    } 
  }
  
  public final void disconnect() {
    this.zzfke.lock();
    try {
      this.zzfkt = false;
      this.zzfku = null;
      this.zzfkv = null;
      zzag zzag1 = this.zzfkw;
      if (zzag1 != null) {
        zzag1.cancel();
        this.zzfkw = null;
      } 
      this.zzfkx = null;
      while (!this.zzfks.isEmpty()) {
        zzm zzm = this.zzfks.remove();
        zzm.zza((zzdm)null);
        zzm.cancel();
      } 
      this.zzfkp.signalAll();
      return;
    } finally {
      this.zzfke.unlock();
    } 
  }
  
  public final void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {}
  
  @Nullable
  public final ConnectionResult getConnectionResult(@NonNull Api<?> paramApi) {
    return zzb(paramApi.zzafe());
  }
  
  public final boolean isConnected() {
    this.zzfke.lock();
    try {
      if (this.zzfku != null) {
        ConnectionResult connectionResult = this.zzfkx;
        if (connectionResult == null)
          return true; 
      } 
      return false;
    } finally {
      this.zzfke.unlock();
    } 
  }
  
  public final boolean isConnecting() {
    this.zzfke.lock();
    try {
      if (this.zzfku == null) {
        boolean bool = this.zzfkt;
        if (bool) {
          bool = true;
          return bool;
        } 
      } 
      return false;
    } finally {
      this.zzfke.unlock();
    } 
  }
  
  public final boolean zza(zzcv paramzzcv) {
    this.zzfke.lock();
    try {
      if (this.zzfkt && !zzago()) {
        this.zzfgv.zzafw();
        zzag zzag1 = new zzag();
        this(this, paramzzcv);
        this.zzfkw = zzag1;
        Task<Void> task = this.zzfgv.zza(this.zzfkl.values());
        zzbdy zzbdy = new zzbdy();
        this(this.zzakg);
        task.addOnCompleteListener((Executor)zzbdy, this.zzfkw);
        return true;
      } 
      return false;
    } finally {
      this.zzfke.unlock();
    } 
  }
  
  public final void zzafp() {
    this.zzfke.lock();
    try {
      this.zzfgv.zzafp();
      zzag zzag1 = this.zzfkw;
      if (zzag1 != null) {
        zzag1.cancel();
        this.zzfkw = null;
      } 
      if (this.zzfkv == null) {
        ArrayMap arrayMap = new ArrayMap();
        this(this.zzfkl.size());
        this.zzfkv = (Map<zzh<?>, ConnectionResult>)arrayMap;
      } 
      ConnectionResult connectionResult = new ConnectionResult();
      this(4);
      for (zzac<?> zzac : this.zzfkl.values())
        this.zzfkv.put(zzac.zzafk(), connectionResult); 
      Map<zzh<?>, ConnectionResult> map = this.zzfku;
      if (map != null)
        map.putAll(this.zzfkv); 
      return;
    } finally {
      this.zzfke.unlock();
    } 
  }
  
  public final void zzagi() {}
  
  public final <A extends Api.zzb, R extends Result, T extends zzm<R, A>> T zzd(@NonNull T paramT) {
    if (this.zzfkq && zzg((zzm<? extends Result, ? extends Api.zzb>)paramT))
      return paramT; 
    if (!isConnected()) {
      this.zzfks.add((zzm<?, ?>)paramT);
      return paramT;
    } 
    this.zzfkn.zzfmt.zzb((zzs<? extends Result>)paramT);
    return (T)((zzac)this.zzfkk.get(paramT.zzafe())).zza((zzm)paramT);
  }
  
  public final <A extends Api.zzb, T extends zzm<? extends Result, A>> T zze(@NonNull T paramT) {
    Api.zzc zzc = paramT.zzafe();
    if (this.zzfkq && zzg((zzm<? extends Result, ? extends Api.zzb>)paramT))
      return paramT; 
    this.zzfkn.zzfmt.zzb((zzs<? extends Result>)paramT);
    return (T)((zzac)this.zzfkk.get(zzc)).zzb((zzm)paramT);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzad.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */