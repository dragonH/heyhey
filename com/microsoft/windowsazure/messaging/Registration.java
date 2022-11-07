package com.microsoft.windowsazure.messaging;

import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public abstract class Registration {
  static final String DEFAULT_REGISTRATION_NAME = "$Default";
  
  static final String REGISTRATIONID_JSON_PROPERTY = "registrationid";
  
  static final String REGISTRATION_NAME_JSON_PROPERTY = "registrationName";
  
  protected String mETag;
  
  protected String mExpirationTime;
  
  protected String mName;
  
  protected String mNotificationHubPath;
  
  protected String mPNSHandle;
  
  protected String mRegistrationId;
  
  public RegistrationType mRegistrationType;
  
  protected List<String> mTags = new ArrayList<String>();
  
  protected String mURI;
  
  protected String mUpdated;
  
  Registration(String paramString) {
    this.mNotificationHubPath = paramString;
  }
  
  private static Date UTCDateStringToDate(String paramString) throws ParseException {
    paramString = paramString.replace("Z", "+00:00");
    try {
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append(paramString.substring(0, 26));
      stringBuilder.append(paramString.substring(27));
      paramString = stringBuilder.toString();
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'.'SSSZ", Locale.getDefault());
      simpleDateFormat.setTimeZone(TimeZone.getDefault());
      return simpleDateFormat.parse(paramString);
    } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
      throw new ParseException("The 'updated' value has an invalid format", 26);
    } 
  }
  
  private void appendContentNode(Document paramDocument, Element paramElement) {
    Element element = paramDocument.createElement("content");
    element.setAttribute("type", "application/xml");
    paramElement.appendChild(element);
    paramElement = paramDocument.createElement(getSpecificPayloadNodeName());
    paramElement.setAttribute("xmlns:i", "http://www.w3.org/2001/XMLSchema-instance");
    paramElement.setAttribute("xmlns", "http://schemas.microsoft.com/netservices/2010/10/servicebus/connect");
    element.appendChild(paramElement);
    appendNodeWithValue(paramDocument, paramElement, "ETag", getETag());
    appendNodeWithValue(paramDocument, paramElement, "ExpirationTime", getExpirationTimeString());
    appendNodeWithValue(paramDocument, paramElement, "RegistrationId", getRegistrationId());
    appendTagsNode(paramDocument, paramElement);
    appendCustomPayload(paramDocument, paramElement);
  }
  
  protected static String getNodeValue(Element paramElement, String paramString) {
    NodeList nodeList = paramElement.getElementsByTagName(paramString);
    return (nodeList.getLength() > 0) ? nodeList.item(0).getTextContent() : null;
  }
  
  void addTags(String[] paramArrayOfString) {
    if (paramArrayOfString != null) {
      int i = paramArrayOfString.length;
      for (byte b = 0; b < i; b++) {
        String str = paramArrayOfString[b];
        if (!Utils.isNullOrWhiteSpace(str))
          this.mTags.add(str); 
      } 
    } 
  }
  
  protected abstract void appendCustomPayload(Document paramDocument, Element paramElement);
  
  protected void appendNodeWithValue(Document paramDocument, Element paramElement, String paramString1, String paramString2) {
    if (!Utils.isNullOrWhiteSpace(paramString2)) {
      Element element = paramDocument.createElement(paramString1);
      element.appendChild(paramDocument.createTextNode(paramString2));
      paramElement.appendChild(element);
    } 
  }
  
  protected void appendTagsNode(Document paramDocument, Element paramElement) {
    List<String> list = getTags();
    if (list != null && list.size() > 0) {
      String str = list.get(0);
      for (byte b = 1; b < list.size(); b++) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(",");
        stringBuilder.append(list.get(b));
        str = stringBuilder.toString();
      } 
      Element element = paramDocument.createElement("Tags");
      element.appendChild(paramDocument.createTextNode(str));
      paramElement.appendChild(element);
    } 
  }
  
  String getETag() {
    return this.mETag;
  }
  
  public Date getExpirationTime() throws ParseException {
    return UTCDateStringToDate(this.mExpirationTime);
  }
  
  String getExpirationTimeString() {
    return this.mExpirationTime;
  }
  
  String getName() {
    return this.mName;
  }
  
  public String getNotificationHubPath() {
    return this.mNotificationHubPath;
  }
  
  public String getPNSHandle() {
    return this.mPNSHandle;
  }
  
  public String getRegistrationId() {
    return this.mRegistrationId;
  }
  
  JSONObject getRegistrationInformation() throws JSONException {
    JSONObject jSONObject = new JSONObject();
    jSONObject.put("registrationid", getRegistrationId());
    jSONObject.put("registrationName", getName());
    return jSONObject;
  }
  
  public RegistrationType getRegistrationType() {
    return this.mRegistrationType;
  }
  
  protected abstract String getSpecificPayloadNodeName();
  
  public List<String> getTags() {
    return new ArrayList<String>(this.mTags);
  }
  
  public String getURI() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(getNotificationHubPath());
    stringBuilder.append("/Registrations/");
    stringBuilder.append(this.mRegistrationId);
    return stringBuilder.toString();
  }
  
  Date getUpdated() throws ParseException {
    return UTCDateStringToDate(this.mUpdated);
  }
  
  String getUpdatedString() {
    return this.mUpdated;
  }
  
  protected abstract void loadCustomXmlData(Element paramElement);
  
  void loadXml(String paramString1, String paramString2) throws Exception {
    Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(paramString1)));
    document.getDocumentElement().normalize();
    Element element = document.getDocumentElement();
    this.mNotificationHubPath = paramString2;
    this.mUpdated = getNodeValue(element, "updated");
    NodeList nodeList = document.getElementsByTagName(getSpecificPayloadNodeName());
    if (nodeList.getLength() > 0) {
      byte b = 0;
      Element element1 = (Element)nodeList.item(0);
      this.mETag = getNodeValue(element1, "ETag");
      this.mExpirationTime = getNodeValue(element1, "ExpirationTime");
      this.mRegistrationId = getNodeValue(element1, "RegistrationId");
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(paramString2);
      stringBuilder.append("/Registrations/");
      stringBuilder.append(this.mRegistrationId);
      this.mURI = stringBuilder.toString();
      paramString2 = getNodeValue(element1, "Tags");
      if (!Utils.isNullOrWhiteSpace(paramString2)) {
        String[] arrayOfString = paramString2.trim().split(",");
        int i = arrayOfString.length;
        while (b < i) {
          String str = arrayOfString[b];
          this.mTags.add(str);
          b++;
        } 
      } 
      loadCustomXmlData(element1);
    } 
  }
  
  void setETag(String paramString) {
    this.mETag = paramString;
  }
  
  void setExpirationTimeString(String paramString) {
    this.mExpirationTime = paramString;
  }
  
  void setName(String paramString) {
    this.mName = paramString;
  }
  
  void setNotificationHubPath(String paramString) {
    this.mNotificationHubPath = paramString;
  }
  
  void setPNSHandle(String paramString) {
    this.mPNSHandle = paramString;
  }
  
  void setRegistrationId(String paramString) {
    this.mRegistrationId = paramString;
  }
  
  void setRegistrationType(RegistrationType paramRegistrationType) {
    this.mRegistrationType = paramRegistrationType;
  }
  
  void setUpdatedString(String paramString) {
    this.mUpdated = paramString;
  }
  
  String toXml() throws Exception {
    DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    documentBuilder.setEntityResolver(new EntityResolver() {
          public InputSource resolveEntity(String param1String1, String param1String2) throws SAXException, IOException {
            return null;
          }
        });
    Document document = documentBuilder.newDocument();
    Element element = document.createElement("entry");
    element.setAttribute("xmlns", "http://www.w3.org/2005/Atom");
    document.appendChild(element);
    appendNodeWithValue(document, element, "id", getURI());
    appendNodeWithValue(document, element, "updated", getUpdatedString());
    appendContentNode(document, element);
    return Utils.getXmlString(document.getDocumentElement());
  }
  
  public enum RegistrationType {
    adm, baidu, gcm, unknown;
    
    static {
      RegistrationType registrationType1 = new RegistrationType("unknown", 0);
      unknown = registrationType1;
      RegistrationType registrationType2 = new RegistrationType("gcm", 1);
      gcm = registrationType2;
      RegistrationType registrationType3 = new RegistrationType("adm", 2);
      adm = registrationType3;
      RegistrationType registrationType4 = new RegistrationType("baidu", 3);
      baidu = registrationType4;
      $VALUES = new RegistrationType[] { registrationType1, registrationType2, registrationType3, registrationType4 };
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/windowsazure/messaging/Registration.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */