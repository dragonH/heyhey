package com.google.android.gms.internal;

import java.io.IOException;

public final class zzeym extends IOException {
  public zzeym(String paramString) {
    super(paramString);
  }
  
  static zzeym zzcwc() {
    return new zzeym("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
  }
  
  static zzeym zzcwd() {
    return new zzeym("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
  }
  
  static zzeym zzcwe() {
    return new zzeym("CodedInputStream encountered a malformed varint.");
  }
  
  static zzeym zzcwf() {
    return new zzeym("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzeym.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */