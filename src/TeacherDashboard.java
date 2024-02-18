import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JLayeredPane;
import javax.swing.JInternalFrame;
import javax.swing.JToolBar;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.InputMethodListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.InputMethodEvent;
import java.awt.Rectangle;
import java.awt.Component;
import java.awt.ComponentOrientation;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class TeacherDashboard extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeacherDashboard frame = new TeacherDashboard();
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
	public TeacherDashboard() {
		setTitle("TeacherDashboard"); // Set the title of the JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 955, 651);
        
		setBackground(new Color(230, 230, 250));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 955, 651);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel dashboard = new JLabel("Dashboard");
		dashboard.setForeground(new Color(178, 34, 34));
		dashboard.setFont(new Font("Georgia", Font.BOLD, 25));
		dashboard.setBounds(566, 21, 154, 41);
		contentPane.add(dashboard);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds(0, 0, 276, 614);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Course Management System");
		lblNewLabel.setForeground(new Color(85, 107, 47));
		lblNewLabel.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 15));
		lblNewLabel.setBounds(42, 57, 205, 20);
		panel.add(lblNewLabel);
		
		JButton btndashboard = new JButton("      Dashboard");
		btndashboard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Dashboard dhb= new Dashboard();
				dhb.setVisible(true);
				dispose();
			}
		});
		
		
		btndashboard.setBackground(new Color(210, 180, 140));
		btndashboard.setHorizontalAlignment(SwingConstants.LEFT);
		btndashboard.setIcon(new ImageIcon("D:\\Course-management-System\\Images\\dashboard.png"));
		btndashboard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btndashboard.setFont(new Font("Arial", Font.PLAIN, 15));
		btndashboard.setBounds(42, 139, 195, 50);
		panel.add(btndashboard);
		
		JButton btncourses = new JButton("      Courses");
		btncourses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeacherCourse sc= new TeacherCourse();
				sc.setVisible(true);
				dispose();
			}
		});
		btncourses.setIcon(new ImageIcon("D:\\Course-management-System\\Images\\book.png"));
		btncourses.setHorizontalAlignment(SwingConstants.LEFT);
		btncourses.setFont(new Font("Arial", Font.PLAIN, 15));
		btncourses.setBounds(42, 234, 195, 50);
		panel.add(btncourses);
		
		JButton btnteacher = new JButton("      Teachers");
		btnteacher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeacherTeacher thr= new TeacherTeacher();
				thr.setVisible(true);
				dispose();
			}
		});
		btnteacher.setIcon(new ImageIcon("D:\\Course-management-System\\Images\\teacher.png"));
		btnteacher.setHorizontalAlignment(SwingConstants.LEFT);
		btnteacher.setFont(new Font("Arial", Font.PLAIN, 15));
		btnteacher.setBounds(42, 321, 195, 50);
		panel.add(btnteacher);
		
		JButton btnstudent = new JButton("      Students");
		btnstudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeacherStudent std= new TeacherStudent();
				std.setVisible(true);
				dispose();
			}
		});
		btnstudent.setIcon(new ImageIcon("D:\\Course-management-System\\Images\\student.png"));
		btnstudent.setHorizontalAlignment(SwingConstants.LEFT);
		btnstudent.setFont(new Font("Arial", Font.PLAIN, 15));
		btnstudent.setBounds(42, 412, 195, 50);
		panel.add(btnstudent);
		
		JButton btnlogout = new JButton("      Logout");
		btnlogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Signup sgn= new Signup();
				sgn.setVisible(true);
				
			}
		});
		btnlogout.setIcon(new ImageIcon("D:\\Course-management-System\\Images\\logout.png"));
		btnlogout.setActionCommand("      Logout");
		btnlogout.setHorizontalAlignment(SwingConstants.LEFT);
		btnlogout.setFont(new Font("Arial", Font.PLAIN, 15));
		btnlogout.setBounds(42, 507, 195, 50);
		panel.add(btnlogout);
		
		JPanel coursespanel = new JPanel();
		coursespanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		coursespanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		coursespanel.setBackground(new Color(248, 248, 255));
		coursespanel.setBounds(357, 112, 141, 135);
		contentPane.add(coursespanel);
		coursespanel.setLayout(null);
		
		
		JPanel databasePanel = createDatabasePanel();
        databasePanel.setBounds(0, 0, 141, 135); // Adjust bounds to fit within coursespanel
        coursespanel.add(databasePanel);
		
        
	
        // Teachers Panel
		JPanel teacherpanel = createTeacherPanel();
		teacherpanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		teacherpanel.setBackground(new Color(240, 255, 240));
		teacherpanel.setBounds(564, 112, 141, 135);
		contentPane.add(teacherpanel);
		teacherpanel.setLayout(null);
		
        JPanel studentspanel = createStudentPanel(); // Create student panel
		studentspanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		studentspanel.setBackground(new Color(240, 248, 255));
		studentspanel.setBounds(755, 112, 141, 135);
		contentPane.add(studentspanel);
		studentspanel.setLayout(null);
	}
	
	
	private JPanel createDatabasePanel() {
	    JPanel panel = new JPanel(null); // Use null layout
	    panel.setBorder(new LineBorder(new Color(0, 0, 0)));
	    panel.setBackground(new Color(220, 220, 220));

	    JLabel titleLabel = new JLabel("Total Modules:");
	    titleLabel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	    titleLabel.setHorizontalTextPosition(SwingConstants.CENTER);
	    titleLabel.setFont(new Font("Arial", Font.PLAIN, 17));
	    titleLabel.setBounds(10, 15, 120, 20); // Set bounds for titleLabel
	    panel.add(titleLabel);

	    JLabel countLabel = new JLabel();
	    countLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    countLabel.setFont(new Font("Arial", Font.BOLD, 30)); // Set the font size for the count
	    countLabel.setBounds(0, 70, 120, 50); // Set bounds for countLabel
	    panel.add(countLabel);

	    // Retrieve count of records from the database and set it to the label
	    try {
	        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/system", "root", "");
	        Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS total_count FROM modules");

	        if (rs.next()) {
	            int count = rs.getInt("total_count");
	            countLabel.setText(String.valueOf(count));
	        }

	        rs.close();
	        stmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return panel;
	}

	 private JPanel createTeacherPanel() {
	        JPanel panel = new JPanel(null);

	        JLabel titleLabel = new JLabel("Total Teachers:");
	        titleLabel.setFont(new Font("Arial", Font.PLAIN, 17));
	        titleLabel.setBounds(10, 15, 120, 20);
	        panel.add(titleLabel);

	        JLabel countLabel = new JLabel();
	        countLabel.setHorizontalAlignment(SwingConstants.CENTER);
	        countLabel.setFont(new Font("Arial", Font.BOLD, 30));
	        countLabel.setBounds(0, 70, 120, 50);
	        panel.add(countLabel);

	        // Retrieve count of teachers from the database and set it to the label
	        try {
	            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/system", "root", "");
	            Statement stmt = conn.createStatement();
	            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS total_teachers FROM teacher");

	            if (rs.next()) {
	                int count = rs.getInt("total_teachers");
	                countLabel.setText(String.valueOf(count));
	            }

	            rs.close();
	            stmt.close();
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return panel;
	    }

	 private JPanel createStudentPanel() {
	        JPanel panel = new JPanel(null);

	        JLabel titleLabel = new JLabel("Total Students:");
	        titleLabel.setFont(new Font("Arial", Font.PLAIN, 18));
	        titleLabel.setBounds(10, 15, 120, 20);
	        panel.add(titleLabel);

	        JLabel countLabel = new JLabel();
	        countLabel.setHorizontalAlignment(SwingConstants.CENTER);
	        countLabel.setFont(new Font("Arial", Font.BOLD, 30));
	        countLabel.setBounds(0, 70, 120, 50);
	        panel.add(countLabel);

	        // Retrieve count of students from the database and set it to the label
	        try {
	            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/system", "root", "");
	            Statement stmt = conn.createStatement();
	            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS total_students FROM student");

	            if (rs.next()) {
	                int count = rs.getInt("total_students");
	                countLabel.setText(String.valueOf(count));
	            }

	            rs.close();
	            stmt.close();
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return panel;
	    }
}