package aplicacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccioBotonsAny implements ActionListener {
    private Visualitzacio finestra;
    private boolean esIncrement;    // true implica aumentar l'any, false implica disminuir l'any

    /**
     * Constructor
     * @param etiquetaAny
     * @param anyActual
     * @param esIncrement
     */
    public AccioBotonsAny(Visualitzacio finestra, boolean esIncrement) {
        this.finestra = finestra;
        this.esIncrement = esIncrement;
    }

    public void actionPerformed(ActionEvent evt) {
        finestra.actualitzarAny(esIncrement);
    }
}
