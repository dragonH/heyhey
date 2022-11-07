package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.location.Geofence;
import java.util.Locale;

public final class zzcac extends zzbck implements Geofence {
  public static final Parcelable.Creator<zzcac> CREATOR = new zzcad();
  
  private final String zzcjw;
  
  private final int zzhxf;
  
  private final short zzhxh;
  
  private final double zzhxi;
  
  private final double zzhxj;
  
  private final float zzhxk;
  
  private final int zzhxl;
  
  private final int zzhxm;
  
  private final long zziaf;
  
  public zzcac(String paramString, int paramInt1, short paramShort, double paramDouble1, double paramDouble2, float paramFloat, long paramLong, int paramInt2, int paramInt3) {
    if (paramString == null || paramString.length() > 100) {
      paramString = String.valueOf(paramString);
      if (paramString.length() != 0) {
        paramString = "requestId is null or too long: ".concat(paramString);
      } else {
        paramString = new String("requestId is null or too long: ");
      } 
      throw new IllegalArgumentException(paramString);
    } 
    if (paramFloat > 0.0F) {
      if (paramDouble1 <= 90.0D && paramDouble1 >= -90.0D) {
        if (paramDouble2 <= 180.0D && paramDouble2 >= -180.0D) {
          int i = paramInt1 & 0x7;
          if (i != 0) {
            this.zzhxh = (short)paramShort;
            this.zzcjw = paramString;
            this.zzhxi = paramDouble1;
            this.zzhxj = paramDouble2;
            this.zzhxk = paramFloat;
            this.zziaf = paramLong;
            this.zzhxf = i;
            this.zzhxl = paramInt2;
            this.zzhxm = paramInt3;
            return;
          } 
          StringBuilder stringBuilder3 = new StringBuilder(46);
          stringBuilder3.append("No supported transition specified: ");
          stringBuilder3.append(paramInt1);
          throw new IllegalArgumentException(stringBuilder3.toString());
        } 
        StringBuilder stringBuilder2 = new StringBuilder(43);
        stringBuilder2.append("invalid longitude: ");
        stringBuilder2.append(paramDouble2);
        throw new IllegalArgumentException(stringBuilder2.toString());
      } 
      StringBuilder stringBuilder1 = new StringBuilder(42);
      stringBuilder1.append("invalid latitude: ");
      stringBuilder1.append(paramDouble1);
      throw new IllegalArgumentException(stringBuilder1.toString());
    } 
    StringBuilder stringBuilder = new StringBuilder(31);
    stringBuilder.append("invalid radius: ");
    stringBuilder.append(paramFloat);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public static zzcac zzo(byte[] paramArrayOfbyte) {
    Parcel parcel = Parcel.obtain();
    parcel.unmarshall(paramArrayOfbyte, 0, paramArrayOfbyte.length);
    parcel.setDataPosition(0);
    zzcac zzcac1 = (zzcac)CREATOR.createFromParcel(parcel);
    parcel.recycle();
    return zzcac1;
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (!(paramObject instanceof zzcac))
      return false; 
    paramObject = paramObject;
    return (this.zzhxk != ((zzcac)paramObject).zzhxk) ? false : ((this.zzhxi != ((zzcac)paramObject).zzhxi) ? false : ((this.zzhxj != ((zzcac)paramObject).zzhxj) ? false : (!(this.zzhxh != ((zzcac)paramObject).zzhxh))));
  }
  
  public final String getRequestId() {
    return this.zzcjw;
  }
  
  public final int hashCode() {
    long l = Double.doubleToLongBits(this.zzhxi);
    int i = (int)(l ^ l >>> 32L);
    l = Double.doubleToLongBits(this.zzhxj);
    return ((((i + 31) * 31 + (int)(l ^ l >>> 32L)) * 31 + Float.floatToIntBits(this.zzhxk)) * 31 + this.zzhxh) * 31 + this.zzhxf;
  }
  
  public final String toString() {
    String str;
    Locale locale = Locale.US;
    if (this.zzhxh != 1) {
      str = null;
    } else {
      str = "CIRCLE";
    } 
    return String.format(locale, "Geofence[%s id:%s transitions:%d %.6f, %.6f %.0fm, resp=%ds, dwell=%dms, @%d]", new Object[] { str, this.zzcjw, Integer.valueOf(this.zzhxf), Double.valueOf(this.zzhxi), Double.valueOf(this.zzhxj), Float.valueOf(this.zzhxk), Integer.valueOf(this.zzhxl / 1000), Integer.valueOf(this.zzhxm), Long.valueOf(this.zziaf) });
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = zzbcn.zze(paramParcel);
    zzbcn.zza(paramParcel, 1, getRequestId(), false);
    zzbcn.zza(paramParcel, 2, this.zziaf);
    zzbcn.zza(paramParcel, 3, this.zzhxh);
    zzbcn.zza(paramParcel, 4, this.zzhxi);
    zzbcn.zza(paramParcel, 5, this.zzhxj);
    zzbcn.zza(paramParcel, 6, this.zzhxk);
    zzbcn.zzc(paramParcel, 7, this.zzhxf);
    zzbcn.zzc(paramParcel, 8, this.zzhxl);
    zzbcn.zzc(paramParcel, 9, this.zzhxm);
    zzbcn.zzai(paramParcel, paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzcac.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */