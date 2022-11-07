package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Arrays;

public final class zzezl extends zzeyh<zzezl> implements Cloneable {
  private String tag = "";
  
  private int zzajv = 0;
  
  private boolean zzlyp = false;
  
  private zzezn zznky;
  
  public long zzowv = 0L;
  
  public long zzoww = 0L;
  
  private long zzowx = 0L;
  
  private int zzowy = 0;
  
  private zzezm[] zzowz = zzezm.zzcwu();
  
  private byte[] zzoxa;
  
  private zzezj zzoxb;
  
  public byte[] zzoxc;
  
  private String zzoxd;
  
  private String zzoxe;
  
  private zzezi zzoxf;
  
  private String zzoxg;
  
  public long zzoxh;
  
  private zzezk zzoxi;
  
  public byte[] zzoxj;
  
  private String zzoxk;
  
  private int zzoxl;
  
  private int[] zzoxm;
  
  private long zzoxn;
  
  public zzezl() {
    byte[] arrayOfByte = zzeyq.zzoue;
    this.zzoxa = arrayOfByte;
    this.zzoxb = null;
    this.zzoxc = arrayOfByte;
    this.zzoxd = "";
    this.zzoxe = "";
    this.zzoxf = null;
    this.zzoxg = "";
    this.zzoxh = 180000L;
    this.zzoxi = null;
    this.zzoxj = arrayOfByte;
    this.zzoxk = "";
    this.zzoxl = 0;
    this.zzoxm = zzeyq.zzoty;
    this.zzoxn = 0L;
    this.zznky = null;
    this.zzotl = null;
    this.zzomu = -1;
  }
  
  private final zzezl zzcwt() {
    try {
      zzezl zzezl1 = super.zzcvz();
      zzezm[] arrayOfZzezm = this.zzowz;
      if (arrayOfZzezm != null && arrayOfZzezm.length > 0) {
        zzezl1.zzowz = new zzezm[arrayOfZzezm.length];
        byte b = 0;
        while (true) {
          arrayOfZzezm = this.zzowz;
          if (b < arrayOfZzezm.length) {
            zzezm zzezm1 = arrayOfZzezm[b];
            if (zzezm1 != null)
              zzezl1.zzowz[b] = (zzezm)zzezm1.clone(); 
            b++;
            continue;
          } 
          break;
        } 
      } 
      zzezj zzezj1 = this.zzoxb;
      if (zzezj1 != null)
        zzezl1.zzoxb = (zzezj)zzezj1.clone(); 
      zzezi zzezi1 = this.zzoxf;
      if (zzezi1 != null)
        zzezl1.zzoxf = (zzezi)zzezi1.clone(); 
      zzezk zzezk1 = this.zzoxi;
      if (zzezk1 != null)
        zzezl1.zzoxi = (zzezk)zzezk1.clone(); 
      int[] arrayOfInt = this.zzoxm;
      if (arrayOfInt != null && arrayOfInt.length > 0)
        zzezl1.zzoxm = (int[])arrayOfInt.clone(); 
      zzezn zzezn1 = this.zznky;
      if (zzezn1 != null)
        zzezl1.zznky = (zzezn)zzezn1.clone(); 
      return zzezl1;
    } catch (CloneNotSupportedException cloneNotSupportedException) {
      AssertionError assertionError = new AssertionError(cloneNotSupportedException);
      throw assertionError;
    } 
  }
  
  public final boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof zzezl))
      return false; 
    paramObject = paramObject;
    if (this.zzowv != ((zzezl)paramObject).zzowv)
      return false; 
    if (this.zzoww != ((zzezl)paramObject).zzoww)
      return false; 
    if (this.zzowx != ((zzezl)paramObject).zzowx)
      return false; 
    String str4 = this.tag;
    if (str4 == null) {
      if (((zzezl)paramObject).tag != null)
        return false; 
    } else if (!str4.equals(((zzezl)paramObject).tag)) {
      return false;
    } 
    if (this.zzowy != ((zzezl)paramObject).zzowy)
      return false; 
    if (this.zzajv != ((zzezl)paramObject).zzajv)
      return false; 
    if (this.zzlyp != ((zzezl)paramObject).zzlyp)
      return false; 
    if (!zzeyl.equals((Object[])this.zzowz, (Object[])((zzezl)paramObject).zzowz))
      return false; 
    if (!Arrays.equals(this.zzoxa, ((zzezl)paramObject).zzoxa))
      return false; 
    zzezj zzezj1 = this.zzoxb;
    if (zzezj1 == null) {
      if (((zzezl)paramObject).zzoxb != null)
        return false; 
    } else if (!zzezj1.equals(((zzezl)paramObject).zzoxb)) {
      return false;
    } 
    if (!Arrays.equals(this.zzoxc, ((zzezl)paramObject).zzoxc))
      return false; 
    String str3 = this.zzoxd;
    if (str3 == null) {
      if (((zzezl)paramObject).zzoxd != null)
        return false; 
    } else if (!str3.equals(((zzezl)paramObject).zzoxd)) {
      return false;
    } 
    str3 = this.zzoxe;
    if (str3 == null) {
      if (((zzezl)paramObject).zzoxe != null)
        return false; 
    } else if (!str3.equals(((zzezl)paramObject).zzoxe)) {
      return false;
    } 
    zzezi zzezi1 = this.zzoxf;
    if (zzezi1 == null) {
      if (((zzezl)paramObject).zzoxf != null)
        return false; 
    } else if (!zzezi1.equals(((zzezl)paramObject).zzoxf)) {
      return false;
    } 
    String str2 = this.zzoxg;
    if (str2 == null) {
      if (((zzezl)paramObject).zzoxg != null)
        return false; 
    } else if (!str2.equals(((zzezl)paramObject).zzoxg)) {
      return false;
    } 
    if (this.zzoxh != ((zzezl)paramObject).zzoxh)
      return false; 
    zzezk zzezk1 = this.zzoxi;
    if (zzezk1 == null) {
      if (((zzezl)paramObject).zzoxi != null)
        return false; 
    } else if (!zzezk1.equals(((zzezl)paramObject).zzoxi)) {
      return false;
    } 
    if (!Arrays.equals(this.zzoxj, ((zzezl)paramObject).zzoxj))
      return false; 
    String str1 = this.zzoxk;
    if (str1 == null) {
      if (((zzezl)paramObject).zzoxk != null)
        return false; 
    } else if (!str1.equals(((zzezl)paramObject).zzoxk)) {
      return false;
    } 
    if (this.zzoxl != ((zzezl)paramObject).zzoxl)
      return false; 
    if (!zzeyl.equals(this.zzoxm, ((zzezl)paramObject).zzoxm))
      return false; 
    if (this.zzoxn != ((zzezl)paramObject).zzoxn)
      return false; 
    zzezn zzezn1 = this.zznky;
    if (zzezn1 == null) {
      if (((zzezl)paramObject).zznky != null)
        return false; 
    } else if (!zzezn1.equals(((zzezl)paramObject).zznky)) {
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
    int n;
    char c;
    int i5;
    int i7;
    int i8;
    int i9;
    int i10;
    int i12;
    int i14;
    int i18;
    int i = zzezl.class.getName().hashCode();
    long l = this.zzowv;
    int j = (int)(l ^ l >>> 32L);
    l = this.zzoww;
    int k = (int)(l ^ l >>> 32L);
    l = this.zzowx;
    int m = (int)(l ^ l >>> 32L);
    String str4 = this.tag;
    byte b = 0;
    if (str4 == null) {
      n = 0;
    } else {
      n = str4.hashCode();
    } 
    int i1 = this.zzowy;
    int i2 = this.zzajv;
    if (this.zzlyp) {
      c = 'ӏ';
    } else {
      c = 'ӕ';
    } 
    int i3 = zzeyl.hashCode((Object[])this.zzowz);
    int i4 = Arrays.hashCode(this.zzoxa);
    zzezj zzezj1 = this.zzoxb;
    if (zzezj1 == null) {
      i5 = 0;
    } else {
      i5 = zzezj1.hashCode();
    } 
    int i6 = Arrays.hashCode(this.zzoxc);
    String str3 = this.zzoxd;
    if (str3 == null) {
      i7 = 0;
    } else {
      i7 = str3.hashCode();
    } 
    str3 = this.zzoxe;
    if (str3 == null) {
      i8 = 0;
    } else {
      i8 = str3.hashCode();
    } 
    zzezi zzezi1 = this.zzoxf;
    if (zzezi1 == null) {
      i9 = 0;
    } else {
      i9 = zzezi1.hashCode();
    } 
    String str2 = this.zzoxg;
    if (str2 == null) {
      i10 = 0;
    } else {
      i10 = str2.hashCode();
    } 
    l = this.zzoxh;
    int i11 = (int)(l ^ l >>> 32L);
    zzezk zzezk1 = this.zzoxi;
    if (zzezk1 == null) {
      i12 = 0;
    } else {
      i12 = zzezk1.hashCode();
    } 
    int i13 = Arrays.hashCode(this.zzoxj);
    String str1 = this.zzoxk;
    if (str1 == null) {
      i14 = 0;
    } else {
      i14 = str1.hashCode();
    } 
    int i15 = this.zzoxl;
    int i16 = zzeyl.hashCode(this.zzoxm);
    l = this.zzoxn;
    int i17 = (int)(l ^ l >>> 32L);
    zzezn zzezn1 = this.zznky;
    if (zzezn1 == null) {
      i18 = 0;
    } else {
      i18 = zzezn1.hashCode();
    } 
    zzeyj zzeyj = this.zzotl;
    int i19 = b;
    if (zzeyj != null)
      if (zzeyj.isEmpty()) {
        i19 = b;
      } else {
        i19 = this.zzotl.hashCode();
      }  
    return ((((((((((((((((((((((((i + 527) * 31 + j) * 31 + k) * 31 + m) * 31 + n) * 31 + i1) * 31 + i2) * 31 + c) * 31 + i3) * 31 + i4) * 31 + i5) * 31 + i6) * 31 + i7) * 31 + i8) * 31 + i9) * 31 + i10) * 31 + i11) * 31 + i12) * 31 + i13) * 31 + i14) * 31 + i15) * 31 + i16) * 31 + i17) * 31 + i18) * 31 + i19;
  }
  
  public final void zza(zzeyf paramzzeyf) throws IOException {
    long l = this.zzowv;
    if (l != 0L)
      paramzzeyf.zzf(1, l); 
    String str2 = this.tag;
    if (str2 != null && !str2.equals(""))
      paramzzeyf.zzm(2, this.tag); 
    zzezm[] arrayOfZzezm = this.zzowz;
    boolean bool = false;
    if (arrayOfZzezm != null && arrayOfZzezm.length > 0) {
      byte b = 0;
      while (true) {
        arrayOfZzezm = this.zzowz;
        if (b < arrayOfZzezm.length) {
          zzezm zzezm1 = arrayOfZzezm[b];
          if (zzezm1 != null)
            paramzzeyf.zza(3, zzezm1); 
          b++;
          continue;
        } 
        break;
      } 
    } 
    byte[] arrayOfByte2 = this.zzoxa;
    byte[] arrayOfByte1 = zzeyq.zzoue;
    if (!Arrays.equals(arrayOfByte2, arrayOfByte1))
      paramzzeyf.zzc(4, this.zzoxa); 
    if (!Arrays.equals(this.zzoxc, arrayOfByte1))
      paramzzeyf.zzc(6, this.zzoxc); 
    zzezi zzezi1 = this.zzoxf;
    if (zzezi1 != null)
      paramzzeyf.zza(7, zzezi1); 
    String str4 = this.zzoxd;
    if (str4 != null && !str4.equals(""))
      paramzzeyf.zzm(8, this.zzoxd); 
    zzezj zzezj1 = this.zzoxb;
    if (zzezj1 != null)
      paramzzeyf.zza(9, zzezj1); 
    boolean bool1 = this.zzlyp;
    if (bool1)
      paramzzeyf.zzl(10, bool1); 
    int i = this.zzowy;
    if (i != 0)
      paramzzeyf.zzx(11, i); 
    i = this.zzajv;
    if (i != 0)
      paramzzeyf.zzx(12, i); 
    String str3 = this.zzoxe;
    if (str3 != null && !str3.equals(""))
      paramzzeyf.zzm(13, this.zzoxe); 
    str3 = this.zzoxg;
    if (str3 != null && !str3.equals(""))
      paramzzeyf.zzm(14, this.zzoxg); 
    l = this.zzoxh;
    if (l != 180000L)
      paramzzeyf.zzg(15, l); 
    zzezk zzezk1 = this.zzoxi;
    if (zzezk1 != null)
      paramzzeyf.zza(16, zzezk1); 
    l = this.zzoww;
    if (l != 0L)
      paramzzeyf.zzf(17, l); 
    if (!Arrays.equals(this.zzoxj, arrayOfByte1))
      paramzzeyf.zzc(18, this.zzoxj); 
    i = this.zzoxl;
    if (i != 0)
      paramzzeyf.zzx(19, i); 
    int[] arrayOfInt = this.zzoxm;
    if (arrayOfInt != null && arrayOfInt.length > 0) {
      i = bool;
      while (true) {
        arrayOfInt = this.zzoxm;
        if (i < arrayOfInt.length) {
          paramzzeyf.zzx(20, arrayOfInt[i]);
          i++;
          continue;
        } 
        break;
      } 
    } 
    l = this.zzowx;
    if (l != 0L)
      paramzzeyf.zzf(21, l); 
    l = this.zzoxn;
    if (l != 0L)
      paramzzeyf.zzf(22, l); 
    zzezn zzezn1 = this.zznky;
    if (zzezn1 != null)
      paramzzeyf.zza(23, zzezn1); 
    String str1 = this.zzoxk;
    if (str1 != null && !str1.equals(""))
      paramzzeyf.zzm(24, this.zzoxk); 
    super.zza(paramzzeyf);
  }
  
  protected final int zzn() {
    int i = super.zzn();
    long l = this.zzowv;
    int j = i;
    if (l != 0L)
      j = i + zzeyf.zzc(1, l); 
    String str2 = this.tag;
    i = j;
    if (str2 != null) {
      i = j;
      if (!str2.equals(""))
        i = j + zzeyf.zzn(2, this.tag); 
    } 
    zzezm[] arrayOfZzezm = this.zzowz;
    boolean bool = false;
    j = i;
    if (arrayOfZzezm != null) {
      j = i;
      if (arrayOfZzezm.length > 0) {
        byte b = 0;
        while (true) {
          arrayOfZzezm = this.zzowz;
          j = i;
          if (b < arrayOfZzezm.length) {
            zzezm zzezm1 = arrayOfZzezm[b];
            j = i;
            if (zzezm1 != null)
              j = i + zzeyf.zzb(3, zzezm1); 
            b++;
            i = j;
            continue;
          } 
          break;
        } 
      } 
    } 
    byte[] arrayOfByte2 = this.zzoxa;
    byte[] arrayOfByte1 = zzeyq.zzoue;
    i = j;
    if (!Arrays.equals(arrayOfByte2, arrayOfByte1))
      i = j + zzeyf.zzd(4, this.zzoxa); 
    j = i;
    if (!Arrays.equals(this.zzoxc, arrayOfByte1))
      j = i + zzeyf.zzd(6, this.zzoxc); 
    zzezi zzezi1 = this.zzoxf;
    i = j;
    if (zzezi1 != null)
      i = j + zzeyf.zzb(7, zzezi1); 
    String str4 = this.zzoxd;
    j = i;
    if (str4 != null) {
      j = i;
      if (!str4.equals(""))
        j = i + zzeyf.zzn(8, this.zzoxd); 
    } 
    zzezj zzezj1 = this.zzoxb;
    i = j;
    if (zzezj1 != null)
      i = j + zzeyf.zzb(9, zzezj1); 
    j = i;
    if (this.zzlyp)
      j = i + zzeyf.zzkb(10) + 1; 
    int k = this.zzowy;
    i = j;
    if (k != 0)
      i = j + zzeyf.zzaa(11, k); 
    k = this.zzajv;
    j = i;
    if (k != 0)
      j = i + zzeyf.zzaa(12, k); 
    String str3 = this.zzoxe;
    i = j;
    if (str3 != null) {
      i = j;
      if (!str3.equals(""))
        i = j + zzeyf.zzn(13, this.zzoxe); 
    } 
    str3 = this.zzoxg;
    j = i;
    if (str3 != null) {
      j = i;
      if (!str3.equals(""))
        j = i + zzeyf.zzn(14, this.zzoxg); 
    } 
    l = this.zzoxh;
    i = j;
    if (l != 180000L)
      i = j + zzeyf.zzh(15, l); 
    zzezk zzezk1 = this.zzoxi;
    j = i;
    if (zzezk1 != null)
      j = i + zzeyf.zzb(16, zzezk1); 
    l = this.zzoww;
    i = j;
    if (l != 0L)
      i = j + zzeyf.zzc(17, l); 
    j = i;
    if (!Arrays.equals(this.zzoxj, arrayOfByte1))
      j = i + zzeyf.zzd(18, this.zzoxj); 
    k = this.zzoxl;
    i = j;
    if (k != 0)
      i = j + zzeyf.zzaa(19, k); 
    int[] arrayOfInt = this.zzoxm;
    j = i;
    if (arrayOfInt != null) {
      j = i;
      if (arrayOfInt.length > 0) {
        k = 0;
        j = bool;
        while (true) {
          arrayOfInt = this.zzoxm;
          if (j < arrayOfInt.length) {
            k += zzeyf.zzkc(arrayOfInt[j]);
            j++;
            continue;
          } 
          j = i + k + arrayOfInt.length * 2;
          break;
        } 
      } 
    } 
    l = this.zzowx;
    k = j;
    if (l != 0L)
      k = j + zzeyf.zzc(21, l); 
    l = this.zzoxn;
    i = k;
    if (l != 0L)
      i = k + zzeyf.zzc(22, l); 
    zzezn zzezn1 = this.zznky;
    j = i;
    if (zzezn1 != null)
      j = i + zzeyf.zzb(23, zzezn1); 
    String str1 = this.zzoxk;
    i = j;
    if (str1 != null) {
      i = j;
      if (!str1.equals(""))
        i = j + zzeyf.zzn(24, this.zzoxk); 
    } 
    return i;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzezl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */