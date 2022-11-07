package com.google.android.gms.common.api;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.internal.zzak;
import com.google.android.gms.common.api.internal.zzbp;
import com.google.android.gms.common.api.internal.zzbr;
import com.google.android.gms.common.api.internal.zzbx;
import com.google.android.gms.common.api.internal.zzcw;
import com.google.android.gms.common.api.internal.zzcz;
import com.google.android.gms.common.api.internal.zzdd;
import com.google.android.gms.common.api.internal.zzg;
import com.google.android.gms.common.api.internal.zzh;
import com.google.android.gms.common.api.internal.zzm;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Collections;
import java.util.Set;

public class GoogleApi<O extends Api.ApiOptions> {
  private final Context mContext;
  
  private final int mId;
  
  private final Looper zzakg;
  
  private final Api<O> zzfdg;
  
  private final O zzfgr;
  
  private final zzh<O> zzfgs;
  
  private final GoogleApiClient zzfgt;
  
  private final zzcz zzfgu;
  
  protected final zzbp zzfgv;
  
  @MainThread
  public GoogleApi(@NonNull Activity paramActivity, Api<O> paramApi, O paramO, zza paramzza) {
    zzbp.zzb(paramActivity, "Null activity is not permitted.");
    zzbp.zzb(paramApi, "Api must not be null.");
    zzbp.zzb(paramzza, "Settings must not be null; use Settings.DEFAULT_SETTINGS instead.");
    Context context = paramActivity.getApplicationContext();
    this.mContext = context;
    this.zzfdg = paramApi;
    this.zzfgr = paramO;
    this.zzakg = paramzza.zzfgy;
    zzh<O> zzh1 = zzh.zza(paramApi, (Api.ApiOptions)paramO);
    this.zzfgs = zzh1;
    this.zzfgt = (GoogleApiClient)new zzbx(this);
    zzbp zzbp1 = zzbp.zzca(context);
    this.zzfgv = zzbp1;
    this.mId = zzbp1.zzahq();
    this.zzfgu = paramzza.zzfgx;
    zzak.zza(paramActivity, zzbp1, zzh1);
    zzbp1.zza(this);
  }
  
  @Deprecated
  public GoogleApi(@NonNull Activity paramActivity, Api<O> paramApi, O paramO, zzcz paramzzcz) {
    this(paramActivity, paramApi, paramO, (new zzd()).zza(paramzzcz).zza(paramActivity.getMainLooper()).zzafn());
  }
  
  protected GoogleApi(@NonNull Context paramContext, Api<O> paramApi, Looper paramLooper) {
    zzbp.zzb(paramContext, "Null context is not permitted.");
    zzbp.zzb(paramApi, "Api must not be null.");
    zzbp.zzb(paramLooper, "Looper must not be null.");
    paramContext = paramContext.getApplicationContext();
    this.mContext = paramContext;
    this.zzfdg = paramApi;
    this.zzfgr = null;
    this.zzakg = paramLooper;
    this.zzfgs = zzh.zzb(paramApi);
    this.zzfgt = (GoogleApiClient)new zzbx(this);
    zzbp zzbp1 = zzbp.zzca(paramContext);
    this.zzfgv = zzbp1;
    this.mId = zzbp1.zzahq();
    this.zzfgu = (zzcz)new zzg();
  }
  
  @Deprecated
  public GoogleApi(@NonNull Context paramContext, Api<O> paramApi, O paramO, Looper paramLooper, zzcz paramzzcz) {
    this(paramContext, paramApi, (O)null, (new zzd()).zza(paramLooper).zza(paramzzcz).zzafn());
  }
  
  public GoogleApi(@NonNull Context paramContext, Api<O> paramApi, O paramO, zza paramzza) {
    zzbp.zzb(paramContext, "Null context is not permitted.");
    zzbp.zzb(paramApi, "Api must not be null.");
    zzbp.zzb(paramzza, "Settings must not be null; use Settings.DEFAULT_SETTINGS instead.");
    paramContext = paramContext.getApplicationContext();
    this.mContext = paramContext;
    this.zzfdg = paramApi;
    this.zzfgr = paramO;
    this.zzakg = paramzza.zzfgy;
    this.zzfgs = zzh.zza(paramApi, (Api.ApiOptions)paramO);
    this.zzfgt = (GoogleApiClient)new zzbx(this);
    zzbp zzbp1 = zzbp.zzca(paramContext);
    this.zzfgv = zzbp1;
    this.mId = zzbp1.zzahq();
    this.zzfgu = paramzza.zzfgx;
    zzbp1.zza(this);
  }
  
  @Deprecated
  public GoogleApi(@NonNull Context paramContext, Api<O> paramApi, O paramO, zzcz paramzzcz) {
    this(paramContext, paramApi, paramO, (new zzd()).zza(paramzzcz).zzafn());
  }
  
  private final <A extends Api.zzb, T extends zzm<? extends Result, A>> T zza(int paramInt, @NonNull T paramT) {
    paramT.zzagg();
    this.zzfgv.zza(this, paramInt, (zzm)paramT);
    return paramT;
  }
  
  private final <TResult, A extends Api.zzb> Task<TResult> zza(int paramInt, @NonNull zzdd<A, TResult> paramzzdd) {
    TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
    this.zzfgv.zza(this, paramInt, paramzzdd, taskCompletionSource, this.zzfgu);
    return taskCompletionSource.getTask();
  }
  
  private final zzr zzafm() {
    Account account;
    zzr zzr = new zzr();
    O o2 = this.zzfgr;
    if (o2 instanceof Api.ApiOptions.HasGoogleSignInAccountOptions) {
      account = ((Api.ApiOptions.HasGoogleSignInAccountOptions)o2).getGoogleSignInAccount().getAccount();
    } else if (account instanceof Api.ApiOptions.HasAccountOptions) {
      account = ((Api.ApiOptions.HasAccountOptions)account).getAccount();
    } else {
      account = null;
    } 
    zzr = zzr.zze(account);
    O o1 = this.zzfgr;
    if (o1 instanceof Api.ApiOptions.HasGoogleSignInAccountOptions) {
      GoogleSignInAccount googleSignInAccount = ((Api.ApiOptions.HasGoogleSignInAccountOptions)o1).getGoogleSignInAccount();
      if (googleSignInAccount != null) {
        Set set1 = googleSignInAccount.getGrantedScopes();
        return zzr.zze(set1);
      } 
    } 
    Set<?> set = Collections.emptySet();
    return zzr.zze(set);
  }
  
  public final Context getApplicationContext() {
    return this.mContext;
  }
  
  public final int getInstanceId() {
    return this.mId;
  }
  
  public final Looper getLooper() {
    return this.zzakg;
  }
  
  @WorkerThread
  public Api.zze zza(Looper paramLooper, zzbr<O> paramzzbr) {
    zzq zzq = zzafm().zzfz(this.mContext.getPackageName()).zzga(this.mContext.getClass().getName()).zzajz();
    return this.zzfdg.zzafd().zza(this.mContext, paramLooper, zzq, this.zzfgr, (GoogleApiClient.ConnectionCallbacks)paramzzbr, (GoogleApiClient.OnConnectionFailedListener)paramzzbr);
  }
  
  public zzcw zza(Context paramContext, Handler paramHandler) {
    return new zzcw(paramContext, paramHandler, zzafm().zzajz());
  }
  
  public final <A extends Api.zzb, T extends zzm<? extends Result, A>> T zza(@NonNull T paramT) {
    return zza(0, paramT);
  }
  
  public final <TResult, A extends Api.zzb> Task<TResult> zza(zzdd<A, TResult> paramzzdd) {
    return zza(0, paramzzdd);
  }
  
  public final Api<O> zzafj() {
    return this.zzfdg;
  }
  
  public final zzh<O> zzafk() {
    return this.zzfgs;
  }
  
  public final GoogleApiClient zzafl() {
    return this.zzfgt;
  }
  
  public final <A extends Api.zzb, T extends zzm<? extends Result, A>> T zzb(@NonNull T paramT) {
    return zza(1, paramT);
  }
  
  public final <TResult, A extends Api.zzb> Task<TResult> zzb(zzdd<A, TResult> paramzzdd) {
    return zza(1, paramzzdd);
  }
  
  public final <A extends Api.zzb, T extends zzm<? extends Result, A>> T zzc(@NonNull T paramT) {
    return zza(2, paramT);
  }
  
  public static final class zza {
    public static final zza zzfgw = (new zzd()).zzafn();
    
    public final zzcz zzfgx;
    
    public final Looper zzfgy;
    
    private zza(zzcz param1zzcz, Account param1Account, Looper param1Looper) {
      this.zzfgx = param1zzcz;
      this.zzfgy = param1Looper;
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/GoogleApi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */