package com.google.android.gms.internal;

import java.util.Collections;
import java.util.List;
import java.util.Map;

final class zzewy extends zzewx<Object, Object> {
  zzewy(int paramInt) {
    super(paramInt, null);
  }
  
  public final void zzbhs() {
    if (!isImmutable()) {
      for (byte b = 0; b < zzcve(); b++) {
        Map.Entry<K, V> entry = zzkv(b);
        if (((zzevg)entry.getKey()).zzctx())
          entry.setValue((V)Collections.unmodifiableList((List)entry.getValue())); 
      } 
      for (Map.Entry<K, V> entry : zzcvf()) {
        if (((zzevg)entry.getKey()).zzctx())
          entry.setValue(Collections.unmodifiableList((List)entry.getValue())); 
      } 
    } 
    super.zzbhs();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzewy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */