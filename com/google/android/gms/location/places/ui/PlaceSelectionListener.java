package com.google.android.gms.location.places.ui;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;

public interface PlaceSelectionListener {
  void onError(Status paramStatus);
  
  void onPlaceSelected(Place paramPlace);
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/ui/PlaceSelectionListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */