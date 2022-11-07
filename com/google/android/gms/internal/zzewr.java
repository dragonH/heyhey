package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.List;

final class zzewr<E> extends zzeug<E> {
  private static final zzewr<Object> zzopr;
  
  private final List<E> zzops;
  
  static {
    zzewr<Object> zzewr1 = new zzewr();
    zzopr = zzewr1;
    zzewr1.zzbhs();
  }
  
  zzewr() {
    this(new ArrayList<E>(10));
  }
  
  private zzewr(List<E> paramList) {
    this.zzops = paramList;
  }
  
  public static <E> zzewr<E> zzcva() {
    return (zzewr)zzopr;
  }
  
  public final void add(int paramInt, E paramE) {
    zzcsd();
    this.zzops.add(paramInt, paramE);
    this.modCount++;
  }
  
  public final E get(int paramInt) {
    return this.zzops.get(paramInt);
  }
  
  public final E remove(int paramInt) {
    zzcsd();
    E e = this.zzops.remove(paramInt);
    this.modCount++;
    return e;
  }
  
  public final E set(int paramInt, E paramE) {
    zzcsd();
    paramE = this.zzops.set(paramInt, paramE);
    this.modCount++;
    return paramE;
  }
  
  public final int size() {
    return this.zzops.size();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzewr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */