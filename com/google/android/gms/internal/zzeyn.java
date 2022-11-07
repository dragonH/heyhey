package com.google.android.gms.internal;

import java.io.IOException;

public abstract class zzeyn {
  protected volatile int zzomu = -1;
  
  public static final <T extends zzeyn> T zza(T paramT, byte[] paramArrayOfbyte) throws zzeym {
    return zza(paramT, paramArrayOfbyte, 0, paramArrayOfbyte.length);
  }
  
  private static <T extends zzeyn> T zza(T paramT, byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws zzeym {
    try {
      zzeye zzeye = zzeye.zzm(paramArrayOfbyte, 0, paramInt2);
      paramT.zza(zzeye);
      zzeye.zzjk(0);
      return paramT;
    } catch (zzeym zzeym) {
      throw zzeym;
    } catch (IOException iOException) {
      throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).", iOException);
    } 
  }
  
  public static final byte[] zzc(zzeyn paramzzeyn) {
    int i = paramzzeyn.zzhi();
    byte[] arrayOfByte = new byte[i];
    try {
      zzeyf zzeyf = zzeyf.zzn(arrayOfByte, 0, i);
      paramzzeyn.zza(zzeyf);
      zzeyf.zzctn();
      return arrayOfByte;
    } catch (IOException iOException) {
      throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", iOException);
    } 
  }
  
  public String toString() {
    return zzeyo.zzd(this);
  }
  
  public abstract zzeyn zza(zzeye paramzzeye) throws IOException;
  
  public void zza(zzeyf paramzzeyf) throws IOException {}
  
  public zzeyn zzcwa() throws CloneNotSupportedException {
    return (zzeyn)super.clone();
  }
  
  public final int zzcwg() {
    if (this.zzomu < 0)
      zzhi(); 
    return this.zzomu;
  }
  
  public final int zzhi() {
    int i = zzn();
    this.zzomu = i;
    return i;
  }
  
  protected int zzn() {
    return 0;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzeyn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */