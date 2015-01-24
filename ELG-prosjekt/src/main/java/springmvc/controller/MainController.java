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
import springmvc.domain.HighscoreDisplay;
import springmvc.domain.Login;
import springmvc.domain.MultiChoice;
import springmvc.domain.MultiChoiceInfo;
import springmvc.domain.Person;
import springmvc.domain.ResembleGame;
import springmvc.domain.ResembleTask;
import springmvc.domain.User;
import springmvc.service.GameListService;
import springmvc.service.LoginService;
import springmvc.service.PersonService;
import springmvc.service.ResembleTaskService;
import springmvc.service.ResultService;
import springmvc.ui.SendNewPassword;

@Controller
public class MainController {
    @Autowired
    private GameListService gameListService;
    
     @Autowired
    private ResembleTaskService resembleTaskService; 
   
    @Autowired
    private ResultService r;

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
    
    @RequestMapping(value = "*")
    public String person(@ModelAttribute Login login, @ModelAttribute("sendNewPassword") SendNewPassword sendNewPassword, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "firstLogin";
        }
        return "about"; 
    }

    @RequestMapping(value = "about")
    public String showAbout(Model model, HttpSession session, @ModelAttribute Login login) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "firstLogin";
        } else if (!user.isInLogged()) {
            return "notloggedin";
        }
        return "about";
    }

    @RequestMapping(value = "creategame")
    public String showCreateGame(Model model, HttpSession session, @ModelAttribute Login login) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "firstLogin";
        } else if (!user.isInLogged()) {
            return "notloggedin";
        }
        return "creategame";
    }
    
    @RequestMapping(value = "choosegame") 
    public ModelAndView chooseGame(ModelAndView mav, HttpSession session, @ModelAttribute Login login) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            mav.setViewName("error");
            return mav;
        }
        ArrayList<ResembleGame> resembleGames = gameListService.getAllResembleGamesFromOving();
        ArrayList<MultiChoiceInfo> multiChoiceGames = gameListService.getAllMultiChoiceInfoFromOving();
        ArrayList<ResembleGame> resembleGamesExtra = gameListService.getAllResembleGamesFromOvingExtra();
        ArrayList<MultiChoiceInfo> multiChoiceGamesExtra = gameListService.getAllMultiChoiceInfoFromOvingExtra();
        ArrayList<MultiChoiceInfo> multiChoiceGamesWithApproved = gameListService.updateApprovedMultiChoiceGames(multiChoiceGames, user);
        ArrayList<ResembleGame> resembleGamesWithApproved = gameListService.updateApprovedResembleGames(resembleGames, user);
        ArrayList<MultiChoiceInfo> multiChoiceGamesWithApprovedExtra = gameListService.updateApprovedMultiChoiceGames(multiChoiceGamesExtra, user);
        ArrayList<ResembleGame> resembleGamesWithApprovedExtra = gameListService.updateApprovedResembleGames(resembleGamesExtra, user);
        int resemble = 0;
        mav.addObject("gametype", resemble);
        mav.addObject("resembleGames", resembleGames);
        mav.addObject("multiChoiceGames", multiChoiceGames);
        mav.addObject("resembleGamesExtra", resembleGamesExtra);
        mav.addObject("multiChoiceGamesExtra", multiChoiceGamesExtra);
        mav.setViewName("chooseGame");
        return mav;
    }
    
    @RequestMapping(value = "choosegame", method = RequestMethod.POST)
    public ModelAndView chooseGame(ModelAndView mav, @RequestParam("gameid") String id, @RequestParam("gametype") String gametype, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            mav.setViewName("error");
            return mav;
        }
        int resemble = 0;
        MultiChoiceInfo multiTemp = null;
        ResembleGame resembleTemp = null;
        if(gametype.equals("resemble")){
            int a = Integer.parseInt(id);
            resemble = 1;
            resembleTemp = gameListService.getResembleGame(a);
            ArrayList<Integer> task_numbers = resembleTemp.getTaskNumbers();
            ArrayList<ResembleTask> temp_tasks = gameListService.getResembleTasks(task_numbers);
            mav.addObject("tasks", temp_tasks);
            mav.addObject("resembleInfo", resembleTemp);
        } else if(gametype.equals("multichoice")){
            resemble = 2;
            System.out.println("ID " + id);
            multiTemp = gameListService.getMultiChoiceInfo(id);
            mav.addObject("multiChoiceInfo", multiTemp);
            System.out.println("MultiChoice");
        }
        ArrayList<ResembleGame> resembleGames = gameListService.getAllResembleGamesFromOving();
        ArrayList<MultiChoiceInfo> multiChoiceGames = gameListService.getAllMultiChoiceInfoFromOving();
        ArrayList<ResembleGame> resembleGamesExtra = gameListService.getAllResembleGamesFromOvingExtra();
        ArrayList<MultiChoiceInfo> multiChoiceGamesExtra = gameListService.getAllMultiChoiceInfoFromOvingExtra();
        ArrayList<ResembleGame> resembleGamesWithApproved = gameListService.updateApprovedResembleGames(resembleGames, user);
        ArrayList<MultiChoiceInfo> multiChoiceGamesWithApproved = gameListService.updateApprovedMultiChoiceGames(multiChoiceGames, user);
        mav.addObject("resembleGames", resembleGames);
        mav.addObject("multiChoiceGames", multiChoiceGames);
        mav.addObject("resembleGamesExtra", resembleGamesExtra);
        mav.addObject("multiChoiceGamesExtra", multiChoiceGamesExtra);
        mav.addObject("gametype", resemble);
        mav.addObject("gamenr", id);
        mav.setViewName("chooseGame");
        return mav;
    }

    @RequestMapping(value = "votegame", method = RequestMethod.POST)
    public ModelAndView voteResembleGame(ModelAndView mav, @RequestParam("gameid") String id, @RequestParam("gametype") String gametype, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if(gametype.equals("resemble")){
            int gameid = Integer.parseInt(id);
            gameListService.registerResembleGameVote(user.getEmail(), gameid);
        }else if(gametype.equals("multichoice")){
            gameListService.registerMultiGameVote(user.getEmail(), id);
        }
        return chooseOtherGames(mav, session);
    }
    
    @RequestMapping(value = "chooseothergames")
    public ModelAndView chooseOtherGames(ModelAndView mav, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            mav.setViewName("error");
            return mav;
        }
        ArrayList<ResembleGame> resembleGames = gameListService.getAllResembleGamesNotInOving();
        ArrayList<MultiChoiceInfo> multiChoiceGames = gameListService.getAllMultiGamesNotInOving();//not in oving
        ArrayList<MultiChoiceInfo> multiChoiceGamesWithApproved = gameListService.updateApprovedMultiChoiceGames(multiChoiceGames, user);
        ArrayList<ResembleGame> resembleGamesWithApproved = gameListService.updateApprovedResembleGames(resembleGames, user);
        int resemble = 0;
        mav.addObject("gametype", resemble);
        mav.addObject("resembleGames", resembleGames);
        mav.addObject("multiChoiceGames", multiChoiceGames);
        mav.setViewName("chooseothergame");
        return mav;
    }

    @RequestMapping(value = "chooseothergames", method = RequestMethod.POST)
    public ModelAndView chooseOtherGames(ModelAndView mav, @RequestParam("gameid") String id, @RequestParam("gametype") String gametype, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            mav.setViewName("error");
            return mav;
        }
        int resemble = 0;
        MultiChoiceInfo multiTemp = null;
        ResembleGame resembleTemp = null;
        if(gametype.equals("resemble")){
            int a = Integer.parseInt(id);
            resemble = 1;
            resembleTemp = gameListService.getResembleGame(a);
            ArrayList<Integer> task_numbers = resembleTemp.getTaskNumbers();
            ArrayList<ResembleTask> temp_tasks = gameListService.getResembleTasks(task_numbers);
            mav.addObject("tasks", temp_tasks);
            mav.addObject("resembleInfo", resembleTemp);
        }else if(gametype.equals("multichoice")){
            resemble = 2;
            multiTemp = gameListService.getMultiChoiceInfo(id);
            mav.addObject("multiChoiceInfo", multiTemp);
        }
        mav.addObject("gametype", resemble);
        ArrayList<ResembleGame> resembleGames = gameListService.getAllResembleGamesNotInOving();
        ArrayList<MultiChoiceInfo> multiChoiceGames = gameListService.getAllMultiGamesNotInOving();//not in oving
        ArrayList<ResembleGame> resembleGamesWithApproved = gameListService.updateApprovedResembleGames(resembleGames, user);
        ArrayList<MultiChoiceInfo> multiChoiceGamesWithApproved = gameListService.updateApprovedMultiChoiceGames(multiChoiceGames, user);
        mav.addObject("gamenr", id);
        mav.addObject("resembleGames", resembleGames);
        mav.addObject("multiChoiceGames", multiChoiceGames);
        mav.setViewName("chooseothergame");
        return mav;
    }
    
    @RequestMapping(value = "highscore", method = RequestMethod.GET)
    public ModelAndView chooseGameHighscore(ModelAndView mav, HttpSession session, @ModelAttribute Login login) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            mav.setViewName("firstLogin");
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

    @RequestMapping(value = "highscore", method = RequestMethod.POST)
    public ModelAndView chooseGameHighscore(ModelAndView mav, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            mav.setViewName("firstLogin");
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
        if (error.hasErrors()) {
            mav.setViewName("firstLogin");
            return mav;
        }
        if (loginService.compareInformation(login)) {
            Person inloggedPerson = personService.getPerson(login.getEmail());
            User user = new User(inloggedPerson.getEmail(), inloggedPerson.getFname(), inloggedPerson.getLname(), inloggedPerson.getTeacher());
            user.setInLogged(true);
            session.setAttribute("user", user);
            mav.setViewName("chooseGameHighscore");
            return chooseGameHighscore(mav, session);
        } else {
            modell.addAttribute("wrongPassword", "Feil brukernavn/passord. Prøv på nytt");
            mav.setViewName("firstLogin");
            return mav;
        }
    }

    @RequestMapping(value = "choosegameHighscore", method = RequestMethod.POST)
    public ModelAndView chooseGameHighscore(ModelAndView mav, @RequestParam("gameid") String id, @RequestParam("gametype") String gametype) {
        int resemble = 0;
        MultiChoice multiTemp = null;
        ResembleGame resembleTemp = null;
        ArrayList<HighscoreDisplay> hs = new ArrayList<HighscoreDisplay>();
        if(gametype.equals("resemble")){
            int a = Integer.parseInt(id);
            resemble = 1;
            resembleTemp = gameListService.getResembleGame(a);
            hs = r.highscoreRG(resembleTemp);
            mav.addObject("highscore", hs);
        } else if(gametype.equals("multichoice")){
            resemble = 2;
            multiTemp = gameListService.getMultiChoiceGame(id);
            hs = r.highscoreMC(multiTemp);
            mav.addObject("highscore", hs);
        }
        mav.addObject("gametype", resemble);
        ArrayList<ResembleGame> resembleGames = gameListService.getAllResembleGames();
        ArrayList<MultiChoiceInfo> multiChoiceGames = gameListService.getAllMultiChoiceInfo();
        mav.addObject("gamenr", id);
        mav.addObject("sortedScores", r.sortHighScores(hs));
        mav.addObject("resembleGames", resembleGames);
        mav.addObject("multiChoiceGames", multiChoiceGames);
        mav.setViewName("chooseGameHighscore");
        return mav;
    }

    @RequestMapping(value = "loginAsGuest")
    public ModelAndView loginAsGuestFunction(ModelAndView mav, HttpSession session) {
        User user = new User("GUEST", "GUEST", "");
        user.setInLogged(true);
        session.setAttribute("user", user);
        return chooseGameHighscore(mav, session);
    }

    @RequestMapping(value = "completionlist")
    public ModelAndView chooseGameCompletionlist(ModelAndView mav, HttpSession session, @ModelAttribute Login login) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            mav.setViewName("firstLogin");
            return mav;
        }
        if (!user.isAdmin()) {
            mav.setViewName("about");
            return mav;
        }
        ArrayList<String> list = r.getAllClasses(user.getEmail());
        mav.addObject("allClasses", list);
        mav.setViewName("completionlist");
        return mav;
    }

    @RequestMapping(value = "choosegameCompletionlist", method = RequestMethod.POST)
    public ModelAndView chooseGameCompletionlist(ModelAndView mav, @RequestParam("classid") String id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            mav.setViewName("firstLogin");
            return mav;
        }
        if (!user.isAdmin()) {
            mav.setViewName("about");
            return mav;
        }
        ArrayList<HighscoreDisplay> hs = new ArrayList<HighscoreDisplay>();
        hs = r.getCompleteCompletion(id);
        if (hs.size() == 0) {
            String s = "Ingen studenter har bestått";
            mav.addObject("nopass", s);
        }
        mav.addObject("classid", id);
        mav.addObject("list", hs);
        int nc = (r.getNumberInClass(id) - (hs.size()));
        mav.addObject("notcompleted", nc);
        ArrayList<String> list = r.getAllClasses(user.getEmail());
        mav.addObject("allClasses", list);
        mav.setViewName("completionlist");
        return mav;
    }
    
    @RequestMapping(value = "kOdesLostTags")
    public String kOdesLostTags(ModelAndView mav, HttpSession session, @ModelAttribute Login login) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "firstLogin";
        }
        return "kOdesLostTags";
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public ModelAndView logout(ModelAndView mav, HttpSession session, @ModelAttribute("login") Login login) {
        if (session != null) {
            session.invalidate();
        }
        mav.setViewName("firstLogin");
        return mav;
    }
    
    @RequestMapping(value = "movegame", method = RequestMethod.POST)
    public ModelAndView moveResembleGame(ModelAndView mav, @RequestParam("gameid") String id, @RequestParam("button") String button, @RequestParam("gametype") String gametype, HttpSession session){
        if(gametype.equals("resemble")){
            int gameid = Integer.parseInt(id);
            ResembleGame resembleGame = gameListService.getResembleGame(gameid);
            if(button.equals("makeextra")){
                gameListService.makeResembleGameExerciseExtra(gameid); 
            }else if(button.equals("makeexercise")){
                gameListService.makeResembleGameExercise(gameid);
            }else if(button.equals("removeexercise")){
                gameListService.removeResembleGameFromExercise(gameid);
            }
            mav.addObject("resembleGame", resembleGame);
            mav.addObject("resembleTask", resembleTaskService.getResembleTask(resembleGame.getCurrentTask())); 
        } else if (gametype.equals("multichoice")){
            if(button.equals("makeextra")){
                gameListService.makeMultiGameExerciseExtra(id); 
            }else if(button.equals("makeexercise")){
                gameListService.makeMultiGameExercise(id);
            }else if(button.equals("removeexercise")){
                gameListService.removeMultiGameFromExercise(id);
            }
        }
        mav.setViewName("about");
        return mav;           
    }
}