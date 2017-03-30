package eprit.tn.cowbot.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import eprit.tn.cowbot.Adapter.WeatherAdapter;
import eprit.tn.cowbot.CallBack.AbstractServiceCallBack;
import eprit.tn.cowbot.Entity.Weather;
import eprit.tn.cowbot.R;
import eprit.tn.cowbot.Service.WeatherService;

public class WeatherFragment extends Fragment {

    private RecyclerView weatherRs;
    private TextView local, temperature, weath;
    private ImageView TempImg;
    private SwipeRefreshLayout refreshLayout;

    private WeatherAdapter weatherAdapter;
    private WeatherService weatherService = new WeatherService();
    private final Handler handler = new Handler();

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

        return InitializeView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getWeather();
        refrechData();
    }

    /*
     * getData from database
     */
    public void getWeather() {
        weatherService.getWeather(new AbstractServiceCallBack<Weather>() {

            @Override
            public void onSuccess(List weathers, String temp) {

                setDataToRecyclerView(weathers);
                temperature.setText(temp + "°");
            }

        }, 1);
    }

    /*
         *set Data from database to RecyclerView
         */
    public void setDataToRecyclerView(List<Weather> weatherList) {
        weatherRs.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        weatherRs.setLayoutManager(mLayoutManager);
        weatherRs.setItemAnimator(new DefaultItemAnimator());
        weatherAdapter = new WeatherAdapter(weatherList, getActivity());
        weatherRs.setAdapter(weatherAdapter);
    }

    public View InitializeView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);

        weatherRs = (RecyclerView) view.findViewById(R.id.weatherrs);
        local = (TextView) view.findViewById(R.id.local);
        temperature = (TextView) view.findViewById(R.id.temperature);
        weath = (TextView) view.findViewById(R.id.weath);
        TempImg = (ImageView) view.findViewById(R.id.tempimg);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refrech);

        return view;
    }

    public void refrechData() {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    public void run() {
                        getWeather();
                        refreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }

        });
    }


}
