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
import java.awt.event.InputMethodEvent;
import java.awt.Rectangle;
import java.awt.Component;
import java.awt.ComponentOrientation;
import javax.swing.border.CompoundBorder;

public class Logout extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Logout frame = new Logout();
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
	public Logout() {
		setBackground(new Color(230, 230, 250));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 955, 651);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel logout = new JLabel("Logout");
		logout.setForeground(new Color(178, 34, 34));
		logout.setFont(new Font("Georgia", Font.BOLD, 25));
		logout.setBounds(566, 21, 154, 41);
		contentPane.add(logout);
		
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
		btndashboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dashboard dhb= new Dashboard();
				dhb.setVisible(true);
				dispose();
			}
		});
		btndashboard.setHorizontalAlignment(SwingConstants.LEFT);
		btndashboard.setIcon(new ImageIcon("D:\\Course-management-System\\Images\\dashboard.png"));
		btndashboard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btndashboard.setFont(new Font("Arial", Font.PLAIN, 15));
		btndashboard.setBounds(42, 139, 195, 50);
		panel.add(btndashboard);
		
		JButton btncourses = new JButton("      Courses");
		btncourses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Course cr= new Course();
				cr.setVisible(true);
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
				Teacher thr= new Teacher();
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
				Student std= new Student();
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
				Logout log= new Logout();
				log.setVisible(true);
				dispose();
			}
		});
		btnlogout.setBackground(new Color(222, 184, 135));
		btnlogout.setIcon(new ImageIcon("D:\\Course-management-System\\Images\\logout.png"));
		btnlogout.setActionCommand("      Logout");
		btnlogout.setHorizontalAlignment(SwingConstants.LEFT);
		btnlogout.setFont(new Font("Arial", Font.PLAIN, 15));
		btnlogout.setBounds(42, 507, 195, 50);
		panel.add(btnlogout);
	}
	
	
	private JPanel createDatabasePanel() {

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
	}

	 private JPanel createTeacherPanel() {

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
	    }

	 private JPanel createStudentPanel() {

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
	    }
}