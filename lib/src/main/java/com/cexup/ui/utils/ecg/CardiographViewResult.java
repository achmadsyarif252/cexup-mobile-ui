package com.cexup.ui.utils.ecg;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


import com.cexup.ui.R;

import java.util.ArrayList;
import java.util.List;

public class CardiographViewResult extends View {
    private static final float MULTIPLE = 0.5f;
    public static final int state = 10;
    private String age;
    private String bp;
    private Context context;
    private String heart;
    protected int mGridColor;
    protected float mGridWidth;
    protected int mHeight;
    protected int mInitColor;
    protected int mLineColor;
    protected Paint mPaint;
    protected Path mPath;
    protected int mSGridColor;
    protected float mSGridWidth;
    private int mSecLineColor;
    protected int mWidth;
    private List<Integer> plist;
    private String sex;
    private String userName;

    public CardiographViewResult(Context context2) {
        this(context2, null);
    }

    public CardiographViewResult(Context context2, AttributeSet attributeSet) {
        this(context2, attributeSet, 0);
    }

    public CardiographViewResult(Context context2, AttributeSet attributeSet, int i) {
        super(context2, attributeSet, i);
        this.plist = new ArrayList();
        this.mInitColor = Color.parseColor("#000000");
        this.mLineColor = Color.parseColor("#FB3159");
        this.mGridColor = Color.parseColor("#D9D9D9");
        this.mSGridColor = Color.parseColor("#F0F0F0");
        this.mSecLineColor = Color.parseColor("#8C8C8C");
        this.context = context2;
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mPath = new Path();
    }


    public void setDatas(
            List<Integer> datas,
            String username,
            String sex,
            String age,
            String heart,
            String bp
    ) {


        if (!datas.isEmpty()) {

            this.plist.addAll(datas);
            invalidate();
        }

    }


    public List<Integer> getDatas() {
        return this.plist;
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        this.mWidth = i;
        this.mHeight = i2;
        super.onSizeChanged(i, i2, i3, i4);
    }


    public void initBackground(Canvas canvas) {
       try {


           //bg
           String str;
           float multiple = getMultiple(this.context) * 10.0f;
           this.mSGridWidth = multiple;
           this.mGridWidth = multiple * 5.0f;
           canvas.drawColor(-1);
           float f = this.mSGridWidth;
           int i2 = (int) (((float) this.mWidth) / f);
           int i3 = (int) (((float) this.mHeight) / f);
           this.mPaint.setColor(this.mSGridColor);
           this.mPaint.setStyle(Paint.Style.STROKE);
           this.mPaint.setStrokeWidth(2.0f);
           int i4 = 0;
           while (true) {
               if (i4 >= i2 + 1) {
                   break;
               }
               float f2 = (float) i4;
               float f3 = this.mSGridWidth;
               canvas.drawLine(f2 * f3, 0.0f, f2 * f3, (float) this.mHeight, this.mPaint);
               i4++;
           }
           for (int i5 = 0; i5 < i3 + 1; i5++) {
               float f4 = (float) i5;
               float f5 = this.mSGridWidth;
               canvas.drawLine(0.0f, f4 * f5, (float) this.mWidth, f4 * f5, this.mPaint);
           }
           float f6 = this.mGridWidth;
           int i6 = (int) (((float) this.mWidth) / f6);
           int i7 = (int) (((float) this.mHeight) / f6);
           this.mPaint.setColor(this.mGridColor);
           this.mPaint.setStrokeWidth(3.0f);
           for (int i8 = 0; i8 < i7 + 1; i8++) {
               if (i8 == 0 || i8 == i7) {
                   this.mPaint.setColor(this.mInitColor);
               } else {
                   this.mPaint.setColor(this.mGridColor);
               }
               float f7 = (float) i8;
               float f8 = this.mGridWidth;
               canvas.drawLine(0.0f, f7 * f8, (float) this.mWidth, f7 * f8, this.mPaint);
           }
           for (int i9 = 0; i9 < i6 + 1; i9++) {
               this.mPaint.setStyle(Paint.Style.STROKE);
               this.mPaint.setStrokeWidth(3.0f);
               if (i9 == 0 || i9 == i6) {
                   this.mPaint.setColor(this.mInitColor);
               } else {
                   this.mPaint.setColor(this.mGridColor);
               }
               float f9 = (float) i9;
               float f10 = this.mGridWidth;
               canvas.drawLine(f9 * f10, 0.0f, f9 * f10, (float) this.mHeight, this.mPaint);
               int i10 = i9 - 2;
               if (i10 % 5 == 0) {
                   this.mPaint.setColor(this.mSecLineColor);
                   float f11 = this.mGridWidth;
                   canvas.drawLine(f9 * f11, f11 * 2.0f, f9 * f11, ((float) this.mHeight) - (f11 * 2.0f), this.mPaint);
                   this.mPaint.setColor(this.mInitColor);
                   this.mPaint.setStyle(Paint.Style.FILL);
                   this.mPaint.setStrokeWidth(1.0f);
                   this.mPaint.setTextSize(getResources().getDisplayMetrics().density * 10.0f);
                   float f12 = this.mGridWidth;
                   canvas.drawText((i10 / 5) + "s", (f9 * f12) + (this.mSGridWidth * 0.5f), ((float) this.mHeight) - (f12 * 2.0f), this.mPaint);
               }
           }
           this.mPaint.setStyle(Paint.Style.STROKE);
           this.mPaint.setColor(this.mInitColor);
           this.mPaint.setStrokeWidth(3.0f);
           this.mPath.reset();
           this.mPath.moveTo(0.0f, this.mGridWidth * 4.0f);
           this.mPath.lineTo(this.mSGridWidth * 2.0f, this.mGridWidth * 4.0f);
           this.mPath.lineTo(this.mSGridWidth * 2.0f, this.mGridWidth * 2.0f);
           Path path = this.mPath;
           float f13 = this.mGridWidth;
           path.lineTo((this.mSGridWidth * 2.0f) + f13, f13 * 2.0f);
           Path path2 = this.mPath;
           float f14 = this.mGridWidth;
           path2.lineTo((this.mSGridWidth * 2.0f) + f14, f14 * 4.0f);
           Path path3 = this.mPath;
           float f15 = this.mGridWidth;
           path3.lineTo((this.mSGridWidth * 4.0f) + f15, f15 * 4.0f);
           canvas.drawPath(this.mPath, this.mPaint);
           this.mPaint.setStyle(Paint.Style.FILL);
           this.mPaint.setStrokeWidth(1.0f);
           this.mPaint.setTextSize(getResources().getDisplayMetrics().density * 12.0f);
           String string = this.context.getString(R.string.ecg_header_title);
           float f16 = this.mGridWidth;
           canvas.drawText(string, f16 * 2.0f, ((float) this.mHeight) - f16, this.mPaint);
           StringBuilder sb = new StringBuilder();
           sb.append(this.context.getString(R.string.username));
           sb.append(":");
           String str2 = this.userName;
           if (str2 == null) {
               str2 = "13812345678";//Tools.readString("phonexxx", this.context, "13812345678");
           }
           sb.append(str2);
           sb.append("    ");
           sb.append(this.context.getString(R.string.sex));
           sb.append(":");
           sb.append(this.sex);
           sb.append("    ");
           sb.append(this.context.getString(R.string.age));
           sb.append(":");
           sb.append(this.age);
           sb.append("    ");
           sb.append(this.context.getString(R.string.heart));
           sb.append(":");
           String str3 = "--";
           sb.append(("0".equals(this.heart) || (str = this.heart) == null || "-1".equals(str)) ? str3 : this.heart);
           sb.append("    ");
           sb.append(this.context.getString(R.string.blood_pressure));
           sb.append(":");
           String str4 = this.bp;
           if (str4 != null && !str4.equals("0/0") && !this.bp.equals("0")) {
               str3 = this.bp;
           }
           sb.append(str3);
           String sb2 = sb.toString();
           float f17 = this.mGridWidth;
           canvas.drawText(sb2, f17 * 2.0f, f17, this.mPaint);
           //end bg

           int i;
           this.mPaint.setStyle(Paint.Style.STROKE);
           this.mPaint.setColor(this.mLineColor);
           this.mPaint.setStrokeWidth(this.context.getResources().getDisplayMetrics().density * 1.25f);
           float f18 = this.mGridWidth * 2.0f;
           float f19 = 0.0f;

           for (i = 1; i < this.plist.size(); i++) {
               if (i == 833 || i == 1666 || i == 2500 || i == 3333 || i == 4166) {
                   float multiple2 = ((float) ((i - 1) * 3)) * getMultiple(this.context);
                   f18 += this.mGridWidth * 4.0f;
                   f19 = multiple2;
               } else if (i != 5000) {
                   int i11 = i - 1;
                   canvas.drawLine(
                           ((this.mGridWidth * 2.0f) + (((float) (i11 * 3)) * getMultiple(this.context))) - f19,
                           (((this.mGridWidth * 4.0f) / 2.0f) - ((((float) (this.plist.get(i11).intValue() * 10)) * this.mSGridWidth) / 1000.0f)) + f18,
                           ((this.mGridWidth * 2.0f) + (((float) (i * 3)) * getMultiple(this.context))) - f19,
                           (((this.mGridWidth * 4.0f) / 2.0f) - ((((float) (this.plist.get(i).intValue() * 10)) * this.mSGridWidth) / 1000.0f)) + f18,
                           this.mPaint);
               } else {
                   return;
               }
           }
       }catch (Exception e){
           Log.e("e",e.getMessage());
        }

    }

    public static float getMultiple(Context context2) {
        return context2.getResources().getDisplayMetrics().density * 0.5f;
    }


    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        initBackground(canvas);

    }



}