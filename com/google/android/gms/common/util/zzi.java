package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;

public final class zzi {
  private static Boolean zzfys;
  
  private static Boolean zzfyt;
  
  private static Boolean zzfyu;
  
  private static Boolean zzfyv;
  
  private static Boolean zzfyw;
  
  public static boolean zza(Resources paramResources) {
    // Byte code:
    //   0: iconst_0
    //   1: istore_1
    //   2: aload_0
    //   3: ifnonnull -> 8
    //   6: iconst_0
    //   7: ireturn
    //   8: getstatic com/google/android/gms/common/util/zzi.zzfys : Ljava/lang/Boolean;
    //   11: ifnonnull -> 105
    //   14: aload_0
    //   15: invokevirtual getConfiguration : ()Landroid/content/res/Configuration;
    //   18: getfield screenLayout : I
    //   21: bipush #15
    //   23: iand
    //   24: iconst_3
    //   25: if_icmple -> 33
    //   28: iconst_1
    //   29: istore_2
    //   30: goto -> 35
    //   33: iconst_0
    //   34: istore_2
    //   35: iload_2
    //   36: ifne -> 96
    //   39: getstatic com/google/android/gms/common/util/zzi.zzfyt : Ljava/lang/Boolean;
    //   42: ifnonnull -> 85
    //   45: aload_0
    //   46: invokevirtual getConfiguration : ()Landroid/content/res/Configuration;
    //   49: astore_0
    //   50: aload_0
    //   51: getfield screenLayout : I
    //   54: bipush #15
    //   56: iand
    //   57: iconst_3
    //   58: if_icmpgt -> 76
    //   61: aload_0
    //   62: getfield smallestScreenWidthDp : I
    //   65: sipush #600
    //   68: if_icmplt -> 76
    //   71: iconst_1
    //   72: istore_3
    //   73: goto -> 78
    //   76: iconst_0
    //   77: istore_3
    //   78: iload_3
    //   79: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   82: putstatic com/google/android/gms/common/util/zzi.zzfyt : Ljava/lang/Boolean;
    //   85: iload_1
    //   86: istore_3
    //   87: getstatic com/google/android/gms/common/util/zzi.zzfyt : Ljava/lang/Boolean;
    //   90: invokevirtual booleanValue : ()Z
    //   93: ifeq -> 98
    //   96: iconst_1
    //   97: istore_3
    //   98: iload_3
    //   99: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   102: putstatic com/google/android/gms/common/util/zzi.zzfys : Ljava/lang/Boolean;
    //   105: getstatic com/google/android/gms/common/util/zzi.zzfys : Ljava/lang/Boolean;
    //   108: invokevirtual booleanValue : ()Z
    //   111: ireturn
  }
  
  @TargetApi(20)
  public static boolean zzci(Context paramContext) {
    if (zzfyu == null) {
      boolean bool;
      if (zzq.zzali() && paramContext.getPackageManager().hasSystemFeature("android.hardware.type.watch")) {
        bool = true;
      } else {
        bool = false;
      } 
      zzfyu = Boolean.valueOf(bool);
    } 
    return zzfyu.booleanValue();
  }
  
  @TargetApi(24)
  public static boolean zzcj(Context paramContext) {
    return ((!zzq.isAtLeastN() || zzck(paramContext)) && zzci(paramContext));
  }
  
  @TargetApi(21)
  public static boolean zzck(Context paramContext) {
    if (zzfyv == null) {
      boolean bool;
      if (zzq.zzalj() && paramContext.getPackageManager().hasSystemFeature("cn.google")) {
        bool = true;
      } else {
        bool = false;
      } 
      zzfyv = Boolean.valueOf(bool);
    } 
    return zzfyv.booleanValue();
  }
  
  public static boolean zzcl(Context paramContext) {
    if (zzfyw == null) {
      boolean bool;
      if (paramContext.getPackageManager().hasSystemFeature("android.hardware.type.iot") || paramContext.getPackageManager().hasSystemFeature("android.hardware.type.embedded")) {
        bool = true;
      } else {
        bool = false;
      } 
      zzfyw = Boolean.valueOf(bool);
    } 
    return zzfyw.booleanValue();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/util/zzi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */