package android.support.v4.app;

import android.app.RemoteInput;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import java.util.HashMap;
import java.util.Map;

@RequiresApi(20)
class RemoteInputCompatApi20 {
  private static final String EXTRA_DATA_TYPE_RESULTS_DATA = "android.remoteinput.dataTypeResultsData";
  
  public static void addDataResultToIntent(RemoteInputCompatBase.RemoteInput paramRemoteInput, Intent paramIntent, Map<String, Uri> paramMap) {
    Intent intent1 = getClipDataIntentFromIntent(paramIntent);
    Intent intent2 = intent1;
    if (intent1 == null)
      intent2 = new Intent(); 
    for (Map.Entry<String, Uri> entry : paramMap.entrySet()) {
      String str = (String)entry.getKey();
      Uri uri = (Uri)entry.getValue();
      if (str == null)
        continue; 
      Bundle bundle2 = intent2.getBundleExtra(getExtraResultsKeyForData(str));
      Bundle bundle1 = bundle2;
      if (bundle2 == null)
        bundle1 = new Bundle(); 
      bundle1.putString(paramRemoteInput.getResultKey(), uri.toString());
      intent2.putExtra(getExtraResultsKeyForData(str), bundle1);
    } 
    paramIntent.setClipData(ClipData.newIntent("android.remoteinput.results", intent2));
  }
  
  static void addResultsToIntent(RemoteInputCompatBase.RemoteInput[] paramArrayOfRemoteInput, Intent paramIntent, Bundle paramBundle) {
    Bundle bundle = getResultsFromIntent(paramIntent);
    if (bundle != null) {
      bundle.putAll(paramBundle);
      paramBundle = bundle;
    } 
    int i = paramArrayOfRemoteInput.length;
    for (byte b = 0; b < i; b++) {
      RemoteInputCompatBase.RemoteInput remoteInput = paramArrayOfRemoteInput[b];
      Map<String, Uri> map = getDataResultsFromIntent(paramIntent, remoteInput.getResultKey());
      RemoteInput.addResultsToIntent(fromCompat(new RemoteInputCompatBase.RemoteInput[] { remoteInput }, ), paramIntent, paramBundle);
      if (map != null)
        addDataResultToIntent(remoteInput, paramIntent, map); 
    } 
  }
  
  static RemoteInput[] fromCompat(RemoteInputCompatBase.RemoteInput[] paramArrayOfRemoteInput) {
    if (paramArrayOfRemoteInput == null)
      return null; 
    RemoteInput[] arrayOfRemoteInput = new RemoteInput[paramArrayOfRemoteInput.length];
    for (byte b = 0; b < paramArrayOfRemoteInput.length; b++) {
      RemoteInputCompatBase.RemoteInput remoteInput = paramArrayOfRemoteInput[b];
      arrayOfRemoteInput[b] = (new RemoteInput.Builder(remoteInput.getResultKey())).setLabel(remoteInput.getLabel()).setChoices(remoteInput.getChoices()).setAllowFreeFormInput(remoteInput.getAllowFreeFormInput()).addExtras(remoteInput.getExtras()).build();
    } 
    return arrayOfRemoteInput;
  }
  
  private static Intent getClipDataIntentFromIntent(Intent paramIntent) {
    ClipData clipData = paramIntent.getClipData();
    if (clipData == null)
      return null; 
    ClipDescription clipDescription = clipData.getDescription();
    return !clipDescription.hasMimeType("text/vnd.android.intent") ? null : (!clipDescription.getLabel().equals("android.remoteinput.results") ? null : clipData.getItemAt(0).getIntent());
  }
  
  static Map<String, Uri> getDataResultsFromIntent(Intent paramIntent, String paramString) {
    Intent intent = getClipDataIntentFromIntent(paramIntent);
    HashMap hashMap1 = null;
    if (intent == null)
      return null; 
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    for (String str : intent.getExtras().keySet()) {
      if (str.startsWith("android.remoteinput.dataTypeResultsData")) {
        String str1 = str.substring(39);
        if (str1 == null || str1.isEmpty())
          continue; 
        str = intent.getBundleExtra(str).getString(paramString);
        if (str == null || str.isEmpty())
          continue; 
        hashMap.put(str1, Uri.parse(str));
      } 
    } 
    if (hashMap.isEmpty())
      hashMap = hashMap1; 
    return (Map)hashMap;
  }
  
  private static String getExtraResultsKeyForData(String paramString) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("android.remoteinput.dataTypeResultsData");
    stringBuilder.append(paramString);
    return stringBuilder.toString();
  }
  
  static Bundle getResultsFromIntent(Intent paramIntent) {
    return RemoteInput.getResultsFromIntent(paramIntent);
  }
  
  static RemoteInputCompatBase.RemoteInput[] toCompat(RemoteInput[] paramArrayOfRemoteInput, RemoteInputCompatBase.RemoteInput.Factory paramFactory) {
    if (paramArrayOfRemoteInput == null)
      return null; 
    RemoteInputCompatBase.RemoteInput[] arrayOfRemoteInput = paramFactory.newArray(paramArrayOfRemoteInput.length);
    for (byte b = 0; b < paramArrayOfRemoteInput.length; b++) {
      RemoteInput remoteInput = paramArrayOfRemoteInput[b];
      arrayOfRemoteInput[b] = paramFactory.build(remoteInput.getResultKey(), remoteInput.getLabel(), remoteInput.getChoices(), remoteInput.getAllowFreeFormInput(), remoteInput.getExtras(), null);
    } 
    return arrayOfRemoteInput;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/app/RemoteInputCompatApi20.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */