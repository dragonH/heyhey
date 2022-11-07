package android.support.v7.widget;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

public class TooltipCompat {
  private static final ViewCompatImpl IMPL;
  
  static {
    if (Build.VERSION.SDK_INT >= 26) {
      IMPL = new Api26ViewCompatImpl();
    } else {
      IMPL = new BaseViewCompatImpl();
    } 
  }
  
  public static void setTooltipText(@NonNull View paramView, @Nullable CharSequence paramCharSequence) {
    IMPL.setTooltipText(paramView, paramCharSequence);
  }
  
  @TargetApi(26)
  private static class Api26ViewCompatImpl implements ViewCompatImpl {
    private Api26ViewCompatImpl() {}
    
    public void setTooltipText(@NonNull View param1View, @Nullable CharSequence param1CharSequence) {
      param1View.setTooltipText(param1CharSequence);
    }
  }
  
  private static class BaseViewCompatImpl implements ViewCompatImpl {
    private BaseViewCompatImpl() {}
    
    public void setTooltipText(@NonNull View param1View, @Nullable CharSequence param1CharSequence) {
      TooltipCompatHandler.setTooltipText(param1View, param1CharSequence);
    }
  }
  
  private static interface ViewCompatImpl {
    void setTooltipText(@NonNull View param1View, @Nullable CharSequence param1CharSequence);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/widget/TooltipCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */