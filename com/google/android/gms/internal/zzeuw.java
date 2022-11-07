package com.google.android.gms.internal;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

final class zzeuw extends zzeut {
  private final byte[] buffer;
  
  private int pos;
  
  private int zzonj;
  
  private int zzonl;
  
  private int zzonm = Integer.MAX_VALUE;
  
  private final InputStream zzonn;
  
  private int zzono;
  
  private int zzonp;
  
  private zzeux zzonq = null;
  
  private zzeuw(InputStream paramInputStream, int paramInt) {
    super(null);
    zzevu.zzb(paramInputStream, "input");
    this.zzonn = paramInputStream;
    this.buffer = new byte[paramInt];
    this.zzono = 0;
    this.pos = 0;
    this.zzonp = 0;
  }
  
  private final long zzcth() throws IOException {
    int k;
    int i = this.pos;
    int j = this.zzono;
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
                l2 = l3 ^ arrayOfByte[k] << 35L;
                if (l2 < 0L) {
                  l3 = -34093383808L;
                } else {
                  k = j + 1;
                  l3 = l2 ^ arrayOfByte[j] << 42L;
                  if (l3 >= 0L) {
                    l2 = 4363953127296L;
                    j = k;
                  } else {
                    i = k + 1;
                    l2 = l3 ^ arrayOfByte[k] << 49L;
                    if (l2 < 0L) {
                      l3 = -558586000294016L;
                      j = i;
                    } else {
                      j = i + 1;
                      l2 = l2 ^ arrayOfByte[i] << 56L ^ 0xFE03F80FE03F80L;
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
                    l2 ^= l3;
                  } 
                  l2 ^= l3;
                } 
                l2 ^= l3;
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
    int j = i;
    if (this.zzono - i < 4) {
      zzjr(4);
      j = this.pos;
    } 
    byte[] arrayOfByte = this.buffer;
    this.pos = j + 4;
    byte b1 = arrayOfByte[j];
    i = arrayOfByte[j + 1];
    byte b2 = arrayOfByte[j + 2];
    return (arrayOfByte[j + 3] & 0xFF) << 24 | b1 & 0xFF | (i & 0xFF) << 8 | (b2 & 0xFF) << 16;
  }
  
  private final long zzctj() throws IOException {
    int i = this.pos;
    int j = i;
    if (this.zzono - i < 8) {
      zzjr(8);
      j = this.pos;
    } 
    byte[] arrayOfByte = this.buffer;
    this.pos = j + 8;
    long l1 = arrayOfByte[j];
    long l2 = arrayOfByte[j + 1];
    long l3 = arrayOfByte[j + 2];
    long l4 = arrayOfByte[j + 3];
    long l5 = arrayOfByte[j + 4];
    long l6 = arrayOfByte[j + 5];
    long l7 = arrayOfByte[j + 6];
    return (arrayOfByte[j + 7] & 0xFFL) << 56L | l1 & 0xFFL | (l2 & 0xFFL) << 8L | (l3 & 0xFFL) << 16L | (l4 & 0xFFL) << 24L | (l5 & 0xFFL) << 32L | (l6 & 0xFFL) << 40L | (l7 & 0xFFL) << 48L;
  }
  
  private final void zzctk() {
    int i = this.zzono + this.zzonj;
    this.zzono = i;
    int j = this.zzonp + i;
    int k = this.zzonm;
    if (j > k) {
      j -= k;
      this.zzonj = j;
      this.zzono = i - j;
      return;
    } 
    this.zzonj = 0;
  }
  
  private final byte zzctl() throws IOException {
    if (this.pos == this.zzono)
      zzjr(1); 
    byte[] arrayOfByte = this.buffer;
    int i = this.pos;
    this.pos = i + 1;
    return arrayOfByte[i];
  }
  
  private final void zzjr(int paramInt) throws IOException {
    if (!zzjs(paramInt)) {
      if (paramInt > this.zzonf - this.zzonp - this.pos)
        throw zzevz.zzcut(); 
      throw zzevz.zzcum();
    } 
  }
  
  private final boolean zzjs(int paramInt) throws IOException {
    while (true) {
      int i = this.pos;
      int j = this.zzono;
      if (i + paramInt > j) {
        int k = this.zzonf;
        int m = this.zzonp;
        if (paramInt > k - m - i)
          return false; 
        if (m + i + paramInt > this.zzonm)
          return false; 
        if (i > 0) {
          if (j > i) {
            byte[] arrayOfByte1 = this.buffer;
            System.arraycopy(arrayOfByte1, i, arrayOfByte1, 0, j - i);
          } 
          this.zzonp += i;
          this.zzono -= i;
          this.pos = 0;
        } 
        InputStream inputStream = this.zzonn;
        byte[] arrayOfByte = this.buffer;
        k = this.zzono;
        k = inputStream.read(arrayOfByte, k, Math.min(arrayOfByte.length - k, this.zzonf - this.zzonp - k));
        if (k != 0) {
          if (k >= -1) {
            if (k <= this.buffer.length) {
              if (k > 0) {
                this.zzono += k;
                zzctk();
                if (this.zzono >= paramInt)
                  return true; 
                continue;
              } 
              return false;
            } 
            continue;
          } 
          continue;
        } 
        StringBuilder stringBuilder1 = new StringBuilder(102);
        stringBuilder1.append("InputStream#read(byte[]) returned invalid result: ");
        stringBuilder1.append(k);
        stringBuilder1.append("\nThe InputStream implementation is buggy.");
        throw new IllegalStateException(stringBuilder1.toString());
      } 
      StringBuilder stringBuilder = new StringBuilder(77);
      stringBuilder.append("refillBuffer() called when ");
      stringBuilder.append(paramInt);
      stringBuilder.append(" bytes were already available in buffer");
      IllegalStateException illegalStateException = new IllegalStateException(stringBuilder.toString());
      throw illegalStateException;
    } 
  }
  
  private final byte[] zzjt(int paramInt) throws IOException {
    byte[] arrayOfByte = zzju(paramInt);
    if (arrayOfByte != null)
      return arrayOfByte; 
    int i = this.pos;
    int j = this.zzono;
    int k = j - i;
    this.zzonp += j;
    this.pos = 0;
    this.zzono = 0;
    List<byte[]> list = zzjv(paramInt - k);
    arrayOfByte = new byte[paramInt];
    System.arraycopy(this.buffer, i, arrayOfByte, 0, k);
    Iterator<byte> iterator = list.iterator();
    for (paramInt = k; iterator.hasNext(); paramInt += arrayOfByte1.length) {
      byte[] arrayOfByte1 = (byte[])iterator.next();
      System.arraycopy(arrayOfByte1, 0, arrayOfByte, paramInt, arrayOfByte1.length);
    } 
    return arrayOfByte;
  }
  
  private final byte[] zzju(int paramInt) throws IOException {
    if (paramInt == 0)
      return zzevu.EMPTY_BYTE_ARRAY; 
    if (paramInt >= 0) {
      int i = this.zzonp;
      int j = this.pos;
      int k = i + j + paramInt;
      if (k - this.zzonf <= 0) {
        int m = this.zzonm;
        if (k <= m) {
          m = this.zzono - j;
          k = paramInt - m;
          if (k < 4096 || k <= this.zzonn.available()) {
            byte[] arrayOfByte = new byte[paramInt];
            System.arraycopy(this.buffer, this.pos, arrayOfByte, 0, m);
            this.zzonp += this.zzono;
            this.pos = 0;
            this.zzono = 0;
            while (m < paramInt) {
              k = this.zzonn.read(arrayOfByte, m, paramInt - m);
              if (k != -1) {
                this.zzonp += k;
                m += k;
                continue;
              } 
              throw zzevz.zzcum();
            } 
            return arrayOfByte;
          } 
          return null;
        } 
        super.zzjp(m - i - j);
        throw zzevz.zzcum();
      } 
      throw zzevz.zzcut();
    } 
    zzevz zzevz = zzevz.zzcun();
    throw zzevz;
  }
  
  private final List<byte[]> zzjv(int paramInt) throws IOException {
    ArrayList<byte[]> arrayList = new ArrayList();
    while (paramInt > 0) {
      int i = Math.min(paramInt, 4096);
      byte[] arrayOfByte = new byte[i];
      int j = 0;
      while (j < i) {
        int k = this.zzonn.read(arrayOfByte, j, i - j);
        if (k != -1) {
          this.zzonp += k;
          j += k;
          continue;
        } 
        throw zzevz.zzcum();
      } 
      paramInt -= i;
      arrayList.add(arrayOfByte);
    } 
    return (List<byte[]>)arrayList;
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
      int j = this.zzono;
      int k = this.pos;
      if (i <= j - k) {
        String str = new String(this.buffer, k, i, zzevu.UTF_8);
        this.pos += i;
        return str;
      } 
    } 
    if (i == 0)
      return ""; 
    if (i <= this.zzono) {
      zzjr(i);
      String str = new String(this.buffer, this.pos, i, zzevu.UTF_8);
      this.pos += i;
      return str;
    } 
    return new String(zzjt(i), zzevu.UTF_8);
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
    byte[] arrayOfByte;
    int i = super.zzctc();
    int j = this.pos;
    int k = this.zzono;
    if (i <= k - j && i > 0) {
      arrayOfByte = this.buffer;
      this.pos = j + i;
    } else {
      if (i == 0)
        return ""; 
      if (i <= k) {
        zzjr(i);
        arrayOfByte = this.buffer;
        this.pos = i;
      } else {
        arrayOfByte = zzjt(i);
      } 
      j = 0;
    } 
    if (zzexo.zzj(arrayOfByte, j, j + i))
      return new String(arrayOfByte, j, i, zzevu.UTF_8); 
    throw zzevz.zzcuu();
  }
  
  public final zzeuk zzcsv() throws IOException {
    int i = super.zzctc();
    int j = this.zzono;
    int k = this.pos;
    if (i <= j - k && i > 0) {
      zzeuk zzeuk = zzeuk.zzd(this.buffer, k, i);
      this.pos += i;
      return zzeuk;
    } 
    if (i == 0)
      return zzeuk.zzomx; 
    byte[] arrayOfByte = zzju(i);
    if (arrayOfByte != null)
      return zzeuk.zzba(arrayOfByte); 
    j = this.pos;
    int m = this.zzono;
    k = m - j;
    this.zzonp += m;
    this.pos = 0;
    this.zzono = 0;
    List<byte[]> list = zzjv(i - k);
    ArrayList<zzeuk> arrayList = new ArrayList(list.size() + 1);
    arrayList.add(zzeuk.zzd(this.buffer, j, k));
    Iterator<byte> iterator = list.iterator();
    while (iterator.hasNext())
      arrayList.add(zzeuk.zzba((byte[])iterator.next())); 
    return zzeuk.zzf(arrayList);
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
    int j = this.zzono;
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
                      i1 = m + 1;
                      k = i;
                      j = i1;
                      if (arrayOfByte[m] < 0) {
                        j = i1 + 1;
                        if (arrayOfByte[i1] >= 0) {
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
    return (i == Integer.MAX_VALUE) ? -1 : (i - this.zzonp + this.pos);
  }
  
  public final boolean zzctf() throws IOException {
    return (this.pos == this.zzono && !zzjs(1));
  }
  
  public final int zzctg() {
    return this.zzonp + this.pos;
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
    if (this.zzono - this.pos >= 10) {
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
      int i = paramInt + this.zzonp + this.pos;
      paramInt = this.zzonm;
      if (i <= paramInt) {
        this.zzonm = i;
        zzctk();
        return paramInt;
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
    int i = this.zzono;
    int j = this.pos;
    if (paramInt <= i - j && paramInt >= 0) {
      this.pos = j + paramInt;
      return;
    } 
    if (paramInt >= 0) {
      int k = this.zzonp;
      int m = this.zzonm;
      if (k + j + paramInt <= m) {
        k = i - j;
        this.pos = i;
        while (true) {
          zzjr(1);
          j = paramInt - k;
          i = this.zzono;
          if (j > i) {
            k += i;
            this.pos = i;
            continue;
          } 
          this.pos = j;
          return;
        } 
      } 
      super.zzjp(m - k - j);
      throw zzevz.zzcum();
    } 
    zzevz zzevz = zzevz.zzcun();
    throw zzevz;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzeuw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */