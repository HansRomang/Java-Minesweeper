public class GameWin {
                     //We pass in our values
   public static boolean check(boolean[][] boardchecker, int numOfMines){
   
   int counter = 0;
   //Counter is created, this will be the number to check clicked tiles to see if we've won
   for(int i = 0; i < boardchecker.length; i++){
      
      for(int j=0; j <boardchecker.length; j++){
         if(boardchecker[i][j] == true){ 
         //If the tile is clicked
         counter++; 
         //Add to the counter
         }
      }
   }
   int totalTiles = boardchecker.length * boardchecker.length;
   //total tiles is the length of the board squared
   if(counter == (totalTiles - numOfMines)){
    //if the amount of tiles clicked is equal to the total tiles available   
   return true;
   //You win
      }
   return false;
   }

}