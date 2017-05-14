package eprit.tn.cowbot.Fragment;

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
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import eprit.tn.cowbot.Activity.FinishedPlantedPlantsActivity;
import eprit.tn.cowbot.Adapter.PlantedPlanAdapter;
import eprit.tn.cowbot.Entity.Planted.PlantedInput;
import eprit.tn.cowbot.Factory.ServiceFactory;
import eprit.tn.cowbot.R;
import eprit.tn.cowbot.Service.PlantedService;
import eprit.tn.cowbot.Utils.URLS;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class ProgressPlantedPlantsFragment extends Fragment {

    private RecyclerView PlantInProgress;
    private PlantedPlanAdapter plantedPlanAdapter;


    private TextView dataText;
    private ProgressBar progressData;

    private CompositeDisposable mCompositeDisposable;
    private PlantedService plantedService;
    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());

    public ProgressPlantedPlantsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
        InitializeUtils();
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
        getData();
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


    private void getData() {
        mCompositeDisposable.add(plantedService.InProgressPlant(10)
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

    /*
     *set Data from database to RecyclerView
     */
    private void setDataToRecyclerView(List<PlantedInput> plantedPlantArrayList) {
        PlantInProgress.setHasFixedSize(true);

        PlantInProgress.setLayoutManager(mLayoutManager);
        PlantInProgress.setItemAnimator(new DefaultItemAnimator());
        plantedPlanAdapter = new PlantedPlanAdapter(plantedPlantArrayList);
        PlantInProgress.setAdapter(plantedPlanAdapter);
        PlantInProgress.setVisibility(View.VISIBLE);

    }

    private View InitializeView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_progress, container, false);
        PlantInProgress = (RecyclerView) view.findViewById(R.id.PlantInProgress);
        progressData = (ProgressBar) view.findViewById(R.id.progressData);
        dataText = (TextView) view.findViewById(R.id.dataText);
        PlantInProgress.setVisibility(View.GONE);
        progressData.setVisibility(View.VISIBLE);
        dataText.setVisibility(View.GONE);
        return view;


    }

    private void InitializeUtils() {
        mCompositeDisposable = new CompositeDisposable();
        plantedService = ServiceFactory.createRetrofitService(PlantedService.class, URLS.EndPoint);
    }


}
