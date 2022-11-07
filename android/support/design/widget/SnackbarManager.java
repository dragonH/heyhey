package android.support.design.widget;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;

class SnackbarManager {
  private static final int LONG_DURATION_MS = 2750;
  
  static final int MSG_TIMEOUT = 0;
  
  private static final int SHORT_DURATION_MS = 1500;
  
  private static SnackbarManager sSnackbarManager;
  
  private SnackbarRecord mCurrentSnackbar;
  
  private final Handler mHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
        public boolean handleMessage(Message param1Message) {
          if (param1Message.what != 0)
            return false; 
          SnackbarManager.this.handleTimeout((SnackbarManager.SnackbarRecord)param1Message.obj);
          return true;
        }
      });
  
  private final Object mLock = new Object();
  
  private SnackbarRecord mNextSnackbar;
  
  private boolean cancelSnackbarLocked(SnackbarRecord paramSnackbarRecord, int paramInt) {
    Callback callback = paramSnackbarRecord.callback.get();
    if (callback != null) {
      this.mHandler.removeCallbacksAndMessages(paramSnackbarRecord);
      callback.dismiss(paramInt);
      return true;
    } 
    return false;
  }
  
  static SnackbarManager getInstance() {
    if (sSnackbarManager == null)
      sSnackbarManager = new SnackbarManager(); 
    return sSnackbarManager;
  }
  
  private boolean isCurrentSnackbarLocked(Callback paramCallback) {
    boolean bool;
    SnackbarRecord snackbarRecord = this.mCurrentSnackbar;
    if (snackbarRecord != null && snackbarRecord.isSnackbar(paramCallback)) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private boolean isNextSnackbarLocked(Callback paramCallback) {
    boolean bool;
    SnackbarRecord snackbarRecord = this.mNextSnackbar;
    if (snackbarRecord != null && snackbarRecord.isSnackbar(paramCallback)) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private void scheduleTimeoutLocked(SnackbarRecord paramSnackbarRecord) {
    int i = paramSnackbarRecord.duration;
    if (i == -2)
      return; 
    if (i <= 0)
      if (i == -1) {
        i = 1500;
      } else {
        i = 2750;
      }  
    this.mHandler.removeCallbacksAndMessages(paramSnackbarRecord);
    Handler handler = this.mHandler;
    handler.sendMessageDelayed(Message.obtain(handler, 0, paramSnackbarRecord), i);
  }
  
  private void showNextSnackbarLocked() {
    SnackbarRecord snackbarRecord = this.mNextSnackbar;
    if (snackbarRecord != null) {
      this.mCurrentSnackbar = snackbarRecord;
      this.mNextSnackbar = null;
      Callback callback = snackbarRecord.callback.get();
      if (callback != null) {
        callback.show();
      } else {
        this.mCurrentSnackbar = null;
      } 
    } 
  }
  
  public void dismiss(Callback paramCallback, int paramInt) {
    synchronized (this.mLock) {
      if (isCurrentSnackbarLocked(paramCallback)) {
        cancelSnackbarLocked(this.mCurrentSnackbar, paramInt);
      } else if (isNextSnackbarLocked(paramCallback)) {
        cancelSnackbarLocked(this.mNextSnackbar, paramInt);
      } 
      return;
    } 
  }
  
  void handleTimeout(SnackbarRecord paramSnackbarRecord) {
    synchronized (this.mLock) {
      if (this.mCurrentSnackbar == paramSnackbarRecord || this.mNextSnackbar == paramSnackbarRecord)
        cancelSnackbarLocked(paramSnackbarRecord, 2); 
      return;
    } 
  }
  
  public boolean isCurrent(Callback paramCallback) {
    synchronized (this.mLock) {
      return isCurrentSnackbarLocked(paramCallback);
    } 
  }
  
  public boolean isCurrentOrNext(Callback paramCallback) {
    synchronized (this.mLock) {
      if (isCurrentSnackbarLocked(paramCallback) || isNextSnackbarLocked(paramCallback))
        return true; 
      return false;
    } 
  }
  
  public void onDismissed(Callback paramCallback) {
    synchronized (this.mLock) {
      if (isCurrentSnackbarLocked(paramCallback)) {
        this.mCurrentSnackbar = null;
        if (this.mNextSnackbar != null)
          showNextSnackbarLocked(); 
      } 
      return;
    } 
  }
  
  public void onShown(Callback paramCallback) {
    synchronized (this.mLock) {
      if (isCurrentSnackbarLocked(paramCallback))
        scheduleTimeoutLocked(this.mCurrentSnackbar); 
      return;
    } 
  }
  
  public void pauseTimeout(Callback paramCallback) {
    synchronized (this.mLock) {
      if (isCurrentSnackbarLocked(paramCallback)) {
        SnackbarRecord snackbarRecord = this.mCurrentSnackbar;
        if (!snackbarRecord.paused) {
          snackbarRecord.paused = true;
          this.mHandler.removeCallbacksAndMessages(snackbarRecord);
        } 
      } 
      return;
    } 
  }
  
  public void restoreTimeoutIfPaused(Callback paramCallback) {
    synchronized (this.mLock) {
      if (isCurrentSnackbarLocked(paramCallback)) {
        SnackbarRecord snackbarRecord = this.mCurrentSnackbar;
        if (snackbarRecord.paused) {
          snackbarRecord.paused = false;
          scheduleTimeoutLocked(snackbarRecord);
        } 
      } 
      return;
    } 
  }
  
  public void show(int paramInt, Callback paramCallback) {
    synchronized (this.mLock) {
      if (isCurrentSnackbarLocked(paramCallback)) {
        snackbarRecord = this.mCurrentSnackbar;
        snackbarRecord.duration = paramInt;
        this.mHandler.removeCallbacksAndMessages(snackbarRecord);
        scheduleTimeoutLocked(this.mCurrentSnackbar);
        return;
      } 
      if (isNextSnackbarLocked((Callback)snackbarRecord)) {
        this.mNextSnackbar.duration = paramInt;
      } else {
        SnackbarRecord snackbarRecord1 = new SnackbarRecord();
        this(paramInt, (Callback)snackbarRecord);
        this.mNextSnackbar = snackbarRecord1;
      } 
      SnackbarRecord snackbarRecord = this.mCurrentSnackbar;
      if (snackbarRecord != null && cancelSnackbarLocked(snackbarRecord, 4))
        return; 
      this.mCurrentSnackbar = null;
      showNextSnackbarLocked();
      return;
    } 
  }
  
  static interface Callback {
    void dismiss(int param1Int);
    
    void show();
  }
  
  private static class SnackbarRecord {
    final WeakReference<SnackbarManager.Callback> callback;
    
    int duration;
    
    boolean paused;
    
    SnackbarRecord(int param1Int, SnackbarManager.Callback param1Callback) {
      this.callback = new WeakReference<SnackbarManager.Callback>(param1Callback);
      this.duration = param1Int;
    }
    
    boolean isSnackbar(SnackbarManager.Callback param1Callback) {
      boolean bool;
      if (param1Callback != null && this.callback.get() == param1Callback) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/design/widget/SnackbarManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */