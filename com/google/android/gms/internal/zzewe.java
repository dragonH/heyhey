package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.Map;

final class zzewe<K> implements Iterator<Map.Entry<K, Object>> {
  private Iterator<Map.Entry<K, Object>> zzlsn;
  
  public zzewe(Iterator<Map.Entry<K, Object>> paramIterator) {
    this.zzlsn = paramIterator;
  }
  
  public final boolean hasNext() {
    return this.zzlsn.hasNext();
  }
  
  public final void remove() {
    this.zzlsn.remove();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzewe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */