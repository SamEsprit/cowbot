package eprit.tn.cowbot.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import eprit.tn.cowbot.Adapter.PlantedPlanFAdapter;
import eprit.tn.cowbot.Entity.PlantedPlant;
import eprit.tn.cowbot.R;


public class FinishedPlantedPlantsActivity extends AppCompatActivity {




    private RecyclerView history;
    private ProgressBar progressData;
    private TextView dataText;


    private RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

    private PlantedPlanFAdapter plantedPlanAdapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InitializeView();
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
     *set Data from database to RecyclerView
     */
    public void setDataToRecyclerView(List<PlantedPlant> plantArrayList) {
        history.setHasFixedSize(true);
        history.setLayoutManager(mLayoutManager);
        history.setItemAnimator(new DefaultItemAnimator());
        plantedPlanAdapter = new PlantedPlanFAdapter(plantArrayList);
        history.setAdapter(plantedPlanAdapter);
    }
}
