package labProject;

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
					        
					        JLabel priorityLabel = new JLabel("Priority:");
					        priorityLabel.setBounds(30, 120, 100, 25);
					        frame.add(priorityLabel);

					        String[] priorities = {"Low", "Medium", "High"};
					        JComboBox<String> priorityBox = new JComboBox<>(priorities);
					        priorityBox.setBounds(120, 120, 200, 25);
					        frame.add(priorityBox);
					        
					        JLabel tagLabel = new JLabel("Tags:");
					        tagLabel.setBounds(30, 160, 100, 25);
					        frame.add(tagLabel);

					        JCheckBox urgent = new JCheckBox("Urgent");
					        urgent.setBounds(120, 160, 80, 25);
					        frame.add(urgent);

					        JCheckBox important = new JCheckBox("Important");
					        important.setBounds(200, 160, 100, 25);
					        frame.add(important);
					        
					        JLabel typeLabel = new JLabel("Type:");
					        typeLabel.setBounds(30, 200, 100, 25);
					        frame.add(typeLabel);

					        JRadioButton work = new JRadioButton("Work");
					        work.setBounds(120, 200, 70, 25);
					        JRadioButton personal = new JRadioButton("Personal");
					        personal.setBounds(190, 200, 90, 25);
					        JRadioButton other = new JRadioButton("Other");
					        other.setBounds(280, 200, 70, 25);
					        
					        ButtonGroup typeGroup = new ButtonGroup();
					        typeGroup.add(work);
					        typeGroup.add(personal);
					        typeGroup.add(other);

					        frame.add(work);
					        frame.add(personal);
					        frame.add(other);

					        frame.setVisible(true);
					    }
					}