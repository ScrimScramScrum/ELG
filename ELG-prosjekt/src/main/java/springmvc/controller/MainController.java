/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.controller;

import java.util.ArrayList;
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
import springmvc.domain.HighscoreDisplay;
import springmvc.domain.Login;
import springmvc.domain.MultiChoice;
import springmvc.domain.MultiChoiceInfo;
import springmvc.domain.Person;
import springmvc.domain.ResembleGame;
import springmvc.domain.ResembleTask;
import springmvc.domain.User;
import springmvc.service.GameListService;
import springmvc.service.GameListServiceImpl;
import springmvc.service.LoginService;
import springmvc.service.PersonService;
import springmvc.service.ResultService;
import springmvc.ui.SendNewPassword;

/**
 *
 * @author Jorgen
 */
@Controller
public class MainController {

    @Autowired
    private GameListService gameListService;

    @Autowired
    private ResultService r;

    @Autowired
    private LoginService loginService;

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "index")
    public String showIndex(Model model) {
        //model.addAttribute("melding", "melding");
        return "firstlogin";
    }

    @RequestMapping(value = "*")
    public String person(@ModelAttribute Login login, @ModelAttribute("sendNewPassword") SendNewPassword sendNewPassword) {
        return "firstLogin";
    }

    @RequestMapping(value = "about")
    public String showAbout(Model model, HttpSession session) {
        User user = (User)session.getAttribute("user");
        if(user == null){
            return "notloggedin";
        } else if(!user.isInLogged()){
            return "notloggedin";
        }
        //model.addAttribute("melding", "melding");
        return "about";
    }

    /*
     @RequestMapping(value = "highscore")
     public String showHighscore(Model model, HttpSession session) {
     //model.addAttribute("melding", "melding");
     User user = (User) session.getAttribute("user");
     if (user == null) {
     System.out.println("user = null");
     }
     return ((User) session.getAttribute("user")) == null ? "index" : ((User) session.getAttribute("user")).isInLogged() ? "highscore" : "index";
     // return ((User)session.getAttribute("user")).isInLogged() ? "highscore" : "index"; 
     // return "highscore"; 
     }*/
    @RequestMapping(value = "choosegame")
    public ModelAndView chooseGame(ModelAndView mav, HttpSession session) {
        User user = (User)session.getAttribute("user");
        System.out.println("chooseGame");
        if(user == null){
            mav.setViewName("notloggedin");
            return mav;
        } else if(!user.isInLogged()){
            mav.setViewName("notloggedin");
            return mav;
        }
        ArrayList<ResembleGame> resembleGames = gameListService.getAllResembleGames();
        ArrayList<MultiChoiceInfo> multiChoiceGames = gameListService.getAllMultiChoiceInfo();
        int resemble = 0;
        mav.addObject("gametype", resemble);
        mav.addObject("resembleGames", resembleGames);
        mav.addObject("multiChoiceGames", multiChoiceGames);
        mav.setViewName("chooseGame");
        return mav;
    }

    @RequestMapping(value = "choosegame", method = RequestMethod.POST)
    public ModelAndView chooseGame(ModelAndView mav, @RequestParam("gameid") String id, HttpSession session) {
        User user = (User)session.getAttribute("user");
        if(user == null){
            mav.setViewName("notloggedin");
            return mav;
        } else if(!user.isInLogged()){
            mav.setViewName("notloggedin");
            return mav;
        }
        int resemble = 0;
        String info = "test...";
        MultiChoiceInfo multiTemp = null;
        ResembleGame resembleTemp = null;
        try {
            int a = Integer.parseInt(id);
            resemble = 1;
            resembleTemp = gameListService.getResembleGame(a);
            ArrayList<Integer> task_numbers = resembleTemp.getTaskNumbers();
            ArrayList<ResembleTask> temp_tasks = gameListService.getResembleTasks(task_numbers);
            mav.addObject("tasks", temp_tasks);
            mav.addObject("resembleInfo", resembleTemp);
            // add info here
        } catch (NumberFormatException e) {
            resemble = 2;
            multiTemp = gameListService.getMultiChoiceInfo(id);
            mav.addObject("multiChoiceInfo", multiTemp);
            // or info here
            System.out.println("MultiChoice");
        }
        mav.addObject("gametype", resemble);
        // use session instead of getting all games every time a game get clicked?
        ArrayList<ResembleGame> resembleGames = gameListService.getAllResembleGames();
        ArrayList<MultiChoiceInfo> multiChoiceGames = gameListService.getAllMultiChoiceInfo();
        mav.addObject("gamenr", id);
        //mav.addObject("info", info);
        mav.addObject("resembleGames", resembleGames);
        mav.addObject("multiChoiceGames", multiChoiceGames);
        mav.setViewName("chooseGame");
        return mav;
    }

    @RequestMapping(value = "highscore")
    public ModelAndView chooseGameHighscore(ModelAndView mav, HttpSession session) {
        User user = (User)session.getAttribute("user");
        if(user == null){
            mav.setViewName("notloggedin");
            return mav;
        } else if(!user.isInLogged()){
            mav.setViewName("notloggedin");
            return mav;
        }
        ArrayList<ResembleGame> resembleGames = gameListService.getAllResembleGames();
        ArrayList<MultiChoiceInfo> multiChoiceGames = gameListService.getAllMultiChoiceInfo();
        int resemble = 0;
        mav.addObject("gametype", resemble);
        mav.addObject("resembleGames", resembleGames);
        mav.addObject("multiChoiceGames", multiChoiceGames);
        mav.setViewName("chooseGameHighscore");
        return mav;
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ModelAndView logIn(ModelAndView mav, HttpSession session, @Valid @ModelAttribute("login") Login login, BindingResult error, Model modell, @ModelAttribute("sendNewPassword") SendNewPassword sendNewPassword) {
        System.out.println("333333333");
        if (error.hasErrors()) {
            System.out.println(" Passord tomt, eller ikke gyldig Email-adresse.  ");
            //modell.addAttribute("melding", "Personnr ikke fylt ut riktig"); 
            mav.setViewName("firstLogin");
            return mav;
        }

        if (loginService.compareInformation(login)) {
            Person inloggedPerson = personService.getPerson(login.getEmail());
            User user = new User(inloggedPerson.getEmail(), inloggedPerson.getFname(), inloggedPerson.getLname(), inloggedPerson.getTeacher());
            user.setInLogged(true);
            session.setAttribute("user", user);
            mav.setViewName("chooseGameHighscore");
            System.out.println("Highscore er satt **********************************");
            return chooseGameHighscore(mav, session);
        } else {
            System.out.println("Innlogging feilet.  ");
            modell.addAttribute("wrongPassword", "Feil brukernavn/passord. Prøv på nytt");
            mav.setViewName("firstLogin");
            return mav;
        }

    }

    @RequestMapping(value = "choosegameHighscore", method = RequestMethod.POST)
    public ModelAndView chooseGameHighscore(ModelAndView mav, @RequestParam("gameid") String id) {
        int resemble = 0;
        MultiChoice multiTemp = null;
        ResembleGame resembleTemp = null;
        ArrayList<HighscoreDisplay> hs = new ArrayList<HighscoreDisplay>();
        try {
            int a = Integer.parseInt(id);
            resemble = 1;
            resembleTemp = gameListService.getResembleGame(a);
            hs = r.highscoreRG(resembleTemp);
            mav.addObject("highscore", hs);
        } catch (NumberFormatException e) {
            resemble = 2;
            multiTemp = gameListService.getMultiChoiceGame(id);
            hs = r.highscoreMC(multiTemp);
            mav.addObject("highscore", hs);
        }
        mav.addObject("gametype", resemble);
        // use session instead of getting all games every time a game get clicked?
        ArrayList<ResembleGame> resembleGames = gameListService.getAllResembleGames();
        ArrayList<MultiChoiceInfo> multiChoiceGames = gameListService.getAllMultiChoiceInfo();
        mav.addObject("gamenr", id);
        //mav.addObject("info", info);
        mav.addObject("sortedScores", r.sortHighScores(hs));
        mav.addObject("resembleGames", resembleGames);
        mav.addObject("multiChoiceGames", multiChoiceGames);
        mav.setViewName("chooseGameHighscore");
        return mav;
    }

    @RequestMapping(value = "loginAsGuest")
    public ModelAndView loginAsGuestFunction(ModelAndView mav, HttpSession session) {
        System.out.println("Logger inn som guest");

        User user = new User("GUEST", "GUEST", "");
        user.setInLogged(true);
        session.setAttribute("user", user);
        return chooseGameHighscore(mav, session);
    }

    @RequestMapping(value = "completionlist")
    public ModelAndView chooseGameCompletionlist(ModelAndView mav, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (!user.isAdmin()) {
            mav.setViewName("about");
            return mav;
        }
        ArrayList<ResembleGame> resembleGames = gameListService.getAllResembleGames();
        ArrayList<MultiChoiceInfo> multiChoiceGames = gameListService.getAllMultiChoiceInfo();
        int resemble = 0;
        mav.addObject("gametype", resemble);
        mav.addObject("resembleGames", resembleGames);
        mav.addObject("multiChoiceGames", multiChoiceGames);
        mav.setViewName("completionlist");
        return mav;
    }

    @RequestMapping(value = "choosegameCompletionlist", method = RequestMethod.POST)
    public ModelAndView chooseGameCompletionlist(ModelAndView mav, @RequestParam("gameid") String id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (!user.isAdmin()) {
            mav.setViewName("about");
            return mav;
        }
        int resemble = 0;
        MultiChoice multiTemp = null;
        ResembleGame resembleTemp = null;
        ArrayList<HighscoreDisplay> hs = new ArrayList<HighscoreDisplay>();
        try {
            int a = Integer.parseInt(id);
            resemble = 1;
            resembleTemp = gameListService.getResembleGame(a);
            //hs = r.getCompletionRG(resembleTemp);
            if (hs.size() == 0) {
                String s = "Ingen studenter har bestått";
                mav.addObject("nopass", s);
            }
            mav.addObject("list", hs);
            System.out.println("***** lengde= " + hs.size());

        } catch (NumberFormatException e) {
            resemble = 2;
            multiTemp = gameListService.getMultiChoiceGame(id);
            //hs = r.getCompletion(multiTemp);
            if (hs.size() == 0) {
                String s = "Ingen studenter har bestått";
                mav.addObject("nopass", s);
            }
            mav.addObject("list", hs);
            System.out.println("********** lengde = " + hs.size());
        }
        mav.addObject("gametype", resemble);
        // use session instead of getting all games every time a game get clicked?
        ArrayList<ResembleGame> resembleGames = gameListService.getAllResembleGames();
        ArrayList<MultiChoiceInfo> multiChoiceGames = gameListService.getAllMultiChoiceInfo();
        mav.addObject("gamenr", id);
        //mav.addObject("info", info);
        mav.addObject("sortedScores", r.sortHighScores(hs));
        mav.addObject("resembleGames", resembleGames);
        mav.addObject("multiChoiceGames", multiChoiceGames);
        mav.setViewName("completionlist");
        return mav;
    }
    
    
    @RequestMapping(value = "kOdesLostTags")
    public String kOdesLostTags(ModelAndView mav, HttpSession session) {
        System.out.println("Starter K.Odes");

        
        return "kOdesLostTags";
    }
}
