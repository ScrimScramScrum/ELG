
package springmvc.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    
    @RequestMapping(value = "login" , method=RequestMethod.GET)
    public String person(@ModelAttribute Login login, @ModelAttribute("sendNewPassword") SendNewPassword sendNewPassword) {
        System.out.println("111111111");
        return "firstLogin";
    }
    
    @RequestMapping(value = "firstLogin" , method=RequestMethod.GET)
    public String newLogin(@ModelAttribute Login login, @ModelAttribute("sendNewPassword") SendNewPassword sendNewPassword) {
        System.out.println("222222222");
        return "firstLogin";
    }
    
    
    @RequestMapping(value = "forgotPasswordFromLogin" , method=RequestMethod.GET)
    public String sendToForgotPassword(@ModelAttribute("sendNewPassword") SendNewPassword sendNewPassword, Model modell) {
        return "forgotPasswordFromLogin";
    }
    
    @RequestMapping(value = "sendNewPassword")
    public String sendNewPassword(@Valid @ModelAttribute("sendNewPassword") SendNewPassword sendNewPassword, BindingResult error, Model modell, @ModelAttribute Login login, @ModelAttribute NewPassword newPassword) {
        System.out.println("NEWPASSWORD ON THE WAY TO: "+sendNewPassword.getEmail());
        
        if(error.hasErrors()){
            System.out.println("errors in forgotten password. ");
            return "forgotPasswordFromLogin";
        }
        
        Person person = personService.getPerson(sendNewPassword.getEmail());
        //Person has to be pulled from session?
        if (person == null){ // Denne kjører, Hvorfor vil ikke feilmeldingen vises? 
            System.out.println("Feil: Noe gikk galt ved endring av passord");
            modell.addAttribute("sendNewPasswordError", "something went wrong with the resend of the Password");
            return "forgotPasswordFromLogin";
        }else if (personService.generateNewPassword(person)){
            System.out.println("New Password is sent");
            modell.addAttribute("regeneratedPassword", "<br>Passordet er nå sent på mailen din: "+person.getEmail()+"<br> &nbsp"); 
        } else {
            System.out.println("Error, something went wrong with the resend of the Password");
            modell.addAttribute("Error, something went wrong with the resend of the Password"); 
            return "forgotPasswordFromLogin";

        }
        return "firstLogin";
    } 
}

 /*
    @RequestMapping(value = "resemblegame", method = RequestMethod.POST)
    public ModelAndView resembleGame(ModelAndView mav, @RequestParam("gameid") String id){
        int gameid = Integer.parseInt(id);
        ResembleGame resembleGame = gameListService.getResembleGame(gameid);
        mav.addObject("resembleGame", resembleGame);
        mav.addObject("resembleTask", resembleTaskService.getResembleTask(resembleGame.getCurrentTask())); 
        mav.setViewName("resembleGame");
        return mav; 
    }
    
   Dette ligger i AdministrateController. */
    
    
    /*
    Vil slette dette, men venter litt! 
    
    @RequestMapping(value = "newPassword")
    public String newPassword(@ModelAttribute NewPassword newPassword, Model modell, @ModelAttribute Login login, @ModelAttribute("sendNewPassword") SendNewPassword sendNewPassword) {
        System.out.println("NewPassword blir nå kjort. ");
        Person inLoggedPerson = new Person("TEST@GMAIL.COM","TESTFORNAME","TESTETTERNAVN");
        //Person has to be pulled from session?
        if(personService.generateNewPassword(inLoggedPerson)){
            modell.addAttribute("regeneratedPassword", "Passordet er nå sent på mailen din:"); 

        } else {
            System.out.println("Error, something went wrong with the resend of the Password");
            modell.addAttribute("Error, something went wrong with the resend of the Password"); 
        }
        return "login";
    }
    
    */