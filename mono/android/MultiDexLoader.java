package mono.android;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import mono.android.incrementaldeployment.IncrementalClassLoader;

public class MultiDexLoader extends ContentProvider {
  private List<String> getDexList(String paramString) {
    return new ArrayList<String>();
  }
  
  public void attachInfo(Context paramContext, ProviderInfo paramProviderInfo) {
    File file = paramContext.getCacheDir();
    String str1 = (paramContext.getApplicationInfo()).nativeLibraryDir;
    String str2 = (paramContext.getApplicationInfo()).dataDir;
    str2 = paramContext.getPackageName();
    List<String> list = getDexList(str2);
    if (list != null && list.size() > 0)
      IncrementalClassLoader.inject(MultiDexLoader.class.getClassLoader(), str2, file, str1, list); 
    super.attachInfo(paramContext, paramProviderInfo);
  }
  
  public int delete(Uri paramUri, String paramString, String[] paramArrayOfString) {
    throw new RuntimeException("This operation is not supported.");
  }
  
  public String getType(Uri paramUri) {
    throw new RuntimeException("This operation is not supported.");
  }
  
  public Uri insert(Uri paramUri, ContentValues paramContentValues) {
    throw new RuntimeException("This operation is not supported.");
  }
  
  public boolean onCreate() {
    return true;
  }
  
  public Cursor query(Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2) {
    throw new RuntimeException("This operation is not supported.");
  }
  
  public int update(Uri paramUri, ContentValues paramContentValues, String paramString, String[] paramArrayOfString) {
    throw new RuntimeException("This operation is not supported.");
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/MultiDexLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */