package com.google.android.gms.internal;

import java.util.AbstractMap;
import java.util.Iterator;
import java.util.Map;

final class zzdor implements Iterator<Map.Entry<Object, Object>> {
  private int zzlsf;
  
  zzdor(zzdoq paramzzdoq, int paramInt, boolean paramBoolean) {
    this.zzlsf = paramInt;
  }
  
  public final boolean hasNext() {
    return this.zzlsh ? ((this.zzlsf >= 0)) : ((this.zzlsf < (zzdoq.zza(this.zzlsi)).length));
  }
  
  public final void remove() {
    throw new UnsupportedOperationException("Can't remove elements from ImmutableSortedMap");
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzdor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */