package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.cardview.R;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

public class CardView extends FrameLayout {
  private static final int[] COLOR_BACKGROUND_ATTR = new int[] { 16842801 };
  
  private static final CardViewImpl IMPL;
  
  private final CardViewDelegate mCardViewDelegate = new CardViewDelegate() {
      private Drawable mCardBackground;
      
      public Drawable getCardBackground() {
        return this.mCardBackground;
      }
      
      public View getCardView() {
        return (View)CardView.this;
      }
      
      public boolean getPreventCornerOverlap() {
        return CardView.this.getPreventCornerOverlap();
      }
      
      public boolean getUseCompatPadding() {
        return CardView.this.getUseCompatPadding();
      }
      
      public void setCardBackground(Drawable param1Drawable) {
        this.mCardBackground = param1Drawable;
        CardView.this.setBackgroundDrawable(param1Drawable);
      }
      
      public void setMinWidthHeightInternal(int param1Int1, int param1Int2) {
        CardView cardView = CardView.this;
        if (param1Int1 > cardView.mUserSetMinWidth)
          cardView.setMinimumWidth(param1Int1); 
        cardView = CardView.this;
        if (param1Int2 > cardView.mUserSetMinHeight)
          cardView.setMinimumHeight(param1Int2); 
      }
      
      public void setShadowPadding(int param1Int1, int param1Int2, int param1Int3, int param1Int4) {
        CardView.this.mShadowBounds.set(param1Int1, param1Int2, param1Int3, param1Int4);
        CardView cardView = CardView.this;
        Rect rect = cardView.mContentPadding;
        cardView.setPadding(param1Int1 + rect.left, param1Int2 + rect.top, param1Int3 + rect.right, param1Int4 + rect.bottom);
      }
    };
  
  private boolean mCompatPadding;
  
  final Rect mContentPadding = new Rect();
  
  private boolean mPreventCornerOverlap;
  
  final Rect mShadowBounds = new Rect();
  
  int mUserSetMinHeight;
  
  int mUserSetMinWidth;
  
  static {
    int i = Build.VERSION.SDK_INT;
    if (i >= 21) {
      IMPL = new CardViewApi21Impl();
    } else if (i >= 17) {
      IMPL = new CardViewApi17Impl();
    } else {
      IMPL = new CardViewBaseImpl();
    } 
    IMPL.initStatic();
  }
  
  public CardView(Context paramContext) {
    super(paramContext);
    initialize(paramContext, null, 0);
  }
  
  public CardView(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
    initialize(paramContext, paramAttributeSet, 0);
  }
  
