package dades;

/**
 * Representa una data concreta amb dia, mes i any.
 * Proporciona mètodes per comparar dates i afegir dies.
 */

public class Data {
    private int dia;
    private int mes;
    private int any;

    /**
     * Crea una data amb el dia, mes i any indicats.
     *
     * @param dia Dia de la data
     * @param mes Mes de la data
     * @param any Any de la data
     * @throws IllegalArgumentException Si la data no és vàlida
     */
    public Data(int dia, int mes, int any) {
        if(!esDataValida(dia, mes, any)){
            throw new IllegalArgumentException("Aquesta data es incorrecta");
        }
        this.dia = dia;
        this.mes = mes;
        this.any = any;
    }

    /**
     * Comprova si una data és vàlida.
     *
     * @param dia Dia
     * @param mes Mes
     * @param any Any
     * @return true si la data és vàlida, false si no
     */
    private boolean esDataValida(int dia, int mes, int any){
        if(mes<1 || mes>12 || dia<1){
            return false; 
        }
        return dia <= diesDelMes(mes, any);
    }

    /**
     * Comprova si un any és de traspàs.
     *
     * @param any Any a comprovar
     * @return true si és de traspàs, false si no
     */
    private boolean esAnyDeTraspas(int any){
        return (any%4==0 && any%100!=0) || (any%400==0); 
    }

    /**
     * Retorna el dia de la data.
     * @return Dia
     */
    public int getDia() {
        return dia;
    }

    /**
     * Retorna el mes de la data
     * @return Mes
     */
    public int getMes() {
        return mes;
    }
    /**
     * Retorna l'any de la data
     * @return Any
     */
    public int getAny() {
        return any;
    }

    /**
     * Retorna l'any de la data
     * @return Data com a cadena
     */
    @Override
    public String toString() {
        return dia + "/" + mes + "/" + any;
    }

    /**
     * Comprova si aquesta data és anterior a una altra.
     * @param altra Data a comparar
     * @return true si aquesta data és anterior, false en cas contrari
     */
    public boolean esAnterior(Data altra){
        if(this.any!=altra.any){
            return this.any<altra.any; 
        }
        if(this.mes!=altra.mes){
            return this.mes<altra.mes; 
        }
       return this.dia<altra.dia; 
    }

    /**
     * Comprova si aquesta data és posterior a una altra.
     * @param altra Data a comparar
     * @return true si aquesta data és posterior, false en cas contrari
     */
    public boolean esPosterior(Data altra){
        if(this.any!=altra.any){
            return this.any>altra.any; 
        }
        if(this.mes!=altra.mes){
            return this.mes>altra.mes; 
        }
       return this.dia>altra.dia; 
    }

    /**
     * Comprova si aquesta data és igual a una altra.
     * @param altra Data a comparar
     * @return true si les dates són iguals, false en cas contrari
     */
    public boolean esIgual(Data altra){
        return this.dia==altra.dia && this.mes==altra.mes && this.any==altra.any; 
    }

    /**
     * Retorna el nombre de dies d'un mes determinat.
     *
     * @param mes Mes
     * @param any Any
     * @return Nombre de dies del mes
     */
    private int diesDelMes(int mes, int any) {
        switch (mes) {
            case 2:
                return esAnyDeTraspas(any) ? 29 : 28;
            case 4: case 6: case 9: case 11:
                return 30;
            default:
                return 31;
        }
    }

    /**
     * Retorna una nova data afegint un nombre de dies a aquesta data.
     * @param dies Nombre de dies a afegir (pot ser negatiu)
     * @return Nova data amb els dies afegits
     */
    public Data afegirDies(int dies) {
        int d = dia;
        int m = mes;
        int a = any;

        d += dies;

        while (d > diesDelMes(m, a)) {
            d -= diesDelMes(m, a);
            m++;
            if (m > 12) {
                m = 1;
                a++;
            }
        }

        while (d < 1) { 
            m--; 
        if (m < 1) { 
            m = 12; a--; 
        } 
        d += diesDelMes(m, a); 
    } 
        return new Data(d, m, a);
    }

    /**
     * Retorna el dia de la setmana de la data
     * 
     * @return Nom del dia de la setmana
     */
    public String getDiaSetmana() {
    int diaMes = dia;
    int mesAny = mes;
    int anyComplet = any;

    // Ajustar enero y febrero como meses 13 y 14 del año anterior
    if (mesAny < 3) {
        mesAny += 12;
        anyComplet--;
    }

    int anyDelSegle = anyComplet % 100;   // k
    int segle = anyComplet / 100;         // j

    // Fórmula de Zeller
    int resultat = (diaMes 
                    + (13 * (mesAny + 1)) / 5 
                    + anyDelSegle 
                    + (anyDelSegle / 4) 
                    + (segle / 4) 
                    + (5 * segle)) % 7;

    String[] dies = {
        "Dissabte", "Diumenge", "Dilluns", 
        "Dimarts", "Dimecres", "Dijous", "Divendres"
    };

    return dies[resultat];
}

    }