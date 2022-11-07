package com.google.android.gms.internal;

import java.io.IOException;

public final class zzewh<K, V> {
  private final V value;
  
  private final K zzlss;
  
  private final zzewj<K, V> zzopl;
  
  private zzewh(zzexu paramzzexu1, K paramK, zzexu paramzzexu2, V paramV) {
    this.zzopl = new zzewj<K, V>(paramzzexu1, paramK, paramzzexu2, paramV);
    this.zzlss = paramK;
    this.value = paramV;
  }
  
  private static <K, V> int zza(zzewj<K, V> paramzzewj, K paramK, V paramV) {
    return zzeve.zza(paramzzewj.zzopm, 1, paramK) + zzeve.zza(paramzzewj.zzopo, 2, paramV);
  }
  
  public static <K, V> zzewh<K, V> zza(zzexu paramzzexu1, K paramK, zzexu paramzzexu2, V paramV) {
    return new zzewh<K, V>(paramzzexu1, paramK, paramzzexu2, paramV);
  }
  
  private static <T> T zza(zzeut paramzzeut, zzevd paramzzevd, zzexu paramzzexu, T paramT) throws IOException {
    int i = zzewi.zzood[paramzzexu.ordinal()];
    if (i != 1) {
      if (i != 2) {
        if (i != 3)
          return (T)zzeve.zza(paramzzeut, paramzzexu, true); 
        throw new RuntimeException("Groups are not allowed in maps.");
      } 
      return (T)Integer.valueOf(paramzzeut.zzcsx());
    } 
    zzewm zzewm = ((zzewl)paramT).zzcub();
    paramzzeut.zza(zzewm, paramzzevd);
    return (T)zzewm.zzcug();
  }
  
  public final void zza(zzeuy paramzzeuy, int paramInt, K paramK, V paramV) throws IOException {
    paramzzeuy.zzw(paramInt, 2);
    paramzzeuy.zzjy(zza(this.zzopl, paramK, paramV));
    zzewj<K, V> zzewj1 = this.zzopl;
    zzeve.zza(paramzzeuy, zzewj1.zzopm, 1, paramK);
    zzeve.zza(paramzzeuy, zzewj1.zzopo, 2, paramV);
  }
  
  public final void zza(zzewk<K, V> paramzzewk, zzeut paramzzeut, zzevd paramzzevd) throws IOException {
    int i = paramzzeut.zzjn(paramzzeut.zzctc());
    zzewj<K, V> zzewj1 = this.zzopl;
    K k = zzewj1.zzopn;
    V v = zzewj1.zzopp;
    while (true) {
      int j = paramzzeut.zzcsn();
      if (j != 0) {
        if (j == (this.zzopl.zzopm.zzcvy() | 0x8)) {
          k = zza(paramzzeut, paramzzevd, this.zzopl.zzopm, k);
          continue;
        } 
        if (j == (this.zzopl.zzopo.zzcvy() | 0x10)) {
          v = zza(paramzzeut, paramzzevd, this.zzopl.zzopo, v);
          continue;
        } 
        if (!paramzzeut.zzjl(j))
          break; 
        continue;
      } 
      break;
    } 
    paramzzeut.zzjk(0);
    paramzzeut.zzjo(i);
    paramzzewk.put(k, v);
  }
  
  public final int zzb(int paramInt, K paramK, V paramV) {
    return zzeuy.zzkb(paramInt) + zzeuy.zzki(zza(this.zzopl, paramK, paramV));
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzewh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */