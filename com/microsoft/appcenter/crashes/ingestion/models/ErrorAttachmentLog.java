package com.microsoft.appcenter.crashes.ingestion.models;

import android.support.annotation.VisibleForTesting;
import android.util.Base64;
import com.microsoft.appcenter.ingestion.models.AbstractLog;
import com.microsoft.appcenter.ingestion.models.json.JSONUtils;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class ErrorAttachmentLog extends AbstractLog {
  @VisibleForTesting
  static final Charset CHARSET = Charset.forName("UTF-8");
  
  private static final String CONTENT_TYPE = "contentType";
  
  public static final String CONTENT_TYPE_TEXT_PLAIN = "text/plain";
  
  @VisibleForTesting
  static final String DATA = "data";
  
  private static final String ERROR_ID = "errorId";
  
  private static final String FILE_NAME = "fileName";
  
  public static final String TYPE = "errorAttachment";
  
  private String contentType;
  
  private byte[] data;
  
  private UUID errorId;
  
  private String fileName;
  
  private UUID id;
  
  public static ErrorAttachmentLog attachmentWithBinary(byte[] paramArrayOfbyte, String paramString1, String paramString2) {
    ErrorAttachmentLog errorAttachmentLog = new ErrorAttachmentLog();
    errorAttachmentLog.setData(paramArrayOfbyte);
    errorAttachmentLog.setFileName(paramString1);
    errorAttachmentLog.setContentType(paramString2);
    return errorAttachmentLog;
  }
  
  public static ErrorAttachmentLog attachmentWithText(String paramString1, String paramString2) {
    return attachmentWithBinary(paramString1.getBytes(CHARSET), paramString2, "text/plain");
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    if (!super.equals(paramObject))
      return false; 
    paramObject = paramObject;
    UUID uUID = this.id;
    if ((uUID != null) ? !uUID.equals(((ErrorAttachmentLog)paramObject).id) : (((ErrorAttachmentLog)paramObject).id != null))
      return false; 
    uUID = this.errorId;
    if ((uUID != null) ? !uUID.equals(((ErrorAttachmentLog)paramObject).errorId) : (((ErrorAttachmentLog)paramObject).errorId != null))
      return false; 
    String str = this.contentType;
    if ((str != null) ? !str.equals(((ErrorAttachmentLog)paramObject).contentType) : (((ErrorAttachmentLog)paramObject).contentType != null))
      return false; 
    str = this.fileName;
    return ((str != null) ? !str.equals(((ErrorAttachmentLog)paramObject).fileName) : (((ErrorAttachmentLog)paramObject).fileName != null)) ? false : Arrays.equals(this.data, ((ErrorAttachmentLog)paramObject).data);
  }
  
  public String getContentType() {
    return this.contentType;
  }
  
  public byte[] getData() {
    return this.data;
  }
  
  public UUID getErrorId() {
    return this.errorId;
  }
  
  public String getFileName() {
    return this.fileName;
  }
  
  public UUID getId() {
    return this.id;
  }
  
  public String getType() {
    return "errorAttachment";
  }
  
  public int hashCode() {
    byte b1;
    byte b2;
    byte b3;
    int i = super.hashCode();
    UUID uUID = this.id;
    int j = 0;
    if (uUID != null) {
      b1 = uUID.hashCode();
    } else {
      b1 = 0;
    } 
    uUID = this.errorId;
    if (uUID != null) {
      b2 = uUID.hashCode();
    } else {
      b2 = 0;
    } 
    String str = this.contentType;
    if (str != null) {
      b3 = str.hashCode();
    } else {
      b3 = 0;
    } 
    str = this.fileName;
    if (str != null)
      j = str.hashCode(); 
    return ((((i * 31 + b1) * 31 + b2) * 31 + b3) * 31 + j) * 31 + Arrays.hashCode(this.data);
  }
  
  public boolean isValid() {
    boolean bool;
    if (getId() != null && getErrorId() != null && getContentType() != null && getData() != null) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void read(JSONObject paramJSONObject) throws JSONException {
    super.read(paramJSONObject);
    setId(UUID.fromString(paramJSONObject.getString("id")));
    setErrorId(UUID.fromString(paramJSONObject.getString("errorId")));
    setContentType(paramJSONObject.getString("contentType"));
    setFileName(paramJSONObject.optString("fileName", null));
    try {
      setData(Base64.decode(paramJSONObject.getString("data"), 0));
      return;
    } catch (IllegalArgumentException illegalArgumentException) {
      throw new JSONException(illegalArgumentException.getMessage());
    } 
  }
  
  public void setContentType(String paramString) {
    this.contentType = paramString;
  }
  
  public void setData(byte[] paramArrayOfbyte) {
    this.data = paramArrayOfbyte;
  }
  
  public void setErrorId(UUID paramUUID) {
    this.errorId = paramUUID;
  }
  
  public void setFileName(String paramString) {
    this.fileName = paramString;
  }
  
  public void setId(UUID paramUUID) {
    this.id = paramUUID;
  }
  
  public void write(JSONStringer paramJSONStringer) throws JSONException {
    super.write(paramJSONStringer);
    JSONUtils.write(paramJSONStringer, "id", getId());
    JSONUtils.write(paramJSONStringer, "errorId", getErrorId());
    JSONUtils.write(paramJSONStringer, "contentType", getContentType());
    JSONUtils.write(paramJSONStringer, "fileName", getFileName());
    JSONUtils.write(paramJSONStringer, "data", Base64.encodeToString(getData(), 2));
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/crashes/ingestion/models/ErrorAttachmentLog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */