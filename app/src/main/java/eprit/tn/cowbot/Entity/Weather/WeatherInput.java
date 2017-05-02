package eprit.tn.cowbot.Entity.Weather;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Sami on 27/04/2017.
 */

public class WeatherInput implements Serializable {

    static final long serialVersionUID = 1;

    @SerializedName("temperature")
    private String Temperature;
    @SerializedName("rain")
    private String Rain;
    @SerializedName("wind")
    private String Wind;
    @SerializedName("humidity")
    private String Humidity;
    @SerializedName("windDirection")
    private String WindDirection;
    @SerializedName("latitude")
    private String Latitude;
    @SerializedName("longitude")
    private String Longitude;

    public String getTemperature() {
        return Temperature;
    }

    public void setTemperature(String temperature) {
        Temperature = temperature;
    }

    public String getRain() {
        return Rain;
    }

    public void setRain(String rain) {
        Rain = rain;
    }

    public String getWind() {
        return Wind;
    }

    public void setWind(String wind) {
        Wind = wind;
    }

    public String getHumidity() {
        return Humidity;
    }

    public void setHumidity(String humidity) {
        Humidity = humidity;
    }

    public String getWindDirection() {
        return WindDirection;
    }

    public void setWindDirection(String windDirection) {
        WindDirection = windDirection;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }
}
