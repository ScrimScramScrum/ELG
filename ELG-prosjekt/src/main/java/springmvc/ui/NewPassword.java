package springmvc.ui;

import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

public class NewPassword {
    
    @Size(min=8)
    @NotEmpty 
    private String oldPw;
    
    @Size(min=8)
    @NotEmpty
    private String newPw; 
    
    @Size(min=8)
    @NotEmpty
    private String confirmPw; 

    public String getOldPw() {
        return oldPw;
    }

    public void setOldPw(String oldPw) {
        this.oldPw = oldPw;
    }

    public String getNewPw() {
        return newPw;
    }

    public void setNewPw(String newPw) {
        this.newPw = newPw;
    }

    public String getConfirmPw() {
        return confirmPw;
    }

    public void setConfirmPw(String confirmPw) {
        this.confirmPw = confirmPw;
    }
}
