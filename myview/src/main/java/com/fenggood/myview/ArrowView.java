package com.fenggood.myview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by linfeng on 2018/3/12.
 */

public class ArrowView extends View {
    private float currentValue = 0; //用于记录当前的位置，取值范围[0,1]映射Path的整个长度
    private float[] pos; //当前点的实际位置
    private float[] tan;//当前点的tangent值,用于计算图片所需要旋转的角度
    private Bitmap mBitmap;//箭头图片
    private Matrix mMatrix;//矩阵，用于对图片进行一些操作
    private int mWidth, mHeight;
    private Paint mPaint;
    public ArrowView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        pos = new float[2];
        tan = new float[2];
        mPaint=new Paint();
        mPaint.setStyle(Paint.Style.STROKE);

        try {
            InputStream io = context.getAssets().open("jiantou.jpg");
            mBitmap = BitmapFactory.decodeStream(io);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mMatrix = new Matrix();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawArrow(canvas);
    }

    private void drawArrow(Canvas canvas) {
        canvas.translate(mWidth / 2, mHeight / 2);//平移坐标系
        Path path = new Path();
        path.addCircle(0, 0, 200, Path.Direction.CW);
        PathMeasure measure = new PathMeasure(path, false);
        currentValue += 0.05;
        if (currentValue >= 1) {
            currentValue = 0;
        }
        measure.getPosTan(measure.getLength()*currentValue,pos,tan);//获取当前的位置的坐标以及趋势
        mMatrix.reset();
        float degress=(float)(Math.atan2(tan[1],tan[0])*180/Math.PI);//计算图片旋转角度
        mMatrix.postRotate(degress,mBitmap.getWidth()/2,mBitmap.getHeight()/2);//旋转图片
        mMatrix.postTranslate(pos[0]-mBitmap.getWidth()/2,pos[1]-mBitmap.getHeight()/2);//将图片绘制中心调整到与当前点重合
        canvas.drawPath(path,mPaint);//绘制Path
        canvas.drawBitmap(mBitmap,mMatrix,mPaint);//绘制箭头
        invalidate();
    }
}

