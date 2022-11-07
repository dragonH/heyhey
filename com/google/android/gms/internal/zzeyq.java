package com.google.android.gms.internal;

import java.io.IOException;

public final class zzeyq {
  public static final String[] EMPTY_STRING_ARRAY;
  
  private static int zzotu = 11;
  
  private static int zzotv = 12;
  
  private static int zzotw = 16;
  
  private static int zzotx = 26;
  
  public static final int[] zzoty = new int[0];
  
  public static final long[] zzotz = new long[0];
  
  public static final float[] zzoua = new float[0];
  
  private static double[] zzoub = new double[0];
  
  public static final boolean[] zzouc = new boolean[0];
  
  public static final byte[][] zzoud;
  
  public static final byte[] zzoue;
  
  static {
    EMPTY_STRING_ARRAY = new String[0];
    zzoud = new byte[0][];
    zzoue = new byte[0];
  }
  
  public static final int zzb(zzeye paramzzeye, int paramInt) throws IOException {
    int i = paramzzeye.getPosition();
    paramzzeye.zzjl(paramInt);
    byte b;
    for (b = 1; paramzzeye.zzcsn() == paramInt; b++)
      paramzzeye.zzjl(paramInt); 
    paramzzeye.zzaj(i, paramInt);
    return b;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzeyq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */