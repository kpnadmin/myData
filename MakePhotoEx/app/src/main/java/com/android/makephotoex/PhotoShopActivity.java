package com.android.makephotoex;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class PhotoShopActivity extends Activity {

    private DrawView mDrawView;
    public static float mStrokeWidth = 1;
    public static int mStrokeColor = Color.BLACK;
    public static int mBackColor = Color.WHITE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photoshop);
        mDrawView = new DrawView(this);

        if(savedInstanceState != null){
            // 화면전환 전에 넣어주었던 pointList를 꺼내서 세팅
            mDrawView.pointList =
                    (ArrayList<PhotoShopActivity.DrawView.Point>)
                            savedInstanceState.getSerializable("list");
        }
        setContentView(mDrawView);
        savePicture();
    }
//


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("list", mDrawView.pointList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 선굵기 , 색깔, 바탕색 변경 메뉴 추가
        SubMenu subMenu = menu.addSubMenu(0,0,0, "선굵기");
        subMenu.add(1,1,0, "얇은선");
        subMenu.add(1,2,1, "중간선");
        subMenu.add(1,3,2, "굵은선");
        subMenu.setGroupCheckable(1,true, true);

        getMenuInflater().inflate(R.menu.photoshop_menu, menu);
        // 화면 회전에 따른 저장
        menu.add(2, 4, 5, "그림저장");
        menu.add(3, 5, 6, "불러오기");
        return true;
    }
    // 메뉴버튼을 누를 때마다 호출(특정값이 선택되어 있게 할 때 사용)
    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        switch ((int)mStrokeWidth){
            case 1 :
                menu.findItem(1).setChecked(true);
                break;
            case 3 :
                menu.findItem(2).setChecked(true);
            case 5:
                menu.findItem(3).setChecked(true);
                break;
        }
        switch(mStrokeColor){
            case Color.BLACK:
                menu.findItem(R.id.item_black).setChecked(true);
                break;
            case Color.BLUE:
                menu.findItem(R.id.item_blue).setChecked(true);
                break;
            case Color.RED:
                menu.findItem(R.id.item_red).setChecked(true);
                break;
        }
        switch (mBackColor){
            case Color.YELLOW:
                menu.findItem(R.id.item_back_yellow).setChecked(true);
                break;
            case Color.WHITE:
                menu.findItem(R.id.item_back_white).setChecked(true);
                break;
            case Color.GREEN:
                menu.findItem(R.id.item_back_green).setChecked(true);
                break;
        }
        return super.onPrepareOptionsMenu(menu);
    }
@Override
public boolean onOptionsItemSelected(MenuItem item){
    switch (item.getItemId()){
        case 1 :
            mStrokeWidth = 1;
            mDrawView.invalidate();
            break;
        case 2:
            mStrokeWidth = 3;
            mDrawView.invalidate();
            break;
        case 3:
            mStrokeWidth = 5;
            mDrawView.invalidate();
            break;
        case 4:
            //
            savePicture();
        case 5:
            //그림을 불러온다.
            loadPicture();
        case R.id.item_black:
            mStrokeColor = Color.BLACK;
            mDrawView.invalidate();
            break;
        case R.id.item_blue:
            mStrokeColor = Color.RED;
            mDrawView.invalidate();
            break;
        case R.id.item_red:
            mStrokeColor = Color.RED;
            mDrawView.invalidate();
            break;
        case R.id.item_back_white:
            mBackColor = Color.WHITE;
            break;
        case R.id.item_back_yellow:
            mBackColor = Color.YELLOW;
            mDrawView.invalidate();
        case R.id.item_clear:
            mDrawView.pointList.clear();
            mDrawView.invalidate();
    }
    return super.onOptionsItemSelected(item);
}
private void loadPicture(){
    String path = Environment.getExternalStorageDirectory().getAbsolutePath();
    File dir =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
    Bitmap bm = BitmapFactory.decodeFile(dir+"/my.png");
    Log.d("pthoto", dir+"/my.png");
    mDrawView.draw(new Canvas(bm));
}
    private  void savePicture(){
        //String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        //1. 캐쉬를 허용시킨다
        // 2. 그림은 Bitmap으로 저장
        // 3. 캐쉬를 막는다 .
        mDrawView.setDrawingCacheEnabled(true); // 캐쉬허용
        // 캐쉬에서 가져온 비트맵을 복사해서 새로운 비트맵(스크린샷) 생성
        //Bitmap screenshot = Bitmap.createBitmap(mDrawView.getDrawingCache());
        // SDCard(ExternalStorate) : 외부 저장공간
        // 접근하려면 반드시 AndroidManifest.xml 에 권한 설정을 한다.
     // File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        //File dir = new File(path+"/mydir");

     /*   // 폴더가 있는지 확인 후 없으면 새로 만들어준다 .
        if(!dir.exists()) dir.mkdirs();
        FileOutputStream fos;
        try{
            fos = new FileOutputStream(new File(dir, "my.png"));
            screenshot.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
            Toast.makeText(this, "저장 성공", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Log.e("pthoto", "그림저장오류", e);
            Toast.makeText(this, "저장 실패", Toast.LENGTH_SHORT).show();
        }*/
        mDrawView.setDrawingCacheEnabled(false); // 캐쉬 닫기
    }


    /*
    액티비티 화면에 그리기 처리를 하는 내용을 정의
    */
    private static class DrawView extends View implements View.OnTouchListener{
            float x;
             float y;
        public ArrayList<Point> pointList = new ArrayList<DrawView.Point>();

        public DrawView(Context context) {
            super(context);
            setOnTouchListener(this);
            setFocusableInTouchMode(true);// 이벤트가 계속해서 발생하기 위해

        }
        public void onDraw( Canvas canvas ) {
///
            canvas.drawColor(Color.WHITE);
            //
            Paint pnt = new Paint();
            pnt.setStyle(Paint.Style.STROKE);
            pnt.setStrokeWidth(3);
            pnt.setColor(Color.BLACK);
            pnt.setAntiAlias(true);
            //
            RectF rect = new RectF();
            rect.set(200,200,600,600);
            //
            pnt.setStyle(Paint.Style.FILL);
            pnt.setColor(0xffff8800);
            canvas.drawArc(rect, 0 , 120, true, pnt);
            // 2
            pnt.setStyle(Paint.Style.FILL);
            pnt.setColor(0xffffff00);
            canvas.drawArc(rect, 120, 80, true, pnt);
            //
            pnt.setStyle(Paint.Style.FILL);
            pnt.setColor(0xff0088ff);
            canvas.drawArc(rect, 200, 160, true, pnt);
            if(pointList.size() < 2) return ;
            for(int i = 1 ; i <pointList.size(); i ++){
                if(pointList.get(i).draw){
                    pnt.setColor(pointList.get(i).mStrokeColor);
                    pnt.setStrokeWidth(pointList.get(i).mStrokeWidth);
                    canvas.drawLine(pointList.get(i-1).x,
                            pointList.get(i-1).y, pointList.get(i).x,
                            pointList.get(i).y, pnt);
                }
            }
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            x = event.getX();
            y = event.getY();
            switch(event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    Log.d("photo", "손으로 터치했음");
                    pointList.add(new Point(x,y,false, mStrokeWidth, mStrokeColor));
                    invalidate();
                    return true;
                case MotionEvent.ACTION_MOVE:
                    Log.d("photo", "손가락으로 움직이는 중");
                    pointList.add(new Point(x,y,true, mStrokeWidth, mStrokeColor));
                    invalidate();
                    return true;
                case MotionEvent.ACTION_UP:
                    Log.d("photo", "손가락 뗏음");
                default:
            }
            return false;
        }

        static class Point implements Serializable{
            float x,y ;
            boolean draw;
            float mStrokeWidth;
            int mStrokeColor;

            public Point(float x, float y, boolean draw, float strokeWidth, int strokeColor) {
                this.x = x;
                this.y = y;
                this.draw = draw;
                mStrokeWidth = strokeWidth;
                mStrokeColor = strokeColor;
            }
        }//end class Point
    }



}
