package eprit.tn.cowbot.Entity;

/**
 * Created by Sami on 29/01/2017.
 */

public class Weather {


    private String Titre;
    private String value;
    private int ResId;
    private Cowbot cowbot;

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

    public Cowbot getCowbot() {
        return cowbot;
    }

    public void setCowbot(Cowbot cowbot) {
        this.cowbot = cowbot;
    }
}
