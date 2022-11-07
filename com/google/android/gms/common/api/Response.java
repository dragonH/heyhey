package com.google.android.gms.common.api;

import android.support.annotation.NonNull;

public class Response<T extends Result> {
  private T zzfhr;
  
  public Response() {}
  
  protected Response(@NonNull T paramT) {
    this.zzfhr = paramT;
  }
  
  @NonNull
  protected T getResult() {
    return this.zzfhr;
  }
  
  public void setResult(@NonNull T paramT) {
    this.zzfhr = paramT;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/Response.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */