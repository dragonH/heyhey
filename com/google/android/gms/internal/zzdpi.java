package com.google.android.gms.internal;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

final class zzdpi<A, B, C> {
  private final Map<B, C> values;
  
  private final List<A> zzlsw;
  
  private final zzdov<A, B> zzlsx;
  
  private zzdpf<A, C> zzlsy;
  
  private zzdpf<A, C> zzlsz;
  
  private zzdpi(List<A> paramList, Map<B, C> paramMap, zzdov<A, B> paramzzdov) {
    this.zzlsw = paramList;
    this.values = paramMap;
    this.zzlsx = paramzzdov;
  }
  
  private final C zzbi(A paramA) {
    return this.values.get(this.zzlsx.zzbd(paramA));
  }
  
  public static <A, B, C> zzdpg<A, C> zzc(List<A> paramList, Map<B, C> paramMap, zzdov<A, B> paramzzdov, Comparator<A> paramComparator) {
    zzdpa<?, ?> zzdpa;
    zzdpi<A, B, C> zzdpi1 = new zzdpi<A, B, C>(paramList, paramMap, paramzzdov);
    Collections.sort(paramList, paramComparator);
    Iterator<zzdpl> iterator = (new zzdpj(paramList.size())).iterator();
    int i = paramList.size();
    while (iterator.hasNext()) {
      zzdpl zzdpl = iterator.next();
      int j = zzdpl.zzltd;
      i -= j;
      if (zzdpl.zzltc) {
        zzdpi1.zze(zzdpc.zzlsq, j, i);
        continue;
      } 
      zzdpi1.zze(zzdpc.zzlsq, j, i);
      j = zzdpl.zzltd;
      i -= j;
      zzdpi1.zze(zzdpc.zzlsp, j, i);
    } 
    zzdpf<A, C> zzdpf2 = zzdpi1.zzlsy;
    zzdpf<A, C> zzdpf1 = zzdpf2;
    if (zzdpf2 == null)
      zzdpa = zzdpa.zzbql(); 
    return new zzdpg<A, C>(zzdpa, paramComparator, null);
  }
  
  private final void zze(int paramInt1, int paramInt2, int paramInt3) {
    zzdpb<A, C> zzdpb = zzr(paramInt3 + 1, paramInt2 - 1);
    A a = this.zzlsw.get(paramInt3);
    if (paramInt1 == zzdpc.zzlsp) {
      zzdpb = new zzdpe<A, C>(a, zzbi(a), null, zzdpb);
    } else {
      zzdpb = new zzdoz<A, C>(a, zzbi(a), null, zzdpb);
    } 
    if (this.zzlsy == null) {
      this.zzlsy = (zzdpf<A, C>)zzdpb;
    } else {
      this.zzlsz.zza(zzdpb);
    } 
    this.zzlsz = (zzdpf<A, C>)zzdpb;
  }
  
  private final zzdpb<A, C> zzr(int paramInt1, int paramInt2) {
    if (paramInt2 == 0)
      return zzdpa.zzbql(); 
    if (paramInt2 == 1) {
      A a1 = this.zzlsw.get(paramInt1);
      return new zzdoz<A, C>(a1, zzbi(a1), null, null);
    } 
    int i = paramInt2 / 2;
    paramInt2 = paramInt1 + i;
    zzdpb<A, C> zzdpb1 = zzr(paramInt1, i);
    zzdpb<A, C> zzdpb2 = zzr(paramInt2 + 1, i);
    A a = this.zzlsw.get(paramInt2);
    return new zzdoz<A, C>(a, zzbi(a), zzdpb1, zzdpb2);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzdpi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */