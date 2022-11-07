package crc646957603ea1820544;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class MediaPickerActivity extends Activity implements IGCUserPeer, MediaScannerConnection.OnScanCompletedListener {
  public static final String __md_methods = "n_onSaveInstanceState:(Landroid/os/Bundle;)V:GetOnSaveInstanceState_Landroid_os_Bundle_Handler\nn_onCreate:(Landroid/os/Bundle;)V:GetOnCreate_Landroid_os_Bundle_Handler\nn_onActivityResult:(IILandroid/content/Intent;)V:GetOnActivityResult_IILandroid_content_Intent_Handler\nn_onDestroy:()V:GetOnDestroyHandler\nn_onScanCompleted:(Ljava/lang/String;Landroid/net/Uri;)V:GetOnScanCompleted_Ljava_lang_String_Landroid_net_Uri_Handler:Android.Media.MediaScannerConnection/IOnScanCompletedListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Plugin.Media.MediaPickerActivity, Plugin.Media", MediaPickerActivity.class, "n_onSaveInstanceState:(Landroid/os/Bundle;)V:GetOnSaveInstanceState_Landroid_os_Bundle_Handler\nn_onCreate:(Landroid/os/Bundle;)V:GetOnCreate_Landroid_os_Bundle_Handler\nn_onActivityResult:(IILandroid/content/Intent;)V:GetOnActivityResult_IILandroid_content_Intent_Handler\nn_onDestroy:()V:GetOnDestroyHandler\nn_onScanCompleted:(Ljava/lang/String;Landroid/net/Uri;)V:GetOnScanCompleted_Ljava_lang_String_Landroid_net_Uri_Handler:Android.Media.MediaScannerConnection/IOnScanCompletedListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public MediaPickerActivity() {
    if (getClass() == MediaPickerActivity.class)
      TypeManager.Activate("Plugin.Media.MediaPickerActivity, Plugin.Media", "", this, new Object[0]); 
  }
  
  private native void n_onActivityResult(int paramInt1, int paramInt2, Intent paramIntent);
  
  private native void n_onCreate(Bundle paramBundle);
  
  private native void n_onDestroy();
  
  private native void n_onSaveInstanceState(Bundle paramBundle);
  
  private native void n_onScanCompleted(String paramString, Uri paramUri);
  
  public void monodroidAddReference(Object paramObject) {
    if (this.refList == null)
      this.refList = new ArrayList(); 
    this.refList.add(paramObject);
  }
  
  public void monodroidClearReferences() {
    ArrayList arrayList = this.refList;
    if (arrayList != null)
      arrayList.clear(); 
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
    n_onActivityResult(paramInt1, paramInt2, paramIntent);
  }
  
  public void onCreate(Bundle paramBundle) {
    n_onCreate(paramBundle);
  }
  
  public void onDestroy() {
    n_onDestroy();
  }
  
  public void onSaveInstanceState(Bundle paramBundle) {
    n_onSaveInstanceState(paramBundle);
  }
  
  public void onScanCompleted(String paramString, Uri paramUri) {
    n_onScanCompleted(paramString, paramUri);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/crc646957603ea1820544/MediaPickerActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */