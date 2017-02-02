package eprit.tn.cowbot.Service;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import eprit.tn.cowbot.CallBack.WeatherCallBack;
import eprit.tn.cowbot.Entity.Weather;
import eprit.tn.cowbot.MainApplication;
import eprit.tn.cowbot.R;
import eprit.tn.cowbot.Utils.Const;

import static android.content.ContentValues.TAG;

/**
 * Created by Sami on 02/02/2017.
 */

public class WeatherService {
    public void getWeather(final WeatherCallBack onCallBack,int id)
    {

        final List<Weather> weatherList = new ArrayList<>();
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET,Const.URL_getWeather+"?id="+id,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {


                        try {
                            JSONArray weather= response.getJSONArray("weather");
                            JSONObject c = weather.getJSONObject(0);
                            weatherList.add(new Weather("Wind",c.getString("Wind"), R.drawable.wind));
                            weatherList.add(new Weather("WindDirection",c.getString("WindDirection")+" Km/h",R.drawable.wind));
                            weatherList.add(new Weather("Humidity",c.getString("Humidity")+" %",R.drawable.humidity));
                            weatherList.add(new Weather("Rain",c.getString("Rain")+" %",R.drawable.rain));
                            onCallBack.onSuccess(weatherList,c.getString("Temperature"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());

            }
        });
        // Adding request to request queue
        MainApplication.getInstance().addToRequestQueue(req);
    }
}
