package com.microsoft.appcenter.utils;

import android.support.annotation.NonNull;
import com.microsoft.appcenter.utils.storage.StorageHelper;
import java.util.UUID;

public class IdHelper {
  @NonNull
  public static UUID getInstallId() {
    UUID uUID;
    String str = StorageHelper.PreferencesStorage.getString("installId", "");
    try {
      uUID = UUID.fromString(str);
    } catch (Exception exception) {
      AppCenterLog.warn("AppCenter", "Unable to get installID from Shared Preferences");
      uUID = UUIDUtils.randomUUID();
      StorageHelper.PreferencesStorage.putString("installId", uUID.toString());
    } 
    return uUID;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/utils/IdHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */