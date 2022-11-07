package com.google.firebase.auth;

import android.support.annotation.Nullable;

public class GetTokenResult {
  private String zzdxt;
  
  public GetTokenResult(String paramString) {
    this.zzdxt = paramString;
  }
  
  @Nullable
  public String getToken() {
    return this.zzdxt;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/firebase/auth/GetTokenResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */