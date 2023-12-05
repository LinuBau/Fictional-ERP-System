
package ActionListener;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


public class WindowEventListener implements WindowListener{

    @Override
    public void windowOpened(WindowEvent e) {
        System.out.println("Fenster geöffnet");
    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("Fenster schließen");
        System.exit(0);    
    }

    @Override
    public void windowClosed(WindowEvent e) {
        System.out.println("Fenster geschlossen");
    }

    @Override
    public void windowIconified(WindowEvent e) {
        System.out.println("Fenster verkleinert");    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        System.out.println("Fenster vergrößert");    }

    @Override
    public void windowActivated(WindowEvent e) {
        System.out.println("Fenster öffnen");    
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        System.out.println("Fenster beenden");
    }
   
    
}
