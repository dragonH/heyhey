package com.google.android.gms.internal;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

final class zzaj extends FilterInputStream {
  private int zzca = 0;
  
  private zzaj(InputStream paramInputStream) {
    super(paramInputStream);
  }
  
  public final int read() throws IOException {
    int i = super.read();
    if (i != -1)
      this.zzca++; 
    return i;
  }
  
  public final int read(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException {
    paramInt1 = super.read(paramArrayOfbyte, paramInt1, paramInt2);
    if (paramInt1 != -1)
      this.zzca += paramInt1; 
    return paramInt1;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzaj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */