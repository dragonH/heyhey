package com.google.android.gms.internal;

import java.io.IOException;

public final class zzezn extends zzeyh<zzezn> implements Cloneable {
  private int zzoxp = -1;
  
  private int zzoxq = 0;
  
  public zzezn() {
    this.zzotl = null;
    this.zzomu = -1;
  }
  
  private zzezn zzcww() {
    try {
      return super.zzcvz();
    } catch (CloneNotSupportedException cloneNotSupportedException) {
      throw new AssertionError(cloneNotSupportedException);
    } 
  }
  
  public final boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof zzezn))
      return false; 
    zzezn zzezn1 = (zzezn)paramObject;
    if (this.zzoxp != zzezn1.zzoxp)
      return false; 
    if (this.zzoxq != zzezn1.zzoxq)
      return false; 
    paramObject = this.zzotl;
    if (paramObject == null || paramObject.isEmpty()) {
      paramObject = zzezn1.zzotl;
      return (paramObject == null || paramObject.isEmpty());
    } 
    return this.zzotl.equals(zzezn1.zzotl);
  }
  
  public final int hashCode() {
    int i = zzezn.class.getName().hashCode();
    int j = this.zzoxp;
    int k = this.zzoxq;
    zzeyj zzeyj = this.zzotl;
    if (zzeyj == null || zzeyj.isEmpty()) {
      byte b = 0;
      return (((i + 527) * 31 + j) * 31 + k) * 31 + b;
    } 
    int m = this.zzotl.hashCode();
    return (((i + 527) * 31 + j) * 31 + k) * 31 + m;
  }
  
  public final void zza(zzeyf paramzzeyf) throws IOException {
    int i = this.zzoxp;
    if (i != -1)
      paramzzeyf.zzx(1, i); 
    i = this.zzoxq;
    if (i != 0)
      paramzzeyf.zzx(2, i); 
    super.zza(paramzzeyf);
  }
  
  protected final int zzn() {
    int i = super.zzn();
    int j = this.zzoxp;
    int k = i;
    if (j != -1)
      k = i + zzeyf.zzaa(1, j); 
    j = this.zzoxq;
    i = k;
    if (j != 0)
      i = k + zzeyf.zzaa(2, j); 
    return i;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzezn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */