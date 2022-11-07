package com.google.android.gms.internal;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class zzeuy extends zzeuj {
  private static final Logger logger = Logger.getLogger(zzeuy.class.getName());
  
  private static final boolean zzonr = zzexm.zzcvr();
  
  private zzeuy() {}
  
  public static int zza(zzewf paramzzewf) {
    int i = paramzzewf.zzhi();
    return zzkd(i) + i;
  }
  
  public static int zzaa(int paramInt1, int paramInt2) {
    return zzkb(paramInt1) + zzkc(paramInt2);
  }
  
  public static int zzab(int paramInt1, int paramInt2) {
    return zzkb(paramInt1) + zzkd(paramInt2);
  }
  
  public static int zzac(int paramInt1, int paramInt2) {
    return zzkb(paramInt1) + 4;
  }
  
  public static int zzad(int paramInt1, int paramInt2) {
    return zzkb(paramInt1) + zzkc(paramInt2);
  }
  
  public static int zzan(zzeuk paramzzeuk) {
    int i = paramzzeuk.size();
    return zzkd(i) + i;
  }
  
  public static int zzb(int paramInt, double paramDouble) {
    return zzkb(paramInt) + 8;
  }
  
  public static int zzb(int paramInt, zzeuk paramzzeuk) {
    paramInt = zzkb(paramInt);
    int i = paramzzeuk.size();
    return paramInt + zzkd(i) + i;
  }
  
  public static int zzb(int paramInt, zzewl paramzzewl) {
    return zzkb(paramInt) + zze(paramzzewl);
  }
  
  public static zzeuy zzb(OutputStream paramOutputStream, int paramInt) {
    return new zzd(paramOutputStream, paramInt);
  }
  
  public static zzeuy zzbc(byte[] paramArrayOfbyte) {
    return zzg(paramArrayOfbyte, 0, paramArrayOfbyte.length);
  }
  
  public static int zzbd(byte[] paramArrayOfbyte) {
    int i = paramArrayOfbyte.length;
    return zzkd(i) + i;
  }
  
  public static int zzc(int paramInt, long paramLong) {
    return zzkb(paramInt) + zzcv(paramLong);
  }
  
  public static int zzcu(long paramLong) {
    return zzcv(paramLong);
  }
  
  public static int zzcv(long paramLong) {
    if ((0xFFFFFFFFFFFFFF80L & paramLong) == 0L)
      return 1; 
    if (paramLong < 0L)
      return 10; 
    if ((0xFFFFFFF800000000L & paramLong) != 0L) {
      i = 6;
      paramLong >>>= 28L;
    } else {
      i = 2;
    } 
    int j = i;
    long l = paramLong;
    if ((0xFFFFFFFFFFE00000L & paramLong) != 0L) {
      j = i + 2;
      l = paramLong >>> 14L;
    } 
    int i = j;
    if ((l & 0xFFFFFFFFFFFFC000L) != 0L)
      i = j + 1; 
    return i;
  }
  
  public static int zzcw(long paramLong) {
    return zzcv(zzcz(paramLong));
  }
  
  public static int zzcx(long paramLong) {
    return 8;
  }
  
  public static int zzcy(long paramLong) {
    return 8;
  }
  
  public static int zzcy(boolean paramBoolean) {
    return 1;
  }
  
  private static long zzcz(long paramLong) {
    return paramLong >> 63L ^ paramLong << 1L;
  }
  
  public static int zzd(int paramInt, long paramLong) {
    return zzkb(paramInt) + zzcv(paramLong);
  }
  
  public static int zze(int paramInt, long paramLong) {
    return zzkb(paramInt) + 8;
  }
  
  public static int zze(zzewl paramzzewl) {
    int i = paramzzewl.zzhi();
    return zzkd(i) + i;
  }
  
  public static int zzf(float paramFloat) {
    return 4;
  }
  
  @Deprecated
  public static int zzf(zzewl paramzzewl) {
    return paramzzewl.zzhi();
  }
  
  public static zzeuy zzg(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    return new zzb(paramArrayOfbyte, paramInt1, paramInt2);
  }
  
  static int zzjw(int paramInt) {
    return (paramInt > 4096) ? 4096 : paramInt;
  }
  
  public static int zzkb(int paramInt) {
    return zzkd(paramInt << 3);
  }
  
  public static int zzkc(int paramInt) {
    return (paramInt >= 0) ? zzkd(paramInt) : 10;
  }
  
  public static int zzkd(int paramInt) {
    return ((paramInt & 0xFFFFFF80) == 0) ? 1 : (((paramInt & 0xFFFFC000) == 0) ? 2 : (((0xFFE00000 & paramInt) == 0) ? 3 : (((paramInt & 0xF0000000) == 0) ? 4 : 5)));
  }
  
  public static int zzke(int paramInt) {
    return zzkd(zzkj(paramInt));
  }
  
  public static int zzkf(int paramInt) {
    return 4;
  }
  
  public static int zzkg(int paramInt) {
    return 4;
  }
  
  public static int zzkh(int paramInt) {
    return zzkc(paramInt);
  }
  
  static int zzki(int paramInt) {
    return zzkd(paramInt) + paramInt;
  }
  
  private static int zzkj(int paramInt) {
    return paramInt >> 31 ^ paramInt << 1;
  }
  
  public static int zzm(double paramDouble) {
    return 8;
  }
  
  public static int zzm(int paramInt, boolean paramBoolean) {
    return zzkb(paramInt) + 1;
  }
  
  public static int zzn(int paramInt, String paramString) {
    return zzkb(paramInt) + zztk(paramString);
  }
  
  public static int zztk(String paramString) {
    int i;
    try {
      i = zzexo.zzc(paramString);
    } catch (zzexr zzexr) {
      i = (paramString.getBytes(zzevu.UTF_8)).length;
    } 
    return zzkd(i) + i;
  }
  
  public abstract void flush() throws IOException;
  
  public abstract void write(byte paramByte) throws IOException;
  
  public abstract void write(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException;
  
  public final void zza(int paramInt, double paramDouble) throws IOException {
    zzb(paramInt, Double.doubleToRawLongBits(paramDouble));
  }
  
  public abstract void zza(int paramInt, long paramLong) throws IOException;
  
  public abstract void zza(int paramInt, zzeuk paramzzeuk) throws IOException;
  
  public abstract void zza(int paramInt, zzewl paramzzewl) throws IOException;
  
  final void zza(String paramString, zzexr paramzzexr) throws IOException {
    logger.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", paramzzexr);
    byte[] arrayOfByte = paramString.getBytes(zzevu.UTF_8);
    try {
      zzjy(arrayOfByte.length);
      zzc(arrayOfByte, 0, arrayOfByte.length);
      return;
    } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
      throw new zzc(indexOutOfBoundsException);
    } catch (zzc zzc) {
      throw zzc;
    } 
  }
  
  public abstract void zzam(zzeuk paramzzeuk) throws IOException;
  
  public abstract void zzb(int paramInt, long paramLong) throws IOException;
  
  public abstract void zzcr(long paramLong) throws IOException;
  
  public final void zzcs(long paramLong) throws IOException {
    zzcr(zzcz(paramLong));
  }
  
  public abstract void zzct(long paramLong) throws IOException;
  
  public abstract int zzctm();
  
  public final void zzctn() {
    if (zzctm() == 0)
      return; 
    throw new IllegalStateException("Did not write as much data as expected.");
  }
  
  public abstract void zzd(zzewl paramzzewl) throws IOException;
  
  abstract void zzh(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException;
  
  public abstract void zzjx(int paramInt) throws IOException;
  
  public abstract void zzjy(int paramInt) throws IOException;
  
  public final void zzjz(int paramInt) throws IOException {
    zzjy(zzkj(paramInt));
  }
  
  public abstract void zzka(int paramInt) throws IOException;
  
  public abstract void zzl(int paramInt, boolean paramBoolean) throws IOException;
  
  public abstract void zzm(int paramInt, String paramString) throws IOException;
  
  public abstract void zztj(String paramString) throws IOException;
  
  public abstract void zzw(int paramInt1, int paramInt2) throws IOException;
  
  public abstract void zzx(int paramInt1, int paramInt2) throws IOException;
  
  public abstract void zzy(int paramInt1, int paramInt2) throws IOException;
  
  public abstract void zzz(int paramInt1, int paramInt2) throws IOException;
  
  static abstract class zza extends zzeuy {
    final byte[] buffer;
    
    final int limit;
    
    int position;
    
    int zzons;
    
    zza(int param1Int) {
      super(null);
      if (param1Int >= 0) {
        byte[] arrayOfByte = new byte[Math.max(param1Int, 20)];
        this.buffer = arrayOfByte;
        this.limit = arrayOfByte.length;
        return;
      } 
      throw new IllegalArgumentException("bufferSize must be >= 0");
    }
    
    final void zzae(int param1Int1, int param1Int2) {
      zzkk(param1Int1 << 3 | param1Int2);
    }
    
    final void zzb(byte param1Byte) {
      byte[] arrayOfByte = this.buffer;
      int i = this.position;
      this.position = i + 1;
      arrayOfByte[i] = (byte)param1Byte;
      this.zzons++;
    }
    
    public final int zzctm() {
      throw new UnsupportedOperationException("spaceLeft() can only be called on CodedOutputStreams that are writing to a flat array or ByteBuffer.");
    }
    
    final void zzda(long param1Long) {
      long l = param1Long;
      if (zzeuy.zzcto()) {
        l = this.position;
        while (true) {
          int i;
          if ((param1Long & 0xFFFFFFFFFFFFFF80L) == 0L) {
            byte[] arrayOfByte = this.buffer;
            i = this.position;
            this.position = i + 1;
            zzexm.zza(arrayOfByte, i, (byte)(int)param1Long);
            i = (int)(this.position - l);
            i = this.zzons + i;
          } else {
            byte[] arrayOfByte = this.buffer;
            i = this.position;
            this.position = i + 1;
            zzexm.zza(arrayOfByte, i, (byte)((int)param1Long & 0x7F | 0x80));
            param1Long >>>= 7L;
            continue;
          } 
          this.zzons = i;
          return;
        } 
      } 
      while (true) {
        int i;
        if ((l & 0xFFFFFFFFFFFFFF80L) == 0L) {
          byte[] arrayOfByte = this.buffer;
          i = this.position;
          this.position = i + 1;
          arrayOfByte[i] = (byte)(byte)(int)l;
          i = this.zzons + 1;
        } else {
          byte[] arrayOfByte = this.buffer;
          i = this.position;
          this.position = i + 1;
          arrayOfByte[i] = (byte)(byte)((int)l & 0x7F | 0x80);
          this.zzons++;
          l >>>= 7L;
          continue;
        } 
        this.zzons = i;
        return;
      } 
    }
    
    final void zzdb(long param1Long) {
      byte[] arrayOfByte = this.buffer;
      int i = this.position;
      int j = i + 1;
      arrayOfByte[i] = (byte)(byte)(int)(param1Long & 0xFFL);
      i = j + 1;
      arrayOfByte[j] = (byte)(byte)(int)(param1Long >> 8L & 0xFFL);
      j = i + 1;
      arrayOfByte[i] = (byte)(byte)(int)(param1Long >> 16L & 0xFFL);
      i = j + 1;
      arrayOfByte[j] = (byte)(byte)(int)(0xFFL & param1Long >> 24L);
      j = i + 1;
      arrayOfByte[i] = (byte)(byte)(int)(param1Long >> 32L);
      i = j + 1;
      arrayOfByte[j] = (byte)(byte)(int)(param1Long >> 40L);
      j = i + 1;
      arrayOfByte[i] = (byte)(byte)(int)(param1Long >> 48L);
      this.position = j + 1;
      arrayOfByte[j] = (byte)(byte)(int)(param1Long >> 56L);
      this.zzons += 8;
    }
    
    final void zzkk(int param1Int) {
      int i = param1Int;
      if (zzeuy.zzcto()) {
        long l = this.position;
        while (true) {
          if ((param1Int & 0xFFFFFF80) == 0) {
            byte[] arrayOfByte1 = this.buffer;
            i = this.position;
            this.position = i + 1;
            zzexm.zza(arrayOfByte1, i, (byte)param1Int);
            param1Int = (int)(this.position - l);
            this.zzons += param1Int;
            return;
          } 
          byte[] arrayOfByte = this.buffer;
          i = this.position;
          this.position = i + 1;
          zzexm.zza(arrayOfByte, i, (byte)(param1Int & 0x7F | 0x80));
          param1Int >>>= 7;
        } 
      } 
      while (true) {
        if ((i & 0xFFFFFF80) == 0) {
          byte[] arrayOfByte1 = this.buffer;
          param1Int = this.position;
          this.position = param1Int + 1;
          arrayOfByte1[param1Int] = (byte)(byte)i;
          this.zzons++;
          return;
        } 
        byte[] arrayOfByte = this.buffer;
        param1Int = this.position;
        this.position = param1Int + 1;
        arrayOfByte[param1Int] = (byte)(byte)(i & 0x7F | 0x80);
        this.zzons++;
        i >>>= 7;
      } 
    }
    
    final void zzkl(int param1Int) {
      byte[] arrayOfByte = this.buffer;
      int i = this.position;
      int j = i + 1;
      arrayOfByte[i] = (byte)(byte)param1Int;
      i = j + 1;
      arrayOfByte[j] = (byte)(byte)(param1Int >> 8);
      j = i + 1;
      arrayOfByte[i] = (byte)(byte)(param1Int >> 16);
      this.position = j + 1;
      arrayOfByte[j] = (byte)(param1Int >> 24);
      this.zzons += 4;
    }
  }
  
  static class zzb extends zzeuy {
    private final byte[] buffer;
    
    private final int limit;
    
    private final int offset;
    
    private int position;
    
    zzb(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) {
      super(null);
      if (param1ArrayOfbyte != null) {
        int i = param1ArrayOfbyte.length;
        int j = param1Int1 + param1Int2;
        if ((param1Int1 | param1Int2 | i - j) >= 0) {
          this.buffer = param1ArrayOfbyte;
          this.offset = param1Int1;
          this.position = param1Int1;
          this.limit = j;
          return;
        } 
        throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", new Object[] { Integer.valueOf(param1ArrayOfbyte.length), Integer.valueOf(param1Int1), Integer.valueOf(param1Int2) }));
      } 
      throw new NullPointerException("buffer");
    }
    
    public void flush() {}
    
    public final void write(byte param1Byte) throws IOException {
      try {
        byte[] arrayOfByte = this.buffer;
        int i = this.position;
        this.position = i + 1;
        arrayOfByte[i] = (byte)param1Byte;
        return;
      } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
        throw new zzeuy.zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[] { Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1) }), indexOutOfBoundsException);
      } 
    }
    
    public final void write(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) throws IOException {
      try {
        System.arraycopy(param1ArrayOfbyte, param1Int1, this.buffer, this.position, param1Int2);
        this.position += param1Int2;
        return;
      } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
        throw new zzeuy.zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[] { Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(param1Int2) }), indexOutOfBoundsException);
      } 
    }
    
    public final void zza(int param1Int, long param1Long) throws IOException {
      super.zzw(param1Int, 0);
      super.zzcr(param1Long);
    }
    
    public final void zza(int param1Int, zzeuk param1zzeuk) throws IOException {
      super.zzw(param1Int, 2);
      super.zzam(param1zzeuk);
    }
    
    public final void zza(int param1Int, zzewl param1zzewl) throws IOException {
      super.zzw(param1Int, 2);
      super.zzd(param1zzewl);
    }
    
    public final void zzam(zzeuk param1zzeuk) throws IOException {
      super.zzjy(param1zzeuk.size());
      param1zzeuk.zza(this);
    }
    
    public final void zzb(int param1Int, long param1Long) throws IOException {
      super.zzw(param1Int, 1);
      super.zzct(param1Long);
    }
    
    public final void zzc(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) throws IOException {
      super.write(param1ArrayOfbyte, param1Int1, param1Int2);
    }
    
    public final void zzcr(long param1Long) throws IOException {
      long l = param1Long;
      if (zzeuy.zzcto()) {
        l = param1Long;
        if (super.zzctm() >= 10)
          while (true) {
            if ((param1Long & 0xFFFFFFFFFFFFFF80L) == 0L) {
              byte[] arrayOfByte1 = this.buffer;
              int j = this.position;
              this.position = j + 1;
              zzexm.zza(arrayOfByte1, j, (byte)(int)param1Long);
              return;
            } 
            byte[] arrayOfByte = this.buffer;
            int i = this.position;
            this.position = i + 1;
            zzexm.zza(arrayOfByte, i, (byte)((int)param1Long & 0x7F | 0x80));
            param1Long >>>= 7L;
          }  
      } 
      while (true) {
        if ((l & 0xFFFFFFFFFFFFFF80L) == 0L)
          try {
            byte[] arrayOfByte1 = this.buffer;
            int j = this.position;
            this.position = j + 1;
            arrayOfByte1[j] = (byte)(byte)(int)l;
            return;
          } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            zzeuy.zzc zzc = new zzeuy.zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[] { Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1) }), indexOutOfBoundsException);
            throw zzc;
          }  
        byte[] arrayOfByte = this.buffer;
        int i = this.position;
        this.position = i + 1;
        arrayOfByte[i] = (byte)(byte)((int)l & 0x7F | 0x80);
        l >>>= 7L;
      } 
    }
    
    public final void zzct(long param1Long) throws IOException {
      try {
        byte[] arrayOfByte = this.buffer;
        int i = this.position;
        int j = i + 1;
        arrayOfByte[i] = (byte)(byte)(int)param1Long;
        i = j + 1;
        arrayOfByte[j] = (byte)(byte)(int)(param1Long >> 8L);
        j = i + 1;
        arrayOfByte[i] = (byte)(byte)(int)(param1Long >> 16L);
        i = j + 1;
        arrayOfByte[j] = (byte)(byte)(int)(param1Long >> 24L);
        j = i + 1;
        arrayOfByte[i] = (byte)(byte)(int)(param1Long >> 32L);
        i = j + 1;
        arrayOfByte[j] = (byte)(byte)(int)(param1Long >> 40L);
        j = i + 1;
        arrayOfByte[i] = (byte)(byte)(int)(param1Long >> 48L);
        this.position = j + 1;
        arrayOfByte[j] = (byte)(byte)(int)(param1Long >> 56L);
        return;
      } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
        throw new zzeuy.zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[] { Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1) }), indexOutOfBoundsException);
      } 
    }
    
    public final int zzctm() {
      return this.limit - this.position;
    }
    
    public final void zzd(zzewl param1zzewl) throws IOException {
      super.zzjy(param1zzewl.zzhi());
      param1zzewl.zza(this);
    }
    
    public final void zzh(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) throws IOException {
      super.zzjy(param1Int2);
      super.write(param1ArrayOfbyte, 0, param1Int2);
    }
    
    public final void zzjx(int param1Int) throws IOException {
      if (param1Int >= 0) {
        super.zzjy(param1Int);
        return;
      } 
      super.zzcr(param1Int);
    }
    
    public final void zzjy(int param1Int) throws IOException {
      int i = param1Int;
      if (zzeuy.zzcto()) {
        i = param1Int;
        if (super.zzctm() >= 10)
          while (true) {
            if ((param1Int & 0xFFFFFF80) == 0) {
              byte[] arrayOfByte1 = this.buffer;
              i = this.position;
              this.position = i + 1;
              zzexm.zza(arrayOfByte1, i, (byte)param1Int);
              return;
            } 
            byte[] arrayOfByte = this.buffer;
            i = this.position;
            this.position = i + 1;
            zzexm.zza(arrayOfByte, i, (byte)(param1Int & 0x7F | 0x80));
            param1Int >>>= 7;
          }  
      } 
      while (true) {
        if ((i & 0xFFFFFF80) == 0)
          try {
            byte[] arrayOfByte1 = this.buffer;
            param1Int = this.position;
            this.position = param1Int + 1;
            arrayOfByte1[param1Int] = (byte)(byte)i;
            return;
          } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            zzeuy.zzc zzc = new zzeuy.zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[] { Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1) }), indexOutOfBoundsException);
            throw zzc;
          }  
        byte[] arrayOfByte = this.buffer;
        param1Int = this.position;
        this.position = param1Int + 1;
        arrayOfByte[param1Int] = (byte)(byte)(i & 0x7F | 0x80);
        i >>>= 7;
      } 
    }
    
    public final void zzka(int param1Int) throws IOException {
      try {
        byte[] arrayOfByte = this.buffer;
        int i = this.position;
        int j = i + 1;
        arrayOfByte[i] = (byte)(byte)param1Int;
        i = j + 1;
        arrayOfByte[j] = (byte)(byte)(param1Int >> 8);
        j = i + 1;
        arrayOfByte[i] = (byte)(byte)(param1Int >> 16);
        this.position = j + 1;
        arrayOfByte[j] = (byte)(param1Int >> 24);
        return;
      } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
        throw new zzeuy.zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[] { Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1) }), indexOutOfBoundsException);
      } 
    }
    
    public final void zzl(int param1Int, boolean param1Boolean) throws IOException {
      super.zzw(param1Int, 0);
      super.write((byte)param1Boolean);
    }
    
    public final void zzm(int param1Int, String param1String) throws IOException {
      super.zzw(param1Int, 2);
      super.zztj(param1String);
    }
    
    public final void zztj(String param1String) throws IOException {
      int i = this.position;
      try {
        int j = zzeuy.zzkd(param1String.length() * 3);
        int k = zzeuy.zzkd(param1String.length());
        if (k == j) {
          j = i + k;
          this.position = j;
          j = zzexo.zza(param1String, this.buffer, j, super.zzctm());
          this.position = i;
          super.zzjy(j - i - k);
          this.position = j;
          return;
        } 
        super.zzjy(zzexo.zzc(param1String));
        this.position = zzexo.zza(param1String, this.buffer, this.position, super.zzctm());
        return;
      } catch (zzexr zzexr) {
        this.position = i;
        zza(param1String, zzexr);
        return;
      } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
        throw new zzeuy.zzc(indexOutOfBoundsException);
      } 
    }
    
    public final void zzw(int param1Int1, int param1Int2) throws IOException {
      super.zzjy(param1Int1 << 3 | param1Int2);
    }
    
    public final void zzx(int param1Int1, int param1Int2) throws IOException {
      super.zzw(param1Int1, 0);
      super.zzjx(param1Int2);
    }
    
    public final void zzy(int param1Int1, int param1Int2) throws IOException {
      super.zzw(param1Int1, 0);
      super.zzjy(param1Int2);
    }
    
    public final void zzz(int param1Int1, int param1Int2) throws IOException {
      super.zzw(param1Int1, 5);
      super.zzka(param1Int2);
    }
  }
  
  public static final class zzc extends IOException {
    zzc() {
      super("CodedOutputStream was writing to a flat byte array and ran out of space.");
    }
    
    zzc(String param1String, Throwable param1Throwable) {
      super(param1String, param1Throwable);
    }
    
    zzc(Throwable param1Throwable) {
      super("CodedOutputStream was writing to a flat byte array and ran out of space.", param1Throwable);
    }
  }
  
  static final class zzd extends zza {
    private final OutputStream out;
    
    zzd(OutputStream param1OutputStream, int param1Int) {
      super(param1Int);
      if (param1OutputStream != null) {
        this.out = param1OutputStream;
        return;
      } 
      throw new NullPointerException("out");
    }
    
    private final void doFlush() throws IOException {
      this.out.write(this.buffer, 0, this.position);
      this.position = 0;
    }
    
    private final void zzkm(int param1Int) throws IOException {
      if (this.limit - this.position < param1Int)
        doFlush(); 
    }
    
    public final void flush() throws IOException {
      if (this.position > 0)
        doFlush(); 
    }
    
    public final void write(byte param1Byte) throws IOException {
      if (this.position == this.limit)
        doFlush(); 
      zzb(param1Byte);
    }
    
    public final void write(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) throws IOException {
      int i = this.limit;
      int j = this.position;
      if (i - j >= param1Int2) {
        System.arraycopy(param1ArrayOfbyte, param1Int1, this.buffer, j, param1Int2);
        this.position += param1Int2;
      } else {
        i -= j;
        System.arraycopy(param1ArrayOfbyte, param1Int1, this.buffer, j, i);
        param1Int1 += i;
        param1Int2 -= i;
        this.position = this.limit;
        this.zzons += i;
        doFlush();
        if (param1Int2 <= this.limit) {
          System.arraycopy(param1ArrayOfbyte, param1Int1, this.buffer, 0, param1Int2);
          this.position = param1Int2;
        } else {
          this.out.write(param1ArrayOfbyte, param1Int1, param1Int2);
        } 
      } 
      this.zzons += param1Int2;
    }
    
    public final void zza(int param1Int, long param1Long) throws IOException {
      zzkm(20);
      zzae(param1Int, 0);
      zzda(param1Long);
    }
    
    public final void zza(int param1Int, zzeuk param1zzeuk) throws IOException {
      super.zzw(param1Int, 2);
      super.zzam(param1zzeuk);
    }
    
    public final void zza(int param1Int, zzewl param1zzewl) throws IOException {
      super.zzw(param1Int, 2);
      super.zzd(param1zzewl);
    }
    
    public final void zzam(zzeuk param1zzeuk) throws IOException {
      super.zzjy(param1zzeuk.size());
      param1zzeuk.zza(this);
    }
    
    public final void zzb(int param1Int, long param1Long) throws IOException {
      zzkm(18);
      zzae(param1Int, 1);
      zzdb(param1Long);
    }
    
    public final void zzc(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) throws IOException {
      super.write(param1ArrayOfbyte, param1Int1, param1Int2);
    }
    
    public final void zzcr(long param1Long) throws IOException {
      zzkm(10);
      zzda(param1Long);
    }
    
    public final void zzct(long param1Long) throws IOException {
      zzkm(8);
      zzdb(param1Long);
    }
    
    public final void zzd(zzewl param1zzewl) throws IOException {
      super.zzjy(param1zzewl.zzhi());
      param1zzewl.zza(this);
    }
    
    public final void zzh(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) throws IOException {
      super.zzjy(param1Int2);
      super.write(param1ArrayOfbyte, 0, param1Int2);
    }
    
    public final void zzjx(int param1Int) throws IOException {
      if (param1Int >= 0) {
        super.zzjy(param1Int);
        return;
      } 
      super.zzcr(param1Int);
    }
    
    public final void zzjy(int param1Int) throws IOException {
      zzkm(10);
      zzkk(param1Int);
    }
    
    public final void zzka(int param1Int) throws IOException {
      zzkm(4);
      zzkl(param1Int);
    }
    
    public final void zzl(int param1Int, boolean param1Boolean) throws IOException {
      zzkm(11);
      zzae(param1Int, 0);
      zzb((byte)param1Boolean);
    }
    
    public final void zzm(int param1Int, String param1String) throws IOException {
      super.zzw(param1Int, 2);
      super.zztj(param1String);
    }
    
    public final void zztj(String param1String) throws IOException {
      try {
        int i = param1String.length() * 3;
        int j = zzeuy.zzkd(i);
        int k = j + i;
        int m = this.limit;
        if (k > m) {
          byte[] arrayOfByte = new byte[i];
          j = zzexo.zza(param1String, arrayOfByte, 0, i);
          super.zzjy(j);
          super.zzc(arrayOfByte, 0, j);
          return;
        } 
        if (k > m - this.position)
          doFlush(); 
        i = zzeuy.zzkd(param1String.length());
        m = this.position;
        if (i == j) {
          j = m + i;
          try {
            this.position = j;
            k = zzexo.zza(param1String, this.buffer, j, this.limit - j);
            this.position = m;
            j = k - m - i;
            zzkk(j);
            this.position = k;
            this.zzons += j;
            return;
          } catch (zzexr zzexr) {
            this.zzons -= this.position - m;
            this.position = m;
            throw zzexr;
          } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
            zzeuy.zzc zzc = new zzeuy.zzc();
            this(arrayIndexOutOfBoundsException);
            throw zzc;
          } 
        } 
        j = zzexo.zzc(param1String);
        zzkk(j);
        this.position = zzexo.zza(param1String, this.buffer, this.position, j);
        this.zzons += j;
        return;
      } catch (zzexr zzexr) {
        zza(param1String, zzexr);
        return;
      } 
    }
    
    public final void zzw(int param1Int1, int param1Int2) throws IOException {
      super.zzjy(param1Int1 << 3 | param1Int2);
    }
    
    public final void zzx(int param1Int1, int param1Int2) throws IOException {
      zzkm(20);
      zzae(param1Int1, 0);
      if (param1Int2 >= 0) {
        zzkk(param1Int2);
        return;
      } 
      zzda(param1Int2);
    }
    
    public final void zzy(int param1Int1, int param1Int2) throws IOException {
      zzkm(20);
      zzae(param1Int1, 0);
      zzkk(param1Int2);
    }
    
    public final void zzz(int param1Int1, int param1Int2) throws IOException {
      zzkm(14);
      zzae(param1Int1, 5);
      zzkl(param1Int2);
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzeuy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */