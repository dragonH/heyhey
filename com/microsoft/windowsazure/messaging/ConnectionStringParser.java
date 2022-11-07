package com.microsoft.windowsazure.messaging;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

class ConnectionStringParser {
  private int _pos;
  
  private ParserState _state;
  
  private String _value;
  
  private ConnectionStringParser(String paramString) {
    this._value = paramString;
    this._pos = 0;
    this._state = ParserState.EXPECT_KEY;
  }
  
  private IllegalArgumentException createException(int paramInt, String paramString, Object... paramVarArgs) {
    return new IllegalArgumentException(String.format("Invalid connection string: %s.", new Object[] { String.format("Error parsing connection string: %s. Position %s", new Object[] { String.format(paramString, paramVarArgs), Integer.valueOf(this._pos) }) }));
  }
  
  private String extractKey() {
    int i = this._pos;
    String str = this._value;
    this._pos = i + 1;
    char c = str.charAt(i);
    if (c == '"' || c == '\'') {
      str = extractString(c);
    } else if (c != ';' && c != '=') {
      while (this._pos < this._value.length() && this._value.charAt(this._pos) != '=')
        this._pos++; 
      str = this._value.substring(i, this._pos).trim();
    } else {
      throw createException(i, "Missing key", new Object[0]);
    } 
    if (str.length() != 0)
      return str; 
    IllegalArgumentException illegalArgumentException = createException(i, "Empty key", new Object[0]);
    throw illegalArgumentException;
  }
  
  private String extractString(char paramChar) {
    int i = this._pos;
    while (this._pos < this._value.length() && this._value.charAt(this._pos) != paramChar)
      this._pos++; 
    if (this._pos != this._value.length()) {
      String str = this._value;
      int j = this._pos;
      this._pos = j + 1;
      return str.substring(i, j);
    } 
    IllegalArgumentException illegalArgumentException = createException(this._pos, "Missing character", new Object[] { Character.valueOf(paramChar) });
    throw illegalArgumentException;
  }
  
  private String extractValue() {
    String str;
    if (this._pos < this._value.length()) {
      char c = this._value.charAt(this._pos);
      if (c == '\'' || c == '"') {
        this._pos++;
        return extractString(c);
      } 
      int i = this._pos;
      boolean bool = false;
      while (this._pos < this._value.length() && !bool) {
        if (this._value.charAt(this._pos) == ';') {
          if (isStartWithKnownKey()) {
            bool = true;
            continue;
          } 
          this._pos++;
          continue;
        } 
        this._pos++;
      } 
      str = this._value.substring(i, this._pos).trim();
    } else {
      str = "";
    } 
    return str;
  }
  
  private boolean isStartWithKnownKey() {
    Locale locale = Locale.getDefault();
    int i = this._value.length();
    int j = this._pos;
    boolean bool1 = true;
    boolean bool2 = bool1;
    if (i > j + 1) {
      bool2 = bool1;
      if (!this._value.substring(j + 1).toLowerCase(locale).startsWith("endpoint")) {
        bool2 = bool1;
        if (!this._value.substring(this._pos + 1).toLowerCase(locale).startsWith("stsendpoint")) {
          bool2 = bool1;
          if (!this._value.substring(this._pos + 1).toLowerCase(locale).startsWith("sharedsecretissuer")) {
            bool2 = bool1;
            if (!this._value.substring(this._pos + 1).toLowerCase(locale).startsWith("sharedsecretvalue")) {
              bool2 = bool1;
              if (!this._value.substring(this._pos + 1).toLowerCase(locale).startsWith("sharedaccesskeyname"))
                if (this._value.substring(this._pos + 1).toLowerCase(locale).startsWith("sharedaccesskey")) {
                  bool2 = bool1;
                } else {
                  bool2 = false;
                }  
            } 
          } 
        } 
      } 
    } 
    return bool2;
  }
  
  private Map<String, String> parse() {
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    label24: while (true) {
      String str = null;
      while (true) {
        skipWhitespaces();
        if (this._pos == this._value.length()) {
          ParserState parserState = this._state;
          if (parserState != ParserState.EXPECT_VALUE) {
            if (parserState != ParserState.EXPECT_ASSIGNMENT)
              return (Map)hashMap; 
            throw createException(this._pos, "Missing character %s", new Object[] { "=" });
          } 
        } 
        int i = null.$SwitchMap$com$microsoft$windowsazure$messaging$ConnectionStringParser$ParserState[this._state.ordinal()];
        if (i != 1) {
          if (i != 2) {
            if (i != 3) {
              skipOperator(';');
              this._state = ParserState.EXPECT_KEY;
              continue;
            } 
            String str1 = extractValue();
            this._state = ParserState.EXPECT_SEPARATOR;
            hashMap.put(str, str1);
            continue label24;
          } 
          skipOperator('=');
          this._state = ParserState.EXPECT_VALUE;
          continue;
        } 
        str = extractKey();
        this._state = ParserState.EXPECT_ASSIGNMENT;
      } 
      break;
    } 
  }
  
  public static Map<String, String> parse(String paramString) {
    return (new ConnectionStringParser(paramString)).parse();
  }
  
  private void skipOperator(char paramChar) {
    if (this._value.charAt(this._pos) == paramChar) {
      this._pos++;
      return;
    } 
    throw createException(this._pos, "Missing character", new Object[] { Character.valueOf(paramChar) });
  }
  
  private void skipWhitespaces() {
    while (this._pos < this._value.length() && Character.isWhitespace(this._value.charAt(this._pos)))
      this._pos++; 
  }
  
  private enum ParserState {
    EXPECT_ASSIGNMENT, EXPECT_KEY, EXPECT_SEPARATOR, EXPECT_VALUE;
    
    static {
      ParserState parserState1 = new ParserState("EXPECT_KEY", 0);
      EXPECT_KEY = parserState1;
      ParserState parserState2 = new ParserState("EXPECT_ASSIGNMENT", 1);
      EXPECT_ASSIGNMENT = parserState2;
      ParserState parserState3 = new ParserState("EXPECT_VALUE", 2);
      EXPECT_VALUE = parserState3;
      ParserState parserState4 = new ParserState("EXPECT_SEPARATOR", 3);
      EXPECT_SEPARATOR = parserState4;
      $VALUES = new ParserState[] { parserState1, parserState2, parserState3, parserState4 };
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/windowsazure/messaging/ConnectionStringParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */