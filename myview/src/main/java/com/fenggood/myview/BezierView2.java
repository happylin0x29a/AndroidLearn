package com.fenggood.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by linfeng on 2018/3/9.
 */

public class BezierView2 extends View {
    private Paint mPaint;
    private int centerX,centerY;
    private PointF start,end,control1,control2;
    private int mode=1;

    public BezierView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }
    private void initPaint(){
        mPaint=new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(20f);
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX=w/2;
        centerY=h/2;
        start=new PointF(centerX-200,centerY);
        end=new PointF(centerX+200,centerY);
        control1=new PointF(centerX-200,centerY-200);
        control2=new PointF(centerX+200,centerY-200);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (mode){
            case 1:
                control1.x=event.getX();
                control1.y=event.getY();
                invalidate();
                break;
            case 2:
                control2.x=event.getX();
                control2.y=event.getY();
                invalidate();
                break;
        }
        return true;
    }

    public void setControl1(){
        mode=1;
    }

    public void setControl2(){
       mode=2;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制点
        canvas.drawPoint(start.x,start.y,mPaint);
        canvas.drawPoint(end.x,end.y,mPaint);
        canvas.drawPoint(control1.x,control1.y,mPaint);
        canvas.drawPoint(control2.x,control2.y,mPaint);
        //绘制线
        mPaint.setColor(Color.GRAY);
        canvas.drawLines(new float[]{start.x,start.y,
                control1.x,control1.y,
                control1.x,control1.y,
                control2.x,control2.y,
                control2.x,control2.y,
                end.x,end.y
        },mPaint);
        //绘制曲线
        mPaint.setColor(Color.RED);
        Path path=new Path();
        path.moveTo(start.x,start.y);
        path.cubicTo(control1.x,control2.y,control2.x,control2.y,end.x,end.y);
        canvas.drawPath(path,mPaint);
    }
}
