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

import java.util.List;

import eprit.tn.cowbot.Adapter.WeatherAdapter;
import eprit.tn.cowbot.CallBack.WeatherCallBack;
import eprit.tn.cowbot.Entity.Weather;
import eprit.tn.cowbot.R;
import eprit.tn.cowbot.Service.WeatherService;

public class WeatherFragment extends Fragment {

    private RecyclerView weatherRs;
    private TextView local, temperature, weath;
    private ImageView TempImg;
    private WeatherAdapter weatherAdapter;
    private WeatherService weatherService = new WeatherService();

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
        weatherService.getWeather(new WeatherCallBack() {
            @Override
            public void onSuccess(List<Weather> weathers, String temp) {
                setDataToRecyclerView(weathers);
                temperature.setText(temp);
            }

            @Override
            public void onFail() {

            }
        },1);
    }

    /*
         *set Data from database to RecyclerView
         */
    public void setDataToRecyclerView(List<Weather> weatherList) {
        Log.d("responce: ", weatherList.get(0).getTitre().toString());
        weatherRs.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        weatherRs.setLayoutManager(mLayoutManager);
        weatherRs.setItemAnimator(new DefaultItemAnimator());
        weatherAdapter = new WeatherAdapter(weatherList, getActivity());
        weatherRs.setAdapter(weatherAdapter);
    }


}
