/**MineSweeper GUI*/

import java.awt.*;       // using layouts
import java.awt.event.*; // using AWT event classes and listener interfaces
import javax.swing.*;    // using Swing components and containers

public class MinesweeperGUI
{
   // fields
   private final double BUTTON_TO_SIZE_RATIO = 38.8;              // multiply number of rows/columns by this number to size frame
   private int counter;
   
   // swing GUI elements
   private JDialog frame;                                         // dialog box to hold containers
   private Container gridContainer, miscContainer;                // containers to hold components
   private int[][] array;                                         // integer representation of grid, holds values
   private boolean[][] clickedButtons;                            // boolean representation of grid, holds whether or not a button has been clicked
   private JButton[][] buttonArray;                               // array of buttons
   
   // images for button icons
   private final ImageIcon blank = new ImageIcon();
   private final ImageIcon num1 = new ImageIcon("num1.png");
   private final ImageIcon num2 = new ImageIcon("num2.png");
   private final ImageIcon num3 = new ImageIcon("num3.png");
   private final ImageIcon num4 = new ImageIcon("num4.png");
   private final ImageIcon flag = new ImageIcon(/*FIXME*/);
   private final ImageIcon mine = new ImageIcon(/*FIXME*/);
   
   // constructor
   
   /**MinesweeperGUI
      create GUI object
      setup JDialog customizations
      create array of buttons
      make frame visible
      @param int[][] array of values */
   public MinesweeperGUI(int[][] array)
   {
      this.array = array;
      this.clickedButtons = new boolean[array.length][array[0].length];
      
      frame = new JDialog();                                      // create frame (window) to contain GUI components
      frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);   // end program when window is closed
      
      gridContainer = frame.getContentPane();                     // initialize gridContainer
      
      generateBoard();                                            // create array of buttons inside gridContainer
      
      // FIXME: use this container for timer, flags, etc
      miscContainer = frame.getContentPane();                     // initialize miscContainer
      
      int length = (int)(array.length * BUTTON_TO_SIZE_RATIO);
      int width = (int)(array[0].length * BUTTON_TO_SIZE_RATIO);
      
      frame.setSize(length, width);                               // set size of frame
      frame.setVisible(true);                                     // show frame
   }
   
   // methods
   
   /**generateBoard
      initializes a 2d JButton[][] array of a specified size
      assume length and width of array are the same */
   private void generateBoard()
   {
      int size = this.array.length;
      buttonArray = new JButton[size][size];                      // initialize 2d array of specified size
      frame.setLayout(new GridLayout(size, size, 1, 1));          // grid layout of specified size, horizontal and vertical gaps of 1 pixels
      
      for (int i = 0; i < size; ++i)                              // for each element in buttonArray
      {
         for (int j = 0; j < size; ++j)
         {
            button(array[i][j], i, j);                            // create a button, passing value from array and coordinates
         }
      }
   }
   
   /**button
      create button using image
      create ActionListeners for each button
      modify button based on value
      add to gridContainer container
      @param int value from int[][] array
      @param int xCoord X coordinate of array
      @param int yCoord Y coordinate of array
      @see generateBoard */
      
   private void button(int value, int xCoord, int yCoord)
   {
      buttonArray[xCoord][yCoord] = new JButton(blank);        // add new button to buttonArray
      
      rightClickDetector(buttonArray[xCoord][yCoord]);         // FIXME: flagging system
      
      buttonArray[xCoord][yCoord].addActionListener( new ActionListener()  // add ActionListener to button
      {
         public void actionPerformed(ActionEvent e)            // if button is clicked
         {
            clickedButtons[xCoord][yCoord] = true;             // set button to clicked
            
            switch (value)                                     // change or hide button icon based on value
            {
               case 1:                                         // if value = 1
                  buttonArray[xCoord][yCoord].setIcon(num1);   // set icon to 1
                  break;
                  //counter++;
               case 2:                                         // if value = 2
                  buttonArray[xCoord][yCoord].setIcon(num2);   // set icon to 2
                  break;
                  //counter++;
               case 3:                                         // if value = 3
                  buttonArray[xCoord][yCoord].setIcon(num3);   // set icon to 3
                  break;
                  //counter++;
               case 4:                                         // if value = 4
                  buttonArray[xCoord][yCoord].setIcon(num4);   // set icon to 4
                  break;
                  //counter++;
               case 9:                                         // if value = 9 (mine)
                  buttonArray[xCoord][yCoord].setIcon(mine);   // set icon to mine
                  break;
               case 0:                                         // if value = 0 (blank)
                  buttonArray[xCoord][yCoord].setVisible(false);  // hide button
                  exposeNearby(xCoord, yCoord);                // expose nearby blank spaces
                  //counter++;
                  System.out.print(counter);
                  break;
               default:
                  System.out.println("ERROR: Unknown value, see button method in MinesweeperGUI");
            }
         }
      });
      
      gridContainer.add(buttonArray[xCoord][yCoord]);          // add button to gridContainer
   }
   
   /**rightClickDetector
      */
   private void rightClickDetector(JButton button)
   {
      // FIXME: flagging system
   }
   
   /**getBoard
      @return JButton[][] that contains all buttons */
   public JButton[][] getBoard()
   {
      return this.buttonArray;
   }
   
   /**exposeNearby
      exposes nearby spaces
      called when a blank space is clicked
      @param int xCoord of button
      @param int yCoord of button */
   public void exposeNearby(int xCoord, int yCoord)
   {
      int rMin = (xCoord == 0) ? 0 : xCoord - 1;                        // minimum row to be accessed
      int rMax = (xCoord == array.length - 1) ? xCoord : xCoord + 1;    // maximum row to be accessed
      int cMin = (yCoord == 0) ? 0 : yCoord - 1;	                     // minimum column to be accessed
      int cMax = (yCoord == array.length - 1) ? yCoord : yCoord + 1;    // maximum column to be accessed
      
      for (int i = rMin; i <= rMax; i++)                                // for each row to be accessed
      {
         for (int j = cMin; j <= cMax; j++)                             // for each column to be accessed
         {
            if (clickedButtons[i][j] == false)                          // if button hasn't been clicked yet
            {
               buttonArray[i][j].doClick();                             // click button
            }
         }
      }
   }
   
   /**main
      @param String[] arguments for cmd line */
   public static void main(String[] args)
   {
      int[][] grid = BoardRandomizer.generateBoard();
      
      MinesweeperGUI myGUI = new MinesweeperGUI(grid);
   }
}
