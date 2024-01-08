package ActionListener;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;

public class addToShoppingCart extends JDialog implements ActionListener {
    private TextField platteTextField;
    private TextField cdTextField;
    private JCheckBox mp3CheckBox;
    private JButton hinzufuegeButton;
    public addToShoppingCart(){
        platteTextField = new TextField();
        cdTextField = new TextField();
        mp3CheckBox = new JCheckBox();
        hinzufuegeButton = new JButton();
        JPanel centerPanel = new JPanel(new GridLayout(1, 3));
        JPanel southPanel = new JPanel(new GridLayout(1,3));
        centerPanel.add(platteTextField);
        centerPanel.add(cdTextField);
        centerPanel.add(mp3CheckBox);

        southPanel.add(new JPanel());
        southPanel.add(hinzufuegeButton);
        southPanel.add(new JPanel());

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(centerPanel,BorderLayout.CENTER);
        this.getContentPane().add(hinzufuegeButton,BorderLayout.SOUTH);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}
