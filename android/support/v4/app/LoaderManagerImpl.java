package android.support.v4.app;

import android.os.Bundle;
import android.support.v4.content.Loader;
import android.support.v4.util.DebugUtils;
import android.support.v4.util.SparseArrayCompat;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;

class LoaderManagerImpl extends LoaderManager {
  static boolean DEBUG = false;
  
  static final String TAG = "LoaderManager";
  
  boolean mCreatingLoader;
  
  FragmentHostCallback mHost;
  
  final SparseArrayCompat<LoaderInfo> mInactiveLoaders = new SparseArrayCompat();
  
  final SparseArrayCompat<LoaderInfo> mLoaders = new SparseArrayCompat();
  
  boolean mRetaining;
  
  boolean mRetainingStarted;
  
  boolean mStarted;
  
  final String mWho;
  
  LoaderManagerImpl(String paramString, FragmentHostCallback paramFragmentHostCallback, boolean paramBoolean) {
    this.mWho = paramString;
    this.mHost = paramFragmentHostCallback;
    this.mStarted = paramBoolean;
  }
  
  private LoaderInfo createAndInstallLoader(int paramInt, Bundle paramBundle, LoaderManager.LoaderCallbacks<Object> paramLoaderCallbacks) {
    try {
      this.mCreatingLoader = true;
      LoaderInfo loaderInfo = createLoader(paramInt, paramBundle, paramLoaderCallbacks);
      installLoader(loaderInfo);
      return loaderInfo;
    } finally {
      this.mCreatingLoader = false;
    } 
  }
  
  private LoaderInfo createLoader(int paramInt, Bundle paramBundle, LoaderManager.LoaderCallbacks<Object> paramLoaderCallbacks) {
    LoaderInfo loaderInfo = new LoaderInfo(paramInt, paramBundle, paramLoaderCallbacks);
    loaderInfo.mLoader = paramLoaderCallbacks.onCreateLoader(paramInt, paramBundle);
    return loaderInfo;
  }
  
  public void destroyLoader(int paramInt) {
    if (!this.mCreatingLoader) {
      if (DEBUG) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("destroyLoader in ");
        stringBuilder.append(this);
        stringBuilder.append(" of ");
        stringBuilder.append(paramInt);
        Log.v("LoaderManager", stringBuilder.toString());
      } 
      int i = this.mLoaders.indexOfKey(paramInt);
      if (i >= 0) {
        LoaderInfo loaderInfo = (LoaderInfo)this.mLoaders.valueAt(i);
        this.mLoaders.removeAt(i);
        loaderInfo.destroy();
      } 
      paramInt = this.mInactiveLoaders.indexOfKey(paramInt);
      if (paramInt >= 0) {
        LoaderInfo loaderInfo = (LoaderInfo)this.mInactiveLoaders.valueAt(paramInt);
        this.mInactiveLoaders.removeAt(paramInt);
        loaderInfo.destroy();
      } 
      if (this.mHost != null && !hasRunningLoaders())
        this.mHost.mFragmentManager.startPendingDeferredFragments(); 
      return;
    } 
    throw new IllegalStateException("Called while creating a loader");
  }
  
  void doDestroy() {
    if (!this.mRetaining) {
      if (DEBUG) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Destroying Active in ");
        stringBuilder.append(this);
        Log.v("LoaderManager", stringBuilder.toString());
      } 
      for (int j = this.mLoaders.size() - 1; j >= 0; j--)
        ((LoaderInfo)this.mLoaders.valueAt(j)).destroy(); 
      this.mLoaders.clear();
    } 
    if (DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Destroying Inactive in ");
      stringBuilder.append(this);
      Log.v("LoaderManager", stringBuilder.toString());
    } 
    for (int i = this.mInactiveLoaders.size() - 1; i >= 0; i--)
      ((LoaderInfo)this.mInactiveLoaders.valueAt(i)).destroy(); 
    this.mInactiveLoaders.clear();
    this.mHost = null;
  }
  
  void doReportNextStart() {
    for (int i = this.mLoaders.size() - 1; i >= 0; i--)
      ((LoaderInfo)this.mLoaders.valueAt(i)).mReportNextStart = true; 
  }
  
  void doReportStart() {
    for (int i = this.mLoaders.size() - 1; i >= 0; i--)
      ((LoaderInfo)this.mLoaders.valueAt(i)).reportStart(); 
  }
  
  void doRetain() {
    if (DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Retaining in ");
      stringBuilder.append(this);
      Log.v("LoaderManager", stringBuilder.toString());
    } 
    if (!this.mStarted) {
      RuntimeException runtimeException = new RuntimeException("here");
      runtimeException.fillInStackTrace();
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Called doRetain when not started: ");
      stringBuilder.append(this);
      Log.w("LoaderManager", stringBuilder.toString(), runtimeException);
      return;
    } 
    this.mRetaining = true;
    this.mStarted = false;
    for (int i = this.mLoaders.size() - 1; i >= 0; i--)
      ((LoaderInfo)this.mLoaders.valueAt(i)).retain(); 
  }
  
  void doStart() {
    if (DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Starting in ");
      stringBuilder.append(this);
      Log.v("LoaderManager", stringBuilder.toString());
    } 
    if (this.mStarted) {
      RuntimeException runtimeException = new RuntimeException("here");
      runtimeException.fillInStackTrace();
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Called doStart when already started: ");
      stringBuilder.append(this);
      Log.w("LoaderManager", stringBuilder.toString(), runtimeException);
      return;
    } 
    this.mStarted = true;
    for (int i = this.mLoaders.size() - 1; i >= 0; i--)
      ((LoaderInfo)this.mLoaders.valueAt(i)).start(); 
  }
  
  void doStop() {
    if (DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Stopping in ");
      stringBuilder.append(this);
      Log.v("LoaderManager", stringBuilder.toString());
    } 
    if (!this.mStarted) {
      RuntimeException runtimeException = new RuntimeException("here");
      runtimeException.fillInStackTrace();
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Called doStop when not started: ");
      stringBuilder.append(this);
      Log.w("LoaderManager", stringBuilder.toString(), runtimeException);
      return;
    } 
    for (int i = this.mLoaders.size() - 1; i >= 0; i--)
      ((LoaderInfo)this.mLoaders.valueAt(i)).stop(); 
    this.mStarted = false;
  }
  
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {
    int i = this.mLoaders.size();
    boolean bool = false;
    if (i > 0) {
      paramPrintWriter.print(paramString);
      paramPrintWriter.println("Active Loaders:");
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append("    ");
      String str = stringBuilder.toString();
      for (i = 0; i < this.mLoaders.size(); i++) {
        LoaderInfo loaderInfo = (LoaderInfo)this.mLoaders.valueAt(i);
        paramPrintWriter.print(paramString);
        paramPrintWriter.print("  #");
        paramPrintWriter.print(this.mLoaders.keyAt(i));
        paramPrintWriter.print(": ");
        paramPrintWriter.println(loaderInfo.toString());
        loaderInfo.dump(str, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
      } 
    } 
    if (this.mInactiveLoaders.size() > 0) {
      paramPrintWriter.print(paramString);
      paramPrintWriter.println("Inactive Loaders:");
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append("    ");
      String str = stringBuilder.toString();
      for (i = bool; i < this.mInactiveLoaders.size(); i++) {
        LoaderInfo loaderInfo = (LoaderInfo)this.mInactiveLoaders.valueAt(i);
        paramPrintWriter.print(paramString);
        paramPrintWriter.print("  #");
        paramPrintWriter.print(this.mInactiveLoaders.keyAt(i));
        paramPrintWriter.print(": ");
        paramPrintWriter.println(loaderInfo.toString());
        loaderInfo.dump(str, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
      } 
    } 
  }
  
  void finishRetain() {
    if (this.mRetaining) {
      if (DEBUG) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Finished Retaining in ");
        stringBuilder.append(this);
        Log.v("LoaderManager", stringBuilder.toString());
      } 
      this.mRetaining = false;
      for (int i = this.mLoaders.size() - 1; i >= 0; i--)
        ((LoaderInfo)this.mLoaders.valueAt(i)).finishRetain(); 
    } 
  }
  
  public <D> Loader<D> getLoader(int paramInt) {
    if (!this.mCreatingLoader) {
      LoaderInfo loaderInfo = (LoaderInfo)this.mLoaders.get(paramInt);
      if (loaderInfo != null) {
        LoaderInfo loaderInfo1 = loaderInfo.mPendingLoader;
        return (loaderInfo1 != null) ? loaderInfo1.mLoader : loaderInfo.mLoader;
      } 
      return null;
    } 
    throw new IllegalStateException("Called while creating a loader");
  }
  
  public boolean hasRunningLoaders() {
    int i = this.mLoaders.size();
    byte b = 0;
    int j = 0;
    while (b < i) {
      byte b1;
      LoaderInfo loaderInfo = (LoaderInfo)this.mLoaders.valueAt(b);
      if (loaderInfo.mStarted && !loaderInfo.mDeliveredData) {
        b1 = 1;
      } else {
        b1 = 0;
      } 
      j |= b1;
      b++;
    } 
    return j;
  }
  
  public <D> Loader<D> initLoader(int paramInt, Bundle paramBundle, LoaderManager.LoaderCallbacks<D> paramLoaderCallbacks) {
    if (!this.mCreatingLoader) {
      LoaderInfo loaderInfo1;
      LoaderInfo loaderInfo2;
      LoaderInfo loaderInfo3 = (LoaderInfo)this.mLoaders.get(paramInt);
      if (DEBUG) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("initLoader in ");
        stringBuilder.append(this);
        stringBuilder.append(": args=");
        stringBuilder.append(paramBundle);
        Log.v("LoaderManager", stringBuilder.toString());
      } 
      if (loaderInfo3 == null) {
        loaderInfo2 = createAndInstallLoader(paramInt, paramBundle, (LoaderManager.LoaderCallbacks)paramLoaderCallbacks);
        loaderInfo1 = loaderInfo2;
        if (DEBUG) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("  Created new loader ");
          stringBuilder.append(loaderInfo2);
          Log.v("LoaderManager", stringBuilder.toString());
          LoaderInfo loaderInfo = loaderInfo2;
        } 
      } else {
        if (DEBUG) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("  Re-using existing loader ");
          stringBuilder.append(loaderInfo3);
          Log.v("LoaderManager", stringBuilder.toString());
        } 
        loaderInfo3.mCallbacks = (LoaderManager.LoaderCallbacks)loaderInfo2;
        loaderInfo1 = loaderInfo3;
      } 
      if (loaderInfo1.mHaveData && this.mStarted)
        loaderInfo1.callOnLoadFinished(loaderInfo1.mLoader, loaderInfo1.mData); 
      return loaderInfo1.mLoader;
    } 
    throw new IllegalStateException("Called while creating a loader");
  }
  
  void installLoader(LoaderInfo paramLoaderInfo) {
    this.mLoaders.put(paramLoaderInfo.mId, paramLoaderInfo);
    if (this.mStarted)
      paramLoaderInfo.start(); 
  }
  
  public <D> Loader<D> restartLoader(int paramInt, Bundle paramBundle, LoaderManager.LoaderCallbacks<D> paramLoaderCallbacks) {
    if (!this.mCreatingLoader) {
      LoaderInfo loaderInfo1;
      LoaderInfo loaderInfo2 = (LoaderInfo)this.mLoaders.get(paramInt);
      if (DEBUG) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("restartLoader in ");
        stringBuilder.append(this);
        stringBuilder.append(": args=");
        stringBuilder.append(paramBundle);
        Log.v("LoaderManager", stringBuilder.toString());
      } 
      if (loaderInfo2 != null) {
        LoaderInfo loaderInfo = (LoaderInfo)this.mInactiveLoaders.get(paramInt);
        if (loaderInfo != null) {
          if (loaderInfo2.mHaveData) {
            if (DEBUG) {
              StringBuilder stringBuilder = new StringBuilder();
              stringBuilder.append("  Removing last inactive loader: ");
              stringBuilder.append(loaderInfo2);
              Log.v("LoaderManager", stringBuilder.toString());
            } 
            loaderInfo.mDeliveredData = false;
            loaderInfo.destroy();
            loaderInfo2.mLoader.abandon();
            this.mInactiveLoaders.put(paramInt, loaderInfo2);
          } else if (!loaderInfo2.cancel()) {
            if (DEBUG)
              Log.v("LoaderManager", "  Current loader is stopped; replacing"); 
            this.mLoaders.put(paramInt, null);
            loaderInfo2.destroy();
          } else {
            if (DEBUG)
              Log.v("LoaderManager", "  Current loader is running; configuring pending loader"); 
            if (loaderInfo2.mPendingLoader != null) {
              if (DEBUG) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("  Removing pending loader: ");
                stringBuilder.append(loaderInfo2.mPendingLoader);
                Log.v("LoaderManager", stringBuilder.toString());
              } 
              loaderInfo2.mPendingLoader.destroy();
              loaderInfo2.mPendingLoader = null;
            } 
            if (DEBUG)
              Log.v("LoaderManager", "  Enqueuing as new pending loader"); 
            loaderInfo1 = createLoader(paramInt, paramBundle, (LoaderManager.LoaderCallbacks)paramLoaderCallbacks);
            loaderInfo2.mPendingLoader = loaderInfo1;
            return loaderInfo1.mLoader;
          } 
        } else {
          if (DEBUG) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("  Making last loader inactive: ");
            stringBuilder.append(loaderInfo2);
            Log.v("LoaderManager", stringBuilder.toString());
          } 
          loaderInfo2.mLoader.abandon();
          this.mInactiveLoaders.put(paramInt, loaderInfo2);
        } 
      } 
      return (createAndInstallLoader(paramInt, (Bundle)loaderInfo1, (LoaderManager.LoaderCallbacks)paramLoaderCallbacks)).mLoader;
    } 
    throw new IllegalStateException("Called while creating a loader");
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder(128);
    stringBuilder.append("LoaderManager{");
    stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    stringBuilder.append(" in ");
    DebugUtils.buildShortClassTag(this.mHost, stringBuilder);
    stringBuilder.append("}}");
    return stringBuilder.toString();
  }
  
  void updateHostController(FragmentHostCallback paramFragmentHostCallback) {
    this.mHost = paramFragmentHostCallback;
  }
  
  final class LoaderInfo implements Loader.OnLoadCompleteListener<Object>, Loader.OnLoadCanceledListener<Object> {
    final Bundle mArgs;
    
    LoaderManager.LoaderCallbacks<Object> mCallbacks;
    
    Object mData;
    
    boolean mDeliveredData;
    
    boolean mDestroyed;
    
    boolean mHaveData;
    
    final int mId;
    
    boolean mListenerRegistered;
    
    Loader<Object> mLoader;
    
    LoaderInfo mPendingLoader;
    
    boolean mReportNextStart;
    
    boolean mRetaining;
    
    boolean mRetainingStarted;
    
    boolean mStarted;
    
    public LoaderInfo(int param1Int, Bundle param1Bundle, LoaderManager.LoaderCallbacks<Object> param1LoaderCallbacks) {
      this.mId = param1Int;
      this.mArgs = param1Bundle;
      this.mCallbacks = param1LoaderCallbacks;
    }
    
    void callOnLoadFinished(Loader<Object> param1Loader, Object param1Object) {
      if (this.mCallbacks != null) {
        String str = null;
        FragmentHostCallback fragmentHostCallback = LoaderManagerImpl.this.mHost;
        if (fragmentHostCallback != null) {
          FragmentManagerImpl fragmentManagerImpl = fragmentHostCallback.mFragmentManager;
          str = fragmentManagerImpl.mNoTransactionsBecause;
          fragmentManagerImpl.mNoTransactionsBecause = "onLoadFinished";
        } 
        try {
          if (LoaderManagerImpl.DEBUG) {
            StringBuilder stringBuilder = new StringBuilder();
            this();
            stringBuilder.append("  onLoadFinished in ");
            stringBuilder.append(param1Loader);
            stringBuilder.append(": ");
            stringBuilder.append(param1Loader.dataToString(param1Object));
            Log.v("LoaderManager", stringBuilder.toString());
          } 
          this.mCallbacks.onLoadFinished(param1Loader, param1Object);
          FragmentHostCallback fragmentHostCallback1 = LoaderManagerImpl.this.mHost;
        } finally {
          param1Object = LoaderManagerImpl.this.mHost;
          if (param1Object != null)
            ((FragmentHostCallback)param1Object).mFragmentManager.mNoTransactionsBecause = str; 
        } 
      } 
    }
    
    boolean cancel() {
      if (LoaderManagerImpl.DEBUG) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("  Canceling: ");
        stringBuilder.append(this);
        Log.v("LoaderManager", stringBuilder.toString());
      } 
      if (this.mStarted) {
        Loader<Object> loader = this.mLoader;
        if (loader != null && this.mListenerRegistered) {
          boolean bool = loader.cancelLoad();
          if (!bool)
            onLoadCanceled(this.mLoader); 
          return bool;
        } 
      } 
      return false;
    }
    
    void destroy() {
      if (LoaderManagerImpl.DEBUG) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("  Destroying: ");
        stringBuilder.append(this);
        Log.v("LoaderManager", stringBuilder.toString());
      } 
      this.mDestroyed = true;
      boolean bool = this.mDeliveredData;
      this.mDeliveredData = false;
      if (this.mCallbacks != null && this.mLoader != null && this.mHaveData && bool) {
        if (LoaderManagerImpl.DEBUG) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("  Resetting: ");
          stringBuilder.append(this);
          Log.v("LoaderManager", stringBuilder.toString());
        } 
        FragmentHostCallback fragmentHostCallback = LoaderManagerImpl.this.mHost;
        if (fragmentHostCallback != null) {
          FragmentManagerImpl fragmentManagerImpl = fragmentHostCallback.mFragmentManager;
          String str = fragmentManagerImpl.mNoTransactionsBecause;
          fragmentManagerImpl.mNoTransactionsBecause = "onLoaderReset";
        } else {
          fragmentHostCallback = null;
        } 
        try {
          this.mCallbacks.onLoaderReset(this.mLoader);
        } finally {
          FragmentHostCallback fragmentHostCallback1 = LoaderManagerImpl.this.mHost;
          if (fragmentHostCallback1 != null)
            fragmentHostCallback1.mFragmentManager.mNoTransactionsBecause = (String)fragmentHostCallback; 
        } 
      } 
      this.mCallbacks = null;
      this.mData = null;
      this.mHaveData = false;
      Loader<Object> loader = this.mLoader;
      if (loader != null) {
        if (this.mListenerRegistered) {
          this.mListenerRegistered = false;
          loader.unregisterListener(this);
          this.mLoader.unregisterOnLoadCanceledListener(this);
        } 
        this.mLoader.reset();
      } 
      LoaderInfo loaderInfo = this.mPendingLoader;
      if (loaderInfo != null)
        loaderInfo.destroy(); 
    }
    
    public void dump(String param1String, FileDescriptor param1FileDescriptor, PrintWriter param1PrintWriter, String[] param1ArrayOfString) {
      param1PrintWriter.print(param1String);
      param1PrintWriter.print("mId=");
      param1PrintWriter.print(this.mId);
      param1PrintWriter.print(" mArgs=");
      param1PrintWriter.println(this.mArgs);
      param1PrintWriter.print(param1String);
      param1PrintWriter.print("mCallbacks=");
      param1PrintWriter.println(this.mCallbacks);
      param1PrintWriter.print(param1String);
      param1PrintWriter.print("mLoader=");
      param1PrintWriter.println(this.mLoader);
      Loader<Object> loader = this.mLoader;
      if (loader != null) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(param1String);
        stringBuilder.append("  ");
        loader.dump(stringBuilder.toString(), param1FileDescriptor, param1PrintWriter, param1ArrayOfString);
      } 
      if (this.mHaveData || this.mDeliveredData) {
        param1PrintWriter.print(param1String);
        param1PrintWriter.print("mHaveData=");
        param1PrintWriter.print(this.mHaveData);
        param1PrintWriter.print("  mDeliveredData=");
        param1PrintWriter.println(this.mDeliveredData);
        param1PrintWriter.print(param1String);
        param1PrintWriter.print("mData=");
        param1PrintWriter.println(this.mData);
      } 
      param1PrintWriter.print(param1String);
      param1PrintWriter.print("mStarted=");
      param1PrintWriter.print(this.mStarted);
      param1PrintWriter.print(" mReportNextStart=");
      param1PrintWriter.print(this.mReportNextStart);
      param1PrintWriter.print(" mDestroyed=");
      param1PrintWriter.println(this.mDestroyed);
      param1PrintWriter.print(param1String);
      param1PrintWriter.print("mRetaining=");
      param1PrintWriter.print(this.mRetaining);
      param1PrintWriter.print(" mRetainingStarted=");
      param1PrintWriter.print(this.mRetainingStarted);
      param1PrintWriter.print(" mListenerRegistered=");
      param1PrintWriter.println(this.mListenerRegistered);
      if (this.mPendingLoader != null) {
        param1PrintWriter.print(param1String);
        param1PrintWriter.println("Pending Loader ");
        param1PrintWriter.print(this.mPendingLoader);
        param1PrintWriter.println(":");
        LoaderInfo loaderInfo = this.mPendingLoader;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(param1String);
        stringBuilder.append("  ");
        loaderInfo.dump(stringBuilder.toString(), param1FileDescriptor, param1PrintWriter, param1ArrayOfString);
      } 
    }
    
    void finishRetain() {
      if (this.mRetaining) {
        if (LoaderManagerImpl.DEBUG) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("  Finished Retaining: ");
          stringBuilder.append(this);
          Log.v("LoaderManager", stringBuilder.toString());
        } 
        this.mRetaining = false;
        boolean bool = this.mStarted;
        if (bool != this.mRetainingStarted && !bool)
          stop(); 
      } 
      if (this.mStarted && this.mHaveData && !this.mReportNextStart)
        callOnLoadFinished(this.mLoader, this.mData); 
    }
    
    public void onLoadCanceled(Loader<Object> param1Loader) {
      if (LoaderManagerImpl.DEBUG) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onLoadCanceled: ");
        stringBuilder.append(this);
        Log.v("LoaderManager", stringBuilder.toString());
      } 
      if (this.mDestroyed) {
        if (LoaderManagerImpl.DEBUG)
          Log.v("LoaderManager", "  Ignoring load canceled -- destroyed"); 
        return;
      } 
      if (LoaderManagerImpl.this.mLoaders.get(this.mId) != this) {
        if (LoaderManagerImpl.DEBUG)
          Log.v("LoaderManager", "  Ignoring load canceled -- not active"); 
        return;
      } 
      LoaderInfo loaderInfo = this.mPendingLoader;
      if (loaderInfo != null) {
        if (LoaderManagerImpl.DEBUG) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("  Switching to pending loader: ");
          stringBuilder.append(loaderInfo);
          Log.v("LoaderManager", stringBuilder.toString());
        } 
        this.mPendingLoader = null;
        LoaderManagerImpl.this.mLoaders.put(this.mId, null);
        destroy();
        LoaderManagerImpl.this.installLoader(loaderInfo);
      } 
    }
    
    public void onLoadComplete(Loader<Object> param1Loader, Object param1Object) {
      StringBuilder stringBuilder;
      if (LoaderManagerImpl.DEBUG) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("onLoadComplete: ");
        stringBuilder1.append(this);
        Log.v("LoaderManager", stringBuilder1.toString());
      } 
      if (this.mDestroyed) {
        if (LoaderManagerImpl.DEBUG)
          Log.v("LoaderManager", "  Ignoring load complete -- destroyed"); 
        return;
      } 
      if (LoaderManagerImpl.this.mLoaders.get(this.mId) != this) {
        if (LoaderManagerImpl.DEBUG)
          Log.v("LoaderManager", "  Ignoring load complete -- not active"); 
        return;
      } 
      LoaderInfo loaderInfo2 = this.mPendingLoader;
      if (loaderInfo2 != null) {
        if (LoaderManagerImpl.DEBUG) {
          stringBuilder = new StringBuilder();
          stringBuilder.append("  Switching to pending loader: ");
          stringBuilder.append(loaderInfo2);
          Log.v("LoaderManager", stringBuilder.toString());
        } 
        this.mPendingLoader = null;
        LoaderManagerImpl.this.mLoaders.put(this.mId, null);
        destroy();
        LoaderManagerImpl.this.installLoader(loaderInfo2);
        return;
      } 
      if (this.mData != param1Object || !this.mHaveData) {
        this.mData = param1Object;
        this.mHaveData = true;
        if (this.mStarted)
          callOnLoadFinished((Loader<Object>)stringBuilder, param1Object); 
      } 
      LoaderInfo loaderInfo1 = (LoaderInfo)LoaderManagerImpl.this.mInactiveLoaders.get(this.mId);
      if (loaderInfo1 != null && loaderInfo1 != this) {
        loaderInfo1.mDeliveredData = false;
        loaderInfo1.destroy();
        LoaderManagerImpl.this.mInactiveLoaders.remove(this.mId);
      } 
      LoaderManagerImpl loaderManagerImpl = LoaderManagerImpl.this;
      if (loaderManagerImpl.mHost != null && !loaderManagerImpl.hasRunningLoaders())
        LoaderManagerImpl.this.mHost.mFragmentManager.startPendingDeferredFragments(); 
    }
    
    void reportStart() {
      if (this.mStarted && this.mReportNextStart) {
        this.mReportNextStart = false;
        if (this.mHaveData && !this.mRetaining)
          callOnLoadFinished(this.mLoader, this.mData); 
      } 
    }
    
    void retain() {
      if (LoaderManagerImpl.DEBUG) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("  Retaining: ");
        stringBuilder.append(this);
        Log.v("LoaderManager", stringBuilder.toString());
      } 
      this.mRetaining = true;
      this.mRetainingStarted = this.mStarted;
      this.mStarted = false;
      this.mCallbacks = null;
    }
    
    void start() {
      if (this.mRetaining && this.mRetainingStarted) {
        this.mStarted = true;
        return;
      } 
      if (this.mStarted)
        return; 
      this.mStarted = true;
      if (LoaderManagerImpl.DEBUG) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("  Starting: ");
        stringBuilder.append(this);
        Log.v("LoaderManager", stringBuilder.toString());
      } 
      if (this.mLoader == null) {
        LoaderManager.LoaderCallbacks<Object> loaderCallbacks = this.mCallbacks;
        if (loaderCallbacks != null)
          this.mLoader = loaderCallbacks.onCreateLoader(this.mId, this.mArgs); 
      } 
      Loader<Object> loader = this.mLoader;
      if (loader != null) {
        if (!loader.getClass().isMemberClass() || Modifier.isStatic(this.mLoader.getClass().getModifiers())) {
          if (!this.mListenerRegistered) {
            this.mLoader.registerListener(this.mId, this);
            this.mLoader.registerOnLoadCanceledListener(this);
            this.mListenerRegistered = true;
          } 
          this.mLoader.startLoading();
          return;
        } 
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Object returned from onCreateLoader must not be a non-static inner member class: ");
        stringBuilder.append(this.mLoader);
        throw new IllegalArgumentException(stringBuilder.toString());
      } 
    }
    
    void stop() {
      if (LoaderManagerImpl.DEBUG) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("  Stopping: ");
        stringBuilder.append(this);
        Log.v("LoaderManager", stringBuilder.toString());
      } 
      this.mStarted = false;
      if (!this.mRetaining) {
        Loader<Object> loader = this.mLoader;
        if (loader != null && this.mListenerRegistered) {
          this.mListenerRegistered = false;
          loader.unregisterListener(this);
          this.mLoader.unregisterOnLoadCanceledListener(this);
          this.mLoader.stopLoading();
        } 
      } 
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder(64);
      stringBuilder.append("LoaderInfo{");
      stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
      stringBuilder.append(" #");
      stringBuilder.append(this.mId);
      stringBuilder.append(" : ");
      DebugUtils.buildShortClassTag(this.mLoader, stringBuilder);
      stringBuilder.append("}}");
      return stringBuilder.toString();
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/app/LoaderManagerImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */