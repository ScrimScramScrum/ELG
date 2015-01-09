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
import org.springframework.beans.factory.annotation.Autowired;
import springmvc.repository.*;



public class PersonServiceTesting implements PersonService {
    
    @Autowired
    private PersonRepoDB personRepo;  //endre denne til DB etter hvert
    
    
    
    @Override
    public Person getPerson(String email){ 
        //hent fra repo
        System.out.println(email);
        return personRepo.getPerson(email.toUpperCase());
        
    }
    
    public boolean updatePerson(Person p){
        if (getPerson(p.getEmail())==null){
            return false; 
        }
        System.out.println("Person is beeing updated"+p);
        return personRepo.updatePerson(p); 
    }
    
    @Override
    public boolean registrerPerson(Person p){ // HUSK: Når database kommer, skal ny registrering sammenlignes med allerede registrerte brukere.
               
        if (getPerson(p.getEmail().toUpperCase())!=null){
            return false; 
        }
        
        String pw = generate();         
        //THIS PASSWORD TO BE SENT ON EMAIL IN A LATER SPRINT.         
        String hashedPw = hash(pw);      
        p.setHashedPassword(hashedPw); 
        allToUpperCase(p);
        if(personRepo.registerPerson(p)){
            System.out.println("Registered person in DB");
            
            return true;
        } else {
            System.out.println("Error in register person in DB");
            return false;
        }
           
    }
    
    public String generate(){
        SecureRandom random = new SecureRandom();
        String pw = new BigInteger(130,random).toString(32); 
        return pw.substring(0, 10);
               
    }
    
    public boolean generateNewPassword(Person p){
        
        String newPassword = generate();
        System.out.println("Nytt pass: " + newPassword);
        String newHashedPassword = hash(newPassword);
        
        p.setHashedPassword(newHashedPassword);
        
        return updatePerson(p);
        
    }
    
    @Override
    public boolean changePassword(Person p, String oldPw, String newPw, String confirm){
        String hashedOldFromP = hash(oldPw);
        String hasedOldFromDb = getPerson(p.getEmail()).getHashedPassword();
        String hasedNew = hash(newPw);
        
        System.out.println(hashedOldFromP);
        System.out.println(hasedOldFromDb); // Hent fra databasen, ikke fra p. 
        
        System.out.println();
        if (hashedOldFromP.equals(hasedOldFromDb)){ // Old password and password saved in database is the same
            System.out.println("Gammelt passord stemmer!");
            if (confirm.equals(newPw)){
                String newHashedPw = hash(newPw); 
                p.setHashedPassword(newHashedPw);
                updatePerson(p); 
                return true; 
            } 
        }
        return false; 
    }
    

    
    private void allToUpperCase(Person p){
        p.setEmail(p.getEmail().toUpperCase());
        p.setFname(p.getFname().toUpperCase());
        p.setLname(p.getLname().toUpperCase());        
    }
    
    @Override
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
    
    
    @Override
    public boolean setClassId(Person p, String classId){
        
        if (classId.equals("admin")){
            
            System.out.println("person set as admin");
            //sett p som lærer. 
            
            
        }
        
        //TODO FJERNET 
        //p.setClassId(classId);
        
        if(updatePerson(p)){
            System.out.println("Set Class ID OK");
            return true;
        } else {
            System.out.println("Set Class ID failed. ");
            return false; 
        }
        
        
    }
    
    @Override
    public boolean makeAdmin(Person p, String pw){
        String password = "123"; // MAYBE change where to save the password?  
        if (password.equals(pw)){
            p.setTeacher(1);
            System.out.println("Person set as admin");
            return updatePerson(p); 
        } else {
            System.out.println("Set Class ID failed. ");
            return false; 
        }
    } 
    
}
