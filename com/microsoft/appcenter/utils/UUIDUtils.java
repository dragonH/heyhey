package com.microsoft.appcenter.utils;

import android.support.annotation.VisibleForTesting;
import java.util.Random;
import java.util.UUID;

public class UUIDUtils {
  @VisibleForTesting
  static Implementation sImplementation = new Implementation() {
      public UUID randomUUID() {
        return UUID.randomUUID();
      }
    };
  
  private static Random sRandom;
  
  private static void initFailOver(SecurityException paramSecurityException) {
    // Byte code:
    //   0: ldc com/microsoft/appcenter/utils/UUIDUtils
    //   2: monitorenter
    //   3: getstatic com/microsoft/appcenter/utils/UUIDUtils.sRandom : Ljava/util/Random;
    //   6: ifnonnull -> 29
    //   9: new java/util/Random
    //   12: astore_1
    //   13: aload_1
    //   14: invokespecial <init> : ()V
    //   17: aload_1
    //   18: putstatic com/microsoft/appcenter/utils/UUIDUtils.sRandom : Ljava/util/Random;
    //   21: ldc 'AppCenter'
    //   23: ldc 'UUID.randomUUID failed, using Random as fallback'
    //   25: aload_0
    //   26: invokestatic error : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   29: ldc com/microsoft/appcenter/utils/UUIDUtils
    //   31: monitorexit
    //   32: return
    //   33: astore_0
    //   34: ldc com/microsoft/appcenter/utils/UUIDUtils
    //   36: monitorexit
    //   37: aload_0
    //   38: athrow
    // Exception table:
    //   from	to	target	type
    //   3	29	33	finally
  }
  
  public static UUID randomUUID() {
    try {
      return sImplementation.randomUUID();
    } catch (SecurityException securityException) {
      initFailOver(securityException);
      return new UUID(sRandom.nextLong() & 0xFFFFFFFFFFFF0FFFL | 0x4000L, sRandom.nextLong() & 0x3FFFFFFFFFFFFFFFL | Long.MIN_VALUE);
    } 
  }
  
  @VisibleForTesting
  static interface Implementation {
    UUID randomUUID();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/utils/UUIDUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */