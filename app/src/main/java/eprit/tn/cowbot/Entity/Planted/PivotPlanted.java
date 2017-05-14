package eprit.tn.cowbot.Entity.Planted;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sami on 14/05/2017.
 */

public class PivotPlanted {

    @SerializedName("X")
    private String X;
    @SerializedName("Y")
    private String Y;
    @SerializedName("updated_at")
    private String updated_at;

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

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}