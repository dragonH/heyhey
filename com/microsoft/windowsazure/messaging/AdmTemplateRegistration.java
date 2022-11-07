package com.microsoft.windowsazure.messaging;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class AdmTemplateRegistration extends TemplateRegistration {
  private static final String ADM_HANDLE_NODE = "AdmRegistrationId";
  
  static final String ADM_TEMPLATE_REGISTRATION_CUSTOM_NODE = "AdmTemplateRegistrationDescription";
  
  AdmTemplateRegistration(String paramString) {
    super(paramString);
    this.mRegistrationType = Registration.RegistrationType.adm;
  }
  
  protected void appendCustomPayload(Document paramDocument, Element paramElement) {
    appendNodeWithValue(paramDocument, paramElement, "AdmRegistrationId", getPNSHandle());
    super.appendCustomPayload(paramDocument, paramElement);
  }
  
  protected String getSpecificPayloadNodeName() {
    return "AdmTemplateRegistrationDescription";
  }
  
  protected void loadCustomXmlData(Element paramElement) {
    setPNSHandle(Registration.getNodeValue(paramElement, "AdmRegistrationId"));
    super.loadCustomXmlData(paramElement);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/windowsazure/messaging/AdmTemplateRegistration.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */