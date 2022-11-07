package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.common.internal.zzbf;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import java.util.Arrays;

public class Cap extends zzbck {
  public static final Parcelable.Creator<Cap> CREATOR;
  
  private static final String TAG = Cap.class.getSimpleName();
  
  @Nullable
  private final BitmapDescriptor bitmapDescriptor;
  
  private final int type;
  
  @Nullable
  private final Float zziik;
  
  static {
    CREATOR = new zzb();
  }
  
  protected Cap(int paramInt) {
    this(paramInt, (BitmapDescriptor)null, (Float)null);
  }
  
  Cap(int paramInt, @Nullable IBinder paramIBinder, @Nullable Float paramFloat) {
    this(paramInt, bitmapDescriptor, paramFloat);
  }
  
  private Cap(int paramInt, @Nullable BitmapDescriptor paramBitmapDescriptor, @Nullable Float paramFloat) {
    boolean bool2;
    boolean bool1 = true;
    if (paramFloat != null && paramFloat.floatValue() > 0.0F) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    boolean bool3 = bool1;
    if (paramInt == 3)
      if (paramBitmapDescriptor != null && bool2) {
        bool3 = bool1;
      } else {
        bool3 = false;
      }  
    String str1 = String.valueOf(paramBitmapDescriptor);
    String str2 = String.valueOf(paramFloat);
    StringBuilder stringBuilder = new StringBuilder(str1.length() + 63 + str2.length());
    stringBuilder.append("Invalid Cap: type=");
    stringBuilder.append(paramInt);
    stringBuilder.append(" bitmapDescriptor=");
    stringBuilder.append(str1);
    stringBuilder.append(" bitmapRefWidth=");
    stringBuilder.append(str2);
    zzbp.zzb(bool3, stringBuilder.toString());
    this.type = paramInt;
    this.bitmapDescriptor = paramBitmapDescriptor;
    this.zziik = paramFloat;
  }
  
  protected Cap(@NonNull BitmapDescriptor paramBitmapDescriptor, float paramFloat) {
    this(3, paramBitmapDescriptor, Float.valueOf(paramFloat));
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof Cap))
      return false; 
    paramObject = paramObject;
    return (this.type == ((Cap)paramObject).type && zzbf.equal(this.bitmapDescriptor, ((Cap)paramObject).bitmapDescriptor) && zzbf.equal(this.zziik, ((Cap)paramObject).zziik));
  }
  
  public int hashCode() {
    return Arrays.hashCode(new Object[] { Integer.valueOf(this.type), this.bitmapDescriptor, this.zziik });
  }
  
  public String toString() {
    int i = this.type;
    StringBuilder stringBuilder = new StringBuilder(23);
    stringBuilder.append("[Cap: type=");
    stringBuilder.append(i);
    stringBuilder.append("]");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    IBinder iBinder;
    paramInt = zzbcn.zze(paramParcel);
    zzbcn.zzc(paramParcel, 2, this.type);
    BitmapDescriptor bitmapDescriptor = this.bitmapDescriptor;
    if (bitmapDescriptor == null) {
      bitmapDescriptor = null;
    } else {
      iBinder = bitmapDescriptor.zzatl().asBinder();
    } 
    zzbcn.zza(paramParcel, 3, iBinder, false);
    zzbcn.zza(paramParcel, 4, this.zziik, false);
    zzbcn.zzai(paramParcel, paramInt);
  }
  
  final Cap zzatr() {
    int i = this.type;
    if (i != 0) {
      if (i != 1) {
        if (i != 2) {
          if (i != 3) {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder(29);
            stringBuilder.append("Unknown Cap type: ");
            stringBuilder.append(i);
            Log.w(str, stringBuilder.toString());
            return this;
          } 
          return new CustomCap(this.bitmapDescriptor, this.zziik.floatValue());
        } 
        return new RoundCap();
      } 
      return new SquareCap();
    } 
    return new ButtCap();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/model/Cap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */