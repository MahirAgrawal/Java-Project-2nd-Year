import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

@SuppressWarnings("serial")
public class SortingVisualizer extends JFrame implements ActionListener, Runnable {

  ArrayList<Integer> array, copyArray;
  FirstPanel first;
  MidPanel second;
  JPanel mainframe;
  int winHeight, winWidth;
  final int sizeOfArray = 25;
  int curri = 0, currj = 0;
  Thread sortingThread;
  static boolean endsort = false;

  public static void main(String[] args) {
    SortingVisualizer frame = new SortingVisualizer();
    frame.initUI();
  }


  /**intiliazes the variables of frame including button,label and panel
   and asssign memory to the thread of sorting algo
  */
  public void initUI() {
    setTitle("Bubble Sort Visualizer");
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    winHeight = (int) dimension.getHeight();
    winWidth = (int) dimension.getWidth();
    winWidth -= 80;
    setSize(winWidth, winHeight);
    setLocationRelativeTo(null);
    array = new ArrayList<Integer>();
    for (int i = 0; i < sizeOfArray; i++)
      array.add(i, ((int) (Math.random() * 1000)) % (int) (winHeight * 0.85));
    copyArray = new ArrayList<Integer>();
    for (int i = 0; i < sizeOfArray; i++)
      copyArray.add(i, array.get(i));
    first = new FirstPanel((int) (winWidth * 0.25), (int) (0.85 * winHeight), this);
    first.setPreferredSize(new Dimension((int) (winWidth * 0.30), (int) (0.85 * winHeight)));
    first.changeText(Integer.toString(array.get(0)), Integer.toString(array.get(1)));
    second = new MidPanel((int) (winWidth * 0.7), (int) (0.85 * winHeight), array);
    second.setPreferredSize(new Dimension((int) (winWidth * 0.70), (int) (0.85 * winHeight)));
    mainframe = new JPanel();
    mainframe.add(first);
    mainframe.add(second);
    add(mainframe);
    pack();
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setResizable(false);
    this.setVisible(true);
    sortingThread = new Thread(this);
    sortingThread.start();
    sortingThread.suspend();
  }

  /**event handling of buttons which includes playButton,pauseButton,resetButton and shuffleButton
  */
  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals("SHUFFLE")) {
      curri = 0;
      currj = 0;
      Collections.shuffle(array);
      for (int i = 0; i < sizeOfArray; i++)
        copyArray.set(i, array.get(i));
      first.changeText(Integer.toString(array.get(currj)), Integer.toString(array.get(currj + 1)));
      second.repaint();
    }

    else if (e.getActionCommand().equals("PLAY")) {
      first.disableButton("PLAY");
      first.disableButton("SHUFFLE");
      first.enableButton("PAUSE");
      first.disableButton("RESET");
      if (sortingThread.isAlive())
        sortingThread.resume();
    }

    else if (e.getActionCommand().equals("PAUSE")) {
      first.disableButton("PAUSE");
      first.enableButton("PLAY");
      first.enableButton("RESET");
      if (sortingThread.isAlive())
        sortingThread.suspend();
    }

    else if (e.getActionCommand().equals("RESET")) {
      endsort = true;
      if (sortingThread.isAlive()) {
        sortingThread.resume();
        try {
          sortingThread.join();
        } catch (InterruptedException e1) {
          e1.printStackTrace();
        }
      }
      endsort = false;
      sortingThread = new Thread(this);
      sortingThread.start();
      sortingThread.suspend();
      curri = 0;
      currj = 0;
      for (int k = 0; k < sizeOfArray; k++)
        array.set(k, copyArray.get(k));
      first.changeText(Integer.toString(array.get(currj)), Integer.toString(array.get(currj + 1)));
      second.drawRectangle(currj, currj + 1);
      first.enableButton("PLAY");
      first.disableButton("RESET");
      first.disableButton("PAUSE");
      first.enableButton("SHUFFLE");
    }
  }

  /**thread of sorting algorithm which is bubble sort
   also sends the data to integrate with both of the panels 
   and calls to take decision made for two selected indices on them
  */
  @Override
  public void run() {
    if (endsort)
      return;
    int swap = 0;
    int maxele = array.get(0);
    for (curri = 0; curri < sizeOfArray - 1; curri++) {
      swap = 0;
      for (currj = 0; currj < sizeOfArray - 1 - curri; currj++) {
        try {
          Thread.sleep(500 - first.getSpeed());
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        if (!endsort) {
          first.changeText(Integer.toString(array.get(currj)), Integer.toString(array.get(currj + 1)));
          second.drawRectangle(currj, currj + 1);
          if (array.get(currj) > array.get(currj + 1)) {
            swap++;
            maxele = array.get(currj);
            array.set(currj, array.get(currj + 1));
            array.set(currj + 1, maxele);
            try {
              Thread.sleep(300 - first.getSpeed());
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
            first.changeText(Integer.toString(array.get(currj)), Integer.toString(array.get(currj + 1)));
            first.swappingAction(true);
            try {
              Thread.sleep(300 - first.getSpeed());
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
            second.drawRectangle(currj,currj+1);
          }
          else{
            first.swappingAction(false);
            try {
              Thread.sleep(800 - first.getSpeed());
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        }
        else
          break;
      }
      if(swap == 0)
        break;
      if(endsort)
        break;
    }
    endsort = true;
  }

};