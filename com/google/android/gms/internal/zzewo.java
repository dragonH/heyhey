package com.google.android.gms.internal;

import java.util.List;

final class zzewo {
  static String zza(zzewl paramzzewl, String paramString) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("# ");
    stringBuilder.append(paramString);
    zza(paramzzewl, stringBuilder, 0);
    return stringBuilder.toString();
  }
  
  private static void zza(zzewl paramzzewl, StringBuilder paramStringBuilder, int paramInt) {
    // Byte code:
    //   0: new java/util/HashMap
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: astore_3
    //   8: new java/util/HashMap
    //   11: dup
    //   12: invokespecial <init> : ()V
    //   15: astore #4
    //   17: new java/util/TreeSet
    //   20: dup
    //   21: invokespecial <init> : ()V
    //   24: astore #5
    //   26: aload_0
    //   27: invokevirtual getClass : ()Ljava/lang/Class;
    //   30: invokevirtual getDeclaredMethods : ()[Ljava/lang/reflect/Method;
    //   33: astore #6
    //   35: aload #6
    //   37: arraylength
    //   38: istore #7
    //   40: iconst_0
    //   41: istore #8
    //   43: iload #8
    //   45: iload #7
    //   47: if_icmpge -> 127
    //   50: aload #6
    //   52: iload #8
    //   54: aaload
    //   55: astore #9
    //   57: aload #4
    //   59: aload #9
    //   61: invokevirtual getName : ()Ljava/lang/String;
    //   64: aload #9
    //   66: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   71: pop
    //   72: aload #9
    //   74: invokevirtual getParameterTypes : ()[Ljava/lang/Class;
    //   77: arraylength
    //   78: ifne -> 121
    //   81: aload_3
    //   82: aload #9
    //   84: invokevirtual getName : ()Ljava/lang/String;
    //   87: aload #9
    //   89: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   94: pop
    //   95: aload #9
    //   97: invokevirtual getName : ()Ljava/lang/String;
    //   100: ldc 'get'
    //   102: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   105: ifeq -> 121
    //   108: aload #5
    //   110: aload #9
    //   112: invokevirtual getName : ()Ljava/lang/String;
    //   115: invokeinterface add : (Ljava/lang/Object;)Z
    //   120: pop
    //   121: iinc #8, 1
    //   124: goto -> 43
    //   127: aload #5
    //   129: invokeinterface iterator : ()Ljava/util/Iterator;
    //   134: astore #9
    //   136: aload #9
    //   138: invokeinterface hasNext : ()Z
    //   143: ifeq -> 870
    //   146: aload #9
    //   148: invokeinterface next : ()Ljava/lang/Object;
    //   153: checkcast java/lang/String
    //   156: ldc 'get'
    //   158: ldc ''
    //   160: invokevirtual replaceFirst : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   163: astore #10
    //   165: aload #10
    //   167: ldc 'List'
    //   169: invokevirtual endsWith : (Ljava/lang/String;)Z
    //   172: istore #11
    //   174: iconst_1
    //   175: istore #12
    //   177: iload #11
    //   179: ifeq -> 341
    //   182: aload #10
    //   184: ldc 'OrBuilderList'
    //   186: invokevirtual endsWith : (Ljava/lang/String;)Z
    //   189: ifne -> 341
    //   192: aload #10
    //   194: iconst_0
    //   195: iconst_1
    //   196: invokevirtual substring : (II)Ljava/lang/String;
    //   199: invokevirtual toLowerCase : ()Ljava/lang/String;
    //   202: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   205: astore #6
    //   207: aload #10
    //   209: iconst_1
    //   210: aload #10
    //   212: invokevirtual length : ()I
    //   215: iconst_4
    //   216: isub
    //   217: invokevirtual substring : (II)Ljava/lang/String;
    //   220: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   223: astore #5
    //   225: aload #5
    //   227: invokevirtual length : ()I
    //   230: ifeq -> 245
    //   233: aload #6
    //   235: aload #5
    //   237: invokevirtual concat : (Ljava/lang/String;)Ljava/lang/String;
    //   240: astore #6
    //   242: goto -> 256
    //   245: new java/lang/String
    //   248: dup
    //   249: aload #6
    //   251: invokespecial <init> : (Ljava/lang/String;)V
    //   254: astore #6
    //   256: aload #10
    //   258: invokevirtual length : ()I
    //   261: ifeq -> 276
    //   264: ldc 'get'
    //   266: aload #10
    //   268: invokevirtual concat : (Ljava/lang/String;)Ljava/lang/String;
    //   271: astore #5
    //   273: goto -> 287
    //   276: new java/lang/String
    //   279: dup
    //   280: ldc 'get'
    //   282: invokespecial <init> : (Ljava/lang/String;)V
    //   285: astore #5
    //   287: aload_3
    //   288: aload #5
    //   290: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   295: checkcast java/lang/reflect/Method
    //   298: astore #5
    //   300: aload #5
    //   302: ifnull -> 341
    //   305: aload #5
    //   307: invokevirtual getReturnType : ()Ljava/lang/Class;
    //   310: ldc java/util/List
    //   312: invokevirtual equals : (Ljava/lang/Object;)Z
    //   315: ifeq -> 341
    //   318: aload_1
    //   319: iload_2
    //   320: aload #6
    //   322: invokestatic zztm : (Ljava/lang/String;)Ljava/lang/String;
    //   325: aload #5
    //   327: aload_0
    //   328: iconst_0
    //   329: anewarray java/lang/Object
    //   332: invokestatic zza : (Ljava/lang/reflect/Method;Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   335: invokestatic zzb : (Ljava/lang/StringBuilder;ILjava/lang/String;Ljava/lang/Object;)V
    //   338: goto -> 136
    //   341: aload #10
    //   343: invokevirtual length : ()I
    //   346: ifeq -> 361
    //   349: ldc 'set'
    //   351: aload #10
    //   353: invokevirtual concat : (Ljava/lang/String;)Ljava/lang/String;
    //   356: astore #6
    //   358: goto -> 372
    //   361: new java/lang/String
    //   364: dup
    //   365: ldc 'set'
    //   367: invokespecial <init> : (Ljava/lang/String;)V
    //   370: astore #6
    //   372: aload #4
    //   374: aload #6
    //   376: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   381: checkcast java/lang/reflect/Method
    //   384: ifnull -> 136
    //   387: aload #10
    //   389: ldc 'Bytes'
    //   391: invokevirtual endsWith : (Ljava/lang/String;)Z
    //   394: ifeq -> 457
    //   397: aload #10
    //   399: iconst_0
    //   400: aload #10
    //   402: invokevirtual length : ()I
    //   405: iconst_5
    //   406: isub
    //   407: invokevirtual substring : (II)Ljava/lang/String;
    //   410: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   413: astore #6
    //   415: aload #6
    //   417: invokevirtual length : ()I
    //   420: ifeq -> 435
    //   423: ldc 'get'
    //   425: aload #6
    //   427: invokevirtual concat : (Ljava/lang/String;)Ljava/lang/String;
    //   430: astore #6
    //   432: goto -> 446
    //   435: new java/lang/String
    //   438: dup
    //   439: ldc 'get'
    //   441: invokespecial <init> : (Ljava/lang/String;)V
    //   444: astore #6
    //   446: aload_3
    //   447: aload #6
    //   449: invokeinterface containsKey : (Ljava/lang/Object;)Z
    //   454: ifne -> 136
    //   457: aload #10
    //   459: iconst_0
    //   460: iconst_1
    //   461: invokevirtual substring : (II)Ljava/lang/String;
    //   464: invokevirtual toLowerCase : ()Ljava/lang/String;
    //   467: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   470: astore #5
    //   472: aload #10
    //   474: iconst_1
    //   475: invokevirtual substring : (I)Ljava/lang/String;
    //   478: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   481: astore #6
    //   483: aload #6
    //   485: invokevirtual length : ()I
    //   488: ifeq -> 503
    //   491: aload #5
    //   493: aload #6
    //   495: invokevirtual concat : (Ljava/lang/String;)Ljava/lang/String;
    //   498: astore #6
    //   500: goto -> 514
    //   503: new java/lang/String
    //   506: dup
    //   507: aload #5
    //   509: invokespecial <init> : (Ljava/lang/String;)V
    //   512: astore #6
    //   514: aload #10
    //   516: invokevirtual length : ()I
    //   519: ifeq -> 534
    //   522: ldc 'get'
    //   524: aload #10
    //   526: invokevirtual concat : (Ljava/lang/String;)Ljava/lang/String;
    //   529: astore #5
    //   531: goto -> 545
    //   534: new java/lang/String
    //   537: dup
    //   538: ldc 'get'
    //   540: invokespecial <init> : (Ljava/lang/String;)V
    //   543: astore #5
    //   545: aload_3
    //   546: aload #5
    //   548: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   553: checkcast java/lang/reflect/Method
    //   556: astore #13
    //   558: aload #10
    //   560: invokevirtual length : ()I
    //   563: ifeq -> 578
    //   566: ldc 'has'
    //   568: aload #10
    //   570: invokevirtual concat : (Ljava/lang/String;)Ljava/lang/String;
    //   573: astore #5
    //   575: goto -> 589
    //   578: new java/lang/String
    //   581: dup
    //   582: ldc 'has'
    //   584: invokespecial <init> : (Ljava/lang/String;)V
    //   587: astore #5
    //   589: aload_3
    //   590: aload #5
    //   592: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   597: checkcast java/lang/reflect/Method
    //   600: astore #5
    //   602: aload #13
    //   604: ifnull -> 136
    //   607: aload #13
    //   609: aload_0
    //   610: iconst_0
    //   611: anewarray java/lang/Object
    //   614: invokestatic zza : (Ljava/lang/reflect/Method;Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   617: astore #10
    //   619: aload #5
    //   621: ifnonnull -> 832
    //   624: aload #10
    //   626: instanceof java/lang/Boolean
    //   629: ifeq -> 655
    //   632: aload #10
    //   634: checkcast java/lang/Boolean
    //   637: invokevirtual booleanValue : ()Z
    //   640: ifne -> 649
    //   643: iconst_1
    //   644: istore #11
    //   646: goto -> 814
    //   649: iconst_0
    //   650: istore #11
    //   652: goto -> 814
    //   655: aload #10
    //   657: instanceof java/lang/Integer
    //   660: ifeq -> 677
    //   663: aload #10
    //   665: checkcast java/lang/Integer
    //   668: invokevirtual intValue : ()I
    //   671: ifne -> 649
    //   674: goto -> 643
    //   677: aload #10
    //   679: instanceof java/lang/Float
    //   682: ifeq -> 701
    //   685: aload #10
    //   687: checkcast java/lang/Float
    //   690: invokevirtual floatValue : ()F
    //   693: fconst_0
    //   694: fcmpl
    //   695: ifne -> 649
    //   698: goto -> 643
    //   701: aload #10
    //   703: instanceof java/lang/Double
    //   706: ifeq -> 725
    //   709: aload #10
    //   711: checkcast java/lang/Double
    //   714: invokevirtual doubleValue : ()D
    //   717: dconst_0
    //   718: dcmpl
    //   719: ifne -> 649
    //   722: goto -> 643
    //   725: aload #10
    //   727: instanceof java/lang/String
    //   730: ifeq -> 745
    //   733: aload #10
    //   735: ldc ''
    //   737: invokevirtual equals : (Ljava/lang/Object;)Z
    //   740: istore #11
    //   742: goto -> 814
    //   745: aload #10
    //   747: instanceof com/google/android/gms/internal/zzeuk
    //   750: ifeq -> 766
    //   753: aload #10
    //   755: getstatic com/google/android/gms/internal/zzeuk.zzomx : Lcom/google/android/gms/internal/zzeuk;
    //   758: invokevirtual equals : (Ljava/lang/Object;)Z
    //   761: istore #11
    //   763: goto -> 814
    //   766: aload #10
    //   768: instanceof com/google/android/gms/internal/zzewl
    //   771: ifeq -> 792
    //   774: aload #10
    //   776: aload #10
    //   778: checkcast com/google/android/gms/internal/zzewl
    //   781: invokeinterface zzcuc : ()Lcom/google/android/gms/internal/zzewl;
    //   786: if_acmpne -> 649
    //   789: goto -> 643
    //   792: aload #10
    //   794: instanceof java/lang/Enum
    //   797: ifeq -> 649
    //   800: aload #10
    //   802: checkcast java/lang/Enum
    //   805: invokevirtual ordinal : ()I
    //   808: ifne -> 649
    //   811: goto -> 643
    //   814: iload #11
    //   816: ifne -> 826
    //   819: iload #12
    //   821: istore #11
    //   823: goto -> 850
    //   826: iconst_0
    //   827: istore #11
    //   829: goto -> 850
    //   832: aload #5
    //   834: aload_0
    //   835: iconst_0
    //   836: anewarray java/lang/Object
    //   839: invokestatic zza : (Ljava/lang/reflect/Method;Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   842: checkcast java/lang/Boolean
    //   845: invokevirtual booleanValue : ()Z
    //   848: istore #11
    //   850: iload #11
    //   852: ifeq -> 136
    //   855: aload_1
    //   856: iload_2
    //   857: aload #6
    //   859: invokestatic zztm : (Ljava/lang/String;)Ljava/lang/String;
    //   862: aload #10
    //   864: invokestatic zzb : (Ljava/lang/StringBuilder;ILjava/lang/String;Ljava/lang/Object;)V
    //   867: goto -> 136
    //   870: aload_0
    //   871: instanceof com/google/android/gms/internal/zzevm
    //   874: ifeq -> 926
    //   877: aload_0
    //   878: checkcast com/google/android/gms/internal/zzevm
    //   881: getfield zzool : Lcom/google/android/gms/internal/zzeve;
    //   884: invokevirtual iterator : ()Ljava/util/Iterator;
    //   887: astore #6
    //   889: aload #6
    //   891: invokeinterface hasNext : ()Z
    //   896: ifne -> 902
    //   899: goto -> 926
    //   902: aload #6
    //   904: invokeinterface next : ()Ljava/lang/Object;
    //   909: checkcast java/util/Map$Entry
    //   912: invokeinterface getKey : ()Ljava/lang/Object;
    //   917: pop
    //   918: new java/lang/NoSuchMethodError
    //   921: dup
    //   922: invokespecial <init> : ()V
    //   925: athrow
    //   926: aload_0
    //   927: checkcast com/google/android/gms/internal/zzevh
    //   930: getfield zzooe : Lcom/google/android/gms/internal/zzexl;
    //   933: astore_0
    //   934: aload_0
    //   935: ifnull -> 944
    //   938: aload_0
    //   939: aload_1
    //   940: iload_2
    //   941: invokevirtual zzd : (Ljava/lang/StringBuilder;I)V
    //   944: return
  }
  
  static final void zzb(StringBuilder paramStringBuilder, int paramInt, String paramString, Object paramObject) {
    if (paramObject instanceof List) {
      paramObject = ((List)paramObject).iterator();
      while (paramObject.hasNext())
        zzb(paramStringBuilder, paramInt, paramString, paramObject.next()); 
      return;
    } 
    paramStringBuilder.append('\n');
    boolean bool = false;
    byte b;
    for (b = 0; b < paramInt; b++)
      paramStringBuilder.append(' '); 
    paramStringBuilder.append(paramString);
    if (paramObject instanceof String) {
      paramStringBuilder.append(": \"");
      paramStringBuilder.append(zzexf.zzaq(zzeuk.zzti((String)paramObject)));
      paramStringBuilder.append('"');
      return;
    } 
    if (paramObject instanceof zzeuk) {
      paramStringBuilder.append(": \"");
      paramStringBuilder.append(zzexf.zzaq((zzeuk)paramObject));
      paramStringBuilder.append('"');
      return;
    } 
    if (paramObject instanceof zzevh) {
      paramStringBuilder.append(" {");
      zza((zzevh)paramObject, paramStringBuilder, paramInt + 2);
      paramStringBuilder.append("\n");
      for (b = bool; b < paramInt; b++)
        paramStringBuilder.append(' '); 
      paramStringBuilder.append("}");
      return;
    } 
    paramStringBuilder.append(": ");
    paramStringBuilder.append(paramObject.toString());
  }
  
  private static final String zztm(String paramString) {
    StringBuilder stringBuilder = new StringBuilder();
    for (byte b = 0; b < paramString.length(); b++) {
      char c = paramString.charAt(b);
      if (Character.isUpperCase(c))
        stringBuilder.append("_"); 
      stringBuilder.append(Character.toLowerCase(c));
    } 
    return stringBuilder.toString();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzewo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */