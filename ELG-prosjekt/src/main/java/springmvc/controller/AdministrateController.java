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
import springmvc.domain.Person;
import springmvc.service.*;
import springmvc.ui.NewPassword; 
import springmvc.ui.AddNewClassId;




@Controller
public class AdministrateController {
    
    @Autowired
    private PersonService personService;
    
         
    
    @RequestMapping(value = "administrateAccount" , method=RequestMethod.GET)
    public String adminAccount(@ModelAttribute NewPassword newPassword, @ModelAttribute("addNewClassIdAttribute") AddNewClassId addNewClassIdAttribute){
        System.out.println("GET kjorer");
        return "administrateAccount";
    }
    
    
    @RequestMapping(value = "changePassword" , method=RequestMethod.POST)
    public String changePass(@Valid @ModelAttribute NewPassword newPassword, BindingResult error, Model modell) {
        System.out.println("POST kjorer");

        Person inLoggedPerson = new Person("TEST@GMAIL.COM","NAVN","ETTERNAVN");

        if (personService.changePassword(
                inLoggedPerson, 
                newPassword.getOldPw(), 
                newPassword.getNewPw(), 
                newPassword.getConfirmPw()
        )){
            modell.addAttribute("changedPassword", "Passordet er endret"); 
        } else {
            modell.addAttribute("changedPassword", "Noe gikk feil under endring av passordet");
        }
        
        
        if(error.hasErrors()){
            System.out.println(" Change password error ");
            //modell.addAttribute("melding", "Personnr ikke fylt ut riktig"); 
            return "administrateAccount";
        }
        
        
        return "administrateAccount";
    }
    
    
    
    @RequestMapping(value = "newPassword")
    public String newPassword(@ModelAttribute NewPassword newPassword, Model modell) {
        System.out.println("NEWPASSWORD ON THE WASY");
        
        Person inLoggedPerson = new Person("TEST@GMAIL.COM","TESTFORNAME","TESTETTERNAVN");
        //Person has to be pulled from session?
        if(personService.generateNewPassword(inLoggedPerson)){
            System.out.println("New Password is sent");
            modell.addAttribute("regeneratedPassword", "Passordet er nå sent på mailen din:"); 

        } else {
            System.out.println("Error, something went wrong with the resend of the Password");
            modell.addAttribute("Error, something went wrong with the resend of the Password"); 

        }
        
        return "administrateAccount";
    }
    
    
    
    
    @RequestMapping(value = "addClassId" , method=RequestMethod.POST)
    public String addNewClassId(@Valid @ModelAttribute("addNewClassIdAttribute") AddNewClassId addNewClassIdAttribute, BindingResult error, Model modell, @ModelAttribute NewPassword newPassword) {
        System.out.println("Post ADd Class kjorer");

        Person inLoggedPerson = new Person("TEST@GMAIL.COM","NAVN","ETTERNAVN");
 
        
        if(personService.setClassId(inLoggedPerson, addNewClassIdAttribute.getClassId())){
            modell.addAttribute("NewClassMessage", "Du er nå registrert i klasse: "+addNewClassIdAttribute.getClassId()); 
        } else {
            modell.addAttribute("NewClassMessage", "Feil, noe er galt. "); 
            
        }
        
        
        if(error.hasErrors()){
            System.out.println(" error with Add Class ID");
            modell.addAttribute("NewClassMessage", "Feil, for få tegn"); 
            return "administrateAccount";
        }
        
        
        return "administrateAccount";
    }
    
    
}
