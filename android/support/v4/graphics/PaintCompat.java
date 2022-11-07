package android.support.v4.graphics;

import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.NonNull;

public final class PaintCompat {
  public static boolean hasGlyph(@NonNull Paint paramPaint, @NonNull String paramString) {
    return (Build.VERSION.SDK_INT >= 23) ? paramPaint.hasGlyph(paramString) : PaintCompatApi14.hasGlyph(paramPaint, paramString);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/graphics/PaintCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */