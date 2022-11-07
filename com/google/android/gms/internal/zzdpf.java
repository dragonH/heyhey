package com.google.android.gms.internal;

import java.util.Comparator;

public abstract class zzdpf<K, V> implements zzdpb<K, V> {
  private final V value;
  
  private final K zzlss;
  
  private zzdpb<K, V> zzlst;
  
  private final zzdpb<K, V> zzlsu;
  
  zzdpf(K paramK, V paramV, zzdpb<K, V> paramzzdpb1, zzdpb<K, V> paramzzdpb2) {
    this.zzlss = paramK;
    this.value = paramV;
    zzdpb<K, V> zzdpb1 = paramzzdpb1;
    if (paramzzdpb1 == null)
      zzdpb1 = zzdpa.zzbql(); 
    this.zzlst = zzdpb1;
    zzdpb1 = paramzzdpb2;
    if (paramzzdpb2 == null)
      zzdpb1 = zzdpa.zzbql(); 
    this.zzlsu = zzdpb1;
  }
  
  private static int zzb(zzdpb paramzzdpb) {
    return paramzzdpb.zzbqk() ? zzdpc.zzlsq : zzdpc.zzlsp;
  }
  
  private final zzdpf<K, V> zzb(K paramK, V paramV, Integer paramInteger, zzdpb<K, V> paramzzdpb1, zzdpb<K, V> paramzzdpb2) {
    K k = this.zzlss;
    V v = this.value;
    zzdpb<K, V> zzdpb1 = paramzzdpb1;
    if (paramzzdpb1 == null)
      zzdpb1 = this.zzlst; 
    zzdpb<K, V> zzdpb2 = paramzzdpb2;
    if (paramzzdpb2 == null)
      zzdpb2 = this.zzlsu; 
    return (zzdpf<K, V>)((paramInteger == zzdpc.zzlsp) ? new zzdpe<K, V>(k, v, zzdpb1, zzdpb2) : new zzdoz<K, V>(k, v, zzdpb1, zzdpb2));
  }
  
  private final zzdpb<K, V> zzbqq() {
    zzdpf<K, V> zzdpf1;
    if (this.zzlst.isEmpty())
      return zzdpa.zzbql(); 
    if (!this.zzlst.zzbqk() && !this.zzlst.zzbqm().zzbqk()) {
      zzdpf1 = zzbqr();
    } else {
      zzdpf1 = this;
    } 
    return zzdpf1.zza(null, null, ((zzdpf<K, V>)zzdpf1.zzlst).zzbqq(), null).zzbqs();
  }
  
  private final zzdpf<K, V> zzbqr() {
    zzdpf<K, V> zzdpf1 = zzbqv();
    zzdpf<K, V> zzdpf2 = zzdpf1;
    if (zzdpf1.zzlsu.zzbqm().zzbqk())
      zzdpf2 = zzdpf1.zza(null, null, null, ((zzdpf<K, V>)zzdpf1.zzlsu).zzbqu()).zzbqt().zzbqv(); 
    return zzdpf2;
  }
  
  private final zzdpf<K, V> zzbqs() {
    if (this.zzlsu.zzbqk() && !this.zzlst.zzbqk()) {
      zzdpf1 = zzbqt();
    } else {
      zzdpf1 = this;
    } 
    zzdpf<K, V> zzdpf2 = zzdpf1;
    if (zzdpf1.zzlst.zzbqk()) {
      zzdpf2 = zzdpf1;
      if (((zzdpf)zzdpf1.zzlst).zzlst.zzbqk())
        zzdpf2 = zzdpf1.zzbqu(); 
    } 
    zzdpf<K, V> zzdpf1 = zzdpf2;
    if (zzdpf2.zzlst.zzbqk()) {
      zzdpf1 = zzdpf2;
      if (zzdpf2.zzlsu.zzbqk())
        zzdpf1 = zzdpf2.zzbqv(); 
    } 
    return zzdpf1;
  }
  
  private final zzdpf<K, V> zzbqt() {
    zzdpf<K, V> zzdpf1 = zzb(null, null, zzdpc.zzlsp, null, ((zzdpf)this.zzlsu).zzlst);
    return (zzdpf<K, V>)this.zzlsu.zza(null, null, zzbqj(), zzdpf1, null);
  }
  
  private final zzdpf<K, V> zzbqu() {
    zzdpf<K, V> zzdpf1 = zzb(null, null, zzdpc.zzlsp, ((zzdpf)this.zzlst).zzlsu, null);
    return (zzdpf<K, V>)this.zzlst.zza(null, null, zzbqj(), null, zzdpf1);
  }
  
  private final zzdpf<K, V> zzbqv() {
    zzdpb<K, V> zzdpb1 = this.zzlst;
    zzdpb1 = zzdpb1.zza(null, null, zzb(zzdpb1), null, null);
    zzdpb<K, V> zzdpb2 = this.zzlsu;
    zzdpb2 = zzdpb2.zza(null, null, zzb(zzdpb2), null, null);
    return zzb(null, null, zzb(this), zzdpb1, zzdpb2);
  }
  
  public final K getKey() {
    return this.zzlss;
  }
  
  public final V getValue() {
    return this.value;
  }
  
  public final boolean isEmpty() {
    return false;
  }
  
  public final zzdpb<K, V> zza(K paramK, V paramV, Comparator<K> paramComparator) {
    zzdpf<K, V> zzdpf1;
    int i = paramComparator.compare(paramK, this.zzlss);
    if (i < 0) {
      zzdpf1 = zza(null, null, this.zzlst.zza(paramK, paramV, paramComparator), null);
    } else if (i == 0) {
      zzdpf1 = zza((K)zzdpf1, paramV, null, null);
    } else {
      zzdpf1 = zza(null, null, null, this.zzlsu.zza((K)zzdpf1, paramV, paramComparator));
    } 
    return zzdpf1.zzbqs();
  }
  
  public final zzdpb<K, V> zza(K paramK, Comparator<K> paramComparator) {
    zzdpf<K, V> zzdpf1;
    if (paramComparator.compare(paramK, this.zzlss) < 0) {
      zzdpf zzdpf2;
      if (!this.zzlst.isEmpty() && !this.zzlst.zzbqk() && !((zzdpf)this.zzlst).zzlst.zzbqk()) {
        zzdpf2 = zzbqr();
      } else {
        zzdpf2 = this;
      } 
      zzdpf1 = zzdpf2.zza(null, null, zzdpf2.zzlst.zza(paramK, paramComparator), null);
    } else {
      if (this.zzlst.zzbqk()) {
        zzdpb1 = zzbqu();
      } else {
        zzdpb1 = this;
      } 
      zzdpf<K, V> zzdpf2 = (zzdpf<K, V>)zzdpb1;
      if (!((zzdpf)zzdpb1).zzlsu.isEmpty()) {
        zzdpf2 = (zzdpf<K, V>)zzdpb1;
        if (!((zzdpf)zzdpb1).zzlsu.zzbqk()) {
          zzdpf2 = (zzdpf<K, V>)zzdpb1;
          if (!((zzdpf)((zzdpf)zzdpb1).zzlsu).zzlst.zzbqk()) {
            zzdpb1 = zzdpb1.zzbqv();
            zzdpf2 = (zzdpf<K, V>)zzdpb1;
            if (((zzdpf)zzdpb1).zzlst.zzbqm().zzbqk())
              zzdpf2 = zzdpb1.zzbqu().zzbqv(); 
          } 
        } 
      } 
      zzdpb<K, V> zzdpb1 = zzdpf2;
      if (paramComparator.compare((K)zzdpf1, zzdpf2.zzlss) == 0) {
        if (zzdpf2.zzlsu.isEmpty())
          return zzdpa.zzbql(); 
        zzdpb1 = zzdpf2.zzlsu.zzbqo();
        zzdpb1 = zzdpf2.zza(zzdpb1.getKey(), zzdpb1.getValue(), null, ((zzdpf<K, V>)zzdpf2.zzlsu).zzbqq());
      } 
      zzdpf1 = zzdpb1.zza(null, null, null, ((zzdpf)zzdpb1).zzlsu.zza((K)zzdpf1, paramComparator));
    } 
    return zzdpf1.zzbqs();
  }
  
  protected abstract zzdpf<K, V> zza(K paramK, V paramV, zzdpb<K, V> paramzzdpb1, zzdpb<K, V> paramzzdpb2);
  
  void zza(zzdpb<K, V> paramzzdpb) {
    this.zzlst = paramzzdpb;
  }
  
  public final void zza(zzdpd<K, V> paramzzdpd) {
    this.zzlst.zza(paramzzdpd);
    paramzzdpd.zzh(this.zzlss, this.value);
    this.zzlsu.zza(paramzzdpd);
  }
  
  protected abstract int zzbqj();
  
  public final zzdpb<K, V> zzbqm() {
    return this.zzlst;
  }
  
  public final zzdpb<K, V> zzbqn() {
    return this.zzlsu;
  }
  
  public final zzdpb<K, V> zzbqo() {
    return this.zzlst.isEmpty() ? this : this.zzlst.zzbqo();
  }
  
  public final zzdpb<K, V> zzbqp() {
    return this.zzlsu.isEmpty() ? this : this.zzlsu.zzbqp();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzdpf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */