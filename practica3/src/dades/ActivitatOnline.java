package dades;

public class ActivitatOnline extends Activitats {
    private Data dataInici;
    private int periodeVisualitzacio;
    private String enllac; 

    private Data dataFi;

    public ActivitatOnline(String nom, String[]colectius, Data dataIniciInscripcio, Data dataFiInscripcio, Data dataInici, int periodeVisualitzacio, String enllac) {
        super(nom, colectius, dataIniciInscripcio, dataFiInscripcio, "Online");
        this.dataInici = dataInici;
        this.periodeVisualitzacio = periodeVisualitzacio;
        this.enllac = enllac; 

        dataFi=new Data(dataInici.getDia()+periodeVisualitzacio, dataInici.getMes(), dataInici.getAny());
    }

    /**
     * Retorna l'enllaç per accedir a l'activitat.
     * @return Enllaç de l'activitat
     */
    public String getEnllac(){
        return enllac; 
    }

    /**
     * Retorna la data d'inici de l'activitat.
     * @return Data d'inici de l'activitat
     */
    public Data getDataInici() {
        return dataInici;
    }

    /**
     * Retorna el periode de visualització de l'activitat (nombre de dies).
     * @return Nombre de dies de visualització de l'activitat després de la seva data d'inici
     */
    public int getPeriodeVisualitzacio() {
        return periodeVisualitzacio;
    }

    /**
     * Les activitats online no tenen classe presencial, sempre retorna false.
     * @param avui Data a comprovar
     * @return false
     */
    @Override
    public boolean teClasseAvui(Data avui){
        return false; 
    }

    /**
     * Comprova si l'activitat està activa avui, tenint en compte la data d'inici i el període de visualització.
     * @param avui Data a comprovar
     * @return true si l'activitat està disponible avui, false altrament
     */
    @Override
    public boolean esActivaAvui(Data avui){
        return ((avui.esPosterior(dataInici))&&(avui.esAnterior(dataFi)));
    }

    public double getPreu(){
        return 0; 
    }

    public boolean haAcabat(Data avui){
        return avui.esPosterior(dataFi);
    }

    /**
    * Retorna una representació en format de línia de text de l'activitat online,
    * preparada per ser guardada en el fitxer de dades.
    * 
    * El format és:
    * Online;[dades comunes de Activitats];dia;mes;any;periodeVisualitzacio;enllac
    *
    * On super.toString() aporta:
    * nom;colectius;dataIniciInscripcio;dataFiInscripcio;limitPlaces
    *
    * @return Cadena de text amb tots els atributs necessaris per reconstruir l'activitat.
    */
    @Override
    public String toString() {
    return "Online;" + super.toString() + ";" +
           dataInici.getDia() + ";" + dataInici.getMes() + ";" + dataInici.getAny() + ";" +
           periodeVisualitzacio + ";" +
           enllac;
}


}
