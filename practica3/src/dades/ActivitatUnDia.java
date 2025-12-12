import java.time.LocalDate;
import java.time.LocalTime;

public class ActivitatUnDia extends Activitats{
private LocalDate data; 
private LocalTime horari; 
private String ciutat; 
private int limitPlaces;
private double preu; 

public ActivitatUnDia(String nom, String[] colectius, LocalDate dataIniciInscripcio, LocalDate dataFiInscripcio, LocalDate data, LocalTime horari, String ciutat, int limitPlaces, double preu){
    super(nom, colectius, dataIniciInscripcio, dataFiInscripcio, "UnDia");
    this.data=data;
    this.horari=horari;
    this.ciutat=ciutat;
    this.limitPlaces=limitPlaces; 
    this.preu=preu; 
}
}
