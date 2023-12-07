package ActionListener;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


public class HinzufuegenListener extends JDialog implements ActionListener {

    private JTextField titelTextField;
    private JTextField interpretTextField;
    private JComboBox<String> genreComboBox;
    

    public HinzufuegenListener() {
        super();
        this.setSize(400,150);
        
        String[] genreOptionen = {"Alle", "Pop", "Rock", "Hip-Hop", "Klassik", "Andere"};
        genreComboBox = new JComboBox<>(genreOptionen);
        
        titelTextField = new JTextField(20);
        interpretTextField = new JTextField(20);
        
        this.setLayout(new FlowLayout());
        
        JPanel eingabePanel = new JPanel(new GridLayout(3, 2));
        eingabePanel.add(new JLabel("Titel: "));
        eingabePanel.add(titelTextField);
        eingabePanel.add(new JLabel("Interpret: "));
        eingabePanel.add(interpretTextField);
        eingabePanel.add(new JLabel("Genre: "));
        eingabePanel.add(genreComboBox);
        
        JButton Hinzufuegen = new JButton("Hinzufuegen");
        
        JPanel hinzufuegenPanel = new JPanel(new FlowLayout());
        hinzufuegenPanel.add(Hinzufuegen);
             
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(eingabePanel, BorderLayout.NORTH);
        this.getContentPane().add(hinzufuegenPanel, BorderLayout.CENTER);

        
        this.setModal(true);
        this.setVisible(false);
        
    }
    
   

    
    @Override
    public void actionPerformed(ActionEvent arg0) {
        this.setVisible(true);
       
    }
    
}
