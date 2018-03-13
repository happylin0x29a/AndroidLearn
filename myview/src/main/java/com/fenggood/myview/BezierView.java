package com.fenggood.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by linfeng on 2018/3/9.
 */

public class BezierView extends View {
    private Paint mPaint;
    private int mWidth,mHeight;
    private PointF control,start,end;
    public BezierView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }
    private void initPaint(){
        mPaint=new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(60);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.translate(mWidth,mHeight);
//        drawBezier(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth=w/2;
        mHeight=h/2;
        Log.i("wid","w:"+mWidth+",h:"+mHeight);
        control=new PointF(mWidth,mHeight-200);
        start=new PointF(mWidth-200,mHeight);
        end=new PointF(mWidth+200,mHeight);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        control.x=event.getX();
//        control.y=event.getY();
//        Log.i("Control","x"+control.x+",y:"+control.y);
//        invalidate();//每次点击重绘
//        return true;
//    }
    private void drawBezier(Canvas canvas){
        //绘制数据点和控制点
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(20);
        canvas.drawPoint(start.x,start.y,mPaint);
        canvas.drawPoint(end.x,end.y,mPaint);
        canvas.drawPoint(control.x,control.y,mPaint);
        //绘制连接线
        Path path=new Path();
        mPaint.setStrokeWidth(4);
        canvas.drawLine(start.x,start.y,control.x,control.y,mPaint);
        canvas.drawLine(end.x,end.y,control.x,control.y,mPaint);
        //绘制曲线
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(8);
        path.moveTo(start.x,start.y);
        path.quadTo(control.x,control.y,end.x,end.y);
        canvas.drawPath(path,mPaint);
    }
}
