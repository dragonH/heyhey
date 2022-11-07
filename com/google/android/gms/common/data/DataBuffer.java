package com.google.android.gms.common.data;

import android.os.Bundle;
import com.google.android.gms.common.api.Releasable;
import java.util.Iterator;

public interface DataBuffer<T> extends Releasable, Iterable<T> {
  @Deprecated
  void close();
  
  T get(int paramInt);
  
  int getCount();
  
  @Deprecated
  boolean isClosed();
  
  Iterator<T> iterator();
  
  void release();
  
  Iterator<T> singleRefIterator();
  
  Bundle zzafi();
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/data/DataBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */