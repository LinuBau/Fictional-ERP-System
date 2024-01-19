package ActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FileCloseItemListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Beenden");
        System.exit(0);
    }

}
