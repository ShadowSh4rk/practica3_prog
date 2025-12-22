package dades;
import java.util.Date;
public class ActivitatOnline extends Activitats {
    private Date dataInici;
    private int periodeVisualitzacio;
    private String enllac; 

    public ActivitatOnline(String nom, String[]colectius, Date dataIniciInscripcio, Date dataFiInscripcio, Date edataInici, int periodeVisualitzacio, String enllac) {
        super(nom, colectius, dataIniciInscripcio, dataFiInscripcio, "Online");
        this.dataInici = dataInici;
        this.periodeVisualitzacio = periodeVisualitzacio;
        this.enllac = enllac; 
    }

    @Override
    public boolean teClasseAvui(Date avui){
        return false; 
    }

    @Override
    public boolean esActivaAvui(Date avui){
        Date dataFi=new Date(dataInici.getDia()+periodeVisualitzacio, dataInici,getMes(), dataInici.getAny());
dataInici.
    }

}
