package springmvc.domain;

import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class Login {
   
    @NotEmpty  
    @Email
    private String email;
    
    @NotEmpty
    private String password;

    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Login(){
        
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
