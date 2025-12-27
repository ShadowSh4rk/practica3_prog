package dades;

import java.util.*;

public class ActivitatUnDia extends Activitats {
    private Date data; 
    private String horari; 
    private String ciutat; 
    private double preu; 

    public ActivitatUnDia(String nom, String[] colectius, Date dataIniciInscripcio, Date dataFiInscripcio, Date data, String horari, String ciutat, int limitPlaces, double preu) {
        super(nom, colectius, dataIniciInscripcio, dataFiInscripcio, "UnDia");
        this.data = data;
        this.horari = horari;
        this.ciutat = ciutat; 
        this.limitPlaces=limitPlaces;
        this.preu = preu; 
    }
    /**
     * Retorna la ciutat on es realitza l'activitat.
     *
     * @return Nom de la ciutat
     */
    public String getCiutat(){
        return ciutat; 
    }
    /**
     * Retorna l'horari de l'activitat.
     *
     * @return Horari en format cadena (ex. "10:00-12:00")
     */
    public String getHorari(){
        return horari; 
    }

    public Date getData(){
        return data; 
    }

    //compareTo: si retorna 0, son iguals. Si retorna menor a 0 es anterior i si retorna major a 0 es posterior

    @Override
    public int teClasseAvui(Date avui){
        return avui.compareTo(data); 
    }

    /**
     * Indica si l'activitat està activa en la data indicada.
     *
     * @param avui Data a comprovar
     * @return true si l'activitat està activa avui, false en cas contrari
     */
    @Override
    public int esActivaAvui(Date avui){ 
        return avui.compareTo(data);
    }

    /**
     * Retorna el preu de l'activitat.
     *
     * @return Preu
     */

    @Override
    public double getPreu(){
        return preu; 
    }

    /**
    * Retorna una representació en format de línia de text de l'activitat d'un dia,
    * preparada per ser guardada en el fitxer de dades.
    *
    * El format és:
    * UnDia;[dades comunes de Activitats];dia;mes;any;horari;ciutat;preu
    *
    * On super.toString() aporta:
    * nom;colectius;dataIniciInscripcio;dataFiInscripcio;limitPlaces
    *
    * @return Cadena de text amb tots els atributs necessaris per reconstruir l'activitat.
    */
    @Override
    public String toString() {
    return "UnDia;" + super.toString() + ";" +
           data.getDia() + ";" + data.getMes() + ";" + data.getAny() + ";" +
           horari + ";" +
           ciutat + ";" +
           preu;
}


    
}
