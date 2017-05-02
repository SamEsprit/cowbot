package eprit.tn.cowbot.Service;

import eprit.tn.cowbot.Entity.Weather.WeatherInput;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Sami on 27/04/2017.
 */

public interface WeatherService {

    @GET("Api/Weather/{id}")
    Observable<WeatherInput> getWeather(@Path("id")int id);
}
