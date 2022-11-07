package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import java.util.Arrays;
import java.util.Comparator;

public class DetectedActivity extends zzbck {
  public static final Parcelable.Creator<DetectedActivity> CREATOR;
  
  public static final int IN_VEHICLE = 0;
  
  public static final int ON_BICYCLE = 1;
  
  public static final int ON_FOOT = 2;
  
  public static final int RUNNING = 8;
  
  public static final int STILL = 3;
  
  public static final int TILTING = 5;
  
  public static final int UNKNOWN = 4;
  
  public static final int WALKING = 7;
  
  private static Comparator<DetectedActivity> zzhwy = new zzc();
  
  private static int[] zzhwz = new int[] { 9, 10 };
  
  private static int[] zzhxa = new int[] { 
      0, 1, 2, 4, 5, 6, 7, 8, 10, 11, 
      12, 13, 14, 16, 17 };
  
  private int zzhxb;
  
  private int zzhxc;
  
  static {
    CREATOR = new zzd();
  }
  
  public DetectedActivity(int paramInt1, int paramInt2) {
    this.zzhxb = paramInt1;
    this.zzhxc = paramInt2;
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject != null && getClass() == paramObject.getClass()) {
      paramObject = paramObject;
      if (this.zzhxb == ((DetectedActivity)paramObject).zzhxb && this.zzhxc == ((DetectedActivity)paramObject).zzhxc)
        return true; 
    } 
    return false;
  }
  
  public int getConfidence() {
    return this.zzhxc;
  }
  
  public int getType() {
    int i = this.zzhxb;
    int j = i;
    if (i > 17)
      j = 4; 
    return j;
  }
  
  public int hashCode() {
    return Arrays.hashCode(new Object[] { Integer.valueOf(this.zzhxb), Integer.valueOf(this.zzhxc) });
  }
  
  public String toString() {
    String str;
    int i = getType();
    if (i != 0) {
      if (i != 1) {
        if (i != 2) {
          if (i != 3) {
            if (i != 4) {
              if (i != 5) {
                if (i != 7) {
                  if (i != 8) {
                    if (i != 16) {
                      if (i != 17) {
                        str = Integer.toString(i);
                      } else {
                        str = "IN_RAIL_VEHICLE";
                      } 
                    } else {
                      str = "IN_ROAD_VEHICLE";
                    } 
                  } else {
                    str = "RUNNING";
                  } 
                } else {
                  str = "WALKING";
                } 
              } else {
                str = "TILTING";
              } 
            } else {
              str = "UNKNOWN";
            } 
          } else {
            str = "STILL";
          } 
        } else {
          str = "ON_FOOT";
        } 
      } else {
        str = "ON_BICYCLE";
      } 
    } else {
      str = "IN_VEHICLE";
    } 
    i = this.zzhxc;
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 48);
    stringBuilder.append("DetectedActivity [type=");
    stringBuilder.append(str);
    stringBuilder.append(", confidence=");
    stringBuilder.append(i);
    stringBuilder.append("]");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = zzbcn.zze(paramParcel);
    zzbcn.zzc(paramParcel, 1, this.zzhxb);
    zzbcn.zzc(paramParcel, 2, this.zzhxc);
    zzbcn.zzai(paramParcel, paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/DetectedActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */