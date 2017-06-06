package Communes;

/**
 * Created by chris on 19/05/2017.
 */
public class Commune {

    private String id;
    private String nom;
    private int population;
    private double longitude;
    private double latitude;

    public Commune(String id, String nom, int population, double longitude, double latitude){
        this.id = id;
        this.nom = nom;
        this.population = population;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Commune(){

    }

    public void afficheCommune(){
        System.out.println("id: "+id+"\nNom: "+nom+"\nPopulation: "+population+"\nLongitude: "+longitude+"\nLatitude: "+latitude);
    }

    public String getId(){
        return id;
    }

    public int getPopulation() {
        return population;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getNom(){
        return nom;

    }

}
