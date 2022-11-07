package com.google.android.gms.internal;

import java.util.Arrays;

final class zzeyp {
  final byte[] bytes;
  
  final int tag;
  
  zzeyp(int paramInt, byte[] paramArrayOfbyte) {
    this.tag = paramInt;
    this.bytes = paramArrayOfbyte;
  }
  
  public final boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof zzeyp))
      return false; 
    paramObject = paramObject;
    return (this.tag == ((zzeyp)paramObject).tag && Arrays.equals(this.bytes, ((zzeyp)paramObject).bytes));
  }
  
  public final int hashCode() {
    return (this.tag + 527) * 31 + Arrays.hashCode(this.bytes);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzeyp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */