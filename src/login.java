import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.Cursor;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MouseInputAdapter;
import javax.swing.UIManager;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;


public class login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField emailbox;
	private JPasswordField passwordField;
	String Roles;
	private JComboBox<String> Role;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public login() {
		setBackground(new Color(0, 0, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 461, 534);
		contentPane = new JPanel();
		contentPane.setFont(new Font("Cambria Math", Font.PLAIN, 14));
		contentPane.setBackground(new Color(223, 224, 216));
		contentPane.setBorder(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		emailbox = new JTextField();
		emailbox.setBounds(156, 144, 215, 26);
		contentPane.add(emailbox);
		emailbox.setColumns(10);
		
		JLabel login = new JLabel("Login Page");
		login.setForeground(new Color(44, 54, 48));
		login.setFont(new Font("Times New Roman", Font.BOLD, 30));
		login.setBounds(156, 50, 146, 44);
		contentPane.add(login);
		
		JLabel email = new JLabel("Email");
		email.setFont(new Font("Bahnschrift", Font.PLAIN, 18));
		email.setBounds(73, 146, 65, 20);
		contentPane.add(email);
		
		JLabel lblNewLabel = new JLabel("Password");
		lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 18));
		lblNewLabel.setBounds(47, 215, 98, 26);
		contentPane.add(lblNewLabel);
		
		
		Role = new JComboBox<String>();
		Role.setModel(new DefaultComboBoxModel<String>(new String[] {"Select Your Role", "Administrator", "Teacher", "Student"}));
		Role.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		Role.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		Role.setBackground(new Color(230, 230, 250));
		Role.setBounds(156, 271, 215, 33);
		Role.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				Roles= Role.getSelectedItem().toString();
			}
		});
		contentPane.add(Role);
		
		
		
		JButton loginbtn = new JButton("Login");
		loginbtn.setBackground(new Color(245, 245, 220));
		loginbtn.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		loginbtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		loginbtn.setFont(new Font("Dubai Medium", Font.PLAIN, 18));
		loginbtn.setBounds(170, 349, 98, 34);
		contentPane.add(loginbtn);
		
		
		loginbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = emailbox.getText();
                String password = String.valueOf(passwordField.getPassword());        
				String role = (String) Role.getSelectedItem();

                String dbURL = "jdbc:mysql://localhost/system";
                String dbUser = "root";
                String dbPassword = "";
                
                
                // Validate if all required fields are filled
		        if (email.isEmpty() || password.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Please fill in all required fields.");
		            return; // Exit the method if any field is empty
		        }
		        
		        // Validate if a role is selected
		        if (role.equals("Select Your Role")) {
		            JOptionPane.showMessageDialog(null, "Please select your role.");
		            return; // Exit the method if no role is selected
		        }
                
                
                
                
                try (Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPassword)) {
                    // Query the respective table based on the role
                    String tableName = "";
                    if (role.equals("Student")) {
                        tableName = "student";
                    } else if (role.equals("Administrator")) {
                        tableName = "administrator";
                    } else if(role.equals("Teacher")) {
                            tableName = "teacher";
                    }

                    PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tableName + " WHERE Email_Address = ? AND Password = ?");
                    ps.setString(1, email);
                    ps.setString(2, password);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        // Successful login
                        String role1 = rs.getString("Roles");

                        switch (role1) {
                            case "Administrator":
                                // Redirect to Admin Dashboard
                                Dashboard adminDashboard = new Dashboard();
                                adminDashboard.setVisible(true);
                                break;
                            case "Student":
                                // Redirect to Student Dashboard
                                StudentDashboard studentDashboard = new StudentDashboard();
                                studentDashboard.setVisible(true);
                                break;
                            case "Teacher":
                                // Redirect to Teacher Dashboard
                                TeacherDashboard teacherDashboard = new TeacherDashboard();
                                teacherDashboard.setVisible(true);
                                break;
                            default:
                                // Role not recognized
                                JOptionPane.showMessageDialog(null, "Invalid role. Please contact administrator.", "Login Error", JOptionPane.ERROR_MESSAGE);
                                break;
                        }
                    } else {
                        // Invalid credentials
                        // Display error message
                    	JOptionPane.showMessageDialog(null, "Invalid email or password or role. Please try again.", "Login Error", JOptionPane.ERROR_MESSAGE); 
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                
             
                
			}
			
		});
		
		
		JLabel lblNewLabel_1 = new JLabel("Don't have an account?");
		lblNewLabel_1.setFont(new Font("Cambria Math", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(135, 439, 143, 26);
		contentPane.add(lblNewLabel_1);
		
		JLabel sign = new JLabel("Sign Up");
		sign.setFont(new Font("Cambria Math", Font.PLAIN, 14));
		sign.setForeground(new Color(128, 128, 0));
		sign.setBounds(282, 442, 58, 20);
		contentPane.add(sign);
		sign.addMouseListener(new MouseInputAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
					Signup sgn= new Signup();
					sgn.setVisible(true);
					dispose();
					
				}
		});
		
		passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		passwordField.setBounds(156, 216, 215, 26);
		contentPane.add(passwordField);
		
		JLabel visible = new JLabel("");
		JLabel invisible = new JLabel("");
		invisible.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
					passwordField.setEchoChar((char) 0);
					invisible.setVisible(false);
					visible.setVisible(true);
				}
		});
		
		visible.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				passwordField.setEchoChar('*');
				invisible.setVisible(true);
				visible.setVisible(false);
			}
		});
		visible.setIcon(new ImageIcon("D:\\Course-management-System\\Images\\visible.png"));
		visible.setBounds(378, 221, 24, 20);
		contentPane.add(visible);
		
		
		
		
		invisible.setIcon(new ImageIcon("D:\\Course-management-System\\Images\\invisible.png"));
		invisible.setBounds(378, 221, 24, 20);
		contentPane.add(invisible);
		
		
	}
}
