package com.microsoft.appcenter.ingestion.models;

import com.microsoft.appcenter.ingestion.models.json.JSONUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class WrapperSdk implements Model {
  private static final String LIVE_UPDATE_DEPLOYMENT_KEY = "liveUpdateDeploymentKey";
  
  private static final String LIVE_UPDATE_PACKAGE_HASH = "liveUpdatePackageHash";
  
  private static final String LIVE_UPDATE_RELEASE_LABEL = "liveUpdateReleaseLabel";
  
  private static final String WRAPPER_RUNTIME_VERSION = "wrapperRuntimeVersion";
  
  private static final String WRAPPER_SDK_NAME = "wrapperSdkName";
  
  private static final String WRAPPER_SDK_VERSION = "wrapperSdkVersion";
  
  private String liveUpdateDeploymentKey;
  
  private String liveUpdatePackageHash;
  
  private String liveUpdateReleaseLabel;
  
  private String wrapperRuntimeVersion;
  
  private String wrapperSdkName;
  
  private String wrapperSdkVersion;
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    String str = this.wrapperSdkVersion;
    if ((str != null) ? !str.equals(((WrapperSdk)paramObject).wrapperSdkVersion) : (((WrapperSdk)paramObject).wrapperSdkVersion != null))
      return false; 
    str = this.wrapperSdkName;
    if ((str != null) ? !str.equals(((WrapperSdk)paramObject).wrapperSdkName) : (((WrapperSdk)paramObject).wrapperSdkName != null))
      return false; 
    str = this.wrapperRuntimeVersion;
    if ((str != null) ? !str.equals(((WrapperSdk)paramObject).wrapperRuntimeVersion) : (((WrapperSdk)paramObject).wrapperRuntimeVersion != null))
      return false; 
    str = this.liveUpdateReleaseLabel;
    if ((str != null) ? !str.equals(((WrapperSdk)paramObject).liveUpdateReleaseLabel) : (((WrapperSdk)paramObject).liveUpdateReleaseLabel != null))
      return false; 
    str = this.liveUpdateDeploymentKey;
    if ((str != null) ? !str.equals(((WrapperSdk)paramObject).liveUpdateDeploymentKey) : (((WrapperSdk)paramObject).liveUpdateDeploymentKey != null))
      return false; 
    str = this.liveUpdatePackageHash;
    paramObject = ((WrapperSdk)paramObject).liveUpdatePackageHash;
    if (str != null) {
      bool = str.equals(paramObject);
    } else if (paramObject != null) {
      bool = false;
    } 
    return bool;
  }
  
  public String getLiveUpdateDeploymentKey() {
    return this.liveUpdateDeploymentKey;
  }
  
  public String getLiveUpdatePackageHash() {
    return this.liveUpdatePackageHash;
  }
  
  public String getLiveUpdateReleaseLabel() {
    return this.liveUpdateReleaseLabel;
  }
  
  public String getWrapperRuntimeVersion() {
    return this.wrapperRuntimeVersion;
  }
  
  public String getWrapperSdkName() {
    return this.wrapperSdkName;
  }
  
  public String getWrapperSdkVersion() {
    return this.wrapperSdkVersion;
  }
  
  public int hashCode() {
    byte b1;
    byte b2;
    byte b3;
    byte b4;
    byte b5;
    String str = this.wrapperSdkVersion;
    int i = 0;
    if (str != null) {
      b1 = str.hashCode();
    } else {
      b1 = 0;
    } 
    str = this.wrapperSdkName;
    if (str != null) {
      b2 = str.hashCode();
    } else {
      b2 = 0;
    } 
    str = this.wrapperRuntimeVersion;
    if (str != null) {
      b3 = str.hashCode();
    } else {
      b3 = 0;
    } 
    str = this.liveUpdateReleaseLabel;
    if (str != null) {
      b4 = str.hashCode();
    } else {
      b4 = 0;
    } 
    str = this.liveUpdateDeploymentKey;
    if (str != null) {
      b5 = str.hashCode();
    } else {
      b5 = 0;
    } 
    str = this.liveUpdatePackageHash;
    if (str != null)
      i = str.hashCode(); 
    return ((((b1 * 31 + b2) * 31 + b3) * 31 + b4) * 31 + b5) * 31 + i;
  }
  
  public void read(JSONObject paramJSONObject) throws JSONException {
    setWrapperSdkVersion(paramJSONObject.optString("wrapperSdkVersion", null));
    setWrapperSdkName(paramJSONObject.optString("wrapperSdkName", null));
    setWrapperRuntimeVersion(paramJSONObject.optString("wrapperRuntimeVersion", null));
    setLiveUpdateReleaseLabel(paramJSONObject.optString("liveUpdateReleaseLabel", null));
    setLiveUpdateDeploymentKey(paramJSONObject.optString("liveUpdateDeploymentKey", null));
    setLiveUpdatePackageHash(paramJSONObject.optString("liveUpdatePackageHash", null));
  }
  
  public void setLiveUpdateDeploymentKey(String paramString) {
    this.liveUpdateDeploymentKey = paramString;
  }
  
  public void setLiveUpdatePackageHash(String paramString) {
    this.liveUpdatePackageHash = paramString;
  }
  
  public void setLiveUpdateReleaseLabel(String paramString) {
    this.liveUpdateReleaseLabel = paramString;
  }
  
  public void setWrapperRuntimeVersion(String paramString) {
    this.wrapperRuntimeVersion = paramString;
  }
  
  public void setWrapperSdkName(String paramString) {
    this.wrapperSdkName = paramString;
  }
  
  public void setWrapperSdkVersion(String paramString) {
    this.wrapperSdkVersion = paramString;
  }
  
  public void write(JSONStringer paramJSONStringer) throws JSONException {
    JSONUtils.write(paramJSONStringer, "wrapperSdkVersion", getWrapperSdkVersion());
    JSONUtils.write(paramJSONStringer, "wrapperSdkName", getWrapperSdkName());
    JSONUtils.write(paramJSONStringer, "wrapperRuntimeVersion", getWrapperRuntimeVersion());
    JSONUtils.write(paramJSONStringer, "liveUpdateReleaseLabel", getLiveUpdateReleaseLabel());
    JSONUtils.write(paramJSONStringer, "liveUpdateDeploymentKey", getLiveUpdateDeploymentKey());
    JSONUtils.write(paramJSONStringer, "liveUpdatePackageHash", getLiveUpdatePackageHash());
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/ingestion/models/WrapperSdk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */