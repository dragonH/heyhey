package android.support.v7.app;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.mediarouter.R;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

final class MediaRouteDialogHelper {
  public static int getDialogWidth(Context paramContext) {
    DisplayMetrics displayMetrics = paramContext.getResources().getDisplayMetrics();
    if (displayMetrics.widthPixels < displayMetrics.heightPixels) {
      i = 1;
    } else {
      i = 0;
    } 
    TypedValue typedValue = new TypedValue();
    Resources resources = paramContext.getResources();
    if (i) {
      i = R.dimen.mr_dialog_fixed_width_minor;
    } else {
      i = R.dimen.mr_dialog_fixed_width_major;
    } 
    resources.getValue(i, typedValue, true);
    int i = typedValue.type;
    if (i == 5) {
      float f = typedValue.getDimension(displayMetrics);
      return (int)f;
    } 
    if (i == 6) {
      i = displayMetrics.widthPixels;
      float f = typedValue.getFraction(i, i);
      return (int)f;
    } 
    return -2;
  }
  
  public static <E> HashMap<E, BitmapDrawable> getItemBitmapMap(Context paramContext, ListView paramListView, ArrayAdapter<E> paramArrayAdapter) {
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    int i = paramListView.getFirstVisiblePosition();
    for (byte b = 0; b < paramListView.getChildCount(); b++)
      hashMap.put(paramArrayAdapter.getItem(i + b), getViewBitmap(paramContext, paramListView.getChildAt(b))); 
    return (HashMap)hashMap;
  }
  
  public static <E> HashMap<E, Rect> getItemBoundMap(ListView paramListView, ArrayAdapter<E> paramArrayAdapter) {
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    int i = paramListView.getFirstVisiblePosition();
    for (byte b = 0; b < paramListView.getChildCount(); b++) {
      Object object = paramArrayAdapter.getItem(i + b);
      View view = paramListView.getChildAt(b);
      hashMap.put(object, new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()));
    } 
    return (HashMap)hashMap;
  }
  
  public static <E> Set<E> getItemsAdded(List<E> paramList1, List<E> paramList2) {
    HashSet<E> hashSet = new HashSet<E>(paramList2);
    hashSet.removeAll(paramList1);
    return hashSet;
  }
  
  public static <E> Set<E> getItemsRemoved(List<E> paramList1, List<E> paramList2) {
    HashSet<E> hashSet = new HashSet<E>(paramList1);
    hashSet.removeAll(paramList2);
    return hashSet;
  }
  
  private static BitmapDrawable getViewBitmap(Context paramContext, View paramView) {
    Bitmap bitmap = Bitmap.createBitmap(paramView.getWidth(), paramView.getHeight(), Bitmap.Config.ARGB_8888);
    paramView.draw(new Canvas(bitmap));
    return new BitmapDrawable(paramContext.getResources(), bitmap);
  }
  
  public static <E> boolean listUnorderedEquals(List<E> paramList1, List<E> paramList2) {
    return (new HashSet(paramList1)).equals(new HashSet<E>(paramList2));
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/app/MediaRouteDialogHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */