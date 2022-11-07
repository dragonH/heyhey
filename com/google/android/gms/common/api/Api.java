package com.google.android.gms.common.api;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.Nullable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.internal.zzam;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.internal.zzj;
import com.google.android.gms.common.internal.zzq;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public final class Api<O extends Api.ApiOptions> {
  private final String mName;
  
  private final zza<?, O> zzfgf;
  
  private final zzh<?, O> zzfgg;
  
  private final zzf<?> zzfgh;
  
  private final zzi<?> zzfgi;
  
  public <C extends zze> Api(String paramString, zza<C, O> paramzza, zzf<C> paramzzf) {
    zzbp.zzb(paramzza, "Cannot construct an Api with a null ClientBuilder");
    zzbp.zzb(paramzzf, "Cannot construct an Api with a null ClientKey");
    this.mName = paramString;
    this.zzfgf = paramzza;
    this.zzfgg = null;
    this.zzfgh = paramzzf;
    this.zzfgi = null;
  }
  
  public final String getName() {
    return this.mName;
  }
  
  public final zzd<?, O> zzafc() {
    return this.zzfgf;
  }
  
  public final zza<?, O> zzafd() {
    boolean bool;
    if (this.zzfgf != null) {
      bool = true;
    } else {
      bool = false;
    } 
    zzbp.zza(bool, "This API was constructed with a SimpleClientBuilder. Use getSimpleClientBuilder");
    return this.zzfgf;
  }
  
  public final zzc<?> zzafe() {
    zzf<?> zzf1 = this.zzfgh;
    if (zzf1 != null)
      return zzf1; 
    throw new IllegalStateException("This API was constructed with null client keys. This should not be possible.");
  }
  
  public static interface ApiOptions {
    public static interface HasAccountOptions extends HasOptions, NotRequiredOptions {
      Account getAccount();
    }
    
    public static interface HasGoogleSignInAccountOptions extends HasOptions {
      GoogleSignInAccount getGoogleSignInAccount();
    }
    
    public static interface HasOptions extends ApiOptions {}
    
    public static final class NoOptions implements NotRequiredOptions {}
    
    public static interface NotRequiredOptions extends ApiOptions {}
    
    public static interface Optional extends HasOptions, NotRequiredOptions {}
  }
  
  public static interface HasAccountOptions extends ApiOptions.HasOptions, ApiOptions.NotRequiredOptions {
    Account getAccount();
  }
  
  public static interface HasGoogleSignInAccountOptions extends ApiOptions.HasOptions {
    GoogleSignInAccount getGoogleSignInAccount();
  }
  
  public static interface HasOptions extends ApiOptions {}
  
  public static final class NoOptions implements ApiOptions.NotRequiredOptions {}
  
  public static interface NotRequiredOptions extends ApiOptions {}
  
  public static interface Optional extends ApiOptions.HasOptions, ApiOptions.NotRequiredOptions {}
  
  public static abstract class zza<T extends zze, O> extends zzd<T, O> {
    public abstract T zza(Context param1Context, Looper param1Looper, zzq param1zzq, O param1O, GoogleApiClient.ConnectionCallbacks param1ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener param1OnConnectionFailedListener);
  }
  
  public static interface zzb {}
  
  public static class zzc<C extends zzb> {}
  
  public static class zzd<T extends zzb, O> {
    public int getPriority() {
      return Integer.MAX_VALUE;
    }
    
    public List<Scope> zzn(O param1O) {
      return Collections.emptyList();
    }
  }
  
  public static interface zze extends zzb {
    void disconnect();
    
    void dump(String param1String, FileDescriptor param1FileDescriptor, PrintWriter param1PrintWriter, String[] param1ArrayOfString);
    
    boolean isConnected();
    
    boolean isConnecting();
    
    void zza(zzam param1zzam, Set<Scope> param1Set);
    
    void zza(zzj param1zzj);
    
    boolean zzaac();
    
    boolean zzaal();
    
    Intent zzaam();
    
    boolean zzaff();
    
    @Nullable
    IBinder zzafg();
  }
  
  public static final class zzf<C extends zze> extends zzc<C> {}
  
  public static interface zzg<T extends android.os.IInterface> extends zzb {}
  
  public static class zzh<T extends zzg, O> extends zzd<T, O> {}
  
  public static final class zzi<C extends zzg> extends zzc<C> {}
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/Api.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */