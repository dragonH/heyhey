package com.microsoft.windowsazure.messaging;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.URLEncoder;
import java.util.AbstractMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class NotificationHub {
  private static final String NEW_REGISTRATION_LOCATION_HEADER = "Location";
  
  private static final String PNS_HANDLE_KEY = "PNS_HANDLE";
  
  private static final String REGISTRATION_NAME_STORAGE_KEY = "REG_NAME_";
  
  private static final String STORAGE_PREFIX = "__NH_";
  
  private static final String STORAGE_VERSION = "1.0.0";
  
  private static final String STORAGE_VERSION_KEY = "STORAGE_VERSION";
  
  private static final String XML_CONTENT_TYPE = "application/atom+xml";
  
  private String mConnectionString;
  
  private boolean mIsRefreshNeeded = false;
  
  private String mNotificationHubPath;
  
  private SharedPreferences mSharedPreferences;
  
  public NotificationHub(String paramString1, String paramString2, Context paramContext) {
    setConnectionString(paramString2);
    setNotificationHubPath(paramString1);
    if (paramContext != null) {
      this.mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(paramContext.getApplicationContext());
      verifyStorageVersion();
      return;
    } 
    throw new IllegalArgumentException("context");
  }
  
  private String createRegistrationId() throws Exception {
    Connection connection = new Connection(this.mConnectionString);
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(this.mNotificationHubPath);
    stringBuilder.append("/registrationids/");
    String[] arrayOfString = (new URI(connection.executeRequest(stringBuilder.toString(), null, "application/atom+xml", "POST", "Location", (AbstractMap.SimpleEntry<String, String>[])new AbstractMap.SimpleEntry[0]))).getPath().split("/");
    return arrayOfString[arrayOfString.length - 1];
  }
  
  private void deleteRegistrationInternal(String paramString1, String paramString2) throws Exception {
    Connection connection = new Connection(this.mConnectionString);
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(this.mNotificationHubPath);
    stringBuilder.append("/Registrations/");
    stringBuilder.append(paramString2);
    paramString2 = stringBuilder.toString();
    try {
      AbstractMap.SimpleEntry<Object, Object> simpleEntry = new AbstractMap.SimpleEntry<Object, Object>();
      this((K)"If-Match", (V)"*");
      connection.executeRequest(paramString2, null, "application/atom+xml", "DELETE", (AbstractMap.SimpleEntry<String, String>[])new AbstractMap.SimpleEntry[] { simpleEntry });
      return;
    } finally {
      removeRegistrationId(paramString1);
    } 
  }
  
  private void refreshRegistrationInformation(String paramString) throws Exception {
    if (!Utils.isNullOrWhiteSpace(paramString)) {
      SharedPreferences.Editor editor = this.mSharedPreferences.edit();
      for (String str1 : this.mSharedPreferences.getAll().keySet()) {
        if (str1.startsWith("__NH_REG_NAME_"))
          editor.remove(str1); 
      } 
      editor.commit();
      Connection connection = new Connection(this.mConnectionString);
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(PnsSpecificRegistrationFactory.getInstance().getPNSHandleFieldName());
      stringBuilder.append(" eq '");
      stringBuilder.append(paramString);
      stringBuilder.append("'");
      paramString = stringBuilder.toString();
      stringBuilder = new StringBuilder();
      stringBuilder.append(this.mNotificationHubPath);
      stringBuilder.append("/Registrations/?$filter=");
      stringBuilder.append(URLEncoder.encode(paramString, "UTF-8"));
      String str = connection.executeRequest(stringBuilder.toString(), null, "application/atom+xml", "GET", (AbstractMap.SimpleEntry<String, String>[])new AbstractMap.SimpleEntry[0]);
      DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      documentBuilder.setEntityResolver(new EntityResolver() {
            public InputSource resolveEntity(String param1String1, String param1String2) throws SAXException, IOException {
              return null;
            }
          });
      Document document = documentBuilder.parse(new InputSource(new StringReader(str)));
      document.getDocumentElement().normalize();
      NodeList nodeList = document.getDocumentElement().getElementsByTagName("entry");
      for (byte b = 0; b < nodeList.getLength(); b++) {
        Registration registration;
        String str1 = Utils.getXmlString((Element)nodeList.item(b));
        if (PnsSpecificRegistrationFactory.getInstance().isTemplateRegistration(str1)) {
          registration = PnsSpecificRegistrationFactory.getInstance().createTemplateRegistration(this.mNotificationHubPath);
        } else {
          registration = PnsSpecificRegistrationFactory.getInstance().createNativeRegistration(this.mNotificationHubPath);
        } 
        registration.loadXml(str1, this.mNotificationHubPath);
        storeRegistrationId(registration.getName(), registration.getRegistrationId(), registration.getPNSHandle());
      } 
      this.mIsRefreshNeeded = false;
      return;
    } 
    IllegalArgumentException illegalArgumentException = new IllegalArgumentException("pnsHandle");
    throw illegalArgumentException;
  }
  
  private Registration registerInternal(Registration paramRegistration) throws Exception {
    if (this.mIsRefreshNeeded) {
      String str3 = this.mSharedPreferences.getString("__NH_PNS_HANDLE", "");
      String str4 = str3;
      if (Utils.isNullOrWhiteSpace(str3))
        str4 = paramRegistration.getPNSHandle(); 
      refreshRegistrationInformation(str4);
    } 
    String str1 = retrieveRegistrationId(paramRegistration.getName());
    String str2 = str1;
    if (Utils.isNullOrWhiteSpace(str1))
      str2 = createRegistrationId(); 
    paramRegistration.setRegistrationId(str2);
    try {
      return upsertRegistrationInternal(paramRegistration);
    } catch (RegistrationGoneException registrationGoneException) {
      paramRegistration.setRegistrationId(createRegistrationId());
      return upsertRegistrationInternal(paramRegistration);
    } 
  }
  
  private void removeRegistrationId(String paramString) throws Exception {
    SharedPreferences.Editor editor = this.mSharedPreferences.edit();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("__NH_REG_NAME_");
    stringBuilder.append(paramString);
    editor.remove(stringBuilder.toString());
    editor.commit();
  }
  
  private String retrieveRegistrationId(String paramString) throws Exception {
    SharedPreferences sharedPreferences = this.mSharedPreferences;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("__NH_REG_NAME_");
    stringBuilder.append(paramString);
    return sharedPreferences.getString(stringBuilder.toString(), null);
  }
  
  private void storeRegistrationId(String paramString1, String paramString2, String paramString3) throws Exception {
    SharedPreferences.Editor editor = this.mSharedPreferences.edit();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("__NH_REG_NAME_");
    stringBuilder.append(paramString1);
    editor.putString(stringBuilder.toString(), paramString2);
    editor.putString("__NH_PNS_HANDLE", paramString3);
    editor.putString("__NH_STORAGE_VERSION", "1.0.0");
    editor.commit();
  }
  
  private void unregisterInternal(String paramString) throws Exception {
    String str = retrieveRegistrationId(paramString);
    if (!Utils.isNullOrWhiteSpace(str))
      deleteRegistrationInternal(paramString, str); 
  }
  
  private Registration upsertRegistrationInternal(Registration paramRegistration) throws Exception {
    Registration registration;
    String str = (new Connection(this.mConnectionString)).executeRequest(paramRegistration.getURI(), paramRegistration.toXml(), "application/atom+xml", "PUT", (AbstractMap.SimpleEntry<String, String>[])new AbstractMap.SimpleEntry[0]);
    if (PnsSpecificRegistrationFactory.getInstance().isTemplateRegistration(str)) {
      registration = PnsSpecificRegistrationFactory.getInstance().createTemplateRegistration(this.mNotificationHubPath);
    } else {
      registration = PnsSpecificRegistrationFactory.getInstance().createNativeRegistration(this.mNotificationHubPath);
    } 
    registration.loadXml(str, this.mNotificationHubPath);
    storeRegistrationId(registration.getName(), registration.getRegistrationId(), paramRegistration.getPNSHandle());
    return registration;
  }
  
  private void verifyStorageVersion() {
    str = this.mSharedPreferences.getString("__NH_STORAGE_VERSION", "");
    SharedPreferences.Editor editor = this.mSharedPreferences.edit();
    if (!str.equals("1.0.0"))
      for (String str : this.mSharedPreferences.getAll().keySet()) {
        if (str.startsWith("__NH_"))
          editor.remove(str); 
      }  
    editor.commit();
    this.mIsRefreshNeeded = true;
  }
  
  public String getConnectionString() {
    return this.mConnectionString;
  }
  
  public String getNotificationHubPath() {
    return this.mNotificationHubPath;
  }
  
  public Registration register(String paramString, String... paramVarArgs) throws Exception {
    if (!Utils.isNullOrWhiteSpace(paramString)) {
      Registration registration = PnsSpecificRegistrationFactory.getInstance().createNativeRegistration(this.mNotificationHubPath);
      registration.setPNSHandle(paramString);
      registration.setName("$Default");
      registration.addTags(paramVarArgs);
      return registerInternal(registration);
    } 
    throw new IllegalArgumentException("pnsHandle");
  }
  
  public Registration registerBaidu(String paramString1, String paramString2, String... paramVarArgs) throws Exception {
    if (!Utils.isNullOrWhiteSpace(paramString1)) {
      if (!Utils.isNullOrWhiteSpace(paramString2)) {
        PnsSpecificRegistrationFactory.getInstance().setRegistrationType(Registration.RegistrationType.baidu);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(paramString1);
        stringBuilder.append("-");
        stringBuilder.append(paramString2);
        return register(stringBuilder.toString(), paramVarArgs);
      } 
      throw new IllegalArgumentException("channelId");
    } 
    throw new IllegalArgumentException("userId");
  }
  
  public TemplateRegistration registerBaiduTemplate(String paramString1, String paramString2, String paramString3, String paramString4, String... paramVarArgs) throws Exception {
    if (!Utils.isNullOrWhiteSpace(paramString1)) {
      if (!Utils.isNullOrWhiteSpace(paramString2)) {
        if (!Utils.isNullOrWhiteSpace(paramString3)) {
          if (!Utils.isNullOrWhiteSpace(paramString4)) {
            PnsSpecificRegistrationFactory.getInstance().setRegistrationType(Registration.RegistrationType.baidu);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(paramString1);
            stringBuilder.append("-");
            stringBuilder.append(paramString2);
            return registerTemplate(stringBuilder.toString(), paramString3, paramString4, paramVarArgs);
          } 
          throw new IllegalArgumentException("template");
        } 
        throw new IllegalArgumentException("templateName");
      } 
      throw new IllegalArgumentException("channelId");
    } 
    throw new IllegalArgumentException("userId");
  }
  
  public TemplateRegistration registerTemplate(String paramString1, String paramString2, String paramString3, String... paramVarArgs) throws Exception {
    if (!Utils.isNullOrWhiteSpace(paramString1)) {
      if (!Utils.isNullOrWhiteSpace(paramString2)) {
        if (!Utils.isNullOrWhiteSpace(paramString3)) {
          TemplateRegistration templateRegistration = PnsSpecificRegistrationFactory.getInstance().createTemplateRegistration(this.mNotificationHubPath);
          templateRegistration.setPNSHandle(paramString1);
          templateRegistration.setName(paramString2);
          templateRegistration.setBodyTemplate(paramString3);
          templateRegistration.addTags(paramVarArgs);
          return (TemplateRegistration)registerInternal(templateRegistration);
        } 
        throw new IllegalArgumentException("template");
      } 
      throw new IllegalArgumentException("templateName");
    } 
    throw new IllegalArgumentException("pnsHandle");
  }
  
  public void setConnectionString(String paramString) {
    if (!Utils.isNullOrWhiteSpace(paramString))
      try {
        ConnectionStringParser.parse(paramString);
        this.mConnectionString = paramString;
        return;
      } catch (Exception exception) {
        throw new IllegalArgumentException("connectionString", exception);
      }  
    throw new IllegalArgumentException("connectionString");
  }
  
  public void setNotificationHubPath(String paramString) {
    if (!Utils.isNullOrWhiteSpace(paramString)) {
      this.mNotificationHubPath = paramString;
      return;
    } 
    throw new IllegalArgumentException("notificationHubPath");
  }
  
  public void unregister() throws Exception {
    unregisterInternal("$Default");
  }
  
  public void unregisterAll(String paramString) throws Exception {
    refreshRegistrationInformation(paramString);
    for (String paramString : this.mSharedPreferences.getAll().keySet()) {
      if (paramString.startsWith("__NH_REG_NAME_"))
        deleteRegistrationInternal(paramString.substring(14), this.mSharedPreferences.getString(paramString, "")); 
    } 
  }
  
  public void unregisterTemplate(String paramString) throws Exception {
    if (!Utils.isNullOrWhiteSpace(paramString)) {
      unregisterInternal(paramString);
      return;
    } 
    throw new IllegalArgumentException("templateName");
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/windowsazure/messaging/NotificationHub.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */