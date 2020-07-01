import java.util.*;

public class sudoku{

    public static void main(String[] args){

        
    }

    public static boolean isValidToPlaceNumber(char[][] board, int x, int y, int num){
       //row
       
       for(int j =0;j<9;j++){
           if(board[x][j]='0'==num){
               return false;
           }
       }
      //column
       for(int i =0;i<9;i++){
        if(board[i][y]-'0'==num){
            return false;
        }
       }

       //3x3 matrix
       int smx = (x/3)*3;
       int smy = (y/3)*3;

       int endx = smx+2;
       int endy = smy+2;

       for(int i = smx;i<=endx;i++){
           for(int j = smy;j<=endy;j++){
               if(board[i][j]==num){
                   return false;
               }
           }
       }
       return true;
    }

    public static boolean sudokuSolver(char[][] board, int idx){

        int x = idx/9;
        int y = idx%9;

        if(board[x][y]== '.'){
           for(int num =1; num <= 9;num++){
               if(isValidToPlaceNumber(board,x,y,nu )){
                   board[x][y]= (char)(num);
                   if(sudokuSolver(board,idx+1)){
                       return true;
                   }

                   board[x][y]='.';
               }
           }  

        }else{
            return sudokuSolver(board, idx+1);
        }

        return false;
    }
}