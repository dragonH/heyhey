package android.support.design.widget;

import android.graphics.PorterDuff;

class ViewUtils {
  static PorterDuff.Mode parseTintMode(int paramInt, PorterDuff.Mode paramMode) {
    return (paramInt != 3) ? ((paramInt != 5) ? ((paramInt != 9) ? ((paramInt != 14) ? ((paramInt != 15) ? paramMode : PorterDuff.Mode.SCREEN) : PorterDuff.Mode.MULTIPLY) : PorterDuff.Mode.SRC_ATOP) : PorterDuff.Mode.SRC_IN) : PorterDuff.Mode.SRC_OVER;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/design/widget/ViewUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */