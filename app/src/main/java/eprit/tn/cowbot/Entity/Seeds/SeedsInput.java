package eprit.tn.cowbot.Entity.Seeds;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Sami on 27/04/2017.
 */

public class SeedsInput implements Serializable {

    @SerializedName("Libelle")
    private String libelle;
    @SerializedName("Image")
    private String Image;
    @SerializedName("pivot")
    private Seed seed;



    public Seed getSeed() {
        return seed;
    }

    public void setSeed(Seed seed) {
        this.seed = seed;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    @Override
    public String toString() {
        return  libelle ;
    }
}
