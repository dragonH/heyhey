package com.google.android.gms.internal;

import java.io.IOException;
import java.io.InputStream;

public abstract class zzeut {
  private static volatile boolean zzonh = true;
  
  int zzond;
  
  int zzone = 100;
  
  int zzonf = Integer.MAX_VALUE;
  
  private boolean zzong = false;
  
  private zzeut() {}
  
  static zzeut zzb(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, boolean paramBoolean) {
    zzeuv zzeuv = new zzeuv(paramArrayOfbyte, paramInt1, paramInt2, paramBoolean, null);
    try {
      zzeuv.zzjn(paramInt2);
      return zzeuv;
    } catch (zzevz zzevz) {
      throw new IllegalArgumentException(zzevz);
    } 
  }
  
  public static zzeut zzbb(byte[] paramArrayOfbyte) {
    return zzb(paramArrayOfbyte, 0, paramArrayOfbyte.length, false);
  }
  
  public static long zzcq(long paramLong) {
    return -(paramLong & 0x1L) ^ paramLong >>> 1L;
  }
  
  public static zzeut zzf(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    return zzb(paramArrayOfbyte, paramInt1, paramInt2, false);
  }
  
  public static int zzjq(int paramInt) {
    return -(paramInt & 0x1) ^ paramInt >>> 1;
  }
  
  public static zzeut zzl(InputStream paramInputStream) {
    byte[] arrayOfByte;
    if (paramInputStream == null) {
      arrayOfByte = zzevu.EMPTY_BYTE_ARRAY;
      return zzb(arrayOfByte, 0, arrayOfByte.length, false);
    } 
    return new zzeuw((InputStream)arrayOfByte, 4096, null);
  }
  
  public abstract double readDouble() throws IOException;
  
  public abstract float readFloat() throws IOException;
  
  public abstract String readString() throws IOException;
  
  public abstract <T extends zzevh<T, ?>> T zza(T paramT, zzevd paramzzevd) throws IOException;
  
  public abstract void zza(zzewm paramzzewm, zzevd paramzzevd) throws IOException;
  
  public abstract int zzcsn() throws IOException;
  
  public abstract long zzcso() throws IOException;
  
  public abstract long zzcsp() throws IOException;
  
  public abstract int zzcsq() throws IOException;
  
  public abstract long zzcsr() throws IOException;
  
  public abstract int zzcss() throws IOException;
  
  public abstract boolean zzcst() throws IOException;
  
  public abstract String zzcsu() throws IOException;
  
  public abstract zzeuk zzcsv() throws IOException;
  
  public abstract int zzcsw() throws IOException;
  
  public abstract int zzcsx() throws IOException;
  
  public abstract int zzcsy() throws IOException;
  
  public abstract long zzcsz() throws IOException;
  
  public abstract int zzcta() throws IOException;
  
  public abstract long zzctb() throws IOException;
  
  public abstract int zzctc() throws IOException;
  
  abstract long zzctd() throws IOException;
  
  public abstract int zzcte();
  
  public abstract boolean zzctf() throws IOException;
  
  public abstract int zzctg();
  
  public abstract void zzjk(int paramInt) throws zzevz;
  
  public abstract boolean zzjl(int paramInt) throws IOException;
  
  public final int zzjm(int paramInt) {
    paramInt = this.zzonf;
    this.zzonf = Integer.MAX_VALUE;
    return paramInt;
  }
  
  public abstract int zzjn(int paramInt) throws zzevz;
  
  public abstract void zzjo(int paramInt);
  
  public abstract void zzjp(int paramInt) throws IOException;
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzeut.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */