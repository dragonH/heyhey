package com.google.android.gms.internal;

final class zzevk implements zzevq {
  static final zzevk zzooj = new zzevk();
  
  private static zzevl zzook = new zzevl();
  
  public final double zza(boolean paramBoolean1, double paramDouble1, boolean paramBoolean2, double paramDouble2) {
    if (paramBoolean1 == paramBoolean2 && paramDouble1 == paramDouble2)
      return paramDouble1; 
    throw zzook;
  }
  
  public final int zza(boolean paramBoolean1, int paramInt1, boolean paramBoolean2, int paramInt2) {
    if (paramBoolean1 == paramBoolean2 && paramInt1 == paramInt2)
      return paramInt1; 
    throw zzook;
  }
  
  public final long zza(boolean paramBoolean1, long paramLong1, boolean paramBoolean2, long paramLong2) {
    if (paramBoolean1 == paramBoolean2 && paramLong1 == paramLong2)
      return paramLong1; 
    throw zzook;
  }
  
  public final zzeuk zza(boolean paramBoolean1, zzeuk paramzzeuk1, boolean paramBoolean2, zzeuk paramzzeuk2) {
    if (paramBoolean1 == paramBoolean2 && paramzzeuk1.equals(paramzzeuk2))
      return paramzzeuk1; 
    throw zzook;
  }
  
  public final zzevx zza(zzevx paramzzevx1, zzevx paramzzevx2) {
    if (paramzzevx1.equals(paramzzevx2))
      return paramzzevx1; 
    throw zzook;
  }
  
  public final <T> zzevy<T> zza(zzevy<T> paramzzevy1, zzevy<T> paramzzevy2) {
    if (paramzzevy1.equals(paramzzevy2))
      return paramzzevy1; 
    throw zzook;
  }
  
  public final <K, V> zzewk<K, V> zza(zzewk<K, V> paramzzewk1, zzewk<K, V> paramzzewk2) {
    if (paramzzewk1.equals(paramzzewk2))
      return paramzzewk1; 
    throw zzook;
  }
  
  public final <T extends zzewl> T zza(T paramT1, T paramT2) {
    if (paramT1 == null && paramT2 == null)
      return null; 
    if (paramT1 != null && paramT2 != null) {
      zzevh zzevh = (zzevh)paramT1;
      if (zzevh != paramT2 && ((zzevh)zzevh.zza(zzevp.zzoou, (Object)null, (Object)null)).getClass().isInstance(paramT2)) {
        zzevh zzevh1 = (zzevh)paramT2;
        zzevh.zza(zzevp.zzoop, this, zzevh1);
        zzevh.zzooe = super.zza(zzevh.zzooe, zzevh1.zzooe);
      } 
      return paramT1;
    } 
    throw zzook;
  }
  
  public final zzexl zza(zzexl paramzzexl1, zzexl paramzzexl2) {
    if (paramzzexl1.equals(paramzzexl2))
      return paramzzexl1; 
    throw zzook;
  }
  
  public final Object zza(boolean paramBoolean, Object paramObject1, Object paramObject2) {
    if (paramBoolean && paramObject1.equals(paramObject2))
      return paramObject1; 
    throw zzook;
  }
  
  public final String zza(boolean paramBoolean1, String paramString1, boolean paramBoolean2, String paramString2) {
    if (paramBoolean1 == paramBoolean2 && paramString1.equals(paramString2))
      return paramString1; 
    throw zzook;
  }
  
  public final boolean zza(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4) {
    if (paramBoolean1 == paramBoolean3 && paramBoolean2 == paramBoolean4)
      return paramBoolean2; 
    throw zzook;
  }
  
  public final Object zzb(boolean paramBoolean, Object paramObject1, Object paramObject2) {
    if (paramBoolean && paramObject1.equals(paramObject2))
      return paramObject1; 
    throw zzook;
  }
  
  public final Object zzc(boolean paramBoolean, Object paramObject1, Object paramObject2) {
    if (paramBoolean && paramObject1.equals(paramObject2))
      return paramObject1; 
    throw zzook;
  }
  
  public final void zzcz(boolean paramBoolean) {
    if (!paramBoolean)
      return; 
    throw zzook;
  }
  
  public final Object zzd(boolean paramBoolean, Object paramObject1, Object paramObject2) {
    if (paramBoolean && paramObject1.equals(paramObject2))
      return paramObject1; 
    throw zzook;
  }
  
  public final Object zze(boolean paramBoolean, Object paramObject1, Object paramObject2) {
    if (paramBoolean && paramObject1.equals(paramObject2))
      return paramObject1; 
    throw zzook;
  }
  
  public final Object zzf(boolean paramBoolean, Object paramObject1, Object paramObject2) {
    if (paramBoolean && paramObject1.equals(paramObject2))
      return paramObject1; 
    throw zzook;
  }
  
  public final Object zzg(boolean paramBoolean, Object paramObject1, Object paramObject2) {
    if (paramBoolean) {
      zzevh zzevh = (zzevh)paramObject1;
      paramObject2 = paramObject2;
      boolean bool = true;
      if (zzevh != paramObject2)
        if (!((zzevh)zzevh.zza(zzevp.zzoou, (Object)null, (Object)null)).getClass().isInstance(paramObject2)) {
          bool = false;
        } else {
          paramObject2 = paramObject2;
          zzevh.zza(zzevp.zzoop, this, paramObject2);
          zzevh.zzooe = super.zza(zzevh.zzooe, ((zzevh)paramObject2).zzooe);
        }  
      if (bool)
        return paramObject1; 
    } 
    throw zzook;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzevk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */