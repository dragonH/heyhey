package com.google.android.gms.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

final class zzai {
  public String key;
  
  public String zza;
  
  public long zzb;
  
  public long zzbz;
  
  public long zzc;
  
  public long zzd;
  
  public long zze;
  
  public Map<String, String> zzf;
  
  private zzai() {}
  
  public zzai(String paramString, zzc paramzzc) {
    this.key = paramString;
    this.zzbz = paramzzc.data.length;
    this.zza = paramzzc.zza;
    this.zzb = paramzzc.zzb;
    this.zzc = paramzzc.zzc;
    this.zzd = paramzzc.zzd;
    this.zze = paramzzc.zze;
    this.zzf = paramzzc.zzf;
  }
  
  public static zzai zzf(InputStream paramInputStream) throws IOException {
    zzai zzai1 = new zzai();
    if (zzag.zzb(paramInputStream) == 538247942) {
      zzai1.key = zzag.zzd(paramInputStream);
      String str = zzag.zzd(paramInputStream);
      zzai1.zza = str;
      if (str.equals(""))
        zzai1.zza = null; 
      zzai1.zzb = zzag.zzc(paramInputStream);
      zzai1.zzc = zzag.zzc(paramInputStream);
      zzai1.zzd = zzag.zzc(paramInputStream);
      zzai1.zze = zzag.zzc(paramInputStream);
      zzai1.zzf = zzag.zze(paramInputStream);
      return zzai1;
    } 
    throw new IOException();
  }
  
  public final boolean zza(OutputStream paramOutputStream) {
    try {
      zzag.zza(paramOutputStream, 538247942);
      zzag.zza(paramOutputStream, this.key);
      String str1 = this.zza;
      String str2 = str1;
      if (str1 == null)
        str2 = ""; 
      zzag.zza(paramOutputStream, str2);
      zzag.zza(paramOutputStream, this.zzb);
      zzag.zza(paramOutputStream, this.zzc);
      zzag.zza(paramOutputStream, this.zzd);
      zzag.zza(paramOutputStream, this.zze);
      Map<String, String> map = this.zzf;
      if (map != null) {
        zzag.zza(paramOutputStream, map.size());
        for (Map.Entry<String, String> entry : map.entrySet()) {
          zzag.zza(paramOutputStream, (String)entry.getKey());
          zzag.zza(paramOutputStream, (String)entry.getValue());
        } 
      } else {
        zzag.zza(paramOutputStream, 0);
      } 
      paramOutputStream.flush();
      return true;
    } catch (IOException iOException) {
      zzab.zzb("%s", new Object[] { iOException.toString() });
      return false;
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzai.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */