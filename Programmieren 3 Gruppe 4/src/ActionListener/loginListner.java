package ActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import App_GUI.Gui;
import App_GUI.loginGUi;

public class loginListner implements ActionListener {
    private loginGUi parent;

    public loginListner(loginGUi p) {
        parent = p;
    }

    @Override
    public void actionPerformed(ActionEvent e) { 
        int index = parent.getProfilList().indexOfLogin( parent.getUsername().trim(),parent.getPassword(), parent.getProfilList());
        if(index != -1){
               Gui mainWindow = new Gui(parent.getProfilList().get(index).getIsmitarbeiter());
                mainWindow.setTitle("Musik Katalog");
                mainWindow.setSize(1000, 500);
                mainWindow.setLocationRelativeTo(null);
                parent.setVisible(false);
                mainWindow.setVisible(true);
        }
    }

}
