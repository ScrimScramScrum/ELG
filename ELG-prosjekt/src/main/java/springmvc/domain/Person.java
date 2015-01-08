
package springmvc.domain;


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
    
    private String classId;
    

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

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }    

    @Override
    public String toString() {
        return email + " " + fname + " " + lname;
    }
}
