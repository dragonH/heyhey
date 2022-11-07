package android.support.v4.app;

import android.app.Activity;
import android.app.SharedElementCallback;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.v4.content.ContextCompat;
import android.view.View;
import java.util.List;
import java.util.Map;

public class ActivityCompat extends ContextCompat {
  public static void finishAffinity(Activity paramActivity) {
    paramActivity.finishAffinity();
  }
  
  public static void finishAfterTransition(Activity paramActivity) {
    if (Build.VERSION.SDK_INT >= 21) {
      paramActivity.finishAfterTransition();
    } else {
      paramActivity.finish();
    } 
  }
  
  @Nullable
  public static Uri getReferrer(Activity paramActivity) {
    if (Build.VERSION.SDK_INT >= 22)
      return paramActivity.getReferrer(); 
    Intent intent = paramActivity.getIntent();
    Uri uri = (Uri)intent.getParcelableExtra("android.intent.extra.REFERRER");
    if (uri != null)
      return uri; 
    String str = intent.getStringExtra("android.intent.extra.REFERRER_NAME");
    return (str != null) ? Uri.parse(str) : null;
  }
  
  public static boolean invalidateOptionsMenu(Activity paramActivity) {
    paramActivity.invalidateOptionsMenu();
    return true;
  }
  
  public static void postponeEnterTransition(Activity paramActivity) {
    if (Build.VERSION.SDK_INT >= 21)
      paramActivity.postponeEnterTransition(); 
  }
  
  public static void requestPermissions(@NonNull final Activity activity, @NonNull final String[] permissions, @IntRange(from = 0L) final int requestCode) {
    if (Build.VERSION.SDK_INT >= 23) {
      if (activity instanceof RequestPermissionsRequestCodeValidator)
        ((RequestPermissionsRequestCodeValidator)activity).validateRequestPermissionsRequestCode(requestCode); 
      activity.requestPermissions(permissions, requestCode);
    } else if (activity instanceof OnRequestPermissionsResultCallback) {
      (new Handler(Looper.getMainLooper())).post(new Runnable() {
            public void run() {
              int[] arrayOfInt = new int[permissions.length];
              PackageManager packageManager = activity.getPackageManager();
              String str = activity.getPackageName();
              int i = permissions.length;
              for (byte b = 0; b < i; b++)
                arrayOfInt[b] = packageManager.checkPermission(permissions[b], str); 
              ((ActivityCompat.OnRequestPermissionsResultCallback)activity).onRequestPermissionsResult(requestCode, permissions, arrayOfInt);
            }
          });
    } 
  }
  
  public static void setEnterSharedElementCallback(Activity paramActivity, SharedElementCallback paramSharedElementCallback) {
    int i = Build.VERSION.SDK_INT;
    SharedElementCallback23Impl sharedElementCallback23Impl1 = null;
    SharedElementCallback23Impl sharedElementCallback23Impl2 = null;
    if (i >= 23) {
      if (paramSharedElementCallback != null)
        sharedElementCallback23Impl2 = new SharedElementCallback23Impl(paramSharedElementCallback); 
      paramActivity.setEnterSharedElementCallback(sharedElementCallback23Impl2);
    } else if (i >= 21) {
      SharedElementCallback21Impl sharedElementCallback21Impl;
      sharedElementCallback23Impl2 = sharedElementCallback23Impl1;
      if (paramSharedElementCallback != null)
        sharedElementCallback21Impl = new SharedElementCallback21Impl(paramSharedElementCallback); 
      paramActivity.setEnterSharedElementCallback(sharedElementCallback21Impl);
    } 
  }
  
  public static void setExitSharedElementCallback(Activity paramActivity, SharedElementCallback paramSharedElementCallback) {
    int i = Build.VERSION.SDK_INT;
    SharedElementCallback23Impl sharedElementCallback23Impl1 = null;
    SharedElementCallback23Impl sharedElementCallback23Impl2 = null;
    if (i >= 23) {
      if (paramSharedElementCallback != null)
        sharedElementCallback23Impl2 = new SharedElementCallback23Impl(paramSharedElementCallback); 
      paramActivity.setExitSharedElementCallback(sharedElementCallback23Impl2);
    } else if (i >= 21) {
      SharedElementCallback21Impl sharedElementCallback21Impl;
      sharedElementCallback23Impl2 = sharedElementCallback23Impl1;
      if (paramSharedElementCallback != null)
        sharedElementCallback21Impl = new SharedElementCallback21Impl(paramSharedElementCallback); 
      paramActivity.setExitSharedElementCallback(sharedElementCallback21Impl);
    } 
  }
  
  public static boolean shouldShowRequestPermissionRationale(@NonNull Activity paramActivity, @NonNull String paramString) {
    return (Build.VERSION.SDK_INT >= 23) ? paramActivity.shouldShowRequestPermissionRationale(paramString) : false;
  }
  
  public static void startActivityForResult(Activity paramActivity, Intent paramIntent, int paramInt, @Nullable Bundle paramBundle) {
    paramActivity.startActivityForResult(paramIntent, paramInt, paramBundle);
  }
  
  public static void startIntentSenderForResult(Activity paramActivity, IntentSender paramIntentSender, int paramInt1, Intent paramIntent, int paramInt2, int paramInt3, int paramInt4, @Nullable Bundle paramBundle) throws IntentSender.SendIntentException {
    paramActivity.startIntentSenderForResult(paramIntentSender, paramInt1, paramIntent, paramInt2, paramInt3, paramInt4, paramBundle);
  }
  
  public static void startPostponedEnterTransition(Activity paramActivity) {
    if (Build.VERSION.SDK_INT >= 21)
      paramActivity.startPostponedEnterTransition(); 
  }
  
  public static interface OnRequestPermissionsResultCallback {
    void onRequestPermissionsResult(int param1Int, @NonNull String[] param1ArrayOfString, @NonNull int[] param1ArrayOfint);
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static interface RequestPermissionsRequestCodeValidator {
    void validateRequestPermissionsRequestCode(int param1Int);
  }
  
  @RequiresApi(21)
  private static class SharedElementCallback21Impl extends SharedElementCallback {
    protected SharedElementCallback mCallback;
    
    public SharedElementCallback21Impl(SharedElementCallback param1SharedElementCallback) {
      this.mCallback = param1SharedElementCallback;
    }
    
    public Parcelable onCaptureSharedElementSnapshot(View param1View, Matrix param1Matrix, RectF param1RectF) {
      return this.mCallback.onCaptureSharedElementSnapshot(param1View, param1Matrix, param1RectF);
    }
    
    public View onCreateSnapshotView(Context param1Context, Parcelable param1Parcelable) {
      return this.mCallback.onCreateSnapshotView(param1Context, param1Parcelable);
    }
    
    public void onMapSharedElements(List<String> param1List, Map<String, View> param1Map) {
      this.mCallback.onMapSharedElements(param1List, param1Map);
    }
    
    public void onRejectSharedElements(List<View> param1List) {
      this.mCallback.onRejectSharedElements(param1List);
    }
    
    public void onSharedElementEnd(List<String> param1List, List<View> param1List1, List<View> param1List2) {
      this.mCallback.onSharedElementEnd(param1List, param1List1, param1List2);
    }
    
    public void onSharedElementStart(List<String> param1List, List<View> param1List1, List<View> param1List2) {
      this.mCallback.onSharedElementStart(param1List, param1List1, param1List2);
    }
  }
  
  @RequiresApi(23)
  private static class SharedElementCallback23Impl extends SharedElementCallback21Impl {
    public SharedElementCallback23Impl(SharedElementCallback param1SharedElementCallback) {
      super(param1SharedElementCallback);
    }
    
    public void onSharedElementsArrived(List<String> param1List, List<View> param1List1, final SharedElementCallback.OnSharedElementsReadyListener listener) {
      this.mCallback.onSharedElementsArrived(param1List, param1List1, new SharedElementCallback.OnSharedElementsReadyListener() {
            public void onSharedElementsReady() {
              listener.onSharedElementsReady();
            }
          });
    }
  }
  
  class null implements SharedElementCallback.OnSharedElementsReadyListener {
    public void onSharedElementsReady() {
      listener.onSharedElementsReady();
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/app/ActivityCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */