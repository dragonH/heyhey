package com.google.android.gms.internal;

import java.util.Iterator;

final class zzdpk implements Iterator<zzdpl> {
  private int zzlta;
  
  zzdpk(zzdpj paramzzdpj) {
    this.zzlta = zzdpj.zza(paramzzdpj) - 1;
  }
  
  public final boolean hasNext() {
    return (this.zzlta >= 0);
  }
  
  public final void remove() {}
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzdpk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */