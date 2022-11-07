package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

public final class zzbcn {
  public static void zza(Parcel paramParcel, int paramInt, byte paramByte) {
    zzb(paramParcel, paramInt, 4);
    paramParcel.writeInt(paramByte);
  }
  
  public static void zza(Parcel paramParcel, int paramInt, double paramDouble) {
    zzb(paramParcel, paramInt, 8);
    paramParcel.writeDouble(paramDouble);
  }
  
  public static void zza(Parcel paramParcel, int paramInt, float paramFloat) {
    zzb(paramParcel, paramInt, 4);
    paramParcel.writeFloat(paramFloat);
  }
  
  public static void zza(Parcel paramParcel, int paramInt, long paramLong) {
    zzb(paramParcel, paramInt, 8);
    paramParcel.writeLong(paramLong);
  }
  
  public static void zza(Parcel paramParcel, int paramInt, Bundle paramBundle, boolean paramBoolean) {
    if (paramBundle == null)
      return; 
    paramInt = zzag(paramParcel, paramInt);
    paramParcel.writeBundle(paramBundle);
    zzah(paramParcel, paramInt);
  }
  
  public static void zza(Parcel paramParcel, int paramInt, IBinder paramIBinder, boolean paramBoolean) {
    if (paramIBinder == null)
      return; 
    paramInt = zzag(paramParcel, paramInt);
    paramParcel.writeStrongBinder(paramIBinder);
    zzah(paramParcel, paramInt);
  }
  
  public static void zza(Parcel paramParcel1, int paramInt, Parcel paramParcel2, boolean paramBoolean) {
    if (paramParcel2 == null)
      return; 
    paramInt = zzag(paramParcel1, 2);
    paramParcel1.appendFrom(paramParcel2, 0, paramParcel2.dataSize());
    zzah(paramParcel1, paramInt);
  }
  
  public static void zza(Parcel paramParcel, int paramInt1, Parcelable paramParcelable, int paramInt2, boolean paramBoolean) {
    if (paramParcelable == null) {
      if (paramBoolean)
        zzb(paramParcel, paramInt1, 0); 
      return;
    } 
    paramInt1 = zzag(paramParcel, paramInt1);
    paramParcelable.writeToParcel(paramParcel, paramInt2);
    zzah(paramParcel, paramInt1);
  }
  
  public static void zza(Parcel paramParcel, int paramInt, Boolean paramBoolean, boolean paramBoolean1) {
    if (paramBoolean == null)
      return; 
    zzb(paramParcel, 3, 4);
    paramParcel.writeInt(paramBoolean.booleanValue());
  }
  
  public static void zza(Parcel paramParcel, int paramInt, Double paramDouble, boolean paramBoolean) {
    if (paramDouble == null)
      return; 
    zzb(paramParcel, paramInt, 8);
    paramParcel.writeDouble(paramDouble.doubleValue());
  }
  
  public static void zza(Parcel paramParcel, int paramInt, Float paramFloat, boolean paramBoolean) {
    if (paramFloat == null)
      return; 
    zzb(paramParcel, paramInt, 4);
    paramParcel.writeFloat(paramFloat.floatValue());
  }
  
  public static void zza(Parcel paramParcel, int paramInt, Integer paramInteger, boolean paramBoolean) {
    if (paramInteger == null)
      return; 
    zzb(paramParcel, paramInt, 4);
    paramParcel.writeInt(paramInteger.intValue());
  }
  
  public static void zza(Parcel paramParcel, int paramInt, Long paramLong, boolean paramBoolean) {
    if (paramLong == null)
      return; 
    zzb(paramParcel, paramInt, 8);
    paramParcel.writeLong(paramLong.longValue());
  }
  
  public static void zza(Parcel paramParcel, int paramInt, String paramString, boolean paramBoolean) {
    if (paramString == null) {
      if (paramBoolean)
        zzb(paramParcel, paramInt, 0); 
      return;
    } 
    paramInt = zzag(paramParcel, paramInt);
    paramParcel.writeString(paramString);
    zzah(paramParcel, paramInt);
  }
  
  public static void zza(Parcel paramParcel, int paramInt, List<Integer> paramList, boolean paramBoolean) {
    if (paramList == null)
      return; 
    int i = zzag(paramParcel, paramInt);
    int j = paramList.size();
    paramParcel.writeInt(j);
    for (paramInt = 0; paramInt < j; paramInt++)
      paramParcel.writeInt(((Integer)paramList.get(paramInt)).intValue()); 
    zzah(paramParcel, i);
  }
  
  public static void zza(Parcel paramParcel, int paramInt, short paramShort) {
    zzb(paramParcel, 3, 4);
    paramParcel.writeInt(paramShort);
  }
  
  public static void zza(Parcel paramParcel, int paramInt, boolean paramBoolean) {
    zzb(paramParcel, paramInt, 4);
    paramParcel.writeInt(paramBoolean);
  }
  
  public static void zza(Parcel paramParcel, int paramInt, byte[] paramArrayOfbyte, boolean paramBoolean) {
    if (paramArrayOfbyte == null) {
      if (paramBoolean)
        zzb(paramParcel, paramInt, 0); 
      return;
    } 
    paramInt = zzag(paramParcel, paramInt);
    paramParcel.writeByteArray(paramArrayOfbyte);
    zzah(paramParcel, paramInt);
  }
  
  public static void zza(Parcel paramParcel, int paramInt, float[] paramArrayOffloat, boolean paramBoolean) {
    if (paramArrayOffloat == null)
      return; 
    paramInt = zzag(paramParcel, 7);
    paramParcel.writeFloatArray(paramArrayOffloat);
    zzah(paramParcel, paramInt);
  }
  
  public static void zza(Parcel paramParcel, int paramInt, int[] paramArrayOfint, boolean paramBoolean) {
    if (paramArrayOfint == null)
      return; 
    paramInt = zzag(paramParcel, paramInt);
    paramParcel.writeIntArray(paramArrayOfint);
    zzah(paramParcel, paramInt);
  }
  
  public static void zza(Parcel paramParcel, int paramInt, long[] paramArrayOflong, boolean paramBoolean) {
    if (paramArrayOflong == null)
      return; 
    paramInt = zzag(paramParcel, paramInt);
    paramParcel.writeLongArray(paramArrayOflong);
    zzah(paramParcel, paramInt);
  }
  
  public static <T extends Parcelable> void zza(Parcel paramParcel, int paramInt1, T[] paramArrayOfT, int paramInt2, boolean paramBoolean) {
    if (paramArrayOfT == null)
      return; 
    int i = zzag(paramParcel, paramInt1);
    int j = paramArrayOfT.length;
    paramParcel.writeInt(j);
    for (paramInt1 = 0; paramInt1 < j; paramInt1++) {
      T t = paramArrayOfT[paramInt1];
      if (t == null) {
        paramParcel.writeInt(0);
      } else {
        zza(paramParcel, t, paramInt2);
      } 
    } 
    zzah(paramParcel, i);
  }
  
  public static void zza(Parcel paramParcel, int paramInt, String[] paramArrayOfString, boolean paramBoolean) {
    if (paramArrayOfString == null)
      return; 
    paramInt = zzag(paramParcel, paramInt);
    paramParcel.writeStringArray(paramArrayOfString);
    zzah(paramParcel, paramInt);
  }
  
  public static void zza(Parcel paramParcel, int paramInt, boolean[] paramArrayOfboolean, boolean paramBoolean) {
    if (paramArrayOfboolean == null)
      return; 
    paramInt = zzag(paramParcel, paramInt);
    paramParcel.writeBooleanArray(paramArrayOfboolean);
    zzah(paramParcel, paramInt);
  }
  
  public static void zza(Parcel paramParcel, int paramInt, byte[][] paramArrayOfbyte, boolean paramBoolean) {
    if (paramArrayOfbyte == null)
      return; 
    int i = zzag(paramParcel, paramInt);
    int j = paramArrayOfbyte.length;
    paramParcel.writeInt(j);
    for (paramInt = 0; paramInt < j; paramInt++)
      paramParcel.writeByteArray(paramArrayOfbyte[paramInt]); 
    zzah(paramParcel, i);
  }
  
  private static <T extends Parcelable> void zza(Parcel paramParcel, T paramT, int paramInt) {
    int i = paramParcel.dataPosition();
    paramParcel.writeInt(1);
    int j = paramParcel.dataPosition();
    paramT.writeToParcel(paramParcel, paramInt);
    paramInt = paramParcel.dataPosition();
    paramParcel.setDataPosition(i);
    paramParcel.writeInt(paramInt - j);
    paramParcel.setDataPosition(paramInt);
  }
  
  private static int zzag(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(paramInt | 0xFFFF0000);
    paramParcel.writeInt(0);
    return paramParcel.dataPosition();
  }
  
  private static void zzah(Parcel paramParcel, int paramInt) {
    int i = paramParcel.dataPosition();
    paramParcel.setDataPosition(paramInt - 4);
    paramParcel.writeInt(i - paramInt);
    paramParcel.setDataPosition(i);
  }
  
  public static void zzai(Parcel paramParcel, int paramInt) {
    zzah(paramParcel, paramInt);
  }
  
  private static void zzb(Parcel paramParcel, int paramInt1, int paramInt2) {
    if (paramInt2 >= 65535) {
      paramParcel.writeInt(paramInt1 | 0xFFFF0000);
      paramParcel.writeInt(paramInt2);
      return;
    } 
    paramParcel.writeInt(paramInt1 | paramInt2 << 16);
  }
  
  public static void zzb(Parcel paramParcel, int paramInt, List<String> paramList, boolean paramBoolean) {
    if (paramList == null)
      return; 
    paramInt = zzag(paramParcel, paramInt);
    paramParcel.writeStringList(paramList);
    zzah(paramParcel, paramInt);
  }
  
  public static void zzc(Parcel paramParcel, int paramInt1, int paramInt2) {
    zzb(paramParcel, paramInt1, 4);
    paramParcel.writeInt(paramInt2);
  }
  
  public static <T extends Parcelable> void zzc(Parcel paramParcel, int paramInt, List<T> paramList, boolean paramBoolean) {
    if (paramList == null) {
      if (paramBoolean)
        zzb(paramParcel, paramInt, 0); 
      return;
    } 
    int i = zzag(paramParcel, paramInt);
    int j = paramList.size();
    paramParcel.writeInt(j);
    for (paramInt = 0; paramInt < j; paramInt++) {
      Parcelable parcelable = (Parcelable)paramList.get(paramInt);
      if (parcelable == null) {
        paramParcel.writeInt(0);
      } else {
        zza(paramParcel, parcelable, 0);
      } 
    } 
    zzah(paramParcel, i);
  }
  
  public static void zzd(Parcel paramParcel, int paramInt, List paramList, boolean paramBoolean) {
    if (paramList == null)
      return; 
    paramInt = zzag(paramParcel, paramInt);
    paramParcel.writeList(paramList);
    zzah(paramParcel, paramInt);
  }
  
  public static int zze(Parcel paramParcel) {
    return zzag(paramParcel, 20293);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzbcn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */