package eprit.tn.cowbot.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import eprit.tn.cowbot.Activity.FinishedPlantedPlantsActivity;
import eprit.tn.cowbot.Adapter.PlantedPlanAdapter;
import eprit.tn.cowbot.CallBack.PlantedPlantCallBack;
import eprit.tn.cowbot.Service.PlantedPlantService;
import eprit.tn.cowbot.Entity.PlantedPlant;
import eprit.tn.cowbot.MainApplication;
import eprit.tn.cowbot.R;


public class ProgressPlantedPlantsFragment extends Fragment {

    private RecyclerView PlantInProgress;
    private ArrayList<PlantedPlant> plantedPlantArrayList;
    private PlantedPlanAdapter plantedPlanAdapter;
    private PlantedPlantService plantedPlantService = new PlantedPlantService();
    private Context context = MainApplication.getContext();


    public ProgressPlantedPlantsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return InitializeView(inflater,container,savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getPlant();
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        switch (item.getItemId()) {
            case R.id.action_History:
                // do s.th.
                Intent intent = new Intent(getActivity(), FinishedPlantedPlantsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void getPlant() {

        plantedPlantService.getPlantedPlantsInProgress(new PlantedPlantCallBack() {
            @Override
            public void onSuccess(List<PlantedPlant> plants) {
                setDataToRecyclerView(plants);
            }

            @Override
            public void onFail() {
            }
        },1);
    }

    /*
     *set Data from database to RecyclerView
     */
    public void setDataToRecyclerView(List<PlantedPlant> plantedPlantArrayList) {
        PlantInProgress.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        PlantInProgress.setLayoutManager(mLayoutManager);
        PlantInProgress.setItemAnimator(new DefaultItemAnimator());
        plantedPlanAdapter = new PlantedPlanAdapter(plantedPlantArrayList);
        PlantInProgress.setAdapter(plantedPlanAdapter);
    }

    public View InitializeView(LayoutInflater inflater, ViewGroup container,
                               Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_progress, container, false);
        PlantInProgress = (RecyclerView) view.findViewById(R.id.PlantInProgress);
        return view;


    }
}
