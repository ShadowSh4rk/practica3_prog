package aplicacio;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dades.*;


/**
 * Clase que implementa la interacció dels botons per a seleccionar un dia d'un mes i any determinat.
 */
public class AccioBotonsDia implements ActionListener {
    private Visualitzacio finestra;     // finestra de visualització
    private Data dataActual;

    /**
     * Constructor del botó per a seleccionar un mes
     * @param finestra finestra de visualització
     * @param dataActual data específica a visualitzar
     */
    public AccioBotonsDia(Visualitzacio finestra, Data dataActual) {
        this.finestra = finestra;
        this.dataActual = dataActual;
    }

    /**
     * En clicar el botó, actualitzem la etiqueta de la capçalera superior i activem la visualització dels dies del mes seleccionat.
     */
    public void actionPerformed(ActionEvent evt) {
        new FinestraDia(dataActual);
    }
}
