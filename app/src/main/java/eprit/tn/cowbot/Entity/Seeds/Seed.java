package eprit.tn.cowbot.Entity.Seeds;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Sami on 27/04/2017.
 */

public class Seed implements Serializable {


    @SerializedName("user_id")
    private Integer user_id;
    @SerializedName("plant_id")
    private Integer plant_id;
    @SerializedName("position")
    private String position;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getPlant_id() {
        return plant_id;
    }

    public void setPlant_id(Integer plant_id) {
        this.plant_id = plant_id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
