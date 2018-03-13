package com.fenggood.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by linfeng on 2018/3/8.
 * 蜘蛛网雷达图
 */

public class SpiderRadarView extends View {
    private int CenterX, CenterY;
    private Paint mPaint = new Paint();
    private float angle = 60f; //每个点角的度数 60度 6边型
    private float radius;//网格最大半径
    private String[] titles = {"攻击", "防御", "魔法", "仙术", "护体", "物理"};
    private float[] data = {100, 60, 60, 60, 100, 50};//各维度分值;
    private int count = data.length; //数据个数
    private float maxValue = 100; //数据最大值
//    private Paint mainPaint;//雷达区画笔
//    private Paint valuePaint;//数据区画笔
//    private Paint textPaint;//文本画笔

    public SpiderRadarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(CenterX, CenterY);
//        drawPolygon(canvas);
//        drawLines(canvas);
//        drawText(canvas);
//        drawRegion(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        CenterX = w / 2;
        CenterY = h / 2;
        radius = Math.min(h, w) / 2 * 0.9f;
        postInvalidate();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    private void initPaint() {
        mPaint.setColor(0xFFaaaaaa);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5f);
    }

    //绘制6边型
    private void drawPolygon(Canvas canvas) {
        Path path = new Path();
        float r = radius / (count - 1);//r是蜘蛛丝之间的距离
        for (int i = 1; i < count; i++) { //中心点不用绘制
            float curentR = r * i;
            path.reset();
            for (int j = 0; j < 6; j++) {
                if (j == 0) {
                    path.moveTo(curentR, 0);
                } else {
                    double curAngle = Math.toRadians(angle * j);
                    float x = (float) (curentR * Math.cos(curAngle));
                    float y = -(float) (curentR * Math.sin(curAngle));
                    path.lineTo(x, y);
                }
            }
            path.close();
            canvas.drawPath(path, mPaint);
        }
    }

    //绘制从中心到末端的直线
    private void drawLines(Canvas canvas) {
        Path path = new Path();
        for (int i = 0; i < 6; i++) {
            double curAngle = Math.toRadians(angle * i);
            path.moveTo(0, 0);
            float x = (float) (Math.cos(curAngle) * radius);
            float y = -(float) (Math.sin(curAngle) * radius);
            path.lineTo(x, y);
            canvas.drawPath(path, mPaint);
        }
    }

    private void drawText(Canvas canvas) {
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        float fontHeight = fontMetrics.descent - fontMetrics.ascent;//字体高度
        mPaint.setTextSize(50);
        mPaint.setColor(Color.BLACK);
        for (int i = 0; i < 6; i++) {
            double curAngle = Math.toRadians(30+(angle*i));
            float x = (float) (Math.cos(curAngle) * (radius + fontHeight/2));
            float y = -(float) (Math.sin(curAngle) * (radius + fontHeight/2));
            curAngle=curAngle-Math.toRadians(30);
            float dis = mPaint.measureText(titles[i]);
            if (curAngle > 0 && curAngle < Math.PI*3/2) {
                Log.i("左移",dis+"");
                x = x - dis/2;
            }
//            if(curAngle >Math.PI && curAngle <Math.PI*2){
//                y=y+fontHeight/2;
//            }
            canvas.drawText(titles[i], x, y, mPaint);
            Log.i(titles[i]+":","x:"+x+",y:"+y);
            Log.i("dis:",dis+"");
            Log.i("curAngle",curAngle+"");
        }
    }

    private void drawRegion(Canvas canvas){
        mPaint.setColor(0xFF000055);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setAlpha(255);
        Path path=new Path();
        for(int i=0;i<count;i++){
            double curAngle = Math.toRadians(angle * i);
            double percent=data[i]/maxValue;
            float x= (float) (radius*Math.cos(curAngle)*percent);
            float y= -(float) (radius*Math.sin(curAngle)*percent);
            if(i==0){
                path.moveTo(x,0);
            }else{
                path.lineTo(x,y);
            }
            canvas.drawCircle(x,y,10,mPaint);
        }
        mPaint.setStyle(Paint.Style.STROKE);
        path.close();
        canvas.drawPath(path,mPaint);
        mPaint.setAlpha(127);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawPath(path,mPaint);
    }

}
