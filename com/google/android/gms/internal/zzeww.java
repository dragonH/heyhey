package com.google.android.gms.internal;

import java.io.IOException;
import java.io.InputStream;

final class zzeww extends InputStream {
  private int mark;
  
  private zzewv zzoqc;
  
  private zzeuq zzoqd;
  
  private int zzoqe;
  
  private int zzoqf;
  
  private int zzoqg;
  
  public zzeww(zzews paramzzews) {
    initialize();
  }
  
  private final void initialize() {
    zzewv zzewv1 = new zzewv(this.zzoqh, null);
    this.zzoqc = zzewv1;
    zzeuq zzeuq1 = (zzeuq)zzewv1.next();
    this.zzoqd = zzeuq1;
    this.zzoqe = zzeuq1.size();
    this.zzoqf = 0;
    this.zzoqg = 0;
  }
  
  private final void zzcvd() {
    if (this.zzoqd != null) {
      int i = this.zzoqf;
      int j = this.zzoqe;
      if (i == j) {
        this.zzoqg += j;
        this.zzoqf = 0;
        if (this.zzoqc.hasNext()) {
          zzeuq zzeuq1 = (zzeuq)this.zzoqc.next();
          this.zzoqd = zzeuq1;
          this.zzoqe = zzeuq1.size();
          return;
        } 
        this.zzoqd = null;
        this.zzoqe = 0;
      } 
    } 
  }
  
  private final int zzi(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    int i = paramInt2;
    int j;
    for (j = paramInt1; i > 0; j = paramInt1) {
      zzcvd();
      if (this.zzoqd == null) {
        if (i == paramInt2)
          return -1; 
        break;
      } 
      int k = Math.min(this.zzoqe - this.zzoqf, i);
      paramInt1 = j;
      if (paramArrayOfbyte != null) {
        this.zzoqd.zza(paramArrayOfbyte, this.zzoqf, j, k);
        paramInt1 = j + k;
      } 
      this.zzoqf += k;
      i -= k;
    } 
    return paramInt2 - i;
  }
  
  public final int available() throws IOException {
    int i = this.zzoqg;
    int j = this.zzoqf;
    return this.zzoqh.size() - i + j;
  }
  
  public final void mark(int paramInt) {
    this.mark = this.zzoqg + this.zzoqf;
  }
  
  public final boolean markSupported() {
    return true;
  }
  
  public final int read() throws IOException {
    zzcvd();
    zzeuq zzeuq1 = this.zzoqd;
    if (zzeuq1 == null)
      return -1; 
    int i = this.zzoqf;
    this.zzoqf = i + 1;
    return zzeuq1.zzji(i) & 0xFF;
  }
  
  public final int read(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    paramArrayOfbyte.getClass();
    if (paramInt1 >= 0 && paramInt2 >= 0 && paramInt2 <= paramArrayOfbyte.length - paramInt1)
      return zzi(paramArrayOfbyte, paramInt1, paramInt2); 
    throw new IndexOutOfBoundsException();
  }
  
  public final void reset() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial initialize : ()V
    //   6: aload_0
    //   7: aconst_null
    //   8: iconst_0
    //   9: aload_0
    //   10: getfield mark : I
    //   13: invokespecial zzi : ([BII)I
    //   16: pop
    //   17: aload_0
    //   18: monitorexit
    //   19: return
    //   20: astore_1
    //   21: aload_0
    //   22: monitorexit
    //   23: aload_1
    //   24: athrow
    // Exception table:
    //   from	to	target	type
    //   2	17	20	finally
  }
  
  public final long skip(long paramLong) {
    if (paramLong >= 0L) {
      long l = paramLong;
      if (paramLong > 2147483647L)
        l = 2147483647L; 
      return zzi(null, 0, (int)l);
    } 
    throw new IndexOutOfBoundsException();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzeww.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */