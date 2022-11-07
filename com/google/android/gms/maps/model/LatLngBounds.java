package com.google.android.gms.maps.model;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import com.google.android.gms.R;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbf;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import java.util.Arrays;

public final class LatLngBounds extends zzbck implements ReflectedParcelable {
  public static final Parcelable.Creator<LatLngBounds> CREATOR = new zze();
  
  public final LatLng northeast;
  
  public final LatLng southwest;
  
  public LatLngBounds(LatLng paramLatLng1, LatLng paramLatLng2) {
    boolean bool;
    zzbp.zzb(paramLatLng1, "null southwest");
    zzbp.zzb(paramLatLng2, "null northeast");
    double d1 = paramLatLng2.latitude;
    double d2 = paramLatLng1.latitude;
    if (d1 >= d2) {
      bool = true;
    } else {
      bool = false;
    } 
    zzbp.zzb(bool, "southern latitude exceeds northern latitude (%s > %s)", new Object[] { Double.valueOf(d2), Double.valueOf(paramLatLng2.latitude) });
    this.southwest = paramLatLng1;
    this.northeast = paramLatLng2;
  }
  
  public static Builder builder() {
    return new Builder();
  }
  
  public static LatLngBounds createFromAttributes(Context paramContext, AttributeSet paramAttributeSet) {
    if (paramContext != null && paramAttributeSet != null) {
      Float float_;
      TypedArray typedArray = paramContext.getResources().obtainAttributes(paramAttributeSet, R.styleable.MapAttrs);
      int i = R.styleable.MapAttrs_latLngBoundsSouthWestLatitude;
      if (typedArray.hasValue(i)) {
        Float float_1 = Float.valueOf(typedArray.getFloat(i, 0.0F));
      } else {
        paramContext = null;
      } 
      i = R.styleable.MapAttrs_latLngBoundsSouthWestLongitude;
      if (typedArray.hasValue(i)) {
        Float float_1 = Float.valueOf(typedArray.getFloat(i, 0.0F));
      } else {
        paramAttributeSet = null;
      } 
      i = R.styleable.MapAttrs_latLngBoundsNorthEastLatitude;
      if (typedArray.hasValue(i)) {
        float_ = Float.valueOf(typedArray.getFloat(i, 0.0F));
      } else {
        float_ = null;
      } 
      i = R.styleable.MapAttrs_latLngBoundsNorthEastLongitude;
      if (typedArray.hasValue(i)) {
        Float float_1 = Float.valueOf(typedArray.getFloat(i, 0.0F));
      } else {
        typedArray = null;
      } 
      if (paramContext != null && paramAttributeSet != null && float_ != null && typedArray != null)
        return new LatLngBounds(new LatLng(paramContext.floatValue(), paramAttributeSet.floatValue()), new LatLng(float_.floatValue(), typedArray.floatValue())); 
    } 
    return null;
  }
  
  private static double zza(double paramDouble1, double paramDouble2) {
    return (paramDouble1 - paramDouble2 + 360.0D) % 360.0D;
  }
  
  private static double zzb(double paramDouble1, double paramDouble2) {
    return (paramDouble2 - paramDouble1 + 360.0D) % 360.0D;
  }
  
  private final boolean zzg(double paramDouble) {
    double d1 = this.southwest.longitude;
    double d2 = this.northeast.longitude;
    return (d1 <= d2) ? ((d1 <= paramDouble && paramDouble <= d2)) : ((d1 <= paramDouble || paramDouble <= d2));
  }
  
  public final boolean contains(LatLng paramLatLng) {
    boolean bool;
    double d = paramLatLng.latitude;
    if (this.southwest.latitude <= d && d <= this.northeast.latitude) {
      bool = true;
    } else {
      bool = false;
    } 
    return (bool && zzg(paramLatLng.longitude));
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof LatLngBounds))
      return false; 
    paramObject = paramObject;
    return (this.southwest.equals(((LatLngBounds)paramObject).southwest) && this.northeast.equals(((LatLngBounds)paramObject).northeast));
  }
  
  public final LatLng getCenter() {
    LatLng latLng1 = this.southwest;
    double d1 = latLng1.latitude;
    LatLng latLng2 = this.northeast;
    double d2 = (d1 + latLng2.latitude) / 2.0D;
    d1 = latLng2.longitude;
    double d3 = latLng1.longitude;
    if (d3 > d1)
      d1 += 360.0D; 
    return new LatLng(d2, (d1 + d3) / 2.0D);
  }
  
  public final int hashCode() {
    return Arrays.hashCode(new Object[] { this.southwest, this.northeast });
  }
  
  public final LatLngBounds including(LatLng paramLatLng) {
    double d1 = Math.min(this.southwest.latitude, paramLatLng.latitude);
    double d2 = Math.max(this.northeast.latitude, paramLatLng.latitude);
    double d3 = this.northeast.longitude;
    double d4 = this.southwest.longitude;
    double d5 = paramLatLng.longitude;
    double d6 = d3;
    double d7 = d4;
    if (!zzg(d5))
      if (zza(d4, d5) < zzb(d3, d5)) {
        d7 = d5;
        d6 = d3;
      } else {
        d6 = d5;
        d7 = d4;
      }  
    return new LatLngBounds(new LatLng(d1, d7), new LatLng(d2, d6));
  }
  
  public final String toString() {
    return zzbf.zzt(this).zzg("southwest", this.southwest).zzg("northeast", this.northeast).toString();
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = zzbcn.zze(paramParcel);
    zzbcn.zza(paramParcel, 2, (Parcelable)this.southwest, paramInt, false);
    zzbcn.zza(paramParcel, 3, (Parcelable)this.northeast, paramInt, false);
    zzbcn.zzai(paramParcel, i);
  }
  
  public static final class Builder {
    private double zzijc = Double.POSITIVE_INFINITY;
    
    private double zzijd = Double.NEGATIVE_INFINITY;
    
    private double zzije = Double.NaN;
    
    private double zzijf = Double.NaN;
    
    public final LatLngBounds build() {
      zzbp.zza(Double.isNaN(this.zzije) ^ true, "no included points");
      return new LatLngBounds(new LatLng(this.zzijc, this.zzije), new LatLng(this.zzijd, this.zzijf));
    }
    
    public final Builder include(LatLng param1LatLng) {
      // Byte code:
      //   0: aload_0
      //   1: aload_0
      //   2: getfield zzijc : D
      //   5: aload_1
      //   6: getfield latitude : D
      //   9: invokestatic min : (DD)D
      //   12: putfield zzijc : D
      //   15: aload_0
      //   16: aload_0
      //   17: getfield zzijd : D
      //   20: aload_1
      //   21: getfield latitude : D
      //   24: invokestatic max : (DD)D
      //   27: putfield zzijd : D
      //   30: aload_1
      //   31: getfield longitude : D
      //   34: dstore_2
      //   35: aload_0
      //   36: getfield zzije : D
      //   39: invokestatic isNaN : (D)Z
      //   42: ifeq -> 53
      //   45: aload_0
      //   46: dload_2
      //   47: putfield zzije : D
      //   50: goto -> 153
      //   53: aload_0
      //   54: getfield zzije : D
      //   57: dstore #4
      //   59: aload_0
      //   60: getfield zzijf : D
      //   63: dstore #6
      //   65: iconst_0
      //   66: istore #8
      //   68: dload #4
      //   70: dload #6
      //   72: dcmpg
      //   73: ifgt -> 101
      //   76: iload #8
      //   78: istore #9
      //   80: dload #4
      //   82: dload_2
      //   83: dcmpg
      //   84: ifgt -> 122
      //   87: iload #8
      //   89: istore #9
      //   91: dload_2
      //   92: dload #6
      //   94: dcmpg
      //   95: ifgt -> 122
      //   98: goto -> 119
      //   101: dload #4
      //   103: dload_2
      //   104: dcmpg
      //   105: ifle -> 119
      //   108: iload #8
      //   110: istore #9
      //   112: dload_2
      //   113: dload #6
      //   115: dcmpg
      //   116: ifgt -> 122
      //   119: iconst_1
      //   120: istore #9
      //   122: iload #9
      //   124: ifne -> 158
      //   127: dload #4
      //   129: dload_2
      //   130: invokestatic zzc : (DD)D
      //   133: aload_0
      //   134: getfield zzijf : D
      //   137: dload_2
      //   138: invokestatic zzd : (DD)D
      //   141: dcmpg
      //   142: ifge -> 153
      //   145: aload_0
      //   146: dload_2
      //   147: putfield zzije : D
      //   150: goto -> 158
      //   153: aload_0
      //   154: dload_2
      //   155: putfield zzijf : D
      //   158: aload_0
      //   159: areturn
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/model/LatLngBounds.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */