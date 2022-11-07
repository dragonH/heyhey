package com.google.android.gms.internal;

import java.util.Map;
import org.apache.http.impl.cookie.DateParseException;
import org.apache.http.impl.cookie.DateUtils;

public final class zzam {
  public static String zza(Map<String, String> paramMap) {
    String str = paramMap.get("Content-Type");
    if (str != null) {
      String[] arrayOfString = str.split(";");
      for (byte b = 1; b < arrayOfString.length; b++) {
        String[] arrayOfString1 = arrayOfString[b].trim().split("=");
        if (arrayOfString1.length == 2 && arrayOfString1[0].equals("charset"))
          return arrayOfString1[1]; 
      } 
    } 
    return "ISO-8859-1";
  }
  
  public static zzc zzb(zzn paramzzn) {
    // Byte code:
    //   0: invokestatic currentTimeMillis : ()J
    //   3: lstore_1
    //   4: aload_0
    //   5: getfield zzy : Ljava/util/Map;
    //   8: astore_3
    //   9: aload_3
    //   10: ldc 'Date'
    //   12: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   17: checkcast java/lang/String
    //   20: astore #4
    //   22: aload #4
    //   24: ifnull -> 37
    //   27: aload #4
    //   29: invokestatic zzf : (Ljava/lang/String;)J
    //   32: lstore #5
    //   34: goto -> 40
    //   37: lconst_0
    //   38: lstore #5
    //   40: aload_3
    //   41: ldc 'Cache-Control'
    //   43: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   48: checkcast java/lang/String
    //   51: astore #4
    //   53: iconst_0
    //   54: istore #7
    //   56: iconst_0
    //   57: istore #8
    //   59: aload #4
    //   61: ifnull -> 242
    //   64: aload #4
    //   66: ldc ','
    //   68: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
    //   71: astore #4
    //   73: iconst_0
    //   74: istore #7
    //   76: lconst_0
    //   77: lstore #9
    //   79: lconst_0
    //   80: lstore #11
    //   82: iload #8
    //   84: aload #4
    //   86: arraylength
    //   87: if_icmpge -> 236
    //   90: aload #4
    //   92: iload #8
    //   94: aaload
    //   95: invokevirtual trim : ()Ljava/lang/String;
    //   98: astore #13
    //   100: aload #13
    //   102: ldc 'no-cache'
    //   104: invokevirtual equals : (Ljava/lang/Object;)Z
    //   107: ifne -> 234
    //   110: aload #13
    //   112: ldc 'no-store'
    //   114: invokevirtual equals : (Ljava/lang/Object;)Z
    //   117: ifeq -> 123
    //   120: goto -> 234
    //   123: aload #13
    //   125: ldc 'max-age='
    //   127: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   130: ifeq -> 152
    //   133: aload #13
    //   135: bipush #8
    //   137: invokevirtual substring : (I)Ljava/lang/String;
    //   140: invokestatic parseLong : (Ljava/lang/String;)J
    //   143: lstore #14
    //   145: lload #11
    //   147: lstore #16
    //   149: goto -> 220
    //   152: aload #13
    //   154: ldc 'stale-while-revalidate='
    //   156: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   159: ifeq -> 181
    //   162: aload #13
    //   164: bipush #23
    //   166: invokevirtual substring : (I)Ljava/lang/String;
    //   169: invokestatic parseLong : (Ljava/lang/String;)J
    //   172: lstore #16
    //   174: lload #9
    //   176: lstore #14
    //   178: goto -> 220
    //   181: aload #13
    //   183: ldc 'must-revalidate'
    //   185: invokevirtual equals : (Ljava/lang/Object;)Z
    //   188: ifne -> 209
    //   191: lload #9
    //   193: lstore #14
    //   195: lload #11
    //   197: lstore #16
    //   199: aload #13
    //   201: ldc 'proxy-revalidate'
    //   203: invokevirtual equals : (Ljava/lang/Object;)Z
    //   206: ifeq -> 220
    //   209: iconst_1
    //   210: istore #7
    //   212: lload #11
    //   214: lstore #16
    //   216: lload #9
    //   218: lstore #14
    //   220: iinc #8, 1
    //   223: lload #14
    //   225: lstore #9
    //   227: lload #16
    //   229: lstore #11
    //   231: goto -> 82
    //   234: aconst_null
    //   235: areturn
    //   236: iconst_1
    //   237: istore #8
    //   239: goto -> 251
    //   242: iconst_0
    //   243: istore #8
    //   245: lconst_0
    //   246: lstore #9
    //   248: lconst_0
    //   249: lstore #11
    //   251: aload_3
    //   252: ldc 'Expires'
    //   254: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   259: checkcast java/lang/String
    //   262: astore #4
    //   264: aload #4
    //   266: ifnull -> 279
    //   269: aload #4
    //   271: invokestatic zzf : (Ljava/lang/String;)J
    //   274: lstore #16
    //   276: goto -> 282
    //   279: lconst_0
    //   280: lstore #16
    //   282: aload_3
    //   283: ldc 'Last-Modified'
    //   285: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   290: checkcast java/lang/String
    //   293: astore #4
    //   295: aload #4
    //   297: ifnull -> 310
    //   300: aload #4
    //   302: invokestatic zzf : (Ljava/lang/String;)J
    //   305: lstore #14
    //   307: goto -> 313
    //   310: lconst_0
    //   311: lstore #14
    //   313: aload_3
    //   314: ldc 'ETag'
    //   316: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   321: checkcast java/lang/String
    //   324: astore #13
    //   326: iload #8
    //   328: ifeq -> 385
    //   331: lload_1
    //   332: lload #9
    //   334: ldc2_w 1000
    //   337: lmul
    //   338: ladd
    //   339: lstore #9
    //   341: iload #7
    //   343: ifeq -> 353
    //   346: lload #9
    //   348: lstore #11
    //   350: goto -> 370
    //   353: lload #11
    //   355: invokestatic signum : (J)I
    //   358: pop
    //   359: lload #11
    //   361: ldc2_w 1000
    //   364: lmul
    //   365: lload #9
    //   367: ladd
    //   368: lstore #11
    //   370: lload #11
    //   372: lstore #16
    //   374: lload #9
    //   376: lstore #11
    //   378: lload #16
    //   380: lstore #9
    //   382: goto -> 422
    //   385: lconst_0
    //   386: lstore #9
    //   388: lload #5
    //   390: lconst_0
    //   391: lcmp
    //   392: ifle -> 419
    //   395: lload #16
    //   397: lload #5
    //   399: lcmp
    //   400: iflt -> 419
    //   403: lload_1
    //   404: lload #16
    //   406: lload #5
    //   408: lsub
    //   409: ladd
    //   410: lstore #11
    //   412: lload #11
    //   414: lstore #9
    //   416: goto -> 422
    //   419: lconst_0
    //   420: lstore #11
    //   422: new com/google/android/gms/internal/zzc
    //   425: dup
    //   426: invokespecial <init> : ()V
    //   429: astore #4
    //   431: aload #4
    //   433: aload_0
    //   434: getfield data : [B
    //   437: putfield data : [B
    //   440: aload #4
    //   442: aload #13
    //   444: putfield zza : Ljava/lang/String;
    //   447: aload #4
    //   449: lload #11
    //   451: putfield zze : J
    //   454: aload #4
    //   456: lload #9
    //   458: putfield zzd : J
    //   461: aload #4
    //   463: lload #5
    //   465: putfield zzb : J
    //   468: aload #4
    //   470: lload #14
    //   472: putfield zzc : J
    //   475: aload #4
    //   477: aload_3
    //   478: putfield zzf : Ljava/util/Map;
    //   481: aload #4
    //   483: areturn
    //   484: astore #13
    //   486: lload #9
    //   488: lstore #14
    //   490: lload #11
    //   492: lstore #16
    //   494: goto -> 220
    // Exception table:
    //   from	to	target	type
    //   133	145	484	java/lang/Exception
    //   162	174	484	java/lang/Exception
  }
  
  private static long zzf(String paramString) {
    try {
      return DateUtils.parseDate(paramString).getTime();
    } catch (DateParseException dateParseException) {
      return 0L;
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzam.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */