/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package springmvc.service;

import springmvc.domain.Person;
import java.security.SecureRandom;
import java.math.BigInteger;
import java.security.MessageDigest;


/**
 *
 * @author Hoxmark
 */
public class PersonServiceTesting implements PersonService {
    
    @Override
    public Person getPerson(String email){ 
        
        if (email.equals("test@gmail.com")){
            Person dummy = new Person("test@gmail.com", "Lars", "Garberg"); 
            String passord = "testpassord"; 
            passord = hash(passord); 
            dummy.setHashedPassword(passord);
            
            return dummy; 
        }
        return null;
    }
    
    //public boolean oppdaterPerson(Person p);
    
    @Override
    public boolean registrerPerson(Person p){ // HUSK: Når database kommer, skal ny registrering sammenlignes med allerede registrerte brukere.
        String pw = generate(); 
        return true; 
    }
    
    public String generate(){
        SecureRandom random = new SecureRandom();
        String pw = new BigInteger(130,random).toString(32); 
        
        return pw.substring(0, 10);
    }
    

    public String hash(String input){
        // initial content:
        String generatedPass1 = null;
        try{
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            //Add password bytes to digest
            md.update(input.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPass1 = sb.toString();
 
        }catch(Exception e){
        }
        return generatedPass1;
    }
}
