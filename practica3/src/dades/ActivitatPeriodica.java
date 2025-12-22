package dades;

public class ActivitatPeriodica extends Activitats{
    private String diaSetmana;
    //private LocalTime horari; 
    private Data dataInici;
    private int numSetmanes;
    private String centre; 
    private String ciutat; 
    private double preuTotal; 

    public ActivitatPeriodica(String nom, String[] colectius, Data dataIniciInscripcio, Data dataFiInscripcio, 
        String diaSetmana, /*LocalTime horari,*/ Data dataInici, int numSetmanes, String centre, String ciutat, int limitPlaces,double preuTotal) {
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
