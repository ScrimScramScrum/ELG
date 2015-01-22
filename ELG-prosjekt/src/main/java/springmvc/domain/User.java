package springmvc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


public class User implements Serializable{
    @NotEmpty  
    @Email
    private String email;
    
    @Size(max=30)
    @NotEmpty
    private String fname;
    
    @Size(max=30)
    @NotEmpty
    private String lname;
    
    private boolean inLogged = false; 
    
    private boolean admin = false;
    
    private ArrayList<String> klasse = new ArrayList<String>(); 

    public User(String email, String fname, String lname) {
        this.email = email;
        this.fname = fname;
        this.lname = lname;
    }
    
    public User(String email, String fname, String lname, int admin) {
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        if(admin == 1){
            this.admin=true;
        }   
    }
    
    public User(){
        
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public boolean isInLogged() {
        return inLogged;
    }

    public void setInLogged(boolean inLogged) {
        this.inLogged = inLogged;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public ArrayList<String> getKlasse() {
        return klasse;
    }

    public void setKlasse(ArrayList<String> klasse) {
        this.klasse = klasse;
    }
    
    public void addNewKlasse(String newKlasse) {
        klasse.add(newKlasse);
    }
    
    public String getKlasseString(){
        String message = ""; 
        for (String klasse1 : klasse) {
            message += (klasse1 + ", "); 
        }
        return message; 
    }

    @Override
    public String toString() {
        return "User{" + "email=" + email + ", fname=" + fname + ", lname=" + lname + ", inLogged=" + inLogged + '}';
    } 
}