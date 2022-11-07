package com.google.android.gms.common.api.internal;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.MainThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiActivity;

final class zzq implements Runnable {
  private final zzp zzfja;
  
  zzq(zzo paramzzo, zzp paramzzp) {
    this.zzfja = paramzzp;
  }
  
  @MainThread
  public final void run() {
    Dialog dialog;
    if (!this.zzfjb.mStarted)
      return; 
    ConnectionResult connectionResult = this.zzfja.zzagd();
    if (connectionResult.hasResolution()) {
      zzo zzo1 = this.zzfjb;
      zzo1.zzfoo.startActivityForResult(GoogleApiActivity.zza((Context)zzo1.getActivity(), connectionResult.getResolution(), this.zzfja.zzagc(), false), 1);
      return;
    } 
    if (this.zzfjb.zzfhl.isUserResolvableError(connectionResult.getErrorCode())) {
      zzo zzo1 = this.zzfjb;
      zzo1.zzfhl.zza(zzo1.getActivity(), this.zzfjb.zzfoo, connectionResult.getErrorCode(), 2, this.zzfjb);
      return;
    } 
    if (connectionResult.getErrorCode() == 18) {
      dialog = GoogleApiAvailability.zza(this.zzfjb.getActivity(), this.zzfjb);
      GoogleApiAvailability.zza(this.zzfjb.getActivity().getApplicationContext(), new zzr(this, dialog));
      return;
    } 
    this.zzfjb.zza((ConnectionResult)dialog, this.zzfja.zzagc());
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */