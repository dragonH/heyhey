package android.support.v7.app;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class MediaRouteControllerDialogFragment extends DialogFragment {
  private MediaRouteControllerDialog mDialog;
  
  public MediaRouteControllerDialogFragment() {
    setCancelable(true);
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration) {
    super.onConfigurationChanged(paramConfiguration);
    MediaRouteControllerDialog mediaRouteControllerDialog = this.mDialog;
    if (mediaRouteControllerDialog != null)
      mediaRouteControllerDialog.updateLayout(); 
  }
  
  public MediaRouteControllerDialog onCreateControllerDialog(Context paramContext, Bundle paramBundle) {
    return new MediaRouteControllerDialog(paramContext);
  }
  
  public Dialog onCreateDialog(Bundle paramBundle) {
    MediaRouteControllerDialog mediaRouteControllerDialog = onCreateControllerDialog(getContext(), paramBundle);
    this.mDialog = mediaRouteControllerDialog;
    return mediaRouteControllerDialog;
  }
  
  public void onStop() {
    super.onStop();
    MediaRouteControllerDialog mediaRouteControllerDialog = this.mDialog;
    if (mediaRouteControllerDialog != null)
      mediaRouteControllerDialog.clearGroupListAnimation(false); 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/app/MediaRouteControllerDialogFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */