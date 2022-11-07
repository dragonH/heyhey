package com.google.android.gms.internal;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

final class zzexm {
  private static final Logger logger;
  
  private static final Unsafe zzlal;
  
  private static final boolean zzonr;
  
  private static final Class<?> zzorb;
  
  private static final boolean zzorc;
  
  private static final boolean zzord;
  
  private static final boolean zzore;
  
  private static final zzd zzorf;
  
  private static final boolean zzorg;
  
  private static final long zzorh;
  
  private static final long zzori;
  
  private static final long zzorj;
  
  private static final long zzork;
  
  private static final long zzorl;
  
  private static final long zzorm;
  
  private static final long zzorn;
  
  private static final long zzoro;
  
  private static final long zzorp;
  
  private static final long zzorq;
  
  private static final long zzorr;
  
  private static final long zzors;
  
  private static final long zzort;
  
  private static final long zzoru;
  
  private static final boolean zzorv;
  
  static {
    // Byte code:
    //   0: ldc com/google/android/gms/internal/zzexm
    //   2: invokevirtual getName : ()Ljava/lang/String;
    //   5: invokestatic getLogger : (Ljava/lang/String;)Ljava/util/logging/Logger;
    //   8: putstatic com/google/android/gms/internal/zzexm.logger : Ljava/util/logging/Logger;
    //   11: invokestatic zzcvt : ()Lsun/misc/Unsafe;
    //   14: astore_0
    //   15: aload_0
    //   16: putstatic com/google/android/gms/internal/zzexm.zzlal : Lsun/misc/Unsafe;
    //   19: ldc 'libcore.io.Memory'
    //   21: invokestatic zztn : (Ljava/lang/String;)Ljava/lang/Class;
    //   24: putstatic com/google/android/gms/internal/zzexm.zzorb : Ljava/lang/Class;
    //   27: ldc 'org.robolectric.Robolectric'
    //   29: invokestatic zztn : (Ljava/lang/String;)Ljava/lang/Class;
    //   32: astore_1
    //   33: iconst_1
    //   34: istore_2
    //   35: aload_1
    //   36: ifnull -> 44
    //   39: iconst_1
    //   40: istore_3
    //   41: goto -> 46
    //   44: iconst_0
    //   45: istore_3
    //   46: iload_3
    //   47: putstatic com/google/android/gms/internal/zzexm.zzorc : Z
    //   50: getstatic java/lang/Long.TYPE : Ljava/lang/Class;
    //   53: invokestatic zzk : (Ljava/lang/Class;)Z
    //   56: istore #4
    //   58: iload #4
    //   60: putstatic com/google/android/gms/internal/zzexm.zzord : Z
    //   63: getstatic java/lang/Integer.TYPE : Ljava/lang/Class;
    //   66: invokestatic zzk : (Ljava/lang/Class;)Z
    //   69: istore_3
    //   70: iload_3
    //   71: putstatic com/google/android/gms/internal/zzexm.zzore : Z
    //   74: aconst_null
    //   75: astore_1
    //   76: aload_0
    //   77: ifnonnull -> 83
    //   80: goto -> 131
    //   83: invokestatic zzcvw : ()Z
    //   86: ifeq -> 122
    //   89: iload #4
    //   91: ifeq -> 106
    //   94: new com/google/android/gms/internal/zzexm$zzb
    //   97: dup
    //   98: aload_0
    //   99: invokespecial <init> : (Lsun/misc/Unsafe;)V
    //   102: astore_1
    //   103: goto -> 131
    //   106: iload_3
    //   107: ifeq -> 131
    //   110: new com/google/android/gms/internal/zzexm$zza
    //   113: dup
    //   114: aload_0
    //   115: invokespecial <init> : (Lsun/misc/Unsafe;)V
    //   118: astore_1
    //   119: goto -> 131
    //   122: new com/google/android/gms/internal/zzexm$zzc
    //   125: dup
    //   126: aload_0
    //   127: invokespecial <init> : (Lsun/misc/Unsafe;)V
    //   130: astore_1
    //   131: aload_1
    //   132: putstatic com/google/android/gms/internal/zzexm.zzorf : Lcom/google/android/gms/internal/zzexm$zzd;
    //   135: invokestatic zzcvv : ()Z
    //   138: putstatic com/google/android/gms/internal/zzexm.zzorg : Z
    //   141: invokestatic zzcvu : ()Z
    //   144: putstatic com/google/android/gms/internal/zzexm.zzonr : Z
    //   147: ldc [B
    //   149: invokestatic zzi : (Ljava/lang/Class;)I
    //   152: i2l
    //   153: putstatic com/google/android/gms/internal/zzexm.zzorh : J
    //   156: ldc [Z
    //   158: invokestatic zzi : (Ljava/lang/Class;)I
    //   161: i2l
    //   162: putstatic com/google/android/gms/internal/zzexm.zzori : J
    //   165: ldc [Z
    //   167: invokestatic zzj : (Ljava/lang/Class;)I
    //   170: i2l
    //   171: putstatic com/google/android/gms/internal/zzexm.zzorj : J
    //   174: ldc [I
    //   176: invokestatic zzi : (Ljava/lang/Class;)I
    //   179: i2l
    //   180: putstatic com/google/android/gms/internal/zzexm.zzork : J
    //   183: ldc [I
    //   185: invokestatic zzj : (Ljava/lang/Class;)I
    //   188: i2l
    //   189: putstatic com/google/android/gms/internal/zzexm.zzorl : J
    //   192: ldc [J
    //   194: invokestatic zzi : (Ljava/lang/Class;)I
    //   197: i2l
    //   198: putstatic com/google/android/gms/internal/zzexm.zzorm : J
    //   201: ldc [J
    //   203: invokestatic zzj : (Ljava/lang/Class;)I
    //   206: i2l
    //   207: putstatic com/google/android/gms/internal/zzexm.zzorn : J
    //   210: ldc [F
    //   212: invokestatic zzi : (Ljava/lang/Class;)I
    //   215: i2l
    //   216: putstatic com/google/android/gms/internal/zzexm.zzoro : J
    //   219: ldc [F
    //   221: invokestatic zzj : (Ljava/lang/Class;)I
    //   224: i2l
    //   225: putstatic com/google/android/gms/internal/zzexm.zzorp : J
    //   228: ldc [D
    //   230: invokestatic zzi : (Ljava/lang/Class;)I
    //   233: i2l
    //   234: putstatic com/google/android/gms/internal/zzexm.zzorq : J
    //   237: ldc [D
    //   239: invokestatic zzj : (Ljava/lang/Class;)I
    //   242: i2l
    //   243: putstatic com/google/android/gms/internal/zzexm.zzorr : J
    //   246: ldc [Ljava/lang/Object;
    //   248: invokestatic zzi : (Ljava/lang/Class;)I
    //   251: i2l
    //   252: putstatic com/google/android/gms/internal/zzexm.zzors : J
    //   255: ldc [Ljava/lang/Object;
    //   257: invokestatic zzj : (Ljava/lang/Class;)I
    //   260: i2l
    //   261: putstatic com/google/android/gms/internal/zzexm.zzort : J
    //   264: invokestatic zzcvw : ()Z
    //   267: ifeq -> 285
    //   270: ldc java/nio/Buffer
    //   272: ldc 'effectiveDirectAddress'
    //   274: invokestatic zza : (Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   277: astore_0
    //   278: aload_0
    //   279: ifnull -> 285
    //   282: goto -> 293
    //   285: ldc java/nio/Buffer
    //   287: ldc 'address'
    //   289: invokestatic zza : (Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   292: astore_0
    //   293: aload_0
    //   294: ifnull -> 317
    //   297: aload_1
    //   298: ifnonnull -> 304
    //   301: goto -> 317
    //   304: aload_1
    //   305: getfield zzorw : Lsun/misc/Unsafe;
    //   308: aload_0
    //   309: invokevirtual objectFieldOffset : (Ljava/lang/reflect/Field;)J
    //   312: lstore #5
    //   314: goto -> 322
    //   317: ldc2_w -1
    //   320: lstore #5
    //   322: lload #5
    //   324: putstatic com/google/android/gms/internal/zzexm.zzoru : J
    //   327: invokestatic nativeOrder : ()Ljava/nio/ByteOrder;
    //   330: getstatic java/nio/ByteOrder.BIG_ENDIAN : Ljava/nio/ByteOrder;
    //   333: if_acmpne -> 341
    //   336: iload_2
    //   337: istore_3
    //   338: goto -> 343
    //   341: iconst_0
    //   342: istore_3
    //   343: iload_3
    //   344: putstatic com/google/android/gms/internal/zzexm.zzorv : Z
    //   347: return
  }
  
  private static int zza(Object paramObject, long paramLong) {
    return zzorf.zzorw.getInt(paramObject, paramLong);
  }
  
  private static Field zza(Class<?> paramClass, String paramString) {
    try {
      Field field = paramClass.getDeclaredField(paramString);
      field.setAccessible(true);
    } finally {
      paramClass = null;
    } 
  }
  
  private static void zza(Object paramObject, long paramLong, byte paramByte) {
    long l = 0xFFFFFFFFFFFFFFFCL & paramLong;
    int i = zza(paramObject, l);
    int j = (((int)paramLong ^ 0xFFFFFFFF) & 0x3) << 3;
    zza(paramObject, l, (0xFF & paramByte) << j | i & (255 << j ^ 0xFFFFFFFF));
  }
  
  private static void zza(Object paramObject, long paramLong, int paramInt) {
    zzorf.zzorw.putInt(paramObject, paramLong, paramInt);
  }
  
  static void zza(byte[] paramArrayOfbyte, long paramLong, byte paramByte) {
    zzorf.zze(paramArrayOfbyte, zzorh + paramLong, paramByte);
  }
  
  private static byte zzb(Object paramObject, long paramLong) {
    return (byte)(zza(paramObject, 0xFFFFFFFFFFFFFFFCL & paramLong) >>> (int)(((paramLong ^ 0xFFFFFFFFFFFFFFFFL) & 0x3L) << 3L));
  }
  
  static byte zzb(byte[] paramArrayOfbyte, long paramLong) {
    return zzorf.zzf(paramArrayOfbyte, zzorh + paramLong);
  }
  
  private static void zzb(Object paramObject, long paramLong, byte paramByte) {
    long l = 0xFFFFFFFFFFFFFFFCL & paramLong;
    int i = zza(paramObject, l);
    int j = ((int)paramLong & 0x3) << 3;
    zza(paramObject, l, (0xFF & paramByte) << j | i & (255 << j ^ 0xFFFFFFFF));
  }
  
  private static byte zzc(Object paramObject, long paramLong) {
    return (byte)(zza(paramObject, 0xFFFFFFFFFFFFFFFCL & paramLong) >>> (int)((paramLong & 0x3L) << 3L));
  }
  
  static boolean zzcvr() {
    return zzonr;
  }
  
  static boolean zzcvs() {
    return zzorg;
  }
  
  private static Unsafe zzcvt() {
    Exception exception;
    try {
      zzexn zzexn = new zzexn();
      this();
      Unsafe unsafe = AccessController.<Unsafe>doPrivileged(zzexn);
    } finally {
      exception = null;
    } 
  }
  
  private static boolean zzcvu() {
    Unsafe unsafe = zzlal;
    if (unsafe == null)
      return false; 
    try {
      Class<?> clazz = unsafe.getClass();
      clazz.getMethod("objectFieldOffset", new Class[] { Field.class });
      clazz.getMethod("arrayBaseOffset", new Class[] { Class.class });
      clazz.getMethod("arrayIndexScale", new Class[] { Class.class });
      Class<long> clazz1 = long.class;
      clazz.getMethod("getInt", new Class[] { Object.class, clazz1 });
      clazz.getMethod("putInt", new Class[] { Object.class, clazz1, int.class });
      clazz.getMethod("getLong", new Class[] { Object.class, clazz1 });
      clazz.getMethod("putLong", new Class[] { Object.class, clazz1, clazz1 });
      clazz.getMethod("getObject", new Class[] { Object.class, clazz1 });
      clazz.getMethod("putObject", new Class[] { Object.class, clazz1, Object.class });
      if (zzcvw())
        return true; 
      return true;
    } finally {
      Exception exception = null;
      Logger logger = logger;
      Level level = Level.WARNING;
      String str = String.valueOf(exception);
      StringBuilder stringBuilder = new StringBuilder(str.length() + 71);
      stringBuilder.append("platform method missing - proto runtime falling back to safer methods: ");
      stringBuilder.append(str);
      logger.logp(level, "com.google.protobuf.UnsafeUtil", "supportsUnsafeArrayOperations", stringBuilder.toString());
    } 
  }
  
  private static boolean zzcvv() {
    Unsafe unsafe = zzlal;
    if (unsafe == null)
      return false; 
    try {
      Class<?> clazz1 = unsafe.getClass();
      clazz1.getMethod("objectFieldOffset", new Class[] { Field.class });
      Class<long> clazz = long.class;
      clazz1.getMethod("getLong", new Class[] { Object.class, clazz });
      if (zzcvw())
        return true; 
      return true;
    } finally {
      Exception exception = null;
      Logger logger = logger;
      Level level = Level.WARNING;
      String str = String.valueOf(exception);
      StringBuilder stringBuilder = new StringBuilder(str.length() + 71);
      stringBuilder.append("platform method missing - proto runtime falling back to safer methods: ");
      stringBuilder.append(str);
      logger.logp(level, "com.google.protobuf.UnsafeUtil", "supportsUnsafeByteBufferOperations", stringBuilder.toString());
    } 
  }
  
  private static boolean zzcvw() {
    return (zzorb != null && !zzorc);
  }
  
  private static int zzi(Class<?> paramClass) {
    return zzonr ? zzorf.zzorw.arrayBaseOffset(paramClass) : -1;
  }
  
  private static int zzj(Class<?> paramClass) {
    return zzonr ? zzorf.zzorw.arrayIndexScale(paramClass) : -1;
  }
  
  private static boolean zzk(Class<?> paramClass) {
    if (!zzcvw())
      return false; 
    try {
      Class<?> clazz = zzorb;
      Class<boolean> clazz1 = boolean.class;
      clazz.getMethod("peekLong", new Class[] { paramClass, clazz1 });
      clazz.getMethod("pokeLong", new Class[] { paramClass, long.class, clazz1 });
      Class<int> clazz2 = int.class;
      clazz.getMethod("pokeInt", new Class[] { paramClass, clazz2, clazz1 });
      clazz.getMethod("peekInt", new Class[] { paramClass, clazz1 });
      clazz.getMethod("pokeByte", new Class[] { paramClass, byte.class });
      clazz.getMethod("peekByte", new Class[] { paramClass });
      clazz.getMethod("pokeByteArray", new Class[] { paramClass, byte[].class, clazz2, clazz2 });
      return true;
    } finally {
      paramClass = null;
    } 
  }
  
  private static <T> Class<T> zztn(String paramString) {
    try {
      return (Class)Class.forName(paramString);
    } finally {
      paramString = null;
    } 
  }
  
  static final class zza extends zzd {
    zza(Unsafe param1Unsafe) {
      super(param1Unsafe);
    }
    
    public final void zze(Object param1Object, long param1Long, byte param1Byte) {
      if (zzexm.zzcto()) {
        zzexm.zzc(param1Object, param1Long, param1Byte);
        return;
      } 
      zzexm.zzd(param1Object, param1Long, param1Byte);
    }
    
    public final byte zzf(Object param1Object, long param1Long) {
      return zzexm.zzcto() ? zzexm.zzd(param1Object, param1Long) : zzexm.zze(param1Object, param1Long);
    }
  }
  
  static final class zzb extends zzd {
    zzb(Unsafe param1Unsafe) {
      super(param1Unsafe);
    }
    
    public final void zze(Object param1Object, long param1Long, byte param1Byte) {
      if (zzexm.zzcto()) {
        zzexm.zzc(param1Object, param1Long, param1Byte);
        return;
      } 
      zzexm.zzd(param1Object, param1Long, param1Byte);
    }
    
    public final byte zzf(Object param1Object, long param1Long) {
      return zzexm.zzcto() ? zzexm.zzd(param1Object, param1Long) : zzexm.zze(param1Object, param1Long);
    }
  }
  
  static final class zzc extends zzd {
    zzc(Unsafe param1Unsafe) {
      super(param1Unsafe);
    }
    
    public final void zze(Object param1Object, long param1Long, byte param1Byte) {
      this.zzorw.putByte(param1Object, param1Long, param1Byte);
    }
    
    public final byte zzf(Object param1Object, long param1Long) {
      return this.zzorw.getByte(param1Object, param1Long);
    }
  }
  
  static abstract class zzd {
    Unsafe zzorw;
    
    zzd(Unsafe param1Unsafe) {
      this.zzorw = param1Unsafe;
    }
    
    public abstract void zze(Object param1Object, long param1Long, byte param1Byte);
    
    public abstract byte zzf(Object param1Object, long param1Long);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzexm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */