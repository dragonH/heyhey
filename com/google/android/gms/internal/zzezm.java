package com.google.android.gms.internal;

import java.io.IOException;

public final class zzezm extends zzeyh<zzezm> implements Cloneable {
  private static volatile zzezm[] zzoxo;
  
  private String key = "";
  
  private String value = "";
  
  public zzezm() {
    this.zzotl = null;
    this.zzomu = -1;
  }
  
  public static zzezm[] zzcwu() {
    if (zzoxo == null)
      synchronized (zzeyl.zzott) {
        if (zzoxo == null)
          zzoxo = new zzezm[0]; 
      }  
    return zzoxo;
  }
  
  private zzezm zzcwv() {
    try {
      return super.zzcvz();
    } catch (CloneNotSupportedException cloneNotSupportedException) {
      throw new AssertionError(cloneNotSupportedException);
    } 
  }
  
  public final boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof zzezm))
      return false; 
    paramObject = paramObject;
    String str = this.key;
    if (str == null) {
      if (((zzezm)paramObject).key != null)
        return false; 
    } else if (!str.equals(((zzezm)paramObject).key)) {
      return false;
    } 
    str = this.value;
    if (str == null) {
      if (((zzezm)paramObject).value != null)
        return false; 
    } else if (!str.equals(((zzezm)paramObject).value)) {
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
    int j;
    int k;
    int i = zzezm.class.getName().hashCode();
    String str = this.key;
    byte b = 0;
    if (str == null) {
      j = 0;
    } else {
      j = str.hashCode();
    } 
    str = this.value;
    if (str == null) {
      k = 0;
    } else {
      k = str.hashCode();
    } 
    zzeyj zzeyj = this.zzotl;
    int m = b;
    if (zzeyj != null)
      if (zzeyj.isEmpty()) {
        m = b;
      } else {
        m = this.zzotl.hashCode();
      }  
    return (((i + 527) * 31 + j) * 31 + k) * 31 + m;
  }
  
  public final void zza(zzeyf paramzzeyf) throws IOException {
    String str = this.key;
    if (str != null && !str.equals(""))
      paramzzeyf.zzm(1, this.key); 
    str = this.value;
    if (str != null && !str.equals(""))
      paramzzeyf.zzm(2, this.value); 
    super.zza(paramzzeyf);
  }
  
  protected final int zzn() {
    int i = super.zzn();
    String str = this.key;
    int j = i;
    if (str != null) {
      j = i;
      if (!str.equals(""))
        j = i + zzeyf.zzn(1, this.key); 
    } 
    str = this.value;
    i = j;
    if (str != null) {
      i = j;
      if (!str.equals(""))
        i = j + zzeyf.zzn(2, this.value); 
    } 
    return i;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzezm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */