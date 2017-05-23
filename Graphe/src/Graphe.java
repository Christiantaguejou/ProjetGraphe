import java.util.ArrayList;

/**
 * Created by Christian TAGUEJOU on 23/05/2017.
 */
public class Graphe {

    ArrayList<Commune> listeCommune;
    Commune depart;
    Commune arrive;

    public Graphe(ArrayList<Commune> listCommune, Commune depart, Commune arrive){
        this.listeCommune = listCommune;
        this.depart = depart;
        this.arrive = arrive;
    }

    public void gps(){
        ArrayList<Commune> chemin = new ArrayList<>();
        Commune comSuiv;
        Commune comDepart = depart;

        System.out.println("Dans gps");
        for(int i = 0; i < listeCommune.size(); i++) {
          //  while (comDepart != null) {
                if (comDepart != arrive) {
                    comSuiv = nextCommune(comDepart);
                    chemin.add(comSuiv);
                    comDepart = comSuiv;
                    System.out.println(comSuiv.getNom());
                }
          //  }
        }

    /*    for(int i = 0; i < chemin.size(); i++){
            System.out.println(chemin.get(i).getNom());
        }*/
    }

    public Commune nextCommune(Commune precedCommune){
      //  System.out.println("Dans next com");
        double distance = 999999;
        Commune nextCommune = new Commune();

        for(Commune com : listeCommune){
            Arc precedCom = new Arc(precedCommune, com);
            Arc arrivePreced = new Arc(precedCommune, arrive);
            Arc arriveCom = new Arc (com, arrive);

            if((precedCom.distanceVolOiseau() < distance) &&
                    ( arriveCom.distanceVolOiseau() <arrivePreced.distanceVolOiseau())){
                distance = precedCom.distanceVolOiseau();
                    nextCommune = com;
            }
        }
        return nextCommune;
    }
}
