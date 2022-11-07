package com.google.android.gms.internal;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public final class zzevu {
  public static final byte[] EMPTY_BYTE_ARRAY;
  
  private static Charset ISO_8859_1;
  
  static final Charset UTF_8 = Charset.forName("UTF-8");
  
  private static ByteBuffer zzope;
  
  private static zzeut zzopf;
  
  static {
    ISO_8859_1 = Charset.forName("ISO-8859-1");
    byte[] arrayOfByte = new byte[0];
    EMPTY_BYTE_ARRAY = arrayOfByte;
    zzope = ByteBuffer.wrap(arrayOfByte);
    zzopf = zzeut.zzbb(arrayOfByte);
  }
  
  public static int hashCode(byte[] paramArrayOfbyte) {
    int i = paramArrayOfbyte.length;
    int j = zza(i, paramArrayOfbyte, 0, i);
    i = j;
    if (j == 0)
      i = 1; 
    return i;
  }
  
  static int zza(int paramInt1, byte[] paramArrayOfbyte, int paramInt2, int paramInt3) {
    for (int i = paramInt2; i < paramInt2 + paramInt3; i++)
      paramInt1 = paramInt1 * 31 + paramArrayOfbyte[i]; 
    return paramInt1;
  }
  
  static <T> T zzb(T paramT, String paramString) {
    if (paramT != null)
      return paramT; 
    throw new NullPointerException(paramString);
  }
  
  public static int zzda(boolean paramBoolean) {
    return paramBoolean ? 1231 : 1237;
  }
  
  public static int zzdc(long paramLong) {
    return (int)(paramLong ^ paramLong >>> 32L);
  }
  
  static boolean zzg(zzewl paramzzewl) {
    return false;
  }
  
  static <T> T zzu(T paramT) {
    paramT.getClass();
    return paramT;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzevu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */