package android.support.v4.util;

import android.os.Build;
import android.support.annotation.RequiresApi;

public class ObjectsCompat {
  private static final ImplBase IMPL;
  
  static {
    if (Build.VERSION.SDK_INT >= 19) {
      IMPL = new ImplApi19();
    } else {
      IMPL = new ImplBase();
    } 
  }
  
  public static boolean equals(Object paramObject1, Object paramObject2) {
    return IMPL.equals(paramObject1, paramObject2);
  }
  
  @RequiresApi(19)
  private static class ImplApi19 extends ImplBase {
    private ImplApi19() {}
    
    public boolean equals(Object param1Object1, Object param1Object2) {
      return ObjectsCompat$ImplApi19$$ExternalSyntheticBackport0.m(param1Object1, param1Object2);
    }
  }
  
  private static class ImplBase {
    private ImplBase() {}
    
    public boolean equals(Object param1Object1, Object param1Object2) {
      return (param1Object1 == param1Object2 || (param1Object1 != null && param1Object1.equals(param1Object2)));
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/util/ObjectsCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */