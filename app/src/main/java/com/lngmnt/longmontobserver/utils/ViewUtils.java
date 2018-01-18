package com.lngmnt.longmontobserver.utils;

import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.View;
import android.widget.ViewFlipper;

import static android.support.v4.graphics.drawable.DrawableCompat.*;

/**
 * Created by @sangeles on 12/24/17.
 */

public class ViewUtils {

    @Nullable
    public static Integer getFlipperChildIndex(ViewFlipper viewFlipper, int childViewResId) {
        for (int i = 0; i < viewFlipper.getChildCount(); i++) {
            View childFlipperView = viewFlipper.getChildAt(i);
            if (childFlipperView.getId() == childViewResId) {
                return i;
            }
        }
        return null;
    }

    public static void setDrawableCompatTint(@Nullable Drawable drawable, @ColorInt int tintColorRes) {
        if (drawable != null) {
            Drawable unwrappedDrawable = unwrap(drawable);
            setTint(unwrappedDrawable, tintColorRes);
        }
    }
}
