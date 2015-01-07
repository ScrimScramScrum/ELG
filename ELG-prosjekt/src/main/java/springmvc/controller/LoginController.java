/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package springmvc.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springmvc.domain.Login;
import springmvc.service.LoginService;

/**
 *
 * @author Hoxmark
 */
@Controller
public class LoginController {
    
    @Autowired
    private LoginService loginService;
    
    
    
    
    //TODO: Trenger vi denne?
    @RequestMapping(value = "login" , method=RequestMethod.GET)
    public String person(@ModelAttribute Login login) {
        return "login";
    }
    
    @RequestMapping(value = "login" , method=RequestMethod.POST)
    public String CreateNewPerson(@Valid @ModelAttribute("login") Login login, BindingResult error, Model modell) {
        if(error.hasErrors()){
            System.out.println(" Passord tomt, eller ikke gyldig Email-adresse.  ");
            //modell.addAttribute("melding", "Personnr ikke fylt ut riktig"); 
            return "login";
        }
        
        if (loginService.compareInformation(login)) {
            System.out.println("Du er nå logget inn. ");
            
            return "index";
            
        } else {
            System.out.println("Innlogging feilet.  "); 
            modell.addAttribute("wrongPassword","Feil brukernavn/passord. Prøv på nytt");

            return "login"; 
        }
                
                
    }
    
    
    
}
