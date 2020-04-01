package com.example.project3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class DrawHard2 extends View {

    Paint paint = new Paint();


    public DrawHard2 (Context context){
        super(context);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(200);
    }

    public DrawHard2 (Context context, AttributeSet attrs){
        super(context, attrs);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(200);
    }

    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        canvas.drawLine(100, 300, 100, 800, paint);
        canvas.drawLine(0, 800, 800, 800, paint);
        canvas.drawLine(700, 800, 700, 200, paint);
        canvas.drawLine(600, 200, 1200, 200, paint);
    }
}