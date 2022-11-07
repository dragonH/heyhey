package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Arrays;

public final class zzexl {
  private static final zzexl zzoqy = new zzexl(0, new int[0], new Object[0], false);
  
  private int count;
  
  private boolean zzomt;
  
  private int zzoof = -1;
  
  private int[] zzoqz;
  
  private Object[] zzora;
  
  private zzexl() {
    this(0, new int[8], new Object[8], true);
  }
  
  private zzexl(int paramInt, int[] paramArrayOfint, Object[] paramArrayOfObject, boolean paramBoolean) {
    this.count = paramInt;
    this.zzoqz = paramArrayOfint;
    this.zzora = paramArrayOfObject;
    this.zzomt = paramBoolean;
  }
  
  private final zzexl zza(zzeut paramzzeut) throws IOException {
    int i;
    do {
      i = paramzzeut.zzcsn();
    } while (i != 0 && zzb(i, paramzzeut));
    return this;
  }
  
  static zzexl zzb(zzexl paramzzexl1, zzexl paramzzexl2) {
    int i = paramzzexl1.count + paramzzexl2.count;
    int[] arrayOfInt = Arrays.copyOf(paramzzexl1.zzoqz, i);
    System.arraycopy(paramzzexl2.zzoqz, 0, arrayOfInt, paramzzexl1.count, paramzzexl2.count);
    Object[] arrayOfObject = Arrays.copyOf(paramzzexl1.zzora, i);
    System.arraycopy(paramzzexl2.zzora, 0, arrayOfObject, paramzzexl1.count, paramzzexl2.count);
    return new zzexl(i, arrayOfInt, arrayOfObject, true);
  }
  
  private void zzb(int paramInt, Object paramObject) {
    int i = this.count;
    int[] arrayOfInt = this.zzoqz;
    if (i == arrayOfInt.length) {
      if (i < 4) {
        k = 8;
      } else {
        k = i >> 1;
      } 
      int k = i + k;
      this.zzoqz = Arrays.copyOf(arrayOfInt, k);
      this.zzora = Arrays.copyOf(this.zzora, k);
    } 
    arrayOfInt = this.zzoqz;
    int j = this.count;
    arrayOfInt[j] = paramInt;
    this.zzora[j] = paramObject;
    this.count = j + 1;
  }
  
  public static zzexl zzcvp() {
    return zzoqy;
  }
  
  static zzexl zzcvq() {
    return new zzexl();
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (!(paramObject instanceof zzexl))
      return false; 
    paramObject = paramObject;
    int i = this.count;
    if (i == ((zzexl)paramObject).count) {
      int[] arrayOfInt1 = this.zzoqz;
      int[] arrayOfInt2 = ((zzexl)paramObject).zzoqz;
      byte b = 0;
      while (true) {
        if (b < i) {
          if (arrayOfInt1[b] != arrayOfInt2[b]) {
            b = 0;
            break;
          } 
          b++;
          continue;
        } 
        b = 1;
        break;
      } 
      if (b != 0) {
        Object[] arrayOfObject = this.zzora;
        paramObject = ((zzexl)paramObject).zzora;
        i = this.count;
        b = 0;
        while (true) {
          if (b < i) {
            if (!arrayOfObject[b].equals(paramObject[b])) {
              b = 0;
              break;
            } 
            b++;
            continue;
          } 
          b = 1;
          break;
        } 
        if (b != 0)
          return true; 
      } 
    } 
    return false;
  }
  
  public final int hashCode() {
    return ((this.count + 527) * 31 + Arrays.hashCode(this.zzoqz)) * 31 + Arrays.deepHashCode(this.zzora);
  }
  
  public final void zza(zzeuy paramzzeuy) throws IOException {
    for (byte b = 0; b < this.count; b++) {
      int i = this.zzoqz[b];
      int j = i >>> 3;
      i &= 0x7;
      if (i != 0) {
        if (i != 1) {
          if (i != 2) {
            if (i != 3) {
              if (i == 5) {
                paramzzeuy.zzz(j, ((Integer)this.zzora[b]).intValue());
              } else {
                throw zzevz.zzcur();
              } 
            } else {
              paramzzeuy.zzw(j, 3);
              ((zzexl)this.zzora[b]).zza(paramzzeuy);
              paramzzeuy.zzw(j, 4);
            } 
          } else {
            paramzzeuy.zza(j, (zzeuk)this.zzora[b]);
          } 
        } else {
          paramzzeuy.zzb(j, ((Long)this.zzora[b]).longValue());
        } 
      } else {
        paramzzeuy.zza(j, ((Long)this.zzora[b]).longValue());
      } 
    } 
  }
  
  final boolean zzb(int paramInt, zzeut paramzzeut) throws IOException {
    if (this.zzomt) {
      int i = paramInt & 0x7;
      if (i != 0) {
        if (i != 1) {
          if (i != 2) {
            if (i != 3) {
              if (i != 4) {
                if (i == 5) {
                  zzb(paramInt, Integer.valueOf(paramzzeut.zzcss()));
                  return true;
                } 
                throw zzevz.zzcur();
              } 
              return false;
            } 
            zzexl zzexl1 = new zzexl();
            zzexl1.zza(paramzzeut);
            paramzzeut.zzjk(paramInt >>> 3 << 3 | 0x4);
            zzb(paramInt, zzexl1);
            return true;
          } 
          zzb(paramInt, paramzzeut.zzcsv());
          return true;
        } 
        zzb(paramInt, Long.valueOf(paramzzeut.zzcsr()));
        return true;
      } 
      zzb(paramInt, Long.valueOf(paramzzeut.zzcsp()));
      return true;
    } 
    throw new UnsupportedOperationException();
  }
  
  public final void zzbhs() {
    this.zzomt = false;
  }
  
  final void zzd(StringBuilder paramStringBuilder, int paramInt) {
    for (byte b = 0; b < this.count; b++)
      zzewo.zzb(paramStringBuilder, paramInt, String.valueOf(this.zzoqz[b] >>> 3), this.zzora[b]); 
  }
  
  public final int zzhi() {
    int i = this.zzoof;
    if (i != -1)
      return i; 
    byte b = 0;
    int j = 0;
    while (b < this.count) {
      int k = this.zzoqz[b];
      i = k >>> 3;
      k &= 0x7;
      if (k != 0) {
        if (k != 1) {
          if (k != 2) {
            if (k != 3) {
              if (k == 5) {
                i = zzeuy.zzac(i, ((Integer)this.zzora[b]).intValue());
              } else {
                throw new IllegalStateException(zzevz.zzcur());
              } 
            } else {
              i = (zzeuy.zzkb(i) << 1) + ((zzexl)this.zzora[b]).zzhi();
            } 
          } else {
            i = zzeuy.zzb(i, (zzeuk)this.zzora[b]);
          } 
        } else {
          i = zzeuy.zze(i, ((Long)this.zzora[b]).longValue());
        } 
      } else {
        i = zzeuy.zzd(i, ((Long)this.zzora[b]).longValue());
      } 
      j += i;
      b++;
    } 
    this.zzoof = j;
    return j;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzexl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */