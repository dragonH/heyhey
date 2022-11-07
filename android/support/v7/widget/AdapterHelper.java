package android.support.v7.widget;

import android.support.v4.util.Pools;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class AdapterHelper implements OpReorderer.Callback {
  private static final boolean DEBUG = false;
  
  static final int POSITION_TYPE_INVISIBLE = 0;
  
  static final int POSITION_TYPE_NEW_OR_LAID_OUT = 1;
  
  private static final String TAG = "AHT";
  
  final Callback mCallback;
  
  final boolean mDisableRecycler;
  
  private int mExistingUpdateTypes = 0;
  
  Runnable mOnItemProcessedCallback;
  
  final OpReorderer mOpReorderer;
  
  final ArrayList<UpdateOp> mPendingUpdates = new ArrayList<UpdateOp>();
  
  final ArrayList<UpdateOp> mPostponedList = new ArrayList<UpdateOp>();
  
  private Pools.Pool<UpdateOp> mUpdateOpPool = (Pools.Pool<UpdateOp>)new Pools.SimplePool(30);
  
  AdapterHelper(Callback paramCallback) {
    this(paramCallback, false);
  }
  
  AdapterHelper(Callback paramCallback, boolean paramBoolean) {
    this.mCallback = paramCallback;
    this.mDisableRecycler = paramBoolean;
    this.mOpReorderer = new OpReorderer(this);
  }
  
  private void applyAdd(UpdateOp paramUpdateOp) {
    postponeAndUpdateViewHolders(paramUpdateOp);
  }
  
  private void applyMove(UpdateOp paramUpdateOp) {
    postponeAndUpdateViewHolders(paramUpdateOp);
  }
  
  private void applyRemove(UpdateOp paramUpdateOp) {
    int i = paramUpdateOp.positionStart;
    int j = paramUpdateOp.itemCount + i;
    byte b = -1;
    int k = i;
    int m;
    for (m = 0; k < j; m = n) {
      int n;
      if (this.mCallback.findViewHolder(k) != null || canFindInPreLayout(k)) {
        if (b == 0) {
          dispatchAndUpdateViewHolders(obtainUpdateOp(2, i, m, null));
          n = 1;
        } else {
          n = 0;
        } 
        b = 1;
      } else {
        if (b == 1) {
          postponeAndUpdateViewHolders(obtainUpdateOp(2, i, m, null));
          b = 1;
        } else {
          b = 0;
        } 
        boolean bool = false;
        n = b;
        b = bool;
      } 
      if (n != 0) {
        k -= m;
        j -= m;
        n = 1;
      } else {
        n = m + 1;
      } 
      k++;
    } 
    UpdateOp updateOp = paramUpdateOp;
    if (m != paramUpdateOp.itemCount) {
      recycleUpdateOp(paramUpdateOp);
      updateOp = obtainUpdateOp(2, i, m, null);
    } 
    if (b == 0) {
      dispatchAndUpdateViewHolders(updateOp);
    } else {
      postponeAndUpdateViewHolders(updateOp);
    } 
  }
  
  private void applyUpdate(UpdateOp paramUpdateOp) {
    int i = paramUpdateOp.positionStart;
    int j = paramUpdateOp.itemCount;
    int k = i;
    int m = -1;
    int n = 0;
    int i1 = i;
    while (i1 < j + i) {
      int i2;
      if (this.mCallback.findViewHolder(i1) != null || canFindInPreLayout(i1)) {
        int i4 = k;
        i2 = n;
        if (m == 0) {
          dispatchAndUpdateViewHolders(obtainUpdateOp(4, k, n, paramUpdateOp.payload));
          i4 = i1;
          i2 = 0;
        } 
        n = 1;
        k = i4;
      } else {
        i2 = k;
        int i4 = n;
        if (m == 1) {
          postponeAndUpdateViewHolders(obtainUpdateOp(4, k, n, paramUpdateOp.payload));
          i2 = i1;
          i4 = 0;
        } 
        n = 0;
        k = i2;
        i2 = i4;
      } 
      int i3 = i2 + 1;
      i1++;
      m = n;
      n = i3;
    } 
    Object object = paramUpdateOp;
    if (n != paramUpdateOp.itemCount) {
      object = paramUpdateOp.payload;
      recycleUpdateOp(paramUpdateOp);
      object = obtainUpdateOp(4, k, n, object);
    } 
    if (m == 0) {
      dispatchAndUpdateViewHolders((UpdateOp)object);
    } else {
      postponeAndUpdateViewHolders((UpdateOp)object);
    } 
  }
  
  private boolean canFindInPreLayout(int paramInt) {
    int i = this.mPostponedList.size();
    for (byte b = 0; b < i; b++) {
      UpdateOp updateOp = this.mPostponedList.get(b);
      int j = updateOp.cmd;
      if (j == 8) {
        if (findPositionOffset(updateOp.itemCount, b + 1) == paramInt)
          return true; 
      } else if (j == 1) {
        int k = updateOp.positionStart;
        int m = updateOp.itemCount;
        for (j = k; j < m + k; j++) {
          if (findPositionOffset(j, b + 1) == paramInt)
            return true; 
        } 
      } 
    } 
    return false;
  }
  
  private void dispatchAndUpdateViewHolders(UpdateOp paramUpdateOp) {
    int i = paramUpdateOp.cmd;
    if (i != 1 && i != 8) {
      byte b1;
      int j = updatePositionWithPostponed(paramUpdateOp.positionStart, i);
      i = paramUpdateOp.positionStart;
      int k = paramUpdateOp.cmd;
      if (k != 2) {
        if (k == 4) {
          b1 = 1;
        } else {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("op should be remove or update.");
          stringBuilder.append(paramUpdateOp);
          throw new IllegalArgumentException(stringBuilder.toString());
        } 
      } else {
        b1 = 0;
      } 
      byte b2 = 1;
      k = 1;
      while (b2 < paramUpdateOp.itemCount) {
        int i1;
        int m = updatePositionWithPostponed(paramUpdateOp.positionStart + b1 * b2, paramUpdateOp.cmd);
        int n = paramUpdateOp.cmd;
        if ((n != 2) ? (n == 4 && m == j + 1) : (m == j)) {
          i1 = 1;
        } else {
          i1 = 0;
        } 
        if (i1) {
          k++;
        } else {
          UpdateOp updateOp = obtainUpdateOp(n, j, k, paramUpdateOp.payload);
          dispatchFirstPassAndUpdateViewHolders(updateOp, i);
          recycleUpdateOp(updateOp);
          j = i;
          if (paramUpdateOp.cmd == 4)
            j = i + k; 
          i1 = m;
          k = 1;
          i = j;
          j = i1;
        } 
        b2++;
      } 
      Object object = paramUpdateOp.payload;
      recycleUpdateOp(paramUpdateOp);
      if (k > 0) {
        paramUpdateOp = obtainUpdateOp(paramUpdateOp.cmd, j, k, object);
        dispatchFirstPassAndUpdateViewHolders(paramUpdateOp, i);
        recycleUpdateOp(paramUpdateOp);
      } 
      return;
    } 
    IllegalArgumentException illegalArgumentException = new IllegalArgumentException("should not dispatch add or move for pre layout");
    throw illegalArgumentException;
  }
  
  private void postponeAndUpdateViewHolders(UpdateOp paramUpdateOp) {
    this.mPostponedList.add(paramUpdateOp);
    int i = paramUpdateOp.cmd;
    if (i != 1) {
      if (i != 2) {
        if (i != 4) {
          if (i == 8) {
            this.mCallback.offsetPositionsForMove(paramUpdateOp.positionStart, paramUpdateOp.itemCount);
          } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Unknown update op type for ");
            stringBuilder.append(paramUpdateOp);
            throw new IllegalArgumentException(stringBuilder.toString());
          } 
        } else {
          this.mCallback.markViewHoldersUpdated(paramUpdateOp.positionStart, paramUpdateOp.itemCount, paramUpdateOp.payload);
        } 
      } else {
        this.mCallback.offsetPositionsForRemovingLaidOutOrNewView(paramUpdateOp.positionStart, paramUpdateOp.itemCount);
      } 
    } else {
      this.mCallback.offsetPositionsForAdd(paramUpdateOp.positionStart, paramUpdateOp.itemCount);
    } 
  }
  
  private int updatePositionWithPostponed(int paramInt1, int paramInt2) {
    int i = this.mPostponedList.size() - 1;
    int j;
    for (j = paramInt1; i >= 0; j = paramInt1) {
      UpdateOp updateOp = this.mPostponedList.get(i);
      int k = updateOp.cmd;
      if (k == 8) {
        int n;
        k = updateOp.positionStart;
        int m = updateOp.itemCount;
        if (k < m) {
          n = k;
          paramInt1 = m;
        } else {
          paramInt1 = k;
          n = m;
        } 
        if (j >= n && j <= paramInt1) {
          if (n == k) {
            if (paramInt2 == 1) {
              updateOp.itemCount = m + 1;
            } else if (paramInt2 == 2) {
              updateOp.itemCount = m - 1;
            } 
            paramInt1 = j + 1;
          } else {
            if (paramInt2 == 1) {
              updateOp.positionStart = k + 1;
            } else if (paramInt2 == 2) {
              updateOp.positionStart = k - 1;
            } 
            paramInt1 = j - 1;
          } 
        } else {
          paramInt1 = j;
          if (j < k)
            if (paramInt2 == 1) {
              updateOp.positionStart = k + 1;
              updateOp.itemCount = m + 1;
              paramInt1 = j;
            } else {
              paramInt1 = j;
              if (paramInt2 == 2) {
                updateOp.positionStart = k - 1;
                updateOp.itemCount = m - 1;
                paramInt1 = j;
              } 
            }  
        } 
      } else {
        int m = updateOp.positionStart;
        if (m <= j) {
          if (k == 1) {
            paramInt1 = j - updateOp.itemCount;
          } else {
            paramInt1 = j;
            if (k == 2)
              paramInt1 = j + updateOp.itemCount; 
          } 
        } else if (paramInt2 == 1) {
          updateOp.positionStart = m + 1;
          paramInt1 = j;
        } else {
          paramInt1 = j;
          if (paramInt2 == 2) {
            updateOp.positionStart = m - 1;
            paramInt1 = j;
          } 
        } 
      } 
      i--;
    } 
    for (paramInt1 = this.mPostponedList.size() - 1; paramInt1 >= 0; paramInt1--) {
      UpdateOp updateOp = this.mPostponedList.get(paramInt1);
      if (updateOp.cmd == 8) {
        paramInt2 = updateOp.itemCount;
        if (paramInt2 == updateOp.positionStart || paramInt2 < 0) {
          this.mPostponedList.remove(paramInt1);
          recycleUpdateOp(updateOp);
        } 
      } else if (updateOp.itemCount <= 0) {
        this.mPostponedList.remove(paramInt1);
        recycleUpdateOp(updateOp);
      } 
    } 
    return j;
  }
  
  AdapterHelper addUpdateOp(UpdateOp... paramVarArgs) {
    Collections.addAll(this.mPendingUpdates, paramVarArgs);
    return this;
  }
  
  public int applyPendingUpdatesToPosition(int paramInt) {
    int i = this.mPendingUpdates.size();
    byte b = 0;
    int j;
    for (j = paramInt; b < i; j = paramInt) {
      UpdateOp updateOp = this.mPendingUpdates.get(b);
      paramInt = updateOp.cmd;
      if (paramInt != 1) {
        if (paramInt != 2) {
          if (paramInt != 8) {
            paramInt = j;
          } else {
            paramInt = updateOp.positionStart;
            if (paramInt == j) {
              paramInt = updateOp.itemCount;
            } else {
              int k = j;
              if (paramInt < j)
                k = j - 1; 
              paramInt = k;
              if (updateOp.itemCount <= k)
                paramInt = k + 1; 
            } 
          } 
        } else {
          int k = updateOp.positionStart;
          paramInt = j;
          if (k <= j) {
            paramInt = updateOp.itemCount;
            if (k + paramInt > j)
              return -1; 
            paramInt = j - paramInt;
          } 
        } 
      } else {
        paramInt = j;
        if (updateOp.positionStart <= j)
          paramInt = j + updateOp.itemCount; 
      } 
      b++;
    } 
    return j;
  }
  
  void consumePostponedUpdates() {
    int i = this.mPostponedList.size();
    for (byte b = 0; b < i; b++)
      this.mCallback.onDispatchSecondPass(this.mPostponedList.get(b)); 
    recycleUpdateOpsAndClearList(this.mPostponedList);
    this.mExistingUpdateTypes = 0;
  }
  
  void consumeUpdatesInOnePass() {
    consumePostponedUpdates();
    int i = this.mPendingUpdates.size();
    for (byte b = 0; b < i; b++) {
      UpdateOp updateOp = this.mPendingUpdates.get(b);
      int j = updateOp.cmd;
      if (j != 1) {
        if (j != 2) {
          if (j != 4) {
            if (j == 8) {
              this.mCallback.onDispatchSecondPass(updateOp);
              this.mCallback.offsetPositionsForMove(updateOp.positionStart, updateOp.itemCount);
            } 
          } else {
            this.mCallback.onDispatchSecondPass(updateOp);
            this.mCallback.markViewHoldersUpdated(updateOp.positionStart, updateOp.itemCount, updateOp.payload);
          } 
        } else {
          this.mCallback.onDispatchSecondPass(updateOp);
          this.mCallback.offsetPositionsForRemovingInvisible(updateOp.positionStart, updateOp.itemCount);
        } 
      } else {
        this.mCallback.onDispatchSecondPass(updateOp);
        this.mCallback.offsetPositionsForAdd(updateOp.positionStart, updateOp.itemCount);
      } 
      Runnable runnable = this.mOnItemProcessedCallback;
      if (runnable != null)
        runnable.run(); 
    } 
    recycleUpdateOpsAndClearList(this.mPendingUpdates);
    this.mExistingUpdateTypes = 0;
  }
  
  void dispatchFirstPassAndUpdateViewHolders(UpdateOp paramUpdateOp, int paramInt) {
    this.mCallback.onDispatchFirstPass(paramUpdateOp);
    int i = paramUpdateOp.cmd;
    if (i != 2) {
      if (i == 4) {
        this.mCallback.markViewHoldersUpdated(paramInt, paramUpdateOp.itemCount, paramUpdateOp.payload);
      } else {
        throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
      } 
    } else {
      this.mCallback.offsetPositionsForRemovingInvisible(paramInt, paramUpdateOp.itemCount);
    } 
  }
  
  int findPositionOffset(int paramInt) {
    return findPositionOffset(paramInt, 0);
  }
  
  int findPositionOffset(int paramInt1, int paramInt2) {
    int i = this.mPostponedList.size();
    int j = paramInt2;
    for (paramInt2 = paramInt1; j < i; paramInt2 = paramInt1) {
      UpdateOp updateOp = this.mPostponedList.get(j);
      int k = updateOp.cmd;
      if (k == 8) {
        paramInt1 = updateOp.positionStart;
        if (paramInt1 == paramInt2) {
          paramInt1 = updateOp.itemCount;
        } else {
          int m = paramInt2;
          if (paramInt1 < paramInt2)
            m = paramInt2 - 1; 
          paramInt1 = m;
          if (updateOp.itemCount <= m)
            paramInt1 = m + 1; 
        } 
      } else {
        int m = updateOp.positionStart;
        paramInt1 = paramInt2;
        if (m <= paramInt2)
          if (k == 2) {
            paramInt1 = updateOp.itemCount;
            if (paramInt2 < m + paramInt1)
              return -1; 
            paramInt1 = paramInt2 - paramInt1;
          } else {
            paramInt1 = paramInt2;
            if (k == 1)
              paramInt1 = paramInt2 + updateOp.itemCount; 
          }  
      } 
      j++;
    } 
    return paramInt2;
  }
  
  boolean hasAnyUpdateTypes(int paramInt) {
    boolean bool;
    if ((paramInt & this.mExistingUpdateTypes) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  boolean hasPendingUpdates() {
    boolean bool;
    if (this.mPendingUpdates.size() > 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  boolean hasUpdates() {
    boolean bool;
    if (!this.mPostponedList.isEmpty() && !this.mPendingUpdates.isEmpty()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public UpdateOp obtainUpdateOp(int paramInt1, int paramInt2, int paramInt3, Object paramObject) {
    UpdateOp updateOp = (UpdateOp)this.mUpdateOpPool.acquire();
    if (updateOp == null) {
      paramObject = new UpdateOp(paramInt1, paramInt2, paramInt3, paramObject);
    } else {
      updateOp.cmd = paramInt1;
      updateOp.positionStart = paramInt2;
      updateOp.itemCount = paramInt3;
      updateOp.payload = paramObject;
      paramObject = updateOp;
    } 
    return (UpdateOp)paramObject;
  }
  
  boolean onItemRangeChanged(int paramInt1, int paramInt2, Object paramObject) {
    boolean bool = false;
    if (paramInt2 < 1)
      return false; 
    this.mPendingUpdates.add(obtainUpdateOp(4, paramInt1, paramInt2, paramObject));
    this.mExistingUpdateTypes |= 0x4;
    if (this.mPendingUpdates.size() == 1)
      bool = true; 
    return bool;
  }
  
  boolean onItemRangeInserted(int paramInt1, int paramInt2) {
    boolean bool = false;
    if (paramInt2 < 1)
      return false; 
    this.mPendingUpdates.add(obtainUpdateOp(1, paramInt1, paramInt2, null));
    this.mExistingUpdateTypes |= 0x1;
    if (this.mPendingUpdates.size() == 1)
      bool = true; 
    return bool;
  }
  
  boolean onItemRangeMoved(int paramInt1, int paramInt2, int paramInt3) {
    boolean bool = false;
    if (paramInt1 == paramInt2)
      return false; 
    if (paramInt3 == 1) {
      this.mPendingUpdates.add(obtainUpdateOp(8, paramInt1, paramInt2, null));
      this.mExistingUpdateTypes |= 0x8;
      if (this.mPendingUpdates.size() == 1)
        bool = true; 
      return bool;
    } 
    throw new IllegalArgumentException("Moving more than 1 item is not supported yet");
  }
  
  boolean onItemRangeRemoved(int paramInt1, int paramInt2) {
    boolean bool = false;
    if (paramInt2 < 1)
      return false; 
    this.mPendingUpdates.add(obtainUpdateOp(2, paramInt1, paramInt2, null));
    this.mExistingUpdateTypes |= 0x2;
    if (this.mPendingUpdates.size() == 1)
      bool = true; 
    return bool;
  }
  
  void preProcess() {
    this.mOpReorderer.reorderOps(this.mPendingUpdates);
    int i = this.mPendingUpdates.size();
    for (byte b = 0; b < i; b++) {
      UpdateOp updateOp = this.mPendingUpdates.get(b);
      int j = updateOp.cmd;
      if (j != 1) {
        if (j != 2) {
          if (j != 4) {
            if (j == 8)
              applyMove(updateOp); 
          } else {
            applyUpdate(updateOp);
          } 
        } else {
          applyRemove(updateOp);
        } 
      } else {
        applyAdd(updateOp);
      } 
      Runnable runnable = this.mOnItemProcessedCallback;
      if (runnable != null)
        runnable.run(); 
    } 
    this.mPendingUpdates.clear();
  }
  
  public void recycleUpdateOp(UpdateOp paramUpdateOp) {
    if (!this.mDisableRecycler) {
      paramUpdateOp.payload = null;
      this.mUpdateOpPool.release(paramUpdateOp);
    } 
  }
  
  void recycleUpdateOpsAndClearList(List<UpdateOp> paramList) {
    int i = paramList.size();
    for (byte b = 0; b < i; b++)
      recycleUpdateOp(paramList.get(b)); 
    paramList.clear();
  }
  
  void reset() {
    recycleUpdateOpsAndClearList(this.mPendingUpdates);
    recycleUpdateOpsAndClearList(this.mPostponedList);
    this.mExistingUpdateTypes = 0;
  }
  
  static interface Callback {
    RecyclerView.ViewHolder findViewHolder(int param1Int);
    
    void markViewHoldersUpdated(int param1Int1, int param1Int2, Object param1Object);
    
    void offsetPositionsForAdd(int param1Int1, int param1Int2);
    
    void offsetPositionsForMove(int param1Int1, int param1Int2);
    
    void offsetPositionsForRemovingInvisible(int param1Int1, int param1Int2);
    
    void offsetPositionsForRemovingLaidOutOrNewView(int param1Int1, int param1Int2);
    
    void onDispatchFirstPass(AdapterHelper.UpdateOp param1UpdateOp);
    
    void onDispatchSecondPass(AdapterHelper.UpdateOp param1UpdateOp);
  }
  
  static class UpdateOp {
    static final int ADD = 1;
    
    static final int MOVE = 8;
    
    static final int POOL_SIZE = 30;
    
    static final int REMOVE = 2;
    
    static final int UPDATE = 4;
    
    int cmd;
    
    int itemCount;
    
    Object payload;
    
    int positionStart;
    
    UpdateOp(int param1Int1, int param1Int2, int param1Int3, Object param1Object) {
      this.cmd = param1Int1;
      this.positionStart = param1Int2;
      this.itemCount = param1Int3;
      this.payload = param1Object;
    }
    
    String cmdToString() {
      int i = this.cmd;
      return (i != 1) ? ((i != 2) ? ((i != 4) ? ((i != 8) ? "??" : "mv") : "up") : "rm") : "add";
    }
    
    public boolean equals(Object param1Object) {
      if (this == param1Object)
        return true; 
      if (param1Object == null || getClass() != param1Object.getClass())
        return false; 
      param1Object = param1Object;
      int i = this.cmd;
      if (i != ((UpdateOp)param1Object).cmd)
        return false; 
      if (i == 8 && Math.abs(this.itemCount - this.positionStart) == 1 && this.itemCount == ((UpdateOp)param1Object).positionStart && this.positionStart == ((UpdateOp)param1Object).itemCount)
        return true; 
      if (this.itemCount != ((UpdateOp)param1Object).itemCount)
        return false; 
      if (this.positionStart != ((UpdateOp)param1Object).positionStart)
        return false; 
      Object object = this.payload;
      if (object != null) {
        if (!object.equals(((UpdateOp)param1Object).payload))
          return false; 
      } else if (((UpdateOp)param1Object).payload != null) {
        return false;
      } 
      return true;
    }
    
    public int hashCode() {
      return (this.cmd * 31 + this.positionStart) * 31 + this.itemCount;
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
      stringBuilder.append("[");
      stringBuilder.append(cmdToString());
      stringBuilder.append(",s:");
      stringBuilder.append(this.positionStart);
      stringBuilder.append("c:");
      stringBuilder.append(this.itemCount);
      stringBuilder.append(",p:");
      stringBuilder.append(this.payload);
      stringBuilder.append("]");
      return stringBuilder.toString();
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/widget/AdapterHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */