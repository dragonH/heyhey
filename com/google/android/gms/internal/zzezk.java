package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Arrays;

public final class zzezk extends zzeyh<zzezk> implements Cloneable {
  private byte[] zzowr = zzeyq.zzoue;
  
  private String zzows = "";
  
  private byte[][] zzowt = zzeyq.zzoud;
  
  private boolean zzowu = false;
  
  public zzezk() {
    this.zzotl = null;
    this.zzomu = -1;
  }
  
  private zzezk zzcws() {
    try {
      zzezk zzezk1 = super.zzcvz();
      byte[][] arrayOfByte = this.zzowt;
      if (arrayOfByte != null && arrayOfByte.length > 0)
        zzezk1.zzowt = (byte[][])arrayOfByte.clone(); 
      return zzezk1;
    } catch (CloneNotSupportedException cloneNotSupportedException) {
      throw new AssertionError(cloneNotSupportedException);
    } 
  }
  
  public final boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof zzezk))
      return false; 
    paramObject = paramObject;
    if (!Arrays.equals(this.zzowr, ((zzezk)paramObject).zzowr))
      return false; 
    String str = this.zzows;
    if (str == null) {
      if (((zzezk)paramObject).zzows != null)
        return false; 
    } else if (!str.equals(((zzezk)paramObject).zzows)) {
      return false;
    } 
    if (!zzeyl.zza(this.zzowt, ((zzezk)paramObject).zzowt))
      return false; 
    if (this.zzowu != ((zzezk)paramObject).zzowu)
      return false; 
    zzeyj zzeyj = this.zzotl;
    if (zzeyj == null || zzeyj.isEmpty()) {
      paramObject = ((zzeyh)paramObject).zzotl;
      return (paramObject == null || paramObject.isEmpty());
    } 
    return this.zzotl.equals(((zzeyh)paramObject).zzotl);
  }
  
  public final int hashCode() {
    int k;
    char c;
    int i = zzezk.class.getName().hashCode();
    int j = Arrays.hashCode(this.zzowr);
    String str = this.zzows;
    byte b = 0;
    if (str == null) {
      k = 0;
    } else {
      k = str.hashCode();
    } 
    int m = zzeyl.zzd(this.zzowt);
    if (this.zzowu) {
      c = 'ӏ';
    } else {
      c = 'ӕ';
    } 
    zzeyj zzeyj = this.zzotl;
    int n = b;
    if (zzeyj != null)
      if (zzeyj.isEmpty()) {
        n = b;
      } else {
        n = this.zzotl.hashCode();
      }  
    return (((((i + 527) * 31 + j) * 31 + k) * 31 + m) * 31 + c) * 31 + n;
  }
  
  public final void zza(zzeyf paramzzeyf) throws IOException {
    if (!Arrays.equals(this.zzowr, zzeyq.zzoue))
      paramzzeyf.zzc(1, this.zzowr); 
    byte[][] arrayOfByte = this.zzowt;
    if (arrayOfByte != null && arrayOfByte.length > 0) {
      byte b = 0;
      while (true) {
        arrayOfByte = this.zzowt;
        if (b < arrayOfByte.length) {
          byte[] arrayOfByte1 = arrayOfByte[b];
          if (arrayOfByte1 != null)
            paramzzeyf.zzc(2, arrayOfByte1); 
          b++;
          continue;
        } 
        break;
      } 
    } 
    boolean bool = this.zzowu;
    if (bool)
      paramzzeyf.zzl(3, bool); 
    String str = this.zzows;
    if (str != null && !str.equals(""))
      paramzzeyf.zzm(4, this.zzows); 
    super.zza(paramzzeyf);
  }
  
  protected final int zzn() {
    int i = super.zzn();
    int j = i;
    if (!Arrays.equals(this.zzowr, zzeyq.zzoue))
      j = i + zzeyf.zzd(1, this.zzowr); 
    byte[][] arrayOfByte = this.zzowt;
    i = j;
    if (arrayOfByte != null) {
      i = j;
      if (arrayOfByte.length > 0) {
        byte b = 0;
        int k = 0;
        i = 0;
        while (true) {
          arrayOfByte = this.zzowt;
          if (b < arrayOfByte.length) {
            byte[] arrayOfByte1 = arrayOfByte[b];
            int m = k;
            int n = i;
            if (arrayOfByte1 != null) {
              n = i + 1;
              m = k + zzeyf.zzbg(arrayOfByte1);
            } 
            b++;
            k = m;
            i = n;
            continue;
          } 
          i = j + k + i * 1;
          break;
        } 
      } 
    } 
    j = i;
    if (this.zzowu)
      j = i + zzeyf.zzkb(3) + 1; 
    String str = this.zzows;
    i = j;
    if (str != null) {
      i = j;
      if (!str.equals(""))
        i = j + zzeyf.zzn(4, this.zzows); 
    } 
    return i;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzezk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */