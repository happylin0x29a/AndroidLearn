package com.fenggood.myview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by linfeng on 2018/3/24.
 */

public class MyRectView extends View {
    public MyRectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray=context.obtainStyledAttributes(attrs,R.styleable.MyRectView);
        int color=typedArray.getColor(R.styleable.MyRectView_rect_color,0xffff0000);
        setBackgroundColor(color);
        typedArray.recycle();
    }
}
