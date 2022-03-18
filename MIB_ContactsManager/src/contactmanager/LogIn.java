package contactmanager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
 
@SuppressWarnings("serial")
public class LogIn extends JDialog {

	JLabel lblEmail,lblPassword,PgTitle,image, SignUpText;
    JTextField EmailTextField;
    JPasswordField PswdTextField;
    JButton SignInBtn,blank,signUpBtn;
    private JPanel contentPane;
    
    String uid;
    
    public LogIn() {
    	setTitle("Contacts Manager");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 10, 450, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBackground(Color.BLACK);
		setContentPane(contentPane);
		setVisible(true);
		
		image = new JLabel(new ImageIcon("MIB.png"));
		image.setBounds(100,45,60,60);
        image.setSize(250,250);
        contentPane.add(image);
		
        PgTitle = new JLabel("SignIn");
        PgTitle.setBounds(145,20,200,60);
        PgTitle.setFont(new Font("Algerian",Font.BOLD,50));
        PgTitle.setForeground(Color.GREEN.darker());
        contentPane.add(PgTitle);

        lblEmail = new JLabel("Email : ");
        lblEmail.setFont(new Font("Serif", Font.BOLD, 20));
        lblEmail.setBounds(50,290,150,30);//x,y,width,height
        lblEmail.setForeground(Color.GREEN.darker());
        contentPane.add(lblEmail);

        EmailTextField = new JTextField(" Enter your Email",30);
		EmailTextField.setForeground(Color.GRAY);
        EmailTextField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
            	EmailTextField.setText(null);
            	EmailTextField.setForeground(Color.BLACK);
            }public void focusLost(FocusEvent e) {}
		});
        EmailTextField.setFont(new Font("Serif", Font.PLAIN, 20));
        EmailTextField.setBounds(160,290,150,30);
        EmailTextField.setSize(220, 30);
        contentPane.add(EmailTextField);

        lblPassword = new JLabel("Password : ");
        lblPassword.setFont(new Font("Serif", Font.BOLD, 20));
        lblPassword.setForeground(Color.GREEN.darker());
        lblPassword.setBounds(50,340,150,30);
        contentPane.add(lblPassword);

        PswdTextField = new JPasswordField();
        PswdTextField.setText("********");
        PswdTextField.setForeground(Color.GRAY);
    	PswdTextField.setEchoChar('*');
        PswdTextField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
            	PswdTextField.setText(null);
            	PswdTextField.setForeground(Color.BLACK);
            }public void focusLost(FocusEvent e) {}
		});
        PswdTextField.setFont(new Font("Serif", Font.BOLD, 20));
        PswdTextField.setBounds(160,340,150,30);
        PswdTextField.setSize(220, 30);
        contentPane.add(PswdTextField);

        SignInBtn = new JButton("Log In");
        SignInBtn.setBackground(Color.GREEN.darker());
        SignInBtn.setForeground(Color.WHITE);
        SignInBtn.setBounds(180,410,100,30);
        SignInBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
	                ContactsDB c = new ContactsDB();
	                String email = EmailTextField.getText();
	                String password = new String(PswdTextField.getPassword());
	                if(email.length()!=0 && password.length()!=0) {
	                    ResultSet res = c.stmt.executeQuery("select * from Users where loginid='"+email+"' and pswd='"+password+"'");
	                    if(res.next()) {
	                    	dispose();
	                    	res = c.stmt.executeQuery("select uid from Users where loginid='"+email+"'");
	                    	if(res.next()) uid = res.getString("uid");
	                    	new ContactManagerApp(true,uid);
	                    	System.out.println(uid+"-"+email+" Logged in Successfully");
	                    }
	                    else JOptionPane.showMessageDialog(null,"Invalid email and password");
	                }
	                else JOptionPane.showMessageDialog(null, "Invalid Details");
	             }catch(Exception ex) {
	                 ex.printStackTrace();
	             }
			}
		});
        contentPane.add(SignInBtn);

        SignUpText = new JLabel("Don't  have  an  account?");
        SignUpText.setFont(new Font("Calibri", Font.PLAIN, 15));
        SignUpText.setForeground(Color.WHITE);
        SignUpText.setBounds(150,470,200,30);
        contentPane.add(SignUpText);
        
        signUpBtn = new JButton("Sign Up");
        signUpBtn.setBackground(Color.GREEN.darker());
        signUpBtn.setForeground(Color.WHITE);
        signUpBtn.setBounds(180,500,100,30);
        signUpBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SignUp().setVisible(true);
				dispose();
			}
		});
        contentPane.add(signUpBtn);
        
    	JLabel cpr = new JLabel("\u00a9 MIB (Mirza Iliyaz Baig)");
    	cpr.setFont(new Font("arial",Font.BOLD,12));
        cpr.setBounds(165,610,570,50);
        cpr.setForeground(Color.WHITE);
        contentPane.add(cpr);
        
        blank=new JButton("");
        blank.setBounds(180,450,100,30);
        blank.setSize(100,30);
        blank.setBorderPainted(false);
        blank.setContentAreaFilled(false);
        contentPane.add(blank);
        
    }
    
    public static void main(String[] args) {
		new LogIn().setVisible(true);
	}
}
