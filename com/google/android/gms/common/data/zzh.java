package com.google.android.gms.common.data;

import java.util.NoSuchElementException;

public final class zzh<T> extends zzb<T> {
  private T zzfra;
  
  public zzh(DataBuffer<T> paramDataBuffer) {
    super(paramDataBuffer);
  }
  
  public final T next() {
    if (hasNext()) {
      int j = this.zzfqf + 1;
      this.zzfqf = j;
      if (j == 0) {
        T t = this.zzfqe.get(0);
        this.zzfra = t;
        if (!(t instanceof zzc)) {
          String str = String.valueOf(this.zzfra.getClass());
          StringBuilder stringBuilder1 = new StringBuilder(str.length() + 44);
          stringBuilder1.append("DataBuffer reference of type ");
          stringBuilder1.append(str);
          stringBuilder1.append(" is not movable");
          throw new IllegalStateException(stringBuilder1.toString());
        } 
      } else {
        ((zzc)this.zzfra).zzbv(j);
      } 
      return this.zzfra;
    } 
    int i = this.zzfqf;
    StringBuilder stringBuilder = new StringBuilder(46);
    stringBuilder.append("Cannot advance the iterator beyond ");
    stringBuilder.append(i);
    throw new NoSuchElementException(stringBuilder.toString());
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/data/zzh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */