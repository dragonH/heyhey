package com.google.android.gms.internal;

import java.io.IOException;
import java.util.ArrayList;

final class zzews extends zzeuk {
  private static final int[] zzopt;
  
  private final int zzopu;
  
  private final zzeuk zzopv;
  
  private final zzeuk zzopw;
  
  private final int zzopx;
  
  private final int zzopy;
  
  static {
    ArrayList<Integer> arrayList = new ArrayList();
    int i = 1;
    int j = 1;
    while (true) {
      int k = j;
      if (i) {
        arrayList.add(Integer.valueOf(i));
        j = i;
        i = k + i;
        continue;
      } 
      arrayList.add(Integer.valueOf(2147483647));
      zzopt = new int[arrayList.size()];
      i = 0;
      while (true) {
        int[] arrayOfInt = zzopt;
        if (i < arrayOfInt.length) {
          arrayOfInt[i] = ((Integer)arrayList.get(i)).intValue();
          i++;
          continue;
        } 
        break;
      } 
      return;
    } 
  }
  
  private zzews(zzeuk paramzzeuk1, zzeuk paramzzeuk2) {
    this.zzopv = paramzzeuk1;
    this.zzopw = paramzzeuk2;
    int i = paramzzeuk1.size();
    this.zzopx = i;
    this.zzopu = i + paramzzeuk2.size();
    this.zzopy = Math.max(paramzzeuk1.zzcsh(), paramzzeuk2.zzcsh()) + 1;
  }
  
  static zzeuk zza(zzeuk paramzzeuk1, zzeuk paramzzeuk2) {
    if (paramzzeuk2.size() == 0)
      return paramzzeuk1; 
    if (paramzzeuk1.size() == 0)
      return paramzzeuk2; 
    int i = paramzzeuk1.size() + paramzzeuk2.size();
    if (i < 128)
      return zzb(paramzzeuk1, paramzzeuk2); 
    if (paramzzeuk1 instanceof zzews) {
      zzews zzews1 = (zzews)paramzzeuk1;
      if (zzews1.zzopw.size() + paramzzeuk2.size() < 128) {
        paramzzeuk1 = zzb(zzews1.zzopw, paramzzeuk2);
        return new zzews(zzews1.zzopv, paramzzeuk1);
      } 
      if (zzews1.zzopv.zzcsh() > zzews1.zzopw.zzcsh() && zzews1.zzcsh() > paramzzeuk2.zzcsh()) {
        paramzzeuk1 = new zzews(zzews1.zzopw, paramzzeuk2);
        return new zzews(zzews1.zzopv, paramzzeuk1);
      } 
    } 
    int j = Math.max(paramzzeuk1.zzcsh(), paramzzeuk2.zzcsh());
    return (i >= zzopt[j + 1]) ? new zzews(paramzzeuk1, paramzzeuk2) : zzewu.zza(new zzewu(null), paramzzeuk1, paramzzeuk2);
  }
  
  private static zzeuk zzb(zzeuk paramzzeuk1, zzeuk paramzzeuk2) {
    int i = paramzzeuk1.size();
    int j = paramzzeuk2.size();
    byte[] arrayOfByte = new byte[i + j];
    paramzzeuk1.zza(arrayOfByte, 0, 0, i);
    paramzzeuk2.zza(arrayOfByte, 0, i, j);
    return zzeuk.zzba(arrayOfByte);
  }
  
  public final boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof zzeuk))
      return false; 
    paramObject = paramObject;
    if (this.zzopu != paramObject.size())
      return false; 
    if (this.zzopu == 0)
      return true; 
    int i = zzcsj();
    int j = paramObject.zzcsj();
    if (i != 0 && j != 0 && i != j)
      return false; 
    zzewv zzewv1 = new zzewv(this, null);
    zzeuq zzeuq = zzewv1.next();
    zzewv zzewv2 = new zzewv((zzeuk)paramObject, null);
    paramObject = zzewv2.next();
    i = 0;
    j = 0;
    int k = 0;
    while (true) {
      boolean bool;
      int m = zzeuq.size() - i;
      int n = paramObject.size() - j;
      int i1 = Math.min(m, n);
      if (i == 0) {
        bool = zzeuq.zza((zzeuk)paramObject, j, i1);
      } else {
        bool = paramObject.zza(zzeuq, i, i1);
      } 
      if (!bool)
        return false; 
      k += i1;
      int i2 = this.zzopu;
      if (k >= i2) {
        if (k == i2)
          return true; 
        throw new IllegalStateException();
      } 
      if (i1 == m) {
        zzeuq = zzewv1.next();
        i = 0;
      } else {
        i += i1;
      } 
      if (i1 == n) {
        paramObject = zzewv2.next();
        j = 0;
        continue;
      } 
      j += i1;
    } 
  }
  
  public final int size() {
    return this.zzopu;
  }
  
  final void zza(zzeuj paramzzeuj) throws IOException {
    this.zzopv.zza(paramzzeuj);
    this.zzopw.zza(paramzzeuj);
  }
  
  protected final void zzb(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, int paramInt3) {
    int i = this.zzopx;
    if (paramInt1 + paramInt3 <= i) {
      this.zzopv.zzb(paramArrayOfbyte, paramInt1, paramInt2, paramInt3);
      return;
    } 
    if (paramInt1 >= i) {
      this.zzopw.zzb(paramArrayOfbyte, paramInt1 - i, paramInt2, paramInt3);
      return;
    } 
    i -= paramInt1;
    this.zzopv.zzb(paramArrayOfbyte, paramInt1, paramInt2, i);
    this.zzopw.zzb(paramArrayOfbyte, 0, paramInt2 + i, paramInt3 - i);
  }
  
  public final zzeut zzcsg() {
    return zzeut.zzl(new zzeww(this));
  }
  
  protected final int zzcsh() {
    return this.zzopy;
  }
  
  protected final boolean zzcsi() {
    return (this.zzopu >= zzopt[this.zzopy]);
  }
  
  protected final int zzf(int paramInt1, int paramInt2, int paramInt3) {
    int i = this.zzopx;
    if (paramInt2 + paramInt3 <= i)
      return this.zzopv.zzf(paramInt1, paramInt2, paramInt3); 
    if (paramInt2 >= i)
      return this.zzopw.zzf(paramInt1, paramInt2 - i, paramInt3); 
    i -= paramInt2;
    paramInt1 = this.zzopv.zzf(paramInt1, paramInt2, i);
    return this.zzopw.zzf(paramInt1, 0, paramInt3 - i);
  }
  
  public final byte zzji(int paramInt) {
    zzeuk.zzv(paramInt, this.zzopu);
    int i = this.zzopx;
    return (paramInt < i) ? this.zzopv.zzji(paramInt) : this.zzopw.zzji(paramInt - i);
  }
  
  public final zzeuk zzu(int paramInt1, int paramInt2) {
    int i = zzeuk.zzg(paramInt1, paramInt2, this.zzopu);
    if (i == 0)
      return zzeuk.zzomx; 
    if (i == this.zzopu)
      return this; 
    i = this.zzopx;
    if (paramInt2 <= i)
      return this.zzopv.zzu(paramInt1, paramInt2); 
    if (paramInt1 >= i)
      return this.zzopw.zzu(paramInt1 - i, paramInt2 - i); 
    zzeuk zzeuk1 = this.zzopv;
    return new zzews(zzeuk1.zzu(paramInt1, zzeuk1.size()), this.zzopw.zzu(0, paramInt2 - this.zzopx));
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzews.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */