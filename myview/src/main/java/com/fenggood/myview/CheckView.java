package com.fenggood.myview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;

import android.os.Handler;

/**
 * Created by linfeng on 2018/3/7.
 */

public class CheckView extends View {
    private Context context;
    private Paint mPaint = new Paint();
    private int mWidth, mHeight;
    private static final int ANIM_NULL = 0; //动画状态-没有
    private static final int ANIM_CHECK = 1; //动画状态-开启
    private static final int ANIM_UNCHECK = 2; //动画状态-结束
    private Handler mHandler;
    private int animCurrentPage = -1; //当前页码
    private int animMaxPage = 13; //总页数
    private int animDuration = 500;//动画时长
    private int animState = ANIM_NULL; //动画状态
    private boolean isCheck = false; //是否被选中状态
    private Bitmap mBitmap;

    public CheckView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        getBitMap();
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);
        canvas.drawCircle(0, 0, 240, mPaint);
        //得到图像边长
        int sideLength = mBitmap.getHeight();
        //得到图像选区和实际绘制位置
        // 指定图片绘制区域 显示区域
        Rect src = new Rect(sideLength * animCurrentPage, 0, sideLength * (animCurrentPage + 1), sideLength);
        // 指定图片在屏幕上显示的区域
        Rect dst = new Rect(-200, -200, 200, 200);
//        canvas.drawBitmap(mBitmap, new Matrix(), mPaint);
        canvas.drawBitmap(mBitmap, src, dst, null);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    public void check() {
        if (animState != ANIM_NULL || isCheck) return;
        animState = ANIM_CHECK;
        animCurrentPage = 0;
        mHandler.sendEmptyMessageDelayed(0, animDuration / animMaxPage);
        isCheck = true;
    }

    public void unCheck() {
        if (animState != ANIM_NULL || (!isCheck)) return;
        animState = ANIM_UNCHECK;
        animCurrentPage = animMaxPage - 2;
        mHandler.sendEmptyMessageDelayed(0, animDuration / animMaxPage);
        isCheck = false;
    }

    //设置动画时长
    public void setAnimDuration(int animDuration) {
        if (animDuration <= 0) return;
        this.animDuration = animDuration;
    }

    //设置背景颜色
    public void setBackground(int color) {
        mPaint.setColor(color);
    }

    private void getBitMap() {
        try {
            InputStream is = context.getAssets().open("Checkmark.png");
            mBitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            Log.e("NO Check.png", "no");
        }
    }

    private void init() {
        mPaint.setColor(0xffFF5317);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);//抗锯齿 相当于打马赛克
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (animCurrentPage < animMaxPage-1 && animCurrentPage >= 0) {
                    invalidate();
                    if (animState == ANIM_NULL) return;
                    if (animState == ANIM_CHECK) {
                        animCurrentPage++;
                    } else if (animState == ANIM_UNCHECK) {
                        animCurrentPage--;
                    }
                    this.sendEmptyMessageDelayed(0, animDuration / animMaxPage);
                    Log.e("AAA", "Count=" + animCurrentPage);
                } else {
                    if (isCheck) {
                        animCurrentPage = animMaxPage - 1;
                    } else {
                        animCurrentPage = -1;
                    }
//                    invalidate();
                    animState = ANIM_NULL;
                }
            }
        };
    }
}
