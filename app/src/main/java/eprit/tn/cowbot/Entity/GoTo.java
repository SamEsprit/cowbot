package eprit.tn.cowbot.Entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Sami on 18/05/2017.
 */

public class GoTo  implements Serializable {

    @SerializedName("user_id")
    private Integer user_id;
    @SerializedName("X")
    private String GoToX;
    @SerializedName("Y")
    private String GoToY;
    @SerializedName("speed")
    private String speed;

    public GoTo(Integer user_id, String goToX, String goToY, String speed) {
        this.user_id = user_id;
        GoToX = goToX;
        GoToY = goToY;
        this.speed = speed;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getGoToX() {
        return GoToX;
    }

    public void setGoToX(String goToX) {
        GoToX = goToX;
    }

    public String getGoToY() {
        return GoToY;
    }

    public void setGoToY(String goToY) {
        GoToY = goToY;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }
}
