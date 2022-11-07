package com.google.firebase.iid;

import android.support.annotation.Nullable;

@Deprecated
public final class zzi {
  private final FirebaseInstanceId zznfi;
  
  private zzi(FirebaseInstanceId paramFirebaseInstanceId) {
    this.zznfi = paramFirebaseInstanceId;
  }
  
  public static zzi zzcfw() {
    return new zzi(FirebaseInstanceId.getInstance());
  }
  
  public final String getId() {
    return this.zznfi.getId();
  }
  
  @Nullable
  public final String getToken() {
    return this.zznfi.getToken();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/firebase/iid/zzi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */