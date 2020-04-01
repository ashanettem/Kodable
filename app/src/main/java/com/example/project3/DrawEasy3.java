package com.example.project3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class DrawEasy3 extends View {

    Paint paint = new Paint();


    public DrawEasy3(Context context){
        super(context);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(200);
    }

    public DrawEasy3(Context context, AttributeSet attrs){
        super(context, attrs);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(200);
    }

    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        canvas.drawLine(500, 100, 2000, 100, paint);
        canvas.drawLine(600, 600, 600, 100, paint);
        canvas.drawLine(0, 700, 700, 700, paint);
    }
}