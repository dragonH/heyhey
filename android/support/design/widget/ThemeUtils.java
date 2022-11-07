package android.support.design.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.appcompat.R;

class ThemeUtils {
  private static final int[] APPCOMPAT_CHECK_ATTRS = new int[] { R.attr.colorPrimary };
  
  static void checkAppCompatTheme(Context paramContext) {
    TypedArray typedArray = paramContext.obtainStyledAttributes(APPCOMPAT_CHECK_ATTRS);
    boolean bool = typedArray.hasValue(0);
    typedArray.recycle();
    if ((bool ^ true) == 0)
      return; 
    throw new IllegalArgumentException("You need to use a Theme.AppCompat theme (or descendant) with the design library.");
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/design/widget/ThemeUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */