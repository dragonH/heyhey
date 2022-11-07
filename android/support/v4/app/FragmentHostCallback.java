package android.support.v4.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.SimpleArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public abstract class FragmentHostCallback<E> extends FragmentContainer {
  private final Activity mActivity;
  
  private SimpleArrayMap<String, LoaderManager> mAllLoaderManagers;
  
  private boolean mCheckedForLoaderManager;
  
  final Context mContext;
  
  final FragmentManagerImpl mFragmentManager = new FragmentManagerImpl();
  
  private final Handler mHandler;
  
  private LoaderManagerImpl mLoaderManager;
  
  private boolean mLoadersStarted;
  
  private boolean mRetainLoaders;
  
  final int mWindowAnimations;
  
  FragmentHostCallback(Activity paramActivity, Context paramContext, Handler paramHandler, int paramInt) {
    this.mActivity = paramActivity;
    this.mContext = paramContext;
    this.mHandler = paramHandler;
    this.mWindowAnimations = paramInt;
  }
  
  public FragmentHostCallback(Context paramContext, Handler paramHandler, int paramInt) {
    this(activity, paramContext, paramHandler, paramInt);
  }
  
  FragmentHostCallback(FragmentActivity paramFragmentActivity) {
    this(paramFragmentActivity, (Context)paramFragmentActivity, paramFragmentActivity.mHandler, 0);
  }
  
  void doLoaderDestroy() {
    LoaderManagerImpl loaderManagerImpl = this.mLoaderManager;
    if (loaderManagerImpl == null)
      return; 
    loaderManagerImpl.doDestroy();
  }
  
  void doLoaderRetain() {
    LoaderManagerImpl loaderManagerImpl = this.mLoaderManager;
    if (loaderManagerImpl == null)
      return; 
    loaderManagerImpl.doRetain();
  }
  
  void doLoaderStart() {
    if (this.mLoadersStarted)
      return; 
    this.mLoadersStarted = true;
    LoaderManagerImpl loaderManagerImpl = this.mLoaderManager;
    if (loaderManagerImpl != null) {
      loaderManagerImpl.doStart();
    } else if (!this.mCheckedForLoaderManager) {
      loaderManagerImpl = getLoaderManager("(root)", true, false);
      this.mLoaderManager = loaderManagerImpl;
      if (loaderManagerImpl != null && !loaderManagerImpl.mStarted)
        loaderManagerImpl.doStart(); 
    } 
    this.mCheckedForLoaderManager = true;
  }
  
  void doLoaderStop(boolean paramBoolean) {
    this.mRetainLoaders = paramBoolean;
    LoaderManagerImpl loaderManagerImpl = this.mLoaderManager;
    if (loaderManagerImpl == null)
      return; 
    if (!this.mLoadersStarted)
      return; 
    this.mLoadersStarted = false;
    if (paramBoolean) {
      loaderManagerImpl.doRetain();
    } else {
      loaderManagerImpl.doStop();
    } 
  }
  
  void dumpLoaders(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mLoadersStarted=");
    paramPrintWriter.println(this.mLoadersStarted);
    if (this.mLoaderManager != null) {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("Loader Manager ");
      paramPrintWriter.print(Integer.toHexString(System.identityHashCode(this.mLoaderManager)));
      paramPrintWriter.println(":");
      LoaderManagerImpl loaderManagerImpl = this.mLoaderManager;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append("  ");
      loaderManagerImpl.dump(stringBuilder.toString(), paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    } 
  }
  
  Activity getActivity() {
    return this.mActivity;
  }
  
  Context getContext() {
    return this.mContext;
  }
  
  FragmentManagerImpl getFragmentManagerImpl() {
    return this.mFragmentManager;
  }
  
  Handler getHandler() {
    return this.mHandler;
  }
  
  LoaderManagerImpl getLoaderManager(String paramString, boolean paramBoolean1, boolean paramBoolean2) {
    LoaderManagerImpl loaderManagerImpl1;
    if (this.mAllLoaderManagers == null)
      this.mAllLoaderManagers = new SimpleArrayMap(); 
    LoaderManagerImpl loaderManagerImpl2 = (LoaderManagerImpl)this.mAllLoaderManagers.get(paramString);
    if (loaderManagerImpl2 == null && paramBoolean2) {
      loaderManagerImpl2 = new LoaderManagerImpl(paramString, this, paramBoolean1);
      this.mAllLoaderManagers.put(paramString, loaderManagerImpl2);
      loaderManagerImpl1 = loaderManagerImpl2;
    } else {
      loaderManagerImpl1 = loaderManagerImpl2;
      if (paramBoolean1) {
        loaderManagerImpl1 = loaderManagerImpl2;
        if (loaderManagerImpl2 != null) {
          loaderManagerImpl1 = loaderManagerImpl2;
          if (!loaderManagerImpl2.mStarted) {
            loaderManagerImpl2.doStart();
            loaderManagerImpl1 = loaderManagerImpl2;
          } 
        } 
      } 
    } 
    return loaderManagerImpl1;
  }
  
  LoaderManagerImpl getLoaderManagerImpl() {
    LoaderManagerImpl loaderManagerImpl = this.mLoaderManager;
    if (loaderManagerImpl != null)
      return loaderManagerImpl; 
    this.mCheckedForLoaderManager = true;
    loaderManagerImpl = getLoaderManager("(root)", this.mLoadersStarted, true);
    this.mLoaderManager = loaderManagerImpl;
    return loaderManagerImpl;
  }
  
  boolean getRetainLoaders() {
    return this.mRetainLoaders;
  }
  
  void inactivateFragment(String paramString) {
    SimpleArrayMap<String, LoaderManager> simpleArrayMap = this.mAllLoaderManagers;
    if (simpleArrayMap != null) {
      LoaderManagerImpl loaderManagerImpl = (LoaderManagerImpl)simpleArrayMap.get(paramString);
      if (loaderManagerImpl != null && !loaderManagerImpl.mRetaining) {
        loaderManagerImpl.doDestroy();
        this.mAllLoaderManagers.remove(paramString);
      } 
    } 
  }
  
  void onAttachFragment(Fragment paramFragment) {}
  
  public void onDump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {}
  
  @Nullable
  public View onFindViewById(int paramInt) {
    return null;
  }
  
  @Nullable
  public abstract E onGetHost();
  
  public LayoutInflater onGetLayoutInflater() {
    return (LayoutInflater)this.mContext.getSystemService("layout_inflater");
  }
  
  public int onGetWindowAnimations() {
    return this.mWindowAnimations;
  }
  
  public boolean onHasView() {
    return true;
  }
  
  public boolean onHasWindowAnimations() {
    return true;
  }
  
  public void onRequestPermissionsFromFragment(@NonNull Fragment paramFragment, @NonNull String[] paramArrayOfString, int paramInt) {}
  
  public boolean onShouldSaveFragmentState(Fragment paramFragment) {
    return true;
  }
  
  public boolean onShouldShowRequestPermissionRationale(@NonNull String paramString) {
    return false;
  }
  
  public void onStartActivityFromFragment(Fragment paramFragment, Intent paramIntent, int paramInt) {
    onStartActivityFromFragment(paramFragment, paramIntent, paramInt, null);
  }
  
  public void onStartActivityFromFragment(Fragment paramFragment, Intent paramIntent, int paramInt, @Nullable Bundle paramBundle) {
    if (paramInt == -1) {
      this.mContext.startActivity(paramIntent);
      return;
    } 
    throw new IllegalStateException("Starting activity with a requestCode requires a FragmentActivity host");
  }
  
  public void onStartIntentSenderFromFragment(Fragment paramFragment, IntentSender paramIntentSender, int paramInt1, @Nullable Intent paramIntent, int paramInt2, int paramInt3, int paramInt4, Bundle paramBundle) throws IntentSender.SendIntentException {
    if (paramInt1 == -1) {
      ActivityCompat.startIntentSenderForResult(this.mActivity, paramIntentSender, paramInt1, paramIntent, paramInt2, paramInt3, paramInt4, paramBundle);
      return;
    } 
    throw new IllegalStateException("Starting intent sender with a requestCode requires a FragmentActivity host");
  }
  
  public void onSupportInvalidateOptionsMenu() {}
  
  void reportLoaderStart() {
    SimpleArrayMap<String, LoaderManager> simpleArrayMap = this.mAllLoaderManagers;
    if (simpleArrayMap != null) {
      int i = simpleArrayMap.size();
      LoaderManagerImpl[] arrayOfLoaderManagerImpl = new LoaderManagerImpl[i];
      int j;
      for (j = i - 1; j >= 0; j--)
        arrayOfLoaderManagerImpl[j] = (LoaderManagerImpl)this.mAllLoaderManagers.valueAt(j); 
      for (j = 0; j < i; j++) {
        LoaderManagerImpl loaderManagerImpl = arrayOfLoaderManagerImpl[j];
        loaderManagerImpl.finishRetain();
        loaderManagerImpl.doReportStart();
      } 
    } 
  }
  
  void restoreLoaderNonConfig(SimpleArrayMap<String, LoaderManager> paramSimpleArrayMap) {
    if (paramSimpleArrayMap != null) {
      byte b = 0;
      int i = paramSimpleArrayMap.size();
      while (b < i) {
        ((LoaderManagerImpl)paramSimpleArrayMap.valueAt(b)).updateHostController(this);
        b++;
      } 
    } 
    this.mAllLoaderManagers = paramSimpleArrayMap;
  }
  
  SimpleArrayMap<String, LoaderManager> retainLoaderNonConfig() {
    SimpleArrayMap<String, LoaderManager> simpleArrayMap = this.mAllLoaderManagers;
    int i = 0;
    byte b = 0;
    if (simpleArrayMap != null) {
      int j = simpleArrayMap.size();
      LoaderManagerImpl[] arrayOfLoaderManagerImpl = new LoaderManagerImpl[j];
      for (i = j - 1; i >= 0; i--)
        arrayOfLoaderManagerImpl[i] = (LoaderManagerImpl)this.mAllLoaderManagers.valueAt(i); 
      boolean bool = getRetainLoaders();
      i = 0;
      while (b < j) {
        LoaderManagerImpl loaderManagerImpl = arrayOfLoaderManagerImpl[b];
        if (!loaderManagerImpl.mRetaining && bool) {
          if (!loaderManagerImpl.mStarted)
            loaderManagerImpl.doStart(); 
          loaderManagerImpl.doRetain();
        } 
        if (loaderManagerImpl.mRetaining) {
          i = 1;
        } else {
          loaderManagerImpl.doDestroy();
          this.mAllLoaderManagers.remove(loaderManagerImpl.mWho);
        } 
        b++;
      } 
    } 
    return (i != 0) ? this.mAllLoaderManagers : null;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/app/FragmentHostCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */