package aplicacio;


import java.awt.event.*;
import javax.swing.*;


/**
 * Clase que implementa la interacció dels botons per a acceptar o cancelar en una finestra.
 */
public class AccioBotoAcceptarCancelarFiltre implements ActionListener {
    private EntrarFiltres dialeg;     // finestra de visualització
    private boolean esAcceptar;         // boolean que indica si el botó es per acceptar les dades o és per cancelar i tancar la finestra

    /**
     * Constructor del botó per a acceptar o cancelar en el dialeg per Entrar Filtres.
     * @param dialeg dialeg on volem acceptar les seves dades o cancelar-lo
     */
    public AccioBotoAcceptarCancelarFiltre(EntrarFiltres dialeg) {
        this.dialeg = dialeg;
    }

    /**
     * En clicar el botó, acceptem la informació donada o cancelem i no fem res.
     */
    public void actionPerformed(ActionEvent evt) {

    }
}
