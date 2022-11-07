package com.microsoft.windowsazure.messaging;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class GcmNativeRegistration extends Registration {
  static final String GCM_HANDLE_NODE = "GcmRegistrationId";
  
  private static final String GCM_NATIVE_REGISTRATION_CUSTOM_NODE = "GcmRegistrationDescription";
  
  GcmNativeRegistration(String paramString) {
    super(paramString);
    this.mRegistrationType = Registration.RegistrationType.gcm;
  }
  
  protected void appendCustomPayload(Document paramDocument, Element paramElement) {
    appendNodeWithValue(paramDocument, paramElement, "GcmRegistrationId", getPNSHandle());
  }
  
  protected String getSpecificPayloadNodeName() {
    return "GcmRegistrationDescription";
  }
  
  protected void loadCustomXmlData(Element paramElement) {
    setPNSHandle(Registration.getNodeValue(paramElement, "GcmRegistrationId"));
    setName("$Default");
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/windowsazure/messaging/GcmNativeRegistration.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */