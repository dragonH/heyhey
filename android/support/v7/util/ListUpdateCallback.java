package android.support.v7.util;

public interface ListUpdateCallback {
  void onChanged(int paramInt1, int paramInt2, Object paramObject);
  
  void onInserted(int paramInt1, int paramInt2);
  
  void onMoved(int paramInt1, int paramInt2);
  
  void onRemoved(int paramInt1, int paramInt2);
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/util/ListUpdateCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */