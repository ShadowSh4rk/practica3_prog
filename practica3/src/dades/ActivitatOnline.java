package dades;
import java.util.*;

public class ActivitatOnline extends Activitats {
    private Date dataInici;
    private int periodeVisualitzacio;
    private String enllac; 

    public ActivitatOnline(String nom, String[]colectius, Date dataIniciInscripcio, Date dataFiInscripcio, Date dataInici, int periodeVisualitzacio, String enllac) {
        super(nom, colectius, dataIniciInscripcio, dataFiInscripcio, "Online");
        this.dataInici = dataInici;
        this.periodeVisualitzacio = periodeVisualitzacio;
        this.enllac = enllac; 
    }

    /**
     * Retorna l'enllaç per accedir a l'activitat.
     * @return Enllaç de l'activitat
     */
    public String getEnllac(){
        return enllac; 
    }

    /**
     * Les activitats online no tenen classe presencial, sempre retorna false.
     * @param avui Data a comprovar
     * @return false
     */
    @Override
    public int teClasseAvui(Date avui){
        return false; 
    }

    /**
     * Comprova si l'activitat està activa avui, tenint en compte la data d'inici i el període de visualització.
     * @param avui Data a comprovar
     * @return true si l'activitat està disponible avui, false altrament
     */
    @Override
    public int esActivaAvui(Date avui){
        Date dataFi=new Date(dataInici.getDia()+periodeVisualitzacio, dataInici.getMes(), dataInici.getAny());
    }

    /**
    * Retorna una representació en format de línia de text de l'activitat online,
    * preparada per ser guardada en el fitxer de dades.
    * 
    * El format és:
    * Online;[dades comunes de Activitats];dia;mes;any;periodeVisualitzacio;enllac
    *
    * On super.toString() aporta:
    * nom;colectius;dataIniciInscripcio;dataFiInscripcio;limitPlaces
    *
    * @return Cadena de text amb tots els atributs necessaris per reconstruir l'activitat.
    */
    @Override
    public String toString() {
    return "Online;" + super.toString() + ";" +
           dataInici.getDia() + ";" + dataInici.getMes() + ";" + dataInici.getAny() + ";" +
           periodeVisualitzacio + ";" +
           enllac;
}


}
