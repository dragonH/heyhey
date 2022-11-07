package android.support.v7.app;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.media.MediaRouteSelector;

public class MediaRouteChooserDialogFragment extends DialogFragment {
  private final String ARGUMENT_SELECTOR = "selector";
  
  private MediaRouteChooserDialog mDialog;
  
  private MediaRouteSelector mSelector;
  
  public MediaRouteChooserDialogFragment() {
    setCancelable(true);
  }
  
  private void ensureRouteSelector() {
    if (this.mSelector == null) {
      Bundle bundle = getArguments();
      if (bundle != null)
        this.mSelector = MediaRouteSelector.fromBundle(bundle.getBundle("selector")); 
      if (this.mSelector == null)
        this.mSelector = MediaRouteSelector.EMPTY; 
    } 
  }
  
  public MediaRouteSelector getRouteSelector() {
    ensureRouteSelector();
    return this.mSelector;
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration) {
    super.onConfigurationChanged(paramConfiguration);
    MediaRouteChooserDialog mediaRouteChooserDialog = this.mDialog;
    if (mediaRouteChooserDialog != null)
      mediaRouteChooserDialog.updateLayout(); 
  }
  
  public MediaRouteChooserDialog onCreateChooserDialog(Context paramContext, Bundle paramBundle) {
    return new MediaRouteChooserDialog(paramContext);
  }
  
  public Dialog onCreateDialog(Bundle paramBundle) {
    MediaRouteChooserDialog mediaRouteChooserDialog = onCreateChooserDialog(getContext(), paramBundle);
    this.mDialog = mediaRouteChooserDialog;
    mediaRouteChooserDialog.setRouteSelector(getRouteSelector());
    return this.mDialog;
  }
  
  public void setRouteSelector(MediaRouteSelector paramMediaRouteSelector) {
    if (paramMediaRouteSelector != null) {
      ensureRouteSelector();
      if (!this.mSelector.equals(paramMediaRouteSelector)) {
        this.mSelector = paramMediaRouteSelector;
        Bundle bundle1 = getArguments();
        Bundle bundle2 = bundle1;
        if (bundle1 == null)
          bundle2 = new Bundle(); 
        bundle2.putBundle("selector", paramMediaRouteSelector.asBundle());
        setArguments(bundle2);
        MediaRouteChooserDialog mediaRouteChooserDialog = (MediaRouteChooserDialog)getDialog();
        if (mediaRouteChooserDialog != null)
          mediaRouteChooserDialog.setRouteSelector(paramMediaRouteSelector); 
      } 
      return;
    } 
    throw new IllegalArgumentException("selector must not be null");
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/app/MediaRouteChooserDialogFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */