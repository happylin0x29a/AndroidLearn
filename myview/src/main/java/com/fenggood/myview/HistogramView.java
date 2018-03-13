package com.fenggood.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by linfeng on 2018/3/9.
 * 直方图
 */

public class HistogramView extends View{
    private float mWidth,mHeight;
    private Paint mPaint;
    private float  scale=0.9f;//x,y轴占父布局的比例
    private float hScale=0.7f;//柱子占平均数据量的比例
    private float gap;//两个柱子之间的间隔
    private float size;//柱子的大小
    private float xLength; //x轴长度
    private float yLength; //Y轴长度
    private float data[]={10,20,30,40,50,80,90.9f,91.9f,20,66};
    private float maxValue=100;
    private String xTitle="X轴";
    private String yTitle="y轴";
    private String names[]={"林峰","林晓峰","林晓峰2","林晓峰33","林晓峰44","林晓峰55","h","i","j","k"};
    private int color=0xFF506E7A;
    public HistogramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(color);
        drawLine(canvas);
        drawHistogram(canvas);
    }
    private void initPaint(){
        mPaint=new Paint();
        mPaint.setStrokeWidth(5);
        mPaint.setColor(Color.WHITE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth=w;
        mHeight=h;
        xLength=w*(2*scale-1);
        yLength=h*(2*scale-1);
        size=(xLength/data.length)*hScale;
        gap=(xLength/data.length)*(1-hScale);
        invalidate();
    }
    //画x轴和Y轴
    private void drawLine(Canvas canvas){
        float x=mWidth*(1-scale);
        float y=mHeight*scale;
        canvas.drawLine(x,mHeight*(1-scale),x,y,mPaint);
        canvas.drawLine(x,y,mWidth*scale,y,mPaint);
    }

    //画柱子
    private void drawHistogram(Canvas canvas){
        Paint textPaint=new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(20f);
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float fontHeight = fontMetrics.descent - fontMetrics.ascent;//字体高度
        Log.i("fontHeight",fontHeight+"");
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.FILL);
        for (int i=0;i<data.length;i++){
            float percent=data[i]/maxValue;
            float start=gap*(i+1)+size*i+mWidth*(1-scale);//直方开始x坐标
            float end=(size+gap)*(i+1)+mWidth*(1-scale);//直方结束x坐标
            float dataTextSize=textPaint.measureText(data[i]+"");
            float nameTextSize=textPaint.measureText(names[i]+"");
            float textX=start+(end-start)/2;
            float y=scale*mHeight-(yLength*percent);
            RectF rectF=new RectF(start,y,end,scale*mHeight);//左下角和右上角坐标
            canvas.drawRoundRect(rectF,size*(1-scale),size*(1-scale),mPaint);
            canvas.drawText(data[i]+"",textX-dataTextSize/2,y-fontHeight/2,textPaint);
            canvas.drawText(names[i]+"",textX-nameTextSize/2,scale*mHeight+fontHeight,textPaint);
        }
        textPaint.setTextSize(30f);
        textPaint.setColor(Color.BLACK);
        float textSizeX=textPaint.measureText(xTitle);
        canvas.drawText(xTitle,mWidth*scale+textSizeX/2,scale*mHeight+fontHeight,textPaint);
        float textSizeY=textPaint.measureText(yTitle);
        canvas.drawText(yTitle,mWidth*(1-scale)-textSizeY*3/2,(1-scale)*mHeight+fontHeight,textPaint);
    }
    public void setXTitle(String s){
        this.xTitle=s;
    }
    public void setYTitle(String s){
        this.yTitle=s;
    }
    public void setBackground(int color){
        this.color=color;
    }
    public void setData(float[] data){
        this.data=data;
    }
}
