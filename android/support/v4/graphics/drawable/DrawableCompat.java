package android.support.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.InsetDrawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import java.io.IOException;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public final class DrawableCompat {
  static final DrawableCompatBaseImpl IMPL;
  
  static {
    int i = Build.VERSION.SDK_INT;
    if (i >= 23) {
      IMPL = new DrawableCompatApi23Impl();
    } else if (i >= 21) {
      IMPL = new DrawableCompatApi21Impl();
    } else if (i >= 19) {
      IMPL = new DrawableCompatApi19Impl();
    } else if (i >= 17) {
      IMPL = new DrawableCompatApi17Impl();
    } else {
      IMPL = new DrawableCompatBaseImpl();
    } 
  }
  
  public static void applyTheme(@NonNull Drawable paramDrawable, @NonNull Resources.Theme paramTheme) {
    IMPL.applyTheme(paramDrawable, paramTheme);
  }
  
  public static boolean canApplyTheme(@NonNull Drawable paramDrawable) {
    return IMPL.canApplyTheme(paramDrawable);
  }
  
  public static void clearColorFilter(@NonNull Drawable paramDrawable) {
    IMPL.clearColorFilter(paramDrawable);
  }
  
  public static int getAlpha(@NonNull Drawable paramDrawable) {
    return IMPL.getAlpha(paramDrawable);
  }
  
  public static ColorFilter getColorFilter(@NonNull Drawable paramDrawable) {
    return IMPL.getColorFilter(paramDrawable);
  }
  
  public static int getLayoutDirection(@NonNull Drawable paramDrawable) {
    return IMPL.getLayoutDirection(paramDrawable);
  }
  
  public static void inflate(@NonNull Drawable paramDrawable, @NonNull Resources paramResources, @NonNull XmlPullParser paramXmlPullParser, @NonNull AttributeSet paramAttributeSet, @Nullable Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    IMPL.inflate(paramDrawable, paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
  }
  
  public static boolean isAutoMirrored(@NonNull Drawable paramDrawable) {
    return IMPL.isAutoMirrored(paramDrawable);
  }
  
  public static void jumpToCurrentState(@NonNull Drawable paramDrawable) {
    IMPL.jumpToCurrentState(paramDrawable);
  }
  
  public static void setAutoMirrored(@NonNull Drawable paramDrawable, boolean paramBoolean) {
    IMPL.setAutoMirrored(paramDrawable, paramBoolean);
  }
  
  public static void setHotspot(@NonNull Drawable paramDrawable, float paramFloat1, float paramFloat2) {
    IMPL.setHotspot(paramDrawable, paramFloat1, paramFloat2);
  }
  
  public static void setHotspotBounds(@NonNull Drawable paramDrawable, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    IMPL.setHotspotBounds(paramDrawable, paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public static boolean setLayoutDirection(@NonNull Drawable paramDrawable, int paramInt) {
    return IMPL.setLayoutDirection(paramDrawable, paramInt);
  }
  
  public static void setTint(@NonNull Drawable paramDrawable, @ColorInt int paramInt) {
    IMPL.setTint(paramDrawable, paramInt);
  }
  
  public static void setTintList(@NonNull Drawable paramDrawable, @Nullable ColorStateList paramColorStateList) {
    IMPL.setTintList(paramDrawable, paramColorStateList);
  }
  
  public static void setTintMode(@NonNull Drawable paramDrawable, @Nullable PorterDuff.Mode paramMode) {
    IMPL.setTintMode(paramDrawable, paramMode);
  }
  
  public static <T extends Drawable> T unwrap(@NonNull Drawable paramDrawable) {
    Drawable drawable = paramDrawable;
    if (paramDrawable instanceof DrawableWrapper)
      drawable = ((DrawableWrapper)paramDrawable).getWrappedDrawable(); 
    return (T)drawable;
  }
  
  public static Drawable wrap(@NonNull Drawable paramDrawable) {
    return IMPL.wrap(paramDrawable);
  }
  
  @RequiresApi(17)
  static class DrawableCompatApi17Impl extends DrawableCompatBaseImpl {
    private static final String TAG = "DrawableCompatApi17";
    
    private static Method sGetLayoutDirectionMethod;
    
    private static boolean sGetLayoutDirectionMethodFetched;
    
    private static Method sSetLayoutDirectionMethod;
    
    private static boolean sSetLayoutDirectionMethodFetched;
    
    public int getLayoutDirection(Drawable param1Drawable) {
      if (!sGetLayoutDirectionMethodFetched) {
        try {
          Method method1 = Drawable.class.getDeclaredMethod("getLayoutDirection", new Class[0]);
          sGetLayoutDirectionMethod = method1;
          method1.setAccessible(true);
        } catch (NoSuchMethodException noSuchMethodException) {
          Log.i("DrawableCompatApi17", "Failed to retrieve getLayoutDirection() method", noSuchMethodException);
        } 
        sGetLayoutDirectionMethodFetched = true;
      } 
      Method method = sGetLayoutDirectionMethod;
      if (method != null)
        try {
          return ((Integer)method.invoke(param1Drawable, new Object[0])).intValue();
        } catch (Exception exception) {
          Log.i("DrawableCompatApi17", "Failed to invoke getLayoutDirection() via reflection", exception);
          sGetLayoutDirectionMethod = null;
        }  
      return 0;
    }
    
    public boolean setLayoutDirection(Drawable param1Drawable, int param1Int) {
      if (!sSetLayoutDirectionMethodFetched) {
        try {
          Method method1 = Drawable.class.getDeclaredMethod("setLayoutDirection", new Class[] { int.class });
          sSetLayoutDirectionMethod = method1;
          method1.setAccessible(true);
        } catch (NoSuchMethodException noSuchMethodException) {
          Log.i("DrawableCompatApi17", "Failed to retrieve setLayoutDirection(int) method", noSuchMethodException);
        } 
        sSetLayoutDirectionMethodFetched = true;
      } 
      Method method = sSetLayoutDirectionMethod;
      if (method != null)
        try {
          method.invoke(param1Drawable, new Object[] { Integer.valueOf(param1Int) });
          return true;
        } catch (Exception exception) {
          Log.i("DrawableCompatApi17", "Failed to invoke setLayoutDirection(int) via reflection", exception);
          sSetLayoutDirectionMethod = null;
        }  
      return false;
    }
  }
  
  @RequiresApi(19)
  static class DrawableCompatApi19Impl extends DrawableCompatApi17Impl {
    public int getAlpha(Drawable param1Drawable) {
      return param1Drawable.getAlpha();
    }
    
    public boolean isAutoMirrored(Drawable param1Drawable) {
      return param1Drawable.isAutoMirrored();
    }
    
    public void setAutoMirrored(Drawable param1Drawable, boolean param1Boolean) {
      param1Drawable.setAutoMirrored(param1Boolean);
    }
    
    public Drawable wrap(Drawable param1Drawable) {
      return !(param1Drawable instanceof TintAwareDrawable) ? new DrawableWrapperApi19(param1Drawable) : param1Drawable;
    }
  }
  
  @RequiresApi(21)
  static class DrawableCompatApi21Impl extends DrawableCompatApi19Impl {
    public void applyTheme(Drawable param1Drawable, Resources.Theme param1Theme) {
      param1Drawable.applyTheme(param1Theme);
    }
    
    public boolean canApplyTheme(Drawable param1Drawable) {
      return param1Drawable.canApplyTheme();
    }
    
    public void clearColorFilter(Drawable param1Drawable) {
      param1Drawable.clearColorFilter();
      if (param1Drawable instanceof InsetDrawable) {
        clearColorFilter(((InsetDrawable)param1Drawable).getDrawable());
      } else if (param1Drawable instanceof DrawableWrapper) {
        clearColorFilter(((DrawableWrapper)param1Drawable).getWrappedDrawable());
      } else if (param1Drawable instanceof DrawableContainer) {
        DrawableContainer.DrawableContainerState drawableContainerState = (DrawableContainer.DrawableContainerState)((DrawableContainer)param1Drawable).getConstantState();
        if (drawableContainerState != null) {
          byte b = 0;
          int i = drawableContainerState.getChildCount();
          while (b < i) {
            Drawable drawable = drawableContainerState.getChild(b);
            if (drawable != null)
              clearColorFilter(drawable); 
            b++;
          } 
        } 
      } 
    }
    
    public ColorFilter getColorFilter(Drawable param1Drawable) {
      return param1Drawable.getColorFilter();
    }
    
    public void inflate(Drawable param1Drawable, Resources param1Resources, XmlPullParser param1XmlPullParser, AttributeSet param1AttributeSet, Resources.Theme param1Theme) throws IOException, XmlPullParserException {
      param1Drawable.inflate(param1Resources, param1XmlPullParser, param1AttributeSet, param1Theme);
    }
    
    public void setHotspot(Drawable param1Drawable, float param1Float1, float param1Float2) {
      param1Drawable.setHotspot(param1Float1, param1Float2);
    }
    
    public void setHotspotBounds(Drawable param1Drawable, int param1Int1, int param1Int2, int param1Int3, int param1Int4) {
      param1Drawable.setHotspotBounds(param1Int1, param1Int2, param1Int3, param1Int4);
    }
    
    public void setTint(Drawable param1Drawable, int param1Int) {
      param1Drawable.setTint(param1Int);
    }
    
    public void setTintList(Drawable param1Drawable, ColorStateList param1ColorStateList) {
      param1Drawable.setTintList(param1ColorStateList);
    }
    
    public void setTintMode(Drawable param1Drawable, PorterDuff.Mode param1Mode) {
      param1Drawable.setTintMode(param1Mode);
    }
    
    public Drawable wrap(Drawable param1Drawable) {
      return !(param1Drawable instanceof TintAwareDrawable) ? new DrawableWrapperApi21(param1Drawable) : param1Drawable;
    }
  }
  
  @RequiresApi(23)
  static class DrawableCompatApi23Impl extends DrawableCompatApi21Impl {
    public void clearColorFilter(Drawable param1Drawable) {
      param1Drawable.clearColorFilter();
    }
    
    public int getLayoutDirection(Drawable param1Drawable) {
      return param1Drawable.getLayoutDirection();
    }
    
    public boolean setLayoutDirection(Drawable param1Drawable, int param1Int) {
      return param1Drawable.setLayoutDirection(param1Int);
    }
    
    public Drawable wrap(Drawable param1Drawable) {
      return param1Drawable;
    }
  }
  
  static class DrawableCompatBaseImpl {
    public void applyTheme(Drawable param1Drawable, Resources.Theme param1Theme) {}
    
    public boolean canApplyTheme(Drawable param1Drawable) {
      return false;
    }
    
    public void clearColorFilter(Drawable param1Drawable) {
      param1Drawable.clearColorFilter();
    }
    
    public int getAlpha(Drawable param1Drawable) {
      return 0;
    }
    
    public ColorFilter getColorFilter(Drawable param1Drawable) {
      return null;
    }
    
    public int getLayoutDirection(Drawable param1Drawable) {
      return 0;
    }
    
    public void inflate(Drawable param1Drawable, Resources param1Resources, XmlPullParser param1XmlPullParser, AttributeSet param1AttributeSet, Resources.Theme param1Theme) throws IOException, XmlPullParserException {
      param1Drawable.inflate(param1Resources, param1XmlPullParser, param1AttributeSet);
    }
    
    public boolean isAutoMirrored(Drawable param1Drawable) {
      return false;
    }
    
    public void jumpToCurrentState(Drawable param1Drawable) {
      param1Drawable.jumpToCurrentState();
    }
    
    public void setAutoMirrored(Drawable param1Drawable, boolean param1Boolean) {}
    
    public void setHotspot(Drawable param1Drawable, float param1Float1, float param1Float2) {}
    
    public void setHotspotBounds(Drawable param1Drawable, int param1Int1, int param1Int2, int param1Int3, int param1Int4) {}
    
    public boolean setLayoutDirection(Drawable param1Drawable, int param1Int) {
      return false;
    }
    
    public void setTint(Drawable param1Drawable, int param1Int) {
      if (param1Drawable instanceof TintAwareDrawable)
        ((TintAwareDrawable)param1Drawable).setTint(param1Int); 
    }
    
    public void setTintList(Drawable param1Drawable, ColorStateList param1ColorStateList) {
      if (param1Drawable instanceof TintAwareDrawable)
        ((TintAwareDrawable)param1Drawable).setTintList(param1ColorStateList); 
    }
    
    public void setTintMode(Drawable param1Drawable, PorterDuff.Mode param1Mode) {
      if (param1Drawable instanceof TintAwareDrawable)
        ((TintAwareDrawable)param1Drawable).setTintMode(param1Mode); 
    }
    
    public Drawable wrap(Drawable param1Drawable) {
      return !(param1Drawable instanceof TintAwareDrawable) ? new DrawableWrapperApi14(param1Drawable) : param1Drawable;
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/graphics/drawable/DrawableCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */