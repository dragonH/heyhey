package com.google.android.gms.common;

import java.util.Arrays;

final class zzh extends zzg {
  private final byte[] zzffp;
  
  zzh(byte[] paramArrayOfbyte) {
    super(Arrays.copyOfRange(paramArrayOfbyte, 0, 25));
    this.zzffp = paramArrayOfbyte;
  }
  
  final byte[] getBytes() {
    return this.zzffp;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/zzh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */