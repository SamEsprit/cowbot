package eprit.tn.cowbot.Entity;

import eprit.tn.cowbot.Entity.Plant.Plant;

/**
 * Created by Sami on 15/01/2017.
 */

public class PlantedPlant {
    private int id;
    private Plant plant;
    private String position;
    private String date_plantation;
    private String date_final;
    private String Stat;
    private Cowbot cowbot;


    public PlantedPlant() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public String getDate_plantation() {
        return date_plantation;
    }

    public void setDate_plantation(String date_plantation) {
        this.date_plantation = date_plantation;
    }

    public String getDate_final() {
        return date_final;
    }

    public void setDate_final(String date_final) {
        this.date_final = date_final;
    }

    public String getStat() {
        return Stat;
    }

    public void setStat(String stat) {
        Stat = stat;
    }

    public Cowbot getCowbot() {
        return cowbot;
    }

    public void setCowbot(Cowbot cowbot) {
        this.cowbot = cowbot;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
