package com.cexup.ui.utils.ecg;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class CardioGraphBackgroundView extends View {
    private static final float MULTIPLE = 0.5f;
    private static int mGridWidth;
    private static int mSGridWidth;
    Context context;
    protected int mBackgroundColor;
    protected int mGridColor;
    protected int mHeight;
    protected int mInitColor;
    protected Paint mPaint;
    private Path mPath;
    protected int mSGridColor;
    private int mSecLineColor;
    protected int mWidth;
    public List<Integer> plist;
    int screenHeight;

    public CardioGraphBackgroundView(Context context2) {
        this(context2, null);
    }

    public CardioGraphBackgroundView(Context context2, AttributeSet attributeSet) {
        this(context2, attributeSet, 0);
    }

    public CardioGraphBackgroundView(Context context2, AttributeSet attributeSet, int i) {
        super(context2, attributeSet, i);
        this.plist = new ArrayList();
        this.mGridColor = Color.parseColor("#D9D9D9");
        this.mSGridColor = Color.parseColor("#F0F0F0");
        this.mInitColor = Color.parseColor("#000000");
        this.mSecLineColor = Color.parseColor("#8C8C8C");
        this.mBackgroundColor = -1;
        this.screenHeight = 0;
        this.context = context2;
        Display defaultDisplay = ((Activity) context2).getWindowManager().getDefaultDisplay();
        int width = defaultDisplay.getWidth();
        this.screenHeight = defaultDisplay.getHeight();
        float f = context2.getResources().getDisplayMetrics().density;
        for (int i2 = 0; i2 < ((int) (((float) width) / f)); i2++) {
            this.plist.add(0);
        }
        this.mPaint = new Paint();
        this.mPath = new Path();
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        this.mWidth = i;
        this.mHeight = i2;
        super.onSizeChanged(i, i2, i3, i4);
    }

    public static float getMultiple(Context context2) {
        return context2.getResources().getDisplayMetrics().density * 0.5f;
    }

    public static int getSGridWidth() {
        return mSGridWidth;
    }

    private void initBackground(Canvas canvas) {
        int multiple = (int) (getMultiple(this.context) * 10.0f);
        mSGridWidth = multiple;
        mGridWidth = multiple * 5;
        canvas.drawColor(this.mBackgroundColor);
        int i = this.mWidth;
        int i2 = mSGridWidth;
        int i3 = i / i2;
        int i4 = this.mHeight / i2;
        this.mPaint.setColor(this.mSGridColor);
        this.mPaint.setStrokeWidth(2.0f);
        for (int i5 = 0; i5 < i3 + 1; i5++) {
            int i6 = mSGridWidth;
            canvas.drawLine((float) (i5 * i6), 0.0f, (float) (i6 * i5), (float) this.mHeight, this.mPaint);
        }
        for (int i7 = 0; i7 < i4 + 1; i7++) {
            int i8 = mSGridWidth;
            canvas.drawLine(0.0f, (float) (i7 * i8), (float) this.mWidth, (float) (i8 * i7), this.mPaint);
        }
        int i9 = this.mWidth;
        int i10 = mGridWidth;
        int i11 = i9 / i10;
        int i12 = this.mHeight / i10;
        this.mPaint.setColor(this.mGridColor);
        this.mPaint.setStrokeWidth(3.0f);
        for (int i13 = 0; i13 < i12 + 1; i13++) {
            int i14 = mGridWidth;
            canvas.drawLine(0.0f, (float) (i13 * i14), (float) this.mWidth, (float) (i14 * i13), this.mPaint);
        }
        for (int i15 = 0; i15 < i11 + 1; i15++) {
            this.mPaint.setColor(this.mGridColor);
            int i16 = mGridWidth;
            canvas.drawLine((float) (i15 * i16), 0.0f, (float) (i16 * i15), (float) this.mHeight, this.mPaint);
            if (i15 != 0 && i15 % 5 == 0) {
                this.mPaint.setColor(this.mSecLineColor);
                int i17 = mGridWidth;
                canvas.drawLine((float) (i15 * i17), 0.0f, (float) (i17 * i15), (float) this.mHeight, this.mPaint);
            }
        }
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setColor(this.mInitColor);
        this.mPaint.setStrokeWidth(3.0f);
        this.mPath.reset();
        this.mPath.moveTo(0.0f, (float) (mGridWidth * 3));
        this.mPath.lineTo((float) (mSGridWidth * 2), (float) (mGridWidth * 3));
        this.mPath.lineTo((float) (mSGridWidth * 2), (float) mGridWidth);
        Path path = this.mPath;
        int i18 = mGridWidth;
        path.lineTo((float) ((mSGridWidth * 2) + i18), (float) i18);
        Path path2 = this.mPath;
        int i19 = mGridWidth;
        path2.lineTo((float) ((mSGridWidth * 2) + i19), (float) (i19 * 3));
        Path path3 = this.mPath;
        int i20 = mGridWidth;
        path3.lineTo((float) ((mSGridWidth * 4) + i20), (float) (i20 * 3));
        canvas.drawPath(this.mPath, this.mPaint);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        initBackground(canvas);
    }
}