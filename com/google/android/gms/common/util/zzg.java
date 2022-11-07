package com.google.android.gms.common.util;

import android.database.CharArrayBuffer;
import android.text.TextUtils;

public final class zzg {
  public static void zzb(String paramString, CharArrayBuffer paramCharArrayBuffer) {
    if (TextUtils.isEmpty(paramString)) {
      paramCharArrayBuffer.sizeCopied = 0;
    } else {
      char[] arrayOfChar = paramCharArrayBuffer.data;
      if (arrayOfChar == null || arrayOfChar.length < paramString.length()) {
        paramCharArrayBuffer.data = paramString.toCharArray();
      } else {
        paramString.getChars(0, paramString.length(), paramCharArrayBuffer.data, 0);
      } 
    } 
    paramCharArrayBuffer.sizeCopied = paramString.length();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/util/zzg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */