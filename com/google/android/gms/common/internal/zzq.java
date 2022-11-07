package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.view.View;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.zzcpt;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public final class zzq {
  private final Account zzduz;
  
  private final String zzdxc;
  
  private final Set<Scope> zzfhb;
  
  private final int zzfhd;
  
  private final View zzfhe;
  
  private final String zzfhf;
  
  private final Set<Scope> zzftr;
  
  private final Map<Api<?>, zzs> zzfts;
  
  private final zzcpt zzftt;
  
  private Integer zzftu;
  
  public zzq(Account paramAccount, Set<Scope> paramSet, Map<Api<?>, zzs> paramMap, int paramInt, View paramView, String paramString1, String paramString2, zzcpt paramzzcpt) {
    this.zzduz = paramAccount;
    if (paramSet == null) {
      set = Collections.EMPTY_SET;
    } else {
      set = Collections.unmodifiableSet(paramSet);
    } 
    this.zzfhb = set;
    Map<Api<?>, zzs> map = paramMap;
    if (paramMap == null)
      map = Collections.EMPTY_MAP; 
    this.zzfts = map;
    this.zzfhe = paramView;
    this.zzfhd = paramInt;
    this.zzdxc = paramString1;
    this.zzfhf = paramString2;
    this.zzftt = paramzzcpt;
    Set<Scope> set = new HashSet<Scope>(set);
    Iterator iterator = map.values().iterator();
    while (iterator.hasNext())
      set.addAll(((zzs)iterator.next()).zzecm); 
    this.zzftr = Collections.unmodifiableSet(set);
  }
  
  public static zzq zzcc(Context paramContext) {
    return (new GoogleApiClient.Builder(paramContext)).zzafr();
  }
  
  public final Account getAccount() {
    return this.zzduz;
  }
  
  @Deprecated
  public final String getAccountName() {
    Account account = this.zzduz;
    return (account != null) ? account.name : null;
  }
  
  public final Account zzajp() {
    Account account = this.zzduz;
    return (account != null) ? account : new Account("<<default account>>", "com.google");
  }
  
  public final int zzajq() {
    return this.zzfhd;
  }
  
  public final Set<Scope> zzajr() {
    return this.zzfhb;
  }
  
  public final Set<Scope> zzajs() {
    return this.zzftr;
  }
  
  public final Map<Api<?>, zzs> zzajt() {
    return this.zzfts;
  }
  
  public final String zzaju() {
    return this.zzdxc;
  }
  
  public final String zzajv() {
    return this.zzfhf;
  }
  
  public final View zzajw() {
    return this.zzfhe;
  }
  
  public final zzcpt zzajx() {
    return this.zzftt;
  }
  
  public final Integer zzajy() {
    return this.zzftu;
  }
  
  public final Set<Scope> zzc(Api<?> paramApi) {
    zzs zzs = this.zzfts.get(paramApi);
    if (zzs == null || zzs.zzecm.isEmpty())
      return this.zzfhb; 
    HashSet<Scope> hashSet = new HashSet<Scope>(this.zzfhb);
    hashSet.addAll(zzs.zzecm);
    return hashSet;
  }
  
  public final void zzc(Integer paramInteger) {
    this.zzftu = paramInteger;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/internal/zzq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */