package eprit.tn.cowbot.Entity.User;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Sami on 17/05/2017.
 */

public class UserInput implements Serializable {

    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String FirstName;
    @SerializedName("longitude")
    private String longitude;
    @SerializedName("latitude")
    private String latitude;


    public UserInput(Integer id, String firstName, String longitude, String latitude) {
        this.id = id;
        FirstName = firstName;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }
}
