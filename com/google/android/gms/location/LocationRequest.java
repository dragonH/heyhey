package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import java.util.Arrays;

public final class LocationRequest extends zzbck implements ReflectedParcelable {
  public static final Parcelable.Creator<LocationRequest> CREATOR = new zzq();
  
  public static final int PRIORITY_BALANCED_POWER_ACCURACY = 102;
  
  public static final int PRIORITY_HIGH_ACCURACY = 100;
  
  public static final int PRIORITY_LOW_POWER = 104;
  
  public static final int PRIORITY_NO_POWER = 105;
  
  private int mPriority = 102;
  
  private boolean zzgzv = false;
  
  private long zzhxg = Long.MAX_VALUE;
  
  private long zzhxx = 3600000L;
  
  private long zzhxy = 600000L;
  
  private int zzhxz = Integer.MAX_VALUE;
  
  private float zzhya = 0.0F;
  
  private long zzhyb = 0L;
  
  public LocationRequest() {}
  
  LocationRequest(int paramInt1, long paramLong1, long paramLong2, boolean paramBoolean, long paramLong3, int paramInt2, float paramFloat, long paramLong4) {}
  
  public static LocationRequest create() {
    return new LocationRequest();
  }
  
  private static void zzai(long paramLong) {
    if (paramLong >= 0L)
      return; 
    StringBuilder stringBuilder = new StringBuilder(38);
    stringBuilder.append("invalid interval: ");
    stringBuilder.append(paramLong);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof LocationRequest))
      return false; 
    paramObject = paramObject;
    return (this.mPriority == ((LocationRequest)paramObject).mPriority && this.zzhxx == ((LocationRequest)paramObject).zzhxx && this.zzhxy == ((LocationRequest)paramObject).zzhxy && this.zzgzv == ((LocationRequest)paramObject).zzgzv && this.zzhxg == ((LocationRequest)paramObject).zzhxg && this.zzhxz == ((LocationRequest)paramObject).zzhxz && this.zzhya == ((LocationRequest)paramObject).zzhya && getMaxWaitTime() == paramObject.getMaxWaitTime());
  }
  
  public final long getExpirationTime() {
    return this.zzhxg;
  }
  
  public final long getFastestInterval() {
    return this.zzhxy;
  }
  
  public final long getInterval() {
    return this.zzhxx;
  }
  
  public final long getMaxWaitTime() {
    long l1 = this.zzhyb;
    long l2 = this.zzhxx;
    long l3 = l1;
    if (l1 < l2)
      l3 = l2; 
    return l3;
  }
  
  public final int getNumUpdates() {
    return this.zzhxz;
  }
  
  public final int getPriority() {
    return this.mPriority;
  }
  
  public final float getSmallestDisplacement() {
    return this.zzhya;
  }
  
  public final int hashCode() {
    return Arrays.hashCode(new Object[] { Integer.valueOf(this.mPriority), Long.valueOf(this.zzhxx), Float.valueOf(this.zzhya), Long.valueOf(this.zzhyb) });
  }
  
  public final LocationRequest setExpirationDuration(long paramLong) {
    long l = SystemClock.elapsedRealtime();
    if (paramLong > Long.MAX_VALUE - l) {
      this.zzhxg = Long.MAX_VALUE;
    } else {
      this.zzhxg = paramLong + l;
    } 
    if (this.zzhxg < 0L)
      this.zzhxg = 0L; 
    return this;
  }
  
  public final LocationRequest setExpirationTime(long paramLong) {
    this.zzhxg = paramLong;
    if (paramLong < 0L)
      this.zzhxg = 0L; 
    return this;
  }
  
  public final LocationRequest setFastestInterval(long paramLong) {
    zzai(paramLong);
    this.zzgzv = true;
    this.zzhxy = paramLong;
    return this;
  }
  
  public final LocationRequest setInterval(long paramLong) {
    zzai(paramLong);
    this.zzhxx = paramLong;
    if (!this.zzgzv) {
      double d = paramLong;
      Double.isNaN(d);
      this.zzhxy = (long)(d / 6.0D);
    } 
    return this;
  }
  
  public final LocationRequest setMaxWaitTime(long paramLong) {
    zzai(paramLong);
    this.zzhyb = paramLong;
    return this;
  }
  
  public final LocationRequest setNumUpdates(int paramInt) {
    if (paramInt > 0) {
      this.zzhxz = paramInt;
      return this;
    } 
    StringBuilder stringBuilder = new StringBuilder(31);
    stringBuilder.append("invalid numUpdates: ");
    stringBuilder.append(paramInt);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public final LocationRequest setPriority(int paramInt) {
    if (paramInt == 100 || paramInt == 102 || paramInt == 104 || paramInt == 105) {
      this.mPriority = paramInt;
      return this;
    } 
    StringBuilder stringBuilder = new StringBuilder(28);
    stringBuilder.append("invalid quality: ");
    stringBuilder.append(paramInt);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public final LocationRequest setSmallestDisplacement(float paramFloat) {
    if (paramFloat >= 0.0F) {
      this.zzhya = paramFloat;
      return this;
    } 
    StringBuilder stringBuilder = new StringBuilder(37);
    stringBuilder.append("invalid displacement: ");
    stringBuilder.append(paramFloat);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public final String toString() {
    String str;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Request[");
    int i = this.mPriority;
    if (i != 100) {
      if (i != 102) {
        if (i != 104) {
          if (i != 105) {
            str = "???";
          } else {
            str = "PRIORITY_NO_POWER";
          } 
        } else {
          str = "PRIORITY_LOW_POWER";
        } 
      } else {
        str = "PRIORITY_BALANCED_POWER_ACCURACY";
      } 
    } else {
      str = "PRIORITY_HIGH_ACCURACY";
    } 
    stringBuilder.append(str);
    if (this.mPriority != 105) {
      stringBuilder.append(" requested=");
      stringBuilder.append(this.zzhxx);
      stringBuilder.append("ms");
    } 
    stringBuilder.append(" fastest=");
    stringBuilder.append(this.zzhxy);
    stringBuilder.append("ms");
    if (this.zzhyb > this.zzhxx) {
      stringBuilder.append(" maxWait=");
      stringBuilder.append(this.zzhyb);
      stringBuilder.append("ms");
    } 
    if (this.zzhya > 0.0F) {
      stringBuilder.append(" smallestDisplacement=");
      stringBuilder.append(this.zzhya);
      stringBuilder.append("m");
    } 
    long l = this.zzhxg;
    if (l != Long.MAX_VALUE) {
      long l1 = SystemClock.elapsedRealtime();
      stringBuilder.append(" expireIn=");
      stringBuilder.append(l - l1);
      stringBuilder.append("ms");
    } 
    if (this.zzhxz != Integer.MAX_VALUE) {
      stringBuilder.append(" num=");
      stringBuilder.append(this.zzhxz);
    } 
    stringBuilder.append(']');
    return stringBuilder.toString();
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = zzbcn.zze(paramParcel);
    zzbcn.zzc(paramParcel, 1, this.mPriority);
    zzbcn.zza(paramParcel, 2, this.zzhxx);
    zzbcn.zza(paramParcel, 3, this.zzhxy);
    zzbcn.zza(paramParcel, 4, this.zzgzv);
    zzbcn.zza(paramParcel, 5, this.zzhxg);
    zzbcn.zzc(paramParcel, 6, this.zzhxz);
    zzbcn.zza(paramParcel, 7, this.zzhya);
    zzbcn.zza(paramParcel, 8, this.zzhyb);
    zzbcn.zzai(paramParcel, paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/LocationRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */