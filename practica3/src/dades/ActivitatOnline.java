package dades;
import java.util.*;

public class ActivitatOnline extends Activitats {
    private Date dataInici;
    private int periodeVisualitzacio;
    private String enllac; 

    public ActivitatOnline(String nom, String[]colectius, Date dataIniciInscripcio, Date dataFiInscripcio, Date dataInici, int periodeVisualitzacio, String enllac) {
        super(nom, colectius, dataIniciInscripcio, dataFiInscripcio, "Online");
        this.dataInici = dataInici;
        this.periodeVisualitzacio = periodeVisualitzacio;
        this.enllac = enllac; 
    }

    @Override
    public int teClasseAvui(Date avui){
        return false; 
    }

    @Override
    public int esActivaAvui(Date avui){
        Date dataFi=new Date(dataInici.getDia()+periodeVisualitzacio, dataInici.getMes(), dataInici.getAny());
    }

}
