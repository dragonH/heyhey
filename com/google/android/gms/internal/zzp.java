package com.google.android.gms.internal;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import java.util.Collections;
import java.util.Map;

public abstract class zzp<T> implements Comparable<zzp<T>> {
  private final zzab.zza zzab;
  
  private final int zzac;
  
  private final String zzad;
  
  private final int zzae;
  
  private final zzu zzaf;
  
  private Integer zzag;
  
  private zzs zzah;
  
  private boolean zzai;
  
  private boolean zzaj;
  
  private boolean zzak;
  
  private boolean zzal;
  
  private zzx zzam;
  
  private zzc zzan;
  
  public zzp(int paramInt, String paramString, zzu paramzzu) {
    zzab.zza zza1;
    if (zzab.zza.zzbi) {
      zza1 = new zzab.zza();
    } else {
      zza1 = null;
    } 
    this.zzab = zza1;
    this.zzai = true;
    boolean bool = false;
    this.zzaj = false;
    this.zzak = false;
    this.zzal = false;
    this.zzan = null;
    this.zzac = paramInt;
    this.zzad = paramString;
    this.zzaf = paramzzu;
    this.zzam = new zzg();
    paramInt = bool;
    if (!TextUtils.isEmpty(paramString)) {
      Uri uri = Uri.parse(paramString);
      paramInt = bool;
      if (uri != null) {
        String str = uri.getHost();
        paramInt = bool;
        if (str != null)
          paramInt = str.hashCode(); 
      } 
    } 
    this.zzae = paramInt;
  }
  
  public static String zzf() {
    return ("UTF-8".length() != 0) ? "application/x-www-form-urlencoded; charset=".concat("UTF-8") : new String("application/x-www-form-urlencoded; charset=");
  }
  
  public Map<String, String> getHeaders() throws zza {
    return Collections.emptyMap();
  }
  
  public final int getMethod() {
    return this.zzac;
  }
  
  public final String getUrl() {
    return this.zzad;
  }
  
  public String toString() {
    String str1 = String.valueOf(Integer.toHexString(this.zzae));
    if (str1.length() != 0) {
      str1 = "0x".concat(str1);
    } else {
      str1 = new String("0x");
    } 
    String str2 = this.zzad;
    String str3 = String.valueOf(zzr.zzas);
    String str4 = String.valueOf(this.zzag);
    StringBuilder stringBuilder = new StringBuilder("[ ] ".length() + 3 + String.valueOf(str2).length() + String.valueOf(str1).length() + str3.length() + str4.length());
    stringBuilder.append("[ ] ");
    stringBuilder.append(str2);
    stringBuilder.append(" ");
    stringBuilder.append(str1);
    stringBuilder.append(" ");
    stringBuilder.append(str3);
    stringBuilder.append(" ");
    stringBuilder.append(str4);
    return stringBuilder.toString();
  }
  
  public final zzp<?> zza(int paramInt) {
    this.zzag = Integer.valueOf(paramInt);
    return this;
  }
  
  public final zzp<?> zza(zzc paramzzc) {
    this.zzan = paramzzc;
    return this;
  }
  
  public final zzp<?> zza(zzs paramzzs) {
    this.zzah = paramzzs;
    return this;
  }
  
  protected abstract zzt<T> zza(zzn paramzzn);
  
  protected abstract void zza(T paramT);
  
  public final void zzb(zzaa paramzzaa) {
    zzu zzu1 = this.zzaf;
    if (zzu1 != null)
      zzu1.zzd(paramzzaa); 
  }
  
  public final void zzb(String paramString) {
    if (zzab.zza.zzbi)
      this.zzab.zza(paramString, Thread.currentThread().getId()); 
  }
  
  public final int zzc() {
    return this.zzae;
  }
  
  final void zzc(String paramString) {
    zzs zzs1 = this.zzah;
    if (zzs1 != null)
      zzs1.zzd(this); 
    if (zzab.zza.zzbi) {
      long l = Thread.currentThread().getId();
      if (Looper.myLooper() != Looper.getMainLooper()) {
        (new Handler(Looper.getMainLooper())).post(new zzq(this, paramString, l));
        return;
      } 
      this.zzab.zza(paramString, l);
      this.zzab.zzc(toString());
    } 
  }
  
  public final String zzd() {
    return this.zzad;
  }
  
  public final zzc zze() {
    return this.zzan;
  }
  
  public byte[] zzg() throws zza {
    return null;
  }
  
  public final boolean zzh() {
    return this.zzai;
  }
  
  public final int zzi() {
    return this.zzam.zza();
  }
  
  public final zzx zzj() {
    return this.zzam;
  }
  
  public final void zzk() {
    this.zzak = true;
  }
  
  public final boolean zzl() {
    return this.zzak;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */