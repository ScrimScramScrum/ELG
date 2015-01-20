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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import springmvc.domain.Login;
import springmvc.domain.Person;
import springmvc.domain.User;
import springmvc.service.*;
import springmvc.ui.AddNewClassId;
import springmvc.ui.MakeAdmin;
import springmvc.ui.NewPassword;
import springmvc.ui.makeNewClass;




@Controller
public class AdministrateController {
    
    @Autowired
    private PersonService personService;
    
    @Autowired 
    private ClassService classService;
    
         
    
    @RequestMapping(value = "administrateAccount" , method=RequestMethod.GET)
    public String adminAccount(@ModelAttribute NewPassword newPassword, @ModelAttribute("addNewClassIdAttribute") AddNewClassId addNewClassIdAttribute, @ModelAttribute("makeNewClassAttribute") makeNewClass makeNewClassAttribute, @ModelAttribute("makeAdmin") MakeAdmin makeAdmin, HttpSession session, @ModelAttribute Login login){
        System.out.println("GET kjorer NÅ");
        User user = (User)session.getAttribute("user");
        if (user == null){
            return "firstLogin"; 
        }
        if (user.getEmail().equals("GUEST")){
            return "about";
        } 
        return "administrateAccount";
    }
    
    
   @RequestMapping(value = "changePassword" , method=RequestMethod.POST)
    public String changePass(@ModelAttribute NewPassword newPassword, BindingResult error, Model modell, @ModelAttribute("makeNewClassAttribute") makeNewClass makeNewClassAttribute, @ModelAttribute("addNewClassIdAttribute") AddNewClassId addNewClassIdAttribute, @ModelAttribute("makeAdmin") MakeAdmin makeAdmin,HttpSession session) {
       
        if(error.hasErrors()){
            System.out.println(" Change password error ");
            modell.addAttribute("chooseSite", 1); 
            return "administrateAccount";
        }
        User user = (User)session.getAttribute("user");
        Person inLoggedPerson = personService.getPerson(user.getEmail());
        modell.addAttribute("user", user);
        
        if (personService.changePassword(
                inLoggedPerson, 
                newPassword.getOldPw(), 
                newPassword.getNewPw(), 
                newPassword.getConfirmPw()
        )){
            modell.addAttribute("changedPassword", "Passordet er endret."); 
        } else {
            modell.addAttribute("chooseSite", 1);
            modell.addAttribute("changedPassword", "Feil. Husk at passordet må være lengre enn 8 tegn.");
        }
        return "administrateAccount";
    }
    
    
    @RequestMapping(value = "addClassId" , method=RequestMethod.POST)
    public String addNewClassId(@ModelAttribute("addNewClassIdAttribute") AddNewClassId addNewClassIdAttribute, BindingResult error, @ModelAttribute("makeNewClassAttribute") makeNewClass makeNewClassAttribute, Model modell, @ModelAttribute NewPassword newPassword, @ModelAttribute("makeAdmin") MakeAdmin makeAdmin, HttpSession session) {
        System.out.println("Legg til ny klasse SasaSassk jører");
        
        if(error.hasErrors()){
            System.out.println(" error with Add Class ID");
            modell.addAttribute("chooseSite", 2);
            //modell.addAttribute("NewClassMessage", "Feil, for få tegn"); 
            return "administrateAccount";
            
        }

        User user = (User)session.getAttribute("user");
        Person inLoggedPerson = personService.getPerson(user.getEmail());
        modell.addAttribute("user", user);
        
        if (personService.setClassId(inLoggedPerson, addNewClassIdAttribute.getClassId())){
            System.out.println("Legger til i ny klasse ");
            modell.addAttribute("NewClassMessage", "Du er nå registrert i klasse: " + addNewClassIdAttribute.getClassId()); 
        } else { // ENDER HER OM FELTET ER TOMT. 
            modell.addAttribute("chooseSite", 2);
            modell.addAttribute("NewClassMessage", "Feil. Klassen eksisterer ikke. "); 
        }
        return "administrateAccount";
    }
    
    
    
    @RequestMapping(value = "makeNewAdmin" , method=RequestMethod.POST)
    public String makeNewAdmin(@ModelAttribute("makeAdmin") MakeAdmin makeAdmin, BindingResult error, @ModelAttribute("makeNewClassAttribute") makeNewClass makeNewClassAttribute, Model modell, @ModelAttribute("addNewClassIdAttribute") AddNewClassId addNewClassIdAttribute, @ModelAttribute NewPassword newPassword, HttpSession session) {
        System.out.println("makeAdmin kjorer");
        if(error.hasErrors()){
            System.out.println("ERROR with making user Admin/Teacher. ");
            modell.addAttribute("chooseSite", 3);
            // modell.addAttribute("NewClassMessage", "Feil, for få tegn"); 
            return "administrateAccount";
        }
        
        //Person inLoggedPerson = new Person("TEST@GMAIL.COM","NAVN","ETTERNAVN");
        
        User user = (User)session.getAttribute("user");
        Person inLoggedPerson = personService.getPerson(user.getEmail());
        modell.addAttribute("user", user);

        if (personService.makeAdmin(inLoggedPerson, makeAdmin.getMakeAdminPw())){ // Registreres som admin. 
            user.setAdmin(true);
            System.out.println("Person set as admin");
            modell.addAttribute("makeAdminMessage", "Du har nå admin-rettigheter. "); 
            
        } else { // Feiler med å registrere seg som admin.  // ENDER HER. 
            modell.addAttribute("chooseSite", 3);
            modell.addAttribute("makeAdminMessage", "Feil. Administrator-passordet var ikke riktig. ");
        }
        
        return "administrateAccount";
    }
    
    @RequestMapping(value = "makeClass" , method=RequestMethod.POST)
    public String makeClass(@ModelAttribute("makeNewClassAttribute") makeNewClass makeNewClassAttribute, BindingResult error, @ModelAttribute("makeAdmin") MakeAdmin makeAdmin, Model modell, @ModelAttribute("addNewClassIdAttribute") AddNewClassId addNewClassIdAttribute, @ModelAttribute NewPassword newPassword,HttpSession session) {
        System.out.println("REGISTRER NY KLASSE kjorer");
        
        if(error.hasErrors()){
            System.out.println("ERROR with making user Admin/Teacher. ");
            modell.addAttribute("chooseSite", 4);
            return "administrateAccount";
        }
        User user = (User)session.getAttribute("user");
        Person inLoggedPerson = personService.getPerson(user.getEmail());
        modell.addAttribute("user", user);

        if (classService.registrateNewClassId(makeNewClassAttribute.getClassId())){  
            System.out.println("Ny klasse registrert");
            modell.addAttribute("makeClassMessage", "Ny klasse ble registrert.  "); 
        } else { // Feiler med å registrere ny klasse  
            modell.addAttribute("chooseSite", 4);
            modell.addAttribute("makeClassMessage", "Feil, Klassen finnes fra før eller .  "); 
        }
        
        
        return "administrateAccount";
    } 
    
    @RequestMapping(value = "chooseAdministrateFunction", method = RequestMethod.POST)
    public ModelAndView chooseAdministrate(ModelAndView mav, @RequestParam("chooseId") String id, @ModelAttribute("makeNewClassAttribute") makeNewClass makeNewClassAttribute, @ModelAttribute("makeAdmin") MakeAdmin makeAdmin, BindingResult error, Model modell, @ModelAttribute("addNewClassIdAttribute") AddNewClassId addNewClassIdAttribute, @ModelAttribute NewPassword newPassword,HttpSession session) {
        User user = (User)session.getAttribute("user");
        String email = user.getEmail(); 
        modell.addAttribute("user", user);
        if(email.equals("GUEST")){
            mav.addObject("chooseSite", 5);
            mav.setViewName("administrateAccount");
            return mav; 
        }
        
        int a = Integer.parseInt(id); 
        mav.addObject("chooseSite", a);
        mav.setViewName("administrateAccount");
        return mav;    
    }
}


    
    
    /*
    Dette er flyttet til Login Controller. 
    @RequestMapping(value = "newPassword")
    public String newPassword(@ModelAttribute NewPassword newPassword, Model modell, @ModelAttribute("addNewClassIdAttribute") AddNewClassId addNewClassIdAttribute) {
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
        //Endret til Index fra 
        return "index";
    }
    
    */


