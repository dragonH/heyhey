package com.google.android.gms.internal;

import java.io.IOException;

public abstract class zzeyh<M extends zzeyh<M>> extends zzeyn {
  protected zzeyj zzotl;
  
  public final <T> T zza(zzeyi<M, T> paramzzeyi) {
    zzeyj zzeyj1 = this.zzotl;
    if (zzeyj1 == null)
      return null; 
    zzeyk zzeyk = zzeyj1.zzle(paramzzeyi.tag >>> 3);
    return (zzeyk == null) ? null : zzeyk.zzb(paramzzeyi);
  }
  
  public void zza(zzeyf paramzzeyf) throws IOException {
    if (this.zzotl == null)
      return; 
    for (byte b = 0; b < this.zzotl.size(); b++)
      this.zzotl.zzlf(b).zza(paramzzeyf); 
  }
  
  protected final boolean zza(zzeye paramzzeye, int paramInt) throws IOException {
    zzeyk zzeyk1;
    int i = paramzzeye.getPosition();
    if (!paramzzeye.zzjl(paramInt))
      return false; 
    int j = paramInt >>> 3;
    zzeyp zzeyp = new zzeyp(paramInt, paramzzeye.zzai(i, paramzzeye.getPosition() - i));
    paramzzeye = null;
    zzeyj zzeyj1 = this.zzotl;
    if (zzeyj1 == null) {
      this.zzotl = new zzeyj();
    } else {
      zzeyk1 = zzeyj1.zzle(j);
    } 
    zzeyk zzeyk2 = zzeyk1;
    if (zzeyk1 == null) {
      zzeyk2 = new zzeyk();
      this.zzotl.zza(j, zzeyk2);
    } 
    zzeyk2.zza(zzeyp);
    return true;
  }
  
  public M zzcvz() throws CloneNotSupportedException {
    zzeyh zzeyh1 = (zzeyh)super.zzcwa();
    zzeyl.zza(this, zzeyh1);
    return (M)zzeyh1;
  }
  
  protected int zzn() {
    zzeyj zzeyj1 = this.zzotl;
    int i = 0;
    byte b = 0;
    if (zzeyj1 != null) {
      i = 0;
      while (b < this.zzotl.size()) {
        i += this.zzotl.zzlf(b).zzn();
        b++;
      } 
    } 
    return i;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzeyh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */