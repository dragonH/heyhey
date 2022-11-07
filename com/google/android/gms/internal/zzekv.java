package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzbf;
import java.util.Arrays;

public final class zzekv {
  private String zzdxt;
  
  public zzekv(@Nullable String paramString) {
    this.zzdxt = paramString;
  }
  
  public final boolean equals(Object paramObject) {
    if (!(paramObject instanceof zzekv))
      return false; 
    paramObject = paramObject;
    return zzbf.equal(this.zzdxt, ((zzekv)paramObject).zzdxt);
  }
  
  @Nullable
  public final String getToken() {
    return this.zzdxt;
  }
  
  public final int hashCode() {
    return Arrays.hashCode(new Object[] { this.zzdxt });
  }
  
  public final String toString() {
    return zzbf.zzt(this).zzg("token", this.zzdxt).toString();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzekv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */