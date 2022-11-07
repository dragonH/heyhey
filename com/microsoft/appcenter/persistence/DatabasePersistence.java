package com.microsoft.appcenter.persistence;

import android.content.ContentValues;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import com.microsoft.appcenter.ingestion.models.Log;
import com.microsoft.appcenter.utils.AppCenterLog;
import com.microsoft.appcenter.utils.UUIDUtils;
import com.microsoft.appcenter.utils.storage.StorageHelper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.json.JSONException;

public class DatabasePersistence extends Persistence {
  @VisibleForTesting
  static final String COLUMN_GROUP = "persistence_group";
  
  @VisibleForTesting
  static final String COLUMN_LOG = "log";
  
  private static final String DATABASE = "com.microsoft.appcenter.persistence";
  
  private static final ContentValues SCHEMA = getContentValues("", "");
  
  private static final String TABLE = "logs";
  
  final StorageHelper.DatabaseStorage mDatabaseStorage;
  
  @VisibleForTesting
  final Set<Long> mPendingDbIdentifiers = new HashSet<Long>();
  
  @VisibleForTesting
  final Map<String, List<Long>> mPendingDbIdentifiersGroups = new HashMap<String, List<Long>>();
  
  public DatabasePersistence() {
    this("com.microsoft.appcenter.persistence", "logs", 1);
  }
  
  DatabasePersistence(String paramString1, String paramString2, int paramInt) {
    this(paramString1, paramString2, paramInt, 300);
  }
  
  DatabasePersistence(String paramString1, String paramString2, int paramInt1, int paramInt2) {
    this.mDatabaseStorage = StorageHelper.DatabaseStorage.getDatabaseStorage(paramString1, paramString2, paramInt1, SCHEMA, paramInt2, new StorageHelper.DatabaseStorage.DatabaseErrorListener() {
          public void onError(String param1String, RuntimeException param1RuntimeException) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Cannot complete an operation (");
            stringBuilder.append(param1String);
            stringBuilder.append(")");
            AppCenterLog.error("AppCenter", stringBuilder.toString(), param1RuntimeException);
          }
        });
  }
  
  private static ContentValues getContentValues(@Nullable String paramString1, @Nullable String paramString2) {
    ContentValues contentValues = new ContentValues();
    contentValues.put("persistence_group", paramString1);
    contentValues.put("log", paramString2);
    return contentValues;
  }
  
  public void clearPendingLogState() {
    this.mPendingDbIdentifiers.clear();
    this.mPendingDbIdentifiersGroups.clear();
    AppCenterLog.debug("AppCenter", "Cleared pending log states");
  }
  
  public void close() throws IOException {
    this.mDatabaseStorage.close();
  }
  
  public int countLogs(@NonNull String paramString) {
    StorageHelper.DatabaseStorage.DatabaseScanner databaseScanner = this.mDatabaseStorage.getScanner("persistence_group", paramString);
    int i = databaseScanner.getCount();
    databaseScanner.close();
    return i;
  }
  
  public void deleteLogs(String paramString) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Deleting all logs from the Persistence database for ");
    stringBuilder.append(paramString);
    AppCenterLog.debug("AppCenter", stringBuilder.toString());
    this.mDatabaseStorage.delete("persistence_group", paramString);
    Iterator<String> iterator = this.mPendingDbIdentifiersGroups.keySet().iterator();
    while (iterator.hasNext()) {
      if (((String)iterator.next()).startsWith(paramString))
        iterator.remove(); 
    } 
  }
  
  public void deleteLogs(@NonNull String paramString1, @NonNull String paramString2) {
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append("Deleting logs from the Persistence database for ");
    stringBuilder1.append(paramString1);
    stringBuilder1.append(" with ");
    stringBuilder1.append(paramString2);
    AppCenterLog.debug("AppCenter", stringBuilder1.toString());
    AppCenterLog.debug("AppCenter", "The IDs for deleting log(s) is/are:");
    Map<String, List<Long>> map = this.mPendingDbIdentifiersGroups;
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append(paramString1);
    stringBuilder2.append(paramString2);
    List list = map.remove(stringBuilder2.toString());
    if (list != null)
      for (Long long_ : list) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\t");
        stringBuilder.append(long_);
        AppCenterLog.debug("AppCenter", stringBuilder.toString());
        this.mDatabaseStorage.delete(long_.longValue());
        this.mPendingDbIdentifiers.remove(long_);
      }  
  }
  
  @Nullable
  public String getLogs(@NonNull String paramString, @IntRange(from = 0L) int paramInt, @NonNull List<Log> paramList) {
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append("Trying to get ");
    stringBuilder2.append(paramInt);
    stringBuilder2.append(" logs from the Persistence database for ");
    stringBuilder2.append(paramString);
    AppCenterLog.debug("AppCenter", stringBuilder2.toString());
    StorageHelper.DatabaseStorage.DatabaseScanner databaseScanner = this.mDatabaseStorage.getScanner("persistence_group", paramString);
    TreeMap<Object, Object> treeMap = new TreeMap<Object, Object>();
    ArrayList<Long> arrayList1 = new ArrayList();
    Iterator<ContentValues> iterator = databaseScanner.iterator();
    byte b = 0;
    while (iterator.hasNext() && b < paramInt) {
      ContentValues contentValues = iterator.next();
      Long long_ = contentValues.getAsLong("oid");
      if (!this.mPendingDbIdentifiers.contains(long_))
        try {
          treeMap.put(long_, getLogSerializer().deserializeLog(contentValues.getAsString("log")));
          b++;
        } catch (JSONException jSONException) {
          AppCenterLog.error("AppCenter", "Cannot deserialize a log in the database", (Throwable)jSONException);
          arrayList1.add(long_);
        }  
    } 
    databaseScanner.close();
    if (arrayList1.size() > 0) {
      this.mDatabaseStorage.delete(arrayList1);
      AppCenterLog.warn("AppCenter", "Deleted logs that cannot be deserialized");
    } 
    if (treeMap.size() <= 0) {
      AppCenterLog.debug("AppCenter", "No logs found in the Persistence database at the moment");
      return null;
    } 
    String str = UUIDUtils.randomUUID().toString();
    StringBuilder stringBuilder3 = new StringBuilder();
    stringBuilder3.append("Returning ");
    stringBuilder3.append(treeMap.size());
    stringBuilder3.append(" log(s) with an ID, ");
    stringBuilder3.append(str);
    AppCenterLog.debug("AppCenter", stringBuilder3.toString());
    AppCenterLog.debug("AppCenter", "The SID/ID pairs for returning log(s) is/are:");
    ArrayList<Long> arrayList2 = new ArrayList();
    for (Map.Entry<Object, Object> entry : treeMap.entrySet()) {
      Long long_ = (Long)entry.getKey();
      this.mPendingDbIdentifiers.add(long_);
      arrayList2.add(long_);
      paramList.add((Log)entry.getValue());
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("\t");
      stringBuilder.append(((Log)entry.getValue()).getSid());
      stringBuilder.append(" / ");
      stringBuilder.append(long_);
      AppCenterLog.debug("AppCenter", stringBuilder.toString());
    } 
    Map<String, List<Long>> map = this.mPendingDbIdentifiersGroups;
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append(paramString);
    stringBuilder1.append(str);
    map.put(stringBuilder1.toString(), arrayList2);
    return str;
  }
  
  public void putLog(@NonNull String paramString, @NonNull Log paramLog) throws Persistence.PersistenceException {
    try {
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("Storing a log to the Persistence database for log type ");
      stringBuilder.append(paramLog.getType());
      stringBuilder.append(" with ");
      stringBuilder.append(paramLog.getSid());
      AppCenterLog.debug("AppCenter", stringBuilder.toString());
      this.mDatabaseStorage.put(getContentValues(paramString, getLogSerializer().serializeLog(paramLog)));
      return;
    } catch (JSONException jSONException) {
      throw new Persistence.PersistenceException("Cannot convert to JSON string", jSONException);
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/persistence/DatabasePersistence.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */