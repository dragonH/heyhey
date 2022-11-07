package com.google.android.gms.dynamite;

import android.content.Context;

final class zzd implements DynamiteModule.zzd {
  public final zzj zza(Context paramContext, String paramString, zzi paramzzi) throws DynamiteModule.zzc {
    zzj zzj = new zzj();
    zzj.zzgpy = paramzzi.zzad(paramContext, paramString);
    int i = paramzzi.zzb(paramContext, paramString, true);
    zzj.zzgpz = i;
    int j = zzj.zzgpy;
    if (j == 0 && i == 0) {
      j = 0;
    } else if (j >= i) {
      j = -1;
    } else {
      zzj.zzgqa = 1;
      return zzj;
    } 
    zzj.zzgqa = j;
    return zzj;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/dynamite/zzd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */