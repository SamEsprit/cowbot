package eprit.tn.cowbot.Service;

import java.util.List;

import eprit.tn.cowbot.Entity.Controlle;
import eprit.tn.cowbot.Entity.GoTo;
import eprit.tn.cowbot.Entity.Status;
import io.reactivex.Completable;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Sami on 16/05/2017.
 */

public interface ControlleService {
    @POST("Api/SetControlles")
    Completable controlle(@Body Controlle controlle);

    @GET("Api/GetControllesFor/{user_id}")
    Observable<List<Status>> GetStatusControlle(@Path("user_id") Integer user_id);

    @GET("Api/SetStateControllesFor/{user_id}/{state}")
    Completable StatusControlle(@Path("user_id") Integer user_id, @Path("state") String state);

    @POST("Api/GoToAxis")
    Completable GoTo(@Body GoTo goTo);

}
