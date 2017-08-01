package skyblue.android.skyhotelapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;

import com.squareup.otto.Subscribe;


public class HotelInfoActivity extends Fragment implements View.OnClickListener{
    private Button btn_roomInfo ,btn_reserve ,btn_facilities ,btn_location ,btn_phonecall;
    private Integer Act_code = -1;
    private int REQ_CODE_CONTENT_DETAIL = 1;

    public HotelInfoActivity() {
    }

    public Integer getAct_code() {
        return Act_code;
    }

    public void setAct_code(Integer act_code) {
        Act_code = act_code;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View v =   inflater.inflate(R.layout.act_hotel_info, container, false);
        btn_location = (Button) v.findViewById(R.id.btn_location);
        btn_location.setOnClickListener(this);
        btn_facilities = (Button) v.findViewById(R.id.btn_facilities);
        btn_facilities.setOnClickListener(this);
        btn_reserve = (Button) v.findViewById(R.id.btn_reserve);
        btn_reserve.setOnClickListener(this);
        BusProvider.getInstance().register(this);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_location :
                Act_code = 1;
                Intent intent = new Intent(v.getContext(),HotelLocActivity.class);
                getActivity().startActivityForResult(intent, REQ_CODE_CONTENT_DETAIL);
                break;
            case R.id.btn_facilities :
                Act_code = 2;
                intent = new Intent(v.getContext(),FacilitiesActivity.class);
                getActivity().startActivityForResult(intent, REQ_CODE_CONTENT_DETAIL);
                break;
            case R.id.btn_reserve:
                Act_code = 3;
                intent = new Intent(v.getContext(),ReserveActivity.class);
                getActivity().startActivityForResult(intent, REQ_CODE_CONTENT_DETAIL);
                break;
            default:
                break;
        }
    }

    /**
     * NestedFragment에서 startactivityForresult실행시 fragment에 들어오지 않는 문제
     *
     * @param activityResultEvent
     */
    @Subscribe
    public void onActivityResult(ActivityResultEvent activityResultEvent){
        onActivityResult(activityResultEvent.getRequestCode(), activityResultEvent.getResultCode(), activityResultEvent.getData());
    }
    @Override
    public void onDestroyView() {
        BusProvider.getInstance().unregister(this);
        super.onDestroyView();

    }


}
