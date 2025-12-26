package dades;

import java.util.*;

public class ActivitatPeriodica extends Activitats{
    private String diaSetmana;
    //private LocalTime horari; 
    private Date dataInici;
    private int numSetmanes;
    private String centre; 
    private String ciutat; 
    private double preuTotal; 

    public ActivitatPeriodica(String nom, String[] colectius, Date dataIniciInscripcio, Date dataFiInscripcio, 
        String diaSetmana, /*LocalTime horari,*/ Date dataInici, int numSetmanes, String centre, String ciutat, int limitPlaces,double preuTotal) {
            super(nom, colectius, dataIniciInscripcio, dataFiInscripcio, "Periodica");
            this.diaSetmana=diaSetmana;
            this.horari=horari; 
            this.dataInici=dataInici;
            this.numSetmanes=numSetmanes;
            this.centre=centre;
            this.ciutat=ciutat;
            this.limitPlaces=limitPlaces; 
            this.preuTotal=preuTotal;
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
            int diesTotals=numSetmanes*7; 
            Data dataFi = dataInici.afegirDies(diesTotals); 
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
    * Retorna el dia de la setmana en què es realitza l'activitat.
    * 
    * @return dia de la setmana 
    */
        public String getDiaSetmana(){
            return diaSetmana; 
        }
}
