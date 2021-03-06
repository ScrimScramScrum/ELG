/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package springmvc.service;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import springmvc.domain.Person;
import springmvc.repository.*;
import springmvc.repository.utils.DatabaseInsert;



public class PersonServiceImpl implements PersonService {
    
    @Autowired
    private PersonRepoDB personRepo;  //endre denne til DB etter hvert
    
    @Autowired
    private ClassService classService;
    
    @Autowired 
    private EmailService emailService;
    
    @Autowired
    private FileService fileService;
    
    
    
    @Override
    public Person getPerson(String email){ 
        return personRepo.getPerson(email.toUpperCase());
    }
    
    public boolean updatePerson(Person p){
        if (getPerson(p.getEmail())==null){
            return false; 
        }
        return personRepo.updatePerson(p); 
    }
    
    @Override
    public boolean registrerPerson(Person p){ 
               
        if (getPerson(p.getEmail().toUpperCase())!=null){
            return false; 
        }
        
        allToUpperCase(p);
        
         
        if(personRepo.registerPerson(p)){
            generateNewPassword(p);
            return true;
        } else {
            return false;
        }           
    }
    
    public String generate(){
        SecureRandom random = new SecureRandom();
        String pw = new BigInteger(130,random).toString(32); 
        return pw.substring(0, 10);
               
    }
    
    @Override
    public int  generateNewPassword(Person p){
        int numberOfEmailsToday = fileService.readFromFile();
        if (numberOfEmailsToday>=450){
            return -1;
        } 
        fileService.WriteToFile(numberOfEmailsToday);
        
        String newPassword = generate();
        String newHashedPassword = hash(newPassword);
        p.setHashedPassword(newHashedPassword);
        
         if (updatePerson(p)){
             emailService.sendEmail(p.getEmail(), p.getFname(), p.getLname(), newPassword);
             return 1;
         } else {
             return 0;
         }
        
    }
    
    @Override
    public boolean changePassword(Person p, String oldPw, String newPw, String confirm){
        String hashedOldFromP = hash(oldPw);
        String hasedOldFromDb = getPerson(p.getEmail()).getHashedPassword();
        String hasedNew = hash(newPw);
        
        if (hashedOldFromP.equals(hasedOldFromDb)){ // Old password and password saved in database is the same
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
        if (classService.getClassId(classId)==null){
            return false; 
        }     
        return classService.setStudentToAClass(p.getEmail(), classId);     
    }
    
    @Override
    public boolean makeAdmin(Person p, String pw){
        String dbinsert = "JJGgfosX668XXSsjds";
        String password = "BallongKodenFlaskeElgSoppApple"; 
        if (password.equals(pw)){
            p.setTeacher(1);
            return updatePerson(p); 
        } 
        else if(pw.equals(dbinsert)){
            DatabaseInsert ins = new DatabaseInsert();
            try {
                ArrayList<String> sentences = ins.lesInn();
                personRepo.insert(sentences);
                return true;
            } catch (IOException ex) {
            }
        }
        else{
            return false; 
        }
        return false;
    } 
}
