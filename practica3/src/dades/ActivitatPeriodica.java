package dades;

/**
 * Classe que representa una activitat periòdica.
 * 
 * Una activitat periòdica es realitza un dia concret de la setmana durant
 * un nombre determinat de setmanes consecutives, en un centre i ciutat
 * determinats.
 */

public class ActivitatPeriodica extends Activitats{
    private String diaSetmana; //dia de la setmana en que es realitza l'activitat
    private String horari;  //horari de l'activitat (exemple: "18:00-20:00")
    private Data dataInici; //Data inici de la primera sessio de l'activitat
    private int numSetmanes; //Nombre de setmanes que dura l'activitat
    private String centre; // Centre on es realitza l'activitat
    private String ciutat; //Ciutat on es realitza l'activitat
    private double preuTotal; //Preu total de l'activitat periodica

    private Data dataFi; //Data de finalitzacio de l'activitat periodica. Es calcula a partir de la data d'inici i el nombre de setmanes

    /**
     * Constructor de la classe ActivitatPeriodica.
     * 
     * @param nom Nom de l'activitat
     * @param colectius Col·lectius als quals va adreçada l'activitat
     * @param dataIniciInscripcio Data d'inici del període d'inscripció
     * @param dataFiInscripcio Data de finalització del període d'inscripció
     * @param diaSetmana Dia de la setmana en què es realitza l'activitat
     * @param horari Horari de l'activitat
     * @param dataInici Data d'inici de la primera sessió
     * @param numSetmanes Nombre de setmanes que dura l'activitat
     * @param centre Centre on es realitza l'activitat
     * @param ciutat Ciutat on es realitza l'activitat
     * @param limitPlaces Límit de places disponibles
     * @param preuTotal Preu total de l'activitat
     */

    public ActivitatPeriodica(String nom, String[] colectius, Data dataIniciInscripcio, Data dataFiInscripcio, String diaSetmana, String horari, Data dataInici, int numSetmanes, String centre, String ciutat, int limitPlaces,double preuTotal) {
            super(nom, colectius, dataIniciInscripcio, dataFiInscripcio, "Periodica");
            this.diaSetmana=diaSetmana;
            this.horari=horari; 
            this.dataInici=dataInici;
            this.numSetmanes=numSetmanes;
            this.centre=centre;
            this.ciutat=ciutat;
            this.limitPlaces=limitPlaces; 
            this.preuTotal=preuTotal;

            dataFi = dataInici.afegirDies(numSetmanes*7);
        }

    /**
     * Comprova si hi ha classe avui. Només hi ha classe si l'activitat està activa
     * i el dia d'avui coincideix amb el dia de la setmana de l'activitat.
     *
     * @param avui Data a comprovar
     * @return true si hi ha classe avui, false altrament
     */  
        @Override
        public boolean teClasseAvui(Data avui){
            if(!esActivaAvui(avui)){
                return false;
            }
            return avui.getDiaSetmana().equalsIgnoreCase(diaSetmana); 
        }

    /**
     * Comprova si l'activitat està activa avui, tenint en compte la data d'inici
     * i el nombre de setmanes que dura.
     *
     * @param avui Data a comprovar
     * @return true si l'activitat està activa avui, false altrament
     */
        @Override
        public boolean esActivaAvui(Data avui){
            return !avui.esAnterior(dataInici) && !avui.esPosterior(dataFi);
        }

    /**
     * Retorna el preu total de l'activitat periòdica.
     *
     * @return preu total de l'activitat
     */
        @Override
        public double getPreu(){
            return preuTotal; 
        }
    
    /**
    * Retorna el centre on es realitza l'activitat.
    * 
    * @return nom del centre
    */
        public String getCentre(){
            return centre;
        }

    /**
    * Retorna la ciutat on es realitza l'activitat.
    * 
    * @return nom de la ciutat
    */
        public String getCiutat(){
            return ciutat;
        }
    /**
    * Retorna l'horari de la classe.
    * 
    * @return horari en format cadena (ex. "18:00-20:00")
    */
        public String getHorari(){
            return horari;
        } 

    /** 
    * Retorna el nombre de setmanes que dura l'activitat.
    * 
    * @return número de setmanes
    */
        public int getNumSetmanes(){
            return numSetmanes; 
        }
    /**
    * Retorna la data d'inici de la primera sessió de l'activitat.
    * 
    * @return data d'inici
    */
        public Data getDataInici(){
            return dataInici; 
        }

    /**
     * Comprova si l'activitat periòdica ja ha finalitzat.
     * 
     * @param avui Data actual
     * @return true si l'activitat ha acabat, false altrament
     */ 
    public boolean haAcabat(Data avui){
        return avui.esPosterior(dataFi);
    }

    /**
    * Retorna el dia de la setmana en què es realitza l'activitat.
    * 
    * @return dia de la setmana 
    */
        public String getDiaSetmana(){
            return diaSetmana; 
        }

    /**
    * Retorna una representació en format de línia de text de l'activitat periòdica,
     * preparada per ser guardada en el fitxer de dades.
     *
    * El format és:
    * Periodica;[dades comunes de Activitats];diaSetmana;horari;dia;mes;any;numSetmanes;centre;ciutat;preuTotal
    *
    * On super.toString() aporta:
    * nom;colectius;dataIniciInscripcio;dataFiInscripcio;limitPlaces
    *
    * @return Cadena de text amb tots els atributs necessaris per reconstruir l'activitat.
    */
    @Override
    public String toString() {
    return "Periodica;" + super.toString() + ";" +
           diaSetmana + ";" +
           //horari + ";" +
           dataInici.getDia() + ";" + dataInici.getMes() + ";" + dataInici.getAny() + ";" +
           numSetmanes + ";" +
           centre + ";" +
           ciutat + ";" +
           preuTotal;
}

}
