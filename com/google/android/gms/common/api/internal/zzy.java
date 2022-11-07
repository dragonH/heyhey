package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.common.zze;
import com.google.android.gms.internal.zzcps;
import com.google.android.gms.internal.zzcpt;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

final class zzy implements zzcd {
  private final Context mContext;
  
  private final Looper zzakg;
  
  private final zzbd zzfju;
  
  private final zzbl zzfjv;
  
  private final zzbl zzfjw;
  
  private final Map<Api.zzc<?>, zzbl> zzfjx;
  
  private final Set<zzcv> zzfjy = Collections.newSetFromMap(new WeakHashMap<zzcv, Boolean>());
  
  private final Api.zze zzfjz;
  
  private Bundle zzfka;
  
  private ConnectionResult zzfkb = null;
  
  private ConnectionResult zzfkc = null;
  
  private boolean zzfkd = false;
  
  private final Lock zzfke;
  
  private int zzfkf = 0;
  
  private zzy(Context paramContext, zzbd paramzzbd, Lock paramLock, Looper paramLooper, zze paramzze, Map<Api.zzc<?>, Api.zze> paramMap1, Map<Api.zzc<?>, Api.zze> paramMap2, zzq paramzzq, Api.zza<? extends zzcps, zzcpt> paramzza, Api.zze paramzze1, ArrayList<zzw> paramArrayList1, ArrayList<zzw> paramArrayList2, Map<Api<?>, Boolean> paramMap3, Map<Api<?>, Boolean> paramMap4) {
    this.mContext = paramContext;
    this.zzfju = paramzzbd;
    this.zzfke = paramLock;
    this.zzakg = paramLooper;
    this.zzfjz = paramzze1;
    this.zzfjv = new zzbl(paramContext, paramzzbd, paramLock, paramLooper, paramzze, paramMap2, null, paramMap4, null, paramArrayList2, new zzaa(this, null));
    this.zzfjw = new zzbl(paramContext, paramzzbd, paramLock, paramLooper, paramzze, paramMap1, paramzzq, paramMap3, paramzza, paramArrayList1, new zzab(this, null));
    ArrayMap arrayMap = new ArrayMap();
    Iterator<Api.zzc> iterator = paramMap2.keySet().iterator();
    while (iterator.hasNext())
      arrayMap.put(iterator.next(), this.zzfjv); 
    iterator = paramMap1.keySet().iterator();
    while (iterator.hasNext())
      arrayMap.put(iterator.next(), this.zzfjw); 
    this.zzfjx = Collections.unmodifiableMap((Map<? extends Api.zzc<?>, ? extends zzbl>)arrayMap);
  }
  
  public static zzy zza(Context paramContext, zzbd paramzzbd, Lock paramLock, Looper paramLooper, zze paramzze, Map<Api.zzc<?>, Api.zze> paramMap, zzq paramzzq, Map<Api<?>, Boolean> paramMap1, Api.zza<? extends zzcps, zzcpt> paramzza, ArrayList<zzw> paramArrayList) {
    Api.zze zze1;
    ArrayMap<Api.zzc, Api.zze> arrayMap1 = new ArrayMap();
    ArrayMap<Api.zzc, Api.zze> arrayMap2 = new ArrayMap();
    Iterator<Map.Entry> iterator = paramMap.entrySet().iterator();
    paramMap = null;
    while (iterator.hasNext()) {
      Map.Entry entry = iterator.next();
      Api.zze zze2 = (Api.zze)entry.getValue();
      if (zze2.zzaal())
        zze1 = zze2; 
      boolean bool = zze2.zzaac();
      Api.zzc zzc = (Api.zzc)entry.getKey();
      if (bool) {
        arrayMap1.put(zzc, zze2);
        continue;
      } 
      arrayMap2.put(zzc, zze2);
    } 
    zzbp.zza(arrayMap1.isEmpty() ^ true, "CompositeGoogleApiClient should not be used without any APIs that require sign-in.");
    ArrayMap<Api, Boolean> arrayMap3 = new ArrayMap();
    ArrayMap<Api, Boolean> arrayMap4 = new ArrayMap();
    for (Api<?> api : paramMap1.keySet()) {
      Api.zzc zzc = api.zzafe();
      if (arrayMap1.containsKey(zzc)) {
        arrayMap3.put(api, paramMap1.get(api));
        continue;
      } 
      if (arrayMap2.containsKey(zzc)) {
        arrayMap4.put(api, paramMap1.get(api));
        continue;
      } 
      throw new IllegalStateException("Each API in the isOptionalMap must have a corresponding client in the clients map.");
    } 
    ArrayList<zzw> arrayList1 = new ArrayList();
    ArrayList<zzw> arrayList2 = new ArrayList();
    int i = paramArrayList.size();
    byte b = 0;
    while (b < i) {
      zzw zzw = (zzw)paramArrayList.get(b);
      b++;
      zzw = zzw;
      if (arrayMap3.containsKey(zzw.zzfdg)) {
        arrayList1.add(zzw);
        continue;
      } 
      if (arrayMap4.containsKey(zzw.zzfdg)) {
        arrayList2.add(zzw);
        continue;
      } 
      throw new IllegalStateException("Each ClientCallbacks must have a corresponding API in the isOptionalMap");
    } 
    return new zzy(paramContext, paramzzbd, paramLock, paramLooper, paramzze, (Map)arrayMap1, (Map)arrayMap2, paramzzq, paramzza, zze1, arrayList1, arrayList2, (Map)arrayMap3, (Map)arrayMap4);
  }
  
