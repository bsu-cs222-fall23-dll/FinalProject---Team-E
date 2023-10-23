import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Arithmetic
{
    private JTextField textField1;
    private JButton a1Button;
    private JButton a2Button;
    private JButton a3Button;
    private JButton a4Button;
    private JButton a5Button;
    private JButton a6Button;
    private JButton a7Button;
    private JButton a8Button;
    private JButton a9Button;
    private JButton button1;
    private JButton a0Button;
    private JButton button2;
    private JButton button3;
    private JButton CEButton;
    private JButton cButton;
    private JButton DELButton;
    private JButton a1XButton;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton xButton;
    private JButton button7;
    private JButton x2Button;
    private JButton sqrtXButton;
    private Operations operations;

    public Arithmetic() {
        a1Button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                textField1.setText("1");
            }
        });
    }
}
