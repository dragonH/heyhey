package com.microsoft.appcenter.utils;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtils {
  private static final char[] HEXADECIMAL_OUTPUT = "0123456789abcdef".toCharArray();
  
  @NonNull
  private static String encodeHex(@NonNull byte[] paramArrayOfbyte) {
    char[] arrayOfChar = new char[paramArrayOfbyte.length * 2];
    for (byte b = 0; b < paramArrayOfbyte.length; b++) {
      int i = paramArrayOfbyte[b] & 0xFF;
      int j = b * 2;
      char[] arrayOfChar1 = HEXADECIMAL_OUTPUT;
      arrayOfChar[j] = (char)arrayOfChar1[i >>> 4];
      arrayOfChar[j + 1] = (char)arrayOfChar1[i & 0xF];
    } 
    return new String(arrayOfChar);
  }
  
  @NonNull
  public static String sha256(@NonNull String paramString) {
    return sha256(paramString, "UTF-8");
  }
  
  @NonNull
  @VisibleForTesting
  static String sha256(@NonNull String paramString1, String paramString2) {
    try {
      MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
      messageDigest.update(paramString1.getBytes(paramString2));
      return encodeHex(messageDigest.digest());
    } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
    
    } catch (UnsupportedEncodingException unsupportedEncodingException) {}
    throw new RuntimeException(unsupportedEncodingException);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/utils/HashUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */