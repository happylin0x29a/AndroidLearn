package com.fenggood.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by linfeng on 2018/3/8.
 * 根据路径绘制文本和剪裁画布
 * Path封装了由直线和曲线(二次，三次贝塞尔曲线)构成的几何路径。
 * 你能用Canvas中的drawPath来把这条路径画出来(同样支持Paint的不同绘制模式)，也可以用于剪裁画布和根据路径绘制文字。
 * 我们有时会用Path来描述一个图像的轮廓，所以也会称为轮廓线(轮廓线仅是Path的一种使用方法，两者并不等价)
 */

public class PathView extends View {
    private Paint mPaint=new Paint();
    private int mWidth,mHeight;
    public PathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth/2,mHeight/2);
//        mLineTo(canvas);
//        mMoveTo(canvas);
//        mSetLastPoint(canvas);
//        mClose(canvas);
//        addXxx(canvas);
//        addPath(canvas);
//        mAddArc(canvas);
//        mArcTo(canvas);
//        mSet(canvas);
//        mOffset(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth=w;
        mHeight=h;
    }

    private void initPaint(){
        mPaint.setColor(Color.BLACK);           // 画笔颜色 - 黑色
        mPaint.setStyle(Paint.Style.STROKE);    // 填充模式 - 描边
        mPaint.setStrokeWidth(10);              // 边框宽度 - 10
    }
    private void mLineTo(Canvas canvas){
        Path path=new Path();
        path.lineTo(200,200);//原点到（200,200）的直线
        path.lineTo(200,0);//（200,200）到（200,0）的直线
        canvas.drawPath(path,mPaint);
    }
    private void mMoveTo(Canvas canvas){
        Path path=new Path();
        path.lineTo(200,200);
        path.moveTo(200,100);//移动下一次操作的起点位置
        path.lineTo(200,0);
        canvas.drawPath(path,mPaint);
    }
    private void mSetLastPoint(Canvas canvas){
        Path path=new Path();
        path.lineTo(200,200);
        path.setLastPoint(200,100);//设置之前操作的最后一个点位置  之前线就到这个点
        path.lineTo(200,0);
        canvas.drawPath(path,mPaint);
    }
    //close方法用于连接当前最后一个点和最初的一个点(如果两个点不重合的话)，最终形成一个封闭的图形。
    private void mClose(Canvas canvas){
        Path path=new Path();
        path.lineTo(200,200);
        path.lineTo(200,0);
        path.close();
        canvas.drawPath(path,mPaint);
    }
    //添加各种形状的图形
    private void addXxx(Canvas canvas){
        //public void addRoundRect (RectF rect, float rx, float ry, Path.Direction dir)
        //Path.Direction dir 控制方向趋势 一个枚举类型 CW：顺时针 CCW：逆时针  在添加图形时确定闭合顺序(各个点的记录顺序)
        // 对图形的渲染结果有影响(是判断图形渲染的重要条件)
        Path path=new Path();
        path.addRect(-200,-200,200,200, Path.Direction.CCW);;//逆时针和顺时针一样 没有特殊操作情况下
        //隐身技能
        path.setLastPoint(100,0);//moveto 到第一个点然后依次lineto 到最后一个点的时候改变了 然后close方法 首尾连接 猜测是这样
        canvas.drawPath(path,mPaint);
    }
    //将两个path合在一起
    private void addPath(Canvas canvas){
        canvas.scale(1,-1);//翻转y坐标轴
        Path path=new Path();
        Path src=new Path();
        path.addRect(-200,-200,200,200,Path.Direction.CW);
        src.addCircle(0,0,100,Path.Direction.CW);
        path.addPath(src,0,200);//dx,dy 代表添加的path其实坐标点
        canvas.drawPath(path,mPaint);
    }
    //添加圆弧
    private void mAddArc(Canvas canvas){
        canvas.scale(1,-1);//翻转y坐标轴
        Path path=new Path();
        path.lineTo(100,100);
        RectF oval=new RectF(0,0,300,300);
        path.addArc(oval,0,270);
        //path.arcTo(oval,0,270,true);代表不连接
        canvas.drawPath(path,mPaint);
    }
    //添加圆弧 会连接
    private void mArcTo(Canvas canvas){
        canvas.scale(1,-1);
        Path path=new Path();
        path.lineTo(100,100);
        RectF oval=new RectF(0,0,300,300);
        path.arcTo(oval,0,270); //会连接
        //等价于path.arcTo(oval,0,270,false);会连接
        canvas.drawPath(path,mPaint);
    }
    //判断path是否包含内容
    private void isEmpty(){
        Path path = new Path();
        Log.e("1",path.isEmpty()+"");
        path.lineTo(100,100);
        Log.e("2",path.isEmpty()+"");
    }
    //判断path是否是矩形 isRect( Rect rect)判断path是否是一个矩形，如果是一个矩形的话，会将矩形的信息存放进参数rect中。
    //将新的path赋值到现有的path set
    private void mSet(Canvas canvas){
        canvas.scale(1,-1);
        Path path=new Path();
        path.addRect(-200,-200,200,200,Path.Direction.CW);
//        Path src=new Path();
//        src.addCircle(0,0,100,Path.Direction.CW);
        canvas.drawPath(path,mPaint);
        canvas.drawPoint(0,0,mPaint);
    }
    //offset 平移
    private void mOffset(Canvas canvas){
        canvas.scale(1,-1);
        Path path=new Path();
        path.addCircle(0,0,100,Path.Direction.CW);
        Path dst=new Path();
        dst.addRect(-200,-200,200,200,Path.Direction.CW);
        path.offset(300,0,dst);//平移会使path替换成dst
        canvas.drawPath(path,mPaint);
        mPaint.setColor(Color.BLUE);
        canvas.drawPath(dst,mPaint);
    }
}
