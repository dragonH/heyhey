package com.google.android.gms.internal;

import java.net.URI;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

public final class zzal extends HttpEntityEnclosingRequestBase {
  public zzal() {}
  
  public zzal(String paramString) {
    setURI(URI.create(paramString));
  }
  
  public final String getMethod() {
    return "PATCH";
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */