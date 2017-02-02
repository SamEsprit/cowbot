package eprit.tn.cowbot.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import eprit.tn.cowbot.Adapter.WeatherAdapter;
import eprit.tn.cowbot.Entity.Weather;
import eprit.tn.cowbot.MainApplication;
import eprit.tn.cowbot.R;
import eprit.tn.cowbot.Utils.Const;

import static android.content.ContentValues.TAG;

public class WeatherFragment extends Fragment {

    private RecyclerView weatherRs;
    private TextView local, temperature, weath;
    private ImageView TempImg;
    private WeatherAdapter weatherAdapter;


    public WeatherFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weather, container, false);

        weatherRs = (RecyclerView) view.findViewById(R.id.weatherrs);
        local = (TextView) view.findViewById(R.id.local);
        temperature = (TextView) view.findViewById(R.id.temperature);
        weath = (TextView) view.findViewById(R.id.weath);
        TempImg = (ImageView) view.findViewById(R.id.tempimg);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getPlant();
    }

    /*
     * getData from database
     */
    public void getPlant() {

        final List<Weather> weatherList = new ArrayList<>();
        JsonArrayRequest req = new JsonArrayRequest(Const.URL_getWeather,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {


                            try {
                                JSONObject c = response.getJSONObject(0);
                                temperature.setText(c.getString("Temperature"));
                                weatherList.add(new Weather("Wind",c.getString("Wind"),R.drawable.wind));
                                weatherList.add(new Weather("WindDirection",c.getString("WindDirection")+" Km/h",R.drawable.wind));
                                weatherList.add(new Weather("Humidity",c.getString("Humidity")+" %",R.drawable.humidity));
                                weatherList.add(new Weather("Rain",c.getString("Rain")+" %",R.drawable.rain));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        setDataToRecyclerView(weatherList);


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
    /*
         *set Data from database to RecyclerView
         */
    public void setDataToRecyclerView(List<Weather> weatherList) {
        Log.d("responce: " , weatherList.get(0).getTitre().toString());
        weatherRs.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(),2, GridLayoutManager.VERTICAL, false);
        weatherRs.setLayoutManager(mLayoutManager);
        weatherRs.setItemAnimator(new DefaultItemAnimator());
        weatherAdapter=new WeatherAdapter(weatherList,getActivity());
        weatherRs.setAdapter(weatherAdapter);
    }


}
