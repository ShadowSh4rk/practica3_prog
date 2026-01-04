package aplicacio;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Clase que implementa la interacció dels botons per a seleccionar un mes d'un any determinat.
 */
public class AccioBotoData implements ActionListener {
    private Visualitzacio finestra;     // finestra de visualització

    /**
     * Constructor del botó per a seleccionar un mes
     * @param finestra finestra de visualització
     */
    public AccioBotoData(Visualitzacio finestra) {
        this.finestra = finestra;
    }

    /**
     * En clicar el botó, actualitzem la etiqueta de la capçalera superior i activem la visualització dels dies del mes seleccionat.
     */
    public void actionPerformed(ActionEvent evt) {
        finestra.preguntarDades();
    }
}
