package seveno.android.miniseconds.paginglistview;

import com.android.mobilelibrary.HttpRequest;
import com.android.mobilelibrary.ModelPerson;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 2017-08-04.
 */

public class HttpItem {
    private final String URL_ITEMLIST   = "http://192.168.0.59:8080/rest/itemlist";
    private final String URL_ITEMVIEW   = "http://192.168.0.59:8080/rest/itemview";


    public List<ModelItem> get_item_list(int start, int end){

        String weburl = "http://192.168.0.59:8080/rest/getitemlist";
        HttpRequest request = null;
        JSONArray response = null;
        List<ModelItem> result = null;

        try {
            request = new HttpRequest(weburl).addHeader("charset","utf-8");
            request.addParameter("id",String.valueOf(start));
            request.addParameter("end",String.valueOf(end));
            int httpCode = request.post();

            if(httpCode == HttpURLConnection.HTTP_OK){
                try {
                    response = request.getJSONArrayResponse();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }else{

            }


            JSONObject json = (JSONObject) response.get(0);


            //
            String jsonInString = response.toString();

            if(response !=null){
                for(int i = 0 ; i < response.length(); i++){
                    JSONObject obj = (JSONObject) response.get(i);
                    ModelItem item = new ModelItem();
                    item.setDataItem01(obj.getString("dataItem01"));
                    item.setDataItem02(obj.getString("dataItem02"));
                    item.setDataItem03(obj.getString("dataItem03"));

                    result.add(item);
                }
            }

            result = new Gson().fromJson(jsonInString, new TypeToken<List<ModelItem>>(){}.getType());




        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            request.close();
        }

        return result;
    }


}
