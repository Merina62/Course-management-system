import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Addmarks extends JFrame {
    private JTextField nameField;
    private JTextField moduleField;
    private JTextField marksField;

    public Addmarks() {
        setTitle("Add Marks");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null); // Center the frame on the screen
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();
        JLabel moduleLabel = new JLabel("Module:");
        moduleField = new JTextField();
        JLabel marksLabel = new JLabel("Marks:");
        marksField = new JTextField();

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(moduleLabel);
        panel.add(moduleField);
        panel.add(marksLabel);
        panel.add(marksField);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addMarks();
            }
        });

        panel.add(okButton);

        getContentPane().add(panel);
    }

    private void addMarks() {
        String name = nameField.getText().trim();
        String module = moduleField.getText().trim();
        String marksStr = marksField.getText().trim();

        if (name.isEmpty() || module.isEmpty() || marksStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int marks = Integer.parseInt(marksStr);
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/system", "root", "");
            String query = "INSERT INTO result (Name, Module, Marks) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, module);
            pstmt.setInt(3, marks);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            JOptionPane.showMessageDialog(this, "Marks added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for marks", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: Unable to add marks", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        nameField.setText("");
        moduleField.setText("");
        marksField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Addmarks().setVisible(true);
            }
        });
    }
}
