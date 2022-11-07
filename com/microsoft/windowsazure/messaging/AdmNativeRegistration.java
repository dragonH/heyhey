package com.microsoft.windowsazure.messaging;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class AdmNativeRegistration extends Registration {
  static final String ADM_HANDLE_NODE = "AdmRegistrationId";
  
  private static final String ADM_NATIVE_REGISTRATION_CUSTOM_NODE = "AdmRegistrationDescription";
  
  AdmNativeRegistration(String paramString) {
    super(paramString);
    this.mRegistrationType = Registration.RegistrationType.adm;
  }
  
  protected void appendCustomPayload(Document paramDocument, Element paramElement) {
    appendNodeWithValue(paramDocument, paramElement, "AdmRegistrationId", getPNSHandle());
  }
  
  protected String getSpecificPayloadNodeName() {
    return "AdmRegistrationDescription";
  }
  
  protected void loadCustomXmlData(Element paramElement) {
    setPNSHandle(Registration.getNodeValue(paramElement, "AdmRegistrationId"));
    setName("$Default");
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/windowsazure/messaging/AdmNativeRegistration.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */