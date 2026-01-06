// PAQUET
package aplicacio;


// IMPORTS JAVA
import javax.swing.*;
import java.awt.*;

// IMPORTS PAQUETS LOCALS
import dades.*;


/**
 * Classe amb la visualització gràfica per a mostrar els detalls complets d'una activitat d'un dia.
 */
public class FinestraDetallActivitat extends JFrame {
    private JPanel panellTitol; //
    private JLabel etiquetaNom; //

    /**
     * Constructor
     * @param activitat
     */
    public FinestraDetallActivitat(Activitats activitat) {
        super("Detalls de l'activitat: "+activitat.getNom());
        setSize(420, 550);
        setLayout(new BorderLayout());
        
        // 
        etiquetaNom = new JLabel(activitat.getNom());
        etiquetaNom.setFont(new Font("Dialog", Font.BOLD, 18));
        etiquetaNom.setForeground(new Color(0, 0, 139));
        etiquetaNom.setHorizontalAlignment(SwingConstants.CENTER);
        
        panellTitol = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panellTitol.add(etiquetaNom);
        this.add(panellTitol, BorderLayout.NORTH);
        
        // Panel principal per la resta de contingut
        JPanel panellPrincipal = new JPanel(new GridLayout(0, 1, 0, 5));

        // Tipus d'activitat
        String tipus = activitat.getClass().getSimpleName();
        tipus = tipus.replace("Activitat", "").replace("Online", "En línia");
        JLabel etiquetaTipus = new JLabel("Tipus: " + tipus);
        etiquetaTipus.setFont(new Font("Dialog", Font.PLAIN, 14));
        etiquetaTipus.setHorizontalAlignment(SwingConstants.LEFT);
        
        // Categories
        JLabel etiquetaCategories = new JLabel("Col·lectius: " + String.join(", ", activitat.getColectius()));
        etiquetaCategories.setFont(new Font("Dialog", Font.PLAIN, 14));
        etiquetaCategories.setHorizontalAlignment(SwingConstants.LEFT);
        
        // Dates d'inscripció
        JLabel etiquetaInscripcio = new JLabel("Període d'inscripció: " + 
            activitat.getDataIniciInscripcio().toString() + " - " + activitat.getDataFiInscripcio().toString());
        etiquetaInscripcio.setFont(new Font("Dialog", Font.PLAIN, 14));
        etiquetaInscripcio.setHorizontalAlignment(SwingConstants.LEFT);
        
        // Afegir camps comuns
        panellPrincipal.add(etiquetaTipus);
        panellPrincipal.add(new JLabel(" "));
        panellPrincipal.add(etiquetaCategories);
        panellPrincipal.add(new JLabel(" "));
        panellPrincipal.add(etiquetaInscripcio);
        panellPrincipal.add(new JLabel(" "));
        panellPrincipal.add(new JLabel(" ")); // Espai extra
        
        // Informació específica segons el tipus
        if (activitat instanceof ActivitatUnDia) {
            afegirDetallsUnDia(panellPrincipal, (ActivitatUnDia) activitat);
        } else if (activitat instanceof ActivitatPeriodica) {
            afegirDetallsPeriodica(panellPrincipal, (ActivitatPeriodica) activitat);
        } else if (activitat instanceof ActivitatOnline) {
            afegirDetallsOnline(panellPrincipal, (ActivitatOnline) activitat);
        }
        
        // Crear un panel contenidor per centrar el contingut
        JPanel panelCentre = new JPanel(new BorderLayout());
        panelCentre.add(panellPrincipal, BorderLayout.NORTH);
        
        // Afegir panel principal al CENTER
        this.add(panelCentre, BorderLayout.CENTER);

        // Botó de tancar
        JPanel panelSud = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton botoTancar = new JButton("Tancar");
        botoTancar.addActionListener(e -> dispose());
        botoTancar.setBackground(new Color(192, 192, 192));
        botoTancar.setPreferredSize(new Dimension(100, 30));
        panelSud.add(botoTancar);
        
        this.add(panelSud, BorderLayout.SOUTH);
        
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    
    private void afegirDetallsUnDia(JPanel panel, ActivitatUnDia activitat) {
        // Separador
        JLabel separador = new JLabel("_________________________");
        separador.setHorizontalAlignment(SwingConstants.LEFT);
        panel.add(separador);
        panel.add(new JLabel(" "));
        
        JLabel etiquetaData = new JLabel("Data: " + activitat.getData().toString());
        etiquetaData.setFont(new Font("Dialog", Font.PLAIN, 14));
        etiquetaData.setHorizontalAlignment(SwingConstants.LEFT);
        
        JLabel etiquetaHorari = new JLabel("Horari: " + activitat.getHorari());
        etiquetaHorari.setFont(new Font("Dialog", Font.PLAIN, 14));
        etiquetaHorari.setHorizontalAlignment(SwingConstants.LEFT);
        
        JLabel etiquetaCiutat = new JLabel("Ciutat: " + activitat.getCiutat());
        etiquetaCiutat.setFont(new Font("Dialog", Font.PLAIN, 14));
        etiquetaCiutat.setHorizontalAlignment(SwingConstants.LEFT);
        
        JLabel etiquetaPlaces = new JLabel("Places disponibles: " + activitat.getLimitPlaces());
        etiquetaPlaces.setFont(new Font("Dialog", Font.PLAIN, 14));
        etiquetaPlaces.setHorizontalAlignment(SwingConstants.LEFT);
        
        JLabel etiquetaPreu = new JLabel("Preu: " + activitat.getPreu() + "€");
        etiquetaPreu.setFont(new Font("Dialog", Font.PLAIN, 14));
        etiquetaPreu.setHorizontalAlignment(SwingConstants.LEFT);
        
        panel.add(etiquetaData);
        panel.add(new JLabel(" "));
        panel.add(etiquetaHorari);
        panel.add(new JLabel(" "));
        panel.add(etiquetaCiutat);
        panel.add(new JLabel(" "));
        panel.add(etiquetaPlaces);
        panel.add(new JLabel(" "));
        panel.add(etiquetaPreu);
    }
    
    private void afegirDetallsPeriodica(JPanel panel, ActivitatPeriodica activitat) {
        JLabel separador = new JLabel("_________________________");
        separador.setHorizontalAlignment(SwingConstants.LEFT);
        panel.add(separador);
        panel.add(new JLabel(" "));
        
        JLabel etiquetaDia = new JLabel("Dia de la setmana: " + activitat.getDiaSetmana());
        etiquetaDia.setFont(new Font("Dialog", Font.PLAIN, 14));
        etiquetaDia.setHorizontalAlignment(SwingConstants.LEFT);
        
        JLabel etiquetaHorari = new JLabel("Horari: " + activitat.getHorari());
        etiquetaHorari.setFont(new Font("Dialog", Font.PLAIN, 14));
        etiquetaHorari.setHorizontalAlignment(SwingConstants.LEFT);
        
        JLabel etiquetaDataIni = new JLabel("Data d'inici: " + activitat.getDataInici().toString());
        etiquetaDataIni.setFont(new Font("Dialog", Font.PLAIN, 14));
        etiquetaDataIni.setHorizontalAlignment(SwingConstants.LEFT);
        
        JLabel etiquetaSetmanes = new JLabel("Nombre de setmanes: " + activitat.getNumSetmanes());
        etiquetaSetmanes.setFont(new Font("Dialog", Font.PLAIN, 14));
        etiquetaSetmanes.setHorizontalAlignment(SwingConstants.LEFT);
        
        JLabel etiquetaCentre = new JLabel("Centre: " + activitat.getCentre());
        etiquetaCentre.setFont(new Font("Dialog", Font.PLAIN, 14));
        etiquetaCentre.setHorizontalAlignment(SwingConstants.LEFT);
        
        JLabel etiquetaCiutat = new JLabel("Ciutat: " + activitat.getCiutat());
        etiquetaCiutat.setFont(new Font("Dialog", Font.PLAIN, 14));
        etiquetaCiutat.setHorizontalAlignment(SwingConstants.LEFT);
        
        JLabel etiquetaPlaces = new JLabel("Places disponibles: " + activitat.getLimitPlaces());
        etiquetaPlaces.setFont(new Font("Dialog", Font.PLAIN, 14));
        etiquetaPlaces.setHorizontalAlignment(SwingConstants.LEFT);
        
        JLabel etiquetaPreu = new JLabel("Preu total: " + activitat.getPreu() + "€");
        etiquetaPreu.setFont(new Font("Dialog", Font.PLAIN, 14));
        etiquetaPreu.setHorizontalAlignment(SwingConstants.LEFT);
        
        panel.add(etiquetaDia);
        panel.add(new JLabel(" "));
        panel.add(etiquetaHorari);
        panel.add(new JLabel(" "));
        panel.add(etiquetaDataIni);
        panel.add(new JLabel(" "));
        panel.add(etiquetaSetmanes);
        panel.add(new JLabel(" "));
        panel.add(etiquetaCentre);
        panel.add(new JLabel(" "));
        panel.add(etiquetaCiutat);
        panel.add(new JLabel(" "));
        panel.add(etiquetaPlaces);
        panel.add(new JLabel(" "));
        panel.add(etiquetaPreu);
    }
    
    private void afegirDetallsOnline(JPanel panel, ActivitatOnline activitat) {
        JLabel separador = new JLabel("_________________________");
        separador.setHorizontalAlignment(SwingConstants.LEFT);
        panel.add(separador);
        panel.add(new JLabel(" "));
        
        JLabel etiquetaDataIni = new JLabel("Data d'inici: " + activitat.getDataInici().toString());
        etiquetaDataIni.setFont(new Font("Dialog", Font.PLAIN, 14));
        etiquetaDataIni.setHorizontalAlignment(SwingConstants.LEFT);
        
        JLabel etiquetaPeriode = new JLabel("Període de visibilitat: " + activitat.getPeriodeVisualitzacio() + " dies");
        etiquetaPeriode.setFont(new Font("Dialog", Font.PLAIN, 14));
        etiquetaPeriode.setHorizontalAlignment(SwingConstants.LEFT);
        
        JLabel etiquetaEnllac = new JLabel("Enllaç: " + activitat.getEnllac());
        etiquetaEnllac.setFont(new Font("Dialog", Font.PLAIN, 14));
        etiquetaEnllac.setHorizontalAlignment(SwingConstants.LEFT);
        
        panel.add(etiquetaDataIni);
        panel.add(new JLabel(" "));
        panel.add(etiquetaPeriode);
        panel.add(new JLabel(" "));
        panel.add(etiquetaEnllac);
    }
}