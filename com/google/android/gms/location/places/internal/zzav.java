package com.google.android.gms.location.places.internal;

import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzc;
import com.google.android.gms.internal.zzbco;
import com.google.android.gms.internal.zzbcp;
import com.google.android.gms.internal.zzdfn;
import com.google.android.gms.internal.zzeym;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class zzav extends zzc {
  public zzav(DataHolder paramDataHolder, int paramInt) {
    super(paramDataHolder, paramInt);
  }
  
  private final byte[] zzb(String paramString, byte[] paramArrayOfbyte) {
    return (zzfu(paramString) && !zzfw(paramString)) ? getByteArray(paramString) : null;
  }
  
  protected final float zza(String paramString, float paramFloat) {
    return (zzfu(paramString) && !zzfw(paramString)) ? getFloat(paramString) : paramFloat;
  }
  
  protected final <E extends zzbco> E zza(String paramString, Parcelable.Creator<E> paramCreator) {
    byte[] arrayOfByte = zzb(paramString, (byte[])null);
    return (E)((arrayOfByte == null) ? null : zzbcp.zza(arrayOfByte, paramCreator));
  }
  
  protected final <E extends zzbco> List<E> zza(String paramString, Parcelable.Creator<E> paramCreator, List<E> paramList) {
    byte[] arrayOfByte = zzb(paramString, (byte[])null);
    if (arrayOfByte == null)
      return paramList; 
    try {
      zzdfn zzdfn = zzdfn.zzad(arrayOfByte);
      if (zzdfn.zzkzb == null)
        return paramList; 
      ArrayList<zzbco> arrayList = new ArrayList();
      this(zzdfn.zzkzb.length);
      byte[][] arrayOfByte1 = zzdfn.zzkzb;
      int i = arrayOfByte1.length;
      for (byte b = 0; b < i; b++)
        arrayList.add(zzbcp.zza(arrayOfByte1[b], paramCreator)); 
      return (List)arrayList;
    } catch (zzeym zzeym) {
      if (Log.isLoggable("SafeDataBufferRef", 6))
        Log.e("SafeDataBufferRef", "Cannot parse byte[]", (Throwable)zzeym); 
      return paramList;
    } 
  }
  
  protected final List<Integer> zza(String paramString, List<Integer> paramList) {
    byte[] arrayOfByte = zzb(paramString, (byte[])null);
    if (arrayOfByte == null)
      return paramList; 
    try {
      zzdfn zzdfn = zzdfn.zzad(arrayOfByte);
      if (zzdfn.zzkza == null)
        return paramList; 
      ArrayList<Integer> arrayList = new ArrayList();
      this(zzdfn.zzkza.length);
      byte b = 0;
      while (true) {
        int[] arrayOfInt = zzdfn.zzkza;
        if (b < arrayOfInt.length) {
          arrayList.add(Integer.valueOf(arrayOfInt[b]));
          b++;
          continue;
        } 
        return arrayList;
      } 
    } catch (zzeym zzeym) {
      if (Log.isLoggable("SafeDataBufferRef", 6))
        Log.e("SafeDataBufferRef", "Cannot parse byte[]", (Throwable)zzeym); 
      return paramList;
    } 
  }
  
  protected final String zzae(String paramString1, String paramString2) {
    return (zzfu(paramString1) && !zzfw(paramString1)) ? getString(paramString1) : paramString2;
  }
  
  protected final List<String> zzb(String paramString, List<String> paramList) {
    byte[] arrayOfByte = zzb(paramString, (byte[])null);
    if (arrayOfByte == null)
      return paramList; 
    try {
      String[] arrayOfString = (zzdfn.zzad(arrayOfByte)).zzkyz;
      return (arrayOfString == null) ? paramList : Arrays.asList(arrayOfString);
    } catch (zzeym zzeym) {
      if (Log.isLoggable("SafeDataBufferRef", 6))
        Log.e("SafeDataBufferRef", "Cannot parse byte[]", (Throwable)zzeym); 
      return paramList;
    } 
  }
  
  protected final int zzw(String paramString, int paramInt) {
    return (zzfu(paramString) && !zzfw(paramString)) ? getInteger(paramString) : paramInt;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/internal/zzav.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */