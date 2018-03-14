package com.fenggood.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by linfeng on 2018/3/13.
 */

public class LineView extends View {
    private Paint blackPaint,linePaint,dashPaint,textPaint;
    private float originX,originY,xLength,yLength;
    private float originScale=0.85f;//原点占大小
    private float arrow=20.0f;//箭头大小
    private float maxValue=100;//最大的值
    private float dashLineNumbers=10;//虚线+x轴的条数
    private float dashGap=maxValue/dashLineNumbers;//虚线间隔
    private Map<String,float[]> dataMap=new HashMap<>();
    private Map<String,Integer> colorMap=new HashMap<>();
    private float textSize=30f;
    private String[] xValue;

    public LineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawXY(canvas);
        drawDashLine(canvas);
        drawMultiLines(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        originX=(1-originScale)*w;
        originY=originScale*h;
        yLength=originY-(1-originScale)*h;
        xLength=originScale*w-originX;
        arrow=Math.min(xLength,yLength)*0.03f;
        dashGap=yLength/dashLineNumbers;
        textSize=Math.min(xLength,yLength)*0.04f;
        invalidate();
    }
    private void initPaint(){
        blackPaint=new Paint();
        linePaint=new Paint();
        dashPaint=new Paint();
        textPaint=new Paint();
        blackPaint.setStyle(Paint.Style.STROKE);
        blackPaint.setColor(Color.BLACK);
        blackPaint.setStrokeWidth(3f);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(10f);
        dashPaint.setStyle(Paint.Style.STROKE);
        dashPaint.setStrokeWidth(5f);
        dashPaint.setColor(Color.BLACK);
        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setTextSize(textSize);
        textPaint.setStrokeWidth(2.0f);
    }
    //绘制XY轴
    private void drawXY(Canvas canvas){
        //画X轴
        canvas.drawLine(originX,originY,originX,originY-yLength,blackPaint);
        //画Y轴
        canvas.drawLine(originX,originY,originX+xLength,originY,blackPaint);
        //画x箭头
        canvas.drawLine(originX+xLength,originY,originX+xLength-arrow,originY+arrow,blackPaint);
        canvas.drawLine(originX+xLength,originY,originX+xLength-arrow,originY-arrow,blackPaint);
        //画y箭头
        canvas.drawLine(originX,originY-yLength,originX-arrow,originY-yLength+arrow,blackPaint);
        canvas.drawLine(originX,originY-yLength,originX+arrow,originY-yLength+arrow,blackPaint);
    }
    //画虚线
    private void drawDashLine(Canvas canvas){
        PathEffect effect=new DashPathEffect(new float[]{5,15},0);
        dashPaint.setPathEffect(effect);
        Path path=new Path();
        float singleValue=maxValue/dashLineNumbers;
        for (int i=0;i<dashLineNumbers;i++){
            float j=i+1;
            if(j!=dashLineNumbers){
                path.moveTo(originX,originY-dashGap*j);
                path.lineTo(originX+xLength,originY-dashGap*j);
                canvas.drawPath(path,dashPaint);
            }
            //绘制Y轴数据
            String s=singleValue*i+"";
            float textSize=textPaint.measureText(s);
            canvas.drawText(s,originX-textSize*1.3f,originY-dashGap*i,textPaint);
        }
    }
    //绘制数据
    private void drawValue(Canvas canvas,float[] data,int color,String dataType,int index){
        float nameGap=xLength/(data.length+1);
        Path path=new Path();
        for (int i=0;i<data.length;i++){
            String sName=xValue[i]+"";
            float size=textPaint.measureText(sName);
            //绘制X轴Text
            canvas.drawText(sName,originX+nameGap*(i+1)-size/2,originY+textSize*1.1f,textPaint);
            float percent=data[i]/maxValue;
            float valueY=originY-percent*yLength;
            if(i==0){
                path.moveTo(originX+nameGap*(i+1),valueY);
            }else{
                path.lineTo(originX+nameGap*(i+1),valueY);
            }
            path.addCircle(originX+nameGap*(i+1),valueY,5, Path.Direction.CW);
        }
        linePaint.setColor(color);
        float xDesc=originX+xLength+originX*0.6f;
        float yDesc=(originY-yLength)*(1.0f/(float)dataMap.size());
        canvas.drawLine(originX+xLength,yDesc*index,xDesc,yDesc*index,linePaint);
        canvas.drawText(dataType,xDesc+textSize*0.1f,yDesc*index,textPaint);
        canvas.drawPath(path,linePaint);
    }
    
    private void drawMultiLines(Canvas canvas){
        int index=0;
        for (String key:dataMap.keySet()) {
            float[] lineDatas=dataMap.get(key);
            int color=colorMap.get(key);
            index+=1;
            drawValue(canvas,lineDatas,color,key,index);
        }
    }
    public void setMaxValue(float maxValue){
        this.maxValue=maxValue;
    }
    public void setDashLineNumbers(int numbers){
        this.dashLineNumbers=numbers;
    }
    public void setXNames(String[] xValue){
        this.xValue=xValue;
    }
    public void addLine(String dataType,float[] data,int color){
        dataMap.put(dataType,data);
        colorMap.put(dataType,color);
        invalidate();
    }
    public void moveLine(String dataType){
        dataMap.remove(dataType);
        colorMap.remove(dataType);
        invalidate();
    }
}
