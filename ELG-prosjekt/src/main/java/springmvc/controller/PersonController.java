package springmvc.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springmvc.domain.Login;
import springmvc.domain.Person;
import springmvc.service.*;
import springmvc.ui.SendNewPassword;

@Controller
public class PersonController {
    
    @ExceptionHandler(Throwable.class)
    public String handleTException(Throwable t) {
        return "error";
    } 

    @ExceptionHandler(Exception.class)
    public String handleException(Throwable t) {
        return "error";
    }
    
    @Autowired
    private PersonService personService;
    
    @RequestMapping(value = "newPersonFromLogin" , method=RequestMethod.GET)
    public String person(@ModelAttribute Person person, BindingResult error, Model modell) {
        return "newPersonFromLogin";
    }
    
    @RequestMapping(value = "newPersonFromLogin" , method=RequestMethod.POST)
    public String CreateNewPerson(@Valid @ModelAttribute("person") Person person, BindingResult error, Model modell,@ModelAttribute Login login, @ModelAttribute("sendNewPassword") SendNewPassword sendNewPassword) {
        if(error.hasErrors()){
            return "newPersonFromLogin";
        }
        if (personService.getPerson(person.getEmail()) != null){
            modell.addAttribute("newPersonError", "Feil. E-mail-adressen er allerede registrert. ");
            return "newPersonFromLogin";
        }
        if (personService.registrerPerson(person)) {
            modell.addAttribute("registeredOK", "&nbsp<br>"+person.getEmail() + " er registrert. Sjekk E-mailen din for passord. <br>&nbsp  ");
            return "firstLogin"; 
        } else {
            modell.addAttribute("melding","Denne eposten er allerede tatt");
            return "newPersonFromLogin";
        }        
    }
}
