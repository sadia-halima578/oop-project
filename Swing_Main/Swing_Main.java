package oop;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Swing_Main {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
                        
        JFrame frame = new JFrame("Daily Task Manager");
        frame.setSize(400, 400);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        
        JLabel titleLabel = new JLabel("Daily Task Manager");
        titleLabel.setBounds(110, 20, 200, 30);
        frame.add(titleLabel);

        JLabel taskLabel = new JLabel("Task Name:");
        taskLabel.setBounds(30, 80, 100, 25);
        frame.add(taskLabel);

        JTextField taskField = new JTextField();
        taskField.setBounds(120, 80, 200, 25);
        frame.add(taskField);
        
        frame.setVisible(true);
    }

}
