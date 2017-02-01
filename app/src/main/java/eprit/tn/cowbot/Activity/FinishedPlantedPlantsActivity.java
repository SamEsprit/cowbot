package eprit.tn.cowbot.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
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

import eprit.tn.cowbot.Adapter.PlantedPlanFAdapter;
import eprit.tn.cowbot.Entity.Plant;
import eprit.tn.cowbot.Entity.plantedPlant;
import eprit.tn.cowbot.MainApplication;
import eprit.tn.cowbot.R;
import eprit.tn.cowbot.Utils.Const;

import static android.content.ContentValues.TAG;


public class FinishedPlantedPlantsActivity extends AppCompatActivity {
    private RecyclerView history;
    private PlantedPlanFAdapter plantedPlanAdapter;
    private Context context = MainApplication.getContext();
    private ProgressBar pDialog;
    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);

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
        final List<plantedPlant> plantArrayList = new ArrayList<>();
        JsonArrayRequest req = new JsonArrayRequest(Const.URL_getPlantedPlantFinished,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("responce: " , response.toString());
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject c = response.getJSONObject(i);
                                plantedPlant pp = new plantedPlant();
                                pp.setDate_final(c.getString("date_final"));
                                pp.setDate_plantation(c.getString("date_plantation"));
                                pp.setPosition(c.getString("position"));
                                Plant p = new Plant();
                                p.setAge(c.getInt("age"));
                                p.setLibelle(c.getString("Libelle"));
                                p.setDescription(c.getString("Description"));
                                pp.setPlant(p);
                                plantArrayList.add(pp);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        setDataToRecyclerView(plantArrayList);
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
                Const.tag_json_arry);
    }

    /*
     *set Data from database to RecyclerView
     */
    public void setDataToRecyclerView(List<plantedPlant> plantArrayList) {
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
