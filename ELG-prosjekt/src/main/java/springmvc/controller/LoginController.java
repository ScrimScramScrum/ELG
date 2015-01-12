/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
        return "login";
    }
    
    @RequestMapping(value = "firstLogin" , method=RequestMethod.GET)
    public String newLogin(@ModelAttribute Login login, @ModelAttribute("sendNewPassword") SendNewPassword sendNewPassword) {
        return "firstLogin";
    }
    
    
    
    @RequestMapping(value = "login" , method=RequestMethod.POST)
    public ModelAndView CreateNewPerson(ModelAndView mav, HttpSession session, @Valid @ModelAttribute("login") Login login, BindingResult error, Model modell, @ModelAttribute("sendNewPassword") SendNewPassword sendNewPassword) {
        if(error.hasErrors()){
            System.out.println(" Passord tomt, eller ikke gyldig Email-adresse.  ");
            //modell.addAttribute("melding", "Personnr ikke fylt ut riktig"); 
            mav.setViewName("firstLogin");
            return mav;
        }
        
        if (loginService.compareInformation(login)) {            
            Person inloggedPerson = personService.getPerson(login.getEmail());
            User user = new User(inloggedPerson.getEmail(),inloggedPerson.getFname(), inloggedPerson.getLname());
            user.setInLogged(true);            
            session.setAttribute("user", user);            
            mav.setViewName("index");                        
            return mav;
            
        } else {
            System.out.println("Innlogging feilet.  "); 
            modell.addAttribute("wrongPassword","Feil brukernavn/passord. Prøv på nytt");
            
            mav.setViewName("firstLogin");
            return mav; 
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
    
    
    @RequestMapping(value = " forgotPasswordFromLogin" , method=RequestMethod.GET)
    public String sendToForgotPassword(@ModelAttribute("sendNewPassword") SendNewPassword sendNewPassword) {
        return "forgotPasswordFromLogin";
    }
    
    
    
    @RequestMapping(value = "sendNewPassword")
    public String sendNewPassword(@ModelAttribute("sendNewPassword") SendNewPassword sendNewPassword, Model modell, @ModelAttribute Login login, @ModelAttribute NewPassword newPassword) {
        System.out.println("NEWPASSWORD ON THE WAY TO: "+sendNewPassword.getEmail());
        
        Person person = personService.getPerson(sendNewPassword.getEmail());
        //Person has to be pulled from session?
        if(personService.generateNewPassword(person)){
            System.out.println("New Password is sent");
            modell.addAttribute("regeneratedPassword", "Passordet er nå sent på mailen din:"); 
        } else {
            System.out.println("Error, something went wrong with the resend of the Password");
            modell.addAttribute("Error, something went wrong with the resend of the Password"); 

        }
        return "firstLogin";
    } 
    
    
    
    
            
    @RequestMapping(value = "loginAsGuest") 
    public String loginAsGuestFunction(HttpSession session) {
        System.out.println("Logger inn som guest");
        
        User user = new User("GUEST","GUEST", "");
        user.setInLogged(true);            
        session.setAttribute("user", user);            
        return "index";
    } 
}
