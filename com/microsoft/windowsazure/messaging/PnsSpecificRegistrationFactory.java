package com.microsoft.windowsazure.messaging;

import android.os.Build;

public final class PnsSpecificRegistrationFactory {
  private static final PnsSpecificRegistrationFactory mInstance;
  
  private static Registration.RegistrationType mRegistrationType = Registration.RegistrationType.gcm;
  
  static {
    mInstance = new PnsSpecificRegistrationFactory();
  }
  
  private PnsSpecificRegistrationFactory() {
    boolean bool;
    if (Build.MANUFACTURER.compareToIgnoreCase("Amazon") == 0) {
      bool = true;
    } else {
      bool = false;
    } 
    if (bool)
      mRegistrationType = Registration.RegistrationType.adm; 
  }
  
  public static PnsSpecificRegistrationFactory getInstance() {
    return mInstance;
  }
  
  public Registration createNativeRegistration(String paramString) {
    int i = null.$SwitchMap$com$microsoft$windowsazure$messaging$Registration$RegistrationType[mRegistrationType.ordinal()];
    if (i != 1) {
      if (i != 2) {
        if (i == 3)
          return new AdmNativeRegistration(paramString); 
        throw new AssertionError("Ivalid registration type!");
      } 
      return new BaiduNativeRegistration(paramString);
    } 
    return new GcmNativeRegistration(paramString);
  }
  
  public TemplateRegistration createTemplateRegistration(String paramString) {
    int i = null.$SwitchMap$com$microsoft$windowsazure$messaging$Registration$RegistrationType[mRegistrationType.ordinal()];
    if (i != 1) {
      if (i != 2) {
        if (i == 3)
          return new AdmTemplateRegistration(paramString); 
        throw new AssertionError("Invalid registration type!");
      } 
      return new BaiduTemplateRegistration(paramString);
    } 
    return new GcmTemplateRegistration(paramString);
  }
  
  public String getAPIOrigin() {
    int i = null.$SwitchMap$com$microsoft$windowsazure$messaging$Registration$RegistrationType[mRegistrationType.ordinal()];
    if (i != 1) {
      if (i != 2) {
        if (i == 3)
          return "AndroidSdkGcm"; 
        throw new AssertionError("Invalid registration type!");
      } 
      return "AndroidSdkBaidu";
    } 
    return "AndroidSdkAdm";
  }
  
  public String getPNSHandleFieldName() {
    int i = null.$SwitchMap$com$microsoft$windowsazure$messaging$Registration$RegistrationType[mRegistrationType.ordinal()];
    if (i != 1) {
      if (i != 2) {
        if (i == 3)
          return "AdmRegistrationId"; 
        throw new AssertionError("Invalid registration type!");
      } 
      return "BaiduUserId-BaiduChannelId";
    } 
    return "GcmRegistrationId";
  }
  
  public boolean isTemplateRegistration(String paramString) {
    String str;
    int i = null.$SwitchMap$com$microsoft$windowsazure$messaging$Registration$RegistrationType[mRegistrationType.ordinal()];
    if (i != 1) {
      if (i != 2) {
        if (i == 3) {
          str = "AdmTemplateRegistrationDescription";
        } else {
          throw new AssertionError("Invalid registration type!");
        } 
      } else {
        str = "BaiduTemplateRegistrationDescription";
      } 
    } else {
      str = "GcmTemplateRegistrationDescription";
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("<");
    stringBuilder.append(str);
    return paramString.contains(stringBuilder.toString());
  }
  
  public void setRegistrationType(Registration.RegistrationType paramRegistrationType) {
    mRegistrationType = paramRegistrationType;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/windowsazure/messaging/PnsSpecificRegistrationFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */