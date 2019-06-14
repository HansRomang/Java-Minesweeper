/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/

import java.util.Random;

public class BoardRandomizer
{
   public static int[][] generateBoard()
   {

      int ROW;			//integer for rows
      int COL;			//integer for columns
      int mine;		//integer for mines

      Random rand = new Random ();	//creating random number generator

      ROW = 18;			//Rows
      COL = 18;			//Columns
      mine = 30;		   //number of mines


      int[][] board = new int[ROW][COL];	//creating grid array 
      int count = 0;		//counter 
      int a;			   //number for rows
      int b;			   //number for columns
      //while loop for mines
      while (count < mine)
      {
	      a = rand.nextInt (ROW);
	      b = rand.nextInt (COL);
	      if (board[a][b] != 9)	//while not a mine (value = 9) change it to mine
	      {
	         board[a][b] = 9;
	         count++;		//add to counter if mine is there
	      }
      }

      //for loop to create grid and fill
      for (int r = 0; r < board.length; r++)
      {
	      for (int c = 0; c < board[r].length; c++)
	      {
	         //created to check to make sure array isn't out of bounds
	         int rMax = r + 1;	//maxium amount of r at each point
	         int cMax = c + 1;	//maxium amount of columns at each point
	         int rMin = r - 1;	//minimum amount of rows at each point
      	   int cMin = c - 1;	//minimum amount of rows at each point
      	   int count1 = 0;	//count

	         if (r == 0)
	         {
		         rMin = 0;
	         }
	         if (c == 0)
	         {
		         cMin = 0;
	         }
	         if (r == ROW - 1)
	         {
		         rMax = ROW - 1;	//setting max number of rows to user input rows - 1 (so the counter will not go past that point
	         }
	         if (c == COL - 1)
	         {
		         cMax = COL - 1;	//setting max number of columns to user input cols - 1 (so the counter will not go past that point
	         }
	         //to find the positions where not equal to 9(mine)
	         if (board[r][c] != 9)
	         {
		         count1 = 0;	//setting counter equal to 0
		         //for loop to scan the grid
		         for (int i = rMin; i <= rMax; i++)
		         {
		            for (int j = cMin; j <= cMax; j++)
		            {
			            if (board[i][j] == 9)
			            {
			               count1++;	//if posistion is equal to 9 then add 1 to counter
			            }
			            if (count > 0)
			            {
			               board[r][c] = count1;	//if count is greater than 0 than set the position to the ammount of the counter
			            }
		            }
		         }
	         }
            // print out board
            System.out.print (board[r][c] + " ");
	      }
	      System.out.println ("");
      }
      return board;
   }
}
