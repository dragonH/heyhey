package android.support.v4.app;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@RequiresApi(16)
class RemoteInputCompatJellybean {
  private static final String EXTRA_DATA_TYPE_RESULTS_DATA = "android.remoteinput.dataTypeResultsData";
  
  private static final String KEY_ALLOWED_DATA_TYPES = "allowedDataTypes";
  
  private static final String KEY_ALLOW_FREE_FORM_INPUT = "allowFreeFormInput";
  
  private static final String KEY_CHOICES = "choices";
  
  private static final String KEY_EXTRAS = "extras";
  
  private static final String KEY_LABEL = "label";
  
  private static final String KEY_RESULT_KEY = "resultKey";
  
  public static void addDataResultToIntent(RemoteInput paramRemoteInput, Intent paramIntent, Map<String, Uri> paramMap) {
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
    Intent intent1 = getClipDataIntentFromIntent(paramIntent);
    Intent intent2 = intent1;
    if (intent1 == null)
      intent2 = new Intent(); 
    Bundle bundle2 = intent2.getBundleExtra("android.remoteinput.resultsData");
    Bundle bundle1 = bundle2;
    if (bundle2 == null)
      bundle1 = new Bundle(); 
    int i = paramArrayOfRemoteInput.length;
    for (byte b = 0; b < i; b++) {
      RemoteInputCompatBase.RemoteInput remoteInput = paramArrayOfRemoteInput[b];
      Object object = paramBundle.get(remoteInput.getResultKey());
      if (object instanceof CharSequence)
        bundle1.putCharSequence(remoteInput.getResultKey(), (CharSequence)object); 
    } 
    intent2.putExtra("android.remoteinput.resultsData", bundle1);
    paramIntent.setClipData(ClipData.newIntent("android.remoteinput.results", intent2));
  }
  
  static RemoteInputCompatBase.RemoteInput fromBundle(Bundle paramBundle, RemoteInputCompatBase.RemoteInput.Factory paramFactory) {
    ArrayList arrayList = paramBundle.getStringArrayList("allowedDataTypes");
    HashSet<String> hashSet = new HashSet();
    if (arrayList != null) {
      Iterator<String> iterator = arrayList.iterator();
      while (iterator.hasNext())
        hashSet.add(iterator.next()); 
    } 
    return paramFactory.build(paramBundle.getString("resultKey"), paramBundle.getCharSequence("label"), paramBundle.getCharSequenceArray("choices"), paramBundle.getBoolean("allowFreeFormInput"), paramBundle.getBundle("extras"), hashSet);
  }
  
  static RemoteInputCompatBase.RemoteInput[] fromBundleArray(Bundle[] paramArrayOfBundle, RemoteInputCompatBase.RemoteInput.Factory paramFactory) {
    if (paramArrayOfBundle == null)
      return null; 
    RemoteInputCompatBase.RemoteInput[] arrayOfRemoteInput = paramFactory.newArray(paramArrayOfBundle.length);
    for (byte b = 0; b < paramArrayOfBundle.length; b++)
      arrayOfRemoteInput[b] = fromBundle(paramArrayOfBundle[b], paramFactory); 
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
    paramIntent = getClipDataIntentFromIntent(paramIntent);
    return (paramIntent == null) ? null : (Bundle)paramIntent.getExtras().getParcelable("android.remoteinput.resultsData");
  }
  
  static Bundle toBundle(RemoteInputCompatBase.RemoteInput paramRemoteInput) {
    Bundle bundle = new Bundle();
    bundle.putString("resultKey", paramRemoteInput.getResultKey());
    bundle.putCharSequence("label", paramRemoteInput.getLabel());
    bundle.putCharSequenceArray("choices", paramRemoteInput.getChoices());
    bundle.putBoolean("allowFreeFormInput", paramRemoteInput.getAllowFreeFormInput());
    bundle.putBundle("extras", paramRemoteInput.getExtras());
    Set<String> set = paramRemoteInput.getAllowedDataTypes();
    if (set != null && !set.isEmpty()) {
      ArrayList<String> arrayList = new ArrayList(set.size());
      Iterator<String> iterator = set.iterator();
      while (iterator.hasNext())
        arrayList.add(iterator.next()); 
      bundle.putStringArrayList("allowedDataTypes", arrayList);
    } 
    return bundle;
  }
  
  static Bundle[] toBundleArray(RemoteInputCompatBase.RemoteInput[] paramArrayOfRemoteInput) {
    if (paramArrayOfRemoteInput == null)
      return null; 
    Bundle[] arrayOfBundle = new Bundle[paramArrayOfRemoteInput.length];
    for (byte b = 0; b < paramArrayOfRemoteInput.length; b++)
      arrayOfBundle[b] = toBundle(paramArrayOfRemoteInput[b]); 
    return arrayOfBundle;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/app/RemoteInputCompatJellybean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */