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

    public String getCiutat(){
        return ciutat; 
    }

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

    @Override
    public int esActivaAvui(Date avui){ 
        return avui.compareTo(data);
    }

    @Override
    public double getPreu(){
        return preu; 
    }
}
