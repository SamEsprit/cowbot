package eprit.tn.cowbot.Entity.Planted;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Sami on 13/05/2017.
 */

public class PlantedInput implements Serializable{

    @SerializedName("plant_id")
    private Integer id;
    @SerializedName("Libelle")
    private String Libelle;
    @SerializedName("Description")
    private String Description;
    @SerializedName("Image")
    private String  Image;
    @SerializedName("Age")
    private Integer Age;
    @SerializedName("daysPassed")
    private Integer DayPassed;
    @SerializedName("pourcentage")
    private Float Pouratage;
    @SerializedName("pivot")
    private PivotPlanted pivotPlanted;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return Age;
    }

    public void setAge(Integer age) {
        Age = age;
    }

    public Integer getDayPassed() {
        return DayPassed;
    }

    public void setDayPassed(Integer dayPassed) {
        DayPassed = dayPassed;
    }

    public Float getPouratage() {
        return Pouratage;
    }

    public void setPouratage(Float pouratage) {
        Pouratage = pouratage;
    }


    public String getLibelle() {
        return Libelle;
    }

    public void setLibelle(String libelle) {
        Libelle = libelle;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public PivotPlanted getPivotPlanted() {
        return pivotPlanted;
    }

    public void setPivotPlanted(PivotPlanted pivotPlanted) {
        this.pivotPlanted = pivotPlanted;
    }
}
