package com.google.android.gms.internal;

import java.io.IOException;

public final class zzevr extends zzevh<zzevr, zzevr.zza> implements zzewn {
  private static volatile zzewp<zzevr> zzbar;
  
  private static final zzevr zzopb;
  
  private int zzopa;
  
  static {
    zzevr zzevr1 = new zzevr();
    zzopb = zzevr1;
    zzevr1.zza(zzevp.zzoor, (Object)null, (Object)null);
    zzevr1.zzooe.zzbhs();
  }
  
  private final void setValue(int paramInt) {
    this.zzopa = paramInt;
  }
  
  public static zza zzcui() {
    zzevr zzevr1 = zzopb;
    zzevi zzevi = (zzevi)zzevr1.zza(zzevp.zzoot, (Object)null, (Object)null);
    zzevi.zza(zzevr1);
    return (zza)zzevi;
  }
  
  public static zzevr zzcuj() {
    return zzopb;
  }
  
  public final int getValue() {
    return this.zzopa;
  }
  
  protected final Object zza(int paramInt, Object<zzevh> paramObject1, Object paramObject2) {
    // Byte code:
    //   0: getstatic com/google/android/gms/internal/zzevs.zzbap : [I
    //   3: astore #4
    //   5: iconst_1
    //   6: istore #5
    //   8: aload #4
    //   10: iload_1
    //   11: iconst_1
    //   12: isub
    //   13: iaload
    //   14: tableswitch default -> 60, 1 -> 346, 2 -> 342, 3 -> 340, 4 -> 331, 5 -> 267, 6 -> 114, 7 -> 263, 8 -> 68
    //   60: new java/lang/UnsupportedOperationException
    //   63: dup
    //   64: invokespecial <init> : ()V
    //   67: athrow
    //   68: getstatic com/google/android/gms/internal/zzevr.zzbar : Lcom/google/android/gms/internal/zzewp;
    //   71: ifnonnull -> 110
    //   74: ldc com/google/android/gms/internal/zzevr
    //   76: monitorenter
    //   77: getstatic com/google/android/gms/internal/zzevr.zzbar : Lcom/google/android/gms/internal/zzewp;
    //   80: ifnonnull -> 98
    //   83: new com/google/android/gms/internal/zzevj
    //   86: astore_2
    //   87: aload_2
    //   88: getstatic com/google/android/gms/internal/zzevr.zzopb : Lcom/google/android/gms/internal/zzevr;
    //   91: invokespecial <init> : (Lcom/google/android/gms/internal/zzevh;)V
    //   94: aload_2
    //   95: putstatic com/google/android/gms/internal/zzevr.zzbar : Lcom/google/android/gms/internal/zzewp;
    //   98: ldc com/google/android/gms/internal/zzevr
    //   100: monitorexit
    //   101: goto -> 110
    //   104: astore_2
    //   105: ldc com/google/android/gms/internal/zzevr
    //   107: monitorexit
    //   108: aload_2
    //   109: athrow
    //   110: getstatic com/google/android/gms/internal/zzevr.zzbar : Lcom/google/android/gms/internal/zzewp;
    //   113: areturn
    //   114: aload_2
    //   115: checkcast com/google/android/gms/internal/zzeut
    //   118: astore_2
    //   119: iconst_0
    //   120: istore_1
    //   121: iload_1
    //   122: ifne -> 263
    //   125: aload_2
    //   126: invokevirtual zzcsn : ()I
    //   129: istore #6
    //   131: iload #6
    //   133: ifeq -> 206
    //   136: iload #6
    //   138: bipush #8
    //   140: if_icmpeq -> 195
    //   143: iload #6
    //   145: bipush #7
    //   147: iand
    //   148: iconst_4
    //   149: if_icmpne -> 158
    //   152: iconst_0
    //   153: istore #7
    //   155: goto -> 187
    //   158: aload_0
    //   159: getfield zzooe : Lcom/google/android/gms/internal/zzexl;
    //   162: invokestatic zzcvp : ()Lcom/google/android/gms/internal/zzexl;
    //   165: if_acmpne -> 175
    //   168: aload_0
    //   169: invokestatic zzcvq : ()Lcom/google/android/gms/internal/zzexl;
    //   172: putfield zzooe : Lcom/google/android/gms/internal/zzexl;
    //   175: aload_0
    //   176: getfield zzooe : Lcom/google/android/gms/internal/zzexl;
    //   179: iload #6
    //   181: aload_2
    //   182: invokevirtual zzb : (ILcom/google/android/gms/internal/zzeut;)Z
    //   185: istore #7
    //   187: iload #7
    //   189: ifne -> 121
    //   192: goto -> 206
    //   195: aload_0
    //   196: aload_2
    //   197: invokevirtual zzcsq : ()I
    //   200: putfield zzopa : I
    //   203: goto -> 121
    //   206: iconst_1
    //   207: istore_1
    //   208: goto -> 121
    //   211: astore_2
    //   212: goto -> 261
    //   215: astore #4
    //   217: new java/lang/RuntimeException
    //   220: astore_2
    //   221: new com/google/android/gms/internal/zzevz
    //   224: astore_3
    //   225: aload_3
    //   226: aload #4
    //   228: invokevirtual getMessage : ()Ljava/lang/String;
    //   231: invokespecial <init> : (Ljava/lang/String;)V
    //   234: aload_2
    //   235: aload_3
    //   236: aload_0
    //   237: invokevirtual zzh : (Lcom/google/android/gms/internal/zzewl;)Lcom/google/android/gms/internal/zzevz;
    //   240: invokespecial <init> : (Ljava/lang/Throwable;)V
    //   243: aload_2
    //   244: athrow
    //   245: astore_2
    //   246: new java/lang/RuntimeException
    //   249: astore_3
    //   250: aload_3
    //   251: aload_2
    //   252: aload_0
    //   253: invokevirtual zzh : (Lcom/google/android/gms/internal/zzewl;)Lcom/google/android/gms/internal/zzevz;
    //   256: invokespecial <init> : (Ljava/lang/Throwable;)V
    //   259: aload_3
    //   260: athrow
    //   261: aload_2
    //   262: athrow
    //   263: getstatic com/google/android/gms/internal/zzevr.zzopb : Lcom/google/android/gms/internal/zzevr;
    //   266: areturn
    //   267: aload_2
    //   268: checkcast com/google/android/gms/internal/zzevq
    //   271: astore_2
    //   272: aload_3
    //   273: checkcast com/google/android/gms/internal/zzevr
    //   276: astore_3
    //   277: aload_0
    //   278: getfield zzopa : I
    //   281: istore #6
    //   283: iload #6
    //   285: ifeq -> 294
    //   288: iconst_1
    //   289: istore #7
    //   291: goto -> 297
    //   294: iconst_0
    //   295: istore #7
    //   297: aload_3
    //   298: getfield zzopa : I
    //   301: istore_1
    //   302: iload_1
    //   303: ifeq -> 309
    //   306: goto -> 312
    //   309: iconst_0
    //   310: istore #5
    //   312: aload_0
    //   313: aload_2
    //   314: iload #7
    //   316: iload #6
    //   318: iload #5
    //   320: iload_1
    //   321: invokeinterface zza : (ZIZI)I
    //   326: putfield zzopa : I
    //   329: aload_0
    //   330: areturn
    //   331: new com/google/android/gms/internal/zzevr$zza
    //   334: dup
    //   335: aconst_null
    //   336: invokespecial <init> : (Lcom/google/android/gms/internal/zzevs;)V
    //   339: areturn
    //   340: aconst_null
    //   341: areturn
    //   342: getstatic com/google/android/gms/internal/zzevr.zzopb : Lcom/google/android/gms/internal/zzevr;
    //   345: areturn
    //   346: new com/google/android/gms/internal/zzevr
    //   349: dup
    //   350: invokespecial <init> : ()V
    //   353: areturn
    // Exception table:
    //   from	to	target	type
    //   77	98	104	finally
    //   98	101	104	finally
    //   105	108	104	finally
    //   125	131	245	com/google/android/gms/internal/zzevz
    //   125	131	215	java/io/IOException
    //   125	131	211	finally
    //   158	175	245	com/google/android/gms/internal/zzevz
    //   158	175	215	java/io/IOException
    //   158	175	211	finally
    //   175	187	245	com/google/android/gms/internal/zzevz
    //   175	187	215	java/io/IOException
    //   175	187	211	finally
    //   195	203	245	com/google/android/gms/internal/zzevz
    //   195	203	215	java/io/IOException
    //   195	203	211	finally
    //   217	245	211	finally
    //   246	261	211	finally
  }
  
  public final void zza(zzeuy paramzzeuy) throws IOException {
    int i = this.zzopa;
    if (i != 0)
      paramzzeuy.zzx(1, i); 
    this.zzooe.zza(paramzzeuy);
  }
  
  public final int zzhi() {
    int i = this.zzoof;
    if (i != -1)
      return i; 
    int j = this.zzopa;
    i = 0;
    if (j != 0)
      i = 0 + zzeuy.zzaa(1, j); 
    i += this.zzooe.zzhi();
    this.zzoof = i;
    return i;
  }
  
  public static final class zza extends zzevi<zzevr, zza> implements zzewn {
    private zza() {
      super(zzevr.zzcuk());
    }
    
    public final zza zzkn(int param1Int) {
      zzcud();
      zzevr.zza((zzevr)this.zzooh, param1Int);
      return this;
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzevr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */