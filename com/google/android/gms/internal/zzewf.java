package com.google.android.gms.internal;

public class zzewf {
  private static final zzevd zzomv = zzevd.zzctu();
  
  private zzeuk zzopi;
  
  private volatile zzewl zzopj;
  
  private volatile zzeuk zzopk;
  
  private zzeuk toByteString() {
    // Byte code:
    //   0: aload_0
    //   1: getfield zzopk : Lcom/google/android/gms/internal/zzeuk;
    //   4: ifnull -> 12
    //   7: aload_0
    //   8: getfield zzopk : Lcom/google/android/gms/internal/zzeuk;
    //   11: areturn
    //   12: aload_0
    //   13: monitorenter
    //   14: aload_0
    //   15: getfield zzopk : Lcom/google/android/gms/internal/zzeuk;
    //   18: ifnull -> 30
    //   21: aload_0
    //   22: getfield zzopk : Lcom/google/android/gms/internal/zzeuk;
    //   25: astore_1
    //   26: aload_0
    //   27: monitorexit
    //   28: aload_1
    //   29: areturn
    //   30: aload_0
    //   31: getfield zzopj : Lcom/google/android/gms/internal/zzewl;
    //   34: ifnonnull -> 49
    //   37: getstatic com/google/android/gms/internal/zzeuk.zzomx : Lcom/google/android/gms/internal/zzeuk;
    //   40: astore_1
    //   41: aload_0
    //   42: aload_1
    //   43: putfield zzopk : Lcom/google/android/gms/internal/zzeuk;
    //   46: goto -> 62
    //   49: aload_0
    //   50: getfield zzopj : Lcom/google/android/gms/internal/zzewl;
    //   53: invokeinterface toByteString : ()Lcom/google/android/gms/internal/zzeuk;
    //   58: astore_1
    //   59: goto -> 41
    //   62: aload_0
    //   63: getfield zzopk : Lcom/google/android/gms/internal/zzeuk;
    //   66: astore_1
    //   67: aload_0
    //   68: monitorexit
    //   69: aload_1
    //   70: areturn
    //   71: astore_1
    //   72: aload_0
    //   73: monitorexit
    //   74: goto -> 79
    //   77: aload_1
    //   78: athrow
    //   79: goto -> 77
    // Exception table:
    //   from	to	target	type
    //   14	28	71	finally
    //   30	41	71	finally
    //   41	46	71	finally
    //   49	59	71	finally
    //   62	69	71	finally
    //   72	74	71	finally
  }
  
  private zzewl zzi(zzewl paramzzewl) {
    // Byte code:
    //   0: aload_0
    //   1: getfield zzopj : Lcom/google/android/gms/internal/zzewl;
    //   4: ifnonnull -> 57
    //   7: aload_0
    //   8: monitorenter
    //   9: aload_0
    //   10: getfield zzopj : Lcom/google/android/gms/internal/zzewl;
    //   13: ifnull -> 21
    //   16: aload_0
    //   17: monitorexit
    //   18: goto -> 57
    //   21: aload_0
    //   22: aload_1
    //   23: putfield zzopj : Lcom/google/android/gms/internal/zzewl;
    //   26: aload_0
    //   27: getstatic com/google/android/gms/internal/zzeuk.zzomx : Lcom/google/android/gms/internal/zzeuk;
    //   30: putfield zzopk : Lcom/google/android/gms/internal/zzeuk;
    //   33: goto -> 16
    //   36: astore_2
    //   37: aload_0
    //   38: aload_1
    //   39: putfield zzopj : Lcom/google/android/gms/internal/zzewl;
    //   42: aload_0
    //   43: getstatic com/google/android/gms/internal/zzeuk.zzomx : Lcom/google/android/gms/internal/zzeuk;
    //   46: putfield zzopk : Lcom/google/android/gms/internal/zzeuk;
    //   49: goto -> 16
    //   52: astore_1
    //   53: aload_0
    //   54: monitorexit
    //   55: aload_1
    //   56: athrow
    //   57: aload_0
    //   58: getfield zzopj : Lcom/google/android/gms/internal/zzewl;
    //   61: areturn
    // Exception table:
    //   from	to	target	type
    //   9	16	52	finally
    //   16	18	52	finally
    //   21	33	36	com/google/android/gms/internal/zzevz
    //   21	33	52	finally
    //   37	49	52	finally
    //   53	55	52	finally
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof zzewf))
      return false; 
    paramObject = paramObject;
    zzewl zzewl1 = this.zzopj;
    zzewl zzewl2 = ((zzewf)paramObject).zzopj;
    return (zzewl1 == null && zzewl2 == null) ? toByteString().equals(paramObject.toByteString()) : ((zzewl1 != null && zzewl2 != null) ? zzewl1.equals(zzewl2) : ((zzewl1 != null) ? zzewl1.equals(paramObject.zzi(zzewl1.zzcuc())) : zzi(zzewl2.zzcuc()).equals(zzewl2)));
  }
  
  public int hashCode() {
    return 1;
  }
  
  public final int zzhi() {
    return (this.zzopk != null) ? this.zzopk.size() : ((this.zzopj != null) ? this.zzopj.zzhi() : 0);
  }
  
  public final zzewl zzj(zzewl paramzzewl) {
    zzewl zzewl1 = this.zzopj;
    this.zzopi = null;
    this.zzopk = null;
    this.zzopj = paramzzewl;
    return zzewl1;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzewf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */