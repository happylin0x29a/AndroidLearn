package com.fenggood.myview;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by linfeng on 2018/3/7.
 */

public class CanvasControlView extends View {
    private Paint mPaint = new Paint();
    private int mWidth, mHeight;


    public CanvasControlView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        mTranslate(canvas);
//        scale(canvas);
//        dizzyScale(canvas);
//        mRotate(canvas);
//        mRotate1(canvas);
//        mSkew(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    private void initPaint() {
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(10f);
    }

    //位移 位移是基于当前坐标原点移动，而不是每次基于屏幕左上角的(0,0)点移动
    private void mTranslate(Canvas canvas) {
        // 省略了创建画笔的代码
        // 在坐标原点绘制一个黑色圆形
        mPaint.setColor(Color.BLACK);
        canvas.translate(200, 200);
        canvas.drawCircle(0, 0, 100, mPaint);
        // 移动坐标原点后
        mPaint.setColor(Color.BLUE);
        canvas.translate(200, 200);
        canvas.drawCircle(0, 0, 100, mPaint);
    }

    //缩放
//    public void scale (float sx, float sy)
//    public final void scale (float sx, float sy, float px, float py)
    private void scale(Canvas canvas) {
        //将坐标原点移动至屏幕中心
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.translate(mWidth / 2, mHeight / 2);
        RectF rectF = new RectF(0, -400, 400, 0);//矩形区域
        mPaint.setColor(Color.BLACK);
        canvas.drawRect(rectF, mPaint);
//        canvas.scale(0.5f,0.5f);//画布缩放
//        canvas.scale(0.5f,0.5f,200,0);// 画布缩放  <-- 缩放中心向右偏移了200个单位
//        canvas.scale(-0.5f,-0.5f);//当缩放比例为负数的时候会根据缩放中心轴进行翻转 缩放可以看做是先根据缩放中心(坐标原点)缩放到原来的0.5倍，然后分别按照x轴和y轴进行翻转
        canvas.scale(-0.5f, -0.5f, 200, 0);

        //调用两次缩放则 x轴实际缩放为0.5x0.5=0.25 y轴实际缩放为0.5x0.1=0.05

        mPaint.setColor(Color.BLUE);//绘制蓝色矩形
        canvas.drawRect(rectF, mPaint);
    }

    //无线缩放 晕眩效果
    private void dizzyScale(Canvas canvas) {
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.translate(mWidth / 2, mHeight / 2);
        RectF rectF = new RectF(-400, -400, 400, 400);
        for (int i = 0; i < 50; i++) {
            canvas.drawRect(rectF, mPaint);
            canvas.scale(0.9f, 0.9f);
        }
    }

    //旋转
//    public void rotate (float degrees)
//    public final void rotate (float degrees, float px, float py)
    private void mRotate(Canvas canvas) {
        // 将坐标系原点移动到画布正中心
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.translate(mWidth / 2, mHeight / 2);
        RectF rectF = new RectF(0, -400, 400, 0);
        mPaint.setColor(Color.BLACK);//绘制黑色矩形
        canvas.drawRect(rectF, mPaint);
        mPaint.setColor(Color.BLUE);
//        canvas.rotate(6);//顺时针旋转x度
        canvas.rotate(180, 200, 0);//旋转中心 往右平移200px
        canvas.drawRect(rectF, mPaint);
    }

    private void mRotate1(Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.translate(mWidth / 2, mHeight / 2);
        mPaint.setColor(Color.BLACK);
        canvas.drawCircle(0, 0, 400, mPaint);
        canvas.drawCircle(0, 0, 500, mPaint);
        for (int i = 0; i < 60; i++) {
            canvas.drawLine(400, 0, 500, 0, mPaint);
            canvas.rotate(6);
        }
    }

    //错切 错切是特殊类型的线性变换。
    //public void skew (float sx, float sy)
    //        float sx:将画布在x方向上倾斜相应的角度，sx倾斜角度的tan值，
//        float sy:将画布在y轴方向上倾斜相应的角度，sy为倾斜角度的tan值.
    public void mSkew(Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.translate(mWidth / 2, mHeight / 2);
        mPaint.setColor(Color.BLACK);
        RectF rectF=new RectF(0,0,200,200);
        canvas.drawRect(rectF,mPaint);
        canvas.skew(1,0);// 水平错切 <- 45度 错切也是可叠加的，不过请注意，调用次序不同绘制结果也会不同
//        canvas.skew(0,1);//垂直错切45度
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(rectF,mPaint);
    }
}
