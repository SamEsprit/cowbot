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

import eprit.tn.cowbot.CallBack.AbstractServiceCallBack;
import eprit.tn.cowbot.Entity.Weather;
import eprit.tn.cowbot.MainApplication;
import eprit.tn.cowbot.R;
import eprit.tn.cowbot.Utils.Const;

import static android.content.ContentValues.TAG;

/**
 * Created by Sami on 02/02/2017.
 */

public class WeatherService {
    public void getWeather(final AbstractServiceCallBack<Weather> onCallBack, int id) {

        final List<Weather> weatherList = new ArrayList<>();
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, Const.URL_getWeather + "?id=" + id, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {


                        try {
                            JSONArray weather = response.getJSONArray("weather");
                            JSONObject c = weather.getJSONObject(0);
                            weatherList.add(new Weather("Wind", c.getString("Wind") + " m/h", R.drawable.wind));
                            int windDirection = c.getInt("WindDirection");


                            if (windDirection == 0)
                                weatherList.add(new Weather("Wind\nDirection", "North", R.drawable.wind));
                            else if (windDirection > 0 && windDirection < 90)
                                weatherList.add(new Weather("Wind\nDirection", "North/East", R.drawable.wind));
                            else if (windDirection == 90)
                                weatherList.add(new Weather("Wind\nDirection", "East", R.drawable.wind));
                            else if (windDirection > 90 && windDirection < 180)
                                weatherList.add(new Weather("Wind\nDirection", "South/East", R.drawable.wind));
                            else if (windDirection == 180)
                                weatherList.add(new Weather("Wind\nDirection", "South", R.drawable.wind));
                            else if (windDirection > 180 && windDirection < 270)
                                weatherList.add(new Weather("Wind\nDirection", "South/West", R.drawable.wind));
                            else if (windDirection == 270)
                                weatherList.add(new Weather("Wind\nDirection", "West", R.drawable.wind));
                            else if (windDirection > 270 && windDirection < 360)
                                weatherList.add(new Weather("Wind\nDirection", "North/West", R.drawable.wind));

                            weatherList.add(new Weather("Humidity", c.getString("Humidity") + " %", R.drawable.humidity));
                            weatherList.add(new Weather("Rain", c.getString("Rain") + " mm", R.drawable.rain));
                            onCallBack.onSuccess(weatherList, c.getString("Temperature"));

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
