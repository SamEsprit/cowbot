package eprit.tn.cowbot.Service;

import java.util.List;

import eprit.tn.cowbot.Entity.Seeds.SeedsInput;
import eprit.tn.cowbot.Entity.Seeds.SeedsOutput;
import io.reactivex.Completable;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Sami on 27/04/2017.
 */

public interface SeedService {


    @GET("Api/GrainByUser/{id}")
    Observable<List<SeedsInput>> getSeeds(@Path("id")int id);
    @POST("Api/SeedControl")
    Completable SeedControl(@Body SeedsOutput seedsOutput);
}
