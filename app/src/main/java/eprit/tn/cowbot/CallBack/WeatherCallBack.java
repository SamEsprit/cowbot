package eprit.tn.cowbot.CallBack;

import java.util.List;

import eprit.tn.cowbot.Entity.Weather;

/**
 * Created by Sami on 02/02/2017.
 */

public interface WeatherCallBack {
    void onSuccess(List<Weather> weathers,String temperature);
    void onFail();
}
