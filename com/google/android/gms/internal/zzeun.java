package com.google.android.gms.internal;

final class zzeun extends zzeur {
  private final int zzona;
  
  private final int zzonb;
  
  zzeun(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    super(paramArrayOfbyte);
    zzeuk.zzg(paramInt1, paramInt1 + paramInt2, paramArrayOfbyte.length);
    this.zzona = paramInt1;
    this.zzonb = paramInt2;
  }
  
  public final int size() {
    return this.zzonb;
  }
  
  protected final void zzb(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, int paramInt3) {
    System.arraycopy(this.bytes, super.zzcsk() + paramInt1, paramArrayOfbyte, paramInt2, paramInt3);
  }
  
  protected final int zzcsk() {
    return this.zzona;
  }
  
  public final byte zzji(int paramInt) {
    zzeuk.zzv(paramInt, super.size());
    return this.bytes[this.zzona + paramInt];
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzeun.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */