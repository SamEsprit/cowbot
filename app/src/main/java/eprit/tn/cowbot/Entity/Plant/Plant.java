package eprit.tn.cowbot.Entity.Plant;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Sami on 15/01/2017.
 */

public class Plant implements Serializable {
    @SerializedName("id")
    private int id;
    @SerializedName("Libelle")
    private String libelle;
    @SerializedName("Image")
    private String image;
    @SerializedName("Age")
    private int age;
    @SerializedName("Description")
    private String Description;

    public Plant() {
    }

    public Plant(String libelle) {
        this.libelle = libelle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return libelle ;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Plant plant = (Plant) o;
        return libelle.equals(plant.libelle);

    }

    @Override
    public int hashCode() {
        return libelle.hashCode();
    }
}
