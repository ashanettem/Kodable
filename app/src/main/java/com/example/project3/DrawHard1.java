package com.example.project3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class DrawHard1 extends View {

    Paint paint = new Paint();


    public DrawHard1(Context context){
        super(context);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(200);
    }

    public DrawHard1(Context context, AttributeSet attrs){
        super(context, attrs);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(200);
    }

    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        canvas.drawLine(100, 800, 800, 800, paint);
        canvas.drawLine(700, 100, 700, 700, paint);
        canvas.drawLine(800, 100, 0, 100, paint);
        canvas.drawLine(200, 800, 200, 2000, paint);
    }
}