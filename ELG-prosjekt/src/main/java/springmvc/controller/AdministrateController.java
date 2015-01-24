package springmvc.controller;

import java.util.ArrayList;
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
    
    @Autowired
    private ResultService r;
    
    @ExceptionHandler(Throwable.class)
    public String handleTException(Throwable t, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (!(user.isInLogged())){
            return "login";
        }
        return "error";
    } 

    @ExceptionHandler(Exception.class)
    public String handleException(Throwable t, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (!(user.isInLogged())){
            return "login";
        }
        return "error";
    }

    @RequestMapping(value = "administrateAccount", method = RequestMethod.GET)
    public String adminAccount(@ModelAttribute NewPassword newPassword, @ModelAttribute("addNewClassIdAttribute") AddNewClassId addNewClassIdAttribute, @ModelAttribute("makeNewClassAttribute") makeNewClass makeNewClassAttribute, @ModelAttribute("makeAdmin") MakeAdmin makeAdmin, HttpSession session, @ModelAttribute Login login, Model modell) {
        User user = (User) session.getAttribute("user");
        modell.addAttribute("user", user);
        ArrayList<String> list = r.getAllClasses(user.getEmail());
        modell.addAttribute("list", list); 
        if (user == null) {
            return "firstLogin";
        }
        if (user.getEmail().equals("GUEST")) {
            return "about";
        }
        return "administrateAccount";
    }

    @RequestMapping(value = "changePassword", method = RequestMethod.POST)
    public String changePass(@Valid @ModelAttribute NewPassword newPassword, BindingResult error, Model modell, @ModelAttribute("makeNewClassAttribute") makeNewClass makeNewClassAttribute, @ModelAttribute("addNewClassIdAttribute") AddNewClassId addNewClassIdAttribute, @ModelAttribute("makeAdmin") MakeAdmin makeAdmin, HttpSession session) {
        if (error.hasErrors()) {
            modell.addAttribute("changedPassword", "Feil. Husk at passordene må være lengre enn 8 tegn.");
            modell.addAttribute("chooseSite", 1);
            return "administrateAccount";
        }
        User user = (User) session.getAttribute("user");
        Person inLoggedPerson = personService.getPerson(user.getEmail());
        modell.addAttribute("user", user);

        if (personService.changePassword(
                inLoggedPerson,
                newPassword.getOldPw(),
                newPassword.getNewPw(),
                newPassword.getConfirmPw()
        )) {
            modell.addAttribute("changedPassword", "Passordet er endret.");
        } else {
            modell.addAttribute("chooseSite", 1);
            modell.addAttribute("changedPassword", "Gammelt passord er feil.");
        }
        return "administrateAccount";
    }

    @RequestMapping(value = "addClassId", method = RequestMethod.POST)
    public String addNewClassId(@ModelAttribute("addNewClassIdAttribute") AddNewClassId addNewClassIdAttribute, BindingResult error, @ModelAttribute("makeNewClassAttribute") makeNewClass makeNewClassAttribute, Model modell, @ModelAttribute NewPassword newPassword, @ModelAttribute("makeAdmin") MakeAdmin makeAdmin, HttpSession session) {

        if (error.hasErrors()) {
            modell.addAttribute("chooseSite", 2);
            modell.addAttribute("NewClassMessage", "Feil, for få tegn"); 
            return "administrateAccount";
        }

        User user = (User) session.getAttribute("user");
        Person inLoggedPerson = personService.getPerson(user.getEmail());

        if (personService.setClassId(inLoggedPerson, addNewClassIdAttribute.getClassId())) { // A user is registered in a class. 
            ArrayList<String> list = r.getAllClasses(user.getEmail());
            modell.addAttribute("list", list); 
            modell.addAttribute("NewClassMessage", "Du er nå registrert i klasse: " + addNewClassIdAttribute.getClassId());
        } else { 
            modell.addAttribute("chooseSite", 2);
            modell.addAttribute("NewClassMessage", "Feil. Klassen eksisterer ikke eller du er registrert i den fra før ");
        }
        modell.addAttribute("user", user);
        return "administrateAccount";
    }

    @RequestMapping(value = "makeNewAdmin", method = RequestMethod.POST)
    public String makeNewAdmin(@ModelAttribute("makeAdmin") MakeAdmin makeAdmin, BindingResult error, @ModelAttribute("makeNewClassAttribute") makeNewClass makeNewClassAttribute, Model modell, @ModelAttribute("addNewClassIdAttribute") AddNewClassId addNewClassIdAttribute, @ModelAttribute NewPassword newPassword, HttpSession session) {
        if (error.hasErrors()) {
            modell.addAttribute("chooseSite", 3);
            return "administrateAccount";
        }

        User user = (User) session.getAttribute("user");
        Person inLoggedPerson = personService.getPerson(user.getEmail());
        modell.addAttribute("user", user);

        if (personService.makeAdmin(inLoggedPerson, makeAdmin.getMakeAdminPw())) { // User is registered as admin 
            user.setAdmin(true);
            modell.addAttribute("makeAdminMessage", "Du har nå admin-rettigheter. ");
        } else {
            modell.addAttribute("chooseSite", 3);
            modell.addAttribute("makeAdminMessage", "Feil. Administrator-passordet var ikke riktig. ");
        }
        return "administrateAccount";
    }

    @RequestMapping(value = "makeClass", method = RequestMethod.POST)
    public String makeClass(@ModelAttribute("makeNewClassAttribute") makeNewClass makeNewClassAttribute, BindingResult error, @ModelAttribute("makeAdmin") MakeAdmin makeAdmin, Model modell, @ModelAttribute("addNewClassIdAttribute") AddNewClassId addNewClassIdAttribute, @ModelAttribute NewPassword newPassword, HttpSession session) {
        User user = (User) session.getAttribute("user");
        modell.addAttribute("user", user);
        
        if (error.hasErrors()) {
            modell.addAttribute("chooseSite", 4);
            return "administrateAccount";
        }

        if (classService.registrateNewClassId(makeNewClassAttribute.getClassId())) { // New class is being registered. 
            modell.addAttribute("makeClassMessage", "Ny klasse ble registrert.");
        } else { // Feiler med å registrere ny klasse  
            modell.addAttribute("chooseSite", 4);
            modell.addAttribute("makeClassMessage", "Feil, Klassen finnes fra før.");
        }
        return "administrateAccount";
    }

    @RequestMapping(value = "chooseAdministrateFunction", method = RequestMethod.POST)
    public ModelAndView chooseAdministrate(ModelAndView mav, @RequestParam("chooseId") String id, @ModelAttribute("makeNewClassAttribute") makeNewClass makeNewClassAttribute, @ModelAttribute("makeAdmin") MakeAdmin makeAdmin, BindingResult error, Model modell, @ModelAttribute("addNewClassIdAttribute") AddNewClassId addNewClassIdAttribute, @ModelAttribute NewPassword newPassword, HttpSession session) {
        User user = (User) session.getAttribute("user");
        String email = user.getEmail();
        modell.addAttribute("user", user);
        if (email.equals("GUEST")) {
            mav.addObject("chooseSite", 5);
            mav.setViewName("administrateAccount");
            return mav;
        }

        int a = Integer.parseInt(id);
        mav.addObject("chooseSite", a);
        mav.setViewName("administrateAccount");
        return mav;
    }

    @RequestMapping(value = "removeClass", method = RequestMethod.POST)
    public ModelAndView removeClass(ModelAndView mav, @RequestParam("classid") String id, HttpSession session){
        User user = (User) session.getAttribute("user");
        String email = user.getEmail();
        r.deleteClass(id, email);
        mav.setViewName("about");
        return mav;
    }
}