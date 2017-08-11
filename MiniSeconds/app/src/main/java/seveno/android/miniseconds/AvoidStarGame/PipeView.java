package seveno.android.miniseconds.AvoidStarGame;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Administrator on 2017-08-11.
 */

public class PipeView extends SurfaceView implements SurfaceHolder.Callback{



    public PipeView(Context context) {
        super(context);
    }

    public PipeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PipeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
