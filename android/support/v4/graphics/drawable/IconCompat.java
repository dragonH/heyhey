package android.support.v4.graphics.drawable;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;

public class IconCompat {
  private static final float ADAPTIVE_ICON_INSET_FACTOR = 0.25F;
  
  private static final int AMBIENT_SHADOW_ALPHA = 30;
  
  private static final float BLUR_FACTOR = 0.010416667F;
  
  private static final float DEFAULT_VIEW_PORT_SCALE = 0.6666667F;
  
  private static final float ICON_DIAMETER_FACTOR = 0.9166667F;
  
  private static final int KEY_SHADOW_ALPHA = 61;
  
  private static final float KEY_SHADOW_OFFSET_FACTOR = 0.020833334F;
  
  private static final int TYPE_ADAPTIVE_BITMAP = 5;
  
  private static final int TYPE_BITMAP = 1;
  
  private static final int TYPE_DATA = 3;
  
  private static final int TYPE_RESOURCE = 2;
  
  private static final int TYPE_URI = 4;
  
  private int mInt1;
  
  private int mInt2;
  
  private Object mObj1;
  
  private final int mType;
  
  private IconCompat(int paramInt) {
    this.mType = paramInt;
  }
  
  @VisibleForTesting
  static Bitmap createLegacyIconFromAdaptiveIcon(Bitmap paramBitmap) {
    int i = (int)(Math.min(paramBitmap.getWidth(), paramBitmap.getHeight()) * 0.6666667F);
    Bitmap bitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(bitmap);
    Paint paint = new Paint(3);
    float f1 = i;
    float f2 = 0.5F * f1;
    float f3 = 0.9166667F * f2;
    float f4 = 0.010416667F * f1;
    paint.setColor(0);
    paint.setShadowLayer(f4, 0.0F, f1 * 0.020833334F, 1023410176);
    canvas.drawCircle(f2, f2, f3, paint);
    paint.setShadowLayer(f4, 0.0F, 0.0F, 503316480);
    canvas.drawCircle(f2, f2, f3, paint);
    paint.clearShadowLayer();
    paint.setColor(-16777216);
    Shader.TileMode tileMode = Shader.TileMode.CLAMP;
    BitmapShader bitmapShader = new BitmapShader(paramBitmap, tileMode, tileMode);
    Matrix matrix = new Matrix();
    matrix.setTranslate((-(paramBitmap.getWidth() - i) / 2), (-(paramBitmap.getHeight() - i) / 2));
    bitmapShader.setLocalMatrix(matrix);
    paint.setShader((Shader)bitmapShader);
    canvas.drawCircle(f2, f2, f3, paint);
    canvas.setBitmap(null);
    return bitmap;
  }
  
  public static IconCompat createWithAdaptiveBitmap(Bitmap paramBitmap) {
    if (paramBitmap != null) {
      IconCompat iconCompat = new IconCompat(5);
      iconCompat.mObj1 = paramBitmap;
      return iconCompat;
    } 
    throw new IllegalArgumentException("Bitmap must not be null.");
  }
  
  public static IconCompat createWithBitmap(Bitmap paramBitmap) {
    if (paramBitmap != null) {
      IconCompat iconCompat = new IconCompat(1);
      iconCompat.mObj1 = paramBitmap;
      return iconCompat;
    } 
    throw new IllegalArgumentException("Bitmap must not be null.");
  }
  
  public static IconCompat createWithContentUri(Uri paramUri) {
    if (paramUri != null)
      return createWithContentUri(paramUri.toString()); 
    throw new IllegalArgumentException("Uri must not be null.");
  }
  
  public static IconCompat createWithContentUri(String paramString) {
    if (paramString != null) {
      IconCompat iconCompat = new IconCompat(4);
      iconCompat.mObj1 = paramString;
      return iconCompat;
    } 
    throw new IllegalArgumentException("Uri must not be null.");
  }
  
  public static IconCompat createWithData(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    if (paramArrayOfbyte != null) {
      IconCompat iconCompat = new IconCompat(3);
      iconCompat.mObj1 = paramArrayOfbyte;
      iconCompat.mInt1 = paramInt1;
      iconCompat.mInt2 = paramInt2;
      return iconCompat;
    } 
    throw new IllegalArgumentException("Data must not be null.");
  }
  
  public static IconCompat createWithResource(Context paramContext, @DrawableRes int paramInt) {
    if (paramContext != null) {
      IconCompat iconCompat = new IconCompat(2);
      iconCompat.mInt1 = paramInt;
      iconCompat.mObj1 = paramContext;
      return iconCompat;
    } 
    throw new IllegalArgumentException("Context must not be null.");
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public void addToShortcutIntent(Intent paramIntent) {
    int i = this.mType;
    if (i != 1) {
      if (i != 2) {
        if (i == 5) {
          paramIntent.putExtra("android.intent.extra.shortcut.ICON", (Parcelable)createLegacyIconFromAdaptiveIcon((Bitmap)this.mObj1));
        } else {
          throw new IllegalArgumentException("Icon type not supported for intent shortcuts");
        } 
      } else {
        paramIntent.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", (Parcelable)Intent.ShortcutIconResource.fromContext((Context)this.mObj1, this.mInt1));
      } 
    } else {
      paramIntent.putExtra("android.intent.extra.shortcut.ICON", (Parcelable)this.mObj1);
    } 
  }
  
  @TargetApi(26)
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public Icon toIcon() {
    int i = this.mType;
    if (i != 1) {
      if (i != 2) {
        if (i != 3) {
          if (i != 4) {
            if (i == 5)
              return (Build.VERSION.SDK_INT >= 26) ? Icon.createWithAdaptiveBitmap((Bitmap)this.mObj1) : Icon.createWithBitmap(createLegacyIconFromAdaptiveIcon((Bitmap)this.mObj1)); 
            throw new IllegalArgumentException("Unknown type");
          } 
          return Icon.createWithContentUri((String)this.mObj1);
        } 
        return Icon.createWithData((byte[])this.mObj1, this.mInt1, this.mInt2);
      } 
      return Icon.createWithResource((Context)this.mObj1, this.mInt1);
    } 
    return Icon.createWithBitmap((Bitmap)this.mObj1);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/graphics/drawable/IconCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */