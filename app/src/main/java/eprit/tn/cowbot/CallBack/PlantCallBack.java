package eprit.tn.cowbot.CallBack;

import java.util.List;

import eprit.tn.cowbot.Entity.Plant;

/**
 * Created by Sami on 02/02/2017.
 */

public interface PlantCallBack  {
     void onSuccess(List<Plant> plants);
     void onFail();
}
