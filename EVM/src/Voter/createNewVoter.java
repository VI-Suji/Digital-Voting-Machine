package Voter;

import Poll.Valid;
import Crypto.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.io.FileWriter;
import evm.Error;
import java.io.File;
//import Voter.Voter ;   //compile in parent directory of voter i.e in src otherwise error

//voter validity and removal of duplicates can be done only with address field provided!!
import javax.crypto.IllegalBlockSizeException;
import java.security.*;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.BadPaddingException;

class VoteEligibility extends Exception{
	public VoteEligibility(String s){
		super(s);
	}
}

class NewVoter{
        public static String s,c,dob;
        public static long uid;
        private static String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAKAUZV+tjiNBKhlBZbKBnzeugpdYPhh5PbHanjV0aQ+LF7vetPYhbTiCVqA3a+Chmge44+prlqd3qQCYra6OYIe7oPVq4mETa1c/7IuSlKJgxC5wMqYKxYydb1eULkrs5IvvtNddx+9O/JlyM5sTPosgFHOzr4WqkVtQ71IkR+HrAgMBAAECgYAkQLo8kteP0GAyXAcmCAkA2Tql/8wASuTX9ITD4lsws/VqDKO64hMUKyBnJGX/91kkypCDNF5oCsdxZSJgV8owViYWZPnbvEcNqLtqgs7nj1UHuX9S5yYIPGN/mHL6OJJ7sosOd6rqdpg6JRRkAKUV+tmN/7Gh0+GFXM+ug6mgwQJBAO9/+CWpCAVoGxCA+YsTMb82fTOmGYMkZOAfQsvIV2v6DC8eJrSa+c0yCOTa3tirlCkhBfB08f8U2iEPS+Gu3bECQQCrG7O0gYmFL2RX1O+37ovyyHTbst4s4xbLW4jLzbSoimL235lCdIC+fllEEP96wPAiqo6dzmdH8KsGmVozsVRbAkB0ME8AZjp/9Pt8TDXD5LHzo8mlruUdnCBcIo5TMoRG2+3hRe1dHPonNCjgbdZCoyqjsWOiPfnQ2Brigvs7J4xhAkBGRiZUKC92x7QKbqXVgN9xYuq7oIanIM0nz/wq190uq0dh5Qtow7hshC/dSK3kmIEHe8z++tpoLWvQVgM538apAkBoSNfaTkDZhFavuiVl6L8cWCoDcJBItip8wKQhXwHp0O3HLg10OEd14M58ooNfpgt+8D8/8/2OOFaR0HzA+2Dm";
        public NewVoter(String s,int id,String dob,String con) throws IOException{
        this.s=s;
        this.uid=id;
        this.dob=dob;
        this.c=con;
        try{
            vote();
        }
        catch(IllegalBlockSizeException|InvalidKeyException|NoSuchPaddingException|BadPaddingException f){
                System.out.println("crypto error");
            }
        }
	public static void getDiff(GregorianCalendar a,GregorianCalendar b) throws VoteEligibility{
        int diff = b.get(Calendar.YEAR) - a.get(Calendar.YEAR);
            if (a.get(Calendar.MONTH) > b.get(Calendar.MONTH) || 
                   (a.get(Calendar.MONTH) == b.get(Calendar.MONTH) && a.get(Calendar.DATE) > b.get(Calendar.DATE))) {
                                    diff--;
                                }
        if(diff<18)
        	throw new VoteEligibility("under 18 years of age !\nnot eligible for application");
	}

	public static void vote() throws IOException,IllegalBlockSizeException,InvalidKeyException,
										 NoSuchPaddingException,BadPaddingException{
                
                Decrypt.decrypt(new File("files/voter.encrypted"),privateKey);
                try{
                    BufferedReader br=new BufferedReader(new FileReader("files/voter.decrypted"));
                    FileWriter fw=new FileWriter("files/voter.txt",true);
                    String s;
                    while((s=br.readLine())!=null)
                    {
                        fw.write(s+"\n");
                    }
                    fw.close();
                    br.close();
                }
                catch(Exception e)
                {
                    new Error(e.getMessage());
                }
		try(
                        FileWriter out = new FileWriter("files/voter.txt",true)){
			int count=1;
			GregorianCalendar g ;
			while(count<=1){
				//data should be read from aadhar database or enter manually
				String[] date=dob.split("/",3);
				try{
                                    //System.out.println("hello");
				g = new GregorianCalendar(Integer.parseInt(date[0]),Integer.parseInt(date[1])-1,Integer.parseInt(date[2]));
                getDiff(g,new GregorianCalendar());
                }
                catch(VoteEligibility e){
                      new Error(e.getMessage());
                      count++;
                      continue;
                }
                if(Valid.validCons(c)){
				Voter v = new Voter(uid,s,g,c);
				String output = v.getVoterId()+ " " + s + " " + dob +" "+c;
				out.write(output);
				out.write("\n");
                                Error.display("Successfully added");
			    }
			    else{
			    	new Error("unidentified constituency");
			    }
				++count;
			}
		}
		catch(IOException e){
			new Error("The system has detected some failure!");
		}
          
            new Encrypt(new File("files/voter.txt"));
            try{
                FileWriter f=new FileWriter("files/voter.txt");
                FileWriter w=new FileWriter("files/voter.decrypted");
                f.write("");
                w.write("");
                f.close();
                w.close();
            }
            catch(Exception e)
            {
                new Error(e.getMessage());
            }
            
	}
}