  public CardView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    initialize(paramContext, paramAttributeSet, paramInt);
  }
  
  private void initialize(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    ColorStateList colorStateList;
    TypedArray typedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.CardView, paramInt, R.style.CardView);
    paramInt = R.styleable.CardView_cardBackgroundColor;
    if (typedArray.hasValue(paramInt)) {
      colorStateList = typedArray.getColorStateList(paramInt);
    } else {
      TypedArray typedArray1 = getContext().obtainStyledAttributes(COLOR_BACKGROUND_ATTR);
      paramInt = typedArray1.getColor(0, 0);
      typedArray1.recycle();
      float[] arrayOfFloat = new float[3];
      Color.colorToHSV(paramInt, arrayOfFloat);
      if (arrayOfFloat[2] > 0.5F) {
        paramInt = getResources().getColor(R.color.cardview_light_background);
      } else {
        paramInt = getResources().getColor(R.color.cardview_dark_background);
      } 
      colorStateList = ColorStateList.valueOf(paramInt);
    } 
    float f1 = typedArray.getDimension(R.styleable.CardView_cardCornerRadius, 0.0F);
    float f2 = typedArray.getDimension(R.styleable.CardView_cardElevation, 0.0F);
    float f3 = typedArray.getDimension(R.styleable.CardView_cardMaxElevation, 0.0F);
    this.mCompatPadding = typedArray.getBoolean(R.styleable.CardView_cardUseCompatPadding, false);
    this.mPreventCornerOverlap = typedArray.getBoolean(R.styleable.CardView_cardPreventCornerOverlap, true);
    paramInt = typedArray.getDimensionPixelSize(R.styleable.CardView_contentPadding, 0);
    this.mContentPadding.left = typedArray.getDimensionPixelSize(R.styleable.CardView_contentPaddingLeft, paramInt);
    this.mContentPadding.top = typedArray.getDimensionPixelSize(R.styleable.CardView_contentPaddingTop, paramInt);
    this.mContentPadding.right = typedArray.getDimensionPixelSize(R.styleable.CardView_contentPaddingRight, paramInt);
    this.mContentPadding.bottom = typedArray.getDimensionPixelSize(R.styleable.CardView_contentPaddingBottom, paramInt);
    if (f2 > f3)
      f3 = f2; 
    this.mUserSetMinWidth = typedArray.getDimensionPixelSize(R.styleable.CardView_android_minWidth, 0);
    this.mUserSetMinHeight = typedArray.getDimensionPixelSize(R.styleable.CardView_android_minHeight, 0);
    typedArray.recycle();
    IMPL.initialize(this.mCardViewDelegate, paramContext, colorStateList, f1, f2, f3);
  }
  
  public ColorStateList getCardBackgroundColor() {
    return IMPL.getBackgroundColor(this.mCardViewDelegate);
  }
  
  public float getCardElevation() {
    return IMPL.getElevation(this.mCardViewDelegate);
  }
  
  public int getContentPaddingBottom() {
    return this.mContentPadding.bottom;
  }
  
  public int getContentPaddingLeft() {
    return this.mContentPadding.left;
  }
  
  public int getContentPaddingRight() {
    return this.mContentPadding.right;
  }
  
  public int getContentPaddingTop() {
    return this.mContentPadding.top;
  }
  
  public float getMaxCardElevation() {
    return IMPL.getMaxElevation(this.mCardViewDelegate);
  }
  
  public boolean getPreventCornerOverlap() {
    return this.mPreventCornerOverlap;
  }
  
  public float getRadius() {
    return IMPL.getRadius(this.mCardViewDelegate);
  }
  
  public boolean getUseCompatPadding() {
    return this.mCompatPadding;
  }
  
  protected void onMeasure(int paramInt1, int paramInt2) {
    CardViewImpl cardViewImpl = IMPL;
    if (!(cardViewImpl instanceof CardViewApi21Impl)) {
      int i = View.MeasureSpec.getMode(paramInt1);
      if (i == Integer.MIN_VALUE || i == 1073741824)
        paramInt1 = View.MeasureSpec.makeMeasureSpec(Math.max((int)Math.ceil(cardViewImpl.getMinWidth(this.mCardViewDelegate)), View.MeasureSpec.getSize(paramInt1)), i); 
      i = View.MeasureSpec.getMode(paramInt2);
      if (i == Integer.MIN_VALUE || i == 1073741824)
        paramInt2 = View.MeasureSpec.makeMeasureSpec(Math.max((int)Math.ceil(cardViewImpl.getMinHeight(this.mCardViewDelegate)), View.MeasureSpec.getSize(paramInt2)), i); 
      super.onMeasure(paramInt1, paramInt2);
    } else {
      super.onMeasure(paramInt1, paramInt2);
    } 
  }
  
  public void setCardBackgroundColor(@ColorInt int paramInt) {
    IMPL.setBackgroundColor(this.mCardViewDelegate, ColorStateList.valueOf(paramInt));
  }
  
  public void setCardBackgroundColor(@Nullable ColorStateList paramColorStateList) {
    IMPL.setBackgroundColor(this.mCardViewDelegate, paramColorStateList);
  }
  
  public void setCardElevation(float paramFloat) {
    IMPL.setElevation(this.mCardViewDelegate, paramFloat);
  }
  
  public void setContentPadding(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    this.mContentPadding.set(paramInt1, paramInt2, paramInt3, paramInt4);
    IMPL.updatePadding(this.mCardViewDelegate);
  }
  
  public void setMaxCardElevation(float paramFloat) {
    IMPL.setMaxElevation(this.mCardViewDelegate, paramFloat);
  }
  
  public void setMinimumHeight(int paramInt) {
    this.mUserSetMinHeight = paramInt;
    super.setMinimumHeight(paramInt);
  }
  
  public void setMinimumWidth(int paramInt) {
    this.mUserSetMinWidth = paramInt;
    super.setMinimumWidth(paramInt);
  }
  
  public void setPadding(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {}
  
  public void setPaddingRelative(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {}
  
  public void setPreventCornerOverlap(boolean paramBoolean) {
    if (paramBoolean != this.mPreventCornerOverlap) {
      this.mPreventCornerOverlap = paramBoolean;
      IMPL.onPreventCornerOverlapChanged(this.mCardViewDelegate);
    } 
  }
  
  public void setRadius(float paramFloat) {
    IMPL.setRadius(this.mCardViewDelegate, paramFloat);
  }
  
  public void setUseCompatPadding(boolean paramBoolean) {
    if (this.mCompatPadding != paramBoolean) {
      this.mCompatPadding = paramBoolean;
      IMPL.onCompatPaddingChanged(this.mCardViewDelegate);
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/widget/CardView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */