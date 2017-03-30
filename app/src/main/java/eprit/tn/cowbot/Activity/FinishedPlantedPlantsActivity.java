package eprit.tn.cowbot.Activity;

import android.content.Context;
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

import eprit.tn.cowbot.Adapter.PlantedPlanFAdapter;
import eprit.tn.cowbot.CallBack.AbstractServiceCallBack;
import eprit.tn.cowbot.Entity.PlantedPlant;
import eprit.tn.cowbot.MainApplication;
import eprit.tn.cowbot.R;
import eprit.tn.cowbot.Service.PlantedPlantService;


public class FinishedPlantedPlantsActivity extends AppCompatActivity {


    private Context context = MainApplication.getContext();

    private RecyclerView history;
    private ProgressBar progressData;
    private TextView dataText;


    private RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
    private PlantedPlantService plantedPlantService = new PlantedPlantService();
    private PlantedPlanFAdapter plantedPlanAdapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InitializeView();
        getPlant();
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

    }

    /*
     * getData from database
     */
    public void getPlant() {
        showProgressBar();
        plantedPlantService.getPlantedPlantsFinished(new AbstractServiceCallBack<PlantedPlant>() {
            @Override
            public void onSuccess(List plants) {
                setDataToRecyclerView(plants);
                hideProgressBar();
            }


            @Override
            public void onFail() {
                hideProgressBar();
            }

            @Override
            public void noData() {
                ShowNoData();
            }
        },2);
    }

    /*
     *set Data from database to RecyclerView
     */
    public void setDataToRecyclerView(List<PlantedPlant> plantArrayList) {
        history.setHasFixedSize(true);
        history.setLayoutManager(mLayoutManager);
        history.setItemAnimator(new DefaultItemAnimator());
        plantedPlanAdapter = new PlantedPlanFAdapter(plantArrayList);
        history.setAdapter(plantedPlanAdapter);
    }
    private void showProgressBar() {
        history.setVisibility(View.GONE);
        progressData.setVisibility(View.VISIBLE);
    }
    /*
     *hideProgressBar
     */
    private void hideProgressBar() {
        progressData.setVisibility(View.GONE);
        history.setVisibility(View.VISIBLE);
    }
    private  void ShowNoData()
    {
        progressData.setVisibility(View.GONE);
        history.setVisibility(View.GONE);
        dataText.setVisibility(View.VISIBLE);
    }
}
