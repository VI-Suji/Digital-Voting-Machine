
package Candidate;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author Tapan
 */
public class Validity {
    public static boolean partyPlace(String[] w,String[] list){
        //System.out.println("i am here 1 !");
        //diff candidate with same party and cons are not allowed
        //System.out.println(w.length);
       
        //convert to lowercase and check
        return !(w[w.length-2].equals(list[1]) && w[w.length-1].equals(list[2]));
        }
    public static boolean candidateParty(String[] w,String[] list){
        //candidate can contest only for same party,if diff party then terminate
        //System.out.println("i am here 2 !");
       
        return (w[w.length-1].equals(list[2]) && w[0].equals(list[0])||
                !w[0].equals(list[0]));
    }
    public static boolean idName(String[] list){
        try{
            //checking if voter id and name are equal else application is rejected!
         BufferedReader b = new BufferedReader(new FileReader("files/voter.txt"));
         String line;
         while((line = b.readLine())!=null){
            String[] words = line.toLowerCase().split(" ",5);
            String [] name = list[3].toLowerCase().split(" ",3);
            int len = name.length;
            //System.out.println("w");
            System.out.println(words[0]);
            System.out.println(list[0]);
            if(words[0].equals(list[0]) && ((words.length-3)==len) &&
                ((len==1 && words[1].equals(name[0]))
                ||(len==2 && words[1].equals(name[0]) && words[2].equals(name[1]))
                ||(len>2 && words[1].equals(name[0]) && words[2].equals(name[1]) && words[3].equals(name[2])))){
                return true;
            }
         }
         return false;
        }
        catch(IOException ie){
            System.out.println("error");
        }
        return false;
        
    }
    public static int valid(String[] list) throws IOException{
        try{
        RandomAccessFile br = new RandomAccessFile("files/candidate.txt","r");
        String line;
        int valid=1;
        //vid cons party name        passed here
        
        while((line=br.readLine())!=null){
            //System.out.println(line);

            String[] w = line.toLowerCase().split(" ",5);
            
            //System.out.println(w[0]);
            //should check all lines whether new candidate list is valid
            //System.out.println(partyPlace(w,list));

            //System.out.println(candidateParty(w,list));
            //System.out.print(list[2]);
            if(!((partyPlace(w,list)) && (candidateParty(w,list))))
                valid=0;
        }
        br.seek(0);
        //check for independent
        while((line=br.readLine())!=null){
            String[] w = line.toLowerCase().split(" ",5);
            if(list[2].equals("independent")){
            if(list[0].equals(w[0])){
                System.out.println("entered");
                if(list[2].equals(w[w.length-1])){
                    if(list[1].equals(w[w.length-2])){
                         valid=0;
                         System.out.println("x");
                         break;
                    }    
                    else{
                         valid=1;
                         System.out.println("xx");
                    }    
                }
                else{
                    valid=0;
                    System.out.println("enteredxx");
                    break;
                }
                    
            }
            else
                valid=1;
        }
        }
        return valid;
        }
        catch(IOException ie){
            System.out.println("cannot find");
        }
        return -1;  //error occurred!
    }
    
}
