package seveno.android.miniseconds.recyclertimeex;

/**
 * Created by Administrator on 2017-08-24.
 */

public class ListData extends ListItems{

    private String name;

    public ListData(String name, long time) {
        super(time);
        this.name = name;
    }

    public ListData(String name, int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
        this.name = name;
    }

    @Override
    public int getType() {
        return TYPE_DATA;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
