package eprit.tn.cowbot.Activity;
/**
 * Created by Sami on 15/01/2017.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import eprit.tn.cowbot.Adapter.PlantAdapter;
import eprit.tn.cowbot.CallBack.AbstractServiceCallBack;
import eprit.tn.cowbot.Entity.Plant;
import eprit.tn.cowbot.R;
import eprit.tn.cowbot.Service.PlantService;

public class DashboardActivity extends AppCompatActivity {

    private RecyclerView planted;
    private RecyclerView plant;
    private PlantAdapter plantAdapter;
    private GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3, GridLayoutManager.HORIZONTAL, false);
    private ProgressBar pDialog;
    private PlantService plantService = new PlantService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        InitializeView();
        getPlant();
    }
    /*
     * InitializeView du layout
     */
    public void InitializeView() {
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        planted = (RecyclerView) findViewById(R.id.planted);
        plant = (RecyclerView) findViewById(R.id.plant);
        pDialog = (ProgressBar) findViewById(R.id.progressBar);


    }
    /*
     * getData from database
     */
    public void getPlant() {
        showProgressBar();
        plantService.getAllPlants(new AbstractServiceCallBack<Plant>() {

            public void onSuccess(List plants) {
                setDataToRecyclerView(plants);
                hideProgressBar();
            }

        });
    }
    /*
     *set Data from database to RecyclerView
     */
    public void setDataToRecyclerView(List<Plant> plantArrayList) {
        plant.setHasFixedSize(true);
        plant.setLayoutManager(gridLayoutManager);
        plant.setItemAnimator(new DefaultItemAnimator());
        plantAdapter = new PlantAdapter(plantArrayList);
        plant.setAdapter(plantAdapter);
    }
    /*
     * showProgressBar
     */
    private void showProgressBar() {
        plant.setVisibility(View.GONE);
        pDialog.setVisibility(View.VISIBLE);
    }
    /*
     *hideProgressBar
     */
    private void hideProgressBar() {
        pDialog.setVisibility(View.GONE);
        plant.setVisibility(View.VISIBLE);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                setVisible(true);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
