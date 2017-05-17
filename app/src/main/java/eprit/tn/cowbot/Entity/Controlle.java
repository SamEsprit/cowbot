package eprit.tn.cowbot.Entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Sami on 17/05/2017.
 */

public class Controlle implements Serializable {

    @SerializedName("user_id")
    private Integer user_id;
    @SerializedName("type")
    private String Type;

    public Controlle(Integer user_id, String type) {
        this.user_id = user_id;
        Type = type;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
