package com.google.firebase.auth;

import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzbp;
import com.google.firebase.FirebaseException;

public class FirebaseAuthException extends FirebaseException {
  private final String zzlla;
  
  public FirebaseAuthException(@NonNull String paramString1, @NonNull String paramString2) {
    super(paramString2);
    this.zzlla = zzbp.zzgg(paramString1);
  }
  
  @NonNull
  public String getErrorCode() {
    return this.zzlla;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/firebase/auth/FirebaseAuthException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */