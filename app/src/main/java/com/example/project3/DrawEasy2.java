package com.example.project3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class DrawEasy2 extends View {

    Paint paint = new Paint();


    public DrawEasy2(Context context){
        super(context);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(200);
    }

    public DrawEasy2(Context context, AttributeSet attrs){
        super(context, attrs);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(200);
    }

    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        canvas.drawLine(0, 800, 800, 800, paint);
        canvas.drawLine(700, 0, 700, 700, paint);

    }
}