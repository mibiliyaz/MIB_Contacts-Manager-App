package contactmanager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
 
public class LogIn extends JDialog {

	JLabel lblEmail,lblPassword,PgTitle;
	JLabel image=new JLabel(new ImageIcon("MIB.png"));
    JTextField EmailTextField,PswdTextField;
    JButton SignInBtn,blank,signUpBtn;
    private JPanel contentPane;
    
    public String id;

    public LogIn() {
    	setTitle("Contacts Manager");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 10, 450, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setVisible(true);

		JPanel panel = new JPanel();
		panel.getLayout();
		contentPane.add(panel, BorderLayout.NORTH);
		
		image.setBounds(100,45,60,60);
        image.setSize(250,250);
        contentPane.add(image);
		
        PgTitle=new JLabel("Sign In");
        PgTitle.setBounds(145,20,200,60);
        PgTitle.setFont(new Font("TimesNewRoman",Font.BOLD,50));
        contentPane.add(PgTitle);

        lblEmail=new JLabel("Email: ");
        lblEmail.setFont(new Font("Serif", Font.BOLD, 20));
        lblEmail.setBounds(50,290,150,30);//x,y,width,height
        contentPane.add(lblEmail);

        EmailTextField=new JTextField(30);
        EmailTextField.setFont(new Font("Serif", Font.BOLD, 20));
        EmailTextField.setBounds(160,290,150,30);
        EmailTextField.setSize(220, 30);
        EmailTextField.setOpaque(false);
        contentPane.add(EmailTextField);

        lblPassword=new JLabel("Password: ");
        lblPassword.setFont(new Font("Serif", Font.BOLD, 20));
        lblPassword.setBounds(50,340,150,30);
        contentPane.add(lblPassword);

        PswdTextField=new JPasswordField();
        PswdTextField.setFont(new Font("Serif", Font.BOLD, 20));
        PswdTextField.setBounds(160,340,150,30);
        PswdTextField.setSize(220, 30);
        PswdTextField.setOpaque(false);
        contentPane.add(PswdTextField);

        SignInBtn=new JButton("Sign In");
        SignInBtn.setBounds(180,400,100,30);
        SignInBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
	                DBConnect c=new DBConnect();
	                String email=EmailTextField.getText();
	                String password=PswdTextField.getText();
	                if(email.length()!=0 && password.length()!=0) {
	                    ResultSet rs=c.s.executeQuery("select * from Users where loginid='"+email+"' and pswd='"+password+"'");
	                    if(rs.next()) {   
	                        id=EmailTextField.getText();
	                        new WelcomeScreen();
	                        new ContactManagerApp().setVisible(true);  
	                        dispose();
	                    }
	                    else {   
	                        JOptionPane.showMessageDialog(null,"Invalid email and password");
	                        dispose();
	                    }
	                }
	                else {
	                    JOptionPane.showMessageDialog(null, "Invalid Details");
	                    dispose();
	                }
	             }catch(Exception ex) {
	                 ex.printStackTrace();
	                 dispose();
	             }
			}
		});
        contentPane.add(SignInBtn);

        signUpBtn = new JButton("Sign Up");
        signUpBtn.setBounds(180,450,100,30);
        signUpBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SignUp().setVisible(true);
				dispose();
			}
		});
        contentPane.add(signUpBtn);
        
        blank=new JButton("");
        blank.setBounds(180,450,100,30);
        blank.setSize(100,30);
        blank.setBorderPainted(false);
        blank.setContentAreaFilled(false);
        contentPane.add(blank);
        
    }
    
//    public static void main(String[] args) {
//		new LogIn().setVisible(true);
//	}
}
