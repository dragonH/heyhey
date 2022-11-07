package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.zzbf;
import java.util.Arrays;

public final class zzh<O extends Api.ApiOptions> {
  private final Api<O> zzfdg;
  
  private final O zzfgr;
  
  private final boolean zzfii = true;
  
  private final int zzfij;
  
  private zzh(Api<O> paramApi) {
    this.zzfdg = paramApi;
    this.zzfgr = null;
    this.zzfij = System.identityHashCode(this);
  }
  
  private zzh(Api<O> paramApi, O paramO) {
    this.zzfdg = paramApi;
    this.zzfgr = paramO;
    this.zzfij = Arrays.hashCode(new Object[] { paramApi, paramO });
  }
  
  public static <O extends Api.ApiOptions> zzh<O> zza(Api<O> paramApi, O paramO) {
    return new zzh<O>(paramApi, paramO);
  }
  
  public static <O extends Api.ApiOptions> zzh<O> zzb(Api<O> paramApi) {
    return new zzh<O>(paramApi);
  }
  
  public final boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof zzh))
      return false; 
    paramObject = paramObject;
    return (!this.zzfii && !((zzh)paramObject).zzfii && zzbf.equal(this.zzfdg, ((zzh)paramObject).zzfdg) && zzbf.equal(this.zzfgr, ((zzh)paramObject).zzfgr));
  }
  
  public final int hashCode() {
    return this.zzfij;
  }
  
  public final String zzafv() {
    return this.zzfdg.getName();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */