package com.google.android.gms.internal;

public final class zzdpe<K, V> extends zzdpf<K, V> {
  zzdpe(K paramK, V paramV) {
    super(paramK, paramV, zzdpa.zzbql(), zzdpa.zzbql());
  }
  
  zzdpe(K paramK, V paramV, zzdpb<K, V> paramzzdpb1, zzdpb<K, V> paramzzdpb2) {
    super(paramK, paramV, paramzzdpb1, paramzzdpb2);
  }
  
  public final int size() {
    return zzbqm().size() + 1 + zzbqn().size();
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
    return new zzdpe(k, v, zzdpb1, paramzzdpb1);
  }
  
  protected final int zzbqj() {
    return zzdpc.zzlsp;
  }
  
  public final boolean zzbqk() {
    return true;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzdpe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */