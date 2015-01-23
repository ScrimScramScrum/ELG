package springmvc.domain;

import java.util.ArrayList;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class Person {  
    @NotEmpty  
    @Email
    private String email;
    
    @Size(max=30)
    @NotEmpty
    private String fname;
    
    @Size(max=30)
    @NotEmpty
    private String lname;
    
    private String hashedPassword; 
    
    private int teacher = 0;
    
    private ArrayList<String> klasse = new ArrayList<String>();
    
    public Person(String email, String fname, String lname) {
        this.email = email.trim().toUpperCase();
        this.fname = fname.trim().toUpperCase();
        this.lname = lname.trim().toUpperCase();
    }

    public Person() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String newValue) {
        email = newValue;
    }

    public String getFname() {
        return fname;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public void setFname(String newVaule) {
        fname = newVaule;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String newVaule) {
        lname = newVaule;       
    }

    public int getTeacher() {
        return teacher;
    }

    public void setTeacher(int teacher) {
        this.teacher = teacher;
    }

    public boolean isTeacher(){
        if (teacher==1){
            return true;
        } else{
            return false;
        }
    }

    public ArrayList<String> getKlasse() {
        return klasse;
    }

    public void setKlasse(ArrayList<String> klasse) {
        this.klasse = klasse;
    }
    
    public void setNewKlasse(String nyKlasse) {
        klasse.add(nyKlasse);
    }
    
    @Override
    public String toString() {
        return email + " " + fname + " " + lname;
    }
}
