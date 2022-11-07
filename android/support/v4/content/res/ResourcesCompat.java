package android.support.v4.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.FontRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.graphics.TypefaceCompat;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public final class ResourcesCompat {
  private static final String TAG = "ResourcesCompat";
  
  @ColorInt
  public static int getColor(@NonNull Resources paramResources, @ColorRes int paramInt, @Nullable Resources.Theme paramTheme) throws Resources.NotFoundException {
    return (Build.VERSION.SDK_INT >= 23) ? paramResources.getColor(paramInt, paramTheme) : paramResources.getColor(paramInt);
  }
  
  @Nullable
  public static ColorStateList getColorStateList(@NonNull Resources paramResources, @ColorRes int paramInt, @Nullable Resources.Theme paramTheme) throws Resources.NotFoundException {
    return (Build.VERSION.SDK_INT >= 23) ? paramResources.getColorStateList(paramInt, paramTheme) : paramResources.getColorStateList(paramInt);
  }
  
  @Nullable
  public static Drawable getDrawable(@NonNull Resources paramResources, @DrawableRes int paramInt, @Nullable Resources.Theme paramTheme) throws Resources.NotFoundException {
    return (Build.VERSION.SDK_INT >= 21) ? paramResources.getDrawable(paramInt, paramTheme) : paramResources.getDrawable(paramInt);
  }
  
  @Nullable
  public static Drawable getDrawableForDensity(@NonNull Resources paramResources, @DrawableRes int paramInt1, int paramInt2, @Nullable Resources.Theme paramTheme) throws Resources.NotFoundException {
    return (Build.VERSION.SDK_INT >= 21) ? paramResources.getDrawableForDensity(paramInt1, paramInt2, paramTheme) : paramResources.getDrawableForDensity(paramInt1, paramInt2);
  }
  
  @Nullable
  public static Typeface getFont(@NonNull Context paramContext, @FontRes int paramInt) throws Resources.NotFoundException {
    return paramContext.isRestricted() ? null : loadFont(paramContext, paramInt, new TypedValue(), 0, null);
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static Typeface getFont(@NonNull Context paramContext, @FontRes int paramInt1, TypedValue paramTypedValue, int paramInt2, @Nullable TextView paramTextView) throws Resources.NotFoundException {
    return paramContext.isRestricted() ? null : loadFont(paramContext, paramInt1, paramTypedValue, paramInt2, paramTextView);
  }
  
  private static Typeface loadFont(@NonNull Context paramContext, int paramInt1, TypedValue paramTypedValue, int paramInt2, @Nullable TextView paramTextView) {
    Resources resources = paramContext.getResources();
    resources.getValue(paramInt1, paramTypedValue, true);
    Typeface typeface = loadFont(paramContext, resources, paramTypedValue, paramInt1, paramInt2, paramTextView);
    if (typeface != null)
      return typeface; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Font resource ID #0x");
    stringBuilder.append(Integer.toHexString(paramInt1));
    throw new Resources.NotFoundException(stringBuilder.toString());
  }
  
  private static Typeface loadFont(@NonNull Context paramContext, Resources paramResources, TypedValue paramTypedValue, int paramInt1, int paramInt2, @Nullable TextView paramTextView) {
    StringBuilder stringBuilder2;
    String str;
    CharSequence charSequence = paramTypedValue.string;
    if (charSequence != null) {
      str = charSequence.toString();
      if (!str.startsWith("res/"))
        return null; 
      Typeface typeface = TypefaceCompat.findFromCache(paramResources, paramInt1, paramInt2);
      if (typeface != null)
        return typeface; 
      try {
        if (str.toLowerCase().endsWith(".xml")) {
          FontResourcesParserCompat.FamilyResourceEntry familyResourceEntry = FontResourcesParserCompat.parse((XmlPullParser)paramResources.getXml(paramInt1), paramResources);
          if (familyResourceEntry == null) {
            Log.e("ResourcesCompat", "Failed to find font-family tag");
            return null;
          } 
          return TypefaceCompat.createFromResourcesFamilyXml(paramContext, familyResourceEntry, paramResources, paramInt1, paramInt2, paramTextView);
        } 
        return TypefaceCompat.createFromResourcesFontFile(paramContext, paramResources, paramInt1, str, paramInt2);
      } catch (XmlPullParserException xmlPullParserException) {
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Failed to parse xml resource ");
        stringBuilder2.append(str);
        Log.e("ResourcesCompat", stringBuilder2.toString(), (Throwable)xmlPullParserException);
      } catch (IOException iOException) {
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Failed to read xml resource ");
        stringBuilder2.append(str);
        Log.e("ResourcesCompat", stringBuilder2.toString(), iOException);
      } 
      return null;
    } 
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append("Resource \"");
    stringBuilder1.append(stringBuilder2.getResourceName(paramInt1));
    stringBuilder1.append("\" (");
    stringBuilder1.append(Integer.toHexString(paramInt1));
    stringBuilder1.append(") is not a Font: ");
    stringBuilder1.append(str);
    throw new Resources.NotFoundException(stringBuilder1.toString());
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/content/res/ResourcesCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */