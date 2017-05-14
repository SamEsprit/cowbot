package eprit.tn.cowbot.Entity.Plant;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sami on 09/05/2017.
 */

public class PlantToPlantOutput {
    @SerializedName("user_id")
    private Integer user_id;
    @SerializedName("plant_id")
    private Integer plant_id;
    @SerializedName("X")
    private String X;
    @SerializedName("Y")
    private String Y;


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

    public String getX() {
        return X;
    }

    public void setX(String x) {
        X = x;
    }

    public String getY() {
        return Y;
    }

    public void setY(String y) {
        Y = y;
    }
}
