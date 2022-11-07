package com.microsoft.windowsazure.messaging;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class GcmTemplateRegistration extends TemplateRegistration {
  private static final String GCM_HANDLE_NODE = "GcmRegistrationId";
  
  static final String GCM_TEMPLATE_REGISTRATION_CUSTOM_NODE = "GcmTemplateRegistrationDescription";
  
  GcmTemplateRegistration(String paramString) {
    super(paramString);
    this.mRegistrationType = Registration.RegistrationType.gcm;
  }
  
  protected void appendCustomPayload(Document paramDocument, Element paramElement) {
    appendNodeWithValue(paramDocument, paramElement, "GcmRegistrationId", getPNSHandle());
    super.appendCustomPayload(paramDocument, paramElement);
  }
  
  protected String getSpecificPayloadNodeName() {
    return "GcmTemplateRegistrationDescription";
  }
  
  protected void loadCustomXmlData(Element paramElement) {
    setPNSHandle(Registration.getNodeValue(paramElement, "GcmRegistrationId"));
    super.loadCustomXmlData(paramElement);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/windowsazure/messaging/GcmTemplateRegistration.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */