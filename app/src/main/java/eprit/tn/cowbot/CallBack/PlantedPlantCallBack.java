package eprit.tn.cowbot.CallBack;

import java.util.List;

import eprit.tn.cowbot.Entity.PlantedPlant;

/**
 * Created by Sami on 02/02/2017.
 */

public interface PlantedPlantCallBack {
    void onSuccess(List<PlantedPlant> plants);
    void onFail();
}
