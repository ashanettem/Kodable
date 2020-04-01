
package com.example.project3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class DrawEasy1 extends View {

    Paint paint = new Paint();
    Bitmap bitmap;


    public DrawEasy1(Context context){
        super(context);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(200);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.star);
    }

    public DrawEasy1(Context context, AttributeSet attrs){
        super(context, attrs);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(200);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.star);
    }

    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        canvas.drawLine(0, 300, 700, 300, paint);
        canvas.drawLine(800, 200, 800, 2000, paint);
    }
}