package mono.android.view.textservice;

import android.view.textservice.SentenceSuggestionsInfo;
import android.view.textservice.SpellCheckerSession;
import android.view.textservice.SuggestionsInfo;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class SpellCheckerSession_SpellCheckerSessionListenerImplementor implements IGCUserPeer, SpellCheckerSession.SpellCheckerSessionListener {
  public static final String __md_methods = "n_onGetSentenceSuggestions:([Landroid/view/textservice/SentenceSuggestionsInfo;)V:GetOnGetSentenceSuggestions_arrayLandroid_view_textservice_SentenceSuggestionsInfo_Handler:Android.Views.TextService.SpellCheckerSession/ISpellCheckerSessionListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onGetSuggestions:([Landroid/view/textservice/SuggestionsInfo;)V:GetOnGetSuggestions_arrayLandroid_view_textservice_SuggestionsInfo_Handler:Android.Views.TextService.SpellCheckerSession/ISpellCheckerSessionListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Views.TextService.SpellCheckerSession+ISpellCheckerSessionListenerImplementor, Mono.Android", SpellCheckerSession_SpellCheckerSessionListenerImplementor.class, "n_onGetSentenceSuggestions:([Landroid/view/textservice/SentenceSuggestionsInfo;)V:GetOnGetSentenceSuggestions_arrayLandroid_view_textservice_SentenceSuggestionsInfo_Handler:Android.Views.TextService.SpellCheckerSession/ISpellCheckerSessionListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onGetSuggestions:([Landroid/view/textservice/SuggestionsInfo;)V:GetOnGetSuggestions_arrayLandroid_view_textservice_SuggestionsInfo_Handler:Android.Views.TextService.SpellCheckerSession/ISpellCheckerSessionListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public SpellCheckerSession_SpellCheckerSessionListenerImplementor() {
    if (getClass() == SpellCheckerSession_SpellCheckerSessionListenerImplementor.class)
      TypeManager.Activate("Android.Views.TextService.SpellCheckerSession+ISpellCheckerSessionListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onGetSentenceSuggestions(SentenceSuggestionsInfo[] paramArrayOfSentenceSuggestionsInfo);
  
  private native void n_onGetSuggestions(SuggestionsInfo[] paramArrayOfSuggestionsInfo);
  
  public void monodroidAddReference(Object paramObject) {
    if (this.refList == null)
      this.refList = new ArrayList(); 
    this.refList.add(paramObject);
  }
  
  public void monodroidClearReferences() {
    ArrayList arrayList = this.refList;
    if (arrayList != null)
      arrayList.clear(); 
  }
  
  public void onGetSentenceSuggestions(SentenceSuggestionsInfo[] paramArrayOfSentenceSuggestionsInfo) {
    n_onGetSentenceSuggestions(paramArrayOfSentenceSuggestionsInfo);
  }
  
  public void onGetSuggestions(SuggestionsInfo[] paramArrayOfSuggestionsInfo) {
    n_onGetSuggestions(paramArrayOfSuggestionsInfo);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/view/textservice/SpellCheckerSession_SpellCheckerSessionListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */