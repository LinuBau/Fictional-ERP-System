package ActionListener;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author ninfr
 */
public class FilterListener extends JDialog implements ActionListener {

    private JTextField titelTextField;
    private JTextField interpretTextField;
    private JComboBox<String> genreComboBox;
    

    public FilterListener() {
        super();
        this.setSize(400,400);
        
        JCheckBox checkBoxAlle = new JCheckBox("Alle");
        JCheckBox checkBoxPop = new JCheckBox("Pop");
        JCheckBox checkBoxRock = new JCheckBox("Rock");
        JCheckBox checkBoxHipHop = new JCheckBox("Hip-Hop");
        JCheckBox checkBoxKlassik = new JCheckBox("Klassik");
        JCheckBox checkBoxAndere = new JCheckBox("Andere");
        
        
        
        titelTextField = new JTextField(20);
        interpretTextField = new JTextField(20);
        
        this.setLayout(new FlowLayout());
        
        JPanel eingabePanel = new JPanel(new GridLayout(3, 2));
        eingabePanel.add(new JLabel("Titel: "));
        eingabePanel.add(titelTextField);
        eingabePanel.add(new JLabel("Interpret: "));
        eingabePanel.add(interpretTextField);
        eingabePanel.add(new JLabel("Genre: "));
        
        JPanel checkboxPanel = new JPanel(new GridLayout(6, 1));
        
        checkboxPanel.add(checkBoxAlle);
        checkboxPanel.add(checkBoxPop);
        checkboxPanel.add(checkBoxRock);
        checkboxPanel.add(checkBoxHipHop);
        checkboxPanel.add(checkBoxKlassik);
        checkboxPanel.add(checkBoxAndere);
        
        JButton Filtern = new JButton("Filtern");
        
        JPanel filterPanel = new JPanel(new FlowLayout());
        filterPanel.add(Filtern);
             
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(eingabePanel, BorderLayout.NORTH);
        this.getContentPane().add(checkboxPanel, BorderLayout.CENTER);
        this.getContentPane().add(filterPanel, BorderLayout.SOUTH);

        
        this.setModal(true);
        this.setVisible(false);
        
    }
    
   

    
    @Override
    public void actionPerformed(ActionEvent arg0) {
        this.setVisible(true);
       
    }
    
}
