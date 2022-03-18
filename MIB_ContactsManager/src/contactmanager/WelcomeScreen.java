package contactmanager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class WelcomeScreen extends JDialog {
	
	JLabel image, text1, text2, message, blank;
    JProgressBar progressBar;
    JPanel contentPane;
    
    public static boolean status = ContactManagerApp.status;
	
    public WelcomeScreen() {
    	System.out.println("Welcome to Contact Manager App");
    	setTitle("Welcome to Contact Manager App");
    	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 10, 450, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBackground(Color.BLACK);
		setContentPane(contentPane);
		setVisible(true);
		
		image = new JLabel(new ImageIcon("MIB.png"));
    	image.setBounds(100,20,100,100);
        image.setSize(250,250);
        contentPane.add(image);

        text1 = new JLabel("Contacts");
    	text1.setFont(new Font("Algerian",Font.BOLD,65));
        text1.setBounds(50,250,400,100);
        text1.setForeground(Color.GREEN.darker());
        contentPane.add(text1);
        
        text2 = new JLabel("Manager");
        text2.setFont(new Font("Algerian",Font.BOLD,75));
        text2.setBounds(30,330,400,100);
        text2.setForeground(Color.GREEN.darker());
        contentPane.add(text2);

        message = new JLabel("CONNECTING.....");
        message.setBounds(160,450,570,50);//Setting the size and location of the label
        message.setForeground(Color.GRAY.brighter());//Setting foreground Color
        message.setFont(new Font("arial",Font.BOLD,15));//Setting font properties
        contentPane.add(message);//adding label to the frame

        progressBar = new JProgressBar();
        progressBar.setBounds(20,486,400,30);
        progressBar.setSize(400, 30);
        progressBar.setFont(new Font("arial",Font.BOLD,18));
        progressBar.setBorderPainted(true);
        progressBar.setStringPainted(true);
        progressBar.setBackground(Color.WHITE);
        progressBar.setForeground(Color.DARK_GRAY.darker());
        progressBar.setValue(0);
        contentPane.add(progressBar);

    	JLabel cpr = new JLabel("\u00a9 MIB (Mirza Iliyaz Baig)");
    	cpr.setFont(new Font("arial",Font.BOLD,12));
        cpr.setBounds(165,610,570,50);
        cpr.setForeground(Color.WHITE);
        contentPane.add(cpr);

    	blank=new JLabel("");
        contentPane.add(blank);
        
    	int i=0;//Creating an integer variable and initializing it to 0
        while( i<=100) {
    		setVisible(true);
        	try {
                Thread.sleep(10);//Pausing execution for 50 milliseconds
                progressBar.setValue(i);//Setting value of Progress Bar
                if(i++ == 100) dispose();
            } catch(Exception e){ e.printStackTrace(); }
        }
	}
    public static void main(String[] args) {
    	new WelcomeScreen().setVisible(true);
	}
}
