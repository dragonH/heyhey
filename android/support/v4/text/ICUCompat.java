package android.support.v4.text;

import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import java.util.Locale;

public final class ICUCompat {
  private static final ICUCompatBaseImpl IMPL;
  
  static {
    if (Build.VERSION.SDK_INT >= 21) {
      IMPL = new ICUCompatApi21Impl();
    } else {
      IMPL = new ICUCompatBaseImpl();
    } 
  }
  
  @Nullable
  public static String maximizeAndGetScript(Locale paramLocale) {
    return IMPL.maximizeAndGetScript(paramLocale);
  }
  
  @RequiresApi(21)
  static class ICUCompatApi21Impl extends ICUCompatBaseImpl {
    public String maximizeAndGetScript(Locale param1Locale) {
      return ICUCompatApi21.maximizeAndGetScript(param1Locale);
    }
  }
  
  static class ICUCompatBaseImpl {
    public String maximizeAndGetScript(Locale param1Locale) {
      return ICUCompatIcs.maximizeAndGetScript(param1Locale);
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/text/ICUCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */