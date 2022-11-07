package com.google.android.gms.internal;

import java.io.PrintStream;
import java.io.PrintWriter;

public final class zzdjq {
  private static zzdjr zzlhx;
  
  static {
    zza zza;
    try {
    
    } finally {
      Exception exception = null;
      PrintStream printStream = System.err;
      String str = zza.class.getName();
      StringBuilder stringBuilder = new StringBuilder(str.length() + 132);
      stringBuilder.append("An error has occured when initializing the try-with-resources desuguring strategy. The default strategy ");
      stringBuilder.append(str);
      stringBuilder.append("will be used. The error is: ");
      printStream.println(stringBuilder.toString());
      exception.printStackTrace(System.err);
    } 
    zzlhx = zza;
  }
  
  public static void zza(Throwable paramThrowable, PrintStream paramPrintStream) {
    zzlhx.zza(paramThrowable, paramPrintStream);
  }
  
  public static void zza(Throwable paramThrowable, PrintWriter paramPrintWriter) {
    zzlhx.zza(paramThrowable, paramPrintWriter);
  }
  
  private static Integer zzbnu() {
    try {
      return (Integer)Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null);
    } catch (Exception exception) {
      System.err.println("Failed to retrieve value from android.os.Build$VERSION.SDK_INT due to the following exception.");
      exception.printStackTrace(System.err);
      return null;
    } 
  }
  
  public static void zzd(Throwable paramThrowable) {
    zzlhx.zzd(paramThrowable);
  }
  
  static final class zza extends zzdjr {
    public final void zza(Throwable param1Throwable, PrintStream param1PrintStream) {
      param1Throwable.printStackTrace(param1PrintStream);
    }
    
    public final void zza(Throwable param1Throwable, PrintWriter param1PrintWriter) {
      param1Throwable.printStackTrace(param1PrintWriter);
    }
    
    public final void zzd(Throwable param1Throwable) {
      param1Throwable.printStackTrace();
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzdjq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */