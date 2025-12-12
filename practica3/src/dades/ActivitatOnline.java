package dades;

import java.time.LocalDate;
import java.time.LocalTime;

public class ActivitatOnline extends Activitats {
    private LocalDate dataInici;
    private int periodeVisualitzacio;
    private String enllaç; 

    public ActivitatOnline(String nom, String[]colectius, LocalDate dataIniciInscripcio, LocalDate dataFiInscripcio, LocalDate dataInici, int periodeVisualitzacio, String enllaç){
        super(nom, colectius, dataIniciInscripcio, dataFiInscripcio, "Online");
        this.dataInici=dataInici;
        this.periodeVisualitzacio=periodeVisualitzacio;
        this.enllaç=enllaç; 
    }
}
