package labProject;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.BoxLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Swing_Main {

    private static final String FILE_NAME = "tasks.txt";

    public static void main(String[] args) {

        JFrame frame = new JFrame("Daily Task Manager");
        frame.setSize(600, 550);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        Color backgroundColor = new Color(255, 249, 230); 
        frame.getContentPane().setBackground(backgroundColor);

        JLabel titleLabel = new JLabel("Daily Task Manager");
        titleLabel.setBounds(200, 10, 200, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        frame.add(titleLabel);

        JLabel taskLabel = new JLabel("Task Name:");
        taskLabel.setBounds(30, 60, 100, 25);
        frame.add(taskLabel);

        JTextField taskField = new JTextField();
        taskField.setBounds(120, 60, 300, 25);
        frame.add(taskField);

        JLabel priorityLabel = new JLabel("Priority:");
        priorityLabel.setBounds(30, 100, 100, 25);
        frame.add(priorityLabel);

        String[] priorities = {"Select Priority", "Low", "Medium", "High"};
        JComboBox<String> priorityBox = new JComboBox<>(priorities);
        priorityBox.setBounds(120, 100, 300, 25);
        frame.add(priorityBox);

        JLabel tagLabel = new JLabel("Tags:");
        tagLabel.setBounds(30, 140, 100, 25);
        frame.add(tagLabel);

        JCheckBox urgent = new JCheckBox("Urgent");
        urgent.setOpaque(false);
        urgent.setBounds(120, 140, 80, 25);
        frame.add(urgent);

        JCheckBox important = new JCheckBox("Important");
        important.setOpaque(false);
        important.setBounds(210, 140, 100, 25);
        frame.add(important);

        JLabel typeLabel = new JLabel("Type:");
        typeLabel.setBounds(30, 180, 100, 25);
        frame.add(typeLabel);

        JRadioButton work = new JRadioButton("Work");
        work.setOpaque(false);
        work.setBounds(120, 180, 70, 25);
        JRadioButton personal = new JRadioButton("Personal");
        personal.setOpaque(false);
        personal.setBounds(190, 180, 90, 25);
        JRadioButton other = new JRadioButton("Other");
        other.setOpaque(false);
        other.setBounds(290, 180, 70, 25);

        ButtonGroup typeGroup = new ButtonGroup();
        typeGroup.add(work);
        typeGroup.add(personal);
        typeGroup.add(other);

        frame.add(work);
        frame.add(personal);
        frame.add(other);

        JButton addButton = new JButton("Add Task");
        JButton clearInfoButton = new JButton("Clear Information");
        JButton clearTasksButton = new JButton("Clear Tasks");

        Color greenBtnColor = new Color(102, 187, 106);
        JButton[] buttons = {addButton, clearInfoButton, clearTasksButton};
        for (JButton btn : buttons) {
            btn.setBackground(greenBtnColor);
            btn.setForeground(Color.WHITE);
            btn.setOpaque(true);
            btn.setBorderPainted(false);
        }

        addButton.setBounds(120, 220, 100, 30);
        clearInfoButton.setBounds(230, 220, 100, 30);
        clearTasksButton.setBounds(340, 220, 120, 30);

        frame.add(addButton);
        frame.add(clearInfoButton);
        frame.add(clearTasksButton);

        JPanel taskPanel = new JPanel();
        taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS));
        taskPanel.setBackground(backgroundColor);

        JScrollPane scrollPane = new JScrollPane(taskPanel);
        scrollPane.setBounds(30, 270, 520, 200);
        frame.add(scrollPane);

        loadTasks(taskPanel, backgroundColor);

        addButton.addActionListener(e -> {
            String task = taskField.getText().trim();
            String priority = (String) priorityBox.getSelectedItem();

            if (task.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter a task name!");
                return;
            }
            if (priority.equals("Select Priority")) {
                JOptionPane.showMessageDialog(frame, "Please select a priority!");
                return;
            }
            if (!urgent.isSelected() && !important.isSelected()) {
                JOptionPane.showMessageDialog(frame, "Please select at least one tag!");
                return;
            }
            if (!work.isSelected() && !personal.isSelected() && !other.isSelected()) {
                JOptionPane.showMessageDialog(frame, "Please select a task type!");
                return;
            }

            StringBuilder tags = new StringBuilder();
            if (urgent.isSelected()) tags.append("Urgent ");
            if (important.isSelected()) tags.append("Important ");

            String type = work.isSelected() ? "Work" : personal.isSelected() ? "Personal" : "Other";

            String taskDetails = task + " | Priority: " + priority + " | Tags: " + tags + "| Type: " + type;

            JCheckBox taskCheck = new JCheckBox(taskDetails);
            taskCheck.setBackground(backgroundColor);
            taskPanel.add(taskCheck);
            taskPanel.revalidate();
            taskPanel.repaint();

            saveTasks(taskPanel);

            taskField.setText("");
            priorityBox.setSelectedIndex(0);
            urgent.setSelected(false);
            important.setSelected(false);
            typeGroup.clearSelection();
        });

        clearInfoButton.addActionListener(e -> {
            taskField.setText("");
            priorityBox.setSelectedIndex(0);
            urgent.setSelected(false);
            important.setSelected(false);
            typeGroup.clearSelection();
        });

        clearTasksButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to clear all tasks?", "Confirm Clear", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                taskPanel.removeAll();
                taskPanel.revalidate();
                taskPanel.repaint();
                clearTasksFile();
            }
        });

        frame.setVisible(true);
    }

    private static void loadTasks(JPanel taskPanel, Color backgroundColor) {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                JCheckBox taskCheck = new JCheckBox(line);
                taskCheck.setBackground(backgroundColor);
                taskPanel.add(taskCheck);
            }
            taskPanel.revalidate();
            taskPanel.repaint();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveTasks(JPanel taskPanel) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Component comp : taskPanel.getComponents()) {
                if (comp instanceof JCheckBox) {
                    JCheckBox cb = (JCheckBox) comp;
                    pw.println(cb.getText());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void clearTasksFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
