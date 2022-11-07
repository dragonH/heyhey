package com.google.android.gms.location.places.ui;

import android.content.Context;
import android.content.Intent;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.internal.zzbcp;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.internal.PlaceEntity;

class zza {
  public static final int RESULT_ERROR = 2;
  
  public static Place getPlace(Context paramContext, Intent paramIntent) {
    zzbp.zzb(paramIntent, "intent must not be null");
    zzbp.zzb(paramContext, "context must not be null");
    return (Place)zzbcp.zza(paramIntent, "selected_place", PlaceEntity.CREATOR);
  }
  
  public static Status getStatus(Context paramContext, Intent paramIntent) {
    zzbp.zzb(paramIntent, "intent must not be null");
    zzbp.zzb(paramContext, "context must not be null");
    return (Status)zzbcp.zza(paramIntent, "status", Status.CREATOR);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/ui/zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */