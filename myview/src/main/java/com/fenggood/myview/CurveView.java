package com.fenggood.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by linfeng on 2018/3/14.
 */

public class CurveView extends View {
    private float originX, originY, xLength, yLength;
    private Paint tablePaint, curvePaint, solidPaint, textPaint;
    private float originScale = 0.85f;//原点占大小
    private float arrow = 20.0f;//箭头大小
    private float maxValue = 100;//最大的值
    private float solidLineNumbers = 10;//表格
    private float solidLineGap;//表间隔
    private Map<String, float[]> dataMap = new HashMap<>(); //数据和数据类型
    private Map<String, Integer> colorMap = new HashMap<>();//数据类型和颜色
    private float textSize = 30f;
    private ArrayList<String> xValues=new ArrayList<>();
    public CurveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        originX = (1 - originScale) * w;
        originY = originScale * h;
        yLength = originY - (1 - originScale) * h;
        xLength = originScale * w - originX;
        arrow = Math.min(xLength, yLength) * 0.03f;
        solidLineGap = yLength / solidLineNumbers;
        textSize = Math.min(xLength, yLength) * 0.04f;
        invalidate();
    }

    private void initPaint() {
        tablePaint = new Paint();
        curvePaint = new Paint();
        solidPaint = new Paint();
        textPaint = new Paint();
        tablePaint.setStyle(Paint.Style.STROKE);
        tablePaint.setColor(Color.GRAY);
        tablePaint.setStrokeWidth(3f);
        curvePaint.setStyle(Paint.Style.STROKE);
        curvePaint.setStrokeWidth(3f);
        curvePaint.setDither(true);
        curvePaint.setAntiAlias(true);
        PathEffect pathEffect=new CornerPathEffect(25);
        curvePaint.setPathEffect(pathEffect);
        solidPaint.setStyle(Paint.Style.STROKE);
        solidPaint.setStrokeWidth(2f);
        solidPaint.setColor(Color.BLACK);
        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setTextSize(textSize);
        textPaint.setStrokeWidth(2.0f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawXY(canvas);
        drawTable(canvas);
        drawMultiLines(canvas);
    }

    //绘制XY轴
    private void drawXY(Canvas canvas) {
        float arrowMore=0f;
        //画Y轴
        canvas.drawLine(originX, originY, originX, originY - yLength-arrowMore, solidPaint);
        //画X轴
        canvas.drawLine(originX, originY, originX + xLength, originY, solidPaint);
        //画x箭头
        canvas.drawLine(originX + xLength +arrowMore, originY, originX + xLength - arrow, originY + arrow, solidPaint);
        canvas.drawLine(originX + xLength +arrowMore, originY, originX + xLength - arrow, originY - arrow, solidPaint);
        //画y箭头
        canvas.drawLine(originX, originY - yLength -arrowMore, originX - arrow, originY - yLength + arrow, solidPaint);
        canvas.drawLine(originX, originY - yLength-arrowMore, originX + arrow, originY - yLength + arrow, solidPaint);
    }

    //绘制表格以及轴上数据
    private void drawTable(Canvas canvas) {
        float singleValue=maxValue/solidLineNumbers;
        if (xValues.size()==0) return;
        for (int i = 0; i < solidLineNumbers; i++) {
            float j = i + 1;
            if (j != solidLineNumbers) {
                canvas.drawLine(originX, originY - solidLineGap * (i + 1), originX + xLength, originY - solidLineGap * (i + 1), solidPaint);
            }
            //绘制Y轴数据
            String s=singleValue*i+"";
            float textSize=textPaint.measureText(s);
            canvas.drawText(s,originX-textSize*1.3f,originY-solidLineGap*i,textPaint);
        }
        //绘制x轴数据
        for(int i=0;i<xValues.size();i++){
            float size=textPaint.measureText(xValues.get(i));
            canvas.drawText(xValues.get(i),originX+solidLineGap*(i+1)-size/2,originY+textSize*1.1f,textPaint);
        }
        //绘制横向
        for(int i=0;solidLineGap*(i+1)<xLength;i++){
            canvas.drawLine(originX+solidLineGap*(i+1), originY, originX+solidLineGap*(i+1), originY-yLength, solidPaint);
        }
    }
    //绘制曲线
    private void drawCurve(Canvas canvas,float[] data,int color,String dataType,int index){
        Path path=new Path();
        curvePaint.setColor(color);
        for (int i=0;i<data.length;i++){
            float percent=data[i]/maxValue;
            float valueY=originY-percent*yLength;
            if(i==0){
                path.moveTo(originX+solidLineGap*(i+1),valueY);
            }else{
                path.lineTo(originX+solidLineGap*(i+1),valueY);
            }
//            String dataText=data[i]+"";
//            canvas.drawText(dataText,originX+solidLineGap*(i+1),valueY,textPaint);
            canvas.drawCircle(originX+solidLineGap*(i+1),valueY,5,curvePaint);
        }
        float xDesc=originX+xLength+originX*0.6f;
        float yDesc=(originY-yLength)*(1.0f/(float)dataMap.size());
        canvas.drawLine(originX+xLength,yDesc*index,xDesc,yDesc*index,curvePaint);
        canvas.drawText(dataType,xDesc+textSize*0.1f,yDesc*index,textPaint);
        canvas.drawPath(path,curvePaint);
    }



    //增加实线
    public void addLine(String dataType,float[] data,int color){
        dataMap.put(dataType,data);
        colorMap.put(dataType,color);
        invalidate();
    }
    public void addXValue(String xValue){
        xValues.add(xValue);
        invalidate();
    }
    private void drawMultiLines(Canvas canvas){
        int index=0;
        for (String key:dataMap.keySet()) {
            float[] lineDatas=dataMap.get(key);
            int color=colorMap.get(key);
            index+=1;
            drawCurve(canvas,lineDatas,color,key,index);
        }
    }
}
