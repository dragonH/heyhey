package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;

public final class zzdpg<K, V> extends zzdos<K, V> {
  private Comparator<K> zzlse;
  
  private zzdpb<K, V> zzlsv;
  
  private zzdpg(zzdpb<K, V> paramzzdpb, Comparator<K> paramComparator) {
    this.zzlsv = paramzzdpb;
    this.zzlse = paramComparator;
  }
  
  public static <A, B> zzdpg<A, B> zzb(Map<A, B> paramMap, Comparator<A> paramComparator) {
    return zzdpi.zzc(new ArrayList<A>(paramMap.keySet()), paramMap, zzdot.zzbqg(), paramComparator);
  }
  
  private final zzdpb<K, V> zzbh(K paramK) {
    for (zzdpb<K, V> zzdpb1 = this.zzlsv; !zzdpb1.isEmpty(); zzdpb1 = zzdpb1.zzbqn()) {
      int i = this.zzlse.compare(paramK, zzdpb1.getKey());
      if (i < 0) {
        zzdpb1 = zzdpb1.zzbqm();
        continue;
      } 
      if (i == 0)
        return zzdpb1; 
    } 
    return null;
  }
  
  public final boolean containsKey(K paramK) {
    return (zzbh(paramK) != null);
  }
  
  public final V get(K paramK) {
    zzdpb<K, V> zzdpb1 = zzbh(paramK);
    return (zzdpb1 != null) ? zzdpb1.getValue() : null;
  }
  
  public final Comparator<K> getComparator() {
    return this.zzlse;
  }
  
  public final int indexOf(K paramK) {
    zzdpb<K, V> zzdpb1 = this.zzlsv;
    int i = 0;
    while (!zzdpb1.isEmpty()) {
      int j = this.zzlse.compare(paramK, zzdpb1.getKey());
      if (j == 0)
        return i + zzdpb1.zzbqm().size(); 
      if (j < 0) {
        zzdpb1 = zzdpb1.zzbqm();
        continue;
      } 
      i += zzdpb1.zzbqm().size() + 1;
      zzdpb1 = zzdpb1.zzbqn();
    } 
    return -1;
  }
  
  public final boolean isEmpty() {
    return this.zzlsv.isEmpty();
  }
  
  public final Iterator<Map.Entry<K, V>> iterator() {
    return new zzdow<K, V>(this.zzlsv, null, this.zzlse, false);
  }
  
  public final int size() {
    return this.zzlsv.size();
  }
  
  public final void zza(zzdpd<K, V> paramzzdpd) {
    this.zzlsv.zza(paramzzdpd);
  }
  
  public final zzdos<K, V> zzay(K paramK) {
    return !super.containsKey(paramK) ? this : new zzdpg(this.zzlsv.zza(paramK, this.zzlse).zza(null, null, zzdpc.zzlsq, null, null), this.zzlse);
  }
  
  public final Iterator<Map.Entry<K, V>> zzaz(K paramK) {
    return new zzdow<K, V>(this.zzlsv, paramK, this.zzlse, false);
  }
  
  public final K zzba(K paramK) {
    zzdpb<K, V> zzdpb1;
    zzdpb<K, V> zzdpb2 = this.zzlsv;
    zzdpb<K, V> zzdpb3 = null;
    while (!zzdpb2.isEmpty()) {
      int i = this.zzlse.compare(paramK, zzdpb2.getKey());
      if (i == 0) {
        if (!zzdpb2.zzbqm().isEmpty()) {
          for (zzdpb1 = zzdpb2.zzbqm(); !zzdpb1.zzbqn().isEmpty(); zzdpb1 = zzdpb1.zzbqn());
          return zzdpb1.getKey();
        } 
        return (K)((zzdpb3 != null) ? zzdpb3.getKey() : null);
      } 
      if (i < 0) {
        zzdpb2 = zzdpb2.zzbqm();
        continue;
      } 
      zzdpb<K, V> zzdpb4 = zzdpb2.zzbqn();
      zzdpb3 = zzdpb2;
      zzdpb2 = zzdpb4;
    } 
    String str = String.valueOf(zzdpb1);
    StringBuilder stringBuilder = new StringBuilder(str.length() + 50);
    stringBuilder.append("Couldn't find predecessor key of non-present key: ");
    stringBuilder.append(str);
    IllegalArgumentException illegalArgumentException = new IllegalArgumentException(stringBuilder.toString());
    throw illegalArgumentException;
  }
  
  public final K zzbqd() {
    return (K)this.zzlsv.zzbqo().getKey();
  }
  
  public final K zzbqe() {
    return (K)this.zzlsv.zzbqp().getKey();
  }
  
  public final Iterator<Map.Entry<K, V>> zzbqf() {
    return new zzdow<K, V>(this.zzlsv, null, this.zzlse, true);
  }
  
  public final zzdos<K, V> zzg(K paramK, V paramV) {
    return new zzdpg(this.zzlsv.zza(paramK, paramV, this.zzlse).zza(null, null, zzdpc.zzlsq, null, null), this.zzlse);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzdpg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */