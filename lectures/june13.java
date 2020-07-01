import java.util.*;

public class june13 {
    
   public static void main(String[] args){
    
    permutationWithoutDuplicates("aba","");
    
  }

  public static int permutationWithDuplicates(String str, String ans){
    if(str.length()==0){
        System.out.println(ans);
        return 1;
    }

    int count = 0;

    for(int i = 0;i<str.length();i++){
        char ch = str.charAt(i);
        String ros = str.substring(0,i)+str.substring(i+1);
        count+= permutationWithDuplicates(ros,ans+ch);
    }
     return count;
  }

  public static int permutationWithoutDuplicates(String str, String ans){
    if(str.length()==0){
        System.out.println(ans);
        return 1;
    }

    int count = 0;
    boolean[] vis = new boolean[26];
    for(int i = 0;i<str.length();i++){
        char ch = str.charAt(i);
        if(!vis[ch-'a']){
        vis[ch-'a']=true;    
        String ros = str.substring(0,i)+str.substring(i+1);
        count+= permutationWithoutDuplicates(ros,ans+ch);
        }
    }
     return count;
  }

  public static boolean canPlaceH(){

  }

  public static void placeH(int r, int c, String word){
    boolean[] loc = new boolean[word.length()];
    for(int i =0;i<words.length;i++){
      if(board[r][c+i]=="-"){
        board[r][c+i]=word.charAt(i);
        
      }  
        
    }
  }

  public static void unplaceH(int r, )


}