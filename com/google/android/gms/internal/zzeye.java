package com.google.android.gms.internal;

import java.io.IOException;

public final class zzeye {
  private final byte[] buffer;
  
  private int zzond;
  
  private int zzone = 64;
  
  private int zzonf = 67108864;
  
  private int zzonj;
  
  private int zzonl;
  
  private int zzonm = Integer.MAX_VALUE;
  
  private int zzono;
  
  private int zzoti;
  
  private int zzotj;
  
  private zzeye(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    this.buffer = paramArrayOfbyte;
    this.zzoti = paramInt1;
    this.zzono = paramInt2 + paramInt1;
    this.zzotj = paramInt1;
  }
  
  public static zzeye zzbe(byte[] paramArrayOfbyte) {
    return zzm(paramArrayOfbyte, 0, paramArrayOfbyte.length);
  }
  
  private final void zzctk() {
    int i = this.zzono + this.zzonj;
    this.zzono = i;
    int j = this.zzonm;
    if (i > j) {
      j = i - j;
      this.zzonj = j;
      this.zzono = i - j;
      return;
    } 
    this.zzonj = 0;
  }
  
  private final byte zzctl() throws IOException {
    int i = this.zzotj;
    if (i != this.zzono) {
      byte[] arrayOfByte = this.buffer;
      this.zzotj = i + 1;
      return arrayOfByte[i];
    } 
    throw zzeym.zzcwc();
  }
  
  private final void zzjp(int paramInt) throws IOException {
    if (paramInt >= 0) {
      int i = this.zzotj;
      int j = this.zzonm;
      if (i + paramInt <= j) {
        if (paramInt <= this.zzono - i) {
          this.zzotj = i + paramInt;
          return;
        } 
        throw zzeym.zzcwc();
      } 
      zzjp(j - i);
      throw zzeym.zzcwc();
    } 
    throw zzeym.zzcwd();
  }
  
  public static zzeye zzm(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    return new zzeye(paramArrayOfbyte, 0, paramInt2);
  }
  
  public final int getPosition() {
    return this.zzotj - this.zzoti;
  }
  
  public final byte[] readBytes() throws IOException {
    int i = zzctc();
    if (i >= 0) {
      if (i == 0)
        return zzeyq.zzoue; 
      int j = this.zzono;
      int k = this.zzotj;
      if (i <= j - k) {
        byte[] arrayOfByte = new byte[i];
        System.arraycopy(this.buffer, k, arrayOfByte, 0, i);
        this.zzotj += i;
        return arrayOfByte;
      } 
      throw zzeym.zzcwc();
    } 
    throw zzeym.zzcwd();
  }
  
  public final String readString() throws IOException {
    int i = zzctc();
    if (i >= 0) {
      int j = this.zzono;
      int k = this.zzotj;
      if (i <= j - k) {
        String str = new String(this.buffer, k, i, zzeyl.UTF_8);
        this.zzotj += i;
        return str;
      } 
      throw zzeym.zzcwc();
    } 
    throw zzeym.zzcwd();
  }
  
  public final void zza(zzeyn paramzzeyn) throws IOException {
    int i = zzctc();
    if (this.zzond < this.zzone) {
      i = zzjn(i);
      this.zzond++;
      paramzzeyn.zza(this);
      zzjk(0);
      this.zzond--;
      zzjo(i);
      return;
    } 
    throw zzeym.zzcwf();
  }
  
  public final void zza(zzeyn paramzzeyn, int paramInt) throws IOException {
    int i = this.zzond;
    if (i < this.zzone) {
      this.zzond = i + 1;
      paramzzeyn.zza(this);
      zzjk(paramInt << 3 | 0x4);
      this.zzond--;
      return;
    } 
    throw zzeym.zzcwf();
  }
  
  public final byte[] zzai(int paramInt1, int paramInt2) {
    if (paramInt2 == 0)
      return zzeyq.zzoue; 
    byte[] arrayOfByte = new byte[paramInt2];
    int i = this.zzoti;
    System.arraycopy(this.buffer, i + paramInt1, arrayOfByte, 0, paramInt2);
    return arrayOfByte;
  }
  
  final void zzaj(int paramInt1, int paramInt2) {
    int i = this.zzotj;
    int j = this.zzoti;
    if (paramInt1 <= i - j) {
      if (paramInt1 >= 0) {
        this.zzotj = j + paramInt1;
        this.zzonl = paramInt2;
        return;
      } 
      StringBuilder stringBuilder1 = new StringBuilder(24);
      stringBuilder1.append("Bad position ");
      stringBuilder1.append(paramInt1);
      throw new IllegalArgumentException(stringBuilder1.toString());
    } 
    i = this.zzotj;
    paramInt2 = this.zzoti;
    StringBuilder stringBuilder = new StringBuilder(50);
    stringBuilder.append("Position ");
    stringBuilder.append(paramInt1);
    stringBuilder.append(" is beyond current ");
    stringBuilder.append(i - paramInt2);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public final int zzcsn() throws IOException {
    if (this.zzotj == this.zzono) {
      this.zzonl = 0;
      return 0;
    } 
    int i = zzctc();
    this.zzonl = i;
    if (i != 0)
      return i; 
    throw new zzeym("Protocol message contained an invalid tag (zero).");
  }
  
  public final long zzcsp() throws IOException {
    return zzcth();
  }
  
  public final int zzcsq() throws IOException {
    return zzctc();
  }
  
  public final boolean zzcst() throws IOException {
    return (zzctc() != 0);
  }
  
  public final long zzctb() throws IOException {
    long l = zzcth();
    return -(l & 0x1L) ^ l >>> 1L;
  }
  
  public final int zzctc() throws IOException {
    byte b = zzctl();
    if (b >= 0)
      return b; 
    int i = b & Byte.MAX_VALUE;
    int j = zzctl();
    if (j >= 0) {
      j = j << 7;
    } else {
      i |= (j & 0x7F) << 7;
      j = zzctl();
      if (j >= 0) {
        j <<= 14;
      } else {
        i |= (j & 0x7F) << 14;
        j = zzctl();
        if (j >= 0) {
          j <<= 21;
        } else {
          byte b1 = zzctl();
          j = i | (j & 0x7F) << 21 | b1 << 28;
          i = j;
          if (b1 < 0) {
            for (i = 0; i < 5; i++) {
              if (zzctl() >= 0)
                return j; 
            } 
            throw zzeym.zzcwe();
          } 
          return i;
        } 
      } 
    } 
    i |= j;
    return i;
  }
  
  public final int zzcte() {
    int i = this.zzonm;
    return (i == Integer.MAX_VALUE) ? -1 : (i - this.zzotj);
  }
  
  public final long zzcth() throws IOException {
    byte b = 0;
    long l = 0L;
    while (b < 64) {
      byte b1 = zzctl();
      l |= (b1 & Byte.MAX_VALUE) << b;
      if ((b1 & 0x80) == 0)
        return l; 
      b += 7;
    } 
    zzeym zzeym = zzeym.zzcwe();
    throw zzeym;
  }
  
  public final int zzcti() throws IOException {
    return zzctl() & 0xFF | (zzctl() & 0xFF) << 8 | (zzctl() & 0xFF) << 16 | (zzctl() & 0xFF) << 24;
  }
  
  public final long zzctj() throws IOException {
    byte b1 = zzctl();
    byte b2 = zzctl();
    byte b3 = zzctl();
    byte b4 = zzctl();
    byte b5 = zzctl();
    byte b6 = zzctl();
    byte b7 = zzctl();
    byte b8 = zzctl();
    long l = b1;
    return (b2 & 0xFFL) << 8L | l & 0xFFL | (b3 & 0xFFL) << 16L | (b4 & 0xFFL) << 24L | (b5 & 0xFFL) << 32L | (b6 & 0xFFL) << 40L | (b7 & 0xFFL) << 48L | (b8 & 0xFFL) << 56L;
  }
  
  public final void zzjk(int paramInt) throws zzeym {
    if (this.zzonl == paramInt)
      return; 
    throw new zzeym("Protocol message end-group tag did not match expected tag.");
  }
  
  public final boolean zzjl(int paramInt) throws IOException {
    int i = paramInt & 0x7;
    if (i != 0) {
      if (i != 1) {
        if (i != 2) {
          if (i != 3) {
            if (i != 4) {
              if (i == 5) {
                zzcti();
                return true;
              } 
              throw new zzeym("Protocol message tag had invalid wire type.");
            } 
            return false;
          } 
          do {
            i = zzcsn();
          } while (i != 0 && zzjl(i));
          zzjk(paramInt >>> 3 << 3 | 0x4);
          return true;
        } 
        zzjp(zzctc());
        return true;
      } 
      zzctj();
      return true;
    } 
    zzctc();
    return true;
  }
  
  public final int zzjn(int paramInt) throws zzeym {
    if (paramInt >= 0) {
      paramInt += this.zzotj;
      int i = this.zzonm;
      if (paramInt <= i) {
        this.zzonm = paramInt;
        zzctk();
        return i;
      } 
      throw zzeym.zzcwc();
    } 
    throw zzeym.zzcwd();
  }
  
  public final void zzjo(int paramInt) {
    this.zzonm = paramInt;
    zzctk();
  }
  
  public final void zzla(int paramInt) {
    zzaj(paramInt, this.zzonl);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzeye.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */