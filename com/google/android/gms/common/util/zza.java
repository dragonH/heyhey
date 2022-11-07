package com.google.android.gms.common.util;

import java.util.ArrayList;
import java.util.Arrays;

public final class zza {
  public static <T> ArrayList<T> zza(T[] paramArrayOfT) {
    int i = paramArrayOfT.length;
    ArrayList<T> arrayList = new ArrayList(i);
    for (byte b = 0; b < i; b++)
      arrayList.add(paramArrayOfT[b]); 
    return arrayList;
  }
  
  public static void zza(StringBuilder paramStringBuilder, double[] paramArrayOfdouble) {
    int i = paramArrayOfdouble.length;
    for (byte b = 0; b < i; b++) {
      if (b != 0)
        paramStringBuilder.append(","); 
      paramStringBuilder.append(Double.toString(paramArrayOfdouble[b]));
    } 
  }
  
  public static void zza(StringBuilder paramStringBuilder, float[] paramArrayOffloat) {
    int i = paramArrayOffloat.length;
    for (byte b = 0; b < i; b++) {
      if (b != 0)
        paramStringBuilder.append(","); 
      paramStringBuilder.append(Float.toString(paramArrayOffloat[b]));
    } 
  }
  
  public static void zza(StringBuilder paramStringBuilder, long[] paramArrayOflong) {
    int i = paramArrayOflong.length;
    for (byte b = 0; b < i; b++) {
      if (b != 0)
        paramStringBuilder.append(","); 
      paramStringBuilder.append(Long.toString(paramArrayOflong[b]));
    } 
  }
  
  public static <T> void zza(StringBuilder paramStringBuilder, T[] paramArrayOfT) {
    int i = paramArrayOfT.length;
    for (byte b = 0; b < i; b++) {
      if (b != 0)
        paramStringBuilder.append(","); 
      paramStringBuilder.append(paramArrayOfT[b].toString());
    } 
  }
  
  public static void zza(StringBuilder paramStringBuilder, String[] paramArrayOfString) {
    int i = paramArrayOfString.length;
    for (byte b = 0; b < i; b++) {
      if (b != 0)
        paramStringBuilder.append(","); 
      paramStringBuilder.append("\"");
      paramStringBuilder.append(paramArrayOfString[b]);
      paramStringBuilder.append("\"");
    } 
  }
  
  public static void zza(StringBuilder paramStringBuilder, boolean[] paramArrayOfboolean) {
    int i = paramArrayOfboolean.length;
    for (byte b = 0; b < i; b++) {
      if (b != 0)
        paramStringBuilder.append(","); 
      paramStringBuilder.append(Boolean.toString(paramArrayOfboolean[b]));
    } 
  }
  
  public static byte[] zza(byte[]... paramVarArgs) {
    if (paramVarArgs.length == 0)
      return new byte[0]; 
    byte b = 0;
    int i = 0;
    while (b < paramVarArgs.length) {
      i += (paramVarArgs[b]).length;
      b++;
    } 
    byte[] arrayOfByte = Arrays.copyOf(paramVarArgs[0], i);
    i = (paramVarArgs[0]).length;
    for (b = 1; b < paramVarArgs.length; b++) {
      byte[] arrayOfByte1 = paramVarArgs[b];
      System.arraycopy(arrayOfByte1, 0, arrayOfByte, i, arrayOfByte1.length);
      i += arrayOfByte1.length;
    } 
    return arrayOfByte;
  }
  
  public static Integer[] zzc(int[] paramArrayOfint) {
    if (paramArrayOfint == null)
      return null; 
    int i = paramArrayOfint.length;
    Integer[] arrayOfInteger = new Integer[i];
    for (byte b = 0; b < i; b++)
      arrayOfInteger[b] = Integer.valueOf(paramArrayOfint[b]); 
    return arrayOfInteger;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/util/zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */