package com.google.android.gms.location.places;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.internal.zzbf;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;

public class PlacePhotoResult extends zzbck implements Result {
  public static final Parcelable.Creator<PlacePhotoResult> CREATOR = new zzk();
  
  private final Bitmap mBitmap;
  
  private final Status mStatus;
  
  private BitmapTeleporter zzibi;
  
  public PlacePhotoResult(Status paramStatus, BitmapTeleporter paramBitmapTeleporter) {
    this.mStatus = paramStatus;
    this.zzibi = paramBitmapTeleporter;
    if (paramBitmapTeleporter != null) {
      Bitmap bitmap = paramBitmapTeleporter.zzais();
    } else {
      paramStatus = null;
    } 
    this.mBitmap = (Bitmap)paramStatus;
  }
  
  public Bitmap getBitmap() {
    return this.mBitmap;
  }
  
  public Status getStatus() {
    return this.mStatus;
  }
  
  public String toString() {
    return zzbf.zzt(this).zzg("status", this.mStatus).zzg("bitmap", this.mBitmap).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = zzbcn.zze(paramParcel);
    zzbcn.zza(paramParcel, 1, (Parcelable)getStatus(), paramInt, false);
    zzbcn.zza(paramParcel, 2, (Parcelable)this.zzibi, paramInt, false);
    zzbcn.zzai(paramParcel, i);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/PlacePhotoResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */