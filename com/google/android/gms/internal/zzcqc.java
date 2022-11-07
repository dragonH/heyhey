package com.google.android.gms.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.internal.zzy;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzam;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.internal.zzd;
import com.google.android.gms.common.internal.zzj;
import com.google.android.gms.common.internal.zzm;
import com.google.android.gms.common.internal.zzq;

public final class zzcqc extends zzaa<zzcqa> implements zzcps {
  private final zzq zzfkj;
  
  private Integer zzftu;
  
  private final boolean zzjnv = true;
  
  private final Bundle zzjnw;
  
  private zzcqc(Context paramContext, Looper paramLooper, boolean paramBoolean, zzq paramzzq, Bundle paramBundle, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener) {
    super(paramContext, paramLooper, 44, paramzzq, paramConnectionCallbacks, paramOnConnectionFailedListener);
    this.zzfkj = paramzzq;
    this.zzjnw = paramBundle;
    this.zzftu = paramzzq.zzajy();
  }
  
  public zzcqc(Context paramContext, Looper paramLooper, boolean paramBoolean, zzq paramzzq, zzcpt paramzzcpt, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener) {
    this(paramContext, paramLooper, true, paramzzq, zza(paramzzq), paramConnectionCallbacks, paramOnConnectionFailedListener);
  }
  
  public static Bundle zza(zzq paramzzq) {
    zzcpt zzcpt = paramzzq.zzajx();
    Integer integer = paramzzq.zzajy();
    Bundle bundle = new Bundle();
    bundle.putParcelable("com.google.android.gms.signin.internal.clientRequestedAccount", (Parcelable)paramzzq.getAccount());
    if (integer != null)
      bundle.putInt("com.google.android.gms.common.internal.ClientSettings.sessionId", integer.intValue()); 
    if (zzcpt != null) {
      bundle.putBoolean("com.google.android.gms.signin.internal.offlineAccessRequested", zzcpt.zzbbw());
      bundle.putBoolean("com.google.android.gms.signin.internal.idTokenRequested", zzcpt.isIdTokenRequested());
      bundle.putString("com.google.android.gms.signin.internal.serverClientId", zzcpt.getServerClientId());
      bundle.putBoolean("com.google.android.gms.signin.internal.usePromptModeForAuthCode", true);
      bundle.putBoolean("com.google.android.gms.signin.internal.forceCodeForRefreshToken", zzcpt.zzbbx());
      bundle.putString("com.google.android.gms.signin.internal.hostedDomain", zzcpt.zzbby());
      bundle.putBoolean("com.google.android.gms.signin.internal.waitForAccessTokenRefresh", zzcpt.zzbbz());
      if (zzcpt.zzbca() != null)
        bundle.putLong("com.google.android.gms.signin.internal.authApiSignInModuleVersion", zzcpt.zzbca().longValue()); 
      if (zzcpt.zzbcb() != null)
        bundle.putLong("com.google.android.gms.signin.internal.realClientLibraryVersion", zzcpt.zzbcb().longValue()); 
    } 
    return bundle;
  }
  
  public final void connect() {
    zza((zzj)new zzm((zzd)this));
  }
  
  public final void zza(zzam paramzzam, boolean paramBoolean) {
    try {
      ((zzcqa)zzajj()).zza(paramzzam, this.zzftu.intValue(), paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      Log.w("SignInClientImpl", "Remote service probably died when saveDefaultAccount is called");
      return;
    } 
  }
  
  public final void zza(zzcpy paramzzcpy) {
    zzbp.zzb(paramzzcpy, "Expecting a valid ISignInCallbacks");
    try {
      Account account = this.zzfkj.zzajp();
      GoogleSignInAccount googleSignInAccount = null;
      if ("<<default account>>".equals(account.name))
        googleSignInAccount = zzy.zzbl(getContext()).zzaas(); 
      zzbq zzbq = new zzbq();
      this(account, this.zzftu.intValue(), googleSignInAccount);
      zzcqa zzcqa = (zzcqa)zzajj();
      zzcqd zzcqd = new zzcqd();
      this(zzbq);
      zzcqa.zza(zzcqd, paramzzcpy);
      return;
    } catch (RemoteException remoteException) {
      Log.w("SignInClientImpl", "Remote service probably died when signIn is called");
      try {
        zzcqf zzcqf = new zzcqf();
        this(8);
        paramzzcpy.zzb(zzcqf);
        return;
      } catch (RemoteException remoteException1) {
        Log.wtf("SignInClientImpl", "ISignInCallbacks#onSignInComplete should be executed from the same process, unexpected RemoteException.", (Throwable)remoteException);
        return;
      } 
    } 
  }
  
  public final boolean zzaac() {
    return this.zzjnv;
  }
  
  public final void zzbbv() {
    try {
      ((zzcqa)zzajj()).zzec(this.zzftu.intValue());
      return;
    } catch (RemoteException remoteException) {
      Log.w("SignInClientImpl", "Remote service probably died when clearAccountFromSessionStore is called");
      return;
    } 
  }
  
  protected final String zzhc() {
    return "com.google.android.gms.signin.service.START";
  }
  
  protected final String zzhd() {
    return "com.google.android.gms.signin.internal.ISignInService";
  }
  
  protected final Bundle zzzu() {
    String str = this.zzfkj.zzaju();
    if (!getContext().getPackageName().equals(str))
      this.zzjnw.putString("com.google.android.gms.signin.internal.realClientPackageName", this.zzfkj.zzaju()); 
    return this.zzjnw;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzcqc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */