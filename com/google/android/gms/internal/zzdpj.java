package com.google.android.gms.internal;

import java.util.Iterator;

final class zzdpj implements Iterable<zzdpl> {
  private final int length;
  
  private long value;
  
  public zzdpj(int paramInt) {
    int i = paramInt + 1;
    paramInt = (int)Math.floor(Math.log(i) / Math.log(2.0D));
    this.length = paramInt;
    this.value = (long)Math.pow(2.0D, paramInt) - 1L & i;
  }
  
  public final Iterator<zzdpl> iterator() {
    return new zzdpk(this);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzdpj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */