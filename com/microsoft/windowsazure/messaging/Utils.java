package com.microsoft.windowsazure.messaging;

import java.io.ByteArrayOutputStream;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Element;

class Utils {
  public static String getXmlString(Element paramElement) throws Exception {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    StreamResult streamResult = new StreamResult(byteArrayOutputStream);
    DOMSource dOMSource = new DOMSource(paramElement);
    Transformer transformer = TransformerFactory.newInstance().newTransformer();
    transformer.setOutputProperty("omit-xml-declaration", "yes");
    transformer.transform(dOMSource, streamResult);
    return new String(byteArrayOutputStream.toByteArray(), "UTF-8");
  }
  
  public static boolean isNullOrWhiteSpace(String paramString) {
    return (paramString == null || paramString.trim().equals(""));
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/windowsazure/messaging/Utils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */