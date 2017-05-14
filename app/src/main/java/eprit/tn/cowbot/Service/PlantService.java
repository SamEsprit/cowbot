package eprit.tn.cowbot.Service;


import java.util.List;

import eprit.tn.cowbot.Entity.Plant.Plant;
import eprit.tn.cowbot.Entity.Plant.PlantToPlantOutput;
import eprit.tn.cowbot.Entity.Seeds.SeedsInput;
import io.reactivex.Completable;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Sami on 27/04/2017.
 */

public interface PlantService {

    @GET("Api/Plant")
    Observable<List<Plant>> getPlants();

    @GET("Api/PlantedByUser/{id}")
    Observable<List<SeedsInput>> getPlanteds(@Path("id")int id);

    @POST("Api/PlantedControl")
    Completable addPlant(@Body PlantToPlantOutput plantToPlantOutput);
}
