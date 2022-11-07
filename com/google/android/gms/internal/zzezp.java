package com.google.android.gms.internal;

import java.io.IOException;

public final class zzezp extends zzeyh<zzezp> {
  public long zzgcc = 0L;
  
  public String zzoxs = "";
  
  public String zzoxt = "";
  
  public long zzoxu = 0L;
  
  public String zzoxv = "";
  
  public long zzoxw = 0L;
  
  public String zzoxx = "";
  
  public String zzoxy = "";
  
  public String zzoxz = "";
  
  public String zzoya = "";
  
  public String zzoyb = "";
  
  public int zzoyc = 0;
  
  public zzezo[] zzoyd = zzezo.zzcwx();
  
  public zzezp() {
    this.zzotl = null;
    this.zzomu = -1;
  }
  
  public static zzezp zzbi(byte[] paramArrayOfbyte) throws zzeym {
    return zzeyn.<zzezp>zza(new zzezp(), paramArrayOfbyte);
  }
  
  public final void zza(zzeyf paramzzeyf) throws IOException {
    String str = this.zzoxs;
    if (str != null && !str.equals(""))
      paramzzeyf.zzm(1, this.zzoxs); 
    str = this.zzoxt;
    if (str != null && !str.equals(""))
      paramzzeyf.zzm(2, this.zzoxt); 
    long l = this.zzoxu;
    if (l != 0L)
      paramzzeyf.zzf(3, l); 
    str = this.zzoxv;
    if (str != null && !str.equals(""))
      paramzzeyf.zzm(4, this.zzoxv); 
    l = this.zzoxw;
    if (l != 0L)
      paramzzeyf.zzf(5, l); 
    l = this.zzgcc;
    if (l != 0L)
      paramzzeyf.zzf(6, l); 
    str = this.zzoxx;
    if (str != null && !str.equals(""))
      paramzzeyf.zzm(7, this.zzoxx); 
    str = this.zzoxy;
    if (str != null && !str.equals(""))
      paramzzeyf.zzm(8, this.zzoxy); 
    str = this.zzoxz;
    if (str != null && !str.equals(""))
      paramzzeyf.zzm(9, this.zzoxz); 
    str = this.zzoya;
    if (str != null && !str.equals(""))
      paramzzeyf.zzm(10, this.zzoya); 
    str = this.zzoyb;
    if (str != null && !str.equals(""))
      paramzzeyf.zzm(11, this.zzoyb); 
    int i = this.zzoyc;
    if (i != 0)
      paramzzeyf.zzx(12, i); 
    zzezo[] arrayOfZzezo = this.zzoyd;
    if (arrayOfZzezo != null && arrayOfZzezo.length > 0) {
      i = 0;
      while (true) {
        arrayOfZzezo = this.zzoyd;
        if (i < arrayOfZzezo.length) {
          zzezo zzezo1 = arrayOfZzezo[i];
          if (zzezo1 != null)
            paramzzeyf.zza(13, zzezo1); 
          i++;
          continue;
        } 
        break;
      } 
    } 
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
    str = this.zzoxt;
    i = j;
    if (str != null) {
      i = j;
      if (!str.equals(""))
        i = j + zzeyf.zzn(2, this.zzoxt); 
    } 
    long l = this.zzoxu;
    j = i;
    if (l != 0L)
      j = i + zzeyf.zzc(3, l); 
    str = this.zzoxv;
    i = j;
    if (str != null) {
      i = j;
      if (!str.equals(""))
        i = j + zzeyf.zzn(4, this.zzoxv); 
    } 
    l = this.zzoxw;
    j = i;
    if (l != 0L)
      j = i + zzeyf.zzc(5, l); 
    l = this.zzgcc;
    i = j;
    if (l != 0L)
      i = j + zzeyf.zzc(6, l); 
    str = this.zzoxx;
    j = i;
    if (str != null) {
      j = i;
      if (!str.equals(""))
        j = i + zzeyf.zzn(7, this.zzoxx); 
    } 
    str = this.zzoxy;
    i = j;
    if (str != null) {
      i = j;
      if (!str.equals(""))
        i = j + zzeyf.zzn(8, this.zzoxy); 
    } 
    str = this.zzoxz;
    j = i;
    if (str != null) {
      j = i;
      if (!str.equals(""))
        j = i + zzeyf.zzn(9, this.zzoxz); 
    } 
    str = this.zzoya;
    int k = j;
    if (str != null) {
      k = j;
      if (!str.equals(""))
        k = j + zzeyf.zzn(10, this.zzoya); 
    } 
    str = this.zzoyb;
    i = k;
    if (str != null) {
      i = k;
      if (!str.equals(""))
        i = k + zzeyf.zzn(11, this.zzoyb); 
    } 
    k = this.zzoyc;
    j = i;
    if (k != 0)
      j = i + zzeyf.zzaa(12, k); 
    zzezo[] arrayOfZzezo = this.zzoyd;
    k = j;
    if (arrayOfZzezo != null) {
      k = j;
      if (arrayOfZzezo.length > 0) {
        i = 0;
        while (true) {
          arrayOfZzezo = this.zzoyd;
          k = j;
          if (i < arrayOfZzezo.length) {
            zzezo zzezo1 = arrayOfZzezo[i];
            k = j;
            if (zzezo1 != null)
              k = j + zzeyf.zzb(13, zzezo1); 
            i++;
            j = k;
            continue;
          } 
          break;
        } 
      } 
    } 
    return k;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzezp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */