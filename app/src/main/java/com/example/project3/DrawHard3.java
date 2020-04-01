package com.example.project3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class DrawHard3 extends View {

    Paint paint = new Paint();


    public DrawHard3(Context context){
        super(context);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(200);
    }

    public DrawHard3(Context context, AttributeSet attrs){
        super(context, attrs);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(200);
    }

    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        canvas.drawLine(0, 1000, 500, 1000, paint);
        canvas.drawLine(400, 1000, 400, 500, paint);
        canvas.drawLine(300, 500, 975, 500, paint);
        canvas.drawLine(900, 600, 900, 0, paint);
    }
}