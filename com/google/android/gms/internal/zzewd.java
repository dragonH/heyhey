package com.google.android.gms.internal;

import java.util.Map;

final class zzewd<K> implements Map.Entry<K, Object> {
  private Map.Entry<K, zzewb> zzoph;
  
  private zzewd(Map.Entry<K, zzewb> paramEntry) {
    this.zzoph = paramEntry;
  }
  
  public final K getKey() {
    return this.zzoph.getKey();
  }
  
  public final Object getValue() {
    return ((zzewb)this.zzoph.getValue() == null) ? null : zzewb.zzcuv();
  }
  
  public final Object setValue(Object paramObject) {
    if (paramObject instanceof zzewl)
      return ((zzewb)this.zzoph.getValue()).zzj((zzewl)paramObject); 
    throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzewd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */