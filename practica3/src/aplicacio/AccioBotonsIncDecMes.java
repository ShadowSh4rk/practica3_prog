package aplicacio;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Clase que implementa la interacció dels botons de la capçalera superior per a incrementar o decrementar un any utilitzant.
 */
public class AccioBotonsIncDecMes implements ActionListener {
    private Visualitzacio finestra; // finestra de visualització
    private boolean esIncrement;    // true implica aumentar l'any, false implica disminuir el mes

    /**
     * Constructor del botó per a incrementar/decrementar l'any
     * @param finestra finestra de visualització
     * @param esIncrement boolean que indica si hem d'incrementar (true) o decrementar (false) el valor del mes
     */
    public AccioBotonsIncDecMes(Visualitzacio finestra, boolean esIncrement) {
        this.finestra = finestra;
        this.esIncrement = esIncrement;
    }

    /**
     * En clicar el botó, actualitzem l'etiqueta de la capçalera superior i modifiquem el valor del mes actual.
     */
    public void actionPerformed(ActionEvent evt) {
        finestra.actualitzarMes(esIncrement);
    }
}
