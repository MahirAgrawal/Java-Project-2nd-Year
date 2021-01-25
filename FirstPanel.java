import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**firstpanel class which displays info of current selected two indices 
and decides whether to swap them or not and according to which
rectangles animation changes
*/
@SuppressWarnings("serial")
public class FirstPanel extends JPanel {
    private int width, height;
    JLabel num1,num2,message;
    Button shuffle,play,pause,reset;
    JSlider slider;

    /**
     Creates the First panel with the given height and width along with the actionlistener to send
     button action to the main frame
     */
    public FirstPanel(int width, int height,ActionListener AL) {
        this.width = width;
        this.height = height;
        Dimension d = new Dimension(this.width, this.height);
        this.setSize(d);
        this.setLayout(null);
        createButtonShuffle("SHUFFLE",AL);
        createButtonPlay("PLAY", AL);
        createButtonPause("PAUSE", AL);
        createButtonReset("RESET", AL);
        createLabel("NUM1","NUM2");
        this.disableButton("PAUSE");
        this.disableButton("RESET");
        slider = new JSlider(JSlider.HORIZONTAL, -50, 400, 100);
        slider.setBounds(30, 550, 200, 30);
        this.add(slider);
      }
    
    /**
     creates the shuffle button
     */
    public void createButtonShuffle(String label,ActionListener AL){
      shuffle = new Button(label);
      shuffle.setBackground(new Color(59, 89, 182));
      shuffle.setForeground(Color.WHITE);
      shuffle.setFont(new Font("Tahoma", Font.BOLD, 12));
      shuffle.setBounds(width/2 - 50, 100, 100, 50);
      this.add(shuffle);
      shuffle.addActionListener(AL);
    }

    /**
     creates the play button
     */
    public void createButtonPlay(String label,ActionListener AL){
      play = new Button(label);
      play.setForeground(Color.BLUE);
      play.setFont(new Font("Serif", Font.BOLD, 12));
      play.setBounds(15, 200, 70, 50);
      this.add(play);
      play.addActionListener(AL);
    }

    /**
     creates the pause button
     */
    public void createButtonPause(String label,ActionListener AL){
      pause = new Button(label);
      pause.setFont(new Font("Serif", Font.BOLD, 12));
      pause.setForeground(Color.BLUE);
      pause.setBounds(100, 200, 70, 50);
      this.add(pause);
      pause.addActionListener(AL);
    }

    /**
     creates the reset button
    */
    public void createButtonReset(String label,ActionListener AL){
      reset = new Button(label);
      reset.setForeground(Color.BLUE);
      reset.setFont(new Font("Serif", Font.BOLD, 12));
      reset.setBounds(185,200, 70, 50);
      this.add(reset);
      reset.addActionListener(AL);
    }

    /**
     creates the labels to display two numbers
    */
    public void createLabel(String label1,String label2){
        num1 = new JLabel(label1); 
        num1.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2)); 
        num1.setBounds(15,400,100,30);  
        num2 = new JLabel(label2);  
        JLabel comparesign = new JLabel();
        comparesign.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        comparesign.setBounds(140, 400, 30, 30);
        comparesign.setFont(new Font("Serif", Font.BOLD, 20));
        comparesign.setText(" <");
        num2.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        num2.setBounds(width/2 + 50,400,100,30);
        message = new JLabel("");
        message.setBorder(BorderFactory.createLineBorder(Color.orange,2));
        message.setBounds(width/2-75,500, 150, 30);
        this.add(num1);
        this.add(comparesign);
        this.add(num2);
        this.add(message);
    }

    /**
     returns the speed of animation needed by the user
     */
    public int getSpeed(){
      return this.slider.getValue();
    }


    /**called when swap is needed in the sequence
       also takes boolean argument to display respective messages 
       based on decision taken by sorting algo class
     */
    public void swappingAction(boolean swapFlag){
      message.setFont(new Font("Serif", Font.ROMAN_BASELINE, 25));
      if(swapFlag){
        message.setForeground(Color.RED);
        message.setText("  SWAP");
      }else{
         message.setForeground(Color.GREEN);
         message.setText(" NO SWAP");
      }
        
    }

    /**
     changes text in both number display label in which two numbers are display around which decision space is involved
     */
    public void changeText(String label1,String label2){
      num1.setFont(new Font("Serif",Font.BOLD,20));
      num1.setText(label1);
      num2.setFont(new Font("Serif",Font.BOLD,20));
      num2.setText(label2);
    }

    /**
     disables button during operation which are incompitable with running functions
     E.g. when playButton is pressed then shuffle,reset and playButton itself gets disabled and so are not having active
     action listener 
     */
    public void disableButton(String button){
      if(button.equals("SHUFFLE")){
        shuffle.setEnabled(false);
      }else if(button.equals("PLAY")){
        play.setEnabled(false);
      }else if(button.equals("PAUSE")){
        pause.setEnabled(false);
      }else if(button.equals("RESET")){
        reset.setEnabled(false);
      }
    }

    /**
     enables button during operation which are compitable with running functions
     E.g. when playButton is pressed then pause Button gets enabled and so is having active
     action listener 
     */
    public void enableButton(String button){
      if(button.equals("PLAY")){
        play.setEnabled(true);
      }else if(button.equals("SHUFFLE")){
        shuffle.setEnabled(true);
      }else if(button.equals("PAUSE")){
        pause.setEnabled(true);
      }else if(button.equals("RESET")){
        reset.setEnabled(true);
      }
    }
}