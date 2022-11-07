package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Arrays;

final class zzeuv extends zzeut {
  private final byte[] buffer;
  
  private int limit;
  
  private int pos;
  
  private final boolean zzoni;
  
  private int zzonj;
  
  private int zzonk;
  
  private int zzonl;
  
  private int zzonm = Integer.MAX_VALUE;
  
  private zzeuv(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, boolean paramBoolean) {
    super(null);
    this.buffer = paramArrayOfbyte;
    this.limit = paramInt2 + paramInt1;
    this.pos = paramInt1;
    this.zzonk = paramInt1;
    this.zzoni = paramBoolean;
  }
  
  private final long zzcth() throws IOException {
    int k;
    int i = this.pos;
    int j = this.limit;
    if (j != i) {
      byte[] arrayOfByte = this.buffer;
      k = i + 1;
      i = arrayOfByte[i];
      if (i >= 0) {
        this.pos = k;
        return i;
      } 
      if (j - k >= 9) {
        j = k + 1;
        i ^= arrayOfByte[k] << 7;
        if (i < 0) {
          k = i ^ 0xFFFFFF80;
        } else {
          long l1;
          k = j + 1;
          i ^= arrayOfByte[j] << 14;
          if (i >= 0) {
            l1 = (i ^ 0x3F80);
            j = k;
          } else {
            j = k + 1;
            k = i ^ arrayOfByte[k] << 21;
            if (k < 0) {
              k ^= 0xFFE03F80;
            } else {
              long l2 = k;
              k = j + 1;
              long l3 = l2 ^ arrayOfByte[j] << 28L;
              if (l3 >= 0L) {
                l2 = 266354560L;
                j = k;
              } else {
                j = k + 1;
                l3 ^= arrayOfByte[k] << 35L;
                if (l3 < 0L) {
                  l2 = -34093383808L;
                } else {
                  i = j + 1;
                  l3 ^= arrayOfByte[j] << 42L;
                  if (l3 >= 0L) {
                    l2 = 4363953127296L;
                    j = i;
                  } else {
                    k = i + 1;
                    l3 ^= arrayOfByte[i] << 49L;
                    if (l3 < 0L) {
                      l2 = -558586000294016L;
                      j = k;
                    } else {
                      j = k + 1;
                      l2 = l3 ^ arrayOfByte[k] << 56L ^ 0xFE03F80FE03F80L;
                      if (l2 < 0L) {
                        k = j + 1;
                        if (arrayOfByte[j] >= 0L) {
                          j = k;
                          this.pos = j;
                          return l2;
                        } 
                      } else {
                        this.pos = j;
                        return l2;
                      } 
                      return super.zzctd();
                    } 
                    l2 = l3 ^ l2;
                  } 
                  l2 ^= l3;
                } 
                l2 = l3 ^ l2;
              } 
              l2 ^= l3;
            } 
            l1 = k;
          } 
          this.pos = j;
          return l1;
        } 
      } else {
        return super.zzctd();
      } 
    } else {
      return super.zzctd();
    } 
    long l = k;
  }
  
  private final int zzcti() throws IOException {
    int i = this.pos;
    if (this.limit - i >= 4) {
      byte[] arrayOfByte = this.buffer;
      this.pos = i + 4;
      byte b1 = arrayOfByte[i];
      byte b2 = arrayOfByte[i + 1];
      byte b3 = arrayOfByte[i + 2];
      return (arrayOfByte[i + 3] & 0xFF) << 24 | b1 & 0xFF | (b2 & 0xFF) << 8 | (b3 & 0xFF) << 16;
    } 
    throw zzevz.zzcum();
  }
  
  private final long zzctj() throws IOException {
    int i = this.pos;
    if (this.limit - i >= 8) {
      byte[] arrayOfByte = this.buffer;
      this.pos = i + 8;
      long l1 = arrayOfByte[i];
      long l2 = arrayOfByte[i + 1];
      long l3 = arrayOfByte[i + 2];
      long l4 = arrayOfByte[i + 3];
      long l5 = arrayOfByte[i + 4];
      long l6 = arrayOfByte[i + 5];
      long l7 = arrayOfByte[i + 6];
      return (arrayOfByte[i + 7] & 0xFFL) << 56L | l1 & 0xFFL | (l2 & 0xFFL) << 8L | (l3 & 0xFFL) << 16L | (l4 & 0xFFL) << 24L | (l5 & 0xFFL) << 32L | (l6 & 0xFFL) << 40L | (l7 & 0xFFL) << 48L;
    } 
    throw zzevz.zzcum();
  }
  
  private final void zzctk() {
    int i = this.limit + this.zzonj;
    this.limit = i;
    int j = i - this.zzonk;
    int k = this.zzonm;
    if (j > k) {
      k = j - k;
      this.zzonj = k;
      this.limit = i - k;
      return;
    } 
    this.zzonj = 0;
  }
  
  private final byte zzctl() throws IOException {
    int i = this.pos;
    if (i != this.limit) {
      byte[] arrayOfByte = this.buffer;
      this.pos = i + 1;
      return arrayOfByte[i];
    } 
    throw zzevz.zzcum();
  }
  
  public final double readDouble() throws IOException {
    return Double.longBitsToDouble(zzctj());
  }
  
  public final float readFloat() throws IOException {
    return Float.intBitsToFloat(zzcti());
  }
  
  public final String readString() throws IOException {
    int i = super.zzctc();
    if (i > 0) {
      int j = this.limit;
      int k = this.pos;
      if (i <= j - k) {
        String str = new String(this.buffer, k, i, zzevu.UTF_8);
        this.pos += i;
        return str;
      } 
    } 
    if (i == 0)
      return ""; 
    if (i < 0)
      throw zzevz.zzcun(); 
    throw zzevz.zzcum();
  }
  
  public final <T extends zzevh<T, ?>> T zza(T paramT, zzevd paramzzevd) throws IOException {
    int i = super.zzctc();
    if (this.zzond < this.zzone) {
      i = super.zzjn(i);
      this.zzond++;
      paramT = zzevh.zza(paramT, this, paramzzevd);
      super.zzjk(0);
      this.zzond--;
      super.zzjo(i);
      return paramT;
    } 
    throw zzevz.zzcus();
  }
  
  public final void zza(zzewm paramzzewm, zzevd paramzzevd) throws IOException {
    int i = super.zzctc();
    if (this.zzond < this.zzone) {
      i = super.zzjn(i);
      this.zzond++;
      paramzzewm.zzb(this, paramzzevd);
      super.zzjk(0);
      this.zzond--;
      super.zzjo(i);
      return;
    } 
    throw zzevz.zzcus();
  }
  
  public final int zzcsn() throws IOException {
    if (super.zzctf()) {
      this.zzonl = 0;
      return 0;
    } 
    int i = super.zzctc();
    this.zzonl = i;
    if (i >>> 3 != 0)
      return i; 
    throw zzevz.zzcup();
  }
  
  public final long zzcso() throws IOException {
    return zzcth();
  }
  
  public final long zzcsp() throws IOException {
    return zzcth();
  }
  
  public final int zzcsq() throws IOException {
    return super.zzctc();
  }
  
  public final long zzcsr() throws IOException {
    return zzctj();
  }
  
  public final int zzcss() throws IOException {
    return zzcti();
  }
  
  public final boolean zzcst() throws IOException {
    return (zzcth() != 0L);
  }
  
  public final String zzcsu() throws IOException {
    int i = super.zzctc();
    if (i > 0) {
      int j = this.limit;
      int k = this.pos;
      if (i <= j - k) {
        if (zzexo.zzj(this.buffer, k, k + i)) {
          j = this.pos;
          this.pos = j + i;
          return new String(this.buffer, j, i, zzevu.UTF_8);
        } 
        throw zzevz.zzcuu();
      } 
    } 
    if (i == 0)
      return ""; 
    if (i <= 0)
      throw zzevz.zzcun(); 
    throw zzevz.zzcum();
  }
  
  public final zzeuk zzcsv() throws IOException {
    int i = super.zzctc();
    if (i > 0) {
      int j = this.limit;
      int k = this.pos;
      if (i <= j - k) {
        zzeuk zzeuk = zzeuk.zzd(this.buffer, k, i);
        this.pos += i;
        return zzeuk;
      } 
    } 
    if (i == 0)
      return zzeuk.zzomx; 
    if (i > 0) {
      int k = this.limit;
      int j = this.pos;
      if (i <= k - j) {
        i += j;
        this.pos = i;
        byte[] arrayOfByte = Arrays.copyOfRange(this.buffer, j, i);
        return zzeuk.zzba(arrayOfByte);
      } 
    } 
    if (i <= 0) {
      if (i == 0) {
        byte[] arrayOfByte = zzevu.EMPTY_BYTE_ARRAY;
        return zzeuk.zzba(arrayOfByte);
      } 
      throw zzevz.zzcun();
    } 
    throw zzevz.zzcum();
  }
  
  public final int zzcsw() throws IOException {
    return super.zzctc();
  }
  
  public final int zzcsx() throws IOException {
    return super.zzctc();
  }
  
  public final int zzcsy() throws IOException {
    return zzcti();
  }
  
  public final long zzcsz() throws IOException {
    return zzctj();
  }
  
  public final int zzcta() throws IOException {
    return zzeut.zzjq(super.zzctc());
  }
  
  public final long zzctb() throws IOException {
    return zzeut.zzcq(zzcth());
  }
  
  public final int zzctc() throws IOException {
    int i = this.pos;
    int j = this.limit;
    if (j != i) {
      byte[] arrayOfByte = this.buffer;
      int k = i + 1;
      i = arrayOfByte[i];
      if (i >= 0) {
        this.pos = k;
        return i;
      } 
      if (j - k >= 9) {
        j = k + 1;
        i ^= arrayOfByte[k] << 7;
        if (i < 0) {
          k = i ^ 0xFFFFFF80;
        } else {
          k = j + 1;
          i ^= arrayOfByte[j] << 14;
          if (i >= 0) {
            i ^= 0x3F80;
            j = k;
            k = i;
          } else {
            j = k + 1;
            k = i ^ arrayOfByte[k] << 21;
            if (k < 0) {
              k ^= 0xFFE03F80;
            } else {
              int m = j + 1;
              byte b = arrayOfByte[j];
              i = k ^ b << 28 ^ 0xFE03F80;
              k = i;
              j = m;
              if (b < 0) {
                int n = m + 1;
                k = i;
                j = n;
                if (arrayOfByte[m] < 0) {
                  int i1 = n + 1;
                  k = i;
                  j = i1;
                  if (arrayOfByte[n] < 0) {
                    m = i1 + 1;
                    k = i;
                    j = m;
                    if (arrayOfByte[i1] < 0) {
                      n = m + 1;
                      k = i;
                      j = n;
                      if (arrayOfByte[m] < 0) {
                        j = n + 1;
                        if (arrayOfByte[n] >= 0) {
                          k = i;
                          this.pos = j;
                          return k;
                        } 
                      } else {
                        this.pos = j;
                        return k;
                      } 
                    } else {
                      this.pos = j;
                      return k;
                    } 
                  } else {
                    this.pos = j;
                    return k;
                  } 
                } else {
                  this.pos = j;
                  return k;
                } 
              } else {
                this.pos = j;
                return k;
              } 
              return (int)super.zzctd();
            } 
          } 
        } 
        this.pos = j;
        return k;
      } 
    } 
    return (int)super.zzctd();
  }
  
  final long zzctd() throws IOException {
    long l = 0L;
    for (byte b = 0; b < 64; b += 7) {
      byte b1 = zzctl();
      l |= (b1 & Byte.MAX_VALUE) << b;
      if ((b1 & 0x80) == 0)
        return l; 
    } 
    zzevz zzevz = zzevz.zzcuo();
    throw zzevz;
  }
  
  public final int zzcte() {
    int i = this.zzonm;
    return (i == Integer.MAX_VALUE) ? -1 : (i - super.zzctg());
  }
  
  public final boolean zzctf() throws IOException {
    return (this.pos == this.limit);
  }
  
  public final int zzctg() {
    return this.pos - this.zzonk;
  }
  
  public final void zzjk(int paramInt) throws zzevz {
    if (this.zzonl == paramInt)
      return; 
    throw zzevz.zzcuq();
  }
  
  public final boolean zzjl(int paramInt) throws IOException {
    int i = paramInt & 0x7;
    boolean bool = false;
    int j = 0;
    if (i != 0) {
      if (i != 1) {
        if (i != 2) {
          if (i != 3) {
            if (i != 4) {
              if (i == 5) {
                super.zzjp(4);
                return true;
              } 
              throw zzevz.zzcur();
            } 
            return false;
          } 
          do {
            j = super.zzcsn();
          } while (j != 0 && super.zzjl(j));
          super.zzjk(paramInt >>> 3 << 3 | 0x4);
          return true;
        } 
        super.zzjp(super.zzctc());
        return true;
      } 
      super.zzjp(8);
      return true;
    } 
    paramInt = bool;
    if (this.limit - this.pos >= 10) {
      paramInt = j;
      while (paramInt < 10) {
        byte[] arrayOfByte = this.buffer;
        j = this.pos;
        this.pos = j + 1;
        if (arrayOfByte[j] < 0) {
          paramInt++;
          continue;
        } 
        return true;
      } 
      throw zzevz.zzcuo();
    } 
    while (paramInt < 10) {
      if (zzctl() < 0) {
        paramInt++;
        continue;
      } 
      return true;
    } 
    zzevz zzevz = zzevz.zzcuo();
    throw zzevz;
  }
  
  public final int zzjn(int paramInt) throws zzevz {
    if (paramInt >= 0) {
      paramInt += super.zzctg();
      int i = this.zzonm;
      if (paramInt <= i) {
        this.zzonm = paramInt;
        zzctk();
        return i;
      } 
      throw zzevz.zzcum();
    } 
    throw zzevz.zzcun();
  }
  
  public final void zzjo(int paramInt) {
    this.zzonm = paramInt;
    zzctk();
  }
  
  public final void zzjp(int paramInt) throws IOException {
    if (paramInt >= 0) {
      int i = this.limit;
      int j = this.pos;
      if (paramInt <= i - j) {
        this.pos = j + paramInt;
        return;
      } 
    } 
    if (paramInt < 0)
      throw zzevz.zzcun(); 
    throw zzevz.zzcum();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzeuv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */