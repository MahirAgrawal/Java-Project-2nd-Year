
import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

@SuppressWarnings("serial")
/**
 Creates the panel with the rectangle bars of array numbers to sort in array 
*/ 
public class MidPanel extends JPanel {
  private int width, height;
  private ArrayList<Integer> arr;
  private int selected1, selected2,widthOfRectangle = 0,gap = 10,offsetX = 10, offsetY = 10,selected1X = 0, selected2X = 0;

  public MidPanel(int width, int height, ArrayList<Integer> arr) {
    this.width = width;
    this.height = height;
    this.arr = arr;
    Dimension d = new Dimension(this.width, this.height);
    this.setPreferredSize(d);
    this.widthOfRectangle = (this.width - (offsetX * 2) - (gap * (arr.size() - 1))) / this.arr.size();
    this.selected1 = 0;
    this.selected2 = 1;
    this.selected1X = (int) offsetX;
    this.selected2X = (int) offsetX + ((int) gap + (int) widthOfRectangle);
  }

  /** draw the rectangular bars representing the two numbers of decision space*/
  void drawRectangle(int selected1, int selected2) {
    this.selected1 = selected1;
    this.selected2 = selected2;
    this.selected1X = (int)offsetX+((int)gap+(int)widthOfRectangle)*selected1;
    this.selected2X = (int)offsetX+((int)gap+(int)widthOfRectangle)*selected2;
    repaint();
  }

  /**
   paint the midpanel i.e. the panel with rectangles using
   the array reference which is shared among all panels
   */
  @Override
  public void paint(Graphics g){
    super.paintComponent(g);
    for(int i = 0;i < arr.size();i++){
      if(i == selected1 || i == selected2){
        g.setColor(Color.RED);
        if(i == selected1)
          g.fill3DRect(selected1X, height - offsetY, widthOfRectangle,-arr.get(i),true);
        else
          g.fill3DRect(selected2X, height - offsetY, widthOfRectangle,-arr.get(i),true);
      }
      else{
        g.setColor(Color.YELLOW);
        g.fill3DRect((int)offsetX+((int)gap+(int)widthOfRectangle)*i, height - offsetY, widthOfRectangle,-arr.get(i),true);
      }    
    }
  }

}
