import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class InputFrame extends JFrame {
    private JTextField nameField;
    private JTextField moduleField;
    private JTextArea resultArea; // Added JTextArea for displaying student results

    public InputFrame() {
        setTitle("Enter Student Details");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300); // Increased frame size to accommodate result panel
        setLocationRelativeTo(null); // Center the frame on the screen

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();

        inputPanel.add(nameLabel);
        inputPanel.add(nameField);

        JButton printButton = new JButton("Print");
        printButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                printStudentDetails();
            }
        });
        inputPanel.add(printButton);

        mainPanel.add(inputPanel, BorderLayout.NORTH);

        // Panel for displaying student results
        JPanel resultPanel = new JPanel(new BorderLayout());
        resultPanel.setBorder(BorderFactory.createTitledBorder("Student Result"));
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        resultPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(resultPanel, BorderLayout.CENTER);

        getContentPane().add(mainPanel);
    }

    private void printStudentDetails() {
        String name = nameField.getText().trim();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a name", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/system", "root", "");
            String query = "SELECT * FROM result WHERE Name = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();

            // Clear previous result
            resultArea.setText("");

            boolean found = false; // Flag to track if student details found

            while (rs.next()) {
                found = true;
                // Retrieve student details
                String studentModule = rs.getString("module");
                int studentMarks = rs.getInt("marks");

                // Append marks to the resultArea
                resultArea.append(studentModule + ": " + studentMarks + "\n");
            }

            if (!found) {
                JOptionPane.showMessageDialog(this, "Student details not found", "Error", JOptionPane.ERROR_MESSAGE);
            }

            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: Unable to fetch student details", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new InputFrame().setVisible(true);
            }
        });
    }
}
