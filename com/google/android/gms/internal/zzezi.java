package com.google.android.gms.internal;

import java.io.IOException;

public final class zzezi extends zzeyh<zzezi> implements Cloneable {
  private String[] zzowl;
  
  private String[] zzowm;
  
  private int[] zzown;
  
  private long[] zzowo;
  
  private long[] zzowp;
  
  public zzezi() {
    String[] arrayOfString = zzeyq.EMPTY_STRING_ARRAY;
    this.zzowl = arrayOfString;
    this.zzowm = arrayOfString;
    this.zzown = zzeyq.zzoty;
    long[] arrayOfLong = zzeyq.zzotz;
    this.zzowo = arrayOfLong;
    this.zzowp = arrayOfLong;
    this.zzotl = null;
    this.zzomu = -1;
  }
  
  private zzezi zzcwq() {
    try {
      zzezi zzezi1 = super.zzcvz();
      String[] arrayOfString = this.zzowl;
      if (arrayOfString != null && arrayOfString.length > 0)
        zzezi1.zzowl = (String[])arrayOfString.clone(); 
      arrayOfString = this.zzowm;
      if (arrayOfString != null && arrayOfString.length > 0)
        zzezi1.zzowm = (String[])arrayOfString.clone(); 
      int[] arrayOfInt = this.zzown;
      if (arrayOfInt != null && arrayOfInt.length > 0)
        zzezi1.zzown = (int[])arrayOfInt.clone(); 
      long[] arrayOfLong = this.zzowo;
      if (arrayOfLong != null && arrayOfLong.length > 0)
        zzezi1.zzowo = (long[])arrayOfLong.clone(); 
      arrayOfLong = this.zzowp;
      if (arrayOfLong != null && arrayOfLong.length > 0)
        zzezi1.zzowp = (long[])arrayOfLong.clone(); 
      return zzezi1;
    } catch (CloneNotSupportedException cloneNotSupportedException) {
      throw new AssertionError(cloneNotSupportedException);
    } 
  }
  
  public final boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof zzezi))
      return false; 
    zzezi zzezi1 = (zzezi)paramObject;
    if (!zzeyl.equals((Object[])this.zzowl, (Object[])zzezi1.zzowl))
      return false; 
    if (!zzeyl.equals((Object[])this.zzowm, (Object[])zzezi1.zzowm))
      return false; 
    if (!zzeyl.equals(this.zzown, zzezi1.zzown))
      return false; 
    if (!zzeyl.equals(this.zzowo, zzezi1.zzowo))
      return false; 
    if (!zzeyl.equals(this.zzowp, zzezi1.zzowp))
      return false; 
    paramObject = this.zzotl;
    if (paramObject == null || paramObject.isEmpty()) {
      paramObject = zzezi1.zzotl;
      return (paramObject == null || paramObject.isEmpty());
    } 
    return this.zzotl.equals(zzezi1.zzotl);
  }
  
  public final int hashCode() {
    int i = zzezi.class.getName().hashCode();
    int j = zzeyl.hashCode((Object[])this.zzowl);
    int k = zzeyl.hashCode((Object[])this.zzowm);
    int m = zzeyl.hashCode(this.zzown);
    int n = zzeyl.hashCode(this.zzowo);
    int i1 = zzeyl.hashCode(this.zzowp);
    zzeyj zzeyj = this.zzotl;
    if (zzeyj == null || zzeyj.isEmpty()) {
      byte b = 0;
      return ((((((i + 527) * 31 + j) * 31 + k) * 31 + m) * 31 + n) * 31 + i1) * 31 + b;
    } 
    int i2 = this.zzotl.hashCode();
    return ((((((i + 527) * 31 + j) * 31 + k) * 31 + m) * 31 + n) * 31 + i1) * 31 + i2;
  }
  
  public final void zza(zzeyf paramzzeyf) throws IOException {
    String[] arrayOfString = this.zzowl;
    byte b = 0;
    if (arrayOfString != null && arrayOfString.length > 0) {
      byte b1 = 0;
      while (true) {
        arrayOfString = this.zzowl;
        if (b1 < arrayOfString.length) {
          String str = arrayOfString[b1];
          if (str != null)
            paramzzeyf.zzm(1, str); 
          b1++;
          continue;
        } 
        break;
      } 
    } 
    arrayOfString = this.zzowm;
    if (arrayOfString != null && arrayOfString.length > 0) {
      byte b1 = 0;
      while (true) {
        arrayOfString = this.zzowm;
        if (b1 < arrayOfString.length) {
          String str = arrayOfString[b1];
          if (str != null)
            paramzzeyf.zzm(2, str); 
          b1++;
          continue;
        } 
        break;
      } 
    } 
    int[] arrayOfInt = this.zzown;
    if (arrayOfInt != null && arrayOfInt.length > 0) {
      byte b1 = 0;
      while (true) {
        arrayOfInt = this.zzown;
        if (b1 < arrayOfInt.length) {
          paramzzeyf.zzx(3, arrayOfInt[b1]);
          b1++;
          continue;
        } 
        break;
      } 
    } 
    long[] arrayOfLong = this.zzowo;
    if (arrayOfLong != null && arrayOfLong.length > 0) {
      byte b1 = 0;
      while (true) {
        arrayOfLong = this.zzowo;
        if (b1 < arrayOfLong.length) {
          paramzzeyf.zzf(4, arrayOfLong[b1]);
          b1++;
          continue;
        } 
        break;
      } 
    } 
    arrayOfLong = this.zzowp;
    if (arrayOfLong != null && arrayOfLong.length > 0) {
      byte b1 = b;
      while (true) {
        arrayOfLong = this.zzowp;
        if (b1 < arrayOfLong.length) {
          paramzzeyf.zzf(5, arrayOfLong[b1]);
          b1++;
          continue;
        } 
        break;
      } 
    } 
    super.zza(paramzzeyf);
  }
  
  protected final int zzn() {
    int i = super.zzn();
    String[] arrayOfString = this.zzowl;
    boolean bool = false;
    int j = i;
    if (arrayOfString != null) {
      j = i;
      if (arrayOfString.length > 0) {
        byte b = 0;
        int m = 0;
        j = 0;
        while (true) {
          arrayOfString = this.zzowl;
          if (b < arrayOfString.length) {
            String str = arrayOfString[b];
            int n = m;
            int i1 = j;
            if (str != null) {
              i1 = j + 1;
              n = m + zzeyf.zztk(str);
            } 
            b++;
            m = n;
            j = i1;
            continue;
          } 
          j = i + m + j * 1;
          break;
        } 
      } 
    } 
    arrayOfString = this.zzowm;
    int k = j;
    if (arrayOfString != null) {
      k = j;
      if (arrayOfString.length > 0) {
        byte b = 0;
        k = 0;
        int m = 0;
        while (true) {
          arrayOfString = this.zzowm;
          if (b < arrayOfString.length) {
            String str = arrayOfString[b];
            i = k;
            int n = m;
            if (str != null) {
              n = m + 1;
              i = k + zzeyf.zztk(str);
            } 
            b++;
            k = i;
            m = n;
            continue;
          } 
          k = j + k + m * 1;
          break;
        } 
      } 
    } 
    int[] arrayOfInt = this.zzown;
    j = k;
    if (arrayOfInt != null) {
      j = k;
      if (arrayOfInt.length > 0) {
        j = 0;
        int m = 0;
        while (true) {
          arrayOfInt = this.zzown;
          if (j < arrayOfInt.length) {
            m += zzeyf.zzkc(arrayOfInt[j]);
            j++;
            continue;
          } 
          j = k + m + arrayOfInt.length * 1;
          break;
        } 
      } 
    } 
    long[] arrayOfLong = this.zzowo;
    k = j;
    if (arrayOfLong != null) {
      k = j;
      if (arrayOfLong.length > 0) {
        byte b = 0;
        k = 0;
        while (true) {
          arrayOfLong = this.zzowo;
          if (b < arrayOfLong.length) {
            k += zzeyf.zzdg(arrayOfLong[b]);
            b++;
            continue;
          } 
          k = j + k + arrayOfLong.length * 1;
          break;
        } 
      } 
    } 
    arrayOfLong = this.zzowp;
    j = k;
    if (arrayOfLong != null) {
      j = k;
      if (arrayOfLong.length > 0) {
        int m = 0;
        j = bool;
        while (true) {
          arrayOfLong = this.zzowp;
          if (j < arrayOfLong.length) {
            m += zzeyf.zzdg(arrayOfLong[j]);
            j++;
            continue;
          } 
          j = k + m + arrayOfLong.length * 1;
          break;
        } 
      } 
    } 
    return j;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzezi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */