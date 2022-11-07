package com.google.android.gms.internal;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

public abstract class zzeuk implements Serializable, Iterable<Byte> {
  static {
    try {
      Class.forName("android.content.Context");
      bool = true;
    } catch (ClassNotFoundException classNotFoundException) {
      bool = false;
    } 
    if (bool) {
      zzeus zzeus = new zzeus(null);
    } else {
      zzeum = new zzeum(null);
    } 
    zzomy = zzeum;
  }
  
  private static zzeuk zza(Iterator<zzeuk> paramIterator, int paramInt) {
    if (paramInt > 0) {
      zzeuk zzeuk1;
      StringBuilder stringBuilder;
      if (paramInt == 1) {
        zzeuk1 = paramIterator.next();
      } else {
        int i = paramInt >>> 1;
        zzeuk zzeuk2 = zza((Iterator<zzeuk>)zzeuk1, i);
        zzeuk1 = zza((Iterator<zzeuk>)zzeuk1, paramInt - i);
        if (Integer.MAX_VALUE - zzeuk2.size() >= zzeuk1.size())
          return zzews.zza(zzeuk2, zzeuk1); 
        i = zzeuk2.size();
        paramInt = zzeuk1.size();
        stringBuilder = new StringBuilder(53);
        stringBuilder.append("ByteString would be too long: ");
        stringBuilder.append(i);
        stringBuilder.append("+");
        stringBuilder.append(paramInt);
        throw new IllegalArgumentException(stringBuilder.toString());
      } 
      return (zzeuk)stringBuilder;
    } 
    throw new IllegalArgumentException(String.format("length (%s) must be >= 1", new Object[] { Integer.valueOf(paramInt) }));
  }
  
  public static zzeuk zzaz(byte[] paramArrayOfbyte) {
    return zzd(paramArrayOfbyte, 0, paramArrayOfbyte.length);
  }
  
  static zzeuk zzba(byte[] paramArrayOfbyte) {
    return new zzeur(paramArrayOfbyte);
  }
  
  public static zzeuk zzd(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    return new zzeur(zzomy.zze(paramArrayOfbyte, paramInt1, paramInt2));
  }
  
  public static zzeuk zzf(Iterable<zzeuk> paramIterable) {
    int i = ((Collection)paramIterable).size();
    return (i == 0) ? zzomx : zza(paramIterable.iterator(), i);
  }
  
  static int zzg(int paramInt1, int paramInt2, int paramInt3) {
    int i = paramInt2 - paramInt1;
    if ((paramInt1 | paramInt2 | i | paramInt3 - paramInt2) < 0) {
      if (paramInt1 >= 0) {
        if (paramInt2 < paramInt1) {
          StringBuilder stringBuilder2 = new StringBuilder(66);
          stringBuilder2.append("Beginning index larger than ending index: ");
          stringBuilder2.append(paramInt1);
          stringBuilder2.append(", ");
          stringBuilder2.append(paramInt2);
          throw new IndexOutOfBoundsException(stringBuilder2.toString());
        } 
        StringBuilder stringBuilder1 = new StringBuilder(37);
        stringBuilder1.append("End index: ");
        stringBuilder1.append(paramInt2);
        stringBuilder1.append(" >= ");
        stringBuilder1.append(paramInt3);
        throw new IndexOutOfBoundsException(stringBuilder1.toString());
      } 
      StringBuilder stringBuilder = new StringBuilder(32);
      stringBuilder.append("Beginning index: ");
      stringBuilder.append(paramInt1);
      stringBuilder.append(" < 0");
      throw new IndexOutOfBoundsException(stringBuilder.toString());
    } 
    return i;
  }
  
  static zzeup zzjj(int paramInt) {
    return new zzeup(paramInt, null);
  }
  
  public static zzeuk zzti(String paramString) {
    return new zzeur(paramString.getBytes(zzevu.UTF_8));
  }
  
  static void zzv(int paramInt1, int paramInt2) {
    if ((paramInt2 - paramInt1 + 1 | paramInt1) < 0) {
      if (paramInt1 < 0) {
        StringBuilder stringBuilder1 = new StringBuilder(22);
        stringBuilder1.append("Index < 0: ");
        stringBuilder1.append(paramInt1);
        throw new ArrayIndexOutOfBoundsException(stringBuilder1.toString());
      } 
      StringBuilder stringBuilder = new StringBuilder(40);
      stringBuilder.append("Index > length: ");
      stringBuilder.append(paramInt1);
      stringBuilder.append(", ");
      stringBuilder.append(paramInt2);
      throw new ArrayIndexOutOfBoundsException(stringBuilder.toString());
    } 
  }
  
  public abstract boolean equals(Object paramObject);
  
  public final int hashCode() {
    int i = this.zzlib;
    int j = i;
    if (i == 0) {
      j = size();
      i = zzf(j, 0, j);
      j = i;
      if (i == 0)
        j = 1; 
      this.zzlib = j;
    } 
    return j;
  }
  
  public final boolean isEmpty() {
    return (size() == 0);
  }
  
  public abstract int size();
  
  public final byte[] toByteArray() {
    int i = size();
    if (i == 0)
      return zzevu.EMPTY_BYTE_ARRAY; 
    byte[] arrayOfByte = new byte[i];
    zzb(arrayOfByte, 0, 0, i);
    return arrayOfByte;
  }
  
  public final String toString() {
    return String.format("<ByteString@%s size=%d>", new Object[] { Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(size()) });
  }
  
  abstract void zza(zzeuj paramzzeuj) throws IOException;
  
  public final void zza(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, int paramInt3) {
    zzg(paramInt1, paramInt1 + paramInt3, size());
    zzg(paramInt2, paramInt2 + paramInt3, paramArrayOfbyte.length);
    if (paramInt3 > 0)
      zzb(paramArrayOfbyte, paramInt1, paramInt2, paramInt3); 
  }
  
  protected abstract void zzb(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, int paramInt3);
  
  public abstract zzeut zzcsg();
  
  protected abstract int zzcsh();
  
  protected abstract boolean zzcsi();
  
  protected final int zzcsj() {
    return this.zzlib;
  }
  
  protected abstract int zzf(int paramInt1, int paramInt2, int paramInt3);
  
  public abstract byte zzji(int paramInt);
  
  public abstract zzeuk zzu(int paramInt1, int paramInt2);
  
  static {
    boolean bool;
    zzeum zzeum;
  }
  
  public static final zzeuk zzomx = new zzeur(zzevu.EMPTY_BYTE_ARRAY);
  
  private static final zzeuo zzomy;
  
  private int zzlib = 0;
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzeuk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */