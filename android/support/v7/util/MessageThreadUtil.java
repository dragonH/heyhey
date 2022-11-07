package android.support.v7.util;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

class MessageThreadUtil<T> implements ThreadUtil<T> {
  public ThreadUtil.BackgroundCallback<T> getBackgroundProxy(final ThreadUtil.BackgroundCallback<T> callback) {
    return new ThreadUtil.BackgroundCallback<T>() {
        static final int LOAD_TILE = 3;
        
        static final int RECYCLE_TILE = 4;
        
        static final int REFRESH = 1;
        
        static final int UPDATE_RANGE = 2;
        
        private Runnable mBackgroundRunnable = new Runnable() {
            public void run() {
              while (true) {
                MessageThreadUtil.SyncQueueItem syncQueueItem = MessageThreadUtil.null.this.mQueue.next();
                if (syncQueueItem == null) {
                  MessageThreadUtil.null.this.mBackgroundRunning.set(false);
                  return;
                } 
                int i = syncQueueItem.what;
                if (i != 1) {
                  if (i != 2) {
                    if (i != 3) {
                      if (i != 4) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Unsupported message, what=");
                        stringBuilder.append(syncQueueItem.what);
                        Log.e("ThreadUtil", stringBuilder.toString());
                        continue;
                      } 
                      callback.recycleTile((TileList.Tile)syncQueueItem.data);
                      continue;
                    } 
                    callback.loadTile(syncQueueItem.arg1, syncQueueItem.arg2);
                    continue;
                  } 
                  MessageThreadUtil.null.this.mQueue.removeMessages(2);
                  MessageThreadUtil.null.this.mQueue.removeMessages(3);
                  callback.updateRange(syncQueueItem.arg1, syncQueueItem.arg2, syncQueueItem.arg3, syncQueueItem.arg4, syncQueueItem.arg5);
                  continue;
                } 
                MessageThreadUtil.null.this.mQueue.removeMessages(1);
                callback.refresh(syncQueueItem.arg1);
              } 
            }
          };
        
        AtomicBoolean mBackgroundRunning = new AtomicBoolean(false);
        
        private final Executor mExecutor = AsyncTask.THREAD_POOL_EXECUTOR;
        
        final MessageThreadUtil.MessageQueue mQueue = new MessageThreadUtil.MessageQueue();
        
        private void maybeExecuteBackgroundRunnable() {
          if (this.mBackgroundRunning.compareAndSet(false, true))
            this.mExecutor.execute(this.mBackgroundRunnable); 
        }
        
        private void sendMessage(MessageThreadUtil.SyncQueueItem param1SyncQueueItem) {
          this.mQueue.sendMessage(param1SyncQueueItem);
          maybeExecuteBackgroundRunnable();
        }
        
        private void sendMessageAtFrontOfQueue(MessageThreadUtil.SyncQueueItem param1SyncQueueItem) {
          this.mQueue.sendMessageAtFrontOfQueue(param1SyncQueueItem);
          maybeExecuteBackgroundRunnable();
        }
        
        public void loadTile(int param1Int1, int param1Int2) {
          sendMessage(MessageThreadUtil.SyncQueueItem.obtainMessage(3, param1Int1, param1Int2));
        }
        
        public void recycleTile(TileList.Tile<T> param1Tile) {
          sendMessage(MessageThreadUtil.SyncQueueItem.obtainMessage(4, 0, param1Tile));
        }
        
        public void refresh(int param1Int) {
          sendMessageAtFrontOfQueue(MessageThreadUtil.SyncQueueItem.obtainMessage(1, param1Int, (Object)null));
        }
        
        public void updateRange(int param1Int1, int param1Int2, int param1Int3, int param1Int4, int param1Int5) {
          sendMessageAtFrontOfQueue(MessageThreadUtil.SyncQueueItem.obtainMessage(2, param1Int1, param1Int2, param1Int3, param1Int4, param1Int5, null));
        }
      };
  }
  
  public ThreadUtil.MainThreadCallback<T> getMainThreadProxy(final ThreadUtil.MainThreadCallback<T> callback) {
    return new ThreadUtil.MainThreadCallback<T>() {
        static final int ADD_TILE = 2;
        
        static final int REMOVE_TILE = 3;
        
        static final int UPDATE_ITEM_COUNT = 1;
        
        private final Handler mMainThreadHandler = new Handler(Looper.getMainLooper());
        
        private Runnable mMainThreadRunnable = new Runnable() {
            public void run() {
              for (MessageThreadUtil.SyncQueueItem syncQueueItem = MessageThreadUtil.null.this.mQueue.next(); syncQueueItem != null; syncQueueItem = MessageThreadUtil.null.this.mQueue.next()) {
                int i = syncQueueItem.what;
                if (i != 1) {
                  if (i != 2) {
                    if (i != 3) {
                      StringBuilder stringBuilder = new StringBuilder();
                      stringBuilder.append("Unsupported message, what=");
                      stringBuilder.append(syncQueueItem.what);
                      Log.e("ThreadUtil", stringBuilder.toString());
                    } else {
                      callback.removeTile(syncQueueItem.arg1, syncQueueItem.arg2);
                    } 
                  } else {
                    callback.addTile(syncQueueItem.arg1, (TileList.Tile)syncQueueItem.data);
                  } 
                } else {
                  callback.updateItemCount(syncQueueItem.arg1, syncQueueItem.arg2);
                } 
              } 
            }
          };
        
        final MessageThreadUtil.MessageQueue mQueue = new MessageThreadUtil.MessageQueue();
        
        private void sendMessage(MessageThreadUtil.SyncQueueItem param1SyncQueueItem) {
          this.mQueue.sendMessage(param1SyncQueueItem);
          this.mMainThreadHandler.post(this.mMainThreadRunnable);
        }
        
        public void addTile(int param1Int, TileList.Tile<T> param1Tile) {
          sendMessage(MessageThreadUtil.SyncQueueItem.obtainMessage(2, param1Int, param1Tile));
        }
        
        public void removeTile(int param1Int1, int param1Int2) {
          sendMessage(MessageThreadUtil.SyncQueueItem.obtainMessage(3, param1Int1, param1Int2));
        }
        
        public void updateItemCount(int param1Int1, int param1Int2) {
          sendMessage(MessageThreadUtil.SyncQueueItem.obtainMessage(1, param1Int1, param1Int2));
        }
      };
  }
  
  static class MessageQueue {
    private MessageThreadUtil.SyncQueueItem mRoot;
    
    MessageThreadUtil.SyncQueueItem next() {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield mRoot : Landroid/support/v7/util/MessageThreadUtil$SyncQueueItem;
      //   6: astore_1
      //   7: aload_1
      //   8: ifnonnull -> 15
      //   11: aload_0
      //   12: monitorexit
      //   13: aconst_null
      //   14: areturn
      //   15: aload_0
      //   16: aload_1
      //   17: invokestatic access$000 : (Landroid/support/v7/util/MessageThreadUtil$SyncQueueItem;)Landroid/support/v7/util/MessageThreadUtil$SyncQueueItem;
      //   20: putfield mRoot : Landroid/support/v7/util/MessageThreadUtil$SyncQueueItem;
      //   23: aload_0
      //   24: monitorexit
      //   25: aload_1
      //   26: areturn
      //   27: astore_1
      //   28: aload_0
      //   29: monitorexit
      //   30: aload_1
      //   31: athrow
      // Exception table:
      //   from	to	target	type
      //   2	7	27	finally
      //   15	23	27	finally
    }
    
    void removeMessages(int param1Int) {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield mRoot : Landroid/support/v7/util/MessageThreadUtil$SyncQueueItem;
      //   6: astore_2
      //   7: aload_2
      //   8: ifnull -> 34
      //   11: aload_2
      //   12: getfield what : I
      //   15: iload_1
      //   16: if_icmpne -> 34
      //   19: aload_0
      //   20: aload_2
      //   21: invokestatic access$000 : (Landroid/support/v7/util/MessageThreadUtil$SyncQueueItem;)Landroid/support/v7/util/MessageThreadUtil$SyncQueueItem;
      //   24: putfield mRoot : Landroid/support/v7/util/MessageThreadUtil$SyncQueueItem;
      //   27: aload_2
      //   28: invokevirtual recycle : ()V
      //   31: goto -> 2
      //   34: aload_2
      //   35: ifnull -> 83
      //   38: aload_2
      //   39: invokestatic access$000 : (Landroid/support/v7/util/MessageThreadUtil$SyncQueueItem;)Landroid/support/v7/util/MessageThreadUtil$SyncQueueItem;
      //   42: astore_3
      //   43: aload_3
      //   44: ifnull -> 83
      //   47: aload_3
      //   48: invokestatic access$000 : (Landroid/support/v7/util/MessageThreadUtil$SyncQueueItem;)Landroid/support/v7/util/MessageThreadUtil$SyncQueueItem;
      //   51: astore #4
      //   53: aload_3
      //   54: getfield what : I
      //   57: iload_1
      //   58: if_icmpne -> 75
      //   61: aload_2
      //   62: aload #4
      //   64: invokestatic access$002 : (Landroid/support/v7/util/MessageThreadUtil$SyncQueueItem;Landroid/support/v7/util/MessageThreadUtil$SyncQueueItem;)Landroid/support/v7/util/MessageThreadUtil$SyncQueueItem;
      //   67: pop
      //   68: aload_3
      //   69: invokevirtual recycle : ()V
      //   72: goto -> 77
      //   75: aload_3
      //   76: astore_2
      //   77: aload #4
      //   79: astore_3
      //   80: goto -> 43
      //   83: aload_0
      //   84: monitorexit
      //   85: return
      //   86: astore_3
      //   87: aload_0
      //   88: monitorexit
      //   89: goto -> 94
      //   92: aload_3
      //   93: athrow
      //   94: goto -> 92
      // Exception table:
      //   from	to	target	type
      //   2	7	86	finally
      //   11	31	86	finally
      //   38	43	86	finally
      //   47	72	86	finally
    }
    
    void sendMessage(MessageThreadUtil.SyncQueueItem param1SyncQueueItem) {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield mRoot : Landroid/support/v7/util/MessageThreadUtil$SyncQueueItem;
      //   6: astore_2
      //   7: aload_2
      //   8: astore_3
      //   9: aload_2
      //   10: ifnonnull -> 21
      //   13: aload_0
      //   14: aload_1
      //   15: putfield mRoot : Landroid/support/v7/util/MessageThreadUtil$SyncQueueItem;
      //   18: aload_0
      //   19: monitorexit
      //   20: return
      //   21: aload_3
      //   22: invokestatic access$000 : (Landroid/support/v7/util/MessageThreadUtil$SyncQueueItem;)Landroid/support/v7/util/MessageThreadUtil$SyncQueueItem;
      //   25: ifnull -> 36
      //   28: aload_3
      //   29: invokestatic access$000 : (Landroid/support/v7/util/MessageThreadUtil$SyncQueueItem;)Landroid/support/v7/util/MessageThreadUtil$SyncQueueItem;
      //   32: astore_3
      //   33: goto -> 21
      //   36: aload_3
      //   37: aload_1
      //   38: invokestatic access$002 : (Landroid/support/v7/util/MessageThreadUtil$SyncQueueItem;Landroid/support/v7/util/MessageThreadUtil$SyncQueueItem;)Landroid/support/v7/util/MessageThreadUtil$SyncQueueItem;
      //   41: pop
      //   42: aload_0
      //   43: monitorexit
      //   44: return
      //   45: astore_1
      //   46: aload_0
      //   47: monitorexit
      //   48: goto -> 53
      //   51: aload_1
      //   52: athrow
      //   53: goto -> 51
      // Exception table:
      //   from	to	target	type
      //   2	7	45	finally
      //   13	18	45	finally
      //   21	33	45	finally
      //   36	42	45	finally
    }
    
    void sendMessageAtFrontOfQueue(MessageThreadUtil.SyncQueueItem param1SyncQueueItem) {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_1
      //   3: aload_0
      //   4: getfield mRoot : Landroid/support/v7/util/MessageThreadUtil$SyncQueueItem;
      //   7: invokestatic access$002 : (Landroid/support/v7/util/MessageThreadUtil$SyncQueueItem;Landroid/support/v7/util/MessageThreadUtil$SyncQueueItem;)Landroid/support/v7/util/MessageThreadUtil$SyncQueueItem;
      //   10: pop
      //   11: aload_0
      //   12: aload_1
      //   13: putfield mRoot : Landroid/support/v7/util/MessageThreadUtil$SyncQueueItem;
      //   16: aload_0
      //   17: monitorexit
      //   18: return
      //   19: astore_1
      //   20: aload_0
      //   21: monitorexit
      //   22: aload_1
      //   23: athrow
      // Exception table:
      //   from	to	target	type
      //   2	16	19	finally
    }
  }
  
  static class SyncQueueItem {
    private static SyncQueueItem sPool;
    
    private static final Object sPoolLock = new Object();
    
    public int arg1;
    
    public int arg2;
    
    public int arg3;
    
    public int arg4;
    
    public int arg5;
    
    public Object data;
    
    private SyncQueueItem next;
    
    public int what;
    
    static SyncQueueItem obtainMessage(int param1Int1, int param1Int2, int param1Int3) {
      return obtainMessage(param1Int1, param1Int2, param1Int3, 0, 0, 0, null);
    }
    
    static SyncQueueItem obtainMessage(int param1Int1, int param1Int2, int param1Int3, int param1Int4, int param1Int5, int param1Int6, Object param1Object) {
      synchronized (sPoolLock) {
        SyncQueueItem syncQueueItem = sPool;
        if (syncQueueItem == null) {
          syncQueueItem = new SyncQueueItem();
          this();
        } else {
          sPool = syncQueueItem.next;
          syncQueueItem.next = null;
        } 
        syncQueueItem.what = param1Int1;
        syncQueueItem.arg1 = param1Int2;
        syncQueueItem.arg2 = param1Int3;
        syncQueueItem.arg3 = param1Int4;
        syncQueueItem.arg4 = param1Int5;
        syncQueueItem.arg5 = param1Int6;
        syncQueueItem.data = param1Object;
        return syncQueueItem;
      } 
    }
    
    static SyncQueueItem obtainMessage(int param1Int1, int param1Int2, Object param1Object) {
      return obtainMessage(param1Int1, param1Int2, 0, 0, 0, 0, param1Object);
    }
    
    void recycle() {
      this.next = null;
      this.arg5 = 0;
      this.arg4 = 0;
      this.arg3 = 0;
      this.arg2 = 0;
      this.arg1 = 0;
      this.what = 0;
      this.data = null;
      synchronized (sPoolLock) {
        SyncQueueItem syncQueueItem = sPool;
        if (syncQueueItem != null)
          this.next = syncQueueItem; 
        sPool = this;
        return;
      } 
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/util/MessageThreadUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */