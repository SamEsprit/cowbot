package eprit.tn.cowbot.Entity;

/**
 * Created by Sami on 15/01/2017.
 */

public class Plant {
    private int id;
    private String libelle;
    private String image;
    private String image0;
    private String image25;
    private String image50;
    private String image75;
    private String image100;
    private int age;
    private String Description;

    public Plant() {
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

    public String getImage0() {
        return image0;
    }

    public void setImage0(String image0) {
        this.image0 = image0;
    }

    public String getImage25() {
        return image25;
    }

    public void setImage25(String image25) {
        this.image25 = image25;
    }

    public String getImage50() {
        return image50;
    }

    public void setImage50(String image50) {
        this.image50 = image50;
    }

    public String getImage75() {
        return image75;
    }

    public void setImage75(String image75) {
        this.image75 = image75;
    }

    public String getImage100() {
        return image100;
    }

    public void setImage100(String image100) {
        this.image100 = image100;
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
}
