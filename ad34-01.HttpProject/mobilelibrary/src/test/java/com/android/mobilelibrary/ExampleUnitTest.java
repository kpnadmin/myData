package com.android.mobilelibrary;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;

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

        String weburl = "http://192.168.0.59:8080/rest/login";
        HttpRequest request = null;
        JSONObject response = null;


        try {
            request = new HttpRequest(weburl).addHeader("Content-Type","application/json").addHeader("charset","utf-8");
            request.addParameter("id","test1id");
            int httpCode = request.get();

            if(httpCode == HttpURLConnection.HTTP_OK){
                response = request.getJSONObjectResponse();

            }else{

            }
            assertEquals("valid", response.getString("id"           ));
            assertEquals("valpw", response.getString("pw"           ));
            assertEquals("valname", response.getString("name"           ));
            assertEquals("valemail", response.getString("email"           ));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            request.close();
        }


    }


}