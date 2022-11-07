package com.google.android.gms.internal;

import java.nio.charset.Charset;
import java.util.Arrays;

public final class zzeyl {
  private static Charset ISO_8859_1;
  
  protected static final Charset UTF_8 = Charset.forName("UTF-8");
  
  public static final Object zzott;
  
  static {
    ISO_8859_1 = Charset.forName("ISO-8859-1");
    zzott = new Object();
  }
  
  public static boolean equals(float[] paramArrayOffloat1, float[] paramArrayOffloat2) {
    return (paramArrayOffloat1 == null || paramArrayOffloat1.length == 0) ? ((paramArrayOffloat2 == null || paramArrayOffloat2.length == 0)) : Arrays.equals(paramArrayOffloat1, paramArrayOffloat2);
  }
  
  public static boolean equals(int[] paramArrayOfint1, int[] paramArrayOfint2) {
    return (paramArrayOfint1 == null || paramArrayOfint1.length == 0) ? ((paramArrayOfint2 == null || paramArrayOfint2.length == 0)) : Arrays.equals(paramArrayOfint1, paramArrayOfint2);
  }
  
  public static boolean equals(long[] paramArrayOflong1, long[] paramArrayOflong2) {
    return (paramArrayOflong1 == null || paramArrayOflong1.length == 0) ? ((paramArrayOflong2 == null || paramArrayOflong2.length == 0)) : Arrays.equals(paramArrayOflong1, paramArrayOflong2);
  }
  
  public static boolean equals(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2) {
    int i;
    int j;
    if (paramArrayOfObject1 == null) {
      i = 0;
    } else {
      i = paramArrayOfObject1.length;
    } 
    if (paramArrayOfObject2 == null) {
      j = 0;
    } else {
      j = paramArrayOfObject2.length;
    } 
    byte b = 0;
    int k;
    for (k = 0;; k = m + 1) {
      byte b1;
      int m = k;
      if (b < i) {
        m = k;
        if (paramArrayOfObject1[b] == null) {
          b++;
          continue;
        } 
      } 
      while (m < j && paramArrayOfObject2[m] == null)
        m++; 
      if (b >= i) {
        k = 1;
      } else {
        k = 0;
      } 
      if (m >= j) {
        b1 = 1;
      } else {
        b1 = 0;
      } 
      if (k != 0 && b1)
        return true; 
      if (k != b1)
        return false; 
      if (!paramArrayOfObject1[b].equals(paramArrayOfObject2[m]))
        return false; 
      b++;
    } 
  }
  
  public static boolean equals(boolean[] paramArrayOfboolean1, boolean[] paramArrayOfboolean2) {
    return (paramArrayOfboolean1 == null || paramArrayOfboolean1.length == 0) ? ((paramArrayOfboolean2 == null || paramArrayOfboolean2.length == 0)) : Arrays.equals(paramArrayOfboolean1, paramArrayOfboolean2);
  }
  
  public static int hashCode(float[] paramArrayOffloat) {
    return (paramArrayOffloat == null || paramArrayOffloat.length == 0) ? 0 : Arrays.hashCode(paramArrayOffloat);
  }
  
  public static int hashCode(int[] paramArrayOfint) {
    return (paramArrayOfint == null || paramArrayOfint.length == 0) ? 0 : Arrays.hashCode(paramArrayOfint);
  }
  
  public static int hashCode(long[] paramArrayOflong) {
    return (paramArrayOflong == null || paramArrayOflong.length == 0) ? 0 : Arrays.hashCode(paramArrayOflong);
  }
  
  public static int hashCode(Object[] paramArrayOfObject) {
    int i;
    byte b = 0;
    if (paramArrayOfObject == null) {
      i = 0;
    } else {
      i = paramArrayOfObject.length;
    } 
    int j;
    for (j = 0; b < i; j = k) {
      Object object = paramArrayOfObject[b];
      int k = j;
      if (object != null)
        k = j * 31 + object.hashCode(); 
      b++;
    } 
    return j;
  }
  
  public static int hashCode(boolean[] paramArrayOfboolean) {
    return (paramArrayOfboolean == null || paramArrayOfboolean.length == 0) ? 0 : Arrays.hashCode(paramArrayOfboolean);
  }
  
  public static void zza(zzeyh paramzzeyh1, zzeyh paramzzeyh2) {
    zzeyj zzeyj = paramzzeyh1.zzotl;
    if (zzeyj != null)
      paramzzeyh2.zzotl = (zzeyj)zzeyj.clone(); 
  }
  
  public static boolean zza(byte[][] paramArrayOfbyte1, byte[][] paramArrayOfbyte2) {
    int i;
    int j;
    if (paramArrayOfbyte1 == null) {
      i = 0;
    } else {
      i = paramArrayOfbyte1.length;
    } 
    if (paramArrayOfbyte2 == null) {
      j = 0;
    } else {
      j = paramArrayOfbyte2.length;
    } 
    byte b = 0;
    int k;
    for (k = 0;; k = m + 1) {
      byte b1;
      int m = k;
      if (b < i) {
        m = k;
        if (paramArrayOfbyte1[b] == null) {
          b++;
          continue;
        } 
      } 
      while (m < j && paramArrayOfbyte2[m] == null)
        m++; 
      if (b >= i) {
        k = 1;
      } else {
        k = 0;
      } 
      if (m >= j) {
        b1 = 1;
      } else {
        b1 = 0;
      } 
      if (k != 0 && b1)
        return true; 
      if (k != b1)
        return false; 
      if (!Arrays.equals(paramArrayOfbyte1[b], paramArrayOfbyte2[m]))
        return false; 
      b++;
    } 
  }
  
  public static int zzd(byte[][] paramArrayOfbyte) {
    int i;
    byte b = 0;
    if (paramArrayOfbyte == null) {
      i = 0;
    } else {
      i = paramArrayOfbyte.length;
    } 
    int j;
    for (j = 0; b < i; j = k) {
      byte[] arrayOfByte = paramArrayOfbyte[b];
      int k = j;
      if (arrayOfByte != null)
        k = j * 31 + Arrays.hashCode(arrayOfByte); 
      b++;
    } 
    return j;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzeyl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */