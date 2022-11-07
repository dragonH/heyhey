package com.google.android.gms.internal;

public final class zzg implements zzx {
  private int zzn = 2500;
  
  private int zzo;
  
  private final int zzp = 1;
  
  private final float zzq = 1.0F;
  
  public zzg() {
    this(2500, 1, 1.0F);
  }
  
  private zzg(int paramInt1, int paramInt2, float paramFloat) {}
  
  public final int zza() {
    return this.zzn;
  }
  
  public final void zza(zzaa paramzzaa) throws zzaa {
    int i = this.zzo;
    boolean bool = true;
    int j = i + 1;
    this.zzo = j;
    i = this.zzn;
    this.zzn = (int)(i + i * this.zzq);
    if (j > this.zzp)
      bool = false; 
    if (bool)
      return; 
    throw paramzzaa;
  }
  
  public final int zzb() {
    return this.zzo;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */