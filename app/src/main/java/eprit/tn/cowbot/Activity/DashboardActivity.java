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
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eprit.tn.cowbot.Adapter.PlantAdapter;
import eprit.tn.cowbot.Entity.Plant;
import eprit.tn.cowbot.MainApplication;
import eprit.tn.cowbot.R;
import eprit.tn.cowbot.Utils.Const;

import static android.content.ContentValues.TAG;

public class DashboardActivity extends AppCompatActivity {

    private RecyclerView planted;
    private RecyclerView plant;
    private PlantAdapter plantAdapter;
    private GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3, GridLayoutManager.HORIZONTAL, false);
    private ProgressBar pDialog;

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
        planted = (RecyclerView) findViewById(R.id.planted);
        plant = (RecyclerView) findViewById(R.id.plant);
        pDialog = (ProgressBar) findViewById(R.id.progressBar);


    }
    /*
     * getData from database
     */
    public void getPlant() {
        showProgressBar();
        final List<Plant> plantArrayList = new ArrayList<>();
        JsonArrayRequest req = new JsonArrayRequest(Method.GET,Const.URL_getPlants,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject c = response.getJSONObject(i);
                                Plant p = new Plant();
                                p.setAge(c.getInt("age"));
                                p.setLibelle(c.getString("Libelle"));
                                p.setDescription(c.getString("Description"));
                                plantArrayList.add(p);
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
        }) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", "1");
                return params;
            }
        };
        // Adding request to request queue
        MainApplication.getInstance().addToRequestQueue(req,
                Const.tag_json_arry);
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
}
