package springmvc.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import springmvc.domain.CreateMultiExercise;
import springmvc.domain.Exercise;
import springmvc.domain.HighscoreDisplay;
import springmvc.domain.MultiChoice;
import springmvc.domain.MultiChoiceInfo;
import springmvc.domain.ResembleGame;
import springmvc.domain.User;
import springmvc.service.GameListService;
import springmvc.service.MultiChoiceService;
import springmvc.service.ResultService;

@Controller
@SessionAttributes({"spillet", "createExercise", "Exercises", "MultiGame"})
public class MultiChoiceController {

    @Autowired
    private MultiChoiceService s;

    @Autowired
    private ResultService r;
    
    @Autowired 
    private GameListService gameListService;
    
    @ExceptionHandler(Throwable.class)
    public String handleTException(Throwable t) {
        return "error";
    } 

    @ExceptionHandler(Exception.class)
    public String handleException(Throwable t) {
        return "error";
    }
    
    @RequestMapping(value = "multi", method = RequestMethod.POST)
    public String showMultiChoice(Model model, @RequestParam("gamename") String name, @RequestParam("othergame")String otherGame) {
        MultiChoice mc = s.getMultiChoice(name);
        mc.initMC();
        model.addAttribute("spillet", mc);
        if(otherGame.equals("othergame")){
            model.addAttribute("isOving", 0);
            return "othermultichoice";
        } 
        model.addAttribute("isOving", 1);
        return "multichoice";
    }

    @RequestMapping(value = "createmulti")
    public ModelAndView showCreateMultiChoice(ModelAndView mav) {
        MultiChoice game = new MultiChoice();
        ArrayList<Exercise> exercises = new ArrayList<>();
        Exercise e = new Exercise();
        CreateMultiExercise ce = new CreateMultiExercise();
        mav.addObject("MultiGame", game);
        mav.addObject("Exercises", exercises);
        int n = 0;
        mav.addObject("createExercise", ce);
        mav.setViewName("createmulti");
        return mav;
    }

    @RequestMapping(value = "createmulti", method = RequestMethod.POST)
    public ModelAndView showCreateMultiChoice(ModelAndView mav, @ModelAttribute(value = "Exercises") ArrayList<Exercise> exercises, @ModelAttribute(value = "createExercise") CreateMultiExercise ce) {
        mav.setViewName("createmulti");
        return mav;
    }

    @RequestMapping(value = "createExercise", method = RequestMethod.POST)
    public ModelAndView createExercise(ModelAndView mav, @ModelAttribute(value = "Exercises") ArrayList<Exercise> exercises, @Valid @ModelAttribute(value = "createExercise") CreateMultiExercise ce, BindingResult error) {
        if (error.hasErrors()) {
            mav.setViewName("createmulti");
            return mav;
        }
        String[] t = {ce.getAlt1(), ce.getAlt2(), ce.getAlt3(), ce.getAlt4()};
        Exercise e = new Exercise(t, ce.getAnswer(), ce.getTaskText());
        exercises.add(e);
        ce = new CreateMultiExercise();
        mav.addObject("createExercise", ce);
        mav.setViewName("createmulti");
        return mav;
    }

    @RequestMapping(value = "submitgame", method = RequestMethod.POST)
    public ModelAndView makeMultiChoiceGame(ModelAndView mav, HttpSession session, HttpServletRequest request, @ModelAttribute(value = "Exercises") ArrayList<Exercise> exercises, @Valid @ModelAttribute(value = "MultiGame") MultiChoice game1, BindingResult error) {
        if (error.hasErrors()) {;
            mav.setViewName("createmulti");
            return mav;
        }
        if (exercises.isEmpty()) {
            String er = "Du må minst legge til ett spørsmål";
            mav.addObject("ErrorMessage", er);
            mav.setViewName("createmulti");
            return mav;
        }
        MultiChoice check = s.getMultiChoice(game1.getName()); // FINNER IKKE NOE SPILL HER! 
        if(check != null){
            String er = "Et spill med dette navnet finnes fra før. Vennligst velg et annet.";
            mav.addObject("ErrorMessage", er);
            mav.setViewName("createmulti");
            return mav;
        }
        User user = (User) session.getAttribute("user");
        game1.setExercises(exercises);
        game1.setCreator(user.getEmail());
        s.regMultiChoiceGame(game1);
        mav.setViewName("about");
        return mav;
    }

    @RequestMapping(value = "nextTask", method = RequestMethod.POST)
    public String nextTask(HttpSession session, Model model, @ModelAttribute(value = "spillet") MultiChoice mc, String value, HttpServletRequest request, @RequestParam("othergame")String gameType) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "firstLogin";
        }
        if (request.getParameter("button") != null) {
            String button = request.getParameter("button");
            mc.setResult(mc.current(), mc.getCurrent().checkAnswer(button));
        }
        mc.getNextExercise();       
        if (mc.lastExercise() == true) { // Siste oppgave. 
            model.addAttribute("eachresult", mc.getEachResult());
            model.addAttribute("result", mc.getResult());
            //******DETTE HER HER FOR Å REGISTRERE RESULTAT I DATABASEN!!!*****
            Double score = mc.getResult();
            String k = user.getEmail();
            int d = r.getMultiChoiceRes(k, mc);
            if (d == -1) {
                r.regMultiChoiceRes(k, score, mc);
            } else if (score > d) {
                r.updateMultiResult(k, score, mc);
            }
            ArrayList<HighscoreDisplay> hs = r.highscoreMC(mc);
            String melding = "";
            for (int i = 0; i < hs.size(); i++) {
                melding += hs.get(i).getFname() + " " + hs.get(i).getLname() + " " + hs.get(i).getScore() + "\n";
            }
            model.addAttribute("highscorelist", melding);
            mc.resetCurrent();          
            if (gameType.equals("othergame,othergame,othergame,othergame")){
                int resemble = 0;
                ArrayList<ResembleGame> resembleGames = gameListService.getAllResembleGamesNotInOving();
                ArrayList<MultiChoiceInfo> multiChoiceGames = gameListService.getAllMultiGamesNotInOving();
                ArrayList<MultiChoiceInfo> multiChoiceGamesWithApproved = gameListService.updateApprovedMultiChoiceGames(multiChoiceGames, user);
                ArrayList<ResembleGame> resembleGamesWithApproved = gameListService.updateApprovedResembleGames(resembleGames, user);
                model.addAttribute("gametype", resemble);
                model.addAttribute("resembleGames", resembleGames);
                model.addAttribute("multiChoiceGames", multiChoiceGames);
                return "otherresult";  
            } else {
                ArrayList<ResembleGame> resembleGames = gameListService.getAllResembleGamesFromOving();
                ArrayList<MultiChoiceInfo> multiChoiceGames = gameListService.getAllMultiChoiceInfoFromOving();
                ArrayList<ResembleGame> resembleGamesExtra = gameListService.getAllResembleGamesFromOvingExtra();
                ArrayList<MultiChoiceInfo> multiChoiceGamesExtra = gameListService.getAllMultiChoiceInfoFromOvingExtra();
                ArrayList<MultiChoiceInfo> multiChoiceGamesWithApproved = gameListService.updateApprovedMultiChoiceGames(multiChoiceGames, user);
                ArrayList<ResembleGame> resembleGamesWithApproved = gameListService.updateApprovedResembleGames(resembleGames, user);
                ArrayList<MultiChoiceInfo> multiChoiceGamesWithApprovedExtra = gameListService.updateApprovedMultiChoiceGames(multiChoiceGamesExtra, user);
                ArrayList<ResembleGame> resembleGamesWithApprovedExtra = gameListService.updateApprovedResembleGames(resembleGamesExtra, user);
                int resemble = 0;
                model.addAttribute("gametype", resemble);
                model.addAttribute("resembleGames", resembleGames);
                model.addAttribute("multiChoiceGames", multiChoiceGames);
                model.addAttribute("resembleGamesExtra", resembleGamesExtra);
                model.addAttribute("multiChoiceGamesExtra", multiChoiceGamesExtra);
                return "result";
            }
        }      
        if (gameType.equals("othergame,othergame,othergame,othergame")){
            model.addAttribute("isOving", 0); 
            return "othermultichoice";  
        } else {
            model.addAttribute("isOving", 1);
            return "multichoice";
        }
    }
}
