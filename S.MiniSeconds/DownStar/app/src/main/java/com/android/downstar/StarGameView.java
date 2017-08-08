package com.android.downstar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by Administrator on 2017-08-08.
 */

public class StarGameView extends View {

    private String text = null;
    private DisplayMetrics metrics;
    private int backgroundColor = Color.RED;
    private int backgroundStroke = Color.YELLOW;
    private String tempText;

    public DisplayMetrics getMetrics() {
        return metrics;
    }

    public void setMetrics(DisplayMetrics metrics) {
        this.metrics = metrics;
    }

    public StarGameView(Context context) {
        super(context);
        Log.w("CustomView","CustomView("+context+")");
    }

     /*
     *  리소스 xml 파일에서 정의하면 이 생성자가 사용된다.
     *  대부분 this 를 이용해 3번째 생성자로 넘기고 모든 처리를 3번째 생성자에서 한다.
     */

    public StarGameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    /*
         * xml 에서 넘어온 속성을 멤버변수로 셋팅하는 역할을 한다.
         */
    public StarGameView(Context context, AttributeSet attrs, int defStyle) {
        super(context,attrs,defStyle);

        this.text = attrs.getAttributeValue(null,"text");

        Log.w("CustomView","CustomView("+context+","+attrs+","+defStyle+"),text:"+text);
    }


    /*
     * xml 로 부터 모든 뷰를 inflate 를 끝내고 실행된다.
     * 대부분 이 함수에서는 각종 변수 초기화가 이루어 진다.
     * super 메소드에서는 아무것도 하지않기때문에 쓰지 않는다.
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        setClickable(true);
        Log.w("CustomView","onFinishInflate()");
    }

  /*
     * 넘어오는 파라메터는 부모뷰로부터 결정된 치수제한을 의미한다.
     * 또한 파라메터에는 bit 연산자를 사용해서 모드와 크기를 같이 담고있다.
     * 모드는 MeasureSpec.getMode(spec) 형태로 얻어오며 다음과 같은 3종류가 있다.
     *    MeasureSpec.AT_MOST : wrap_content (뷰 내부의 크기에 따라 크기가 달라짐)
     *    MeasureSpec.EXACTLY : fill_parent, match_parent (외부에서 이미 크기가 지정되었음)
     *    MeasureSpec.UNSPECIFIED : MODE 가 셋팅되지 않은 크기가 넘어올때 (대부분 이 경우는 없다)
     *
     *   fill_parent, match_parent 를 사용하면 윗단에서 이미 크기가 계산되어 EXACTLY 로 넘어온다.
     *   이러한 크기는 MeasureSpec.getSize(spec) 으로 얻어낼 수 있다.
     *
     *   이 메소드에서는 setMeasuredDimension(measuredWidth,measuredHeight) 를 호출해 주어야 하는데
     *   super.onMeasure() 에서는 기본으로 이를 기본으로 계산하는 함수를 포함하고 있다.
     *
     *   만약 xml 에서 크기를 wrap_content 로 설정했다면 이 함수에서 크기를 계산해서 셋팅해 줘야한다.
     *   그렇지 않으면 무조껀 fill_parent 로 나오게 된다.
     */

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        // height 진짜 크기 구하기
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = 0;
        switch(heightMode) {
            case MeasureSpec.UNSPECIFIED:    // mode 가 셋팅되지 않은 크기가 넘어올때
                heightSize = heightMeasureSpec;
                break;
            case MeasureSpec.AT_MOST:        // wrap_content (뷰 내부의 크기에 따라 크기가 달라짐)
                heightSize = 20;
                break;
            case MeasureSpec.EXACTLY:        // fill_parent, match_parent (외부에서 이미 크기가 지정되었음)
                heightSize = MeasureSpec.getSize(heightMeasureSpec);
                break;
        }

        // width 진짜 크기 구하기
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = 0;
        switch(widthMode) {
            case MeasureSpec.UNSPECIFIED:    // mode 가 셋팅되지 않은 크기가 넘어올때
                widthSize = widthMeasureSpec;
                break;
            case MeasureSpec.AT_MOST:        // wrap_content (뷰 내부의 크기에 따라 크기가 달라짐)
                widthSize = 100;
                break;
            case MeasureSpec.EXACTLY:        // fill_parent, match_parent (외부에서 이미 크기가 지정되었음)
                widthSize = MeasureSpec.getSize(widthMeasureSpec);
                break;
        }
        Log.w("CustomView","onMeasure("+widthMeasureSpec+","+heightMeasureSpec+")");

        setMeasuredDimension(widthSize, heightSize);
    }

    /*
         * 실제로 화면에 그리는 영역으로 View 를 상속하고 이 메소드만 구현해도 제대로 보여지게 된다.
         * 그릴 위치는 0,0 으로 시작해서 getMeasuredWidth(), getMeasuredHeight() 까지 그리면 된다.
         * super 메소드에서는 아무것도 하지않기때문에 쓰지 않는다.
         */
    @Override
    protected void onDraw(Canvas canvas) {
        final Paint p = new Paint();
       // p.setColor(backgroundColor);
        p.setColor(Color.YELLOW);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(10);
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(), p);
        if (text != null) {
            p.setColor(Color.BLACK);
            canvas.drawText(text, 10, 15, p); // 왼쪽 아래를 0,0 으로 보고있음
        }
        Log.w("CustomView","onDraw("+canvas+")");
    }

    /*
        * 현재 view 가 focus 상태일때 key 를 누르면 이 메소드가 호출됨.
        * 즉 이 메소드를 사용하려면 setFocusable(true) 여야함.
        * 그리고 super 메소드에서는 기본적인 키 작업(예를들면 BACK 키 누르면 종료)을 처리하기
        * 때문에 일반적으로 return 시에 호출하는게 좋다.
        * 만약 기본적인 작업을 하지않게 하려면 super 함수를 호출하지 않아도 된다.
        * 다른 event 메소드들도 유사하게 동작한다.
        */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.w("CustomView","onKeyDown("+keyCode+","+event+")");
        return super.onKeyDown(keyCode, event);
    }

    /*
     * 이 view 에 touch 가 일어날때 실행됨.
     * 기본적으로 touch up 이벤트가 일어날때만 잡아내며
     * setClickable(true) 로 셋팅하면 up,move,down 모두 잡아냄
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.w("CustomView","onTouchEvent("+event+")");
        switch(event.getAction()) {
            case MotionEvent.ACTION_UP:
                //backgroundColor = Color.RED;
                backgroundStroke = Color.RED;
                text = tempText;
                break;
            case MotionEvent.ACTION_DOWN:
                //backgroundColor = Color.YELLOW;
                backgroundStroke = Color.BLUE;
                tempText = text;
                text = "Clicked!";
                break;
            case MotionEvent.ACTION_MOVE:
                //backgroundColor = Color.BLUE;
                backgroundStroke = Color.YELLOW;
                text = "Moved!";
                break;
        }
        invalidate();
        return super.onTouchEvent(event);
    }


    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
}
