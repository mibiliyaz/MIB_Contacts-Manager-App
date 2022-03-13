package contactmanager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
 
public class SignUp extends JDialog {

	JLabel lblName,lblEmail,lblPassword,lblSecret,PgTitle;
	JLabel image=new JLabel(new ImageIcon("MIB.png"));
    JTextField NameTextField, EmailTextField, PswdTextField, SecretTextField;
    JButton SignUpBtn,signInBtn,blank;
    private JPanel contentPane;

    public SignUp() {
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
		
        PgTitle=new JLabel("SignUp");
        PgTitle.setBounds(140,20,200,60);
        PgTitle.setFont(new Font("TimesNewRoman",Font.BOLD,50));
        contentPane.add(PgTitle);

        lblName=new JLabel("Name: ");
        lblName.setFont(new Font("Serif", Font.BOLD, 20));
        lblName.setBounds(50,280,150,30);//x,y,width,height
        contentPane.add(lblName);

        NameTextField=new JTextField(30);
        NameTextField.setFont(new Font("Serif", Font.BOLD, 20));
        NameTextField.setBounds(165,280,150,30);
        NameTextField.setSize(220, 30);
        NameTextField.setOpaque(false);
        contentPane.add(NameTextField);

        lblEmail=new JLabel("Email: ");
        lblEmail.setFont(new Font("Serif", Font.BOLD, 20));
        lblEmail.setBounds(50,330,150,30);//x,y,width,height
        contentPane.add(lblEmail);

        EmailTextField=new JTextField(30);
        EmailTextField.setFont(new Font("Serif", Font.BOLD, 20));
        EmailTextField.setBounds(165,330,150,30);
        EmailTextField.setSize(220, 30);
        EmailTextField.setOpaque(false);
        contentPane.add(EmailTextField);

        lblPassword=new JLabel("Password: ");
        lblPassword.setFont(new Font("Serif", Font.BOLD, 20));
        lblPassword.setBounds(50,380,150,30);
        contentPane.add(lblPassword);

        PswdTextField=new JPasswordField();
        PswdTextField.setFont(new Font("Serif", Font.BOLD, 20));
        PswdTextField.setBounds(165,380,150,30);
        PswdTextField.setSize(220, 30);
        PswdTextField.setOpaque(false);
        contentPane.add(PswdTextField);

        lblSecret = new JLabel("Secret Code: ");
        lblSecret.setFont(new Font("Serif", Font.BOLD, 20));
        lblSecret.setBounds(50,430,150,30);
        contentPane.add(lblSecret);

        SecretTextField=new JPasswordField();
        SecretTextField.setFont(new Font("Serif", Font.BOLD, 20));
        SecretTextField.setBounds(165,430,150,30);
        SecretTextField.setSize(220, 30);
        contentPane.add(SecretTextField);

        SignUpBtn=new JButton("Sign Up");
        SignUpBtn.setBounds(180,480,100,30);
        SignUpBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                PreparedStatement stmt=null;
                try {
	                new DBConnect();
                    String name = NameTextField.getText();
	                String email = EmailTextField.getText();
	                String password = PswdTextField.getText();
                    String secret = SecretTextField.getText();
	                if(name.length()!=0 && email.length()!=0 && password.length()!=0 && secret.length()!=0) {
	                    stmt = new ContactsDB().conn.prepareStatement("insert into Users(name,loginid,pswd,secret) values(?,?,?,?)");
                        stmt.setString(1, name);
                        stmt.setString(2, email);
                        stmt.setString(3, password);
                        stmt.setString(4, secret);
                        stmt.executeUpdate();
                        stmt.close();
                        System.out.println("New User added");
                        
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

        signInBtn=new JButton("Sign In");
        signInBtn.setBounds(180,530,100,30);
        signInBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LogIn().setVisible(true);
				dispose();
			}
		});
        contentPane.add(signInBtn);
        
        blank=new JButton("");
        blank.setBounds(180,450,100,30);
        blank.setSize(100,30);
        blank.setBorderPainted(false);
        blank.setContentAreaFilled(false);
        contentPane.add(blank);
    }
    
//    public static void main(String[] args) {
//		new SignUp().setVisible(true);
//	}
}
