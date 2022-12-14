package com.google.android.gms.maps.model;

import android.support.annotation.NonNull;

public final class CustomCap extends Cap {
  public final BitmapDescriptor bitmapDescriptor;
  
  public final float refWidth;
  
  public CustomCap(@NonNull BitmapDescriptor paramBitmapDescriptor) {
    this(paramBitmapDescriptor, 10.0F);
  }
  
  public CustomCap(@NonNull BitmapDescriptor paramBitmapDescriptor, float paramFloat) {}
  
  public final String toString() {
    String str = String.valueOf(this.bitmapDescriptor);
    float f = this.refWidth;
    StringBuilder stringBuilder = new StringBuilder(str.length() + 55);
    stringBuilder.append("[CustomCap: bitmapDescriptor=");
    stringBuilder.append(str);
    stringBuilder.append(" refWidth=");
    stringBuilder.append(f);
    stringBuilder.append("]");
    return stringBuilder.toString();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/model/CustomCap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */