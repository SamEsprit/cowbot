package eprit.tn.cowbot.Entity.User;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Sami on 17/05/2017.
 */

public class UserOutput implements Serializable {

    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;

    public UserOutput(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
