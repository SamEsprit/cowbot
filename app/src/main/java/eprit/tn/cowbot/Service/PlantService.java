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

import eprit.tn.cowbot.CallBack.AbstractServiceCallBack;
import eprit.tn.cowbot.Entity.Plant;
import eprit.tn.cowbot.Interface.IManagement;
import eprit.tn.cowbot.MainApplication;
import eprit.tn.cowbot.Utils.Const;

import static android.content.ContentValues.TAG;

/**
 * Created by Sami on 16/01/2017.
 */

public class PlantService implements IManagement {


    public void getAllPlants(final AbstractServiceCallBack<Plant> onCallBack)
    {
        final List<Plant> plantArrayList = new ArrayList<>();

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, Const.URL_getPlants,null,
                new Response.Listener<JSONObject>() {
                    JSONArray plants=null;
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            plants=response.getJSONArray("plants");
                            for (int i = 0; i < plants.length(); i++) {

                                JSONObject c = plants.getJSONObject(i);
                                Plant p = new Plant();
                                p.setAge(c.getInt("age"));
                                p.setLibelle(c.getString("Libelle"));
                                p.setDescription(c.getString("Description"));
                                p.setImage(c.getString("image"));
                                plantArrayList.add(p);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        onCallBack.onSuccess(plantArrayList);

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

    @Override
    public void updatePlant(int id) {

    }

    @Override
    public void removePlant(int id) {

    }
}
