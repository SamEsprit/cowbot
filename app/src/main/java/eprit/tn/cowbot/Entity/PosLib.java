package eprit.tn.cowbot.Entity;

/**
 * Created by Sami on 09/05/2017.
 */

public class PosLib {
    private String pos;
    private String libelle;

    public PosLib(String pos, String libelle) {
        this.pos = pos;
        this.libelle = libelle;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
