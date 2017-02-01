package eprit.tn.cowbot.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import eprit.tn.cowbot.Adapter.PlantedPlanFAdapter;
import eprit.tn.cowbot.DAO.PlantedPlantDAO;
import eprit.tn.cowbot.Entity.plantedPlant;
import eprit.tn.cowbot.MainApplication;
import eprit.tn.cowbot.R;


public class HistoryActivity extends AppCompatActivity {
    private RecyclerView history;
    private ArrayList<plantedPlant> plantedPlantArrayList;
    private PlantedPlanFAdapter plantedPlanAdapter;
    private PlantedPlantDAO plantedPlantDAO = new PlantedPlantDAO();
    private Context context = MainApplication.getContext();



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_history);

        try {
            plantedPlantArrayList = plantedPlantDAO.getPlantedPlantFinished();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        history = (RecyclerView)findViewById(R.id.history);
        history.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        history.setLayoutManager(mLayoutManager);
        history.setItemAnimator(new DefaultItemAnimator());
        plantedPlanAdapter = new PlantedPlanFAdapter(plantedPlantArrayList);
        history.setAdapter(plantedPlanAdapter);
    }

}
