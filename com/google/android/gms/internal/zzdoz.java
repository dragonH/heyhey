package com.google.android.gms.internal;

public final class zzdoz<K, V> extends zzdpf<K, V> {
  private int size = -1;
  
  zzdoz(K paramK, V paramV, zzdpb<K, V> paramzzdpb1, zzdpb<K, V> paramzzdpb2) {
    super(paramK, paramV, paramzzdpb1, paramzzdpb2);
  }
  
  public final int size() {
    if (this.size == -1)
      this.size = zzbqm().size() + 1 + zzbqn().size(); 
    return this.size;
  }
  
  protected final zzdpf<K, V> zza(K paramK, V paramV, zzdpb<K, V> paramzzdpb1, zzdpb<K, V> paramzzdpb2) {
    K k = paramK;
    if (paramK == null)
      k = getKey(); 
    V v = paramV;
    if (paramV == null)
      v = getValue(); 
    zzdpb<K, V> zzdpb1 = paramzzdpb1;
    if (paramzzdpb1 == null)
      zzdpb1 = zzbqm(); 
    paramzzdpb1 = paramzzdpb2;
    if (paramzzdpb2 == null)
      paramzzdpb1 = zzbqn(); 
    return new zzdoz(k, v, zzdpb1, paramzzdpb1);
  }
  
  final void zza(zzdpb<K, V> paramzzdpb) {
    if (this.size == -1) {
      super.zza(paramzzdpb);
      return;
    } 
    throw new IllegalStateException("Can't set left after using size");
  }
  
  protected final int zzbqj() {
    return zzdpc.zzlsq;
  }
  
  public final boolean zzbqk() {
    return false;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzdoz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */