package com.google.android.gms.internal;

import java.io.IOException;

public final class zzezo extends zzeyh<zzezo> {
  private static volatile zzezo[] zzoxr;
  
  public String zzoxs = "";
  
  public zzezo() {
    this.zzotl = null;
    this.zzomu = -1;
  }
  
  public static zzezo[] zzcwx() {
    if (zzoxr == null)
      synchronized (zzeyl.zzott) {
        if (zzoxr == null)
          zzoxr = new zzezo[0]; 
      }  
    return zzoxr;
  }
  
  public final void zza(zzeyf paramzzeyf) throws IOException {
    String str = this.zzoxs;
    if (str != null && !str.equals(""))
      paramzzeyf.zzm(1, this.zzoxs); 
    super.zza(paramzzeyf);
  }
  
  protected final int zzn() {
    int i = super.zzn();
    String str = this.zzoxs;
    int j = i;
    if (str != null) {
      j = i;
      if (!str.equals(""))
        j = i + zzeyf.zzn(1, this.zzoxs); 
    } 
    return j;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzezo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */