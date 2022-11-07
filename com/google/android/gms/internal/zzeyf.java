package com.google.android.gms.internal;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ReadOnlyBufferException;

public final class zzeyf {
  private final ByteBuffer zzotk;
  
  private zzeyf(ByteBuffer paramByteBuffer) {
    this.zzotk = paramByteBuffer;
    paramByteBuffer.order(ByteOrder.LITTLE_ENDIAN);
  }
  
  private zzeyf(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    this(ByteBuffer.wrap(paramArrayOfbyte, paramInt1, paramInt2));
  }
  
  private static int zza(CharSequence paramCharSequence, byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    int i = paramCharSequence.length();
    int j = paramInt2 + paramInt1;
    paramInt2 = 0;
    while (paramInt2 < i) {
      int m = paramInt2 + paramInt1;
      if (m < j) {
        char c = paramCharSequence.charAt(paramInt2);
        if (c < '') {
          paramArrayOfbyte[m] = (byte)(byte)c;
          paramInt2++;
        } 
      } 
    } 
    if (paramInt2 == i)
      return paramInt1 + i; 
    int k = paramInt1 + paramInt2;
    paramInt1 = paramInt2;
    while (paramInt1 < i) {
      char c = paramCharSequence.charAt(paramInt1);
      if (c < '' && k < j) {
        paramInt2 = k + 1;
        paramArrayOfbyte[k] = (byte)(byte)c;
      } else if (c < 'ࠀ' && k <= j - 2) {
        int m = k + 1;
        paramArrayOfbyte[k] = (byte)(byte)(c >>> 6 | 0x3C0);
        paramInt2 = m + 1;
        paramArrayOfbyte[m] = (byte)(byte)(c & 0x3F | 0x80);
      } else if ((c < '?' || '?' < c) && k <= j - 3) {
        paramInt2 = k + 1;
        paramArrayOfbyte[k] = (byte)(byte)(c >>> 12 | 0x1E0);
        k = paramInt2 + 1;
        paramArrayOfbyte[paramInt2] = (byte)(byte)(c >>> 6 & 0x3F | 0x80);
        paramInt2 = k + 1;
        paramArrayOfbyte[k] = (byte)(byte)(c & 0x3F | 0x80);
      } else if (k <= j - 4) {
        paramInt2 = paramInt1 + 1;
        if (paramInt2 != paramCharSequence.length()) {
          char c1 = paramCharSequence.charAt(paramInt2);
          if (Character.isSurrogatePair(c, c1)) {
            paramInt1 = Character.toCodePoint(c, c1);
            int m = k + 1;
            paramArrayOfbyte[k] = (byte)(byte)(paramInt1 >>> 18 | 0xF0);
            k = m + 1;
            paramArrayOfbyte[m] = (byte)(byte)(paramInt1 >>> 12 & 0x3F | 0x80);
            m = k + 1;
            paramArrayOfbyte[k] = (byte)(byte)(paramInt1 >>> 6 & 0x3F | 0x80);
            k = m + 1;
            paramArrayOfbyte[m] = (byte)(byte)(paramInt1 & 0x3F | 0x80);
            paramInt1 = paramInt2;
            paramInt2 = k;
          } else {
            paramInt1 = paramInt2;
            paramCharSequence = new StringBuilder(39);
            paramCharSequence.append("Unpaired surrogate at index ");
            paramCharSequence.append(paramInt1 - 1);
            throw new IllegalArgumentException(paramCharSequence.toString());
          } 
        } else {
          paramCharSequence = new StringBuilder(39);
          paramCharSequence.append("Unpaired surrogate at index ");
          paramCharSequence.append(paramInt1 - 1);
          throw new IllegalArgumentException(paramCharSequence.toString());
        } 
      } else {
        paramCharSequence = new StringBuilder(37);
        paramCharSequence.append("Failed writing ");
        paramCharSequence.append(c);
        paramCharSequence.append(" at index ");
        paramCharSequence.append(k);
        throw new ArrayIndexOutOfBoundsException(paramCharSequence.toString());
      } 
      paramInt1++;
      k = paramInt2;
    } 
    return k;
  }
  
  private static void zza(CharSequence paramCharSequence, ByteBuffer paramByteBuffer) {
    if (!paramByteBuffer.isReadOnly()) {
      BufferOverflowException bufferOverflowException;
      if (paramByteBuffer.hasArray())
        try {
          paramByteBuffer.position(zza(paramCharSequence, paramByteBuffer.array(), paramByteBuffer.arrayOffset() + paramByteBuffer.position(), paramByteBuffer.remaining()) - paramByteBuffer.arrayOffset());
          return;
        } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
          bufferOverflowException = new BufferOverflowException();
          bufferOverflowException.initCause(arrayIndexOutOfBoundsException);
          throw bufferOverflowException;
        }  
      zzb((CharSequence)arrayIndexOutOfBoundsException, (ByteBuffer)bufferOverflowException);
      return;
    } 
    throw new ReadOnlyBufferException();
  }
  
  public static int zzaa(int paramInt1, int paramInt2) {
    return zzkb(paramInt1) + zzkc(paramInt2);
  }
  
  public static int zzb(int paramInt, zzeyn paramzzeyn) {
    paramInt = zzkb(paramInt);
    int i = paramzzeyn.zzhi();
    return paramInt + zzld(i) + i;
  }
  
  private static void zzb(CharSequence paramCharSequence, ByteBuffer paramByteBuffer) {
    int i = paramCharSequence.length();
    for (int j = 0;; j++) {
      if (j < i) {
        int k;
        char c = paramCharSequence.charAt(j);
        if (c < '') {
          k = c;
        } else {
          if (c < 'ࠀ') {
            k = c >>> 6 | 0x3C0;
          } else if (c < '?' || '?' < c) {
            paramByteBuffer.put((byte)(c >>> 12 | 0x1E0));
            k = c >>> 6 & 0x3F | 0x80;
          } else {
            k = j + 1;
            if (k != paramCharSequence.length()) {
              char c1 = paramCharSequence.charAt(k);
              if (Character.isSurrogatePair(c, c1)) {
                j = Character.toCodePoint(c, c1);
                paramByteBuffer.put((byte)(j >>> 18 | 0xF0));
                paramByteBuffer.put((byte)(j >>> 12 & 0x3F | 0x80));
                paramByteBuffer.put((byte)(j >>> 6 & 0x3F | 0x80));
                paramByteBuffer.put((byte)(j & 0x3F | 0x80));
                j = k;
              } else {
                j = k;
                paramCharSequence = new StringBuilder(39);
                paramCharSequence.append("Unpaired surrogate at index ");
                paramCharSequence.append(j - 1);
                throw new IllegalArgumentException(paramCharSequence.toString());
              } 
            } else {
              paramCharSequence = new StringBuilder(39);
              paramCharSequence.append("Unpaired surrogate at index ");
              paramCharSequence.append(j - 1);
              throw new IllegalArgumentException(paramCharSequence.toString());
            } 
            j++;
          } 
          paramByteBuffer.put((byte)k);
          k = c & 0x3F | 0x80;
        } 
        paramByteBuffer.put((byte)k);
      } else {
        break;
      } 
    } 
  }
  
  public static zzeyf zzbf(byte[] paramArrayOfbyte) {
    return zzn(paramArrayOfbyte, 0, paramArrayOfbyte.length);
  }
  
  public static int zzbg(byte[] paramArrayOfbyte) {
    return zzld(paramArrayOfbyte.length) + paramArrayOfbyte.length;
  }
  
  public static int zzc(int paramInt, long paramLong) {
    return zzkb(paramInt) + zzdg(paramLong);
  }
  
  private static int zzc(CharSequence paramCharSequence) {
    int n;
    int i = paramCharSequence.length();
    int j = 0;
    int k;
    for (k = 0; k < i && paramCharSequence.charAt(k) < ''; k++);
    int m = i;
    while (true) {
      n = m;
      if (k < i) {
        n = paramCharSequence.charAt(k);
        if (n < 2048) {
          m += 127 - n >>> 31;
          k++;
          continue;
        } 
        int i1 = paramCharSequence.length();
        n = j;
        while (k < i1) {
          char c = paramCharSequence.charAt(k);
          if (c < 'ࠀ') {
            n += 127 - c >>> 31;
            j = k;
          } else {
            int i2 = n + 2;
            n = i2;
            j = k;
            if ('?' <= c) {
              n = i2;
              j = k;
              if (c <= '?')
                if (Character.codePointAt(paramCharSequence, k) >= 65536) {
                  j = k + 1;
                  n = i2;
                } else {
                  paramCharSequence = new StringBuilder(39);
                  paramCharSequence.append("Unpaired surrogate at index ");
                  paramCharSequence.append(k);
                  throw new IllegalArgumentException(paramCharSequence.toString());
                }  
            } 
          } 
          k = j + 1;
        } 
        n = m + n;
      } 
      break;
    } 
    if (n >= i)
      return n; 
    long l = n;
    paramCharSequence = new StringBuilder(54);
    paramCharSequence.append("UTF-8 length does not fit in int: ");
    paramCharSequence.append(l + 4294967296L);
    IllegalArgumentException illegalArgumentException = new IllegalArgumentException(paramCharSequence.toString());
    throw illegalArgumentException;
  }
  
  private static long zzcz(long paramLong) {
    return paramLong >> 63L ^ paramLong << 1L;
  }
  
  public static int zzd(int paramInt, byte[] paramArrayOfbyte) {
    return zzkb(paramInt) + zzbg(paramArrayOfbyte);
  }
  
  private final void zzdf(long paramLong) throws IOException {
    while (true) {
      if ((0xFFFFFFFFFFFFFF80L & paramLong) == 0L) {
        zzlb((int)paramLong);
        return;
      } 
      zzlb((int)paramLong & 0x7F | 0x80);
      paramLong >>>= 7L;
    } 
  }
  
  public static int zzdg(long paramLong) {
    return ((0xFFFFFFFFFFFFFF80L & paramLong) == 0L) ? 1 : (((0xFFFFFFFFFFFFC000L & paramLong) == 0L) ? 2 : (((0xFFFFFFFFFFE00000L & paramLong) == 0L) ? 3 : (((0xFFFFFFFFF0000000L & paramLong) == 0L) ? 4 : (((0xFFFFFFF800000000L & paramLong) == 0L) ? 5 : (((0xFFFFFC0000000000L & paramLong) == 0L) ? 6 : (((0xFFFE000000000000L & paramLong) == 0L) ? 7 : (((0xFF00000000000000L & paramLong) == 0L) ? 8 : (((paramLong & Long.MIN_VALUE) == 0L) ? 9 : 10))))))));
  }
  
  private final void zzdh(long paramLong) throws IOException {
    if (this.zzotk.remaining() >= 8) {
      this.zzotk.putLong(paramLong);
      return;
    } 
    throw new zzeyg(this.zzotk.position(), this.zzotk.limit());
  }
  
  public static int zzh(int paramInt, long paramLong) {
    return zzkb(paramInt) + zzdg(zzcz(paramLong));
  }
  
  public static int zzkb(int paramInt) {
    return zzld(paramInt << 3);
  }
  
  public static int zzkc(int paramInt) {
    return (paramInt >= 0) ? zzld(paramInt) : 10;
  }
  
  public static int zzkj(int paramInt) {
    return paramInt >> 31 ^ paramInt << 1;
  }
  
  private final void zzlb(int paramInt) throws IOException {
    byte b = (byte)paramInt;
    if (this.zzotk.hasRemaining()) {
      this.zzotk.put(b);
      return;
    } 
    throw new zzeyg(this.zzotk.position(), this.zzotk.limit());
  }
  
  public static int zzld(int paramInt) {
    return ((paramInt & 0xFFFFFF80) == 0) ? 1 : (((paramInt & 0xFFFFC000) == 0) ? 2 : (((0xFFE00000 & paramInt) == 0) ? 3 : (((paramInt & 0xF0000000) == 0) ? 4 : 5)));
  }
  
  public static int zzn(int paramInt, String paramString) {
    return zzkb(paramInt) + zztk(paramString);
  }
  
  public static zzeyf zzn(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    return new zzeyf(paramArrayOfbyte, 0, paramInt2);
  }
  
  public static int zztk(String paramString) {
    int i = zzc(paramString);
    return zzld(i) + i;
  }
  
  public final void zza(int paramInt, double paramDouble) throws IOException {
    zzw(paramInt, 1);
    zzdh(Double.doubleToLongBits(paramDouble));
  }
  
  public final void zza(int paramInt, long paramLong) throws IOException {
    zzw(paramInt, 0);
    zzdf(paramLong);
  }
  
  public final void zza(int paramInt, zzeyn paramzzeyn) throws IOException {
    zzw(paramInt, 2);
    zzb(paramzzeyn);
  }
  
  public final void zzb(int paramInt, long paramLong) throws IOException {
    zzw(paramInt, 1);
    zzdh(paramLong);
  }
  
  public final void zzb(zzeyn paramzzeyn) throws IOException {
    zzlc(paramzzeyn.zzcwg());
    paramzzeyn.zza(this);
  }
  
  public final void zzbh(byte[] paramArrayOfbyte) throws IOException {
    int i = paramArrayOfbyte.length;
    if (this.zzotk.remaining() >= i) {
      this.zzotk.put(paramArrayOfbyte, 0, i);
      return;
    } 
    throw new zzeyg(this.zzotk.position(), this.zzotk.limit());
  }
  
  public final void zzc(int paramInt, float paramFloat) throws IOException {
    zzw(paramInt, 5);
    paramInt = Float.floatToIntBits(paramFloat);
    if (this.zzotk.remaining() >= 4) {
      this.zzotk.putInt(paramInt);
      return;
    } 
    throw new zzeyg(this.zzotk.position(), this.zzotk.limit());
  }
  
  public final void zzc(int paramInt, byte[] paramArrayOfbyte) throws IOException {
    zzw(paramInt, 2);
    zzlc(paramArrayOfbyte.length);
    zzbh(paramArrayOfbyte);
  }
  
  public final void zzctn() {
    if (this.zzotk.remaining() == 0)
      return; 
    throw new IllegalStateException(String.format("Did not write as much data as expected, %s bytes remaining.", new Object[] { Integer.valueOf(this.zzotk.remaining()) }));
  }
  
  public final void zzf(int paramInt, long paramLong) throws IOException {
    zzw(paramInt, 0);
    zzdf(paramLong);
  }
  
  public final void zzg(int paramInt, long paramLong) throws IOException {
    zzw(paramInt, 0);
    zzdf(zzcz(paramLong));
  }
  
  public final void zzl(int paramInt, boolean paramBoolean) throws IOException {
    zzw(paramInt, 0);
    byte b = (byte)paramBoolean;
    if (this.zzotk.hasRemaining()) {
      this.zzotk.put(b);
      return;
    } 
    throw new zzeyg(this.zzotk.position(), this.zzotk.limit());
  }
  
  public final void zzlc(int paramInt) throws IOException {
    while (true) {
      if ((paramInt & 0xFFFFFF80) == 0) {
        zzlb(paramInt);
        return;
      } 
      zzlb(paramInt & 0x7F | 0x80);
      paramInt >>>= 7;
    } 
  }
  
  public final void zzm(int paramInt, String paramString) throws IOException {
    zzw(paramInt, 2);
    try {
      zzeyg zzeyg;
      paramInt = zzld(paramString.length());
      if (paramInt == zzld(paramString.length() * 3)) {
        int i = this.zzotk.position();
        if (this.zzotk.remaining() >= paramInt) {
          this.zzotk.position(i + paramInt);
          zza(paramString, this.zzotk);
          int j = this.zzotk.position();
          this.zzotk.position(i);
          zzlc(j - i - paramInt);
          this.zzotk.position(j);
          return;
        } 
        zzeyg = new zzeyg();
        this(i + paramInt, this.zzotk.limit());
        throw zzeyg;
      } 
      zzlc(zzc((CharSequence)zzeyg));
      zza((CharSequence)zzeyg, this.zzotk);
      return;
    } catch (BufferOverflowException bufferOverflowException) {
      zzeyg zzeyg = new zzeyg(this.zzotk.position(), this.zzotk.limit());
      zzeyg.initCause(bufferOverflowException);
      throw zzeyg;
    } 
  }
  
  public final void zzw(int paramInt1, int paramInt2) throws IOException {
    zzlc(paramInt1 << 3 | paramInt2);
  }
  
  public final void zzx(int paramInt1, int paramInt2) throws IOException {
    zzw(paramInt1, 0);
    if (paramInt2 >= 0) {
      zzlc(paramInt2);
      return;
    } 
    zzdf(paramInt2);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzeyf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */