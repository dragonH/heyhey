package com.google.android.gms.common.util;

public final class zzl {
  public static String zza(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: ifnull -> 196
    //   4: aload_0
    //   5: arraylength
    //   6: ifeq -> 196
    //   9: iload_2
    //   10: ifle -> 196
    //   13: iload_2
    //   14: aload_0
    //   15: arraylength
    //   16: if_icmple -> 22
    //   19: goto -> 196
    //   22: new java/lang/StringBuilder
    //   25: dup
    //   26: iload_2
    //   27: bipush #16
    //   29: iadd
    //   30: iconst_1
    //   31: isub
    //   32: bipush #16
    //   34: idiv
    //   35: bipush #57
    //   37: imul
    //   38: invokespecial <init> : (I)V
    //   41: astore #4
    //   43: iload_2
    //   44: istore #5
    //   46: iconst_0
    //   47: istore_1
    //   48: iconst_0
    //   49: istore #6
    //   51: iload #5
    //   53: ifle -> 190
    //   56: iload_1
    //   57: ifne -> 110
    //   60: iload_2
    //   61: ldc 65536
    //   63: if_icmpge -> 88
    //   66: ldc '%04X:'
    //   68: iconst_1
    //   69: anewarray java/lang/Object
    //   72: dup
    //   73: iconst_0
    //   74: iload #6
    //   76: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   79: aastore
    //   80: invokestatic format : (Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   83: astore #7
    //   85: goto -> 120
    //   88: ldc '%08X:'
    //   90: iconst_1
    //   91: anewarray java/lang/Object
    //   94: dup
    //   95: iconst_0
    //   96: iload #6
    //   98: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   101: aastore
    //   102: invokestatic format : (Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   105: astore #7
    //   107: goto -> 120
    //   110: iload_1
    //   111: bipush #8
    //   113: if_icmpne -> 128
    //   116: ldc ' -'
    //   118: astore #7
    //   120: aload #4
    //   122: aload #7
    //   124: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   127: pop
    //   128: aload #4
    //   130: ldc ' %02X'
    //   132: iconst_1
    //   133: anewarray java/lang/Object
    //   136: dup
    //   137: iconst_0
    //   138: aload_0
    //   139: iload #6
    //   141: baload
    //   142: sipush #255
    //   145: iand
    //   146: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   149: aastore
    //   150: invokestatic format : (Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   153: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   156: pop
    //   157: iinc #5, -1
    //   160: iinc #1, 1
    //   163: iload_1
    //   164: bipush #16
    //   166: if_icmpeq -> 174
    //   169: iload #5
    //   171: ifne -> 184
    //   174: aload #4
    //   176: bipush #10
    //   178: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   181: pop
    //   182: iconst_0
    //   183: istore_1
    //   184: iinc #6, 1
    //   187: goto -> 51
    //   190: aload #4
    //   192: invokevirtual toString : ()Ljava/lang/String;
    //   195: areturn
    //   196: aconst_null
    //   197: areturn
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/util/zzl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */