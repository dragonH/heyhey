package android.support.transition;

import android.support.annotation.RequiresApi;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.LongSparseArray;
import android.util.SparseArray;
import android.view.View;

@RequiresApi(14)
class TransitionValuesMaps {
  final SparseArray<View> mIdValues = new SparseArray();
  
  final LongSparseArray<View> mItemIdValues = new LongSparseArray();
  
  final ArrayMap<String, View> mNameValues = new ArrayMap();
  
  final ArrayMap<View, TransitionValues> mViewValues = new ArrayMap();
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/transition/TransitionValuesMaps.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */