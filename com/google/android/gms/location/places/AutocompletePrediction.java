package com.google.android.gms.location.places;

import android.support.annotation.Nullable;
import android.text.style.CharacterStyle;
import com.google.android.gms.common.data.Freezable;
import java.util.List;

public interface AutocompletePrediction extends Freezable<AutocompletePrediction> {
  CharSequence getFullText(@Nullable CharacterStyle paramCharacterStyle);
  
  @Nullable
  String getPlaceId();
  
  @Nullable
  List<Integer> getPlaceTypes();
  
  CharSequence getPrimaryText(@Nullable CharacterStyle paramCharacterStyle);
  
  CharSequence getSecondaryText(@Nullable CharacterStyle paramCharacterStyle);
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/AutocompletePrediction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */