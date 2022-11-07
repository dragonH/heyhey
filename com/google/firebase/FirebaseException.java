package com.google.firebase;

import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzbp;

public class FirebaseException extends Exception {
  @Deprecated
  protected FirebaseException() {}
  
  public FirebaseException(@NonNull String paramString) {
    super(zzbp.zzh(paramString, "Detail message must not be empty"));
  }
  
  public FirebaseException(@NonNull String paramString, Throwable paramThrowable) {
    super(zzbp.zzh(paramString, "Detail message must not be empty"), paramThrowable);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/firebase/FirebaseException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */