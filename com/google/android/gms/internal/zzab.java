package com.google.android.gms.internal;

import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class zzab {
  public static boolean DEBUG = Log.isLoggable("Volley", 2);
  
  private static String TAG = "Volley";
  
  public static void zza(String paramString, Object... paramVarArgs) {
    if (DEBUG)
      Log.v(TAG, zzd(paramString, paramVarArgs)); 
  }
  
  public static void zza(Throwable paramThrowable, String paramString, Object... paramVarArgs) {
    Log.e(TAG, zzd(paramString, paramVarArgs), paramThrowable);
  }
  
  public static void zzb(String paramString, Object... paramVarArgs) {
    Log.d(TAG, zzd(paramString, paramVarArgs));
  }
  
  public static void zzc(String paramString, Object... paramVarArgs) {
    Log.e(TAG, zzd(paramString, paramVarArgs));
  }
  
  private static String zzd(String paramString, Object... paramVarArgs) {
    String str;
    if (paramVarArgs != null)
      paramString = String.format(Locale.US, paramString, paramVarArgs); 
    StackTraceElement[] arrayOfStackTraceElement = (new Throwable()).fillInStackTrace().getStackTrace();
    byte b = 2;
    while (true) {
      if (b < arrayOfStackTraceElement.length) {
        if (!arrayOfStackTraceElement[b].getClass().equals(zzab.class)) {
          String str1 = arrayOfStackTraceElement[b].getClassName();
          str1 = str1.substring(str1.lastIndexOf('.') + 1);
          str1 = str1.substring(str1.lastIndexOf('$') + 1);
          String str2 = arrayOfStackTraceElement[b].getMethodName();
          StringBuilder stringBuilder = new StringBuilder(String.valueOf(str1).length() + 1 + String.valueOf(str2).length());
          stringBuilder.append(str1);
          stringBuilder.append(".");
          stringBuilder.append(str2);
          str1 = stringBuilder.toString();
          break;
        } 
        b++;
        continue;
      } 
      str = "<unknown>";
      break;
    } 
    return String.format(Locale.US, "[%d] %s: %s", new Object[] { Long.valueOf(Thread.currentThread().getId()), str, paramString });
  }
  
  static final class zza {
    public static final boolean zzbi = zzab.DEBUG;
    
    private final List<zzac> zzbj = new ArrayList<zzac>();
    
    private boolean zzbk = false;
    
    protected final void finalize() throws Throwable {
      if (!this.zzbk) {
        zzc("Request on the loose");
        zzab.zzc("Marker log finalized without finish() - uncaught exit point for request", new Object[0]);
      } 
    }
    
    public final void zza(String param1String, long param1Long) {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield zzbk : Z
      //   6: ifne -> 43
      //   9: aload_0
      //   10: getfield zzbj : Ljava/util/List;
      //   13: astore #4
      //   15: new com/google/android/gms/internal/zzac
      //   18: astore #5
      //   20: aload #5
      //   22: aload_1
      //   23: lload_2
      //   24: invokestatic elapsedRealtime : ()J
      //   27: invokespecial <init> : (Ljava/lang/String;JJ)V
      //   30: aload #4
      //   32: aload #5
      //   34: invokeinterface add : (Ljava/lang/Object;)Z
      //   39: pop
      //   40: aload_0
      //   41: monitorexit
      //   42: return
      //   43: new java/lang/IllegalStateException
      //   46: astore_1
      //   47: aload_1
      //   48: ldc 'Marker added to finished log'
      //   50: invokespecial <init> : (Ljava/lang/String;)V
      //   53: aload_1
      //   54: athrow
      //   55: astore_1
      //   56: aload_0
      //   57: monitorexit
      //   58: aload_1
      //   59: athrow
      // Exception table:
      //   from	to	target	type
      //   2	40	55	finally
      //   43	55	55	finally
    }
    
    public final void zzc(String param1String) {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: iconst_1
      //   4: putfield zzbk : Z
      //   7: aload_0
      //   8: getfield zzbj : Ljava/util/List;
      //   11: invokeinterface size : ()I
      //   16: ifne -> 24
      //   19: lconst_0
      //   20: lstore_2
      //   21: goto -> 76
      //   24: aload_0
      //   25: getfield zzbj : Ljava/util/List;
      //   28: iconst_0
      //   29: invokeinterface get : (I)Ljava/lang/Object;
      //   34: checkcast com/google/android/gms/internal/zzac
      //   37: getfield time : J
      //   40: lstore #4
      //   42: aload_0
      //   43: getfield zzbj : Ljava/util/List;
      //   46: astore #6
      //   48: aload #6
      //   50: aload #6
      //   52: invokeinterface size : ()I
      //   57: iconst_1
      //   58: isub
      //   59: invokeinterface get : (I)Ljava/lang/Object;
      //   64: checkcast com/google/android/gms/internal/zzac
      //   67: getfield time : J
      //   70: lstore_2
      //   71: lload_2
      //   72: lload #4
      //   74: lsub
      //   75: lstore_2
      //   76: lload_2
      //   77: lconst_0
      //   78: lcmp
      //   79: ifgt -> 85
      //   82: aload_0
      //   83: monitorexit
      //   84: return
      //   85: aload_0
      //   86: getfield zzbj : Ljava/util/List;
      //   89: iconst_0
      //   90: invokeinterface get : (I)Ljava/lang/Object;
      //   95: checkcast com/google/android/gms/internal/zzac
      //   98: getfield time : J
      //   101: lstore #4
      //   103: ldc '(%-4d ms) %s'
      //   105: iconst_2
      //   106: anewarray java/lang/Object
      //   109: dup
      //   110: iconst_0
      //   111: lload_2
      //   112: invokestatic valueOf : (J)Ljava/lang/Long;
      //   115: aastore
      //   116: dup
      //   117: iconst_1
      //   118: aload_1
      //   119: aastore
      //   120: invokestatic zzb : (Ljava/lang/String;[Ljava/lang/Object;)V
      //   123: aload_0
      //   124: getfield zzbj : Ljava/util/List;
      //   127: invokeinterface iterator : ()Ljava/util/Iterator;
      //   132: astore_1
      //   133: lload #4
      //   135: lstore_2
      //   136: aload_1
      //   137: invokeinterface hasNext : ()Z
      //   142: ifeq -> 207
      //   145: aload_1
      //   146: invokeinterface next : ()Ljava/lang/Object;
      //   151: checkcast com/google/android/gms/internal/zzac
      //   154: astore #6
      //   156: aload #6
      //   158: getfield time : J
      //   161: lstore #4
      //   163: ldc '(+%-4d) [%2d] %s'
      //   165: iconst_3
      //   166: anewarray java/lang/Object
      //   169: dup
      //   170: iconst_0
      //   171: lload #4
      //   173: lload_2
      //   174: lsub
      //   175: invokestatic valueOf : (J)Ljava/lang/Long;
      //   178: aastore
      //   179: dup
      //   180: iconst_1
      //   181: aload #6
      //   183: getfield zzbl : J
      //   186: invokestatic valueOf : (J)Ljava/lang/Long;
      //   189: aastore
      //   190: dup
      //   191: iconst_2
      //   192: aload #6
      //   194: getfield name : Ljava/lang/String;
      //   197: aastore
      //   198: invokestatic zzb : (Ljava/lang/String;[Ljava/lang/Object;)V
      //   201: lload #4
      //   203: lstore_2
      //   204: goto -> 136
      //   207: aload_0
      //   208: monitorexit
      //   209: return
      //   210: astore_1
      //   211: aload_0
      //   212: monitorexit
      //   213: goto -> 218
      //   216: aload_1
      //   217: athrow
      //   218: goto -> 216
      // Exception table:
      //   from	to	target	type
      //   2	19	210	finally
      //   24	71	210	finally
      //   85	133	210	finally
      //   136	201	210	finally
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzab.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */