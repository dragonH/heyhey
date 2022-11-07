package com.google.android.gms.location;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import java.util.Collections;
import java.util.List;

public final class zzaa extends zzbck {
  public static final Parcelable.Creator<zzaa> CREATOR = new zzab();
  
  private final PendingIntent mPendingIntent;
  
  private final String mTag;
  
  private final List<String> zzhyx;
  
  zzaa(@Nullable List<String> paramList, @Nullable PendingIntent paramPendingIntent, String paramString) {
    if (paramList == null) {
      paramList = Collections.emptyList();
    } else {
      paramList = Collections.unmodifiableList(paramList);
    } 
    this.zzhyx = paramList;
    this.mPendingIntent = paramPendingIntent;
    this.mTag = paramString;
  }
  
  public static zzaa zzb(PendingIntent paramPendingIntent) {
    zzbp.zzb(paramPendingIntent, "PendingIntent can not be null.");
    return new zzaa(null, paramPendingIntent, "");
  }
  
  public static zzaa zzz(List<String> paramList) {
    zzbp.zzb(paramList, "geofence can't be null.");
    zzbp.zzb(paramList.isEmpty() ^ true, "Geofences must contains at least one id.");
    return new zzaa(paramList, null, "");
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = zzbcn.zze(paramParcel);
    zzbcn.zzb(paramParcel, 1, this.zzhyx, false);
    zzbcn.zza(paramParcel, 2, (Parcelable)this.mPendingIntent, paramInt, false);
    zzbcn.zza(paramParcel, 3, this.mTag, false);
    zzbcn.zzai(paramParcel, i);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/zzaa.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */