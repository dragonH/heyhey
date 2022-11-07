package com.google.android.gms.internal;

import java.io.IOException;

public final class zzexi extends zzevh<zzexi, zzexi.zza> implements zzewn {
  private static volatile zzewp<zzexi> zzbar;
  
  private static final zzexi zzoqw;
  
  private long zzoqu;
  
  private int zzoqv;
  
  static {
    zzexi zzexi1 = new zzexi();
    zzoqw = zzexi1;
    zzexi1.zza(zzevp.zzoor, (Object)null, (Object)null);
    zzexi1.zzooe.zzbhs();
  }
  
  private final void setNanos(int paramInt) {
    this.zzoqv = paramInt;
  }
  
  public static zza zzcvl() {
    zzexi zzexi1 = zzoqw;
    zzevi zzevi = (zzevi)zzexi1.zza(zzevp.zzoot, (Object)null, (Object)null);
    zzevi.zza(zzexi1);
    return (zza)zzevi;
  }
  
  public static zzexi zzcvm() {
    return zzoqw;
  }
  
  private final void zzdd(long paramLong) {
    this.zzoqu = paramLong;
  }
  
  public final int getNanos() {
    return this.zzoqv;
  }
  
  protected final Object zza(int paramInt, Object<zzevh> paramObject1, Object paramObject2) {
    // Byte code:
    //   0: getstatic com/google/android/gms/internal/zzexj.zzbap : [I
    //   3: astore #4
    //   5: iconst_1
    //   6: istore #5
    //   8: aload #4
    //   10: iload_1
    //   11: iconst_1
    //   12: isub
    //   13: iaload
    //   14: tableswitch default -> 60, 1 -> 431, 2 -> 427, 3 -> 425, 4 -> 416, 5 -> 286, 6 -> 114, 7 -> 282, 8 -> 68
    //   60: new java/lang/UnsupportedOperationException
    //   63: dup
    //   64: invokespecial <init> : ()V
    //   67: athrow
    //   68: getstatic com/google/android/gms/internal/zzexi.zzbar : Lcom/google/android/gms/internal/zzewp;
    //   71: ifnonnull -> 110
    //   74: ldc com/google/android/gms/internal/zzexi
    //   76: monitorenter
    //   77: getstatic com/google/android/gms/internal/zzexi.zzbar : Lcom/google/android/gms/internal/zzewp;
    //   80: ifnonnull -> 98
    //   83: new com/google/android/gms/internal/zzevj
    //   86: astore_2
    //   87: aload_2
    //   88: getstatic com/google/android/gms/internal/zzexi.zzoqw : Lcom/google/android/gms/internal/zzexi;
    //   91: invokespecial <init> : (Lcom/google/android/gms/internal/zzevh;)V
    //   94: aload_2
    //   95: putstatic com/google/android/gms/internal/zzexi.zzbar : Lcom/google/android/gms/internal/zzewp;
    //   98: ldc com/google/android/gms/internal/zzexi
    //   100: monitorexit
    //   101: goto -> 110
    //   104: astore_2
    //   105: ldc com/google/android/gms/internal/zzexi
    //   107: monitorexit
    //   108: aload_2
    //   109: athrow
    //   110: getstatic com/google/android/gms/internal/zzexi.zzbar : Lcom/google/android/gms/internal/zzewp;
    //   113: areturn
    //   114: aload_2
    //   115: checkcast com/google/android/gms/internal/zzeut
    //   118: astore_2
    //   119: iconst_0
    //   120: istore_1
    //   121: iload_1
    //   122: ifne -> 282
    //   125: aload_2
    //   126: invokevirtual zzcsn : ()I
    //   129: istore #6
    //   131: iload #6
    //   133: ifeq -> 224
    //   136: iload #6
    //   138: bipush #8
    //   140: if_icmpeq -> 213
    //   143: iload #6
    //   145: bipush #16
    //   147: if_icmpeq -> 202
    //   150: iload #6
    //   152: bipush #7
    //   154: iand
    //   155: iconst_4
    //   156: if_icmpne -> 165
    //   159: iconst_0
    //   160: istore #7
    //   162: goto -> 194
    //   165: aload_0
    //   166: getfield zzooe : Lcom/google/android/gms/internal/zzexl;
    //   169: invokestatic zzcvp : ()Lcom/google/android/gms/internal/zzexl;
    //   172: if_acmpne -> 182
    //   175: aload_0
    //   176: invokestatic zzcvq : ()Lcom/google/android/gms/internal/zzexl;
    //   179: putfield zzooe : Lcom/google/android/gms/internal/zzexl;
    //   182: aload_0
    //   183: getfield zzooe : Lcom/google/android/gms/internal/zzexl;
    //   186: iload #6
    //   188: aload_2
    //   189: invokevirtual zzb : (ILcom/google/android/gms/internal/zzeut;)Z
    //   192: istore #7
    //   194: iload #7
    //   196: ifne -> 121
    //   199: goto -> 224
    //   202: aload_0
    //   203: aload_2
    //   204: invokevirtual zzcsq : ()I
    //   207: putfield zzoqv : I
    //   210: goto -> 121
    //   213: aload_0
    //   214: aload_2
    //   215: invokevirtual zzcsp : ()J
    //   218: putfield zzoqu : J
    //   221: goto -> 121
    //   224: iconst_1
    //   225: istore_1
    //   226: goto -> 121
    //   229: astore_2
    //   230: goto -> 280
    //   233: astore_3
    //   234: new java/lang/RuntimeException
    //   237: astore #4
    //   239: new com/google/android/gms/internal/zzevz
    //   242: astore_2
    //   243: aload_2
    //   244: aload_3
    //   245: invokevirtual getMessage : ()Ljava/lang/String;
    //   248: invokespecial <init> : (Ljava/lang/String;)V
    //   251: aload #4
    //   253: aload_2
    //   254: aload_0
    //   255: invokevirtual zzh : (Lcom/google/android/gms/internal/zzewl;)Lcom/google/android/gms/internal/zzevz;
    //   258: invokespecial <init> : (Ljava/lang/Throwable;)V
    //   261: aload #4
    //   263: athrow
    //   264: astore_3
    //   265: new java/lang/RuntimeException
    //   268: astore_2
    //   269: aload_2
    //   270: aload_3
    //   271: aload_0
    //   272: invokevirtual zzh : (Lcom/google/android/gms/internal/zzewl;)Lcom/google/android/gms/internal/zzevz;
    //   275: invokespecial <init> : (Ljava/lang/Throwable;)V
    //   278: aload_2
    //   279: athrow
    //   280: aload_2
    //   281: athrow
    //   282: getstatic com/google/android/gms/internal/zzexi.zzoqw : Lcom/google/android/gms/internal/zzexi;
    //   285: areturn
    //   286: aload_2
    //   287: checkcast com/google/android/gms/internal/zzevq
    //   290: astore_2
    //   291: aload_3
    //   292: checkcast com/google/android/gms/internal/zzexi
    //   295: astore_3
    //   296: aload_0
    //   297: getfield zzoqu : J
    //   300: lstore #8
    //   302: lload #8
    //   304: lconst_0
    //   305: lcmp
    //   306: ifeq -> 315
    //   309: iconst_1
    //   310: istore #7
    //   312: goto -> 318
    //   315: iconst_0
    //   316: istore #7
    //   318: aload_3
    //   319: getfield zzoqu : J
    //   322: lstore #10
    //   324: lload #10
    //   326: lconst_0
    //   327: lcmp
    //   328: ifeq -> 337
    //   331: iconst_1
    //   332: istore #12
    //   334: goto -> 340
    //   337: iconst_0
    //   338: istore #12
    //   340: aload_0
    //   341: aload_2
    //   342: iload #7
    //   344: lload #8
    //   346: iload #12
    //   348: lload #10
    //   350: invokeinterface zza : (ZJZJ)J
    //   355: putfield zzoqu : J
    //   358: aload_0
    //   359: getfield zzoqv : I
    //   362: istore #6
    //   364: iload #6
    //   366: ifeq -> 375
    //   369: iconst_1
    //   370: istore #7
    //   372: goto -> 378
    //   375: iconst_0
    //   376: istore #7
    //   378: aload_3
    //   379: getfield zzoqv : I
    //   382: istore_1
    //   383: iload_1
    //   384: ifeq -> 394
    //   387: iload #5
    //   389: istore #12
    //   391: goto -> 397
    //   394: iconst_0
    //   395: istore #12
    //   397: aload_0
    //   398: aload_2
    //   399: iload #7
    //   401: iload #6
    //   403: iload #12
    //   405: iload_1
    //   406: invokeinterface zza : (ZIZI)I
    //   411: putfield zzoqv : I
    //   414: aload_0
    //   415: areturn
    //   416: new com/google/android/gms/internal/zzexi$zza
    //   419: dup
    //   420: aconst_null
    //   421: invokespecial <init> : (Lcom/google/android/gms/internal/zzexj;)V
    //   424: areturn
    //   425: aconst_null
    //   426: areturn
    //   427: getstatic com/google/android/gms/internal/zzexi.zzoqw : Lcom/google/android/gms/internal/zzexi;
    //   430: areturn
    //   431: new com/google/android/gms/internal/zzexi
    //   434: dup
    //   435: invokespecial <init> : ()V
    //   438: areturn
    // Exception table:
    //   from	to	target	type
    //   77	98	104	finally
    //   98	101	104	finally
    //   105	108	104	finally
    //   125	131	264	com/google/android/gms/internal/zzevz
    //   125	131	233	java/io/IOException
    //   125	131	229	finally
    //   165	182	264	com/google/android/gms/internal/zzevz
    //   165	182	233	java/io/IOException
    //   165	182	229	finally
    //   182	194	264	com/google/android/gms/internal/zzevz
    //   182	194	233	java/io/IOException
    //   182	194	229	finally
    //   202	210	264	com/google/android/gms/internal/zzevz
    //   202	210	233	java/io/IOException
    //   202	210	229	finally
    //   213	221	264	com/google/android/gms/internal/zzevz
    //   213	221	233	java/io/IOException
    //   213	221	229	finally
    //   234	264	229	finally
    //   265	280	229	finally
  }
  
  public final void zza(zzeuy paramzzeuy) throws IOException {
    long l = this.zzoqu;
    if (l != 0L)
      paramzzeuy.zza(1, l); 
    int i = this.zzoqv;
    if (i != 0)
      paramzzeuy.zzx(2, i); 
    this.zzooe.zza(paramzzeuy);
  }
  
  public final long zzcca() {
    return this.zzoqu;
  }
  
  public final int zzhi() {
    int i = this.zzoof;
    if (i != -1)
      return i; 
    long l = this.zzoqu;
    i = 0;
    if (l != 0L)
      i = 0 + zzeuy.zzc(1, l); 
    int j = this.zzoqv;
    int k = i;
    if (j != 0)
      k = i + zzeuy.zzaa(2, j); 
    i = k + this.zzooe.zzhi();
    this.zzoof = i;
    return i;
  }
  
  public static final class zza extends zzevi<zzexi, zza> implements zzewn {
    private zza() {
      super(zzexi.zzcvn());
    }
    
    public final zza zzde(long param1Long) {
      zzcud();
      zzexi.zza((zzexi)this.zzooh, param1Long);
      return this;
    }
    
    public final zza zzkx(int param1Int) {
      zzcud();
      zzexi.zza((zzexi)this.zzooh, param1Int);
      return this;
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzexi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */