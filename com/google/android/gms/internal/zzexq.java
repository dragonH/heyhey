package com.google.android.gms.internal;

final class zzexq extends zzexp {
  final int zzb(int paramInt1, byte[] paramArrayOfbyte, int paramInt2, int paramInt3) {
    while (paramInt2 < paramInt3 && paramArrayOfbyte[paramInt2] >= 0)
      paramInt2++; 
    paramInt1 = paramInt2;
    if (paramInt2 >= paramInt3)
      return 0; 
    while (true) {
      if (paramInt1 >= paramInt3)
        return 0; 
      paramInt2 = paramInt1 + 1;
      byte b = paramArrayOfbyte[paramInt1];
      paramInt1 = paramInt2;
      if (b < 0) {
        if (b < -32) {
          if (paramInt2 >= paramInt3)
            return b; 
          if (b >= -62) {
            paramInt1 = paramInt2 + 1;
            if (paramArrayOfbyte[paramInt2] > -65)
              return -1; 
            continue;
          } 
          return -1;
        } 
        if (b < -16) {
          if (paramInt2 >= paramInt3 - 1)
            return zzexo.zzl(paramArrayOfbyte, paramInt2, paramInt3); 
          int i = paramInt2 + 1;
          paramInt1 = paramArrayOfbyte[paramInt2];
          if (paramInt1 <= -65 && (b != -32 || paramInt1 >= -96) && (b != -19 || paramInt1 < -96)) {
            paramInt1 = i + 1;
            if (paramArrayOfbyte[i] > -65)
              return -1; 
            continue;
          } 
          return -1;
        } 
        if (paramInt2 >= paramInt3 - 2)
          return zzexo.zzl(paramArrayOfbyte, paramInt2, paramInt3); 
        paramInt1 = paramInt2 + 1;
        paramInt2 = paramArrayOfbyte[paramInt2];
        if (paramInt2 <= -65 && (b << 28) + paramInt2 + 112 >> 30 == 0) {
          paramInt2 = paramInt1 + 1;
          if (paramArrayOfbyte[paramInt1] <= -65) {
            paramInt1 = paramInt2 + 1;
            if (paramArrayOfbyte[paramInt2] > -65)
              break; 
            continue;
          } 
        } 
        break;
      } 
    } 
    return -1;
  }
  
  final int zzb(CharSequence paramCharSequence, byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    int i = paramCharSequence.length();
    int j = paramInt2 + paramInt1;
    paramInt2 = 0;
    while (paramInt2 < i) {
      int m = paramInt2 + paramInt1;
      if (m < j) {
        char c = paramCharSequence.charAt(paramInt2);
        if (c < '') {
          paramArrayOfbyte[m] = (byte)(byte)c;
          paramInt2++;
        } 
      } 
    } 
    if (paramInt2 == i)
      return paramInt1 + i; 
    int k = paramInt1 + paramInt2;
    paramInt1 = paramInt2;
    while (paramInt1 < i) {
      char c = paramCharSequence.charAt(paramInt1);
      if (c < '' && k < j) {
        paramInt2 = k + 1;
        paramArrayOfbyte[k] = (byte)(byte)c;
      } else if (c < 'ࠀ' && k <= j - 2) {
        int m = k + 1;
        paramArrayOfbyte[k] = (byte)(byte)(c >>> 6 | 0x3C0);
        paramInt2 = m + 1;
        paramArrayOfbyte[m] = (byte)(byte)(c & 0x3F | 0x80);
      } else if ((c < '?' || '?' < c) && k <= j - 3) {
        paramInt2 = k + 1;
        paramArrayOfbyte[k] = (byte)(byte)(c >>> 12 | 0x1E0);
        k = paramInt2 + 1;
        paramArrayOfbyte[paramInt2] = (byte)(byte)(c >>> 6 & 0x3F | 0x80);
        paramInt2 = k + 1;
        paramArrayOfbyte[k] = (byte)(byte)(c & 0x3F | 0x80);
      } else if (k <= j - 4) {
        paramInt2 = paramInt1 + 1;
        if (paramInt2 != paramCharSequence.length()) {
          char c1 = paramCharSequence.charAt(paramInt2);
          if (Character.isSurrogatePair(c, c1)) {
            paramInt1 = Character.toCodePoint(c, c1);
            int m = k + 1;
            paramArrayOfbyte[k] = (byte)(byte)(paramInt1 >>> 18 | 0xF0);
            k = m + 1;
            paramArrayOfbyte[m] = (byte)(byte)(paramInt1 >>> 12 & 0x3F | 0x80);
            m = k + 1;
            paramArrayOfbyte[k] = (byte)(byte)(paramInt1 >>> 6 & 0x3F | 0x80);
            k = m + 1;
            paramArrayOfbyte[m] = (byte)(byte)(paramInt1 & 0x3F | 0x80);
            paramInt1 = paramInt2;
            paramInt2 = k;
          } else {
            paramInt1 = paramInt2;
            throw new zzexr(paramInt1 - 1, i);
          } 
        } else {
          throw new zzexr(paramInt1 - 1, i);
        } 
      } else {
        if ('?' <= c && c <= '?') {
          paramInt2 = paramInt1 + 1;
          if (paramInt2 == paramCharSequence.length() || !Character.isSurrogatePair(c, paramCharSequence.charAt(paramInt2)))
            throw new zzexr(paramInt1, i); 
        } 
        paramCharSequence = new StringBuilder(37);
        paramCharSequence.append("Failed writing ");
        paramCharSequence.append(c);
        paramCharSequence.append(" at index ");
        paramCharSequence.append(k);
        throw new ArrayIndexOutOfBoundsException(paramCharSequence.toString());
      } 
      paramInt1++;
      k = paramInt2;
    } 
    return k;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzexq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */