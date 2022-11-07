package android.support.v7.widget;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class TintContextWrapper extends ContextWrapper {
  private static final Object CACHE_LOCK = new Object();
  
  private static ArrayList<WeakReference<TintContextWrapper>> sCache;
  
  private final Resources mResources;
  
  private final Resources.Theme mTheme;
  
  private TintContextWrapper(@NonNull Context paramContext) {
    super(paramContext);
    if (VectorEnabledTintResources.shouldBeUsed()) {
      VectorEnabledTintResources vectorEnabledTintResources = new VectorEnabledTintResources((Context)this, paramContext.getResources());
      this.mResources = vectorEnabledTintResources;
      Resources.Theme theme = vectorEnabledTintResources.newTheme();
      this.mTheme = theme;
      theme.setTo(paramContext.getTheme());
    } else {
      this.mResources = new TintResources((Context)this, paramContext.getResources());
      this.mTheme = null;
    } 
  }
  
  private static boolean shouldWrap(@NonNull Context paramContext) {
    boolean bool = paramContext instanceof TintContextWrapper;
    boolean bool1 = false;
    boolean bool2 = bool1;
    if (!bool) {
      bool2 = bool1;
      if (!(paramContext.getResources() instanceof TintResources))
        if (paramContext.getResources() instanceof VectorEnabledTintResources) {
          bool2 = bool1;
        } else {
          if (Build.VERSION.SDK_INT >= 21) {
            bool2 = bool1;
            if (VectorEnabledTintResources.shouldBeUsed())
              bool2 = true; 
            return bool2;
          } 
          bool2 = true;
        }  
    } 
    return bool2;
  }
  
  public static Context wrap(@NonNull Context paramContext) {
    if (shouldWrap(paramContext))
      synchronized (CACHE_LOCK) {
        ArrayList<WeakReference<TintContextWrapper>> arrayList1 = sCache;
        if (arrayList1 == null) {
          arrayList1 = new ArrayList<WeakReference<TintContextWrapper>>();
          this();
          sCache = arrayList1;
        } else {
          int i;
          for (i = arrayList1.size() - 1; i >= 0; i--) {
            WeakReference weakReference1 = sCache.get(i);
            if (weakReference1 == null || weakReference1.get() == null)
              sCache.remove(i); 
          } 
          for (i = sCache.size() - 1; i >= 0; i--) {
            WeakReference<TintContextWrapper> weakReference1 = sCache.get(i);
            if (weakReference1 != null) {
              TintContextWrapper tintContextWrapper1 = weakReference1.get();
            } else {
              weakReference1 = null;
            } 
            if (weakReference1 != null && weakReference1.getBaseContext() == paramContext)
              return (Context)weakReference1; 
          } 
        } 
        TintContextWrapper tintContextWrapper = new TintContextWrapper();
        this(paramContext);
        ArrayList<WeakReference<TintContextWrapper>> arrayList2 = sCache;
        WeakReference<TintContextWrapper> weakReference = new WeakReference();
        this((T)tintContextWrapper);
        arrayList2.add(weakReference);
        return (Context)tintContextWrapper;
      }  
    return paramContext;
  }
  
  public AssetManager getAssets() {
    return this.mResources.getAssets();
  }
  
  public Resources getResources() {
    return this.mResources;
  }
  
  public Resources.Theme getTheme() {
    Resources.Theme theme1 = this.mTheme;
    Resources.Theme theme2 = theme1;
    if (theme1 == null)
      theme2 = super.getTheme(); 
    return theme2;
  }
  
  public void setTheme(int paramInt) {
    Resources.Theme theme = this.mTheme;
    if (theme == null) {
      super.setTheme(paramInt);
    } else {
      theme.applyStyle(paramInt, true);
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/widget/TintContextWrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */