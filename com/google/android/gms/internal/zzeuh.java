package com.google.android.gms.internal;

import java.io.IOException;

public final class zzeuh extends zzevh<zzeuh, zzeuh.zza> implements zzewn {
  private static volatile zzewp<zzeuh> zzbar;
  
  private static final zzeuh zzomw;
  
  private String zzlek = "";
  
  private zzeuk zzlel = zzeuk.zzomx;
  
  static {
    zzeuh zzeuh1 = new zzeuh();
    zzomw = zzeuh1;
    zzeuh1.zza(zzevp.zzoor, (Object)null, (Object)null);
    zzeuh1.zzooe.zzbhs();
  }
  
  public static zzeuh zzcse() {
    return zzomw;
  }
  
  protected final Object zza(int paramInt, Object<zzevh> paramObject1, Object paramObject2) {
    // Byte code:
    //   0: getstatic com/google/android/gms/internal/zzeui.zzbap : [I
    //   3: astore #4
    //   5: iconst_1
    //   6: istore #5
    //   8: aload #4
    //   10: iload_1
    //   11: iconst_1
    //   12: isub
    //   13: iaload
    //   14: tableswitch default -> 60, 1 -> 414, 2 -> 410, 3 -> 408, 4 -> 399, 5 -> 286, 6 -> 114, 7 -> 282, 8 -> 68
    //   60: new java/lang/UnsupportedOperationException
    //   63: dup
    //   64: invokespecial <init> : ()V
    //   67: athrow
    //   68: getstatic com/google/android/gms/internal/zzeuh.zzbar : Lcom/google/android/gms/internal/zzewp;
    //   71: ifnonnull -> 110
    //   74: ldc com/google/android/gms/internal/zzeuh
    //   76: monitorenter
    //   77: getstatic com/google/android/gms/internal/zzeuh.zzbar : Lcom/google/android/gms/internal/zzewp;
    //   80: ifnonnull -> 98
    //   83: new com/google/android/gms/internal/zzevj
    //   86: astore_2
    //   87: aload_2
    //   88: getstatic com/google/android/gms/internal/zzeuh.zzomw : Lcom/google/android/gms/internal/zzeuh;
    //   91: invokespecial <init> : (Lcom/google/android/gms/internal/zzevh;)V
    //   94: aload_2
    //   95: putstatic com/google/android/gms/internal/zzeuh.zzbar : Lcom/google/android/gms/internal/zzewp;
    //   98: ldc com/google/android/gms/internal/zzeuh
    //   100: monitorexit
    //   101: goto -> 110
    //   104: astore_2
    //   105: ldc com/google/android/gms/internal/zzeuh
    //   107: monitorexit
    //   108: aload_2
    //   109: athrow
    //   110: getstatic com/google/android/gms/internal/zzeuh.zzbar : Lcom/google/android/gms/internal/zzewp;
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
    //   138: bipush #10
    //   140: if_icmpeq -> 213
    //   143: iload #6
    //   145: bipush #18
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
    //   204: invokevirtual zzcsv : ()Lcom/google/android/gms/internal/zzeuk;
    //   207: putfield zzlel : Lcom/google/android/gms/internal/zzeuk;
    //   210: goto -> 121
    //   213: aload_0
    //   214: aload_2
    //   215: invokevirtual zzcsu : ()Ljava/lang/String;
    //   218: putfield zzlek : Ljava/lang/String;
    //   221: goto -> 121
    //   224: iconst_1
    //   225: istore_1
    //   226: goto -> 121
    //   229: astore_2
    //   230: goto -> 280
    //   233: astore_2
    //   234: new java/lang/RuntimeException
    //   237: astore_3
    //   238: new com/google/android/gms/internal/zzevz
    //   241: astore #4
    //   243: aload #4
    //   245: aload_2
    //   246: invokevirtual getMessage : ()Ljava/lang/String;
    //   249: invokespecial <init> : (Ljava/lang/String;)V
    //   252: aload_3
    //   253: aload #4
    //   255: aload_0
    //   256: invokevirtual zzh : (Lcom/google/android/gms/internal/zzewl;)Lcom/google/android/gms/internal/zzevz;
    //   259: invokespecial <init> : (Ljava/lang/Throwable;)V
    //   262: aload_3
    //   263: athrow
    //   264: astore_2
    //   265: new java/lang/RuntimeException
    //   268: astore_3
    //   269: aload_3
    //   270: aload_2
    //   271: aload_0
    //   272: invokevirtual zzh : (Lcom/google/android/gms/internal/zzewl;)Lcom/google/android/gms/internal/zzevz;
    //   275: invokespecial <init> : (Ljava/lang/Throwable;)V
    //   278: aload_3
    //   279: athrow
    //   280: aload_2
    //   281: athrow
    //   282: getstatic com/google/android/gms/internal/zzeuh.zzomw : Lcom/google/android/gms/internal/zzeuh;
    //   285: areturn
    //   286: aload_2
    //   287: checkcast com/google/android/gms/internal/zzevq
    //   290: astore_2
    //   291: aload_3
    //   292: checkcast com/google/android/gms/internal/zzeuh
    //   295: astore #8
    //   297: aload_0
    //   298: aload_2
    //   299: aload_0
    //   300: getfield zzlek : Ljava/lang/String;
    //   303: invokevirtual isEmpty : ()Z
    //   306: iconst_1
    //   307: ixor
    //   308: aload_0
    //   309: getfield zzlek : Ljava/lang/String;
    //   312: aload #8
    //   314: getfield zzlek : Ljava/lang/String;
    //   317: invokevirtual isEmpty : ()Z
    //   320: iconst_1
    //   321: ixor
    //   322: aload #8
    //   324: getfield zzlek : Ljava/lang/String;
    //   327: invokeinterface zza : (ZLjava/lang/String;ZLjava/lang/String;)Ljava/lang/String;
    //   332: putfield zzlek : Ljava/lang/String;
    //   335: aload_0
    //   336: getfield zzlel : Lcom/google/android/gms/internal/zzeuk;
    //   339: astore #4
    //   341: getstatic com/google/android/gms/internal/zzeuk.zzomx : Lcom/google/android/gms/internal/zzeuk;
    //   344: astore_3
    //   345: aload #4
    //   347: aload_3
    //   348: if_acmpeq -> 357
    //   351: iconst_1
    //   352: istore #7
    //   354: goto -> 360
    //   357: iconst_0
    //   358: istore #7
    //   360: aload #8
    //   362: getfield zzlel : Lcom/google/android/gms/internal/zzeuk;
    //   365: astore #8
    //   367: aload #8
    //   369: aload_3
    //   370: if_acmpeq -> 376
    //   373: goto -> 379
    //   376: iconst_0
    //   377: istore #5
    //   379: aload_0
    //   380: aload_2
    //   381: iload #7
    //   383: aload #4
    //   385: iload #5
    //   387: aload #8
    //   389: invokeinterface zza : (ZLcom/google/android/gms/internal/zzeuk;ZLcom/google/android/gms/internal/zzeuk;)Lcom/google/android/gms/internal/zzeuk;
    //   394: putfield zzlel : Lcom/google/android/gms/internal/zzeuk;
    //   397: aload_0
    //   398: areturn
    //   399: new com/google/android/gms/internal/zzeuh$zza
    //   402: dup
    //   403: aconst_null
    //   404: invokespecial <init> : (Lcom/google/android/gms/internal/zzeui;)V
    //   407: areturn
    //   408: aconst_null
    //   409: areturn
    //   410: getstatic com/google/android/gms/internal/zzeuh.zzomw : Lcom/google/android/gms/internal/zzeuh;
    //   413: areturn
    //   414: new com/google/android/gms/internal/zzeuh
    //   417: dup
    //   418: invokespecial <init> : ()V
    //   421: areturn
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
    if (!this.zzlek.isEmpty())
      paramzzeuy.zzm(1, this.zzlek); 
    if (!this.zzlel.isEmpty())
      paramzzeuy.zza(2, this.zzlel); 
    this.zzooe.zza(paramzzeuy);
  }
  
  public final int zzhi() {
    int i = this.zzoof;
    if (i != -1)
      return i; 
    boolean bool = this.zzlek.isEmpty();
    i = 0;
    if (!bool)
      i = 0 + zzeuy.zzn(1, this.zzlek); 
    int j = i;
    if (!this.zzlel.isEmpty())
      j = i + zzeuy.zzb(2, this.zzlel); 
    i = j + this.zzooe.zzhi();
    this.zzoof = i;
    return i;
  }
  
  public static final class zza extends zzevi<zzeuh, zza> implements zzewn {
    private zza() {
      super(zzeuh.zzcsf());
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzeuh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */