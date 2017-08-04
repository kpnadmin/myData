package seveno.android.miniseconds.tabandfragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class TabFragment1 extends Fragment {

    private View view = null;

    public TabFragment1() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.tab_fragment_1, container, false);
        view = inflater.inflate(R.layout.tab_fragment_1, container, false);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // 출력 데이터 생성
        // ListView 생성
        // Adapter 생성
        // ListView와 Adapter 연결

        // 출력 데이터 생성
        List<String> list = new ArrayList<>();
        list.add("Item 1A");
        list.add("Item 1B");
        list.add("Item 1C");
        list.add("Item 1D");
        list.add("Item 1E");
        list.add("Item 1F");
        list.add("Item 1G");
        list.add("Item 1H");
        list.add("Item 1I");
        list.add("Item 1J");
        list.add("Item 1K");
        list.add("Item 1L");
        list.add("Item 1M");
        list.add("Item 1N");
        list.add("Item 1O");
        list.add("Item 1P");
        list.add("Item 1Q");
        list.add("Item 1R");
        list.add("Item 1S");
        list.add("Item 1T");
        list.add("Item 1U");
        list.add("Item 1V");
        list.add("Item 1W");
        list.add("Item 1X");
        list.add("Item 1Y");
        list.add("Item 1Z");

        // ListView 생성
        ListView listView = (ListView) view.findViewById( R.id.list_view );
        // Adapter 생성
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, list);
        // ListView와 Adapter 연결
        listView.setAdapter( adapter );
    }


}
