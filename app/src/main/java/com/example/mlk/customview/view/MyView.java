package com.example.mlk.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class MyView extends View {

    private Paint paint = new Paint();
    private int num = 0;

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //圆·的·颜色
        paint.setColor(Color.BLUE);
        //抗锯齿设定
        paint.setAntiAlias(true);
        //圆的位置判断、半径
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 4, paint);


        //画笔颜色不能重复，不然看不出来
        paint.setColor(Color.RED);
        //字体大小
        paint.setTextSize(50);
        //文字不像圆，需要微调
        canvas.drawText(num + "", getWidth() / 2 - 15, getHeight() / 2 + 25, paint);
    }

    private GestureDetector gestureDetector = new GestureDetector(this.getContext(), new GestureDetector.OnGestureListener() {
        @Override
        public boolean onDown(MotionEvent motionEvent) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent motionEvent) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent motionEvent) {
            //实现自加
            num++;
            //重新调用上面onDraw方法
            invalidate();
        }

        @Override
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            return false;
        }
    });

    //点击事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        if(event.getAction()!=MotionEvent.ACTION_DOWN&&event.getAction()!=MotionEvent.ACTION_MOVE){
//            //实现自加
//            num++;
//            //重新调用上面onDraw方法
//            invalidate();
//        }
        return gestureDetector.onTouchEvent(event);
//        return super.onTouchEvent(event);
    }
}