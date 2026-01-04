package aplicacio;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Clase que implementa la interacció del botó que indica un mes i any determinats a visualitzar.
 */
public class AccioBotoData implements ActionListener {
    private Visualitzacio finestra;     // finestra de visualització

    /**
     * Constructor del botó per a seleccionar un mes i un any
     * @param finestra finestra de visualització
     */
    public AccioBotoData(Visualitzacio finestra) {
        this.finestra = finestra;
    }

    /**
     * En clicar el botó, obrim un diàleg per a preguntar les dades que vol consultar l'usuari.
     */
    public void actionPerformed(ActionEvent evt) {
        finestra.preguntarDades();
    }
}
