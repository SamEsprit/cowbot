package eprit.tn.cowbot.DAO;

import android.os.AsyncTask;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import eprit.tn.cowbot.Entity.Plant;
import eprit.tn.cowbot.Entity.plantedPlant;
import eprit.tn.cowbot.Utils.JSONParser;


/**
 * Created by Sami on 16/01/2017.
 */

public class PlantedPlantDAO {


    JSONParser jParser= new JSONParser();
    JSONArray plantedPlant = null;


    public ArrayList<plantedPlant> getPlantedPlantProgress() throws ExecutionException, InterruptedException {
        return new LoadAllPlantedPlantProgress().execute().get();
    }
    public ArrayList<plantedPlant> getPlantedPlantFinished() throws ExecutionException, InterruptedException {
        return new LoadAllPlantedPlantFinished().execute().get();
    }

    class LoadAllPlantedPlantProgress extends AsyncTask<String, String, ArrayList<plantedPlant>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }
        protected ArrayList<plantedPlant> doInBackground(String... args) {

            ArrayList<plantedPlant> plantedPlantArrayList= new ArrayList<>();
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            // getting JSON string from URL
            JSONObject json = jParser.makeHttpRequest("http://www.theleadercenter.tn/Cowbot/JsonPHP/getPlantedPlantProgress.php", "GET",
                    params);
            try {
                // Parking found
                // Getting Array of Parkings
                plantedPlant = json.getJSONArray("plantedplants");
                // looping through All Products
                for (int i = 0; i < plantedPlant.length(); i++) {

                    JSONObject c = plantedPlant.getJSONObject(i);
                    plantedPlant pp = new plantedPlant();
                    pp.setDate_final(c.getString("date_final"));
                    pp.setDate_plantation(c.getString("date_plantation"));
                    pp.setPosition(c.getString("position"));
                    Plant p= new Plant();
                    p.setAge(c.getInt("age"));
                    p.setLibelle(c.getString("Libelle"));
                    p.setDescription(c.getString("Description"));
                    pp.setPlant(p);
                    plantedPlantArrayList.add(pp);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return plantedPlantArrayList;
        }
        protected void onPostExecute(String file_url) {


        }
    }
    class LoadAllPlantedPlantFinished extends AsyncTask<String, String, ArrayList<plantedPlant>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }
        protected ArrayList<plantedPlant> doInBackground(String... args) {

            ArrayList<plantedPlant> plantedPlantArrayList= new ArrayList<>();
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            // getting JSON string from URL
            JSONObject json = jParser.makeHttpRequest("http://www.theleadercenter.tn/Cowbot/JsonPHP/getPlantedPlantFinished.php", "GET",
                    params);
            try {
                // Parking found
                // Getting Array of Parkings
                plantedPlant = json.getJSONArray("plantedplants");
                // looping through All Products
                for (int i = 0; i < plantedPlant.length(); i++) {

                    JSONObject c = plantedPlant.getJSONObject(i);
                    plantedPlant pp = new plantedPlant();
                    pp.setDate_final(c.getString("date_final"));
                    pp.setDate_plantation(c.getString("date_plantation"));
                    pp.setPosition(c.getString("position"));
                    Plant p= new Plant();
                    p.setAge(c.getInt("age"));
                    p.setLibelle(c.getString("Libelle"));
                    p.setDescription(c.getString("Description"));
                    pp.setPlant(p);
                    plantedPlantArrayList.add(pp);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return plantedPlantArrayList;
        }
        protected void onPostExecute(String file_url) {


        }
    }
}
