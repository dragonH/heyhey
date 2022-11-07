package com.google.android.gms.internal;

public final class zzevo implements zzevq {
  public static final zzevo zzoon = new zzevo();
  
  public final double zza(boolean paramBoolean1, double paramDouble1, boolean paramBoolean2, double paramDouble2) {
    return paramBoolean2 ? paramDouble2 : paramDouble1;
  }
  
  public final int zza(boolean paramBoolean1, int paramInt1, boolean paramBoolean2, int paramInt2) {
    return paramBoolean2 ? paramInt2 : paramInt1;
  }
  
  public final long zza(boolean paramBoolean1, long paramLong1, boolean paramBoolean2, long paramLong2) {
    return paramBoolean2 ? paramLong2 : paramLong1;
  }
  
  public final zzeuk zza(boolean paramBoolean1, zzeuk paramzzeuk1, boolean paramBoolean2, zzeuk paramzzeuk2) {
    return paramBoolean2 ? paramzzeuk2 : paramzzeuk1;
  }
  
  public final zzevx zza(zzevx paramzzevx1, zzevx paramzzevx2) {
    int i = paramzzevx1.size();
    int j = paramzzevx2.size();
    zzevx zzevx1 = paramzzevx1;
    if (i > 0) {
      zzevx1 = paramzzevx1;
      if (j > 0) {
        zzevx1 = paramzzevx1;
        if (!paramzzevx1.zzcsc())
          zzevx1 = paramzzevx1.zzko(j + i); 
        zzevx1.addAll(paramzzevx2);
      } 
    } 
    return (i > 0) ? zzevx1 : paramzzevx2;
  }
  
  public final <T> zzevy<T> zza(zzevy<T> paramzzevy1, zzevy<T> paramzzevy2) {
    int i = paramzzevy1.size();
    int j = paramzzevy2.size();
    zzevy<T> zzevy1 = paramzzevy1;
    if (i > 0) {
      zzevy1 = paramzzevy1;
      if (j > 0) {
        zzevy1 = paramzzevy1;
        if (!paramzzevy1.zzcsc())
          zzevy1 = paramzzevy1.zzks(j + i); 
        zzevy1.addAll(paramzzevy2);
      } 
    } 
    return (i > 0) ? zzevy1 : paramzzevy2;
  }
  
  public final <K, V> zzewk<K, V> zza(zzewk<K, V> paramzzewk1, zzewk<K, V> paramzzewk2) {
    zzewk<K, V> zzewk1 = paramzzewk1;
    if (!paramzzewk2.isEmpty()) {
      zzewk1 = paramzzewk1;
      if (!paramzzewk1.isMutable())
        zzewk1 = paramzzewk1.zzcuy(); 
      zzewk1.zza(paramzzewk2);
    } 
    return zzewk1;
  }
  
  public final <T extends zzewl> T zza(T paramT1, T paramT2) {
    return (T)((paramT1 != null && paramT2 != null) ? paramT1.zzcub().zzc((zzewl)paramT2).zzcuh() : (Object)((paramT1 != null) ? paramT1 : paramT2));
  }
  
  public final zzexl zza(zzexl paramzzexl1, zzexl paramzzexl2) {
    return (paramzzexl2 == zzexl.zzcvp()) ? paramzzexl1 : zzexl.zzb(paramzzexl1, paramzzexl2);
  }
  
  public final Object zza(boolean paramBoolean, Object paramObject1, Object paramObject2) {
    return paramObject2;
  }
  
  public final String zza(boolean paramBoolean1, String paramString1, boolean paramBoolean2, String paramString2) {
    return paramBoolean2 ? paramString2 : paramString1;
  }
  
  public final boolean zza(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4) {
    return paramBoolean3 ? paramBoolean4 : paramBoolean2;
  }
  
  public final Object zzb(boolean paramBoolean, Object paramObject1, Object paramObject2) {
    return paramObject2;
  }
  
  public final Object zzc(boolean paramBoolean, Object paramObject1, Object paramObject2) {
    return paramObject2;
  }
  
  public final void zzcz(boolean paramBoolean) {}
  
  public final Object zzd(boolean paramBoolean, Object paramObject1, Object paramObject2) {
    return paramObject2;
  }
  
  public final Object zze(boolean paramBoolean, Object paramObject1, Object paramObject2) {
    return paramObject2;
  }
  
  public final Object zzf(boolean paramBoolean, Object paramObject1, Object paramObject2) {
    return paramObject2;
  }
  
  public final Object zzg(boolean paramBoolean, Object paramObject1, Object paramObject2) {
    return paramBoolean ? zza((zzewl)paramObject1, (zzewl)paramObject2) : paramObject2;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzevo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */