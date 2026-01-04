package aplicacio;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Clase que implementa la interacció dels botons per a seleccionar un mes d'un any determinat.
 */
public class AccioBotonsMes implements ActionListener {
    private Visualitzacio finestra;     // finestra de visualització
    private int mes;                    // mes actual

    /**
     * Constructor del botó per a seleccionar un mes
     * @param finestra finestra de visualització
     * @param mes mes del botó
     */
    public AccioBotonsMes(Visualitzacio finestra, int mes) {
        this.finestra = finestra;
        this.mes = mes;
    }

    /**
     * En clicar el botó, actualitzem la etiqueta de la capçalera superior i activem la visualització dels dies del mes seleccionat.
     */
    public void actionPerformed(ActionEvent evt) {
        finestra.afegirMes(mes);
        finestra.visualitzarMes();
    }
}
