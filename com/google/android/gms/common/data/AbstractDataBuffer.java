package com.google.android.gms.common.data;

import android.os.Bundle;
import java.util.Iterator;

public abstract class AbstractDataBuffer<T> implements DataBuffer<T> {
  protected final DataHolder zzflf;
  
  protected AbstractDataBuffer(DataHolder paramDataHolder) {
    this.zzflf = paramDataHolder;
  }
  
  @Deprecated
  public final void close() {
    release();
  }
  
  public abstract T get(int paramInt);
  
  public int getCount() {
    DataHolder dataHolder = this.zzflf;
    return (dataHolder == null) ? 0 : dataHolder.zzfqq;
  }
  
  @Deprecated
  public boolean isClosed() {
    DataHolder dataHolder = this.zzflf;
    return (dataHolder == null || dataHolder.isClosed());
  }
  
  public Iterator<T> iterator() {
    return new zzb<T>(this);
  }
  
  public void release() {
    DataHolder dataHolder = this.zzflf;
    if (dataHolder != null)
      dataHolder.close(); 
  }
  
  public Iterator<T> singleRefIterator() {
    return new zzh<T>(this);
  }
  
  public final Bundle zzafi() {
    return this.zzflf.zzafi();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/data/AbstractDataBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */