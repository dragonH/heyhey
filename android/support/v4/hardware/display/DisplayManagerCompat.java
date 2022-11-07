package android.support.v4.hardware.display;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.Display;
import android.view.WindowManager;
import java.util.WeakHashMap;

public abstract class DisplayManagerCompat {
  public static final String DISPLAY_CATEGORY_PRESENTATION = "android.hardware.display.category.PRESENTATION";
  
  private static final WeakHashMap<Context, DisplayManagerCompat> sInstances = new WeakHashMap<Context, DisplayManagerCompat>();
  
  public static DisplayManagerCompat getInstance(Context paramContext) {
    synchronized (sInstances) {
      DisplayManagerCompat displayManagerCompat1 = null.get(paramContext);
      DisplayManagerCompat displayManagerCompat2 = displayManagerCompat1;
      if (displayManagerCompat1 == null) {
        if (Build.VERSION.SDK_INT >= 17) {
          displayManagerCompat2 = new DisplayManagerCompatApi17Impl();
          super(paramContext);
        } else {
          displayManagerCompat2 = new DisplayManagerCompatApi14Impl(paramContext);
        } 
        null.put(paramContext, displayManagerCompat2);
      } 
      return displayManagerCompat2;
    } 
  }
  
  public abstract Display getDisplay(int paramInt);
  
  public abstract Display[] getDisplays();
  
  public abstract Display[] getDisplays(String paramString);
  
  private static class DisplayManagerCompatApi14Impl extends DisplayManagerCompat {
    private final WindowManager mWindowManager;
    
    DisplayManagerCompatApi14Impl(Context param1Context) {
      this.mWindowManager = (WindowManager)param1Context.getSystemService("window");
    }
    
    public Display getDisplay(int param1Int) {
      Display display = this.mWindowManager.getDefaultDisplay();
      return (display.getDisplayId() == param1Int) ? display : null;
    }
    
    public Display[] getDisplays() {
      return new Display[] { this.mWindowManager.getDefaultDisplay() };
    }
    
    public Display[] getDisplays(String param1String) {
      Display[] arrayOfDisplay;
      if (param1String == null) {
        arrayOfDisplay = getDisplays();
      } else {
        arrayOfDisplay = new Display[0];
      } 
      return arrayOfDisplay;
    }
  }
  
  @RequiresApi(17)
  private static class DisplayManagerCompatApi17Impl extends DisplayManagerCompat {
    private final DisplayManager mDisplayManager;
    
    DisplayManagerCompatApi17Impl(Context param1Context) {
      this.mDisplayManager = (DisplayManager)param1Context.getSystemService("display");
    }
    
    public Display getDisplay(int param1Int) {
      return this.mDisplayManager.getDisplay(param1Int);
    }
    
    public Display[] getDisplays() {
      return this.mDisplayManager.getDisplays();
    }
    
    public Display[] getDisplays(String param1String) {
      return this.mDisplayManager.getDisplays(param1String);
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/hardware/display/DisplayManagerCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */