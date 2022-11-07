package android.support.v4.graphics;

import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.RequiresApi;

public final class BitmapCompat {
  static final BitmapCompatBaseImpl IMPL;
  
  static {
    int i = Build.VERSION.SDK_INT;
    if (i >= 19) {
      IMPL = new BitmapCompatApi19Impl();
    } else if (i >= 18) {
      IMPL = new BitmapCompatApi18Impl();
    } else {
      IMPL = new BitmapCompatBaseImpl();
    } 
  }
  
  public static int getAllocationByteCount(Bitmap paramBitmap) {
    return IMPL.getAllocationByteCount(paramBitmap);
  }
  
  public static boolean hasMipMap(Bitmap paramBitmap) {
    return IMPL.hasMipMap(paramBitmap);
  }
  
  public static void setHasMipMap(Bitmap paramBitmap, boolean paramBoolean) {
    IMPL.setHasMipMap(paramBitmap, paramBoolean);
  }
  
  @RequiresApi(18)
  static class BitmapCompatApi18Impl extends BitmapCompatBaseImpl {
    public boolean hasMipMap(Bitmap param1Bitmap) {
      return param1Bitmap.hasMipMap();
    }
    
    public void setHasMipMap(Bitmap param1Bitmap, boolean param1Boolean) {
      param1Bitmap.setHasMipMap(param1Boolean);
    }
  }
  
  @RequiresApi(19)
  static class BitmapCompatApi19Impl extends BitmapCompatApi18Impl {
    public int getAllocationByteCount(Bitmap param1Bitmap) {
      return param1Bitmap.getAllocationByteCount();
    }
  }
  
  static class BitmapCompatBaseImpl {
    public int getAllocationByteCount(Bitmap param1Bitmap) {
      return param1Bitmap.getByteCount();
    }
    
    public boolean hasMipMap(Bitmap param1Bitmap) {
      return false;
    }
    
    public void setHasMipMap(Bitmap param1Bitmap, boolean param1Boolean) {}
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/graphics/BitmapCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */