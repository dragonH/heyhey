package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.AvailabilityException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.Collections;
import java.util.Map;

final class zzag implements OnCompleteListener<Void> {
  private zzcv zzfkz;
  
  zzag(zzad paramzzad, zzcv paramzzcv) {
    this.zzfkz = paramzzcv;
  }
  
  final void cancel() {
    this.zzfkz.zzaak();
  }
  
  public final void onComplete(@NonNull Task<Void> paramTask) {
    zzad.zza(this.zzfky).lock();
    try {
      zzcv zzcv1;
      if (!zzad.zzb(this.zzfky)) {
        zzcv1 = this.zzfkz;
      } else {
        if (zzcv1.isSuccessful()) {
          zzad zzad1 = this.zzfky;
          ArrayMap arrayMap = new ArrayMap();
          this(zzad.zzm(zzad1).size());
          zzad.zzb(zzad1, (Map)arrayMap);
          for (zzac zzac : zzad.zzm(this.zzfky).values())
            zzad.zzg(this.zzfky).put(zzac.zzafk(), ConnectionResult.zzfff); 
        } else {
          ConnectionResult connectionResult;
          if (zzcv1.getException() instanceof AvailabilityException) {
            AvailabilityException availabilityException = (AvailabilityException)zzcv1.getException();
            if (zzad.zze(this.zzfky)) {
              zzad zzad1 = this.zzfky;
              ArrayMap arrayMap = new ArrayMap();
              this(zzad.zzm(zzad1).size());
              zzad.zzb(zzad1, (Map)arrayMap);
              for (zzac zzac : zzad.zzm(this.zzfky).values()) {
                Map<zzh, ConnectionResult> map;
                zzh zzh = zzac.zzafk();
                connectionResult = availabilityException.getConnectionResult(zzac);
                if (zzad.zza(this.zzfky, zzac, connectionResult)) {
                  map = zzad.zzg(this.zzfky);
                  connectionResult = new ConnectionResult();
                  this(16);
                } else {
                  map = zzad.zzg(this.zzfky);
                } 
                map.put(zzh, connectionResult);
              } 
            } else {
              zzad.zzb(this.zzfky, (Map)availabilityException.zzafh());
            } 
          } else {
            Log.e("ConnectionlessGAC", "Unexpected availability exception", connectionResult.getException());
            zzad.zzb(this.zzfky, Collections.emptyMap());
          } 
        } 
        if (this.zzfky.isConnected()) {
          zzad.zzd(this.zzfky).putAll(zzad.zzg(this.zzfky));
          if (zzad.zzf(this.zzfky) == null) {
            zzad.zzi(this.zzfky);
            zzad.zzj(this.zzfky);
            zzad.zzl(this.zzfky).signalAll();
          } 
        } 
        zzcv1 = this.zzfkz;
      } 
      zzcv1.zzaak();
      return;
    } finally {
      zzad.zza(this.zzfky).unlock();
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzag.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */