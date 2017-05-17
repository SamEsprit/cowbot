package eprit.tn.cowbot.Service;

import eprit.tn.cowbot.Entity.Controlle;
import io.reactivex.Completable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Sami on 16/05/2017.
 */

public interface ControlleService {
    @POST("Api/SetControlles")
    Completable controlle(@Body Controlle controlle);

}
