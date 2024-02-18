import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class DeleteCourse extends JFrame {

    private JTextField courseField;

    public DeleteCourse() {
        setTitle("Delete Course");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 400, 150);
        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(2, 2));

        JLabel lblCourse = new JLabel("Course:");
        contentPane.add(lblCourse);

        courseField = new JTextField();
        contentPane.add(courseField);
        courseField.setColumns(10);

        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteCourseFromDatabase();
            }
        });
        contentPane.add(btnDelete);
    }

    private void deleteCourseFromDatabase() {
        String course = courseField.getText().trim();

        if (course.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the course to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Database connection details
        String url = "jdbc:mysql://localhost:3306/system";
        String username = "root";
        String password = "";

        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            String query = "DELETE FROM modules WHERE course = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, course);
            int rowsDeleted = pstmt.executeUpdate();

            pstmt.close();
            conn.close();

            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(this, "Course deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "No course found with the provided name.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error occurred while deleting course.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        courseField.setText("");
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DeleteCourse frame = new DeleteCourse();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
