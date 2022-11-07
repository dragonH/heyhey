package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.support.v4.util.ArraySet;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.zzcpt;
import java.util.Collection;
import java.util.Set;

public final class zzr {
  private Account zzduz;
  
  private String zzdxc;
  
  private int zzfhd = 0;
  
  private String zzfhf;
  
  private zzcpt zzftt = zzcpt.zzjno;
  
  private ArraySet<Scope> zzftv;
  
  public final zzq zzajz() {
    return new zzq(this.zzduz, (Set<Scope>)this.zzftv, null, 0, null, this.zzdxc, this.zzfhf, this.zzftt);
  }
  
  public final zzr zze(Account paramAccount) {
    this.zzduz = paramAccount;
    return this;
  }
  
  public final zzr zze(Collection<Scope> paramCollection) {
    if (this.zzftv == null)
      this.zzftv = new ArraySet(); 
    this.zzftv.addAll(paramCollection);
    return this;
  }
  
  public final zzr zzfz(String paramString) {
    this.zzdxc = paramString;
    return this;
  }
  
  public final zzr zzga(String paramString) {
    this.zzfhf = paramString;
    return this;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/internal/zzr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */