package seveno.android.miniseconds.AvoidStarGame;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceView;

/**
 * Created by Administrator on 2017-08-07.
 */

public class KnightView extends SurfaceView {

    public enum AnimationType {
        BREATH,
        MOVE,
        JUMP,
    }

    public KnightView(Context context) {
        super(context);
    }


    public KnightView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
