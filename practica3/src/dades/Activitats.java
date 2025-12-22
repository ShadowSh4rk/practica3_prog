package dades;

public abstract class Activitats implements Laos {
private String nom;    //nom de l'activitat
private String[]colectius;   //PDI, PTGAS, Estudiants
protected Data dataIniciInscripcio; 
protected Data dataFiInscripcio; 
protected int limitPlaces;
private String tipus; // UnDia, Periodica, Online

    public Activitats(String nom, String[]colectius, Data dataIniciInscripcio, Data dataFiInscripcio, String tipus) {
        this.nom = nom;
        this.colectius = colectius; 
        this.dataIniciInscripcio = dataIniciInscripcio; 
        this.dataFiInscripcio = dataFiInscripcio;
        this.limitPlaces = Integer.MAX_VALUE;
        this.tipus = tipus;
    }

    public Data getDataIniciInscripcio(){
        return dataIniciInscripcio; 
    }

    public Data getDataFiInscripcio(){
        return dataFiInscripcio; 
    }

    public String [] getColectius(){
        return colectius; 
    }

    public String getTipus() {
        return tipus;
    }

    public String getNom() {
        return nom;
    }

    public int getLimitPlaces() {
        return limitPlaces;
    }

    public boolean esEnPeriodeInscripcio(Data avui){
        return !avui.esAnterior(dataIniciInscripcio) && !avui.esPosterior(dataFiInscripcio); 
    }

    public boolean acceptaColectiu(String col){
        for(int i=0; i<colectius.length; i++){
            if(colectius[i].equalsIgnoreCase(col)){
                return true; 
            }
        }
        return false; 
    }

    public abstract boolean teClasseAvui(Data avui);
    public abstract boolean esActivaAvui(Data avui); 
    public abstract double getPreu(); 

    @Override 
    public String toString(){
        return nom+" (" +tipus+ ")"; 
    }
}
