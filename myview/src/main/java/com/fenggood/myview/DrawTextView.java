package com.fenggood.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by linfeng on 2018/3/8.
 */

public class DrawTextView extends View {
    private Paint textPaint=new Paint();
    public DrawTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        mDrawText(canvas);
//        mDrawPosText(canvas);
    }
    private void initPaint(){
        textPaint.setColor(Color.BLACK);        // 设置颜色
        textPaint.setStyle(Paint.Style.FILL);   // 设置样式
        textPaint.setTextSize(50);              // 设置字体大小
    }
    private void mDrawText(Canvas canvas){
        //基线相当于坐标
        String s="ABCDEFFG";
        // 参数分别为 (文本 基线x 基线y 画笔)
//        canvas.drawText(s,200,500,textPaint);
        // 参数分别为 (字符串 开始截取位置 结束截取位置 基线x 基线y 画笔) 从0开始 前闭后开 包含start指定的下标，而不包含end指定的下标
        canvas.drawText(s,1,3,200,500,textPaint);
        //字符数组要绘制的内容
        char[] chars="ABCDEFG".toCharArray();
        // 参数为 (字符数组 起始坐标 截取长度 基线x 基线y 画笔)
        canvas.drawText(chars,1,3,200,600,textPaint); //结果是BCD
    }
    private void mDrawPosText(Canvas canvas){
        String s="ABCDE";
        //已过时，每个字符一个坐标
        canvas.drawPosText(s,new float[]{100,100,    // 第一个字符位置
                200,200,    // 第二个字符位置
                300,300,    // ...
                400,400,
                500,500},textPaint);
    }
}
