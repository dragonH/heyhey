package com.google.android.gms.internal;

final class zzexs extends zzexp {
  private static int zza(byte[] paramArrayOfbyte, int paramInt1, long paramLong, int paramInt2) {
    if (paramInt2 != 0) {
      if (paramInt2 != 1) {
        if (paramInt2 == 2)
          return zzexo.zzi(paramInt1, zzexm.zzb(paramArrayOfbyte, paramLong), zzexm.zzb(paramArrayOfbyte, paramLong + 1L)); 
        throw new AssertionError();
      } 
      return zzexo.zzah(paramInt1, zzexm.zzb(paramArrayOfbyte, paramLong));
    } 
    return zzexo.zzkz(paramInt1);
  }
  
  private static int zza(byte[] paramArrayOfbyte, long paramLong, int paramInt) {
    // Byte code:
    //   0: iload_3
    //   1: bipush #16
    //   3: if_icmpge -> 12
    //   6: iconst_0
    //   7: istore #4
    //   9: goto -> 51
    //   12: lload_1
    //   13: lstore #5
    //   15: iconst_0
    //   16: istore #4
    //   18: iload #4
    //   20: iload_3
    //   21: if_icmpge -> 48
    //   24: aload_0
    //   25: lload #5
    //   27: invokestatic zzb : ([BJ)B
    //   30: ifge -> 36
    //   33: goto -> 51
    //   36: iinc #4, 1
    //   39: lload #5
    //   41: lconst_1
    //   42: ladd
    //   43: lstore #5
    //   45: goto -> 18
    //   48: iload_3
    //   49: istore #4
    //   51: iload_3
    //   52: iload #4
    //   54: isub
    //   55: istore_3
    //   56: lload_1
    //   57: iload #4
    //   59: i2l
    //   60: ladd
    //   61: lstore_1
    //   62: iconst_0
    //   63: istore #7
    //   65: iload_3
    //   66: istore #4
    //   68: iload #7
    //   70: istore_3
    //   71: lload_1
    //   72: lstore #5
    //   74: iload #4
    //   76: ifle -> 103
    //   79: lload_1
    //   80: lconst_1
    //   81: ladd
    //   82: lstore #5
    //   84: aload_0
    //   85: lload_1
    //   86: invokestatic zzb : ([BJ)B
    //   89: istore_3
    //   90: iload_3
    //   91: iflt -> 103
    //   94: iinc #4, -1
    //   97: lload #5
    //   99: lstore_1
    //   100: goto -> 71
    //   103: iload #4
    //   105: ifne -> 110
    //   108: iconst_0
    //   109: ireturn
    //   110: iinc #4, -1
    //   113: iload_3
    //   114: bipush #-32
    //   116: if_icmpge -> 162
    //   119: iload #4
    //   121: ifne -> 126
    //   124: iload_3
    //   125: ireturn
    //   126: iinc #4, -1
    //   129: iload_3
    //   130: bipush #-62
    //   132: if_icmplt -> 160
    //   135: lload #5
    //   137: lconst_1
    //   138: ladd
    //   139: lstore_1
    //   140: iload #4
    //   142: istore_3
    //   143: aload_0
    //   144: lload #5
    //   146: invokestatic zzb : ([BJ)B
    //   149: bipush #-65
    //   151: if_icmple -> 157
    //   154: goto -> 160
    //   157: goto -> 62
    //   160: iconst_m1
    //   161: ireturn
    //   162: iload_3
    //   163: bipush #-16
    //   165: if_icmpge -> 255
    //   168: iload #4
    //   170: iconst_2
    //   171: if_icmpge -> 184
    //   174: aload_0
    //   175: iload_3
    //   176: lload #5
    //   178: iload #4
    //   180: invokestatic zza : ([BIJI)I
    //   183: ireturn
    //   184: iinc #4, -2
    //   187: lload #5
    //   189: lconst_1
    //   190: ladd
    //   191: lstore #8
    //   193: aload_0
    //   194: lload #5
    //   196: invokestatic zzb : ([BJ)B
    //   199: istore #7
    //   201: iload #7
    //   203: bipush #-65
    //   205: if_icmpgt -> 253
    //   208: iload_3
    //   209: bipush #-32
    //   211: if_icmpne -> 221
    //   214: iload #7
    //   216: bipush #-96
    //   218: if_icmplt -> 253
    //   221: iload_3
    //   222: bipush #-19
    //   224: if_icmpne -> 234
    //   227: iload #7
    //   229: bipush #-96
    //   231: if_icmpge -> 253
    //   234: lload #8
    //   236: lconst_1
    //   237: ladd
    //   238: lstore_1
    //   239: iload #4
    //   241: istore_3
    //   242: aload_0
    //   243: lload #8
    //   245: invokestatic zzb : ([BJ)B
    //   248: bipush #-65
    //   250: if_icmple -> 62
    //   253: iconst_m1
    //   254: ireturn
    //   255: iload #4
    //   257: iconst_3
    //   258: if_icmpge -> 271
    //   261: aload_0
    //   262: iload_3
    //   263: lload #5
    //   265: iload #4
    //   267: invokestatic zza : ([BIJI)I
    //   270: ireturn
    //   271: iinc #4, -3
    //   274: lload #5
    //   276: lconst_1
    //   277: ladd
    //   278: lstore_1
    //   279: aload_0
    //   280: lload #5
    //   282: invokestatic zzb : ([BJ)B
    //   285: istore #7
    //   287: iload #7
    //   289: bipush #-65
    //   291: if_icmpgt -> 344
    //   294: iload_3
    //   295: bipush #28
    //   297: ishl
    //   298: iload #7
    //   300: bipush #112
    //   302: iadd
    //   303: iadd
    //   304: bipush #30
    //   306: ishr
    //   307: ifne -> 344
    //   310: lload_1
    //   311: lconst_1
    //   312: ladd
    //   313: lstore #5
    //   315: aload_0
    //   316: lload_1
    //   317: invokestatic zzb : ([BJ)B
    //   320: bipush #-65
    //   322: if_icmpgt -> 344
    //   325: lload #5
    //   327: lconst_1
    //   328: ladd
    //   329: lstore_1
    //   330: iload #4
    //   332: istore_3
    //   333: aload_0
    //   334: lload #5
    //   336: invokestatic zzb : ([BJ)B
    //   339: bipush #-65
    //   341: if_icmple -> 157
    //   344: iconst_m1
    //   345: ireturn
  }
  
  final int zzb(int paramInt1, byte[] paramArrayOfbyte, int paramInt2, int paramInt3) {
    if ((paramInt2 | paramInt3 | paramArrayOfbyte.length - paramInt3) >= 0) {
      long l = paramInt2;
      return zza(paramArrayOfbyte, l, (int)(paramInt3 - l));
    } 
    throw new ArrayIndexOutOfBoundsException(String.format("Array length=%d, index=%d, limit=%d", new Object[] { Integer.valueOf(paramArrayOfbyte.length), Integer.valueOf(paramInt2), Integer.valueOf(paramInt3) }));
  }
  
  final int zzb(CharSequence paramCharSequence, byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    long l1 = paramInt1;
    long l2 = paramInt2 + l1;
    int i = paramCharSequence.length();
    if (i <= paramInt2 && paramArrayOfbyte.length - paramInt2 >= paramInt1) {
      long l3;
      paramInt2 = 0;
      while (true) {
        l3 = 1L;
        if (paramInt2 < i) {
          paramInt1 = paramCharSequence.charAt(paramInt2);
          if (paramInt1 < 128) {
            zzexm.zza(paramArrayOfbyte, l1, (byte)paramInt1);
            paramInt2++;
            l1 = 1L + l1;
            continue;
          } 
        } 
        break;
      } 
      paramInt1 = paramInt2;
      long l4 = l1;
      if (paramInt2 == i)
        return (int)l1; 
      while (paramInt1 < i) {
        char c1 = paramCharSequence.charAt(paramInt1);
        if (c1 < '' && l4 < l2) {
          zzexm.zza(paramArrayOfbyte, l4, (byte)c1);
          long l = l3;
          l1 = l4 + l3;
          l3 = l;
        } else if (c1 < 'ࠀ' && l4 <= l2 - 2L) {
          l1 = l4 + l3;
          zzexm.zza(paramArrayOfbyte, l4, (byte)(c1 >>> 6 | 0x3C0));
          zzexm.zza(paramArrayOfbyte, l1, (byte)(c1 & 0x3F | 0x80));
          l1 += l3;
        } else if ((c1 < '?' || '?' < c1) && l4 <= l2 - 3L) {
          l1 = l4 + l3;
          zzexm.zza(paramArrayOfbyte, l4, (byte)(c1 >>> 12 | 0x1E0));
          l3 = l1 + l3;
          zzexm.zza(paramArrayOfbyte, l1, (byte)(c1 >>> 6 & 0x3F | 0x80));
          zzexm.zza(paramArrayOfbyte, l3, (byte)(c1 & 0x3F | 0x80));
          l1 = l3 + 1L;
          l3 = 1L;
        } else if (l4 <= l2 - 4L) {
          paramInt2 = paramInt1 + 1;
          if (paramInt2 != i) {
            char c2 = paramCharSequence.charAt(paramInt2);
            if (Character.isSurrogatePair(c1, c2)) {
              paramInt1 = Character.toCodePoint(c1, c2);
              l3 = l4 + 1L;
              zzexm.zza(paramArrayOfbyte, l4, (byte)(paramInt1 >>> 18 | 0xF0));
              l1 = l3 + 1L;
              zzexm.zza(paramArrayOfbyte, l3, (byte)(paramInt1 >>> 12 & 0x3F | 0x80));
              l4 = l1 + 1L;
              zzexm.zza(paramArrayOfbyte, l1, (byte)(paramInt1 >>> 6 & 0x3F | 0x80));
              l3 = 1L;
              l1 = l4 + 1L;
              zzexm.zza(paramArrayOfbyte, l4, (byte)(paramInt1 & 0x3F | 0x80));
              paramInt1 = paramInt2;
            } else {
              paramInt1 = paramInt2;
              throw new zzexr(paramInt1 - 1, i);
            } 
          } else {
            throw new zzexr(paramInt1 - 1, i);
          } 
        } else {
          if ('?' <= c1 && c1 <= '?') {
            paramInt2 = paramInt1 + 1;
            if (paramInt2 == i || !Character.isSurrogatePair(c1, paramCharSequence.charAt(paramInt2)))
              throw new zzexr(paramInt1, i); 
          } 
          paramCharSequence = new StringBuilder(46);
          paramCharSequence.append("Failed writing ");
          paramCharSequence.append(c1);
          paramCharSequence.append(" at index ");
          paramCharSequence.append(l4);
          throw new ArrayIndexOutOfBoundsException(paramCharSequence.toString());
        } 
        paramInt1++;
        l4 = l1;
      } 
      return (int)l4;
    } 
    char c = paramCharSequence.charAt(i - 1);
    paramCharSequence = new StringBuilder(37);
    paramCharSequence.append("Failed writing ");
    paramCharSequence.append(c);
    paramCharSequence.append(" at index ");
    paramCharSequence.append(paramInt1 + paramInt2);
    ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException = new ArrayIndexOutOfBoundsException(paramCharSequence.toString());
    throw arrayIndexOutOfBoundsException;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzexs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */