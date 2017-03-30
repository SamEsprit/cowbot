package eprit.tn.cowbot.Service;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eprit.tn.cowbot.CallBack.AbstractServiceCallBack;
import eprit.tn.cowbot.Entity.Plant;
import eprit.tn.cowbot.Entity.PlantedPlant;
import eprit.tn.cowbot.Interface.IManagement;
import eprit.tn.cowbot.MainApplication;
import eprit.tn.cowbot.Utils.Const;

import static android.content.ContentValues.TAG;

/**
 * Created by Sami on 16/01/2017.
 */

public class PlantedPlantService implements IManagement {


    public void getPlantedPlantsFinished(final AbstractServiceCallBack<PlantedPlant> plantedPlantCallBack,final int id) {
        final List<PlantedPlant> plantArrayList = new ArrayList<>();
        StringRequest req = new StringRequest(Request.Method.POST, Const.URL_getPlantedPlantFinished ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        JSONArray FinishedPlantedPlant = null;
                        Integer statut;
                        try {
                            statut = new JSONObject(response).getJSONArray("Statuts").getInt(0);
                            Log.i("status", String.valueOf(statut));
                            if (statut == 1) {
                            FinishedPlantedPlant = new JSONObject(response).getJSONArray("finishedPlantedPlants");
                            for (int i = 0; i < FinishedPlantedPlant.length(); i++) {

                                JSONObject c = FinishedPlantedPlant.getJSONObject(i);
                                PlantedPlant pp = new PlantedPlant();
                                pp.setDate_final(c.getString("date_final"));
                                pp.setDate_plantation(c.getString("date_plantation"));
                                pp.setPosition(c.getString("position"));
                                Plant p = new Plant();
                                p.setImage(c.getString("image"));
                                p.setAge(c.getInt("age"));
                                p.setLibelle(c.getString("Libelle"));
                                p.setDescription(c.getString("Description"));
                                pp.setPlant(p);
                                plantArrayList.add(pp);
                            }
                            plantedPlantCallBack.onSuccess(plantArrayList);
                            } else if (statut == 0) {
                                plantedPlantCallBack.noData();

                            } else {
                                plantedPlantCallBack.onFail();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e(TAG, error.networkResponse);
                plantedPlantCallBack.onFail();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", String.valueOf(id));
                return params;
            }
        };
        // Adding request to request queue
      MainApplication.getInstance().addToRequestQueue(req);
    }

    public void getPlantedPlantsInProgress(final AbstractServiceCallBack<PlantedPlant> plantedPlantCallBack, final int id) {
        final List<PlantedPlant> plantArrayList = new ArrayList<>();
        StringRequest req = new StringRequest(Request.Method.POST, Const.URL_getPlantedPlantInProgress,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        JSONArray PlantedPlantsInProgress;
                        Integer statut;
                        try {

                            statut = new JSONObject(response).getJSONArray("Statuts").getInt(0);
                            Log.i("status", String.valueOf(statut));
                            if (statut == 1) {
                                PlantedPlantsInProgress = new JSONObject(response).getJSONArray("PlantedPlantsInProgress");
                                for (int i = 0; i < PlantedPlantsInProgress.length(); i++) {

                                    JSONObject c = PlantedPlantsInProgress.getJSONObject(i);
                                    PlantedPlant pp = new PlantedPlant();
                                    pp.setDate_final(c.getString("date_final"));
                                    pp.setDate_plantation(c.getString("date_plantation"));
                                    pp.setPosition(c.getString("position"));
                                    Plant p = new Plant();
                                    p.setImage(c.getString("image"));
                                    p.setAge(c.getInt("age"));
                                    p.setLibelle(c.getString("Libelle"));
                                    p.setDescription(c.getString("Description"));
                                    pp.setPlant(p);
                                    plantArrayList.add(pp);
                                }
                                plantedPlantCallBack.onSuccess(plantArrayList);
                            } else if (statut == 0) {
                                plantedPlantCallBack.noData();

                            } else {
                                plantedPlantCallBack.onFail();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                plantedPlantCallBack.onFail();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", String.valueOf(id));
                return params;
            }
        };
        // Adding request to request queue
        MainApplication.getInstance().addToRequestQueue(req);
    }

    @Override
    public void updatePlant(int id) {

    }

    @Override
    public void removePlant(int id) {

    }
}
