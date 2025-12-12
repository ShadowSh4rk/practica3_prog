package dades;

import java.util.Date;

public class ActivitatPeriodica extends Activitats{
    private String diaSetmana;
    //private LocalTime horari; 
    private Date dataInici;
    private int numSetmanes;
    private String centre; 
    private String ciutat; 
    private int limitPlaces;
    private double preuTotal; 

    public ActivitatPeriodica(String nom, String[] colectius, Date dataIniciInscripcio, Date dataFiInscripcio, 
        String diaSetmana, /*LocalTime horari,*/ Date dataInici, int numSetmanes, String centre, String ciutat, int limitPlaces,double preuTotal) {
            super(nom, colectius, dataIniciInscripcio, dataFiInscripcio, "Periodica");
            this.diaSetmana=diaSetmana;
            //this.horari=horari; 
            this.dataInici=dataInici;
            this.numSetmanes=numSetmanes;
            this.centre=centre;
            this.ciutat=ciutat;
            this.limitPlaces=limitPlaces;
            this.preuTotal=preuTotal;
        }
}
