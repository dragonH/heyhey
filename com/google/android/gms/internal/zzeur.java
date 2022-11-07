package com.google.android.gms.internal;

import java.io.IOException;

class zzeur extends zzeuq {
  protected final byte[] bytes;
  
  zzeur(byte[] paramArrayOfbyte) {
    this.bytes = paramArrayOfbyte;
  }
  
  public final boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof zzeuk))
      return false; 
    if (super.size() != ((zzeuk)paramObject).size())
      return false; 
    if (super.size() == 0)
      return true; 
    if (paramObject instanceof zzeur) {
      paramObject = paramObject;
      int i = zzcsj();
      int j = paramObject.zzcsj();
      return (i != 0 && j != 0 && i != j) ? false : super.zza((zzeuk)paramObject, 0, super.size());
    } 
    return paramObject.equals(this);
  }
  
  public int size() {
    return this.bytes.length;
  }
  
  final void zza(zzeuj paramzzeuj) throws IOException {
    paramzzeuj.zzc(this.bytes, zzcsk(), super.size());
  }
  
  final boolean zza(zzeuk paramzzeuk, int paramInt1, int paramInt2) {
    if (paramInt2 <= paramzzeuk.size()) {
      int i = paramInt1 + paramInt2;
      if (i <= paramzzeuk.size()) {
        if (paramzzeuk instanceof zzeur) {
          paramzzeuk = paramzzeuk;
          byte[] arrayOfByte1 = this.bytes;
          byte[] arrayOfByte2 = ((zzeur)paramzzeuk).bytes;
          int j = zzcsk();
          i = zzcsk();
          for (paramInt1 = paramzzeuk.zzcsk() + paramInt1; i < j + paramInt2; paramInt1++) {
            if (arrayOfByte1[i] != arrayOfByte2[paramInt1])
              return false; 
            i++;
          } 
          return true;
        } 
        return paramzzeuk.zzu(paramInt1, i).equals(super.zzu(0, paramInt2));
      } 
      i = paramzzeuk.size();
      StringBuilder stringBuilder1 = new StringBuilder(59);
      stringBuilder1.append("Ran off end of other: ");
      stringBuilder1.append(paramInt1);
      stringBuilder1.append(", ");
      stringBuilder1.append(paramInt2);
      stringBuilder1.append(", ");
      stringBuilder1.append(i);
      throw new IllegalArgumentException(stringBuilder1.toString());
    } 
    paramInt1 = super.size();
    StringBuilder stringBuilder = new StringBuilder(40);
    stringBuilder.append("Length too large: ");
    stringBuilder.append(paramInt2);
    stringBuilder.append(paramInt1);
    IllegalArgumentException illegalArgumentException = new IllegalArgumentException(stringBuilder.toString());
    throw illegalArgumentException;
  }
  
  protected void zzb(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, int paramInt3) {
    System.arraycopy(this.bytes, paramInt1, paramArrayOfbyte, paramInt2, paramInt3);
  }
  
  public final zzeut zzcsg() {
    return zzeut.zzb(this.bytes, zzcsk(), super.size(), true);
  }
  
  protected int zzcsk() {
    return 0;
  }
  
  protected final int zzf(int paramInt1, int paramInt2, int paramInt3) {
    return zzevu.zza(paramInt1, this.bytes, zzcsk() + paramInt2, paramInt3);
  }
  
  public byte zzji(int paramInt) {
    return this.bytes[paramInt];
  }
  
  public final zzeuk zzu(int paramInt1, int paramInt2) {
    paramInt2 = zzeuk.zzg(paramInt1, paramInt2, super.size());
    return (paramInt2 == 0) ? zzeuk.zzomx : new zzeun(this.bytes, zzcsk() + paramInt1, paramInt2);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzeur.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */