package com.google.android.gms.internal;

public final class zzeyj implements Cloneable {
  private static final zzeyk zzotn = new zzeyk();
  
  private int mSize;
  
  private boolean zzoto = false;
  
  private int[] zzotp;
  
  private zzeyk[] zzotq;
  
  zzeyj() {
    this(10);
  }
  
  private zzeyj(int paramInt) {
    paramInt = idealIntArraySize(paramInt);
    this.zzotp = new int[paramInt];
    this.zzotq = new zzeyk[paramInt];
    this.mSize = 0;
  }
  
  private static int idealIntArraySize(int paramInt) {
    int j;
    int i = paramInt << 2;
    paramInt = 4;
    while (true) {
      j = i;
      if (paramInt < 32) {
        j = (1 << paramInt) - 12;
        if (i <= j)
          break; 
        paramInt++;
        continue;
      } 
      break;
    } 
    return j / 4;
  }
  
  private final int zzlg(int paramInt) {
    int i = this.mSize - 1;
    int j = 0;
    while (j <= i) {
      int k = j + i >>> 1;
      int m = this.zzotp[k];
      if (m < paramInt) {
        j = k + 1;
        continue;
      } 
      if (m > paramInt) {
        i = k - 1;
        continue;
      } 
      return k;
    } 
    return j ^ 0xFFFFFFFF;
  }
  
  public final boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof zzeyj))
      return false; 
    paramObject = paramObject;
    int i = this.mSize;
    if (i != ((zzeyj)paramObject).mSize)
      return false; 
    int[] arrayOfInt1 = this.zzotp;
    int[] arrayOfInt2 = ((zzeyj)paramObject).zzotp;
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
      zzeyk[] arrayOfZzeyk = this.zzotq;
      paramObject = ((zzeyj)paramObject).zzotq;
      i = this.mSize;
      b = 0;
      while (true) {
        if (b < i) {
          if (!arrayOfZzeyk[b].equals(paramObject[b])) {
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
    return false;
  }
  
  public final int hashCode() {
    int i = 17;
    for (byte b = 0; b < this.mSize; b++)
      i = (i * 31 + this.zzotp[b]) * 31 + this.zzotq[b].hashCode(); 
    return i;
  }
  
  public final boolean isEmpty() {
    return (this.mSize == 0);
  }
  
  final int size() {
    return this.mSize;
  }
  
  final void zza(int paramInt, zzeyk paramzzeyk) {
    int i = zzlg(paramInt);
    if (i >= 0) {
      this.zzotq[i] = paramzzeyk;
      return;
    } 
    i ^= 0xFFFFFFFF;
    int j = this.mSize;
    if (i < j) {
      zzeyk[] arrayOfZzeyk = this.zzotq;
      if (arrayOfZzeyk[i] == zzotn) {
        this.zzotp[i] = paramInt;
        arrayOfZzeyk[i] = paramzzeyk;
        return;
      } 
    } 
    if (j >= this.zzotp.length) {
      j = idealIntArraySize(j + 1);
      int[] arrayOfInt1 = new int[j];
      zzeyk[] arrayOfZzeyk1 = new zzeyk[j];
      int[] arrayOfInt2 = this.zzotp;
      System.arraycopy(arrayOfInt2, 0, arrayOfInt1, 0, arrayOfInt2.length);
      zzeyk[] arrayOfZzeyk2 = this.zzotq;
      System.arraycopy(arrayOfZzeyk2, 0, arrayOfZzeyk1, 0, arrayOfZzeyk2.length);
      this.zzotp = arrayOfInt1;
      this.zzotq = arrayOfZzeyk1;
    } 
    int k = this.mSize;
    if (k - i != 0) {
      int[] arrayOfInt = this.zzotp;
      j = i + 1;
      System.arraycopy(arrayOfInt, i, arrayOfInt, j, k - i);
      zzeyk[] arrayOfZzeyk = this.zzotq;
      System.arraycopy(arrayOfZzeyk, i, arrayOfZzeyk, j, this.mSize - i);
    } 
    this.zzotp[i] = paramInt;
    this.zzotq[i] = paramzzeyk;
    this.mSize++;
  }
  
  final zzeyk zzle(int paramInt) {
    paramInt = zzlg(paramInt);
    if (paramInt >= 0) {
      zzeyk zzeyk1 = this.zzotq[paramInt];
      if (zzeyk1 != zzotn)
        return zzeyk1; 
    } 
    return null;
  }
  
  final zzeyk zzlf(int paramInt) {
    return this.zzotq[paramInt];
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzeyj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */