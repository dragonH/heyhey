package com.google.android.gms.dynamite;

import android.content.Context;

final class zze implements DynamiteModule.zzd {
  public final zzj zza(Context paramContext, String paramString, zzi paramzzi) throws DynamiteModule.zzc {
    zzj zzj = new zzj();
    int i = paramzzi.zzad(paramContext, paramString);
    zzj.zzgpy = i;
    if (i != 0) {
      i = paramzzi.zzb(paramContext, paramString, false);
    } else {
      i = paramzzi.zzb(paramContext, paramString, true);
    } 
    zzj.zzgpz = i;
    i = zzj.zzgpy;
    if (i == 0 && zzj.zzgpz == 0) {
      zzj.zzgqa = 0;
    } else if (i >= zzj.zzgpz) {
      zzj.zzgqa = -1;
    } else {
      zzj.zzgqa = 1;
    } 
    return zzj;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/dynamite/zze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */