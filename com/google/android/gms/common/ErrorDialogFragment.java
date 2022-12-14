package com.google.android.gms.common;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import com.google.android.gms.common.internal.zzbp;

public class ErrorDialogFragment extends DialogFragment {
  private Dialog mDialog = null;
  
  private DialogInterface.OnCancelListener zzffh = null;
  
  public static ErrorDialogFragment newInstance(Dialog paramDialog) {
    return newInstance(paramDialog, null);
  }
  
  public static ErrorDialogFragment newInstance(Dialog paramDialog, DialogInterface.OnCancelListener paramOnCancelListener) {
    ErrorDialogFragment errorDialogFragment = new ErrorDialogFragment();
    paramDialog = (Dialog)zzbp.zzb(paramDialog, "Cannot display null dialog");
    paramDialog.setOnCancelListener(null);
    paramDialog.setOnDismissListener(null);
    errorDialogFragment.mDialog = paramDialog;
    if (paramOnCancelListener != null)
      errorDialogFragment.zzffh = paramOnCancelListener; 
    return errorDialogFragment;
  }
  
  public void onCancel(DialogInterface paramDialogInterface) {
    DialogInterface.OnCancelListener onCancelListener = this.zzffh;
    if (onCancelListener != null)
      onCancelListener.onCancel(paramDialogInterface); 
  }
  
  public Dialog onCreateDialog(Bundle paramBundle) {
    if (this.mDialog == null)
      setShowsDialog(false); 
    return this.mDialog;
  }
  
  public void show(FragmentManager paramFragmentManager, String paramString) {
    super.show(paramFragmentManager, paramString);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/ErrorDialogFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */