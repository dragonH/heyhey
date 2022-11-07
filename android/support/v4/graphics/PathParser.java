package android.support.v4.graphics;

import android.graphics.Path;
import android.support.annotation.RestrictTo;
import android.util.Log;
import java.util.ArrayList;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class PathParser {
  private static final String LOGTAG = "PathParser";
  
  private static void addNode(ArrayList<PathDataNode> paramArrayList, char paramChar, float[] paramArrayOffloat) {
    paramArrayList.add(new PathDataNode(paramChar, paramArrayOffloat));
  }
  
  public static boolean canMorph(PathDataNode[] paramArrayOfPathDataNode1, PathDataNode[] paramArrayOfPathDataNode2) {
    if (paramArrayOfPathDataNode1 == null || paramArrayOfPathDataNode2 == null)
      return false; 
    if (paramArrayOfPathDataNode1.length != paramArrayOfPathDataNode2.length)
      return false; 
    for (byte b = 0; b < paramArrayOfPathDataNode1.length; b++) {
      PathDataNode pathDataNode1 = paramArrayOfPathDataNode1[b];
      char c = pathDataNode1.mType;
      PathDataNode pathDataNode2 = paramArrayOfPathDataNode2[b];
      if (c != pathDataNode2.mType || pathDataNode1.mParams.length != pathDataNode2.mParams.length)
        return false; 
    } 
    return true;
  }
  
  static float[] copyOfRange(float[] paramArrayOffloat, int paramInt1, int paramInt2) {
    if (paramInt1 <= paramInt2) {
      int i = paramArrayOffloat.length;
      if (paramInt1 >= 0 && paramInt1 <= i) {
        paramInt2 -= paramInt1;
        i = Math.min(paramInt2, i - paramInt1);
        float[] arrayOfFloat = new float[paramInt2];
        System.arraycopy(paramArrayOffloat, paramInt1, arrayOfFloat, 0, i);
        return arrayOfFloat;
      } 
      throw new ArrayIndexOutOfBoundsException();
    } 
    throw new IllegalArgumentException();
  }
  
  public static PathDataNode[] createNodesFromPathData(String paramString) {
    if (paramString == null)
      return null; 
    ArrayList<PathDataNode> arrayList = new ArrayList();
    int i = 1;
    int j = 0;
    while (i < paramString.length()) {
      i = nextStart(paramString, i);
      String str = paramString.substring(j, i).trim();
      if (str.length() > 0) {
        float[] arrayOfFloat = getFloats(str);
        addNode(arrayList, str.charAt(0), arrayOfFloat);
      } 
      j = i;
      i++;
    } 
    if (i - j == 1 && j < paramString.length())
      addNode(arrayList, paramString.charAt(j), new float[0]); 
    return arrayList.<PathDataNode>toArray(new PathDataNode[arrayList.size()]);
  }
  
  public static Path createPathFromPathData(String paramString) {
    Path path = new Path();
    PathDataNode[] arrayOfPathDataNode = createNodesFromPathData(paramString);
    if (arrayOfPathDataNode != null)
      try {
        PathDataNode.nodesToPath(arrayOfPathDataNode, path);
        return path;
      } catch (RuntimeException runtimeException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Error in parsing ");
        stringBuilder.append(paramString);
        throw new RuntimeException(stringBuilder.toString(), runtimeException);
      }  
    return null;
  }
  
  public static PathDataNode[] deepCopyNodes(PathDataNode[] paramArrayOfPathDataNode) {
    if (paramArrayOfPathDataNode == null)
      return null; 
    PathDataNode[] arrayOfPathDataNode = new PathDataNode[paramArrayOfPathDataNode.length];
    for (byte b = 0; b < paramArrayOfPathDataNode.length; b++)
      arrayOfPathDataNode[b] = new PathDataNode(paramArrayOfPathDataNode[b]); 
    return arrayOfPathDataNode;
  }
  
  private static void extract(String paramString, int paramInt, ExtractFloatResult paramExtractFloatResult) {
    paramExtractFloatResult.mEndWithNegOrDot = false;
    int i = paramInt;
    boolean bool1 = false;
    boolean bool2 = false;
    boolean bool3 = false;
    while (i < paramString.length()) {
      char c = paramString.charAt(i);
      if (c != ' ') {
        if (c != 'E' && c != 'e') {
          switch (c) {
            default:
              bool1 = false;
              break;
            case '.':
              if (!bool2) {
                bool1 = false;
                bool2 = true;
                break;
              } 
              paramExtractFloatResult.mEndWithNegOrDot = true;
            case '-':
            
            case ',':
              bool1 = false;
              bool3 = true;
              break;
          } 
        } else {
          bool1 = true;
        } 
        if (bool3)
          break; 
        continue;
      } 
      i++;
    } 
    paramExtractFloatResult.mEndPosition = i;
  }
  
  private static float[] getFloats(String paramString) {
    if (paramString.charAt(0) == 'z' || paramString.charAt(0) == 'Z')
      return new float[0]; 
    try {
      float[] arrayOfFloat = new float[paramString.length()];
      ExtractFloatResult extractFloatResult = new ExtractFloatResult();
      this();
      int i = paramString.length();
      int j = 1;
      int k;
      for (k = 0; j < i; k = n) {
        extract(paramString, j, extractFloatResult);
        int m = extractFloatResult.mEndPosition;
        int n = k;
        if (j < m) {
          arrayOfFloat[k] = Float.parseFloat(paramString.substring(j, m));
          n = k + 1;
        } 
        if (extractFloatResult.mEndWithNegOrDot) {
          j = m;
          k = n;
          continue;
        } 
        j = m + 1;
      } 
      return copyOfRange(arrayOfFloat, 0, k);
    } catch (NumberFormatException numberFormatException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("error in parsing \"");
      stringBuilder.append(paramString);
      stringBuilder.append("\"");
      throw new RuntimeException(stringBuilder.toString(), numberFormatException);
    } 
  }
  
  private static int nextStart(String paramString, int paramInt) {
    while (paramInt < paramString.length()) {
      char c = paramString.charAt(paramInt);
      if (((c - 65) * (c - 90) <= 0 || (c - 97) * (c - 122) <= 0) && c != 'e' && c != 'E')
        return paramInt; 
      paramInt++;
    } 
    return paramInt;
  }
  
  public static void updateNodes(PathDataNode[] paramArrayOfPathDataNode1, PathDataNode[] paramArrayOfPathDataNode2) {
    byte b = 0;
    while (b < paramArrayOfPathDataNode2.length) {
      (paramArrayOfPathDataNode1[b]).mType = (char)(paramArrayOfPathDataNode2[b]).mType;
      byte b1 = 0;
      while (true) {
        float[] arrayOfFloat = (paramArrayOfPathDataNode2[b]).mParams;
        if (b1 < arrayOfFloat.length) {
          (paramArrayOfPathDataNode1[b]).mParams[b1] = arrayOfFloat[b1];
          b1++;
          continue;
        } 
        b++;
      } 
    } 
  }
  
  private static class ExtractFloatResult {
    int mEndPosition;
    
    boolean mEndWithNegOrDot;
  }
  
  public static class PathDataNode {
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public float[] mParams;
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public char mType;
    
    PathDataNode(char param1Char, float[] param1ArrayOffloat) {
      this.mType = (char)param1Char;
      this.mParams = param1ArrayOffloat;
    }
    
    PathDataNode(PathDataNode param1PathDataNode) {
      this.mType = (char)param1PathDataNode.mType;
      float[] arrayOfFloat = param1PathDataNode.mParams;
      this.mParams = PathParser.copyOfRange(arrayOfFloat, 0, arrayOfFloat.length);
    }
    
    private static void addCommand(Path param1Path, float[] param1ArrayOffloat1, char param1Char1, char param1Char2, float[] param1ArrayOffloat2) {
      // Byte code:
      //   0: aload_1
      //   1: iconst_0
      //   2: faload
      //   3: fstore #5
      //   5: aload_1
      //   6: iconst_1
      //   7: faload
      //   8: fstore #6
      //   10: aload_1
      //   11: iconst_2
      //   12: faload
      //   13: fstore #7
      //   15: aload_1
      //   16: iconst_3
      //   17: faload
      //   18: fstore #8
      //   20: aload_1
      //   21: iconst_4
      //   22: faload
      //   23: fstore #9
      //   25: aload_1
      //   26: iconst_5
      //   27: faload
      //   28: fstore #10
      //   30: fload #5
      //   32: fstore #11
      //   34: fload #6
      //   36: fstore #12
      //   38: fload #7
      //   40: fstore #13
      //   42: fload #8
      //   44: fstore #14
      //   46: iload_3
      //   47: lookupswitch default -> 216, 65 -> 336, 67 -> 313, 72 -> 291, 76 -> 232, 77 -> 232, 81 -> 269, 83 -> 269, 84 -> 232, 86 -> 291, 90 -> 238, 97 -> 336, 99 -> 313, 104 -> 291, 108 -> 232, 109 -> 232, 113 -> 269, 115 -> 269, 116 -> 232, 118 -> 291, 122 -> 238
      //   216: fload #8
      //   218: fstore #14
      //   220: fload #7
      //   222: fstore #13
      //   224: fload #6
      //   226: fstore #12
      //   228: fload #5
      //   230: fstore #11
      //   232: iconst_2
      //   233: istore #15
      //   235: goto -> 356
      //   238: aload_0
      //   239: invokevirtual close : ()V
      //   242: aload_0
      //   243: fload #9
      //   245: fload #10
      //   247: invokevirtual moveTo : (FF)V
      //   250: fload #9
      //   252: fstore #11
      //   254: fload #11
      //   256: fstore #13
      //   258: fload #10
      //   260: fstore #12
      //   262: fload #12
      //   264: fstore #14
      //   266: goto -> 232
      //   269: iconst_4
      //   270: istore #15
      //   272: fload #5
      //   274: fstore #11
      //   276: fload #6
      //   278: fstore #12
      //   280: fload #7
      //   282: fstore #13
      //   284: fload #8
      //   286: fstore #14
      //   288: goto -> 356
      //   291: iconst_1
      //   292: istore #15
      //   294: fload #5
      //   296: fstore #11
      //   298: fload #6
      //   300: fstore #12
      //   302: fload #7
      //   304: fstore #13
      //   306: fload #8
      //   308: fstore #14
      //   310: goto -> 356
      //   313: bipush #6
      //   315: istore #15
      //   317: fload #5
      //   319: fstore #11
      //   321: fload #6
      //   323: fstore #12
      //   325: fload #7
      //   327: fstore #13
      //   329: fload #8
      //   331: fstore #14
      //   333: goto -> 356
      //   336: bipush #7
      //   338: istore #15
      //   340: fload #8
      //   342: fstore #14
      //   344: fload #7
      //   346: fstore #13
      //   348: fload #6
      //   350: fstore #12
      //   352: fload #5
      //   354: fstore #11
      //   356: iconst_0
      //   357: istore #16
      //   359: iload_2
      //   360: istore #17
      //   362: fload #10
      //   364: fstore #7
      //   366: fload #9
      //   368: fstore #6
      //   370: iload #16
      //   372: istore_2
      //   373: iload_3
      //   374: istore #16
      //   376: iload_2
      //   377: aload #4
      //   379: arraylength
      //   380: if_icmpge -> 2113
      //   383: iload #16
      //   385: bipush #65
      //   387: if_icmpeq -> 1959
      //   390: iload #16
      //   392: bipush #67
      //   394: if_icmpeq -> 1846
      //   397: iload #16
      //   399: bipush #72
      //   401: if_icmpeq -> 1820
      //   404: iload #16
      //   406: bipush #81
      //   408: if_icmpeq -> 1729
      //   411: iload #16
      //   413: bipush #86
      //   415: if_icmpeq -> 1703
      //   418: iload #16
      //   420: bipush #97
      //   422: if_icmpeq -> 1563
      //   425: iload #16
      //   427: bipush #99
      //   429: if_icmpeq -> 1420
      //   432: iload #16
      //   434: bipush #104
      //   436: if_icmpeq -> 1392
      //   439: iload #16
      //   441: bipush #113
      //   443: if_icmpeq -> 1292
      //   446: iload #16
      //   448: bipush #118
      //   450: if_icmpeq -> 1267
      //   453: iload #16
      //   455: bipush #76
      //   457: if_icmpeq -> 1222
      //   460: iload #16
      //   462: bipush #77
      //   464: if_icmpeq -> 1172
      //   467: iload #16
      //   469: bipush #83
      //   471: if_icmpeq -> 1027
      //   474: iload #16
      //   476: bipush #84
      //   478: if_icmpeq -> 916
      //   481: iload #16
      //   483: bipush #108
      //   485: if_icmpeq -> 861
      //   488: iload #16
      //   490: bipush #109
      //   492: if_icmpeq -> 805
      //   495: iload #16
      //   497: bipush #115
      //   499: if_icmpeq -> 643
      //   502: iload #16
      //   504: bipush #116
      //   506: if_icmpeq -> 512
      //   509: goto -> 2102
      //   512: iload #17
      //   514: bipush #113
      //   516: if_icmpeq -> 552
      //   519: iload #17
      //   521: bipush #116
      //   523: if_icmpeq -> 552
      //   526: iload #17
      //   528: bipush #81
      //   530: if_icmpeq -> 552
      //   533: iload #17
      //   535: bipush #84
      //   537: if_icmpne -> 543
      //   540: goto -> 552
      //   543: fconst_0
      //   544: fstore #14
      //   546: fconst_0
      //   547: fstore #13
      //   549: goto -> 566
      //   552: fload #11
      //   554: fload #13
      //   556: fsub
      //   557: fstore #13
      //   559: fload #12
      //   561: fload #14
      //   563: fsub
      //   564: fstore #14
      //   566: iload_2
      //   567: iconst_0
      //   568: iadd
      //   569: istore #16
      //   571: aload #4
      //   573: iload #16
      //   575: faload
      //   576: fstore #10
      //   578: iload_2
      //   579: iconst_1
      //   580: iadd
      //   581: istore #17
      //   583: aload_0
      //   584: fload #13
      //   586: fload #14
      //   588: fload #10
      //   590: aload #4
      //   592: iload #17
      //   594: faload
      //   595: invokevirtual rQuadTo : (FFFF)V
      //   598: fload #11
      //   600: aload #4
      //   602: iload #16
      //   604: faload
      //   605: fadd
      //   606: fstore #10
      //   608: fload #12
      //   610: aload #4
      //   612: iload #17
      //   614: faload
      //   615: fadd
      //   616: fstore #9
      //   618: fload #14
      //   620: fload #12
      //   622: fadd
      //   623: fstore #14
      //   625: fload #13
      //   627: fload #11
      //   629: fadd
      //   630: fstore #13
      //   632: fload #9
      //   634: fstore #12
      //   636: fload #10
      //   638: fstore #11
      //   640: goto -> 509
      //   643: iload #17
      //   645: bipush #99
      //   647: if_icmpeq -> 683
      //   650: iload #17
      //   652: bipush #115
      //   654: if_icmpeq -> 683
      //   657: iload #17
      //   659: bipush #67
      //   661: if_icmpeq -> 683
      //   664: iload #17
      //   666: bipush #83
      //   668: if_icmpne -> 674
      //   671: goto -> 683
      //   674: fconst_0
      //   675: fstore #14
      //   677: fconst_0
      //   678: fstore #13
      //   680: goto -> 701
      //   683: fload #12
      //   685: fload #14
      //   687: fsub
      //   688: fstore #10
      //   690: fload #11
      //   692: fload #13
      //   694: fsub
      //   695: fstore #14
      //   697: fload #10
      //   699: fstore #13
      //   701: iload_2
      //   702: iconst_0
      //   703: iadd
      //   704: istore #17
      //   706: aload #4
      //   708: iload #17
      //   710: faload
      //   711: fstore #10
      //   713: iload_2
      //   714: iconst_1
      //   715: iadd
      //   716: istore #16
      //   718: aload #4
      //   720: iload #16
      //   722: faload
      //   723: fstore #5
      //   725: iload_2
      //   726: iconst_2
      //   727: iadd
      //   728: istore #18
      //   730: aload #4
      //   732: iload #18
      //   734: faload
      //   735: fstore #9
      //   737: iload_2
      //   738: iconst_3
      //   739: iadd
      //   740: istore #19
      //   742: aload_0
      //   743: fload #14
      //   745: fload #13
      //   747: fload #10
      //   749: fload #5
      //   751: fload #9
      //   753: aload #4
      //   755: iload #19
      //   757: faload
      //   758: invokevirtual rCubicTo : (FFFFFF)V
      //   761: aload #4
      //   763: iload #17
      //   765: faload
      //   766: fload #11
      //   768: fadd
      //   769: fstore #9
      //   771: aload #4
      //   773: iload #16
      //   775: faload
      //   776: fload #12
      //   778: fadd
      //   779: fstore #13
      //   781: fload #11
      //   783: aload #4
      //   785: iload #18
      //   787: faload
      //   788: fadd
      //   789: fstore #14
      //   791: aload #4
      //   793: iload #19
      //   795: faload
      //   796: fstore #10
      //   798: fload #9
      //   800: fstore #11
      //   802: goto -> 1537
      //   805: aload #4
      //   807: iload_2
      //   808: iconst_0
      //   809: iadd
      //   810: faload
      //   811: fstore #10
      //   813: fload #11
      //   815: fload #10
      //   817: fadd
      //   818: fstore #11
      //   820: aload #4
      //   822: iload_2
      //   823: iconst_1
      //   824: iadd
      //   825: faload
      //   826: fstore #9
      //   828: fload #12
      //   830: fload #9
      //   832: fadd
      //   833: fstore #12
      //   835: iload_2
      //   836: ifle -> 850
      //   839: aload_0
      //   840: fload #10
      //   842: fload #9
      //   844: invokevirtual rLineTo : (FF)V
      //   847: goto -> 509
      //   850: aload_0
      //   851: fload #10
      //   853: fload #9
      //   855: invokevirtual rMoveTo : (FF)V
      //   858: goto -> 1211
      //   861: iload_2
      //   862: iconst_0
      //   863: iadd
      //   864: istore #16
      //   866: aload #4
      //   868: iload #16
      //   870: faload
      //   871: fstore #10
      //   873: iload_2
      //   874: iconst_1
      //   875: iadd
      //   876: istore #17
      //   878: aload_0
      //   879: fload #10
      //   881: aload #4
      //   883: iload #17
      //   885: faload
      //   886: invokevirtual rLineTo : (FF)V
      //   889: fload #11
      //   891: aload #4
      //   893: iload #16
      //   895: faload
      //   896: fadd
      //   897: fstore #11
      //   899: aload #4
      //   901: iload #17
      //   903: faload
      //   904: fstore #10
      //   906: fload #12
      //   908: fload #10
      //   910: fadd
      //   911: fstore #12
      //   913: goto -> 509
      //   916: iload #17
      //   918: bipush #113
      //   920: if_icmpeq -> 952
      //   923: iload #17
      //   925: bipush #116
      //   927: if_icmpeq -> 952
      //   930: iload #17
      //   932: bipush #81
      //   934: if_icmpeq -> 952
      //   937: fload #12
      //   939: fstore #9
      //   941: fload #11
      //   943: fstore #10
      //   945: iload #17
      //   947: bipush #84
      //   949: if_icmpne -> 970
      //   952: fload #11
      //   954: fconst_2
      //   955: fmul
      //   956: fload #13
      //   958: fsub
      //   959: fstore #10
      //   961: fload #12
      //   963: fconst_2
      //   964: fmul
      //   965: fload #14
      //   967: fsub
      //   968: fstore #9
      //   970: iload_2
      //   971: iconst_0
      //   972: iadd
      //   973: istore #17
      //   975: aload #4
      //   977: iload #17
      //   979: faload
      //   980: fstore #11
      //   982: iload_2
      //   983: iconst_1
      //   984: iadd
      //   985: istore #16
      //   987: aload_0
      //   988: fload #10
      //   990: fload #9
      //   992: fload #11
      //   994: aload #4
      //   996: iload #16
      //   998: faload
      //   999: invokevirtual quadTo : (FFFF)V
      //   1002: aload #4
      //   1004: iload #17
      //   1006: faload
      //   1007: fstore #11
      //   1009: aload #4
      //   1011: iload #16
      //   1013: faload
      //   1014: fstore #12
      //   1016: fload #9
      //   1018: fstore #14
      //   1020: fload #10
      //   1022: fstore #13
      //   1024: goto -> 2102
      //   1027: iload #17
      //   1029: bipush #99
      //   1031: if_icmpeq -> 1063
      //   1034: iload #17
      //   1036: bipush #115
      //   1038: if_icmpeq -> 1063
      //   1041: iload #17
      //   1043: bipush #67
      //   1045: if_icmpeq -> 1063
      //   1048: fload #12
      //   1050: fstore #9
      //   1052: fload #11
      //   1054: fstore #10
      //   1056: iload #17
      //   1058: bipush #83
      //   1060: if_icmpne -> 1081
      //   1063: fload #11
      //   1065: fconst_2
      //   1066: fmul
      //   1067: fload #13
      //   1069: fsub
      //   1070: fstore #10
      //   1072: fload #12
      //   1074: fconst_2
      //   1075: fmul
      //   1076: fload #14
      //   1078: fsub
      //   1079: fstore #9
      //   1081: iload_2
      //   1082: iconst_0
      //   1083: iadd
      //   1084: istore #16
      //   1086: aload #4
      //   1088: iload #16
      //   1090: faload
      //   1091: fstore #13
      //   1093: iload_2
      //   1094: iconst_1
      //   1095: iadd
      //   1096: istore #17
      //   1098: aload #4
      //   1100: iload #17
      //   1102: faload
      //   1103: fstore #12
      //   1105: iload_2
      //   1106: iconst_2
      //   1107: iadd
      //   1108: istore #18
      //   1110: aload #4
      //   1112: iload #18
      //   1114: faload
      //   1115: fstore #11
      //   1117: iload_2
      //   1118: iconst_3
      //   1119: iadd
      //   1120: istore #19
      //   1122: aload_0
      //   1123: fload #10
      //   1125: fload #9
      //   1127: fload #13
      //   1129: fload #12
      //   1131: fload #11
      //   1133: aload #4
      //   1135: iload #19
      //   1137: faload
      //   1138: invokevirtual cubicTo : (FFFFFF)V
      //   1141: aload #4
      //   1143: iload #16
      //   1145: faload
      //   1146: fstore #11
      //   1148: aload #4
      //   1150: iload #17
      //   1152: faload
      //   1153: fstore #13
      //   1155: aload #4
      //   1157: iload #18
      //   1159: faload
      //   1160: fstore #10
      //   1162: aload #4
      //   1164: iload #19
      //   1166: faload
      //   1167: fstore #12
      //   1169: goto -> 1548
      //   1172: aload #4
      //   1174: iload_2
      //   1175: iconst_0
      //   1176: iadd
      //   1177: faload
      //   1178: fstore #11
      //   1180: aload #4
      //   1182: iload_2
      //   1183: iconst_1
      //   1184: iadd
      //   1185: faload
      //   1186: fstore #12
      //   1188: iload_2
      //   1189: ifle -> 1203
      //   1192: aload_0
      //   1193: fload #11
      //   1195: fload #12
      //   1197: invokevirtual lineTo : (FF)V
      //   1200: goto -> 509
      //   1203: aload_0
      //   1204: fload #11
      //   1206: fload #12
      //   1208: invokevirtual moveTo : (FF)V
      //   1211: fload #12
      //   1213: fstore #7
      //   1215: fload #11
      //   1217: fstore #6
      //   1219: goto -> 2102
      //   1222: iload_2
      //   1223: iconst_0
      //   1224: iadd
      //   1225: istore #17
      //   1227: aload #4
      //   1229: iload #17
      //   1231: faload
      //   1232: fstore #11
      //   1234: iload_2
      //   1235: iconst_1
      //   1236: iadd
      //   1237: istore #16
      //   1239: aload_0
      //   1240: fload #11
      //   1242: aload #4
      //   1244: iload #16
      //   1246: faload
      //   1247: invokevirtual lineTo : (FF)V
      //   1250: aload #4
      //   1252: iload #17
      //   1254: faload
      //   1255: fstore #11
      //   1257: aload #4
      //   1259: iload #16
      //   1261: faload
      //   1262: fstore #12
      //   1264: goto -> 509
      //   1267: iload_2
      //   1268: iconst_0
      //   1269: iadd
      //   1270: istore #17
      //   1272: aload_0
      //   1273: fconst_0
      //   1274: aload #4
      //   1276: iload #17
      //   1278: faload
      //   1279: invokevirtual rLineTo : (FF)V
      //   1282: aload #4
      //   1284: iload #17
      //   1286: faload
      //   1287: fstore #10
      //   1289: goto -> 906
      //   1292: iload_2
      //   1293: iconst_0
      //   1294: iadd
      //   1295: istore #19
      //   1297: aload #4
      //   1299: iload #19
      //   1301: faload
      //   1302: fstore #13
      //   1304: iload_2
      //   1305: iconst_1
      //   1306: iadd
      //   1307: istore #17
      //   1309: aload #4
      //   1311: iload #17
      //   1313: faload
      //   1314: fstore #14
      //   1316: iload_2
      //   1317: iconst_2
      //   1318: iadd
      //   1319: istore #18
      //   1321: aload #4
      //   1323: iload #18
      //   1325: faload
      //   1326: fstore #10
      //   1328: iload_2
      //   1329: iconst_3
      //   1330: iadd
      //   1331: istore #16
      //   1333: aload_0
      //   1334: fload #13
      //   1336: fload #14
      //   1338: fload #10
      //   1340: aload #4
      //   1342: iload #16
      //   1344: faload
      //   1345: invokevirtual rQuadTo : (FFFF)V
      //   1348: aload #4
      //   1350: iload #19
      //   1352: faload
      //   1353: fload #11
      //   1355: fadd
      //   1356: fstore #9
      //   1358: aload #4
      //   1360: iload #17
      //   1362: faload
      //   1363: fload #12
      //   1365: fadd
      //   1366: fstore #13
      //   1368: fload #11
      //   1370: aload #4
      //   1372: iload #18
      //   1374: faload
      //   1375: fadd
      //   1376: fstore #14
      //   1378: aload #4
      //   1380: iload #16
      //   1382: faload
      //   1383: fstore #10
      //   1385: fload #9
      //   1387: fstore #11
      //   1389: goto -> 1537
      //   1392: iload_2
      //   1393: iconst_0
      //   1394: iadd
      //   1395: istore #17
      //   1397: aload_0
      //   1398: aload #4
      //   1400: iload #17
      //   1402: faload
      //   1403: fconst_0
      //   1404: invokevirtual rLineTo : (FF)V
      //   1407: fload #11
      //   1409: aload #4
      //   1411: iload #17
      //   1413: faload
      //   1414: fadd
      //   1415: fstore #11
      //   1417: goto -> 509
      //   1420: aload #4
      //   1422: iload_2
      //   1423: iconst_0
      //   1424: iadd
      //   1425: faload
      //   1426: fstore #10
      //   1428: aload #4
      //   1430: iload_2
      //   1431: iconst_1
      //   1432: iadd
      //   1433: faload
      //   1434: fstore #13
      //   1436: iload_2
      //   1437: iconst_2
      //   1438: iadd
      //   1439: istore #18
      //   1441: aload #4
      //   1443: iload #18
      //   1445: faload
      //   1446: fstore #9
      //   1448: iload_2
      //   1449: iconst_3
      //   1450: iadd
      //   1451: istore #17
      //   1453: aload #4
      //   1455: iload #17
      //   1457: faload
      //   1458: fstore #5
      //   1460: iload_2
      //   1461: iconst_4
      //   1462: iadd
      //   1463: istore #19
      //   1465: aload #4
      //   1467: iload #19
      //   1469: faload
      //   1470: fstore #14
      //   1472: iload_2
      //   1473: iconst_5
      //   1474: iadd
      //   1475: istore #16
      //   1477: aload_0
      //   1478: fload #10
      //   1480: fload #13
      //   1482: fload #9
      //   1484: fload #5
      //   1486: fload #14
      //   1488: aload #4
      //   1490: iload #16
      //   1492: faload
      //   1493: invokevirtual rCubicTo : (FFFFFF)V
      //   1496: aload #4
      //   1498: iload #18
      //   1500: faload
      //   1501: fload #11
      //   1503: fadd
      //   1504: fstore #9
      //   1506: aload #4
      //   1508: iload #17
      //   1510: faload
      //   1511: fload #12
      //   1513: fadd
      //   1514: fstore #13
      //   1516: fload #11
      //   1518: aload #4
      //   1520: iload #19
      //   1522: faload
      //   1523: fadd
      //   1524: fstore #14
      //   1526: aload #4
      //   1528: iload #16
      //   1530: faload
      //   1531: fstore #10
      //   1533: fload #9
      //   1535: fstore #11
      //   1537: fload #12
      //   1539: fload #10
      //   1541: fadd
      //   1542: fstore #12
      //   1544: fload #14
      //   1546: fstore #10
      //   1548: fload #13
      //   1550: fstore #14
      //   1552: fload #11
      //   1554: fstore #13
      //   1556: fload #10
      //   1558: fstore #11
      //   1560: goto -> 509
      //   1563: iload_2
      //   1564: iconst_5
      //   1565: iadd
      //   1566: istore #16
      //   1568: aload #4
      //   1570: iload #16
      //   1572: faload
      //   1573: fstore #14
      //   1575: iload_2
      //   1576: bipush #6
      //   1578: iadd
      //   1579: istore #17
      //   1581: aload #4
      //   1583: iload #17
      //   1585: faload
      //   1586: fstore #9
      //   1588: aload #4
      //   1590: iload_2
      //   1591: iconst_0
      //   1592: iadd
      //   1593: faload
      //   1594: fstore #5
      //   1596: aload #4
      //   1598: iload_2
      //   1599: iconst_1
      //   1600: iadd
      //   1601: faload
      //   1602: fstore #13
      //   1604: aload #4
      //   1606: iload_2
      //   1607: iconst_2
      //   1608: iadd
      //   1609: faload
      //   1610: fstore #10
      //   1612: aload #4
      //   1614: iload_2
      //   1615: iconst_3
      //   1616: iadd
      //   1617: faload
      //   1618: fconst_0
      //   1619: fcmpl
      //   1620: ifeq -> 1629
      //   1623: iconst_1
      //   1624: istore #20
      //   1626: goto -> 1632
      //   1629: iconst_0
      //   1630: istore #20
      //   1632: aload #4
      //   1634: iload_2
      //   1635: iconst_4
      //   1636: iadd
      //   1637: faload
      //   1638: fconst_0
      //   1639: fcmpl
      //   1640: ifeq -> 1649
      //   1643: iconst_1
      //   1644: istore #21
      //   1646: goto -> 1652
      //   1649: iconst_0
      //   1650: istore #21
      //   1652: aload_0
      //   1653: fload #11
      //   1655: fload #12
      //   1657: fload #14
      //   1659: fload #11
      //   1661: fadd
      //   1662: fload #9
      //   1664: fload #12
      //   1666: fadd
      //   1667: fload #5
      //   1669: fload #13
      //   1671: fload #10
      //   1673: iload #20
      //   1675: iload #21
      //   1677: invokestatic drawArc : (Landroid/graphics/Path;FFFFFFFZZ)V
      //   1680: fload #11
      //   1682: aload #4
      //   1684: iload #16
      //   1686: faload
      //   1687: fadd
      //   1688: fstore #11
      //   1690: fload #12
      //   1692: aload #4
      //   1694: iload #17
      //   1696: faload
      //   1697: fadd
      //   1698: fstore #12
      //   1700: goto -> 2094
      //   1703: iload_2
      //   1704: iconst_0
      //   1705: iadd
      //   1706: istore #17
      //   1708: aload_0
      //   1709: fload #11
      //   1711: aload #4
      //   1713: iload #17
      //   1715: faload
      //   1716: invokevirtual lineTo : (FF)V
      //   1719: aload #4
      //   1721: iload #17
      //   1723: faload
      //   1724: fstore #12
      //   1726: goto -> 2102
      //   1729: iload_2
      //   1730: istore #17
      //   1732: iload #17
      //   1734: iconst_0
      //   1735: iadd
      //   1736: istore #18
      //   1738: aload #4
      //   1740: iload #18
      //   1742: faload
      //   1743: fstore #13
      //   1745: iload #17
      //   1747: iconst_1
      //   1748: iadd
      //   1749: istore #19
      //   1751: aload #4
      //   1753: iload #19
      //   1755: faload
      //   1756: fstore #11
      //   1758: iload #17
      //   1760: iconst_2
      //   1761: iadd
      //   1762: istore #16
      //   1764: aload #4
      //   1766: iload #16
      //   1768: faload
      //   1769: fstore #12
      //   1771: iinc #17, 3
      //   1774: aload_0
      //   1775: fload #13
      //   1777: fload #11
      //   1779: fload #12
      //   1781: aload #4
      //   1783: iload #17
      //   1785: faload
      //   1786: invokevirtual quadTo : (FFFF)V
      //   1789: aload #4
      //   1791: iload #18
      //   1793: faload
      //   1794: fstore #13
      //   1796: aload #4
      //   1798: iload #19
      //   1800: faload
      //   1801: fstore #14
      //   1803: aload #4
      //   1805: iload #16
      //   1807: faload
      //   1808: fstore #11
      //   1810: aload #4
      //   1812: iload #17
      //   1814: faload
      //   1815: fstore #12
      //   1817: goto -> 2102
      //   1820: iload_2
      //   1821: iconst_0
      //   1822: iadd
      //   1823: istore #17
      //   1825: aload_0
      //   1826: aload #4
      //   1828: iload #17
      //   1830: faload
      //   1831: fload #12
      //   1833: invokevirtual lineTo : (FF)V
      //   1836: aload #4
      //   1838: iload #17
      //   1840: faload
      //   1841: fstore #11
      //   1843: goto -> 2102
      //   1846: iload_2
      //   1847: istore #17
      //   1849: aload #4
      //   1851: iload #17
      //   1853: iconst_0
      //   1854: iadd
      //   1855: faload
      //   1856: fstore #14
      //   1858: aload #4
      //   1860: iload #17
      //   1862: iconst_1
      //   1863: iadd
      //   1864: faload
      //   1865: fstore #12
      //   1867: iload #17
      //   1869: iconst_2
      //   1870: iadd
      //   1871: istore #16
      //   1873: aload #4
      //   1875: iload #16
      //   1877: faload
      //   1878: fstore #11
      //   1880: iload #17
      //   1882: iconst_3
      //   1883: iadd
      //   1884: istore #18
      //   1886: aload #4
      //   1888: iload #18
      //   1890: faload
      //   1891: fstore #13
      //   1893: iload #17
      //   1895: iconst_4
      //   1896: iadd
      //   1897: istore #19
      //   1899: aload #4
      //   1901: iload #19
      //   1903: faload
      //   1904: fstore #10
      //   1906: iinc #17, 5
      //   1909: aload_0
      //   1910: fload #14
      //   1912: fload #12
      //   1914: fload #11
      //   1916: fload #13
      //   1918: fload #10
      //   1920: aload #4
      //   1922: iload #17
      //   1924: faload
      //   1925: invokevirtual cubicTo : (FFFFFF)V
      //   1928: aload #4
      //   1930: iload #19
      //   1932: faload
      //   1933: fstore #11
      //   1935: aload #4
      //   1937: iload #17
      //   1939: faload
      //   1940: fstore #12
      //   1942: aload #4
      //   1944: iload #16
      //   1946: faload
      //   1947: fstore #13
      //   1949: aload #4
      //   1951: iload #18
      //   1953: faload
      //   1954: fstore #14
      //   1956: goto -> 2102
      //   1959: iload_2
      //   1960: istore #17
      //   1962: iload #17
      //   1964: iconst_5
      //   1965: iadd
      //   1966: istore #16
      //   1968: aload #4
      //   1970: iload #16
      //   1972: faload
      //   1973: fstore #13
      //   1975: iload #17
      //   1977: bipush #6
      //   1979: iadd
      //   1980: istore #18
      //   1982: aload #4
      //   1984: iload #18
      //   1986: faload
      //   1987: fstore #5
      //   1989: aload #4
      //   1991: iload #17
      //   1993: iconst_0
      //   1994: iadd
      //   1995: faload
      //   1996: fstore #10
      //   1998: aload #4
      //   2000: iload #17
      //   2002: iconst_1
      //   2003: iadd
      //   2004: faload
      //   2005: fstore #9
      //   2007: aload #4
      //   2009: iload #17
      //   2011: iconst_2
      //   2012: iadd
      //   2013: faload
      //   2014: fstore #14
      //   2016: aload #4
      //   2018: iload #17
      //   2020: iconst_3
      //   2021: iadd
      //   2022: faload
      //   2023: fconst_0
      //   2024: fcmpl
      //   2025: ifeq -> 2034
      //   2028: iconst_1
      //   2029: istore #20
      //   2031: goto -> 2037
      //   2034: iconst_0
      //   2035: istore #20
      //   2037: aload #4
      //   2039: iload #17
      //   2041: iconst_4
      //   2042: iadd
      //   2043: faload
      //   2044: fconst_0
      //   2045: fcmpl
      //   2046: ifeq -> 2055
      //   2049: iconst_1
      //   2050: istore #21
      //   2052: goto -> 2058
      //   2055: iconst_0
      //   2056: istore #21
      //   2058: aload_0
      //   2059: fload #11
      //   2061: fload #12
      //   2063: fload #13
      //   2065: fload #5
      //   2067: fload #10
      //   2069: fload #9
      //   2071: fload #14
      //   2073: iload #20
      //   2075: iload #21
      //   2077: invokestatic drawArc : (Landroid/graphics/Path;FFFFFFFZZ)V
      //   2080: aload #4
      //   2082: iload #16
      //   2084: faload
      //   2085: fstore #11
      //   2087: aload #4
      //   2089: iload #18
      //   2091: faload
      //   2092: fstore #12
      //   2094: fload #12
      //   2096: fstore #14
      //   2098: fload #11
      //   2100: fstore #13
      //   2102: iload_2
      //   2103: iload #15
      //   2105: iadd
      //   2106: istore_2
      //   2107: iload_3
      //   2108: istore #17
      //   2110: goto -> 373
      //   2113: aload_1
      //   2114: iconst_0
      //   2115: fload #11
      //   2117: fastore
      //   2118: aload_1
      //   2119: iconst_1
      //   2120: fload #12
      //   2122: fastore
      //   2123: aload_1
      //   2124: iconst_2
      //   2125: fload #13
      //   2127: fastore
      //   2128: aload_1
      //   2129: iconst_3
      //   2130: fload #14
      //   2132: fastore
      //   2133: aload_1
      //   2134: iconst_4
      //   2135: fload #6
      //   2137: fastore
      //   2138: aload_1
      //   2139: iconst_5
      //   2140: fload #7
      //   2142: fastore
      //   2143: return
    }
    
    private static void arcToBezier(Path param1Path, double param1Double1, double param1Double2, double param1Double3, double param1Double4, double param1Double5, double param1Double6, double param1Double7, double param1Double8, double param1Double9) {
      int i = (int)Math.ceil(Math.abs(param1Double9 * 4.0D / Math.PI));
      double d1 = Math.cos(param1Double7);
      param1Double7 = Math.sin(param1Double7);
      double d2 = Math.cos(param1Double8);
      double d3 = Math.sin(param1Double8);
      double d4 = -param1Double3;
      double d5 = d4 * d1;
      double d6 = param1Double4 * param1Double7;
      double d7 = d4 * param1Double7;
      double d8 = param1Double4 * d1;
      param1Double4 = i;
      Double.isNaN(param1Double4);
      double d9 = param1Double9 / param1Double4;
      param1Double9 = d3 * d7 + d2 * d8;
      d2 = d5 * d3 - d6 * d2;
      byte b = 0;
      d4 = param1Double8;
      d3 = param1Double6;
      param1Double4 = d7;
      param1Double6 = param1Double5;
      param1Double8 = d9;
      param1Double5 = d1;
      while (true) {
        d1 = param1Double3;
        if (b < i) {
          d9 = d4 + param1Double8;
          double d10 = Math.sin(d9);
          double d11 = Math.cos(d9);
          double d12 = param1Double1 + d1 * param1Double5 * d11 - d6 * d10;
          d1 = param1Double2 + d1 * param1Double7 * d11 + d8 * d10;
          d7 = d5 * d10 - d6 * d11;
          d11 = d10 * param1Double4 + d11 * d8;
          d4 = d9 - d4;
          d10 = Math.tan(d4 / 2.0D);
          d4 = Math.sin(d4) * (Math.sqrt(d10 * 3.0D * d10 + 4.0D) - 1.0D) / 3.0D;
          param1Path.rLineTo(0.0F, 0.0F);
          param1Path.cubicTo((float)(param1Double6 + d2 * d4), (float)(d3 + param1Double9 * d4), (float)(d12 - d4 * d7), (float)(d1 - d4 * d11), (float)d12, (float)d1);
          b++;
          param1Double6 = d12;
          d4 = d9;
          param1Double9 = d11;
          d2 = d7;
          d3 = d1;
          continue;
        } 
        break;
      } 
    }
    
    private static void drawArc(Path param1Path, float param1Float1, float param1Float2, float param1Float3, float param1Float4, float param1Float5, float param1Float6, float param1Float7, boolean param1Boolean1, boolean param1Boolean2) {
      double d1 = Math.toRadians(param1Float7);
      double d2 = Math.cos(d1);
      double d3 = Math.sin(d1);
      double d4 = param1Float1;
      Double.isNaN(d4);
      double d5 = param1Float2;
      Double.isNaN(d5);
      double d6 = param1Float5;
      Double.isNaN(d6);
      double d7 = (d4 * d2 + d5 * d3) / d6;
      double d8 = -param1Float1;
      Double.isNaN(d8);
      Double.isNaN(d5);
      double d9 = param1Float6;
      Double.isNaN(d9);
      double d10 = (d8 * d3 + d5 * d2) / d9;
      double d11 = param1Float3;
      Double.isNaN(d11);
      d8 = param1Float4;
      Double.isNaN(d8);
      Double.isNaN(d6);
      double d12 = (d11 * d2 + d8 * d3) / d6;
      d11 = -param1Float3;
      Double.isNaN(d11);
      Double.isNaN(d8);
      Double.isNaN(d9);
      double d13 = (d11 * d3 + d8 * d2) / d9;
      double d14 = d7 - d12;
      double d15 = d10 - d13;
      d11 = (d7 + d12) / 2.0D;
      d8 = (d10 + d13) / 2.0D;
      double d16 = d14 * d14 + d15 * d15;
      if (d16 == 0.0D) {
        Log.w("PathParser", " Points are coincident");
        return;
      } 
      double d17 = 1.0D / d16 - 0.25D;
      if (d17 < 0.0D) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Points are too far apart ");
        stringBuilder.append(d16);
        Log.w("PathParser", stringBuilder.toString());
        float f = (float)(Math.sqrt(d16) / 1.99999D);
        drawArc(param1Path, param1Float1, param1Float2, param1Float3, param1Float4, param1Float5 * f, param1Float6 * f, param1Float7, param1Boolean1, param1Boolean2);
        return;
      } 
      d16 = Math.sqrt(d17);
      d14 *= d16;
      d15 = d16 * d15;
      if (param1Boolean1 == param1Boolean2) {
        d11 -= d15;
        d8 += d14;
      } else {
        d11 += d15;
        d8 -= d14;
      } 
      d14 = Math.atan2(d10 - d8, d7 - d11);
      d10 = Math.atan2(d13 - d8, d12 - d11) - d14;
      if (d10 >= 0.0D) {
        param1Boolean1 = true;
      } else {
        param1Boolean1 = false;
      } 
      d7 = d10;
      if (param1Boolean2 != param1Boolean1)
        if (d10 > 0.0D) {
          d7 = d10 - 6.283185307179586D;
        } else {
          d7 = d10 + 6.283185307179586D;
        }  
      Double.isNaN(d6);
      d11 *= d6;
      Double.isNaN(d9);
      d8 *= d9;
      arcToBezier(param1Path, d11 * d2 - d8 * d3, d11 * d3 + d8 * d2, d6, d9, d4, d5, d1, d14, d7);
    }
    
    public static void nodesToPath(PathDataNode[] param1ArrayOfPathDataNode, Path param1Path) {
      float[] arrayOfFloat = new float[6];
      char c1 = 'm';
      byte b = 0;
      char c2;
      for (c2 = c1; b < param1ArrayOfPathDataNode.length; c2 = c1) {
        PathDataNode pathDataNode = param1ArrayOfPathDataNode[b];
        addCommand(param1Path, arrayOfFloat, c2, pathDataNode.mType, pathDataNode.mParams);
        c1 = (param1ArrayOfPathDataNode[b]).mType;
        b++;
      } 
    }
    
    public void interpolatePathDataNode(PathDataNode param1PathDataNode1, PathDataNode param1PathDataNode2, float param1Float) {
      byte b = 0;
      while (true) {
        float[] arrayOfFloat = param1PathDataNode1.mParams;
        if (b < arrayOfFloat.length) {
          this.mParams[b] = arrayOfFloat[b] * (1.0F - param1Float) + param1PathDataNode2.mParams[b] * param1Float;
          b++;
          continue;
        } 
        break;
      } 
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/graphics/PathParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */