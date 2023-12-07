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
    private JTextField albumTextField;
    private JTextField genreTextField;
    
    

    public FilterListener() {
        super();
        this.setSize(400,150);
        
        
        
        
        titelTextField = new JTextField(20);
        interpretTextField = new JTextField(20);
        albumTextField = new JTextField(20);
        genreTextField = new JTextField(20);
        
        this.setLayout(new FlowLayout());
        
        JPanel eingabePanel = new JPanel(new GridLayout(4, 3));
        eingabePanel.add(new JLabel("Titel: "));
        eingabePanel.add(titelTextField);
        eingabePanel.add(new JLabel("Interpret: "));
        eingabePanel.add(interpretTextField);
        eingabePanel.add(new JLabel("Album: "));
        eingabePanel.add(albumTextField);
        eingabePanel.add(new JLabel("Genre: "));
        eingabePanel.add(genreTextField);

        

        
        JButton Filtern = new JButton("Filtern");
        
        JPanel filterPanel = new JPanel(new FlowLayout());
        filterPanel.add(Filtern);
             
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(eingabePanel, BorderLayout.NORTH);
        this.getContentPane().add(filterPanel, BorderLayout.SOUTH);

        
        this.setModal(true);
        this.setVisible(false);
        
    }
    
   

    
    @Override
    public void actionPerformed(ActionEvent arg0) {
        this.setVisible(true);
       
    }
    
}
