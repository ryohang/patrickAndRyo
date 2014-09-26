package com.patrick.vignette;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.ComposeShader;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.view.animation.AccelerateInterpolator;

/**
 * Created by Pmoney on 9/24/2014.
 */
public class VignetteDrawable extends Drawable {

    private final Paint mPaint;
    private final RectF mRect = new RectF();
    private final Bitmap mBitmap;

    VignetteDrawable(Bitmap bitmap) {
        mBitmap = bitmap;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);

    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        mRect.set(0, 0, bounds.width(), bounds.height());
        AccelerateInterpolator interpolator = new AccelerateInterpolator();
        float[] positions = new float[4];
        float[] interpolationPoints = new float[]{0, 0.8f, 0.9f, 1f};
        for (int i = 0; i < 4; i++)
            positions[i] = interpolator.getInterpolation(interpolationPoints[i]);
        int[] colors = new int[]{0, 0, 0x9f000000, 0xbf000000};
        RadialGradient vignette = new RadialGradient(mRect.centerX(), mRect.centerY() * 1.0f / 0.7f, mRect.centerX() * 1.3f, colors, positions, Shader.TileMode.CLAMP);
        Matrix oval = new Matrix();
        oval.setScale(1f, 0.7f);
        vignette.setLocalMatrix(oval);

        BitmapShader bitmapShader = new BitmapShader(Bitmap.createScaledBitmap(mBitmap, bounds.width(), bounds.height(), true), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mPaint.setShader(new ComposeShader(bitmapShader, vignette, PorterDuff.Mode.SRC_OVER));
    }


    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect(mRect, mPaint);
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        mPaint.setColorFilter(cf);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
