package springmvc.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import springmvc.domain.Login;
import springmvc.domain.Person;
import springmvc.domain.User;
import springmvc.service.LoginService;
import springmvc.service.PersonService;
import springmvc.ui.AddNewClassId;
import springmvc.ui.NewPassword;
import springmvc.ui.SendNewPassword;

@Controller
public class LoginController {
    
    @Autowired
    private LoginService loginService;
    
    @Autowired
    private PersonService personService;
    
    @ExceptionHandler(Throwable.class)
    public String handleTException(Throwable t) {
        return "error";
    } 

    @ExceptionHandler(Exception.class)
    public String handleException(Throwable t) {
        return "error";
    }
    
    @RequestMapping(value = "login" , method=RequestMethod.GET)
    public String person(@ModelAttribute Login login, @ModelAttribute("sendNewPassword") SendNewPassword sendNewPassword) {
        return "firstLogin";
    }
    
    @RequestMapping(value = "firstLogin" , method=RequestMethod.GET)
    public String newLogin(@ModelAttribute Login login, @ModelAttribute("sendNewPassword") SendNewPassword sendNewPassword) {
        return "firstLogin";
    }
    
    
    @RequestMapping(value = "forgotPasswordFromLogin" , method=RequestMethod.GET)
    public String sendToForgotPassword(@ModelAttribute("sendNewPassword") SendNewPassword sendNewPassword, Model modell) {
        return "forgotPasswordFromLogin";
    }
    
    @RequestMapping(value = "sendNewPassword")
    public String sendNewPassword(@Valid @ModelAttribute("sendNewPassword") SendNewPassword sendNewPassword, BindingResult error, Model modell, @ModelAttribute Login login, @ModelAttribute NewPassword newPassword) {

        if(error.hasErrors()){
            return "forgotPasswordFromLogin";
        }
        
        Person person = personService.getPerson(sendNewPassword.getEmail());
        if (person == null){ 
            modell.addAttribute("sendNewPasswordError", "Feil ved endring av passord");
            return "forgotPasswordFromLogin";
        }else if (personService.generateNewPassword(person)==1){
            modell.addAttribute("regeneratedPassword", "<br>Passordet er nå sent på mailen din: "+person.getEmail()+"<br> &nbsp"); 
        }else if (personService.generateNewPassword(person)==-1){
            modell.addAttribute("regeneratedPassword", "<br>Feil. Prøv igjen i morgen!" +"<br> &nbsp"); 
        } else {
            modell.addAttribute("sendNewPasswordError", "Feil ved endring av passord"); 
            return "forgotPasswordFromLogin";
        }
        return "firstLogin";
    } 
}