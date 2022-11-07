package com.google.android.gms.maps.internal;

import android.os.Bundle;
import android.os.Parcelable;

public final class zzby {
  public static void zza(Bundle paramBundle, String paramString, Parcelable paramParcelable) {
    paramBundle.setClassLoader(zzby.class.getClassLoader());
    Bundle bundle1 = paramBundle.getBundle("map_state");
    Bundle bundle2 = bundle1;
    if (bundle1 == null)
      bundle2 = new Bundle(); 
    bundle2.setClassLoader(zzby.class.getClassLoader());
    bundle2.putParcelable(paramString, paramParcelable);
    paramBundle.putBundle("map_state", bundle2);
  }
  
  public static void zzd(Bundle paramBundle1, Bundle paramBundle2) {
    if (paramBundle1 != null && paramBundle2 != null) {
      Parcelable parcelable = (Parcelable)zzg(paramBundle1, "MapOptions");
      if (parcelable != null)
        zza(paramBundle2, "MapOptions", parcelable); 
      parcelable = zzg(paramBundle1, "StreetViewPanoramaOptions");
      if (parcelable != null)
        zza(paramBundle2, "StreetViewPanoramaOptions", parcelable); 
      parcelable = zzg(paramBundle1, "camera");
      if (parcelable != null)
        zza(paramBundle2, "camera", parcelable); 
      if (paramBundle1.containsKey("position"))
        paramBundle2.putString("position", paramBundle1.getString("position")); 
      if (paramBundle1.containsKey("com.google.android.wearable.compat.extra.LOWBIT_AMBIENT"))
        paramBundle2.putBoolean("com.google.android.wearable.compat.extra.LOWBIT_AMBIENT", paramBundle1.getBoolean("com.google.android.wearable.compat.extra.LOWBIT_AMBIENT", false)); 
    } 
  }
  
  private static <T extends Parcelable> T zzg(Bundle paramBundle, String paramString) {
    if (paramBundle == null)
      return null; 
    paramBundle.setClassLoader(zzby.class.getClassLoader());
    paramBundle = paramBundle.getBundle("map_state");
    if (paramBundle == null)
      return null; 
    paramBundle.setClassLoader(zzby.class.getClassLoader());
    return (T)paramBundle.getParcelable(paramString);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/internal/zzby.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */