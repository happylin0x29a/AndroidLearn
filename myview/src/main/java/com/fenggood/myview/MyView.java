package com.fenggood.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by linfeng on 2018/3/7.
 */

public class MyView extends View {
    private static final String TAG = MyView.class.getSimpleName();
    private Paint mPaint = new Paint();

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);//取出宽度的确切数值
        Log.i(TAG, "widthSize:" + widthSize);
        int widthmode = MeasureSpec.getMode(widthMeasureSpec);//取出宽度的测量模式
        Log.i(TAG, "widthmode:" + widthmode);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        Log.i(TAG, "heightSize:" + heightSize);
        int heightmode = MeasureSpec.getSize(heightMeasureSpec);
        Log.i(TAG, "heightmode:" + heightmode);
        setMeasuredDimension(widthSize, heightSize);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        drawRoundedRectangle(canvas);
//        mDrawOval(canvas);
//        mDrawCircle(canvas);
//        mDrawArc(canvas);
//        mPaintStyle(canvas);
    }


    private void initPaint() {
        mPaint.setColor(Color.RED);//设置画笔颜色
        mPaint.setStyle(Paint.Style.FILL);//设置画笔模式为填充
        mPaint.setStrokeWidth(10f);
        ;//设置画笔宽度为10px
    }

    //绘制点
    private void drawPoint(Canvas canvas) {
        canvas.drawPoint(10, 10, mPaint);//画一个点，坐标相对于父布局
//        canvas.drawColor(Color.BLUE);//画颜色布满整个view的设置的大小
        canvas.drawPoints(new float[]{500, 500,
                500, 600, 500, 700
        }, mPaint);
    }

    //绘制线
    private void drawLine(Canvas canvas) {
        canvas.drawLine(300, 300, 500, 600, mPaint);//在（300,300）和（500,600）之间绘制一条直线
        canvas.drawLines(new float[]{100, 200, 200, 200, 100, 300, 301, 300}, mPaint);// 绘制一组线 每四数字(两个点的坐标)确定一条线
    }

    //绘制矩形
    private void drawRect(Canvas canvas) {

        //第一种就是提供四个数值(矩形左上角和右下角两个点的坐标)来确定一个矩形进行绘制
        canvas.drawRect(100, 100, 800, 400, mPaint);
        //其余两种是先将矩形封装为Rect或RectF(实际上仍然是用两个坐标点来确定的矩形)，然后传递给Canvas绘制
        Rect rect = new Rect(700, 100, 800, 400);
        mPaint.setColor(Color.BLACK);
        canvas.drawRect(rect, mPaint);
        RectF rectF = new RectF(800, 100, 900, 400);
        mPaint.setColor(0xaaaaaaaa);
        canvas.drawRect(rectF, mPaint);
    }

    //绘制圆角矩形
    private void drawRoundedRectangle(Canvas canvas) {
        //第一种
        RectF rectF = new RectF(100, 100, 800, 400);
        canvas.drawRoundRect(rectF, 350, 300, mPaint);//这里圆角矩形的角实际上不是一个正圆的圆弧，
        // 而是椭圆的圆弧，这里的两个参数实际上是椭圆的两个半径
        mPaint.setColor(0xaaaaaaaa);
        canvas.drawRect(100, 100, 800, 400, mPaint);
        // 第二种
//        canvas.drawRoundRect(100, 100, 800, 400, 360, 300, mPaint);
        //当你让 rx大于350(宽度的一半)， ry大于150(高度的一半)
        // 圆角矩形变成了一个椭圆 drawRoundRect对大于该数值的参数进行了限制(修正)，凡是大于一半的参数均按照一半来处理
    }

    //绘制椭圆
    private void mDrawOval(Canvas canvas) {
        //第一种
        RectF rectF = new RectF(100, 100, 800, 400);
        canvas.drawOval(rectF, mPaint);
        //第二种
        // 第二种
//        canvas.drawOval(100,100,800,400,mPaint);
    }

    //绘制圆
    private void mDrawCircle(Canvas canvas) {
        canvas.drawCircle(500, 500, 400, mPaint);// 绘制一个圆心坐标在(500,500)，半径为400 的圆。
    }
    //绘制圆弧
    // 第一种
    //public void drawArc(@NonNull RectF oval, float startAngle, float sweepAngle, boolean useCenter, @NonNull Paint paint){}

    // 第二种
    //public void drawArc(float left, float top, float right, float bottom, float startAngle,
    //float sweepAngle, boolean useCenter, @NonNull Paint paint) {}
//    startAngle  // 开始角度
//    sweepAngle  // 扫过角度
//    useCenter   // 是否使用中心
    private void mDrawArc(Canvas canvas) {
        RectF rectF = new RectF(100, 100, 600, 600);
// 绘制背景矩形
        mPaint.setColor(Color.GRAY);
        canvas.drawRect(rectF, mPaint);
        mPaint.setColor(Color.BLUE);
        //绘制圆弧 不使用中心
        canvas.drawArc(rectF, 0, 90, false, mPaint);
        RectF rectF2 = new RectF(100, 700, 600, 1200);
// 绘制背景矩形
        mPaint.setColor(Color.GRAY);
        canvas.drawRect(rectF2, mPaint);
        //绘制圆弧 使用中心
        mPaint.setColor(Color.BLUE);
        canvas.drawArc(rectF2, 0, 90, true, mPaint);
    }

    //paint 的三种模式
//    STROKE                //描边
//            FILL                  //填充
//    FILL_AND_STROKE       //描边加填充
    private void mPaintStyle(Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);
//        RectF rectF=new RectF(100,100,300,300);
        canvas.drawCircle(200, 200, 100, mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(200, 500, 100, mPaint);

        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(200, 800, 100, mPaint);
    }
}
