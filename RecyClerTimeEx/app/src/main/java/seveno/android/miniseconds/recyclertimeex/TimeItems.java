package seveno.android.miniseconds.recyclertimeex;

/**
 * Created by Administrator on 2017-08-24.
 */

public class TimeItems extends ListItems{

    public TimeItems(long time) {
        super(time);
    }

    public TimeItems(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
    }

    @Override
    public int getType() {
        return TYPE_TIME;
    }
}
