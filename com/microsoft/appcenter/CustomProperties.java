package com.microsoft.appcenter;

import android.support.annotation.VisibleForTesting;
import com.microsoft.appcenter.utils.AppCenterLog;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class CustomProperties {
  private static final Pattern KEY_PATTERN = Pattern.compile("^[a-zA-Z][a-zA-Z0-9]*$");
  
  @VisibleForTesting
  static final int MAX_PROPERTIES_COUNT = 60;
  
  @VisibleForTesting
  static final int MAX_PROPERTY_KEY_LENGTH = 128;
  
  @VisibleForTesting
  static final int MAX_PROPERTY_VALUE_LENGTH = 128;
  
  private static final String VALUE_NULL_ERROR_MESSAGE = "Custom property value cannot be null, did you mean to call clear?";
  
  private final Map<String, Object> mProperties = new HashMap<String, Object>();
  
  private void addProperty(String paramString, Object paramObject) {
    if (this.mProperties.containsKey(paramString) || this.mProperties.size() < 60) {
      this.mProperties.put(paramString, paramObject);
      return;
    } 
    AppCenterLog.error("AppCenter", "Custom properties cannot contain more than 60 items");
  }
  
  private boolean isValidKey(String paramString) {
    if (paramString == null || !KEY_PATTERN.matcher(paramString).matches()) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Custom property \"");
      stringBuilder.append(paramString);
      stringBuilder.append("\" must match \"");
      stringBuilder.append(KEY_PATTERN);
      stringBuilder.append("\"");
      AppCenterLog.error("AppCenter", stringBuilder.toString());
      return false;
    } 
    if (paramString.length() > 128) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Custom property \"");
      stringBuilder.append(paramString);
      stringBuilder.append("\" length cannot be longer than ");
      stringBuilder.append(128);
      stringBuilder.append(" characters.");
      AppCenterLog.error("AppCenter", stringBuilder.toString());
      return false;
    } 
    if (this.mProperties.containsKey(paramString)) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Custom property \"");
      stringBuilder.append(paramString);
      stringBuilder.append("\" is already set or cleared and will be overridden.");
      AppCenterLog.warn("AppCenter", stringBuilder.toString());
    } 
    return true;
  }
  
  private boolean isValidStringValue(String paramString1, String paramString2) {
    if (paramString2 == null) {
      AppCenterLog.error("AppCenter", "Custom property value cannot be null, did you mean to call clear?");
      return false;
    } 
    if (paramString2.length() > 128) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Custom property \"");
      stringBuilder.append(paramString1);
      stringBuilder.append("\" value length cannot be longer than ");
      stringBuilder.append(128);
      stringBuilder.append(" characters.");
      AppCenterLog.error("AppCenter", stringBuilder.toString());
      return false;
    } 
    return true;
  }
  
  public CustomProperties clear(String paramString) {
    if (isValidKey(paramString))
      addProperty(paramString, null); 
    return this;
  }
  
  Map<String, Object> getProperties() {
    return this.mProperties;
  }
  
  public CustomProperties set(String paramString, Number paramNumber) {
    if (isValidKey(paramString))
      if (paramNumber != null) {
        addProperty(paramString, paramNumber);
      } else {
        AppCenterLog.error("AppCenter", "Custom property value cannot be null, did you mean to call clear?");
      }  
    return this;
  }
  
  public CustomProperties set(String paramString1, String paramString2) {
    if (isValidKey(paramString1) && isValidStringValue(paramString1, paramString2))
      addProperty(paramString1, paramString2); 
    return this;
  }
  
  public CustomProperties set(String paramString, Date paramDate) {
    if (isValidKey(paramString))
      if (paramDate != null) {
        addProperty(paramString, paramDate);
      } else {
        AppCenterLog.error("AppCenter", "Custom property value cannot be null, did you mean to call clear?");
      }  
    return this;
  }
  
  public CustomProperties set(String paramString, boolean paramBoolean) {
    if (isValidKey(paramString))
      addProperty(paramString, Boolean.valueOf(paramBoolean)); 
    return this;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/CustomProperties.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */