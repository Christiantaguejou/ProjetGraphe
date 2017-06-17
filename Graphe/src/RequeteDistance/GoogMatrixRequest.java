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

  OkHttpClient client = new OkHttpClient();

  public String run(String url) throws IOException {
    Request request = new Request.Builder()
        .url(url)
        .build();

    Response response = client.newCall(request).execute();
    return response.body().string();
  }

  public static void main(String[] args) throws IOException, JSONException {
    GoogMatrixRequest request = new GoogMatrixRequest();
    String ville1 ="paris";
    String ville2 ="marseille";
  //  String url_request = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=Vancouver+BC%7CSeattle&destinations=San+Francisco%7CVictoria+BC&mode=driving&language=fr-FR&key=" + API_KEY;
    String url_request = "https://maps.googleapis.com/maps/api/distancematrix/json?origins="+ville1+"&destinations="+ville2+"&mode=driving&language=fr-FR&key=" + API_KEY;

    String response = request.run(url_request);
    JSONObject reponse = new JSONObject(response);
    JSONArray lignes = reponse.getJSONArray("rows");
    JSONObject elements = lignes.getJSONObject(0);
    JSONArray tableauElem = elements.getJSONArray("elements");
    JSONObject distance = tableauElem.getJSONObject(0);
    JSONObject distancekm = distance.getJSONObject("distance");
    System.out.println(distancekm.getString("value"));
  }
  
  public int distanceReelle(String ville1,String ville2) throws IOException, JSONException{
    //GoogMatrixRequest request = new GoogMatrixRequest();
    String url_request = "https://maps.googleapis.com/maps/api/distancematrix/json?origins="+ville1+"&destinations="+ville2+"&mode=driving&language=fr-FR&key=" + API_KEY;
    String response = this.run(url_request);
    //System.out.println(response);
    JSONObject reponse = new JSONObject(response);
    JSONArray lignes = reponse.getJSONArray("rows");
    JSONObject elements = lignes.getJSONObject(0);
    JSONArray tableauElem = elements.getJSONArray("elements");
    JSONObject distance = tableauElem.getJSONObject(0);
    JSONObject distancekm = distance.getJSONObject("distance");
    System.out.println(distancekm.getString("value"));
    return (distancekm.getInt(distancekm.getString("value")))/1000;
  }
      
}
