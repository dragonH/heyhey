package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.NoSuchElementException;

final class zzeul implements Iterator {
  private final int limit;
  
  private int position = 0;
  
  zzeul(zzeuk paramzzeuk) {
    this.limit = paramzzeuk.size();
  }
  
  private final byte nextByte() {
    try {
      zzeuk zzeuk1 = this.zzomz;
      int i = this.position;
      this.position = i + 1;
      return zzeuk1.zzji(i);
    } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
      throw new NoSuchElementException(indexOutOfBoundsException.getMessage());
    } 
  }
  
  public final boolean hasNext() {
    return (this.position < this.limit);
  }
  
  public final void remove() {
    throw new UnsupportedOperationException();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzeul.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */