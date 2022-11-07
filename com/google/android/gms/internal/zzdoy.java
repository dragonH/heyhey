package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.Map;

final class zzdoy<T> implements Iterator<T> {
  private Iterator<Map.Entry<T, Void>> zzlsn;
  
  public zzdoy(Iterator<Map.Entry<T, Void>> paramIterator) {
    this.zzlsn = paramIterator;
  }
  
  public final boolean hasNext() {
    return this.zzlsn.hasNext();
  }
  
  public final T next() {
    return (T)((Map.Entry)this.zzlsn.next()).getKey();
  }
  
  public final void remove() {
    this.zzlsn.remove();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzdoy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */