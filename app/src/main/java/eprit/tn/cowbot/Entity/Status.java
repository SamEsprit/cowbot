package eprit.tn.cowbot.Entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Sami on 17/05/2017.
 */

public class Status implements Serializable {

    @SerializedName("state")
    private String  state;

    public Status(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
