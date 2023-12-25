import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CourseRegistrationSystem extends JFrame {
    private List<String> availableCourses;
    private List<String> registeredCourses;
    private List<Student> registeredStudents;

    public CourseRegistrationSystem() {
        setTitle("Course Registration System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        availableCourses = new ArrayList<>();
        availableCourses.add("Mathematics");
        availableCourses.add("Physics");
        availableCourses.add("Computer Science");

        registeredCourses = new ArrayList<>();
        registeredStudents = new ArrayList<>();

        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        getContentPane().add(panel, "Center");
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel nameLabel = new JLabel("Student Name:");
        JTextField nameField = new JTextField();
        panel.add(nameLabel);
        panel.add(nameField);

        JLabel rollLabel = new JLabel("Roll Number:");
        JTextField rollField = new JTextField();
        panel.add(rollLabel);
        panel.add(rollField);

        JLabel availableLabel = new JLabel("Available Courses:");
        panel.add(availableLabel);

        JList<String> availableCoursesList = new JList<>(availableCourses.toArray(new String[0]));
        JScrollPane availableScrollPane = new JScrollPane(availableCoursesList);
        panel.add(availableScrollPane);

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String studentName = nameField.getText();
                String rollNumber = rollField.getText();
                String selectedCourse = availableCoursesList.getSelectedValue();

                if (studentName.isEmpty() || rollNumber.isEmpty() || selectedCourse == null) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields and select a valid course.");
                } else {
                    registeredStudents.add(new Student(studentName, rollNumber));
                    registeredCourses.add(selectedCourse);
                    JOptionPane.showMessageDialog(null, "Successfully registered for: " + selectedCourse +
                            "\nStudent Name: " + studentName + "\nRoll Number: " + rollNumber);

                    // Clear fields after registration
                    nameField.setText("");
                    rollField.setText("");
                }
            }
        });
        panel.add(registerButton);

        JLabel registeredLabel = new JLabel("Registered Courses:");
        panel.add(registeredLabel);

        JList<String> registeredCoursesList = new JList<>(registeredCourses.toArray(new String[0]));
        JScrollPane registeredScrollPane = new JScrollPane(registeredCoursesList);
        panel.add(registeredScrollPane);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CourseRegistrationSystem().setVisible(true);
            }
        });
    }

    private static class Student {
        private String name;
        private String rollNumber;

        public Student(String name, String rollNumber) {
            this.name = name;
            this.rollNumber = rollNumber;
        }

        public String getName() {
            return name;
        }

        public String getRollNumber() {
            return rollNumber;
        }
    }
}
