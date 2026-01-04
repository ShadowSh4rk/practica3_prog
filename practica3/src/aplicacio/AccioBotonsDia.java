package aplicacio;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import dades.*;


/**
 * Clase que implementa la interacció dels botons per a seleccionar un dia d'un mes i any determinat.
 */
public class AccioBotonsDia implements ActionListener {
    private Visualitzacio finestra;     // finestra de visualització
    private Data dataActual;
    private LlistaActivitats llista;
    private boolean ocultarUnDia;
    private boolean ocultarPeriodic;
    private boolean ocultarOnline;

    /**
     * Constructor del botó per a seleccionar un mes
     * @param finestra finestra de visualització
     * @param dataActual data específica a visualitzar
     */
    public AccioBotonsDia(Visualitzacio finestra, Data dataActual, LlistaActivitats llista, boolean ocultarUnDia, boolean ocultarPeriodic, 
            boolean ocultarOnline) {
        this.finestra = finestra;
        this.dataActual = dataActual;
        this.llista = llista;
        this.ocultarUnDia = ocultarUnDia;
        this.ocultarPeriodic = ocultarPeriodic;
        this.ocultarOnline = ocultarOnline;
    }

    /**
     * En clicar el botó, actualitzem la etiqueta de la capçalera superior i activem la visualització dels dies del mes seleccionat.
     */
    public void actionPerformed(ActionEvent evt) {
        try {
            new FinestraDia(dataActual, llista, ocultarUnDia, ocultarPeriodic, ocultarOnline);
        } catch (IOException e) {
            System.out.println("ERROR: "+e);
        }
    }
}
