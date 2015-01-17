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
import springmvc.domain.Person;
import springmvc.service.*;
import springmvc.ui.SendNewPassword;


/**
 *
 * @author Hoxmark
 */
@Controller
public class PersonController {
    
    @Autowired
    private PersonService personService;
    
    @RequestMapping(value = "newPersonFromLogin" , method=RequestMethod.GET)
    public String person(@ModelAttribute Person person, BindingResult error, Model modell) {
        return "newPersonFromLogin";
    }
    
    @RequestMapping(value = "newPersonFromLogin" , method=RequestMethod.POST)
    public String CreateNewPerson(@Valid @ModelAttribute("person") Person person, BindingResult error, Model modell,@ModelAttribute Login login, @ModelAttribute("sendNewPassword") SendNewPassword sendNewPassword) {
        if(error.hasErrors()){
            System.out.println(" Validering feilet **** ");
            //modell.addAttribute("melding", "Personnr ikke fylt ut riktig"); 
            return "newPersonFromLogin";
        }
        if (personService.getPerson(person.getEmail()) != null){
            System.out.println("Feil ved registrering av ny person");
            modell.addAttribute("newPersonError", "Feil ved endring av passord");
            return "newPersonFromLogin";
        }
        
        if (personService.registrerPerson(person)) {
            modell.addAttribute("registeredOK", "&nbsp<br>"+person.getEmail() + " er registrert. Sjekk E-mailen din for passord. <br>&nbsp  ");
            System.out.println("Person service er OK og registrert");
            
            return "firstLogin";  // her er feilen
            
        } else {
            System.out.println("kan ikke registrere person");
            modell.addAttribute("melding","Denne eposten er allerede tatt");
            return "newPersonFromLogin";
        }        
    }
}
