package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public final class zzdot {
  private static final zzdov zzlsj = new zzdou();
  
  public static <K, V> zzdos<K, V> zza(Comparator<K> paramComparator) {
    return new zzdoq<K, V>(paramComparator);
  }
  
  public static <A, B> zzdos<A, B> zza(Map<A, B> paramMap, Comparator<A> paramComparator) {
    return (zzdos<A, B>)((paramMap.size() < 25) ? zzdoq.zza(new ArrayList<A>(paramMap.keySet()), paramMap, zzlsj, paramComparator) : zzdpg.zzb(paramMap, paramComparator));
  }
  
  public static <A, B, C> zzdos<A, C> zzb(List<A> paramList, Map<B, C> paramMap, zzdov<A, B> paramzzdov, Comparator<A> paramComparator) {
    return (zzdos<A, C>)((paramList.size() < 25) ? zzdoq.zza(paramList, paramMap, paramzzdov, paramComparator) : zzdpi.zzc(paramList, paramMap, paramzzdov, paramComparator));
  }
  
  public static <A> zzdov<A, A> zzbqg() {
    return zzlsj;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzdot.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */