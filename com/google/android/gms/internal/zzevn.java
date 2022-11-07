package com.google.android.gms.internal;

final class zzevn implements zzevq {
  int zzoom = 0;
  
  public final double zza(boolean paramBoolean1, double paramDouble1, boolean paramBoolean2, double paramDouble2) {
    this.zzoom = this.zzoom * 53 + zzevu.zzdc(Double.doubleToLongBits(paramDouble1));
    return paramDouble1;
  }
  
  public final int zza(boolean paramBoolean1, int paramInt1, boolean paramBoolean2, int paramInt2) {
    this.zzoom = this.zzoom * 53 + paramInt1;
    return paramInt1;
  }
  
  public final long zza(boolean paramBoolean1, long paramLong1, boolean paramBoolean2, long paramLong2) {
    this.zzoom = this.zzoom * 53 + zzevu.zzdc(paramLong1);
    return paramLong1;
  }
  
  public final zzeuk zza(boolean paramBoolean1, zzeuk paramzzeuk1, boolean paramBoolean2, zzeuk paramzzeuk2) {
    this.zzoom = this.zzoom * 53 + paramzzeuk1.hashCode();
    return paramzzeuk1;
  }
  
  public final zzevx zza(zzevx paramzzevx1, zzevx paramzzevx2) {
    this.zzoom = this.zzoom * 53 + paramzzevx1.hashCode();
    return paramzzevx1;
  }
  
  public final <T> zzevy<T> zza(zzevy<T> paramzzevy1, zzevy<T> paramzzevy2) {
    this.zzoom = this.zzoom * 53 + paramzzevy1.hashCode();
    return paramzzevy1;
  }
  
  public final <K, V> zzewk<K, V> zza(zzewk<K, V> paramzzewk1, zzewk<K, V> paramzzewk2) {
    this.zzoom = this.zzoom * 53 + paramzzewk1.hashCode();
    return paramzzewk1;
  }
  
  public final <T extends zzewl> T zza(T paramT1, T paramT2) {
    byte b;
    if (paramT1 != null) {
      if (paramT1 instanceof zzevh) {
        zzevh zzevh = (zzevh)paramT1;
        if (zzevh.zzomr == 0) {
          int i = this.zzoom;
          this.zzoom = 0;
          zzevh.zza(zzevp.zzoop, this, zzevh);
          zzexl zzexl = zzevh.zzooe;
          zzevh.zzooe = super.zza(zzexl, zzexl);
          zzevh.zzomr = this.zzoom;
          this.zzoom = i;
        } 
        b = zzevh.zzomr;
      } else {
        b = paramT1.hashCode();
      } 
    } else {
      b = 37;
    } 
    this.zzoom = this.zzoom * 53 + b;
    return paramT1;
  }
  
  public final zzexl zza(zzexl paramzzexl1, zzexl paramzzexl2) {
    this.zzoom = this.zzoom * 53 + paramzzexl1.hashCode();
    return paramzzexl1;
  }
  
  public final Object zza(boolean paramBoolean, Object paramObject1, Object paramObject2) {
    this.zzoom = this.zzoom * 53 + zzevu.zzda(((Boolean)paramObject1).booleanValue());
    return paramObject1;
  }
  
  public final String zza(boolean paramBoolean1, String paramString1, boolean paramBoolean2, String paramString2) {
    this.zzoom = this.zzoom * 53 + paramString1.hashCode();
    return paramString1;
  }
  
  public final boolean zza(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4) {
    this.zzoom = this.zzoom * 53 + zzevu.zzda(paramBoolean2);
    return paramBoolean2;
  }
  
  public final Object zzb(boolean paramBoolean, Object paramObject1, Object paramObject2) {
    this.zzoom = this.zzoom * 53 + ((Integer)paramObject1).intValue();
    return paramObject1;
  }
  
  public final Object zzc(boolean paramBoolean, Object paramObject1, Object paramObject2) {
    this.zzoom = this.zzoom * 53 + zzevu.zzdc(Double.doubleToLongBits(((Double)paramObject1).doubleValue()));
    return paramObject1;
  }
  
  public final void zzcz(boolean paramBoolean) {
    if (!paramBoolean)
      return; 
    throw new IllegalStateException();
  }
  
  public final Object zzd(boolean paramBoolean, Object paramObject1, Object paramObject2) {
    this.zzoom = this.zzoom * 53 + zzevu.zzdc(((Long)paramObject1).longValue());
    return paramObject1;
  }
  
  public final Object zze(boolean paramBoolean, Object paramObject1, Object paramObject2) {
    this.zzoom = this.zzoom * 53 + paramObject1.hashCode();
    return paramObject1;
  }
  
  public final Object zzf(boolean paramBoolean, Object paramObject1, Object paramObject2) {
    this.zzoom = this.zzoom * 53 + paramObject1.hashCode();
    return paramObject1;
  }
  
  public final Object zzg(boolean paramBoolean, Object paramObject1, Object paramObject2) {
    return zza((zzewl)paramObject1, (zzewl)paramObject2);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzevn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */