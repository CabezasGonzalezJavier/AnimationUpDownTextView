package com.thedeveloperworldisyours.animationupdown;

import android.animation.Animator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.Locale;

/**
 * Created by javiergonzalezcabezas on 13/2/16.
 */
public class DigitalTextView extends FrameLayout {

        private final static int ANIMATION_DURATION = 250;
        TextView mCurrentTextView, mNextTextView;

        public DigitalTextView(Context context, AttributeSet attrs) {
            super(context, attrs);
            init(context);
        }

        public DigitalTextView(Context context) {
            super(context);
            init(context);
        }

        private void init(Context context) {
            LayoutInflater.from(context).inflate(R.layout.digit_text_view, this);
            mCurrentTextView = (TextView) getRootView().findViewById(R.id.currentTextView);
            mNextTextView = (TextView) getRootView().findViewById(R.id.nextTextView);

            mNextTextView.setTranslationY(getHeight());

            setValue(0);
        }

        public void setValue(final int desiredValue) {
            if (mCurrentTextView.getText() == null || mCurrentTextView.getText().length() == 0) {
                mCurrentTextView.setText(String.format(Locale.getDefault(), "%d", desiredValue));
            }

            final int oldValue = Integer.parseInt(mCurrentTextView.getText().toString());

            if (oldValue > desiredValue) {
                mNextTextView.setText(String.format(Locale.getDefault(), "%d", oldValue-1));

                mCurrentTextView.animate().translationY(-getHeight()).setDuration(ANIMATION_DURATION).start();
                mNextTextView.setTranslationY(mNextTextView.getHeight());
                mNextTextView.animate().translationY(0).setDuration(ANIMATION_DURATION).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {}
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mCurrentTextView.setText(String.format(Locale.getDefault(), "%d", oldValue - 1));
                        mCurrentTextView.setTranslationY(0);
                        if (oldValue - 1 != desiredValue) {
                            setValue(desiredValue);
                        }
                    }
                    @Override
                    public void onAnimationCancel(Animator animation) {}
                    @Override
                    public void onAnimationRepeat(Animator animation) {}
                }).start();
            } else if (oldValue < desiredValue) {
                mNextTextView.setText(String.format(Locale.getDefault(), "%d", oldValue+1));

                mCurrentTextView.animate().translationY(getHeight()).setDuration(ANIMATION_DURATION).start();
                mNextTextView.setTranslationY(-mNextTextView.getHeight());
                mNextTextView.animate().translationY(0).setDuration(ANIMATION_DURATION).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {}
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mCurrentTextView.setText(String.format(Locale.getDefault(), "%d", oldValue + 1));
                        mCurrentTextView.setTranslationY(0);
                        if (oldValue + 1 != desiredValue) {
                            setValue(desiredValue);
                        }
                    }
                    @Override
                    public void onAnimationCancel(Animator animation) {}
                    @Override
                    public void onAnimationRepeat(Animator animation) {}
                }).start();
            }
        }

}
