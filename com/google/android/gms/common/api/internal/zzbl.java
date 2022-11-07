package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.common.zze;
import com.google.android.gms.internal.zzcps;
import com.google.android.gms.internal.zzcpt;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public final class zzbl implements zzcd, zzx {
  private final Context mContext;
  
  private Api.zza<? extends zzcps, zzcpt> zzfhm;
  
  final zzbd zzfju;
  
  private final Lock zzfke;
  
  private zzq zzfkj;
  
  private Map<Api<?>, Boolean> zzfkm;
  
  private final zze zzfko;
  
  final Map<Api.zzc<?>, Api.zze> zzfmn;
  
  private final Condition zzfna;
  
  private final zzbn zzfnb;
  
  final Map<Api.zzc<?>, ConnectionResult> zzfnc = new HashMap<Api.zzc<?>, ConnectionResult>();
  
  private volatile zzbk zzfnd;
  
  private ConnectionResult zzfne = null;
  
  int zzfnf;
  
  final zzce zzfng;
  
  public zzbl(Context paramContext, zzbd paramzzbd, Lock paramLock, Looper paramLooper, zze paramzze, Map<Api.zzc<?>, Api.zze> paramMap, zzq paramzzq, Map<Api<?>, Boolean> paramMap1, Api.zza<? extends zzcps, zzcpt> paramzza, ArrayList<zzw> paramArrayList, zzce paramzzce) {
    this.mContext = paramContext;
    this.zzfke = paramLock;
    this.zzfko = paramzze;
    this.zzfmn = paramMap;
    this.zzfkj = paramzzq;
    this.zzfkm = paramMap1;
    this.zzfhm = paramzza;
    this.zzfju = paramzzbd;
    this.zzfng = paramzzce;
    int i = paramArrayList.size();
    byte b = 0;
    while (b < i) {
      paramContext = (Context)paramArrayList.get(b);
      b++;
      ((zzw)paramContext).zza(this);
    } 
    this.zzfnb = new zzbn(this, paramLooper);
    this.zzfna = paramLock.newCondition();
    this.zzfnd = new zzbc(this);
  }
  
  public final ConnectionResult blockingConnect() {
    connect();
    while (isConnecting()) {
      try {
        this.zzfna.await();
      } catch (InterruptedException interruptedException) {
        Thread.currentThread().interrupt();
        return new ConnectionResult(15, null);
      } 
    } 
    if (isConnected())
      return ConnectionResult.zzfff; 
    ConnectionResult connectionResult = this.zzfne;
    return (connectionResult != null) ? connectionResult : new ConnectionResult(13, null);
  }
  
  public final ConnectionResult blockingConnect(long paramLong, TimeUnit paramTimeUnit) {
    connect();
    for (paramLong = paramTimeUnit.toNanos(paramLong); isConnecting(); paramLong = this.zzfna.awaitNanos(paramLong)) {
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
    ConnectionResult connectionResult = this.zzfne;
    return (connectionResult != null) ? connectionResult : new ConnectionResult(13, null);
  }
  
  public final void connect() {
    this.zzfnd.connect();
  }
  
  public final void disconnect() {
    if (this.zzfnd.disconnect())
      this.zzfnc.clear(); 
  }
  
  public final void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {
    String str = String.valueOf(paramString).concat("  ");
    paramPrintWriter.append(paramString).append("mState=").println(this.zzfnd);
    for (Api<?> api : this.zzfkm.keySet()) {
      paramPrintWriter.append(paramString).append(api.getName()).println(":");
      ((Api.zze)this.zzfmn.get(api.zzafe())).dump(str, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    } 
  }
  
  @Nullable
  public final ConnectionResult getConnectionResult(@NonNull Api<?> paramApi) {
    Api.zzc zzc = paramApi.zzafe();
    if (this.zzfmn.containsKey(zzc)) {
      if (((Api.zze)this.zzfmn.get(zzc)).isConnected())
        return ConnectionResult.zzfff; 
      if (this.zzfnc.containsKey(zzc))
        return this.zzfnc.get(zzc); 
    } 
    return null;
  }
  
  public final boolean isConnected() {
    return this.zzfnd instanceof zzao;
  }
  
  public final boolean isConnecting() {
    return this.zzfnd instanceof zzar;
  }
  
  public final void onConnected(@Nullable Bundle paramBundle) {
    this.zzfke.lock();
    try {
      this.zzfnd.onConnected(paramBundle);
      return;
    } finally {
      this.zzfke.unlock();
    } 
  }
  
  public final void onConnectionSuspended(int paramInt) {
    this.zzfke.lock();
    try {
      this.zzfnd.onConnectionSuspended(paramInt);
      return;
    } finally {
      this.zzfke.unlock();
    } 
  }
  
  public final void zza(@NonNull ConnectionResult paramConnectionResult, @NonNull Api<?> paramApi, boolean paramBoolean) {
    this.zzfke.lock();
    try {
      this.zzfnd.zza(paramConnectionResult, paramApi, paramBoolean);
      return;
    } finally {
      this.zzfke.unlock();
    } 
  }
  
  final void zza(zzbm paramzzbm) {
    Message message = this.zzfnb.obtainMessage(1, paramzzbm);
    this.zzfnb.sendMessage(message);
  }
  
  final void zza(RuntimeException paramRuntimeException) {
    Message message = this.zzfnb.obtainMessage(2, paramRuntimeException);
    this.zzfnb.sendMessage(message);
  }
  
  public final boolean zza(zzcv paramzzcv) {
    return false;
  }
  
  public final void zzafp() {}
  
  public final void zzagi() {
    if (isConnected())
      ((zzao)this.zzfnd).zzagy(); 
  }
  
  final void zzahl() {
    this.zzfke.lock();
    try {
      zzar zzar = new zzar();
      this(this, this.zzfkj, this.zzfkm, this.zzfko, this.zzfhm, this.zzfke, this.mContext);
      this.zzfnd = zzar;
      this.zzfnd.begin();
      this.zzfna.signalAll();
      return;
    } finally {
      this.zzfke.unlock();
    } 
  }
  
  final void zzahm() {
    this.zzfke.lock();
    try {
      this.zzfju.zzahi();
      zzao zzao = new zzao();
      this(this);
      this.zzfnd = zzao;
      this.zzfnd.begin();
      this.zzfna.signalAll();
      return;
    } finally {
      this.zzfke.unlock();
    } 
  }
  
  public final <A extends Api.zzb, R extends com.google.android.gms.common.api.Result, T extends zzm<R, A>> T zzd(@NonNull T paramT) {
    paramT.zzagg();
    return this.zzfnd.zzd(paramT);
  }
  
  public final <A extends Api.zzb, T extends zzm<? extends com.google.android.gms.common.api.Result, A>> T zze(@NonNull T paramT) {
    paramT.zzagg();
    return this.zzfnd.zze(paramT);
  }
  
  final void zzg(ConnectionResult paramConnectionResult) {
    this.zzfke.lock();
    try {
      this.zzfne = paramConnectionResult;
      zzbc zzbc = new zzbc();
      this(this);
      this.zzfnd = zzbc;
      this.zzfnd.begin();
      this.zzfna.signalAll();
      return;
    } finally {
      this.zzfke.unlock();
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzbl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */