package com.google.android.gms.location.places.internal;

import android.support.annotation.Nullable;
import android.text.style.CharacterStyle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.location.places.AutocompletePrediction;
import java.util.Collections;
import java.util.List;

public final class zzd extends zzav implements AutocompletePrediction {
  public zzd(DataHolder paramDataHolder, int paramInt) {
    super(paramDataHolder, paramInt);
  }
  
  private final String zzasz() {
    return zzae("ap_description", "");
  }
  
  private final String zzata() {
    return zzae("ap_primary_text", "");
  }
  
  private final String zzatb() {
    return zzae("ap_secondary_text", "");
  }
  
  private final List<zzb> zzatc() {
    return zza("ap_matched_subscriptions", zzb.CREATOR, Collections.emptyList());
  }
  
  private final List<zzb> zzatd() {
    return zza("ap_primary_text_matched", zzb.CREATOR, Collections.emptyList());
  }
  
  private final List<zzb> zzate() {
    return zza("ap_secondary_text_matched", zzb.CREATOR, Collections.emptyList());
  }
  
  public final CharSequence getFullText(@Nullable CharacterStyle paramCharacterStyle) {
    return zzg.zza(zzasz(), zzatc(), paramCharacterStyle);
  }
  
  public final String getPlaceId() {
    return zzae("ap_place_id", null);
  }
  
  public final List<Integer> getPlaceTypes() {
    return zza("ap_place_types", Collections.emptyList());
  }
  
  public final CharSequence getPrimaryText(@Nullable CharacterStyle paramCharacterStyle) {
    return zzg.zza(zzata(), zzatd(), paramCharacterStyle);
  }
  
  public final CharSequence getSecondaryText(@Nullable CharacterStyle paramCharacterStyle) {
    return zzg.zza(zzatb(), zzate(), paramCharacterStyle);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/internal/zzd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */