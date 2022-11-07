package mono.android.accounts;

import android.accounts.Account;
import android.accounts.OnAccountsUpdateListener;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class OnAccountsUpdateListenerImplementor implements IGCUserPeer, OnAccountsUpdateListener {
  public static final String __md_methods = "n_onAccountsUpdated:([Landroid/accounts/Account;)V:GetOnAccountsUpdated_arrayLandroid_accounts_Account_Handler:Android.Accounts.IOnAccountsUpdateListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Accounts.IOnAccountsUpdateListenerImplementor, Mono.Android", OnAccountsUpdateListenerImplementor.class, "n_onAccountsUpdated:([Landroid/accounts/Account;)V:GetOnAccountsUpdated_arrayLandroid_accounts_Account_Handler:Android.Accounts.IOnAccountsUpdateListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public OnAccountsUpdateListenerImplementor() {
    if (getClass() == OnAccountsUpdateListenerImplementor.class)
      TypeManager.Activate("Android.Accounts.IOnAccountsUpdateListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onAccountsUpdated(Account[] paramArrayOfAccount);
  
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
  
  public void onAccountsUpdated(Account[] paramArrayOfAccount) {
    n_onAccountsUpdated(paramArrayOfAccount);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/accounts/OnAccountsUpdateListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */