package contactmanager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
 
@SuppressWarnings("serial")
public class SignUp extends JDialog {

	JLabel lblName,lblEmail,lblPassword,lblSecret,PgTitle,SignInText;
	JLabel image=new JLabel(new ImageIcon("MIB.png"));
    JTextField NameTextField, EmailTextField;
    JPasswordField PswdTextField, SecretTextField;
    JButton SignUpBtn,signInBtn,blank;
    private JPanel contentPane;

    public SignUp() {
    	setTitle("Contacts Manager");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 10, 450, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBackground(Color.BLACK);
		setContentPane(contentPane);
		setVisible(true);
		
		image.setBounds(100,45,250,250);
        image.setSize(250,250);
        image.setBackground(Color.WHITE);
		contentPane.add(image);
		
        PgTitle=new JLabel("SignUp");
        PgTitle.setForeground(Color.GREEN.darker());
        PgTitle.setBounds(140,20,200,60);
        PgTitle.setFont(new Font("Algerian",Font.BOLD,50));
        contentPane.add(PgTitle);

        lblName=new JLabel("Name : ");
        lblName.setFont(new Font("Serif", Font.BOLD, 20));
        lblName.setForeground(Color.GREEN.darker());
        lblName.setBounds(40,280,150,30);//x,y,width,height
        contentPane.add(lblName);

        NameTextField=new JTextField(" Enter your Name",30);
        NameTextField.setFont(new Font("Serif", Font.PLAIN, 20));
        NameTextField.setForeground(Color.GRAY);
        NameTextField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
            	NameTextField.setText(null);
            	NameTextField.setForeground(Color.BLACK);
            }public void focusLost(FocusEvent e) {}
		});
        NameTextField.setBounds(170,280,150,30);
        NameTextField.setSize(220, 30);
        contentPane.add(NameTextField);

        lblEmail=new JLabel("Email : ");
        lblEmail.setFont(new Font("Serif", Font.BOLD, 20));
        lblEmail.setBounds(40,330,150,30);//x,y,width,height
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
        EmailTextField.setBounds(170,330,150,30);
        EmailTextField.setSize(220, 30);
        contentPane.add(EmailTextField);

        lblPassword=new JLabel("Password : ");
        lblPassword.setFont(new Font("Serif", Font.BOLD, 20));
        lblPassword.setForeground(Color.GREEN.darker());
        lblPassword.setBounds(40,380,150,30);
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
        PswdTextField.setBounds(170,380,150,30);
        PswdTextField.setSize(220, 30);
        contentPane.add(PswdTextField);

        lblSecret = new JLabel("Secret Code : ");
        lblSecret.setFont(new Font("Serif", Font.BOLD, 20));
        lblSecret.setForeground(Color.GREEN.darker());
        lblSecret.setBounds(40,430,150,30);
        contentPane.add(lblSecret);

        SecretTextField=new JPasswordField();
        SecretTextField.setText("********");
        SecretTextField.setForeground(Color.GRAY);
        SecretTextField.setEchoChar('*');
        SecretTextField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
            	SecretTextField.setText(null);
            	SecretTextField.setForeground(Color.BLACK);
            }public void focusLost(FocusEvent e) {}
		});
        SecretTextField.setFont(new Font("Serif", Font.BOLD, 20));
        SecretTextField.setBounds(170,430,150,30);
        SecretTextField.setSize(220, 30);
        contentPane.add(SecretTextField);

        SignUpBtn=new JButton("Sign Up");
        SignUpBtn.setBackground(Color.GREEN.darker());
        SignUpBtn.setForeground(Color.WHITE);
        SignUpBtn.setBounds(180,490,100,30);
        SignUpBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                PreparedStatement stmt=null;
                try {
                	ContactsDB c = new ContactsDB();
                    String name = NameTextField.getText();
	                String email = EmailTextField.getText();
	                String password = new String(PswdTextField.getPassword());
                    String secret = new String(SecretTextField.getPassword());
                    if(name.length()!=0 && email.length()!=0 && password.length()!=0 && secret.length()!=0) {
                    	ResultSet res = c.stmt.executeQuery("select * from Users where loginid='"+email+"' or secret = '"+secret+"'");
	                    if(res.next()) JOptionPane.showMessageDialog(null, "Accout already exist with the entered details.");
	                    else {
		                    stmt = new ContactsDB().conn.prepareStatement("insert into Users(name,loginid,pswd,secret) values(?,?,?,?)");
	                        stmt.setString(1, name);
	                        stmt.setString(2, email);
	                        stmt.setString(3, password);
	                        stmt.setString(4, secret);
	                        stmt.executeUpdate();
	                        stmt.close();
	                        System.out.println("New User added");
	                        //Creating view for new user
	                        ContactsDB.createView(email);
	                    }
                        new LogIn().setVisible(true);  
                        dispose();
	                }
	                else {
	                    JOptionPane.showMessageDialog(null, "Invalid Details\n Enter Details Properly");
	                    dispose();
	                }
	             }catch(Exception ex) {
	                 ex.printStackTrace();
	                 dispose();
	             }
			}
		});
        contentPane.add(SignUpBtn);
        
        SignInText = new JLabel("Already have an account?");
        SignInText.setFont(new Font("Calibri", Font.PLAIN, 15));
        SignInText.setForeground(Color.WHITE);
        SignInText.setBounds(150,540,200,30);
        contentPane.add(SignInText);

        signInBtn=new JButton("Sign In");
        signInBtn.setBackground(Color.GREEN.darker());
        signInBtn.setForeground(Color.WHITE);
        signInBtn.setBounds(180,570,100,30);
        signInBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LogIn().setVisible(true);
				dispose();
			}
		});
        contentPane.add(signInBtn);

    	JLabel cpr = new JLabel("\u00a9 MIB (Mirza Iliyaz Baig)");
    	cpr.setFont(new Font("arial",Font.BOLD,12));
        cpr.setBounds(165,610,570,50);
        cpr.setForeground(Color.WHITE);
        contentPane.add(cpr);
        
        blank=new JButton("");
        blank.setBounds(180,580,100,30);
        blank.setSize(100,30);
        blank.setBorderPainted(false);
        blank.setContentAreaFilled(false);
        contentPane.add(blank);
    }
    
    public static void main(String[] args) {
		new SignUp().setVisible(true);
	}
}
