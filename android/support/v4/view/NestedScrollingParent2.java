package android.support.v4.view;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

public interface NestedScrollingParent2 extends NestedScrollingParent {
  void onNestedPreScroll(@NonNull View paramView, int paramInt1, int paramInt2, @Nullable int[] paramArrayOfint, int paramInt3);
  
  void onNestedScroll(@NonNull View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5);
  
  void onNestedScrollAccepted(@NonNull View paramView1, @NonNull View paramView2, int paramInt1, int paramInt2);
  
  boolean onStartNestedScroll(@NonNull View paramView1, @NonNull View paramView2, int paramInt1, int paramInt2);
  
  void onStopNestedScroll(@NonNull View paramView, int paramInt);
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/view/NestedScrollingParent2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */