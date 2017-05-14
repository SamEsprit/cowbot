package eprit.tn.cowbot.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import eprit.tn.cowbot.Adapter.PlantedPlanAdapter;
import eprit.tn.cowbot.Entity.Planted.PlantedInput;
import eprit.tn.cowbot.Factory.ServiceFactory;
import eprit.tn.cowbot.R;
import eprit.tn.cowbot.Service.PlantedService;
import eprit.tn.cowbot.Utils.URLS;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class FinishedPlantedPlantsActivity extends AppCompatActivity {




    private RecyclerView history;
    private ProgressBar progressData;
    private TextView dataText;

    private CompositeDisposable mCompositeDisposable;
    private PlantedService plantedService;
    RecyclerView.LayoutManager  mLayoutManager;

    private PlantedPlanAdapter plantedPlanAdapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InitializeView();
        InitializeUtils();
        getData();
    }


    /*
     * InitializeView du layout
     */
    public void InitializeView() {
        setContentView(R.layout.fragment_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        history = (RecyclerView) findViewById(R.id.history);
        progressData = (ProgressBar) findViewById(R.id.progressData);
        dataText=(TextView)findViewById(R.id.dataText);
        progressData.setVisibility(View.VISIBLE);
        dataText.setVisibility(View.GONE);
    }

    /*
     *set Data from database to RecyclerView
     */
    public void setDataToRecyclerView(List<PlantedInput> plantArrayList) {
        history.setHasFixedSize(true);
        history.setLayoutManager(mLayoutManager);
        history.setItemAnimator(new DefaultItemAnimator());
        plantedPlanAdapter = new PlantedPlanAdapter(plantArrayList,getApplicationContext());
        history.setAdapter(plantedPlanAdapter);
        progressData.setVisibility(View.GONE);
        history.setVisibility(View.VISIBLE);
    }

    private void getData() {
        mCompositeDisposable.add(plantedService.FinishedPlante(10)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handlePlantedResponse, this::handlePlantedError));
    }

    private void handlePlantedError(Throwable throwable) {
    }

    private void handlePlantedResponse(List<PlantedInput> plantedInputs) {
        progressData.setVisibility(View.INVISIBLE);
        if(plantedInputs.size()==0)
            dataText.setVisibility(View.VISIBLE);
        else
            setDataToRecyclerView(plantedInputs);
    }
    private void InitializeUtils() {
        mCompositeDisposable = new CompositeDisposable();
        plantedService = ServiceFactory.createRetrofitService(PlantedService.class, URLS.EndPoint);
        mLayoutManager = new LinearLayoutManager(this);
    }



}
