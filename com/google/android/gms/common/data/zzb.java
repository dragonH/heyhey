package com.google.android.gms.common.data;

import com.google.android.gms.common.internal.zzbp;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class zzb<T> implements Iterator<T> {
  protected final DataBuffer<T> zzfqe;
  
  protected int zzfqf;
  
  public zzb(DataBuffer<T> paramDataBuffer) {
    this.zzfqe = (DataBuffer<T>)zzbp.zzu(paramDataBuffer);
    this.zzfqf = -1;
  }
  
  public boolean hasNext() {
    return (this.zzfqf < this.zzfqe.getCount() - 1);
  }
  
  public T next() {
    if (hasNext()) {
      DataBuffer<T> dataBuffer = this.zzfqe;
      int j = this.zzfqf + 1;
      this.zzfqf = j;
      return dataBuffer.get(j);
    } 
    int i = this.zzfqf;
    StringBuilder stringBuilder = new StringBuilder(46);
    stringBuilder.append("Cannot advance the iterator beyond ");
    stringBuilder.append(i);
    throw new NoSuchElementException(stringBuilder.toString());
  }
  
  public void remove() {
    throw new UnsupportedOperationException("Cannot remove elements from a DataBufferIterator");
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/data/zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */