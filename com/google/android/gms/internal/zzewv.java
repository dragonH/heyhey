package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

final class zzewv implements Iterator<zzeuq> {
  private final Stack<zzews> zzoqa = new Stack<zzews>();
  
  private zzeuq zzoqb;
  
  private zzewv(zzeuk paramzzeuk) {
    this.zzoqb = zzap(paramzzeuk);
  }
  
  private final zzeuq zzap(zzeuk paramzzeuk) {
    while (paramzzeuk instanceof zzews) {
      paramzzeuk = paramzzeuk;
      this.zzoqa.push(paramzzeuk);
      paramzzeuk = zzews.zza((zzews)paramzzeuk);
    } 
    return (zzeuq)paramzzeuk;
  }
  
  private final zzeuq zzcvc() {
    while (true) {
      if (this.zzoqa.isEmpty())
        return null; 
      zzeuq zzeuq1 = zzap(zzews.zzb(this.zzoqa.pop()));
      if (!zzeuq1.isEmpty())
        return zzeuq1; 
    } 
  }
  
  public final boolean hasNext() {
    return (this.zzoqb != null);
  }
  
  public final void remove() {
    throw new UnsupportedOperationException();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzewv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */