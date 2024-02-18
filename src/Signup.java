import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputAdapter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Cursor;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.ImageIcon;

public class Signup extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField fname;
	private JTextField lname;
	private JTextField email;
	private JPasswordField passwordField;
	String Roles;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Signup frame = new Signup();
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
	public Signup() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 493, 656);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 255, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sign Up page");
		lblNewLabel.setBounds(144, 32, 194, 33);
		lblNewLabel.setForeground(new Color(128, 128, 0));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Email Address");
		lblNewLabel_1.setBounds(74, 225, 113, 19);
		lblNewLabel_1.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setBounds(74, 290, 89, 23);
		lblNewLabel_1_1.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		contentPane.add(lblNewLabel_1_1);
		
		JComboBox Role = new JComboBox();
		Role.setBounds(74, 363, 337, 30);
		Role.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				Roles= Role.getSelectedItem().toString();
			}
		});
		
		
		
		Role.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		Role.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		Role.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Role.setBackground(new Color(230, 230, 250));
		Role.setModel(new DefaultComboBoxModel(new String[] {"Select Your Role", "Administrator", "Teacher", "Student"}));
		contentPane.add(Role);
		
		

		
		
		
		JLabel lblNewLabel_2 = new JLabel("First Name");
		lblNewLabel_2.setBounds(74, 95, 89, 21);
		lblNewLabel_2.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Last Name");
		lblNewLabel_2_1.setBounds(74, 159, 89, 21);
		lblNewLabel_2_1.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		contentPane.add(lblNewLabel_2_1);
		
		fname = new JTextField();
		fname.setBounds(74, 115, 337, 25);
		fname.setFont(new Font("Arial", Font.PLAIN, 12));
		contentPane.add(fname);
		fname.setColumns(10);
		
		lname = new JTextField();
		lname.setBounds(74, 179, 337, 25);
		lname.setFont(new Font("Arial", Font.PLAIN, 12));
		lname.setColumns(10);
		contentPane.add(lname);
		
		email = new JTextField();
		email.setBounds(74, 247, 337, 25);
		email.setFont(new Font("Arial", Font.PLAIN, 12));
		email.setColumns(10);
		contentPane.add(email);
		
		passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		passwordField.setBounds(74, 310, 337, 25);
		passwordField.setFont(new Font("Arial", Font.PLAIN, 12));
		contentPane.add(passwordField);
		
		
		JComboBox Course = new JComboBox();
		Course.setBounds(74, 425, 337, 30);
		Course.setModel(new DefaultComboBoxModel(new String[] {"Choose Your Course", "BIT ", "BIBM", "IMBA"}));
		Course.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		Course.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		Course.setBackground(new Color(230, 230, 250));
		contentPane.add(Course);
		// Initially hide courseComboBox
		Course.setVisible(false);
		

		// Add ItemListener to roleComboBox
		Role.addItemListener(new ItemListener() {
		    public void itemStateChanged(ItemEvent e) {
		        if (e.getStateChange() == ItemEvent.SELECTED) {
		            String selectedRole = (String) Role.getSelectedItem();
		            if (selectedRole.equals("Student")) {
		                // If role is "Student", make Course JComboBox visible
		                Course.setVisible(true);
		            } else {
		                // Otherwise, hide Course JComboBox
		                Course.setVisible(false);
		            }
		        }
		    }
		});

		
		
		
		
		JButton Signupbtn = new JButton("Sign Up");
		Signupbtn.setBounds(179, 482, 107, 33);
		Signupbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String firstname= fname.getText();
				String lastname= lname.getText();
				String emailadd= email.getText();
				String password= passwordField.getText();
				String role = (String) Role.getSelectedItem();
				String course= (String) Course.getSelectedItem();
				
				// Validate if all required fields are filled
		        if (firstname.isEmpty() || lastname.isEmpty() || emailadd.isEmpty() || password.isEmpty() ) {
		            JOptionPane.showMessageDialog(null, "Please fill in all required fields.");
		            return; // Exit the method if any field is empty
		        }
		        
		        // Validate if a role is selected
		        if (role.equals("Select Your Role")) {
		            JOptionPane.showMessageDialog(null, "Please select your role.");
		            return; // Exit the method if no role is selected
		        }
		        
		        // Validate if a course is selected
		        if(role.equals("Student")) {
			        if (course.equals("Choose Your Course")) {
			            JOptionPane.showMessageDialog(null, "Please choose your course.");
			            return; // Exit the method if no role is selected
			        }
		        }
		        //Database Connection
				try(Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/system","root","");
		                Statement stmt=conn.createStatement();
		        ){
					String query;
					if (role.equals("Student")) {
				        query = "INSERT INTO student (First_Name, Last_Name, Email_Address, Password, Roles, Course) VALUES (?, ?, ?, ?, ?, ?)";
				    } else {
				        query = "INSERT INTO " + role.toLowerCase() + " (First_Name, Last_Name, Email_Address, Password, Roles) VALUES (?, ?, ?, ?, ?)";
				    }
					
					PreparedStatement ps=conn.prepareStatement(query);
		           ps.setString(1, fname.getText());
		           ps.setString(2, lname.getText());
		           ps.setString(3, email.getText());
		           ps.setString(4, passwordField.getText());
		           ps.setString(5, Roles);
		           
		           if (role.equals("Student")) {
		               ps.setString(6, course);
		           }
		           
		            int z=ps.executeUpdate();
		            if(z>0) {
		            	JOptionPane.showMessageDialog(null, "Records Inserted Successfully");
		            	// Clearing screen after record insterted
			            fname.setText("");
		                lname.setText("");
		                email.setText("");
		                passwordField.setText("");
		                
		             // Reset the role JComboBox to its initial state ("Select Your Role")
		                Role.setSelectedIndex(0);

		                // Reset the course JComboBox to its initial state ("Choose Your Course")
		                Course.setSelectedIndex(0);
		                
					}else {
		            	JOptionPane.showMessageDialog(null, "Error");
					}
		        } catch(SQLException ex) {
		            ex.printStackTrace();
		        }
				
			}
		});
		
		
		Signupbtn.setBackground(new Color(255, 255, 224));
		Signupbtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Signupbtn.setFont(new Font("Times New Roman", Font.BOLD, 18));
		contentPane.add(Signupbtn);
		
		
		
		
		
		
		
		
		// Login part inside Signup
		JLabel lblNewLabel_3 = new JLabel("Already Have an Account?");
		lblNewLabel_3.setBounds(128, 537, 168, 19);
		lblNewLabel_3.setFont(new Font("Cambria Math", Font.PLAIN, 14));
		contentPane.add(lblNewLabel_3);
		
		JLabel login = new JLabel("Login");
		login.setBounds(295, 539, 56, 15);
		login.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				login lgn= new login();
				lgn.setVisible(true);
				dispose();
			}
		});
		
		

		login.setForeground(new Color(34, 139, 34));
		login.setFont(new Font("Cambria Math", Font.PLAIN, 14));
		contentPane.add(login);
		
		
		
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
		visible.setBounds(421, 315, 30, 20);
		visible.setIcon(new ImageIcon("D:\\Course-management-System\\Images\\visible.png"));
		contentPane.add(visible);
		
		
		invisible.setIcon(new ImageIcon("D:\\Course-management-System\\Images\\invisible.png"));
		invisible.setBounds(421, 315, 30, 20);
		contentPane.add(invisible);
		
		
	}
}
