package aplicacio;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Clase que implementa la interacció dels botons per a seleccionar un dia d'un mes i any determinat.
 */
public class AccioBotonsDia implements ActionListener {
    private Visualitzacio finestra;     // finestra de visualització
    private int dia;                    // dia actual

    /**
     * Constructor del botó per a seleccionar un mes
     * @param finestra finestra de visualització
     * @param dia dia del botó
     */
    public AccioBotonsDia(Visualitzacio finestra, int dia) {
        this.finestra = finestra;
        this.dia = dia;
    }

    /**
     * En clicar el botó, actualitzem la etiqueta de la capçalera superior i activem la visualització dels dies del mes seleccionat.
     */
    public void actionPerformed(ActionEvent evt) {
        
    }
}
