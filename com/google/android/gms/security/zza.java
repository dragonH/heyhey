package com.google.android.gms.security;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.zze;

final class zza extends AsyncTask<Void, Void, Integer> {
  zza(Context paramContext, ProviderInstaller.ProviderInstallListener paramProviderInstallListener) {}
  
  private final Integer zzb(Void... paramVarArgs) {
    int i;
    try {
      ProviderInstaller.installIfNeeded(this.zzaoa);
      i = 0;
    } catch (GooglePlayServicesRepairableException googlePlayServicesRepairableException) {
      i = googlePlayServicesRepairableException.getConnectionStatusCode();
    } catch (GooglePlayServicesNotAvailableException googlePlayServicesNotAvailableException) {
      i = googlePlayServicesNotAvailableException.errorCode;
    } 
    return Integer.valueOf(i);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/security/zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */