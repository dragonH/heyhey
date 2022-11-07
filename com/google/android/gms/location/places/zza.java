package com.google.android.gms.location.places;

import com.google.android.gms.internal.zzbck;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class zza extends zzbck {
  static <E> Set<E> zzaa(List<E> paramList) {
    return (paramList == null || paramList.isEmpty()) ? Collections.emptySet() : Collections.unmodifiableSet(new HashSet<E>(paramList));
  }
  
  static <E> List<E> zzi(Collection<E> paramCollection) {
    return (paramCollection == null || paramCollection.isEmpty()) ? Collections.emptyList() : new ArrayList<E>(paramCollection);
  }
  
  public abstract Set<String> getPlaceIds();
  
  public boolean isRestrictedToPlacesOpenNow() {
    return false;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */