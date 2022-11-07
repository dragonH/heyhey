package com.microsoft.appcenter.ingestion.models;

import com.microsoft.appcenter.ingestion.models.json.JSONUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class Device extends WrapperSdk {
  private static final String APP_BUILD = "appBuild";
  
  private static final String APP_NAMESPACE = "appNamespace";
  
  private static final String APP_VERSION = "appVersion";
  
  private static final String CARRIER_COUNTRY = "carrierCountry";
  
  private static final String CARRIER_NAME = "carrierName";
  
  private static final String LOCALE = "locale";
  
  private static final String MODEL = "model";
  
  private static final String OEM_NAME = "oemName";
  
  private static final String OS_API_LEVEL = "osApiLevel";
  
  private static final String OS_BUILD = "osBuild";
  
  private static final String OS_NAME = "osName";
  
  private static final String OS_VERSION = "osVersion";
  
  private static final String SCREEN_SIZE = "screenSize";
  
  private static final String SDK_NAME = "sdkName";
  
  private static final String SDK_VERSION = "sdkVersion";
  
  private static final String TIME_ZONE_OFFSET = "timeZoneOffset";
  
  private String appBuild;
  
  private String appNamespace;
  
  private String appVersion;
  
  private String carrierCountry;
  
  private String carrierName;
  
  private String locale;
  
  private String model;
  
  private String oemName;
  
  private Integer osApiLevel;
  
  private String osBuild;
  
  private String osName;
  
  private String osVersion;
  
  private String screenSize;
  
  private String sdkName;
  
  private String sdkVersion;
  
  private Integer timeZoneOffset;
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    if (!super.equals(paramObject))
      return false; 
    paramObject = paramObject;
    String str3 = this.sdkName;
    if ((str3 != null) ? !str3.equals(((Device)paramObject).sdkName) : (((Device)paramObject).sdkName != null))
      return false; 
    str3 = this.sdkVersion;
    if ((str3 != null) ? !str3.equals(((Device)paramObject).sdkVersion) : (((Device)paramObject).sdkVersion != null))
      return false; 
    str3 = this.model;
    if ((str3 != null) ? !str3.equals(((Device)paramObject).model) : (((Device)paramObject).model != null))
      return false; 
    str3 = this.oemName;
    if ((str3 != null) ? !str3.equals(((Device)paramObject).oemName) : (((Device)paramObject).oemName != null))
      return false; 
    str3 = this.osName;
    if ((str3 != null) ? !str3.equals(((Device)paramObject).osName) : (((Device)paramObject).osName != null))
      return false; 
    str3 = this.osVersion;
    if ((str3 != null) ? !str3.equals(((Device)paramObject).osVersion) : (((Device)paramObject).osVersion != null))
      return false; 
    str3 = this.osBuild;
    if ((str3 != null) ? !str3.equals(((Device)paramObject).osBuild) : (((Device)paramObject).osBuild != null))
      return false; 
    Integer integer2 = this.osApiLevel;
    if ((integer2 != null) ? !integer2.equals(((Device)paramObject).osApiLevel) : (((Device)paramObject).osApiLevel != null))
      return false; 
    String str2 = this.locale;
    if ((str2 != null) ? !str2.equals(((Device)paramObject).locale) : (((Device)paramObject).locale != null))
      return false; 
    Integer integer1 = this.timeZoneOffset;
    if ((integer1 != null) ? !integer1.equals(((Device)paramObject).timeZoneOffset) : (((Device)paramObject).timeZoneOffset != null))
      return false; 
    String str1 = this.screenSize;
    if ((str1 != null) ? !str1.equals(((Device)paramObject).screenSize) : (((Device)paramObject).screenSize != null))
      return false; 
    str1 = this.appVersion;
    if ((str1 != null) ? !str1.equals(((Device)paramObject).appVersion) : (((Device)paramObject).appVersion != null))
      return false; 
    str1 = this.carrierName;
    if ((str1 != null) ? !str1.equals(((Device)paramObject).carrierName) : (((Device)paramObject).carrierName != null))
      return false; 
    str1 = this.carrierCountry;
    if ((str1 != null) ? !str1.equals(((Device)paramObject).carrierCountry) : (((Device)paramObject).carrierCountry != null))
      return false; 
    str1 = this.appBuild;
    if ((str1 != null) ? !str1.equals(((Device)paramObject).appBuild) : (((Device)paramObject).appBuild != null))
      return false; 
    str1 = this.appNamespace;
    paramObject = ((Device)paramObject).appNamespace;
    if (str1 != null) {
      bool = str1.equals(paramObject);
    } else if (paramObject != null) {
      bool = false;
    } 
    return bool;
  }
  
  public String getAppBuild() {
    return this.appBuild;
  }
  
  public String getAppNamespace() {
    return this.appNamespace;
  }
  
  public String getAppVersion() {
    return this.appVersion;
  }
  
  public String getCarrierCountry() {
    return this.carrierCountry;
  }
  
  public String getCarrierName() {
    return this.carrierName;
  }
  
  public String getLocale() {
    return this.locale;
  }
  
  public String getModel() {
    return this.model;
  }
  
  public String getOemName() {
    return this.oemName;
  }
  
  public Integer getOsApiLevel() {
    return this.osApiLevel;
  }
  
  public String getOsBuild() {
    return this.osBuild;
  }
  
  public String getOsName() {
    return this.osName;
  }
  
  public String getOsVersion() {
    return this.osVersion;
  }
  
  public String getScreenSize() {
    return this.screenSize;
  }
  
  public String getSdkName() {
    return this.sdkName;
  }
  
  public String getSdkVersion() {
    return this.sdkVersion;
  }
  
  public Integer getTimeZoneOffset() {
    return this.timeZoneOffset;
  }
  
  public int hashCode() {
    byte b1;
    byte b2;
    byte b3;
    byte b4;
    byte b5;
    byte b6;
    byte b7;
    byte b8;
    byte b9;
    byte b10;
    byte b11;
    byte b12;
    byte b13;
    byte b14;
    byte b15;
    int i = super.hashCode();
    String str3 = this.sdkName;
    int j = 0;
    if (str3 != null) {
      b1 = str3.hashCode();
    } else {
      b1 = 0;
    } 
    str3 = this.sdkVersion;
    if (str3 != null) {
      b2 = str3.hashCode();
    } else {
      b2 = 0;
    } 
    str3 = this.model;
    if (str3 != null) {
      b3 = str3.hashCode();
    } else {
      b3 = 0;
    } 
    str3 = this.oemName;
    if (str3 != null) {
      b4 = str3.hashCode();
    } else {
      b4 = 0;
    } 
    str3 = this.osName;
    if (str3 != null) {
      b5 = str3.hashCode();
    } else {
      b5 = 0;
    } 
    str3 = this.osVersion;
    if (str3 != null) {
      b6 = str3.hashCode();
    } else {
      b6 = 0;
    } 
    str3 = this.osBuild;
    if (str3 != null) {
      b7 = str3.hashCode();
    } else {
      b7 = 0;
    } 
    Integer integer2 = this.osApiLevel;
    if (integer2 != null) {
      b8 = integer2.hashCode();
    } else {
      b8 = 0;
    } 
    String str2 = this.locale;
    if (str2 != null) {
      b9 = str2.hashCode();
    } else {
      b9 = 0;
    } 
    Integer integer1 = this.timeZoneOffset;
    if (integer1 != null) {
      b10 = integer1.hashCode();
    } else {
      b10 = 0;
    } 
    String str1 = this.screenSize;
    if (str1 != null) {
      b11 = str1.hashCode();
    } else {
      b11 = 0;
    } 
    str1 = this.appVersion;
    if (str1 != null) {
      b12 = str1.hashCode();
    } else {
      b12 = 0;
    } 
    str1 = this.carrierName;
    if (str1 != null) {
      b13 = str1.hashCode();
    } else {
      b13 = 0;
    } 
    str1 = this.carrierCountry;
    if (str1 != null) {
      b14 = str1.hashCode();
    } else {
      b14 = 0;
    } 
    str1 = this.appBuild;
    if (str1 != null) {
      b15 = str1.hashCode();
    } else {
      b15 = 0;
    } 
    str1 = this.appNamespace;
    if (str1 != null)
      j = str1.hashCode(); 
    return (((((((((((((((i * 31 + b1) * 31 + b2) * 31 + b3) * 31 + b4) * 31 + b5) * 31 + b6) * 31 + b7) * 31 + b8) * 31 + b9) * 31 + b10) * 31 + b11) * 31 + b12) * 31 + b13) * 31 + b14) * 31 + b15) * 31 + j;
  }
  
  public void read(JSONObject paramJSONObject) throws JSONException {
    super.read(paramJSONObject);
    setSdkName(paramJSONObject.getString("sdkName"));
    setSdkVersion(paramJSONObject.getString("sdkVersion"));
    setModel(paramJSONObject.getString("model"));
    setOemName(paramJSONObject.getString("oemName"));
    setOsName(paramJSONObject.getString("osName"));
    setOsVersion(paramJSONObject.getString("osVersion"));
    setOsBuild(paramJSONObject.optString("osBuild", null));
    setOsApiLevel(JSONUtils.readInteger(paramJSONObject, "osApiLevel"));
    setLocale(paramJSONObject.getString("locale"));
    setTimeZoneOffset(Integer.valueOf(paramJSONObject.getInt("timeZoneOffset")));
    setScreenSize(paramJSONObject.getString("screenSize"));
    setAppVersion(paramJSONObject.getString("appVersion"));
    setCarrierName(paramJSONObject.optString("carrierName", null));
    setCarrierCountry(paramJSONObject.optString("carrierCountry", null));
    setAppBuild(paramJSONObject.getString("appBuild"));
    setAppNamespace(paramJSONObject.optString("appNamespace", null));
  }
  
  public void setAppBuild(String paramString) {
    this.appBuild = paramString;
  }
  
  public void setAppNamespace(String paramString) {
    this.appNamespace = paramString;
  }
  
  public void setAppVersion(String paramString) {
    this.appVersion = paramString;
  }
  
  public void setCarrierCountry(String paramString) {
    this.carrierCountry = paramString;
  }
  
  public void setCarrierName(String paramString) {
    this.carrierName = paramString;
  }
  
  public void setLocale(String paramString) {
    this.locale = paramString;
  }
  
  public void setModel(String paramString) {
    this.model = paramString;
  }
  
  public void setOemName(String paramString) {
    this.oemName = paramString;
  }
  
  public void setOsApiLevel(Integer paramInteger) {
    this.osApiLevel = paramInteger;
  }
  
  public void setOsBuild(String paramString) {
    this.osBuild = paramString;
  }
  
  public void setOsName(String paramString) {
    this.osName = paramString;
  }
  
  public void setOsVersion(String paramString) {
    this.osVersion = paramString;
  }
  
  public void setScreenSize(String paramString) {
    this.screenSize = paramString;
  }
  
  public void setSdkName(String paramString) {
    this.sdkName = paramString;
  }
  
  public void setSdkVersion(String paramString) {
    this.sdkVersion = paramString;
  }
  
  public void setTimeZoneOffset(Integer paramInteger) {
    this.timeZoneOffset = paramInteger;
  }
  
  public void write(JSONStringer paramJSONStringer) throws JSONException {
    super.write(paramJSONStringer);
    paramJSONStringer.key("sdkName").value(getSdkName());
    paramJSONStringer.key("sdkVersion").value(getSdkVersion());
    paramJSONStringer.key("model").value(getModel());
    paramJSONStringer.key("oemName").value(getOemName());
    paramJSONStringer.key("osName").value(getOsName());
    paramJSONStringer.key("osVersion").value(getOsVersion());
    JSONUtils.write(paramJSONStringer, "osBuild", getOsBuild());
    JSONUtils.write(paramJSONStringer, "osApiLevel", getOsApiLevel());
    paramJSONStringer.key("locale").value(getLocale());
    paramJSONStringer.key("timeZoneOffset").value(getTimeZoneOffset());
    paramJSONStringer.key("screenSize").value(getScreenSize());
    paramJSONStringer.key("appVersion").value(getAppVersion());
    JSONUtils.write(paramJSONStringer, "carrierName", getCarrierName());
    JSONUtils.write(paramJSONStringer, "carrierCountry", getCarrierCountry());
    paramJSONStringer.key("appBuild").value(getAppBuild());
    JSONUtils.write(paramJSONStringer, "appNamespace", getAppNamespace());
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/ingestion/models/Device.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */