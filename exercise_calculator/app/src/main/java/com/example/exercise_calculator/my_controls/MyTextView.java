package com.example.exercise_calculator.my_controls;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/*自定义TextView
 *带边框
 */
public class MyTextView extends androidx.appcompat.widget.AppCompatTextView {
    private Paint paint = new Paint();

    public MyTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //  将边框设为黑色
        paint.setColor(android.graphics.Color.BLACK);
        //  画TextView的4个边
        canvas.drawLine(0, 0, this.getWidth(), 0, paint);//上边框
        canvas.drawLine(0, 0, 0, this.getHeight(), paint);//左边框
        canvas.drawLine(this.getWidth()-1, 0, this.getWidth()-1, this.getHeight(), paint);//右边框
        canvas.drawLine(0, this.getHeight()-1, this.getWidth(), this.getHeight()-1, paint);//下边框
    }
}