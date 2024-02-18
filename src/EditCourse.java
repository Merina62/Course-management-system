import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class EditCourse extends JFrame {

    private JTextField idField;
    private JTextArea modulesArea;

    public EditCourse() {
        setTitle("Edit Modules");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 400, 250);
        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(3, 2));

        JLabel lblid = new JLabel("ID:");
        contentPane.add(lblid);

        idField = new JTextField();
        contentPane.add(idField);
        idField.setColumns(10);

        JLabel lblModules = new JLabel("Modules:");
        contentPane.add(lblModules);

        modulesArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(modulesArea);
        contentPane.add(scrollPane);

        JButton btnEdit = new JButton("Edit");
        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editCourseInDatabase();
            }
        });
        contentPane.add(btnEdit);
    }

    private void editCourseInDatabase() {
        String id = idField.getText().trim();
        String modules = modulesArea.getText().trim();

        if (id.isEmpty() || modules.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both id and modules.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Database connection details
        String url = "jdbc:mysql://localhost:3306/system";
        String username = "root";
        String password = "";

        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            String query = "UPDATE modules SET module = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, modules);
            pstmt.setString(2, id);
            int rowsUpdated = pstmt.executeUpdate();

            pstmt.close();
            conn.close();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Course updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "No course found with the provided name.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error occurred while updating course.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        courseField.setText("");
        modulesArea.setText("");
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    EditCourse frame = new EditCourse();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
