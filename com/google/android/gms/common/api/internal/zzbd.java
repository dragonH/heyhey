package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzad;
import com.google.android.gms.common.internal.zzae;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.common.zze;
import com.google.android.gms.internal.zzbcr;
import com.google.android.gms.internal.zzcps;
import com.google.android.gms.internal.zzcpt;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;

public final class zzbd extends GoogleApiClient implements zzce {
  private final Context mContext;
  
  private final Looper zzakg;
  
  private final int zzfhj;
  
  private final GoogleApiAvailability zzfhl;
  
  private Api.zza<? extends zzcps, zzcpt> zzfhm;
  
  private boolean zzfhp;
  
  private final Lock zzfke;
  
  private zzq zzfkj;
  
  private Map<Api<?>, Boolean> zzfkm;
  
  final Queue<zzm<?, ?>> zzfks = new LinkedList<zzm<?, ?>>();
  
  private final zzad zzfmg;
  
  private zzcd zzfmh = null;
  
  private volatile boolean zzfmi;
  
  private long zzfmj = 120000L;
  
  private long zzfmk = 5000L;
  
  private final zzbi zzfml;
  
  private zzby zzfmm;
  
  final Map<Api.zzc<?>, Api.zze> zzfmn;
  
  Set<Scope> zzfmo = new HashSet<Scope>();
  
  private final zzcn zzfmp = new zzcn();
  
  private final ArrayList<zzw> zzfmq;
  
  private Integer zzfmr = null;
  
  Set<zzdg> zzfms = null;
  
  final zzdj zzfmt;
  
  private final zzae zzfmu;
  
  public zzbd(Context paramContext, Lock paramLock, Looper paramLooper, zzq paramzzq, GoogleApiAvailability paramGoogleApiAvailability, Api.zza<? extends zzcps, zzcpt> paramzza, Map<Api<?>, Boolean> paramMap, List<GoogleApiClient.ConnectionCallbacks> paramList, List<GoogleApiClient.OnConnectionFailedListener> paramList1, Map<Api.zzc<?>, Api.zze> paramMap1, int paramInt1, int paramInt2, ArrayList<zzw> paramArrayList, boolean paramBoolean) {
    zzbe zzbe = new zzbe(this);
    this.zzfmu = zzbe;
    this.mContext = paramContext;
    this.zzfke = paramLock;
    this.zzfhp = false;
    this.zzfmg = new zzad(paramLooper, zzbe);
    this.zzakg = paramLooper;
    this.zzfml = new zzbi(this, paramLooper);
    this.zzfhl = paramGoogleApiAvailability;
    this.zzfhj = paramInt1;
    if (paramInt1 >= 0)
      this.zzfmr = Integer.valueOf(paramInt2); 
    this.zzfkm = paramMap;
    this.zzfmn = paramMap1;
    this.zzfmq = paramArrayList;
    this.zzfmt = new zzdj(paramMap1);
    for (GoogleApiClient.ConnectionCallbacks connectionCallbacks : paramList)
      this.zzfmg.registerConnectionCallbacks(connectionCallbacks); 
    for (GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener : paramList1)
      this.zzfmg.registerConnectionFailedListener(onConnectionFailedListener); 
    this.zzfkj = paramzzq;
    this.zzfhm = paramzza;
  }
  
  private final void resume() {
    this.zzfke.lock();
    try {
      if (this.zzfmi)
        zzahg(); 
      return;
    } finally {
      this.zzfke.unlock();
    } 
  }
  
  public static int zza(Iterable<Api.zze> paramIterable, boolean paramBoolean) {
    Iterator<Api.zze> iterator = paramIterable.iterator();
    boolean bool1 = false;
    boolean bool2 = false;
    while (iterator.hasNext()) {
      Api.zze zze = iterator.next();
      boolean bool = bool1;
      if (zze.zzaac())
        bool = true; 
      bool1 = bool;
      if (zze.zzaal()) {
        bool2 = true;
        bool1 = bool;
      } 
    } 
    return bool1 ? ((bool2 && paramBoolean) ? 2 : 1) : 3;
  }
  
  private final void zza(GoogleApiClient paramGoogleApiClient, zzda paramzzda, boolean paramBoolean) {
    zzbcr.zzfwh.zzd(paramGoogleApiClient).setResultCallback(new zzbh(this, paramzzda, paramBoolean, paramGoogleApiClient));
  }
  
  private final void zzahg() {
    this.zzfmg.zzakf();
    this.zzfmh.connect();
  }
  
  private final void zzahh() {
    this.zzfke.lock();
    try {
      if (zzahi())
        zzahg(); 
      return;
    } finally {
      this.zzfke.unlock();
    } 
  }
  
  private final void zzbt(int paramInt) {
    Integer integer = this.zzfmr;
    if (integer == null) {
      this.zzfmr = Integer.valueOf(paramInt);
    } else if (integer.intValue() != paramInt) {
      String str2 = zzbu(paramInt);
      String str1 = zzbu(this.zzfmr.intValue());
      StringBuilder stringBuilder = new StringBuilder(String.valueOf(str2).length() + 51 + String.valueOf(str1).length());
      stringBuilder.append("Cannot use sign-in mode: ");
      stringBuilder.append(str2);
      stringBuilder.append(". Mode was already set to ");
      stringBuilder.append(str1);
      IllegalStateException illegalStateException = new IllegalStateException(stringBuilder.toString());
      throw illegalStateException;
    } 
    if (this.zzfmh != null)
      return; 
    Iterator<Api.zze> iterator = this.zzfmn.values().iterator();
    boolean bool = false;
    paramInt = 0;
    while (iterator.hasNext()) {
      Api.zze zze = iterator.next();
      boolean bool1 = bool;
      if (zze.zzaac())
        bool1 = true; 
      bool = bool1;
      if (zze.zzaal()) {
        paramInt = 1;
        bool = bool1;
      } 
    } 
    int i = this.zzfmr.intValue();
    if (i != 1) {
      if (i == 2 && bool) {
        if (this.zzfhp) {
          this.zzfmh = new zzad(this.mContext, this.zzfke, this.zzakg, (zze)this.zzfhl, this.zzfmn, this.zzfkj, this.zzfkm, this.zzfhm, this.zzfmq, this, true);
          return;
        } 
        this.zzfmh = zzy.zza(this.mContext, this, this.zzfke, this.zzakg, (zze)this.zzfhl, this.zzfmn, this.zzfkj, this.zzfkm, this.zzfhm, this.zzfmq);
        return;
      } 
    } else if (bool) {
      if (paramInt != 0)
        throw new IllegalStateException("Cannot use SIGN_IN_MODE_REQUIRED with GOOGLE_SIGN_IN_API. Use connect(SIGN_IN_MODE_OPTIONAL) instead."); 
    } else {
      throw new IllegalStateException("SIGN_IN_MODE_REQUIRED cannot be used on a GoogleApiClient that does not contain any authenticated APIs. Use connect() instead.");
    } 
    if (this.zzfhp && paramInt == 0) {
      this.zzfmh = new zzad(this.mContext, this.zzfke, this.zzakg, (zze)this.zzfhl, this.zzfmn, this.zzfkj, this.zzfkm, this.zzfhm, this.zzfmq, this, false);
      return;
    } 
    this.zzfmh = new zzbl(this.mContext, this, this.zzfke, this.zzakg, (zze)this.zzfhl, this.zzfmn, this.zzfkj, this.zzfkm, this.zzfhm, this.zzfmq, this);
  }
  
  private static String zzbu(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? ((paramInt != 3) ? "UNKNOWN" : "SIGN_IN_MODE_NONE") : "SIGN_IN_MODE_OPTIONAL") : "SIGN_IN_MODE_REQUIRED";
  }
  
  public final ConnectionResult blockingConnect() {
    boolean bool2;
    null = Looper.myLooper();
    Looper looper = Looper.getMainLooper();
    boolean bool1 = true;
    if (null != looper) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    zzbp.zza(bool2, "blockingConnect must not be called on the UI thread");
    this.zzfke.lock();
    try {
      if (this.zzfhj >= 0) {
        if (this.zzfmr != null) {
          bool2 = bool1;
        } else {
          bool2 = false;
        } 
        zzbp.zza(bool2, "Sign-in mode should have been set explicitly by auto-manage.");
      } else {
        Integer integer = this.zzfmr;
        if (integer == null) {
          this.zzfmr = Integer.valueOf(zza(this.zzfmn.values(), false));
        } else if (integer.intValue() == 2) {
          IllegalStateException illegalStateException = new IllegalStateException();
          this("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
          throw illegalStateException;
        } 
      } 
      zzbt(this.zzfmr.intValue());
      this.zzfmg.zzakf();
      return this.zzfmh.blockingConnect();
    } finally {
      this.zzfke.unlock();
    } 
  }
  
  public final ConnectionResult blockingConnect(long paramLong, @NonNull TimeUnit paramTimeUnit) {
    boolean bool;
    if (Looper.myLooper() != Looper.getMainLooper()) {
      bool = true;
    } else {
      bool = false;
    } 
    zzbp.zza(bool, "blockingConnect must not be called on the UI thread");
    zzbp.zzb(paramTimeUnit, "TimeUnit must not be null");
    this.zzfke.lock();
    try {
      IllegalStateException illegalStateException;
      Integer integer = this.zzfmr;
      if (integer == null) {
        this.zzfmr = Integer.valueOf(zza(this.zzfmn.values(), false));
      } else if (integer.intValue() == 2) {
        illegalStateException = new IllegalStateException();
        this("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
        throw illegalStateException;
      } 
      zzbt(this.zzfmr.intValue());
      this.zzfmg.zzakf();
      return this.zzfmh.blockingConnect(paramLong, (TimeUnit)illegalStateException);
    } finally {
      this.zzfke.unlock();
    } 
  }
  
  public final PendingResult<Status> clearDefaultAccountAndReconnect() {
    boolean bool;
    zzbp.zza(super.isConnected(), "GoogleApiClient is not connected yet.");
    if (this.zzfmr.intValue() != 2) {
      bool = true;
    } else {
      bool = false;
    } 
    zzbp.zza(bool, "Cannot use clearDefaultAccountAndReconnect with GOOGLE_SIGN_IN_API");
    zzda zzda = new zzda(this);
    if (this.zzfmn.containsKey(zzbcr.zzdwp)) {
      zza(this, zzda, false);
    } else {
      AtomicReference<GoogleApiClient> atomicReference = new AtomicReference();
      zzbf zzbf = new zzbf(this, atomicReference, zzda);
      zzbg zzbg = new zzbg(this, zzda);
      GoogleApiClient googleApiClient = (new GoogleApiClient.Builder(this.mContext)).addApi(zzbcr.API).addConnectionCallbacks(zzbf).addOnConnectionFailedListener(zzbg).setHandler(this.zzfml).build();
      atomicReference.set(googleApiClient);
      googleApiClient.connect();
    } 
    return zzda;
  }
  
  public final void connect() {
    this.zzfke.lock();
    try {
      int i = this.zzfhj;
      boolean bool = false;
      if (i >= 0) {
        if (this.zzfmr != null)
          bool = true; 
        zzbp.zza(bool, "Sign-in mode should have been set explicitly by auto-manage.");
      } else {
        Integer integer = this.zzfmr;
        if (integer == null) {
          this.zzfmr = Integer.valueOf(zza(this.zzfmn.values(), false));
        } else if (integer.intValue() == 2) {
          IllegalStateException illegalStateException = new IllegalStateException();
          this("Cannot call connect() when SignInMode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
          throw illegalStateException;
        } 
      } 
      super.connect(this.zzfmr.intValue());
      return;
    } finally {
      this.zzfke.unlock();
    } 
  }
  
  public final void connect(int paramInt) {
    this.zzfke.lock();
    boolean bool1 = true;
    boolean bool2 = bool1;
    if (paramInt != 3) {
      bool2 = bool1;
      if (paramInt != 1)
        if (paramInt == 2) {
          bool2 = bool1;
        } else {
          bool2 = false;
        }  
    } 
    try {
      StringBuilder stringBuilder = new StringBuilder();
      this(33);
      stringBuilder.append("Illegal sign-in mode: ");
      stringBuilder.append(paramInt);
      zzbp.zzb(bool2, stringBuilder.toString());
      zzbt(paramInt);
      zzahg();
      return;
    } finally {
      this.zzfke.unlock();
    } 
  }
  
  public final void disconnect() {
    this.zzfke.lock();
    try {
      this.zzfmt.release();
      zzcd zzcd1 = this.zzfmh;
      if (zzcd1 != null)
        zzcd1.disconnect(); 
      this.zzfmp.release();
      for (zzm<?, ?> zzm : this.zzfks) {
        zzm.zza((zzdm)null);
        zzm.cancel();
      } 
      this.zzfks.clear();
      zzcd1 = this.zzfmh;
      if (zzcd1 != null) {
        zzahi();
        this.zzfmg.zzake();
      } 
      return;
    } finally {
      this.zzfke.unlock();
    } 
  }
  
  public final void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {
    paramPrintWriter.append(paramString).append("mContext=").println(this.mContext);
    paramPrintWriter.append(paramString).append("mResuming=").print(this.zzfmi);
    paramPrintWriter.append(" mWorkQueue.size()=").print(this.zzfks.size());
    zzdj zzdj1 = this.zzfmt;
    paramPrintWriter.append(" mUnconsumedApiCalls.size()=").println(zzdj1.zzfps.size());
    zzcd zzcd1 = this.zzfmh;
    if (zzcd1 != null)
      zzcd1.dump(paramString, paramFileDescriptor, paramPrintWriter, paramArrayOfString); 
  }
  
  @NonNull
  public final ConnectionResult getConnectionResult(@NonNull Api<?> paramApi) {
    this.zzfke.lock();
    try {
      if (super.isConnected() || this.zzfmi) {
        ConnectionResult connectionResult;
        if (this.zzfmn.containsKey(paramApi.zzafe())) {
          Exception exception;
          ConnectionResult connectionResult1 = this.zzfmh.getConnectionResult(paramApi);
          if (connectionResult1 == null) {
            ConnectionResult connectionResult2;
            if (this.zzfmi) {
              connectionResult2 = ConnectionResult.zzfff;
              return connectionResult2;
            } 
            Log.w("GoogleApiClientImpl", zzahk());
            String str = String.valueOf(connectionResult2.getName()).concat(" requested in getConnectionResult is not connected but is not present in the failed  connections map");
            exception = new Exception();
            this();
            Log.wtf("GoogleApiClientImpl", str, exception);
            connectionResult = new ConnectionResult(8, null);
            return connectionResult;
          } 
          return (ConnectionResult)exception;
        } 
        IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
        this(String.valueOf(connectionResult.getName()).concat(" was never registered with GoogleApiClient"));
        throw illegalArgumentException;
      } 
      IllegalStateException illegalStateException = new IllegalStateException();
      this("Cannot invoke getConnectionResult unless GoogleApiClient is connected");
      throw illegalStateException;
    } finally {
      this.zzfke.unlock();
    } 
  }
  
  public final Context getContext() {
    return this.mContext;
  }
  
  public final Looper getLooper() {
    return this.zzakg;
  }
  
  public final boolean hasConnectedApi(@NonNull Api<?> paramApi) {
    if (!super.isConnected())
      return false; 
    Api.zze zze = this.zzfmn.get(paramApi.zzafe());
    return (zze != null && zze.isConnected());
  }
  
  public final boolean isConnected() {
    zzcd zzcd1 = this.zzfmh;
    return (zzcd1 != null && zzcd1.isConnected());
  }
  
  public final boolean isConnecting() {
    zzcd zzcd1 = this.zzfmh;
    return (zzcd1 != null && zzcd1.isConnecting());
  }
  
  public final boolean isConnectionCallbacksRegistered(@NonNull GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks) {
    return this.zzfmg.isConnectionCallbacksRegistered(paramConnectionCallbacks);
  }
  
  public final boolean isConnectionFailedListenerRegistered(@NonNull GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener) {
    return this.zzfmg.isConnectionFailedListenerRegistered(paramOnConnectionFailedListener);
  }
  
  public final void reconnect() {
    super.disconnect();
    super.connect();
  }
  
  public final void registerConnectionCallbacks(@NonNull GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks) {
    this.zzfmg.registerConnectionCallbacks(paramConnectionCallbacks);
  }
  
  public final void registerConnectionFailedListener(@NonNull GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener) {
    this.zzfmg.registerConnectionFailedListener(paramOnConnectionFailedListener);
  }
  
  public final void stopAutoManage(@NonNull FragmentActivity paramFragmentActivity) {
    zzcf zzcf = new zzcf((Activity)paramFragmentActivity);
    if (this.zzfhj >= 0) {
      zzi.zza(zzcf).zzbp(this.zzfhj);
      return;
    } 
    throw new IllegalStateException("Called stopAutoManage but automatic lifecycle management is not enabled.");
  }
  
  public final void unregisterConnectionCallbacks(@NonNull GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks) {
    this.zzfmg.unregisterConnectionCallbacks(paramConnectionCallbacks);
  }
  
  public final void unregisterConnectionFailedListener(@NonNull GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener) {
    this.zzfmg.unregisterConnectionFailedListener(paramOnConnectionFailedListener);
  }
  
  @NonNull
  public final <C extends Api.zze> C zza(@NonNull Api.zzc<C> paramzzc) {
    Api.zze zze = this.zzfmn.get(paramzzc);
    zzbp.zzb(zze, "Appropriate Api was not requested.");
    return (C)zze;
  }
  
  public final void zza(zzdg paramzzdg) {
    this.zzfke.lock();
    try {
      if (this.zzfms == null) {
        HashSet<zzdg> hashSet = new HashSet();
        this();
        this.zzfms = hashSet;
      } 
      this.zzfms.add(paramzzdg);
      return;
    } finally {
      this.zzfke.unlock();
    } 
  }
  
  public final boolean zza(@NonNull Api<?> paramApi) {
    return this.zzfmn.containsKey(paramApi.zzafe());
  }
  
  public final boolean zza(zzcv paramzzcv) {
    zzcd zzcd1 = this.zzfmh;
    return (zzcd1 != null && zzcd1.zza(paramzzcv));
  }
  
  public final void zzafp() {
    zzcd zzcd1 = this.zzfmh;
    if (zzcd1 != null)
      zzcd1.zzafp(); 
  }
  
  final boolean zzahi() {
    if (!this.zzfmi)
      return false; 
    this.zzfmi = false;
    this.zzfml.removeMessages(2);
    this.zzfml.removeMessages(1);
    zzby zzby1 = this.zzfmm;
    if (zzby1 != null) {
      zzby1.unregister();
      this.zzfmm = null;
    } 
    return true;
  }
  
  final boolean zzahj() {
    this.zzfke.lock();
    try {
      Set<zzdg> set = this.zzfms;
      if (set == null)
        return false; 
      boolean bool = set.isEmpty();
      return bool ^ true;
    } finally {
      this.zzfke.unlock();
    } 
  }
  
  final String zzahk() {
    StringWriter stringWriter = new StringWriter();
    super.dump("", null, new PrintWriter(stringWriter), null);
    return stringWriter.toString();
  }
  
  public final void zzb(zzdg paramzzdg) {
    this.zzfke.lock();
    try {
      Exception exception;
      String str;
      Set<zzdg> set = this.zzfms;
      if (set == null) {
        str = "Attempted to remove pending transform when no transforms are registered.";
        exception = new Exception();
        this();
      } else if (!str.remove(exception)) {
        str = "Failed to remove pending transform - this may lead to memory leaks!";
        exception = new Exception();
      } else {
        if (!zzahj())
          this.zzfmh.zzagi(); 
        this.zzfke.unlock();
      } 
      Log.wtf("GoogleApiClientImpl", str, exception);
    } finally {
      this.zzfke.unlock();
    } 
  }
  
  public final void zzc(ConnectionResult paramConnectionResult) {
    if (!zze.zze(this.mContext, paramConnectionResult.getErrorCode()))
      zzahi(); 
    if (!this.zzfmi) {
      this.zzfmg.zzk(paramConnectionResult);
      this.zzfmg.zzake();
    } 
  }
  
  public final <A extends Api.zzb, R extends Result, T extends zzm<R, A>> T zzd(@NonNull T paramT) {
    String str;
    if (paramT.zzafe() != null) {
      bool = true;
    } else {
      bool = false;
    } 
    zzbp.zzb(bool, "This task can not be enqueued (it's probably a Batch or malformed)");
    boolean bool = this.zzfmn.containsKey(paramT.zzafe());
    if (paramT.zzafj() != null) {
      str = paramT.zzafj().getName();
    } else {
      str = "the API";
    } 
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 65);
    stringBuilder.append("GoogleApiClient is not configured to use ");
    stringBuilder.append(str);
    stringBuilder.append(" required for this call.");
    zzbp.zzb(bool, stringBuilder.toString());
    this.zzfke.lock();
    try {
      zzcd zzcd1 = this.zzfmh;
      if (zzcd1 == null) {
        this.zzfks.add((zzm<?, ?>)paramT);
        return paramT;
      } 
      paramT = zzcd1.zzd(paramT);
      return paramT;
    } finally {
      this.zzfke.unlock();
    } 
  }
  
  public final <A extends Api.zzb, T extends zzm<? extends Result, A>> T zze(@NonNull T paramT) {
    String str;
    if (paramT.zzafe() != null) {
      bool = true;
    } else {
      bool = false;
    } 
    zzbp.zzb(bool, "This task can not be executed (it's probably a Batch or malformed)");
    boolean bool = this.zzfmn.containsKey(paramT.zzafe());
    if (paramT.zzafj() != null) {
      str = paramT.zzafj().getName();
    } else {
      str = "the API";
    } 
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 65);
    stringBuilder.append("GoogleApiClient is not configured to use ");
    stringBuilder.append(str);
    stringBuilder.append(" required for this call.");
    zzbp.zzb(bool, stringBuilder.toString());
    this.zzfke.lock();
    try {
      if (this.zzfmh != null) {
        if (this.zzfmi) {
          zzm<? extends Result> zzm;
          this.zzfks.add((zzm<?, ?>)paramT);
          while (true) {
            T t = paramT;
            if (!this.zzfks.isEmpty()) {
              zzm = (zzm)this.zzfks.remove();
              this.zzfmt.zzb(zzm);
              zzm.zzt(Status.zzfhx);
              continue;
            } 
            break;
          } 
          return (T)zzm;
        } 
        str = this.zzfmh.zze((String)paramT);
        return (T)str;
      } 
      IllegalStateException illegalStateException = new IllegalStateException();
      this("GoogleApiClient is not connected yet.");
      throw illegalStateException;
    } finally {
      this.zzfke.unlock();
    } 
  }
  
  public final void zzf(int paramInt, boolean paramBoolean) {
    if (paramInt == 1 && !paramBoolean && !this.zzfmi) {
      this.zzfmi = true;
      if (this.zzfmm == null)
        this.zzfmm = GoogleApiAvailability.zza(this.mContext.getApplicationContext(), new zzbj(this)); 
      zzbi zzbi1 = this.zzfml;
      zzbi1.sendMessageDelayed(zzbi1.obtainMessage(1), this.zzfmj);
      zzbi1 = this.zzfml;
      zzbi1.sendMessageDelayed(zzbi1.obtainMessage(2), this.zzfmk);
    } 
    this.zzfmt.zzaiq();
    this.zzfmg.zzce(paramInt);
    this.zzfmg.zzake();
    if (paramInt == 2)
      zzahg(); 
  }
  
  public final void zzj(Bundle paramBundle) {
    while (!this.zzfks.isEmpty())
      super.zze(this.zzfks.remove()); 
    this.zzfmg.zzk(paramBundle);
  }
  
  public final <L> zzcj<L> zzp(@NonNull L paramL) {
    this.zzfke.lock();
    try {
      return this.zzfmp.zza(paramL, this.zzakg, "NO_TYPE");
    } finally {
      this.zzfke.unlock();
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzbd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */