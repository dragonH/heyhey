package android.support.v4.provider;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.support.annotation.RequiresApi;
import android.util.Log;
import java.util.ArrayList;

@RequiresApi(21)
class DocumentsContractApi21 {
  private static final String TAG = "DocumentFile";
  
  private static void closeQuietly(AutoCloseable paramAutoCloseable) {
    if (paramAutoCloseable != null)
      try {
        paramAutoCloseable.close();
      } catch (RuntimeException runtimeException) {
        throw runtimeException;
      } catch (Exception exception) {} 
  }
  
  public static Uri createDirectory(Context paramContext, Uri paramUri, String paramString) {
    return createFile(paramContext, paramUri, "vnd.android.document/directory", paramString);
  }
  
  public static Uri createFile(Context paramContext, Uri paramUri, String paramString1, String paramString2) {
    try {
      return DocumentsContract.createDocument(paramContext.getContentResolver(), paramUri, paramString1, paramString2);
    } catch (Exception exception) {
      return null;
    } 
  }
  
  public static Uri[] listFiles(Context paramContext, Uri paramUri) {
    Cursor cursor1;
    ContentResolver contentResolver = paramContext.getContentResolver();
    Uri uri = DocumentsContract.buildChildDocumentsUriUsingTree(paramUri, DocumentsContract.getDocumentId(paramUri));
    ArrayList<Uri> arrayList = new ArrayList();
    paramContext = null;
    Cursor cursor2 = null;
    try {
      Cursor cursor4;
      Cursor cursor3 = contentResolver.query(uri, new String[] { "document_id" }, null, null, null);
      while (true) {
        cursor4 = cursor3;
        cursor2 = cursor3;
        cursor1 = cursor3;
        if (cursor3.moveToNext()) {
          cursor2 = cursor3;
          cursor1 = cursor3;
          arrayList.add(DocumentsContract.buildDocumentUriUsingTree(paramUri, cursor3.getString(0)));
          continue;
        } 
        break;
      } 
      closeQuietly((AutoCloseable)cursor4);
    } catch (Exception exception) {
      cursor2 = cursor1;
      StringBuilder stringBuilder = new StringBuilder();
      cursor2 = cursor1;
      this();
      cursor2 = cursor1;
      stringBuilder.append("Failed query: ");
      cursor2 = cursor1;
      stringBuilder.append(exception);
      cursor2 = cursor1;
      Log.w("DocumentFile", stringBuilder.toString());
      Cursor cursor = cursor1;
      closeQuietly((AutoCloseable)cursor);
    } finally {}
    return arrayList.<Uri>toArray(new Uri[arrayList.size()]);
  }
  
  public static Uri prepareTreeUri(Uri paramUri) {
    return DocumentsContract.buildDocumentUriUsingTree(paramUri, DocumentsContract.getTreeDocumentId(paramUri));
  }
  
  public static Uri renameTo(Context paramContext, Uri paramUri, String paramString) {
    try {
      return DocumentsContract.renameDocument(paramContext.getContentResolver(), paramUri, paramString);
    } catch (Exception exception) {
      return null;
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/provider/DocumentsContractApi21.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */