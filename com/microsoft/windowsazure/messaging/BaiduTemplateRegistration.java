package com.microsoft.windowsazure.messaging;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class BaiduTemplateRegistration extends TemplateRegistration {
  private static final String BAIDU_CHANNEL_ID = "BaiduChannelId";
  
  private static final String BAIDU_HANDLE_NODE = "BaiduUserId-BaiduChannelId";
  
  static final String BAIDU_TEMPLATE_REGISTRATION_CUSTOM_NODE = "BaiduTemplateRegistrationDescription";
  
  private static final String BAIDU_USER_ID = "BaiduUserId";
  
  protected String mChannelId;
  
  protected String mUserId;
  
  BaiduTemplateRegistration(String paramString) {
    super(paramString);
    this.mRegistrationType = Registration.RegistrationType.baidu;
  }
  
  protected void appendCustomPayload(Document paramDocument, Element paramElement) {
    appendNodeWithValue(paramDocument, paramElement, "BaiduUserId", getUserId());
    appendNodeWithValue(paramDocument, paramElement, "BaiduChannelId", getChannelId());
    super.appendCustomPayload(paramDocument, paramElement);
  }
  
  public String getChannelId() {
    return this.mChannelId;
  }
  
  protected String getSpecificPayloadNodeName() {
    return "BaiduTemplateRegistrationDescription";
  }
  
  public String getUserId() {
    return this.mUserId;
  }
  
  protected void loadCustomXmlData(Element paramElement) {
    setPNSHandle(Registration.getNodeValue(paramElement, "BaiduUserId-BaiduChannelId"));
    super.loadCustomXmlData(paramElement);
  }
  
  void setChannelId(String paramString) {
    this.mChannelId = paramString;
  }
  
  void setPNSHandle(String paramString) {
    if (Utils.isNullOrWhiteSpace(paramString))
      return; 
    this.mPNSHandle = paramString;
    String[] arrayOfString = paramString.split("-");
    paramString = arrayOfString[0];
    if (!Utils.isNullOrWhiteSpace(paramString)) {
      setUserId(paramString);
      String str = arrayOfString[1];
      if (!Utils.isNullOrWhiteSpace(paramString)) {
        setChannelId(str);
        return;
      } 
      throw new AssertionError("Baidu channelId is inalid!");
    } 
    throw new AssertionError("Baidu userId is inalid!");
  }
  
  void setUserId(String paramString) {
    this.mUserId = paramString;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/windowsazure/messaging/BaiduTemplateRegistration.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */