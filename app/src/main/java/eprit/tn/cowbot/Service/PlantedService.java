package eprit.tn.cowbot.Service;

import java.util.List;

import eprit.tn.cowbot.Entity.Planted.PlantedInput;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Sami on 13/05/2017.
 */

public interface PlantedService {

    @GET("Api/InProgressPlante/{id}")
    Observable<List<PlantedInput>> InProgressPlant(@Path("id")int id);
}
