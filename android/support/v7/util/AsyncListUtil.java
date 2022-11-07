package android.support.v7.util;

import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;

public class AsyncListUtil<T> {
  static final boolean DEBUG = false;
  
  static final String TAG = "AsyncListUtil";
  
  boolean mAllowScrollHints;
  
  private final ThreadUtil.BackgroundCallback<T> mBackgroundCallback;
  
  final ThreadUtil.BackgroundCallback<T> mBackgroundProxy;
  
  final DataCallback<T> mDataCallback;
  
  int mDisplayedGeneration = 0;
  
  int mItemCount = 0;
  
  private final ThreadUtil.MainThreadCallback<T> mMainThreadCallback;
  
  final ThreadUtil.MainThreadCallback<T> mMainThreadProxy;
  
  final SparseIntArray mMissingPositions = new SparseIntArray();
  
  final int[] mPrevRange = new int[2];
  
  int mRequestedGeneration = 0;
  
  private int mScrollHint = 0;
  
  final Class<T> mTClass;
  
  final TileList<T> mTileList;
  
  final int mTileSize;
  
  final int[] mTmpRange = new int[2];
  
  final int[] mTmpRangeExtended = new int[2];
  
  final ViewCallback mViewCallback;
  
  public AsyncListUtil(Class<T> paramClass, int paramInt, DataCallback<T> paramDataCallback, ViewCallback paramViewCallback) {
    ThreadUtil.MainThreadCallback<T> mainThreadCallback = new ThreadUtil.MainThreadCallback<T>() {
        private boolean isRequestedGeneration(int param1Int) {
          boolean bool;
          if (param1Int == AsyncListUtil.this.mRequestedGeneration) {
            bool = true;
          } else {
            bool = false;
          } 
          return bool;
        }
        
        private void recycleAllTiles() {
          for (byte b = 0; b < AsyncListUtil.this.mTileList.size(); b++) {
            AsyncListUtil asyncListUtil = AsyncListUtil.this;
            asyncListUtil.mBackgroundProxy.recycleTile(asyncListUtil.mTileList.getAtIndex(b));
          } 
          AsyncListUtil.this.mTileList.clear();
        }
        
        public void addTile(int param1Int, TileList.Tile<T> param1Tile) {
          if (!isRequestedGeneration(param1Int)) {
            AsyncListUtil.this.mBackgroundProxy.recycleTile(param1Tile);
            return;
          } 
          TileList.Tile<T> tile = AsyncListUtil.this.mTileList.addOrReplace(param1Tile);
          if (tile != null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("duplicate tile @");
            stringBuilder.append(tile.mStartPosition);
            Log.e("AsyncListUtil", stringBuilder.toString());
            AsyncListUtil.this.mBackgroundProxy.recycleTile(tile);
          } 
          int i = param1Tile.mStartPosition;
          int j = param1Tile.mItemCount;
          for (param1Int = 0; param1Int < AsyncListUtil.this.mMissingPositions.size(); param1Int++) {
            int k = AsyncListUtil.this.mMissingPositions.keyAt(param1Int);
            if (param1Tile.mStartPosition <= k && k < i + j) {
              AsyncListUtil.this.mMissingPositions.removeAt(param1Int);
              AsyncListUtil.this.mViewCallback.onItemLoaded(k);
              continue;
            } 
          } 
        }
        
        public void removeTile(int param1Int1, int param1Int2) {
          StringBuilder stringBuilder;
          if (!isRequestedGeneration(param1Int1))
            return; 
          TileList.Tile tile = AsyncListUtil.this.mTileList.removeAtPos(param1Int2);
          if (tile == null) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("tile not found @");
            stringBuilder.append(param1Int2);
            Log.e("AsyncListUtil", stringBuilder.toString());
            return;
          } 
          AsyncListUtil.this.mBackgroundProxy.recycleTile((TileList.Tile)stringBuilder);
        }
        
        public void updateItemCount(int param1Int1, int param1Int2) {
          if (!isRequestedGeneration(param1Int1))
            return; 
          AsyncListUtil asyncListUtil = AsyncListUtil.this;
          asyncListUtil.mItemCount = param1Int2;
          asyncListUtil.mViewCallback.onDataRefresh();
          asyncListUtil = AsyncListUtil.this;
          asyncListUtil.mDisplayedGeneration = asyncListUtil.mRequestedGeneration;
          recycleAllTiles();
          asyncListUtil = AsyncListUtil.this;
          asyncListUtil.mAllowScrollHints = false;
          asyncListUtil.updateRange();
        }
      };
    this.mMainThreadCallback = mainThreadCallback;
    ThreadUtil.BackgroundCallback<T> backgroundCallback = new ThreadUtil.BackgroundCallback<T>() {
        private int mFirstRequiredTileStart;
        
        private int mGeneration;
        
        private int mItemCount;
        
        private int mLastRequiredTileStart;
        
        final SparseBooleanArray mLoadedTiles = new SparseBooleanArray();
        
        private TileList.Tile<T> mRecycledRoot;
        
        private TileList.Tile<T> acquireTile() {
          TileList.Tile<T> tile = this.mRecycledRoot;
          if (tile != null) {
            this.mRecycledRoot = tile.mNext;
            return tile;
          } 
          AsyncListUtil asyncListUtil = AsyncListUtil.this;
          return new TileList.Tile<T>(asyncListUtil.mTClass, asyncListUtil.mTileSize);
        }
        
        private void addTile(TileList.Tile<T> param1Tile) {
          this.mLoadedTiles.put(param1Tile.mStartPosition, true);
          AsyncListUtil.this.mMainThreadProxy.addTile(this.mGeneration, param1Tile);
        }
        
        private void flushTileCache(int param1Int) {
          int i = AsyncListUtil.this.mDataCallback.getMaxCachedTiles();
          while (this.mLoadedTiles.size() >= i) {
            int j = this.mLoadedTiles.keyAt(0);
            SparseBooleanArray sparseBooleanArray = this.mLoadedTiles;
            int k = sparseBooleanArray.keyAt(sparseBooleanArray.size() - 1);
            int m = this.mFirstRequiredTileStart - j;
            int n = k - this.mLastRequiredTileStart;
            if (m > 0 && (m >= n || param1Int == 2)) {
              removeTile(j);
              continue;
            } 
            if (n > 0 && (m < n || param1Int == 1))
              removeTile(k); 
          } 
        }
        
        private int getTileStart(int param1Int) {
          return param1Int - param1Int % AsyncListUtil.this.mTileSize;
        }
        
        private boolean isTileLoaded(int param1Int) {
          return this.mLoadedTiles.get(param1Int);
        }
        
        private void log(String param1String, Object... param1VarArgs) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("[BKGR] ");
          stringBuilder.append(String.format(param1String, param1VarArgs));
          Log.d("AsyncListUtil", stringBuilder.toString());
        }
        
        private void removeTile(int param1Int) {
          this.mLoadedTiles.delete(param1Int);
          AsyncListUtil.this.mMainThreadProxy.removeTile(this.mGeneration, param1Int);
        }
        
        private void requestTiles(int param1Int1, int param1Int2, int param1Int3, boolean param1Boolean) {
          int i;
          for (i = param1Int1; i <= param1Int2; i += AsyncListUtil.this.mTileSize) {
            int j;
            if (param1Boolean) {
              j = param1Int2 + param1Int1 - i;
            } else {
              j = i;
            } 
            AsyncListUtil.this.mBackgroundProxy.loadTile(j, param1Int3);
          } 
        }
        
        public void loadTile(int param1Int1, int param1Int2) {
          if (isTileLoaded(param1Int1))
            return; 
          TileList.Tile<T> tile = acquireTile();
          tile.mStartPosition = param1Int1;
          param1Int1 = Math.min(AsyncListUtil.this.mTileSize, this.mItemCount - param1Int1);
          tile.mItemCount = param1Int1;
          AsyncListUtil.this.mDataCallback.fillData(tile.mItems, tile.mStartPosition, param1Int1);
          flushTileCache(param1Int2);
          addTile(tile);
        }
        
        public void recycleTile(TileList.Tile<T> param1Tile) {
          AsyncListUtil.this.mDataCallback.recycleData(param1Tile.mItems, param1Tile.mItemCount);
          param1Tile.mNext = this.mRecycledRoot;
          this.mRecycledRoot = param1Tile;
        }
        
        public void refresh(int param1Int) {
          this.mGeneration = param1Int;
          this.mLoadedTiles.clear();
          param1Int = AsyncListUtil.this.mDataCallback.refreshData();
          this.mItemCount = param1Int;
          AsyncListUtil.this.mMainThreadProxy.updateItemCount(this.mGeneration, param1Int);
        }
        
        public void updateRange(int param1Int1, int param1Int2, int param1Int3, int param1Int4, int param1Int5) {
          if (param1Int1 > param1Int2)
            return; 
          param1Int1 = getTileStart(param1Int1);
          param1Int2 = getTileStart(param1Int2);
          this.mFirstRequiredTileStart = getTileStart(param1Int3);
          param1Int3 = getTileStart(param1Int4);
          this.mLastRequiredTileStart = param1Int3;
          if (param1Int5 == 1) {
            requestTiles(this.mFirstRequiredTileStart, param1Int2, param1Int5, true);
            requestTiles(param1Int2 + AsyncListUtil.this.mTileSize, this.mLastRequiredTileStart, param1Int5, false);
          } else {
            requestTiles(param1Int1, param1Int3, param1Int5, false);
            requestTiles(this.mFirstRequiredTileStart, param1Int1 - AsyncListUtil.this.mTileSize, param1Int5, true);
          } 
        }
      };
    this.mBackgroundCallback = backgroundCallback;
    this.mTClass = paramClass;
    this.mTileSize = paramInt;
    this.mDataCallback = paramDataCallback;
    this.mViewCallback = paramViewCallback;
    this.mTileList = new TileList<T>(paramInt);
    MessageThreadUtil<T> messageThreadUtil = new MessageThreadUtil();
    this.mMainThreadProxy = messageThreadUtil.getMainThreadProxy(mainThreadCallback);
    this.mBackgroundProxy = messageThreadUtil.getBackgroundProxy(backgroundCallback);
    refresh();
  }
  
  private boolean isRefreshPending() {
    boolean bool;
    if (this.mRequestedGeneration != this.mDisplayedGeneration) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public T getItem(int paramInt) {
    if (paramInt >= 0 && paramInt < this.mItemCount) {
      T t = this.mTileList.getItemAt(paramInt);
      if (t == null && !isRefreshPending())
        this.mMissingPositions.put(paramInt, 0); 
      return t;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramInt);
    stringBuilder.append(" is not within 0 and ");
    stringBuilder.append(this.mItemCount);
    throw new IndexOutOfBoundsException(stringBuilder.toString());
  }
  
  public int getItemCount() {
    return this.mItemCount;
  }
  
  void log(String paramString, Object... paramVarArgs) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("[MAIN] ");
    stringBuilder.append(String.format(paramString, paramVarArgs));
    Log.d("AsyncListUtil", stringBuilder.toString());
  }
  
  public void onRangeChanged() {
    if (isRefreshPending())
      return; 
    updateRange();
    this.mAllowScrollHints = true;
  }
  
  public void refresh() {
    this.mMissingPositions.clear();
    ThreadUtil.BackgroundCallback<T> backgroundCallback = this.mBackgroundProxy;
    int i = this.mRequestedGeneration + 1;
    this.mRequestedGeneration = i;
    backgroundCallback.refresh(i);
  }
  
  void updateRange() {
    // Byte code:
    //   0: aload_0
    //   1: getfield mViewCallback : Landroid/support/v7/util/AsyncListUtil$ViewCallback;
    //   4: aload_0
    //   5: getfield mTmpRange : [I
    //   8: invokevirtual getItemRangeInto : ([I)V
    //   11: aload_0
    //   12: getfield mTmpRange : [I
    //   15: astore_1
    //   16: aload_1
    //   17: iconst_0
    //   18: iaload
    //   19: istore_2
    //   20: aload_1
    //   21: iconst_1
    //   22: iaload
    //   23: istore_3
    //   24: iload_2
    //   25: iload_3
    //   26: if_icmpgt -> 256
    //   29: iload_2
    //   30: ifge -> 36
    //   33: goto -> 256
    //   36: iload_3
    //   37: aload_0
    //   38: getfield mItemCount : I
    //   41: if_icmplt -> 45
    //   44: return
    //   45: aload_0
    //   46: getfield mAllowScrollHints : Z
    //   49: ifne -> 60
    //   52: aload_0
    //   53: iconst_0
    //   54: putfield mScrollHint : I
    //   57: goto -> 122
    //   60: aload_0
    //   61: getfield mPrevRange : [I
    //   64: astore #4
    //   66: iload_2
    //   67: aload #4
    //   69: iconst_1
    //   70: iaload
    //   71: if_icmpgt -> 117
    //   74: aload #4
    //   76: iconst_0
    //   77: iaload
    //   78: istore #5
    //   80: iload #5
    //   82: iload_3
    //   83: if_icmple -> 89
    //   86: goto -> 117
    //   89: iload_2
    //   90: iload #5
    //   92: if_icmpge -> 103
    //   95: aload_0
    //   96: iconst_1
    //   97: putfield mScrollHint : I
    //   100: goto -> 122
    //   103: iload_2
    //   104: iload #5
    //   106: if_icmple -> 122
    //   109: aload_0
    //   110: iconst_2
    //   111: putfield mScrollHint : I
    //   114: goto -> 122
    //   117: aload_0
    //   118: iconst_0
    //   119: putfield mScrollHint : I
    //   122: aload_0
    //   123: getfield mPrevRange : [I
    //   126: astore #4
    //   128: aload #4
    //   130: iconst_0
    //   131: iload_2
    //   132: iastore
    //   133: aload #4
    //   135: iconst_1
    //   136: iload_3
    //   137: iastore
    //   138: aload_0
    //   139: getfield mViewCallback : Landroid/support/v7/util/AsyncListUtil$ViewCallback;
    //   142: aload_1
    //   143: aload_0
    //   144: getfield mTmpRangeExtended : [I
    //   147: aload_0
    //   148: getfield mScrollHint : I
    //   151: invokevirtual extendRangeInto : ([I[II)V
    //   154: aload_0
    //   155: getfield mTmpRangeExtended : [I
    //   158: astore_1
    //   159: aload_1
    //   160: iconst_0
    //   161: aload_0
    //   162: getfield mTmpRange : [I
    //   165: iconst_0
    //   166: iaload
    //   167: aload_1
    //   168: iconst_0
    //   169: iaload
    //   170: iconst_0
    //   171: invokestatic max : (II)I
    //   174: invokestatic min : (II)I
    //   177: iastore
    //   178: aload_0
    //   179: getfield mTmpRangeExtended : [I
    //   182: astore_1
    //   183: aload_1
    //   184: iconst_1
    //   185: aload_0
    //   186: getfield mTmpRange : [I
    //   189: iconst_1
    //   190: iaload
    //   191: aload_1
    //   192: iconst_1
    //   193: iaload
    //   194: aload_0
    //   195: getfield mItemCount : I
    //   198: iconst_1
    //   199: isub
    //   200: invokestatic min : (II)I
    //   203: invokestatic max : (II)I
    //   206: iastore
    //   207: aload_0
    //   208: getfield mBackgroundProxy : Landroid/support/v7/util/ThreadUtil$BackgroundCallback;
    //   211: astore_1
    //   212: aload_0
    //   213: getfield mTmpRange : [I
    //   216: astore #4
    //   218: aload #4
    //   220: iconst_0
    //   221: iaload
    //   222: istore_3
    //   223: aload #4
    //   225: iconst_1
    //   226: iaload
    //   227: istore #5
    //   229: aload_0
    //   230: getfield mTmpRangeExtended : [I
    //   233: astore #4
    //   235: aload_1
    //   236: iload_3
    //   237: iload #5
    //   239: aload #4
    //   241: iconst_0
    //   242: iaload
    //   243: aload #4
    //   245: iconst_1
    //   246: iaload
    //   247: aload_0
    //   248: getfield mScrollHint : I
    //   251: invokeinterface updateRange : (IIIII)V
    //   256: return
  }
  
  public static abstract class DataCallback<T> {
    @WorkerThread
    public abstract void fillData(T[] param1ArrayOfT, int param1Int1, int param1Int2);
    
    @WorkerThread
    public int getMaxCachedTiles() {
      return 10;
    }
    
    @WorkerThread
    public void recycleData(T[] param1ArrayOfT, int param1Int) {}
    
    @WorkerThread
    public abstract int refreshData();
  }
  
  public static abstract class ViewCallback {
    public static final int HINT_SCROLL_ASC = 2;
    
    public static final int HINT_SCROLL_DESC = 1;
    
    public static final int HINT_SCROLL_NONE = 0;
    
    @UiThread
    public void extendRangeInto(int[] param1ArrayOfint1, int[] param1ArrayOfint2, int param1Int) {
      int n;
      int i = param1ArrayOfint1[1];
      int j = param1ArrayOfint1[0];
      int k = i - j + 1;
      int m = k / 2;
      if (param1Int == 1) {
        n = k;
      } else {
        n = m;
      } 
      param1ArrayOfint2[0] = j - n;
      if (param1Int == 2)
        m = k; 
      param1ArrayOfint2[1] = i + m;
    }
    
    @UiThread
    public abstract void getItemRangeInto(int[] param1ArrayOfint);
    
    @UiThread
    public abstract void onDataRefresh();
    
    @UiThread
    public abstract void onItemLoaded(int param1Int);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/util/AsyncListUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */