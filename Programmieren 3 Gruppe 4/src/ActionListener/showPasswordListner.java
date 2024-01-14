package ActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JPasswordField;

public class showPasswordListner implements ActionListener {
    JCheckBox checkBox;
    JPasswordField passwordField;
    public showPasswordListner(JCheckBox checkBox,JPasswordField pf){
        this.checkBox = checkBox;
        this.passwordField = pf;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(checkBox.isSelected()){
            passwordField.setEchoChar((char)0);
        }else{
            passwordField.setEchoChar('*');
        }
    }
    
}
