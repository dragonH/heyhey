package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Intent;
import java.util.Arrays;

public final class zzag {
  private final String zzdmr = null;
  
  private final String zzfuv = null;
  
  private final ComponentName zzfuw;
  
  private final int zzfux;
  
  public zzag(ComponentName paramComponentName, int paramInt) {
    this.zzfuw = zzbp.<ComponentName>zzu(paramComponentName);
    this.zzfux = 129;
  }
  
  public zzag(String paramString1, String paramString2, int paramInt) {
    this.zzfuw = null;
    this.zzfux = paramInt;
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof zzag))
      return false; 
    paramObject = paramObject;
    return (zzbf.equal(this.zzdmr, ((zzag)paramObject).zzdmr) && zzbf.equal(this.zzfuv, ((zzag)paramObject).zzfuv) && zzbf.equal(this.zzfuw, ((zzag)paramObject).zzfuw) && this.zzfux == ((zzag)paramObject).zzfux);
  }
  
  public final ComponentName getComponentName() {
    return this.zzfuw;
  }
  
  public final String getPackage() {
    return this.zzfuv;
  }
  
  public final int hashCode() {
    return Arrays.hashCode(new Object[] { this.zzdmr, this.zzfuv, this.zzfuw, Integer.valueOf(this.zzfux) });
  }
  
  public final String toString() {
    String str1 = this.zzdmr;
    String str2 = str1;
    if (str1 == null)
      str2 = this.zzfuw.flattenToString(); 
    return str2;
  }
  
  public final int zzakg() {
    return this.zzfux;
  }
  
  public final Intent zzakh() {
    Intent intent;
    if (this.zzdmr != null) {
      intent = (new Intent(this.zzdmr)).setPackage(this.zzfuv);
    } else {
      intent = (new Intent()).setComponent(this.zzfuw);
    } 
    return intent;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/internal/zzag.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */