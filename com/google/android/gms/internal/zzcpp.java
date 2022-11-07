package com.google.android.gms.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;

public final class zzcpp {
  public static final Api<zzcpt> API;
  
  private static Api.zzf<zzcqc> zzdwp = new Api.zzf();
  
  public static final Api.zza<zzcqc, zzcpt> zzdwq;
  
  private static Scope zzecd;
  
  private static Scope zzece;
  
  private static Api<Object> zzgdn;
  
  private static Api.zzf<zzcqc> zzjnm = new Api.zzf();
  
  private static Api.zza<zzcqc, Object> zzjnn;
  
  static {
    zzcpq zzcpq = new zzcpq();
    zzdwq = zzcpq;
    zzjnn = new zzcpr();
    zzecd = new Scope("profile");
    zzece = new Scope("email");
    API = new Api("SignIn.API", zzcpq, zzdwp);
    zzgdn = new Api("SignIn.INTERNAL_API", zzjnn, zzjnm);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzcpp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */