package skyblue.android.skyhotelapp;

import com.squareup.otto.Bus;

/**
 * Created by 김태훈 on 2017-07-29.
 */

public final class BusProvider {
    private static final Bus bus = new Bus();

    public static Bus getInstance(){
        return bus;
    }
private BusProvider(){
    //
}


}
