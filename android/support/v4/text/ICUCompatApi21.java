package android.support.v4.text;

import android.support.annotation.RequiresApi;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

@RequiresApi(21)
class ICUCompatApi21 {
  private static final String TAG = "ICUCompatApi21";
  
  private static Method sAddLikelySubtagsMethod;
  
  static {
    try {
      sAddLikelySubtagsMethod = Class.forName("libcore.icu.ICU").getMethod("addLikelySubtags", new Class[] { Locale.class });
      return;
    } catch (Exception exception) {
      throw new IllegalStateException(exception);
    } 
  }
  
  public static String maximizeAndGetScript(Locale paramLocale) {
    try {
      return ((Locale)sAddLikelySubtagsMethod.invoke(null, new Object[] { paramLocale })).getScript();
    } catch (InvocationTargetException invocationTargetException) {
      Log.w("ICUCompatApi21", invocationTargetException);
    } catch (IllegalAccessException illegalAccessException) {
      Log.w("ICUCompatApi21", illegalAccessException);
    } 
    return paramLocale.getScript();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/text/ICUCompatApi21.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */