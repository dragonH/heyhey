package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzaa;
import com.google.android.gms.maps.model.IndoorBuilding;
import com.google.android.gms.maps.model.internal.zzj;

final class zza extends zzaa {
  zza(GoogleMap paramGoogleMap, GoogleMap.OnIndoorStateChangeListener paramOnIndoorStateChangeListener) {}
  
  public final void onIndoorBuildingFocused() {
    this.zzifc.onIndoorBuildingFocused();
  }
  
  public final void zza(zzj paramzzj) {
    this.zzifc.onIndoorLevelActivated(new IndoorBuilding(paramzzj));
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */