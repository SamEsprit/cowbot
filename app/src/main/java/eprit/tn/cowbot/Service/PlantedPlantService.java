package eprit.tn.cowbot.Service;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import eprit.tn.cowbot.CallBack.PlantedPlantCallBack;
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


    public void getPlantedPlantsFinished(final PlantedPlantCallBack plantedPlantCallBack,  int id) {
        final List<PlantedPlant> plantArrayList = new ArrayList<>();
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET,Const.URL_getPlantedPlantFinished+"?id="+id,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        JSONArray FinishedPlantedPlant= null;
                        try {
                            FinishedPlantedPlant = response.getJSONArray("finishedPlantedPlants");
                        for (int i = 0; i < FinishedPlantedPlant.length(); i++) {

                                JSONObject c = FinishedPlantedPlant.getJSONObject(i);
                                PlantedPlant pp = new PlantedPlant();
                                pp.setDate_final(c.getString("date_final"));
                                pp.setDate_plantation(c.getString("date_plantation"));
                                pp.setPosition(c.getString("position"));
                                Plant p = new Plant();
                                p.setAge(c.getInt("age"));
                                p.setLibelle(c.getString("Libelle"));
                                p.setDescription(c.getString("Description"));
                                pp.setPlant(p);
                                plantArrayList.add(pp);
                        }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        plantedPlantCallBack.onSuccess(plantArrayList);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e(TAG,error.networkResponse);
                plantedPlantCallBack.onFail();
            }
        })
       ;
        // Adding request to request queue
        MainApplication.getInstance().addToRequestQueue(req);
    }

    public void getPlantedPlantsInProgress(final PlantedPlantCallBack plantedPlantCallBack,  int id) {
        final List<PlantedPlant> plantArrayList = new ArrayList<>();
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET,Const.URL_getPlantedPlantInProgress+"?id="+id,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        JSONArray FinishedPlantedPlant= null;
                        try {
                            FinishedPlantedPlant = response.getJSONArray("PlantedPlantsInProgress");
                            for (int i = 0; i < FinishedPlantedPlant.length(); i++) {

                                JSONObject c = FinishedPlantedPlant.getJSONObject(i);
                                PlantedPlant pp = new PlantedPlant();
                                pp.setDate_final(c.getString("date_final"));
                                pp.setDate_plantation(c.getString("date_plantation"));
                                pp.setPosition(c.getString("position"));
                                Plant p = new Plant();
                                p.setAge(c.getInt("age"));
                                p.setLibelle(c.getString("Libelle"));
                                p.setDescription(c.getString("Description"));
                                pp.setPlant(p);
                                plantArrayList.add(pp);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        plantedPlantCallBack.onSuccess(plantArrayList);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                plantedPlantCallBack.onFail();
            }
        })
                ;
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
