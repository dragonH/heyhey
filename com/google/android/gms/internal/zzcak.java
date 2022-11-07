package com.google.android.gms.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzd;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.location.places.PlacesStatusCodes;

@Deprecated
public final class zzcak extends zzd<zzcaj> implements Result {
  private final Status mStatus;
  
  public zzcak(DataHolder paramDataHolder) {
    this(paramDataHolder, PlacesStatusCodes.zzcl(paramDataHolder.getStatusCode()));
  }
  
  private zzcak(DataHolder paramDataHolder, Status paramStatus) {
    super(paramDataHolder, zzcaj.CREATOR);
    boolean bool;
    if (paramDataHolder == null || paramDataHolder.getStatusCode() == paramStatus.getStatusCode()) {
      bool = true;
    } else {
      bool = false;
    } 
    zzbp.zzbh(bool);
    this.mStatus = paramStatus;
  }
  
  public final Status getStatus() {
    return this.mStatus;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzcak.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */