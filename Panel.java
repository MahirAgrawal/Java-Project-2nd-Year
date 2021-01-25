import java.awt.*;
import javax.swing.*;

abstract public class Panel {

    private JPanel mainPanel;

    public Panel(Dimension d) {
        mainPanel = new JPanel();
        mainPanel.setPreferredSize(d);
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,3));
    }

    public JPanel getPanel(){
      return mainPanel;
    }

    abstract public void paintComponent(Graphics g);
};