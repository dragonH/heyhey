package com.google.android.gms.internal;

import java.util.Map;

public final class zzn {
  public final byte[] data;
  
  private int statusCode;
  
  private long zzaa;
  
  public final Map<String, String> zzy;
  
  public final boolean zzz;
  
  public zzn(int paramInt, byte[] paramArrayOfbyte, Map<String, String> paramMap, boolean paramBoolean, long paramLong) {
    this.statusCode = paramInt;
    this.data = paramArrayOfbyte;
    this.zzy = paramMap;
    this.zzz = paramBoolean;
    this.zzaa = paramLong;
  }
  
  public zzn(byte[] paramArrayOfbyte, Map<String, String> paramMap) {
    this(200, paramArrayOfbyte, paramMap, false, 0L);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */