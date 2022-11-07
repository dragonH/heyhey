package android.support.transition;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

@RequiresApi(14)
interface ViewOverlayImpl {
  void add(@NonNull Drawable paramDrawable);
  
  void clear();
  
  void remove(@NonNull Drawable paramDrawable);
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/transition/ViewOverlayImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */