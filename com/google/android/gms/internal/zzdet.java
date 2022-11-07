package com.google.android.gms.internal;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

public class zzdet {
  private static Uri CONTENT_URI = Uri.parse("content://com.google.android.gsf.gservices");
  
  private static Uri zzkxu = Uri.parse("content://com.google.android.gsf.gservices/prefix");
  
  private static Pattern zzkxv = Pattern.compile("^(1|true|t|on|yes|y)$", 2);
  
  private static Pattern zzkxw = Pattern.compile("^(0|false|f|off|no|n)$", 2);
  
  private static final AtomicBoolean zzkxx = new AtomicBoolean();
  
  private static HashMap<String, String> zzkxy;
  
  private static HashMap<String, Boolean> zzkxz = new HashMap<String, Boolean>();
  
  private static HashMap<String, Integer> zzkya = new HashMap<String, Integer>();
  
  private static HashMap<String, Long> zzkyb = new HashMap<String, Long>();
  
  private static HashMap<String, Float> zzkyc = new HashMap<String, Float>();
  
  private static Object zzkyd;
  
  private static boolean zzkye;
  
  private static String[] zzkyf = new String[0];
  
  public static long getLong(ContentResolver paramContentResolver, String paramString, long paramLong) {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic zzb : (Landroid/content/ContentResolver;)Ljava/lang/Object;
    //   4: astore #4
    //   6: getstatic com/google/android/gms/internal/zzdet.zzkyb : Ljava/util/HashMap;
    //   9: astore #5
    //   11: lconst_0
    //   12: lstore #6
    //   14: aload #5
    //   16: aload_1
    //   17: lconst_0
    //   18: invokestatic valueOf : (J)Ljava/lang/Long;
    //   21: invokestatic zza : (Ljava/util/HashMap;Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;
    //   24: checkcast java/lang/Long
    //   27: astore #5
    //   29: aload #5
    //   31: ifnull -> 40
    //   34: aload #5
    //   36: invokevirtual longValue : ()J
    //   39: lreturn
    //   40: aload_0
    //   41: aload_1
    //   42: aconst_null
    //   43: invokestatic zza : (Landroid/content/ContentResolver;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   46: astore_0
    //   47: aload_0
    //   48: ifnonnull -> 60
    //   51: lload #6
    //   53: lstore_2
    //   54: aload #5
    //   56: astore_0
    //   57: goto -> 70
    //   60: aload_0
    //   61: invokestatic parseLong : (Ljava/lang/String;)J
    //   64: lstore_2
    //   65: lload_2
    //   66: invokestatic valueOf : (J)Ljava/lang/Long;
    //   69: astore_0
    //   70: getstatic com/google/android/gms/internal/zzdet.zzkyb : Ljava/util/HashMap;
    //   73: astore #5
    //   75: ldc com/google/android/gms/internal/zzdet
    //   77: monitorenter
    //   78: aload #4
    //   80: getstatic com/google/android/gms/internal/zzdet.zzkyd : Ljava/lang/Object;
    //   83: if_acmpne -> 102
    //   86: aload #5
    //   88: aload_1
    //   89: aload_0
    //   90: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   93: pop
    //   94: getstatic com/google/android/gms/internal/zzdet.zzkxy : Ljava/util/HashMap;
    //   97: aload_1
    //   98: invokevirtual remove : (Ljava/lang/Object;)Ljava/lang/Object;
    //   101: pop
    //   102: ldc com/google/android/gms/internal/zzdet
    //   104: monitorexit
    //   105: lload_2
    //   106: lreturn
    //   107: astore_0
    //   108: ldc com/google/android/gms/internal/zzdet
    //   110: monitorexit
    //   111: aload_0
    //   112: athrow
    //   113: astore_0
    //   114: lload #6
    //   116: lstore_2
    //   117: aload #5
    //   119: astore_0
    //   120: goto -> 70
    // Exception table:
    //   from	to	target	type
    //   60	70	113	java/lang/NumberFormatException
    //   78	102	107	finally
    //   102	105	107	finally
    //   108	111	107	finally
  }
  
  private static <T> T zza(HashMap<String, T> paramHashMap, String paramString, T paramT) {
    // Byte code:
    //   0: ldc com/google/android/gms/internal/zzdet
    //   2: monitorenter
    //   3: aload_0
    //   4: aload_1
    //   5: invokevirtual containsKey : (Ljava/lang/Object;)Z
    //   8: ifeq -> 28
    //   11: aload_0
    //   12: aload_1
    //   13: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   16: astore_0
    //   17: aload_0
    //   18: ifnull -> 23
    //   21: aload_0
    //   22: astore_2
    //   23: ldc com/google/android/gms/internal/zzdet
    //   25: monitorexit
    //   26: aload_2
    //   27: areturn
    //   28: ldc com/google/android/gms/internal/zzdet
    //   30: monitorexit
    //   31: aconst_null
    //   32: areturn
    //   33: astore_0
    //   34: ldc com/google/android/gms/internal/zzdet
    //   36: monitorexit
    //   37: aload_0
    //   38: athrow
    // Exception table:
    //   from	to	target	type
    //   3	17	33	finally
    //   23	26	33	finally
    //   28	31	33	finally
    //   34	37	33	finally
  }
  
  public static String zza(ContentResolver paramContentResolver, String paramString1, String paramString2) {
    // Byte code:
    //   0: ldc com/google/android/gms/internal/zzdet
    //   2: monitorenter
    //   3: aload_0
    //   4: invokestatic zza : (Landroid/content/ContentResolver;)V
    //   7: getstatic com/google/android/gms/internal/zzdet.zzkyd : Ljava/lang/Object;
    //   10: astore_3
    //   11: getstatic com/google/android/gms/internal/zzdet.zzkxy : Ljava/util/HashMap;
    //   14: aload_1
    //   15: invokevirtual containsKey : (Ljava/lang/Object;)Z
    //   18: istore #4
    //   20: aconst_null
    //   21: astore #5
    //   23: aconst_null
    //   24: astore_2
    //   25: aconst_null
    //   26: astore #6
    //   28: iload #4
    //   30: ifeq -> 58
    //   33: getstatic com/google/android/gms/internal/zzdet.zzkxy : Ljava/util/HashMap;
    //   36: aload_1
    //   37: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   40: checkcast java/lang/String
    //   43: astore_1
    //   44: aload #6
    //   46: astore_0
    //   47: aload_1
    //   48: ifnull -> 53
    //   51: aload_1
    //   52: astore_0
    //   53: ldc com/google/android/gms/internal/zzdet
    //   55: monitorexit
    //   56: aload_0
    //   57: areturn
    //   58: getstatic com/google/android/gms/internal/zzdet.zzkyf : [Ljava/lang/String;
    //   61: astore #6
    //   63: aload #6
    //   65: arraylength
    //   66: istore #7
    //   68: iconst_0
    //   69: istore #8
    //   71: iload #8
    //   73: iload #7
    //   75: if_icmpge -> 158
    //   78: aload_1
    //   79: aload #6
    //   81: iload #8
    //   83: aaload
    //   84: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   87: ifeq -> 152
    //   90: getstatic com/google/android/gms/internal/zzdet.zzkye : Z
    //   93: ifeq -> 105
    //   96: getstatic com/google/android/gms/internal/zzdet.zzkxy : Ljava/util/HashMap;
    //   99: invokevirtual isEmpty : ()Z
    //   102: ifeq -> 147
    //   105: aload_0
    //   106: getstatic com/google/android/gms/internal/zzdet.zzkyf : [Ljava/lang/String;
    //   109: invokestatic zzc : (Landroid/content/ContentResolver;[Ljava/lang/String;)V
    //   112: getstatic com/google/android/gms/internal/zzdet.zzkxy : Ljava/util/HashMap;
    //   115: aload_1
    //   116: invokevirtual containsKey : (Ljava/lang/Object;)Z
    //   119: ifeq -> 147
    //   122: getstatic com/google/android/gms/internal/zzdet.zzkxy : Ljava/util/HashMap;
    //   125: aload_1
    //   126: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   129: checkcast java/lang/String
    //   132: astore_1
    //   133: aload #5
    //   135: astore_0
    //   136: aload_1
    //   137: ifnull -> 142
    //   140: aload_1
    //   141: astore_0
    //   142: ldc com/google/android/gms/internal/zzdet
    //   144: monitorexit
    //   145: aload_0
    //   146: areturn
    //   147: ldc com/google/android/gms/internal/zzdet
    //   149: monitorexit
    //   150: aconst_null
    //   151: areturn
    //   152: iinc #8, 1
    //   155: goto -> 71
    //   158: ldc com/google/android/gms/internal/zzdet
    //   160: monitorexit
    //   161: aload_0
    //   162: getstatic com/google/android/gms/internal/zzdet.CONTENT_URI : Landroid/net/Uri;
    //   165: aconst_null
    //   166: aconst_null
    //   167: iconst_1
    //   168: anewarray java/lang/String
    //   171: dup
    //   172: iconst_0
    //   173: aload_1
    //   174: aastore
    //   175: aconst_null
    //   176: invokevirtual query : (Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   179: astore #6
    //   181: aload #6
    //   183: ifnull -> 254
    //   186: aload #6
    //   188: invokeinterface moveToFirst : ()Z
    //   193: ifne -> 199
    //   196: goto -> 254
    //   199: aload #6
    //   201: iconst_1
    //   202: invokeinterface getString : (I)Ljava/lang/String;
    //   207: astore #5
    //   209: aload #5
    //   211: astore_0
    //   212: aload #5
    //   214: ifnull -> 231
    //   217: aload #5
    //   219: astore_0
    //   220: aload #5
    //   222: aconst_null
    //   223: invokevirtual equals : (Ljava/lang/Object;)Z
    //   226: ifeq -> 231
    //   229: aconst_null
    //   230: astore_0
    //   231: aload_3
    //   232: aload_1
    //   233: aload_0
    //   234: invokestatic zza : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;)V
    //   237: aload_2
    //   238: astore_1
    //   239: aload_0
    //   240: ifnull -> 245
    //   243: aload_0
    //   244: astore_1
    //   245: aload #6
    //   247: invokeinterface close : ()V
    //   252: aload_1
    //   253: areturn
    //   254: aload_3
    //   255: aload_1
    //   256: aconst_null
    //   257: invokestatic zza : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;)V
    //   260: aload #6
    //   262: ifnull -> 272
    //   265: aload #6
    //   267: invokeinterface close : ()V
    //   272: aconst_null
    //   273: areturn
    //   274: astore_0
    //   275: aload #6
    //   277: ifnull -> 287
    //   280: aload #6
    //   282: invokeinterface close : ()V
    //   287: aload_0
    //   288: athrow
    //   289: astore_0
    //   290: ldc com/google/android/gms/internal/zzdet
    //   292: monitorexit
    //   293: goto -> 298
    //   296: aload_0
    //   297: athrow
    //   298: goto -> 296
    // Exception table:
    //   from	to	target	type
    //   3	20	289	finally
    //   33	44	289	finally
    //   53	56	289	finally
    //   58	68	289	finally
    //   78	105	289	finally
    //   105	133	289	finally
    //   142	145	289	finally
    //   147	150	289	finally
    //   158	161	289	finally
    //   186	196	274	finally
    //   199	209	274	finally
    //   220	229	274	finally
    //   231	237	274	finally
    //   254	260	274	finally
    //   290	293	289	finally
  }
  
  private static Map<String, String> zza(ContentResolver paramContentResolver, String... paramVarArgs) {
    Cursor cursor = paramContentResolver.query(zzkxu, null, null, paramVarArgs, null);
    null = new TreeMap<Object, Object>();
    if (cursor == null)
      return (Map)null; 
    try {
      while (cursor.moveToNext())
        null.put(cursor.getString(0), cursor.getString(1)); 
      return (Map)null;
    } finally {
      cursor.close();
    } 
  }
  
  private static void zza(ContentResolver paramContentResolver) {
    if (zzkxy == null) {
      zzkxx.set(false);
      zzkxy = new HashMap<String, String>();
      zzkyd = new Object();
      zzkye = false;
      paramContentResolver.registerContentObserver(CONTENT_URI, true, new zzdeu(null));
      return;
    } 
    if (zzkxx.getAndSet(false)) {
      zzkxy.clear();
      zzkxz.clear();
      zzkya.clear();
      zzkyb.clear();
      zzkyc.clear();
      zzkyd = new Object();
      zzkye = false;
    } 
  }
  
  private static void zza(Object paramObject, String paramString1, String paramString2) {
    // Byte code:
    //   0: ldc com/google/android/gms/internal/zzdet
    //   2: monitorenter
    //   3: aload_0
    //   4: getstatic com/google/android/gms/internal/zzdet.zzkyd : Ljava/lang/Object;
    //   7: if_acmpne -> 19
    //   10: getstatic com/google/android/gms/internal/zzdet.zzkxy : Ljava/util/HashMap;
    //   13: aload_1
    //   14: aload_2
    //   15: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   18: pop
    //   19: ldc com/google/android/gms/internal/zzdet
    //   21: monitorexit
    //   22: return
    //   23: astore_0
    //   24: ldc com/google/android/gms/internal/zzdet
    //   26: monitorexit
    //   27: aload_0
    //   28: athrow
    // Exception table:
    //   from	to	target	type
    //   3	19	23	finally
    //   19	22	23	finally
    //   24	27	23	finally
  }
  
  private static Object zzb(ContentResolver paramContentResolver) {
    // Byte code:
    //   0: ldc com/google/android/gms/internal/zzdet
    //   2: monitorenter
    //   3: aload_0
    //   4: invokestatic zza : (Landroid/content/ContentResolver;)V
    //   7: getstatic com/google/android/gms/internal/zzdet.zzkyd : Ljava/lang/Object;
    //   10: astore_0
    //   11: ldc com/google/android/gms/internal/zzdet
    //   13: monitorexit
    //   14: aload_0
    //   15: areturn
    //   16: astore_0
    //   17: ldc com/google/android/gms/internal/zzdet
    //   19: monitorexit
    //   20: aload_0
    //   21: athrow
    // Exception table:
    //   from	to	target	type
    //   3	14	16	finally
    //   17	20	16	finally
  }
  
  public static void zzb(ContentResolver paramContentResolver, String... paramVarArgs) {
    // Byte code:
    //   0: aload_1
    //   1: arraylength
    //   2: ifne -> 6
    //   5: return
    //   6: ldc com/google/android/gms/internal/zzdet
    //   8: monitorenter
    //   9: aload_0
    //   10: invokestatic zza : (Landroid/content/ContentResolver;)V
    //   13: getstatic com/google/android/gms/internal/zzdet.zzkyf : [Ljava/lang/String;
    //   16: arraylength
    //   17: aload_1
    //   18: arraylength
    //   19: iadd
    //   20: iconst_2
    //   21: ishl
    //   22: iconst_3
    //   23: idiv
    //   24: istore_2
    //   25: new java/util/HashSet
    //   28: astore_3
    //   29: aload_3
    //   30: iload_2
    //   31: iconst_1
    //   32: iadd
    //   33: invokespecial <init> : (I)V
    //   36: aload_3
    //   37: getstatic com/google/android/gms/internal/zzdet.zzkyf : [Ljava/lang/String;
    //   40: invokestatic asList : ([Ljava/lang/Object;)Ljava/util/List;
    //   43: invokevirtual addAll : (Ljava/util/Collection;)Z
    //   46: pop
    //   47: new java/util/ArrayList
    //   50: astore #4
    //   52: aload #4
    //   54: invokespecial <init> : ()V
    //   57: aload_1
    //   58: arraylength
    //   59: istore #5
    //   61: iconst_0
    //   62: istore_2
    //   63: iload_2
    //   64: iload #5
    //   66: if_icmpge -> 97
    //   69: aload_1
    //   70: iload_2
    //   71: aaload
    //   72: astore #6
    //   74: aload_3
    //   75: aload #6
    //   77: invokevirtual add : (Ljava/lang/Object;)Z
    //   80: ifeq -> 91
    //   83: aload #4
    //   85: aload #6
    //   87: invokevirtual add : (Ljava/lang/Object;)Z
    //   90: pop
    //   91: iinc #2, 1
    //   94: goto -> 63
    //   97: aload #4
    //   99: invokevirtual isEmpty : ()Z
    //   102: ifeq -> 113
    //   105: iconst_0
    //   106: anewarray java/lang/String
    //   109: astore_1
    //   110: goto -> 147
    //   113: aload_3
    //   114: aload_3
    //   115: invokevirtual size : ()I
    //   118: anewarray java/lang/String
    //   121: invokevirtual toArray : ([Ljava/lang/Object;)[Ljava/lang/Object;
    //   124: checkcast [Ljava/lang/String;
    //   127: putstatic com/google/android/gms/internal/zzdet.zzkyf : [Ljava/lang/String;
    //   130: aload #4
    //   132: aload #4
    //   134: invokevirtual size : ()I
    //   137: anewarray java/lang/String
    //   140: invokevirtual toArray : ([Ljava/lang/Object;)[Ljava/lang/Object;
    //   143: checkcast [Ljava/lang/String;
    //   146: astore_1
    //   147: getstatic com/google/android/gms/internal/zzdet.zzkye : Z
    //   150: ifeq -> 178
    //   153: getstatic com/google/android/gms/internal/zzdet.zzkxy : Ljava/util/HashMap;
    //   156: invokevirtual isEmpty : ()Z
    //   159: ifeq -> 165
    //   162: goto -> 178
    //   165: aload_1
    //   166: arraylength
    //   167: ifeq -> 185
    //   170: aload_0
    //   171: aload_1
    //   172: invokestatic zzc : (Landroid/content/ContentResolver;[Ljava/lang/String;)V
    //   175: goto -> 185
    //   178: aload_0
    //   179: getstatic com/google/android/gms/internal/zzdet.zzkyf : [Ljava/lang/String;
    //   182: invokestatic zzc : (Landroid/content/ContentResolver;[Ljava/lang/String;)V
    //   185: ldc com/google/android/gms/internal/zzdet
    //   187: monitorexit
    //   188: return
    //   189: astore_0
    //   190: ldc com/google/android/gms/internal/zzdet
    //   192: monitorexit
    //   193: goto -> 198
    //   196: aload_0
    //   197: athrow
    //   198: goto -> 196
    // Exception table:
    //   from	to	target	type
    //   9	61	189	finally
    //   74	91	189	finally
    //   97	110	189	finally
    //   113	147	189	finally
    //   147	162	189	finally
    //   165	175	189	finally
    //   178	185	189	finally
    //   185	188	189	finally
    //   190	193	189	finally
  }
  
  private static void zzc(ContentResolver paramContentResolver, String[] paramArrayOfString) {
    zzkxy.putAll(zza(paramContentResolver, paramArrayOfString));
    zzkye = true;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzdet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */