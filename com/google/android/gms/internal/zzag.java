package com.google.android.gms.internal;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public final class zzag implements zzb {
  private final Map<String, zzai> zzbv = new LinkedHashMap<String, zzai>(16, 0.75F, true);
  
  private long zzbw = 0L;
  
  private final File zzbx;
  
  private final int zzby;
  
  public zzag(File paramFile) {
    this(paramFile, 5242880);
  }
  
  private zzag(File paramFile, int paramInt) {
    this.zzbx = paramFile;
    this.zzby = 5242880;
  }
  
  private final void remove(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: invokespecial zze : (Ljava/lang/String;)Ljava/io/File;
    //   7: invokevirtual delete : ()Z
    //   10: istore_2
    //   11: aload_0
    //   12: getfield zzbv : Ljava/util/Map;
    //   15: aload_1
    //   16: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   21: checkcast com/google/android/gms/internal/zzai
    //   24: astore_3
    //   25: aload_3
    //   26: ifnull -> 53
    //   29: aload_0
    //   30: aload_0
    //   31: getfield zzbw : J
    //   34: aload_3
    //   35: getfield zzbz : J
    //   38: lsub
    //   39: putfield zzbw : J
    //   42: aload_0
    //   43: getfield zzbv : Ljava/util/Map;
    //   46: aload_1
    //   47: invokeinterface remove : (Ljava/lang/Object;)Ljava/lang/Object;
    //   52: pop
    //   53: iload_2
    //   54: ifne -> 77
    //   57: ldc 'Could not delete cache entry for key=%s, filename=%s'
    //   59: iconst_2
    //   60: anewarray java/lang/Object
    //   63: dup
    //   64: iconst_0
    //   65: aload_1
    //   66: aastore
    //   67: dup
    //   68: iconst_1
    //   69: aload_1
    //   70: invokestatic zzd : (Ljava/lang/String;)Ljava/lang/String;
    //   73: aastore
    //   74: invokestatic zzb : (Ljava/lang/String;[Ljava/lang/Object;)V
    //   77: aload_0
    //   78: monitorexit
    //   79: return
    //   80: astore_1
    //   81: aload_0
    //   82: monitorexit
    //   83: aload_1
    //   84: athrow
    // Exception table:
    //   from	to	target	type
    //   2	25	80	finally
    //   29	53	80	finally
    //   57	77	80	finally
  }
  
  private static int zza(InputStream paramInputStream) throws IOException {
    int i = paramInputStream.read();
    if (i != -1)
      return i; 
    throw new EOFException();
  }
  
  static void zza(OutputStream paramOutputStream, int paramInt) throws IOException {
    paramOutputStream.write(paramInt & 0xFF);
    paramOutputStream.write(paramInt >> 8 & 0xFF);
    paramOutputStream.write(paramInt >> 16 & 0xFF);
    paramOutputStream.write(paramInt >>> 24);
  }
  
  static void zza(OutputStream paramOutputStream, long paramLong) throws IOException {
    paramOutputStream.write((byte)(int)paramLong);
    paramOutputStream.write((byte)(int)(paramLong >>> 8L));
    paramOutputStream.write((byte)(int)(paramLong >>> 16L));
    paramOutputStream.write((byte)(int)(paramLong >>> 24L));
    paramOutputStream.write((byte)(int)(paramLong >>> 32L));
    paramOutputStream.write((byte)(int)(paramLong >>> 40L));
    paramOutputStream.write((byte)(int)(paramLong >>> 48L));
    paramOutputStream.write((byte)(int)(paramLong >>> 56L));
  }
  
  static void zza(OutputStream paramOutputStream, String paramString) throws IOException {
    byte[] arrayOfByte = paramString.getBytes("UTF-8");
    zza(paramOutputStream, arrayOfByte.length);
    paramOutputStream.write(arrayOfByte, 0, arrayOfByte.length);
  }
  
  private final void zza(String paramString, zzai paramzzai) {
    if (!this.zzbv.containsKey(paramString)) {
      this.zzbw += paramzzai.zzbz;
    } else {
      zzai zzai1 = this.zzbv.get(paramString);
      this.zzbw += paramzzai.zzbz - zzai1.zzbz;
    } 
    this.zzbv.put(paramString, paramzzai);
  }
  
  private static byte[] zza(InputStream paramInputStream, int paramInt) throws IOException {
    byte[] arrayOfByte = new byte[paramInt];
    int i = 0;
    while (i < paramInt) {
      int j = paramInputStream.read(arrayOfByte, i, paramInt - i);
      if (j != -1)
        i += j; 
    } 
    if (i == paramInt)
      return arrayOfByte; 
    StringBuilder stringBuilder = new StringBuilder(50);
    stringBuilder.append("Expected ");
    stringBuilder.append(paramInt);
    stringBuilder.append(" bytes, read ");
    stringBuilder.append(i);
    stringBuilder.append(" bytes");
    IOException iOException = new IOException(stringBuilder.toString());
    throw iOException;
  }
  
  static int zzb(InputStream paramInputStream) throws IOException {
    int i = zza(paramInputStream);
    int j = zza(paramInputStream);
    int k = zza(paramInputStream);
    return zza(paramInputStream) << 24 | i | 0x0 | j << 8 | k << 16;
  }
  
  static long zzc(InputStream paramInputStream) throws IOException {
    return zza(paramInputStream) & 0xFFL | 0x0L | (zza(paramInputStream) & 0xFFL) << 8L | (zza(paramInputStream) & 0xFFL) << 16L | (zza(paramInputStream) & 0xFFL) << 24L | (zza(paramInputStream) & 0xFFL) << 32L | (zza(paramInputStream) & 0xFFL) << 40L | (zza(paramInputStream) & 0xFFL) << 48L | (0xFFL & zza(paramInputStream)) << 56L;
  }
  
  static String zzd(InputStream paramInputStream) throws IOException {
    return new String(zza(paramInputStream, (int)zzc(paramInputStream)), "UTF-8");
  }
  
  private static String zzd(String paramString) {
    int i = paramString.length() / 2;
    String str = String.valueOf(String.valueOf(paramString.substring(0, i).hashCode()));
    paramString = String.valueOf(String.valueOf(paramString.substring(i).hashCode()));
    return (paramString.length() != 0) ? str.concat(paramString) : new String(str);
  }
  
  private final File zze(String paramString) {
    return new File(this.zzbx, zzd(paramString));
  }
  
  static Map<String, String> zze(InputStream paramInputStream) throws IOException {
    Map<?, ?> map;
    int i = zzb(paramInputStream);
    if (i == 0) {
      map = Collections.emptyMap();
    } else {
      map = new HashMap<Object, Object>(i);
    } 
    for (byte b = 0; b < i; b++)
      map.put(zzd(paramInputStream).intern(), zzd(paramInputStream).intern()); 
    return (Map)map;
  }
  
  public final void initialize() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield zzbx : Ljava/io/File;
    //   6: invokevirtual exists : ()Z
    //   9: istore_1
    //   10: iconst_0
    //   11: istore_2
    //   12: iload_1
    //   13: ifne -> 48
    //   16: aload_0
    //   17: getfield zzbx : Ljava/io/File;
    //   20: invokevirtual mkdirs : ()Z
    //   23: ifne -> 45
    //   26: ldc 'Unable to create cache dir %s'
    //   28: iconst_1
    //   29: anewarray java/lang/Object
    //   32: dup
    //   33: iconst_0
    //   34: aload_0
    //   35: getfield zzbx : Ljava/io/File;
    //   38: invokevirtual getAbsolutePath : ()Ljava/lang/String;
    //   41: aastore
    //   42: invokestatic zzc : (Ljava/lang/String;[Ljava/lang/Object;)V
    //   45: aload_0
    //   46: monitorexit
    //   47: return
    //   48: aload_0
    //   49: getfield zzbx : Ljava/io/File;
    //   52: invokevirtual listFiles : ()[Ljava/io/File;
    //   55: astore_3
    //   56: aload_3
    //   57: ifnonnull -> 63
    //   60: aload_0
    //   61: monitorexit
    //   62: return
    //   63: aload_3
    //   64: arraylength
    //   65: istore #4
    //   67: iload_2
    //   68: iload #4
    //   70: if_icmpge -> 236
    //   73: aload_3
    //   74: iload_2
    //   75: aaload
    //   76: astore #5
    //   78: aconst_null
    //   79: astore #6
    //   81: aconst_null
    //   82: astore #7
    //   84: aload #7
    //   86: astore #8
    //   88: new java/io/BufferedInputStream
    //   91: astore #9
    //   93: aload #7
    //   95: astore #8
    //   97: new java/io/FileInputStream
    //   100: astore #10
    //   102: aload #7
    //   104: astore #8
    //   106: aload #10
    //   108: aload #5
    //   110: invokespecial <init> : (Ljava/io/File;)V
    //   113: aload #7
    //   115: astore #8
    //   117: aload #9
    //   119: aload #10
    //   121: invokespecial <init> : (Ljava/io/InputStream;)V
    //   124: aload #9
    //   126: invokestatic zzf : (Ljava/io/InputStream;)Lcom/google/android/gms/internal/zzai;
    //   129: astore #8
    //   131: aload #8
    //   133: aload #5
    //   135: invokevirtual length : ()J
    //   138: putfield zzbz : J
    //   141: aload_0
    //   142: aload #8
    //   144: getfield key : Ljava/lang/String;
    //   147: aload #8
    //   149: invokespecial zza : (Ljava/lang/String;Lcom/google/android/gms/internal/zzai;)V
    //   152: aload #9
    //   154: invokevirtual close : ()V
    //   157: goto -> 230
    //   160: astore #6
    //   162: aload #9
    //   164: astore #8
    //   166: aload #6
    //   168: astore #9
    //   170: goto -> 207
    //   173: astore #8
    //   175: goto -> 189
    //   178: astore #9
    //   180: goto -> 207
    //   183: astore #8
    //   185: aload #6
    //   187: astore #9
    //   189: aload #5
    //   191: ifnull -> 220
    //   194: aload #9
    //   196: astore #8
    //   198: aload #5
    //   200: invokevirtual delete : ()Z
    //   203: pop
    //   204: goto -> 220
    //   207: aload #8
    //   209: ifnull -> 217
    //   212: aload #8
    //   214: invokevirtual close : ()V
    //   217: aload #9
    //   219: athrow
    //   220: aload #9
    //   222: ifnull -> 230
    //   225: aload #9
    //   227: invokevirtual close : ()V
    //   230: iinc #2, 1
    //   233: goto -> 67
    //   236: aload_0
    //   237: monitorexit
    //   238: return
    //   239: astore #8
    //   241: aload_0
    //   242: monitorexit
    //   243: goto -> 249
    //   246: aload #8
    //   248: athrow
    //   249: goto -> 246
    //   252: astore #8
    //   254: goto -> 230
    //   257: astore #8
    //   259: goto -> 217
    // Exception table:
    //   from	to	target	type
    //   2	10	239	finally
    //   16	45	239	finally
    //   48	56	239	finally
    //   63	67	239	finally
    //   88	93	183	java/io/IOException
    //   88	93	178	finally
    //   97	102	183	java/io/IOException
    //   97	102	178	finally
    //   106	113	183	java/io/IOException
    //   106	113	178	finally
    //   117	124	183	java/io/IOException
    //   117	124	178	finally
    //   124	152	173	java/io/IOException
    //   124	152	160	finally
    //   152	157	252	java/io/IOException
    //   152	157	239	finally
    //   198	204	178	finally
    //   212	217	257	java/io/IOException
    //   212	217	239	finally
    //   217	220	239	finally
    //   225	230	252	java/io/IOException
    //   225	230	239	finally
  }
  
  public final zzc zza(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield zzbv : Ljava/util/Map;
    //   6: aload_1
    //   7: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   12: checkcast com/google/android/gms/internal/zzai
    //   15: astore_2
    //   16: aload_2
    //   17: ifnonnull -> 24
    //   20: aload_0
    //   21: monitorexit
    //   22: aconst_null
    //   23: areturn
    //   24: aload_0
    //   25: aload_1
    //   26: invokespecial zze : (Ljava/lang/String;)Ljava/io/File;
    //   29: astore_3
    //   30: new com/google/android/gms/internal/zzaj
    //   33: astore #4
    //   35: new java/io/BufferedInputStream
    //   38: astore #5
    //   40: new java/io/FileInputStream
    //   43: astore #6
    //   45: aload #6
    //   47: aload_3
    //   48: invokespecial <init> : (Ljava/io/File;)V
    //   51: aload #5
    //   53: aload #6
    //   55: invokespecial <init> : (Ljava/io/InputStream;)V
    //   58: aload #4
    //   60: aload #5
    //   62: aconst_null
    //   63: invokespecial <init> : (Ljava/io/InputStream;Lcom/google/android/gms/internal/zzah;)V
    //   66: aload #4
    //   68: astore #5
    //   70: aload #4
    //   72: invokestatic zzf : (Ljava/io/InputStream;)Lcom/google/android/gms/internal/zzai;
    //   75: pop
    //   76: aload #4
    //   78: astore #5
    //   80: aload #4
    //   82: aload_3
    //   83: invokevirtual length : ()J
    //   86: aload #4
    //   88: invokestatic zza : (Lcom/google/android/gms/internal/zzaj;)I
    //   91: i2l
    //   92: lsub
    //   93: l2i
    //   94: invokestatic zza : (Ljava/io/InputStream;I)[B
    //   97: astore #7
    //   99: aload #4
    //   101: astore #5
    //   103: new com/google/android/gms/internal/zzc
    //   106: astore #6
    //   108: aload #4
    //   110: astore #5
    //   112: aload #6
    //   114: invokespecial <init> : ()V
    //   117: aload #4
    //   119: astore #5
    //   121: aload #6
    //   123: aload #7
    //   125: putfield data : [B
    //   128: aload #4
    //   130: astore #5
    //   132: aload #6
    //   134: aload_2
    //   135: getfield zza : Ljava/lang/String;
    //   138: putfield zza : Ljava/lang/String;
    //   141: aload #4
    //   143: astore #5
    //   145: aload #6
    //   147: aload_2
    //   148: getfield zzb : J
    //   151: putfield zzb : J
    //   154: aload #4
    //   156: astore #5
    //   158: aload #6
    //   160: aload_2
    //   161: getfield zzc : J
    //   164: putfield zzc : J
    //   167: aload #4
    //   169: astore #5
    //   171: aload #6
    //   173: aload_2
    //   174: getfield zzd : J
    //   177: putfield zzd : J
    //   180: aload #4
    //   182: astore #5
    //   184: aload #6
    //   186: aload_2
    //   187: getfield zze : J
    //   190: putfield zze : J
    //   193: aload #4
    //   195: astore #5
    //   197: aload #6
    //   199: aload_2
    //   200: getfield zzf : Ljava/util/Map;
    //   203: putfield zzf : Ljava/util/Map;
    //   206: aload #4
    //   208: invokevirtual close : ()V
    //   211: aload_0
    //   212: monitorexit
    //   213: aload #6
    //   215: areturn
    //   216: astore_1
    //   217: aload_0
    //   218: monitorexit
    //   219: aconst_null
    //   220: areturn
    //   221: astore_2
    //   222: goto -> 240
    //   225: astore_2
    //   226: goto -> 303
    //   229: astore_1
    //   230: aconst_null
    //   231: astore #5
    //   233: goto -> 363
    //   236: astore_2
    //   237: aconst_null
    //   238: astore #4
    //   240: aload #4
    //   242: astore #5
    //   244: ldc_w '%s: %s'
    //   247: iconst_2
    //   248: anewarray java/lang/Object
    //   251: dup
    //   252: iconst_0
    //   253: aload_3
    //   254: invokevirtual getAbsolutePath : ()Ljava/lang/String;
    //   257: aastore
    //   258: dup
    //   259: iconst_1
    //   260: aload_2
    //   261: invokevirtual toString : ()Ljava/lang/String;
    //   264: aastore
    //   265: invokestatic zzb : (Ljava/lang/String;[Ljava/lang/Object;)V
    //   268: aload #4
    //   270: astore #5
    //   272: aload_0
    //   273: aload_1
    //   274: invokespecial remove : (Ljava/lang/String;)V
    //   277: aload #4
    //   279: ifnull -> 295
    //   282: aload #4
    //   284: invokevirtual close : ()V
    //   287: goto -> 295
    //   290: astore_1
    //   291: aload_0
    //   292: monitorexit
    //   293: aconst_null
    //   294: areturn
    //   295: aload_0
    //   296: monitorexit
    //   297: aconst_null
    //   298: areturn
    //   299: astore_2
    //   300: aconst_null
    //   301: astore #4
    //   303: aload #4
    //   305: astore #5
    //   307: ldc_w '%s: %s'
    //   310: iconst_2
    //   311: anewarray java/lang/Object
    //   314: dup
    //   315: iconst_0
    //   316: aload_3
    //   317: invokevirtual getAbsolutePath : ()Ljava/lang/String;
    //   320: aastore
    //   321: dup
    //   322: iconst_1
    //   323: aload_2
    //   324: invokevirtual toString : ()Ljava/lang/String;
    //   327: aastore
    //   328: invokestatic zzb : (Ljava/lang/String;[Ljava/lang/Object;)V
    //   331: aload #4
    //   333: astore #5
    //   335: aload_0
    //   336: aload_1
    //   337: invokespecial remove : (Ljava/lang/String;)V
    //   340: aload #4
    //   342: ifnull -> 358
    //   345: aload #4
    //   347: invokevirtual close : ()V
    //   350: goto -> 358
    //   353: astore_1
    //   354: aload_0
    //   355: monitorexit
    //   356: aconst_null
    //   357: areturn
    //   358: aload_0
    //   359: monitorexit
    //   360: aconst_null
    //   361: areturn
    //   362: astore_1
    //   363: aload #5
    //   365: ifnull -> 381
    //   368: aload #5
    //   370: invokevirtual close : ()V
    //   373: goto -> 381
    //   376: astore_1
    //   377: aload_0
    //   378: monitorexit
    //   379: aconst_null
    //   380: areturn
    //   381: aload_1
    //   382: athrow
    //   383: astore_1
    //   384: aload_0
    //   385: monitorexit
    //   386: aload_1
    //   387: athrow
    // Exception table:
    //   from	to	target	type
    //   2	16	383	finally
    //   24	30	383	finally
    //   30	66	299	java/io/IOException
    //   30	66	236	java/lang/NegativeArraySizeException
    //   30	66	229	finally
    //   70	76	225	java/io/IOException
    //   70	76	221	java/lang/NegativeArraySizeException
    //   70	76	362	finally
    //   80	99	225	java/io/IOException
    //   80	99	221	java/lang/NegativeArraySizeException
    //   80	99	362	finally
    //   103	108	225	java/io/IOException
    //   103	108	221	java/lang/NegativeArraySizeException
    //   103	108	362	finally
    //   112	117	225	java/io/IOException
    //   112	117	221	java/lang/NegativeArraySizeException
    //   112	117	362	finally
    //   121	128	225	java/io/IOException
    //   121	128	221	java/lang/NegativeArraySizeException
    //   121	128	362	finally
    //   132	141	225	java/io/IOException
    //   132	141	221	java/lang/NegativeArraySizeException
    //   132	141	362	finally
    //   145	154	225	java/io/IOException
    //   145	154	221	java/lang/NegativeArraySizeException
    //   145	154	362	finally
    //   158	167	225	java/io/IOException
    //   158	167	221	java/lang/NegativeArraySizeException
    //   158	167	362	finally
    //   171	180	225	java/io/IOException
    //   171	180	221	java/lang/NegativeArraySizeException
    //   171	180	362	finally
    //   184	193	225	java/io/IOException
    //   184	193	221	java/lang/NegativeArraySizeException
    //   184	193	362	finally
    //   197	206	225	java/io/IOException
    //   197	206	221	java/lang/NegativeArraySizeException
    //   197	206	362	finally
    //   206	211	216	java/io/IOException
    //   206	211	383	finally
    //   244	268	362	finally
    //   272	277	362	finally
    //   282	287	290	java/io/IOException
    //   282	287	383	finally
    //   307	331	362	finally
    //   335	340	362	finally
    //   345	350	353	java/io/IOException
    //   345	350	383	finally
    //   368	373	376	java/io/IOException
    //   368	373	383	finally
    //   381	383	383	finally
  }
  
  public final void zza(String paramString, zzc paramzzc) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_2
    //   3: getfield data : [B
    //   6: arraylength
    //   7: istore_3
    //   8: aload_0
    //   9: getfield zzbw : J
    //   12: lstore #4
    //   14: iload_3
    //   15: i2l
    //   16: lstore #6
    //   18: lload #4
    //   20: lload #6
    //   22: ladd
    //   23: aload_0
    //   24: getfield zzby : I
    //   27: i2l
    //   28: lcmp
    //   29: iflt -> 253
    //   32: getstatic com/google/android/gms/internal/zzab.DEBUG : Z
    //   35: ifeq -> 48
    //   38: ldc_w 'Pruning old cache entries.'
    //   41: iconst_0
    //   42: anewarray java/lang/Object
    //   45: invokestatic zza : (Ljava/lang/String;[Ljava/lang/Object;)V
    //   48: aload_0
    //   49: getfield zzbw : J
    //   52: lstore #4
    //   54: invokestatic elapsedRealtime : ()J
    //   57: lstore #8
    //   59: aload_0
    //   60: getfield zzbv : Ljava/util/Map;
    //   63: invokeinterface entrySet : ()Ljava/util/Set;
    //   68: invokeinterface iterator : ()Ljava/util/Iterator;
    //   73: astore #10
    //   75: iconst_0
    //   76: istore_3
    //   77: aload #10
    //   79: invokeinterface hasNext : ()Z
    //   84: ifeq -> 205
    //   87: aload #10
    //   89: invokeinterface next : ()Ljava/lang/Object;
    //   94: checkcast java/util/Map$Entry
    //   97: invokeinterface getValue : ()Ljava/lang/Object;
    //   102: checkcast com/google/android/gms/internal/zzai
    //   105: astore #11
    //   107: aload_0
    //   108: aload #11
    //   110: getfield key : Ljava/lang/String;
    //   113: invokespecial zze : (Ljava/lang/String;)Ljava/io/File;
    //   116: invokevirtual delete : ()Z
    //   119: ifeq -> 139
    //   122: aload_0
    //   123: aload_0
    //   124: getfield zzbw : J
    //   127: aload #11
    //   129: getfield zzbz : J
    //   132: lsub
    //   133: putfield zzbw : J
    //   136: goto -> 168
    //   139: aload #11
    //   141: getfield key : Ljava/lang/String;
    //   144: astore #11
    //   146: ldc 'Could not delete cache entry for key=%s, filename=%s'
    //   148: iconst_2
    //   149: anewarray java/lang/Object
    //   152: dup
    //   153: iconst_0
    //   154: aload #11
    //   156: aastore
    //   157: dup
    //   158: iconst_1
    //   159: aload #11
    //   161: invokestatic zzd : (Ljava/lang/String;)Ljava/lang/String;
    //   164: aastore
    //   165: invokestatic zzb : (Ljava/lang/String;[Ljava/lang/Object;)V
    //   168: aload #10
    //   170: invokeinterface remove : ()V
    //   175: iinc #3, 1
    //   178: aload_0
    //   179: getfield zzbw : J
    //   182: lload #6
    //   184: ladd
    //   185: l2f
    //   186: aload_0
    //   187: getfield zzby : I
    //   190: i2f
    //   191: ldc_w 0.9
    //   194: fmul
    //   195: fcmpg
    //   196: ifge -> 202
    //   199: goto -> 205
    //   202: goto -> 77
    //   205: getstatic com/google/android/gms/internal/zzab.DEBUG : Z
    //   208: ifeq -> 253
    //   211: ldc_w 'pruned %d files, %d bytes, %d ms'
    //   214: iconst_3
    //   215: anewarray java/lang/Object
    //   218: dup
    //   219: iconst_0
    //   220: iload_3
    //   221: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   224: aastore
    //   225: dup
    //   226: iconst_1
    //   227: aload_0
    //   228: getfield zzbw : J
    //   231: lload #4
    //   233: lsub
    //   234: invokestatic valueOf : (J)Ljava/lang/Long;
    //   237: aastore
    //   238: dup
    //   239: iconst_2
    //   240: invokestatic elapsedRealtime : ()J
    //   243: lload #8
    //   245: lsub
    //   246: invokestatic valueOf : (J)Ljava/lang/Long;
    //   249: aastore
    //   250: invokestatic zza : (Ljava/lang/String;[Ljava/lang/Object;)V
    //   253: aload_0
    //   254: aload_1
    //   255: invokespecial zze : (Ljava/lang/String;)Ljava/io/File;
    //   258: astore #10
    //   260: new java/io/BufferedOutputStream
    //   263: astore #11
    //   265: new java/io/FileOutputStream
    //   268: astore #12
    //   270: aload #12
    //   272: aload #10
    //   274: invokespecial <init> : (Ljava/io/File;)V
    //   277: aload #11
    //   279: aload #12
    //   281: invokespecial <init> : (Ljava/io/OutputStream;)V
    //   284: new com/google/android/gms/internal/zzai
    //   287: astore #12
    //   289: aload #12
    //   291: aload_1
    //   292: aload_2
    //   293: invokespecial <init> : (Ljava/lang/String;Lcom/google/android/gms/internal/zzc;)V
    //   296: aload #12
    //   298: aload #11
    //   300: invokevirtual zza : (Ljava/io/OutputStream;)Z
    //   303: ifeq -> 330
    //   306: aload #11
    //   308: aload_2
    //   309: getfield data : [B
    //   312: invokevirtual write : ([B)V
    //   315: aload #11
    //   317: invokevirtual close : ()V
    //   320: aload_0
    //   321: aload_1
    //   322: aload #12
    //   324: invokespecial zza : (Ljava/lang/String;Lcom/google/android/gms/internal/zzai;)V
    //   327: aload_0
    //   328: monitorexit
    //   329: return
    //   330: aload #11
    //   332: invokevirtual close : ()V
    //   335: ldc_w 'Failed to write header for %s'
    //   338: iconst_1
    //   339: anewarray java/lang/Object
    //   342: dup
    //   343: iconst_0
    //   344: aload #10
    //   346: invokevirtual getAbsolutePath : ()Ljava/lang/String;
    //   349: aastore
    //   350: invokestatic zzb : (Ljava/lang/String;[Ljava/lang/Object;)V
    //   353: new java/io/IOException
    //   356: astore_1
    //   357: aload_1
    //   358: invokespecial <init> : ()V
    //   361: aload_1
    //   362: athrow
    //   363: astore_1
    //   364: aload #10
    //   366: invokevirtual delete : ()Z
    //   369: ifne -> 390
    //   372: ldc_w 'Could not clean up file %s'
    //   375: iconst_1
    //   376: anewarray java/lang/Object
    //   379: dup
    //   380: iconst_0
    //   381: aload #10
    //   383: invokevirtual getAbsolutePath : ()Ljava/lang/String;
    //   386: aastore
    //   387: invokestatic zzb : (Ljava/lang/String;[Ljava/lang/Object;)V
    //   390: aload_0
    //   391: monitorexit
    //   392: return
    //   393: astore_1
    //   394: aload_0
    //   395: monitorexit
    //   396: goto -> 401
    //   399: aload_1
    //   400: athrow
    //   401: goto -> 399
    // Exception table:
    //   from	to	target	type
    //   2	14	393	finally
    //   18	48	393	finally
    //   48	75	393	finally
    //   77	136	393	finally
    //   139	168	393	finally
    //   168	175	393	finally
    //   178	199	393	finally
    //   205	253	393	finally
    //   253	260	393	finally
    //   260	327	363	java/io/IOException
    //   260	327	393	finally
    //   330	363	363	java/io/IOException
    //   330	363	393	finally
    //   364	390	393	finally
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzag.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */