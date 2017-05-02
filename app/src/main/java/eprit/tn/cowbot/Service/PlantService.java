package eprit.tn.cowbot.Service;


import java.util.List;

import eprit.tn.cowbot.Entity.Plant.Plant;
import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Sami on 27/04/2017.
 */

public interface PlantService {

    @GET("Api/Plant")
    Observable<List<Plant>> getPlants();
}
