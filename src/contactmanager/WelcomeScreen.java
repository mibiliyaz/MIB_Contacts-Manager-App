package contactmanager;

import javax.swing.*;
import java.awt.*;
 
public class WelcomeScreen extends JDialog {
    JFrame frame;
    JLabel image=new JLabel(new ImageIcon("MIB.png"));
    JLabel text1=new JLabel("Contacts");
    JLabel text2=new JLabel("Manager");
    JLabel message=new JLabel();//Crating a JLabel for displaying the message
    JProgressBar progressBar=new JProgressBar();
    WelcomeScreen()
    {
        createGUI();
        addImage();
        addText();
        addProgressBar();
        addMessage();
        runningPBar();
    }
    public void createGUI(){
        frame=new JFrame();
        frame.getContentPane().setLayout(null);
        frame.setUndecorated(true);
        frame.setSize(450,700);
        setUndecorated(true);
        getRootPane().setBorder(
           BorderFactory.createMatteBorder(3, 3, 3, 3, Color.black));
        frame.setLocationRelativeTo(null);
//        frame.getContentPane().setBackground(Color.white);
        frame.setVisible(true);
 
    }
    public void addImage(){
    	image.setBounds(115,15,250,250);
        image.setSize(250,250);
        frame.add(image);
    }
    public void addText()
    {
    	text1.setFont(new Font("Algerian",Font.BOLD,65));
        text1.setBounds(65,250,400,100);
        text1.setForeground(Color.BLUE);
        frame.add(text1);

        text2.setFont(new Font("Algerian",Font.BOLD,75));
        text2.setBounds(45,330,400,100);
        text2.setForeground(Color.BLUE);
        frame.add(text2);

    }
    public void addMessage()
    {
        message.setBounds(180,450,570,50);//Setting the size and location of the label
        message.setForeground(Color.gray);//Setting foreground Color
        message.setFont(new Font("arial",Font.BOLD,15));//Setting font properties
        frame.add(message);//adding label to the frame
    }
    public void addProgressBar(){
        progressBar.setBounds(25,486,400,30);
        progressBar.setBorderPainted(true);
        progressBar.setStringPainted(true);
        progressBar.setBackground(Color.WHITE);
        progressBar.setForeground(Color.BLACK);
        progressBar.setValue(0);
        frame.add(progressBar);
    }
    public void runningPBar(){
        int i=0;//Creating an integer variable and initializing it to 0
        while( i<=100)
        {
            try{
                Thread.sleep(25);//Pausing execution for 50 milliseconds
                progressBar.setValue(i);//Setting value of Progress Bar
                message.setText("CONNECTING.....");//Setting text of the message JLabel
                i++;
                if(i==100)
                    frame.dispose();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
		new WelcomeScreen();
	}
}