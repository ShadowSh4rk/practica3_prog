package dades;

public class ActivitatUnDia extends Activitats {
    private Data data; 
    private String horari; 
    private String ciutat; 
    private double preu; 

    public ActivitatUnDia(String nom, String[] colectius, Data dataIniciInscripcio, Data dataFiInscripcio, Data data, String horari, String ciutat, int limitPlaces, double preu) {
        super(nom, colectius, dataIniciInscripcio, dataFiInscripcio, "UnDia");
        this.data = data;
        this.horari = horari;
        this.ciutat = ciutat; 
        this.limitPlaces=limitPlaces;
        this.preu = preu; 
    }

    public String getCiutat(){
        return ciutat; 
    }

    public String getHorari(){
        return horari; 
    }

    public Data getData(){
        return data; 
    }

    @Override
    public boolean teClasseAvui(Data avui){
        return avui.esIgual(data); 
    }

    @Override
    public boolean esActivaAvui(Data avui){
        return avui.esIgual(data);
    }

    @Override
    public double getPreu(){
        return preu; 
    }
}
