package sample;

public class Module {
    private Integer id;
    private String nom;

    public Module(Integer id, String nom) {
        this.id = id;
        this.nom = nom;
    }


    public Integer getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }
}
