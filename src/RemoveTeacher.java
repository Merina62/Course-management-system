import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class RemoveTeacher extends JFrame {
    private JTextField teacherIdField;

    public RemoveTeacher() {
        setTitle("Remove Teacher");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null); // Center the frame on the screen
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel teacherIdLabel = new JLabel("Teacher ID:");
        teacherIdField = new JTextField();

        panel.add(teacherIdLabel);
        panel.add(teacherIdField);

        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeTeacher();
            }
        });

        panel.add(removeButton);

        add(panel);
    }

    private void removeTeacher() {
        String teacherIdStr = teacherIdField.getText().trim();

        if (teacherIdStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a teacher ID", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int teacherId = Integer.parseInt(teacherIdStr);

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/system", "root", "");
            String query = "DELETE FROM teacher WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, teacherId);
            int rowsAffected = pstmt.executeUpdate();

            pstmt.close();
            conn.close();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Teacher removed successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Teacher not found", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid teacher ID", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: Unable to remove teacher", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        teacherIdField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new RemoveTeacher().setVisible(true);
            }
        });
    }
}
