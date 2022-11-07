package com.google.android.gms.maps.model;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.util.zzn;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import java.io.IOException;
import java.io.InputStream;

public final class MapStyleOptions extends zzbck {
  public static final Parcelable.Creator<MapStyleOptions> CREATOR;
  
  private static final String TAG = MapStyleOptions.class.getSimpleName();
  
  private String zzijg;
  
  static {
    CREATOR = new zzg();
  }
  
  public MapStyleOptions(String paramString) {
    this.zzijg = paramString;
  }
  
  public static MapStyleOptions loadRawResourceStyle(Context paramContext, int paramInt) throws Resources.NotFoundException {
    InputStream inputStream = paramContext.getResources().openRawResource(paramInt);
    try {
      byte[] arrayOfByte = zzn.zza(inputStream, true);
      String str = new String();
      this(arrayOfByte, "UTF-8");
      return new MapStyleOptions(str);
    } catch (IOException iOException) {
      String str = String.valueOf(iOException);
      StringBuilder stringBuilder = new StringBuilder(str.length() + 37);
      stringBuilder.append("Failed to read resource ");
      stringBuilder.append(paramInt);
      stringBuilder.append(": ");
      stringBuilder.append(str);
      throw new Resources.NotFoundException(stringBuilder.toString());
    } 
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = zzbcn.zze(paramParcel);
    zzbcn.zza(paramParcel, 2, this.zzijg, false);
    zzbcn.zzai(paramParcel, paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/model/MapStyleOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */