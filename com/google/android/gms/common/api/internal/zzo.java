package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import java.util.concurrent.atomic.AtomicReference;

public abstract class zzo extends LifecycleCallback implements DialogInterface.OnCancelListener {
  protected volatile boolean mStarted;
  
  protected final GoogleApiAvailability zzfhl;
  
  protected final AtomicReference<zzp> zzfiw = new AtomicReference<zzp>(null);
  
  private final Handler zzfix = new Handler(Looper.getMainLooper());
  
  protected zzo(zzcg paramzzcg) {
    this(paramzzcg, GoogleApiAvailability.getInstance());
  }
  
  private zzo(zzcg paramzzcg, GoogleApiAvailability paramGoogleApiAvailability) {
    super(paramzzcg);
    this.zzfhl = paramGoogleApiAvailability;
  }
  
  private static int zza(@Nullable zzp paramzzp) {
    return (paramzzp == null) ? -1 : paramzzp.zzagc();
  }
  
  public final void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
    zzp zzp = this.zzfiw.get();
    int i = 1;
    boolean bool = true;
    if (paramInt1 != 1) {
      if (paramInt1 != 2) {
        zzp zzp1 = zzp;
      } else {
        i = this.zzfhl.isGooglePlayServicesAvailable((Context)getActivity());
        if (i == 0) {
          paramInt1 = bool;
        } else {
          paramInt1 = 0;
        } 
        if (zzp == null)
          return; 
        zzp zzp1 = zzp;
        paramInt2 = paramInt1;
        if (zzp.zzagd().getErrorCode() == 18) {
          zzp1 = zzp;
          paramInt2 = paramInt1;
          if (i == 18)
            return; 
        } 
        if (paramInt2 != 0) {
          zzagb();
          return;
        } 
      } 
    } else {
      if (paramInt2 == -1) {
        zzp zzp1 = zzp;
        paramInt2 = i;
      } else {
        zzp zzp1 = zzp;
        if (paramInt2 == 0) {
          paramInt1 = 13;
          if (paramIntent != null)
            paramInt1 = paramIntent.getIntExtra("<<ResolutionFailureErrorDetail>>", 13); 
          zzp1 = new zzp(new ConnectionResult(paramInt1, null), zza(zzp));
          this.zzfiw.set(zzp1);
        } 
        paramInt2 = 0;
      } 
      if (paramInt2 != 0) {
        zzagb();
        return;
      } 
    } 
    paramInt2 = 0;
  }
  
  public void onCancel(DialogInterface paramDialogInterface) {
    zza(new ConnectionResult(13, null), zza(this.zzfiw.get()));
    zzagb();
  }
  
  public final void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    if (paramBundle != null) {
      AtomicReference<zzp> atomicReference = this.zzfiw;
      if (paramBundle.getBoolean("resolving_error", false)) {
        zzp zzp = new zzp(new ConnectionResult(paramBundle.getInt("failed_status"), (PendingIntent)paramBundle.getParcelable("failed_resolution")), paramBundle.getInt("failed_client_id", -1));
      } else {
        paramBundle = null;
      } 
      atomicReference.set(paramBundle);
    } 
  }
  
  public final void onSaveInstanceState(Bundle paramBundle) {
    super.onSaveInstanceState(paramBundle);
    zzp zzp = this.zzfiw.get();
    if (zzp != null) {
      paramBundle.putBoolean("resolving_error", true);
      paramBundle.putInt("failed_client_id", zzp.zzagc());
      paramBundle.putInt("failed_status", zzp.zzagd().getErrorCode());
      paramBundle.putParcelable("failed_resolution", (Parcelable)zzp.zzagd().getResolution());
    } 
  }
  
  public void onStart() {
    super.onStart();
    this.mStarted = true;
  }
  
  public void onStop() {
    super.onStop();
    this.mStarted = false;
  }
  
  protected abstract void zza(ConnectionResult paramConnectionResult, int paramInt);
  
  protected abstract void zzafw();
  
  protected final void zzagb() {
    this.zzfiw.set(null);
    zzafw();
  }
  
  public final void zzb(ConnectionResult paramConnectionResult, int paramInt) {
    zzp zzp = new zzp(paramConnectionResult, paramInt);
    if (zzo$$ExternalSyntheticBackportWithForwarding0.m(this.zzfiw, null, zzp))
      this.zzfix.post(new zzq(this, zzp)); 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */