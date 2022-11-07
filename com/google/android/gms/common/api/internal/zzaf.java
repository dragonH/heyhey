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

final class zzaf implements OnCompleteListener<Void> {
  private zzaf(zzad paramzzad) {}
  
  public final void onComplete(@NonNull Task<Void> paramTask) {
    zzad.zza(this.zzfky).lock();
    try {
      boolean bool = zzad.zzb(this.zzfky);
      if (bool) {
        if (paramTask.isSuccessful()) {
          zzad zzad1 = this.zzfky;
          ArrayMap arrayMap = new ArrayMap();
          this(zzad.zzc(zzad1).size());
          zzad.zza(zzad1, (Map)arrayMap);
          for (zzac zzac : zzad.zzc(this.zzfky).values())
            zzad.zzd(this.zzfky).put(zzac.zzafk(), ConnectionResult.zzfff); 
        } else {
          ConnectionResult connectionResult;
          zzad zzad1;
          if (zzac.getException() instanceof AvailabilityException) {
            AvailabilityException availabilityException = (AvailabilityException)zzac.getException();
            if (zzad.zze(this.zzfky)) {
              zzad zzad2 = this.zzfky;
              ArrayMap arrayMap = new ArrayMap();
              this(zzad.zzc(zzad2).size());
              zzad.zza(zzad2, (Map)arrayMap);
              for (zzac zzac1 : zzad.zzc(this.zzfky).values()) {
                Map<zzh, ConnectionResult> map;
                zzh zzh = zzac1.zzafk();
                ConnectionResult connectionResult1 = availabilityException.getConnectionResult(zzac1);
                if (zzad.zza(this.zzfky, zzac1, connectionResult1)) {
                  map = zzad.zzd(this.zzfky);
                  connectionResult1 = new ConnectionResult();
                  this(16);
                } else {
                  map = zzad.zzd(this.zzfky);
                } 
                map.put(zzh, connectionResult1);
              } 
            } else {
              zzad.zza(this.zzfky, (Map)availabilityException.zzafh());
            } 
            zzad1 = this.zzfky;
            connectionResult = zzad.zzf(zzad1);
          } else {
            Log.e("ConnectionlessGAC", "Unexpected availability exception", connectionResult.getException());
            zzad.zza(this.zzfky, Collections.emptyMap());
            zzad1 = this.zzfky;
            connectionResult = new ConnectionResult(8);
          } 
          zzad.zza(zzad1, connectionResult);
        } 
        if (zzad.zzg(this.zzfky) != null) {
          zzad.zzd(this.zzfky).putAll(zzad.zzg(this.zzfky));
          zzad zzad1 = this.zzfky;
          zzad.zza(zzad1, zzad.zzf(zzad1));
        } 
        if (zzad.zzh(this.zzfky) == null) {
          zzad.zzi(this.zzfky);
          zzad.zzj(this.zzfky);
        } else {
          zzad.zza(this.zzfky, false);
          zzad.zzk(this.zzfky).zzc(zzad.zzh(this.zzfky));
        } 
        zzad.zzl(this.zzfky).signalAll();
      } 
      return;
    } finally {
      zzad.zza(this.zzfky).unlock();
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzaf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */