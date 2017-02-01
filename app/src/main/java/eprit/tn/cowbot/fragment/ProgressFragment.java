package eprit.tn.cowbot.fragment;

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
import android.widget.ProgressBar;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import eprit.tn.cowbot.Activity.HistoryActivity;
import eprit.tn.cowbot.Adapter.PlantedPlanAdapter;
import eprit.tn.cowbot.DAO.PlantedPlantDAO;
import eprit.tn.cowbot.Entity.Plant;
import eprit.tn.cowbot.Entity.plantedPlant;
import eprit.tn.cowbot.MainApplication;
import eprit.tn.cowbot.R;
import eprit.tn.cowbot.Utils.Const;

import static android.content.ContentValues.TAG;


public class ProgressFragment extends Fragment {

    private RecyclerView PlantInProgress;
    private ArrayList<plantedPlant> plantedPlantArrayList;
    private PlantedPlanAdapter plantedPlanAdapter;
    private PlantedPlantDAO plantedPlantDAO = new PlantedPlantDAO();
    private Context context = MainApplication.getContext();
    private ProgressBar pDialog;
    private String tag_json_arry = "array";
    public ProgressFragment() {
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
        View view = inflater.inflate(R.layout.fragment_progress, container, false);
        PlantInProgress = (RecyclerView) view.findViewById(R.id.PlantInProgress);
        pDialog = (ProgressBar) view.findViewById(R.id.progressBar);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



    }

    @Override
    public void onCreateOptionsMenu(
            Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        switch (item.getItemId()) {
            case R.id.action_History:
                // do s.th.
                Intent intent= new Intent(getActivity(), HistoryActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void getPlant() {
        showProgressBar();
        final List<plantedPlant> plantedPlantArrayList = new ArrayList<>();
        JsonArrayRequest req = new JsonArrayRequest(Const.URL_getPlantedPlantInProgress,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject c = response.getJSONObject(i);
                                plantedPlant pp = new plantedPlant();
                                pp.setDate_final(c.getString("date_final"));
                                pp.setDate_plantation(c.getString("date_plantation"));
                                pp.setPosition(c.getString("position"));
                                Plant p= new Plant();
                                p.setAge(c.getInt("age"));
                                p.setLibelle(c.getString("Libelle"));
                                p.setDescription(c.getString("Description"));
                                pp.setPlant(p);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        setDataToRecyclerView(plantedPlantArrayList);
                        hideProgressBar();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        });
        // Adding request to request queue
        MainApplication.getInstance().addToRequestQueue(req,
                tag_json_arry);
    }
    /*
         *set Data from database to RecyclerView
         */
    public void setDataToRecyclerView(List<plantedPlant> plantArrayList) {
        PlantInProgress.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        PlantInProgress.setLayoutManager(mLayoutManager);
        PlantInProgress.setItemAnimator(new DefaultItemAnimator());
        plantedPlanAdapter = new PlantedPlanAdapter(plantedPlantArrayList);
        PlantInProgress.setAdapter(plantedPlanAdapter);
    }
    /*
     * showProgressBar
     */
    private void showProgressBar() {
        PlantInProgress.setVisibility(View.GONE);
        pDialog.setVisibility(View.VISIBLE);
    }
    /*
     *hideProgressBar
     */
    private void hideProgressBar() {
        pDialog.setVisibility(View.GONE);
        PlantInProgress.setVisibility(View.VISIBLE);
    }

}