  private final void zza(ConnectionResult paramConnectionResult) {
    int i = this.zzfkf;
    if (i != 1) {
      if (i != 2) {
        Log.wtf("CompositeGAC", "Attempted to call failure callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new Exception());
      } else {
        this.zzfju.zzc(paramConnectionResult);
        zzagk();
      } 
      this.zzfkf = 0;
      return;
    } 
    zzagk();
  }
  
  private final void zzagj() {
    if (zzb(this.zzfkb)) {
      if (zzb(this.zzfkc) || zzagl()) {
        int i = this.zzfkf;
        if (i != 1) {
          if (i != 2) {
            Log.wtf("CompositeGAC", "Attempted to call success callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new AssertionError());
          } else {
            this.zzfju.zzj(this.zzfka);
            zzagk();
          } 
          this.zzfkf = 0;
          return;
        } 
      } else {
        ConnectionResult connectionResult = this.zzfkc;
        if (connectionResult != null) {
          if (this.zzfkf == 1) {
            zzagk();
            return;
          } 
          zza(connectionResult);
          this.zzfjv.disconnect();
          return;
        } 
        return;
      } 
    } else {
      if (this.zzfkb != null && zzb(this.zzfkc)) {
        this.zzfjw.disconnect();
        zza(this.zzfkb);
        return;
      } 
      ConnectionResult connectionResult = this.zzfkb;
      if (connectionResult != null) {
        ConnectionResult connectionResult1 = this.zzfkc;
        if (connectionResult1 != null) {
          if (this.zzfjw.zzfnf < this.zzfjv.zzfnf)
            connectionResult = connectionResult1; 
          zza(connectionResult);
        } 
      } 
      return;
    } 
    zzagk();
  }
  
  private final void zzagk() {
    Iterator<zzcv> iterator = this.zzfjy.iterator();
    while (iterator.hasNext())
      ((zzcv)iterator.next()).zzaak(); 
    this.zzfjy.clear();
  }
  
  private final boolean zzagl() {
    ConnectionResult connectionResult = this.zzfkc;
    return (connectionResult != null && connectionResult.getErrorCode() == 4);
  }
  
  @Nullable
  private final PendingIntent zzagm() {
    return (this.zzfjz == null) ? null : PendingIntent.getActivity(this.mContext, System.identityHashCode(this.zzfju), this.zzfjz.zzaam(), 134217728);
  }
  
  private static boolean zzb(ConnectionResult paramConnectionResult) {
    return (paramConnectionResult != null && paramConnectionResult.isSuccess());
  }
  
  private final void zze(int paramInt, boolean paramBoolean) {
    this.zzfju.zzf(paramInt, paramBoolean);
    this.zzfkc = null;
    this.zzfkb = null;
  }
  
  private final boolean zzf(zzm<? extends Result, ? extends Api.zzb> paramzzm) {
    Api.zzc<? extends Api.zzb> zzc = paramzzm.zzafe();
    zzbp.zzb(this.zzfjx.containsKey(zzc), "GoogleApiClient is not configured to use the API required for this call.");
    return ((zzbl)this.zzfjx.get(zzc)).equals(this.zzfjw);
  }
  
  private final void zzi(Bundle paramBundle) {
    Bundle bundle = this.zzfka;
    if (bundle == null) {
      this.zzfka = paramBundle;
      return;
    } 
    if (paramBundle != null)
      bundle.putAll(paramBundle); 
  }
  
  public final ConnectionResult blockingConnect() {
    throw new UnsupportedOperationException();
  }
  
  public final ConnectionResult blockingConnect(long paramLong, @NonNull TimeUnit paramTimeUnit) {
    throw new UnsupportedOperationException();
  }
  
  public final void connect() {
    this.zzfkf = 2;
    this.zzfkd = false;
    this.zzfkc = null;
    this.zzfkb = null;
    this.zzfjv.connect();
    this.zzfjw.connect();
  }
  
  public final void disconnect() {
    this.zzfkc = null;
    this.zzfkb = null;
    this.zzfkf = 0;
    this.zzfjv.disconnect();
    this.zzfjw.disconnect();
    zzagk();
  }
  
  public final void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {
    paramPrintWriter.append(paramString).append("authClient").println(":");
    this.zzfjw.dump(String.valueOf(paramString).concat("  "), paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    paramPrintWriter.append(paramString).append("anonClient").println(":");
    this.zzfjv.dump(String.valueOf(paramString).concat("  "), paramFileDescriptor, paramPrintWriter, paramArrayOfString);
  }
  
  @Nullable
  public final ConnectionResult getConnectionResult(@NonNull Api<?> paramApi) {
    return ((zzbl)this.zzfjx.get(paramApi.zzafe())).equals(this.zzfjw) ? (zzagl() ? new ConnectionResult(4, zzagm()) : this.zzfjw.getConnectionResult(paramApi)) : this.zzfjv.getConnectionResult(paramApi);
  }
  
  public final boolean isConnected() {
    this.zzfke.lock();
    try {
      boolean bool = this.zzfjv.isConnected();
      boolean bool1 = true;
      if (bool) {
        bool = bool1;
        if (!this.zzfjw.isConnected()) {
          bool = bool1;
          if (!zzagl()) {
            int i = this.zzfkf;
            if (i == 1) {
              bool = bool1;
              return bool;
            } 
          } else {
            return bool;
          } 
        } else {
          return bool;
        } 
      } 
      bool = false;
      return bool;
    } finally {
      this.zzfke.unlock();
    } 
  }
  
  public final boolean isConnecting() {
    this.zzfke.lock();
    try {
      boolean bool;
      int i = this.zzfkf;
      if (i == 2) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    } finally {
      this.zzfke.unlock();
    } 
  }
  
  public final boolean zza(zzcv paramzzcv) {
    this.zzfke.lock();
    try {
      if ((isConnecting() || isConnected()) && !this.zzfjw.isConnected()) {
        this.zzfjy.add(paramzzcv);
        if (this.zzfkf == 0)
          this.zzfkf = 1; 
        this.zzfkc = null;
        this.zzfjw.connect();
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
      boolean bool = isConnecting();
      this.zzfjw.disconnect();
      ConnectionResult connectionResult = new ConnectionResult();
      this(4);
      this.zzfkc = connectionResult;
      if (bool) {
        Handler handler = new Handler();
        this(this.zzakg);
        zzz zzz = new zzz();
        this(this);
        handler.post(zzz);
      } else {
        zzagk();
      } 
      return;
    } finally {
      this.zzfke.unlock();
    } 
  }
  
  public final void zzagi() {
    this.zzfjv.zzagi();
    this.zzfjw.zzagi();
  }
  
  public final <A extends Api.zzb, R extends Result, T extends zzm<R, A>> T zzd(@NonNull T paramT) {
    if (zzf((zzm<? extends Result, ? extends Api.zzb>)paramT)) {
      if (zzagl()) {
        paramT.zzt(new Status(4, null, zzagm()));
        return paramT;
      } 
      return this.zzfjw.zzd(paramT);
    } 
    return this.zzfjv.zzd(paramT);
  }
  
  public final <A extends Api.zzb, T extends zzm<? extends Result, A>> T zze(@NonNull T paramT) {
    if (zzf((zzm<? extends Result, ? extends Api.zzb>)paramT)) {
      if (zzagl()) {
        paramT.zzt(new Status(4, null, zzagm()));
        return paramT;
      } 
      return this.zzfjw.zze(paramT);
    } 
    return this.zzfjv.zze(paramT);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */