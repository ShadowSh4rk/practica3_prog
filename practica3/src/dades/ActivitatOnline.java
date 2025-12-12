package dades;

import java.util.Date;

public class ActivitatOnline extends Activitats {
    private Date dataInici;
    private int periodeVisualitzacio;
    private String enllaç; 

    public ActivitatOnline(String nom, String[]colectius, Date dataIniciInscripcio, Date dataFiInscripcio, Date dataInici, int periodeVisualitzacio, String enllaç) {
        super(nom, colectius, dataIniciInscripcio, dataFiInscripcio, "Online");
        this.dataInici = dataInici;
        this.periodeVisualitzacio = periodeVisualitzacio;
        this.enllaç = enllaç; 
    }
}
