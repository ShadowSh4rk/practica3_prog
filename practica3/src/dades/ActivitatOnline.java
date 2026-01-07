package dades;

/**
 * Classe que representa una activitat en línia.
 * 
 * Una activitat en línia té una data d'inici a partir de la qual està disponible
 * durant un període determinat de dies. No té sessions presencials ni classes
 * associades a una data concreta.
 * 
 */

public class ActivitatOnline extends Activitats {
    private Data dataInici; //data inici de l'activitat online
    private int periodeVisualitzacio; // Periode de visualitzacio de l'activitat (en dies)
    private String enllac; // enllaç per accedir al contingut de l'activitat online

    private Data dataFi; //data de finalitzacio de l'activitat online. Es calcula a partir de la data d'inici i el periode de visualitzacio

    /**
     * Constructor de la classe ActivitatOnline.
     * 
     * @param nom Nom de l'activitat
     * @param colectius Col·lectius als quals va adreçada l'activitat
     * @param dataIniciInscripcio Data d'inici del període d'inscripció
     * @param dataFiInscripcio Data de finalització del període d'inscripció
     * @param dataInici Data d'inici de l'activitat online
     * @param periodeVisualitzacio Nombre de dies que l'activitat estarà disponible
     * @param enllac Enllaç per accedir a l'activitat
     */

    public ActivitatOnline(String nom, String[]colectius, Data dataIniciInscripcio, Data dataFiInscripcio, Data dataInici, int periodeVisualitzacio, String enllac) {
        super(nom, colectius, dataIniciInscripcio, dataFiInscripcio, "Online");
        this.dataInici = dataInici;
        this.periodeVisualitzacio = periodeVisualitzacio;
        this.enllac = enllac; 

        dataFi = dataInici.afegirDies(periodeVisualitzacio);
    }

    /**
     * Retorna l'enllaç per accedir a l'activitat.
     * @return Enllaç de l'activitat
     */
    public String getEnllac(){
        return enllac; 
    }

    /**
     * Retorna la data d'inici de l'activitat.
     * @return Data d'inici de l'activitat
     */
    public Data getDataInici() {
        return dataInici;
    }

    /**
     * Retorna la data de fi de l'activitat.
     * @return Data de fi de l'activitat
     */
    public Data getDataFi() {
        return dataFi;
    }

    /**
     * Retorna el periode de visualització de l'activitat (nombre de dies).
     * @return Nombre de dies de visualització de l'activitat després de la seva data d'inici
     */
    public int getPeriodeVisualitzacio() {
        return periodeVisualitzacio;
    }

    /**
     * Les activitats online no tenen classe presencial, sempre retorna false.
     * @param avui Data a comprovar
     * @return false
     */
    @Override
    public boolean teClasseAvui(Data avui){
        return false; 
    }

    /**
     * Comprova si l'activitat està activa avui, tenint en compte la data d'inici i el període de visualització.
     * @param avui Data a comprovar
     * @return true si l'activitat està disponible avui, false altrament
     */
    @Override
    public boolean esActivaAvui(Data avui){
        return ((avui.esPosterior(dataInici)) && (avui.esAnterior(dataFi)));
    }

    /**
     * Retorna el preu de l'activitat online.
     * @return 0 
     */
    public double getPreu(){
        return 0; //Retorna 0 perque en aquest cas no hi ha cost associat
    }

    /**
     * Comprova si l'activitat online ja ha finalitzat.
     * 
     * @param avui Data actual
     * @return true si l'activitat ha acabat, false altrament
     */
    
    public boolean haAcabat(Data avui){
        return avui.esPosterior(dataFi);
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
