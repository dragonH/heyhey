package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;

final class BackStackState implements Parcelable {
  public static final Parcelable.Creator<BackStackState> CREATOR = new Parcelable.Creator<BackStackState>() {
      public BackStackState createFromParcel(Parcel param1Parcel) {
        return new BackStackState(param1Parcel);
      }
      
      public BackStackState[] newArray(int param1Int) {
        return new BackStackState[param1Int];
      }
    };
  
  final int mBreadCrumbShortTitleRes;
  
  final CharSequence mBreadCrumbShortTitleText;
  
  final int mBreadCrumbTitleRes;
  
  final CharSequence mBreadCrumbTitleText;
  
  final int mIndex;
  
  final String mName;
  
  final int[] mOps;
  
  final boolean mReorderingAllowed;
  
  final ArrayList<String> mSharedElementSourceNames;
  
  final ArrayList<String> mSharedElementTargetNames;
  
  final int mTransition;
  
  final int mTransitionStyle;
  
  public BackStackState(Parcel paramParcel) {
    boolean bool;
    this.mOps = paramParcel.createIntArray();
    this.mTransition = paramParcel.readInt();
    this.mTransitionStyle = paramParcel.readInt();
    this.mName = paramParcel.readString();
    this.mIndex = paramParcel.readInt();
    this.mBreadCrumbTitleRes = paramParcel.readInt();
    this.mBreadCrumbTitleText = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(paramParcel);
    this.mBreadCrumbShortTitleRes = paramParcel.readInt();
    this.mBreadCrumbShortTitleText = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(paramParcel);
    this.mSharedElementSourceNames = paramParcel.createStringArrayList();
    this.mSharedElementTargetNames = paramParcel.createStringArrayList();
    if (paramParcel.readInt() != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    this.mReorderingAllowed = bool;
  }
  
  public BackStackState(BackStackRecord paramBackStackRecord) {
    int i = paramBackStackRecord.mOps.size();
    this.mOps = new int[i * 6];
    if (paramBackStackRecord.mAddToBackStack) {
      byte b = 0;
      int j = 0;
      while (b < i) {
        BackStackRecord.Op op = paramBackStackRecord.mOps.get(b);
        int[] arrayOfInt = this.mOps;
        int k = j + 1;
        arrayOfInt[j] = op.cmd;
        int m = k + 1;
        Fragment fragment = op.fragment;
        if (fragment != null) {
          j = fragment.mIndex;
        } else {
          j = -1;
        } 
        arrayOfInt[k] = j;
        k = m + 1;
        arrayOfInt[m] = op.enterAnim;
        j = k + 1;
        arrayOfInt[k] = op.exitAnim;
        k = j + 1;
        arrayOfInt[j] = op.popEnterAnim;
        j = k + 1;
        arrayOfInt[k] = op.popExitAnim;
        b++;
      } 
      this.mTransition = paramBackStackRecord.mTransition;
      this.mTransitionStyle = paramBackStackRecord.mTransitionStyle;
      this.mName = paramBackStackRecord.mName;
      this.mIndex = paramBackStackRecord.mIndex;
      this.mBreadCrumbTitleRes = paramBackStackRecord.mBreadCrumbTitleRes;
      this.mBreadCrumbTitleText = paramBackStackRecord.mBreadCrumbTitleText;
      this.mBreadCrumbShortTitleRes = paramBackStackRecord.mBreadCrumbShortTitleRes;
      this.mBreadCrumbShortTitleText = paramBackStackRecord.mBreadCrumbShortTitleText;
      this.mSharedElementSourceNames = paramBackStackRecord.mSharedElementSourceNames;
      this.mSharedElementTargetNames = paramBackStackRecord.mSharedElementTargetNames;
      this.mReorderingAllowed = paramBackStackRecord.mReorderingAllowed;
      return;
    } 
    IllegalStateException illegalStateException = new IllegalStateException("Not on back stack");
    throw illegalStateException;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public BackStackRecord instantiate(FragmentManagerImpl paramFragmentManagerImpl) {
    BackStackRecord backStackRecord = new BackStackRecord(paramFragmentManagerImpl);
    int i = 0;
    byte b = 0;
    while (i < this.mOps.length) {
      BackStackRecord.Op op = new BackStackRecord.Op();
      int[] arrayOfInt = this.mOps;
      int j = i + 1;
      op.cmd = arrayOfInt[i];
      if (FragmentManagerImpl.DEBUG) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Instantiate ");
        stringBuilder.append(backStackRecord);
        stringBuilder.append(" op #");
        stringBuilder.append(b);
        stringBuilder.append(" base fragment #");
        stringBuilder.append(this.mOps[j]);
        Log.v("FragmentManager", stringBuilder.toString());
      } 
      arrayOfInt = this.mOps;
      i = j + 1;
      j = arrayOfInt[j];
      if (j >= 0) {
        op.fragment = (Fragment)paramFragmentManagerImpl.mActive.get(j);
      } else {
        op.fragment = null;
      } 
      arrayOfInt = this.mOps;
      j = i + 1;
      i = arrayOfInt[i];
      op.enterAnim = i;
      int k = j + 1;
      int m = arrayOfInt[j];
      op.exitAnim = m;
      j = k + 1;
      k = arrayOfInt[k];
      op.popEnterAnim = k;
      int n = arrayOfInt[j];
      op.popExitAnim = n;
      backStackRecord.mEnterAnim = i;
      backStackRecord.mExitAnim = m;
      backStackRecord.mPopEnterAnim = k;
      backStackRecord.mPopExitAnim = n;
      backStackRecord.addOp(op);
      b++;
      i = j + 1;
    } 
    backStackRecord.mTransition = this.mTransition;
    backStackRecord.mTransitionStyle = this.mTransitionStyle;
    backStackRecord.mName = this.mName;
    backStackRecord.mIndex = this.mIndex;
    backStackRecord.mAddToBackStack = true;
    backStackRecord.mBreadCrumbTitleRes = this.mBreadCrumbTitleRes;
    backStackRecord.mBreadCrumbTitleText = this.mBreadCrumbTitleText;
    backStackRecord.mBreadCrumbShortTitleRes = this.mBreadCrumbShortTitleRes;
    backStackRecord.mBreadCrumbShortTitleText = this.mBreadCrumbShortTitleText;
    backStackRecord.mSharedElementSourceNames = this.mSharedElementSourceNames;
    backStackRecord.mSharedElementTargetNames = this.mSharedElementTargetNames;
    backStackRecord.mReorderingAllowed = this.mReorderingAllowed;
    backStackRecord.bumpBackStackNesting(1);
    return backStackRecord;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeIntArray(this.mOps);
    paramParcel.writeInt(this.mTransition);
    paramParcel.writeInt(this.mTransitionStyle);
    paramParcel.writeString(this.mName);
    paramParcel.writeInt(this.mIndex);
    paramParcel.writeInt(this.mBreadCrumbTitleRes);
    TextUtils.writeToParcel(this.mBreadCrumbTitleText, paramParcel, 0);
    paramParcel.writeInt(this.mBreadCrumbShortTitleRes);
    TextUtils.writeToParcel(this.mBreadCrumbShortTitleText, paramParcel, 0);
    paramParcel.writeStringList(this.mSharedElementSourceNames);
    paramParcel.writeStringList(this.mSharedElementTargetNames);
    paramParcel.writeInt(this.mReorderingAllowed);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/app/BackStackState.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */