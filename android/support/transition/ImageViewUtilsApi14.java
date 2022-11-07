package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Matrix;
import android.support.annotation.RequiresApi;
import android.widget.ImageView;

@RequiresApi(14)
class ImageViewUtilsApi14 implements ImageViewUtilsImpl {
  public void animateTransform(ImageView paramImageView, Matrix paramMatrix) {
    paramImageView.setImageMatrix(paramMatrix);
  }
  
  public void reserveEndAnimateTransform(final ImageView view, Animator paramAnimator) {
    paramAnimator.addListener((Animator.AnimatorListener)new AnimatorListenerAdapter() {
          public void onAnimationEnd(Animator param1Animator) {
            ImageView imageView = view;
            int i = R.id.save_scale_type;
            ImageView.ScaleType scaleType = (ImageView.ScaleType)imageView.getTag(i);
            view.setScaleType(scaleType);
            view.setTag(i, null);
            if (scaleType == ImageView.ScaleType.MATRIX) {
              ImageView imageView1 = view;
              i = R.id.save_image_matrix;
              imageView1.setImageMatrix((Matrix)imageView1.getTag(i));
              view.setTag(i, null);
            } 
            param1Animator.removeListener((Animator.AnimatorListener)this);
          }
        });
  }
  
  public void startAnimateTransform(ImageView paramImageView) {
    ImageView.ScaleType scaleType1 = paramImageView.getScaleType();
    paramImageView.setTag(R.id.save_scale_type, scaleType1);
    ImageView.ScaleType scaleType2 = ImageView.ScaleType.MATRIX;
    if (scaleType1 == scaleType2) {
      paramImageView.setTag(R.id.save_image_matrix, paramImageView.getImageMatrix());
    } else {
      paramImageView.setScaleType(scaleType2);
    } 
    paramImageView.setImageMatrix(MatrixUtils.IDENTITY_MATRIX);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/transition/ImageViewUtilsApi14.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */