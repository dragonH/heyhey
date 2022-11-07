package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.common.internal.zzbf;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PatternItem extends zzbck {
  public static final Parcelable.Creator<PatternItem> CREATOR;
  
  private static final String TAG = PatternItem.class.getSimpleName();
  
  private final int type;
  
  @Nullable
  private final Float zzijp;
  
  static {
    CREATOR = new zzi();
  }
  
  public PatternItem(int paramInt, @Nullable Float paramFloat) {
    boolean bool1 = true;
    boolean bool2 = bool1;
    if (paramInt != 1)
      if (paramFloat != null && paramFloat.floatValue() >= 0.0F) {
        bool2 = bool1;
      } else {
        bool2 = false;
      }  
    String str = String.valueOf(paramFloat);
    StringBuilder stringBuilder = new StringBuilder(str.length() + 45);
    stringBuilder.append("Invalid PatternItem: type=");
    stringBuilder.append(paramInt);
    stringBuilder.append(" length=");
    stringBuilder.append(str);
    zzbp.zzb(bool2, stringBuilder.toString());
    this.type = paramInt;
    this.zzijp = paramFloat;
  }
  
  @Nullable
  static List<PatternItem> zzad(@Nullable List<PatternItem> paramList) {
    if (paramList == null)
      return null; 
    ArrayList<PatternItem> arrayList = new ArrayList(paramList.size());
    for (PatternItem patternItem : paramList) {
      if (patternItem == null) {
        patternItem = null;
      } else {
        int i = patternItem.type;
        if (i != 0) {
          if (i != 1) {
            if (i != 2) {
              String str = TAG;
              StringBuilder stringBuilder = new StringBuilder(37);
              stringBuilder.append("Unknown PatternItem type: ");
              stringBuilder.append(i);
              Log.w(str, stringBuilder.toString());
            } else {
              patternItem = new Gap(patternItem.zzijp.floatValue());
            } 
          } else {
            patternItem = new Dot();
          } 
        } else {
          patternItem = new Dash(patternItem.zzijp.floatValue());
        } 
      } 
      arrayList.add(patternItem);
    } 
    return arrayList;
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof PatternItem))
      return false; 
    paramObject = paramObject;
    return (this.type == ((PatternItem)paramObject).type && zzbf.equal(this.zzijp, ((PatternItem)paramObject).zzijp));
  }
  
  public int hashCode() {
    return Arrays.hashCode(new Object[] { Integer.valueOf(this.type), this.zzijp });
  }
  
  public String toString() {
    int i = this.type;
    String str = String.valueOf(this.zzijp);
    StringBuilder stringBuilder = new StringBuilder(str.length() + 39);
    stringBuilder.append("[PatternItem: type=");
    stringBuilder.append(i);
    stringBuilder.append(" length=");
    stringBuilder.append(str);
    stringBuilder.append("]");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = zzbcn.zze(paramParcel);
    zzbcn.zzc(paramParcel, 2, this.type);
    zzbcn.zza(paramParcel, 3, this.zzijp, false);
    zzbcn.zzai(paramParcel, paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/model/PatternItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */