package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzam;
import com.google.android.gms.common.internal.zzbs;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.common.internal.zzs;
import com.google.android.gms.common.zze;
import com.google.android.gms.internal.zzcps;
import com.google.android.gms.internal.zzcpt;
import com.google.android.gms.internal.zzcqf;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;

public final class zzar implements zzbk {
  private final Context mContext;
  
  private final Api.zza<? extends zzcps, zzcpt> zzfhm;
  
  private final Lock zzfke;
  
  private final zzq zzfkj;
  
  private final Map<Api<?>, Boolean> zzfkm;
  
  private final zze zzfko;
  
  private ConnectionResult zzfkx;
  
  private final zzbl zzflh;
  
  private int zzflk;
  
  private int zzfll = 0;
  
  private int zzflm;
  
  private final Bundle zzfln = new Bundle();
  
  private final Set<Api.zzc> zzflo = new HashSet<Api.zzc>();
  
  private zzcps zzflp;
  
  private boolean zzflq;
  
  private boolean zzflr;
  
  private boolean zzfls;
  
  private zzam zzflt;
  
  private boolean zzflu;
  
  private boolean zzflv;
  
  private ArrayList<Future<?>> zzflw = new ArrayList<Future<?>>();
  
  public zzar(zzbl paramzzbl, zzq paramzzq, Map<Api<?>, Boolean> paramMap, zze paramzze, Api.zza<? extends zzcps, zzcpt> paramzza, Lock paramLock, Context paramContext) {
    this.zzflh = paramzzbl;
    this.zzfkj = paramzzq;
    this.zzfkm = paramMap;
    this.zzfko = paramzze;
    this.zzfhm = paramzza;
    this.zzfke = paramLock;
    this.mContext = paramContext;
  }
  
  private final void zza(zzcqf paramzzcqf) {
    StringBuilder stringBuilder;
    if (!zzbr(0))
      return; 
    ConnectionResult connectionResult = paramzzcqf.zzagd();
    if (connectionResult.isSuccess()) {
      zzbs zzbs = paramzzcqf.zzbcc();
      ConnectionResult connectionResult1 = zzbs.zzagd();
      if (!connectionResult1.isSuccess()) {
        String str = String.valueOf(connectionResult1);
        stringBuilder = new StringBuilder(str.length() + 48);
        stringBuilder.append("Sign-in succeeded with resolve account failure: ");
        stringBuilder.append(str);
        Log.wtf("GoogleApiClientConnecting", stringBuilder.toString(), new Exception());
        zze(connectionResult1);
        return;
      } 
      this.zzfls = true;
      this.zzflt = stringBuilder.zzakl();
      this.zzflu = stringBuilder.zzakm();
      this.zzflv = stringBuilder.zzakn();
      zzahb();
      return;
    } 
    if (zzd((ConnectionResult)stringBuilder)) {
      zzahd();
      zzahb();
      return;
    } 
    zze((ConnectionResult)stringBuilder);
  }
  
  private final boolean zzaha() {
    int i = this.zzflm - 1;
    this.zzflm = i;
    if (i > 0)
      return false; 
    if (i < 0) {
      Log.w("GoogleApiClientConnecting", this.zzflh.zzfju.zzahk());
      Log.wtf("GoogleApiClientConnecting", "GoogleApiClient received too many callbacks for the given step. Clients may be in an unexpected state; GoogleApiClient will now disconnect.", new Exception());
      ConnectionResult connectionResult1 = new ConnectionResult(8, null);
      zze(connectionResult1);
      return false;
    } 
    ConnectionResult connectionResult = this.zzfkx;
    if (connectionResult != null) {
      this.zzflh.zzfnf = this.zzflk;
      zze(connectionResult);
      return false;
    } 
    return true;
  }
  
  private final void zzahb() {
    if (this.zzflm != 0)
      return; 
    if (!this.zzflr || this.zzfls) {
      ArrayList<Api.zze> arrayList = new ArrayList();
      this.zzfll = 1;
      this.zzflm = this.zzflh.zzfmn.size();
      for (Api.zzc<?> zzc : this.zzflh.zzfmn.keySet()) {
        if (this.zzflh.zzfnc.containsKey(zzc)) {
          if (zzaha())
            zzahc(); 
          continue;
        } 
        arrayList.add(this.zzflh.zzfmn.get(zzc));
      } 
      if (!arrayList.isEmpty())
        this.zzflw.add(zzbo.zzahn().submit(new zzax(this, arrayList))); 
    } 
  }
  
  private final void zzahc() {
    Bundle bundle;
    this.zzflh.zzahm();
    zzbo.zzahn().execute(new zzas(this));
    zzcps zzcps1 = this.zzflp;
    if (zzcps1 != null) {
      if (this.zzflu)
        zzcps1.zza(this.zzflt, this.zzflv); 
      zzbf(false);
    } 
    for (Api.zzc<?> zzc : this.zzflh.zzfnc.keySet())
      ((Api.zze)this.zzflh.zzfmn.get(zzc)).disconnect(); 
    if (this.zzfln.isEmpty()) {
      zzcps1 = null;
    } else {
      bundle = this.zzfln;
    } 
    this.zzflh.zzfng.zzj(bundle);
  }
  
  private final void zzahd() {
    this.zzflr = false;
    this.zzflh.zzfju.zzfmo = Collections.emptySet();
    for (Api.zzc<?> zzc : this.zzflo) {
      if (!this.zzflh.zzfnc.containsKey(zzc))
        this.zzflh.zzfnc.put(zzc, new ConnectionResult(17, null)); 
    } 
  }
  
  private final void zzahe() {
    ArrayList<Future<?>> arrayList = this.zzflw;
    int i = arrayList.size();
    byte b = 0;
    while (b < i) {
      Future future = (Future)arrayList.get(b);
      b++;
      ((Future)future).cancel(true);
    } 
    this.zzflw.clear();
  }
  
  private final Set<Scope> zzahf() {
    if (this.zzfkj == null)
      return Collections.emptySet(); 
    HashSet<Scope> hashSet = new HashSet(this.zzfkj.zzajr());
    Map map = this.zzfkj.zzajt();
    for (Api api : map.keySet()) {
      if (!this.zzflh.zzfnc.containsKey(api.zzafe()))
        hashSet.addAll(((zzs)map.get(api)).zzecm); 
    } 
    return hashSet;
  }
  
  private final void zzb(ConnectionResult paramConnectionResult, Api<?> paramApi, boolean paramBoolean) {
    // Byte code:
    //   0: aload_2
    //   1: invokevirtual zzafc : ()Lcom/google/android/gms/common/api/Api$zzd;
    //   4: invokevirtual getPriority : ()I
    //   7: istore #4
    //   9: iconst_0
    //   10: istore #5
    //   12: iload_3
    //   13: ifeq -> 58
    //   16: aload_1
    //   17: invokevirtual hasResolution : ()Z
    //   20: ifeq -> 29
    //   23: iconst_1
    //   24: istore #6
    //   26: goto -> 49
    //   29: aload_0
    //   30: getfield zzfko : Lcom/google/android/gms/common/zze;
    //   33: aload_1
    //   34: invokevirtual getErrorCode : ()I
    //   37: invokevirtual zzbn : (I)Landroid/content/Intent;
    //   40: ifnull -> 46
    //   43: goto -> 23
    //   46: iconst_0
    //   47: istore #6
    //   49: iload #5
    //   51: istore #7
    //   53: iload #6
    //   55: ifeq -> 81
    //   58: aload_0
    //   59: getfield zzfkx : Lcom/google/android/gms/common/ConnectionResult;
    //   62: ifnull -> 78
    //   65: iload #5
    //   67: istore #7
    //   69: iload #4
    //   71: aload_0
    //   72: getfield zzflk : I
    //   75: if_icmpge -> 81
    //   78: iconst_1
    //   79: istore #7
    //   81: iload #7
    //   83: ifeq -> 97
    //   86: aload_0
    //   87: aload_1
    //   88: putfield zzfkx : Lcom/google/android/gms/common/ConnectionResult;
    //   91: aload_0
    //   92: iload #4
    //   94: putfield zzflk : I
    //   97: aload_0
    //   98: getfield zzflh : Lcom/google/android/gms/common/api/internal/zzbl;
    //   101: getfield zzfnc : Ljava/util/Map;
    //   104: aload_2
    //   105: invokevirtual zzafe : ()Lcom/google/android/gms/common/api/Api$zzc;
    //   108: aload_1
    //   109: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   114: pop
    //   115: return
  }
  
  private final void zzbf(boolean paramBoolean) {
    zzcps zzcps1 = this.zzflp;
    if (zzcps1 != null) {
      if (zzcps1.isConnected() && paramBoolean)
        this.zzflp.zzbbv(); 
      this.zzflp.disconnect();
      this.zzflt = null;
    } 
  }
  
  private final boolean zzbr(int paramInt) {
    if (this.zzfll != paramInt) {
      Log.w("GoogleApiClientConnecting", this.zzflh.zzfju.zzahk());
      String str1 = String.valueOf(this);
      StringBuilder stringBuilder = new StringBuilder(str1.length() + 23);
      stringBuilder.append("Unexpected callback in ");
      stringBuilder.append(str1);
      Log.w("GoogleApiClientConnecting", stringBuilder.toString());
      int i = this.zzflm;
      stringBuilder = new StringBuilder(33);
      stringBuilder.append("mRemainingConnections=");
      stringBuilder.append(i);
      Log.w("GoogleApiClientConnecting", stringBuilder.toString());
      str1 = zzbs(this.zzfll);
      String str2 = zzbs(paramInt);
      stringBuilder = new StringBuilder(String.valueOf(str1).length() + 70 + String.valueOf(str2).length());
      stringBuilder.append("GoogleApiClient connecting is in step ");
      stringBuilder.append(str1);
      stringBuilder.append(" but received callback for step ");
      stringBuilder.append(str2);
      Log.wtf("GoogleApiClientConnecting", stringBuilder.toString(), new Exception());
      zze(new ConnectionResult(8, null));
      return false;
    } 
    return true;
  }
  
  private static String zzbs(int paramInt) {
    return (paramInt != 0) ? ((paramInt != 1) ? "UNKNOWN" : "STEP_GETTING_REMOTE_SERVICE") : "STEP_SERVICE_BINDINGS_AND_SIGN_IN";
  }
  
  private final boolean zzd(ConnectionResult paramConnectionResult) {
    return (this.zzflq && !paramConnectionResult.hasResolution());
  }
  
  private final void zze(ConnectionResult paramConnectionResult) {
    zzahe();
    zzbf(paramConnectionResult.hasResolution() ^ true);
    this.zzflh.zzg(paramConnectionResult);
    this.zzflh.zzfng.zzc(paramConnectionResult);
  }
  
  public final void begin() {
    this.zzflh.zzfnc.clear();
    this.zzflr = false;
    this.zzfkx = null;
    this.zzfll = 0;
    this.zzflq = true;
    this.zzfls = false;
    this.zzflu = false;
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    Iterator<Api> iterator = this.zzfkm.keySet().iterator();
    int i = 0;
    while (iterator.hasNext()) {
      byte b;
      Api<?> api = iterator.next();
      Api.zze zze1 = this.zzflh.zzfmn.get(api.zzafe());
      if (api.zzafc().getPriority() == 1) {
        b = 1;
      } else {
        b = 0;
      } 
      i |= b;
      boolean bool = ((Boolean)this.zzfkm.get(api)).booleanValue();
      if (zze1.zzaac()) {
        this.zzflr = true;
        if (bool) {
          this.zzflo.add(api.zzafe());
        } else {
          this.zzflq = false;
        } 
      } 
      hashMap.put(zze1, new zzat(this, api, bool));
    } 
    if (i != 0)
      this.zzflr = false; 
    if (this.zzflr) {
      this.zzfkj.zzc(Integer.valueOf(System.identityHashCode(this.zzflh.zzfju)));
      zzba zzba = new zzba(this, null);
      Api.zza<? extends zzcps, zzcpt> zza1 = this.zzfhm;
      Context context = this.mContext;
      Looper looper = this.zzflh.zzfju.getLooper();
      zzq zzq1 = this.zzfkj;
      this.zzflp = (zzcps)zza1.zza(context, looper, zzq1, zzq1.zzajx(), zzba, zzba);
    } 
    this.zzflm = this.zzflh.zzfmn.size();
    this.zzflw.add(zzbo.zzahn().submit(new zzau(this, (Map)hashMap)));
  }
  
  public final void connect() {}
  
  public final boolean disconnect() {
    zzahe();
    zzbf(true);
    this.zzflh.zzg(null);
    return true;
  }
  
  public final void onConnected(Bundle paramBundle) {
    if (!zzbr(1))
      return; 
    if (paramBundle != null)
      this.zzfln.putAll(paramBundle); 
    if (zzaha())
      zzahc(); 
  }
  
  public final void onConnectionSuspended(int paramInt) {
    zze(new ConnectionResult(8, null));
  }
  
  public final void zza(ConnectionResult paramConnectionResult, Api<?> paramApi, boolean paramBoolean) {
    if (!zzbr(1))
      return; 
    zzb(paramConnectionResult, paramApi, paramBoolean);
    if (zzaha())
      zzahc(); 
  }
  
  public final <A extends Api.zzb, R extends com.google.android.gms.common.api.Result, T extends zzm<R, A>> T zzd(T paramT) {
    this.zzflh.zzfju.zzfks.add((zzm<?, ?>)paramT);
    return paramT;
  }
  
  public final <A extends Api.zzb, T extends zzm<? extends com.google.android.gms.common.api.Result, A>> T zze(T paramT) {
    throw new IllegalStateException("GoogleApiClient is not connected yet.");
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */