import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class RemoveStudent extends JFrame {
    private JTextField emailField;

    public RemoveStudent() {
        setTitle("Remove Student");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null); // Center the frame on the screen
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();

        panel.add(emailLabel);
        panel.add(emailField);

        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeStudent();
            }
        });

        panel.add(removeButton);

        getContentPane().add(panel);
    }

    private void removeStudent() {
        String email = emailField.getText().trim();

        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the email of the student to remove", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/system", "root", "");
            String query = "DELETE FROM student WHERE Email_Address=?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, email);
            int rowsAffected = pstmt.executeUpdate();

            pstmt.close();
            conn.close();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Student removed successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                emailField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "No student found with the provided email", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: Unable to remove student", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new RemoveStudent().setVisible(true);
            }
        });
    }
}
