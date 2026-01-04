package aplicacio;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dades.*;


/**
 * Clase que implementa la interacció dels botons per a filtrar.
 */
public class AccioBotoFiltrar implements ActionListener {
    private Visualitzacio finestra;     // finestra de visualització

    /**
     * Constructor del botó per a filtrar per activitats
     * @param finestra finestra de visualització
     */
    public AccioBotoFiltrar(Visualitzacio finestra) {
        this.finestra = finestra;
    }

    /**
     * En clicar el botó, actualitzem la etiqueta de la capçalera superior i activem la visualització dels dies del mes seleccionat.
     */
    public void actionPerformed(ActionEvent evt) {
        finestra.preguntarFiltres();
    }
}
