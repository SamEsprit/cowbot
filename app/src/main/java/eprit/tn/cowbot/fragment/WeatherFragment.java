package eprit.tn.cowbot.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import eprit.tn.cowbot.Adapter.WeatherAdapter;
import eprit.tn.cowbot.Entity.Weather.Weather;
import eprit.tn.cowbot.Entity.Weather.WeatherInput;
import eprit.tn.cowbot.Factory.ServiceFactory;
import eprit.tn.cowbot.R;
import eprit.tn.cowbot.Service.WeatherService;
import eprit.tn.cowbot.Utils.URLS;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class WeatherFragment extends Fragment {

    private RecyclerView weatherRs;
    private TextView local, temperature, weath;
    private ImageView TempImg;
    Geocoder geocoder;
    List<Address> addresses;

    private CompositeDisposable mCompositeDisposable;

    private Integer idUser;
    private SharedPreferences sharedPreferences;

    private WeatherAdapter weatherAdapter;
    private WeatherService weatherService;

    public WeatherFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCompositeDisposable = new CompositeDisposable();
        weatherService = ServiceFactory.createRetrofitService(WeatherService.class, URLS.EndPoint);
        geocoder = new Geocoder(getActivity().getApplicationContext(), Locale.ENGLISH);
        sharedPreferences = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        idUser =sharedPreferences.getInt("id", 0);

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
        setDataToRecyclerView();

    }

    private void setDataToRecyclerView() {

        mCompositeDisposable
                .add(weatherService.getWeather(idUser)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io()).repeat()
                        .subscribe(this::handleResponse, this::handleError));

    }

    private void handleResponse(WeatherInput weather) throws IOException {


        List<Weather> weathers= new ArrayList<>();
        weathers.add(new Weather("Wind",weather.getWind(),R.drawable.wind));
        if(isTablet(getActivity()))
        weathers.add(new Weather("Wind Direction",weather.getWindDirection(),R.drawable.wind));

        else
            weathers.add(new Weather("Wind \nDirection",weather.getWindDirection(),R.drawable.wind));
        weathers.add(new Weather("Humidity",weather.getHumidity()+"%",R.drawable.humidity));
        weathers.add(new Weather("Rain",weather.getRain(),R.drawable.rain));
        temperature.setText(weather.getTemperature()+"°c");

        setDataToRecyclerView(weathers);
        addresses = geocoder.getFromLocation(Double.parseDouble(weather.getLatitude()), Double.parseDouble(weather.getLongitude()), 1);
        local.setText(addresses.get(0).getAddressLine(0));

    }

    private void handleError(Throwable throwable) {

    }

    /*
         *set Data from database to RecyclerView
         */
    public void setDataToRecyclerView(List<Weather> weatherList) {
        weatherRs.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        RecyclerView.LayoutManager glayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        if (isTablet(getActivity()))
            weatherRs.setLayoutManager(glayoutManager);
        else
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
        return view;
    }
    public boolean isTablet(Context context) {
        boolean xlarge = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE);
        boolean large = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);
        return (xlarge || large);
    }

}


