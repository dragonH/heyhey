package com.google.firebase;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzcz;

public final class zzb implements zzcz {
  public final Exception zzs(Status paramStatus) {
    return (paramStatus.getStatusCode() == 8) ? new FirebaseException(paramStatus.zzafu()) : new FirebaseApiNotAvailableException(paramStatus.zzafu());
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/firebase/zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */