package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.Map;

final class zzexd implements Iterator<Map.Entry<Object, Object>> {
  private int pos = -1;
  
  private boolean zzoqr;
  
  private Iterator<Map.Entry<Object, Object>> zzoqs;
  
  private zzexd(zzewx paramzzewx) {}
  
  private final Iterator<Map.Entry<Object, Object>> zzcvk() {
    if (this.zzoqs == null)
      this.zzoqs = zzewx.zzc(this.zzoqq).entrySet().iterator(); 
    return this.zzoqs;
  }
  
  public final boolean hasNext() {
    return (this.pos + 1 < zzewx.zzb(this.zzoqq).size() || zzcvk().hasNext());
  }
  
  public final void remove() {
    if (this.zzoqr) {
      this.zzoqr = false;
      zzewx.zza(this.zzoqq);
      if (this.pos < zzewx.zzb(this.zzoqq).size()) {
        zzewx zzewx1 = this.zzoqq;
        int i = this.pos;
        this.pos = i - 1;
        zzewx.zza(zzewx1, i);
        return;
      } 
      zzcvk().remove();
      return;
    } 
    throw new IllegalStateException("remove() was called before next()");
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzexd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */