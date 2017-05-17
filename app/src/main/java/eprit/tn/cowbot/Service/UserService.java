package eprit.tn.cowbot.Service;

import eprit.tn.cowbot.Entity.User.UserInput;
import eprit.tn.cowbot.Entity.User.UserOutput;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Sami on 17/05/2017.
 */

public interface UserService {
    @POST("Api/Login")
    Observable<UserInput> SignIn(@Body UserOutput userOutput);
}
