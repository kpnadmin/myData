package com.android.mobilelibrary;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {

        JSONObject json = null;

        String str = "{'key1':'value1','key2':'value2'}";

        json = new JSONObject(str);

        assertEquals(json.get("key1").toString(), "value1");


    }
    @Test
    public void test_make_json_object(){
        String msg = "";
        // {'name':'student'', 'cource':['name':'course1',{'information':'test','id':3} ]}

        try {
            //{'information':'test','id':3}
            JSONObject item = new JSONObject();
            item.put("information","test");
            item.put("id",3);


            JSONArray jArray = new JSONArray();
            jArray.put("name");
            jArray.put("course1");
            jArray.put(item);

            JSONObject json = new JSONObject();
            json.put("name","student");
            json.put("course",jArray);

            msg = json.toString();

            assertEquals(json.get("name").toString(),"student");
            JSONArray test1 = (JSONArray) json.get("course");
            JSONObject test2 = (JSONObject) test1.get(2);

            assertEquals(test2.get("information").toString(), "test");


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void test_get_currentversion(){
        String weburl = "http://192.168.0.59:8080/rest/currentversion";

        HttpRequest request = null;
        String response = null;

        try {
            request = new HttpRequest(weburl).addHeader("charset","utf-8");
            int httpCode = request.get();

            // HttpURLConnection.HTTP_OK = 200
            if(HttpURLConnection.HTTP_OK == httpCode){
            response = request.getStringResponse();
            }else{
                //error

            }
            assertTrue(!response.isEmpty());
            assertNotEquals(response,"1");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            request.close();
        }

    }

    @Test
    public void test_get_login_success(){

        String weburl = "http://192.168.0.59:8080/rest/login";
        HttpRequest request = null;
        String response = "";


        try {
            request = new HttpRequest(weburl).addHeader("charset","utf-8");
            request.addParameter("id","test1id");
            request.addParameter("pw","test1pw");
            int httpCode = request.post();

            if(httpCode == HttpURLConnection.HTTP_OK){
                response = request.getStringResponse();

            }else{

            }
            assertEquals("1", response);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            request.close();
        }


    }
    @Test
    public void test_get_json_object(){

        String weburl = "http://192.168.0.59:8080/rest/personone";
        HttpRequest request = null;
        JSONObject response = null;


        try {
            request = new HttpRequest(weburl).addHeader("Content-Type","application/json").addHeader("charset","utf-8");
            request.addParameter("id","valname");
            int httpCode = request.post();

            if(httpCode == HttpURLConnection.HTTP_OK){
                response = request.getJSONObjectResponse();

            }else{

            }
            assertEquals("valid", response.getString("id"           ));
            assertEquals("valpw", response.getString("pw"           ));
            assertEquals("valname", response.getString("name"           ));
            assertEquals("valemail", response.getString("email"           ));

            String jsonInString = response.toString();
            Gson gson = new Gson();
            ModelPerson person = gson.fromJson(jsonInString, ModelPerson.class);

            assertEquals("valid", response.getString(person.getId()         ));
            assertEquals("valpw", response.getString(person.getPw()       ));
            assertEquals("valname", response.getString(person.getName()   ));
            assertEquals("valemail", response.getString(person.getEmail()  ));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            request.close();
        }


    }
    @Test
    public void get_json_array(){

        String weburl = "http://192.168.0.59:8080/rest/personlist";
        HttpRequest request = null;
        JSONArray response = null;


        try {
            request = new HttpRequest(weburl).addHeader("Content-Type","application/json").addHeader("charset","utf-8");
            request.addParameter("id","test3id");
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
            assertNotNull(response);
            assertNotEquals(0, response.length());

            JSONObject json = (JSONObject) response.get(0);

            assertEquals("test1id",     json.getString("id"           ));
            assertEquals("test1pw",     json.getString("pw"           ));
            assertEquals("test1name",   json.getString("name"           ));
            assertEquals("test1email", json.getString("email"           ));

           //
            String jsonInString = response.toString();
            List<ModelPerson> list = new Gson().fromJson(jsonInString, new TypeToken<List<ModelPerson>>(){}.getType());


            assertEquals("test1id",    list.get(0).getId()       );
            assertEquals("test1pw",    list.get(0).getPw()       );
            assertEquals("test1name",  list.get(0).getName()     );
            assertEquals("test1email", list.get(0).getEmail()    );

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            request.close();
        }


    }

    @Test
    public void test_insert_person(){

        String weburl = "http://192.168.0.59:8080/rest/insertPerson";
        HttpRequest request = null;
        String response = null;

        int httpCode = 0 ;
        try {
           ModelPerson obj = new ModelPerson("valid","valpw","valname","valemail");
            String data = new Gson().toJson(obj);
            request = new HttpRequest(weburl).addHeader("charset","UTF-8").addHeader("Content-Type","application/json").addHeader("Accept","application/json");

            httpCode = request.post(data);

            if(httpCode == HttpURLConnection.HTTP_OK){
                try {
                    response = request.getStringResponse();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }else{

            }
            assertNotNull(response);
            assertEquals("1", response);



        } catch (IOException e) {
            e.printStackTrace();
        }  finally {
            request.close();
        }


    }


    @Test
    public void test_insert_personlist(){

        String weburl = "http://192.168.0.59:8080/rest/insertPersonList";
        HttpRequest request = null;
        String response = null;

        int httpCode = 0 ;
        try {

            List<ModelPerson> list = new ArrayList<ModelPerson>();
            for(int i = 0 ; i <10 ; i++){
                //String t = String.valueOf(i);
                String t = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
                ModelPerson obj = new ModelPerson("valid"+t, "valpw"+t,"valname"+t,"valemail"+t);
                list.add(obj);
            }
            String data = new Gson().toJson(list);

            request = new HttpRequest(weburl).addHeader("charset","UTF-8").addHeader("Content-Type","application/json").addHeader("Accept","application/json");

            httpCode = request.post(data);

            if(httpCode == HttpURLConnection.HTTP_OK){
                try {
                    response = request.getStringResponse();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }else{

            }
            assertNotNull(response);
            assertEquals("10", response);



        } catch (IOException e) {
            e.printStackTrace();
        }  finally {
            request.close();
        }


    }

}