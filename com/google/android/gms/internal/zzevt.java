package com.google.android.gms.internal;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzevt extends zzeug<Integer> implements zzevx, zzewq, RandomAccess {
  private static final zzevt zzopc;
  
  private int size;
  
  private int[] zzopd;
  
  static {
    zzevt zzevt1 = new zzevt();
    zzopc = zzevt1;
    zzevt1.zzbhs();
  }
  
  zzevt() {
    this(new int[10], 0);
  }
  
  private zzevt(int[] paramArrayOfint, int paramInt) {
    this.zzopd = paramArrayOfint;
    this.size = paramInt;
  }
  
  private final void zzaf(int paramInt1, int paramInt2) {
    zzcsd();
    if (paramInt1 >= 0) {
      int i = this.size;
      if (paramInt1 <= i) {
        int[] arrayOfInt = this.zzopd;
        if (i < arrayOfInt.length) {
          System.arraycopy(arrayOfInt, paramInt1, arrayOfInt, paramInt1 + 1, i - paramInt1);
        } else {
          int[] arrayOfInt1 = new int[i * 3 / 2 + 1];
          System.arraycopy(arrayOfInt, 0, arrayOfInt1, 0, paramInt1);
          System.arraycopy(this.zzopd, paramInt1, arrayOfInt1, paramInt1 + 1, this.size - paramInt1);
          this.zzopd = arrayOfInt1;
        } 
        this.zzopd[paramInt1] = paramInt2;
        this.size++;
        this.modCount++;
        return;
      } 
    } 
    throw new IndexOutOfBoundsException(zzkr(paramInt1));
  }
  
  public static zzevt zzcul() {
    return zzopc;
  }
  
  private final void zzkq(int paramInt) {
    if (paramInt >= 0 && paramInt < this.size)
      return; 
    throw new IndexOutOfBoundsException(zzkr(paramInt));
  }
  
  private final String zzkr(int paramInt) {
    int i = this.size;
    StringBuilder stringBuilder = new StringBuilder(35);
    stringBuilder.append("Index:");
    stringBuilder.append(paramInt);
    stringBuilder.append(", Size:");
    stringBuilder.append(i);
    return stringBuilder.toString();
  }
  
  public final boolean addAll(Collection<? extends Integer> paramCollection) {
    zzcsd();
    zzevu.zzu(paramCollection);
    if (!(paramCollection instanceof zzevt))
      return super.addAll(paramCollection); 
    paramCollection = paramCollection;
    int i = ((zzevt)paramCollection).size;
    if (i == 0)
      return false; 
    int j = this.size;
    if (Integer.MAX_VALUE - j >= i) {
      i = j + i;
      int[] arrayOfInt = this.zzopd;
      if (i > arrayOfInt.length)
        this.zzopd = Arrays.copyOf(arrayOfInt, i); 
      System.arraycopy(((zzevt)paramCollection).zzopd, 0, this.zzopd, this.size, ((zzevt)paramCollection).size);
      this.size = i;
      this.modCount++;
      return true;
    } 
    throw new OutOfMemoryError();
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof zzevt))
      return super.equals(paramObject); 
    paramObject = paramObject;
    if (this.size != ((zzevt)paramObject).size)
      return false; 
    paramObject = ((zzevt)paramObject).zzopd;
    for (byte b = 0; b < this.size; b++) {
      if (this.zzopd[b] != paramObject[b])
        return false; 
    } 
    return true;
  }
  
  public final int getInt(int paramInt) {
    zzkq(paramInt);
    return this.zzopd[paramInt];
  }
  
  public final int hashCode() {
    int i = 1;
    for (byte b = 0; b < this.size; b++)
      i = i * 31 + this.zzopd[b]; 
    return i;
  }
  
  public final boolean remove(Object paramObject) {
    zzcsd();
    for (byte b = 0; b < this.size; b++) {
      if (paramObject.equals(Integer.valueOf(this.zzopd[b]))) {
        paramObject = this.zzopd;
        System.arraycopy(paramObject, b + 1, paramObject, b, this.size - b);
        this.size--;
        this.modCount++;
        return true;
      } 
    } 
    return false;
  }
  
  public final int size() {
    return this.size;
  }
  
  public final zzevx zzko(int paramInt) {
    if (paramInt >= this.size)
      return new zzevt(Arrays.copyOf(this.zzopd, paramInt), this.size); 
    throw new IllegalArgumentException();
  }
  
  public final void zzkp(int paramInt) {
    zzaf(this.size, paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzevt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */