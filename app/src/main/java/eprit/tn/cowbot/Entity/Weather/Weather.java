package eprit.tn.cowbot.Entity.Weather;

import java.io.Serializable;

/**
 * Created by Sami on 29/01/2017.
 */

public class Weather implements Serializable {


    private String Titre;
    private String value;
    private int ResId;
    public Weather(String titre, String value, int resId) {
        Titre = titre;
        this.value = value;
        ResId = resId;
    }

    public String getTitre() {
        return Titre;
    }

    public void setTitre(String titre) {
        Titre = titre;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getResId() {
        return ResId;
    }

    public void setResId(int resId) {
        ResId = resId;
    }


}
