import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AddCourse extends JFrame {

    private JTextField moduleField;
    private JTextField courseField;

    public AddCourse() {
        setTitle("Add Course");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 400, 200);
        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(3, 2));

        JLabel lblModule = new JLabel("Module:");
        lblModule.setFont(new Font("Tahoma", Font.PLAIN, 16));
        contentPane.add(lblModule);

        moduleField = new JTextField();
        contentPane.add(moduleField);
        moduleField.setColumns(10);

        JLabel lblCourse = new JLabel("Course:");
        lblCourse.setFont(new Font("Tahoma", Font.PLAIN, 16));
        contentPane.add(lblCourse);

        courseField = new JTextField();
        contentPane.add(courseField);
        courseField.setColumns(10);

        JButton btnAdd = new JButton("Add");
        btnAdd.setBackground(new Color(160, 82, 45));
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addCourseToDatabase();
            }
        });
        contentPane.add(btnAdd);
    }

    private void addCourseToDatabase() {
        String module = moduleField.getText().trim();
        String course = courseField.getText().trim();

        if (module.isEmpty() || course.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both module and course.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Database connection details
        String url = "jdbc:mysql://localhost:3306/system";
        String username = "root";
        String password = "";

        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            String query = "INSERT INTO modules (module, course) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, module);
            pstmt.setString(2, course);
            pstmt.executeUpdate();

            pstmt.close();
            conn.close();

            JOptionPane.showMessageDialog(this, "Course added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error occurred while adding course.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        moduleField.setText("");
        courseField.setText("");
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AddCourse frame = new AddCourse();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
