package dades;

/**
 *Classe abstracta que representa una activitat del programa Benestar URV.
 *Les activitats poden ser d'un dia, periodiques o online
 *  
 */

public abstract class Activitats implements Laos {
private String nom;    //nom de l'activitat (unic)
private String[]colectius;   //Col·lectius que poden accedir: PDI, PTGAS, Estudiants
protected Data dataIniciInscripcio; 
protected Data dataFiInscripcio; 
protected int limitPlaces; //Nombre maxim de places disponibles
private String tipus; // UnDia, Periodica, Online

/**
 * Constructor de la classe Activitats
 * 
 * @param nom Nom de l'activitat
 * @param colectius Array amb els col·lectius als quals s'ofereix
 * @param dataIniciInscripcio Data d'inici del periode d'inscripcio
 * @param dataFiInscripcio Dara final del periode d'inscripcio
 * @param tipus Tipus de l'activitat: "UnDia", "Periodica" o "Online"
 */
    public Activitats(String nom, String[]colectius, Data dataIniciInscripcio, Data dataFiInscripcio, String tipus) {
        this.nom = nom;
        this.colectius = colectius; 
        this.dataIniciInscripcio = dataIniciInscripcio; 
        this.dataFiInscripcio = dataFiInscripcio;
        this.limitPlaces = Integer.MAX_VALUE;
        this.tipus = tipus;
    }

    /**
     * Retorna la data d'inici del periode d'inscripcio
     * @return Data d'inici d'inscripcio
     */
    public Data getDataIniciInscripcio(){
        return dataIniciInscripcio; 
    }

    /**
     * Retorna la data final del periode d'inscripcio
     * @return Data final d'inscripcio
     */
    public Data getDataFiInscripcio(){
        return dataFiInscripcio; 
    }

    /**
     * Retorna els col·lectius als quals s'ofereix l'activitat
     * @return Array de col·lectius
     */
    public String [] getColectius(){
        return colectius; 
    }

    /**
     * Retorna el tipus d'activitat: "UnDia", "Periodica", "Online"
     * @return Tipus d'activitat
     */
    public String getTipus() {
        return tipus;
    }

     /**
     * Retorna el nom de l'activitat.
     * @return Nom de l'activitat
     */
    public String getNom() {
        return nom;
    }

    /**
     * Retorna el límit de places disponibles.
     * @return Límite de places
     */
    public int getLimitPlaces() {
        return limitPlaces;
    }

    /**
     * Comprova si l'activitat està dins del període d'inscripció per a la data indicada.
     * @param avui Data a comprovar
     * @return true si la data està dins del període d'inscripció, false altrament
     */
    public boolean esEnPeriodeInscripcio(Data avui){
        return !avui.esAnterior(dataIniciInscripcio) && !avui.esPosterior(dataFiInscripcio); 
    }

    /**
     * Comprova si l'activitat accepta un col·lectiu determinat.
     * @param col Nom del col·lectiu a comprovar
     * @return true si l'activitat accepta el col·lectiu, false altrament
     */
    public boolean acceptaColectiu(String col){
        for(int i=0; i<colectius.length; i++){
            if(colectius[i].equalsIgnoreCase(col)){
                return true; 
            }
        }
        return false; 
    }

    /**
     * Comprova si hi ha classe avui (només rellevant per activitats presencials).
     * Les subclasses han d'implementar aquest mètode segons el tipus d'activitat.
     * @param avui Data a comprovar
     * @return true si hi ha classe avui, false altrament
     */
    public abstract boolean teClasseAvui(Data avui);
    
    /**
     * Comprova si l'activitat està activa avui.
     * Les subclasses han d'implementar aquest mètode segons el tipus d'activitat.
     * @param avui Data a comprovar
     * @return true si l'activitat està activa avui, false altrament
     */
    public abstract boolean esActivaAvui(Data avui); 
    /**
     * Retorna el preu de l'activitat.
     * Les subclasses han d'implementar aquest mètode segons el tipus d'activitat.
     * @return Preu de l'activitat
     */
    public abstract double getPreu(); 

    /**
     * Retorna una representació en String de l'activitat.
     * @return Nom de l'activitat i tipus entre parèntesis
     */
    @Override 
    public String toString() {
        // Convertir el array de col·lectius a un String "PDI,PTGAS,Estudiants" 
        String col = ""; 
        for (int i = 0; i < colectius.length; i++) { 
            col += colectius[i]; 
            if (i < colectius.length - 1) { 
                col += ","; // añadir coma entre elementos 
                } 
            }
    return nom + ";" + col + ";" + dataIniciInscripcio.getDia() + ";" 
    + dataIniciInscripcio.getMes() + ";" + dataIniciInscripcio.getAny() + ";" 
    + dataFiInscripcio.getDia() + ";" + dataFiInscripcio.getMes() + ";" 
    + dataFiInscripcio.getAny() + ";" + limitPlaces; 
}
 }
    