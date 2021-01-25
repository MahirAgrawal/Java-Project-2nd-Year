import java.awt.Dimension;
import java.awt.event.*;

import javax.swing.JFrame;

public class Main implements ActionListener {
  private static FirstPanel f;
  
  public static void main(String[] args) {
    f = new FirstPanel(500,500,new Main());
    JFrame frame = new JFrame();
    frame.setSize(new Dimension(600,500));
    frame.add(f);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true); 
  }

  @Override
  public void actionPerformed(ActionEvent e) {
      System.out.println("Click");
      f.changeText(Integer.toString(i),"15");
      i++;
  }
};