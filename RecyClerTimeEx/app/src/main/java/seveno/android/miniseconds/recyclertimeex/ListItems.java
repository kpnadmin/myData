package seveno.android.miniseconds.recyclertimeex;

import java.util.Calendar;

/**
 * Created by Administrator on 2017-08-24.
 */

public abstract class ListItems {
    public static final int TYPE_TIME = 1;
    public static final int TYPE_DATA = 2;

    private long time;

    public ListItems(long time) {
        this.time = time;
    }

    public ListItems(int year, int month, int dayOfMonth) {
        setTime(year, month, dayOfMonth);
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setTime(int year, int month, int dayOfMonth) {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(year, month-1, dayOfMonth);
        time = cal.getTimeInMillis();
    }

    public int getYear() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        return cal.get(Calendar.YEAR);
    }

    public int getMonth() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        return cal.get(Calendar.MONTH) + 1;
    }

    public int getDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    public long getTime() {
        return time;
    }

    public String getTimeToString() {
        return getYear() + "." + getMonth() + "." + getDayOfMonth();
    }

    public abstract int getType();
    
    
}
