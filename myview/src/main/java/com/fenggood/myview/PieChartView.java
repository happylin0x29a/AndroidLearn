package com.fenggood.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by linfeng on 2018/3/7.
 * 饼状图
 */

public class PieChartView extends View {
    private static final String TAG = PieChartView.class.getSimpleName();
    //颜色表（注意：此处定义颜色使用的是ARGB，带Alpha通道的，有透明度的）
    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};
    //饼状图初始绘制角度
    private float mStartAngle = 0;
    //数据
    private ArrayList<PieData> mData;
    //宽高
    private int mWidth, mHeight;
    //圆弧画笔
    private Paint mPaint;

    //线画笔
    private Paint linePaint;

    //描述画笔
    private Paint descPaint;
    //间隙角度
    private int gapAngle = 1;
    //画布颜色
    private int color = 0xFF506E7A;

    //弧度引出的第一段线的长度比例
    private float rLineScale = 0.1f;

    //半径占整个屏幕最小长度的比例
    private float rScale=0.7f;

    //touch 坐标
    float touchX,touchY,touchTanValue,touchAngle;

    public PieChartView(Context context) {
        this(context, null);
    }

    public PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }
    private void initPaint(){
        mPaint = new Paint();
        linePaint = new Paint();
        descPaint=new Paint();
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(5f);
        linePaint.setColor(Color.WHITE);
        linePaint.setColor(Color.GRAY);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);//抗锯齿 相当于打马赛克
        descPaint.setColor(Color.BLACK);
        descPaint.setTextSize(30f);
        descPaint.setStyle(Paint.Style.STROKE);
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        touchX=event.getX()-mWidth/2;
        touchY=event.getY()-mHeight/2;
        touchTanValue=touchY/touchX;
        touchAngle= (float) Math.atan(touchTanValue);//结果是多少PI
        Log.i("坐标","("+touchX+","+touchY+")");
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(color);
        drawNormalPie(canvas);
    }

    private void drawNormalPie(Canvas canvas) {
        if (mData == null) return;
        float currentStartAngle = mStartAngle + gapAngle;//当前起始角度
        canvas.translate(mWidth / 2, mHeight / 2);//将画布坐标原点移动到中心位置
        float r = (Math.min(mWidth, mHeight) / 2 * rScale);//饼状图半径
        float xLineEnd2=0f;//第二条线的末端X坐标
        RectF rectF = new RectF(-r, -r, r, r);
        Log.i("touchAngle:",""+touchAngle);
        if((touchX<0&&touchY>0)){
            //第二象限 第2象限的tan值为负 0-90 tan45=1
            touchAngle= (float) (Math.PI-Math.abs(touchAngle));
        }else if(touchX>0&&touchY<0){
            //第四象限 第4象限的tan值为负
            touchAngle= (float) (Math.abs(touchAngle)+Math.PI/2*3);
        }else if(touchX<0&&touchY<0){
            //第三象限
            touchAngle= (float) (touchAngle+Math.PI);
        }
        Log.i("touchAngle2:",""+touchAngle);
        for (int i = 0; i < mData.size(); i++) {
            PieData pie = mData.get(i);
            mPaint.setColor(pie.getColor());
            double rad = Math.toRadians(currentStartAngle + pie.getAngle() / 2);//各弧度的中间值单位多少分之PI
            float xRadCenter = (float) (r * Math.cos(rad));//各弧度中间值X坐标
            float yRadCenter = (float) (r * Math.sin(rad));//各弧度中间值Y坐标
            if((touchAngle>Math.toRadians(currentStartAngle))&&(touchAngle<Math.toRadians(currentStartAngle+pie.getAngle()))){
                float r1=r+r*rLineScale;
                RectF rectF1=new RectF(-r1, -r1, r1, r1);
                canvas.drawArc(rectF1,currentStartAngle, pie.getAngle(),true,mPaint);
                xRadCenter=(float) (r1 * Math.cos(rad));//延长线
                yRadCenter=(float) (r1 * Math.sin(rad));
            }else{
                canvas.drawArc(rectF, currentStartAngle, pie.getAngle(), true, mPaint);
            }
            //第一条线的末端点 X坐标
            float xLineEnd1=(float) (xRadCenter + r * rLineScale * Math.cos(rad));
            //第一条线的末端点 Y坐标
            float yLineEnd1=(float) (yRadCenter + r * rLineScale * Math.sin(rad));
            canvas.drawLine(xRadCenter, yRadCenter, xLineEnd1, yLineEnd1, linePaint);//绘制第一条线
            if ((xRadCenter > 0 && yRadCenter > 0) || (xRadCenter > 0 && yRadCenter < 0)) {
                //第一象限和第四象限 第二条线方向朝右
                 xLineEnd2=xLineEnd1+r*rLineScale;
                descPaint.setTextAlign(Paint.Align.LEFT);//字从左开始写
            } else {
                //第一象限和第四象限 第二条线方向朝左
                xLineEnd2=xLineEnd1-r*rLineScale;
                descPaint.setTextAlign(Paint.Align.RIGHT);//字从右开始写
            }
            canvas.drawLine(xLineEnd1,yLineEnd1,xLineEnd2,yLineEnd1,linePaint);
            canvas.drawText(pie.getName(),xLineEnd2,yLineEnd1,descPaint);
            currentStartAngle += pie.getAngle() + gapAngle;
        }
    }

    //设置起始角度
    public void setStartAngle(int mStartAngle) {
        this.mStartAngle = mStartAngle;
        invalidate();
    }

    //设置数据
    public void setData(ArrayList<PieData> mData) {
        this.mData = mData;
        initData(mData);
        invalidate();
    }

    private void initData(ArrayList<PieData> mData) {
        if (null == mData || mData.size() == 0) return;
        float sumValue = 0;
        for (int i = 0; i < mData.size(); i++) {
            PieData pie = mData.get(i);
            sumValue += pie.getValue(); //计算数值和
            int j = i % mColors.length;//设置颜色
            pie.setColor(mColors[j]);
        }
        for (int i = 0; i < mData.size(); i++) {
            PieData pie = mData.get(i);
            float percentage = pie.getValue() / sumValue;//百分比
            float angle = percentage * (360 - mData.size());//对应的角度
            pie.setPercentage(percentage);//记录百分比
            pie.setAngle(angle);//记录角度大小
            Log.i(TAG, "" + pie.getAngle());
        }
    }

    public static class PieData {
        //用户关心的数据
        private String name;//名字
        private float value;//数值
        private float percentage;//百分比
        //非用户关心数据
        private int color = 0; //颜色
        private float angle = 0;//角度

        public PieData(@NonNull String name, @NonNull float value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public float getValue() {
            return value;
        }

        public void setValue(float value) {
            this.value = value;
        }

        public float getPercentage() {
            return percentage;
        }

        public void setPercentage(float percentage) {
            this.percentage = percentage;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public float getAngle() {
            return angle;
        }

        public void setAngle(float angle) {
            this.angle = angle;
        }
    }
}
