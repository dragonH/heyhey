package com.microsoft.appcenter.http;

import android.support.annotation.VisibleForTesting;
import java.io.EOFException;
import java.io.InterruptedIOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.RejectedExecutionException;
import java.util.regex.Pattern;

public class HttpUtils {
  private static final Pattern CONNECTION_ISSUE_PATTERN;
  
  @VisibleForTesting
  static final int MAX_CHARACTERS_DISPLAYED_FOR_SECRET = 8;
  
  private static final Class[] RECOVERABLE_EXCEPTIONS = new Class[] { EOFException.class, InterruptedIOException.class, SocketException.class, UnknownHostException.class, RejectedExecutionException.class };
  
  static {
    CONNECTION_ISSUE_PATTERN = Pattern.compile("connection (time|reset)|failure in ssl library, usually a protocol error|anchor for certification path not found");
  }
  
  public static String hideSecret(String paramString) {
    String str = paramString;
    if (paramString != null)
      if (paramString.isEmpty()) {
        str = paramString;
      } else {
        int i = paramString.length();
        int j = paramString.length();
        int k = 8;
        if (j < 8)
          k = 0; 
        k = i - k;
        char[] arrayOfChar = new char[k];
        Arrays.fill(arrayOfChar, '*');
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(new String(arrayOfChar));
        stringBuilder.append(paramString.substring(k));
        str = stringBuilder.toString();
      }  
    return str;
  }
  
  public static boolean isRecoverableError(Throwable paramThrowable) {
    boolean bool = paramThrowable instanceof HttpException;
    boolean bool1 = false;
    if (bool) {
      int j = ((HttpException)paramThrowable).getStatusCode();
      if (j >= 500 || j == 408 || j == 429)
        bool1 = true; 
      return bool1;
    } 
    Class[] arrayOfClass = RECOVERABLE_EXCEPTIONS;
    int i = arrayOfClass.length;
    byte b;
    for (b = 0; b < i; b++) {
      if (arrayOfClass[b].isAssignableFrom(paramThrowable.getClass()))
        return true; 
    } 
    Throwable throwable = paramThrowable.getCause();
    if (throwable != null) {
      arrayOfClass = RECOVERABLE_EXCEPTIONS;
      i = arrayOfClass.length;
      for (b = 0; b < i; b++) {
        if (arrayOfClass[b].isAssignableFrom(throwable.getClass()))
          return true; 
      } 
    } 
    if (paramThrowable instanceof javax.net.ssl.SSLException) {
      String str = paramThrowable.getMessage();
      if (str != null && CONNECTION_ISSUE_PATTERN.matcher(str.toLowerCase(Locale.US)).find())
        return true; 
    } 
    return false;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/http/HttpUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */