package com.google.android.gms.dynamite;

import android.content.Context;

final class zzc implements DynamiteModule.zzd {
  public final zzj zza(Context paramContext, String paramString, zzi paramzzi) throws DynamiteModule.zzc {
    zzj zzj = new zzj();
    int i = paramzzi.zzad(paramContext, paramString);
    zzj.zzgpy = i;
    if (i != 0) {
      zzj.zzgqa = -1;
    } else {
      i = paramzzi.zzb(paramContext, paramString, true);
      zzj.zzgpz = i;
      if (i != 0)
        zzj.zzgqa = 1; 
    } 
    return zzj;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/dynamite/zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */