package eprit.tn.cowbot.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import eprit.tn.cowbot.Adapter.WeatherAdapter;
import eprit.tn.cowbot.Entity.Weather;
import eprit.tn.cowbot.R;

public class WeatherFragment extends Fragment {

    private RecyclerView weatherRs;
    private TextView local, temperature, weath;
    private ImageView TempImg;

    private List<Weather> weatherList;
    private WeatherAdapter weatherAdapter;


    public WeatherFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        weatherList = new ArrayList<Weather>();
        weatherList.add(new Weather("Wind","14 km/h S",R.drawable.wind));
        weatherList.add(new Weather("Humidity","77 %",R.drawable.humidity));
        weatherList.add(new Weather("Pressure","13 mb",R.drawable.pressure));

        weatherAdapter=new WeatherAdapter(weatherList,getActivity());

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
        weatherRs.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        weatherRs.setLayoutManager(mLayoutManager);
        weatherRs.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        weatherRs.setItemAnimator(new DefaultItemAnimator());
        weatherRs.setAdapter(weatherAdapter);
    }
}
