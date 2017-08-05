package seveno.android.miniseconds.AvoidStarGame;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.SurfaceView;

/**
 * Created by Administrator on 2017-08-03.
 */

public class AGameView extends SurfaceView{
    private AGameView gameView;


    private Bitmap currentSprite;
    private Bitmap flipSprite;






    public AGameView(Context context) {
        super(context);
    }

    public enum AnimationType {
        BREATH,
        MOVE,
        JUMP,
    }


}
