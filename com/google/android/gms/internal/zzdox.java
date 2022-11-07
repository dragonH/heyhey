package com.google.android.gms.internal;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public final class zzdox<T> implements Iterable<T> {
  private final zzdos<T, Void> zzlsm;
  
  private zzdox(zzdos<T, Void> paramzzdos) {
    this.zzlsm = paramzzdos;
  }
  
  public zzdox(List<T> paramList, Comparator<T> paramComparator) {
    this.zzlsm = zzdot.zzb(paramList, Collections.emptyMap(), zzdot.zzbqg(), paramComparator);
  }
  
  public final boolean contains(T paramT) {
    return this.zzlsm.containsKey(paramT);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof zzdox))
      return false; 
    paramObject = paramObject;
    return this.zzlsm.equals(((zzdox)paramObject).zzlsm);
  }
  
  public final int hashCode() {
    return this.zzlsm.hashCode();
  }
  
  public final int indexOf(T paramT) {
    return this.zzlsm.indexOf(paramT);
  }
  
  public final boolean isEmpty() {
    return this.zzlsm.isEmpty();
  }
  
  public final Iterator<T> iterator() {
    return new zzdoy<T>(this.zzlsm.iterator());
  }
  
  public final int size() {
    return this.zzlsm.size();
  }
  
  public final Iterator<T> zzaz(T paramT) {
    return new zzdoy<T>(this.zzlsm.zzaz(paramT));
  }
  
  public final zzdox<T> zzbe(T paramT) {
    zzdos<T, Void> zzdos1 = this.zzlsm.zzay(paramT);
    return (zzdos1 == this.zzlsm) ? this : new zzdox(zzdos1);
  }
  
  public final zzdox<T> zzbf(T paramT) {
    return new zzdox(this.zzlsm.zzg(paramT, null));
  }
  
  public final T zzbg(T paramT) {
    return this.zzlsm.zzba(paramT);
  }
  
  public final Iterator<T> zzbqf() {
    return new zzdoy<T>(this.zzlsm.zzbqf());
  }
  
  public final T zzbqh() {
    return this.zzlsm.zzbqd();
  }
  
  public final T zzbqi() {
    return this.zzlsm.zzbqe();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzdox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */