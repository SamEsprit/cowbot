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

import java.util.List;

import eprit.tn.cowbot.Adapter.PlantedPlanFAdapter;
import eprit.tn.cowbot.CallBack.PlantedPlantCallBack;
import eprit.tn.cowbot.Service.PlantedPlantService;
import eprit.tn.cowbot.Entity.PlantedPlant;
import eprit.tn.cowbot.MainApplication;
import eprit.tn.cowbot.R;


public class FinishedPlantedPlantsActivity extends AppCompatActivity {
    private RecyclerView history;
    private PlantedPlanFAdapter plantedPlanAdapter;
    private Context context = MainApplication.getContext();
    private ProgressBar pDialog;
    private RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
    private PlantedPlantService plantedPlantService = new PlantedPlantService();
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
        pDialog = (ProgressBar) findViewById(R.id.progressBar);
    }

    /*
     * getData from database
     */
    public void getPlant() {
        showProgressBar();
        plantedPlantService.getPlantedPlantsFinished(new PlantedPlantCallBack() {
            @Override
            public void onSuccess(List<PlantedPlant> plants) {
                setDataToRecyclerView(plants);
                hideProgressBar();
            }

            @Override
            public void onFail() {
                hideProgressBar();
            }
        },1);
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
        pDialog.setVisibility(View.VISIBLE);
    }
    /*
     *hideProgressBar
     */
    private void hideProgressBar() {
        pDialog.setVisibility(View.GONE);
        history.setVisibility(View.VISIBLE);
    }
}
