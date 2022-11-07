package com.google.android.gms.internal;

import java.util.Iterator;

public abstract class zzbdo extends zzbdl implements zzbco {
  public final int describeContents() {
    return 0;
  }
  
  public boolean equals(zzbdm<?, ?> paramObject) {
    if (this == paramObject)
      return true; 
    if (!getClass().isInstance(paramObject))
      return false; 
    zzbdl zzbdl1 = (zzbdl)paramObject;
    for (zzbdm<?, ?> paramObject : zzzz().values()) {
      if (zza(paramObject)) {
        if (!zzbdl1.zza(paramObject) || !zzb(paramObject).equals(zzbdl1.zzb(paramObject)))
          return false; 
        continue;
      } 
      if (zzbdl1.zza(paramObject))
        return false; 
    } 
    return true;
  }
  
  public int hashCode() {
    Iterator<zzbdm> iterator = zzzz().values().iterator();
    int i = 0;
    while (iterator.hasNext()) {
      zzbdm zzbdm = iterator.next();
      if (zza(zzbdm))
        i = i * 31 + zzb(zzbdm).hashCode(); 
    } 
    return i;
  }
  
  public Object zzgi(String paramString) {
    return null;
  }
  
  public boolean zzgj(String paramString) {
    return false;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzbdo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */