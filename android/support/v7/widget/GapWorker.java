package android.support.v7.widget;

import android.support.annotation.Nullable;
import android.support.v4.os.TraceCompat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

final class GapWorker implements Runnable {
  static final ThreadLocal<GapWorker> sGapWorker = new ThreadLocal<GapWorker>();
  
  static Comparator<Task> sTaskComparator = new Comparator<Task>() {
      public int compare(GapWorker.Task param1Task1, GapWorker.Task param1Task2) {
        byte b2;
        RecyclerView recyclerView = param1Task1.view;
        byte b = 1;
        byte b1 = 1;
        if (recyclerView == null) {
          i = 1;
        } else {
          i = 0;
        } 
        if (param1Task2.view == null) {
          b2 = 1;
        } else {
          b2 = 0;
        } 
        if (i != b2) {
          if (recyclerView == null) {
            i = b1;
          } else {
            i = -1;
          } 
          return i;
        } 
        boolean bool = param1Task1.immediate;
        if (bool != param1Task2.immediate) {
          i = b;
          if (bool)
            i = -1; 
          return i;
        } 
        int i = param1Task2.viewVelocity - param1Task1.viewVelocity;
        if (i != 0)
          return i; 
        i = param1Task1.distanceToItem - param1Task2.distanceToItem;
        return (i != 0) ? i : 0;
      }
    };
  
  long mFrameIntervalNs;
  
  long mPostTimeNs;
  
  ArrayList<RecyclerView> mRecyclerViews = new ArrayList<RecyclerView>();
  
  private ArrayList<Task> mTasks = new ArrayList<Task>();
  
  private void buildTaskList() {
    int i = this.mRecyclerViews.size();
    byte b = 0;
    int j;
    for (j = 0; b < i; j = m) {
      RecyclerView recyclerView = this.mRecyclerViews.get(b);
      int m = j;
      if (recyclerView.getWindowVisibility() == 0) {
        recyclerView.mPrefetchRegistry.collectPrefetchPositionsFromView(recyclerView, false);
        m = j + recyclerView.mPrefetchRegistry.mCount;
      } 
      b++;
    } 
    this.mTasks.ensureCapacity(j);
    b = 0;
    int k;
    for (k = 0; b < i; k = m) {
      int m;
      RecyclerView recyclerView = this.mRecyclerViews.get(b);
      if (recyclerView.getWindowVisibility() != 0) {
        m = k;
      } else {
        LayoutPrefetchRegistryImpl layoutPrefetchRegistryImpl = recyclerView.mPrefetchRegistry;
        int n = Math.abs(layoutPrefetchRegistryImpl.mPrefetchDx) + Math.abs(layoutPrefetchRegistryImpl.mPrefetchDy);
        j = 0;
        while (true) {
          m = k;
          if (j < layoutPrefetchRegistryImpl.mCount * 2) {
            Task task;
            boolean bool;
            if (k >= this.mTasks.size()) {
              task = new Task();
              this.mTasks.add(task);
            } else {
              task = this.mTasks.get(k);
            } 
            int[] arrayOfInt = layoutPrefetchRegistryImpl.mPrefetchArray;
            m = arrayOfInt[j + 1];
            if (m <= n) {
              bool = true;
            } else {
              bool = false;
            } 
            task.immediate = bool;
            task.viewVelocity = n;
            task.distanceToItem = m;
            task.view = recyclerView;
            task.position = arrayOfInt[j];
            k++;
            j += 2;
            continue;
          } 
          break;
        } 
      } 
      b++;
    } 
    Collections.sort(this.mTasks, sTaskComparator);
  }
  
  private void flushTaskWithDeadline(Task paramTask, long paramLong) {
    long l;
    if (paramTask.immediate) {
      l = Long.MAX_VALUE;
    } else {
      l = paramLong;
    } 
    RecyclerView.ViewHolder viewHolder = prefetchPositionWithDeadline(paramTask.view, paramTask.position, l);
    if (viewHolder != null && viewHolder.mNestedRecyclerView != null && viewHolder.isBound() && !viewHolder.isInvalid())
      prefetchInnerRecyclerViewWithDeadline(viewHolder.mNestedRecyclerView.get(), paramLong); 
  }
  
  private void flushTasksWithDeadline(long paramLong) {
    for (byte b = 0; b < this.mTasks.size(); b++) {
      Task task = this.mTasks.get(b);
      if (task.view == null)
        break; 
      flushTaskWithDeadline(task, paramLong);
      task.clear();
    } 
  }
  
  static boolean isPrefetchPositionAttached(RecyclerView paramRecyclerView, int paramInt) {
    int i = paramRecyclerView.mChildHelper.getUnfilteredChildCount();
    for (byte b = 0; b < i; b++) {
      RecyclerView.ViewHolder viewHolder = RecyclerView.getChildViewHolderInt(paramRecyclerView.mChildHelper.getUnfilteredChildAt(b));
      if (viewHolder.mPosition == paramInt && !viewHolder.isInvalid())
        return true; 
    } 
    return false;
  }
  
  private void prefetchInnerRecyclerViewWithDeadline(@Nullable RecyclerView paramRecyclerView, long paramLong) {
    if (paramRecyclerView == null)
      return; 
    if (paramRecyclerView.mDataSetHasChangedAfterLayout && paramRecyclerView.mChildHelper.getUnfilteredChildCount() != 0)
      paramRecyclerView.removeAndRecycleViews(); 
    LayoutPrefetchRegistryImpl layoutPrefetchRegistryImpl = paramRecyclerView.mPrefetchRegistry;
    layoutPrefetchRegistryImpl.collectPrefetchPositionsFromView(paramRecyclerView, true);
    if (layoutPrefetchRegistryImpl.mCount != 0)
      try {
        TraceCompat.beginSection("RV Nested Prefetch");
        paramRecyclerView.mState.prepareForNestedPrefetch(paramRecyclerView.mAdapter);
        for (byte b = 0; b < layoutPrefetchRegistryImpl.mCount * 2; b += 2)
          prefetchPositionWithDeadline(paramRecyclerView, layoutPrefetchRegistryImpl.mPrefetchArray[b], paramLong); 
      } finally {
        TraceCompat.endSection();
      }  
  }
  
  private RecyclerView.ViewHolder prefetchPositionWithDeadline(RecyclerView paramRecyclerView, int paramInt, long paramLong) {
    if (isPrefetchPositionAttached(paramRecyclerView, paramInt))
      return null; 
    RecyclerView.Recycler recycler = paramRecyclerView.mRecycler;
    try {
      paramRecyclerView.onEnterLayoutOrScroll();
      RecyclerView.ViewHolder viewHolder = recycler.tryGetViewHolderForPositionByDeadline(paramInt, false, paramLong);
      if (viewHolder != null)
        if (viewHolder.isBound() && !viewHolder.isInvalid()) {
          recycler.recycleView(viewHolder.itemView);
        } else {
          recycler.addViewHolderToRecycledViewPool(viewHolder, false);
        }  
      return viewHolder;
    } finally {
      paramRecyclerView.onExitLayoutOrScroll(false);
    } 
  }
  
  public void add(RecyclerView paramRecyclerView) {
    this.mRecyclerViews.add(paramRecyclerView);
  }
  
  void postFromTraversal(RecyclerView paramRecyclerView, int paramInt1, int paramInt2) {
    if (paramRecyclerView.isAttachedToWindow() && this.mPostTimeNs == 0L) {
      this.mPostTimeNs = paramRecyclerView.getNanoTime();
      paramRecyclerView.post(this);
    } 
    paramRecyclerView.mPrefetchRegistry.setPrefetchVector(paramInt1, paramInt2);
  }
  
  void prefetch(long paramLong) {
    buildTaskList();
    flushTasksWithDeadline(paramLong);
  }
  
  public void remove(RecyclerView paramRecyclerView) {
    this.mRecyclerViews.remove(paramRecyclerView);
  }
  
  public void run() {
    try {
      TraceCompat.beginSection("RV Prefetch");
      boolean bool = this.mRecyclerViews.isEmpty();
      if (!bool) {
        int i = this.mRecyclerViews.size();
        byte b = 0;
        long l;
        for (l = 0L; b < i; l = l1) {
          RecyclerView recyclerView = this.mRecyclerViews.get(b);
          long l1 = l;
          if (recyclerView.getWindowVisibility() == 0)
            l1 = Math.max(recyclerView.getDrawingTime(), l); 
          b++;
        } 
        if (l != 0L) {
          prefetch(TimeUnit.MILLISECONDS.toNanos(l) + this.mFrameIntervalNs);
          return;
        } 
      } 
      return;
    } finally {
      this.mPostTimeNs = 0L;
      TraceCompat.endSection();
    } 
  }
  
  static class LayoutPrefetchRegistryImpl implements RecyclerView.LayoutManager.LayoutPrefetchRegistry {
    int mCount;
    
    int[] mPrefetchArray;
    
    int mPrefetchDx;
    
    int mPrefetchDy;
    
    public void addPosition(int param1Int1, int param1Int2) {
      if (param1Int1 >= 0) {
        if (param1Int2 >= 0) {
          int i = this.mCount * 2;
          int[] arrayOfInt = this.mPrefetchArray;
          if (arrayOfInt == null) {
            arrayOfInt = new int[4];
            this.mPrefetchArray = arrayOfInt;
            Arrays.fill(arrayOfInt, -1);
          } else if (i >= arrayOfInt.length) {
            int[] arrayOfInt1 = new int[i * 2];
            this.mPrefetchArray = arrayOfInt1;
            System.arraycopy(arrayOfInt, 0, arrayOfInt1, 0, arrayOfInt.length);
          } 
          arrayOfInt = this.mPrefetchArray;
          arrayOfInt[i] = param1Int1;
          arrayOfInt[i + 1] = param1Int2;
          this.mCount++;
          return;
        } 
        throw new IllegalArgumentException("Pixel distance must be non-negative");
      } 
      throw new IllegalArgumentException("Layout positions must be non-negative");
    }
    
    void clearPrefetchPositions() {
      int[] arrayOfInt = this.mPrefetchArray;
      if (arrayOfInt != null)
        Arrays.fill(arrayOfInt, -1); 
      this.mCount = 0;
    }
    
    void collectPrefetchPositionsFromView(RecyclerView param1RecyclerView, boolean param1Boolean) {
      this.mCount = 0;
      int[] arrayOfInt = this.mPrefetchArray;
      if (arrayOfInt != null)
        Arrays.fill(arrayOfInt, -1); 
      RecyclerView.LayoutManager layoutManager = param1RecyclerView.mLayout;
      if (param1RecyclerView.mAdapter != null && layoutManager != null && layoutManager.isItemPrefetchEnabled()) {
        if (param1Boolean) {
          if (!param1RecyclerView.mAdapterHelper.hasPendingUpdates())
            layoutManager.collectInitialPrefetchPositions(param1RecyclerView.mAdapter.getItemCount(), this); 
        } else if (!param1RecyclerView.hasPendingAdapterUpdates()) {
          layoutManager.collectAdjacentPrefetchPositions(this.mPrefetchDx, this.mPrefetchDy, param1RecyclerView.mState, this);
        } 
        int i = this.mCount;
        if (i > layoutManager.mPrefetchMaxCountObserved) {
          layoutManager.mPrefetchMaxCountObserved = i;
          layoutManager.mPrefetchMaxObservedInInitialPrefetch = param1Boolean;
          param1RecyclerView.mRecycler.updateViewCacheSize();
        } 
      } 
    }
    
    boolean lastPrefetchIncludedPosition(int param1Int) {
      if (this.mPrefetchArray != null) {
        int i = this.mCount;
        for (byte b = 0; b < i * 2; b += 2) {
          if (this.mPrefetchArray[b] == param1Int)
            return true; 
        } 
      } 
      return false;
    }
    
    void setPrefetchVector(int param1Int1, int param1Int2) {
      this.mPrefetchDx = param1Int1;
      this.mPrefetchDy = param1Int2;
    }
  }
  
  static class Task {
    public int distanceToItem;
    
    public boolean immediate;
    
    public int position;
    
    public RecyclerView view;
    
    public int viewVelocity;
    
    public void clear() {
      this.immediate = false;
      this.viewVelocity = 0;
      this.distanceToItem = 0;
      this.view = null;
      this.position = 0;
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/widget/GapWorker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */