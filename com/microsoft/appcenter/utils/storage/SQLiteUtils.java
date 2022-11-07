package com.microsoft.appcenter.utils.storage;

import android.database.sqlite.SQLiteQueryBuilder;
import android.support.annotation.NonNull;

class SQLiteUtils {
  @NonNull
  static SQLiteQueryBuilder newSQLiteQueryBuilder() {
    return new SQLiteQueryBuilder();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/utils/storage/SQLiteUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */