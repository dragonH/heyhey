package com.google.android.gms.internal;

import java.io.IOException;

public final class zzezb extends zzevh<zzezb, zzezb.zza> implements zzewn {
  private static volatile zzewp<zzezb> zzbar;
  
  private static final zzezb zzovw;
  
  private int zzlfc;
  
  private int zzovt;
  
  private String zzovu = "";
  
  private zzevy<zzeuh> zzovv = zzevh.zzcua();
  
  static {
    zzezb zzezb1 = new zzezb();
    zzovw = zzezb1;
    zzezb1.zza(zzevp.zzoor, (Object)null, (Object)null);
    zzezb1.zzooe.zzbhs();
  }
  
  public static zzezb zzcwj() {
    return zzovw;
  }
  
  public final int getCode() {
    return this.zzovt;
  }
  
  public final String getMessage() {
    return this.zzovu;
  }
  
  protected final Object zza(int paramInt, Object<zzevh> paramObject1, Object paramObject2) {
    // Byte code:
    //   0: getstatic com/google/android/gms/internal/zzezc.zzbap : [I
    //   3: iload_1
    //   4: iconst_1
    //   5: isub
    //   6: iaload
    //   7: istore #4
    //   9: iconst_0
    //   10: istore #5
    //   12: iconst_0
    //   13: istore_1
    //   14: iload #4
    //   16: tableswitch default -> 64, 1 -> 502, 2 -> 498, 3 -> 487, 4 -> 478, 5 -> 343, 6 -> 118, 7 -> 339, 8 -> 72
    //   64: new java/lang/UnsupportedOperationException
    //   67: dup
    //   68: invokespecial <init> : ()V
    //   71: athrow
    //   72: getstatic com/google/android/gms/internal/zzezb.zzbar : Lcom/google/android/gms/internal/zzewp;
    //   75: ifnonnull -> 114
    //   78: ldc com/google/android/gms/internal/zzezb
    //   80: monitorenter
    //   81: getstatic com/google/android/gms/internal/zzezb.zzbar : Lcom/google/android/gms/internal/zzewp;
    //   84: ifnonnull -> 102
    //   87: new com/google/android/gms/internal/zzevj
    //   90: astore_2
    //   91: aload_2
    //   92: getstatic com/google/android/gms/internal/zzezb.zzovw : Lcom/google/android/gms/internal/zzezb;
    //   95: invokespecial <init> : (Lcom/google/android/gms/internal/zzevh;)V
    //   98: aload_2
    //   99: putstatic com/google/android/gms/internal/zzezb.zzbar : Lcom/google/android/gms/internal/zzewp;
    //   102: ldc com/google/android/gms/internal/zzezb
    //   104: monitorexit
    //   105: goto -> 114
    //   108: astore_2
    //   109: ldc com/google/android/gms/internal/zzezb
    //   111: monitorexit
    //   112: aload_2
    //   113: athrow
    //   114: getstatic com/google/android/gms/internal/zzezb.zzbar : Lcom/google/android/gms/internal/zzewp;
    //   117: areturn
    //   118: aload_2
    //   119: checkcast com/google/android/gms/internal/zzeut
    //   122: astore_2
    //   123: aload_3
    //   124: checkcast com/google/android/gms/internal/zzevd
    //   127: astore_3
    //   128: iload_1
    //   129: ifne -> 339
    //   132: aload_2
    //   133: invokevirtual zzcsn : ()I
    //   136: istore #4
    //   138: iload #4
    //   140: ifeq -> 281
    //   143: iload #4
    //   145: bipush #8
    //   147: if_icmpeq -> 270
    //   150: iload #4
    //   152: bipush #18
    //   154: if_icmpeq -> 259
    //   157: iload #4
    //   159: bipush #26
    //   161: if_icmpeq -> 177
    //   164: aload_0
    //   165: iload #4
    //   167: aload_2
    //   168: invokevirtual zza : (ILcom/google/android/gms/internal/zzeut;)Z
    //   171: ifne -> 128
    //   174: goto -> 281
    //   177: aload_0
    //   178: getfield zzovv : Lcom/google/android/gms/internal/zzevy;
    //   181: invokeinterface zzcsc : ()Z
    //   186: ifne -> 235
    //   189: aload_0
    //   190: getfield zzovv : Lcom/google/android/gms/internal/zzevy;
    //   193: astore #6
    //   195: aload #6
    //   197: invokeinterface size : ()I
    //   202: istore #4
    //   204: iload #4
    //   206: ifne -> 216
    //   209: bipush #10
    //   211: istore #4
    //   213: goto -> 222
    //   216: iload #4
    //   218: iconst_1
    //   219: ishl
    //   220: istore #4
    //   222: aload_0
    //   223: aload #6
    //   225: iload #4
    //   227: invokeinterface zzks : (I)Lcom/google/android/gms/internal/zzevy;
    //   232: putfield zzovv : Lcom/google/android/gms/internal/zzevy;
    //   235: aload_0
    //   236: getfield zzovv : Lcom/google/android/gms/internal/zzevy;
    //   239: aload_2
    //   240: invokestatic zzcse : ()Lcom/google/android/gms/internal/zzeuh;
    //   243: aload_3
    //   244: invokevirtual zza : (Lcom/google/android/gms/internal/zzevh;Lcom/google/android/gms/internal/zzevd;)Lcom/google/android/gms/internal/zzevh;
    //   247: checkcast com/google/android/gms/internal/zzeuh
    //   250: invokeinterface add : (Ljava/lang/Object;)Z
    //   255: pop
    //   256: goto -> 128
    //   259: aload_0
    //   260: aload_2
    //   261: invokevirtual zzcsu : ()Ljava/lang/String;
    //   264: putfield zzovu : Ljava/lang/String;
    //   267: goto -> 128
    //   270: aload_0
    //   271: aload_2
    //   272: invokevirtual zzcsq : ()I
    //   275: putfield zzovt : I
    //   278: goto -> 128
    //   281: iconst_1
    //   282: istore_1
    //   283: goto -> 128
    //   286: astore_2
    //   287: goto -> 337
    //   290: astore_3
    //   291: new java/lang/RuntimeException
    //   294: astore_2
    //   295: new com/google/android/gms/internal/zzevz
    //   298: astore #6
    //   300: aload #6
    //   302: aload_3
    //   303: invokevirtual getMessage : ()Ljava/lang/String;
    //   306: invokespecial <init> : (Ljava/lang/String;)V
    //   309: aload_2
    //   310: aload #6
    //   312: aload_0
    //   313: invokevirtual zzh : (Lcom/google/android/gms/internal/zzewl;)Lcom/google/android/gms/internal/zzevz;
    //   316: invokespecial <init> : (Ljava/lang/Throwable;)V
    //   319: aload_2
    //   320: athrow
    //   321: astore_2
    //   322: new java/lang/RuntimeException
    //   325: astore_3
    //   326: aload_3
    //   327: aload_2
    //   328: aload_0
    //   329: invokevirtual zzh : (Lcom/google/android/gms/internal/zzewl;)Lcom/google/android/gms/internal/zzevz;
    //   332: invokespecial <init> : (Ljava/lang/Throwable;)V
    //   335: aload_3
    //   336: athrow
    //   337: aload_2
    //   338: athrow
    //   339: getstatic com/google/android/gms/internal/zzezb.zzovw : Lcom/google/android/gms/internal/zzezb;
    //   342: areturn
    //   343: aload_2
    //   344: checkcast com/google/android/gms/internal/zzevq
    //   347: astore_2
    //   348: aload_3
    //   349: checkcast com/google/android/gms/internal/zzezb
    //   352: astore_3
    //   353: aload_0
    //   354: getfield zzovt : I
    //   357: istore_1
    //   358: iload_1
    //   359: ifeq -> 368
    //   362: iconst_1
    //   363: istore #7
    //   365: goto -> 371
    //   368: iconst_0
    //   369: istore #7
    //   371: aload_3
    //   372: getfield zzovt : I
    //   375: istore #4
    //   377: iload #4
    //   379: ifeq -> 385
    //   382: iconst_1
    //   383: istore #5
    //   385: aload_0
    //   386: aload_2
    //   387: iload #7
    //   389: iload_1
    //   390: iload #5
    //   392: iload #4
    //   394: invokeinterface zza : (ZIZI)I
    //   399: putfield zzovt : I
    //   402: aload_0
    //   403: aload_2
    //   404: aload_0
    //   405: getfield zzovu : Ljava/lang/String;
    //   408: invokevirtual isEmpty : ()Z
    //   411: iconst_1
    //   412: ixor
    //   413: aload_0
    //   414: getfield zzovu : Ljava/lang/String;
    //   417: iconst_1
    //   418: aload_3
    //   419: getfield zzovu : Ljava/lang/String;
    //   422: invokevirtual isEmpty : ()Z
    //   425: ixor
    //   426: aload_3
    //   427: getfield zzovu : Ljava/lang/String;
    //   430: invokeinterface zza : (ZLjava/lang/String;ZLjava/lang/String;)Ljava/lang/String;
    //   435: putfield zzovu : Ljava/lang/String;
    //   438: aload_0
    //   439: aload_2
    //   440: aload_0
    //   441: getfield zzovv : Lcom/google/android/gms/internal/zzevy;
    //   444: aload_3
    //   445: getfield zzovv : Lcom/google/android/gms/internal/zzevy;
    //   448: invokeinterface zza : (Lcom/google/android/gms/internal/zzevy;Lcom/google/android/gms/internal/zzevy;)Lcom/google/android/gms/internal/zzevy;
    //   453: putfield zzovv : Lcom/google/android/gms/internal/zzevy;
    //   456: aload_2
    //   457: getstatic com/google/android/gms/internal/zzevo.zzoon : Lcom/google/android/gms/internal/zzevo;
    //   460: if_acmpne -> 476
    //   463: aload_0
    //   464: aload_0
    //   465: getfield zzlfc : I
    //   468: aload_3
    //   469: getfield zzlfc : I
    //   472: ior
    //   473: putfield zzlfc : I
    //   476: aload_0
    //   477: areturn
    //   478: new com/google/android/gms/internal/zzezb$zza
    //   481: dup
    //   482: aconst_null
    //   483: invokespecial <init> : (Lcom/google/android/gms/internal/zzezc;)V
    //   486: areturn
    //   487: aload_0
    //   488: getfield zzovv : Lcom/google/android/gms/internal/zzevy;
    //   491: invokeinterface zzbhs : ()V
    //   496: aconst_null
    //   497: areturn
    //   498: getstatic com/google/android/gms/internal/zzezb.zzovw : Lcom/google/android/gms/internal/zzezb;
    //   501: areturn
    //   502: new com/google/android/gms/internal/zzezb
    //   505: dup
    //   506: invokespecial <init> : ()V
    //   509: areturn
    // Exception table:
    //   from	to	target	type
    //   81	102	108	finally
    //   102	105	108	finally
    //   109	112	108	finally
    //   132	138	321	com/google/android/gms/internal/zzevz
    //   132	138	290	java/io/IOException
    //   132	138	286	finally
    //   164	174	321	com/google/android/gms/internal/zzevz
    //   164	174	290	java/io/IOException
    //   164	174	286	finally
    //   177	204	321	com/google/android/gms/internal/zzevz
    //   177	204	290	java/io/IOException
    //   177	204	286	finally
    //   222	235	321	com/google/android/gms/internal/zzevz
    //   222	235	290	java/io/IOException
    //   222	235	286	finally
    //   235	256	321	com/google/android/gms/internal/zzevz
    //   235	256	290	java/io/IOException
    //   235	256	286	finally
    //   259	267	321	com/google/android/gms/internal/zzevz
    //   259	267	290	java/io/IOException
    //   259	267	286	finally
    //   270	278	321	com/google/android/gms/internal/zzevz
    //   270	278	290	java/io/IOException
    //   270	278	286	finally
    //   291	321	286	finally
    //   322	337	286	finally
  }
  
  public final void zza(zzeuy paramzzeuy) throws IOException {
    int i = this.zzovt;
    if (i != 0)
      paramzzeuy.zzx(1, i); 
    if (!this.zzovu.isEmpty())
      paramzzeuy.zzm(2, this.zzovu); 
    for (i = 0; i < this.zzovv.size(); i++)
      paramzzeuy.zza(3, this.zzovv.get(i)); 
    this.zzooe.zza(paramzzeuy);
  }
  
  public final int zzhi() {
    byte b2;
    int i = this.zzoof;
    if (i != -1)
      return i; 
    i = this.zzovt;
    byte b1 = 0;
    if (i != 0) {
      b2 = zzeuy.zzaa(1, i) + 0;
    } else {
      b2 = 0;
    } 
    i = b2;
    byte b3 = b1;
    if (!this.zzovu.isEmpty()) {
      i = b2 + zzeuy.zzn(2, this.zzovu);
      b3 = b1;
    } 
    while (b3 < this.zzovv.size()) {
      i += zzeuy.zzb(3, this.zzovv.get(b3));
      b3++;
    } 
    i += this.zzooe.zzhi();
    this.zzoof = i;
    return i;
  }
  
  public static final class zza extends zzevi<zzezb, zza> implements zzewn {
    private zza() {
      super(zzezb.zzcwk());
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzezb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */