package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.IInterface;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.zzc;
import com.google.android.gms.common.zze;
import java.util.Iterator;
import java.util.Set;

public abstract class zzaa<T extends IInterface> extends zzd<T> implements Api.zze, zzae {
  private final Account zzduz;
  
  private final Set<Scope> zzecm;
  
  private final zzq zzfkj;
  
  protected zzaa(Context paramContext, Looper paramLooper, int paramInt, zzq paramzzq, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener) {
    this(paramContext, paramLooper, zzaf.zzce(paramContext), GoogleApiAvailability.getInstance(), paramInt, paramzzq, zzbp.<GoogleApiClient.ConnectionCallbacks>zzu(paramConnectionCallbacks), zzbp.<GoogleApiClient.OnConnectionFailedListener>zzu(paramOnConnectionFailedListener));
  }
  
  private zzaa(Context paramContext, Looper paramLooper, zzaf paramzzaf, GoogleApiAvailability paramGoogleApiAvailability, int paramInt, zzq paramzzq, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener) {
    super(paramContext, paramLooper, paramzzaf, (zze)paramGoogleApiAvailability, paramInt, zzab, zzac, paramzzq.zzajv());
    zzab zzab;
    zzac zzac;
    this.zzfkj = paramzzq;
    this.zzduz = paramzzq.getAccount();
    Set<Scope> set2 = paramzzq.zzajs();
    Set<Scope> set1 = zzb(set2);
    Iterator<Scope> iterator = set1.iterator();
    while (iterator.hasNext()) {
      if (set2.contains(iterator.next()))
        continue; 
      throw new IllegalStateException("Expanding scopes is not permitted, use implied scopes instead");
    } 
    this.zzecm = set1;
  }
  
  public final Account getAccount() {
    return this.zzduz;
  }
  
  public zzc[] zzajh() {
    return new zzc[0];
  }
  
  protected final Set<Scope> zzajl() {
    return this.zzecm;
  }
  
  protected final zzq zzakd() {
    return this.zzfkj;
  }
  
  @NonNull
  protected Set<Scope> zzb(@NonNull Set<Scope> paramSet) {
    return paramSet;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/internal/zzaa.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */