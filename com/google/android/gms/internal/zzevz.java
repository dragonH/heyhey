package com.google.android.gms.internal;

import java.io.IOException;

public class zzevz extends IOException {
  private zzewl zzopg = null;
  
  public zzevz(String paramString) {
    super(paramString);
  }
  
  static zzevz zzcum() {
    return new zzevz("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
  }
  
  static zzevz zzcun() {
    return new zzevz("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
  }
  
  static zzevz zzcuo() {
    return new zzevz("CodedInputStream encountered a malformed varint.");
  }
  
  static zzevz zzcup() {
    return new zzevz("Protocol message contained an invalid tag (zero).");
  }
  
  static zzevz zzcuq() {
    return new zzevz("Protocol message end-group tag did not match expected tag.");
  }
  
  static zzewa zzcur() {
    return new zzewa("Protocol message tag had invalid wire type.");
  }
  
  static zzevz zzcus() {
    return new zzevz("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
  }
  
  static zzevz zzcut() {
    return new zzevz("Protocol message was too large.  May be malicious.  Use CodedInputStream.setSizeLimit() to increase the size limit.");
  }
  
  static zzevz zzcuu() {
    return new zzevz("Protocol message had invalid UTF-8.");
  }
  
  public final zzevz zzh(zzewl paramzzewl) {
    this.zzopg = paramzzewl;
    return this;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzevz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */