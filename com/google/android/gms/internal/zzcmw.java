package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class zzcmw extends zzbck {
  public static final Parcelable.Creator<zzcmw> CREATOR = new zzcnc();
  
  private static byte[][] zzfdr;
  
  private static zzcmw zzjij;
  
  private static final zzcnb zzjis = (zzcnb)new zzcmx();
  
  private static final zzcnb zzjit = (zzcnb)new zzcmy();
  
  private static final zzcnb zzjiu = (zzcnb)new zzcmz();
  
  private static final zzcnb zzjiv = (zzcnb)new zzcna();
  
  private String zzjik;
  
  private byte[] zzjil;
  
  private byte[][] zzjim;
  
  private byte[][] zzjin;
  
  private byte[][] zzjio;
  
  private byte[][] zzjip;
  
  private int[] zzjiq;
  
  private byte[][] zzjir;
  
  public zzcmw(String paramString, byte[] paramArrayOfbyte, byte[][] paramArrayOfbyte1, byte[][] paramArrayOfbyte2, byte[][] paramArrayOfbyte3, byte[][] paramArrayOfbyte4, int[] paramArrayOfint, byte[][] paramArrayOfbyte5) {
    this.zzjik = paramString;
    this.zzjil = paramArrayOfbyte;
    this.zzjim = paramArrayOfbyte1;
    this.zzjin = paramArrayOfbyte2;
    this.zzjio = paramArrayOfbyte3;
    this.zzjip = paramArrayOfbyte4;
    this.zzjiq = paramArrayOfint;
    this.zzjir = paramArrayOfbyte5;
  }
  
  private static void zza(StringBuilder paramStringBuilder, String paramString, int[] paramArrayOfint) {
    paramStringBuilder.append(paramString);
    paramStringBuilder.append("=");
    if (paramArrayOfint == null) {
      paramString = "null";
    } else {
      paramStringBuilder.append("(");
      int i = paramArrayOfint.length;
      boolean bool = true;
      byte b = 0;
      while (b < i) {
        int j = paramArrayOfint[b];
        if (!bool)
          paramStringBuilder.append(", "); 
        paramStringBuilder.append(j);
        b++;
        bool = false;
      } 
      paramString = ")";
    } 
    paramStringBuilder.append(paramString);
  }
  
  private static void zza(StringBuilder paramStringBuilder, String paramString, byte[][] paramArrayOfbyte) {
    paramStringBuilder.append(paramString);
    paramStringBuilder.append("=");
    if (paramArrayOfbyte == null) {
      paramString = "null";
    } else {
      paramStringBuilder.append("(");
      int i = paramArrayOfbyte.length;
      boolean bool = true;
      byte b = 0;
      while (b < i) {
        byte[] arrayOfByte = paramArrayOfbyte[b];
        if (!bool)
          paramStringBuilder.append(", "); 
        paramStringBuilder.append("'");
        paramStringBuilder.append(Base64.encodeToString(arrayOfByte, 3));
        paramStringBuilder.append("'");
        b++;
        bool = false;
      } 
      paramString = ")";
    } 
    paramStringBuilder.append(paramString);
  }
  
  private static List<String> zzb(byte[][] paramArrayOfbyte) {
    if (paramArrayOfbyte == null)
      return Collections.emptyList(); 
    ArrayList<String> arrayList = new ArrayList(paramArrayOfbyte.length);
    int i = paramArrayOfbyte.length;
    for (byte b = 0; b < i; b++)
      arrayList.add(Base64.encodeToString(paramArrayOfbyte[b], 3)); 
    Collections.sort(arrayList);
    return arrayList;
  }
  
  private static List<Integer> zze(int[] paramArrayOfint) {
    if (paramArrayOfint == null)
      return Collections.emptyList(); 
    ArrayList<Integer> arrayList = new ArrayList(paramArrayOfint.length);
    int i = paramArrayOfint.length;
    for (byte b = 0; b < i; b++)
      arrayList.add(Integer.valueOf(paramArrayOfint[b])); 
    Collections.sort(arrayList);
    return arrayList;
  }
  
  public final boolean equals(Object paramObject) {
    if (paramObject instanceof zzcmw) {
      paramObject = paramObject;
      if (zzcnd.equals(this.zzjik, ((zzcmw)paramObject).zzjik) && Arrays.equals(this.zzjil, ((zzcmw)paramObject).zzjil) && zzcnd.equals(zzb(this.zzjim), zzb(((zzcmw)paramObject).zzjim)) && zzcnd.equals(zzb(this.zzjin), zzb(((zzcmw)paramObject).zzjin)) && zzcnd.equals(zzb(this.zzjio), zzb(((zzcmw)paramObject).zzjio)) && zzcnd.equals(zzb(this.zzjip), zzb(((zzcmw)paramObject).zzjip)) && zzcnd.equals(zze(this.zzjiq), zze(((zzcmw)paramObject).zzjiq)) && zzcnd.equals(zzb(this.zzjir), zzb(((zzcmw)paramObject).zzjir)))
        return true; 
    } 
    return false;
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder("ExperimentTokens");
    stringBuilder.append("(");
    String str = this.zzjik;
    if (str == null) {
      str = "null";
    } else {
      StringBuilder stringBuilder1 = new StringBuilder("'".length() + String.valueOf(str).length() + "'".length());
      stringBuilder1.append("'");
      stringBuilder1.append(str);
      stringBuilder1.append("'");
      str = stringBuilder1.toString();
    } 
    stringBuilder.append(str);
    stringBuilder.append(", ");
    byte[] arrayOfByte = this.zzjil;
    stringBuilder.append("direct");
    stringBuilder.append("=");
    if (arrayOfByte == null) {
      stringBuilder.append("null");
    } else {
      stringBuilder.append("'");
      stringBuilder.append(Base64.encodeToString(arrayOfByte, 3));
      stringBuilder.append("'");
    } 
    stringBuilder.append(", ");
    zza(stringBuilder, "GAIA", this.zzjim);
    stringBuilder.append(", ");
    zza(stringBuilder, "PSEUDO", this.zzjin);
    stringBuilder.append(", ");
    zza(stringBuilder, "ALWAYS", this.zzjio);
    stringBuilder.append(", ");
    zza(stringBuilder, "OTHER", this.zzjip);
    stringBuilder.append(", ");
    zza(stringBuilder, "weak", this.zzjiq);
    stringBuilder.append(", ");
    zza(stringBuilder, "directs", this.zzjir);
    stringBuilder.append(")");
    return stringBuilder.toString();
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = zzbcn.zze(paramParcel);
    zzbcn.zza(paramParcel, 2, this.zzjik, false);
    zzbcn.zza(paramParcel, 3, this.zzjil, false);
    zzbcn.zza(paramParcel, 4, this.zzjim, false);
    zzbcn.zza(paramParcel, 5, this.zzjin, false);
    zzbcn.zza(paramParcel, 6, this.zzjio, false);
    zzbcn.zza(paramParcel, 7, this.zzjip, false);
    zzbcn.zza(paramParcel, 8, this.zzjiq, false);
    zzbcn.zza(paramParcel, 9, this.zzjir, false);
    zzbcn.zzai(paramParcel, paramInt);
  }
  
  static {
    byte[][] arrayOfByte = new byte[0][];
    zzfdr = arrayOfByte;
    zzjij = new zzcmw("", null, arrayOfByte, arrayOfByte, arrayOfByte, arrayOfByte, null, null);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzcmw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */