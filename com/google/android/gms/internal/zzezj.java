package com.google.android.gms.internal;

import java.io.IOException;

public final class zzezj extends zzeyh<zzezj> implements Cloneable {
  private String version = "";
  
  private int zzimf = 0;
  
  private String zzowq = "";
  
  public zzezj() {
    this.zzotl = null;
    this.zzomu = -1;
  }
  
  private zzezj zzcwr() {
    try {
      return super.zzcvz();
    } catch (CloneNotSupportedException cloneNotSupportedException) {
      throw new AssertionError(cloneNotSupportedException);
    } 
  }
  
  public final boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof zzezj))
      return false; 
    paramObject = paramObject;
    if (this.zzimf != ((zzezj)paramObject).zzimf)
      return false; 
    String str = this.zzowq;
    if (str == null) {
      if (((zzezj)paramObject).zzowq != null)
        return false; 
    } else if (!str.equals(((zzezj)paramObject).zzowq)) {
      return false;
    } 
    str = this.version;
    if (str == null) {
      if (((zzezj)paramObject).version != null)
        return false; 
    } else if (!str.equals(((zzezj)paramObject).version)) {
      return false;
    } 
    zzeyj zzeyj = this.zzotl;
    if (zzeyj == null || zzeyj.isEmpty()) {
      paramObject = ((zzeyh)paramObject).zzotl;
      return (paramObject == null || paramObject.isEmpty());
    } 
    return this.zzotl.equals(((zzeyh)paramObject).zzotl);
  }
  
  public final int hashCode() {
    int k;
    int m;
    int i = zzezj.class.getName().hashCode();
    int j = this.zzimf;
    String str = this.zzowq;
    byte b = 0;
    if (str == null) {
      k = 0;
    } else {
      k = str.hashCode();
    } 
    str = this.version;
    if (str == null) {
      m = 0;
    } else {
      m = str.hashCode();
    } 
    zzeyj zzeyj = this.zzotl;
    int n = b;
    if (zzeyj != null)
      if (zzeyj.isEmpty()) {
        n = b;
      } else {
        n = this.zzotl.hashCode();
      }  
    return ((((i + 527) * 31 + j) * 31 + k) * 31 + m) * 31 + n;
  }
  
  public final void zza(zzeyf paramzzeyf) throws IOException {
    int i = this.zzimf;
    if (i != 0)
      paramzzeyf.zzx(1, i); 
    String str = this.zzowq;
    if (str != null && !str.equals(""))
      paramzzeyf.zzm(2, this.zzowq); 
    str = this.version;
    if (str != null && !str.equals(""))
      paramzzeyf.zzm(3, this.version); 
    super.zza(paramzzeyf);
  }
  
  protected final int zzn() {
    int i = super.zzn();
    int j = this.zzimf;
    int k = i;
    if (j != 0)
      k = i + zzeyf.zzaa(1, j); 
    String str = this.zzowq;
    i = k;
    if (str != null) {
      i = k;
      if (!str.equals(""))
        i = k + zzeyf.zzn(2, this.zzowq); 
    } 
    str = this.version;
    k = i;
    if (str != null) {
      k = i;
      if (!str.equals(""))
        k = i + zzeyf.zzn(3, this.version); 
    } 
    return k;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzezj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */