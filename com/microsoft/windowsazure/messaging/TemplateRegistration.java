package com.microsoft.windowsazure.messaging;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public abstract class TemplateRegistration extends Registration {
  private String mBodyTemplate;
  
  TemplateRegistration(String paramString) {
    super(paramString);
  }
  
  private void appendBodyTemplateNode(Document paramDocument, Element paramElement) {
    if (!Utils.isNullOrWhiteSpace(getBodyTemplate())) {
      Element element = paramDocument.createElement("BodyTemplate");
      paramElement.appendChild(element);
      element.appendChild(paramDocument.createCDATASection(getBodyTemplate()));
    } 
  }
  
  protected void appendCustomPayload(Document paramDocument, Element paramElement) {
    appendBodyTemplateNode(paramDocument, paramElement);
    appendNodeWithValue(paramDocument, paramElement, "TemplateName", getName());
  }
  
  public String getBodyTemplate() {
    return this.mBodyTemplate;
  }
  
  public String getTemplateName() {
    return getName();
  }
  
  protected void loadCustomXmlData(Element paramElement) {
    NodeList nodeList = paramElement.getElementsByTagName("BodyTemplate");
    if (nodeList.getLength() > 0) {
      byte b = 0;
      nodeList = nodeList.item(0).getChildNodes();
      while (b < nodeList.getLength()) {
        if (nodeList.item(b) instanceof CharacterData) {
          this.mBodyTemplate = ((CharacterData)nodeList.item(b)).getData();
          break;
        } 
        b++;
      } 
    } 
    setName(Registration.getNodeValue(paramElement, "TemplateName"));
  }
  
  void setBodyTemplate(String paramString) {
    this.mBodyTemplate = paramString;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/windowsazure/messaging/TemplateRegistration.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */