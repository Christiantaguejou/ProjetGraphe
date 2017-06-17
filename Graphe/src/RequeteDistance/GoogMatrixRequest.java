/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RequeteDistance;

import java.io.FileReader;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author markk
 */
//cle_api=AIzaSyBkHh6SZhRFEi9yxo8ikALKzgxbHsolWeM 
public class GoogMatrixRequest {
    private static final String API_KEY = "AIzaSyBkHh6SZhRFEi9yxo8ikALKzgxbHsolWeM";

  static OkHttpClient client = new OkHttpClient();

  public static String run(String url) throws IOException {
    Request request = new Request.Builder()
        .url(url)
        .build();
    Response response = client.newCall(request).execute();
    return response.body().string();
  }
  
  public static double distanceReelle1(String ville1,String ville2) throws IOException, JSONException{
    //GoogMatrixRequest request = new GoogMatrixRequest();
    String url_request = "https://maps.googleapis.com/maps/api/distancematrix/json?origins="+ville1+", France"+"&destinations="+ville2+", France"+"&mode=driving&language=fr-FR&key=" + API_KEY;
    String response = GoogMatrixRequest.run(url_request);
    System.out.println(response);
    JSONObject reponse = new JSONObject(response);
    JSONArray lignes = reponse.getJSONArray("rows");
    JSONObject elements = lignes.getJSONObject(0);
    JSONArray tableauElem = elements.getJSONArray("elements");
    JSONObject distance = tableauElem.getJSONObject(0);
    JSONObject distancekm = distance.getJSONObject("distance");
    //System.out.println(distancekm.getString("value"));
    return (Integer.parseInt(distancekm.getString("value")))/1000;
  }
  public static double distanceReelle2(double longville1,double latville1,double longville2,double latville2) throws IOException, JSONException{
    //GoogMatrixRequest request = new GoogMatrixRequest();
    String url_request = "https://maps.googleapis.com/maps/api/distancematrix/json?origins="+latville1+","+longville1+"&destinations="+latville2+","+longville2+"&mode=driving&language=fr-FR&key=" + API_KEY;
    String response = GoogMatrixRequest.run(url_request);
    System.out.println(response);
    JSONObject reponse = new JSONObject(response);
    JSONArray lignes = reponse.getJSONArray("rows");
    JSONObject elements = lignes.getJSONObject(0);
    JSONArray tableauElem = elements.getJSONArray("elements");
    JSONObject distance = tableauElem.getJSONObject(0);
    JSONObject distancekm = distance.getJSONObject("distance");
    //System.out.println(distancekm.getString("value"));
    return (Integer.parseInt(distancekm.getString("value")))/1000;
  }
      
}
