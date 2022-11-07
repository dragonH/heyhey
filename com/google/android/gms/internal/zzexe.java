package com.google.android.gms.internal;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;

final class zzexe extends AbstractSet<Map.Entry<Object, Object>> {
  private zzexe(zzewx paramzzewx) {}
  
  public final void clear() {
    this.zzoqq.clear();
  }
  
  public final boolean contains(Object paramObject) {
    Map.Entry entry = (Map.Entry)paramObject;
    paramObject = this.zzoqq.get(entry.getKey());
    entry = (Map.Entry)entry.getValue();
    return (paramObject == entry || (paramObject != null && paramObject.equals(entry)));
  }
  
  public final Iterator<Map.Entry<Object, Object>> iterator() {
    return new zzexd(this.zzoqq, null);
  }
  
  public final boolean remove(Object paramObject) {
    paramObject = paramObject;
    if (contains(paramObject)) {
      this.zzoqq.remove(paramObject.getKey());
      return true;
    } 
    return false;
  }
  
  public final int size() {
    return this.zzoqq.size();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzexe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */