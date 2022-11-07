package com.google.android.gms.internal;

import java.io.IOException;

public final class zzdfn extends zzeyh<zzdfn> {
  public String[] zzkyz = zzeyq.EMPTY_STRING_ARRAY;
  
  public int[] zzkza = zzeyq.zzoty;
  
  public byte[][] zzkzb = zzeyq.zzoud;
  
  public zzdfn() {
    this.zzotl = null;
    this.zzomu = -1;
  }
  
  public static zzdfn zzad(byte[] paramArrayOfbyte) throws zzeym {
    return zzeyn.<zzdfn>zza(new zzdfn(), paramArrayOfbyte);
  }
  
  public final boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof zzdfn))
      return false; 
    paramObject = paramObject;
    if (!zzeyl.equals((Object[])this.zzkyz, (Object[])((zzdfn)paramObject).zzkyz))
      return false; 
    if (!zzeyl.equals(this.zzkza, ((zzdfn)paramObject).zzkza))
      return false; 
    if (!zzeyl.zza(this.zzkzb, ((zzdfn)paramObject).zzkzb))
      return false; 
    zzeyj zzeyj = this.zzotl;
    if (zzeyj == null || zzeyj.isEmpty()) {
      paramObject = ((zzeyh)paramObject).zzotl;
      return (paramObject == null || paramObject.isEmpty());
    } 
    return this.zzotl.equals(((zzeyh)paramObject).zzotl);
  }
  
  public final int hashCode() {
    int i = zzdfn.class.getName().hashCode();
    int j = zzeyl.hashCode((Object[])this.zzkyz);
    int k = zzeyl.hashCode(this.zzkza);
    int m = zzeyl.zzd(this.zzkzb);
    zzeyj zzeyj = this.zzotl;
    if (zzeyj == null || zzeyj.isEmpty()) {
      byte b = 0;
      return ((((i + 527) * 31 + j) * 31 + k) * 31 + m) * 31 + b;
    } 
    int n = this.zzotl.hashCode();
    return ((((i + 527) * 31 + j) * 31 + k) * 31 + m) * 31 + n;
  }
  
  public final void zza(zzeyf paramzzeyf) throws IOException {
    String[] arrayOfString = this.zzkyz;
    byte b = 0;
    if (arrayOfString != null && arrayOfString.length > 0) {
      byte b1 = 0;
      while (true) {
        arrayOfString = this.zzkyz;
        if (b1 < arrayOfString.length) {
          String str = arrayOfString[b1];
          if (str != null)
            paramzzeyf.zzm(1, str); 
          b1++;
          continue;
        } 
        break;
      } 
    } 
    int[] arrayOfInt = this.zzkza;
    if (arrayOfInt != null && arrayOfInt.length > 0) {
      byte b1 = 0;
      while (true) {
        arrayOfInt = this.zzkza;
        if (b1 < arrayOfInt.length) {
          paramzzeyf.zzx(2, arrayOfInt[b1]);
          b1++;
          continue;
        } 
        break;
      } 
    } 
    byte[][] arrayOfByte = this.zzkzb;
    if (arrayOfByte != null && arrayOfByte.length > 0) {
      byte b1 = b;
      while (true) {
        arrayOfByte = this.zzkzb;
        if (b1 < arrayOfByte.length) {
          byte[] arrayOfByte1 = arrayOfByte[b1];
          if (arrayOfByte1 != null)
            paramzzeyf.zzc(3, arrayOfByte1); 
          b1++;
          continue;
        } 
        break;
      } 
    } 
    super.zza(paramzzeyf);
  }
  
  protected final int zzn() {
    int i = super.zzn();
    String[] arrayOfString = this.zzkyz;
    int j = 0;
    int k = i;
    if (arrayOfString != null) {
      k = i;
      if (arrayOfString.length > 0) {
        k = 0;
        int n = 0;
        int i1 = 0;
        while (true) {
          arrayOfString = this.zzkyz;
          if (k < arrayOfString.length) {
            String str = arrayOfString[k];
            int i2 = n;
            int i3 = i1;
            if (str != null) {
              i3 = i1 + 1;
              i2 = n + zzeyf.zztk(str);
            } 
            k++;
            n = i2;
            i1 = i3;
            continue;
          } 
          k = i + n + i1 * 1;
          break;
        } 
      } 
    } 
    int[] arrayOfInt = this.zzkza;
    int m = k;
    if (arrayOfInt != null) {
      m = k;
      if (arrayOfInt.length > 0) {
        m = 0;
        int n = 0;
        while (true) {
          arrayOfInt = this.zzkza;
          if (m < arrayOfInt.length) {
            n += zzeyf.zzkc(arrayOfInt[m]);
            m++;
            continue;
          } 
          m = k + n + arrayOfInt.length * 1;
          break;
        } 
      } 
    } 
    byte[][] arrayOfByte = this.zzkzb;
    k = m;
    if (arrayOfByte != null) {
      k = m;
      if (arrayOfByte.length > 0) {
        int i1 = 0;
        int n = 0;
        k = j;
        while (true) {
          arrayOfByte = this.zzkzb;
          if (k < arrayOfByte.length) {
            byte[] arrayOfByte1 = arrayOfByte[k];
            j = i1;
            int i2 = n;
            if (arrayOfByte1 != null) {
              i2 = n + 1;
              j = i1 + zzeyf.zzbg(arrayOfByte1);
            } 
            k++;
            i1 = j;
            n = i2;
            continue;
          } 
          k = m + i1 + n * 1;
          break;
        } 
      } 
    } 
    return k;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzdfn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */