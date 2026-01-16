// PAQUET
package aplicacio;


// IMPORTS JAVA
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// IMPORTS PAQUETS LOCALS
import dades.*;


/**
 * Classe amb la visualització gràfica per a mostrar els detalls complets d'una activitat d'un dia.
 */
public class FinestraDetallActivitat extends JFrame {
    private JPanel panellTitol;     // Panell que contindrá el títol (nom) de l'activitat corresponent
    private JPanel panellPrincipal; // Panell que contindrá la informació de l'activitat corresponent
    private JPanel panellSud;       // Panell que contindrá el botó inferior
    private JLabel titol;           // Text que contindrá el títol (nom) de l'activitat corresponent
    private JButton botoTancar;     // Botó que utilitzarem per a tancar l'activitat


    /**
     * Mètode constructor per a crear una finestra de visualització de l'informació de la activitat determinada.
     * @param activitat
     */
    public FinestraDetallActivitat(Activitats activitat) {
        super("Detalls de l'activitat: "+activitat.getNom());   // Assignem el títol de la finestra
        setSize(420, 550);                        // Tamany de la finestra
        setLayout(new BorderLayout());                          // Disposició dels elements en direccions cardinals

        crearTitol(activitat);              // Creem el títol de l'activitat
        crearPanellPrincipal(activitat);    // Creem el panell principal

        add(panellTitol, BorderLayout.NORTH);       // Afegim el títol a dalt de la finestra
        add(panellPrincipal, BorderLayout.CENTER);  // Afegim la informació de l'activitat a la resta de la finestra

        // Creem el botó per a tancar
        panellSud = new JPanel(new FlowLayout(FlowLayout.CENTER));
        botoTancar = new JButton("Tancar");
        botoTancar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        panellSud.add(botoTancar);
        add(panellSud, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }


    // MÉTODES AUXILIARS PER A GESTIONAR LA FINESTRA DE VISUALITZACIÓ DE LA INFORMACIÓ DE L'ACTIVITAT
    /**
     * Mètode per a crear el panell del títol de l'activitat i centrar el text.
     * @param activitat activitat de la qual volem visualitzar el seu títol
     */
    private void crearTitol(Activitats activitat) {
        panellTitol = new JPanel(new FlowLayout(FlowLayout.CENTER));    // Utilitzem un FlowLayout.CENTER per centrar el titol
        titol = new JLabel(activitat.getNom());                         // El títol correspon al nom de l'activitat corresponent
        titol.setFont(new Font("Dialog", Font.BOLD, 18));    // Assignem una font de text destacable respecte a la resta
        panellTitol.add(titol);                                         // Afegim el títol per a centrarlo en el panell
    }

    /**
     * Mètode per a crear el panell principal que contindrá la informació de l'activitat.
     * @param activitat activitat de la qual volem visualitzar la seva informació
     */
    private void crearPanellPrincipal(Activitats activitat) {
        // Incializem el panell principal com un GridLayout amb una sola columna i 0 files (permet ilimitades)
        panellPrincipal = new JPanel(new GridLayout(0, 1, 0, 6));

        // Afegim el tipus d'activitat
        String tipus = activitat.getTipus();
        panellPrincipal.add(crearText("Tipus: " + tipus));

        // Aconseguim la llista de col·lectius als que va dirigida l'activitat i els afegim
        String colectiu[] = activitat.getColectius();
        String colectius = colectiu[0];
        for (int i = 1; i < colectiu.length; i++) colectius += (", "+colectiu[i]);
        panellPrincipal.add(crearText(("Col·lectius: "+colectius)));

        // Afegim les dades entre les que està actiu el periode d'inscripció
        panellPrincipal.add(crearText("Període d'inscripció: "+activitat.getDataIniciInscripcio()+" - "+activitat.getDataFiInscripcio()));

        // Segons el tipus d'activitat corresponent, afegim la informació que contingui
        if (activitat instanceof ActivitatUnDia) afegirUnDia(panellPrincipal, (ActivitatUnDia) activitat);
        else if (activitat instanceof ActivitatPeriodica) afegirPeriodica(panellPrincipal, (ActivitatPeriodica) activitat);
        else if (activitat instanceof ActivitatOnline) afegirOnline(panellPrincipal, (ActivitatOnline) activitat);
    }

    /**
     * Mètode per afegir al panell principal les dades d'una activitat d'un dia.
     * @param panell panell on hem d'afegir la informació d'una activitat d'un dia
     * @param aud activitat d'un dia corresponent
     */
    private void afegirUnDia(JPanel panell, ActivitatUnDia aud) {
        panell.add(crearText("Data: "+aud.getData()));                      // Data quan es realitza l'activitat
        panell.add(crearText("Horari: "+aud.getHorari()));                  // Horari en el que es realitza l'activitat
        panell.add(crearText("Ciutat: "+aud.getCiutat()));                  // Ciutat en la que es realitza l'activitat
        panell.add(crearText("Places disponibles: "+aud.getLimitPlaces())); // Places disponibles de l'activitat
        panell.add(crearText("Preu: "+aud.getPreu()+"€"));                  // Preu de l'activitat
    }

    /**
     * Mètode per afegir al panell principal les dades d'una activitat periòdica.
     * @param panell panell on hem d'afegir la informació d'una activitat periòdica.
     * @param ap activitat periòdica corresponent
     */
    private void afegirPeriodica(JPanel p, ActivitatPeriodica ap) {
        p.add(crearText("Dia de la setmana: "+ap.getDiaSetmana()));                             // Dia de la setmana en el que es realitza l'activitat
        p.add(crearText("Horari: "+ap.getHorari()));                                            // Horari en el que es realitza l'activitat
        p.add(crearText("Data d'inici: "+ap.getDataInici()));                                   // Data en la que es comença a realitzar l'activitat
        p.add(crearText("Data de fi: "+ap.getDataInici().afegirSetmanes(ap.getNumSetmanes()))); // Data en la que es deia de realitzar l'activitat
        p.add(crearText("Centre: "+ap.getCentre()));                                            // Centre en el que es realitza l'activitat
        p.add(crearText("Ciutat: "+ap.getCiutat()));                                            // Ciutat en la que es realitza l'activitat
        p.add(crearText("Places disponibles: "+ap.getLimitPlaces()));                           // Límit de places de l'activitat
        p.add(crearText("Preu total: "+ap.getPreu() + "€"));                                    // Preu de l'activitat
    }

    /**
     * Mètode per afegir al panell principal les dades d'una activitat online.
     * @param panell panell on hem d'afegir la informació d'una activitat online
     * @param ao activitat online corresponent
     */
    private void afegirOnline(JPanel panell, ActivitatOnline ao) {
        panell.add(crearText("Data d'inici: "+ao.getDataInici()));                              // Data en la que comença a ser visible l'activitat
        panell.add(crearText("Període de visibilitat: "+ao.getPeriodeVisualitzacio()+" dies")); // Nombre de dies en les que l'activitat es manté visible
        panell.add(crearText("Data final: "+ao.getDataFi()));                                   // Data en la que deixa de ser visible l'activitat
        panell.add(crearText("Enllaç: "+ao.getEnllac()));                                       // Enllaç de l'activitat
    }

    /**
     * Mètode auxiliar per a crear una JTextArea amb el text que volem mostrar a la finestra
     * @param text String que conté la informació corresponent
     * @return una JTextArea amb el String que li hem passat per paràmetre
     */
    private JTextArea crearText(String text) {
        JTextArea area = new JTextArea(text);                           // Creem la JTextArea amb el String corresponent
        area.setOpaque(false);
        area.setFont(new Font("Dialog", Font.PLAIN, 14));    // Li donem una font determinada
        return area;
    }
}
