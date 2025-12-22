package dades;

public class Data {
    private int dia;
    private int mes;
    private int any;

    public Data(int dia, int mes, int any) {
        if(!esDataValida(dia, mes, any)){
            throw new IllegalArgumentException("Aquesta data es incorrecta");
        }
        this.dia = dia;
        this.mes = mes;
        this.any = any;
    }

    private boolean esDataValida(int dia, int mes, int any){
        if(mes<1 || mes>12 || dia<1){
            return false; 
        }
        int[] diesPerMes={31,28,31,30,31,30,31,31,30,31,30,31};
        if(esAnyDeTraspas(any)){
            diesPerMes[1]=29;
        }
        return (dia<=diesPerMes[mes-1]); 
    }

    private boolean esAnyDeTraspas(int any){
        return (any%4==0 && any%100!=0) || (any%400==0); 
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAny() {
        return any;
    }

    @Override
    public String toString() {
        return dia + "/" + mes + "/" + any;
    }

    public boolean esAnterior(Data altra){
        if(this.any!=altra.any){
            return this.any<altra.any; 
        }
        if(this.mes!=altra.mes){
            return this.mes<altra.mes; 
        }
       return this.dia<altra.dia; 
    }

    public boolean esPosterior(Data altra){
        if(this.any!=altra.any){
            return this.any>altra.any; 
        }
        if(this.mes!=altra.mes){
            return this.mes>altra.mes; 
        }
       return this.dia>altra.dia; 
    }

    public boolean esIgual(Data altra){
        return this.dia==altra.dia && this.mes==altra.mes && this.any==altra.any; 
    }
    }