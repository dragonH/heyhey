package android.support.transition;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.util.Property;
import android.view.View;
import java.lang.reflect.Field;

class ViewUtils {
  static final Property<View, Rect> CLIP_BOUNDS;
  
  private static final ViewUtilsImpl IMPL;
  
  private static final String TAG = "ViewUtils";
  
  static final Property<View, Float> TRANSITION_ALPHA = new Property<View, Float>(Float.class, "translationAlpha") {
      public Float get(View param1View) {
        return Float.valueOf(ViewUtils.getTransitionAlpha(param1View));
      }
      
      public void set(View param1View, Float param1Float) {
        ViewUtils.setTransitionAlpha(param1View, param1Float.floatValue());
      }
    };
  
  private static final int VISIBILITY_MASK = 12;
  
  private static Field sViewFlagsField;
  
  private static boolean sViewFlagsFieldFetched;
  
  static {
    CLIP_BOUNDS = new Property<View, Rect>(Rect.class, "clipBounds") {
        public Rect get(View param1View) {
          return ViewCompat.getClipBounds(param1View);
        }
        
        public void set(View param1View, Rect param1Rect) {
          ViewCompat.setClipBounds(param1View, param1Rect);
        }
      };
  }
  
  static void clearNonTransitionAlpha(@NonNull View paramView) {
    IMPL.clearNonTransitionAlpha(paramView);
  }
  
  private static void fetchViewFlagsField() {
    if (!sViewFlagsFieldFetched) {
      try {
        Field field = View.class.getDeclaredField("mViewFlags");
        sViewFlagsField = field;
        field.setAccessible(true);
      } catch (NoSuchFieldException noSuchFieldException) {
        Log.i("ViewUtils", "fetchViewFlagsField: ");
      } 
      sViewFlagsFieldFetched = true;
    } 
  }
  
  static ViewOverlayImpl getOverlay(@NonNull View paramView) {
    return IMPL.getOverlay(paramView);
  }
  
  static float getTransitionAlpha(@NonNull View paramView) {
    return IMPL.getTransitionAlpha(paramView);
  }
  
  static WindowIdImpl getWindowId(@NonNull View paramView) {
    return IMPL.getWindowId(paramView);
  }
  
  static void saveNonTransitionAlpha(@NonNull View paramView) {
    IMPL.saveNonTransitionAlpha(paramView);
  }
  
  static void setAnimationMatrix(@NonNull View paramView, @Nullable Matrix paramMatrix) {
    IMPL.setAnimationMatrix(paramView, paramMatrix);
  }
  
  static void setLeftTopRightBottom(@NonNull View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    IMPL.setLeftTopRightBottom(paramView, paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  static void setTransitionAlpha(@NonNull View paramView, float paramFloat) {
    IMPL.setTransitionAlpha(paramView, paramFloat);
  }
  
  static void setTransitionVisibility(@NonNull View paramView, int paramInt) {
    fetchViewFlagsField();
    Field field = sViewFlagsField;
    if (field != null)
      try {
        int i = field.getInt(paramView);
        sViewFlagsField.setInt(paramView, paramInt | i & 0xFFFFFFF3);
      } catch (IllegalAccessException illegalAccessException) {} 
  }
  
  static void transformMatrixToGlobal(@NonNull View paramView, @NonNull Matrix paramMatrix) {
    IMPL.transformMatrixToGlobal(paramView, paramMatrix);
  }
  
  static void transformMatrixToLocal(@NonNull View paramView, @NonNull Matrix paramMatrix) {
    IMPL.transformMatrixToLocal(paramView, paramMatrix);
  }
  
  static {
    int i = Build.VERSION.SDK_INT;
    if (i >= 22) {
      IMPL = new ViewUtilsApi22();
    } else if (i >= 21) {
      IMPL = new ViewUtilsApi21();
    } else if (i >= 19) {
      IMPL = new ViewUtilsApi19();
    } else if (i >= 18) {
      IMPL = new ViewUtilsApi18();
    } else {
      IMPL = new ViewUtilsApi14();
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/transition/ViewUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */